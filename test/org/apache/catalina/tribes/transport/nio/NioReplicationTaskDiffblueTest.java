package org.apache.catalina.tribes.transport.nio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.WritableByteChannel;
import org.apache.catalina.tribes.io.ListenCallback;
import org.apache.tomcat.jakartaee.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Test;

public class NioReplicationTaskDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NioReplicationTask#NioReplicationTask(ListenCallback, NioReceiver)}
   *   <li>{@link NioReplicationTask#setRxBufSize(int)}
   *   <li>{@link NioReplicationTask#getRxBufSize()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    NioReceiver callback = new NioReceiver();

    // Act
    NioReplicationTask actualNioReplicationTask = new NioReplicationTask(callback, new NioReceiver());
    actualNioReplicationTask.setRxBufSize(3);
    int actualRxBufSize = actualNioReplicationTask.getRxBufSize();

    // Assert
    assertNull(actualNioReplicationTask.getTaskPool());
    assertEquals(0, actualNioReplicationTask.getOptions());
    assertEquals(3, actualRxBufSize);
    assertTrue(actualNioReplicationTask.getUseBufferPool());
    assertSame(callback, actualNioReplicationTask.getCallback());
  }

  /**
   * Test {@link NioReplicationTask#sendAck(SelectionKey, WritableByteChannel, byte[], SocketAddress)}.
   * <ul>
   *   <li>Then {@link SeekableInMemoryByteChannel#SeekableInMemoryByteChannel()} position is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReplicationTask#sendAck(SelectionKey, WritableByteChannel, byte[], SocketAddress)}
   */
  @Test
  public void testSendAck_thenSeekableInMemoryByteChannelPositionIsEight() throws UnsupportedEncodingException {
    // Arrange
    NioReceiver callback = new NioReceiver();
    NioReplicationTask nioReplicationTask = new NioReplicationTask(callback, new NioReceiver());
    SeekableInMemoryByteChannel channel = new SeekableInMemoryByteChannel();
    byte[] command = "AXAXAXAX".getBytes("UTF-8");

    // Act
    nioReplicationTask.sendAck(null, channel, command, InetSocketAddress.createUnresolved("foo", 1));

    // Assert
    assertEquals(8L, channel.position());
    assertEquals(8L, channel.size());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, channel.array());
  }
}
