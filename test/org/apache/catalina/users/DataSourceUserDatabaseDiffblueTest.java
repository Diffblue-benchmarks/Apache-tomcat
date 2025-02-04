package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.Test;

public class DataSourceUserDatabaseDiffblueTest {
  /**
   * Test {@link DataSourceUserDatabase#DataSourceUserDatabase(DataSource, String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#DataSourceUserDatabase(DataSource, String)}
   */
  @Test
  public void testNewDataSourceUserDatabase() {
    // Arrange and Act
    DataSourceUserDatabase actualDataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");

    // Assert
    assertTrue(actualDataSourceUserDatabase.dataSource instanceof BasicDataSource);
    assertEquals("42", actualDataSourceUserDatabase.getId());
    assertNull(actualDataSourceUserDatabase.getDataSourceName());
    assertNull(actualDataSourceUserDatabase.getGroupNameCol());
    assertNull(actualDataSourceUserDatabase.getGroupRoleTable());
    assertNull(actualDataSourceUserDatabase.getGroupTable());
    assertNull(actualDataSourceUserDatabase.getRoleAndGroupDescriptionCol());
    assertNull(actualDataSourceUserDatabase.getRoleNameCol());
    assertNull(actualDataSourceUserDatabase.getRoleTable());
    assertNull(actualDataSourceUserDatabase.getUserCredCol());
    assertNull(actualDataSourceUserDatabase.getUserFullNameCol());
    assertNull(actualDataSourceUserDatabase.getUserGroupTable());
    assertNull(actualDataSourceUserDatabase.getUserNameCol());
    assertNull(actualDataSourceUserDatabase.getUserRoleTable());
    assertNull(actualDataSourceUserDatabase.getUserTable());
    assertFalse(actualDataSourceUserDatabase.getGroups().hasNext());
    assertFalse(actualDataSourceUserDatabase.getRoles().hasNext());
    assertFalse(actualDataSourceUserDatabase.getUsers().hasNext());
    assertFalse(actualDataSourceUserDatabase.isAvailable());
    assertFalse(actualDataSourceUserDatabase.isGroupStoreDefined());
    assertFalse(actualDataSourceUserDatabase.isRoleStoreDefined());
    assertTrue(actualDataSourceUserDatabase.createdGroups.isEmpty());
    assertTrue(actualDataSourceUserDatabase.createdRoles.isEmpty());
    assertTrue(actualDataSourceUserDatabase.createdUsers.isEmpty());
    assertTrue(actualDataSourceUserDatabase.modifiedGroups.isEmpty());
    assertTrue(actualDataSourceUserDatabase.modifiedRoles.isEmpty());
    assertTrue(actualDataSourceUserDatabase.modifiedUsers.isEmpty());
    assertTrue(actualDataSourceUserDatabase.removedGroups.isEmpty());
    assertTrue(actualDataSourceUserDatabase.removedRoles.isEmpty());
    assertTrue(actualDataSourceUserDatabase.removedUsers.isEmpty());
    assertTrue(actualDataSourceUserDatabase.getReadonly());
    assertTrue(actualDataSourceUserDatabase.isSparse());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DataSourceUserDatabase#setDataSourceName(String)}
   *   <li>{@link DataSourceUserDatabase#setGroupNameCol(String)}
   *   <li>{@link DataSourceUserDatabase#setGroupRoleTable(String)}
   *   <li>{@link DataSourceUserDatabase#setGroupTable(String)}
   *   <li>{@link DataSourceUserDatabase#setReadonly(boolean)}
   *   <li>{@link DataSourceUserDatabase#setRoleAndGroupDescriptionCol(String)}
   *   <li>{@link DataSourceUserDatabase#setRoleNameCol(String)}
   *   <li>{@link DataSourceUserDatabase#setRoleTable(String)}
   *   <li>{@link DataSourceUserDatabase#setUserCredCol(String)}
   *   <li>{@link DataSourceUserDatabase#setUserFullNameCol(String)}
   *   <li>{@link DataSourceUserDatabase#setUserGroupTable(String)}
   *   <li>{@link DataSourceUserDatabase#setUserNameCol(String)}
   *   <li>{@link DataSourceUserDatabase#setUserRoleTable(String)}
   *   <li>{@link DataSourceUserDatabase#setUserTable(String)}
   *   <li>{@link DataSourceUserDatabase#close()}
   *   <li>{@link DataSourceUserDatabase#getDataSourceName()}
   *   <li>{@link DataSourceUserDatabase#getGroupNameCol()}
   *   <li>{@link DataSourceUserDatabase#getGroupRoleTable()}
   *   <li>{@link DataSourceUserDatabase#getGroupTable()}
   *   <li>{@link DataSourceUserDatabase#getId()}
   *   <li>{@link DataSourceUserDatabase#getReadonly()}
   *   <li>{@link DataSourceUserDatabase#getRoleAndGroupDescriptionCol()}
   *   <li>{@link DataSourceUserDatabase#getRoleNameCol()}
   *   <li>{@link DataSourceUserDatabase#getRoleTable()}
   *   <li>{@link DataSourceUserDatabase#getUserCredCol()}
   *   <li>{@link DataSourceUserDatabase#getUserFullNameCol()}
   *   <li>{@link DataSourceUserDatabase#getUserGroupTable()}
   *   <li>{@link DataSourceUserDatabase#getUserNameCol()}
   *   <li>{@link DataSourceUserDatabase#getUserRoleTable()}
   *   <li>{@link DataSourceUserDatabase#getUserTable()}
   *   <li>{@link DataSourceUserDatabase#isAvailable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws Exception {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");

    // Act
    dataSourceUserDatabase.setDataSourceName("Data Source Name");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupTable("Group Table");
    dataSourceUserDatabase.setReadonly(true);
    dataSourceUserDatabase.setRoleAndGroupDescriptionCol("Role And Group Description Col");
    dataSourceUserDatabase.setRoleNameCol("Role Name Col");
    dataSourceUserDatabase.setRoleTable("Role Table");
    dataSourceUserDatabase.setUserCredCol("User Cred Col");
    dataSourceUserDatabase.setUserFullNameCol("Dr Jane Doe");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setUserNameCol("janedoe");
    dataSourceUserDatabase.setUserRoleTable("User Role Table");
    dataSourceUserDatabase.setUserTable("User Table");
    dataSourceUserDatabase.close();
    String actualDataSourceName = dataSourceUserDatabase.getDataSourceName();
    String actualGroupNameCol = dataSourceUserDatabase.getGroupNameCol();
    String actualGroupRoleTable = dataSourceUserDatabase.getGroupRoleTable();
    String actualGroupTable = dataSourceUserDatabase.getGroupTable();
    String actualId = dataSourceUserDatabase.getId();
    boolean actualReadonly = dataSourceUserDatabase.getReadonly();
    String actualRoleAndGroupDescriptionCol = dataSourceUserDatabase.getRoleAndGroupDescriptionCol();
    String actualRoleNameCol = dataSourceUserDatabase.getRoleNameCol();
    String actualRoleTable = dataSourceUserDatabase.getRoleTable();
    String actualUserCredCol = dataSourceUserDatabase.getUserCredCol();
    String actualUserFullNameCol = dataSourceUserDatabase.getUserFullNameCol();
    String actualUserGroupTable = dataSourceUserDatabase.getUserGroupTable();
    String actualUserNameCol = dataSourceUserDatabase.getUserNameCol();
    String actualUserRoleTable = dataSourceUserDatabase.getUserRoleTable();
    String actualUserTable = dataSourceUserDatabase.getUserTable();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Data Source Name", actualDataSourceName);
    assertEquals("Dr Jane Doe", actualUserFullNameCol);
    assertEquals("Group Name Col", actualGroupNameCol);
    assertEquals("Group Role Table", actualGroupRoleTable);
    assertEquals("Group Table", actualGroupTable);
    assertEquals("Role And Group Description Col", actualRoleAndGroupDescriptionCol);
    assertEquals("Role Name Col", actualRoleNameCol);
    assertEquals("Role Table", actualRoleTable);
    assertEquals("User Cred Col", actualUserCredCol);
    assertEquals("User Group Table", actualUserGroupTable);
    assertEquals("User Role Table", actualUserRoleTable);
    assertEquals("User Table", actualUserTable);
    assertEquals("janedoe", actualUserNameCol);
    assertTrue(actualReadonly);
    assertTrue(dataSourceUserDatabase.isAvailable());
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup() {
    // Arrange, Act and Assert
    assertNull((new DataSourceUserDatabase(new BasicDataSource(), "42")).findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup2() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup3() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup4() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup5() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup6() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setRoleTable("Role Table");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup7() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setUserRoleTable("User Role Table");
    dataSourceUserDatabase.setRoleTable("Role Table");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup8() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setRoleNameCol("Role Name Col");
    dataSourceUserDatabase.setUserRoleTable("User Role Table");
    dataSourceUserDatabase.setRoleTable("Role Table");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#findGroup(String)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#findGroup(String)}
   */
  @Test
  public void testFindGroup9() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(null, "42");
    dataSourceUserDatabase.setRoleNameCol("Role Name Col");
    dataSourceUserDatabase.setUserRoleTable("User Role Table");
    dataSourceUserDatabase.setRoleTable("Role Table");
    dataSourceUserDatabase.setGroupRoleTable("Group Role Table");
    dataSourceUserDatabase.setGroupNameCol("Group Name Col");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupTable("Group Table");

    // Act and Assert
    assertNull(dataSourceUserDatabase.findGroup("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#removeGroup(Group)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#removeGroup(Group)}
   */
  @Test
  public void testRemoveGroup() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> group = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act
    dataSourceUserDatabase.removeGroup(group);

    // Assert
    ConcurrentHashMap<String, Group> stringGroupMap = dataSourceUserDatabase.removedGroups;
    assertEquals(1, stringGroupMap.size());
    assertSame(group, stringGroupMap.get("Groupname"));
  }

  /**
   * Test {@link DataSourceUserDatabase#removeRole(Role)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#removeRole(Role)}
   */
  @Test
  public void testRemoveRole() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    GenericRole<UserDatabase> role = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act
    dataSourceUserDatabase.removeRole(role);

    // Assert
    ConcurrentHashMap<String, Role> stringRoleMap = dataSourceUserDatabase.removedRoles;
    assertEquals(1, stringRoleMap.size());
    assertSame(role, stringRoleMap.get("Rolename"));
  }

  /**
   * Test {@link DataSourceUserDatabase#removeUser(User)}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#removeUser(User)}
   */
  @Test
  public void testRemoveUser() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    ArrayList<Group> groups = new ArrayList<>();
    GenericUser<UserDatabase> user = new GenericUser<>(memoryUserDatabase, "janedoe", "iloveyou", "Dr Jane Doe", groups,
        new ArrayList<>());

    // Act
    dataSourceUserDatabase.removeUser(user);

    // Assert
    ConcurrentHashMap<String, User> stringUserMap = dataSourceUserDatabase.removedUsers;
    assertEquals(1, stringUserMap.size());
    assertSame(user, stringUserMap.get("janedoe"));
  }

  /**
   * Test {@link DataSourceUserDatabase#isGroupStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isGroupStoreDefined()}
   */
  @Test
  public void testIsGroupStoreDefined() {
    // Arrange, Act and Assert
    assertFalse((new DataSourceUserDatabase(new BasicDataSource(), "42")).isGroupStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isGroupStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isGroupStoreDefined()}
   */
  @Test
  public void testIsGroupStoreDefined2() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setGroupTable("Group Table");
    dataSourceUserDatabase.setUserGroupTable(null);
    dataSourceUserDatabase.setGroupNameCol(null);
    dataSourceUserDatabase.setGroupRoleTable(null);
    dataSourceUserDatabase.setRoleTable(null);
    dataSourceUserDatabase.setUserRoleTable(null);
    dataSourceUserDatabase.setRoleNameCol(null);

    // Act and Assert
    assertFalse(dataSourceUserDatabase.isGroupStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isGroupStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isGroupStoreDefined()}
   */
  @Test
  public void testIsGroupStoreDefined3() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setGroupTable("Group Table");
    dataSourceUserDatabase.setUserGroupTable("User Group Table");
    dataSourceUserDatabase.setGroupNameCol(null);
    dataSourceUserDatabase.setGroupRoleTable(null);
    dataSourceUserDatabase.setRoleTable(null);
    dataSourceUserDatabase.setUserRoleTable(null);
    dataSourceUserDatabase.setRoleNameCol(null);

    // Act and Assert
    assertFalse(dataSourceUserDatabase.isGroupStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isRoleStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isRoleStoreDefined()}
   */
  @Test
  public void testIsRoleStoreDefined() {
    // Arrange, Act and Assert
    assertFalse((new DataSourceUserDatabase(new BasicDataSource(), "42")).isRoleStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isRoleStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isRoleStoreDefined()}
   */
  @Test
  public void testIsRoleStoreDefined2() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setRoleTable("foo");
    dataSourceUserDatabase.setUserRoleTable(null);
    dataSourceUserDatabase.setRoleNameCol(null);

    // Act and Assert
    assertFalse(dataSourceUserDatabase.isRoleStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isRoleStoreDefined()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isRoleStoreDefined()}
   */
  @Test
  public void testIsRoleStoreDefined3() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setRoleTable("foo");
    dataSourceUserDatabase.setUserRoleTable("foo");
    dataSourceUserDatabase.setRoleNameCol(null);

    // Act and Assert
    assertFalse(dataSourceUserDatabase.isRoleStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#isRoleStoreDefined()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabase#isRoleStoreDefined()}
   */
  @Test
  public void testIsRoleStoreDefined_thenReturnTrue() {
    // Arrange
    DataSourceUserDatabase dataSourceUserDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");
    dataSourceUserDatabase.setRoleTable("foo");
    dataSourceUserDatabase.setUserRoleTable("foo");
    dataSourceUserDatabase.setRoleNameCol("foo");

    // Act and Assert
    assertTrue(dataSourceUserDatabase.isRoleStoreDefined());
  }

  /**
   * Test {@link DataSourceUserDatabase#openConnection()}.
   * <p>
   * Method under test: {@link DataSourceUserDatabase#openConnection()}
   */
  @Test
  public void testOpenConnection() {
    // Arrange, Act and Assert
    assertNull((new DataSourceUserDatabase(new BasicDataSource(), "42")).openConnection());
  }

  /**
   * Test {@link DataSourceUserDatabase#openConnection()}.
   * <ul>
   *   <li>Given {@link BasicDataSource} (default constructor) JmxName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabase#openConnection()}
   */
  @Test
  public void testOpenConnection_givenBasicDataSourceJmxNameIsEmptyString() {
    // Arrange
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setJmxName("");

    // Act and Assert
    assertNull((new DataSourceUserDatabase(dataSource, "42")).openConnection());
  }

  /**
   * Test {@link DataSourceUserDatabase#openConnection()}.
   * <ul>
   *   <li>Given {@link BasicDataSource} (default constructor) JmxName is {@code XJ028.C}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabase#openConnection()}
   */
  @Test
  public void testOpenConnection_givenBasicDataSourceJmxNameIsXj028C() {
    // Arrange
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setJmxName("XJ028.C");

    // Act and Assert
    assertNull((new DataSourceUserDatabase(dataSource, "42")).openConnection());
  }

  /**
   * Test {@link DataSourceUserDatabase#openConnection()}.
   * <ul>
   *   <li>Given {@link DataSourceUserDatabase#DataSourceUserDatabase(DataSource, String)} with dataSource is {@code null} and id is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabase#openConnection()}
   */
  @Test
  public void testOpenConnection_givenDataSourceUserDatabaseWithDataSourceIsNullAndIdIs42() {
    // Arrange, Act and Assert
    assertNull((new DataSourceUserDatabase(null, "42")).openConnection());
  }
}
