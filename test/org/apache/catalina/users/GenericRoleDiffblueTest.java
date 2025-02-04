package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class GenericRoleDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericRole#GenericRole(UserDatabase, String, String)}
   *   <li>{@link GenericRole#getUserDatabase()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();

    // Act
    GenericRole<UserDatabase> actualGenericRole = new GenericRole<>(memoryUserDatabase, "Rolename",
        "The characteristics of someone or something");
    UserDatabase actualUserDatabase = actualGenericRole.getUserDatabase();

    // Assert
    assertEquals("Rolename", actualGenericRole.getRolename());
    assertEquals("The characteristics of someone or something", actualGenericRole.getDescription());
    assertSame(memoryUserDatabase, actualUserDatabase);
  }

  /**
   * Test {@link GenericRole#equals(Object)}, and {@link GenericRole#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericRole#equals(Object)}
   *   <li>{@link GenericRole#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(null, "Rolename",
        "The characteristics of someone or something");
    GenericRole<UserDatabase> genericRole2 = new GenericRole<>(null, "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertEquals(genericRole, genericRole2);
    int expectedHashCodeResult = genericRole.hashCode();
    assertEquals(expectedHashCodeResult, genericRole2.hashCode());
  }

  /**
   * Test {@link GenericRole#equals(Object)}, and {@link GenericRole#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericRole#equals(Object)}
   *   <li>{@link GenericRole#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertEquals(genericRole, genericRole);
    int expectedHashCodeResult = genericRole.hashCode();
    assertEquals(expectedHashCodeResult, genericRole.hashCode());
  }

  /**
   * Test {@link GenericRole#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericRole#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertNotEquals(genericRole,
        new GenericRole<>(new MemoryUserDatabase(), "Rolename", "The characteristics of someone or something"));
  }

  /**
   * Test {@link GenericRole#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericRole#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(null, "org.apache.catalina.users.GenericRole",
        "The characteristics of someone or something");

    // Act and Assert
    assertNotEquals(genericRole, new GenericRole<>(null, "Rolename", "The characteristics of someone or something"));
  }

  /**
   * Test {@link GenericRole#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericRole#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertNotEquals(genericRole, null);
  }

  /**
   * Test {@link GenericRole#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericRole#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertNotEquals(genericRole, "Different type to GenericRole");
  }
}
