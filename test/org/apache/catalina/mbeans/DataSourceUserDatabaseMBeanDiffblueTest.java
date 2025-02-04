package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.junit.Test;

public class DataSourceUserDatabaseMBeanDiffblueTest {
  /**
   * Test new {@link DataSourceUserDatabaseMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DataSourceUserDatabaseMBean}
   */
  @Test
  public void testNewDataSourceUserDatabaseMBean() {
    // Arrange and Act
    DataSourceUserDatabaseMBean actualDataSourceUserDatabaseMBean = new DataSourceUserDatabaseMBean();

    // Assert
    ManagedBean managedBean = actualDataSourceUserDatabaseMBean.managed;
    assertEquals("Catalina", managedBean.getDomain());
    assertEquals("DataSourceUserDatabase", managedBean.getName());
    assertEquals("Lazy load user and group database", managedBean.getDescription());
    assertEquals("UserDatabase", managedBean.getGroup());
    assertEquals("org.apache.catalina.mbeans.DataSourceUserDatabaseMBean", managedBean.getClassName());
    assertEquals("org.apache.catalina.users.DataSourceUserDatabase", managedBean.getType());
    assertNull(actualDataSourceUserDatabaseMBean.getClassName());
    assertNull(actualDataSourceUserDatabaseMBean.getModelerType());
    assertNull(actualDataSourceUserDatabaseMBean.getObjectName());
    assertNull(actualDataSourceUserDatabaseMBean.getJmxName());
    assertEquals(0, managedBean.getNotifications().length);
    assertEquals(17, managedBean.getOperations().length);
    assertEquals(6, managedBean.getAttributes().length);
  }
}
