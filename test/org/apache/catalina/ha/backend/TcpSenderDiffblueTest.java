package org.apache.catalina.ha.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class TcpSenderDiffblueTest {
  /**
   * Test {@link TcpSender#init(HeartbeatListener)}.
   * <ul>
   *   <li>Given {@code ,}.</li>
   *   <li>Then {@link TcpSender} (default constructor) {@link TcpSender#config} ProxyList is {@code ,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpSender#init(HeartbeatListener)}
   */
  @Test
  public void testInit_givenComma_thenTcpSenderConfigProxyListIsComma() throws Exception {
    // Arrange
    TcpSender tcpSender = new TcpSender();

    HeartbeatListener config = new HeartbeatListener();
    config.setProxyList(",");

    // Act
    tcpSender.init(config);

    // Assert
    HeartbeatListener heartbeatListener = tcpSender.config;
    assertEquals(",", heartbeatListener.getProxyList());
    assertEquals("/HeartbeatListener", heartbeatListener.getProxyURL());
    assertEquals("224.0.1.105", heartbeatListener.getGroup());
    assertNull(heartbeatListener.getHost());
    assertEquals(0, tcpSender.connectionReaders.length);
    assertEquals(0, tcpSender.connectionWriters.length);
    assertEquals(0, tcpSender.connections.length);
    assertEquals(0, tcpSender.proxies.length);
    assertEquals(23364, heartbeatListener.getMultiport());
    assertEquals(8009, heartbeatListener.getPort());
    assertEquals(Short.SIZE, heartbeatListener.getTtl());
  }

  /**
   * Test {@link TcpSender#init(HeartbeatListener)}.
   * <ul>
   *   <li>Given {@code Proxy List}.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpSender#init(HeartbeatListener)}
   */
  @Test
  public void testInit_givenProxyList_thenThrowException() throws Exception {
    // Arrange
    TcpSender tcpSender = new TcpSender();

    HeartbeatListener config = new HeartbeatListener();
    config.setProxyList("Proxy List");

    // Act and Assert
    assertThrows(Exception.class, () -> tcpSender.init(config));
  }

  /**
   * Test {@link TcpSender#send(String)}.
   * <p>
   * Method under test: {@link TcpSender#send(String)}
   */
  @Test
  public void testSend() throws Exception {
    // Arrange, Act and Assert
    assertEquals(-1, (new TcpSender()).send("Mess"));
  }

  /**
   * Test new {@link TcpSender} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link TcpSender}
   */
  @Test
  public void testNewTcpSender() {
    // Arrange and Act
    TcpSender actualTcpSender = new TcpSender();

    // Assert
    assertNull(actualTcpSender.connectionReaders);
    assertNull(actualTcpSender.connectionWriters);
    assertNull(actualTcpSender.connections);
    assertNull(actualTcpSender.proxies);
    assertNull(actualTcpSender.config);
  }
}
