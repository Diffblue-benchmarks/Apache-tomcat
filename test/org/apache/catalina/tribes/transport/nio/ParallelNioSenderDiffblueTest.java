package org.apache.catalina.tribes.transport.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMember;
import org.junit.Test;

public class ParallelNioSenderDiffblueTest {
  /**
   * Test {@link ParallelNioSender#ParallelNioSender()}.
   * <p>
   * Method under test: default or parameterless constructor of {@link ParallelNioSender}
   */
  @Test
  public void testNewParallelNioSender() throws IOException {
    // Arrange and Act
    ParallelNioSender actualParallelNioSender = new ParallelNioSender();

    // Assert
    assertNull(actualParallelNioSender.getAddress());
    assertNull(actualParallelNioSender.getDestination());
    assertEquals(-1, actualParallelNioSender.getKeepAliveCount());
    assertEquals(-1, actualParallelNioSender.getUdpPort());
    assertEquals(-1L, actualParallelNioSender.getKeepAliveTime());
    assertEquals(0, actualParallelNioSender.getAttempt());
    assertEquals(0, actualParallelNioSender.getPort());
    assertEquals(0, actualParallelNioSender.getRequestCount());
    assertEquals(0L, actualParallelNioSender.getConnectTime());
    assertEquals(1, actualParallelNioSender.getMaxRetryAttempts());
    assertEquals(25188, actualParallelNioSender.getRxBufSize());
    assertEquals(25188, actualParallelNioSender.getUdpRxBufSize());
    assertEquals(28, actualParallelNioSender.getSoTrafficClass());
    assertEquals(3, actualParallelNioSender.getSoLingerTime());
    assertEquals(3000L, actualParallelNioSender.getTimeout());
    assertEquals(5000L, actualParallelNioSender.selectTimeout);
    assertEquals(65536, actualParallelNioSender.getTxBufSize());
    assertEquals(65536, actualParallelNioSender.getUdpTxBufSize());
    assertFalse(actualParallelNioSender.getDirectBuffer());
    assertFalse(actualParallelNioSender.getSoKeepAlive());
    assertFalse(actualParallelNioSender.getSoLingerOn());
    assertFalse(actualParallelNioSender.isUdpBased());
    assertTrue(actualParallelNioSender.getOoBInline());
    assertTrue(actualParallelNioSender.getSoReuseAddress());
    assertTrue(actualParallelNioSender.getTcpNoDelay());
    assertTrue(actualParallelNioSender.getThrowOnFailedAck());
    assertTrue(actualParallelNioSender.isConnected());
  }

  /**
   * Test {@link ParallelNioSender#sendMessage(Member[], ChannelMessage)}.
   * <ul>
   *   <li>Given {@code A A A A} Bytes is {@code UTF-8}.</li>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParallelNioSender#sendMessage(Member[], ChannelMessage)}
   */
  @Test
  public void testSendMessage_givenAAAABytesIsUtf8_thenThrowChannelException() throws IOException, ChannelException {
    // Arrange
    ParallelNioSender parallelNioSender = new ParallelNioSender();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost("A A A A ".getBytes("UTF-8"));
    byte[] uniqueId = "A A A A ".getBytes("UTF-8");

    ChannelData msg = new ChannelData(uniqueId, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act and Assert
    assertThrows(ChannelException.class, () -> parallelNioSender.sendMessage(new Member[]{memberImpl}, msg));
  }

  /**
   * Test {@link ParallelNioSender#sendMessage(Member[], ChannelMessage)}.
   * <ul>
   *   <li>Given {@code A A A A} Bytes is {@code UTF-8}.</li>
   *   <li>When {@code A}.</li>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParallelNioSender#sendMessage(Member[], ChannelMessage)}
   */
  @Test
  public void testSendMessage_givenAAAABytesIsUtf8_whenA_thenThrowChannelException()
      throws IOException, ChannelException {
    // Arrange
    ParallelNioSender parallelNioSender = new ParallelNioSender();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost("A A A A ".getBytes("UTF-8"));

    ChannelData msg = new ChannelData(new byte[]{'A', ' ', 'A', ' ', 'A', ' ', 4, ' '}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act and Assert
    assertThrows(ChannelException.class, () -> parallelNioSender.sendMessage(new Member[]{memberImpl}, msg));
  }

  /**
   * Test {@link ParallelNioSender#disconnect()}.
   * <p>
   * Method under test: {@link ParallelNioSender#disconnect()}
   */
  @Test
  public void testDisconnect() throws IOException {
    // Arrange
    ParallelNioSender parallelNioSender = new ParallelNioSender();

    // Act
    parallelNioSender.disconnect();

    // Assert
    assertFalse(parallelNioSender.isConnected());
  }

  /**
   * Test {@link ParallelNioSender#keepalive()}.
   * <p>
   * Method under test: {@link ParallelNioSender#keepalive()}
   */
  @Test
  public void testKeepalive() throws IOException {
    // Arrange, Act and Assert
    assertFalse((new ParallelNioSender()).keepalive());
  }
}
