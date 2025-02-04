package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class ParameterMapDiffblueTest {
  /**
   * Test {@link ParameterMap#ParameterMap()}.
   * <p>
   * Method under test: {@link ParameterMap#ParameterMap()}
   */
  @Test
  public void testNewParameterMap() {
    // Arrange and Act
    ParameterMap<Object, Object> actualObjectObjectMap = new ParameterMap<>();

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#ParameterMap(Map)}.
   * <p>
   * Method under test: {@link ParameterMap#ParameterMap(Map)}
   */
  @Test
  public void testNewParameterMap2() {
    // Arrange and Act
    ParameterMap<Object, Object> actualObjectObjectMap = new ParameterMap<>(new HashMap<>());

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#ParameterMap(ParameterMap)}.
   * <p>
   * Method under test: {@link ParameterMap#ParameterMap(ParameterMap)}
   */
  @Test
  public void testNewParameterMap3() {
    // Arrange and Act
    ParameterMap<Object, Object> actualObjectObjectMap = new ParameterMap<>(new ParameterMap<>());

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#ParameterMap(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#ParameterMap(int)}
   */
  @Test
  public void testNewParameterMap_whenOne_thenReturnEmpty() {
    // Arrange and Act
    ParameterMap<Object, Object> actualObjectObjectMap = new ParameterMap<>(1);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#ParameterMap(int, float)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#ParameterMap(int, float)}
   */
  @Test
  public void testNewParameterMap_whenOne_thenReturnEmpty2() {
    // Arrange and Act
    ParameterMap<Object, Object> actualObjectObjectMap = new ParameterMap<>(1, 10.0f);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ParameterMap#setLocked(boolean)}
   *   <li>{@link ParameterMap#isLocked()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act
    objectObjectMap.setLocked(true);

    // Assert
    assertTrue(objectObjectMap.isLocked());
  }

  /**
   * Test {@link ParameterMap#clear()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#clear()}
   */
  @Test
  public void testClear_givenParameterMapLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectObjectMap.clear());
  }

  /**
   * Test {@link ParameterMap#put(Object, Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#put(Object, Object)}
   */
  @Test
  public void testPut_givenParameterMapLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectObjectMap.put("Key", "Value"));
  }

  /**
   * Test {@link ParameterMap#put(Object, Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   *   <li>Then {@link ParameterMap#ParameterMap()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#put(Object, Object)}
   */
  @Test
  public void testPut_givenParameterMap_thenParameterMapSizeIsOne() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertNull(actualPutResult);
  }

  /**
   * Test {@link ParameterMap#putAll(Map)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#putAll(Map)}
   */
  @Test
  public void testPutAll_givenParameterMapLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectObjectMap.putAll(new HashMap<>()));
  }

  /**
   * Test {@link ParameterMap#remove(Object)} with {@code Object}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#remove(Object)}
   */
  @Test
  public void testRemoveWithObject_givenParameterMap_thenReturnNull() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
  }

  /**
   * Test {@link ParameterMap#remove(Object)} with {@code Object}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#remove(Object)}
   */
  @Test
  public void testRemoveWithObject_thenThrowIllegalStateException() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectObjectMap.remove("Key"));
  }

  /**
   * Test {@link ParameterMap#size()}.
   * <p>
   * Method under test: {@link ParameterMap#size()}
   */
  @Test
  public void testSize() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertEquals(0, objectObjectMap.size());
  }

  /**
   * Test {@link ParameterMap#isEmpty()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} {@code Key} is {@code Value}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#isEmpty()}
   */
  @Test
  public void testIsEmpty_givenParameterMapKeyIsValue_thenReturnFalse() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertFalse(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#isEmpty()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#isEmpty()}
   */
  @Test
  public void testIsEmpty_givenParameterMap_thenReturnTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link ParameterMap#containsKey(Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} {@code Key} is {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#containsKey(Object)}
   */
  @Test
  public void testContainsKey_givenParameterMapKeyIsValue_thenReturnTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link ParameterMap#containsKey(Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#containsKey(Object)}
   */
  @Test
  public void testContainsKey_givenParameterMap_thenReturnFalse() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link ParameterMap#containsValue(Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} {@code Key} is {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#containsValue(Object)}
   */
  @Test
  public void testContainsValue_givenParameterMapKeyIsValue_thenReturnTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsValue("Value"));
  }

  /**
   * Test {@link ParameterMap#containsValue(Object)}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#containsValue(Object)}
   */
  @Test
  public void testContainsValue_givenParameterMap_thenReturnFalse() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsValue("Value"));
  }

  /**
   * Test {@link ParameterMap#get(Object)}.
   * <p>
   * Method under test: {@link ParameterMap#get(Object)}
   */
  @Test
  public void testGet() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /**
   * Test {@link ParameterMap#keySet()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#keySet()}
   */
  @Test
  public void testKeySet_givenParameterMap() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.keySet().isEmpty());
  }

  /**
   * Test {@link ParameterMap#keySet()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#keySet()}
   */
  @Test
  public void testKeySet_givenParameterMapLockedIsTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertTrue(objectObjectMap.keySet().isEmpty());
  }

  /**
   * Test {@link ParameterMap#values()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#values()}
   */
  @Test
  public void testValues_givenParameterMap() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.values().isEmpty());
  }

  /**
   * Test {@link ParameterMap#values()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#values()}
   */
  @Test
  public void testValues_givenParameterMapLockedIsTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertTrue(objectObjectMap.values().isEmpty());
  }

  /**
   * Test {@link ParameterMap#entrySet()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#entrySet()}
   */
  @Test
  public void testEntrySet_givenParameterMap() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.entrySet().isEmpty());
  }

  /**
   * Test {@link ParameterMap#entrySet()}.
   * <ul>
   *   <li>Given {@link ParameterMap#ParameterMap()} Locked is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterMap#entrySet()}
   */
  @Test
  public void testEntrySet_givenParameterMapLockedIsTrue() {
    // Arrange
    ParameterMap<Object, Object> objectObjectMap = new ParameterMap<>();
    objectObjectMap.setLocked(true);

    // Act and Assert
    assertTrue(objectObjectMap.entrySet().isEmpty());
  }
}
