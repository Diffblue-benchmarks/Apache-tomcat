package org.apache.catalina.tribes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.util.Arrays;
import org.junit.Test;

public class UniqueIdDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return toString is {@code UniqueId{}}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UniqueId#UniqueId()}
   *   <li>{@link UniqueId#toString()}
   *   <li>{@link UniqueId#getBytes()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_thenReturnToStringIsUniqueId() {
    // Arrange and Act
    UniqueId actualUniqueId = new UniqueId();
    String actualToStringResult = actualUniqueId.toString();

    // Assert
    assertEquals("UniqueId{}", actualToStringResult);
    assertNull(actualUniqueId.getBytes());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return toString is {@code UniqueId{65, 88, 65, 88, 65, 88, 65, 88}}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UniqueId#UniqueId(byte[])}
   *   <li>{@link UniqueId#toString()}
   *   <li>{@link UniqueId#getBytes()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_thenReturnToStringIsUniqueId6588658865886588() throws UnsupportedEncodingException {
    // Arrange
    byte[] id = "AXAXAXAX".getBytes("UTF-8");

    // Act
    UniqueId actualUniqueId = new UniqueId(id);
    String actualToStringResult = actualUniqueId.toString();
    byte[] actualBytes = actualUniqueId.getBytes();

    // Assert
    assertEquals("UniqueId{65, 88, 65, 88, 65, 88, 65, 88}", actualToStringResult);
    assertSame(id, actualBytes);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link UniqueId#UniqueId(byte[], int, int)}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return Bytes is {@code AXA} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#UniqueId(byte[], int, int)}
   */
  @Test
  public void testNewUniqueId_whenAxaxaxaxBytesIsUtf8_thenReturnBytesIsAxaBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange and Act
    UniqueId actualUniqueId = new UniqueId("AXAXAXAX".getBytes("UTF-8"), 2, 3);

    // Assert
    byte[] expectedBytes = "AXA".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualUniqueId.getBytes());
  }

  /**
   * Test {@link UniqueId#equals(Object)}, and {@link UniqueId#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UniqueId#equals(Object)}
   *   <li>{@link UniqueId#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    UniqueId uniqueId = new UniqueId();
    UniqueId uniqueId2 = new UniqueId();

    // Act and Assert
    assertEquals(uniqueId, uniqueId2);
    int expectedHashCodeResult = uniqueId.hashCode();
    assertEquals(expectedHashCodeResult, uniqueId2.hashCode());
  }

  /**
   * Test {@link UniqueId#equals(Object)}, and {@link UniqueId#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UniqueId#equals(Object)}
   *   <li>{@link UniqueId#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    UniqueId uniqueId = new UniqueId();

    // Act and Assert
    assertEquals(uniqueId, uniqueId);
    int expectedHashCodeResult = uniqueId.hashCode();
    assertEquals(expectedHashCodeResult, uniqueId.hashCode());
  }

  /**
   * Test {@link UniqueId#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    UniqueId uniqudId = Arrays.getUniqudId(new ChannelData());

    // Act and Assert
    assertNotEquals(uniqudId, new UniqueId());
  }

  /**
   * Test {@link UniqueId#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    UniqueId uniqueId = new UniqueId();

    // Act and Assert
    assertNotEquals(uniqueId, Arrays.getUniqudId(new ChannelData()));
  }

  /**
   * Test {@link UniqueId#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    UniqueId uniqudId = Arrays.getUniqudId(new ChannelData());

    // Act and Assert
    assertNotEquals(uniqudId, Arrays.getUniqudId(new ChannelData()));
  }

  /**
   * Test {@link UniqueId#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new UniqueId(), null);
  }

  /**
   * Test {@link UniqueId#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UniqueId#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new UniqueId(), "Different type to UniqueId");
  }
}
