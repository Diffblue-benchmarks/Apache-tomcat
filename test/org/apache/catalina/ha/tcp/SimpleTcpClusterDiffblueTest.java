package org.apache.catalina.ha.tcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Manager;
import org.apache.catalina.Valve;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.ClusterDeployer;
import org.apache.catalina.ha.ClusterListener;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.authenticator.ClusterSingleSignOn;
import org.apache.catalina.ha.deploy.FarmWarDeployer;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.session.ClusterSessionListener;
import org.apache.catalina.ha.session.DeltaManager;
import org.apache.catalina.ha.session.JvmRouteBinderValve;
import org.apache.catalina.ha.session.SessionMessageImpl;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.tomcat.unittest.TesterContext;
import org.junit.Test;

public class SimpleTcpClusterDiffblueTest {
  /**
   * Test new {@link SimpleTcpCluster} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SimpleTcpCluster}
   */
  @Test
  public void testNewSimpleTcpCluster() {
    // Arrange and Act
    SimpleTcpCluster actualSimpleTcpCluster = new SimpleTcpCluster();

    // Assert
    assertTrue(actualSimpleTcpCluster.getManagerTemplate() instanceof DeltaManager);
    assertTrue(actualSimpleTcpCluster.getChannel() instanceof GroupChannel);
    assertEquals("Catalina", actualSimpleTcpCluster.getDomain());
    assertEquals("NEW", actualSimpleTcpCluster.getStateName());
    assertEquals("async", actualSimpleTcpCluster.getChannelSendOptionsName());
    assertEquals("type=Cluster", actualSimpleTcpCluster.getObjectNameKeyProperties());
    assertNull(actualSimpleTcpCluster.getClusterName());
    assertNull(actualSimpleTcpCluster.getDomainInternal());
    assertNull(actualSimpleTcpCluster.clusterName);
    assertNull(actualSimpleTcpCluster.getObjectName());
    assertNull(actualSimpleTcpCluster.getContainer());
    assertNull(actualSimpleTcpCluster.getClusterDeployer());
    assertNull(actualSimpleTcpCluster.getLocalMember());
    assertEquals(0, actualSimpleTcpCluster.findClusterListeners().length);
    assertEquals(0, actualSimpleTcpCluster.getMembers().length);
    assertEquals(0, actualSimpleTcpCluster.getValves().length);
    assertEquals(0, actualSimpleTcpCluster.findLifecycleListeners().length);
    assertEquals(15, actualSimpleTcpCluster.getChannelStartOptions());
    assertEquals(8, actualSimpleTcpCluster.getChannelSendOptions());
    assertEquals(LifecycleState.NEW, actualSimpleTcpCluster.getState());
    assertFalse(actualSimpleTcpCluster.hasMembers());
    assertFalse(actualSimpleTcpCluster.isHeartbeatBackgroundEnabled());
    assertFalse(actualSimpleTcpCluster.isNotifyLifecycleListenerOnFailure());
    assertTrue(actualSimpleTcpCluster.clusterListeners.isEmpty());
    assertTrue(actualSimpleTcpCluster.getManagers().isEmpty());
    assertTrue(actualSimpleTcpCluster.getThrowOnFailure());
  }

  /**
   * Test {@link SimpleTcpCluster#getClusterName()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterName is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getClusterName()}
   */
  @Test
  public void testGetClusterName_givenSimpleTcpClusterClusterNameIsFoo_thenReturnFoo() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterName("foo");
    simpleTcpCluster.setContainer(null);

