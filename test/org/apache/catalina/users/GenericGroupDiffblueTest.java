package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class GenericGroupDiffblueTest {
  /**
   * Test {@link GenericGroup#GenericGroup(UserDatabase, String, String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link GenericGroup#roles} is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#GenericGroup(UserDatabase, String, String, List)}
   */
  @Test
  public void testNewGenericGroup_whenArrayList_thenReturnRolesIsArrayList() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Role> roles = new ArrayList<>();

    // Act
    GenericGroup<UserDatabase> actualGenericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", roles);

    // Assert
    UserDatabase userDatabase = actualGenericGroup.getUserDatabase();
    assertTrue(userDatabase instanceof MemoryUserDatabase);
    assertEquals("Groupname", actualGenericGroup.getGroupname());
    assertEquals("Groupname", actualGenericGroup.getName());
    assertEquals("The characteristics of someone or something", actualGenericGroup.getDescription());
    assertFalse(actualGenericGroup.getRoles().hasNext());
    assertFalse(actualGenericGroup.getUsers().hasNext());
    assertEquals(roles, actualGenericGroup.roles);
    assertSame(memoryUserDatabase, userDatabase);
  }

  /**
   * Test {@link GenericGroup#GenericGroup(UserDatabase, String, String, List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link GenericGroup#roles} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#GenericGroup(UserDatabase, String, String, List)}
   */
  @Test
  public void testNewGenericGroup_whenNull_thenReturnRolesEmpty() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();

    // Act
    GenericGroup<UserDatabase> actualGenericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", null);

    // Assert
    UserDatabase userDatabase = actualGenericGroup.getUserDatabase();
    assertTrue(userDatabase instanceof MemoryUserDatabase);
    assertEquals("Groupname", actualGenericGroup.getGroupname());
    assertEquals("Groupname", actualGenericGroup.getName());
    assertEquals("The characteristics of someone or something", actualGenericGroup.getDescription());
    assertFalse(actualGenericGroup.getRoles().hasNext());
    assertFalse(actualGenericGroup.getUsers().hasNext());
    assertTrue(actualGenericGroup.roles.isEmpty());
    assertSame(memoryUserDatabase, userDatabase);
  }

  /**
   * Test {@link GenericGroup#getRoles()}.
   * <p>
   * Method under test: {@link GenericGroup#getRoles()}
   */
  @Test
  public void testGetRoles() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertFalse(genericGroup.getRoles().hasNext());
  }

  /**
   * Test {@link GenericGroup#getUserDatabase()}.
   * <p>
   * Method under test: {@link GenericGroup#getUserDatabase()}
   */
  @Test
  public void testGetUserDatabase() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertSame(genericGroup.database, genericGroup.getUserDatabase());
  }

  /**
   * Test {@link GenericGroup#getUsers()}.
   * <ul>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#getUsers()}
   */
  @Test
  public void testGetUsers_thenReturnNotHasNext() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertFalse(genericGroup.getUsers().hasNext());
  }

  /**
   * Test {@link GenericGroup#isInRole(Role)}.
   * <p>
   * Method under test: {@link GenericGroup#isInRole(Role)}
   */
  @Test
  public void testIsInRole() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertFalse(genericGroup.isInRole(
        new GenericRole<>(new MemoryUserDatabase(), "Rolename", "The characteristics of someone or something")));
  }

  /**
   * Test {@link GenericGroup#removeRoles()}.
   * <p>
   * Method under test: {@link GenericGroup#removeRoles()}
   */
  @Test
  public void testRemoveRoles() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act
    genericGroup.removeRoles();

    // Assert that nothing has changed
    assertFalse(genericGroup.getRoles().hasNext());
    assertTrue(genericGroup.roles.isEmpty());
  }

  /**
   * Test {@link GenericGroup#removeRoles()}.
   * <p>
   * Method under test: {@link GenericGroup#removeRoles()}
   */
  @Test
  public void testRemoveRoles2() {
    // Arrange
    ArrayList<Role> roles = new ArrayList<>();
    roles.add(new GenericRole<>(new MemoryUserDatabase(), "Rolename", "The characteristics of someone or something"));
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(new MemoryUserDatabase(), "Groupname",
        "The characteristics of someone or something", roles);

    // Act
    genericGroup.removeRoles();

    // Assert
    assertFalse(genericGroup.getRoles().hasNext());
    assertTrue(genericGroup.roles.isEmpty());
  }

  /**
   * Test {@link GenericGroup#equals(Object)}, and {@link GenericGroup#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericGroup#equals(Object)}
   *   <li>{@link GenericGroup#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(null, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());
    GenericGroup<UserDatabase> genericGroup2 = new GenericGroup<>(null, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertEquals(genericGroup, genericGroup2);
    int expectedHashCodeResult = genericGroup.hashCode();
    assertEquals(expectedHashCodeResult, genericGroup2.hashCode());
  }

  /**
   * Test {@link GenericGroup#equals(Object)}, and {@link GenericGroup#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericGroup#equals(Object)}
   *   <li>{@link GenericGroup#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertEquals(genericGroup, genericGroup);
    int expectedHashCodeResult = genericGroup.hashCode();
    assertEquals(expectedHashCodeResult, genericGroup.hashCode());
  }

  /**
   * Test {@link GenericGroup#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());
    MemoryUserDatabase memoryUserDatabase2 = new MemoryUserDatabase();

    // Act and Assert
    assertNotEquals(genericGroup, new GenericGroup<>(memoryUserDatabase2, "Groupname",
        "The characteristics of someone or something", new ArrayList<>()));
  }

  /**
   * Test {@link GenericGroup#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(null, "org.apache.catalina.User",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertNotEquals(genericGroup,
        new GenericGroup<>(null, "Groupname", "The characteristics of someone or something", new ArrayList<>()));
  }

  /**
   * Test {@link GenericGroup#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertNotEquals(genericGroup, null);
  }

  /**
   * Test {@link GenericGroup#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericGroup#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertNotEquals(genericGroup, "Different type to GenericGroup");
  }
}
