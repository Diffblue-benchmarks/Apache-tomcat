package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.coyote.Request;
import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.junit.Test;

public class CoyoteInputStreamDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CoyoteInputStream#CoyoteInputStream(InputBuffer)}
   *   <li>{@link CoyoteInputStream#clear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CoyoteInputStream actualCoyoteInputStream = new CoyoteInputStream(new InputBuffer(new Request()));
    actualCoyoteInputStream.clear();

    // Assert
    assertNull(actualCoyoteInputStream.ib);
  }

  /**
   * Test {@link CoyoteInputStream#clone()}.
   * <p>
   * Method under test: {@link CoyoteInputStream#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(CloneNotSupportedException.class,
        () -> (new CoyoteInputStream(new InputBuffer(new Request()))).clone());
  }

  /**
   * Test {@link CoyoteInputStream#read(ByteBuffer)} with {@code ByteBuffer}.
   * <ul>
   *   <li>When {@link ApplicationBufferHandler#EMPTY_BUFFER}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#read(ByteBuffer)}
   */
  @Test
  public void testReadWithByteBuffer_whenEmpty_buffer_thenReturnZero() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0,
        (new CoyoteInputStream(new InputBuffer(new Request()))).read(ApplicationBufferHandler.EMPTY_BUFFER));
  }

  /**
   * Test {@link CoyoteInputStream#available()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#available()}
   */
  @Test
  public void testAvailable_givenInputBufferWithCoyoteRequestIsRequest_thenReturnZero() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0, (new CoyoteInputStream(new InputBuffer(new Request()))).available());
  }

  /**
   * Test {@link CoyoteInputStream#available()}.
   * <ul>
   *   <li>Given {@link Request} (default constructor) Available is two.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#available()}
   */
  @Test
  public void testAvailable_givenRequestAvailableIsTwo_thenReturnOne() throws IOException {
    // Arrange
    Request coyoteRequest = new Request();
    coyoteRequest.setAvailable(2);

    // Act and Assert
    assertEquals(1, (new CoyoteInputStream(new InputBuffer(coyoteRequest))).available());
  }

  /**
   * Test {@link CoyoteInputStream#isFinished()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#isFinished()}
   */
  @Test
  public void testIsFinished_givenInputBufferWithCoyoteRequestIsRequest_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new CoyoteInputStream(new InputBuffer(new Request()))).isFinished());
  }

  /**
   * Test {@link CoyoteInputStream#isReady()}.
   * <ul>
   *   <li>Given {@link CoyoteInputStream#CoyoteInputStream(InputBuffer)} with ib is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#isReady()}
   */
  @Test
  public void testIsReady_givenCoyoteInputStreamWithIbIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new CoyoteInputStream(null)).isReady());
  }

  /**
   * Test {@link CoyoteInputStream#isReady()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteInputStream#isReady()}
   */
  @Test
  public void testIsReady_givenInputBufferWithCoyoteRequestIsRequest_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new CoyoteInputStream(new InputBuffer(new Request()))).isReady());
  }
}
