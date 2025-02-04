package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.catalina.ha.deploy.FarmWarDeployer;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelListener;
import org.apache.catalina.tribes.ChannelReceiver;
import org.apache.catalina.tribes.ChannelSender;
import org.apache.catalina.tribes.ErrorHandler;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipListener;
import org.apache.catalina.tribes.MembershipService;
import org.apache.catalina.tribes.MessageListener;
import org.apache.catalina.tribes.group.GroupChannel.InterceptorIterator;
import org.apache.catalina.tribes.group.TestGroupChannelMemberArrival.TestMbrListener;
import org.apache.catalina.tribes.group.interceptors.DomainFilterInterceptor;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.transport.ReplicationTransmitter;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;
import org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor;
import org.junit.Test;

public class GroupChannelDiffblueTest {
  /**
   * Test InterceptorIterator getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InterceptorIterator#InterceptorIterator(ChannelInterceptor, ChannelInterceptor)}
   *   <li>{@link InterceptorIterator#remove()}
   * </ul>
   */
  @Test
  public void testInterceptorIteratorGettersAndSetters() {
    // Arrange
    ChannelCoordinator start = new ChannelCoordinator();

    // Act
    InterceptorIterator actualInterceptorIterator = new InterceptorIterator(start, new ChannelCoordinator());
    actualInterceptorIterator.remove();

    // Assert
    ChannelInterceptor actualNextResult = actualInterceptorIterator.next();
    assertFalse(actualInterceptorIterator.hasNext());
    assertSame(start, actualNextResult);
  }

