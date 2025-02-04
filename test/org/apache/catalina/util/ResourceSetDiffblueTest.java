package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;

public class ResourceSetDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResourceSet#ResourceSet()}
   *   <li>{@link ResourceSet#setLocked(boolean)}
   *   <li>{@link ResourceSet#isLocked()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ResourceSet<Object> actualObjectSet = new ResourceSet<>();
    actualObjectSet.setLocked(true);

    // Assert
    assertTrue(actualObjectSet.isLocked());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResourceSet#ResourceSet(Collection)}
   *   <li>{@link ResourceSet#setLocked(boolean)}
   *   <li>{@link ResourceSet#isLocked()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenArrayList() {
    // Arrange and Act
    ResourceSet<Object> actualObjectSet = new ResourceSet<>(new ArrayList<>());
    actualObjectSet.setLocked(true);

    // Assert
    assertTrue(actualObjectSet.isLocked());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResourceSet#ResourceSet(int)}
   *   <li>{@link ResourceSet#setLocked(boolean)}
   *   <li>{@link ResourceSet#isLocked()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenOne() {
    // Arrange and Act
    ResourceSet<Object> actualObjectSet = new ResourceSet<>(1);
    actualObjectSet.setLocked(true);

    // Assert
    assertTrue(actualObjectSet.isLocked());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When ten.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResourceSet#ResourceSet(int, float)}
   *   <li>{@link ResourceSet#setLocked(boolean)}
   *   <li>{@link ResourceSet#isLocked()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenTen() {
    // Arrange and Act
    ResourceSet<Object> actualObjectSet = new ResourceSet<>(1, 10.0f);
    actualObjectSet.setLocked(true);

    // Assert
    assertTrue(actualObjectSet.isLocked());
  }

  /**
   * Test {@link ResourceSet#add(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()} add {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#add(Object)}
   */
  @Test
  public void testAdd_givenResourceSetAdd42_thenReturnFalse() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();
    objectSet.add("42");

    // Act
    boolean actualAddResult = objectSet.add("42");

    // Assert
    assertEquals(1, objectSet.size());
    assertFalse(actualAddResult);
  }

  /**
   * Test {@link ResourceSet#add(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#add(Object)}
   */
  @Test
  public void testAdd_givenResourceSetLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();
    objectSet.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectSet.add("42"));
  }

  /**
   * Test {@link ResourceSet#add(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#add(Object)}
   */
  @Test
  public void testAdd_givenResourceSet_thenReturnTrue() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();

    // Act
    boolean actualAddResult = objectSet.add("42");

    // Assert
    assertEquals(1, objectSet.size());
    assertTrue(actualAddResult);
  }

  /**
   * Test {@link ResourceSet#clear()}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#clear()}
   */
  @Test
  public void testClear_givenResourceSetLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();
    objectSet.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectSet.clear());
  }

  /**
   * Test {@link ResourceSet#remove(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()} add {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#remove(Object)}
   */
  @Test
  public void testRemove_givenResourceSetAdd42_thenReturnTrue() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();
    objectSet.add("42");

    // Act
    boolean actualRemoveResult = objectSet.remove("42");

    // Assert
    assertTrue(objectSet.isEmpty());
    assertTrue(actualRemoveResult);
  }

  /**
   * Test {@link ResourceSet#remove(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()} Locked is {@code true}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#remove(Object)}
   */
  @Test
  public void testRemove_givenResourceSetLockedIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();
    objectSet.setLocked(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectSet.remove("42"));
  }

  /**
   * Test {@link ResourceSet#remove(Object)}.
   * <ul>
   *   <li>Given {@link ResourceSet#ResourceSet()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceSet#remove(Object)}
   */
  @Test
  public void testRemove_givenResourceSet_thenReturnFalse() {
    // Arrange
    ResourceSet<Object> objectSet = new ResourceSet<>();

    // Act and Assert
    assertFalse(objectSet.remove("42"));
    assertTrue(objectSet.isEmpty());
  }
}
