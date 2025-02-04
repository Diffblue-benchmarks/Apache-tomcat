package org.apache.catalina.tribes.transport.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.transport.DataSender;
import org.junit.Test;

public class PooledParallelSenderDiffblueTest {
  /**
   * Test {@link PooledParallelSender#sendMessage(Member[], ChannelMessage)}.
   * <p>
   * Method under test: {@link PooledParallelSender#sendMessage(Member[], ChannelMessage)}
   */
  @Test
  public void testSendMessage() throws ChannelException {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act and Assert
    assertThrows(ChannelException.class,
        () -> pooledParallelSender.sendMessage(new Member[]{new MemberImpl()}, new ChannelData()));
  }

  /**
   * Test {@link PooledParallelSender#getNewDataSender()}.
   * <p>
   * Method under test: {@link PooledParallelSender#getNewDataSender()}
   */
  @Test
  public void testGetNewDataSender() {
    // Arrange and Act
    DataSender actualNewDataSender = (new PooledParallelSender()).getNewDataSender();

    // Assert
    assertTrue(actualNewDataSender instanceof ParallelNioSender);
    assertNull(((ParallelNioSender) actualNewDataSender).getAddress());
    assertNull(((ParallelNioSender) actualNewDataSender).getDestination());
    assertEquals(-1, ((ParallelNioSender) actualNewDataSender).getKeepAliveCount());
    assertEquals(-1, ((ParallelNioSender) actualNewDataSender).getUdpPort());
    assertEquals(-1L, ((ParallelNioSender) actualNewDataSender).getKeepAliveTime());
    assertEquals(0, ((ParallelNioSender) actualNewDataSender).getAttempt());
    assertEquals(0, ((ParallelNioSender) actualNewDataSender).getPort());
    assertEquals(0, actualNewDataSender.getRequestCount());
    assertEquals(0L, actualNewDataSender.getConnectTime());
    assertEquals(1, ((ParallelNioSender) actualNewDataSender).getMaxRetryAttempts());
    assertEquals(25188, ((ParallelNioSender) actualNewDataSender).getRxBufSize());
    assertEquals(25188, ((ParallelNioSender) actualNewDataSender).getUdpRxBufSize());
    assertEquals(28, ((ParallelNioSender) actualNewDataSender).getSoTrafficClass());
    assertEquals(3, ((ParallelNioSender) actualNewDataSender).getSoLingerTime());
    assertEquals(3000L, ((ParallelNioSender) actualNewDataSender).getTimeout());
    assertEquals(5000L, ((ParallelNioSender) actualNewDataSender).selectTimeout);
    assertEquals(65536, ((ParallelNioSender) actualNewDataSender).getTxBufSize());
    assertEquals(65536, ((ParallelNioSender) actualNewDataSender).getUdpTxBufSize());
    assertFalse(((ParallelNioSender) actualNewDataSender).getDirectBuffer());
    assertFalse(((ParallelNioSender) actualNewDataSender).getSoKeepAlive());
    assertFalse(((ParallelNioSender) actualNewDataSender).getSoLingerOn());
    assertFalse(((ParallelNioSender) actualNewDataSender).isUdpBased());
    assertTrue(((ParallelNioSender) actualNewDataSender).getOoBInline());
    assertTrue(((ParallelNioSender) actualNewDataSender).getSoReuseAddress());
    assertTrue(((ParallelNioSender) actualNewDataSender).getTcpNoDelay());
    assertTrue(((ParallelNioSender) actualNewDataSender).getThrowOnFailedAck());
    assertTrue(actualNewDataSender.isConnected());
  }

  /**
   * Test new {@link PooledParallelSender} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PooledParallelSender}
   */
  @Test
  public void testNewPooledParallelSender() {
    // Arrange and Act
    PooledParallelSender actualPooledParallelSender = new PooledParallelSender();

    // Assert
    assertTrue(actualPooledParallelSender.getNewDataSender() instanceof ParallelNioSender);
    assertNull(actualPooledParallelSender.getAddress());
    assertNull(actualPooledParallelSender.getDestination());
    assertEquals(-1, actualPooledParallelSender.getKeepAliveCount());
    assertEquals(-1, actualPooledParallelSender.getUdpPort());
    assertEquals(-1L, actualPooledParallelSender.getKeepAliveTime());
    assertEquals(0, actualPooledParallelSender.getAttempt());
    assertEquals(0, actualPooledParallelSender.getPort());
    assertEquals(0, actualPooledParallelSender.getRequestCount());
    assertEquals(0, actualPooledParallelSender.getInPoolSize());
    assertEquals(0, actualPooledParallelSender.getInUsePoolSize());
    assertEquals(0L, actualPooledParallelSender.getConnectTime());
    assertEquals(1, actualPooledParallelSender.getMaxRetryAttempts());
    assertEquals(25, actualPooledParallelSender.getPoolSize());
    assertEquals(25188, actualPooledParallelSender.getRxBufSize());
    assertEquals(25188, actualPooledParallelSender.getUdpRxBufSize());
    assertEquals(28, actualPooledParallelSender.getSoTrafficClass());
    assertEquals(3, actualPooledParallelSender.getSoLingerTime());
    assertEquals(3000L, actualPooledParallelSender.getTimeout());
    assertEquals(3000L, actualPooledParallelSender.getMaxWait());
    assertEquals(65536, actualPooledParallelSender.getTxBufSize());
    assertEquals(65536, actualPooledParallelSender.getUdpTxBufSize());
    assertFalse(actualPooledParallelSender.getDirectBuffer());
    assertFalse(actualPooledParallelSender.getSoKeepAlive());
    assertFalse(actualPooledParallelSender.getSoLingerOn());
    assertFalse(actualPooledParallelSender.isConnected());
    assertFalse(actualPooledParallelSender.isUdpBased());
    assertTrue(actualPooledParallelSender.getOoBInline());
    assertTrue(actualPooledParallelSender.getSoReuseAddress());
    assertTrue(actualPooledParallelSender.getTcpNoDelay());
    assertTrue(actualPooledParallelSender.getThrowOnFailedAck());
  }
}
