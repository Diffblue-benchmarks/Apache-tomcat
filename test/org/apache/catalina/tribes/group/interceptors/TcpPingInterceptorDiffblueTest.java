package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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

public class TcpPingInterceptorDiffblueTest {
  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) Name is {@code Name}.</li>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Channel is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenGroupChannelNameIsName_thenTcpPingInterceptorChannelIsNull() throws ChannelException {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("Name");

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setUseThread(true);
    tcpPingInterceptor.setChannel(channel);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(null);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) addStaticMember {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenStaticMembershipInterceptorAddStaticMemberMemberImpl() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.addStaticMember(new MemberImpl());
    next.setNext(new DomainFilterInterceptor());
    next.setPrevious(new ChannelCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenStaticMembershipInterceptorNextIsChannelCoordinator() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setNext(new ChannelCoordinator());
    next.setPrevious(new ChannelCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenStaticMembershipInterceptorNextIsDomainFilterInterceptor() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setNext(new DomainFilterInterceptor());
    next.setPrevious(new ChannelCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Next is {@link EncryptInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenStaticMembershipInterceptorNextIsEncryptInterceptor() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setNext(new EncryptInterceptor());
    next.setPrevious(new ChannelCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link StaticMembershipInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenStaticMembershipInterceptorPreviousIsChannelCoordinator() throws ChannelException {
    // Arrange
    StaticMembershipInterceptor next = new StaticMembershipInterceptor();
    next.setPrevious(new ChannelCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link TcpPingInterceptor} (default constructor) Next is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenTcpPingInterceptorNextIsChannelCoordinator() throws ChannelException {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(new ChannelCoordinator());

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link TcpPingInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenTcpPingInterceptorNextIsDomainFilterInterceptor() throws ChannelException {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setChannel(null);
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(new DomainFilterInterceptor());

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link TcpPingInterceptor} (default constructor) UseThread is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenTcpPingInterceptorUseThreadIsTrue() throws ChannelException {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setUseThread(true);
    tcpPingInterceptor.setChannel(new GroupChannel());
    tcpPingInterceptor.start(1);
    tcpPingInterceptor.setNext(null);

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#stop(int)}.
   * <ul>
   *   <li>Given {@link TcpPingInterceptor} (default constructor).</li>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Channel is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#stop(int)}
   */
  @Test
  public void testStop_givenTcpPingInterceptor_thenTcpPingInterceptorChannelIsNull() throws ChannelException {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();

    // Act
    tcpPingInterceptor.stop(1);

    // Assert
    assertNull(tcpPingInterceptor.getChannel());
    assertNull(tcpPingInterceptor.thread);
    assertFalse(tcpPingInterceptor.running);
  }

  /**
   * Test {@link TcpPingInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTcpPingInterceptorNextDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor next = new DomainFilterInterceptor();
    TcpFailureDetector next2 = new TcpFailureDetector();
    next.setNext(next2);

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.heartbeat();

    // Assert
    ChannelInterceptor next3 = tcpPingInterceptor.getNext();
    assertTrue(next3 instanceof DomainFilterInterceptor);
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((TcpFailureDetector) next4).membership);
  }

  /**
   * Test {@link TcpPingInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Next Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTcpPingInterceptorNextNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new NonBlockingCoordinator());

    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setNext(next);

    // Act
    tcpPingInterceptor.heartbeat();

    // Assert that nothing has changed
    ChannelInterceptor next2 = tcpPingInterceptor.getNext();
    assertTrue(next2.getNext() instanceof NonBlockingCoordinator);
    assertTrue(next2 instanceof TcpFailureDetector);
  }

  /**
   * Test {@link TcpPingInterceptor#sendPingMessage(Member[])}.
   * <ul>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Next {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#sendPingMessage(Member[])}
   */
  @Test
  public void testSendPingMessage_thenTcpPingInterceptorNextGzipInterceptor() {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setNext(new GzipInterceptor());

    // Act
    tcpPingInterceptor.sendPingMessage(new Member[]{new MemberImpl()});

    // Assert
    ChannelInterceptor next = tcpPingInterceptor.getNext();
    assertTrue(next instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) next).getCount());
  }

  /**
   * Test {@link TcpPingInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link TcpPingInterceptor} (default constructor) Previous {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TcpPingInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenTcpPingInterceptorPreviousGzipInterceptor() {
    // Arrange
    TcpPingInterceptor tcpPingInterceptor = new TcpPingInterceptor();
    tcpPingInterceptor.setPrevious(new GzipInterceptor());

    // Act
    tcpPingInterceptor
        .messageReceived(new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L));

    // Assert
    ChannelInterceptor previous = tcpPingInterceptor.getPrevious();
    assertTrue(previous instanceof GzipInterceptor);
    assertEquals(1, ((GzipInterceptor) previous).getCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TcpPingInterceptor}
   *   <li>{@link TcpPingInterceptor#setInterval(long)}
   *   <li>{@link TcpPingInterceptor#setStaticOnly(boolean)}
   *   <li>{@link TcpPingInterceptor#setUseThread(boolean)}
   *   <li>{@link TcpPingInterceptor#getInterval()}
   *   <li>{@link TcpPingInterceptor#getStaticOnly()}
   *   <li>{@link TcpPingInterceptor#getUseThread()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    TcpPingInterceptor actualTcpPingInterceptor = new TcpPingInterceptor();
    actualTcpPingInterceptor.setInterval(42L);
    actualTcpPingInterceptor.setStaticOnly(true);
    actualTcpPingInterceptor.setUseThread(true);
    long actualInterval = actualTcpPingInterceptor.getInterval();
    boolean actualStaticOnly = actualTcpPingInterceptor.getStaticOnly();
    boolean actualUseThread = actualTcpPingInterceptor.getUseThread();

    // Assert
    assertNull(actualTcpPingInterceptor.getChannel());
    assertNull(actualTcpPingInterceptor.getNext());
    assertNull(actualTcpPingInterceptor.getPrevious());
    assertEquals(0, actualTcpPingInterceptor.getOptionFlag());
    assertEquals(42L, actualInterval);
    assertTrue(actualStaticOnly);
    assertTrue(actualUseThread);
  }
}
