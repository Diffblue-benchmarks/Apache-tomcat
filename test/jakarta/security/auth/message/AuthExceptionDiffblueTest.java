package jakarta.security.auth.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class AuthExceptionDiffblueTest {
  /**
   * Test {@link AuthException#AuthException(String, Throwable)}.
   * <p>
   * Method under test: {@link AuthException#AuthException(String, Throwable)}
   */
  @Test
  public void testNewAuthException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    AuthException actualAuthException = new AuthException("0123456789ABCDEF", cause);

    // Assert
    assertEquals("0123456789ABCDEF", actualAuthException.getLocalizedMessage());
    assertEquals("0123456789ABCDEF", actualAuthException.getMessage());
    assertEquals(0, actualAuthException.getSuppressed().length);
    assertSame(cause, actualAuthException.getCause());
  }

  /**
   * Test {@link AuthException#AuthException(Throwable)}.
   * <p>
   * Method under test: {@link AuthException#AuthException(Throwable)}
   */
  @Test
  public void testNewAuthException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    AuthException actualAuthException = new AuthException(cause);

    // Assert
    assertNull(actualAuthException.getLocalizedMessage());
    assertNull(actualAuthException.getMessage());
    assertEquals(0, actualAuthException.getSuppressed().length);
    assertSame(cause, actualAuthException.getCause());
  }

  /**
   * Test {@link AuthException#AuthException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthException#AuthException()}
   */
  @Test
  public void testNewAuthException_thenReturnMessageIsNull() {
    // Arrange and Act
    AuthException actualAuthException = new AuthException();

    // Assert
    assertNull(actualAuthException.getMessage());
    assertNull(actualAuthException.getCause());
    assertEquals(0, actualAuthException.getSuppressed().length);
  }

  /**
   * Test {@link AuthException#AuthException(String)}.
   * <ul>
   *   <li>When {@code 0123456789ABCDEF}.</li>
   *   <li>Then return Message is {@code 0123456789ABCDEF}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthException#AuthException(String)}
   */
  @Test
  public void testNewAuthException_when0123456789abcdef_thenReturnMessageIs0123456789abcdef() {
    // Arrange and Act
    AuthException actualAuthException = new AuthException("0123456789ABCDEF");

    // Assert
    assertEquals("0123456789ABCDEF", actualAuthException.getMessage());
    assertNull(actualAuthException.getCause());
    assertEquals(0, actualAuthException.getSuppressed().length);
  }
}
