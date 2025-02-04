package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.ListenCallback;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.apache.catalina.tribes.transport.nio.NioReplicationTask;
import org.junit.Test;

public class AbstractRxTaskDiffblueTest {
  /**
   * Test {@link AbstractRxTask#setTaskPool(RxTaskPool)}.
   * <p>
   * Method under test: {@link AbstractRxTask#setTaskPool(RxTaskPool)}
   */
  @Test
  public void testSetTaskPool() throws Exception {
    // Arrange
    NioReceiver callback = new NioReceiver();
    NioReplicationTask nioReplicationTask = new NioReplicationTask(callback, new NioReceiver());
    RxTaskPool pool = new RxTaskPool(3, 1, new NioReceiver());

    // Act
    nioReplicationTask.setTaskPool(pool);

    // Assert
    assertSame(pool, nioReplicationTask.getTaskPool());
  }

  /**
   * Test {@link AbstractRxTask#setOptions(int)}.
   * <p>
   * Method under test: {@link AbstractRxTask#setOptions(int)}
   */
  @Test
  public void testSetOptions() {
    // Arrange
    NioReceiver callback = new NioReceiver();
    NioReplicationTask nioReplicationTask = new NioReplicationTask(callback, new NioReceiver());

    // Act
    nioReplicationTask.setOptions(1);

    // Assert
    assertEquals(1, nioReplicationTask.getOptions());
  }

  /**
   * Test {@link AbstractRxTask#getTaskPool()}.
   * <p>
   * Method under test: {@link AbstractRxTask#getTaskPool()}
   */
  @Test
  public void testGetTaskPool() {
    // Arrange
    NioReceiver callback = new NioReceiver();

    // Act and Assert
    assertNull((new NioReplicationTask(callback, new NioReceiver())).getTaskPool());
  }

  /**
   * Test {@link AbstractRxTask#getOptions()}.
   * <p>
   * Method under test: {@link AbstractRxTask#getOptions()}
   */
  @Test
  public void testGetOptions() {
    // Arrange
    NioReceiver callback = new NioReceiver();

    // Act and Assert
    assertEquals(0, (new NioReplicationTask(callback, new NioReceiver())).getOptions());
  }

  /**
   * Test {@link AbstractRxTask#getCallback()}.
   * <p>
   * Method under test: {@link AbstractRxTask#getCallback()}
   */
  @Test
  public void testGetCallback() {
    // Arrange
    NioReceiver callback = new NioReceiver();

    // Act
    ListenCallback actualCallback = (new NioReplicationTask(callback, new NioReceiver())).getCallback();
    actualCallback.messageDataReceived(new ChannelData());

    // Assert
    assertTrue(actualCallback instanceof NioReceiver);
    assertNull(((NioReceiver) actualCallback).getExecutor());
    assertNull(((NioReceiver) actualCallback).getChannel());
    assertNull(((NioReceiver) actualCallback).getListener());
    assertNull(((NioReceiver) actualCallback).getMessageListener());
    assertNull(((NioReceiver) actualCallback).getTaskPool());
    assertEquals(-1, ((NioReceiver) actualCallback).getActiveCount());
    assertEquals(-1, ((NioReceiver) actualCallback).getPoolSize());
    assertEquals(-1, ((NioReceiver) actualCallback).getSecurePort());
    assertEquals(-1, ((NioReceiver) actualCallback).getUdpPort());
    assertEquals(-1L, ((NioReceiver) actualCallback).getCompletedTaskCount());
    assertEquals(-1L, ((NioReceiver) actualCallback).getTaskCount());
    assertEquals(10, ((NioReceiver) actualCallback).getMinTasks());
    assertEquals(100, ((NioReceiver) actualCallback).getAutoBind());
    assertEquals(100, ((NioReceiver) actualCallback).getMaxTasks());
    assertEquals(15, ((NioReceiver) actualCallback).getMaxThreads());
    assertEquals(28, ((NioReceiver) actualCallback).getSoTrafficClass());
    assertEquals(3, ((NioReceiver) actualCallback).getSoLingerTime());
    assertEquals(3000, ((NioReceiver) actualCallback).getTimeout());
    assertEquals(4, ((NioReceiver) actualCallback).getWorkerThreadOptions());
    assertEquals(4000, ((NioReceiver) actualCallback).getPort());
    assertEquals(5000L, ((NioReceiver) actualCallback).getSelectorTimeout());
    assertEquals(6, ((NioReceiver) actualCallback).getMinThreads());
    assertEquals(60000L, ((NioReceiver) actualCallback).getMaxIdleTime());
    assertFalse(((NioReceiver) actualCallback).getSoKeepAlive());
    assertFalse(((NioReceiver) actualCallback).isListening());
    assertTrue(((NioReceiver) actualCallback).getDirect());
    assertTrue(((NioReceiver) actualCallback).getOoBInline());
    assertTrue(((NioReceiver) actualCallback).getSoLingerOn());
    assertTrue(((NioReceiver) actualCallback).getSoReuseAddress());
    assertTrue(((NioReceiver) actualCallback).getTcpNoDelay());
    assertTrue(((NioReceiver) actualCallback).getUseBufferPool());
    assertTrue(((NioReceiver) actualCallback).isDaemon());
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, ((NioReceiver) actualCallback).getTxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, ((NioReceiver) actualCallback).getUdpTxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, ((NioReceiver) actualCallback).getRxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, ((NioReceiver) actualCallback).getUdpRxBufSize());
  }

  /**
   * Test {@link AbstractRxTask#getUseBufferPool()}.
   * <p>
   * Method under test: {@link AbstractRxTask#getUseBufferPool()}
   */
  @Test
  public void testGetUseBufferPool() {
    // Arrange
    NioReceiver callback = new NioReceiver();

    // Act and Assert
    assertTrue((new NioReplicationTask(callback, new NioReceiver())).getUseBufferPool());
  }
}
