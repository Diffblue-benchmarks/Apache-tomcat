package org.apache.catalina.tribes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class ChannelDiffblueTest {
  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code async}.</li>
   *   <li>Then return eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenAsync_thenReturnEight() {
    // Arrange, Act and Assert
    assertEquals(8, Channel.getSendOptionValue("async"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code asynchronous}.</li>
   *   <li>Then return eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenAsynchronous_thenReturnEight() {
    // Arrange, Act and Assert
    assertEquals(8, Channel.getSendOptionValue("asynchronous"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code byte_message}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenByteMessage_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, Channel.getSendOptionValue("byte_message"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code byte}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenByte_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, Channel.getSendOptionValue("byte"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code multicast}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_MULTICAST}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenMulticast_thenReturnSend_options_multicast() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_MULTICAST, Channel.getSendOptionValue("multicast"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code Opt}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenOpt_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Channel.getSendOptionValue("Opt"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code secure}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_SECURE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenSecure_thenReturnSend_options_secure() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_SECURE, Channel.getSendOptionValue("secure"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code sync}.</li>
   *   <li>Then return four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenSync_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4, Channel.getSendOptionValue("sync"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code synchronized_ack}.</li>
   *   <li>Then return four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenSynchronizedAck_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4, Channel.getSendOptionValue("synchronized_ack"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code udp}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_UDP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenUdp_thenReturnSend_options_udp() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_UDP, Channel.getSendOptionValue("udp"));
  }

  /**
   * Test {@link Channel#getSendOptionValue(String)}.
   * <ul>
   *   <li>When {@code use_ack}.</li>
   *   <li>Then return two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#getSendOptionValue(String)}
   */
  @Test
  public void testGetSendOptionValue_whenUseAck_thenReturnTwo() {
    // Arrange, Act and Assert
    assertEquals(2, Channel.getSendOptionValue("use_ack"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_when42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42, Channel.parseSendOptions("42"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code async}.</li>
   *   <li>Then return eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenAsync_thenReturnEight() {
    // Arrange, Act and Assert
    assertEquals(8, Channel.parseSendOptions("async"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code asynchronous}.</li>
   *   <li>Then return eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenAsynchronous_thenReturnEight() {
    // Arrange, Act and Assert
    assertEquals(8, Channel.parseSendOptions("asynchronous"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code byte_message}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenByteMessage_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, Channel.parseSendOptions("byte_message"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code byte}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenByte_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, Channel.parseSendOptions("byte"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code Input}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenInput_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Channel.parseSendOptions("Input"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code multicast}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_MULTICAST}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenMulticast_thenReturnSend_options_multicast() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_MULTICAST, Channel.parseSendOptions("multicast"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code secure}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_SECURE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenSecure_thenReturnSend_options_secure() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_SECURE, Channel.parseSendOptions("secure"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code sync}.</li>
   *   <li>Then return four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenSync_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4, Channel.parseSendOptions("sync"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code synchronized_ack}.</li>
   *   <li>Then return four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenSynchronizedAck_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4, Channel.parseSendOptions("synchronized_ack"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code udp}.</li>
   *   <li>Then return {@link Channel#SEND_OPTIONS_UDP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenUdp_thenReturnSend_options_udp() {
    // Arrange, Act and Assert
    assertEquals(Channel.SEND_OPTIONS_UDP, Channel.parseSendOptions("udp"));
  }

  /**
   * Test {@link Channel#parseSendOptions(String)}.
   * <ul>
   *   <li>When {@code use_ack}.</li>
   *   <li>Then return two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Channel#parseSendOptions(String)}
   */
  @Test
  public void testParseSendOptions_whenUseAck_thenReturnTwo() {
    // Arrange, Act and Assert
    assertEquals(2, Channel.parseSendOptions("use_ack"));
  }

  /**
   * Test {@link Channel#getSendOptionsAsString(int)}.
   * <p>
   * Method under test: {@link Channel#getSendOptionsAsString(int)}
   */
  @Test
  public void testGetSendOptionsAsString() {
    // Arrange, Act and Assert
    assertEquals("byte", Channel.getSendOptionsAsString(1));
  }
}
