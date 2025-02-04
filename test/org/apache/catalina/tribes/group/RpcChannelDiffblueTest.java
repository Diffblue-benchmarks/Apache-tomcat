package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.RpcChannel.RpcCollector;
import org.apache.catalina.tribes.group.RpcChannel.RpcCollectorKey;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMembershipProvider;
import org.junit.Test;

public class RpcChannelDiffblueTest {
  /**
   * Test {@link RpcChannel#RpcChannel(byte[], Channel, RpcCallback)}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then Channel return {@link GroupChannel}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcChannel#RpcChannel(byte[], Channel, RpcCallback)}
   */
  @Test
  public void testNewRpcChannel_whenGroupChannel_thenChannelReturnGroupChannel() throws UnsupportedEncodingException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    StaticMembershipProvider callback = new StaticMembershipProvider();

    // Act
    RpcChannel actualRpcChannel = new RpcChannel(rpcId, channel, callback);

    // Assert
    Channel channel2 = actualRpcChannel.getChannel();
    assertTrue(channel2 instanceof GroupChannel);
    RpcCallback callback2 = actualRpcChannel.getCallback();
    assertTrue(callback2 instanceof StaticMembershipProvider);
    assertEquals(0, actualRpcChannel.getReplyMessageOptions());
    assertEquals(1, channel.channelListeners.size());
    assertSame(callback, callback2);
    assertSame(channel.interceptors, channel2);
    byte[] expectedRpcId = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedRpcId, actualRpcChannel.getRpcId());
  }

  /**
   * Test RpcCollector {@link RpcCollector#addResponse(Serializable, Member)}.
   * <p>
   * Method under test: {@link RpcCollector#addResponse(Serializable, Member)}
   */
  @Test
  public void testRpcCollectorAddResponse() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1);
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    MemberImpl sender = new MemberImpl();

    // Act
    rpcCollector.addResponse(message, sender);

    // Assert
    ArrayList<Response> responseList = rpcCollector.responses;
    assertEquals(1, responseList.size());
    Response[] responses = rpcCollector.getResponses();
    assertEquals(1, responses.length);
    assertTrue(rpcCollector.isComplete());
    Response response = responses[0];
    assertSame(message, response.getMessage());
    assertSame(sender, response.getSource());
    assertSame(response, responseList.get(0));
  }

  /**
   * Test RpcCollector {@link RpcCollector#equals(Object)}, and {@link RpcCollector#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RpcCollector#equals(Object)}
   *   <li>{@link RpcCollector#hashCode()}
   * </ul>
   */
  @Test
  public void testRpcCollectorEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1);
    RpcCollector rpcCollector2 = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1);

    // Act and Assert
    assertEquals(rpcCollector, rpcCollector2);
    int expectedHashCodeResult = rpcCollector.hashCode();
    assertEquals(expectedHashCodeResult, rpcCollector2.hashCode());
  }

  /**
   * Test RpcCollector {@link RpcCollector#equals(Object)}, and {@link RpcCollector#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RpcCollector#equals(Object)}
   *   <li>{@link RpcCollector#hashCode()}
   * </ul>
   */
  @Test
  public void testRpcCollectorEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1);

    // Act and Assert
    assertEquals(rpcCollector, rpcCollector);
    int expectedHashCodeResult = rpcCollector.hashCode();
    assertEquals(expectedHashCodeResult, rpcCollector.hashCode());
  }

  /**
   * Test RpcCollector {@link RpcCollector#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollector#equals(Object)}
   */
  @Test
  public void testRpcCollectorEquals_whenOtherIsDifferent_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey(new byte[]{1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}),
        1, 1);

    // Act and Assert
    assertNotEquals(rpcCollector, new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1));
  }

  /**
   * Test RpcCollector {@link RpcCollector#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollector#equals(Object)}
   */
  @Test
  public void testRpcCollectorEquals_whenOtherIsNull_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1), null);
  }

  /**
   * Test RpcCollector {@link RpcCollector#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollector#equals(Object)}
   */
  @Test
  public void testRpcCollectorEquals_whenOtherIsWrongType_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1),
        "Different type to RpcCollector");
  }

  /**
   * Test RpcCollector {@link RpcCollector#getResponses()}.
   * <p>
   * Method under test: {@link RpcCollector#getResponses()}
   */
  @Test
  public void testRpcCollectorGetResponses() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1)).getResponses().length);
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1)).isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 2, 1)).isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 3, 1)).isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 0, 1)).isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue((new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 0)).isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete6() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 1, 1);
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    rpcCollector.addResponse(message, new MemberImpl());

    // Act and Assert
    assertTrue(rpcCollector.isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete7() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 2, 1);
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    rpcCollector.addResponse(message, new MemberImpl());

    // Act and Assert
    assertTrue(rpcCollector.isComplete());
  }

  /**
   * Test RpcCollector {@link RpcCollector#isComplete()}.
   * <p>
   * Method under test: {@link RpcCollector#isComplete()}
   */
  @Test
  public void testRpcCollectorIsComplete8() throws UnsupportedEncodingException {
    // Arrange
    RpcCollector rpcCollector = new RpcCollector(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), 3, 1);
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    rpcCollector.addResponse(message, new MemberImpl());

    // Act and Assert
    assertTrue(rpcCollector.isComplete());
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#equals(Object)}, and {@link RpcCollectorKey#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RpcCollectorKey#equals(Object)}
   *   <li>{@link RpcCollectorKey#hashCode()}
   * </ul>
   */
  @Test
  public void testRpcCollectorKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual()
      throws UnsupportedEncodingException {
    // Arrange
    RpcCollectorKey rpcCollectorKey = new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8"));
    RpcCollectorKey rpcCollectorKey2 = new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(rpcCollectorKey, rpcCollectorKey2);
    int expectedHashCodeResult = rpcCollectorKey.hashCode();
    assertEquals(expectedHashCodeResult, rpcCollectorKey2.hashCode());
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#equals(Object)}, and {@link RpcCollectorKey#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RpcCollectorKey#equals(Object)}
   *   <li>{@link RpcCollectorKey#hashCode()}
   * </ul>
   */
  @Test
  public void testRpcCollectorKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual()
      throws UnsupportedEncodingException {
    // Arrange
    RpcCollectorKey rpcCollectorKey = new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(rpcCollectorKey, rpcCollectorKey);
    int expectedHashCodeResult = rpcCollectorKey.hashCode();
    assertEquals(expectedHashCodeResult, rpcCollectorKey.hashCode());
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollectorKey#equals(Object)}
   */
  @Test
  public void testRpcCollectorKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange
    RpcCollectorKey rpcCollectorKey = new RpcCollectorKey(new byte[]{1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertNotEquals(rpcCollectorKey, new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollectorKey#equals(Object)}
   */
  @Test
  public void testRpcCollectorKeyEquals_whenOtherIsNull_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), null);
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcCollectorKey#equals(Object)}
   */
  @Test
  public void testRpcCollectorKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8")), "Different type to RpcCollectorKey");
  }

  /**
   * Test RpcCollectorKey {@link RpcCollectorKey#RpcCollectorKey(byte[])}.
   * <p>
   * Method under test: {@link RpcCollectorKey#RpcCollectorKey(byte[])}
   */
  @Test
  public void testRpcCollectorKeyNewRpcCollectorKey() throws UnsupportedEncodingException {
    // Arrange and Act
    RpcCollectorKey actualRpcCollectorKey = new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRpcCollectorKey.id);
  }

  /**
   * Test RpcCollector {@link RpcCollector#RpcCollector(RpcCollectorKey, int, int)}.
   * <p>
   * Method under test: {@link RpcCollector#RpcCollector(RpcCollectorKey, int, int)}
   */
  @Test
  public void testRpcCollectorNewRpcCollector() throws UnsupportedEncodingException {
    // Arrange
    RpcCollectorKey key = new RpcCollectorKey("AXAXAXAX".getBytes("UTF-8"));

    // Act
    RpcCollector actualRpcCollector = new RpcCollector(key, 1, 1);

    // Assert
    assertEquals(0, actualRpcCollector.getResponses().length);
    assertEquals(1, actualRpcCollector.destcnt);
    assertEquals(1, actualRpcCollector.options);
    assertFalse(actualRpcCollector.isComplete());
    assertTrue(actualRpcCollector.responses.isEmpty());
    byte[] byteArray = actualRpcCollector.key.id;
    assertSame(key.id, byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link RpcChannel#send(Member[], Serializable, int, int, long)}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcChannel#send(Member[], Serializable, int, int, long)}
   */
  @Test
  public void testSend_whenEmptyArrayOfMember_thenReturnArrayLengthIsZero()
      throws UnsupportedEncodingException, ChannelException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());

    // Act and Assert
    assertEquals(0, rpcChannel.send(new Member[]{}, new SimpleDateFormat("yyyy/mm/dd"), 1, 1, 10L).length);
  }

  /**
   * Test {@link RpcChannel#send(Member[], Serializable, int, int, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcChannel#send(Member[], Serializable, int, int, long)}
   */
  @Test
  public void testSend_whenNull_thenReturnArrayLengthIsZero() throws UnsupportedEncodingException, ChannelException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());

    // Act and Assert
    assertEquals(0, rpcChannel.send(null, new SimpleDateFormat("yyyy/mm/dd"), 1, 1, 10L).length);
  }

  /**
   * Test {@link RpcChannel#breakdown()}.
   * <p>
   * Method under test: {@link RpcChannel#breakdown()}
   */
  @Test
  public void testBreakdown() throws UnsupportedEncodingException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());

    // Act
    rpcChannel.breakdown();

    // Assert
    Channel channel2 = rpcChannel.getChannel();
    assertTrue(channel2 instanceof GroupChannel);
    assertTrue(((GroupChannel) channel2).channelListeners.isEmpty());
  }

  /**
   * Test {@link RpcChannel#accept(Serializable, Member)}.
   * <ul>
   *   <li>When {@link RpcMessage#RpcMessage()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcChannel#accept(Serializable, Member)}
   */
  @Test
  public void testAccept_whenRpcMessage() throws UnsupportedEncodingException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());
    RpcMessage msg = new RpcMessage();

    // Act and Assert
    assertFalse(rpcChannel.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link RpcChannel#accept(Serializable, Member)}.
   * <ul>
   *   <li>When {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RpcChannel#accept(Serializable, Member)}
   */
  @Test
  public void testAccept_whenSimpleDateFormatWithYyyyMmDd() throws UnsupportedEncodingException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertFalse(rpcChannel.accept(msg, new MemberImpl()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RpcChannel#setCallback(RpcCallback)}
   *   <li>{@link RpcChannel#setChannel(Channel)}
   *   <li>{@link RpcChannel#setReplyMessageOptions(int)}
   *   <li>{@link RpcChannel#setRpcId(byte[])}
   *   <li>{@link RpcChannel#getCallback()}
   *   <li>{@link RpcChannel#getChannel()}
   *   <li>{@link RpcChannel#getReplyMessageOptions()}
   *   <li>{@link RpcChannel#getRpcId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] rpcId = "AXAXAXAX".getBytes("UTF-8");
    GroupChannel channel = new GroupChannel();
    RpcChannel rpcChannel = new RpcChannel(rpcId, channel, new StaticMembershipProvider());
    StaticMembershipProvider callback = new StaticMembershipProvider();

    // Act
    rpcChannel.setCallback(callback);
    rpcChannel.setChannel(new GroupChannel());
    rpcChannel.setReplyMessageOptions(1);
    byte[] rpcId2 = "AXAXAXAX".getBytes("UTF-8");
    rpcChannel.setRpcId(rpcId2);
    RpcCallback actualCallback = rpcChannel.getCallback();
    Channel actualChannel = rpcChannel.getChannel();
    int actualReplyMessageOptions = rpcChannel.getReplyMessageOptions();
    byte[] actualRpcId = rpcChannel.getRpcId();

    // Assert
    assertTrue(actualCallback instanceof StaticMembershipProvider);
    assertEquals(1, actualReplyMessageOptions);
    assertSame(callback, actualCallback);
    assertSame(rpcId2, actualRpcId);
    assertSame(((GroupChannel) actualChannel).interceptors, actualChannel);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRpcId);
  }
}
