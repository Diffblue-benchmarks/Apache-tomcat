package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MemoryUserDatabaseDiffblueTest {
  /**
   * Test {@link MemoryUserDatabase#MemoryUserDatabase()}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#MemoryUserDatabase()}
   */
  @Test
  public void testNewMemoryUserDatabase() {
    // Arrange and Act
    MemoryUserDatabase actualMemoryUserDatabase = new MemoryUserDatabase();

    // Assert
    assertEquals("conf/tomcat-users.xml", actualMemoryUserDatabase.getPathname());
    assertEquals("conf/tomcat-users.xml.new", actualMemoryUserDatabase.pathnameNew);
    assertEquals("conf/tomcat-users.xml.old", actualMemoryUserDatabase.pathnameOld);
    assertNull(actualMemoryUserDatabase.getId());
    assertFalse(actualMemoryUserDatabase.getGroups().hasNext());
    assertFalse(actualMemoryUserDatabase.getRoles().hasNext());
    assertFalse(actualMemoryUserDatabase.getUsers().hasNext());
    assertFalse(actualMemoryUserDatabase.isSparse());
    assertTrue(actualMemoryUserDatabase.groups.isEmpty());
    assertTrue(actualMemoryUserDatabase.roles.isEmpty());
    assertTrue(actualMemoryUserDatabase.users.isEmpty());
    assertTrue(actualMemoryUserDatabase.isAvailable());
    assertTrue(actualMemoryUserDatabase.getReadonly());
    assertTrue(actualMemoryUserDatabase.getWatchSource());
  }

  /**
   * Test {@link MemoryUserDatabase#MemoryUserDatabase(String)}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#MemoryUserDatabase(String)}
   */
  @Test
  public void testNewMemoryUserDatabase2() {
    // Arrange and Act
    MemoryUserDatabase actualMemoryUserDatabase = new MemoryUserDatabase("42");

    // Assert
    assertEquals("42", actualMemoryUserDatabase.getId());
    assertEquals("conf/tomcat-users.xml", actualMemoryUserDatabase.getPathname());
    assertEquals("conf/tomcat-users.xml.new", actualMemoryUserDatabase.pathnameNew);
    assertEquals("conf/tomcat-users.xml.old", actualMemoryUserDatabase.pathnameOld);
    assertFalse(actualMemoryUserDatabase.getGroups().hasNext());
    assertFalse(actualMemoryUserDatabase.getRoles().hasNext());
    assertFalse(actualMemoryUserDatabase.getUsers().hasNext());
    assertFalse(actualMemoryUserDatabase.isSparse());
    assertTrue(actualMemoryUserDatabase.groups.isEmpty());
    assertTrue(actualMemoryUserDatabase.roles.isEmpty());
    assertTrue(actualMemoryUserDatabase.users.isEmpty());
    assertTrue(actualMemoryUserDatabase.isAvailable());
    assertTrue(actualMemoryUserDatabase.getReadonly());
    assertTrue(actualMemoryUserDatabase.getWatchSource());
  }

  /**
   * Test {@link MemoryUserDatabase#getGroups()}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#getGroups()}
   */
  @Test
  public void testGetGroups() {
    // Arrange, Act and Assert
    assertFalse((new MemoryUserDatabase()).getGroups().hasNext());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MemoryUserDatabase#setReadonly(boolean)}
   *   <li>{@link MemoryUserDatabase#setWatchSource(boolean)}
   *   <li>{@link MemoryUserDatabase#toString()}
   *   <li>{@link MemoryUserDatabase#getId()}
   *   <li>{@link MemoryUserDatabase#getPathname()}
   *   <li>{@link MemoryUserDatabase#getReadonly()}
   *   <li>{@link MemoryUserDatabase#getWatchSource()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();

    // Act
    memoryUserDatabase.setReadonly(true);
    memoryUserDatabase.setWatchSource(true);
    String actualToStringResult = memoryUserDatabase.toString();
    String actualId = memoryUserDatabase.getId();
    String actualPathname = memoryUserDatabase.getPathname();
    boolean actualReadonly = memoryUserDatabase.getReadonly();

    // Assert
    assertEquals("MemoryUserDatabase[id=null,pathname=conf/tomcat-users.xml,groupCount=0,roleCount=0,userCount=0]",
        actualToStringResult);
    assertEquals("conf/tomcat-users.xml", actualPathname);
    assertNull(actualId);
    assertTrue(actualReadonly);
    assertTrue(memoryUserDatabase.getWatchSource());
  }

  /**
   * Test {@link MemoryUserDatabase#setPathname(String)}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#setPathname(String)}
   */
  @Test
  public void testSetPathname() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();

    // Act
    memoryUserDatabase.setPathname("Pathname");

    // Assert
    assertEquals("Pathname", memoryUserDatabase.getPathname());
    assertEquals("Pathname.new", memoryUserDatabase.pathnameNew);
    assertEquals("Pathname.old", memoryUserDatabase.pathnameOld);
  }

  /**
   * Test {@link MemoryUserDatabase#getRoles()}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#getRoles()}
   */
  @Test
  public void testGetRoles() {
    // Arrange, Act and Assert
    assertFalse((new MemoryUserDatabase()).getRoles().hasNext());
  }

  /**
   * Test {@link MemoryUserDatabase#getUsers()}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#getUsers()}
   */
  @Test
  public void testGetUsers() {
    // Arrange, Act and Assert
    assertFalse((new MemoryUserDatabase()).getUsers().hasNext());
  }

  /**
   * Test {@link MemoryUserDatabase#createGroup(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createGroup(String, String)}
   */
  @Test
  public void testCreateGroup_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createGroup("", "The characteristics of someone or something"));
  }

  /**
   * Test {@link MemoryUserDatabase#createGroup(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createGroup(String, String)}
   */
  @Test
  public void testCreateGroup_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createGroup(null, "The characteristics of someone or something"));
  }

  /**
   * Test {@link MemoryUserDatabase#createRole(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createRole(String, String)}
   */
  @Test
  public void testCreateRole_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createRole("", "The characteristics of someone or something"));
  }

  /**
   * Test {@link MemoryUserDatabase#createRole(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createRole(String, String)}
   */
  @Test
  public void testCreateRole_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createRole(null, "The characteristics of someone or something"));
  }

  /**
   * Test {@link MemoryUserDatabase#createUser(String, String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createUser(String, String, String)}
   */
  @Test
  public void testCreateUser_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createUser("", "iloveyou", "Dr Jane Doe"));
  }

  /**
   * Test {@link MemoryUserDatabase#createUser(String, String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabase#createUser(String, String, String)}
   */
  @Test
  public void testCreateUser_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new MemoryUserDatabase()).createUser(null, "iloveyou", "Dr Jane Doe"));
  }

  /**
   * Test {@link MemoryUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup() {
    // Arrange, Act and Assert
    assertNull((new MemoryUserDatabase()).findGroup("Groupname"));
  }

  /**
   * Test {@link MemoryUserDatabase#findRole(String)}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#findRole(String)}
   */
  @Test
  public void testFindRole() {
    // Arrange, Act and Assert
    assertNull((new MemoryUserDatabase()).findRole("Rolename"));
  }

  /**
   * Test {@link MemoryUserDatabase#findUser(String)}.
   * <p>
   * Method under test: {@link MemoryUserDatabase#findUser(String)}
   */
  @Test
  public void testFindUser() {
    // Arrange, Act and Assert
    assertNull((new MemoryUserDatabase()).findUser("janedoe"));
  }
}
