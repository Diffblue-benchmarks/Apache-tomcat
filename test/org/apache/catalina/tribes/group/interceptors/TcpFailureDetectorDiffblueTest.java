package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMember;
import org.junit.Test;

public class TcpFailureDetectorDiffblueTest {
  /**
   * Test {@link TcpFailureDetector#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Previous {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenTcpFailureDetectorPreviousGzipInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setPrevious(new GzipInterceptor());

    // Act
    tcpFailureDetector
        .messageReceived(new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L));

    // Assert
    ChannelInterceptor previous = tcpFailureDetector.getPrevious();
    assertTrue(previous instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) previous).getCount());
  }

  /**
   * Test {@link TcpFailureDetector#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then {@link TcpFailureDetector} (default constructor) {@link TcpFailureDetector#addSuspects} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenA_thenTcpFailureDetectorAddSuspectsSizeIsOne() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    StaticMember member = new StaticMember();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    tcpFailureDetector.memberAdded(member);

    // Assert
    assertEquals(1, tcpFailureDetector.addSuspects.size());
  }

  /**
   * Test {@link TcpFailureDetector#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then {@link TcpFailureDetector} (default constructor) {@link TcpFailureDetector#addSuspects} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenA_thenTcpFailureDetectorAddSuspectsSizeIsOne2() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    StaticMember member = new StaticMember();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    tcpFailureDetector.memberAdded(member);

    // Assert
    assertEquals(1, tcpFailureDetector.addSuspects.size());
  }

  /**
   * Test {@link TcpFailureDetector#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new ChannelCoordinator());

    // Act
    tcpFailureDetector.memberAdded(new MemberImpl());

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.addSuspects.isEmpty());
  }

  /**
   * Test {@link TcpFailureDetector#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenTcpFailureDetectorNextIsGroupChannel() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new GroupChannel());

    // Act
    tcpFailureDetector.memberAdded(new MemberImpl());

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.addSuspects.isEmpty());
  }

  /**
   * Test {@link TcpFailureDetector#memberAdded(Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then {@link TcpFailureDetector} (default constructor) {@link TcpFailureDetector#addSuspects} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_whenMemberImpl_thenTcpFailureDetectorAddSuspectsEmpty() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    // Act
    tcpFailureDetector.memberAdded(new MemberImpl());

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.addSuspects.isEmpty());
  }

  /**
   * Test {@link TcpFailureDetector#hasMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#hasMembers()}
   */
  @Test
  public void testHasMembers_givenTcpFailureDetector() {
    // Arrange, Act and Assert
    assertFalse((new TcpFailureDetector()).hasMembers());
  }

