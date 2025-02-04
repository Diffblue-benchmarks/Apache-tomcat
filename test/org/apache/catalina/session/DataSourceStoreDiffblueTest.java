package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.tomcat.unittest.TesterContext;
import org.junit.Test;

public class DataSourceStoreDiffblueTest {
  /**
   * Test {@link DataSourceStore#getName()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link TesterContext} (default constructor).</li>
   *   <li>Then return {@code ///test}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getName()}
   */
  @Test
  public void testGetName_givenBackupManagerContextIsTesterContext_thenReturnTest() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new TesterContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertEquals("///test", dataSourceStore.getName());
  }

  /**
   * Test {@link DataSourceStore#getName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code /null/null/test}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getName()}
   */
  @Test
  public void testGetName_givenStandardContextParentIsStandardContext_thenReturnNullNullTest() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setParent(new StandardContext());

    TesterContext context = new TesterContext();
    context.setParent(container);

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertEquals("/null/null/test", dataSourceStore.getName());
  }

  /**
   * Test {@link DataSourceStore#getName()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code //null/test}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getName()}
   */
  @Test
  public void testGetName_givenTesterContextParentIsStandardContext_thenReturnNullTest() {
    // Arrange
    TesterContext context = new TesterContext();
    context.setParent(new StandardContext());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertEquals("//null/test", dataSourceStore.getName());
  }

  /**
   * Test {@link DataSourceStore#setSessionTable(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionTable(String)}
   */
  @Test
  public void testSetSessionTable() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionTable("Session Table");

    // Assert
    assertEquals("Session Table", dataSourceStore.getSessionTable());
  }

  /**
   * Test {@link DataSourceStore#setSessionAppCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionAppCol(String)}
   */
  @Test
  public void testSetSessionAppCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionAppCol("Session App Col");

    // Assert
    assertEquals("Session App Col", dataSourceStore.getSessionAppCol());
  }

  /**
   * Test {@link DataSourceStore#setSessionIdCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionIdCol(String)}
   */
  @Test
  public void testSetSessionIdCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionIdCol("Session Id Col");

    // Assert
    assertEquals("Session Id Col", dataSourceStore.getSessionIdCol());
  }

  /**
   * Test {@link DataSourceStore#setSessionDataCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionDataCol(String)}
   */
  @Test
  public void testSetSessionDataCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionDataCol("Session Data Col");

    // Assert
    assertEquals("Session Data Col", dataSourceStore.getSessionDataCol());
  }

  /**
   * Test {@link DataSourceStore#setSessionValidCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionValidCol(String)}
   */
  @Test
  public void testSetSessionValidCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionValidCol("Session Valid Col");

    // Assert
    assertEquals("Session Valid Col", dataSourceStore.getSessionValidCol());
  }

  /**
   * Test {@link DataSourceStore#setSessionMaxInactiveCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionMaxInactiveCol(String)}
   */
  @Test
  public void testSetSessionMaxInactiveCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionMaxInactiveCol("Session Max Inactive Col");

    // Assert
    assertEquals("Session Max Inactive Col", dataSourceStore.getSessionMaxInactiveCol());
  }

  /**
   * Test {@link DataSourceStore#setSessionLastAccessedCol(String)}.
   * <p>
   * Method under test: {@link DataSourceStore#setSessionLastAccessedCol(String)}
   */
  @Test
  public void testSetSessionLastAccessedCol() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setSessionLastAccessedCol("Session Last Accessed Col");

    // Assert
    assertEquals("Session Last Accessed Col", dataSourceStore.getSessionLastAccessedCol());
  }

  /**
   * Test {@link DataSourceStore#setDataSourceName(String)}.
   * <ul>
   *   <li>Then {@link DataSourceStore} (default constructor) DataSourceName is {@code Data Source Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#setDataSourceName(String)}
   */
  @Test
  public void testSetDataSourceName_thenDataSourceStoreDataSourceNameIsDataSourceName() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setDataSourceName("Data Source Name");

    // Assert
    assertEquals("Data Source Name", dataSourceStore.getDataSourceName());
  }

  /**
   * Test {@link DataSourceStore#setDataSourceName(String)}.
   * <ul>
   *   <li>Then {@link DataSourceStore} (default constructor) DataSourceName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#setDataSourceName(String)}
   */
  @Test
  public void testSetDataSourceName_thenDataSourceStoreDataSourceNameIsNull() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act
    dataSourceStore.setDataSourceName("");

    // Assert that nothing has changed
    assertNull(dataSourceStore.getDataSourceName());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DataSourceStore#setLocalDataSource(boolean)}
   *   <li>{@link DataSourceStore#getDataSourceName()}
   *   <li>{@link DataSourceStore#getLocalDataSource()}
   *   <li>{@link DataSourceStore#getSessionAppCol()}
   *   <li>{@link DataSourceStore#getSessionDataCol()}
   *   <li>{@link DataSourceStore#getSessionIdCol()}
   *   <li>{@link DataSourceStore#getSessionLastAccessedCol()}
   *   <li>{@link DataSourceStore#getSessionMaxInactiveCol()}
   *   <li>{@link DataSourceStore#getSessionTable()}
   *   <li>{@link DataSourceStore#getSessionValidCol()}
   *   <li>{@link DataSourceStore#getStoreName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act
    dataSourceStore.setLocalDataSource(true);
    String actualDataSourceName = dataSourceStore.getDataSourceName();
    boolean actualLocalDataSource = dataSourceStore.getLocalDataSource();
    String actualSessionAppCol = dataSourceStore.getSessionAppCol();
    String actualSessionDataCol = dataSourceStore.getSessionDataCol();
    String actualSessionIdCol = dataSourceStore.getSessionIdCol();
    String actualSessionLastAccessedCol = dataSourceStore.getSessionLastAccessedCol();
    String actualSessionMaxInactiveCol = dataSourceStore.getSessionMaxInactiveCol();
    String actualSessionTable = dataSourceStore.getSessionTable();
    String actualSessionValidCol = dataSourceStore.getSessionValidCol();

    // Assert
    assertEquals("app", actualSessionAppCol);
    assertEquals("data", actualSessionDataCol);
    assertEquals("dataSourceStore", dataSourceStore.getStoreName());
    assertEquals("id", actualSessionIdCol);
    assertEquals("lastaccess", actualSessionLastAccessedCol);
    assertEquals("maxinactive", actualSessionMaxInactiveCol);
    assertEquals("tomcat$sessions", actualSessionTable);
    assertEquals("valid", actualSessionValidCol);
    assertNull(actualDataSourceName);
    assertTrue(actualLocalDataSource);
  }

  /**
   * Test {@link DataSourceStore#expiredKeys()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#expiredKeys()}
   */
  @Test
  public void testExpiredKeys_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.expiredKeys());
  }

  /**
   * Test {@link DataSourceStore#expiredKeys()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#expiredKeys()}
   */
  @Test
  public void testExpiredKeys_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.expiredKeys());
  }

  /**
   * Test {@link DataSourceStore#expiredKeys()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#expiredKeys()}
   */
  @Test
  public void testExpiredKeys_givenDataSourceStore_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).expiredKeys());
  }

  /**
   * Test {@link DataSourceStore#keys()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#keys()}
   */
  @Test
  public void testKeys_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.keys());
  }

  /**
   * Test {@link DataSourceStore#keys()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#keys()}
   */
  @Test
  public void testKeys_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.keys());
  }

  /**
   * Test {@link DataSourceStore#keys()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#keys()}
   */
  @Test
  public void testKeys_givenDataSourceStore_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).keys());
  }

  /**
   * Test {@link DataSourceStore#getSize()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getSize()}
   */
  @Test
  public void testGetSize_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.getSize());
  }

  /**
   * Test {@link DataSourceStore#getSize()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getSize()}
   */
  @Test
  public void testGetSize_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.getSize());
  }

  /**
   * Test {@link DataSourceStore#getSize()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getSize()}
   */
  @Test
  public void testGetSize_givenDataSourceStore_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).getSize());
  }

  /**
   * Test {@link DataSourceStore#load(String)}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor) LocalDataSource is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#load(String)}
   */
  @Test
  public void testLoad_givenDataSourceStoreLocalDataSourceIsTrue() throws IOException, ClassNotFoundException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setDataSourceName("/");
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.load("42"));
  }

  /**
   * Test {@link DataSourceStore#load(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#load(String)}
   */
  @Test
  public void testLoad_thenThrowIllegalStateException() throws IOException, ClassNotFoundException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.load("42"));
  }

  /**
   * Test {@link DataSourceStore#load(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#load(String)}
   */
  @Test
  public void testLoad_thenThrowIllegalStateException2() throws IOException, ClassNotFoundException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setDataSourceName("/");
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.load("42"));
  }

  /**
   * Test {@link DataSourceStore#remove(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#remove(String)}
   */
  @Test
  public void testRemoveWithId_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.remove("42"));
  }

  /**
   * Test {@link DataSourceStore#remove(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#remove(String)}
   */
  @Test
  public void testRemoveWithId_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.remove("42"));
  }

  /**
   * Test {@link DataSourceStore#remove(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#remove(String)}
   */
  @Test
  public void testRemoveWithId_givenDataSourceStore_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).remove("42"));
  }

  /**
   * Test {@link DataSourceStore#clear()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#clear()}
   */
  @Test
  public void testClear_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.clear());
  }

  /**
   * Test {@link DataSourceStore#clear()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#clear()}
   */
  @Test
  public void testClear_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.clear());
  }

  /**
   * Test {@link DataSourceStore#clear()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#clear()}
   */
  @Test
  public void testClear_givenDataSourceStore_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).clear());
  }

  /**
   * Test {@link DataSourceStore#save(Session)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#save(Session)}
   */
  @Test
  public void testSave_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.save(new DeltaSession()));
  }

  /**
   * Test {@link DataSourceStore#save(Session)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#save(Session)}
   */
  @Test
  public void testSave_givenBackupManagerContextIsStandardContext2() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.save(new DeltaSession()));
  }

  /**
   * Test {@link DataSourceStore#save(Session)}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#save(Session)}
   */
  @Test
  public void testSave_givenDataSourceStore_whenDeltaSession_thenThrowIllegalStateException() throws IOException {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.save(new DeltaSession()));
  }

  /**
   * Test {@link DataSourceStore#getConnection()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getConnection()}
   */
  @Test
  public void testGetConnection_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.getConnection());
  }

  /**
   * Test {@link DataSourceStore#getConnection()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getConnection()}
   */
  @Test
  public void testGetConnection_givenBackupManagerContextIsStandardContext2() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.getConnection());
  }

  /**
   * Test {@link DataSourceStore#getConnection()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#getConnection()}
   */
  @Test
  public void testGetConnection_givenDataSourceStore_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).getConnection());
  }

  /**
   * Test {@link DataSourceStore#open()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#open()}
   */
  @Test
  public void testOpen_givenBackupManagerContextIsStandardContext() throws SQLException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.open());
  }

  /**
   * Test {@link DataSourceStore#open()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#open()}
   */
  @Test
  public void testOpen_givenBackupManagerContextIsStandardContext2() throws SQLException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setLocalDataSource(true);
    dataSourceStore.setManager(manager);
    dataSourceStore.setDataSourceName("dataSourceStore.missingDataSource");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dataSourceStore.open());
  }

  /**
   * Test {@link DataSourceStore#open()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceStore#open()}
   */
  @Test
  public void testOpen_givenDataSourceStore_thenThrowIllegalStateException() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DataSourceStore()).open());
  }

  /**
   * Test new {@link DataSourceStore} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DataSourceStore}
   */
  @Test
  public void testNewDataSourceStore() {
    // Arrange and Act
    DataSourceStore actualDataSourceStore = new DataSourceStore();

    // Assert
    assertEquals("NEW", actualDataSourceStore.getStateName());
    assertEquals("app", actualDataSourceStore.getSessionAppCol());
    assertEquals("data", actualDataSourceStore.getSessionDataCol());
    assertEquals("dataSourceStore", actualDataSourceStore.getStoreName());
    assertEquals("id", actualDataSourceStore.getSessionIdCol());
    assertEquals("lastaccess", actualDataSourceStore.getSessionLastAccessedCol());
    assertEquals("maxinactive", actualDataSourceStore.getSessionMaxInactiveCol());
    assertEquals("tomcat$sessions", actualDataSourceStore.getSessionTable());
    assertEquals("valid", actualDataSourceStore.getSessionValidCol());
    assertNull(actualDataSourceStore.getDataSourceName());
    assertNull(actualDataSourceStore.dataSource);
    assertNull(actualDataSourceStore.getManager());
    assertEquals(0, actualDataSourceStore.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualDataSourceStore.getState());
    assertFalse(actualDataSourceStore.getLocalDataSource());
    assertTrue(actualDataSourceStore.getThrowOnFailure());
  }
}
