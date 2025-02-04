package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class EncodeExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EncodeException#EncodeException(Object, String)}
   *   <li>{@link EncodeException#getObject()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    EncodeException actualEncodeException = new EncodeException("Object", "An error occurred");

    // Assert
    assertEquals("An error occurred", actualEncodeException.getMessage());
    assertEquals("Object", actualEncodeException.getObject());
    assertNull(actualEncodeException.getCause());
    assertEquals(0, actualEncodeException.getSuppressed().length);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Cause is {@link Throwable#Throwable()}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EncodeException#EncodeException(Object, String, Throwable)}
   *   <li>{@link EncodeException#getObject()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    EncodeException actualEncodeException = new EncodeException("Object", "An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualEncodeException.getMessage());
    assertEquals("Object", actualEncodeException.getObject());
    assertEquals(0, actualEncodeException.getSuppressed().length);
    assertSame(cause, actualEncodeException.getCause());
  }
}
