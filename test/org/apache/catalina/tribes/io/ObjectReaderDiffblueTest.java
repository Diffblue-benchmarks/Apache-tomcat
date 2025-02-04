package org.apache.catalina.tribes.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.junit.Test;

public class ObjectReaderDiffblueTest {
  /**
   * Test {@link ObjectReader#ObjectReader(Socket)}.
   * <ul>
   *   <li>When {@link Socket#Socket()}.</li>
   *   <li>Then return not Accessed.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#ObjectReader(Socket)}
   */
  @Test
  public void testNewObjectReader_whenSocket_thenReturnNotAccessed() {
    // Arrange and Act
    ObjectReader actualObjectReader = new ObjectReader(new Socket());

    // Assert
    assertFalse(actualObjectReader.isAccessed());
    assertFalse(actualObjectReader.isCancelled());
  }

  /**
   * Test {@link ObjectReader#ObjectReader(int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return not Accessed.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#ObjectReader(int)}
   */
  @Test
  public void testNewObjectReader_whenThree_thenReturnNotAccessed() {
    // Arrange and Act
    ObjectReader actualObjectReader = new ObjectReader(3);

    // Assert
    assertFalse(actualObjectReader.isAccessed());
    assertFalse(actualObjectReader.isCancelled());
  }

  /**
   * Test {@link ObjectReader#access()}.
   * <p>
   * Method under test: {@link ObjectReader#access()}
   */
  @Test
  public void testAccess() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);

    // Act
    objectReader.access();

    // Assert
    assertTrue(objectReader.isAccessed());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>Given {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_givenA() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}, 1, 7, true);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(0, objectReader.append(data, 3, true));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>Given {@link ObjectReader#ObjectReader(int)} with packetSize is seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_givenObjectReaderWithPacketSizeIsSeven() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(7);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(0, objectReader.append(data, 3, true));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>Then wrap array of {@code byte} with {@code A} and {@code F} position is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_thenWrapArrayOfByteWithAAndFPositionIsThree() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(7);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'A', 'F', 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(0, objectReader.append(data, 3, true));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>Then wrap array of {@code byte} with {@code A} and three position is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_thenWrapArrayOfByteWithAAndThreePositionIsThree() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(0, objectReader.append(data, 3, true));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>Then wrap array of {@code byte} with {@code F} and three position is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_thenWrapArrayOfByteWithFAndThreePositionIsThree() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(7);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'F', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(0, objectReader.append(data, 3, true));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_whenFalse_thenReturnMinusOne() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    ByteBuffer data = ByteBuffer.wrap(new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act and Assert
    assertEquals(-1, objectReader.append(data, 3, false));
    assertEquals(3, data.position());
  }

  /**
   * Test {@link ObjectReader#append(ByteBuffer, int, boolean)} with {@code data}, {@code len}, {@code count}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link ApplicationBufferHandler#EMPTY_BUFFER} position is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(ByteBuffer, int, boolean)}
   */
  @Test
  public void testAppendWithDataLenCount_whenZero_thenEmpty_bufferPositionIsZero() {
    // Arrange
    ByteBuffer data = ApplicationBufferHandler.EMPTY_BUFFER;

    // Act
    int actualAppendResult = (new ObjectReader(3)).append(data, 0, true);

    // Assert
    assertEquals(0, data.position());
    assertEquals(0, actualAppendResult);
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>Given {@link ObjectReader#ObjectReader(int)} with packetSize is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_givenObjectReaderWithPacketSizeIsEight()
      throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(8);

    // Act and Assert
    assertEquals(0, objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>Given {@link ObjectReader#ObjectReader(int)} with packetSize is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_givenObjectReaderWithPacketSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(1);

    // Act and Assert
    assertEquals(0, objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);

    // Act and Assert
    assertEquals(0, objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>When {@code AFAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_whenAfaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(8);

    // Act and Assert
    assertEquals(0, objectReader.append("AFAXAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>When {@code AXAFAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_whenAxafaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(8);

    // Act and Assert
    assertEquals(0, objectReader.append("AXAFAXAX".getBytes("UTF-8"), 1, 3, true));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_whenFalse_thenReturnMinusOne() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);

    // Act and Assert
    assertEquals(-1, objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, false));
  }

  /**
   * Test {@link ObjectReader#append(byte[], int, int, boolean)} with {@code data}, {@code off}, {@code len}, {@code count}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#append(byte[], int, int, boolean)}
   */
  @Test
  public void testAppendWithDataOffLenCount_whenZero_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);

    // Act and Assert
    assertEquals(0, objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 0, true));
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute() {
    // Arrange, Act and Assert
    assertEquals(0, (new ObjectReader(3)).execute().length);
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute2() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, true);

    // Act and Assert
    assertEquals(0, objectReader.execute().length);
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute3() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.execute().length);
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute4() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AFAXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.execute().length);
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute5() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXFXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.execute().length);
  }

  /**
   * Test {@link ObjectReader#execute()}.
   * <p>
   * Method under test: {@link ObjectReader#execute()}
   */
  @Test
  public void testExecute6() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AFLXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.execute().length);
  }

  /**
   * Test {@link ObjectReader#bufferSize()}.
   * <p>
   * Method under test: {@link ObjectReader#bufferSize()}
   */
  @Test
  public void testBufferSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new ObjectReader(3)).bufferSize());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage() {
    // Arrange, Act and Assert
    assertFalse((new ObjectReader(3)).hasPackage());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage2() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 3, true);

    // Act and Assert
    assertFalse(objectReader.hasPackage());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage3() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, 1, 7, true);

    // Act and Assert
    assertFalse(objectReader.hasPackage());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage4() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 'F', 'A', 1, 'A', 1, 'A', 1}, 1, 7, true);

    // Act and Assert
    assertFalse(objectReader.hasPackage());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <ul>
   *   <li>Given {@code F}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage_givenF() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 1, 'F', 1, 'A', 1, 'A', 1}, 1, 7, true);

    // Act and Assert
    assertFalse(objectReader.hasPackage());
  }

  /**
   * Test {@link ObjectReader#hasPackage()}.
   * <ul>
   *   <li>Given {@code L}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectReader#hasPackage()}
   */
  @Test
  public void testHasPackage_givenL() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append(new byte[]{'A', 'F', 'L', 1, 'A', 1, 'A', 1}, 1, 7, true);

    // Act and Assert
    assertFalse(objectReader.hasPackage());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new ObjectReader(3)).count());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount2() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 3, true);

    // Act and Assert
    assertEquals(0, objectReader.count());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount3() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXAXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.count());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount4() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AFAXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.count());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount5() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AXFXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.count());
  }

  /**
   * Test {@link ObjectReader#count()}.
   * <p>
   * Method under test: {@link ObjectReader#count()}
   */
  @Test
  public void testCount6() throws UnsupportedEncodingException {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);
    objectReader.append("AFLXAXAX".getBytes("UTF-8"), 1, 7, true);

    // Act and Assert
    assertEquals(0, objectReader.count());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ObjectReader#setCancelled(boolean)}
   *   <li>{@link ObjectReader#setLastAccess(long)}
   *   <li>{@link ObjectReader#close()}
   *   <li>{@link ObjectReader#getLastAccess()}
   *   <li>{@link ObjectReader#isAccessed()}
   *   <li>{@link ObjectReader#isCancelled()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ObjectReader objectReader = new ObjectReader(3);

    // Act
    objectReader.setCancelled(true);
    objectReader.setLastAccess(1L);
    objectReader.close();
    long actualLastAccess = objectReader.getLastAccess();
    boolean actualIsAccessedResult = objectReader.isAccessed();

    // Assert
    assertEquals(1L, actualLastAccess);
    assertFalse(actualIsAccessedResult);
    assertTrue(objectReader.isCancelled());
  }
}
