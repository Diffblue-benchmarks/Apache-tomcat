package org.apache.catalina.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

public class IOToolsDiffblueTest {
  /**
   * Test {@link IOTools#flow(InputStream, OutputStream)} with {@code is}, {@code os}.
   * <p>
   * Method under test: {@link IOTools#flow(InputStream, OutputStream)}
   */
  @Test
  public void testFlowWithIsOs() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream os = new ByteArrayOutputStream(1);

    // Act
    IOTools.flow(is, os);

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, os.toByteArray());
  }

  /**
   * Test {@link IOTools#flow(InputStream, OutputStream)} with {@code is}, {@code os}.
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX} Bytes is {@code UTF-8} read is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IOTools#flow(InputStream, OutputStream)}
   */
  @Test
  public void testFlowWithIsOs_thenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8ReadIsMinusOne() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    IOTools.flow(is, null);

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
  }

  /**
   * Test {@link IOTools#flow(Reader, Writer, char[])} with {@code reader}, {@code writer}, {@code buf}.
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IOTools#flow(Reader, Writer, char[])}
   */
  @Test
  public void testFlowWithReaderWriterBuf_thenStringWriterToStringIsFoo() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    StringWriter writer = new StringWriter();
    char[] buf = "AZAZ".toCharArray();

    // Act
    IOTools.flow(reader, writer, buf);

    // Assert
    assertEquals("foo", writer.toString());
    assertArrayEquals("fooZ".toCharArray(), buf);
  }

  /**
   * Test {@link IOTools#flow(Reader, Writer)} with {@code reader}, {@code writer}.
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IOTools#flow(Reader, Writer)}
   */
  @Test
  public void testFlowWithReaderWriter_whenStringReaderWithFoo_thenStringWriterToStringIsFoo() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    StringWriter writer = new StringWriter();

    // Act
    IOTools.flow(reader, writer);

    // Assert
    assertEquals("foo", writer.toString());
  }

  /**
   * Test {@link IOTools#readFully(InputStream, byte[])}.
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX} Bytes is {@code UTF-8} read is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IOTools#readFully(InputStream, byte[])}
   */
  @Test
  public void testReadFully_thenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8ReadIsMinusOne() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    int actualReadFullyResult = IOTools.readFully(is, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
    assertEquals(8, actualReadFullyResult);
  }

  /**
   * Test {@link IOTools#readFully(InputStream, byte[])}.
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with empty array of {@code byte} read is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IOTools#readFully(InputStream, byte[])}
   */
  @Test
  public void testReadFully_thenByteArrayInputStreamWithEmptyArrayOfByteReadIsMinusOne() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream(new byte[]{});

    // Act
    int actualReadFullyResult = IOTools.readFully(is, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
    assertEquals(0, actualReadFullyResult);
  }
}
