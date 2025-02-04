package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.coyote.Response;
import org.junit.Test;

public class CoyoteOutputStreamDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CoyoteOutputStream#CoyoteOutputStream(OutputBuffer)}
   *   <li>{@link CoyoteOutputStream#clear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CoyoteOutputStream actualCoyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, new Response()));
    actualCoyoteOutputStream.clear();

    // Assert
    assertNull(actualCoyoteOutputStream.ob);
  }

  /**
   * Test {@link CoyoteOutputStream#clone()}.
   * <p>
   * Method under test: {@link CoyoteOutputStream#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(CloneNotSupportedException.class,
        () -> (new CoyoteOutputStream(new OutputBuffer(3, new Response()))).clone());
  }

  /**
   * Test {@link CoyoteOutputStream#write(byte[], int, int)} with {@code b}, {@code off}, {@code len}.
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithBOffLen() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 3);
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(ob);

    // Act
    coyoteOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 19088743, 3);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(byte[], int, int)} with {@code b}, {@code off}, {@code len}.
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithBOffLen2() throws IOException {
    // Arrange
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, new Response()));

    // Act
    coyoteOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(byte[], int, int)} with {@code b}, {@code off}, {@code len}.
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithBOffLen3() throws IOException {
    // Arrange
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, new Response()));

    // Act
    coyoteOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 19088743, -1);

    // Assert
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(-1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(byte[], int, int)} with {@code b}, {@code off}, {@code len}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithBOffLen_givenResponseAddHeaderContentLengthAnd42() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.addHeader("Content-Length", "42");
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, coyoteResponse));

    // Act
    coyoteOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(3L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(byte[], int, int)} with {@code b}, {@code off}, {@code len}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(byte[], int, int)}
   */
  @Test
  public void testWriteWithBOffLen_whenZero() throws IOException {
    // Arrange
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, new Response()));

    // Act
    coyoteOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 19088743, 0);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(int)} with {@code i}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(int)}
   */
  @Test
  public void testWriteWithI_givenResponseAddHeaderContentLengthAnd42() throws IOException {
    // Arrange
    Response coyoteResponse = new Response();
    coyoteResponse.addHeader("Content-Length", "42");
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, coyoteResponse));

    // Act
    coyoteOutputStream.write(19088743);

    // Assert
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(int)} with {@code i}.
   * <ul>
   *   <li>Then {@link CoyoteOutputStream#CoyoteOutputStream(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteOutputStream#ob} ContentWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(int)}
   */
  @Test
  public void testWriteWithI_thenCoyoteOutputStreamWithObIsOutputBufferObContentWrittenIsOne() throws IOException {
    // Arrange
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(new OutputBuffer(3, new Response()));

    // Act
    coyoteOutputStream.write(19088743);

    // Assert
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(1L, outputBuffer.getContentWritten());
    assertFalse(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#write(int)} with {@code i}.
   * <ul>
   *   <li>Then {@link CoyoteOutputStream#CoyoteOutputStream(OutputBuffer)} with ob is {@link OutputBuffer#OutputBuffer(int, Response)} {@link CoyoteOutputStream#ob} ContentWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#write(int)}
   */
  @Test
  public void testWriteWithI_thenCoyoteOutputStreamWithObIsOutputBufferObContentWrittenIsZero() throws IOException {
    // Arrange
    OutputBuffer ob = new OutputBuffer(3, new Response());
    ob.setSuspended(true);
    ob.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 3);
    CoyoteOutputStream coyoteOutputStream = new CoyoteOutputStream(ob);

    // Act
    coyoteOutputStream.write(19088743);

    // Assert that nothing has changed
    OutputBuffer outputBuffer = coyoteOutputStream.ob;
    assertEquals(0L, outputBuffer.getContentWritten());
    assertTrue(outputBuffer.isNew());
  }

  /**
   * Test {@link CoyoteOutputStream#isReady()}.
   * <ul>
   *   <li>Given {@link CoyoteOutputStream#CoyoteOutputStream(OutputBuffer)} with ob is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#isReady()}
   */
  @Test
  public void testIsReady_givenCoyoteOutputStreamWithObIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new CoyoteOutputStream(null)).isReady());
  }

  /**
   * Test {@link CoyoteOutputStream#isReady()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteOutputStream#isReady()}
   */
  @Test
  public void testIsReady_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new CoyoteOutputStream(new OutputBuffer(3, new Response()))).isReady());
  }
}
