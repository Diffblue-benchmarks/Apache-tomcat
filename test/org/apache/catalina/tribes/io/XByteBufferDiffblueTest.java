package org.apache.catalina.tribes.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import org.junit.Test;

public class XByteBufferDiffblueTest {
  /**
   * Test {@link XByteBuffer#XByteBuffer(byte[], int, boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#XByteBuffer(byte[], int, boolean)}
   */
  @Test
  public void testNewXByteBuffer() throws UnsupportedEncodingException {
    // Arrange and Act
    XByteBuffer actualXByteBuffer = new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), 3, true);

    // Assert
    assertEquals(8, actualXByteBuffer.getCapacity());
    assertEquals(8, actualXByteBuffer.getLength());
    assertTrue(actualXByteBuffer.getDiscard());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualXByteBuffer.getBytes());
    byte[] expectedBytesDirect = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytesDirect, actualXByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#XByteBuffer(byte[], boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#XByteBuffer(byte[], boolean)}
   */
  @Test
  public void testNewXByteBuffer2() throws UnsupportedEncodingException {
    // Arrange and Act
    XByteBuffer actualXByteBuffer = new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true);

    // Assert
    assertEquals(136, actualXByteBuffer.getCapacity());
    assertEquals(136, actualXByteBuffer.getBytesDirect().length);
    assertEquals(8, actualXByteBuffer.getLength());
    assertTrue(actualXByteBuffer.getDiscard());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualXByteBuffer.getBytes());
  }

  /**
   * Test {@link XByteBuffer#XByteBuffer(int, boolean)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return Length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#XByteBuffer(int, boolean)}
   */
  @Test
  public void testNewXByteBuffer_whenThree_thenReturnLengthIsZero() {
    // Arrange and Act
    XByteBuffer actualXByteBuffer = new XByteBuffer(3, true);

    // Assert
    assertEquals(0, actualXByteBuffer.getLength());
    assertEquals(3, actualXByteBuffer.getCapacity());
    assertTrue(actualXByteBuffer.getDiscard());
    assertArrayEquals(new byte[]{}, actualXByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0}, actualXByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#setLength(int)}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} Length is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#setLength(int)}
   */
  @Test
  public void testSetLength_thenXByteBufferWithSizeIsThreeAndDiscardIsTrueLengthIsThree() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    xByteBuffer.setLength(3);

    // Assert
    assertEquals(3, xByteBuffer.getLength());
    assertArrayEquals(new byte[]{0, 0, 0}, xByteBuffer.getBytes());
  }

  /**
   * Test {@link XByteBuffer#trim(int)}.
   * <p>
   * Method under test: {@link XByteBuffer#trim(int)}
   */
  @Test
  public void testTrim() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(new byte[]{'A', -3, 'A', -3, 'A', -3, 'A', -3}, true);

    // Act
    xByteBuffer.trim(3);

    // Assert
    assertEquals(5, xByteBuffer.getLength());
    assertArrayEquals(new byte[]{'A', -3, 'A', -3, 'A'}, xByteBuffer.getBytes());
  }

  /**
   * Test {@link XByteBuffer#getBytes()}.
   * <p>
   * Method under test: {@link XByteBuffer#getBytes()}
   */
  @Test
  public void testGetBytes() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{}, (new XByteBuffer(3, true)).getBytes());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XByteBuffer#setDiscard(boolean)}
   *   <li>{@link XByteBuffer#clear()}
   *   <li>{@link XByteBuffer#reset()}
   *   <li>{@link XByteBuffer#getBytesDirect()}
   *   <li>{@link XByteBuffer#getDiscard()}
   *   <li>{@link XByteBuffer#getLength()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    xByteBuffer.setDiscard(true);
    xByteBuffer.clear();
    xByteBuffer.reset();
    byte[] actualBytesDirect = xByteBuffer.getBytesDirect();
    boolean actualDiscard = xByteBuffer.getDiscard();

    // Assert
    assertEquals(0, xByteBuffer.getLength());
    assertTrue(actualDiscard);
    assertArrayEquals(new byte[]{0, 0, 0}, actualBytesDirect);
  }

  /**
   * Test {@link XByteBuffer#append(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#append(boolean)}
   */
  @Test
  public void testAppendWithBoolean() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(true);

    // Assert
    assertEquals(1, xByteBuffer.getLength());
    assertEquals(3, xByteBuffer.getCapacity());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{1, 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#append(boolean)}
   */
  @Test
  public void testAppendWithBoolean2() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(false);

    // Assert
    assertEquals(1, xByteBuffer.getLength());
    assertEquals(3, xByteBuffer.getCapacity());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{0}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(boolean)} with {@code boolean}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is zero and discard is {@code true} Capacity is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(boolean)}
   */
  @Test
  public void testAppendWithBoolean_thenXByteBufferWithSizeIsZeroAndDiscardIsTrueCapacityIsOne() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(0, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(true);

    // Assert
    assertEquals(1, xByteBuffer.getCapacity());
    assertEquals(1, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{1}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(ByteBuffer, int)} with {@code ByteBuffer}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(ByteBuffer, int)}
   */
  @Test
  public void testAppendWithByteBufferInt() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 3);

    // Assert
    assertEquals(3, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{'A', 3, 'A'}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{'A', 3, 'A'}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(ByteBuffer, int)} with {@code ByteBuffer}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(ByteBuffer, int)}
   */
  @Test
  public void testAppendWithByteBufferInt2() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, false);

    // Act
    boolean actualAppendResult = xByteBuffer.append(ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 3);

    // Assert
    assertEquals(3, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{'A', 3, 'A'}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{'A', 3, 'A'}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(ByteBuffer, int)} with {@code ByteBuffer}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(ByteBuffer, int)}
   */
  @Test
  public void testAppendWithByteBufferInt3() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 3);

    // Assert
    assertEquals(0, xByteBuffer.getLength());
    byte[] bytesDirect = xByteBuffer.getBytesDirect();
    assertEquals(136, bytesDirect.length);
    assertEquals((byte) 3, bytesDirect[9]);
    assertFalse(actualAppendResult);
    assertEquals('A', bytesDirect[10]);
    assertEquals('A', bytesDirect[8]);
    assertArrayEquals(new byte[]{}, xByteBuffer.getBytes());
  }

  /**
   * Test {@link XByteBuffer#append(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(byte[], int, int)}
   */
  @Test
  public void testAppendWithByteIntInt() throws UnsupportedEncodingException {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    byte[] expectedBytes = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, xByteBuffer.getBytes());
    byte[] expectedBytesDirect = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytesDirect, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(byte[], int, int)}
   */
  @Test
  public void testAppendWithByteIntInt2() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.setDiscard(false);

    // Act
    boolean actualAppendResult = xByteBuffer.append(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 0, 0);

    // Assert
    assertEquals(0, xByteBuffer.getLength());
    assertFalse(actualAppendResult);
    assertArrayEquals(new byte[]{}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(byte[], int, int)}
   */
  @Test
  public void testAppendWithByteIntInt3() throws UnsupportedEncodingException {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(1, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, xByteBuffer.getCapacity());
    assertEquals(3, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    byte[] expectedBytes = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, xByteBuffer.getBytes());
    byte[] expectedBytesDirect = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytesDirect, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(byte[], int, int)}
   */
  @Test
  public void testAppendWithByteIntInt4() throws UnsupportedEncodingException {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, false);

    // Act
    boolean actualAppendResult = xByteBuffer.append("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    byte[] expectedBytes = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, xByteBuffer.getBytes());
    byte[] expectedBytesDirect = "XAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytesDirect, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(byte[], int, int)} with {@code byte[]}, {@code int}, {@code int}.
   * <p>
   * Method under test: {@link XByteBuffer#append(byte[], int, int)}
   */
  @Test
  public void testAppendWithByteIntInt5() throws UnsupportedEncodingException {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true);

    // Act
    xByteBuffer.append("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, xByteBuffer.getLength());
    byte[] bytesDirect = xByteBuffer.getBytesDirect();
    assertEquals(136, bytesDirect.length);
    assertEquals('A', bytesDirect[9]);
    assertEquals('X', bytesDirect[10]);
    assertEquals('X', bytesDirect[8]);
    assertArrayEquals(new byte[]{}, xByteBuffer.getBytes());
  }

  /**
   * Test {@link XByteBuffer#append(byte)} with {@code byte}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} Length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(byte)}
   */
  @Test
  public void testAppendWithByte_thenXByteBufferWithSizeIsThreeAndDiscardIsTrueLengthIsOne() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append((byte) 'A');

    // Assert
    assertEquals(1, xByteBuffer.getLength());
    assertEquals(3, xByteBuffer.getCapacity());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{'A'}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{'A', 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(byte)} with {@code byte}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is zero and discard is {@code true} Capacity is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(byte)}
   */
  @Test
  public void testAppendWithByte_thenXByteBufferWithSizeIsZeroAndDiscardIsTrueCapacityIsOne() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(0, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append((byte) 'A');

    // Assert
    assertEquals(1, xByteBuffer.getCapacity());
    assertEquals(1, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{'A'}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{'A'}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is four and discard is {@code true} Capacity is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(int)}
   */
  @Test
  public void testAppendWithInt_thenXByteBufferWithSizeIsFourAndDiscardIsTrueCapacityIsFour() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(4, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(1);

    // Assert
    assertEquals(4, xByteBuffer.getCapacity());
    assertEquals(4, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{0, 0, 0, 1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 1}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} Length is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(int)}
   */
  @Test
  public void testAppendWithInt_thenXByteBufferWithSizeIsThreeAndDiscardIsTrueLengthIsFour() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(1);

    // Assert
    assertEquals(4, xByteBuffer.getLength());
    assertEquals(6, xByteBuffer.getCapacity());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{0, 0, 0, 1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is eight and discard is {@code true} Capacity is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(long)}
   */
  @Test
  public void testAppendWithLong_thenXByteBufferWithSizeIsEightAndDiscardIsTrueCapacityIsEight() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(8, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(1L);

    // Assert
    assertEquals(8, xByteBuffer.getCapacity());
    assertEquals(8, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#append(long)} with {@code long}.
   * <ul>
   *   <li>Then {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} Capacity is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#append(long)}
   */
  @Test
  public void testAppendWithLong_thenXByteBufferWithSizeIsThreeAndDiscardIsTrueCapacityIsEight() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    boolean actualAppendResult = xByteBuffer.append(1L);

    // Assert
    assertEquals(8, xByteBuffer.getCapacity());
    assertEquals(8, xByteBuffer.getLength());
    assertTrue(actualAppendResult);
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, xByteBuffer.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#expand(int)}.
   * <p>
   * Method under test: {@link XByteBuffer#expand(int)}
   */
  @Test
  public void testExpand() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);

    // Act
    xByteBuffer.expand(3);

    // Assert
    assertEquals(6, xByteBuffer.getCapacity());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0}, xByteBuffer.getBytesDirect());
  }

  /**
   * Test {@link XByteBuffer#getCapacity()}.
   * <p>
   * Method under test: {@link XByteBuffer#getCapacity()}
   */
  @Test
  public void testGetCapacity() {
    // Arrange, Act and Assert
    assertEquals(3, (new XByteBuffer(3, true)).getCapacity());
  }

  /**
   * Test {@link XByteBuffer#countPackages(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#countPackages(boolean)}
   */
  @Test
  public void testCountPackagesWithBoolean() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true)).countPackages(true));
  }

  /**
   * Test {@link XByteBuffer#countPackages(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#countPackages(boolean)}
   */
  @Test
  public void testCountPackagesWithBoolean2() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.append((byte) 'A');

    // Act and Assert
    assertEquals(0, xByteBuffer.countPackages(true));
  }

  /**
   * Test {@link XByteBuffer#countPackages(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#countPackages(boolean)}
   */
  @Test
  public void testCountPackagesWithBoolean3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("FXAXAXAX".getBytes("UTF-8"), true)).countPackages(true));
  }

  /**
   * Test {@link XByteBuffer#countPackages(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link XByteBuffer#countPackages(boolean)}
   */
  @Test
  public void testCountPackagesWithBoolean4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("FLAXAXAX".getBytes("UTF-8"), true)).countPackages(true));
  }

  /**
   * Test {@link XByteBuffer#countPackages(boolean)} with {@code boolean}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages(boolean)}
   */
  @Test
  public void testCountPackagesWithBoolean_givenXByteBufferWithSizeIsThreeAndDiscardIsTrue() {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer(3, true)).countPackages(true));
  }

  /**
   * Test {@link XByteBuffer#countPackages()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(byte[], boolean)} with data is {@code AXAXAXAX} Bytes is {@code UTF-8} and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages()}
   */
  @Test
  public void testCountPackages_givenXByteBufferWithDataIsAxaxaxaxBytesIsUtf8AndDiscardIsTrue()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("AXAXAXAX".getBytes("UTF-8"), true)).countPackages());
  }

  /**
   * Test {@link XByteBuffer#countPackages()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(byte[], boolean)} with data is {@code FLAXAXAX} Bytes is {@code UTF-8} and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages()}
   */
  @Test
  public void testCountPackages_givenXByteBufferWithDataIsFlaxaxaxBytesIsUtf8AndDiscardIsTrue()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("FLAXAXAX".getBytes("UTF-8"), true)).countPackages());
  }

  /**
   * Test {@link XByteBuffer#countPackages()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(byte[], boolean)} with data is {@code FXAXAXAX} Bytes is {@code UTF-8} and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages()}
   */
  @Test
  public void testCountPackages_givenXByteBufferWithDataIsFxaxaxaxBytesIsUtf8AndDiscardIsTrue()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer("FXAXAXAX".getBytes("UTF-8"), true)).countPackages());
  }

  /**
   * Test {@link XByteBuffer#countPackages()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages()}
   */
  @Test
  public void testCountPackages_givenXByteBufferWithSizeIsThreeAndDiscardIsTrue() {
    // Arrange, Act and Assert
    assertEquals(0, (new XByteBuffer(3, true)).countPackages());
  }

  /**
   * Test {@link XByteBuffer#countPackages()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} append {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#countPackages()}
   */
  @Test
  public void testCountPackages_givenXByteBufferWithSizeIsThreeAndDiscardIsTrueAppendA() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.append((byte) 'A');

    // Act and Assert
    assertEquals(0, xByteBuffer.countPackages());
  }

  /**
   * Test {@link XByteBuffer#doesPackageExist()}.
   * <p>
   * Method under test: {@link XByteBuffer#doesPackageExist()}
   */
  @Test
  public void testDoesPackageExist() {
    // Arrange, Act and Assert
    assertFalse((new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true)).doesPackageExist());
  }

  /**
   * Test {@link XByteBuffer#doesPackageExist()}.
   * <p>
   * Method under test: {@link XByteBuffer#doesPackageExist()}
   */
  @Test
  public void testDoesPackageExist2() {
    // Arrange, Act and Assert
    assertFalse((new XByteBuffer(new byte[]{'F', 1, 'A', 1, 'A', 1, 'A', 1}, true)).doesPackageExist());
  }

  /**
   * Test {@link XByteBuffer#doesPackageExist()}.
   * <p>
   * Method under test: {@link XByteBuffer#doesPackageExist()}
   */
  @Test
  public void testDoesPackageExist3() {
    // Arrange, Act and Assert
    assertFalse((new XByteBuffer(new byte[]{'F', 'L', 'A', 1, 'A', 1, 'A', 1}, true)).doesPackageExist());
  }

  /**
   * Test {@link XByteBuffer#doesPackageExist()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#doesPackageExist()}
   */
  @Test
  public void testDoesPackageExist_givenXByteBufferWithSizeIsThreeAndDiscardIsTrue() {
    // Arrange, Act and Assert
    assertFalse((new XByteBuffer(3, true)).doesPackageExist());
  }

  /**
   * Test {@link XByteBuffer#doesPackageExist()}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} append {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#doesPackageExist()}
   */
  @Test
  public void testDoesPackageExist_givenXByteBufferWithSizeIsThreeAndDiscardIsTrueAppendA() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.append((byte) 'A');

    // Act and Assert
    assertFalse(xByteBuffer.doesPackageExist());
  }

  /**
   * Test {@link XByteBuffer#extractDataPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractDataPackage(boolean)}
   */
  @Test
  public void testExtractDataPackage() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true)).extractDataPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractDataPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractDataPackage(boolean)}
   */
  @Test
  public void testExtractDataPackage2() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'F', 1, 'A', 1, 'A', 1, 'A', 1}, true)).extractDataPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractDataPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractDataPackage(boolean)}
   */
  @Test
  public void testExtractDataPackage3() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'F', 'L', 'A', 1, 'A', 1, 'A', 1}, true)).extractDataPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractDataPackage(boolean)}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#extractDataPackage(boolean)}
   */
  @Test
  public void testExtractDataPackage_givenXByteBufferWithSizeIsThreeAndDiscardIsTrue() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new XByteBuffer(3, true)).extractDataPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractDataPackage(boolean)}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} append {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#extractDataPackage(boolean)}
   */
  @Test
  public void testExtractDataPackage_givenXByteBufferWithSizeIsThreeAndDiscardIsTrueAppendA() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.append((byte) 'A');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> xByteBuffer.extractDataPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractPackage(boolean)}
   */
  @Test
  public void testExtractPackage() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true)).extractPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractPackage(boolean)}
   */
  @Test
  public void testExtractPackage2() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'F', 1, 'A', 1, 'A', 1, 'A', 1}, true)).extractPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractPackage(boolean)}.
   * <p>
   * Method under test: {@link XByteBuffer#extractPackage(boolean)}
   */
  @Test
  public void testExtractPackage3() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new XByteBuffer(new byte[]{'F', 'L', 'A', 1, 'A', 1, 'A', 1}, true)).extractPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractPackage(boolean)}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#extractPackage(boolean)}
   */
  @Test
  public void testExtractPackage_givenXByteBufferWithSizeIsThreeAndDiscardIsTrue() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new XByteBuffer(3, true)).extractPackage(true));
  }

  /**
   * Test {@link XByteBuffer#extractPackage(boolean)}.
   * <ul>
   *   <li>Given {@link XByteBuffer#XByteBuffer(int, boolean)} with size is three and discard is {@code true} append {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#extractPackage(boolean)}
   */
  @Test
  public void testExtractPackage_givenXByteBufferWithSizeIsThreeAndDiscardIsTrueAppendA() {
    // Arrange
    XByteBuffer xByteBuffer = new XByteBuffer(3, true);
    xByteBuffer.append((byte) 'A');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> xByteBuffer.extractPackage(true));
  }

  /**
   * Test {@link XByteBuffer#createDataPackage(byte[])} with {@code data}.
   * <p>
   * Method under test: {@link XByteBuffer#createDataPackage(byte[])}
   */
  @Test
  public void testCreateDataPackageWithData() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{'F', 'L', 'T', '2', '0', '0', '2', 0, 0, 0, '\b', 'A', 'X', 'A', 'X', 'A', 'X', 'A',
        'X', 'T', 'L', 'F', '2', '0', '0', '3'}, XByteBuffer.createDataPackage("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link XByteBuffer#toInt(byte[], int)}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code 1480677441}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toInt(byte[], int)}
   */
  @Test
  public void testToInt_whenAxaxaxaxBytesIsUtf8_thenReturn1480677441() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1480677441, XByteBuffer.toInt("AXAXAXAX".getBytes("UTF-8"), 1));
  }

  /**
   * Test {@link XByteBuffer#toLong(byte[], int)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code 522707007627659073}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toLong(byte[], int)}
   */
  @Test
  public void testToLong_whenA_thenReturn522707007627659073() {
    // Arrange, Act and Assert
    assertEquals(522707007627659073L,
        XByteBuffer.toLong(new byte[]{'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7}, 1));
  }

  /**
   * Test {@link XByteBuffer#toBytes(boolean, byte[], int)} with {@code boolean}, {@code byte[]}, {@code int}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then array of {@code byte} with {@code A} and {@code X}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toBytes(boolean, byte[], int)}
   */
  @Test
  public void testToBytesWithBooleanByteInt_whenFalse_thenArrayOfByteWithAAndX() {
    // Arrange
    byte[] data = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act and Assert
    assertArrayEquals(new byte[]{'A', 'X', 0, 'X', 'A', 'X', 'A', 'X'}, XByteBuffer.toBytes(false, data, 2));
    assertArrayEquals(new byte[]{'A', 'X', 0, 'X', 'A', 'X', 'A', 'X'}, data);
  }

  /**
   * Test {@link XByteBuffer#toBytes(long, byte[], int)} with {@code long}, {@code byte[]}, {@code int}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return array of {@code byte} with {@code A} and seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toBytes(long, byte[], int)}
   */
  @Test
  public void testToBytesWithLongByteInt_whenA_thenReturnArrayOfByteWithAAndSeven() {
    // Arrange
    byte[] b = new byte[]{'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7, 'A', 7};

    // Act and Assert
    assertArrayEquals(new byte[]{'A', 7, 0, 0, 0, 0, 0, 0, 0, 1, 'A', 7, 'A', 7, 'A', 7},
        XByteBuffer.toBytes(1L, b, 2));
    assertArrayEquals(new byte[]{'A', 7, 0, 0, 0, 0, 0, 0, 0, 1, 'A', 7, 'A', 7, 'A', 7}, b);
  }

  /**
   * Test {@link XByteBuffer#toBoolean(byte[], int)}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toBoolean(byte[], int)}
   */
  @Test
  public void testToBoolean_whenAxaxaxaxBytesIsUtf8_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue(XByteBuffer.toBoolean("AXAXAXAX".getBytes("UTF-8"), 2));
  }

  /**
   * Test {@link XByteBuffer#toBoolean(byte[], int)}.
   * <ul>
   *   <li>When {@code X}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#toBoolean(byte[], int)}
   */
  @Test
  public void testToBoolean_whenX_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(XByteBuffer.toBoolean(new byte[]{'A', 'X', 0, 'X', 'A', 'X', 'A', 'X'}, 2));
  }

  /**
   * Test {@link XByteBuffer#getDataPackageLength(int)}.
   * <p>
   * Method under test: {@link XByteBuffer#getDataPackageLength(int)}
   */
  @Test
  public void testGetDataPackageLength() {
    // Arrange, Act and Assert
    assertEquals(21, XByteBuffer.getDataPackageLength(3));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When array of {@code byte} with {@code A} and minus one.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenArrayOfByteWithAAndMinusOne_thenReturnMinusOne()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1,
        XByteBuffer.firstIndexOf(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}, 1,
            "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenEmptyArrayOfByte_thenReturnMinusOne() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1, XByteBuffer.firstIndexOf(new byte[]{}, 1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenEmptyArrayOfByte_thenReturnMinusOne2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1, XByteBuffer.firstIndexOf("AXAXAXAX".getBytes("UTF-8"), 1, new byte[]{}));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenOne_thenReturnMinusOne() throws UnsupportedEncodingException {
    // Arrange
    byte[] src = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, XByteBuffer.firstIndexOf(src, 1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When {@code X}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenX_thenReturnMinusOne() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1,
        XByteBuffer.firstIndexOf("AXAXAXAX".getBytes("UTF-8"), 1, new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#firstIndexOf(byte[], int, byte[])}
   */
  @Test
  public void testFirstIndexOf_whenZero_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    byte[] src = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, XByteBuffer.firstIndexOf(src, 0, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link XByteBuffer#deserialize(byte[], int, int, ClassLoader[])} with {@code data}, {@code offset}, {@code length}, {@code cls}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#deserialize(byte[], int, int, ClassLoader[])}
   */
  @Test
  public void testDeserializeWithDataOffsetLengthCls_whenA_thenReturnNull()
      throws IOException, ClassCastException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(XByteBuffer.deserialize(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0, null));
  }

  /**
   * Test {@link XByteBuffer#deserialize(byte[], int, int, ClassLoader[])} with {@code data}, {@code offset}, {@code length}, {@code cls}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#deserialize(byte[], int, int, ClassLoader[])}
   */
  @Test
  public void testDeserializeWithDataOffsetLengthCls_whenZero_thenReturnNull()
      throws IOException, ClassCastException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(XByteBuffer.deserialize(null, 2, 0, null));
  }

  /**
   * Test {@link XByteBuffer#deserialize(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#deserialize(byte[], int, int)}
   */
  @Test
  public void testDeserializeWithDataOffsetLength_whenA_thenReturnNull()
      throws IOException, ClassCastException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(XByteBuffer.deserialize(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0));
  }

  /**
   * Test {@link XByteBuffer#deserialize(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#deserialize(byte[], int, int)}
   */
  @Test
  public void testDeserializeWithDataOffsetLength_whenNull_thenReturnNull()
      throws IOException, ClassCastException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(XByteBuffer.deserialize(null, 2, 0));
  }

  /**
   * Test {@link XByteBuffer#deserialize(byte[])} with {@code data}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XByteBuffer#deserialize(byte[])}
   */
  @Test
  public void testDeserializeWithData_whenEmptyArrayOfByte_thenReturnNull()
      throws IOException, ClassCastException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(XByteBuffer.deserialize(new byte[]{}));
  }

  /**
   * Test {@link XByteBuffer#serialize(Serializable)}.
   * <p>
   * Method under test: {@link XByteBuffer#serialize(Serializable)}
   */
  @Test
  public void testSerialize() throws IOException {
    // Arrange and Act
    byte[] actualSerializeResult = XByteBuffer.serialize(new SimpleDateFormat("yyyy/mm/dd"));

    // Assert
    assertEquals((byte) -19, actualSerializeResult[1]);
    assertEquals((byte) -84, actualSerializeResult[0]);
    assertEquals((byte) 0, actualSerializeResult[2]);
    assertEquals((byte) 0, actualSerializeResult[46325]);
    assertEquals((byte) 0, actualSerializeResult[6]);
    assertEquals((byte) 26, actualSerializeResult[7]);
    assertEquals((byte) 5, actualSerializeResult[3]);
    assertEquals('.', actualSerializeResult[12]);
    assertEquals('.', actualSerializeResult[17]);
    assertEquals('D', actualSerializeResult[Float.PRECISION]);
    assertEquals('S', actualSerializeResult[18]);
    assertEquals('a', actualSerializeResult[11]);
    assertEquals('a', actualSerializeResult[9]);
    assertEquals('e', actualSerializeResult[14]);
    assertEquals('e', actualSerializeResult[23]);
    assertEquals('i', actualSerializeResult[19]);
    assertEquals('j', actualSerializeResult[8]);
    assertEquals('l', actualSerializeResult[22]);
    assertEquals('m', actualSerializeResult[20]);
    assertEquals('p', actualSerializeResult[21]);
    assertEquals('r', actualSerializeResult[5]);
    assertEquals('s', actualSerializeResult[4]);
    assertEquals('t', actualSerializeResult[13]);
    assertEquals('t', actualSerializeResult[Short.SIZE]);
    assertEquals('v', actualSerializeResult[10]);
    assertEquals('x', actualSerializeResult[15]);
  }
}
