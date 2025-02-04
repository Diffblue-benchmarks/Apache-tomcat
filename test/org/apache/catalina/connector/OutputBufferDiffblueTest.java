package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.coyote.Response;
import org.junit.Test;

public class OutputBufferDiffblueTest {
  /**
   * Test {@link OutputBuffer#OutputBuffer(int, Response)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@link OutputBuffer#conv} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#OutputBuffer(int, Response)}
   */
  @Test
  public void testNewOutputBuffer_whenThree_thenReturnConvIsNull() {
    // Arrange and Act
    OutputBuffer actualOutputBuffer = new OutputBuffer(3, new Response());

    // Assert
    assertNull(actualOutputBuffer.conv);
    assertEquals(0L, actualOutputBuffer.getContentWritten());
    assertEquals(3, actualOutputBuffer.getBufferSize());
    assertFalse(actualOutputBuffer.isClosed());
    assertFalse(actualOutputBuffer.isSuspended());
    assertTrue(actualOutputBuffer.isBlocking());
    assertTrue(actualOutputBuffer.isNew());
    assertTrue(actualOutputBuffer.isReady());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link OutputBuffer#setSuspended(boolean)}
   *   <li>{@link OutputBuffer#isClosed()}
   *   <li>{@link OutputBuffer#isSuspended()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.setSuspended(true);
    boolean actualIsClosedResult = outputBuffer.isClosed();

    // Assert
    assertFalse(actualIsClosedResult);
    assertTrue(outputBuffer.isSuspended());
  }

  /**
   * Test {@link OutputBuffer#flush()}.
   * <ul>
   *   <li>Then throw {@link ClientAbortException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#flush()}
   */
  @Test
  public void testFlush_thenThrowClientAbortException() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.setErrorException(new Exception("foo"));

    // Act and Assert
    assertThrows(ClientAbortException.class, () -> (new OutputBuffer(3, coyoteResponse)).flush());
  }

  /**
   * Test {@link OutputBuffer#doFlush(boolean)}.
   * <ul>
   *   <li>Then throw {@link ClientAbortException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#doFlush(boolean)}
   */
  @Test
  public void testDoFlush_thenThrowClientAbortException() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.setErrorException(new Exception("foo"));

    // Act and Assert
    assertThrows(ClientAbortException.class, () -> (new OutputBuffer(3, coyoteResponse)).doFlush(true));
  }

