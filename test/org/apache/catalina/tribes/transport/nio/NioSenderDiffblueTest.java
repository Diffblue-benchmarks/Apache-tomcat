package org.apache.catalina.tribes.transport.nio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import org.junit.Test;

public class NioSenderDiffblueTest {
  /**
   * Test new {@link NioSender} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NioSender}
   */
  @Test
  public void testNewNioSender() {
    // Arrange and Act
    NioSender actualNioSender = new NioSender();

    // Assert
    assertNull(actualNioSender.getMessage());
    assertNull(actualNioSender.getAddress());
    assertNull(actualNioSender.readbuf);
    assertNull(actualNioSender.writebuf);
    assertNull(actualNioSender.dataChannel);
    assertNull(actualNioSender.getSelector());
    assertNull(actualNioSender.socketChannel);
    assertNull(actualNioSender.getDestination());
    assertEquals(-1, actualNioSender.getKeepAliveCount());
    assertEquals(-1, actualNioSender.getUdpPort());
    assertEquals(-1L, actualNioSender.getKeepAliveTime());
    assertEquals(0, actualNioSender.getAttempt());
    assertEquals(0, actualNioSender.getPort());
    assertEquals(0, actualNioSender.getRequestCount());
    assertEquals(0, actualNioSender.remaining);
    assertEquals(0L, actualNioSender.getConnectTime());
    assertEquals(1, actualNioSender.getMaxRetryAttempts());
    assertEquals(25188, actualNioSender.getRxBufSize());
    assertEquals(25188, actualNioSender.getUdpRxBufSize());
    assertEquals(28, actualNioSender.getSoTrafficClass());
    assertEquals(3, actualNioSender.getSoLingerTime());
    assertEquals(3000L, actualNioSender.getTimeout());
    assertEquals(65536, actualNioSender.getTxBufSize());
    assertEquals(65536, actualNioSender.getUdpTxBufSize());
    assertFalse(actualNioSender.getDirectBuffer());
    assertFalse(actualNioSender.getSoKeepAlive());
    assertFalse(actualNioSender.getSoLingerOn());
    assertFalse(actualNioSender.isConnected());
    assertFalse(actualNioSender.isUdpBased());
    assertFalse(actualNioSender.isComplete());
    assertFalse(actualNioSender.connecting);
    assertTrue(actualNioSender.getOoBInline());
    assertTrue(actualNioSender.getSoReuseAddress());
    assertTrue(actualNioSender.getTcpNoDelay());
    assertTrue(actualNioSender.getThrowOnFailedAck());
  }

  /**
   * Test {@link NioSender#read()}.
   * <p>
   * Method under test: {@link NioSender#read()}
   */
  @Test
  public void testRead() throws IOException {
    // Arrange, Act and Assert
    assertTrue((new NioSender()).read());
  }

  /**
   * Test {@link NioSender#write()}.
   * <p>
   * Method under test: {@link NioSender#write()}
   */
  @Test
  public void testWrite() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> (new NioSender()).write());
  }

  /**
   * Test {@link NioSender#setMessage(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then {@link NioSender} (default constructor) {@link NioSender#writebuf} hasArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioSender#setMessage(byte[], int, int)}
   */
  @Test
  public void testSetMessageWithDataOffsetLength_givenNioSender_thenNioSenderWritebufHasArray() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act
    nioSender.setMessage(data, 2, 3);

    // Assert
    ByteBuffer byteBuffer = nioSender.writebuf;
    assertEquals(0, byteBuffer.position());
    assertEquals(3, byteBuffer.capacity());
    assertEquals(3, byteBuffer.limit());
    assertEquals(3, nioSender.remaining);
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    assertSame(data, nioSender.getMessage());
    byte[] expectedArrayResult = "AXA".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, byteBuffer.array());
  }

  /**
   * Test {@link NioSender#setMessage(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>Then not {@link NioSender} (default constructor) {@link NioSender#writebuf} hasArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioSender#setMessage(byte[], int, int)}
   */
  @Test
  public void testSetMessageWithDataOffsetLength_thenNotNioSenderWritebufHasArray() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setDirectBuffer(true);
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act
    nioSender.setMessage(data, 2, 3);

    // Assert
    ByteBuffer byteBuffer = nioSender.writebuf;
    assertEquals(0, byteBuffer.position());
    assertEquals(3, byteBuffer.capacity());
    assertEquals(3, byteBuffer.limit());
    assertEquals(3, nioSender.remaining);
    assertFalse(byteBuffer.hasArray());
    assertTrue(byteBuffer.hasRemaining());
    assertSame(data, nioSender.getMessage());
  }

  /**
   * Test {@link NioSender#setMessage(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link NioSender} (default constructor) {@link NioSender#remaining} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioSender#setMessage(byte[], int, int)}
   */
  @Test
  public void testSetMessageWithDataOffsetLength_whenNull_thenNioSenderRemainingIsZero() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setMessage(null, 2, 3);

    // Assert that nothing has changed
    assertEquals(0, nioSender.remaining);
  }

  /**
   * Test {@link NioSender#setMessage(byte[])} with {@code data}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then {@link NioSender} (default constructor) {@link NioSender#writebuf} hasArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioSender#setMessage(byte[])}
   */
  @Test
  public void testSetMessageWithData_givenNioSender_thenNioSenderWritebufHasArray() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act
    nioSender.setMessage(data);

    // Assert
    ByteBuffer byteBuffer = nioSender.writebuf;
    assertEquals(0, byteBuffer.position());
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertEquals(8, nioSender.remaining);
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    assertSame(data, nioSender.getMessage());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, byteBuffer.array());
  }

  /**
   * Test {@link NioSender#setMessage(byte[])} with {@code data}.
   * <ul>
   *   <li>Then not {@link NioSender} (default constructor) {@link NioSender#writebuf} hasArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioSender#setMessage(byte[])}
   */
  @Test
  public void testSetMessageWithData_thenNotNioSenderWritebufHasArray() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setDirectBuffer(true);
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act
    nioSender.setMessage(data);

    // Assert
    ByteBuffer byteBuffer = nioSender.writebuf;
    assertEquals(0, byteBuffer.position());
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertEquals(8, nioSender.remaining);
    assertFalse(byteBuffer.hasArray());
    assertTrue(byteBuffer.hasRemaining());
    assertSame(data, nioSender.getMessage());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NioSender#setComplete(boolean)}
   *   <li>{@link NioSender#setSelector(Selector)}
   *   <li>{@link NioSender#getMessage()}
   *   <li>{@link NioSender#getSelector()}
   *   <li>{@link NioSender#isComplete()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setComplete(true);
    nioSender.setSelector(null);
    byte[] actualMessage = nioSender.getMessage();
    Selector actualSelector = nioSender.getSelector();

    // Assert
    assertNull(actualMessage);
    assertNull(actualSelector);
    assertTrue(nioSender.isComplete());
  }
}
