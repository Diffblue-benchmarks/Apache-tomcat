package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.coyote.Response;
import org.junit.Test;

public class CoyoteWriterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CoyoteWriter#CoyoteWriter(OutputBuffer)}
   *   <li>{@link CoyoteWriter#clear()}
   *   <li>{@link CoyoteWriter#recycle()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CoyoteWriter actualCoyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));
    actualCoyoteWriter.clear();
    actualCoyoteWriter.recycle();

    // Assert
    assertNull(actualCoyoteWriter.ob);
    assertFalse(actualCoyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#clone()}.
   * <p>
   * Method under test: {@link CoyoteWriter#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(CloneNotSupportedException.class,
        () -> (new CoyoteWriter(new OutputBuffer(3, new Response()))).clone());
  }

  /**
   * Test {@link CoyoteWriter#flush()}.
   * <p>
   * Method under test: {@link CoyoteWriter#flush()}
   */
  @Test
  public void testFlush() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.flush();

    // Assert that nothing has changed
    assertFalse(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#flush()}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#error}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#flush()}
   */
  @Test
  public void testFlush_thenCoyoteWriterWithObIsOutputBufferError() {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.setErrorException(new Exception("foo"));
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, coyoteResponse));

    // Act
    coyoteWriter.flush();

    // Assert
    assertTrue(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#flush()}.
   * <ul>
   *   <li>Then not {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#error}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#flush()}
   */
  @Test
  public void testFlush_thenNotCoyoteWriterWithObIsOutputBufferError() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.flush();

    // Assert that nothing has changed
    assertFalse(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#checkError()}.
   * <p>
   * Method under test: {@link CoyoteWriter#checkError()}
   */
  @Test
  public void testCheckError() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act and Assert
    assertFalse(coyoteWriter.checkError());
    assertFalse(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#checkError()}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) ErrorException is {@link Exception#Exception(String)} with {@code foo}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#checkError()}
   */
  @Test
  public void testCheckError_givenResponseErrorExceptionIsExceptionWithFoo_thenReturnTrue() {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.setErrorException(new Exception("foo"));
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, coyoteResponse));

    // Act and Assert
    assertTrue(coyoteWriter.checkError());
    assertTrue(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#checkError()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#checkError()}
   */
  @Test
  public void testCheckError_thenReturnFalse() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act and Assert
    assertFalse(coyoteWriter.checkError());
    assertFalse(coyoteWriter.error);
  }

  /**
   * Test {@link CoyoteWriter#write(char[], int, int)} with {@code buf}, {@code off}, {@code len}.
   * <p>
   * Method under test: {@link CoyoteWriter#write(char[], int, int)}
   */
  @Test
  public void testWriteWithBufOffLen() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append('\u0003');
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.write("AZAZ".toCharArray(), 19088743, 3);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(char[], int, int)} with {@code buf}, {@code off}, {@code len}.
   * <p>
   * Method under test: {@link CoyoteWriter#write(char[], int, int)}
   */
  @Test
  public void testWriteWithBufOffLen2() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.write("AZAZ".toCharArray(), 1, 3);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(char[], int, int)} with {@code buf}, {@code off}, {@code len}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(char[], int, int)}
   */
  @Test
  public void testWriteWithBufOffLen_whenZero() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.write("AZAZ".toCharArray(), 19088743, 0);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(int)} with {@code c}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(int)}
   */
  @Test
  public void testWriteWithC_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.write(19088743);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(int)} with {@code c}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(int)}
   */
  @Test
  public void testWriteWithC_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.write(19088743);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(String, int, int)} with {@code s}, {@code off}, {@code len}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(String, int, int)}
   */
  @Test
  public void testWriteWithSOffLen_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsThree() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.write("foo", 0, 3);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(String, int, int)} with {@code s}, {@code off}, {@code len}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(String, int, int)}
   */
  @Test
  public void testWriteWithSOffLen_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.write("foo", 19088743, 3);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(String)} with {@code s}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(String)}
   */
  @Test
  public void testWriteWithS_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsThree() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.write("foo");

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#write(String)} with {@code s}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#write(String)}
   */
  @Test
  public void testWriteWithS_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.write("foo");

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(char)} with {@code char}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(char)}
   */
  @Test
  public void testPrintWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.print('A');

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(char)} with {@code char}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(char)}
   */
  @Test
  public void testPrintWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.print('A');

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(int)}
   */
  @Test
  public void testPrintWithInt_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.print(1);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(int)}
   */
  @Test
  public void testPrintWithInt_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append("\u0001A\u0001A".toCharArray(), 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.print(1);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(long)}
   */
  @Test
  public void testPrintWithLong_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.print(1L);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(long)}
   */
  @Test
  public void testPrintWithLong_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append("\u0001A\u0001A".toCharArray(), 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.print(1L);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(Object)} with {@code Object}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(Object)}
   */
  @Test
  public void testPrintWithObject_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsThree() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.print((Object) "Obj");

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(Object)} with {@code Object}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(Object)}
   */
  @Test
  public void testPrintWithObject_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.print((Object) "Obj");

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(String)} with {@code String}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(String)}
   */
  @Test
  public void testPrintWithString_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsThree() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.print("foo");

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#print(String)} with {@code String}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#print(String)}
   */
  @Test
  public void testPrintWithString_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.print("foo");

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(char[])} with {@code char[]}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(char[])}
   */
  @Test
  public void testPrintlnWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println(new char[]{});

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(char)} with {@code char}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(char)}
   */
  @Test
  public void testPrintlnWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsTwo() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println('A');

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(2L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(char)} with {@code char}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(char)}
   */
  @Test
  public void testPrintlnWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println('A');

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(char[])} with {@code char[]}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(char[])}
   */
  @Test
  public void testPrintlnWithChar_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero2() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println("AZAZ".toCharArray());

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(int)}
   */
  @Test
  public void testPrintlnWithInt_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsTwo() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println(1);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(2L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(int)}
   */
  @Test
  public void testPrintlnWithInt_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append("\u0001\u0002\u0001\u0002".toCharArray(), 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println(1);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(long)}
   */
  @Test
  public void testPrintlnWithLong_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsTwo() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println(1L);

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(2L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(long)}
   */
  @Test
  public void testPrintlnWithLong_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append("\u0001\u0002\u0001\u0002".toCharArray(), 1, 3);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println(1L);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(Object)} with {@code Object}.
   * <p>
   * Method under test: {@link CoyoteWriter#println(Object)}
   */
  @Test
  public void testPrintlnWithObject() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println((Object) "42");

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println(Object)} with {@code Object}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println(Object)}
   */
  @Test
  public void testPrintlnWithObject_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println((Object) "42");

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println()}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println()}
   */
  @Test
  public void testPrintln_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsOne() {
    // Arrange
    CoyoteWriter coyoteWriter = new CoyoteWriter(new OutputBuffer(3, new Response()));

    // Act
    coyoteWriter.println();

    // Assert
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteWriter#println()}.
   * <ul>
   *   <li>Then {@link CoyoteWriter#CoyoteWriter(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteWriter#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteWriter#println()}
   */
  @Test
  public void testPrintln_thenCoyoteWriterWithObIsOutputBufferObContentWrittenIsZero() {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    CoyoteWriter coyoteWriter = new CoyoteWriter(ob);

    // Act
    coyoteWriter.println();

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteWriter.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }
}
