package org.apache.catalina.connector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.junit.Test;

public class InputBufferDiffblueTest {
  /**
   * Test {@link InputBuffer#InputBuffer(Request)}.
   * <p>
   * Method under test: {@link InputBuffer#InputBuffer(Request)}
   */
  @Test
  public void testNewInputBuffer() throws IOException {
    // Arrange and Act
    InputBuffer actualInputBuffer = new InputBuffer(new Request());

    // Assert
    assertNull(actualInputBuffer.conv);
    ByteBuffer byteBuffer = actualInputBuffer.getByteBuffer();
    assertEquals(0, byteBuffer.capacity());
    assertEquals(0, byteBuffer.limit());
    assertEquals(0, byteBuffer.position());
    assertEquals(0, actualInputBuffer.INITIAL_STATE);
    assertEquals(1, actualInputBuffer.CHAR_STATE);
    assertEquals(2, actualInputBuffer.BYTE_STATE);
    assertFalse(byteBuffer.hasRemaining());
    assertFalse(actualInputBuffer.ready());
    assertTrue(byteBuffer.hasArray());
    assertTrue(actualInputBuffer.isBlocking());
    assertArrayEquals(new byte[]{}, byteBuffer.array());
  }

  /**
   * Test {@link InputBuffer#InputBuffer(int, Request)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@link InputBuffer#conv} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#InputBuffer(int, Request)}
   */
  @Test
  public void testNewInputBuffer_whenThree_thenReturnConvIsNull() throws IOException {
    // Arrange and Act
    InputBuffer actualInputBuffer = new InputBuffer(3, new Request());

    // Assert
    assertNull(actualInputBuffer.conv);
    ByteBuffer byteBuffer = actualInputBuffer.getByteBuffer();
    assertEquals(0, byteBuffer.capacity());
    assertEquals(0, byteBuffer.limit());
    assertEquals(0, byteBuffer.position());
    assertEquals(0, actualInputBuffer.INITIAL_STATE);
    assertEquals(1, actualInputBuffer.CHAR_STATE);
    assertEquals(2, actualInputBuffer.BYTE_STATE);
    assertFalse(byteBuffer.hasRemaining());
    assertFalse(actualInputBuffer.ready());
    assertTrue(byteBuffer.hasArray());
    assertTrue(actualInputBuffer.isBlocking());
    assertArrayEquals(new byte[]{}, byteBuffer.array());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InputBuffer#setByteBuffer(ByteBuffer)}
   *   <li>{@link InputBuffer#expand(int)}
   *   <li>{@link InputBuffer#close()}
   *   <li>{@link InputBuffer#getByteBuffer()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws IOException {
    // Arrange
    ByteBuffer buffer = ApplicationBufferHandler.EMPTY_BUFFER;
    InputBuffer inputBuffer = new InputBuffer(new Request());

    // Act
    inputBuffer.setByteBuffer(buffer);
    inputBuffer.expand(3);
    inputBuffer.close();

    // Assert
    assertSame(buffer, inputBuffer.getByteBuffer());
  }

  /**
   * Test {@link InputBuffer#available()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#available()}
   */
  @Test
  public void testAvailable_givenInputBufferWithCoyoteRequestIsRequest_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new InputBuffer(new Request())).available());
  }

  /**
   * Test {@link InputBuffer#available()}.
   * <ul>
   *   <li>Given {@link Request} (default constructor) Available is two.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#available()}
   */
  @Test
  public void testAvailable_givenRequestAvailableIsTwo_thenReturnOne() {
    // Arrange
    Request coyoteRequest = new Request();
    coyoteRequest.setAvailable(2);

    // Act and Assert
    assertEquals(1, (new InputBuffer(coyoteRequest)).available());
  }

  /**
   * Test {@link InputBuffer#isFinished()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#isFinished()}
   */
  @Test
  public void testIsFinished_givenInputBufferWithCoyoteRequestIsRequest_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new InputBuffer(new Request())).isFinished());
  }

  /**
   * Test {@link InputBuffer#isReady()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#isReady()}
   */
  @Test
  public void testIsReady_givenInputBufferWithCoyoteRequestIsRequest_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new InputBuffer(new Request())).isReady());
  }

  /**
   * Test {@link InputBuffer#isBlocking()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#isBlocking()}
   */
  @Test
  public void testIsBlocking_givenInputBufferWithCoyoteRequestIsRequest_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new InputBuffer(new Request())).isBlocking());
  }

  /**
   * Test {@link InputBuffer#read(ByteBuffer)} with {@code to}.
   * <ul>
   *   <li>When {@link ApplicationBufferHandler#EMPTY_BUFFER}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#read(ByteBuffer)}
   */
  @Test
  public void testReadWithTo_whenEmpty_buffer_thenReturnZero() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0, (new InputBuffer(new Request())).read(ApplicationBufferHandler.EMPTY_BUFFER));
  }

  /**
   * Test {@link InputBuffer#skip(long)}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code content-type} and {@code 42}.</li>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#skip(long)}
   */
  @Test
  public void testSkip_givenResponseAddHeaderContentTypeAnd42_whenZero_thenReturnZero() throws IOException {
    // Arrange
    Response response = new Response();
    response.addHeader("content-type", "42");

    Request coyoteRequest = new Request();
    coyoteRequest.setResponse(response);

    // Act and Assert
    assertEquals(0L, (new InputBuffer(coyoteRequest)).skip(0L));
  }

  /**
   * Test {@link InputBuffer#skip(long)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#skip(long)}
   */
  @Test
  public void testSkip_whenMinusOne_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    Response response = new Response();
    response.addHeader("content-type", "42");

    Request coyoteRequest = new Request();
    coyoteRequest.setResponse(response);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new InputBuffer(coyoteRequest)).skip(-1L));
  }

  /**
   * Test {@link InputBuffer#ready()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#ready()}
   */
  @Test
  public void testReady_givenInputBufferWithCoyoteRequestIsRequest_thenReturnFalse() throws IOException {
    // Arrange, Act and Assert
    assertFalse((new InputBuffer(new Request())).ready());
  }

  /**
   * Test {@link InputBuffer#ready()}.
   * <ul>
   *   <li>Given {@link Request} (default constructor) Available is one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputBuffer#ready()}
   */
  @Test
  public void testReady_givenRequestAvailableIsOne_thenReturnTrue() throws IOException {
    // Arrange
    Request coyoteRequest = new Request();
    coyoteRequest.setAvailable(1);

    // Act and Assert
    assertTrue((new InputBuffer(coyoteRequest)).ready());
  }

  /**
   * Test {@link InputBuffer#markSupported()}.
   * <p>
   * Method under test: {@link InputBuffer#markSupported()}
   */
  @Test
  public void testMarkSupported() {
    // Arrange, Act and Assert
    assertTrue((new InputBuffer(new Request())).markSupported());
  }

  /**
   * Test {@link InputBuffer#checkConverter()}.
   * <p>
   * Method under test: {@link InputBuffer#checkConverter()}
   */
  @Test
  public void testCheckConverter() throws IOException {
    // Arrange
    InputBuffer inputBuffer = new InputBuffer(new Request());

    // Act
    inputBuffer.checkConverter();

    // Assert
    assertEquals("ISO-8859-1", inputBuffer.conv.getCharset().name());
  }
}
