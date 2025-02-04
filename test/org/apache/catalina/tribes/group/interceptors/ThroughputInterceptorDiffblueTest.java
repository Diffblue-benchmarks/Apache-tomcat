package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.InterceptorPayload;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMember;
import org.junit.Test;

public class ThroughputInterceptorDiffblueTest {
  /**
   * Test {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <p>
   * Method under test: {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage() throws ChannelException {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();

    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert that nothing has changed
    Object deepcloneResult = msg.deepclone();
    assertTrue(deepcloneResult instanceof ChannelData);
    XByteBuffer message = msg.getMessage();
    assertEquals(0, message.getLength());
    byte[] dataPackage = msg.getDataPackage();
    assertEquals((byte) 0, dataPackage[104]);
    assertEquals(105, msg.getDataPackageLength());
    assertEquals(105, ((ChannelData) deepcloneResult).getDataPackageLength());
    assertEquals(105, dataPackage.length);
    assertEquals(105, ((ChannelData) deepcloneResult).getDataPackage().length);
    assertArrayEquals(new byte[]{}, message.getBytes());
  }

  /**
   * Test {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Given {@link ThroughputInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_givenThroughputInterceptorNextIsDomainFilterInterceptor() throws ChannelException {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setNext(new DomainFilterInterceptor());

    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert that nothing has changed
    Object deepcloneResult = msg.deepclone();
    assertTrue(deepcloneResult instanceof ChannelData);
    XByteBuffer message = msg.getMessage();
    assertEquals(0, message.getLength());
    byte[] dataPackage = msg.getDataPackage();
    assertEquals((byte) 0, dataPackage[104]);
    assertEquals(105, msg.getDataPackageLength());
    assertEquals(105, ((ChannelData) deepcloneResult).getDataPackageLength());
    assertEquals(105, dataPackage.length);
    assertEquals(105, ((ChannelData) deepcloneResult).getDataPackage().length);
    assertArrayEquals(new byte[]{}, message.getBytes());
  }

  /**
   * Test {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then one hundred sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenOneHundredSixthElementIsZero() throws ChannelException {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setNext(new FragmentationInterceptor());

    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    Object deepcloneResult = msg.deepclone();
    assertTrue(deepcloneResult instanceof ChannelData);
    byte[] dataPackage = msg.getDataPackage();
    assertEquals((byte) 0, dataPackage[105]);
    XByteBuffer message = msg.getMessage();
    assertEquals(1, message.getLength());
    assertEquals(106, msg.getDataPackageLength());
    assertEquals(106, ((ChannelData) deepcloneResult).getDataPackageLength());
    assertEquals(106, dataPackage.length);
    assertEquals(106, ((ChannelData) deepcloneResult).getDataPackage().length);
    assertEquals((byte) 1, dataPackage[104]);
    assertArrayEquals(new byte[]{0}, message.getBytes());
  }

  /**
   * Test {@link ThroughputInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link ThroughputInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenThroughputInterceptor() {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();

    ChannelData msg = new ChannelData(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1.1730194091796875E-4d, throughputInterceptor.getMbRx(), 0.0);
  }

  /**
   * Test {@link ThroughputInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link ThroughputInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenThroughputInterceptorPreviousIsChannelCoordinator() {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setPrevious(new ChannelCoordinator());

    ChannelData msg = new ChannelData(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1.1730194091796875E-4d, throughputInterceptor.getMbRx(), 0.0);
  }

  /**
   * Test {@link ThroughputInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link ThroughputInterceptor} (default constructor) Previous is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenThroughputInterceptorPreviousIsDomainFilterInterceptor() {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setPrevious(new DomainFilterInterceptor());

    ChannelData msg = new ChannelData(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1.1730194091796875E-4d, throughputInterceptor.getMbRx(), 0.0);
  }

  /**
   * Test {@link ThroughputInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link ThroughputInterceptor} (default constructor) Previous is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenThroughputInterceptorPreviousIsGroupChannel() {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setPrevious(new GroupChannel());

    ChannelData msg = new ChannelData(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16}, new XByteBuffer(3, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1.1730194091796875E-4d, throughputInterceptor.getMbRx(), 0.0);
  }

  /**
   * Test {@link ThroughputInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link ThroughputInterceptor} (default constructor) MbRx is {@code 1.2493133544921875E-4}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ThroughputInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenThroughputInterceptorMbRxIs12493133544921875e4() {
    // Arrange
    ThroughputInterceptor throughputInterceptor = new ThroughputInterceptor();
    throughputInterceptor.setPrevious(new GroupChannel());

    ChannelData msg = new ChannelData(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16},
        new XByteBuffer(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16}, true), 10L);
    msg.setAddress(new StaticMember());

    // Act
    throughputInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1.2493133544921875E-4d, throughputInterceptor.getMbRx(), 0.0);
  }

  /**
   * Test new {@link ThroughputInterceptor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ThroughputInterceptor}
   */
  @Test
  public void testNewThroughputInterceptor() {
    // Arrange and Act
    ThroughputInterceptor actualThroughputInterceptor = new ThroughputInterceptor();

    // Assert
    assertNull(actualThroughputInterceptor.getMembers());
    assertNull(actualThroughputInterceptor.getChannel());
    assertNull(actualThroughputInterceptor.getNext());
    assertNull(actualThroughputInterceptor.getPrevious());
    assertEquals(0, actualThroughputInterceptor.getOptionFlag());
    assertEquals(0.0d, actualThroughputInterceptor.getLastCnt(), 0.0);
    assertEquals(0.0d, actualThroughputInterceptor.getMbAppTx(), 0.0);
    assertEquals(0.0d, actualThroughputInterceptor.getMbRx(), 0.0);
    assertEquals(0.0d, actualThroughputInterceptor.getMbTx(), 0.0);
    assertEquals(0.0d, actualThroughputInterceptor.getTimeTx(), 0.0);
    assertEquals(0L, actualThroughputInterceptor.getRxStart());
    assertEquals(0L, actualThroughputInterceptor.getTxStart());
    assertEquals(10000, actualThroughputInterceptor.getInterval());
    assertFalse(actualThroughputInterceptor.hasMembers());
  }
}
