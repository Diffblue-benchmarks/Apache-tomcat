package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.InetAddress;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.transport.nio.NioSender;
import org.junit.Test;

public class AbstractSenderDiffblueTest {
  /**
   * Test {@link AbstractSender#keepalive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) KeepAliveTime is {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#keepalive()}
   */
  @Test
  public void testKeepalive_givenNioSenderKeepAliveTimeIsMax_value_thenReturnFalse() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setUdpBased(false);
    nioSender.setKeepAliveCount(0);
    nioSender.setKeepAliveTime(Long.MAX_VALUE);

    // Act and Assert
    assertFalse(nioSender.keepalive());
  }

  /**
   * Test {@link AbstractSender#keepalive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) RequestCount is three.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#keepalive()}
   */
  @Test
  public void testKeepalive_givenNioSenderRequestCountIsThree_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setRequestCount(3);
    nioSender.setUdpBased(false);
    nioSender.setKeepAliveCount(0);
    nioSender.setKeepAliveTime(0L);

    // Act and Assert
    assertTrue(nioSender.keepalive());
  }

  /**
   * Test {@link AbstractSender#keepalive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) UdpBased is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#keepalive()}
   */
  @Test
  public void testKeepalive_givenNioSenderUdpBasedIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setUdpBased(true);

    // Act and Assert
    assertTrue(nioSender.keepalive());
  }

  /**
   * Test {@link AbstractSender#keepalive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#keepalive()}
   */
  @Test
  public void testKeepalive_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).keepalive());
  }

  /**
   * Test {@link AbstractSender#setConnected(boolean)}.
   * <p>
   * Method under test: {@link AbstractSender#setConnected(boolean)}
   */
  @Test
  public void testSetConnected() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setConnected(true);

    // Assert
    assertTrue(nioSender.isConnected());
  }

  /**
   * Test {@link AbstractSender#isConnected()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) Connected is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#isConnected()}
   */
  @Test
  public void testIsConnected_givenNioSenderConnectedIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setConnected(true);

    // Act and Assert
    assertTrue(nioSender.isConnected());
  }

  /**
   * Test {@link AbstractSender#isConnected()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#isConnected()}
   */
  @Test
  public void testIsConnected_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).isConnected());
  }

  /**
   * Test {@link AbstractSender#getConnectTime()}.
   * <p>
   * Method under test: {@link AbstractSender#getConnectTime()}
   */
  @Test
  public void testGetConnectTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new NioSender()).getConnectTime());
  }

  /**
   * Test {@link AbstractSender#getDestination()}.
   * <p>
   * Method under test: {@link AbstractSender#getDestination()}
   */
  @Test
  public void testGetDestination() {
    // Arrange, Act and Assert
    assertNull((new NioSender()).getDestination());
  }

  /**
   * Test {@link AbstractSender#getKeepAliveCount()}.
   * <p>
   * Method under test: {@link AbstractSender#getKeepAliveCount()}
   */
  @Test
  public void testGetKeepAliveCount() {
    // Arrange, Act and Assert
    assertEquals(-1, (new NioSender()).getKeepAliveCount());
  }

  /**
   * Test {@link AbstractSender#getKeepAliveTime()}.
   * <p>
   * Method under test: {@link AbstractSender#getKeepAliveTime()}
   */
  @Test
  public void testGetKeepAliveTime() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new NioSender()).getKeepAliveTime());
  }

  /**
   * Test {@link AbstractSender#getRequestCount()}.
   * <p>
   * Method under test: {@link AbstractSender#getRequestCount()}
   */
  @Test
  public void testGetRequestCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new NioSender()).getRequestCount());
  }

  /**
   * Test {@link AbstractSender#getRxBufSize()}.
   * <p>
   * Method under test: {@link AbstractSender#getRxBufSize()}
   */
  @Test
  public void testGetRxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, (new NioSender()).getRxBufSize());
  }

  /**
   * Test {@link AbstractSender#getTimeout()}.
   * <p>
   * Method under test: {@link AbstractSender#getTimeout()}
   */
  @Test
  public void testGetTimeout() {
    // Arrange, Act and Assert
    assertEquals(3000L, (new NioSender()).getTimeout());
  }

  /**
   * Test {@link AbstractSender#getTxBufSize()}.
   * <p>
   * Method under test: {@link AbstractSender#getTxBufSize()}
   */
  @Test
  public void testGetTxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, (new NioSender()).getTxBufSize());
  }

  /**
   * Test {@link AbstractSender#getAddress()}.
   * <p>
   * Method under test: {@link AbstractSender#getAddress()}
   */
  @Test
  public void testGetAddress() {
    // Arrange, Act and Assert
    assertNull((new NioSender()).getAddress());
  }

  /**
   * Test {@link AbstractSender#getPort()}.
   * <p>
   * Method under test: {@link AbstractSender#getPort()}
   */
  @Test
  public void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(0, (new NioSender()).getPort());
  }

  /**
   * Test {@link AbstractSender#getMaxRetryAttempts()}.
   * <p>
   * Method under test: {@link AbstractSender#getMaxRetryAttempts()}
   */
  @Test
  public void testGetMaxRetryAttempts() {
    // Arrange, Act and Assert
    assertEquals(1, (new NioSender()).getMaxRetryAttempts());
  }

  /**
   * Test {@link AbstractSender#setDirectBuffer(boolean)}.
   * <p>
   * Method under test: {@link AbstractSender#setDirectBuffer(boolean)}
   */
  @Test
  public void testSetDirectBuffer() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setDirectBuffer(true);

    // Assert
    assertTrue(nioSender.getDirectBuffer());
  }

  /**
   * Test {@link AbstractSender#getDirectBuffer()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) DirectBuffer is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getDirectBuffer()}
   */
  @Test
  public void testGetDirectBuffer_givenNioSenderDirectBufferIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setDirectBuffer(true);

    // Act and Assert
    assertTrue(nioSender.getDirectBuffer());
  }

  /**
   * Test {@link AbstractSender#getDirectBuffer()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getDirectBuffer()}
   */
  @Test
  public void testGetDirectBuffer_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).getDirectBuffer());
  }

  /**
   * Test {@link AbstractSender#getAttempt()}.
   * <p>
   * Method under test: {@link AbstractSender#getAttempt()}
   */
  @Test
  public void testGetAttempt() {
    // Arrange, Act and Assert
    assertEquals(0, (new NioSender()).getAttempt());
  }

  /**
   * Test {@link AbstractSender#getTcpNoDelay()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) TcpNoDelay is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getTcpNoDelay()}
   */
  @Test
  public void testGetTcpNoDelay_givenNioSenderTcpNoDelayIsFalse_thenReturnFalse() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setTcpNoDelay(false);

    // Act and Assert
    assertFalse(nioSender.getTcpNoDelay());
  }

  /**
   * Test {@link AbstractSender#getTcpNoDelay()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getTcpNoDelay()}
   */
  @Test
  public void testGetTcpNoDelay_givenNioSender_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioSender()).getTcpNoDelay());
  }

  /**
   * Test {@link AbstractSender#getSoKeepAlive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) SoKeepAlive is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoKeepAlive()}
   */
  @Test
  public void testGetSoKeepAlive_givenNioSenderSoKeepAliveIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setSoKeepAlive(true);

    // Act and Assert
    assertTrue(nioSender.getSoKeepAlive());
  }

  /**
   * Test {@link AbstractSender#getSoKeepAlive()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoKeepAlive()}
   */
  @Test
  public void testGetSoKeepAlive_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).getSoKeepAlive());
  }

  /**
   * Test {@link AbstractSender#getOoBInline()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) OoBInline is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getOoBInline()}
   */
  @Test
  public void testGetOoBInline_givenNioSenderOoBInlineIsFalse_thenReturnFalse() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setOoBInline(false);

    // Act and Assert
    assertFalse(nioSender.getOoBInline());
  }

  /**
   * Test {@link AbstractSender#getOoBInline()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getOoBInline()}
   */
  @Test
  public void testGetOoBInline_givenNioSender_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioSender()).getOoBInline());
  }

  /**
   * Test {@link AbstractSender#getSoReuseAddress()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) SoReuseAddress is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoReuseAddress()}
   */
  @Test
  public void testGetSoReuseAddress_givenNioSenderSoReuseAddressIsFalse_thenReturnFalse() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setSoReuseAddress(false);

    // Act and Assert
    assertFalse(nioSender.getSoReuseAddress());
  }

  /**
   * Test {@link AbstractSender#getSoReuseAddress()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoReuseAddress()}
   */
  @Test
  public void testGetSoReuseAddress_givenNioSender_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioSender()).getSoReuseAddress());
  }

  /**
   * Test {@link AbstractSender#getSoLingerOn()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) SoLingerOn is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoLingerOn()}
   */
  @Test
  public void testGetSoLingerOn_givenNioSenderSoLingerOnIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setSoLingerOn(true);

    // Act and Assert
    assertTrue(nioSender.getSoLingerOn());
  }

  /**
   * Test {@link AbstractSender#getSoLingerOn()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getSoLingerOn()}
   */
  @Test
  public void testGetSoLingerOn_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).getSoLingerOn());
  }

  /**
   * Test {@link AbstractSender#getSoLingerTime()}.
   * <p>
   * Method under test: {@link AbstractSender#getSoLingerTime()}
   */
  @Test
  public void testGetSoLingerTime() {
    // Arrange, Act and Assert
    assertEquals(3, (new NioSender()).getSoLingerTime());
  }

  /**
   * Test {@link AbstractSender#getSoTrafficClass()}.
   * <p>
   * Method under test: {@link AbstractSender#getSoTrafficClass()}
   */
  @Test
  public void testGetSoTrafficClass() {
    // Arrange, Act and Assert
    assertEquals(28, (new NioSender()).getSoTrafficClass());
  }

  /**
   * Test {@link AbstractSender#getThrowOnFailedAck()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) ThrowOnFailedAck is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getThrowOnFailedAck()}
   */
  @Test
  public void testGetThrowOnFailedAck_givenNioSenderThrowOnFailedAckIsFalse_thenReturnFalse() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setThrowOnFailedAck(false);

    // Act and Assert
    assertFalse(nioSender.getThrowOnFailedAck());
  }

  /**
   * Test {@link AbstractSender#getThrowOnFailedAck()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#getThrowOnFailedAck()}
   */
  @Test
  public void testGetThrowOnFailedAck_givenNioSender_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NioSender()).getThrowOnFailedAck());
  }

  /**
   * Test {@link AbstractSender#setKeepAliveCount(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setKeepAliveCount(int)}
   */
  @Test
  public void testSetKeepAliveCount() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setKeepAliveCount(3);

    // Assert
    assertEquals(3, nioSender.getKeepAliveCount());
  }

  /**
   * Test {@link AbstractSender#setKeepAliveTime(long)}.
   * <p>
   * Method under test: {@link AbstractSender#setKeepAliveTime(long)}
   */
  @Test
  public void testSetKeepAliveTime() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setKeepAliveTime(1L);

    // Assert
    assertEquals(1L, nioSender.getKeepAliveTime());
  }

  /**
   * Test {@link AbstractSender#setRequestCount(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setRequestCount(int)}
   */
  @Test
  public void testSetRequestCount() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setRequestCount(3);

    // Assert
    assertEquals(3, nioSender.getRequestCount());
  }

  /**
   * Test {@link AbstractSender#setRxBufSize(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setRxBufSize(int)}
   */
  @Test
  public void testSetRxBufSize() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setRxBufSize(3);

    // Assert
    assertEquals(3, nioSender.getRxBufSize());
  }

  /**
   * Test {@link AbstractSender#setTimeout(long)}.
   * <p>
   * Method under test: {@link AbstractSender#setTimeout(long)}
   */
  @Test
  public void testSetTimeout() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setTimeout(10L);

    // Assert
    assertEquals(10L, nioSender.getTimeout());
  }

  /**
   * Test {@link AbstractSender#setTxBufSize(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setTxBufSize(int)}
   */
  @Test
  public void testSetTxBufSize() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setTxBufSize(3);

    // Assert
    assertEquals(3, nioSender.getTxBufSize());
  }

  /**
   * Test {@link AbstractSender#setConnectTime(long)}.
   * <p>
   * Method under test: {@link AbstractSender#setConnectTime(long)}
   */
  @Test
  public void testSetConnectTime() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setConnectTime(1L);

    // Assert
    assertEquals(1L, nioSender.getConnectTime());
  }

  /**
   * Test {@link AbstractSender#setMaxRetryAttempts(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setMaxRetryAttempts(int)}
   */
  @Test
  public void testSetMaxRetryAttempts() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setMaxRetryAttempts(3);

    // Assert
    assertEquals(3, nioSender.getMaxRetryAttempts());
  }

  /**
   * Test {@link AbstractSender#setAttempt(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setAttempt(int)}
   */
  @Test
  public void testSetAttempt() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setAttempt(1);

    // Assert
    assertEquals(1, nioSender.getAttempt());
  }

  /**
   * Test {@link AbstractSender#setSoKeepAlive(boolean)}.
   * <p>
   * Method under test: {@link AbstractSender#setSoKeepAlive(boolean)}
   */
  @Test
  public void testSetSoKeepAlive() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setSoKeepAlive(true);

    // Assert
    assertTrue(nioSender.getSoKeepAlive());
  }

  /**
   * Test {@link AbstractSender#setSoLingerOn(boolean)}.
   * <p>
   * Method under test: {@link AbstractSender#setSoLingerOn(boolean)}
   */
  @Test
  public void testSetSoLingerOn() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setSoLingerOn(true);

    // Assert
    assertTrue(nioSender.getSoLingerOn());
  }

  /**
   * Test {@link AbstractSender#setSoLingerTime(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setSoLingerTime(int)}
   */
  @Test
  public void testSetSoLingerTime() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setSoLingerTime(1);

    // Assert
    assertEquals(1, nioSender.getSoLingerTime());
  }

  /**
   * Test {@link AbstractSender#setSoTrafficClass(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setSoTrafficClass(int)}
   */
  @Test
  public void testSetSoTrafficClass() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setSoTrafficClass(1);

    // Assert
    assertEquals(1, nioSender.getSoTrafficClass());
  }

  /**
   * Test {@link AbstractSender#setDestination(Member)}.
   * <ul>
   *   <li>Then {@link NioSender} (default constructor) Address CanonicalHostName is {@code 0.0.0.42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#setDestination(Member)}
   */
  @Test
  public void testSetDestination_thenNioSenderAddressCanonicalHostNameIs00042() throws IOException {
    // Arrange
    NioSender nioSender = new NioSender();
    MemberImpl destination = new MemberImpl("42", 8080, 1L);

    // Act
    nioSender.setDestination(destination);

    // Assert
    InetAddress address = nioSender.getAddress();
    assertEquals("0.0.0.42", address.getCanonicalHostName());
    assertEquals("0.0.0.42", address.getHostAddress());
    assertEquals("0.0.0.42", address.getHostName());
    assertEquals(8080, nioSender.getPort());
    assertFalse(address.isAnyLocalAddress());
    assertFalse(address.isLinkLocalAddress());
    assertFalse(address.isLoopbackAddress());
    assertFalse(address.isMCGlobal());
    assertFalse(address.isMCLinkLocal());
    assertFalse(address.isMCNodeLocal());
    assertFalse(address.isMCOrgLocal());
    assertFalse(address.isMCSiteLocal());
    assertFalse(address.isMulticastAddress());
    assertFalse(address.isSiteLocalAddress());
    assertSame(destination, nioSender.getDestination());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, address.getAddress());
  }

  /**
   * Test {@link AbstractSender#setPort(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setPort(int)}
   */
  @Test
  public void testSetPort() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setPort(8080);

    // Assert
    assertEquals(8080, nioSender.getPort());
  }

  /**
   * Test {@link AbstractSender#isUdpBased()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor) UdpBased is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#isUdpBased()}
   */
  @Test
  public void testIsUdpBased_givenNioSenderUdpBasedIsTrue_thenReturnTrue() {
    // Arrange
    NioSender nioSender = new NioSender();
    nioSender.setUdpBased(true);

    // Act and Assert
    assertTrue(nioSender.isUdpBased());
  }

  /**
   * Test {@link AbstractSender#isUdpBased()}.
   * <ul>
   *   <li>Given {@link NioSender} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractSender#isUdpBased()}
   */
  @Test
  public void testIsUdpBased_givenNioSender_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NioSender()).isUdpBased());
  }

  /**
   * Test {@link AbstractSender#setUdpBased(boolean)}.
   * <p>
   * Method under test: {@link AbstractSender#setUdpBased(boolean)}
   */
  @Test
  public void testSetUdpBased() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setUdpBased(true);

    // Assert
    assertTrue(nioSender.isUdpBased());
  }

  /**
   * Test {@link AbstractSender#getUdpPort()}.
   * <p>
   * Method under test: {@link AbstractSender#getUdpPort()}
   */
  @Test
  public void testGetUdpPort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new NioSender()).getUdpPort());
  }

  /**
   * Test {@link AbstractSender#setUdpPort(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setUdpPort(int)}
   */
  @Test
  public void testSetUdpPort() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setUdpPort(8080);

    // Assert
    assertEquals(8080, nioSender.getUdpPort());
  }

  /**
   * Test {@link AbstractSender#getUdpRxBufSize()}.
   * <p>
   * Method under test: {@link AbstractSender#getUdpRxBufSize()}
   */
  @Test
  public void testGetUdpRxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, (new NioSender()).getUdpRxBufSize());
  }

  /**
   * Test {@link AbstractSender#setUdpRxBufSize(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setUdpRxBufSize(int)}
   */
  @Test
  public void testSetUdpRxBufSize() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setUdpRxBufSize(3);

    // Assert
    assertEquals(3, nioSender.getUdpRxBufSize());
  }

  /**
   * Test {@link AbstractSender#getUdpTxBufSize()}.
   * <p>
   * Method under test: {@link AbstractSender#getUdpTxBufSize()}
   */
  @Test
  public void testGetUdpTxBufSize() {
    // Arrange, Act and Assert
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, (new NioSender()).getUdpTxBufSize());
  }

  /**
   * Test {@link AbstractSender#setUdpTxBufSize(int)}.
   * <p>
   * Method under test: {@link AbstractSender#setUdpTxBufSize(int)}
   */
  @Test
  public void testSetUdpTxBufSize() {
    // Arrange
    NioSender nioSender = new NioSender();

    // Act
    nioSender.setUdpTxBufSize(3);

    // Assert
    assertEquals(3, nioSender.getUdpTxBufSize());
  }
}
