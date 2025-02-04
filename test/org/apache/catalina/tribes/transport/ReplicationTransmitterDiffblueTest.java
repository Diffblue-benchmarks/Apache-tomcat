package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.transport.nio.ParallelNioSender;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;
import org.junit.Test;

public class ReplicationTransmitterDiffblueTest {
  /**
   * Test new {@link ReplicationTransmitter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ReplicationTransmitter}
   */
  @Test
  public void testNewReplicationTransmitter() {
    // Arrange and Act
    ReplicationTransmitter actualReplicationTransmitter = new ReplicationTransmitter();

    // Assert
    assertTrue(actualReplicationTransmitter.getTransport() instanceof PooledParallelSender);
    assertNull(actualReplicationTransmitter.getChannel());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) JmxDomain is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelJmxDomainIsFoo() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain("foo");
    channel.setName("foo");
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) JmxDomain is {@code jmxRegistry.no.domain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelJmxDomainIsJmxRegistryNoDomain() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain("jmxRegistry.no.domain");
    channel.setName(",component=Sender");
    channel.setJmxEnabled(true);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code ,component=Channel}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsComponentChannel() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName(",component=Channel");
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code ,component=Sender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsComponentSender() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName(",component=Sender");
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code ,component=Sender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsComponentSender2() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName(",component=Sender");
    channel.setJmxEnabled(true);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsFoo() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName("foo");
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsNull() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName(null);
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code type=Channel,channel=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenGroupChannelNameIsTypeChannelChannel() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain("jmxRegistry.no.domain");
    channel.setName("type=Channel,channel=");
    channel.setJmxEnabled(true);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Given {@link ReplicationTransmitter} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_givenReplicationTransmitter() throws IOException {
    // Arrange
    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();

    // Act
    replicationTransmitter.start();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Then {@link ReplicationTransmitter} (default constructor) Transport {@link ParallelNioSender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_thenReplicationTransmitterTransportParallelNioSender() throws IOException {
    // Arrange
    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setTransport(new ParallelNioSender());

    // Act
    replicationTransmitter.start();

    // Assert that nothing has changed
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof ParallelNioSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#start()}.
   * <ul>
   *   <li>Then {@link ReplicationTransmitter} (default constructor) Transport {@link ParallelNioSender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#start()}
   */
  @Test
  public void testStart_thenReplicationTransmitterTransportParallelNioSender2() throws IOException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setJmxDomain(null);
    channel.setName("foo");
    channel.setJmxEnabled(false);

    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setTransport(new ParallelNioSender());
    replicationTransmitter.setChannel(channel);

    // Act
    replicationTransmitter.start();

    // Assert that nothing has changed
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof ParallelNioSender);
    assertTrue(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#stop()}.
   * <ul>
   *   <li>Then {@link ReplicationTransmitter} (default constructor) Transport {@link ParallelNioSender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#stop()}
   */
  @Test
  public void testStop_thenReplicationTransmitterTransportParallelNioSender() throws IOException {
    // Arrange
    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    replicationTransmitter.setTransport(new ParallelNioSender());

    // Act
    replicationTransmitter.stop();

    // Assert
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof ParallelNioSender);
    assertFalse(transport.isConnected());
  }

  /**
   * Test {@link ReplicationTransmitter#stop()}.
   * <ul>
   *   <li>Then {@link ReplicationTransmitter} (default constructor) Transport {@link PooledParallelSender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationTransmitter#stop()}
   */
  @Test
  public void testStop_thenReplicationTransmitterTransportPooledParallelSender() {
    // Arrange
    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();

    // Act
    replicationTransmitter.stop();

    // Assert that nothing has changed
    MultiPointSender transport = replicationTransmitter.getTransport();
    assertTrue(transport instanceof PooledParallelSender);
    assertFalse(transport.isConnected());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplicationTransmitter#setChannel(Channel)}
   *   <li>{@link ReplicationTransmitter#setTransport(MultiPointSender)}
   *   <li>{@link ReplicationTransmitter#getChannel()}
   *   <li>{@link ReplicationTransmitter#getTransport()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws IOException {
    // Arrange
    ReplicationTransmitter replicationTransmitter = new ReplicationTransmitter();
    GroupChannel channel = new GroupChannel();

    // Act
    replicationTransmitter.setChannel(channel);
    ParallelNioSender transport = new ParallelNioSender();
    replicationTransmitter.setTransport(transport);
    Channel actualChannel = replicationTransmitter.getChannel();

    // Assert
    assertSame(channel, actualChannel);
    assertSame(transport, replicationTransmitter.getTransport());
  }
}
