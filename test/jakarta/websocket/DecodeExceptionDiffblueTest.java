package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.nio.ByteBuffer;
import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.junit.Test;

public class DecodeExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link ApplicationBufferHandler#EMPTY_BUFFER}.</li>
   *   <li>Then return Text is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DecodeException#DecodeException(ByteBuffer, String)}
   *   <li>{@link DecodeException#getBytes()}
   *   <li>{@link DecodeException#getText()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenEmpty_buffer_thenReturnTextIsNull() {
    // Arrange
    ByteBuffer bb = ApplicationBufferHandler.EMPTY_BUFFER;

    // Act
    DecodeException actualDecodeException = new DecodeException(bb, "An error occurred");
    ByteBuffer actualBytes = actualDecodeException.getBytes();

    // Assert
    assertEquals("An error occurred", actualDecodeException.getMessage());
    assertNull(actualDecodeException.getText());
    assertNull(actualDecodeException.getCause());
    assertEquals(0, actualDecodeException.getSuppressed().length);
    assertSame(bb, actualBytes);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link ApplicationBufferHandler#EMPTY_BUFFER}.</li>
   *   <li>Then return Text is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DecodeException#DecodeException(ByteBuffer, String, Throwable)}
   *   <li>{@link DecodeException#getBytes()}
   *   <li>{@link DecodeException#getText()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenEmpty_buffer_thenReturnTextIsNull2() {
    // Arrange
    ByteBuffer bb = ApplicationBufferHandler.EMPTY_BUFFER;
    Throwable cause = new Throwable();

    // Act
    DecodeException actualDecodeException = new DecodeException(bb, "An error occurred", cause);
    ByteBuffer actualBytes = actualDecodeException.getBytes();

    // Assert
    assertEquals("An error occurred", actualDecodeException.getMessage());
    assertNull(actualDecodeException.getText());
    assertEquals(0, actualDecodeException.getSuppressed().length);
    assertSame(cause, actualDecodeException.getCause());
    assertSame(bb, actualBytes);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code secret}.</li>
   *   <li>Then return Text is {@code secret}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DecodeException#DecodeException(String, String)}
   *   <li>{@link DecodeException#getBytes()}
   *   <li>{@link DecodeException#getText()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenSecret_thenReturnTextIsSecret() {
    // Arrange and Act
    DecodeException actualDecodeException = new DecodeException("secret", "An error occurred");
    ByteBuffer actualBytes = actualDecodeException.getBytes();

    // Assert
    assertEquals("An error occurred", actualDecodeException.getMessage());
    assertEquals("secret", actualDecodeException.getText());
    assertNull(actualDecodeException.getCause());
    assertNull(actualBytes);
    assertEquals(0, actualDecodeException.getSuppressed().length);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code secret}.</li>
   *   <li>Then return Text is {@code secret}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DecodeException#DecodeException(String, String, Throwable)}
   *   <li>{@link DecodeException#getBytes()}
   *   <li>{@link DecodeException#getText()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenSecret_thenReturnTextIsSecret2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DecodeException actualDecodeException = new DecodeException("secret", "An error occurred", cause);
    ByteBuffer actualBytes = actualDecodeException.getBytes();

    // Assert
    assertEquals("An error occurred", actualDecodeException.getMessage());
    assertEquals("secret", actualDecodeException.getText());
    assertNull(actualBytes);
    assertEquals(0, actualDecodeException.getSuppressed().length);
    assertSame(cause, actualDecodeException.getCause());
  }
}
