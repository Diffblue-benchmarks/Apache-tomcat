package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.apache.catalina.Group;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class AbstractUserDiffblueTest {
  /**
   * Test {@link AbstractUser#getFullName()}.
   * <p>
   * Method under test: {@link AbstractUser#getFullName()}
   */
  @Test
  public void testGetFullName() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertEquals("Dr Jane Doe", genericUser.getFullName());
  }

  /**
   * Test {@link AbstractUser#getPassword()}.
   * <p>
   * Method under test: {@link AbstractUser#getPassword()}
   */
  @Test
  public void testGetPassword() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertEquals("iloveyou", genericUser.getPassword());
  }

  /**
   * Test {@link AbstractUser#getUsername()}.
   * <p>
   * Method under test: {@link AbstractUser#getUsername()}
   */
  @Test
  public void testGetUsername() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertEquals("janedoe", genericUser.getUsername());
  }

  /**
   * Test {@link AbstractUser#getName()}.
   * <p>
   * Method under test: {@link AbstractUser#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> genericUser = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe",
        groups, new ArrayList<>());

    // Act and Assert
    assertEquals("janedoe", genericUser.getName());
  }
}