  /**
   * Test {@link TcpFailureDetector#hasMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#hasMembers()}
   */
  @Test
  public void testHasMembers_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new ChannelCoordinator());

    // Act and Assert
    assertFalse(tcpFailureDetector.hasMembers());
  }

  /**
   * Test {@link TcpFailureDetector#hasMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#hasMembers()}
   */
  @Test
  public void testHasMembers_givenTcpFailureDetectorNextIsDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertFalse(tcpFailureDetector.hasMembers());
  }

  /**
   * Test {@link TcpFailureDetector#hasMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#hasMembers()}
   */
  @Test
  public void testHasMembers_givenTcpFailureDetectorNextIsTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new TcpFailureDetector());

    // Act and Assert
    assertFalse(tcpFailureDetector.hasMembers());
  }

  /**
   * Test {@link TcpFailureDetector#getMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMembers()}
   */
  @Test
  public void testGetMembers_givenTcpFailureDetector() {
    // Arrange, Act and Assert
    assertEquals(0, (new TcpFailureDetector()).getMembers().length);
  }

  /**
   * Test {@link TcpFailureDetector#getMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMembers()}
   */
  @Test
  public void testGetMembers_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new ChannelCoordinator());

    // Act and Assert
    assertEquals(0, tcpFailureDetector.getMembers().length);
  }

  /**
   * Test {@link TcpFailureDetector#getMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMembers()}
   */
  @Test
  public void testGetMembers_givenTcpFailureDetectorNextIsDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertEquals(0, tcpFailureDetector.getMembers().length);
  }

  /**
   * Test {@link TcpFailureDetector#getMembers()}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMembers()}
   */
  @Test
  public void testGetMembers_givenTcpFailureDetectorNextIsTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new TcpFailureDetector());

    // Act and Assert
    assertEquals(0, tcpFailureDetector.getMembers().length);
  }

  /**
   * Test {@link TcpFailureDetector#getMember(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMember(Member)}
   */
  @Test
  public void testGetMember_givenTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    // Act and Assert
    assertNull(tcpFailureDetector.getMember(new MemberImpl()));
  }

  /**
   * Test {@link TcpFailureDetector#getMember(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMember(Member)}
   */
  @Test
  public void testGetMember_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(tcpFailureDetector.getMember(new MemberImpl()));
  }

  /**
   * Test {@link TcpFailureDetector#getMember(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMember(Member)}
   */
  @Test
  public void testGetMember_givenTcpFailureDetectorNextIsDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(tcpFailureDetector.getMember(new MemberImpl()));
  }

  /**
   * Test {@link TcpFailureDetector#getMember(Member)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getMember(Member)}
   */
  @Test
  public void testGetMember_givenTcpFailureDetectorNextIsTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new TcpFailureDetector());

    // Act and Assert
    assertNull(tcpFailureDetector.getMember(new MemberImpl()));
  }

  /**
   * Test {@link TcpFailureDetector#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenTcpFailureDetector() {
    // Arrange, Act and Assert
    assertNull((new TcpFailureDetector()).getLocalMember(true));
  }

  /**
   * Test {@link TcpFailureDetector#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenTcpFailureDetectorNextIsChannelCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new ChannelCoordinator());

    // Act and Assert
    assertNull(tcpFailureDetector.getLocalMember(true));
  }

  /**
   * Test {@link TcpFailureDetector#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenTcpFailureDetectorNextIsDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(tcpFailureDetector.getLocalMember(true));
  }

  /**
   * Test {@link TcpFailureDetector#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link TcpFailureDetector} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenTcpFailureDetectorNextIsTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new TcpFailureDetector());

    // Act and Assert
    assertNull(tcpFailureDetector.getLocalMember(true));
  }

  /**
   * Test {@link TcpFailureDetector#heartbeat()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTcpFailureDetectorNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.heartbeat();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#heartbeat()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTcpFailureDetectorNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new NonBlockingCoordinator());

    // Act
    tcpFailureDetector.heartbeat();

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.getNext() instanceof NonBlockingCoordinator);
  }

  /**
   * Test {@link TcpFailureDetector#heartbeat()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTcpFailureDetectorNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    TcpFailureDetector next = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.heartbeat();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#checkMembers(boolean)}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#checkMembers(boolean)}
   */
  @Test
  public void testCheckMembers_thenTcpFailureDetectorNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.checkMembers(true);

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#checkMembers(boolean)}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#checkMembers(boolean)}
   */
  @Test
  public void testCheckMembers_thenTcpFailureDetectorNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new NonBlockingCoordinator());

    // Act
    tcpFailureDetector.checkMembers(true);

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.getNext() instanceof NonBlockingCoordinator);
  }

  /**
   * Test {@link TcpFailureDetector#checkMembers(boolean)}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#checkMembers(boolean)}
   */
  @Test
  public void testCheckMembers_thenTcpFailureDetectorNextNonBlockingCoordinator2() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(new NonBlockingCoordinator());

    // Act
    tcpFailureDetector.checkMembers(false);

    // Assert that nothing has changed
    assertTrue(tcpFailureDetector.getNext() instanceof NonBlockingCoordinator);
  }

  /**
   * Test {@link TcpFailureDetector#checkMembers(boolean)}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#checkMembers(boolean)}
   */
  @Test
  public void testCheckMembers_thenTcpFailureDetectorNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    TcpFailureDetector next = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.checkMembers(true);

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performForcedCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performForcedCheck()}
   */
  @Test
  public void testPerformForcedCheck_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new ChannelCoordinator());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performForcedCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performForcedCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performForcedCheck()}
   */
  @Test
  public void testPerformForcedCheck_givenDomainFilterInterceptorNextIsDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new DomainFilterInterceptor());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performForcedCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performForcedCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performForcedCheck()}
   */
  @Test
  public void testPerformForcedCheck_givenDomainFilterInterceptorNextIsTcpFailureDetector() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new TcpFailureDetector());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performForcedCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performForcedCheck()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performForcedCheck()}
   */
  @Test
  public void testPerformForcedCheck_thenTcpFailureDetectorNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performForcedCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performForcedCheck()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performForcedCheck()}
   */
  @Test
  public void testPerformForcedCheck_thenTcpFailureDetectorNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    TcpFailureDetector next = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performForcedCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performBasicCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performBasicCheck()}
   */
  @Test
  public void testPerformBasicCheck_givenDomainFilterInterceptorNextIsChannelCoordinator() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new ChannelCoordinator());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performBasicCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performBasicCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performBasicCheck()}
   */
  @Test
  public void testPerformBasicCheck_givenDomainFilterInterceptorNextIsGroupChannel() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new GroupChannel());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performBasicCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performBasicCheck()}.
   * <ul>
   *   <li>Given {@link DomainFilterInterceptor} (default constructor) Next is {@link TcpFailureDetector} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performBasicCheck()}
   */
  @Test
  public void testPerformBasicCheck_givenDomainFilterInterceptorNextIsTcpFailureDetector() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    next.setNext(new TcpFailureDetector());

    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performBasicCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performBasicCheck()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performBasicCheck()}
   */
  @Test
  public void testPerformBasicCheck_thenTcpFailureDetectorNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performBasicCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof DomainFilterInterceptor);
    assertSame(next.membership, ((DomainFilterInterceptor) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#performBasicCheck()}.
   * <ul>
   *   <li>Then {@link TcpFailureDetector} (default constructor) Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#performBasicCheck()}
   */
  @Test
  public void testPerformBasicCheck_thenTcpFailureDetectorNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    TcpFailureDetector next = new TcpFailureDetector();
    tcpFailureDetector.setNext(next);

    // Act
    tcpFailureDetector.performBasicCheck();

    // Assert
    ChannelInterceptor next2 = tcpFailureDetector.getNext();
    assertTrue(next2 instanceof TcpFailureDetector);
    assertSame(next.membership, ((TcpFailureDetector) next2).membership);
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member)} with {@code mbr}.
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member)}
   */
  @Test
  public void testMemberAliveWithMbr() throws IOException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(new MemberImpl("42", -1, 1L)));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)} with {@code mbr}, {@code msgData}, {@code sendTest}, {@code readTest}, {@code readTimeout}, {@code conTimeout}, {@code optionFlag}.
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)}
   */
  @Test
  public void testMemberAliveWithMbrMsgDataSendTestReadTestReadTimeoutConTimeoutOptionFlag()
      throws UnsupportedEncodingException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(mbr, "AXAXAXAX".getBytes("UTF-8"), true, true, 1L, 1L, 1));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)} with {@code mbr}, {@code msgData}, {@code sendTest}, {@code readTest}, {@code readTimeout}, {@code conTimeout}, {@code optionFlag}.
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)}
   */
  @Test
  public void testMemberAliveWithMbrMsgDataSendTestReadTestReadTimeoutConTimeoutOptionFlag2() throws IOException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    MemberImpl mbr = new MemberImpl("42", 8080, 1L);

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(mbr, "AXAXAXAX".getBytes("UTF-8"), true, true, 1L, 1L, 1));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)} with {@code mbr}, {@code msgData}, {@code sendTest}, {@code readTest}, {@code readTimeout}, {@code conTimeout}, {@code optionFlag}.
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)}
   */
  @Test
  public void testMemberAliveWithMbrMsgDataSendTestReadTestReadTimeoutConTimeoutOptionFlag3() throws IOException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    MemberImpl mbr = new MemberImpl("42", -1, 1L);

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(mbr, "AXAXAXAX".getBytes("UTF-8"), true, true, 1L, 1L, 1));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)} with {@code mbr}, {@code msgData}, {@code sendTest}, {@code readTest}, {@code readTimeout}, {@code conTimeout}, {@code optionFlag}.
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member, byte[], boolean, boolean, long, long, int)}
   */
  @Test
  public void testMemberAliveWithMbrMsgDataSendTestReadTestReadTimeoutConTimeoutOptionFlag4() throws IOException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();
    MemberImpl mbr = new MemberImpl("42", 8080, 1L);

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(mbr, "AXAXAXAX".getBytes("UTF-8"), true, true, -1L, 1L, 1));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member)} with {@code mbr}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl(String, int, long)} with host is {@code 42} and port is {@code 8080} and aliveTime is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member)}
   */
  @Test
  public void testMemberAliveWithMbr_whenMemberImplWithHostIs42AndPortIs8080AndAliveTimeIsOne() throws IOException {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(new MemberImpl("42", 8080, 1L)));
  }

  /**
   * Test {@link TcpFailureDetector#memberAlive(Member)} with {@code mbr}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpFailureDetector#memberAlive(Member)}
   */
  @Test
  public void testMemberAliveWithMbr_whenMemberImpl_thenReturnFalse() {
    // Arrange
    TcpFailureDetector tcpFailureDetector = new TcpFailureDetector();

    // Act and Assert
    assertFalse(tcpFailureDetector.memberAlive(new MemberImpl()));
  }
}
