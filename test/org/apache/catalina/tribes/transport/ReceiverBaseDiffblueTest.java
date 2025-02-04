package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.MessageListener;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class ReceiverBaseDiffblueTest {
  /**
   * Test {@link ReceiverBase#stop()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#stop()}
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
   * Test {@link ReceiverBase#stop()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Executor is {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#stop()}
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
   * Test {@link ReceiverBase#getMessageListener()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMessageListener()}
   */
  @Test
  public void testGetMessageListener() {
    // Arrange, Act and Assert
    assertNull((new NioReceiver()).getMessageListener());
  }

  /**
   * Test {@link ReceiverBase#getPort()}.
   * <p>
   * Method under test: {@link ReceiverBase#getPort()}
   */
  @Test
  public void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(4000, (new NioReceiver()).getPort());
  }

  /**
   * Test {@link ReceiverBase#getRxBufSize()}.
   * <p>
   * Method under test: {@link ReceiverBase#getRxBufSize()}
   */
  @Test
  public void testGetRxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, (new NioReceiver()).getRxBufSize());
  }

  /**
   * Test {@link ReceiverBase#getTxBufSize()}.
   * <p>
   * Method under test: {@link ReceiverBase#getTxBufSize()}
   */
  @Test
  public void testGetTxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, (new NioReceiver()).getTxBufSize());
  }

  /**
   * Test {@link ReceiverBase#setMessageListener(MessageListener)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMessageListener(MessageListener)}
   */
  @Test
  public void testSetMessageListener() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    ChannelCoordinator listener = new ChannelCoordinator();

    // Act
    nioReceiver.setMessageListener(listener);

    // Assert
    assertSame(listener, nioReceiver.getListener());
    assertSame(listener, nioReceiver.getMessageListener());
  }

  /**
   * Test {@link ReceiverBase#setRxBufSize(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setRxBufSize(int)}
   */
  @Test
  public void testSetRxBufSize() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setRxBufSize(3);

    // Assert
    assertEquals(3, nioReceiver.getRxBufSize());
  }

  /**
   * Test {@link ReceiverBase#setTxBufSize(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setTxBufSize(int)}
   */
  @Test
  public void testSetTxBufSize() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setTxBufSize(3);

    // Assert
    assertEquals(3, nioReceiver.getTxBufSize());
  }

  /**
   * Test {@link ReceiverBase#bindUdp(DatagramSocket, int, int)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Address is {@code localhost}.</li>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#bindUdp(DatagramSocket, int, int)}
   */
  @Test
  public void testBindUdp_givenNioReceiverAddressIsLocalhost_whenZero_thenReturnZero() throws IOException {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setAddress("localhost");

    // Act and Assert
    assertEquals(0, nioReceiver.bindUdp(null, 1, 0));
  }

  /**
   * Test {@link ReceiverBase#getWorkerThreadOptions()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Direct is {@code false}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getWorkerThreadOptions()}
   */
  @Test
  public void testGetWorkerThreadOptions_givenNioReceiverDirectIsFalse_thenReturnZero() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setDirect(false);

    // Act and Assert
    assertEquals(0, nioReceiver.getWorkerThreadOptions());
  }

  /**
   * Test {@link ReceiverBase#getWorkerThreadOptions()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return four.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getWorkerThreadOptions()}
   */
  @Test
  public void testGetWorkerThreadOptions_givenNioReceiver_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4, (new NioReceiver()).getWorkerThreadOptions());
  }

  /**
   * Test {@link ReceiverBase#getDirect()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Direct is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getDirect()}
   */
  @Test
  public void testGetDirect_givenNioReceiverDirectIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setDirect(false);

    // Act and Assert
    assertFalse(nioReceiver.getDirect());
  }

  /**
   * Test {@link ReceiverBase#getDirect()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getDirect()}
   */
  @Test
  public void testGetDirect_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getDirect());
  }

  /**
   * Test {@link ReceiverBase#getSelectorTimeout()}.
   * <p>
   * Method under test: {@link ReceiverBase#getSelectorTimeout()}
   */
  @Test
  public void testGetSelectorTimeout() {
    // Arrange, Act and Assert
    assertEquals(5000L, (new NioReceiver()).getSelectorTimeout());
  }

  /**
   * Test {@link ReceiverBase#doListen()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Listen is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#doListen()}
   */
  @Test
  public void testDoListen_givenNioReceiverListenIsTrue_thenReturnTrue() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setListen(true);

    // Act and Assert
    assertTrue(nioReceiver.doListen());
  }

  /**
   * Test {@link ReceiverBase#doListen()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#doListen()}
   */
  @Test
  public void testDoListen_givenNioReceiver_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioReceiver()).doListen());
  }

  /**
   * Test {@link ReceiverBase#getListener()}.
   * <p>
   * Method under test: {@link ReceiverBase#getListener()}
   */
  @Test
  public void testGetListener() {
    // Arrange, Act and Assert
    assertNull((new NioReceiver()).getListener());
  }

  /**
   * Test {@link ReceiverBase#getTaskPool()}.
   * <p>
   * Method under test: {@link ReceiverBase#getTaskPool()}
   */
  @Test
  public void testGetTaskPool() {
    // Arrange, Act and Assert
    assertNull((new NioReceiver()).getTaskPool());
  }

  /**
   * Test {@link ReceiverBase#getAutoBind()}.
   * <p>
   * Method under test: {@link ReceiverBase#getAutoBind()}
   */
  @Test
  public void testGetAutoBind() {
    // Arrange, Act and Assert
    assertEquals(100, (new NioReceiver()).getAutoBind());
  }

  /**
   * Test {@link ReceiverBase#getMaxThreads()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMaxThreads()}
   */
  @Test
  public void testGetMaxThreads() {
    // Arrange, Act and Assert
    assertEquals(15, (new NioReceiver()).getMaxThreads());
  }

  /**
   * Test {@link ReceiverBase#getMinThreads()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMinThreads()}
   */
  @Test
  public void testGetMinThreads() {
    // Arrange, Act and Assert
    assertEquals(6, (new NioReceiver()).getMinThreads());
  }

  /**
   * Test {@link ReceiverBase#getTcpNoDelay()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) TcpNoDelay is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getTcpNoDelay()}
   */
  @Test
  public void testGetTcpNoDelay_givenNioReceiverTcpNoDelayIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setTcpNoDelay(false);

    // Act and Assert
    assertFalse(nioReceiver.getTcpNoDelay());
  }

  /**
   * Test {@link ReceiverBase#getTcpNoDelay()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getTcpNoDelay()}
   */
  @Test
  public void testGetTcpNoDelay_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getTcpNoDelay());
  }

  /**
   * Test {@link ReceiverBase#getSoKeepAlive()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) SoKeepAlive is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoKeepAlive()}
   */
  @Test
  public void testGetSoKeepAlive_givenNioReceiverSoKeepAliveIsTrue_thenReturnTrue() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setSoKeepAlive(true);

    // Act and Assert
    assertTrue(nioReceiver.getSoKeepAlive());
  }

  /**
   * Test {@link ReceiverBase#getSoKeepAlive()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoKeepAlive()}
   */
  @Test
  public void testGetSoKeepAlive_givenNioReceiver_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioReceiver()).getSoKeepAlive());
  }

  /**
   * Test {@link ReceiverBase#getOoBInline()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) OoBInline is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getOoBInline()}
   */
  @Test
  public void testGetOoBInline_givenNioReceiverOoBInlineIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setOoBInline(false);

    // Act and Assert
    assertFalse(nioReceiver.getOoBInline());
  }

  /**
   * Test {@link ReceiverBase#getOoBInline()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getOoBInline()}
   */
  @Test
  public void testGetOoBInline_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getOoBInline());
  }

  /**
   * Test {@link ReceiverBase#getSoLingerOn()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) SoLingerOn is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoLingerOn()}
   */
  @Test
  public void testGetSoLingerOn_givenNioReceiverSoLingerOnIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setSoLingerOn(false);

    // Act and Assert
    assertFalse(nioReceiver.getSoLingerOn());
  }

  /**
   * Test {@link ReceiverBase#getSoLingerOn()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoLingerOn()}
   */
  @Test
  public void testGetSoLingerOn_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getSoLingerOn());
  }

  /**
   * Test {@link ReceiverBase#getSoLingerTime()}.
   * <p>
   * Method under test: {@link ReceiverBase#getSoLingerTime()}
   */
  @Test
  public void testGetSoLingerTime() {
    // Arrange, Act and Assert
    assertEquals(3, (new NioReceiver()).getSoLingerTime());
  }

  /**
   * Test {@link ReceiverBase#getSoReuseAddress()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) SoReuseAddress is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoReuseAddress()}
   */
  @Test
  public void testGetSoReuseAddress_givenNioReceiverSoReuseAddressIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setSoReuseAddress(false);

    // Act and Assert
    assertFalse(nioReceiver.getSoReuseAddress());
  }

  /**
   * Test {@link ReceiverBase#getSoReuseAddress()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getSoReuseAddress()}
   */
  @Test
  public void testGetSoReuseAddress_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getSoReuseAddress());
  }

  /**
   * Test {@link ReceiverBase#getSoTrafficClass()}.
   * <p>
   * Method under test: {@link ReceiverBase#getSoTrafficClass()}
   */
  @Test
  public void testGetSoTrafficClass() {
    // Arrange, Act and Assert
    assertEquals(28, (new NioReceiver()).getSoTrafficClass());
  }

  /**
   * Test {@link ReceiverBase#getTimeout()}.
   * <p>
   * Method under test: {@link ReceiverBase#getTimeout()}
   */
  @Test
  public void testGetTimeout() {
    // Arrange, Act and Assert
    assertEquals(3000, (new NioReceiver()).getTimeout());
  }

  /**
   * Test {@link ReceiverBase#getUseBufferPool()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) UseBufferPool is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getUseBufferPool()}
   */
  @Test
  public void testGetUseBufferPool_givenNioReceiverUseBufferPoolIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setUseBufferPool(false);

    // Act and Assert
    assertFalse(nioReceiver.getUseBufferPool());
  }

  /**
   * Test {@link ReceiverBase#getUseBufferPool()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getUseBufferPool()}
   */
  @Test
  public void testGetUseBufferPool_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).getUseBufferPool());
  }

  /**
   * Test {@link ReceiverBase#getSecurePort()}.
   * <p>
   * Method under test: {@link ReceiverBase#getSecurePort()}
   */
  @Test
  public void testGetSecurePort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new NioReceiver()).getSecurePort());
  }

  /**
   * Test {@link ReceiverBase#getMinTasks()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMinTasks()}
   */
  @Test
  public void testGetMinTasks() {
    // Arrange, Act and Assert
    assertEquals(10, (new NioReceiver()).getMinTasks());
  }

  /**
   * Test {@link ReceiverBase#getMaxTasks()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMaxTasks()}
   */
  @Test
  public void testGetMaxTasks() {
    // Arrange, Act and Assert
    assertEquals(100, (new NioReceiver()).getMaxTasks());
  }

  /**
   * Test {@link ReceiverBase#getExecutor()}.
   * <p>
   * Method under test: {@link ReceiverBase#getExecutor()}
   */
  @Test
  public void testGetExecutor() {
    // Arrange, Act and Assert
    assertNull((new NioReceiver()).getExecutor());
  }

  /**
   * Test {@link ReceiverBase#isListening()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Listen is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#isListening()}
   */
  @Test
  public void testIsListening_givenNioReceiverListenIsTrue_thenReturnTrue() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setListen(true);

    // Act and Assert
    assertTrue(nioReceiver.isListening());
  }

  /**
   * Test {@link ReceiverBase#isListening()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#isListening()}
   */
  @Test
  public void testIsListening_givenNioReceiver_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioReceiver()).isListening());
  }

  /**
   * Test {@link ReceiverBase#setSelectorTimeout(long)}.
   * <p>
   * Method under test: {@link ReceiverBase#setSelectorTimeout(long)}
   */
  @Test
  public void testSetSelectorTimeout() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setSelectorTimeout(1L);

    // Assert
    assertEquals(1L, nioReceiver.getSelectorTimeout());
  }

  /**
   * Test {@link ReceiverBase#setListen(boolean)}.
   * <p>
   * Method under test: {@link ReceiverBase#setListen(boolean)}
   */
  @Test
  public void testSetListen() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setListen(true);

    // Assert
    assertTrue(nioReceiver.isListening());
  }

  /**
   * Test {@link ReceiverBase#setListener(MessageListener)}.
   * <p>
   * Method under test: {@link ReceiverBase#setListener(MessageListener)}
   */
  @Test
  public void testSetListener() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    ChannelCoordinator listener = new ChannelCoordinator();

    // Act
    nioReceiver.setListener(listener);

    // Assert
    assertSame(listener, nioReceiver.getListener());
    assertSame(listener, nioReceiver.getMessageListener());
  }

  /**
   * Test {@link ReceiverBase#setPool(RxTaskPool)}.
   * <p>
   * Method under test: {@link ReceiverBase#setPool(RxTaskPool)}
   */
  @Test
  public void testSetPool() throws Exception {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    RxTaskPool pool = new RxTaskPool(3, 1, new NioReceiver());

    // Act
    nioReceiver.setPool(pool);

    // Assert
    assertSame(pool, nioReceiver.getTaskPool());
  }

  /**
   * Test {@link ReceiverBase#setPort(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setPort(int)}
   */
  @Test
  public void testSetPort() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setPort(8080);

    // Assert
    assertEquals(8080, nioReceiver.getPort());
  }

  /**
   * Test {@link ReceiverBase#setAutoBind(int)}.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#setAutoBind(int)}
   */
  @Test
  public void testSetAutoBind_whenOne() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setAutoBind(1);

    // Assert
    assertEquals(1, nioReceiver.getAutoBind());
  }

  /**
   * Test {@link ReceiverBase#setAutoBind(int)}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#setAutoBind(int)}
   */
  @Test
  public void testSetAutoBind_whenZero() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setAutoBind(0);

    // Assert
    assertEquals(1, nioReceiver.getAutoBind());
  }

  /**
   * Test {@link ReceiverBase#setMaxThreads(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMaxThreads(int)}
   */
  @Test
  public void testSetMaxThreads() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setMaxThreads(3);

    // Assert
    assertEquals(3, nioReceiver.getMaxThreads());
  }

  /**
   * Test {@link ReceiverBase#setMinThreads(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMinThreads(int)}
   */
  @Test
  public void testSetMinThreads() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setMinThreads(1);

    // Assert
    assertEquals(1, nioReceiver.getMinThreads());
  }

  /**
   * Test {@link ReceiverBase#setSoKeepAlive(boolean)}.
   * <p>
   * Method under test: {@link ReceiverBase#setSoKeepAlive(boolean)}
   */
  @Test
  public void testSetSoKeepAlive() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setSoKeepAlive(true);

    // Assert
    assertTrue(nioReceiver.getSoKeepAlive());
  }

  /**
   * Test {@link ReceiverBase#setSoLingerTime(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setSoLingerTime(int)}
   */
  @Test
  public void testSetSoLingerTime() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setSoLingerTime(1);

    // Assert
    assertEquals(1, nioReceiver.getSoLingerTime());
  }

  /**
   * Test {@link ReceiverBase#setSoTrafficClass(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setSoTrafficClass(int)}
   */
  @Test
  public void testSetSoTrafficClass() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setSoTrafficClass(1);

    // Assert
    assertEquals(1, nioReceiver.getSoTrafficClass());
  }

  /**
   * Test {@link ReceiverBase#setTimeout(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setTimeout(int)}
   */
  @Test
  public void testSetTimeout() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setTimeout(10);

    // Assert
    assertEquals(10, nioReceiver.getTimeout());
  }

  /**
   * Test {@link ReceiverBase#setSecurePort(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setSecurePort(int)}
   */
  @Test
  public void testSetSecurePort() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setSecurePort(8080);

    // Assert
    assertEquals(8080, nioReceiver.getSecurePort());
  }

  /**
   * Test {@link ReceiverBase#setMinTasks(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMinTasks(int)}
   */
  @Test
  public void testSetMinTasks() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setMinTasks(1);

    // Assert
    assertEquals(1, nioReceiver.getMinTasks());
  }

  /**
   * Test {@link ReceiverBase#setMaxTasks(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMaxTasks(int)}
   */
  @Test
  public void testSetMaxTasks() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setMaxTasks(3);

    // Assert
    assertEquals(3, nioReceiver.getMaxTasks());
  }

  /**
   * Test {@link ReceiverBase#setExecutor(ExecutorService)}.
   * <p>
   * Method under test: {@link ReceiverBase#setExecutor(ExecutorService)}
   */
  @Test
  public void testSetExecutor() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    StandardThreadExecutor executor = new StandardThreadExecutor();

    // Act
    nioReceiver.setExecutor(executor);

    // Assert
    assertSame(executor, nioReceiver.getExecutor());
  }

  /**
   * Test {@link ReceiverBase#getUdpPort()}.
   * <p>
   * Method under test: {@link ReceiverBase#getUdpPort()}
   */
  @Test
  public void testGetUdpPort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new NioReceiver()).getUdpPort());
  }

  /**
   * Test {@link ReceiverBase#setUdpPort(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setUdpPort(int)}
   */
  @Test
  public void testSetUdpPort() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setUdpPort(8080);

    // Assert
    assertEquals(8080, nioReceiver.getUdpPort());
  }

  /**
   * Test {@link ReceiverBase#getUdpRxBufSize()}.
   * <p>
   * Method under test: {@link ReceiverBase#getUdpRxBufSize()}
   */
  @Test
  public void testGetUdpRxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, (new NioReceiver()).getUdpRxBufSize());
  }

  /**
   * Test {@link ReceiverBase#setUdpRxBufSize(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setUdpRxBufSize(int)}
   */
  @Test
  public void testSetUdpRxBufSize() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setUdpRxBufSize(3);

    // Assert
    assertEquals(3, nioReceiver.getUdpRxBufSize());
  }

  /**
   * Test {@link ReceiverBase#getUdpTxBufSize()}.
   * <p>
   * Method under test: {@link ReceiverBase#getUdpTxBufSize()}
   */
  @Test
  public void testGetUdpTxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, (new NioReceiver()).getUdpTxBufSize());
  }

  /**
   * Test {@link ReceiverBase#setUdpTxBufSize(int)}.
   * <p>
   * Method under test: {@link ReceiverBase#setUdpTxBufSize(int)}
   */
  @Test
  public void testSetUdpTxBufSize() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setUdpTxBufSize(3);

    // Assert
    assertEquals(3, nioReceiver.getUdpTxBufSize());
  }

  /**
   * Test {@link ReceiverBase#getChannel()}.
   * <p>
   * Method under test: {@link ReceiverBase#getChannel()}
   */
  @Test
  public void testGetChannel() {
    // Arrange, Act and Assert
    assertNull((new NioReceiver()).getChannel());
  }

  /**
   * Test {@link ReceiverBase#setChannel(Channel)}.
   * <p>
   * Method under test: {@link ReceiverBase#setChannel(Channel)}
   */
  @Test
  public void testSetChannel() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    GroupChannel channel = new GroupChannel();

    // Act
    nioReceiver.setChannel(channel);

    // Assert
    assertSame(channel, nioReceiver.getChannel());
  }

  /**
   * Test {@link ReceiverBase#getPoolSize()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getPoolSize()}
   */
  @Test
  public void testGetPoolSize_givenNioReceiver_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new NioReceiver()).getPoolSize());
  }

  /**
   * Test {@link ReceiverBase#getPoolSize()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getPoolSize()}
   */
  @Test
  public void testGetPoolSize_thenReturnZero() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(new ScheduledThreadPoolExecutor(1));

    // Act and Assert
    assertEquals(0, nioReceiver.getPoolSize());
  }

  /**
   * Test {@link ReceiverBase#getTaskCount()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getTaskCount()}
   */
  @Test
  public void testGetTaskCount_givenNioReceiver_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new NioReceiver()).getTaskCount());
  }

  /**
   * Test {@link ReceiverBase#getTaskCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getTaskCount()}
   */
  @Test
  public void testGetTaskCount_thenReturnZero() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(new ScheduledThreadPoolExecutor(1));

    // Act and Assert
    assertEquals(0L, nioReceiver.getTaskCount());
  }

  /**
   * Test {@link ReceiverBase#getCompletedTaskCount()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getCompletedTaskCount()}
   */
  @Test
  public void testGetCompletedTaskCount_givenNioReceiver_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new NioReceiver()).getCompletedTaskCount());
  }

  /**
   * Test {@link ReceiverBase#getCompletedTaskCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#getCompletedTaskCount()}
   */
  @Test
  public void testGetCompletedTaskCount_thenReturnZero() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(new ScheduledThreadPoolExecutor(1));

    // Act and Assert
    assertEquals(0L, nioReceiver.getCompletedTaskCount());
  }

  /**
   * Test {@link ReceiverBase#isDaemon()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Daemon is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#isDaemon()}
   */
  @Test
  public void testIsDaemon_givenNioReceiverDaemonIsFalse_thenReturnFalse() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setDaemon(false);

    // Act and Assert
    assertFalse(nioReceiver.isDaemon());
  }

  /**
   * Test {@link ReceiverBase#isDaemon()}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReceiverBase#isDaemon()}
   */
  @Test
  public void testIsDaemon_givenNioReceiver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioReceiver()).isDaemon());
  }

  /**
   * Test {@link ReceiverBase#getMaxIdleTime()}.
   * <p>
   * Method under test: {@link ReceiverBase#getMaxIdleTime()}
   */
  @Test
  public void testGetMaxIdleTime() {
    // Arrange, Act and Assert
    assertEquals(60000L, (new NioReceiver()).getMaxIdleTime());
  }

  /**
   * Test {@link ReceiverBase#setMaxIdleTime(long)}.
   * <p>
   * Method under test: {@link ReceiverBase#setMaxIdleTime(long)}
   */
  @Test
  public void testSetMaxIdleTime() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();

    // Act
    nioReceiver.setMaxIdleTime(1L);

    // Assert
    assertEquals(1L, nioReceiver.getMaxIdleTime());
  }
}
