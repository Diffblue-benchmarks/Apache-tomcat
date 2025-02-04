package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.UniqueId;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class TwoPhaseCommitInterceptorDiffblueTest {
  /**
   * Test MapEntry {@link MapEntry#expired(long, long)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#expired(long, long)}
   */
  @Test
  public void testMapEntryExpired_thenReturnFalse() {
    // Arrange
    ChannelData msg = new ChannelData();

    // Act and Assert
    assertFalse((new MapEntry(msg, new UniqueId(), 10L)).expired(1L, 1L));
  }

  /**
   * Test MapEntry {@link MapEntry#expired(long, long)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#expired(long, long)}
   */
  @Test
  public void testMapEntryExpired_thenReturnTrue() {
    // Arrange
    ChannelData msg = new ChannelData();

    // Act and Assert
    assertTrue((new MapEntry(msg, new UniqueId(), -9L)).expired(1L, 1L));
  }

  /**
   * Test MapEntry {@link MapEntry#MapEntry(ChannelMessage, UniqueId, long)}.
   * <p>
   * Method under test: {@link MapEntry#MapEntry(ChannelMessage, UniqueId, long)}
   */
  @Test
  public void testMapEntryNewMapEntry() {
    // Arrange
    ChannelData msg = new ChannelData();

    // Act and Assert
    assertNull((new MapEntry(msg, new UniqueId(), 10L)).id.getBytes());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();

    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new MemberImpl());

    // Act
    twoPhaseCommitInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1, twoPhaseCommitInterceptor.messages.size());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived2() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();

    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1},
        new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true), 10L);
    msg.setAddress(new MemberImpl());

    // Act
    twoPhaseCommitInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1, twoPhaseCommitInterceptor.messages.size());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived3() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setOptionFlag(1);
    twoPhaseCommitInterceptor.setPrevious(new MessageDispatchInterceptor());

    // Act
    twoPhaseCommitInterceptor.messageReceived(new ChannelData());

    // Assert that nothing has changed
    assertTrue(twoPhaseCommitInterceptor.messages.isEmpty());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link TwoPhaseCommitInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenTwoPhaseCommitInterceptorPreviousIsChannelCoordinator() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setOptionFlag(1);
    twoPhaseCommitInterceptor.setPrevious(new ChannelCoordinator());

    // Act
    twoPhaseCommitInterceptor.messageReceived(new ChannelData());

    // Assert that nothing has changed
    assertTrue(twoPhaseCommitInterceptor.messages.isEmpty());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link TwoPhaseCommitInterceptor} (default constructor) Previous is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenTwoPhaseCommitInterceptorPreviousIsGroupChannel() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setOptionFlag(1);
    twoPhaseCommitInterceptor.setPrevious(new GroupChannel());

    // Act
    twoPhaseCommitInterceptor.messageReceived(new ChannelData());

    // Assert that nothing has changed
    assertTrue(twoPhaseCommitInterceptor.messages.isEmpty());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link TwoPhaseCommitInterceptor} (default constructor) {@link TwoPhaseCommitInterceptor#messages} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenTwoPhaseCommitInterceptorMessagesEmpty() {
    // Arrange
    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setOptionFlag(1);

    // Act
    twoPhaseCommitInterceptor.messageReceived(new ChannelData());

    // Assert that nothing has changed
    assertTrue(twoPhaseCommitInterceptor.messages.isEmpty());
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link TwoPhaseCommitInterceptor} (default constructor) Next Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTwoPhaseCommitInterceptorNextNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    DomainFilterInterceptor next2 = new DomainFilterInterceptor();
    next.setNext(next2);

    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setNext(next);

    // Act
    twoPhaseCommitInterceptor.heartbeat();

    // Assert
    ChannelInterceptor next3 = twoPhaseCommitInterceptor.getNext();
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof DomainFilterInterceptor);
    assertTrue(next3 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((DomainFilterInterceptor) next4).membership);
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link TwoPhaseCommitInterceptor} (default constructor) Next Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTwoPhaseCommitInterceptorNextNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new NonBlockingCoordinator());

    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setNext(next);

    // Act
    twoPhaseCommitInterceptor.heartbeat();

    // Assert that nothing has changed
    ChannelInterceptor next2 = twoPhaseCommitInterceptor.getNext();
    assertTrue(next2.getNext() instanceof NonBlockingCoordinator);
    assertTrue(next2 instanceof TcpFailureDetector);
  }

  /**
   * Test {@link TwoPhaseCommitInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link TwoPhaseCommitInterceptor} (default constructor) Next Next {@link TcpFailureDetector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TwoPhaseCommitInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenTwoPhaseCommitInterceptorNextNextTcpFailureDetector() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    TcpFailureDetector next2 = new TcpFailureDetector();
    next.setNext(next2);

    TwoPhaseCommitInterceptor twoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    twoPhaseCommitInterceptor.setNext(next);

    // Act
    twoPhaseCommitInterceptor.heartbeat();

    // Assert
    ChannelInterceptor next3 = twoPhaseCommitInterceptor.getNext();
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof TcpFailureDetector);
    assertTrue(next3 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((TcpFailureDetector) next4).membership);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TwoPhaseCommitInterceptor}
   *   <li>{@link TwoPhaseCommitInterceptor#setDeepclone(boolean)}
   *   <li>{@link TwoPhaseCommitInterceptor#setExpire(long)}
   *   <li>{@link TwoPhaseCommitInterceptor#getDeepclone()}
   *   <li>{@link TwoPhaseCommitInterceptor#getExpire()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    TwoPhaseCommitInterceptor actualTwoPhaseCommitInterceptor = new TwoPhaseCommitInterceptor();
    actualTwoPhaseCommitInterceptor.setDeepclone(true);
    actualTwoPhaseCommitInterceptor.setExpire(1L);
    boolean actualDeepclone = actualTwoPhaseCommitInterceptor.getDeepclone();
    long actualExpire = actualTwoPhaseCommitInterceptor.getExpire();

    // Assert
    assertNull(actualTwoPhaseCommitInterceptor.getChannel());
    assertNull(actualTwoPhaseCommitInterceptor.getNext());
    assertNull(actualTwoPhaseCommitInterceptor.getPrevious());
    assertEquals(0, actualTwoPhaseCommitInterceptor.getOptionFlag());
    assertEquals(1L, actualExpire);
    assertTrue(actualDeepclone);
  }
}
