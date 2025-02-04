package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class StaticMembershipInterceptorDiffblueTest {
  /**
   * Test new {@link StaticMembershipInterceptor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StaticMembershipInterceptor}
   */
  @Test
  public void testNewStaticMembershipInterceptor() {
    // Arrange and Act
    StaticMembershipInterceptor actualStaticMembershipInterceptor = new StaticMembershipInterceptor();

    // Assert
    assertNull(actualStaticMembershipInterceptor.getChannel());
    assertNull(actualStaticMembershipInterceptor.getNext());
    assertNull(actualStaticMembershipInterceptor.getPrevious());
    assertEquals(0, actualStaticMembershipInterceptor.getOptionFlag());
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_givenMemberImplHostIsAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);
    MemberImpl member2 = new MemberImpl();

    // Act
    staticMembershipInterceptor.addStaticMember(member2);

    // Assert
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(2, memberList.size());
    Member getResult = memberList.get(0);
    assertTrue(getResult instanceof MemberImpl);
    assertTrue(staticMembershipInterceptor.hasMembers());
    assertSame(member, getResult);
    assertSame(member2, memberList.get(1));
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} UniqueId is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_givenMemberImplUniqueIdIsAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setUniqueId("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);
    MemberImpl member2 = new MemberImpl();

    // Act
    staticMembershipInterceptor.addStaticMember(member2);

    // Assert
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(2, memberList.size());
    Member getResult = memberList.get(0);
    assertTrue(getResult instanceof MemberImpl);
    assertTrue(staticMembershipInterceptor.hasMembers());
    assertSame(member, getResult);
    assertSame(member2, memberList.get(1));
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_givenStaticMembershipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    MemberImpl member = new MemberImpl();

    // Act
    staticMembershipInterceptor.addStaticMember(member);

    // Assert
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(1, memberList.size());
    assertTrue(staticMembershipInterceptor.hasMembers());
    assertSame(member, memberList.get(0));
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) {@link StaticMembershipInterceptor#members} first is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_thenStaticMembershipInterceptorMembersFirstIsNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);
    MemberImpl member = new MemberImpl();

    // Act
    staticMembershipInterceptor.addStaticMember(member);

    // Assert
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(2, memberList.size());
    assertNull(memberList.get(0));
    assertTrue(staticMembershipInterceptor.hasMembers());
    assertSame(member, memberList.get(1));
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) {@link StaticMembershipInterceptor#members} second {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_thenStaticMembershipInterceptorMembersSecondMemberImpl() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);
    MemberImpl member = new MemberImpl();
    staticMembershipInterceptor.addStaticMember(member);

    // Act
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Assert that nothing has changed
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(2, memberList.size());
    Member getResult = memberList.get(1);
    assertTrue(getResult instanceof MemberImpl);
    assertSame(member, getResult);
  }

  /**
   * Test {@link StaticMembershipInterceptor#addStaticMember(Member)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) {@link StaticMembershipInterceptor#members} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#addStaticMember(Member)}
   */
  @Test
  public void testAddStaticMember_thenStaticMembershipInterceptorMembersSizeIsOne() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    MemberImpl member = new MemberImpl();
    staticMembershipInterceptor.addStaticMember(member);

    // Act
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Assert that nothing has changed
    ArrayList<Member> memberList = staticMembershipInterceptor.members;
    assertEquals(1, memberList.size());
    Member getResult = memberList.get(0);
    assertTrue(getResult instanceof MemberImpl);
    assertTrue(staticMembershipInterceptor.hasMembers());
    assertSame(member, getResult);
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_givenMemberImplHostIsAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert that nothing has changed
    assertEquals(1, staticMembershipInterceptor.members.size());
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} UniqueId is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_givenMemberImplUniqueIdIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setUniqueId("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert that nothing has changed
    assertEquals(1, staticMembershipInterceptor.members.size());
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_givenStaticMembershipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert that nothing has changed
    assertFalse(staticMembershipInterceptor.hasMembers());
    assertTrue(staticMembershipInterceptor.members.isEmpty());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) addStaticMember {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_givenStaticMembershipInterceptorAddStaticMemberNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert that nothing has changed
    assertEquals(1, staticMembershipInterceptor.members.size());
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) addStaticMember {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_givenStaticMembershipInterceptorAddStaticMemberNull2() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert
    assertEquals(1, staticMembershipInterceptor.members.size());
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Members is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_thenStaticMembershipInterceptorMembersIsNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    staticMembershipInterceptor.removeStaticMember(new MemberImpl());

    // Assert
    assertNull(staticMembershipInterceptor.getMembers());
    assertFalse(staticMembershipInterceptor.hasMembers());
    assertTrue(staticMembershipInterceptor.members.isEmpty());
  }

  /**
   * Test {@link StaticMembershipInterceptor#removeStaticMember(Member)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Members is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#removeStaticMember(Member)}
   */
  @Test
  public void testRemoveStaticMember_whenNull_thenStaticMembershipInterceptorMembersIsNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);

    // Act
    staticMembershipInterceptor.removeStaticMember(null);

    // Assert
    assertNull(staticMembershipInterceptor.getMembers());
    assertFalse(staticMembershipInterceptor.hasMembers());
    assertTrue(staticMembershipInterceptor.members.isEmpty());
  }

  /**
   * Test {@link StaticMembershipInterceptor#setLocalMember(Member)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) {@link StaticMembershipInterceptor#localMember} {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#setLocalMember(Member)}
   */
  @Test
  public void testSetLocalMember_thenStaticMembershipInterceptorLocalMemberMemberImpl() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    MemberImpl member = new MemberImpl();

    // Act
    staticMembershipInterceptor.setLocalMember(member);

    // Assert
    Member member2 = staticMembershipInterceptor.localMember;
    assertTrue(member2 instanceof MemberImpl);
    assertEquals("tcp://{}:0", member2.getName());
    assertEquals("{}", ((MemberImpl) member2).getHostname());
    assertEquals(-1, member2.getSecurePort());
    assertEquals(-1, member2.getUdpPort());
    assertEquals(0, member2.getPort());
    assertEquals(0, ((MemberImpl) member2).getMsgCount());
    assertEquals(0L, member2.getMemberAliveTime());
    assertEquals(0L, ((MemberImpl) member2).getServiceStartTime());
    assertEquals(73, member2.getDataLength());
    assertEquals(73, ((MemberImpl) member2).getData().length);
    assertTrue(member2.isLocal());
    assertTrue(member.isLocal());
  }

  /**
   * Test {@link StaticMembershipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Previous {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenStaticMembershipInterceptorPreviousGzipInterceptor()
      throws UnsupportedEncodingException {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setPrevious(new GzipInterceptor());
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");

    // Act
    staticMembershipInterceptor.messageReceived(new ChannelData(uniqueId, new XByteBuffer(3, true), 10L));

    // Assert
    ChannelInterceptor previous = staticMembershipInterceptor.getPrevious();
    assertTrue(previous instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) previous).getCount());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new DomainFilterInterceptor());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsStaticMembershipInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new StaticMembershipInterceptor());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) LocalMember is {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptorLocalMemberIsMemberImpl() {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setLocalMember(new MemberImpl());

    DomainFilterInterceptor next2 = new DomainFilterInterceptor();
    next2.setNext(next);

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next2);

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptorNextIsChannelCoordinator() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptorNextIsEncryptInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptorNextIsStaticMembershipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new StaticMembershipInterceptor());

    // Act and Assert
    assertFalse(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenStaticMembershipInterceptor_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StaticMembershipInterceptor()).hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_thenReturnTrue() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act and Assert
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#hasMembers()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_thenReturnTrue2() {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.addStaticMember(new MemberImpl());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertTrue(staticMembershipInterceptor.hasMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member = actualMembers[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMembers.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptorNextIsGroupChannel() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new GroupChannel());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member = actualMembers[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMembers.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenStaticMembershipInterceptorNextIsChannelCoordinator() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member = actualMembers[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMembers.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenStaticMembershipInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new DomainFilterInterceptor());
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member = actualMembers[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMembers.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenStaticMembershipInterceptorNextIsGroupChannel() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new GroupChannel());
    staticMembershipInterceptor.addStaticMember(new MemberImpl());

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member = actualMembers[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMembers.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenStaticMembershipInterceptor_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StaticMembershipInterceptor()).getMembers());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Then return array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_thenReturnArrayLengthIsTwo() {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setNext(new ChannelCoordinator());
    next.addStaticMember(new MemberImpl());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);
    MemberImpl member = new MemberImpl();
    staticMembershipInterceptor.addStaticMember(member);

    // Act
    Member[] actualMembers = staticMembershipInterceptor.getMembers();

    // Assert
    Member member2 = actualMembers[0];
    assertTrue(member2 instanceof MemberImpl);
    assertEquals(2, actualMembers.length);
    assertSame(member, actualMembers[1]);
    assertArrayEquals(new byte[]{}, member2.getCommand());
    assertArrayEquals(new byte[]{}, member2.getDomain());
    assertArrayEquals(new byte[]{}, member2.getHost());
    assertArrayEquals(new byte[]{}, member2.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member2.getUniqueId());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMembers()}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_thenReturnArrayLengthIsZero() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertEquals(0, staticMembershipInterceptor.getMembers().length);
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new DomainFilterInterceptor());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsStaticMembershipInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new StaticMembershipInterceptor());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenMemberImplHostIsAxaxaxaxBytesIsUtf8_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} UniqueId is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenMemberImplUniqueIdIsAxaxaxaxBytesIsUtf8_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setUniqueId("AXAXAXAX".getBytes("UTF-8"));

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(member);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) addStaticMember {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorAddStaticMemberNull_thenReturnNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) addStaticMember {@code null}.</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorAddStaticMemberNull_whenNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.addStaticMember(null);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(null));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) LocalMember is {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorLocalMemberIsMemberImpl() {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setLocalMember(new MemberImpl());

    DomainFilterInterceptor next2 = new DomainFilterInterceptor();
    next2.setNext(next);

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next2);

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorNextIsChannelCoordinator() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorNextIsEncryptInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptorNextIsStaticMembershipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new StaticMembershipInterceptor());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenStaticMembershipInterceptor_whenMemberImpl_thenReturnNull() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();

    // Act and Assert
    assertNull(staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Then return {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_thenReturnMemberImpl() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    MemberImpl member = new MemberImpl();
    staticMembershipInterceptor.addStaticMember(member);

    // Act and Assert
    assertSame(member, staticMembershipInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getLocalMember(boolean)}.
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new StaticMembershipInterceptor());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenStaticMembershipInterceptorNextIsChannelCoordinator() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenStaticMembershipInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(staticMembershipInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenStaticMembershipInterceptor_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StaticMembershipInterceptor()).getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Then return {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_thenReturnMemberImpl() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    MemberImpl member = new MemberImpl();
    staticMembershipInterceptor.setLocalMember(member);

    // Act and Assert
    assertSame(member, staticMembershipInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link StaticMembershipInterceptor#start(int)}.
   * <ul>
   *   <li>Given {@link EncryptInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#start(int)}
   */
  @Test
  public void testStart_givenEncryptInterceptorPreviousIsChannelCoordinator() throws ChannelException {
    // Arrange
    EncryptInterceptor next = new EncryptInterceptor();
    next.setPrevious(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(next);
    staticMembershipInterceptor.setChannel(new GroupChannel());

    // Act
    staticMembershipInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(staticMembershipInterceptor.getNext() instanceof EncryptInterceptor);
  }

  /**
   * Test {@link StaticMembershipInterceptor#start(int)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#start(int)}
   */
  @Test
  public void testStart_thenStaticMembershipInterceptorNextDomainFilterInterceptor() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new DomainFilterInterceptor());
    staticMembershipInterceptor.setChannel(new GroupChannel());

    // Act
    staticMembershipInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(staticMembershipInterceptor.getNext() instanceof DomainFilterInterceptor);
  }

  /**
   * Test {@link StaticMembershipInterceptor#start(int)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link EncryptInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#start(int)}
   */
  @Test
  public void testStart_thenStaticMembershipInterceptorNextEncryptInterceptor() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new EncryptInterceptor());
    staticMembershipInterceptor.setChannel(new GroupChannel());

    // Act
    staticMembershipInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(staticMembershipInterceptor.getNext() instanceof EncryptInterceptor);
  }

  /**
   * Test {@link StaticMembershipInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenDomainFilterInterceptorNextIsChannelCoordinator() throws ChannelException {
    // Arrange
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    previous.setNext(new ChannelCoordinator());
    previous.setPrevious(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());
    staticMembershipInterceptor.setPrevious(previous);

    // Act
    staticMembershipInterceptor.stop(1);

    // Assert
    ChannelInterceptor previous2 = staticMembershipInterceptor.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link StaticMembershipInterceptor#stop(int)}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Previous {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#stop(int)}
   */
  @Test
  public void testStop_thenStaticMembershipInterceptorPreviousDomainFilterInterceptor() throws ChannelException {
    // Arrange
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    previous.setPrevious(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new ChannelCoordinator());
    staticMembershipInterceptor.setPrevious(previous);

    // Act
    staticMembershipInterceptor.stop(1);

    // Assert
    ChannelInterceptor previous2 = staticMembershipInterceptor.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link StaticMembershipInterceptor#sendLocalMember(Member[])}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#sendLocalMember(Member[])}
   */
  @Test
  public void testSendLocalMember_thenStaticMembershipInterceptorNextGzipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new GzipInterceptor());

    // Act
    staticMembershipInterceptor.sendLocalMember(new Member[]{new MemberImpl()});

    // Assert
    ChannelInterceptor next = staticMembershipInterceptor.getNext();
    assertTrue(next instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) next).getCount());
  }

  /**
   * Test {@link StaticMembershipInterceptor#sendLocalMember(Member[])}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link ThroughputInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#sendLocalMember(Member[])}
   */
  @Test
  public void testSendLocalMember_thenStaticMembershipInterceptorNextThroughputInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setLocalMember(new MemberImpl());
    staticMembershipInterceptor.setNext(new ThroughputInterceptor());

    // Act
    staticMembershipInterceptor.sendLocalMember(new Member[]{new MemberImpl()});

    // Assert
    ChannelInterceptor next = staticMembershipInterceptor.getNext();
    assertTrue(next instanceof ThroughputInterceptor);
    assertEquals(1.0d, ((ThroughputInterceptor) next).getLastCnt(), 0.0);
    assertEquals(1.5926361083984375E-4d, ((ThroughputInterceptor) next).getMbAppTx(), 0.0);
    assertEquals(1.5926361083984375E-4d, ((ThroughputInterceptor) next).getMbTx(), 0.0);
  }

  /**
   * Test {@link StaticMembershipInterceptor#sendShutdown(Member[])}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#sendShutdown(Member[])}
   */
  @Test
  public void testSendShutdown_thenStaticMembershipInterceptorNextGzipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new GzipInterceptor());

    // Act
    staticMembershipInterceptor.sendShutdown(new Member[]{new MemberImpl()});

    // Assert
    ChannelInterceptor next = staticMembershipInterceptor.getNext();
    assertTrue(next instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) next).getCount());
  }

  /**
   * Test {@link StaticMembershipInterceptor#sendShutdown(Member[])}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link ThroughputInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#sendShutdown(Member[])}
   */
  @Test
  public void testSendShutdown_thenStaticMembershipInterceptorNextThroughputInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setLocalMember(new MemberImpl());
    staticMembershipInterceptor.setNext(new ThroughputInterceptor());

    // Act
    staticMembershipInterceptor.sendShutdown(new Member[]{new MemberImpl()});

    // Assert
    ChannelInterceptor next = staticMembershipInterceptor.getNext();
    assertTrue(next instanceof ThroughputInterceptor);
    assertEquals(1.0d, ((ThroughputInterceptor) next).getLastCnt(), 0.0);
    assertEquals(1.5544891357421875E-4d, ((ThroughputInterceptor) next).getMbAppTx(), 0.0);
    assertEquals(1.5544891357421875E-4d, ((ThroughputInterceptor) next).getMbTx(), 0.0);
  }

  /**
   * Test {@link StaticMembershipInterceptor#getfirstInterceptor()}.
   * <ul>
   *   <li>Then return {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getfirstInterceptor()}
   */
  @Test
  public void testGetfirstInterceptor_thenReturnChannelCoordinator() {
    // Arrange
    ChannelCoordinator previous = new ChannelCoordinator();
    previous.setPrevious(new ChannelCoordinator());

    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setPrevious(previous);

    // Act and Assert
    assertSame(previous, staticMembershipInterceptor.getfirstInterceptor());
  }

  /**
   * Test {@link StaticMembershipInterceptor#getfirstInterceptor()}.
   * <ul>
   *   <li>Then return {@link StaticMembershipInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#getfirstInterceptor()}
   */
  @Test
  public void testGetfirstInterceptor_thenReturnStaticMembershipInterceptor() {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setPrevious(new ChannelCoordinator());

    // Act and Assert
    assertSame(staticMembershipInterceptor, staticMembershipInterceptor.getfirstInterceptor());
  }

  /**
   * Test {@link StaticMembershipInterceptor#sendMemberMessage(Member[], byte[])}.
   * <ul>
   *   <li>Then {@link StaticMembershipInterceptor} (default constructor) Next {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipInterceptor#sendMemberMessage(Member[], byte[])}
   */
  @Test
  public void testSendMemberMessage_thenStaticMembershipInterceptorNextGzipInterceptor() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor staticMembershipInterceptor = new StaticMembershipInterceptor();
    staticMembershipInterceptor.setNext(new GzipInterceptor());

    // Act
    staticMembershipInterceptor.sendMemberMessage(new Member[]{new MemberImpl()},
        new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    ChannelInterceptor next = staticMembershipInterceptor.getNext();
    assertTrue(next instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) next).getCount());
  }
}