  /**
   * Test InterceptorIterator {@link InterceptorIterator#hasNext()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InterceptorIterator#hasNext()}
   */
  @Test
  public void testInterceptorIteratorHasNext_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new InterceptorIterator(null, new ChannelCoordinator())).hasNext());
  }

  /**
   * Test InterceptorIterator {@link InterceptorIterator#hasNext()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InterceptorIterator#hasNext()}
   */
  @Test
  public void testInterceptorIteratorHasNext_thenReturnTrue() {
    // Arrange
    ChannelCoordinator start = new ChannelCoordinator();

    // Act and Assert
    assertTrue((new InterceptorIterator(start, new ChannelCoordinator())).hasNext());
  }

  /**
   * Test InterceptorIterator {@link InterceptorIterator#next()}.
   * <p>
   * Method under test: {@link InterceptorIterator#next()}
   */
  @Test
  public void testInterceptorIteratorNext() {
    // Arrange
    ChannelCoordinator start = new ChannelCoordinator();
    InterceptorIterator interceptorIterator = new InterceptorIterator(start, new ChannelCoordinator());

    // Act
    ChannelInterceptor actualNextResult = interceptorIterator.next();

    // Assert
    assertFalse(interceptorIterator.hasNext());
    assertSame(start, actualNextResult);
  }

  /**
   * Test InterceptorIterator {@link InterceptorIterator#next()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InterceptorIterator#next()}
   */
  @Test
  public void testInterceptorIteratorNext_thenReturnNull() {
    // Arrange
    InterceptorIterator interceptorIterator = new InterceptorIterator(null, new ChannelCoordinator());

    // Act and Assert
    assertNull(interceptorIterator.next());
    assertFalse(interceptorIterator.hasNext());
  }

  /**
   * Test new {@link GroupChannel} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link GroupChannel}
   */
  @Test
  public void testNewGroupChannel() {
    // Arrange and Act
    GroupChannel actualGroupChannel = new GroupChannel();

    // Assert
    ChannelInterceptor next = actualGroupChannel.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    assertTrue(actualGroupChannel.interceptors instanceof GroupChannel);
    Iterator<ChannelInterceptor> interceptors = actualGroupChannel.getInterceptors();
    assertTrue(interceptors instanceof InterceptorIterator);
    assertTrue(actualGroupChannel.getMembershipService() instanceof McastService);
    assertTrue(actualGroupChannel.getChannelSender() instanceof ReplicationTransmitter);
    assertTrue(actualGroupChannel.getChannelReceiver() instanceof NioReceiver);
    assertEquals("", actualGroupChannel.getJmxPrefix());
    assertEquals("ClusterChannel", actualGroupChannel.getJmxDomain());
    assertNull(actualGroupChannel.getName());
    assertNull(actualGroupChannel.getUtilityExecutor());
    assertNull(actualGroupChannel.heartbeatFuture);
    assertNull(actualGroupChannel.monitorFuture);
    assertNull(actualGroupChannel.getChannel());
    assertNull(actualGroupChannel.getPrevious());
    assertEquals(0, actualGroupChannel.getOptionFlag());
    assertEquals(0, actualGroupChannel.getMembers().length);
    assertEquals(5000L, actualGroupChannel.getHeartbeatSleeptime());
    assertFalse(interceptors.hasNext());
    assertFalse(actualGroupChannel.hasMembers());
    assertFalse(actualGroupChannel.getOptionCheck());
    assertFalse(actualGroupChannel.ownExecutor);
    assertTrue(actualGroupChannel.channelListeners.isEmpty());
    assertTrue(actualGroupChannel.membershipListeners.isEmpty());
    assertTrue(actualGroupChannel.getHeartbeat());
    assertTrue(actualGroupChannel.isJmxEnabled());
    assertSame(actualGroupChannel.coordinator, next);
    ChannelInterceptor expectedFirstInterceptor = actualGroupChannel.interceptors;
    assertSame(expectedFirstInterceptor, actualGroupChannel.getFirstInterceptor());
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)} with {@code destination}, {@code msg}, {@code options}, {@code handler}.
   * <ul>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)}
   */
  @Test
  public void testSendWithDestinationMsgOptionsHandler_thenThrowChannelException() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class, () -> groupChannel.send(null, new SimpleDateFormat("yyyy/mm/dd"), 1, null));
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)} with {@code destination}, {@code msg}, {@code options}, {@code handler}.
   * <ul>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)}
   */
  @Test
  public void testSendWithDestinationMsgOptionsHandler_thenThrowChannelException2() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class, () -> groupChannel.send(new Member[]{new MemberImpl()}, null, 1, null));
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)} with {@code destination}, {@code msg}, {@code options}, {@code handler}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int, ErrorHandler)}
   */
  @Test
  public void testSendWithDestinationMsgOptionsHandler_whenEmptyArrayOfMember() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class,
        () -> groupChannel.send(new Member[]{}, new SimpleDateFormat("yyyy/mm/dd"), 1, null));
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int)} with {@code destination}, {@code msg}, {@code options}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int)}
   */
  @Test
  public void testSendWithDestinationMsgOptions_whenEmptyArrayOfMember() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class,
        () -> groupChannel.send(new Member[]{}, new SimpleDateFormat("yyyy/mm/dd"), 1));
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int)} with {@code destination}, {@code msg}, {@code options}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int)}
   */
  @Test
  public void testSendWithDestinationMsgOptions_whenNull_thenThrowChannelException() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class, () -> groupChannel.send(null, new SimpleDateFormat("yyyy/mm/dd"), 1));
  }

  /**
   * Test {@link GroupChannel#send(Member[], Serializable, int)} with {@code destination}, {@code msg}, {@code options}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#send(Member[], Serializable, int)}
   */
  @Test
  public void testSendWithDestinationMsgOptions_whenNull_thenThrowChannelException2() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertThrows(ChannelException.class, () -> groupChannel.send(new Member[]{new MemberImpl()}, null, 1));
  }

  /**
   * Test {@link GroupChannel#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenAxaxaxaxBytesIsUtf8_thenArrayLengthIsOne() throws UnsupportedEncodingException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addMembershipListener(new DomainFilterInterceptor());

    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act
    groupChannel.memberAdded(member);

    // Assert
    List<MembershipListener> membershipListenerList = groupChannel.membershipListeners;
    assertEquals(1, membershipListenerList.size());
    MembershipListener getResult = membershipListenerList.get(0);
    assertTrue(getResult instanceof DomainFilterInterceptor);
    Member[] members = ((DomainFilterInterceptor) getResult).getMembers();
    assertEquals(1, members.length);
    assertTrue(((DomainFilterInterceptor) getResult).hasMembers());
    assertSame(member, members[0]);
  }

  /**
   * Test {@link GroupChannel#memberAdded(Member)}.
   * <ul>
   *   <li>Then {@link GroupChannel} (default constructor) {@link GroupChannel#membershipListeners} first {@link TestMbrListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenGroupChannelMembershipListenersFirstTestMbrListener() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    TestMbrListener membershipListener = new TestMbrListener("simpleTcpCluster.member.added");
    groupChannel.addMembershipListener(membershipListener);
    MemberImpl member = new MemberImpl();

    // Act
    groupChannel.memberAdded(member);

    // Assert
    List<MembershipListener> membershipListenerList = groupChannel.membershipListeners;
    assertEquals(1, membershipListenerList.size());
    MembershipListener getResult = membershipListenerList.get(0);
    assertTrue(getResult instanceof TestMbrListener);
    ArrayList<Member> memberList = ((TestMbrListener) getResult).members;
    assertEquals(1, memberList.size());
    assertSame(member, memberList.get(0));
    assertSame(membershipListener.members, memberList);
  }

  /**
   * Test {@link GroupChannel#memberAdded(Member)}.
   * <ul>
   *   <li>Then not {@link GroupChannel} (default constructor) {@link GroupChannel#membershipListeners} first hasMembers.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenNotGroupChannelMembershipListenersFirstHasMembers() {
    // Arrange
    DomainFilterInterceptor membershipListener = new DomainFilterInterceptor();
    membershipListener.setDomain(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addMembershipListener(membershipListener);

    // Act
    groupChannel.memberAdded(new MemberImpl());

    // Assert that nothing has changed
    List<MembershipListener> membershipListenerList = groupChannel.membershipListeners;
    assertEquals(1, membershipListenerList.size());
    MembershipListener getResult = membershipListenerList.get(0);
    assertTrue(getResult instanceof DomainFilterInterceptor);
    assertFalse(((DomainFilterInterceptor) getResult).hasMembers());
  }

  /**
   * Test {@link GroupChannel#checkOptionFlags()}.
   * <ul>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#checkOptionFlags()}
   */
  @Test
  public void testCheckOptionFlags_thenThrowChannelException() throws ChannelException {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new ChannelCoordinator());

    // Act and Assert
    assertThrows(ChannelException.class, () -> groupChannel.checkOptionFlags());
  }

  /**
   * Test {@link GroupChannel#getFirstInterceptor()}.
   * <p>
   * Method under test: {@link GroupChannel#getFirstInterceptor()}
   */
  @Test
  public void testGetFirstInterceptor() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act
    ChannelInterceptor actualFirstInterceptor = groupChannel.getFirstInterceptor();

    // Assert
    ChannelCoordinator expectedNext = ((GroupChannel) actualFirstInterceptor).coordinator;
    assertSame(expectedNext, groupChannel.getNext());
    assertSame(((GroupChannel) actualFirstInterceptor).interceptors, actualFirstInterceptor);
  }

  /**
   * Test {@link GroupChannel#getChannelSender()}.
   * <p>
   * Method under test: {@link GroupChannel#getChannelSender()}
   */
  @Test
  public void testGetChannelSender() {
    // Arrange and Act
    ChannelSender actualChannelSender = (new GroupChannel()).getChannelSender();

    // Assert
    assertTrue(actualChannelSender instanceof ReplicationTransmitter);
    assertTrue(((ReplicationTransmitter) actualChannelSender).getTransport() instanceof PooledParallelSender);
    assertNull(actualChannelSender.getChannel());
  }

  /**
   * Test {@link GroupChannel#getMembershipService()}.
   * <p>
   * Method under test: {@link GroupChannel#getMembershipService()}
   */
  @Test
  public void testGetMembershipService() {
    // Arrange and Act
    MembershipService actualMembershipService = (new GroupChannel()).getMembershipService();

    // Assert
    assertTrue(actualMembershipService instanceof McastService);
    assertEquals("228.0.0.4", ((McastService) actualMembershipService).getAddress());
    assertNull(((McastService) actualMembershipService).getDomain());
    assertNull(((McastService) actualMembershipService).getPayload());
    assertNull(((McastService) actualMembershipService).getBind());
    assertNull(actualMembershipService.getChannel());
    assertNull(actualMembershipService.getMembershipProvider());
    assertEquals(0, ((McastService) actualMembershipService).getSoTimeout());
    assertEquals(0, ((McastService) actualMembershipService).getTtl());
    assertEquals(0, actualMembershipService.getMembers().length);
    assertEquals(0, actualMembershipService.getMembersByName().length);
    assertEquals(10, ((McastService) actualMembershipService).getRecoveryCounter());
    assertEquals(3000L, ((McastService) actualMembershipService).getDropTime());
    assertEquals(45564, ((McastService) actualMembershipService).getPort());
    assertEquals(5000L, ((McastService) actualMembershipService).getRecoverySleepTime());
    assertEquals(500L, ((McastService) actualMembershipService).getFrequency());
    Properties properties = actualMembershipService.getProperties();
    assertEquals(8, properties.size());
    assertFalse(actualMembershipService.hasMembers());
    assertFalse(((McastService) actualMembershipService).getLocalLoopbackDisabled());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
    assertTrue(((McastService) actualMembershipService).getRecoveryEnabled());
  }

  /**
   * Test {@link GroupChannel#setChannelReceiver(ChannelReceiver)}.
   * <ul>
   *   <li>When {@link NioReceiver} (default constructor).</li>
   *   <li>Then {@link NioReceiver} (default constructor) Listener {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#setChannelReceiver(ChannelReceiver)}
   */
  @Test
  public void testSetChannelReceiver_whenNioReceiver_thenNioReceiverListenerChannelCoordinator() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    NioReceiver clusterReceiver = new NioReceiver();

    // Act
    groupChannel.setChannelReceiver(clusterReceiver);

    // Assert
    MessageListener listener = clusterReceiver.getListener();
    assertTrue(listener instanceof ChannelCoordinator);
    assertSame(clusterReceiver, groupChannel.getChannelReceiver());
    ChannelCoordinator channelCoordinator = groupChannel.coordinator;
    assertSame(channelCoordinator, groupChannel.getNext());
    assertSame(channelCoordinator, listener);
    assertSame(channelCoordinator, clusterReceiver.getMessageListener());
    ChannelInterceptor expectedFirstInterceptor = groupChannel.interceptors;
    assertSame(expectedFirstInterceptor, groupChannel.getFirstInterceptor());
  }

  /**
   * Test {@link GroupChannel#setChannelReceiver(ChannelReceiver)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link GroupChannel} (default constructor) Next {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#setChannelReceiver(ChannelReceiver)}
   */
  @Test
  public void testSetChannelReceiver_whenNull_thenGroupChannelNextChannelCoordinator() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act
    groupChannel.setChannelReceiver(null);

    // Assert
    ChannelInterceptor next = groupChannel.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    assertTrue(groupChannel.getMembershipService() instanceof McastService);
    assertTrue(groupChannel.getChannelSender() instanceof ReplicationTransmitter);
    assertNull(groupChannel.getChannelReceiver());
    assertEquals(0, groupChannel.getMembers().length);
    assertSame(groupChannel.coordinator, next);
    ChannelInterceptor expectedFirstInterceptor = groupChannel.interceptors;
    assertSame(expectedFirstInterceptor, groupChannel.getFirstInterceptor());
  }

  /**
   * Test {@link GroupChannel#setChannelSender(ChannelSender)}.
   * <p>
   * Method under test: {@link GroupChannel#setChannelSender(ChannelSender)}
   */
  @Test
  public void testSetChannelSender() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    ReplicationTransmitter clusterSender = new ReplicationTransmitter();

    // Act
    groupChannel.setChannelSender(clusterSender);

    // Assert
    ChannelInterceptor next = groupChannel.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    ChannelInterceptor channelInterceptor = groupChannel.interceptors;
    assertTrue(channelInterceptor instanceof GroupChannel);
    assertSame(clusterSender, groupChannel.coordinator.getClusterSender());
    assertSame(clusterSender, ((ChannelCoordinator) next).getClusterSender());
    assertSame(clusterSender, groupChannel.getChannelSender());
    assertSame(clusterSender, ((GroupChannel) channelInterceptor).getChannelSender());
  }

  /**
   * Test {@link GroupChannel#setMembershipService(MembershipService)}.
   * <ul>
   *   <li>When {@link McastService} (default constructor).</li>
   *   <li>Then {@link GroupChannel} (default constructor) Next {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#setMembershipService(MembershipService)}
   */
  @Test
  public void testSetMembershipService_whenMcastService_thenGroupChannelNextChannelCoordinator() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    McastService membershipService = new McastService();

    // Act
    groupChannel.setMembershipService(membershipService);

    // Assert
    ChannelInterceptor next = groupChannel.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    ChannelInterceptor channelInterceptor = groupChannel.interceptors;
    assertTrue(channelInterceptor instanceof GroupChannel);
    assertSame(membershipService, groupChannel.coordinator.getMembershipService());
    assertSame(membershipService, ((ChannelCoordinator) next).getMembershipService());
    assertSame(membershipService, groupChannel.getMembershipService());
    assertSame(membershipService, ((GroupChannel) channelInterceptor).getMembershipService());
  }

  /**
   * Test {@link GroupChannel#addMembershipListener(MembershipListener)}.
   * <p>
   * Method under test: {@link GroupChannel#addMembershipListener(MembershipListener)}
   */
  @Test
  public void testAddMembershipListener() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    SimpleTcpCluster membershipListener = new SimpleTcpCluster();

    // Act
    groupChannel.addMembershipListener(membershipListener);

    // Assert
    List<MembershipListener> membershipListenerList = groupChannel.membershipListeners;
    assertEquals(1, membershipListenerList.size());
    assertSame(membershipListener, membershipListenerList.get(0));
  }

  /**
   * Test {@link GroupChannel#addChannelListener(ChannelListener)}.
   * <p>
   * Method under test: {@link GroupChannel#addChannelListener(ChannelListener)}
   */
  @Test
  public void testAddChannelListener() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    FarmWarDeployer channelListener = new FarmWarDeployer();

    // Act
    groupChannel.addChannelListener(channelListener);

    // Assert
    List<ChannelListener> channelListenerList = groupChannel.channelListeners;
    assertEquals(1, channelListenerList.size());
    assertSame(channelListener, channelListenerList.get(0));
  }

  /**
   * Test {@link GroupChannel#getInterceptors()}.
   * <p>
   * Method under test: {@link GroupChannel#getInterceptors()}
   */
  @Test
  public void testGetInterceptors() {
    // Arrange and Act
    Iterator<ChannelInterceptor> actualInterceptors = (new GroupChannel()).getInterceptors();

    // Assert
    assertTrue(actualInterceptors instanceof InterceptorIterator);
    assertFalse(actualInterceptors.hasNext());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GroupChannel#setHeartbeat(boolean)}
   *   <li>{@link GroupChannel#setHeartbeatSleeptime(long)}
   *   <li>{@link GroupChannel#setJmxDomain(String)}
   *   <li>{@link GroupChannel#setJmxEnabled(boolean)}
   *   <li>{@link GroupChannel#setJmxPrefix(String)}
   *   <li>{@link GroupChannel#setName(String)}
   *   <li>{@link GroupChannel#setOptionCheck(boolean)}
   *   <li>{@link GroupChannel#setUtilityExecutor(ScheduledExecutorService)}
   *   <li>{@link GroupChannel#postRegister(Boolean)}
   *   <li>{@link GroupChannel#preDeregister()}
   *   <li>{@link GroupChannel#getHeartbeat()}
   *   <li>{@link GroupChannel#getHeartbeatSleeptime()}
   *   <li>{@link GroupChannel#getJmxDomain()}
   *   <li>{@link GroupChannel#getJmxPrefix()}
   *   <li>{@link GroupChannel#getName()}
   *   <li>{@link GroupChannel#getOptionCheck()}
   *   <li>{@link GroupChannel#getUtilityExecutor()}
   *   <li>{@link GroupChannel#isJmxEnabled()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws Exception {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act
    groupChannel.setHeartbeat(true);
    groupChannel.setHeartbeatSleeptime(10L);
    groupChannel.setJmxDomain("Jmx Domain");
    groupChannel.setJmxEnabled(true);
    groupChannel.setJmxPrefix("Jmx Prefix");
    groupChannel.setName("Name");
    groupChannel.setOptionCheck(true);
    ScheduledThreadPoolExecutor utilityExecutor = new ScheduledThreadPoolExecutor(
        new java.util.concurrent.ScheduledThreadPoolExecutor(1));
    groupChannel.setUtilityExecutor(utilityExecutor);
    groupChannel.postRegister(true);
    groupChannel.preDeregister();
    boolean actualHeartbeat = groupChannel.getHeartbeat();
    long actualHeartbeatSleeptime = groupChannel.getHeartbeatSleeptime();
    String actualJmxDomain = groupChannel.getJmxDomain();
    String actualJmxPrefix = groupChannel.getJmxPrefix();
    String actualName = groupChannel.getName();
    boolean actualOptionCheck = groupChannel.getOptionCheck();
    ScheduledExecutorService actualUtilityExecutor = groupChannel.getUtilityExecutor();

    // Assert
    assertEquals("Jmx Domain", actualJmxDomain);
    assertEquals("Jmx Prefix", actualJmxPrefix);
    assertEquals("Name", actualName);
    assertEquals(10L, actualHeartbeatSleeptime);
    assertTrue(actualHeartbeat);
    assertTrue(actualOptionCheck);
    assertTrue(groupChannel.isJmxEnabled());
    assertSame(utilityExecutor, actualUtilityExecutor);
  }

  /**
   * Test {@link GroupChannel#preRegister(MBeanServer, ObjectName)}.
   * <ul>
   *   <li>When Instance is empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GroupChannel#preRegister(MBeanServer, ObjectName)}
   */
  @Test
  public void testPreRegister_whenInstanceIsEmptyString_thenReturnNull() throws Exception {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertNull(groupChannel.preRegister(null, ObjectName.getInstance("")));
  }
}
