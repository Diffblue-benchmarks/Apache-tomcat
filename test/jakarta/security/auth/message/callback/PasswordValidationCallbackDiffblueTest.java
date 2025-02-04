package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import javax.security.auth.Subject;
import org.junit.Test;

public class PasswordValidationCallbackDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PasswordValidationCallback#PasswordValidationCallback(Subject, String, char[])}
   *   <li>{@link PasswordValidationCallback#setResult(boolean)}
   *   <li>{@link PasswordValidationCallback#getPassword()}
   *   <li>{@link PasswordValidationCallback#getResult()}
   *   <li>{@link PasswordValidationCallback#getSubject()}
   *   <li>{@link PasswordValidationCallback#getUsername()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Subject subject = new Subject();
    char[] password = "AZAZ".toCharArray();

    // Act
    PasswordValidationCallback actualPasswordValidationCallback = new PasswordValidationCallback(subject, "janedoe",
        password);
    actualPasswordValidationCallback.setResult(true);
    char[] actualPassword = actualPasswordValidationCallback.getPassword();
    boolean actualResult = actualPasswordValidationCallback.getResult();
    Subject actualSubject = actualPasswordValidationCallback.getSubject();

    // Assert
    assertEquals("janedoe", actualPasswordValidationCallback.getUsername());
    assertTrue(actualResult);
    assertSame(subject, actualSubject);
    assertSame(password, actualPassword);
    assertArrayEquals("AZAZ".toCharArray(), actualPassword);
  }

  /**
   * Test {@link PasswordValidationCallback#clearPassword()}.
   * <p>
   * Method under test: {@link PasswordValidationCallback#clearPassword()}
   */
  @Test
  public void testClearPassword() {
    // Arrange
    Subject subject = new Subject();
    PasswordValidationCallback passwordValidationCallback = new PasswordValidationCallback(subject, "janedoe",
        "AZAZ".toCharArray());

    // Act
    passwordValidationCallback.clearPassword();

    // Assert
    assertArrayEquals(new char[]{}, passwordValidationCallback.getPassword());
  }
}