  /**
   * Test {@link OutputBuffer#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithByteIntInt() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);
    outputBuffer.append(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}, 3, 3);

    // Act
    outputBuffer.write("AXAXAXAX".getBytes("UTF-8"), 19088743, 3);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithByteIntInt2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("AXAXAXAX".getBytes("UTF-8"), 3, 3);

    // Assert
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithByteIntInt3() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("AXAXAXAX".getBytes("UTF-8"), 19088743, -1);

    // Assert
    assertEquals(-1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithByteIntInt_givenResponseAddHeaderContentLengthAnd42() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.addHeader("Content-Length", "42");
    OutputBuffer outputBuffer = new OutputBuffer(3, coyoteResponse);

    // Act
    outputBuffer.write("AXAXAXAX".getBytes("UTF-8"), 3, 3);

    // Assert
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithByteIntInt_whenZero() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("AXAXAXAX".getBytes("UTF-8"), 19088743, 0);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(char[], int, int)} with {@code char[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(char[], int, int)}
   */
  @Test
  public void testWriteWithCharIntInt() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);
    outputBuffer.append('\u0003');

    // Act
    outputBuffer.write("AeAe".toCharArray(), 19088743, 3);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(char[], int, int)} with {@code char[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(char[], int, int)}
   */
  @Test
  public void testWriteWithCharIntInt2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("AeAe".toCharArray(), 1, 3);

    // Assert
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(char[], int, int)} with {@code char[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#write(char[], int, int)}
   */
  @Test
  public void testWriteWithCharIntInt_whenZero() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("AeAe".toCharArray(), 19088743, 0);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(int)} with {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(int)}
   */
  @Test
  public void testWriteWithInt() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write(19088743);

    // Assert
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(int)} with {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(int)}
   */
  @Test
  public void testWriteWithInt2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);

    // Act
    outputBuffer.write(19088743);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(String)} with {@code String}.
   * <p>
   * Method under test: {@link OutputBuffer#write(String)}
   */
  @Test
  public void testWriteWithString() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("foo");

    // Assert
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(String)} with {@code String}.
   * <p>
   * Method under test: {@link OutputBuffer#write(String)}
   */
  @Test
  public void testWriteWithString2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);

    // Act
    outputBuffer.write("foo");

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(String, int, int)} with {@code String}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(String, int, int)}
   */
  @Test
  public void testWriteWithStringIntInt() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.write("foo", 0, 3);

    // Assert
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#write(String, int, int)} with {@code String}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link OutputBuffer#write(String, int, int)}
   */
  @Test
  public void testWriteWithStringIntInt2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);

    // Act
    outputBuffer.write("foo", 19088743, 3);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#writeByte(int)}.
   * <p>
   * Method under test: {@link OutputBuffer#writeByte(int)}
   */
  @Test
  public void testWriteByte() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.writeByte(19088743);

    // Assert
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#writeByte(int)}.
   * <p>
   * Method under test: {@link OutputBuffer#writeByte(int)}
   */
  @Test
  public void testWriteByte2() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.setSuspended(true);
    outputBuffer.append(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}, 3, 3);

    // Act
    outputBuffer.writeByte(19088743);

    // Assert that nothing has changed
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#writeByte(int)}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#writeByte(int)}
   */
  @Test
  public void testWriteByte_givenResponseAddHeaderContentLengthAnd42() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.addHeader("Content-Length", "42");
    OutputBuffer outputBuffer = new OutputBuffer(3, coyoteResponse);

    // Act
    outputBuffer.writeByte(19088743);

    // Assert
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#checkConverter()}.
   * <p>
   * Method under test: {@link OutputBuffer#checkConverter()}
   */
  @Test
  public void testCheckConverter() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.checkConverter();

    // Assert
    assertEquals("ISO-8859-1", outputBuffer.conv.getCharset().name());
  }

  /**
   * Test {@link OutputBuffer#checkConverter()}.
   * <p>
   * Method under test: {@link OutputBuffer#checkConverter()}
   */
  @Test
  public void testCheckConverter2() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.setCharacterEncoding("UTF-8");
    OutputBuffer outputBuffer = new OutputBuffer(3, coyoteResponse);

    // Act
    outputBuffer.checkConverter();

    // Assert
    assertEquals("UTF-8", outputBuffer.conv.getCharset().name());
  }

  /**
   * Test {@link OutputBuffer#getContentWritten()}.
   * <p>
   * Method under test: {@link OutputBuffer#getContentWritten()}
   */
  @Test
  public void testGetContentWritten() {
    // Arrange, Act and Assert
    assertEquals(0L, (new OutputBuffer(3, new Response())).getContentWritten());
  }

  /**
   * Test {@link OutputBuffer#isNew()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#isNew()}
   */
  @Test
  public void testIsNew_thenReturnFalse() throws IOException {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());
    outputBuffer.append('\u0001');

    // Act and Assert
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link OutputBuffer#isNew()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#isNew()}
   */
  @Test
  public void testIsNew_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new OutputBuffer(3, new Response())).isNew());
  }

  /**
   * Test {@link OutputBuffer#setBufferSize(int)}.
   * <p>
   * Method under test: {@link OutputBuffer#setBufferSize(int)}
   */
  @Test
  public void testSetBufferSize() {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(3, new Response());

    // Act
    outputBuffer.setBufferSize(3);

    // Assert that nothing has changed
    assertEquals(3, outputBuffer.getBufferSize());
  }

  /**
   * Test {@link OutputBuffer#setBufferSize(int)}.
   * <p>
   * Method under test: {@link OutputBuffer#setBufferSize(int)}
   */
  @Test
  public void testSetBufferSize2() {
    // Arrange
    OutputBuffer outputBuffer = new OutputBuffer(1, new Response());

    // Act
    outputBuffer.setBufferSize(3);

    // Assert
    assertEquals(3, outputBuffer.getBufferSize());
  }

  /**
   * Test {@link OutputBuffer#getBufferSize()}.
   * <p>
   * Method under test: {@link OutputBuffer#getBufferSize()}
   */
  @Test
  public void testGetBufferSize() {
    // Arrange, Act and Assert
    assertEquals(3, (new OutputBuffer(3, new Response())).getBufferSize());
  }

  /**
   * Test {@link OutputBuffer#isReady()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#isReady()}
   */
  @Test
  public void testIsReady_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new OutputBuffer(3, new Response())).isReady());
  }

  /**
   * Test {@link OutputBuffer#isBlocking()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputBuffer#isBlocking()}
   */
  @Test
  public void testIsBlocking_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new OutputBuffer(3, new Response())).isBlocking());
  }
}
