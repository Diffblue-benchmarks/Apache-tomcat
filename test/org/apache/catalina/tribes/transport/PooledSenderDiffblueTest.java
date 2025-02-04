package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.catalina.tribes.transport.nio.NioSender;
import org.apache.catalina.tribes.transport.nio.ParallelNioSender;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;
import org.junit.Test;

public class PooledSenderDiffblueTest {
  /**
   * Test {@link PooledSender#getSender()}.
   * <p>
   * Method under test: {@link PooledSender#getSender()}
   */
  @Test
  public void testGetSender() {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act
    DataSender actualSender = pooledParallelSender.getSender();

    // Assert
    assertTrue(actualSender instanceof ParallelNioSender);
    assertNull(((ParallelNioSender) actualSender).getAddress());
    assertNull(((ParallelNioSender) actualSender).getDestination());
    assertEquals(-1, ((ParallelNioSender) actualSender).getKeepAliveCount());
    assertEquals(-1, ((ParallelNioSender) actualSender).getUdpPort());
    assertEquals(-1L, ((ParallelNioSender) actualSender).getKeepAliveTime());
    assertEquals(0, ((ParallelNioSender) actualSender).getAttempt());
    assertEquals(0, ((ParallelNioSender) actualSender).getPort());
    assertEquals(0, actualSender.getRequestCount());
    assertEquals(0L, actualSender.getConnectTime());
    assertEquals(1, ((ParallelNioSender) actualSender).getMaxRetryAttempts());
    assertEquals(1, pooledParallelSender.getInUsePoolSize());
    assertEquals(28, ((ParallelNioSender) actualSender).getSoTrafficClass());
    assertEquals(3, ((ParallelNioSender) actualSender).getSoLingerTime());
    assertEquals(3000L, ((ParallelNioSender) actualSender).getTimeout());
    assertFalse(((ParallelNioSender) actualSender).getDirectBuffer());
    assertFalse(((ParallelNioSender) actualSender).getSoKeepAlive());
    assertFalse(((ParallelNioSender) actualSender).getSoLingerOn());
    assertFalse(((ParallelNioSender) actualSender).isUdpBased());
    assertTrue(((ParallelNioSender) actualSender).getOoBInline());
    assertTrue(((ParallelNioSender) actualSender).getSoReuseAddress());
    assertTrue(((ParallelNioSender) actualSender).getTcpNoDelay());
    assertTrue(((ParallelNioSender) actualSender).getThrowOnFailedAck());
    assertTrue(actualSender.isConnected());
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, ((ParallelNioSender) actualSender).getRxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_ACK_BUFFER_SIZE, ((ParallelNioSender) actualSender).getUdpRxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, ((ParallelNioSender) actualSender).getTxBufSize());
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, ((ParallelNioSender) actualSender).getUdpTxBufSize());
  }

  /**
   * Test {@link PooledSender#returnSender(DataSender)}.
   * <ul>
   *   <li>Then {@link PooledParallelSender} (default constructor) InPoolSize is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link PooledSender#returnSender(DataSender)}
   */
  @Test
  public void testReturnSender_thenPooledParallelSenderInPoolSizeIsZero() {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();
    PooledParallelSender sender = new PooledParallelSender();

    // Act
    pooledParallelSender.returnSender(sender);

    // Assert that nothing has changed
    assertEquals(0, sender.getInPoolSize());
  }

  /**
   * Test {@link PooledSender#returnSender(DataSender)}.
   * <ul>
   *   <li>When {@link NioSender} (default constructor).</li>
   *   <li>Then {@link PooledParallelSender} (default constructor) InPoolSize is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PooledSender#returnSender(DataSender)}
   */
  @Test
  public void testReturnSender_whenNioSender_thenPooledParallelSenderInPoolSizeIsOne() {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act
    pooledParallelSender.returnSender(new NioSender());

    // Assert
    assertEquals(1, pooledParallelSender.getInPoolSize());
  }

  /**
   * Test {@link PooledSender#connect()}.
   * <p>
   * Method under test: {@link PooledSender#connect()}
   */
  @Test
  public void testConnect() throws IOException {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act
    pooledParallelSender.connect();

    // Assert
    assertTrue(pooledParallelSender.isConnected());
  }

  /**
   * Test {@link PooledSender#getInPoolSize()}.
   * <p>
   * Method under test: {@link PooledSender#getInPoolSize()}
   */
  @Test
  public void testGetInPoolSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new PooledParallelSender()).getInPoolSize());
  }

  /**
   * Test {@link PooledSender#getInUsePoolSize()}.
   * <p>
   * Method under test: {@link PooledSender#getInUsePoolSize()}
   */
  @Test
  public void testGetInUsePoolSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new PooledParallelSender()).getInUsePoolSize());
  }

  /**
   * Test {@link PooledSender#setPoolSize(int)}.
   * <p>
   * Method under test: {@link PooledSender#setPoolSize(int)}
   */
  @Test
  public void testSetPoolSize() {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act
    pooledParallelSender.setPoolSize(3);

    // Assert
    assertEquals(3, pooledParallelSender.getPoolSize());
  }

  /**
   * Test {@link PooledSender#getPoolSize()}.
   * <p>
   * Method under test: {@link PooledSender#getPoolSize()}
   */
  @Test
  public void testGetPoolSize() {
    // Arrange, Act and Assert
    assertEquals(25, (new PooledParallelSender()).getPoolSize());
  }

  /**
   * Test {@link PooledSender#getMaxWait()}.
   * <p>
   * Method under test: {@link PooledSender#getMaxWait()}
   */
  @Test
  public void testGetMaxWait() {
    // Arrange, Act and Assert
    assertEquals(3000L, (new PooledParallelSender()).getMaxWait());
  }

  /**
   * Test {@link PooledSender#setMaxWait(long)}.
   * <p>
   * Method under test: {@link PooledSender#setMaxWait(long)}
   */
  @Test
  public void testSetMaxWait() {
    // Arrange
    PooledParallelSender pooledParallelSender = new PooledParallelSender();

    // Act
    pooledParallelSender.setMaxWait(1L);

    // Assert
    assertEquals(1L, pooledParallelSender.getMaxWait());
  }

  /**
   * Test {@link PooledSender#keepalive()}.
   * <p>
   * Method under test: {@link PooledSender#keepalive()}
   */
  @Test
  public void testKeepalive() {
    // Arrange, Act and Assert
    assertFalse((new PooledParallelSender()).keepalive());
  }
}
