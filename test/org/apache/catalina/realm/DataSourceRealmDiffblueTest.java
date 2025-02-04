package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class DataSourceRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DataSourceRealm#setDataSourceName(String)}
   *   <li>{@link DataSourceRealm#setLocalDataSource(boolean)}
   *   <li>{@link DataSourceRealm#setRoleNameCol(String)}
   *   <li>{@link DataSourceRealm#setUserCredCol(String)}
   *   <li>{@link DataSourceRealm#setUserNameCol(String)}
   *   <li>{@link DataSourceRealm#setUserRoleTable(String)}
   *   <li>{@link DataSourceRealm#setUserTable(String)}
   *   <li>{@link DataSourceRealm#getDataSourceName()}
   *   <li>{@link DataSourceRealm#getLocalDataSource()}
   *   <li>{@link DataSourceRealm#getRoleNameCol()}
   *   <li>{@link DataSourceRealm#getUserCredCol()}
   *   <li>{@link DataSourceRealm#getUserNameCol()}
   *   <li>{@link DataSourceRealm#getUserRoleTable()}
   *   <li>{@link DataSourceRealm#getUserTable()}
   *   <li>{@link DataSourceRealm#isAvailable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DataSourceRealm dataSourceRealm = new DataSourceRealm();

    // Act
    dataSourceRealm.setDataSourceName("Data Source Name");
    dataSourceRealm.setLocalDataSource(true);
    dataSourceRealm.setRoleNameCol("Role Name Col");
    dataSourceRealm.setUserCredCol("User Cred Col");
    dataSourceRealm.setUserNameCol("janedoe");
    dataSourceRealm.setUserRoleTable("User Role Table");
    dataSourceRealm.setUserTable("User Table");
    String actualDataSourceName = dataSourceRealm.getDataSourceName();
    boolean actualLocalDataSource = dataSourceRealm.getLocalDataSource();
    String actualRoleNameCol = dataSourceRealm.getRoleNameCol();
    String actualUserCredCol = dataSourceRealm.getUserCredCol();
    String actualUserNameCol = dataSourceRealm.getUserNameCol();
    String actualUserRoleTable = dataSourceRealm.getUserRoleTable();
    String actualUserTable = dataSourceRealm.getUserTable();

    // Assert
    assertEquals("Data Source Name", actualDataSourceName);
    assertEquals("Role Name Col", actualRoleNameCol);
    assertEquals("User Cred Col", actualUserCredCol);
    assertEquals("User Role Table", actualUserRoleTable);
    assertEquals("User Table", actualUserTable);
    assertEquals("janedoe", actualUserNameCol);
    assertTrue(actualLocalDataSource);
    assertTrue(dataSourceRealm.isAvailable());
  }

  /**
   * Test new {@link DataSourceRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DataSourceRealm}
   */
  @Test
  public void testNewDataSourceRealm() {
    // Arrange and Act
    DataSourceRealm actualDataSourceRealm = new DataSourceRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualDataSourceRealm.getRealmSuffix());
    assertEquals("/realm0", actualDataSourceRealm.getRealmPath());
    assertEquals("NEW", actualDataSourceRealm.getStateName());
    assertEquals("strict", actualDataSourceRealm.getAllRolesMode());
    assertNull(actualDataSourceRealm.getDataSourceName());
    assertNull(actualDataSourceRealm.getRoleNameCol());
    assertNull(actualDataSourceRealm.getUserCredCol());
    assertNull(actualDataSourceRealm.getUserNameCol());
    assertNull(actualDataSourceRealm.getUserRoleTable());
    assertNull(actualDataSourceRealm.getUserTable());
    assertNull(actualDataSourceRealm.getUserAttributes());
    assertNull(actualDataSourceRealm.getX509UsernameRetrieverClassName());
    assertNull(actualDataSourceRealm.userAttributesList);
    assertNull(actualDataSourceRealm.getObjectName());
    assertNull(actualDataSourceRealm.getContainer());
    assertNull(actualDataSourceRealm.getCredentialHandler());
    assertNull(actualDataSourceRealm.getServer());
    assertNull(actualDataSourceRealm.x509UsernameRetriever);
    assertNull(actualDataSourceRealm.containerLog);
    assertEquals(0, actualDataSourceRealm.findLifecycleListeners().length);
    assertEquals(302, actualDataSourceRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualDataSourceRealm.getState());
    assertFalse(actualDataSourceRealm.getLocalDataSource());
    assertTrue(actualDataSourceRealm.isAvailable());
    assertTrue(actualDataSourceRealm.getValidate());
    assertTrue(actualDataSourceRealm.isStripRealmForGss());
    assertTrue(actualDataSourceRealm.getThrowOnFailure());
  }
}
