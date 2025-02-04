package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class PropertyNotFoundExceptionDiffblueTest {
  /**
   * Test {@link PropertyNotFoundException#PropertyNotFoundException(String)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotFoundException#PropertyNotFoundException(String)}
   */
  @Test
  public void testNewPropertyNotFoundException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    PropertyNotFoundException actualPropertyNotFoundException = new PropertyNotFoundException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualPropertyNotFoundException.getMessage());
    assertNull(actualPropertyNotFoundException.getCause());
    assertEquals(0, actualPropertyNotFoundException.getSuppressed().length);
  }

  /**
   * Test {@link PropertyNotFoundException#PropertyNotFoundException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotFoundException#PropertyNotFoundException(String, Throwable)}
   */
  @Test
  public void testNewPropertyNotFoundException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    PropertyNotFoundException actualPropertyNotFoundException = new PropertyNotFoundException("An error occurred",
        cause);

    // Assert
    assertEquals("An error occurred", actualPropertyNotFoundException.getMessage());
    assertEquals(0, actualPropertyNotFoundException.getSuppressed().length);
    assertSame(cause, actualPropertyNotFoundException.getCause());
  }

  /**
   * Test {@link PropertyNotFoundException#PropertyNotFoundException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotFoundException#PropertyNotFoundException(Throwable)}
   */
  @Test
  public void testNewPropertyNotFoundException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    PropertyNotFoundException actualPropertyNotFoundException = new PropertyNotFoundException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualPropertyNotFoundException.getMessage());
    assertEquals(0, actualPropertyNotFoundException.getSuppressed().length);
    assertSame(cause, actualPropertyNotFoundException.getCause());
  }

  /**
   * Test {@link PropertyNotFoundException#PropertyNotFoundException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyNotFoundException#PropertyNotFoundException()}
   */
  @Test
  public void testNewPropertyNotFoundException_thenReturnMessageIsNull() {
    // Arrange and Act
    PropertyNotFoundException actualPropertyNotFoundException = new PropertyNotFoundException();

    // Assert
    assertNull(actualPropertyNotFoundException.getMessage());
    assertNull(actualPropertyNotFoundException.getCause());
    assertEquals(0, actualPropertyNotFoundException.getSuppressed().length);
  }
}
