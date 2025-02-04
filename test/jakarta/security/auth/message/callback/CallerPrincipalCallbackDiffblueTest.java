package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import javax.security.auth.Subject;
import org.junit.Test;

public class CallerPrincipalCallbackDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CallerPrincipalCallback#CallerPrincipalCallback(Subject, String)}
   *   <li>{@link CallerPrincipalCallback#getName()}
   *   <li>{@link CallerPrincipalCallback#getPrincipal()}
   *   <li>{@link CallerPrincipalCallback#getSubject()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenName_thenReturnName() {
    // Arrange
    Subject subject = new Subject();

    // Act
    CallerPrincipalCallback actualCallerPrincipalCallback = new CallerPrincipalCallback(subject, "Name");
    String actualName = actualCallerPrincipalCallback.getName();
    Principal actualPrincipal = actualCallerPrincipalCallback.getPrincipal();

    // Assert
    assertEquals("Name", actualName);
    assertNull(actualPrincipal);
    assertSame(subject, actualCallerPrincipalCallback.getSubject());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   *   <li>Then return Name is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CallerPrincipalCallback#CallerPrincipalCallback(Subject, Principal)}
   *   <li>{@link CallerPrincipalCallback#getName()}
   *   <li>{@link CallerPrincipalCallback#getPrincipal()}
   *   <li>{@link CallerPrincipalCallback#getSubject()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenUserPrincipalWithNameIsPrincipal_thenReturnNameIsNull() {
    // Arrange
    Subject subject = new Subject();
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    CallerPrincipalCallback actualCallerPrincipalCallback = new CallerPrincipalCallback(subject, principal);
    String actualName = actualCallerPrincipalCallback.getName();
    Principal actualPrincipal = actualCallerPrincipalCallback.getPrincipal();

    // Assert
    assertNull(actualName);
    assertSame(principal, actualPrincipal);
    assertSame(subject, actualCallerPrincipalCallback.getSubject());
  }
}
