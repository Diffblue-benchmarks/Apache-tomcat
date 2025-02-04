package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipService;
import org.apache.catalina.tribes.UniqueId;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.interceptors.NonBlockingCoordinator.CoordinationMessage;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.Membership;
import org.junit.Test;

public class NonBlockingCoordinatorDiffblueTest {
  /**
   * Test CoordinationMessage {@link CoordinationMessage#CoordinationMessage(Member, Member, Member[], UniqueId, byte[])}.
   * <p>
   * Method under test: {@link CoordinationMessage#CoordinationMessage(Member, Member, Member[], UniqueId, byte[])}
   */
  @Test
  public void testCoordinationMessageNewCoordinationMessage() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl leader = new MemberImpl();
    MemberImpl source = new MemberImpl();
    UniqueId id = new UniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    CoordinationMessage actualCoordinationMessage = new CoordinationMessage(leader, source,
        new Member[]{new MemberImpl()}, id, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    Member leader2 = actualCoordinationMessage.getLeader();
    assertTrue(leader2 instanceof MemberImpl);
    XByteBuffer buffer = actualCoordinationMessage.getBuffer();
    assertEquals(267, buffer.getLength());
    assertEquals(267, buffer.getBytes().length);
    byte[] expectedType = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedType, actualCoordinationMessage.getType());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualCoordinationMessage.type);
    assertArrayEquals(new byte[]{}, leader2.getCommand());
    assertArrayEquals(new byte[]{}, leader2.getDomain());
    assertArrayEquals(new byte[]{}, leader2.getHost());
    assertArrayEquals(new byte[]{}, leader2.getPayload());
    assertArrayEquals(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, actualCoordinationMessage.getId().getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, leader2.getUniqueId());
  }

  /**
   * Test CoordinationMessage {@link CoordinationMessage#CoordinationMessage(Member, Member, Member[], UniqueId, byte[])}.
   * <p>
   * Method under test: {@link CoordinationMessage#CoordinationMessage(Member, Member, Member[], UniqueId, byte[])}
   */
  @Test
  public void testCoordinationMessageNewCoordinationMessage2() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl leader = new MemberImpl();
    MemberImpl source = new MemberImpl();
    UniqueId id = new UniqueId(new byte[]{});

    // Act
    CoordinationMessage actualCoordinationMessage = new CoordinationMessage(leader, source,
        new Member[]{new MemberImpl()}, id, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    Member leader2 = actualCoordinationMessage.getLeader();
    assertTrue(leader2 instanceof MemberImpl);
    XByteBuffer buffer = actualCoordinationMessage.getBuffer();
    assertEquals(259, buffer.getLength());
    assertEquals(259, buffer.getBytes().length);
    byte[] expectedType = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedType, actualCoordinationMessage.getType());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualCoordinationMessage.type);
    assertArrayEquals(new byte[]{}, leader2.getCommand());
    assertArrayEquals(new byte[]{}, leader2.getDomain());
    assertArrayEquals(new byte[]{}, leader2.getHost());
    assertArrayEquals(new byte[]{}, leader2.getPayload());
    assertArrayEquals(new byte[]{}, actualCoordinationMessage.getId().getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, leader2.getUniqueId());
  }

  /**
   * Test new {@link NonBlockingCoordinator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NonBlockingCoordinator}
   */
  @Test
  public void testNewNonBlockingCoordinator() {
    // Arrange and Act
    NonBlockingCoordinator actualNonBlockingCoordinator = new NonBlockingCoordinator();

    // Assert
    assertNull(actualNonBlockingCoordinator.getChannel());
    assertNull(actualNonBlockingCoordinator.getNext());
    assertNull(actualNonBlockingCoordinator.getPrevious());
    assertNull(actualNonBlockingCoordinator.getCoordinator());
    assertNull(actualNonBlockingCoordinator.getViewId());
    assertNull(actualNonBlockingCoordinator.suggestedviewId);
    assertNull(actualNonBlockingCoordinator.membership);
    assertNull(actualNonBlockingCoordinator.suggestedView);
    assertNull(actualNonBlockingCoordinator.view);
    assertEquals(0, actualNonBlockingCoordinator.getOptionFlag());
    assertEquals(0, actualNonBlockingCoordinator.getView().length);
    assertEquals(15000L, actualNonBlockingCoordinator.waitForCoordMsgTimeout);
    assertEquals(65535, actualNonBlockingCoordinator.startsvc);
    assertFalse(actualNonBlockingCoordinator.isCoordinator());
    assertFalse(actualNonBlockingCoordinator.started);
  }

  /**
   * Test {@link NonBlockingCoordinator#alive(Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl(String, int, long)} with host is {@code 42} and port is {@code 8080} and aliveTime is {@code 15000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#alive(Member)}
   */
  @Test
  public void testAlive_whenMemberImplWithHostIs42AndPortIs8080AndAliveTimeIs15000() throws IOException {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.alive(new MemberImpl("42", 8080, 15000L)));
  }

  /**
   * Test {@link NonBlockingCoordinator#alive(Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl(String, int, long)} with host is {@code 42} and port is minus one and aliveTime is {@code 15000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#alive(Member)}
   */
  @Test
  public void testAlive_whenMemberImplWithHostIs42AndPortIsMinusOneAndAliveTimeIs15000() throws IOException {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.alive(new MemberImpl("42", -1, 15000L)));
  }

  /**
   * Test {@link NonBlockingCoordinator#alive(Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#alive(Member)}
   */
  @Test
  public void testAlive_whenMemberImpl_thenReturnFalse() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.alive(new MemberImpl()));
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAlive(Member, long)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl(String, int, long)} with host is {@code 42} and port is {@code 8080} and aliveTime is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAlive(Member, long)}
   */
  @Test
  public void testMemberAlive_whenMemberImplWithHostIs42AndPortIs8080AndAliveTimeIsFour() throws IOException {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.memberAlive(new MemberImpl("42", 8080, 4L), 1L));
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAlive(Member, long)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl(String, int, long)} with host is {@code 42} and port is minus one and aliveTime is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAlive(Member, long)}
   */
  @Test
  public void testMemberAlive_whenMemberImplWithHostIs42AndPortIsMinusOneAndAliveTimeIsFour() throws IOException {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.memberAlive(new MemberImpl("42", -1, 4L), 1L));
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAlive(Member, long)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAlive(Member, long)}
   */
  @Test
  public void testMemberAlive_whenMemberImpl_thenReturnFalse() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.memberAlive(new MemberImpl(), 1L));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>Given array of {@code byte} with minus one and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_givenArrayOfByteWithMinusOneAndOne() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{-1, 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()} and {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenArrayOfMemberWithMemberImplAndMemberImpl() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    MemberImpl memberImpl = new MemberImpl();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl, new MemberImpl()},
        new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()} and {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenArrayOfMemberWithMemberImplAndMemberImpl2() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    MemberImpl memberImpl = new MemberImpl();
    MemberImpl memberImpl2 = new MemberImpl();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl, memberImpl2, new MemberImpl()},
        new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenArrayOfMemberWithMemberImpl_thenReturnFalse() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(
        nonBlockingCoordinator.hasHigherPriority(new Member[]{new MemberImpl()}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenEmptyArrayOfMember_thenReturnFalse() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertFalse(nonBlockingCoordinator.hasHigherPriority(new Member[]{new MemberImpl()}, new Member[]{}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenEmptyArrayOfMember_thenReturnTrue() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertTrue(nonBlockingCoordinator.hasHigherPriority(new Member[]{}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenMemberImplHostIsArrayOfByteWithAAndOne() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertTrue(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()} UniqueId is array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenMemberImplUniqueIdIsArrayOfByteWithAAndOne() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()} UniqueId is array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenMemberImplUniqueIdIsArrayOfByteWithAAndOne2() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertTrue(nonBlockingCoordinator.hasHigherPriority(new Member[]{memberImpl}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NonBlockingCoordinator()).hasHigherPriority(null, null));
  }

  /**
   * Test {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#hasHigherPriority(Member[], Member[])}
   */
  @Test
  public void testHasHigherPriority_whenNull_thenReturnTrue() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act and Assert
    assertTrue(nonBlockingCoordinator.hasHigherPriority(null, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link NonBlockingCoordinator#getCoordinator()}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getCoordinator()}
   */
  @Test
  public void testGetCoordinator() {
    // Arrange, Act and Assert
    assertNull((new NonBlockingCoordinator()).getCoordinator());
  }

  /**
   * Test {@link NonBlockingCoordinator#getView()}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getView()}
   */
  @Test
  public void testGetView() {
    // Arrange, Act and Assert
    assertEquals(0, (new NonBlockingCoordinator()).getView().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NonBlockingCoordinator#halt()}
   *   <li>{@link NonBlockingCoordinator#release()}
   *   <li>{@link NonBlockingCoordinator#waitForRelease()}
   *   <li>{@link NonBlockingCoordinator#getViewId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act
    nonBlockingCoordinator.halt();
    nonBlockingCoordinator.release();
    nonBlockingCoordinator.waitForRelease();

    // Assert
    assertNull(nonBlockingCoordinator.getViewId());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember2() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    NonBlockingCoordinator previous = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    ChannelInterceptor previous2 = nonBlockingCoordinator.getPrevious();
    assertTrue(previous2 instanceof NonBlockingCoordinator);
    Member[] members = previous2.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous2.hasMembers());
    assertSame(member, members[0]);
    assertSame(previous.membership, ((NonBlockingCoordinator) previous2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember3() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    ChannelInterceptor previous2 = nonBlockingCoordinator.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    Member[] members = previous2.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous2.hasMembers());
    assertSame(member, members[0]);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember4() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new EncryptInterceptor());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember5() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 1, 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect2() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    NonBlockingCoordinator previous = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    ChannelInterceptor previous2 = nonBlockingCoordinator.getPrevious();
    assertTrue(previous2 instanceof NonBlockingCoordinator);
    Member[] members = previous2.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous2.hasMembers());
    assertSame(member, members[0]);
    assertSame(previous.membership, ((NonBlockingCoordinator) previous2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect3() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    ChannelInterceptor previous2 = nonBlockingCoordinator.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    Member[] members = previous2.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous2.hasMembers());
    assertSame(member, members[0]);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect4() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new EncryptInterceptor());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect5() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 1, 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect6() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect7() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());
    previous.addMembershipListener(new SimpleTcpCluster());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect_givenNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member, boolean)} with {@code member}, {@code elect}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Previous is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member, boolean)}
   */
  @Test
  public void testMemberAddedWithMemberElect_givenNonBlockingCoordinatorPreviousIsGroupChannel() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new GroupChannel());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member, true);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <ul>
   *   <li>Given array of {@code byte} with one and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember_givenArrayOfByteWithOneAndOne() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{1, 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addMembershipListener {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember_givenGroupChannelAddMembershipListenerSimpleTcpCluster() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addMembershipListener {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember_givenGroupChannelAddMembershipListenerSimpleTcpCluster2() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());
    previous.addMembershipListener(new SimpleTcpCluster());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember_givenNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#memberAdded(Member)} with {@code member}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Previous is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#memberAdded(Member)}
   */
  @Test
  public void testMemberAddedWithMember_givenNonBlockingCoordinatorPreviousIsGroupChannel() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setPrevious(new GroupChannel());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    nonBlockingCoordinator.memberAdded(member);

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(1, members.length);
    assertTrue(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#isCoordinator()}.
   * <p>
   * Method under test: {@link NonBlockingCoordinator#isCoordinator()}
   */
  @Test
  public void testIsCoordinator() {
    // Arrange, Act and Assert
    assertFalse((new NonBlockingCoordinator()).isCoordinator());
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new ChannelCoordinator());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next2 = nonBlockingCoordinator.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_givenTcpFailureDetectorNextIsGroupChannel() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new GroupChannel());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next2 = nonBlockingCoordinator.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenNonBlockingCoordinatorNextNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    DomainFilterInterceptor next2 = new DomainFilterInterceptor();
    next.setNext(next2);

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next3 = nonBlockingCoordinator.getNext();
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof DomainFilterInterceptor);
    assertTrue(next3 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((DomainFilterInterceptor) next4).membership);
    assertSame(next.membership, ((TcpFailureDetector) next3).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenNonBlockingCoordinatorNextNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new NonBlockingCoordinator());

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next2 = nonBlockingCoordinator.getNext();
    ChannelInterceptor next3 = next2.getNext();
    assertTrue(next3 instanceof NonBlockingCoordinator);
    assertTrue(next2 instanceof TcpFailureDetector);
    assertNull(((NonBlockingCoordinator) next3).membership);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenNonBlockingCoordinatorNextNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    TcpFailureDetector next2 = new TcpFailureDetector();
    next.setNext(next2);

    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next3 = nonBlockingCoordinator.getNext();
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof TcpFailureDetector);
    assertTrue(next3 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((TcpFailureDetector) next4).membership);
    assertSame(next.membership, ((TcpFailureDetector) next3).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenNonBlockingCoordinatorNextNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new NonBlockingCoordinator());

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert that nothing has changed
    assertTrue(nonBlockingCoordinator.getNext() instanceof NonBlockingCoordinator);
  }

  /**
   * Test {@link NonBlockingCoordinator#heartbeat()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenNonBlockingCoordinatorNextTcpFailureDetector() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    TcpFailureDetector next = new TcpFailureDetector();
    nonBlockingCoordinator.setNext(next);

    // Act
    nonBlockingCoordinator.heartbeat();

    // Assert
    ChannelInterceptor next2 = nonBlockingCoordinator.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link NonBlockingCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenNonBlockingCoordinator() {
    // Arrange, Act and Assert
    assertNull((new NonBlockingCoordinator()).getLocalMember(true));
  }

  /**
   * Test {@link NonBlockingCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenNonBlockingCoordinatorNextIsChannelCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(nonBlockingCoordinator.getLocalMember(true));
  }

  /**
   * Test {@link NonBlockingCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenNonBlockingCoordinatorNextIsDomainFilterInterceptor() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(nonBlockingCoordinator.getLocalMember(true));
  }

  /**
   * Test {@link NonBlockingCoordinator#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Next is {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenNonBlockingCoordinatorNextIsNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new NonBlockingCoordinator());

    // Act and Assert
    assertNull(nonBlockingCoordinator.getLocalMember(true));
  }

  /**
   * Test {@link NonBlockingCoordinator#setupMembership()}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#setupMembership()}
   */
  @Test
  public void testSetupMembership_givenNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();

    // Act
    nonBlockingCoordinator.setupMembership();

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(0, members.length);
    assertFalse(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertFalse(membership.hasMembers());
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#setupMembership()}.
   * <ul>
   *   <li>Given {@link NonBlockingCoordinator} (default constructor) Next is {@link NonBlockingCoordinator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#setupMembership()}
   */
  @Test
  public void testSetupMembership_givenNonBlockingCoordinatorNextIsNonBlockingCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new NonBlockingCoordinator());

    // Act
    nonBlockingCoordinator.setupMembership();

    // Assert
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(0, members.length);
    assertFalse(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertFalse(membership.hasMembers());
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#setupMembership()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#setupMembership()}
   */
  @Test
  public void testSetupMembership_thenNonBlockingCoordinatorNextChannelCoordinator() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new ChannelCoordinator());

    // Act
    nonBlockingCoordinator.setupMembership();

    // Assert
    ChannelInterceptor next = nonBlockingCoordinator.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    MembershipService membershipService = ((ChannelCoordinator) next).getMembershipService();
    assertTrue(membershipService instanceof McastService);
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(0, members.length);
    assertFalse(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertFalse(membership.hasMembers());
    assertSame(members, next.getMembers());
    assertSame(members, membershipService.getMembers());
    assertSame(members, membership.getMembers());
  }

  /**
   * Test {@link NonBlockingCoordinator#setupMembership()}.
   * <ul>
   *   <li>Then {@link NonBlockingCoordinator} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonBlockingCoordinator#setupMembership()}
   */
  @Test
  public void testSetupMembership_thenNonBlockingCoordinatorNextDomainFilterInterceptor() {
    // Arrange
    NonBlockingCoordinator nonBlockingCoordinator = new NonBlockingCoordinator();
    nonBlockingCoordinator.setNext(new DomainFilterInterceptor());

    // Act
    nonBlockingCoordinator.setupMembership();

    // Assert
    ChannelInterceptor next = nonBlockingCoordinator.getNext();
    assertTrue(next instanceof DomainFilterInterceptor);
    Member[] members = nonBlockingCoordinator.getMembers();
    assertEquals(0, members.length);
    assertFalse(nonBlockingCoordinator.hasMembers());
    Membership membership = nonBlockingCoordinator.membership;
    assertFalse(membership.hasMembers());
    assertSame(members, next.getMembers());
    assertSame(members, ((DomainFilterInterceptor) next).membership.getMembers());
    assertSame(members, membership.getMembers());
  }
}
