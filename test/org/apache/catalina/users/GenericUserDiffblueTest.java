package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class GenericUserDiffblueTest {
  /**
   * Test {@link GenericUser#GenericUser(UserDatabase, String, String, String, List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link GenericUser#roles} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#GenericUser(UserDatabase, String, String, String, List, List)}
   */
  @Test
  public void testNewGenericUser_whenArrayList_thenReturnRolesEmpty() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();

    // Act
    GenericUser<UserDatabase> actualGenericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou",
        "Dr Jane Doe", groups, new ArrayList<>());

    // Assert
    assertFalse(actualGenericUser.getGroups().hasNext());
    assertFalse(actualGenericUser.getRoles().hasNext());
    assertTrue(actualGenericUser.roles.isEmpty());
  }

  /**
   * Test {@link GenericUser#GenericUser(UserDatabase, String, String, String, List, List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link GenericUser#groups} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#GenericUser(UserDatabase, String, String, String, List, List)}
   */
  @Test
  public void testNewGenericUser_whenNull_thenReturnGroupsEmpty() {
    // Arrange and Act
    GenericUser<UserDatabase> actualGenericUser = new GenericUser<>(new MemoryUserDatabase(), "janedoe", "iloveyou",
        "Dr Jane Doe", null, null);

    // Assert
    assertTrue(actualGenericUser.groups.isEmpty());
    assertTrue(actualGenericUser.roles.isEmpty());
  }

  /**
   * Test {@link GenericUser#getGroups()}.
   * <p>
   * Method under test: {@link GenericUser#getGroups()}
   */
  @Test
  public void testGetGroups() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertFalse(genericUser.getGroups().hasNext());
  }

  /**
   * Test {@link GenericUser#getRoles()}.
   * <p>
   * Method under test: {@link GenericUser#getRoles()}
   */
  @Test
  public void testGetRoles() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertFalse(genericUser.getRoles().hasNext());
  }

  /**
   * Test {@link GenericUser#getUserDatabase()}.
   * <p>
   * Method under test: {@link GenericUser#getUserDatabase()}
   */
  @Test
  public void testGetUserDatabase() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertSame(genericUser.database, genericUser.getUserDatabase());
  }

  /**
   * Test {@link GenericUser#isInGroup(Group)}.
   * <p>
   * Method under test: {@link GenericUser#isInGroup(Group)}
   */
  @Test
  public void testIsInGroup() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());
    MemoryUserDatabase memoryUserDatabase2 = new MemoryUserDatabase();

    // Act and Assert
    assertFalse(genericUser.isInGroup(new GenericGroup<>(memoryUserDatabase2, "Groupname",
        "The characteristics of someone or something", new ArrayList<>())));
  }

  /**
   * Test {@link GenericUser#isInRole(Role)}.
   * <p>
   * Method under test: {@link GenericUser#isInRole(Role)}
   */
  @Test
  public void testIsInRole() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertFalse(genericUser.isInRole(
        new GenericRole<>(new MemoryUserDatabase(), "Rolename", "The characteristics of someone or something")));
  }

  /**
   * Test {@link GenericUser#removeGroups()}.
   * <p>
   * Method under test: {@link GenericUser#removeGroups()}
   */
  @Test
  public void testRemoveGroups() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act
    genericUser.removeGroups();

    // Assert that nothing has changed
    assertFalse(genericUser.getGroups().hasNext());
    assertTrue(genericUser.groups.isEmpty());
  }

  /**
   * Test {@link GenericUser#removeGroups()}.
   * <p>
   * Method under test: {@link GenericUser#removeGroups()}
   */
  @Test
  public void testRemoveGroups2() {
    // Arrange
    ArrayList<Group> groups = new ArrayList<>();
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    groups.add(new GenericGroup<>(memoryUserDatabase, "Groupname", "The characteristics of someone or something",
        new ArrayList<>()));
    MemoryUserDatabase memoryUserDatabase2 = new MemoryUserDatabase();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase2, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act
    genericUser.removeGroups();

    // Assert
    assertFalse(genericUser.getGroups().hasNext());
    assertTrue(genericUser.groups.isEmpty());
  }

  /**
   * Test {@link GenericUser#removeRoles()}.
   * <p>
   * Method under test: {@link GenericUser#removeRoles()}
   */
  @Test
  public void testRemoveRoles() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act
    genericUser.removeRoles();

    // Assert that nothing has changed
    assertFalse(genericUser.getRoles().hasNext());
    assertTrue(genericUser.roles.isEmpty());
  }

  /**
   * Test {@link GenericUser#removeRoles()}.
   * <p>
   * Method under test: {@link GenericUser#removeRoles()}
   */
  @Test
  public void testRemoveRoles2() {
    // Arrange
    ArrayList<Role> roles = new ArrayList<>();
    roles.add(new GenericRole<>(new MemoryUserDatabase(), "Rolename", "The characteristics of someone or something"));
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        new ArrayList<>(), roles);

    // Act
    genericUser.removeRoles();

    // Assert
    assertFalse(genericUser.getRoles().hasNext());
    assertTrue(genericUser.roles.isEmpty());
  }

  /**
   * Test {@link GenericUser#equals(Object)}, and {@link GenericUser#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericUser#equals(Object)}
   *   <li>{@link GenericUser#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(null, "janedoe", "iloveyou", "Dr Jane Doe", groups,
        new ArrayList<>());
    ArrayList<Group> groups2 = new ArrayList<>();
    GenericUser<UserDatabase> genericUser2 = new GenericUser<>(null, "janedoe", "iloveyou", "Dr Jane Doe", groups2,
        new ArrayList<>());

    // Act and Assert
    assertEquals(genericUser, genericUser2);
    int expectedHashCodeResult = genericUser.hashCode();
    assertEquals(expectedHashCodeResult, genericUser2.hashCode());
  }

  /**
   * Test {@link GenericUser#equals(Object)}, and {@link GenericUser#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericUser#equals(Object)}
   *   <li>{@link GenericUser#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertEquals(genericUser, genericUser);
    int expectedHashCodeResult = genericUser.hashCode();
    assertEquals(expectedHashCodeResult, genericUser.hashCode());
  }

  /**
   * Test {@link GenericUser#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());
    MemoryUserDatabase memoryUserDatabase2 = new MemoryUserDatabase();
    ArrayList<Group> groups2 = new ArrayList<>();

    // Act and Assert
    assertNotEquals(genericUser,
        new GenericUser<>(memoryUserDatabase2, "janedoe", "iloveyou", "Dr Jane Doe", groups2, new ArrayList<>()));
  }

  /**
   * Test {@link GenericUser#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(null, "Username", "iloveyou", "Dr Jane Doe", groups,
        new ArrayList<>());
    ArrayList<Group> groups2 = new ArrayList<>();

    // Act and Assert
    assertNotEquals(genericUser,
        new GenericUser<>(null, "janedoe", "iloveyou", "Dr Jane Doe", groups2, new ArrayList<>()));
  }

  /**
   * Test {@link GenericUser#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertNotEquals(genericUser, null);
  }

  /**
   * Test {@link GenericUser#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericUser#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertNotEquals(genericUser, "Different type to GenericUser");
  }
}
