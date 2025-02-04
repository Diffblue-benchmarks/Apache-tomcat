package org.apache.catalina.tribes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class ByteMessageDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ByteMessage#ByteMessage()}
   *   <li>{@link ByteMessage#setMessage(byte[])}
   *   <li>{@link ByteMessage#getMessage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteMessage actualByteMessage = new ByteMessage();
    byte[] message = "AXAXAXAX".getBytes("UTF-8");
    actualByteMessage.setMessage(message);
    byte[] actualMessage = actualByteMessage.getMessage();

    // Assert
    assertSame(message, actualMessage);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMessage);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ByteMessage#ByteMessage(byte[])}
   *   <li>{@link ByteMessage#setMessage(byte[])}
   *   <li>{@link ByteMessage#getMessage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteMessage actualByteMessage = new ByteMessage("AXAXAXAX".getBytes("UTF-8"));
    byte[] message = "AXAXAXAX".getBytes("UTF-8");
    actualByteMessage.setMessage(message);
    byte[] actualMessage = actualByteMessage.getMessage();

    // Assert
    assertSame(message, actualMessage);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMessage);
  }
}
