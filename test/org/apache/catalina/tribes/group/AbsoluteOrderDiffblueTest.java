package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.AbsoluteOrder.AbsoluteComparator;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class AbsoluteOrderDiffblueTest {
  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte, byte)} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte, byte)}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithB1B2_whenA_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new AbsoluteComparator()).compareBytes((byte) 'A', (byte) 'A'));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte, byte)} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte, byte)}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithB1B2_whenMinusOne_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AbsoluteComparator()).compareBytes((byte) -1, (byte) 'A'));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte, byte)} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When {@code X}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte, byte)}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithB1B2_whenX_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new AbsoluteComparator()).compareBytes((byte) 'X', (byte) 'A'));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte, byte)} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte, byte)}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithB1B2_whenZero_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AbsoluteComparator()).compareBytes((byte) 0, (byte) 'A'));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte[], byte[])} with {@code d1}, {@code d2}.
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte[], byte[])}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithD1D2() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    byte[] d1 = "A\bA\bA\bA\bA\bA\bA\bA\b".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, absoluteComparator.compareBytes(d1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte[], byte[])} with {@code d1}, {@code d2}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte[], byte[])}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithD1D2_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    byte[] d1 = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, absoluteComparator.compareBytes(d1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte[], byte[])} with {@code d1}, {@code d2}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte[], byte[])}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithD1D2_whenEmptyArrayOfByte() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareBytes(new byte[]{}, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte[], byte[])} with {@code d1}, {@code d2}.
   * <ul>
   *   <li>When {@code XAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte[], byte[])}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithD1D2_whenXaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    byte[] d1 = "\bXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareBytes(d1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareBytes(byte[], byte[])} with {@code d1}, {@code d2}.
   * <ul>
   *   <li>When {@code XXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareBytes(byte[], byte[])}
   */
  @Test
  public void testAbsoluteComparatorCompareBytesWithD1D2_whenXxaxaxaxBytesIsUtf8_thenReturnOne()
      throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    byte[] d1 = "XXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, absoluteComparator.compareBytes(d1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIds(Member, Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIds(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIds_givenA_thenReturnMinusOne() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareIds(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIds(Member, Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIds(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIds_givenA_thenReturnMinusOne2() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    MemberImpl m2 = new MemberImpl();
    m2.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareIds(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIds(Member, Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIds(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIds_givenA_thenReturnOne() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    MemberImpl m2 = new MemberImpl();
    m2.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(1, absoluteComparator.compareIds(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIds(Member, Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIds(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIds_givenA_thenReturnOne2() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(1, absoluteComparator.compareIds(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIds(Member, Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIds(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIds_whenMemberImpl_thenReturnZero() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    // Act and Assert
    assertEquals(0, absoluteComparator.compareIds(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareInts(int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareInts(int, int)}
   */
  @Test
  public void testAbsoluteComparatorCompareInts_whenMinusOne_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AbsoluteComparator()).compareInts(-1, 3));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareInts(int, int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareInts(int, int)}
   */
  @Test
  public void testAbsoluteComparatorCompareInts_whenOne_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AbsoluteComparator()).compareInts(1, 3));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareInts(int, int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareInts(int, int)}
   */
  @Test
  public void testAbsoluteComparatorCompareInts_whenOne_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new AbsoluteComparator()).compareInts(3, 1));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareInts(int, int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareInts(int, int)}
   */
  @Test
  public void testAbsoluteComparatorCompareInts_whenThree_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new AbsoluteComparator()).compareInts(3, 3));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareInts(int, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareInts(int, int)}
   */
  @Test
  public void testAbsoluteComparatorCompareInts_whenZero_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AbsoluteComparator()).compareInts(0, 3));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>Given {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_givenAxaxaxaxBytesIsUtf8_thenReturnMinusOne()
      throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    MemberImpl m2 = new MemberImpl();
    m2.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareIps(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>Given {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_givenAxaxaxaxBytesIsUtf8_thenReturnOne()
      throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(1, absoluteComparator.compareIps(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>Given {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_givenAxaxaxaxBytesIsUtf8_thenReturnZero()
      throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setHost("AXAXAXAX".getBytes("UTF-8"));

    MemberImpl m2 = new MemberImpl();
    m2.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0, absoluteComparator.compareIps(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>Given {@code XAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_givenXaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setHost("\bXAXAXAX".getBytes("UTF-8"));

    MemberImpl m2 = new MemberImpl();
    m2.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(-1, absoluteComparator.compareIps(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>Given {@code XXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_givenXxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setHost("XXAXAXAX".getBytes("UTF-8"));

    MemberImpl m2 = new MemberImpl();
    m2.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(1, absoluteComparator.compareIps(m1, m2));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compareIps(Member, Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compareIps(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareIps_whenMemberImpl_thenReturnZero() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    // Act and Assert
    assertEquals(0, absoluteComparator.compareIps(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#comparePorts(Member, Member)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#comparePorts(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorComparePorts_thenReturnMinusOne() throws IOException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl("42", -1, 1L);

    // Act and Assert
    assertEquals(-1, absoluteComparator.comparePorts(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#comparePorts(Member, Member)}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#comparePorts(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorComparePorts_thenReturnOne() throws IOException {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl("42", 8080, 1L);

    // Act and Assert
    assertEquals(1, absoluteComparator.comparePorts(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#comparePorts(Member, Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#comparePorts(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorComparePorts_whenMemberImpl_thenReturnZero() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    // Act and Assert
    assertEquals(0, absoluteComparator.comparePorts(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compare(Member, Member)} with {@code m1}, {@code m2}.
   * <p>
   * Method under test: {@link AbsoluteComparator#compare(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareWithM1M2() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setHost(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(1, absoluteComparator.compare(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compare(Member, Member)} with {@code m1}, {@code m2}.
   * <ul>
   *   <li>Given array of {@code byte} with minus one and sixteen.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compare(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareWithM1M2_givenArrayOfByteWithMinusOneAndSixteen() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setUniqueId(new byte[]{-1, 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(-1, absoluteComparator.compare(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compare(Member, Member)} with {@code m1}, {@code m2}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compare(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareWithM1M2_thenReturnMinusOne() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(-1, absoluteComparator.compare(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compare(Member, Member)} with {@code m1}, {@code m2}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compare(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareWithM1M2_thenReturnOne() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();

    MemberImpl m1 = new MemberImpl();
    m1.setUniqueId(new byte[]{'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16, 'A', 16});

    // Act and Assert
    assertEquals(1, absoluteComparator.compare(m1, new MemberImpl()));
  }

  /**
   * Test AbsoluteComparator {@link AbsoluteComparator#compare(Member, Member)} with {@code m1}, {@code m2}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbsoluteComparator#compare(Member, Member)}
   */
  @Test
  public void testAbsoluteComparatorCompareWithM1M2_whenMemberImpl_thenReturnZero() {
    // Arrange
    AbsoluteComparator absoluteComparator = new AbsoluteComparator();
    MemberImpl m1 = new MemberImpl();

    // Act and Assert
    assertEquals(0, absoluteComparator.compare(m1, new MemberImpl()));
  }
}
