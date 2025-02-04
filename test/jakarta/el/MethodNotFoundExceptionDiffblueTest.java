package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class MethodNotFoundExceptionDiffblueTest {
  /**
   * Test {@link MethodNotFoundException#MethodNotFoundException(String)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodNotFoundException#MethodNotFoundException(String)}
   */
  @Test
  public void testNewMethodNotFoundException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    MethodNotFoundException actualMethodNotFoundException = new MethodNotFoundException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualMethodNotFoundException.getMessage());
    assertNull(actualMethodNotFoundException.getCause());
    assertEquals(0, actualMethodNotFoundException.getSuppressed().length);
  }

  /**
   * Test {@link MethodNotFoundException#MethodNotFoundException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodNotFoundException#MethodNotFoundException(String, Throwable)}
   */
  @Test
  public void testNewMethodNotFoundException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    MethodNotFoundException actualMethodNotFoundException = new MethodNotFoundException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualMethodNotFoundException.getMessage());
    assertEquals(0, actualMethodNotFoundException.getSuppressed().length);
    assertSame(cause, actualMethodNotFoundException.getCause());
  }

  /**
   * Test {@link MethodNotFoundException#MethodNotFoundException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodNotFoundException#MethodNotFoundException(Throwable)}
   */
  @Test
  public void testNewMethodNotFoundException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    MethodNotFoundException actualMethodNotFoundException = new MethodNotFoundException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualMethodNotFoundException.getMessage());
    assertEquals(0, actualMethodNotFoundException.getSuppressed().length);
    assertSame(cause, actualMethodNotFoundException.getCause());
  }

  /**
   * Test {@link MethodNotFoundException#MethodNotFoundException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodNotFoundException#MethodNotFoundException()}
   */
  @Test
  public void testNewMethodNotFoundException_thenReturnMessageIsNull() {
    // Arrange and Act
    MethodNotFoundException actualMethodNotFoundException = new MethodNotFoundException();

    // Assert
    assertNull(actualMethodNotFoundException.getMessage());
    assertNull(actualMethodNotFoundException.getCause());
    assertEquals(0, actualMethodNotFoundException.getSuppressed().length);
  }
}
