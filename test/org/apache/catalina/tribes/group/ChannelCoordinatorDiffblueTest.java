package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.ChannelReceiver;
import org.apache.catalina.tribes.ChannelSender;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipService;
import org.apache.catalina.tribes.group.interceptors.DomainFilterInterceptor;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMember;
import org.apache.catalina.tribes.membership.StaticMembershipService;
import org.apache.catalina.tribes.transport.MultiPointSender;
import org.apache.catalina.tribes.transport.ReplicationTransmitter;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;
import org.junit.Test;

public class ChannelCoordinatorDiffblueTest {
  /**
   * Test {@link ChannelCoordinator#ChannelCoordinator()}.
   * <p>
   * Method under test: {@link ChannelCoordinator#ChannelCoordinator()}
   */
  @Test
  public void testNewChannelCoordinator() {
    // Arrange and Act
    ChannelCoordinator actualChannelCoordinator = new ChannelCoordinator();

    // Assert
    assertTrue(actualChannelCoordinator.getMembershipService() instanceof McastService);
    assertTrue(actualChannelCoordinator.getClusterSender() instanceof ReplicationTransmitter);
    assertTrue(actualChannelCoordinator.getClusterReceiver() instanceof NioReceiver);
    assertNull(actualChannelCoordinator.getChannel());
    assertNull(actualChannelCoordinator.getNext());
    assertNull(actualChannelCoordinator.getPrevious());
    assertEquals(0, actualChannelCoordinator.getMembers().length);
    assertEquals(7, actualChannelCoordinator.getOptionFlag());
    assertFalse(actualChannelCoordinator.hasMembers());
  }

  /**
   * Test {@link ChannelCoordinator#ChannelCoordinator(ChannelReceiver, ChannelSender, MembershipService)}.
   * <ul>
   *   <li>Then MembershipService return {@link McastService}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#ChannelCoordinator(ChannelReceiver, ChannelSender, MembershipService)}
   */
  @Test
  public void testNewChannelCoordinator_thenMembershipServiceReturnMcastService() {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    // Act
    ChannelCoordinator actualChannelCoordinator = new ChannelCoordinator(receiver, sender, new McastService());

    // Assert
    assertTrue(actualChannelCoordinator.getMembershipService() instanceof McastService);
    ChannelReceiver clusterReceiver = actualChannelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    assertSame(receiver, clusterReceiver);
  }

  /**
   * Test {@link ChannelCoordinator#ChannelCoordinator(ChannelReceiver, ChannelSender, MembershipService)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then ClusterSender return {@link ReplicationTransmitter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#ChannelCoordinator(ChannelReceiver, ChannelSender, MembershipService)}
   */
  @Test
  public void testNewChannelCoordinator_whenNull_thenClusterSenderReturnReplicationTransmitter() {
    // Arrange
    ReplicationTransmitter sender = new ReplicationTransmitter();
    McastService service = new McastService();

    // Act
    ChannelCoordinator actualChannelCoordinator = new ChannelCoordinator(null, sender, service);

    // Assert
    ChannelSender clusterSender = actualChannelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    assertNull(actualChannelCoordinator.getChannel());
    assertNull(actualChannelCoordinator.getNext());
    assertNull(actualChannelCoordinator.getPrevious());
    assertNull(actualChannelCoordinator.getClusterReceiver());
    assertEquals(0, actualChannelCoordinator.getMembers().length);
    assertEquals(7, actualChannelCoordinator.getOptionFlag());
    assertFalse(actualChannelCoordinator.hasMembers());
    assertSame(service, actualChannelCoordinator.getMembershipService());
    assertSame(sender, clusterSender);
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()} Channel is {@code null}.</li>
   *   <li>When eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_givenChannelCoordinatorChannelIsNull_whenEight() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(null);

