package org.apache.catalina.tribes.tipis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.ha.authenticator.ClusterSingleSignOn;
import org.apache.catalina.ha.deploy.FarmWarDeployer;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapOwner;
import org.junit.Test;

public class LazyReplicatedMapDiffblueTest {
  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenFarmWarDeployer() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addChannelListener(new FarmWarDeployer());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, 10.0f, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenFarmWarDeployer2() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addChannelListener(new FarmWarDeployer());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenFarmWarDeployer3() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addChannelListener(new FarmWarDeployer());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}.
   * <ul>
   *   <li>Given {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}
   */
  @Test
  public void testNewLazyReplicatedMap_givenFarmWarDeployer4() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addChannelListener(new FarmWarDeployer());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()}, true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenSimpleTcpCluster() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addMembershipListener(new SimpleTcpCluster());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, 10.0f, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenSimpleTcpCluster2() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addMembershipListener(new SimpleTcpCluster());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_givenSimpleTcpCluster3() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addMembershipListener(new SimpleTcpCluster());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}
   */
  @Test
  public void testNewLazyReplicatedMap_givenSimpleTcpCluster4() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();

    GroupChannel channel = new GroupChannel();
    channel.addMembershipListener(new SimpleTcpCluster());

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()}, true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, float, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_whenGroupChannel_thenReturnEmpty() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();
    GroupChannel channel = new GroupChannel();

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, 10.0f, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, int, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_whenGroupChannel_thenReturnEmpty2() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();
    GroupChannel channel = new GroupChannel();

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", 1, new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[])}
   */
  @Test
  public void testNewLazyReplicatedMap_whenGroupChannel_thenReturnEmpty3() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();
    GroupChannel channel = new GroupChannel();

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()});

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyReplicatedMap#LazyReplicatedMap(MapOwner, Channel, long, String, ClassLoader[], boolean)}
   */
  @Test
  public void testNewLazyReplicatedMap_whenGroupChannel_thenReturnEmpty4() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();
    GroupChannel channel = new GroupChannel();

    // Act
    LazyReplicatedMap<Object, Object> actualObjectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L,
        "Map Context Name", new ClassLoader[]{new ParallelWebappClassLoader()}, true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LazyReplicatedMap#getReplicateMessageType()}
   *   <li>{@link LazyReplicatedMap#getStateMessageType()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ClusterSingleSignOn owner = new ClusterSingleSignOn();
    GroupChannel channel = new GroupChannel();
    LazyReplicatedMap<Object, Object> objectObjectMap = new LazyReplicatedMap<>(owner, channel, 10L, "Map Context Name",
        new ClassLoader[]{new ParallelWebappClassLoader()});

    // Act
    int actualReplicateMessageType = objectObjectMap.getReplicateMessageType();

    // Assert
    assertEquals(1, actualReplicateMessageType);
    assertEquals(5, objectObjectMap.getStateMessageType());
  }
}
