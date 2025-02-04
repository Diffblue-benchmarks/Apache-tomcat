package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipProvider;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class StaticMembershipServiceDiffblueTest {
  /**
   * Test new {@link StaticMembershipService} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StaticMembershipService}
   */
  @Test
  public void testNewStaticMembershipService() {
    // Arrange and Act
    StaticMembershipService actualStaticMembershipService = new StaticMembershipService();

    // Assert
    assertNull(actualStaticMembershipService.getChannel());
    assertNull(actualStaticMembershipService.listener);
    assertNull(actualStaticMembershipService.getMembershipProvider());
    assertEquals(0, actualStaticMembershipService.getMembers().length);
    assertEquals(0, actualStaticMembershipService.getMembersByName().length);
    assertEquals(1000L, actualStaticMembershipService.getPingInterval());
    assertEquals(3000L, actualStaticMembershipService.getRpcTimeout());
    Properties properties = actualStaticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertEquals(500, actualStaticMembershipService.getConnectTimeout());
    assertEquals(5000L, actualStaticMembershipService.getExpirationTime());
    assertFalse(actualStaticMembershipService.hasMembers());
    assertFalse(actualStaticMembershipService.getUseThread());
    assertTrue(actualStaticMembershipService.getStaticMembers().isEmpty());
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(new GroupChannel());
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(1);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt2() throws Exception {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("membershipName");

    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(channel);
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(1);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "membershipName-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt3() throws Exception {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("membershipName");

    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.addStaticMember(new StaticMember());
    staticMembershipService.setChannel(channel);
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(1);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "membershipName-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt4() throws Exception {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("type=Channel,channel=");

    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(channel);
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(1);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    Properties properties = staticMembershipService.getProperties();
    assertEquals(6, properties.size());
    assertEquals("type=Channel,channel=-StaticMembership", properties.get("membershipName"));
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
    byte[] expectedRpcId = "type=Channel,channel=-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt5() throws Exception {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName(",component=Membership");

    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(channel);
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(1);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    Properties properties = staticMembershipService.getProperties();
    assertEquals(6, properties.size());
    assertEquals(",component=Membership-StaticMembership", properties.get("membershipName"));
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
    byte[] expectedRpcId = ",component=Membership-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <ul>
   *   <li>Given {@link StaticMembershipService} (default constructor) addStaticMember {@code null}.</li>
   *   <li>When twelve.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt_givenStaticMembershipServiceAddStaticMemberNull_whenTwelve() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.addStaticMember(null);
    staticMembershipService.setChannel(new GroupChannel());
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(12);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <ul>
   *   <li>Given {@link StaticMembershipService} (default constructor) addStaticMember {@link StaticMember#StaticMember()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt_givenStaticMembershipServiceAddStaticMemberStaticMember() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.addStaticMember(new StaticMember());
    staticMembershipService.setChannel(new GroupChannel());
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(12);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link StaticMembershipService} (default constructor) MembershipProvider {@link StaticMembershipProvider#useThread}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt_thenStaticMembershipServiceMembershipProviderUseThread() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setUseThread(true);
    staticMembershipService.addStaticMember(new StaticMember());
    staticMembershipService.setChannel(new GroupChannel());
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(12);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    assertTrue(((StaticMembershipProvider) membershipProvider).useThread);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#start(int)} with {@code int}.
   * <ul>
   *   <li>When twelve.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#start(int)}
   */
  @Test
  public void testStartWithInt_whenTwelve() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(new GroupChannel());
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    staticMembershipService.start(12);

    // Assert
    MembershipProvider membershipProvider = staticMembershipService.getMembershipProvider();
    Member member = ((StaticMembershipProvider) membershipProvider).membership.local;
    assertTrue(member instanceof StaticMember);
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, ((StaticMembershipProvider) membershipProvider).rpcChannel.getRpcId());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#buildMembershipProvider()}.
   * <ul>
   *   <li>Then {@link StaticMembershipProvider#channel} return {@link GroupChannel}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#buildMembershipProvider()}
   */
  @Test
  public void testBuildMembershipProvider_thenChannelReturnGroupChannel() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    StaticMember member = new StaticMember();
    staticMembershipService.setLocalMember(member);
    staticMembershipService.setChannel(new GroupChannel());

    // Act
    StaticMembershipProvider actualBuildMembershipProviderResult = staticMembershipService.buildMembershipProvider();

    // Assert
    Channel channel = actualBuildMembershipProviderResult.channel;
    assertTrue(channel instanceof GroupChannel);
    Member member2 = actualBuildMembershipProviderResult.membership.local;
    assertTrue(member2 instanceof StaticMember);
    assertTrue(((GroupChannel) channel).getChannelReceiver() instanceof NioReceiver);
    assertSame(member, member2);
    byte[] expectedRpcId = "null-StaticMembership".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, actualBuildMembershipProviderResult.rpcChannel.getRpcId());
  }

  /**
   * Test {@link StaticMembershipService#buildMembershipProvider()}.
   * <ul>
   *   <li>Then {@link MembershipProviderBase#membershipListener} return {@link StaticMembershipService}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#buildMembershipProvider()}
   */
  @Test
  public void testBuildMembershipProvider_thenMembershipListenerReturnStaticMembershipService() throws Exception {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setChannel(new GroupChannel());

    // Act
    StaticMembershipProvider actualBuildMembershipProviderResult = staticMembershipService.buildMembershipProvider();

    // Assert
    assertTrue(actualBuildMembershipProviderResult.membershipListener instanceof StaticMembershipService);
    assertTrue(actualBuildMembershipProviderResult.service instanceof StaticMembershipService);
    assertNull(actualBuildMembershipProviderResult.executor);
    assertNull(actualBuildMembershipProviderResult.thread);
    assertEquals(0, actualBuildMembershipProviderResult.getMembers().length);
    assertEquals(0, actualBuildMembershipProviderResult.startLevel);
    assertEquals(1000L, actualBuildMembershipProviderResult.pingInterval);
    assertEquals(3000L, actualBuildMembershipProviderResult.rpcTimeout);
    assertEquals(500, actualBuildMembershipProviderResult.connectTimeout);
    assertEquals(5000L, actualBuildMembershipProviderResult.expirationTime);
    assertEquals(8, actualBuildMembershipProviderResult.sendOptions);
    assertFalse(actualBuildMembershipProviderResult.hasMembers());
    assertFalse(actualBuildMembershipProviderResult.useThread);
    assertTrue(actualBuildMembershipProviderResult.staticMembers.isEmpty());
    assertTrue(actualBuildMembershipProviderResult.running);
    Channel expectedChannel = actualBuildMembershipProviderResult.channel;
    assertSame(expectedChannel, staticMembershipService.getChannel());
    ArrayList<StaticMember> expectedStaticMembers = actualBuildMembershipProviderResult.staticMembers;
    assertSame(expectedStaticMembers, staticMembershipService.getStaticMembers());
  }

  /**
   * Test {@link StaticMembershipService#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link StaticMembershipService} (default constructor).</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenStaticMembershipService_whenTrue_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StaticMembershipService()).getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipService#getLocalMember(boolean)}.
   * <ul>
   *   <li>Then return {@link StaticMember}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_thenReturnStaticMember() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    Member actualLocalMember = staticMembershipService.getLocalMember(true);

    // Assert
    assertTrue(actualLocalMember instanceof StaticMember);
    assertArrayEquals(new byte[]{}, actualLocalMember.getCommand());
    assertArrayEquals(new byte[]{}, actualLocalMember.getDomain());
    assertArrayEquals(new byte[]{}, actualLocalMember.getHost());
    assertArrayEquals(new byte[]{}, actualLocalMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualLocalMember.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#getLocalMember(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@link StaticMember}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_whenFalse_thenReturnStaticMember() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setLocalMember(new StaticMember());

    // Act
    Member actualLocalMember = staticMembershipService.getLocalMember(false);

    // Assert
    assertTrue(actualLocalMember instanceof StaticMember);
    assertArrayEquals(new byte[]{}, actualLocalMember.getCommand());
    assertArrayEquals(new byte[]{}, actualLocalMember.getDomain());
    assertArrayEquals(new byte[]{}, actualLocalMember.getHost());
    assertArrayEquals(new byte[]{}, actualLocalMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualLocalMember.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}.
   * <ul>
   *   <li>Then {@link StaticMembershipService} (default constructor) Properties size is seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}
   */
  @Test
  public void testSetLocalMemberProperties_thenStaticMembershipServicePropertiesSizeIsSeven() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setLocalMember(new StaticMember());
    staticMembershipService.addStaticMember(new StaticMember());

    // Act
    staticMembershipService.setLocalMemberProperties("42", 8080, 8080, 8080);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(7, properties.size());
    assertEquals("42", properties.get("tcpListenHost"));
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}
   */
  @Test
  public void testSetLocalMemberProperties_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new StaticMembershipService()).setLocalMemberProperties("localhost", 8080, 8080, 8080));
  }

  /**
   * Test {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#setLocalMemberProperties(String, int, int, int)}
   */
  @Test
  public void testSetLocalMemberProperties_when42_thenThrowIllegalStateException() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.addStaticMember(new StaticMember());

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> staticMembershipService.setLocalMemberProperties("42", 8080, 8080, 8080));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StaticMembershipService#setDomain(byte[])}
   *   <li>{@link StaticMembershipService#setPayload(byte[])}
   *   <li>{@link StaticMembershipService#getMembershipProvider()}
   *   <li>{@link StaticMembershipService#getStaticMembers()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setDomain("AXAXAXAX".getBytes("UTF-8"));
    staticMembershipService.setPayload("AXAXAXAX".getBytes("UTF-8"));
    MembershipProvider actualMembershipProvider = staticMembershipService.getMembershipProvider();

    // Assert
    assertNull(actualMembershipProvider);
    assertTrue(staticMembershipService.getStaticMembers().isEmpty());
  }

  /**
   * Test {@link StaticMembershipService#addStaticMember(StaticMember)}.
   * <p>
   * Method under test: {@link StaticMembershipService#addStaticMember(StaticMember)}
   */
  @Test
  public void testAddStaticMember() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    StaticMember member = new StaticMember();

    // Act
    staticMembershipService.addStaticMember(member);

    // Assert
    ArrayList<StaticMember> staticMembers = staticMembershipService.getStaticMembers();
    assertEquals(1, staticMembers.size());
    assertSame(member, staticMembers.get(0));
  }

  /**
   * Test {@link StaticMembershipService#setLocalMember(StaticMember)}.
   * <ul>
   *   <li>When {@link StaticMember#StaticMember()}.</li>
   *   <li>Then {@link StaticMember#StaticMember()} Local.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#setLocalMember(StaticMember)}
   */
  @Test
  public void testSetLocalMember_whenStaticMember_thenStaticMemberLocal() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    StaticMember member = new StaticMember();

    // Act
    staticMembershipService.setLocalMember(member);

    // Assert
    assertTrue(member.isLocal());
  }

  /**
   * Test {@link StaticMembershipService#getExpirationTime()}.
   * <p>
   * Method under test: {@link StaticMembershipService#getExpirationTime()}
   */
  @Test
  public void testGetExpirationTime() {
    // Arrange, Act and Assert
    assertEquals(5000L, (new StaticMembershipService()).getExpirationTime());
  }

  /**
   * Test {@link StaticMembershipService#setExpirationTime(long)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setExpirationTime(long)}
   */
  @Test
  public void testSetExpirationTime() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setExpirationTime(1L);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertEquals("1", properties.get("expirationTime"));
    assertEquals(1L, staticMembershipService.getExpirationTime());
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#getConnectTimeout()}.
   * <p>
   * Method under test: {@link StaticMembershipService#getConnectTimeout()}
   */
  @Test
  public void testGetConnectTimeout() {
    // Arrange, Act and Assert
    assertEquals(500, (new StaticMembershipService()).getConnectTimeout());
  }

  /**
   * Test {@link StaticMembershipService#setConnectTimeout(int)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setConnectTimeout(int)}
   */
  @Test
  public void testSetConnectTimeout() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setConnectTimeout(10);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertEquals("10", properties.get("connectTimeout"));
    assertEquals(10, staticMembershipService.getConnectTimeout());
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#getRpcTimeout()}.
   * <p>
   * Method under test: {@link StaticMembershipService#getRpcTimeout()}
   */
  @Test
  public void testGetRpcTimeout() {
    // Arrange, Act and Assert
    assertEquals(3000L, (new StaticMembershipService()).getRpcTimeout());
  }

  /**
   * Test {@link StaticMembershipService#setRpcTimeout(long)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setRpcTimeout(long)}
   */
  @Test
  public void testSetRpcTimeout() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setRpcTimeout(1L);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertEquals("1", properties.get("rpcTimeout"));
    assertEquals(1L, staticMembershipService.getRpcTimeout());
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#getUseThread()}.
   * <ul>
   *   <li>Given {@link StaticMembershipService} (default constructor) UseThread is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#getUseThread()}
   */
  @Test
  public void testGetUseThread_givenStaticMembershipServiceUseThreadIsTrue_thenReturnTrue() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    staticMembershipService.setUseThread(true);

    // Act and Assert
    assertTrue(staticMembershipService.getUseThread());
  }

  /**
   * Test {@link StaticMembershipService#getUseThread()}.
   * <ul>
   *   <li>Given {@link StaticMembershipService} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipService#getUseThread()}
   */
  @Test
  public void testGetUseThread_givenStaticMembershipService_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StaticMembershipService()).getUseThread());
  }

  /**
   * Test {@link StaticMembershipService#setUseThread(boolean)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setUseThread(boolean)}
   */
  @Test
  public void testSetUseThread() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setUseThread(true);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("pingInterval"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(staticMembershipService.getUseThread());
    String expectedString = Boolean.TRUE.toString();
    assertEquals(expectedString, properties.get("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#getPingInterval()}.
   * <p>
   * Method under test: {@link StaticMembershipService#getPingInterval()}
   */
  @Test
  public void testGetPingInterval() {
    // Arrange, Act and Assert
    assertEquals(1000L, (new StaticMembershipService()).getPingInterval());
  }

  /**
   * Test {@link StaticMembershipService#setPingInterval(long)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setPingInterval(long)}
   */
  @Test
  public void testSetPingInterval() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();

    // Act
    staticMembershipService.setPingInterval(42L);

    // Assert
    Properties properties = staticMembershipService.getProperties();
    assertEquals(5, properties.size());
    assertEquals("42", properties.get("pingInterval"));
    assertEquals(42L, staticMembershipService.getPingInterval());
    assertTrue(properties.containsKey("connectTimeout"));
    assertTrue(properties.containsKey("expirationTime"));
    assertTrue(properties.containsKey("rpcTimeout"));
    assertTrue(properties.containsKey("useThread"));
  }

  /**
   * Test {@link StaticMembershipService#setProperties(Properties)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setProperties(Properties)}
   */
  @Test
  public void testSetProperties() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    Properties properties = new Properties();

    // Act
    staticMembershipService.setProperties(properties);

    // Assert
    assertEquals(5, properties.size());
    assertEquals("1000", properties.get("pingInterval"));
    assertEquals("3000", properties.get("rpcTimeout"));
    assertEquals("500", properties.get("connectTimeout"));
    assertEquals("5000", properties.get("expirationTime"));
    String expectedString = Boolean.FALSE.toString();
    assertEquals(expectedString, properties.get("useThread"));
    assertSame(properties, staticMembershipService.getProperties());
  }

  /**
   * Test {@link StaticMembershipService#setDefaults(Properties)}.
   * <p>
   * Method under test: {@link StaticMembershipService#setDefaults(Properties)}
   */
  @Test
  public void testSetDefaults() {
    // Arrange
    StaticMembershipService staticMembershipService = new StaticMembershipService();
    Properties properties = new Properties();

    // Act
    staticMembershipService.setDefaults(properties);

    // Assert
    assertEquals(5, properties.size());
    assertEquals("1000", properties.get("pingInterval"));
    assertEquals("3000", properties.get("rpcTimeout"));
    assertEquals("500", properties.get("connectTimeout"));
    assertEquals("5000", properties.get("expirationTime"));
    assertEquals(properties, staticMembershipService.getProperties());
    String expectedString = Boolean.FALSE.toString();
    assertEquals(expectedString, properties.get("useThread"));
  }
}
