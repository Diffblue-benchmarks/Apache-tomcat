package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipService;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.Membership;
import org.junit.Test;

public class DomainFilterInterceptorDiffblueTest {
  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    Member[] members = domainFilterInterceptor.getMembers();
    assertEquals(1, members.length);
    assertTrue(domainFilterInterceptor.hasMembers());
    assertTrue(domainFilterInterceptor.membership.hasMembers());
    assertSame(member, members[0]);
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addMembershipListener {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenGroupChannelAddMembershipListenerSimpleTcpCluster() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());

    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    ChannelInterceptor previous2 = domainFilterInterceptor.getPrevious();
    ChannelInterceptor next = previous2.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    assertTrue(previous2 instanceof GroupChannel);
    MembershipService membershipService = ((GroupChannel) previous2).getMembershipService();
    assertTrue(membershipService instanceof McastService);
    Member[] members = previous2.getMembers();
    assertEquals(0, members.length);
    Member[] members2 = domainFilterInterceptor.getMembers();
    assertEquals(1, members2.length);
    assertTrue(domainFilterInterceptor.hasMembers());
    assertTrue(domainFilterInterceptor.membership.hasMembers());
    assertSame(member, members2[0]);
    assertSame(members, next.getMembers());
    assertSame(members, membershipService.getMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addMembershipListener {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenGroupChannelAddMembershipListenerSimpleTcpCluster2() {
    // Arrange
    GroupChannel previous = new GroupChannel();
    previous.addMembershipListener(new SimpleTcpCluster());
    previous.addMembershipListener(new SimpleTcpCluster());

    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    ChannelInterceptor previous2 = domainFilterInterceptor.getPrevious();
    ChannelInterceptor next = previous2.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    assertTrue(previous2 instanceof GroupChannel);
    MembershipService membershipService = ((GroupChannel) previous2).getMembershipService();
    assertTrue(membershipService instanceof McastService);
    Member[] members = previous2.getMembers();
    assertEquals(0, members.length);
    Member[] members2 = domainFilterInterceptor.getMembers();
    assertEquals(1, members2.length);
    assertTrue(domainFilterInterceptor.hasMembers());
    assertTrue(domainFilterInterceptor.membership.hasMembers());
    assertSame(member, members2[0]);
    assertSame(members, next.getMembers());
    assertSame(members, membershipService.getMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Previous {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenDomainFilterInterceptorPreviousChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(new ChannelCoordinator());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    ChannelInterceptor previous = domainFilterInterceptor.getPrevious();
    assertTrue(previous instanceof ChannelCoordinator);
    MembershipService membershipService = ((ChannelCoordinator) previous).getMembershipService();
    assertTrue(membershipService instanceof McastService);
    Member[] members = previous.getMembers();
    assertEquals(0, members.length);
    Member[] members2 = domainFilterInterceptor.getMembers();
    assertEquals(1, members2.length);
    assertTrue(domainFilterInterceptor.hasMembers());
    assertTrue(domainFilterInterceptor.membership.hasMembers());
    assertSame(member, members2[0]);
    assertSame(members, membershipService.getMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Previous {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenDomainFilterInterceptorPreviousDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    ChannelInterceptor previous2 = domainFilterInterceptor.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    Member[] members = previous2.getMembers();
    assertEquals(1, members.length);
    assertTrue(previous2.hasMembers());
    Membership membership = ((DomainFilterInterceptor) previous2).membership;
    assertTrue(membership.hasMembers());
    assertSame(member, members[0]);
    assertSame(previous.membership, membership);
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Previous Next {@link ChannelCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenDomainFilterInterceptorPreviousNextChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(new GroupChannel());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert
    ChannelInterceptor previous = domainFilterInterceptor.getPrevious();
    ChannelInterceptor next = previous.getNext();
    assertTrue(next instanceof ChannelCoordinator);
    assertTrue(previous instanceof GroupChannel);
    MembershipService membershipService = ((GroupChannel) previous).getMembershipService();
    assertTrue(membershipService instanceof McastService);
    Member[] members = previous.getMembers();
    assertEquals(0, members.length);
    Member[] members2 = domainFilterInterceptor.getMembers();
    assertEquals(1, members2.length);
    assertTrue(domainFilterInterceptor.hasMembers());
    assertTrue(domainFilterInterceptor.membership.hasMembers());
    assertSame(member, members2[0]);
    assertSame(members, next.getMembers());
    assertSame(members, membershipService.getMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Then not {@link DomainFilterInterceptor} (default constructor) hasMembers.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenNotDomainFilterInterceptorHasMembers() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setDomain(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(new MemberImpl());

    // Assert that nothing has changed
    assertEquals(0, domainFilterInterceptor.getMembers().length);
    assertFalse(domainFilterInterceptor.hasMembers());
    assertFalse(domainFilterInterceptor.membership.hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Then not {@link DomainFilterInterceptor} (default constructor) hasMembers.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_thenNotDomainFilterInterceptorHasMembers2() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setDomain(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberAdded(member);

    // Assert that nothing has changed
    assertEquals(0, domainFilterInterceptor.getMembers().length);
    assertFalse(domainFilterInterceptor.hasMembers());
    assertFalse(domainFilterInterceptor.membership.hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#memberDisappeared(Member)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Previous {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#memberDisappeared(Member)}
   */
  @Test
  public void testMemberDisappeared_thenDomainFilterInterceptorPreviousDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    domainFilterInterceptor.setPrevious(previous);

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    domainFilterInterceptor.memberDisappeared(member);

    // Assert
    ChannelInterceptor previous2 = domainFilterInterceptor.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link DomainFilterInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptor() {
    // Arrange, Act and Assert
    assertFalse((new DomainFilterInterceptor()).hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertFalse(domainFilterInterceptor.hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertFalse(domainFilterInterceptor.hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#hasMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#hasMembers()}
   */
  @Test
  public void testHasMembers_givenDomainFilterInterceptorNextIsEncryptInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertFalse(domainFilterInterceptor.hasMembers());
  }

  /**
   * Test {@link DomainFilterInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptor() {
    // Arrange, Act and Assert
    assertEquals(0, (new DomainFilterInterceptor()).getMembers().length);
  }

  /**
   * Test {@link DomainFilterInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertEquals(0, domainFilterInterceptor.getMembers().length);
  }

  /**
   * Test {@link DomainFilterInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertEquals(0, domainFilterInterceptor.getMembers().length);
  }

  /**
   * Test {@link DomainFilterInterceptor#getMembers()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMembers()}
   */
  @Test
  public void testGetMembers_givenDomainFilterInterceptorNextIsEncryptInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertEquals(0, domainFilterInterceptor.getMembers().length);
  }

  /**
   * Test {@link DomainFilterInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    // Act and Assert
    assertNull(domainFilterInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link DomainFilterInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(domainFilterInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link DomainFilterInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(domainFilterInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link DomainFilterInterceptor#getMember(Member)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getMember(Member)}
   */
  @Test
  public void testGetMember_givenDomainFilterInterceptorNextIsEncryptInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertNull(domainFilterInterceptor.getMember(new MemberImpl()));
  }

  /**
   * Test {@link DomainFilterInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenDomainFilterInterceptor() {
    // Arrange, Act and Assert
    assertNull((new DomainFilterInterceptor()).getLocalMember(true));
  }

  /**
   * Test {@link DomainFilterInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(domainFilterInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link DomainFilterInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(domainFilterInterceptor.getLocalMember(true));
  }

  /**
   * Test {@link DomainFilterInterceptor#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenDomainFilterInterceptorNextIsEncryptInterceptor() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new EncryptInterceptor());

    // Act and Assert
    assertNull(domainFilterInterceptor.getLocalMember(true));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DomainFilterInterceptor#setDomain(byte[])}
   *   <li>{@link DomainFilterInterceptor#setLogInterval(int)}
   *   <li>{@link DomainFilterInterceptor#getDomain()}
   *   <li>{@link DomainFilterInterceptor#getLogInterval()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    byte[] domain = "AXAXAXAX".getBytes("UTF-8");

    // Act
    domainFilterInterceptor.setDomain(domain);
    domainFilterInterceptor.setLogInterval(42);
    byte[] actualDomain = domainFilterInterceptor.getDomain();

    // Assert
    assertEquals(42, domainFilterInterceptor.getLogInterval());
    assertSame(domain, actualDomain);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualDomain);
  }

  /**
   * Test {@link DomainFilterInterceptor#setDomain(String)} with {@code String}.
   * <p>
   * Method under test: {@link DomainFilterInterceptor#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    // Act
    domainFilterInterceptor.setDomain("{42");

    // Assert
    assertArrayEquals(new byte[]{'*'}, domainFilterInterceptor.getDomain());
  }

  /**
   * Test {@link DomainFilterInterceptor#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Domain is {@code Domain} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_thenDomainFilterInterceptorDomainIsDomainBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    // Act
    domainFilterInterceptor.setDomain("Domain");

    // Assert
    byte[] expectedDomain = "Domain".getBytes("UTF-8");
    assertArrayEquals(expectedDomain, domainFilterInterceptor.getDomain());
  }

  /**
   * Test {@link DomainFilterInterceptor#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code {}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenLeftCurlyBracket() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    // Act
    domainFilterInterceptor.setDomain("{");

    // Assert that nothing has changed
    assertArrayEquals(new byte[]{}, domainFilterInterceptor.getDomain());
  }

  /**
   * Test {@link DomainFilterInterceptor#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DomainFilterInterceptor#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenNull() {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();

    // Act
    domainFilterInterceptor.setDomain((String) null);

    // Assert that nothing has changed
    assertArrayEquals(new byte[]{}, domainFilterInterceptor.getDomain());
  }

  /**
   * Test new {@link DomainFilterInterceptor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DomainFilterInterceptor}
   */
  @Test
  public void testNewDomainFilterInterceptor() {
    // Arrange and Act
    DomainFilterInterceptor actualDomainFilterInterceptor = new DomainFilterInterceptor();

    // Assert
    assertNull(actualDomainFilterInterceptor.getChannel());
    assertNull(actualDomainFilterInterceptor.getNext());
    assertNull(actualDomainFilterInterceptor.getPrevious());
    assertEquals(0, actualDomainFilterInterceptor.getOptionFlag());
    Member[] members = actualDomainFilterInterceptor.getMembers();
    assertEquals(0, members.length);
    assertEquals(100, actualDomainFilterInterceptor.getLogInterval());
    assertFalse(actualDomainFilterInterceptor.hasMembers());
    Membership membership = actualDomainFilterInterceptor.membership;
    assertFalse(membership.hasMembers());
    assertSame(members, membership.getMembers());
    assertArrayEquals(new byte[]{}, actualDomainFilterInterceptor.getDomain());
  }
}