    // Act and Assert
    assertThrows(ChannelException.class, () -> channelCoordinator.start(8));
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()} Channel is {@code null}.</li>
   *   <li>When four.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_givenChannelCoordinatorChannelIsNull_whenFour() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(null);

    // Act and Assert
    assertThrows(ChannelException.class, () -> channelCoordinator.start(4));
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Channel is {@link GroupChannel} (default constructor) {@link GroupChannel#interceptors}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_thenChannelCoordinatorClusterSenderChannelIsGroupChannelInterceptors() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    GroupChannel channel = new GroupChannel();
    channelCoordinator.setChannel(channel);

    // Act
    channelCoordinator.start(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
    ChannelInterceptor expectedChannel = channel.interceptors;
    assertSame(expectedChannel, clusterSender.getChannel());
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Channel is {@link GroupChannel} (default constructor) {@link GroupChannel#interceptors}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_thenChannelCoordinatorClusterSenderChannelIsGroupChannelInterceptors2()
      throws ChannelException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("auto");

    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(channel);

    // Act
    channelCoordinator.start(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
    ChannelInterceptor expectedChannel = channel.interceptors;
    assertSame(expectedChannel, clusterSender.getChannel());
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Channel is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_thenChannelCoordinatorClusterSenderChannelIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(null);

    // Act
    channelCoordinator.start(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(clusterSender.getChannel());
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#start(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then not {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Transport Connected.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#start(int)}
   */
  @Test
  public void testStart_whenZero_thenNotChannelCoordinatorClusterSenderTransportConnected() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(new GroupChannel());

    // Act
    channelCoordinator.start(0);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop() throws ChannelException {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    ChannelCoordinator channelCoordinator = new ChannelCoordinator(receiver, sender, new StaticMembershipService());
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_givenChannelCoordinator() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act
    channelCoordinator.stop(1);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertFalse(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Executor is {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_givenNioReceiverExecutorIsStandardThreadExecutor() throws ChannelException {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    receiver.setExecutor(new StandardThreadExecutor());
    ReplicationTransmitter sender = new ReplicationTransmitter();

    ChannelCoordinator channelCoordinator = new ChannelCoordinator(receiver, sender, new StaticMembershipService());
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>When fifteen.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver MessageListener is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_whenFifteen_thenChannelCoordinatorClusterReceiverMessageListenerIsNull()
      throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver MessageListener is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_whenOne_thenChannelCoordinatorClusterReceiverMessageListenerIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(1);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver Executor is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_whenTwo_thenChannelCoordinatorClusterReceiverExecutorIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertFalse(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#stop(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Transport Connected.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#stop(int)}
   */
  @Test
  public void testStop_whenZero_thenChannelCoordinatorClusterSenderTransportConnected() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.stop(0);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#internalStart(int)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStart(int)}
   */
  @Test
  public void testInternalStart() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    GroupChannel channel = new GroupChannel();
    channelCoordinator.setChannel(channel);

    // Act
    channelCoordinator.internalStart(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
    ChannelInterceptor expectedChannel = channel.interceptors;
    assertSame(expectedChannel, clusterSender.getChannel());
  }

  /**
   * Test {@link ChannelCoordinator#internalStart(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()} Channel is {@code null}.</li>
   *   <li>When four.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStart(int)}
   */
  @Test
  public void testInternalStart_givenChannelCoordinatorChannelIsNull_whenFour() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(null);

    // Act and Assert
    assertThrows(ChannelException.class, () -> channelCoordinator.internalStart(4));
  }

  /**
   * Test {@link ChannelCoordinator#internalStart(int)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code auto}.</li>
   *   <li>When fifty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStart(int)}
   */
  @Test
  public void testInternalStart_givenGroupChannelNameIsAuto_whenFiftyEight() throws ChannelException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("auto");

    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(channel);

    // Act and Assert
    assertThrows(ChannelException.class, () -> channelCoordinator.internalStart(58));
  }

  /**
   * Test {@link ChannelCoordinator#internalStart(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Channel is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStart(int)}
   */
  @Test
  public void testInternalStart_thenChannelCoordinatorClusterSenderChannelIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(null);

    // Act
    channelCoordinator.internalStart(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(clusterSender.getChannel());
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStart(int)}.
   * <ul>
   *   <li>Then not {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Transport Connected.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStart(int)}
   */
  @Test
  public void testInternalStart_thenNotChannelCoordinatorClusterSenderTransportConnected() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setChannel(new GroupChannel());

    // Act
    channelCoordinator.internalStart(0);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop() throws ChannelException {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    ChannelCoordinator channelCoordinator = new ChannelCoordinator(receiver, sender, new StaticMembershipService());
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_givenChannelCoordinator() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act
    channelCoordinator.internalStop(1);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertFalse(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Executor is {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_givenNioReceiverExecutorIsStandardThreadExecutor() throws ChannelException {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    receiver.setExecutor(new StandardThreadExecutor());
    ReplicationTransmitter sender = new ReplicationTransmitter();

    ChannelCoordinator channelCoordinator = new ChannelCoordinator(receiver, sender, new StaticMembershipService());
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver MessageListener is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_thenChannelCoordinatorClusterReceiverMessageListenerIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(1);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver MessageListener is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_thenChannelCoordinatorClusterReceiverMessageListenerIsNull2() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(15);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver Executor is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_whenTwo_thenChannelCoordinatorClusterReceiverExecutorIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(2);

    // Assert
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertNull(((NioReceiver) clusterReceiver).getExecutor());
    assertFalse(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#internalStop(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterSender Transport Connected.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#internalStop(int)}
   */
  @Test
  public void testInternalStop_whenZero_thenChannelCoordinatorClusterSenderTransportConnected()
      throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.start(2);

    // Act
    channelCoordinator.internalStop(0);

    // Assert that nothing has changed
    ChannelSender clusterSender = channelCoordinator.getClusterSender();
    assertTrue(clusterSender instanceof ReplicationTransmitter);
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    MultiPointSender transport = ((ReplicationTransmitter) clusterSender).getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(channelCoordinator, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelCoordinator#memberAdded(Member)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} Previous {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenChannelCoordinatorPreviousDomainFilterInterceptor() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setPrevious(new DomainFilterInterceptor());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    channelCoordinator.memberAdded(member);

    // Assert
    ChannelInterceptor previous = channelCoordinator.getPrevious();
    assertTrue(previous instanceof DomainFilterInterceptor);
    Member[] members = previous.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous.hasMembers());
    assertSame(member, members[0]);
  }

  /**
   * Test {@link ChannelCoordinator#accept(ChannelMessage)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#accept(ChannelMessage)}
   */
  @Test
  public void testAccept() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act and Assert
    assertTrue(channelCoordinator.accept(new ChannelData()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ChannelCoordinator#setClusterSender(ChannelSender)}
   *   <li>{@link ChannelCoordinator#getClusterReceiver()}
   *   <li>{@link ChannelCoordinator#getClusterSender()}
   *   <li>{@link ChannelCoordinator#getMembershipService()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    ReplicationTransmitter clusterSender = new ReplicationTransmitter();

    // Act
    channelCoordinator.setClusterSender(clusterSender);
    ChannelReceiver actualClusterReceiver = channelCoordinator.getClusterReceiver();
    ChannelSender actualClusterSender = channelCoordinator.getClusterSender();

    // Assert
    assertTrue(channelCoordinator.getMembershipService() instanceof McastService);
    assertTrue(actualClusterReceiver instanceof NioReceiver);
    assertSame(clusterSender, actualClusterSender);
  }

  /**
   * Test {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}
   */
  @Test
  public void testSetClusterReceiver_givenChannelCoordinatorClusterReceiverIsNull() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setClusterReceiver(null);

    // Act
    channelCoordinator.setClusterReceiver(null);

    // Assert that nothing has changed
    assertNull(channelCoordinator.getClusterReceiver());
  }

  /**
   * Test {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}
   */
  @Test
  public void testSetClusterReceiver_thenChannelCoordinatorClusterReceiverIsNull() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act
    channelCoordinator.setClusterReceiver(null);

    // Assert
    assertNull(channelCoordinator.getClusterReceiver());
  }

  /**
   * Test {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}.
   * <ul>
   *   <li>Then {@link NioReceiver} (default constructor) Listener is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#setClusterReceiver(ChannelReceiver)}
   */
  @Test
  public void testSetClusterReceiver_thenNioReceiverListenerIsChannelCoordinator() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    NioReceiver clusterReceiver = new NioReceiver();

    // Act
    channelCoordinator.setClusterReceiver(clusterReceiver);

    // Assert
    assertSame(channelCoordinator, clusterReceiver.getListener());
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    assertSame(clusterReceiver, channelCoordinator.getClusterReceiver());
  }

  /**
   * Test {@link ChannelCoordinator#setMembershipService(MembershipService)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} MembershipService is {@link McastService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#setMembershipService(MembershipService)}
   */
  @Test
  public void testSetMembershipService_thenChannelCoordinatorMembershipServiceIsMcastService() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    McastService membershipService = new McastService();

    // Act
    channelCoordinator.setMembershipService(membershipService);

    // Assert
    assertSame(membershipService, channelCoordinator.getMembershipService());
  }

  /**
   * Test {@link ChannelCoordinator#hasMembers()}.
   * <p>
   * Method under test: {@link ChannelCoordinator#hasMembers()}
   */
  @Test
  public void testHasMembers() {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    // Act and Assert
    assertFalse((new ChannelCoordinator(receiver, sender, new StaticMembershipService())).hasMembers());
  }

  /**
   * Test {@link ChannelCoordinator#hasMembers()}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#hasMembers()}
   */
  @Test
  public void testHasMembers_givenChannelCoordinator() {
    // Arrange, Act and Assert
    assertFalse((new ChannelCoordinator()).hasMembers());
  }

  /**
   * Test {@link ChannelCoordinator#getMembers()}.
   * <p>
   * Method under test: {@link ChannelCoordinator#getMembers()}
   */
  @Test
  public void testGetMembers() {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    // Act and Assert
    assertEquals(0, (new ChannelCoordinator(receiver, sender, new StaticMembershipService())).getMembers().length);
  }

  /**
   * Test {@link ChannelCoordinator#getMembers()}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#getMembers()}
   */
  @Test
  public void testGetMembers_givenChannelCoordinator() {
    // Arrange, Act and Assert
    assertEquals(0, (new ChannelCoordinator()).getMembers().length);
  }

  /**
   * Test {@link ChannelCoordinator#getMember(Member)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#getMember(Member)}
   */
  @Test
  public void testGetMember() {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();
    ChannelCoordinator channelCoordinator = new ChannelCoordinator(receiver, sender, new StaticMembershipService());

    // Act and Assert
    assertNull(channelCoordinator.getMember(new MemberImpl()));
  }

  /**
   * Test {@link ChannelCoordinator#getMember(Member)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#getMember(Member)}
   */
  @Test
  public void testGetMember_givenChannelCoordinator() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act and Assert
    assertNull(channelCoordinator.getMember(new MemberImpl()));
  }

  /**
   * Test {@link ChannelCoordinator#getLocalMember(boolean)}.
   * <p>
   * Method under test: {@link ChannelCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember() {
    // Arrange
    NioReceiver receiver = new NioReceiver();
    ReplicationTransmitter sender = new ReplicationTransmitter();

    // Act and Assert
    assertNull((new ChannelCoordinator(receiver, sender, new StaticMembershipService())).getLocalMember(true));
  }

  /**
   * Test {@link ChannelCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenChannelCoordinator_whenFalse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ChannelCoordinator()).getLocalMember(false));
  }

  /**
   * Test {@link ChannelCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenChannelCoordinator_whenTrue_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ChannelCoordinator()).getLocalMember(true));
  }

  /**
   * Test {@link ChannelCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Then return {@link StaticMember#StaticMember()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_thenReturnStaticMember() {
    // Arrange
    StaticMembershipService service = new StaticMembershipService();
    StaticMember member = new StaticMember();
    service.setLocalMember(member);
    NioReceiver receiver = new NioReceiver();

    // Act and Assert
    assertSame(member, (new ChannelCoordinator(receiver, new ReplicationTransmitter(), service)).getLocalMember(true));
  }
}
