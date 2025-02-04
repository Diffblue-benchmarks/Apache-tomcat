package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.InterceptorPayload;
import org.apache.catalina.tribes.group.interceptors.EncryptionInterceptorBaseTest.ValueCaptureInterceptor;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class GzipInterceptorDiffblueTest {
  /**
   * Test new {@link GzipInterceptor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link GzipInterceptor}
   */
  @Test
  public void testNewGzipInterceptor() {
    // Arrange and Act
    GzipInterceptor actualGzipInterceptor = new GzipInterceptor();

    // Assert
    assertNull(actualGzipInterceptor.getMembers());
    assertNull(actualGzipInterceptor.getChannel());
    assertNull(actualGzipInterceptor.getNext());
    assertNull(actualGzipInterceptor.getPrevious());
    assertEquals(0, actualGzipInterceptor.getCompressionMinSize());
    assertEquals(0, actualGzipInterceptor.getCount());
    assertEquals(0, actualGzipInterceptor.getCountCompressedRX());
    assertEquals(0, actualGzipInterceptor.getCountCompressedTX());
    assertEquals(0, actualGzipInterceptor.getCountUncompressedRX());
    assertEquals(0, actualGzipInterceptor.getCountUncompressedTX());
    assertEquals(0, actualGzipInterceptor.getInterval());
    assertEquals(0L, actualGzipInterceptor.getCompressedSizeRX());
    assertEquals(0L, actualGzipInterceptor.getCompressedSizeTX());
    assertEquals(0L, actualGzipInterceptor.getSizeRX());
    assertEquals(0L, actualGzipInterceptor.getSizeTX());
    assertEquals(0L, actualGzipInterceptor.getUncompressedSizeRX());
    assertEquals(0L, actualGzipInterceptor.getUncompressedSizeTX());
    assertFalse(actualGzipInterceptor.hasMembers());
    assertFalse(actualGzipInterceptor.getStatsEnabled());
    assertEquals(GzipInterceptor.DEFAULT_OPTION_COMPRESSION_ENABLE, actualGzipInterceptor.getOptionFlag());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Interval is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_givenGzipInterceptorIntervalIsFortyTwo() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(42);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertEquals(1, gzipInterceptor.getCountUncompressedTX());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Interval is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_givenGzipInterceptorIntervalIsOne() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(1);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertEquals(1, gzipInterceptor.getCountUncompressedTX());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Next is {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_givenGzipInterceptorNextIsDomainFilterInterceptor() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setNext(new DomainFilterInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert that nothing has changed
    assertEquals(0, gzipInterceptor.getCountUncompressedTX());
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) StatsEnabled is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_givenGzipInterceptorStatsEnabledIsFalse() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(false);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert that nothing has changed
    assertEquals(0, gzipInterceptor.getCountUncompressedTX());
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then {@link ChannelData#ChannelData()} Message Length is {@link Float#PRECISION}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenChannelDataMessageLengthIsPrecision()
      throws UnsupportedEncodingException, ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(false);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    XByteBuffer message2 = msg.getMessage();
    assertEquals(Float.PRECISION, message2.getLength());
    assertEquals(GzipInterceptor.DEFAULT_OPTION_COMPRESSION_ENABLE, msg.getOptions());
    assertArrayEquals(
        new byte[]{31, -117, '\b', 0, 0, 0, 0, 0, 0, -1, 's', -116, 'p', 4, 'C', 0, -37, 0, -99, -111, '\b', 0, 0, 0},
        message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) CountCompressedTX is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenGzipInterceptorCountCompressedTXIsOne()
      throws UnsupportedEncodingException, ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    assertEquals(1, gzipInterceptor.getCountCompressedTX());
    assertEquals(24L, gzipInterceptor.getCompressedSizeTX());
    assertEquals(8L, gzipInterceptor.getSizeTX());
    assertArrayEquals(
        new byte[]{31, -117, '\b', 0, 0, 0, 0, 0, 0, -1, 's', -116, 'p', 4, 'C', 0, -37, 0, -99, -111, '\b', 0, 0, 0},
        msg.getMessage().getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) CountUncompressedTX is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenGzipInterceptorCountUncompressedTXIsOne() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertEquals(1, gzipInterceptor.getCountUncompressedTX());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) Next {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenGzipInterceptorNextGzipInterceptor() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setNext(new GzipInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    ChannelInterceptor next = gzipInterceptor.getNext();
    assertTrue(next instanceof GzipInterceptor);
    assertEquals(0, gzipInterceptor.getCountUncompressedTX());
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertEquals(1, ((GzipInterceptor) next).getCount());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) Next {@link ValueCaptureInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage_thenGzipInterceptorNextValueCaptureInterceptor() throws ChannelException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setNext(new ValueCaptureInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    ChannelInterceptor next = gzipInterceptor.getNext();
    assertTrue(next instanceof ValueCaptureInterceptor);
    assertEquals(0, gzipInterceptor.getCountUncompressedTX());
    assertEquals(0, msg.getOptions());
    XByteBuffer message2 = msg.getMessage();
    assertEquals(0, message2.getLength());
    assertArrayEquals(new byte[]{}, ((ValueCaptureInterceptor) next).getValue());
    assertArrayEquals(new byte[]{}, message2.getBytes());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived() throws UnsupportedEncodingException {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new GroupChannel());

    XByteBuffer message = new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Interval is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorIntervalIsFortyTwo() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(42);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1, gzipInterceptor.getCount());
    assertEquals(1, gzipInterceptor.getCountUncompressedRX());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Interval is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorIntervalIsOne() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(1);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1, gzipInterceptor.getCount());
    assertEquals(1, gzipInterceptor.getCountUncompressedRX());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Interval is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorIntervalIsZero() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(true);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(1, gzipInterceptor.getCount());
    assertEquals(1, gzipInterceptor.getCountUncompressedRX());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorPreviousIsChannelCoordinator() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new ChannelCoordinator());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Previous is {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorPreviousIsGroupChannel() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new GroupChannel());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) Previous is {@link MessageDispatchInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorPreviousIsMessageDispatchInterceptor() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new MessageDispatchInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Given {@link GzipInterceptor} (default constructor) StatsEnabled is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_givenGzipInterceptorStatsEnabledIsFalse() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setStatsEnabled(false);
    gzipInterceptor.setInterval(0);

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) Previous {@link GzipInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenGzipInterceptorPreviousGzipInterceptor() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new GzipInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    ChannelInterceptor previous = gzipInterceptor.getPrevious();
    assertTrue(previous instanceof GzipInterceptor);
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
    assertEquals(1, ((GzipInterceptor) previous).getCount());
  }

  /**
   * Test {@link GzipInterceptor#messageReceived(ChannelMessage)}.
   * <ul>
   *   <li>Then {@link GzipInterceptor} (default constructor) Previous {@link ValueCaptureInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GzipInterceptor#messageReceived(ChannelMessage)}
   */
  @Test
  public void testMessageReceived_thenGzipInterceptorPreviousValueCaptureInterceptor() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();
    gzipInterceptor.setPrevious(new ValueCaptureInterceptor());

    XByteBuffer message = new XByteBuffer(3, true);
    message.setDiscard(false);

    ChannelData msg = new ChannelData();
    msg.setMessage(message);

    // Act
    gzipInterceptor.messageReceived(msg);

    // Assert
    ChannelInterceptor previous = gzipInterceptor.getPrevious();
    assertTrue(previous instanceof ValueCaptureInterceptor);
    assertEquals(0, gzipInterceptor.getCountUncompressedRX());
    assertEquals(1, gzipInterceptor.getCount());
    assertArrayEquals(new byte[]{}, ((ValueCaptureInterceptor) previous).getValue());
  }

  /**
   * Test {@link GzipInterceptor#compress(byte[])}.
   * <p>
   * Method under test: {@link GzipInterceptor#compress(byte[])}
   */
  @Test
  public void testCompress() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[]{31, -117, '\b', 0, 0, 0, 0, 0, 0, -1, 's', -116, 'p', 4, 'C', 0, -37, 0, -99, -111, '\b', 0, 0, 0},
        GzipInterceptor.compress("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GzipInterceptor#setCompressionMinSize(int)}
   *   <li>{@link GzipInterceptor#setInterval(int)}
   *   <li>{@link GzipInterceptor#setStatsEnabled(boolean)}
   *   <li>{@link GzipInterceptor#getCompressionMinSize()}
   *   <li>{@link GzipInterceptor#getInterval()}
   *   <li>{@link GzipInterceptor#getStatsEnabled()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    GzipInterceptor gzipInterceptor = new GzipInterceptor();

    // Act
    gzipInterceptor.setCompressionMinSize(3);
    gzipInterceptor.setInterval(42);
    gzipInterceptor.setStatsEnabled(true);
    int actualCompressionMinSize = gzipInterceptor.getCompressionMinSize();
    int actualInterval = gzipInterceptor.getInterval();

    // Assert
    assertEquals(3, actualCompressionMinSize);
    assertEquals(42, actualInterval);
    assertTrue(gzipInterceptor.getStatsEnabled());
  }

  /**
   * Test {@link GzipInterceptor#getCount()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCount()}
   */
  @Test
  public void testGetCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new GzipInterceptor()).getCount());
  }

  /**
   * Test {@link GzipInterceptor#getCountCompressedTX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCountCompressedTX()}
   */
  @Test
  public void testGetCountCompressedTX() {
    // Arrange, Act and Assert
    assertEquals(0, (new GzipInterceptor()).getCountCompressedTX());
  }

  /**
   * Test {@link GzipInterceptor#getCountUncompressedTX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCountUncompressedTX()}
   */
  @Test
  public void testGetCountUncompressedTX() {
    // Arrange, Act and Assert
    assertEquals(0, (new GzipInterceptor()).getCountUncompressedTX());
  }

  /**
   * Test {@link GzipInterceptor#getCountCompressedRX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCountCompressedRX()}
   */
  @Test
  public void testGetCountCompressedRX() {
    // Arrange, Act and Assert
    assertEquals(0, (new GzipInterceptor()).getCountCompressedRX());
  }

  /**
   * Test {@link GzipInterceptor#getCountUncompressedRX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCountUncompressedRX()}
   */
  @Test
  public void testGetCountUncompressedRX() {
    // Arrange, Act and Assert
    assertEquals(0, (new GzipInterceptor()).getCountUncompressedRX());
  }

  /**
   * Test {@link GzipInterceptor#getSizeTX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getSizeTX()}
   */
  @Test
  public void testGetSizeTX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getSizeTX());
  }

  /**
   * Test {@link GzipInterceptor#getCompressedSizeTX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCompressedSizeTX()}
   */
  @Test
  public void testGetCompressedSizeTX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getCompressedSizeTX());
  }

  /**
   * Test {@link GzipInterceptor#getUncompressedSizeTX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getUncompressedSizeTX()}
   */
  @Test
  public void testGetUncompressedSizeTX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getUncompressedSizeTX());
  }

  /**
   * Test {@link GzipInterceptor#getSizeRX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getSizeRX()}
   */
  @Test
  public void testGetSizeRX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getSizeRX());
  }

  /**
   * Test {@link GzipInterceptor#getCompressedSizeRX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getCompressedSizeRX()}
   */
  @Test
  public void testGetCompressedSizeRX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getCompressedSizeRX());
  }

  /**
   * Test {@link GzipInterceptor#getUncompressedSizeRX()}.
   * <p>
   * Method under test: {@link GzipInterceptor#getUncompressedSizeRX()}
   */
  @Test
  public void testGetUncompressedSizeRX() {
    // Arrange, Act and Assert
    assertEquals(0L, (new GzipInterceptor()).getUncompressedSizeRX());
  }
}
