package org.apache.catalina.tribes.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.UniqueId;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.Membership;
import org.junit.Test;

public class ArraysDiffblueTest {
  /**
   * Test {@link Arrays#contains(byte[], int, byte[], int, int)}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#contains(byte[], int, byte[], int, int)}
   */
  @Test
  public void testContains_whenAxaxaxaxBytesIsUtf8_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    byte[] source = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertTrue(Arrays.contains(source, 2, "AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link Arrays#contains(byte[], int, byte[], int, int)}.
   * <ul>
   *   <li>When six.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#contains(byte[], int, byte[], int, int)}
   */
  @Test
  public void testContains_whenSix_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange
    byte[] source = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertFalse(Arrays.contains(source, 6, "AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link Arrays#contains(byte[], int, byte[], int, int)}.
   * <ul>
   *   <li>When {@code X}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#contains(byte[], int, byte[], int, int)}
   */
  @Test
  public void testContains_whenX_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(
        Arrays.contains(new byte[]{'A', 'X', 2, 'X', 'A', 'X', 'A', 'X'}, 2, "AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0, false));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code {65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_whenA_thenReturn65() {
    // Arrange, Act and Assert
    assertEquals("{65}", Arrays.toString(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 1, false));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_whenNull() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(null, 2, 0, false));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code {88, 65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_whenOne_thenReturn8865() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{88, 65}", Arrays.toString("AXAXAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code {88, 65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_whenOne_thenReturn88652() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{88, 65}", Arrays.toString("AXAXAXAX".getBytes("UTF-8"), 1, 3, false));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int, boolean)} with {@code byte[]}, {@code int}, {@code int}, {@code boolean}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code {65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int, boolean)}
   */
  @Test
  public void testToStringWithByteIntIntBoolean_whenTrue_thenReturn65() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65}", Arrays.toString("AXAXAXAX".getBytes("UTF-8"), 2, 3, true));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int)}
   */
  @Test
  public void testToStringWithByteIntInt_whenA_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code {65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int)}
   */
  @Test
  public void testToStringWithByteIntInt_whenAxaxaxaxBytesIsUtf8_thenReturn65() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65}", Arrays.toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int)}
   */
  @Test
  public void testToStringWithByteIntInt_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString((byte[]) null, 2, 0));
  }

  /**
   * Test {@link Arrays#toString(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code {88, 65}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[], int, int)}
   */
  @Test
  public void testToStringWithByteIntInt_whenOne_thenReturn8865() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{88, 65}", Arrays.toString("AXAXAXAX".getBytes("UTF-8"), 1, 3));
  }

  /**
   * Test {@link Arrays#toString(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code {65, 88, 65, 88, 65, 88, 65, 88}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[])}
   */
  @Test
  public void testToStringWithByte_whenAxaxaxaxBytesIsUtf8_thenReturn6588658865886588()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65, 88, 65, 88, 65, 88, 65, 88}", Arrays.toString("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link Arrays#toString(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[])}
   */
  @Test
  public void testToStringWithByte_whenEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(new byte[]{}));
  }

  /**
   * Test {@link Arrays#toString(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(byte[])}
   */
  @Test
  public void testToStringWithByte_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString((byte[]) null));
  }

  /**
   * Test {@link Arrays#toString(Object[], int, int)} with {@code Object[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[], int, int)}
   */
  @Test
  public void testToStringWithObjectIntInt_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(new Object[]{"Data"}, 2, 0));
  }

  /**
   * Test {@link Arrays#toString(Object[], int, int)} with {@code Object[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When array of {@link Object} with {@code Data} and {@code Data}.</li>
   *   <li>Then return {@code {Data}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[], int, int)}
   */
  @Test
  public void testToStringWithObjectIntInt_whenArrayOfObjectWithDataAndData_thenReturnData() {
    // Arrange, Act and Assert
    assertEquals("{Data}", Arrays.toString(new Object[]{"Data", "Data", "Data"}, 2, 3));
  }

  /**
   * Test {@link Arrays#toString(Object[], int, int)} with {@code Object[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[], int, int)}
   */
  @Test
  public void testToStringWithObjectIntInt_whenNull() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString((Object[]) null, 2, 0));
  }

  /**
   * Test {@link Arrays#toString(Object[], int, int)} with {@code Object[]}, {@code int}, {@code int}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code {Data, Data}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[], int, int)}
   */
  @Test
  public void testToStringWithObjectIntInt_whenOne_thenReturnDataData() {
    // Arrange, Act and Assert
    assertEquals("{Data, Data}", Arrays.toString(new Object[]{"Data", "Data", "Data"}, 1, 3));
  }

  /**
   * Test {@link Arrays#toString(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code Data} and {@code Data}.</li>
   *   <li>Then return {@code {Data, Data}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[])}
   */
  @Test
  public void testToStringWithObject_whenArrayOfObjectWithDataAndData_thenReturnDataData() {
    // Arrange, Act and Assert
    assertEquals("{Data, Data}", Arrays.toString(new Object[]{"Data", "Data"}));
  }

  /**
   * Test {@link Arrays#toString(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code Data}.</li>
   *   <li>Then return {@code {Data}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[])}
   */
  @Test
  public void testToStringWithObject_whenArrayOfObjectWithData_thenReturnData() {
    // Arrange, Act and Assert
    assertEquals("{Data}", Arrays.toString(new Object[]{"Data"}));
  }

  /**
   * Test {@link Arrays#toString(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When empty array of {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[])}
   */
  @Test
  public void testToStringWithObject_whenEmptyArrayOfObject() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString(new Object[]{}));
  }

  /**
   * Test {@link Arrays#toString(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toString(Object[])}
   */
  @Test
  public void testToStringWithObject_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toString((Object[]) null));
  }

  /**
   * Test {@link Arrays#toNameString(Member[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return {@code {tcp://{65, 3, 65, 3, 65, 3, 65, 3}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[], int, int)}
   */
  @Test
  public void testToNameStringWithDataOffsetLength_givenA_thenReturnTcp6536536536530() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});
    MemberImpl memberImpl2 = new MemberImpl();

    // Act and Assert
    assertEquals("{tcp://{65, 3, 65, 3, 65, 3, 65, 3}:0}",
        Arrays.toNameString(new Member[]{memberImpl2, new MemberImpl(), memberImpl}, 2, 3));
  }

  /**
   * Test {@link Arrays#toNameString(Member[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[], int, int)}
   */
  @Test
  public void testToNameStringWithDataOffsetLength_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toNameString(new Member[]{new MemberImpl()}, 2, 0));
  }

  /**
   * Test {@link Arrays#toNameString(Member[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>Then return {@code {tcp://{}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[], int, int)}
   */
  @Test
  public void testToNameStringWithDataOffsetLength_thenReturnTcp0() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    MemberImpl memberImpl2 = new MemberImpl();

    // Act and Assert
    assertEquals("{tcp://{}:0}", Arrays.toNameString(new Member[]{memberImpl, memberImpl2, new MemberImpl()}, 2, 3));
  }

  /**
   * Test {@link Arrays#toNameString(Member[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[], int, int)}
   */
  @Test
  public void testToNameStringWithDataOffsetLength_whenNull() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toNameString(null, 2, 0));
  }

  /**
   * Test {@link Arrays#toNameString(Member[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code {tcp://{}:0, tcp://{}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[], int, int)}
   */
  @Test
  public void testToNameStringWithDataOffsetLength_whenOne_thenReturnTcp0Tcp0() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    MemberImpl memberImpl2 = new MemberImpl();

    // Act and Assert
    assertEquals("{tcp://{}:0, tcp://{}:0}",
        Arrays.toNameString(new Member[]{memberImpl, memberImpl2, new MemberImpl()}, 1, 3));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return {@code {tcp://{65, 1, 65, 1, 65, 1, 65, 1}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_givenA_thenReturnTcp6516516516510() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertEquals("{tcp://{65, 1, 65, 1, 65, 1, 65, 1}:0}", Arrays.toNameString(new Member[]{memberImpl}));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>Then return {@code {tcp://{}:0, tcp://{}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_thenReturnTcp0Tcp0() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act and Assert
    assertEquals("{tcp://{}:0, tcp://{}:0}", Arrays.toNameString(new Member[]{memberImpl, new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>Then return {@code {tcp://42:8080}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_thenReturnTcp428080() throws IOException {
    // Arrange, Act and Assert
    assertEquals("{tcp://42:8080}", Arrays.toNameString(new Member[]{new MemberImpl("42", 8080, 1L)}));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code {tcp://{}:0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_whenArrayOfMemberWithMemberImpl_thenReturnTcp0() {
    // Arrange, Act and Assert
    assertEquals("{tcp://{}:0}", Arrays.toNameString(new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_whenEmptyArrayOfMember() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toNameString(new Member[]{}));
  }

  /**
   * Test {@link Arrays#toNameString(Member[])} with {@code data}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#toNameString(Member[])}
   */
  @Test
  public void testToNameStringWithData_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", Arrays.toNameString(null));
  }

  /**
   * Test {@link Arrays#add(int[])}.
   * <p>
   * Method under test: {@link Arrays#add(int[])}
   */
  @Test
  public void testAdd() {
    // Arrange, Act and Assert
    assertEquals(6, Arrays.add(new int[]{2, 1, 2, 1}));
  }

  /**
   * Test {@link Arrays#getUniqudId(byte[])} with {@code data}.
   * <p>
   * Method under test: {@link Arrays#getUniqudId(byte[])}
   */
  @Test
  public void testGetUniqudIdWithData() throws UnsupportedEncodingException {
    // Arrange and Act
    UniqueId actualUniqudId = Arrays.getUniqudId("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualUniqudId.getBytes());
  }

  /**
   * Test {@link Arrays#equals(byte[], byte[])} with {@code byte[]}, {@code byte[]}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(byte[], byte[])}
   */
  @Test
  public void testEqualsWithByteByte_whenA_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(Arrays.equals(new byte[]{4, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link Arrays#equals(byte[], byte[])} with {@code byte[]}, {@code byte[]}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(byte[], byte[])}
   */
  @Test
  public void testEqualsWithByteByte_whenAxaxaxaxBytesIsUtf8_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    byte[] o1 = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertTrue(Arrays.equals(o1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link Arrays#equals(Object[], Object[])} with {@code Object[]}, {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code O1} and {@code O1}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(Object[], Object[])}
   */
  @Test
  public void testEqualsWithObjectObject_whenArrayOfObjectWithO1AndO1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Arrays.equals(new Object[]{"O1", "O1"}, new Object[]{"O2", "O2"}));
  }

  /**
   * Test {@link Arrays#equals(Object[], Object[])} with {@code Object[]}, {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code O1}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(Object[], Object[])}
   */
  @Test
  public void testEqualsWithObjectObject_whenArrayOfObjectWithO1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Arrays.equals(new Object[]{"O1"}, new Object[]{"O2"}));
  }

  /**
   * Test {@link Arrays#equals(Object[], Object[])} with {@code Object[]}, {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code O2}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(Object[], Object[])}
   */
  @Test
  public void testEqualsWithObjectObject_whenArrayOfObjectWithO2_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(Arrays.equals(new Object[]{"O2"}, new Object[]{"O2"}));
  }

  /**
   * Test {@link Arrays#equals(Object[], Object[])} with {@code Object[]}, {@code Object[]}.
   * <ul>
   *   <li>When empty array of {@link Object}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#equals(Object[], Object[])}
   */
  @Test
  public void testEqualsWithObjectObject_whenEmptyArrayOfObject_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Arrays.equals(new Object[]{}, new Object[]{"O2"}));
  }

  /**
   * Test {@link Arrays#sameMembers(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()} and {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#sameMembers(Member[], Member[])}
   */
  @Test
  public void testSameMembers_whenArrayOfMemberWithMemberImplAndMemberImpl_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act and Assert
    assertFalse(Arrays.sameMembers(new Member[]{memberImpl, new MemberImpl()}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#sameMembers(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#sameMembers(Member[], Member[])}
   */
  @Test
  public void testSameMembers_whenArrayOfMemberWithMemberImpl_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(Arrays.sameMembers(new Member[]{new MemberImpl()}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#sameMembers(Member[], Member[])}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#sameMembers(Member[], Member[])}
   */
  @Test
  public void testSameMembers_whenEmptyArrayOfMember_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Arrays.sameMembers(new Member[]{}, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#merge(Member[], Member[])}.
   * <ul>
   *   <li>Then return array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#merge(Member[], Member[])}
   */
  @Test
  public void testMerge_thenReturnArrayLengthIsTwo() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    MemberImpl memberImpl2 = new MemberImpl();

    // Act
    Member[] actualMergeResult = Arrays.merge(new Member[]{memberImpl, memberImpl2}, new Member[]{new MemberImpl()});

    // Assert
    Member member = actualMergeResult[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(2, actualMergeResult.length);
    assertSame(memberImpl2, actualMergeResult[1]);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link Arrays#merge(Member[], Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#merge(Member[], Member[])}
   */
  @Test
  public void testMerge_whenArrayOfMemberWithMemberImpl_thenReturnArrayLengthIsOne() {
    // Arrange and Act
    Member[] actualMergeResult = Arrays.merge(new Member[]{new MemberImpl()}, new Member[]{new MemberImpl()});

    // Assert
    Member member = actualMergeResult[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualMergeResult.length);
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getHost());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, member.getUniqueId());
  }

  /**
   * Test {@link Arrays#fill(Membership, Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@code null}.</li>
   *   <li>Then first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#fill(Membership, Member[])}
   */
  @Test
  public void testFill_whenArrayOfMemberWithNull_thenFirstElementIsNull() {
    // Arrange
    Membership mbrship = new Membership(new MemberImpl());

    // Act
    Arrays.fill(mbrship, new Member[]{null});

    // Assert
    Member[] members = mbrship.getMembers();
    assertNull(members[0]);
    assertEquals(1, members.length);
    assertTrue(mbrship.hasMembers());
  }

  /**
   * Test {@link Arrays#diff(Membership, Membership, Member)}.
   * <ul>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#diff(Membership, Membership, Member)}
   */
  @Test
  public void testDiff_thenReturnFirstElementIsNull() {
    // Arrange
    Membership complete = new Membership(null, true);

    Membership local = new Membership(new MemberImpl());

    // Act
    Member[] actualDiffResult = Arrays.diff(complete, local, new MemberImpl());

    // Assert
    assertNull(actualDiffResult[0]);
    assertEquals(1, actualDiffResult.length);
  }

  /**
   * Test {@link Arrays#diff(Membership, Membership, Member)}.
   * <ul>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#diff(Membership, Membership, Member)}
   */
  @Test
  public void testDiff_thenReturnFirstElementIsNull2() {
    // Arrange
    Membership complete = new Membership(null, true);

    // Act
    Member[] actualDiffResult = Arrays.diff(complete, new Membership(new MemberImpl()), null);

    // Assert
    assertNull(actualDiffResult[0]);
    assertEquals(1, actualDiffResult.length);
  }

  /**
   * Test {@link Arrays#diff(Membership, Membership, Member)}.
   * <ul>
   *   <li>When {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#diff(Membership, Membership, Member)}
   */
  @Test
  public void testDiff_whenMembershipWithLocalIsMemberImpl_thenReturnArrayLengthIsZero() {
    // Arrange
    Membership complete = new Membership(new MemberImpl());
    Membership local = new Membership(new MemberImpl());

    // Act and Assert
    assertEquals(0, Arrays.diff(complete, local, new MemberImpl()).length);
  }

  /**
   * Test {@link Arrays#remove(Member[], Member)}.
   * <p>
   * Method under test: {@link Arrays#remove(Member[], Member)}
   */
  @Test
  public void testRemove() {
    // Arrange, Act and Assert
    assertEquals(0, Arrays.remove(new Member[]{new MemberImpl()}, new MemberImpl()).length);
  }

  /**
   * Test {@link Arrays#extract(Member[], Member[])}.
   * <p>
   * Method under test: {@link Arrays#extract(Member[], Member[])}
   */
  @Test
  public void testExtract() {
    // Arrange, Act and Assert
    assertEquals(0, Arrays.extract(new Member[]{new MemberImpl()}, new Member[]{new MemberImpl()}).length);
  }

  /**
   * Test {@link Arrays#indexOf(Member, Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@code null}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#indexOf(Member, Member[])}
   */
  @Test
  public void testIndexOf_whenArrayOfMemberWithNull_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, Arrays.indexOf(new MemberImpl(), new Member[]{null}));
  }

  /**
   * Test {@link Arrays#indexOf(Member, Member[])}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#indexOf(Member, Member[])}
   */
  @Test
  public void testIndexOf_whenMemberImpl_thenReturnZero() {
    // Arrange
    MemberImpl member = new MemberImpl();

    // Act and Assert
    assertEquals(0, Arrays.indexOf(member, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#nextIndex(Member, Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#nextIndex(Member, Member[])}
   */
  @Test
  public void testNextIndex_whenArrayOfMemberWithMemberImpl_thenReturnZero() {
    // Arrange
    MemberImpl member = new MemberImpl();

    // Act and Assert
    assertEquals(0, Arrays.nextIndex(member, new Member[]{new MemberImpl()}));
  }

  /**
   * Test {@link Arrays#nextIndex(Member, Member[])}.
   * <ul>
   *   <li>When array of {@link Member} with {@code null}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#nextIndex(Member, Member[])}
   */
  @Test
  public void testNextIndex_whenArrayOfMemberWithNull_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, Arrays.nextIndex(new MemberImpl(), new Member[]{null}));
  }

  /**
   * Test {@link Arrays#nextIndex(Member, Member[])}.
   * <ul>
   *   <li>When empty array of {@link Member}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#nextIndex(Member, Member[])}
   */
  @Test
  public void testNextIndex_whenEmptyArrayOfMember_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, Arrays.nextIndex(new MemberImpl(), new Member[]{}));
  }

  /**
   * Test {@link Arrays#hashCode(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code -1753799331}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#hashCode(byte[])}
   */
  @Test
  public void testHashCodeWithByte_whenAxaxaxaxBytesIsUtf8_thenReturn1753799331() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1753799331, Arrays.hashCode("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link Arrays#hashCode(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#hashCode(byte[])}
   */
  @Test
  public void testHashCodeWithByte_whenNull_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, Arrays.hashCode(null));
  }

  /**
   * Test {@link Arrays#fromString(String)}.
   * <ul>
   *   <li>When {@code {42}.</li>
   *   <li>Then return array of {@code byte} with {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#fromString(String)}
   */
  @Test
  public void testFromString_when42_thenReturnArrayOfByteWithAsterisk() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{'*'}, Arrays.fromString("{42"));
  }

  /**
   * Test {@link Arrays#fromString(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#fromString(String)}
   */
  @Test
  public void testFromString_when42_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> Arrays.fromString("42"));
  }

  /**
   * Test {@link Arrays#fromString(String)}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then return empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#fromString(String)}
   */
  @Test
  public void testFromString_whenLeftCurlyBracket_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{}, Arrays.fromString("{"));
  }

  /**
   * Test {@link Arrays#fromString(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Arrays#fromString(String)}
   */
  @Test
  public void testFromString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Arrays.fromString(null));
  }

  /**
   * Test {@link Arrays#convert(String)}.
   * <p>
   * Method under test: {@link Arrays#convert(String)}
   */
  @Test
  public void testConvert() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualConvertResult = Arrays.convert("foo");

    // Assert
    assertArrayEquals("foo".getBytes("UTF-8"), actualConvertResult);
  }
}