    // Act and Assert
    assertEquals("foo", simpleTcpCluster.getClusterName());
  }

  /**
   * Test {@link SimpleTcpCluster#getClusterName()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterName is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getClusterName()}
   */
  @Test
  public void testGetClusterName_givenSimpleTcpClusterClusterNameIsNull_thenReturnNull() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterName(null);
    simpleTcpCluster.setContainer(new StandardContext());

    // Act and Assert
    assertNull(simpleTcpCluster.getClusterName());
  }

  /**
   * Test {@link SimpleTcpCluster#getClusterName()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getClusterName()}
   */
  @Test
  public void testGetClusterName_givenSimpleTcpCluster_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SimpleTcpCluster()).getClusterName());
  }

  /**
   * Test {@link SimpleTcpCluster#setContainer(Container)}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#setContainer(Container)}
   */
  @Test
  public void testSetContainer() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    StandardContext container = new StandardContext();

    // Act
    simpleTcpCluster.setContainer(container);

    // Assert
    assertEquals("Catalina", simpleTcpCluster.getDomainInternal());
    assertSame(container, simpleTcpCluster.getContainer());
  }

  /**
   * Test {@link SimpleTcpCluster#setNotifyLifecycleListenerOnFailure(boolean)}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#setNotifyLifecycleListenerOnFailure(boolean)}
   */
  @Test
  public void testSetNotifyLifecycleListenerOnFailure() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.setNotifyLifecycleListenerOnFailure(true);

    // Assert
    assertTrue(simpleTcpCluster.isNotifyLifecycleListenerOnFailure());
  }

  /**
   * Test {@link SimpleTcpCluster#addValve(Valve)}.
   * <ul>
   *   <li>When {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#addValve(Valve)}
   */
  @Test
  public void testAddValve_whenBasicAuthenticator_thenArrayLengthIsZero() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Assert that nothing has changed
    assertEquals(0, simpleTcpCluster.getValves().length);
  }

  /**
   * Test {@link SimpleTcpCluster#addValve(Valve)}.
   * <ul>
   *   <li>When {@link ReplicationValve} (default constructor).</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#addValve(Valve)}
   */
  @Test
  public void testAddValve_whenReplicationValve_thenArrayLengthIsOne() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    ReplicationValve valve = new ReplicationValve();

    // Act
    simpleTcpCluster.addValve(valve);

    // Assert
    Valve[] valves = simpleTcpCluster.getValves();
    assertEquals(1, valves.length);
    assertSame(valve, valves[0]);
  }

  /**
   * Test {@link SimpleTcpCluster#getValves()}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#getValves()}
   */
  @Test
  public void testGetValves() {
    // Arrange, Act and Assert
    assertEquals(0, (new SimpleTcpCluster()).getValves().length);
  }

  /**
   * Test {@link SimpleTcpCluster#findClusterListeners()}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#findClusterListeners()}
   */
  @Test
  public void testFindClusterListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new SimpleTcpCluster()).findClusterListeners().length);
  }

  /**
   * Test {@link SimpleTcpCluster#addClusterListener(ClusterListener)}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) Cluster {@link SimpleTcpCluster}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#addClusterListener(ClusterListener)}
   */
  @Test
  public void testAddClusterListener_thenFarmWarDeployerClusterSimpleTcpCluster() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    FarmWarDeployer listener = new FarmWarDeployer();

    // Act
    simpleTcpCluster.addClusterListener(listener);

    // Assert
    CatalinaCluster cluster = listener.getCluster();
    assertTrue(cluster instanceof SimpleTcpCluster);
    assertEquals(1, simpleTcpCluster.clusterListeners.size());
    assertEquals(1, simpleTcpCluster.findClusterListeners().length);
    assertSame(simpleTcpCluster, cluster);
  }

  /**
   * Test {@link SimpleTcpCluster#addClusterListener(ClusterListener)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#addClusterListener(ClusterListener)}
   */
  @Test
  public void testAddClusterListener_whenNull_thenArrayLengthIsZero() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.addClusterListener(null);

    // Assert that nothing has changed
    assertEquals(0, simpleTcpCluster.findClusterListeners().length);
    assertTrue(simpleTcpCluster.clusterListeners.isEmpty());
  }

  /**
   * Test {@link SimpleTcpCluster#setChannelSendOptions(String)} with {@code String}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#setChannelSendOptions(String)}
   */
  @Test
  public void testSetChannelSendOptionsWithString() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.setChannelSendOptions("42");

    // Assert
    assertEquals("udp, async, use_ack", simpleTcpCluster.getChannelSendOptionsName());
    assertEquals(42, simpleTcpCluster.getChannelSendOptions());
  }

  /**
   * Test {@link SimpleTcpCluster#getMembers()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getMembers()}
   */
  @Test
  public void testGetMembers_givenSimpleTcpCluster_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new SimpleTcpCluster()).getMembers().length);
  }

  /**
   * Test {@link SimpleTcpCluster#getLocalMember()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getLocalMember()}
   */
  @Test
  public void testGetLocalMember_givenSimpleTcpCluster_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SimpleTcpCluster()).getLocalMember());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SimpleTcpCluster#setChannel(Channel)}
   *   <li>{@link SimpleTcpCluster#setChannelSendOptions(int)}
   *   <li>{@link SimpleTcpCluster#setChannelStartOptions(int)}
   *   <li>{@link SimpleTcpCluster#setClusterDeployer(ClusterDeployer)}
   *   <li>{@link SimpleTcpCluster#setClusterName(String)}
   *   <li>{@link SimpleTcpCluster#setHeartbeatBackgroundEnabled(boolean)}
   *   <li>{@link SimpleTcpCluster#setManagerTemplate(ClusterManager)}
   *   <li>{@link SimpleTcpCluster#toString()}
   *   <li>{@link SimpleTcpCluster#getChannel()}
   *   <li>{@link SimpleTcpCluster#getChannelSendOptions()}
   *   <li>{@link SimpleTcpCluster#getChannelStartOptions()}
   *   <li>{@link SimpleTcpCluster#getClusterDeployer()}
   *   <li>{@link SimpleTcpCluster#getContainer()}
   *   <li>{@link SimpleTcpCluster#getManagerTemplate()}
   *   <li>{@link SimpleTcpCluster#getManagers()}
   *   <li>{@link SimpleTcpCluster#hasMembers()}
   *   <li>{@link SimpleTcpCluster#isHeartbeatBackgroundEnabled()}
   *   <li>{@link SimpleTcpCluster#isNotifyLifecycleListenerOnFailure()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    GroupChannel channel = new GroupChannel();

    // Act
    simpleTcpCluster.setChannel(channel);
    simpleTcpCluster.setChannelSendOptions(3);
    simpleTcpCluster.setChannelStartOptions(1);
    FarmWarDeployer clusterDeployer = new FarmWarDeployer();
    simpleTcpCluster.setClusterDeployer(clusterDeployer);
    simpleTcpCluster.setClusterName("Cluster Name");
    simpleTcpCluster.setHeartbeatBackgroundEnabled(true);
    BackupManager managerTemplate = new BackupManager();
    simpleTcpCluster.setManagerTemplate(managerTemplate);
    String actualToStringResult = simpleTcpCluster.toString();
    Channel actualChannel = simpleTcpCluster.getChannel();
    int actualChannelSendOptions = simpleTcpCluster.getChannelSendOptions();
    int actualChannelStartOptions = simpleTcpCluster.getChannelStartOptions();
    ClusterDeployer actualClusterDeployer = simpleTcpCluster.getClusterDeployer();
    Container actualContainer = simpleTcpCluster.getContainer();
    ClusterManager actualManagerTemplate = simpleTcpCluster.getManagerTemplate();
    Map<String, ClusterManager> actualManagers = simpleTcpCluster.getManagers();
    boolean actualHasMembersResult = simpleTcpCluster.hasMembers();
    boolean actualIsHeartbeatBackgroundEnabledResult = simpleTcpCluster.isHeartbeatBackgroundEnabled();

    // Assert
    assertEquals("SimpleTcpCluster[Container is null]", actualToStringResult);
    assertNull(actualContainer);
    assertEquals(1, actualChannelStartOptions);
    assertEquals(3, actualChannelSendOptions);
    assertFalse(actualHasMembersResult);
    assertFalse(simpleTcpCluster.isNotifyLifecycleListenerOnFailure());
    assertTrue(actualManagers.isEmpty());
    assertTrue(actualIsHeartbeatBackgroundEnabledResult);
    assertSame(clusterDeployer, actualClusterDeployer);
    assertSame(managerTemplate, actualManagerTemplate);
    assertSame(channel, actualChannel);
  }

  /**
   * Test {@link SimpleTcpCluster#getChannelSendOptionsName()}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#getChannelSendOptionsName()}
   */
  @Test
  public void testGetChannelSendOptionsName() {
    // Arrange, Act and Assert
    assertEquals("async", (new SimpleTcpCluster()).getChannelSendOptionsName());
  }

  /**
   * Test {@link SimpleTcpCluster#createManager(String)}.
   * <ul>
   *   <li>Then Cluster ManagerTemplate return {@link DeltaManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#createManager(String)}
   */
  @Test
  public void testCreateManager_thenClusterManagerTemplateReturnDeltaManager() {
    // Arrange and Act
    Manager actualCreateManagerResult = (new SimpleTcpCluster()).createManager("Name");

    // Assert
    assertTrue(actualCreateManagerResult instanceof DeltaManager);
    CatalinaCluster cluster = ((DeltaManager) actualCreateManagerResult).getCluster();
    assertTrue(((SimpleTcpCluster) cluster).getManagerTemplate() instanceof DeltaManager);
    assertTrue(cluster instanceof SimpleTcpCluster);
    assertTrue(cluster.getChannel() instanceof GroupChannel);
    assertEquals("Name", ((DeltaManager) actualCreateManagerResult).getName());
  }

  /**
   * Test {@link SimpleTcpCluster#createManager(String)}.
   * <ul>
   *   <li>Then return {@link BackupManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#createManager(String)}
   */
  @Test
  public void testCreateManager_thenReturnBackupManager() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setManagerTemplate(new BackupManager());

    // Act
    Manager actualCreateManagerResult = simpleTcpCluster.createManager("Name");

    // Assert
    assertTrue(actualCreateManagerResult instanceof BackupManager);
    assertEquals("Name", ((BackupManager) actualCreateManagerResult).getName());
    assertEquals("Name-map", ((BackupManager) actualCreateManagerResult).getMapName());
    assertEquals("org.apache.catalina.ha.session.BackupManager",
        ((BackupManager) actualCreateManagerResult).getClassName());
    assertEquals("sync, use_ack", ((BackupManager) actualCreateManagerResult).getMapSendOptionsName());
    assertEquals(0, ((BackupManager) actualCreateManagerResult).getInvalidatedSessions().length);
    assertEquals(15000L, ((BackupManager) actualCreateManagerResult).getRpcTimeout());
    assertEquals(5000L, ((BackupManager) actualCreateManagerResult).getAccessTimeout());
    assertEquals(6, ((BackupManager) actualCreateManagerResult).getMapSendOptions());
    assertFalse(((BackupManager) actualCreateManagerResult).isTerminateOnStartFailure());
  }

  /**
   * Test {@link SimpleTcpCluster#createManager(String)}.
   * <ul>
   *   <li>Then return ClassName is {@code DeltaManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#createManager(String)}
   */
  @Test
  public void testCreateManager_thenReturnClassNameIsOrgApacheCatalinaHaSessionDeltaManager() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setManagerTemplate(null);

    // Act
    Manager actualCreateManagerResult = simpleTcpCluster.createManager("Name");

    // Assert
    assertTrue(actualCreateManagerResult instanceof DeltaManager);
    assertEquals("org.apache.catalina.ha.session.DeltaManager",
        ((DeltaManager) actualCreateManagerResult).getClassName());
    assertNull(((DeltaManager) actualCreateManagerResult).getName());
    assertEquals(0, ((DeltaManager) actualCreateManagerResult).getCounterNoStateTransferred());
    assertEquals(0, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, ((DeltaManager) actualCreateManagerResult).getReceivedQueueSize());
    assertEquals(0, ((DeltaManager) actualCreateManagerResult).getInvalidatedSessions().length);
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_ALL_SESSION_DATA());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_SESSION_ACCESSED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_SESSION_CREATED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_SESSION_DELTA());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterReceive_EVT_SESSION_EXPIRED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_ALL_SESSION_DATA());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_SESSION_ACCESSED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_SESSION_CREATED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_SESSION_DELTA());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getCounterSend_EVT_SESSION_EXPIRED());
    assertEquals(0L, ((DeltaManager) actualCreateManagerResult).getSessionReplaceCounter());
    assertEquals(1000, ((DeltaManager) actualCreateManagerResult).getSendAllSessionsSize());
    assertEquals(2000, ((DeltaManager) actualCreateManagerResult).getSendAllSessionsWaitTime());
    assertEquals(60, ((DeltaManager) actualCreateManagerResult).getStateTransferTimeout());
    assertFalse(((DeltaManager) actualCreateManagerResult).getStateTransferred());
    assertFalse(((DeltaManager) actualCreateManagerResult).isExpireSessionsOnShutdown());
    assertFalse(((DeltaManager) actualCreateManagerResult).isNoContextManagerReceived());
    assertTrue(((DeltaManager) actualCreateManagerResult).isNotifyContainerListenersOnReplication());
    assertTrue(((DeltaManager) actualCreateManagerResult).isNotifySessionListenersOnReplication());
    assertTrue(((DeltaManager) actualCreateManagerResult).isSendAllSessions());
    assertTrue(((DeltaManager) actualCreateManagerResult).isStateTimestampDrop());
  }

  /**
   * Test {@link SimpleTcpCluster#registerManager(Manager)}.
   * <ul>
   *   <li>Given {@code Manager}.</li>
   *   <li>When {@link BackupManager} (default constructor) Name is {@code Manager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#registerManager(Manager)}
   */
  @Test
  public void testRegisterManager_givenManager_whenBackupManagerNameIsManager() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    StandardEngine container = new StandardEngine();
    simpleTcpCluster.setContainer(container);

    StandardContext context = new StandardContext();
    context.setParent(new StandardHost());
    context.setName("Manager");

    BackupManager manager = new BackupManager();
    manager.setName("Manager");
    manager.setContext(context);

    // Act
    simpleTcpCluster.registerManager(manager);

    // Assert
    CatalinaCluster cluster = manager.getCluster();
    Container container2 = cluster.getContainer();
    assertTrue(container2 instanceof StandardEngine);
    assertTrue(cluster instanceof SimpleTcpCluster);
    assertEquals("Catalina", ((SimpleTcpCluster) cluster).getDomainInternal());
    assertEquals("null#Manager", manager.getName());
    assertEquals("null#Manager-map", manager.getMapName());
    assertSame(container, container2);
  }

  /**
   * Test {@link SimpleTcpCluster#registerManager(Manager)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link BackupManager} (default constructor) Name is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#registerManager(Manager)}
   */
  @Test
  public void testRegisterManager_givenNull_whenBackupManagerNameIsNull() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    StandardEngine container = new StandardEngine();
    simpleTcpCluster.setContainer(container);

    StandardContext context = new StandardContext();
    context.setParent(new StandardHost());
    context.setName("Manager");

    BackupManager manager = new BackupManager();
    manager.setName(null);
    manager.setContext(context);

    // Act
    simpleTcpCluster.registerManager(manager);

    // Assert
    CatalinaCluster cluster = manager.getCluster();
    Container container2 = cluster.getContainer();
    assertTrue(container2 instanceof StandardEngine);
    assertTrue(cluster instanceof SimpleTcpCluster);
    assertEquals("Catalina", ((SimpleTcpCluster) cluster).getDomainInternal());
    assertEquals("null#Manager", manager.getName());
    assertEquals("null#Manager-map", manager.getMapName());
    assertSame(container, container2);
  }

  /**
   * Test {@link SimpleTcpCluster#registerManager(Manager)}.
   * <ul>
   *   <li>Then {@link DeltaManager} (default constructor) Cluster ManagerTemplate {@link DeltaManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#registerManager(Manager)}
   */
  @Test
  public void testRegisterManager_thenDeltaManagerClusterManagerTemplateDeltaManager() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    DeltaManager manager = new DeltaManager();
    manager.setName(SimpleTcpCluster.BEFORE_MANAGERREGISTER_EVENT);

    // Act
    simpleTcpCluster.registerManager(manager);

    // Assert
    CatalinaCluster cluster = manager.getCluster();
    assertTrue(((SimpleTcpCluster) cluster).getManagerTemplate() instanceof DeltaManager);
    assertTrue(cluster instanceof SimpleTcpCluster);
    Channel channel = cluster.getChannel();
    assertTrue(((GroupChannel) channel).getNext() instanceof ChannelCoordinator);
    assertTrue(channel instanceof GroupChannel);
    assertTrue(((GroupChannel) channel).getMembershipService() instanceof McastService);
    Map<String, ClusterManager> managers = cluster.getManagers();
    assertEquals(1, managers.size());
    assertSame(manager, managers.get(SimpleTcpCluster.BEFORE_MANAGERREGISTER_EVENT));
    assertSame(channel, ((GroupChannel) channel).getFirstInterceptor());
  }

  /**
   * Test {@link SimpleTcpCluster#getManagerName(String, Manager)}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getManagerName(String, Manager)}
   */
  @Test
  public void testGetManagerName_givenSimpleTcpCluster_whenName_thenReturnName() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act and Assert
    assertEquals("Name", simpleTcpCluster.getManagerName("Name", new BackupManager()));
  }

  /**
   * Test {@link SimpleTcpCluster#getManagerName(String, Manager)}.
   * <ul>
   *   <li>Then return {@code null#Manager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getManagerName(String, Manager)}
   */
  @Test
  public void testGetManagerName_thenReturnNullManager() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(new StandardEngine());

    StandardContext context = new StandardContext();
    context.setParent(new StandardHost());
    context.setName("Manager");

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    // Act and Assert
    assertEquals("null#Manager", simpleTcpCluster.getManagerName(null, manager));
  }

  /**
   * Test {@link SimpleTcpCluster#getManager(String)}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#getManager(String)}
   */
  @Test
  public void testGetManager() {
    // Arrange, Act and Assert
    assertNull((new SimpleTcpCluster()).getManager("Name"));
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());
    simpleTcpCluster.setContainer(new TesterContext());

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Clusternull", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Clusternull", keyPropertyList.get("type"));
    assertEquals("type=Clusternull", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Clusternull", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpCluster() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterDeployer is {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpClusterClusterDeployerIsFarmWarDeployer() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) Container is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpClusterContainerIsNull() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(null);
    simpleTcpCluster.setContainer(null);

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) Container is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpClusterContainerIsNull2() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());
    simpleTcpCluster.setContainer(null);

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpClusterContainerIsStandardEngine() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(null);
    simpleTcpCluster.setContainer(new StandardEngine());

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#initInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#initInternal()}
   */
  @Test
  public void testInitInternal_givenSimpleTcpClusterContainerIsStandardEngine2() throws LifecycleException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());
    simpleTcpCluster.setContainer(new StandardEngine());

    // Act
    simpleTcpCluster.initInternal();

    // Assert
    ObjectName objectName = simpleTcpCluster.getObjectName();
    assertEquals("Catalina:type=Cluster", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Cluster", keyPropertyList.get("type"));
    assertEquals("type=Cluster", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Cluster", objectName.getKeyPropertyListString());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) addClusterListener {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterAddClusterListenerFarmWarDeployer() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.addClusterListener(new FarmWarDeployer());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) addValve {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterAddValveBasicAuthenticator() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) addValve {@link ClusterSingleSignOn} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterAddValveClusterSingleSignOn() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.addValve(new ClusterSingleSignOn());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) addValve {@link ReplicationValve} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterAddValveReplicationValve() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.addValve(new ReplicationValve());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ChannelStartOptions is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterChannelStartOptionsIsEight() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setChannelStartOptions(8);
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ChannelStartOptions is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterChannelStartOptionsIsFive() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setChannelStartOptions(5);
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterDeployer is {@link FarmWarDeployer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterClusterDeployerIsFarmWarDeployer() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterName is {@code ,component=Channel}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterClusterNameIsComponentChannel() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterName(",component=Channel");
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterName is {@code simpleTcpCluster.start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterClusterNameIsSimpleTcpClusterStart() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterName("simpleTcpCluster.start");
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ClusterName is {@code type=Channel,channel=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterClusterNameIsTypeChannelChannel() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterName("type=Channel,channel=");
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) HeartbeatBackgroundEnabled is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterHeartbeatBackgroundEnabledIsTrue() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setHeartbeatBackgroundEnabled(true);
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) ManagerTemplate is {@link BackupManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_givenSimpleTcpClusterManagerTemplateIsBackupManager() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setManagerTemplate(new BackupManager());
    simpleTcpCluster.addValve(new BasicAuthenticator());
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#startInternal()}.
   * <ul>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#startInternal()}
   */
  @Test
  public void testStartInternal_thenThrowLifecycleException() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> simpleTcpCluster.startInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then first element {@link ClusterSessionListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_givenSimpleTcpCluster_thenFirstElementClusterSessionListener() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert
    ClusterListener[] findClusterListenersResult = simpleTcpCluster.findClusterListeners();
    ClusterListener clusterListener = findClusterListenersResult[0];
    assertTrue(clusterListener instanceof ClusterSessionListener);
    List<ClusterListener> clusterListenerList = simpleTcpCluster.clusterListeners;
    assertEquals(1, clusterListenerList.size());
    assertEquals(1, findClusterListenersResult.length);
    assertSame(simpleTcpCluster, clusterListener.getCluster());
    assertSame(clusterListener, clusterListenerList.get(0));
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then first element {@link ClusterSessionListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenFirstElementClusterSessionListener() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert
    ClusterListener[] findClusterListenersResult = simpleTcpCluster.findClusterListeners();
    ClusterListener clusterListener = findClusterListenersResult[0];
    assertTrue(clusterListener instanceof ClusterSessionListener);
    List<ClusterListener> clusterListenerList = simpleTcpCluster.clusterListeners;
    assertEquals(1, clusterListenerList.size());
    assertEquals(1, findClusterListenersResult.length);
    assertSame(simpleTcpCluster, clusterListener.getCluster());
    assertSame(clusterListener, clusterListenerList.get(0));
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then first element {@link FarmWarDeployer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenFirstElementFarmWarDeployer() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    FarmWarDeployer listener = new FarmWarDeployer();
    simpleTcpCluster.addClusterListener(listener);
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert that nothing has changed
    ClusterListener[] findClusterListenersResult = simpleTcpCluster.findClusterListeners();
    ClusterListener clusterListener = findClusterListenersResult[0];
    assertTrue(clusterListener instanceof FarmWarDeployer);
    List<ClusterListener> clusterListenerList = simpleTcpCluster.clusterListeners;
    assertEquals(1, clusterListenerList.size());
    assertEquals(1, findClusterListenersResult.length);
    assertSame(listener, clusterListenerList.get(0));
    assertSame(listener, clusterListener);
    assertSame(simpleTcpCluster, clusterListener.getCluster());
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then first element {@link JvmRouteBinderValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenFirstElementJvmRouteBinderValve() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setManagerTemplate(new BackupManager());
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert
    Valve[] valves = simpleTcpCluster.getValves();
    assertTrue(valves[0] instanceof JvmRouteBinderValve);
    assertTrue(valves[1] instanceof ReplicationValve);
    Channel channel = simpleTcpCluster.getChannel();
    assertTrue(channel instanceof GroupChannel);
    assertEquals(2, valves.length);
    assertTrue(((GroupChannel) channel).getHeartbeat());
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then first element {@link ReplicationValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenFirstElementReplicationValve() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    ReplicationValve valve = new ReplicationValve();
    simpleTcpCluster.addValve(valve);

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert that nothing has changed
    Valve[] valves = simpleTcpCluster.getValves();
    Valve valve2 = valves[0];
    assertTrue(valve2 instanceof ReplicationValve);
    assertEquals(1, valves.length);
    assertSame(valve, valve2);
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then not {@link SimpleTcpCluster} (default constructor) Channel Heartbeat.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenNotSimpleTcpClusterChannelHeartbeat() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setHeartbeatBackgroundEnabled(true);
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert
    ClusterListener[] findClusterListenersResult = simpleTcpCluster.findClusterListeners();
    ClusterListener clusterListener = findClusterListenersResult[0];
    assertTrue(clusterListener instanceof ClusterSessionListener);
    Channel channel = simpleTcpCluster.getChannel();
    assertTrue(channel instanceof GroupChannel);
    List<ClusterListener> clusterListenerList = simpleTcpCluster.clusterListeners;
    assertEquals(1, clusterListenerList.size());
    assertEquals(1, findClusterListenersResult.length);
    assertFalse(((GroupChannel) channel).getHeartbeat());
    assertSame(simpleTcpCluster, clusterListener.getCluster());
    assertSame(clusterListener, clusterListenerList.get(0));
  }

  /**
   * Test {@link SimpleTcpCluster#checkDefaults()}.
   * <ul>
   *   <li>Then {@link SimpleTcpCluster} (default constructor) ClusterDeployer {@link FarmWarDeployer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#checkDefaults()}
   */
  @Test
  public void testCheckDefaults_thenSimpleTcpClusterClusterDeployerFarmWarDeployer() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setClusterDeployer(new FarmWarDeployer());
    simpleTcpCluster.addValve(new BasicAuthenticator());

    // Act
    simpleTcpCluster.checkDefaults();

    // Assert
    ClusterDeployer clusterDeployer = simpleTcpCluster.getClusterDeployer();
    assertTrue(clusterDeployer instanceof FarmWarDeployer);
    ClusterListener[] findClusterListenersResult = simpleTcpCluster.findClusterListeners();
    ClusterListener clusterListener = findClusterListenersResult[0];
    assertTrue(clusterListener instanceof ClusterSessionListener);
    List<ClusterListener> clusterListenerList = simpleTcpCluster.clusterListeners;
    assertEquals(1, clusterListenerList.size());
    assertEquals(1, findClusterListenersResult.length);
    assertSame(simpleTcpCluster, clusterDeployer.getCluster());
    assertSame(simpleTcpCluster, clusterListener.getCluster());
    assertSame(clusterListener, clusterListenerList.get(0));
  }

  /**
   * Test {@link SimpleTcpCluster#unregisterClusterValve()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addValve {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#unregisterClusterValve()}
   */
  @Test
  public void testUnregisterClusterValve_givenStandardContextAddValveBasicAuthenticator() {
    // Arrange
    StandardContext container = new StandardContext();
    container.addValve(new BasicAuthenticator());

    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(container);
    simpleTcpCluster.addValve(new ReplicationValve());

    // Act
    simpleTcpCluster.unregisterClusterValve();

    // Assert
    Valve[] valves = simpleTcpCluster.getValves();
    Valve valve = valves[0];
    assertTrue(valve instanceof ReplicationValve);
    assertEquals("DESTROYED", ((ReplicationValve) valve).getStateName());
    assertEquals(1, valves.length);
    assertEquals(LifecycleState.DESTROYED, ((ReplicationValve) valve).getState());
  }

  /**
   * Test {@link SimpleTcpCluster#unregisterClusterValve()}.
   * <ul>
   *   <li>Then first element {@link ReplicationValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#unregisterClusterValve()}
   */
  @Test
  public void testUnregisterClusterValve_thenFirstElementReplicationValve() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(new StandardContext());
    simpleTcpCluster.addValve(new ReplicationValve());

    // Act
    simpleTcpCluster.unregisterClusterValve();

    // Assert
    Valve[] valves = simpleTcpCluster.getValves();
    Valve valve = valves[0];
    assertTrue(valve instanceof ReplicationValve);
    assertEquals("DESTROYED", ((ReplicationValve) valve).getStateName());
    assertEquals(1, valves.length);
    assertEquals(LifecycleState.DESTROYED, ((ReplicationValve) valve).getState());
  }

  /**
   * Test {@link SimpleTcpCluster#accept(Serializable, Member)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#accept(Serializable, Member)}
   */
  @Test
  public void testAccept_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act and Assert
    assertTrue(simpleTcpCluster.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link SimpleTcpCluster#accept(Serializable, Member)}.
   * <ul>
   *   <li>When {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#accept(Serializable, Member)}
   */
  @Test
  public void testAccept_whenSimpleDateFormatWithYyyyMmDd_thenReturnFalse() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertFalse(simpleTcpCluster.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link SimpleTcpCluster#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenSimpleTcpCluster_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SimpleTcpCluster()).getDomainInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnCatalina() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", simpleTcpCluster.getDomainInternal());
  }

  /**
   * Test {@link SimpleTcpCluster#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenSimpleTcpClusterContainerIsStandardEngine() {
    // Arrange
    SimpleTcpCluster simpleTcpCluster = new SimpleTcpCluster();
    simpleTcpCluster.setContainer(new StandardEngine());

    // Act and Assert
    assertEquals("type=Cluster", simpleTcpCluster.getObjectNameKeyProperties());
  }

  /**
   * Test {@link SimpleTcpCluster#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then return {@code type=Cluster}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTcpCluster#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenSimpleTcpCluster_thenReturnTypeCluster() {
    // Arrange, Act and Assert
    assertEquals("type=Cluster", (new SimpleTcpCluster()).getObjectNameKeyProperties());
  }
}
