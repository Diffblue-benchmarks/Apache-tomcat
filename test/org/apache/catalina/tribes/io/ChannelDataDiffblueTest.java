package org.apache.catalina.tribes.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class ChannelDataDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ChannelData#ChannelData(byte[], XByteBuffer, long)}
   *   <li>{@link ChannelData#setAddress(Member)}
   *   <li>{@link ChannelData#setMessage(XByteBuffer)}
   *   <li>{@link ChannelData#setOptions(int)}
   *   <li>{@link ChannelData#setTimestamp(long)}
   *   <li>{@link ChannelData#setUniqueId(byte[])}
   *   <li>{@link ChannelData#toString()}
   *   <li>{@link ChannelData#getAddress()}
   *   <li>{@link ChannelData#getMessage()}
   *   <li>{@link ChannelData#getOptions()}
   *   <li>{@link ChannelData#getTimestamp()}
   *   <li>{@link ChannelData#getUniqueId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ChannelData actualChannelData = new ChannelData(uniqueId, new XByteBuffer(3, true), 10L);
    MemberImpl address = new MemberImpl();
    actualChannelData.setAddress(address);
    XByteBuffer message = new XByteBuffer(3, true);

    actualChannelData.setMessage(message);
    actualChannelData.setOptions(1);
    actualChannelData.setTimestamp(10L);
    byte[] uniqueId2 = "AXAXAXAX".getBytes("UTF-8");
    actualChannelData.setUniqueId(uniqueId2);
    actualChannelData.toString();
    Member actualAddress = actualChannelData.getAddress();
    XByteBuffer actualMessage = actualChannelData.getMessage();
    int actualOptions = actualChannelData.getOptions();
    long actualTimestamp = actualChannelData.getTimestamp();
    byte[] actualUniqueId = actualChannelData.getUniqueId();

    // Assert
    assertEquals(1, actualOptions);
    assertEquals(10L, actualTimestamp);
    assertSame(message, actualMessage);
    assertSame(address, actualAddress);
    assertSame(uniqueId2, actualUniqueId);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualUniqueId);
  }

  /**
   * Test {@link ChannelData#ChannelData()}.
   * <p>
   * Method under test: {@link ChannelData#ChannelData()}
   */
  @Test
  public void testNewChannelData() {
    // Arrange and Act
    ChannelData actualChannelData = new ChannelData();

    // Assert
    assertNull(actualChannelData.getAddress());
    assertNull(actualChannelData.getMessage());
    assertEquals(0, actualChannelData.getOptions());
    assertEquals(0L, actualChannelData.getTimestamp());
  }

  /**
   * Test {@link ChannelData#ChannelData(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return UniqueId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#ChannelData(boolean)}
   */
  @Test
  public void testNewChannelData_whenFalse_thenReturnUniqueIdIsNull() {
    // Arrange and Act
    ChannelData actualChannelData = new ChannelData(false);

    // Assert
    assertNull(actualChannelData.getUniqueId());
    assertNull(actualChannelData.getAddress());
    assertNull(actualChannelData.getMessage());
    assertEquals(0, actualChannelData.getOptions());
    assertEquals(0L, actualChannelData.getTimestamp());
  }

  /**
   * Test {@link ChannelData#ChannelData(boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#ChannelData(boolean)}
   */
  @Test
  public void testNewChannelData_whenTrue() {
    // Arrange and Act
    ChannelData actualChannelData = new ChannelData(true);

    // Assert
    assertNull(actualChannelData.getAddress());
    assertNull(actualChannelData.getMessage());
    assertEquals(0, actualChannelData.getOptions());
    assertEquals(0L, actualChannelData.getTimestamp());
  }

  /**
   * Test {@link ChannelData#equals(Object)}, and {@link ChannelData#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ChannelData#equals(Object)}
   *   <li>{@link ChannelData#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ChannelData channelData = new ChannelData();

    // Act and Assert
    assertEquals(channelData, channelData);
    int expectedHashCodeResult = channelData.hashCode();
    assertEquals(expectedHashCodeResult, channelData.hashCode());
  }

  /**
   * Test {@link ChannelData#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ChannelData channelData = new ChannelData();

    // Act and Assert
    assertNotEquals(channelData, new ChannelData());
  }

  /**
   * Test {@link ChannelData#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ChannelData(), null);
  }

  /**
   * Test {@link ChannelData#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ChannelData(), "Different type to ChannelData");
  }

  /**
   * Test {@link ChannelData#clone()}.
   * <ul>
   *   <li>Given {@link ChannelData#ChannelData()}.</li>
   *   <li>Then return {@link ChannelData#ChannelData()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#clone()}
   */
  @Test
  public void testClone_givenChannelData_thenReturnChannelData() {
    // Arrange
    ChannelData channelData = new ChannelData();

    // Act and Assert
    assertEquals(channelData, channelData.clone());
  }

  /**
   * Test {@link ChannelData#clone()}.
   * <ul>
   *   <li>Then return Message Capacity is one hundred thirty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#clone()}
   */
  @Test
  public void testClone_thenReturnMessageCapacityIsOneHundredThirtyOne() {
    // Arrange
    ChannelData channelData = new ChannelData();
    channelData.setMessage(new XByteBuffer(3, true));

    // Act and Assert
    XByteBuffer message = channelData.clone().getMessage();
    assertEquals(131, message.getCapacity());
    assertEquals(131, message.getBytesDirect().length);
    assertEquals(3, message.getLength());
    assertFalse(message.getDiscard());
    assertArrayEquals(new byte[]{0, 0, 0}, message.getBytes());
  }

  /**
   * Test {@link ChannelData#sendAckSync(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckSync(int)}
   */
  @Test
  public void testSendAckSync_whenMinusOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ChannelData.sendAckSync(-1));
  }

  /**
   * Test {@link ChannelData#sendAckSync(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckSync(int)}
   */
  @Test
  public void testSendAckSync_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ChannelData.sendAckSync(1));
  }

  /**
   * Test {@link ChannelData#sendAckSync(int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckSync(int)}
   */
  @Test
  public void testSendAckSync_whenTwo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ChannelData.sendAckSync(2));
  }

  /**
   * Test {@link ChannelData#sendAckAsync(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckAsync(int)}
   */
  @Test
  public void testSendAckAsync_whenMinusOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ChannelData.sendAckAsync(-1));
  }

  /**
   * Test {@link ChannelData#sendAckAsync(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckAsync(int)}
   */
  @Test
  public void testSendAckAsync_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ChannelData.sendAckAsync(1));
  }

  /**
   * Test {@link ChannelData#sendAckAsync(int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#sendAckAsync(int)}
   */
  @Test
  public void testSendAckAsync_whenTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ChannelData.sendAckAsync(2));
  }

  /**
   * Test {@link ChannelData#bToS(byte[])}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code {65 88 65 88 65 88 65 88 }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#bToS(byte[])}
   */
  @Test
  public void testBToS_whenAxaxaxaxBytesIsUtf8_thenReturn6588658865886588() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65 88 65 88 65 88 65 88 }", ChannelData.bToS("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link ChannelData#bToS(byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelData#bToS(byte[])}
   */
  @Test
  public void testBToS_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", ChannelData.bToS(null));
  }
}
