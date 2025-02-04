package org.apache.catalina.ssi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import jakarta.servlet.WriteListener;
import org.junit.Test;

public class ByteArrayServletOutputStreamDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ByteArrayServletOutputStream}
   *   <li>{@link ByteArrayServletOutputStream#setWriteListener(WriteListener)}
   *   <li>{@link ByteArrayServletOutputStream#isReady()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ByteArrayServletOutputStream actualByteArrayServletOutputStream = new ByteArrayServletOutputStream();
    actualByteArrayServletOutputStream.setWriteListener(null);

    // Assert
    assertFalse(actualByteArrayServletOutputStream.isReady());
    assertArrayEquals(new byte[]{}, actualByteArrayServletOutputStream.buf.toByteArray());
  }

  /**
   * Test {@link ByteArrayServletOutputStream#toByteArray()}.
   * <p>
   * Method under test: {@link ByteArrayServletOutputStream#toByteArray()}
   */
  @Test
  public void testToByteArray() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{}, (new ByteArrayServletOutputStream()).toByteArray());
  }

  /**
   * Test {@link ByteArrayServletOutputStream#write(int)} with {@code b}.
   * <p>
   * Method under test: {@link ByteArrayServletOutputStream#write(int)}
   */
  @Test
  public void testWriteWithB() {
    // Arrange
    ByteArrayServletOutputStream byteArrayServletOutputStream = new ByteArrayServletOutputStream();

    // Act
    byteArrayServletOutputStream.write(19088743);

    // Assert
    assertArrayEquals(new byte[]{'g'}, byteArrayServletOutputStream.buf.toByteArray());
    assertArrayEquals(new byte[]{'g'}, byteArrayServletOutputStream.toByteArray());
  }
}
