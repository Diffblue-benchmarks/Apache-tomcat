package org.apache.catalina.tribes.transport.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.catalina.core.StandardThreadExecutor;
import org.junit.Test;

public class NioReceiverDiffblueTest {
  /**
   * Test new {@link NioReceiver} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NioReceiver}
   */
  @Test
  public void testNewNioReceiver() {
    // Arrange and Act
    NioReceiver actualNioReceiver = new NioReceiver();

    // Assert
    assertNull(actualNioReceiver.getExecutor());
    assertNull(actualNioReceiver.getChannel());
    assertNull(actualNioReceiver.getListener());
    assertNull(actualNioReceiver.getMessageListener());
    assertNull(actualNioReceiver.getTaskPool());
    assertEquals(-1, actualNioReceiver.getActiveCount());
    assertEquals(-1, actualNioReceiver.getPoolSize());
    assertEquals(-1, actualNioReceiver.getSecurePort());
    assertEquals(-1, actualNioReceiver.getUdpPort());
    assertEquals(-1L, actualNioReceiver.getCompletedTaskCount());
    assertEquals(-1L, actualNioReceiver.getTaskCount());
    assertEquals(10, actualNioReceiver.getMinTasks());
    assertEquals(100, actualNioReceiver.getAutoBind());
    assertEquals(100, actualNioReceiver.getMaxTasks());
    assertEquals(15, actualNioReceiver.getMaxThreads());
    assertEquals(25188, actualNioReceiver.getTxBufSize());
    assertEquals(25188, actualNioReceiver.getUdpTxBufSize());
    assertEquals(28, actualNioReceiver.getSoTrafficClass());
    assertEquals(3, actualNioReceiver.getSoLingerTime());
    assertEquals(3000, actualNioReceiver.getTimeout());
    assertEquals(4, actualNioReceiver.getWorkerThreadOptions());
    assertEquals(4000, actualNioReceiver.getPort());
    assertEquals(5000L, actualNioReceiver.getSelectorTimeout());
    assertEquals(6, actualNioReceiver.getMinThreads());
    assertEquals(60000L, actualNioReceiver.getMaxIdleTime());
    assertEquals(65536, actualNioReceiver.getRxBufSize());
    assertEquals(65536, actualNioReceiver.getUdpRxBufSize());
    assertFalse(actualNioReceiver.getSoKeepAlive());
    assertFalse(actualNioReceiver.isListening());
    assertTrue(actualNioReceiver.events.isEmpty());
    assertTrue(actualNioReceiver.getDirect());
    assertTrue(actualNioReceiver.getOoBInline());
    assertTrue(actualNioReceiver.getSoLingerOn());
    assertTrue(actualNioReceiver.getSoReuseAddress());
    assertTrue(actualNioReceiver.getTcpNoDelay());
    assertTrue(actualNioReceiver.getUseBufferPool());
    assertTrue(actualNioReceiver.isDaemon());
  }

  /**
   * Test {@link NioReceiver#stop()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#stop()}
   */
  @Test
  public void testStop_givenNioReceiver() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.stop();

    // Assert that nothing has changed
    assertNull(nioReceiver.getExecutor());
  }

  /**
   * Test {@link NioReceiver#stop()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Executor is {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#stop()}
   */
  @Test
  public void testStop_givenNioReceiverExecutorIsStandardThreadExecutor() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(new StandardThreadExecutor());

    // Act
    nioReceiver.stop();

    // Assert
    assertNull(nioReceiver.getExecutor());
  }

  /**
   * Test {@link NioReceiver#start()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Address is {@code localhost}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#start()}
   */
  @Test
  public void testStart_givenNioReceiverAddressIsLocalhost_thenThrowIOException() throws IOException {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setAddress("localhost");
    nioReceiver.setChannel(null);
    nioReceiver.setExecutor(new StandardThreadExecutor());

    // Act and Assert
    assertThrows(IOException.class, () -> nioReceiver.start());
  }

  /**
   * Test {@link NioReceiver#start()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Channel is {@code null}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#start()}
   */
  @Test
  public void testStart_givenNioReceiverChannelIsNull_thenThrowIOException() throws IOException {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setChannel(null);
    nioReceiver.setExecutor(new StandardThreadExecutor());

    // Act and Assert
    assertThrows(IOException.class, () -> nioReceiver.start());
  }

  /**
   * Test {@link NioReceiver#run()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#run()}
   */
  @Test
  public void testRun_givenNioReceiver() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.run();

    // Assert
    assertTrue(nioReceiver.isListening());
  }

  /**
   * Test {@link NioReceiver#run()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Listen is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioReceiver#run()}
   */
  @Test
  public void testRun_givenNioReceiverListenIsTrue() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setListen(true);

    // Act
    nioReceiver.run();

    // Assert that nothing has changed
    assertTrue(nioReceiver.isListening());
  }
}
