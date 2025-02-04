package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class PropertyNotWritableExceptionDiffblueTest {
  /**
   * Test {@link PropertyNotWritableException#PropertyNotWritableException(String)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotWritableException#PropertyNotWritableException(String)}
   */
  @Test
  public void testNewPropertyNotWritableException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    PropertyNotWritableException actualPropertyNotWritableException = new PropertyNotWritableException(
        "An error occurred");

    // Assert
    assertEquals("An error occurred", actualPropertyNotWritableException.getMessage());
    assertNull(actualPropertyNotWritableException.getCause());
    assertEquals(0, actualPropertyNotWritableException.getSuppressed().length);
  }

  /**
   * Test {@link PropertyNotWritableException#PropertyNotWritableException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotWritableException#PropertyNotWritableException(String, Throwable)}
   */
  @Test
  public void testNewPropertyNotWritableException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    PropertyNotWritableException actualPropertyNotWritableException = new PropertyNotWritableException(
        "An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualPropertyNotWritableException.getMessage());
    assertEquals(0, actualPropertyNotWritableException.getSuppressed().length);
    assertSame(cause, actualPropertyNotWritableException.getCause());
  }

  /**
   * Test {@link PropertyNotWritableException#PropertyNotWritableException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotWritableException#PropertyNotWritableException(Throwable)}
   */
  @Test
  public void testNewPropertyNotWritableException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    PropertyNotWritableException actualPropertyNotWritableException = new PropertyNotWritableException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualPropertyNotWritableException.getMessage());
    assertEquals(0, actualPropertyNotWritableException.getSuppressed().length);
    assertSame(cause, actualPropertyNotWritableException.getCause());
  }

  /**
   * Test {@link PropertyNotWritableException#PropertyNotWritableException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotWritableException#PropertyNotWritableException()}
   */
  @Test
  public void testNewPropertyNotWritableException_thenReturnMessageIsNull() {
    // Arrange and Act
    PropertyNotWritableException actualPropertyNotWritableException = new PropertyNotWritableException();

    // Assert
    assertNull(actualPropertyNotWritableException.getMessage());
    assertNull(actualPropertyNotWritableException.getCause());
    assertEquals(0, actualPropertyNotWritableException.getSuppressed().length);
  }
}
