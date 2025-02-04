package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.junit.Test;

public class RoleMBeanDiffblueTest {
  /**
   * Test new {@link RoleMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RoleMBean}
   */
  @Test
  public void testNewRoleMBean() {
    // Arrange and Act
    RoleMBean actualRoleMBean = new RoleMBean();

    // Assert
    ManagedBean managedBean = actualRoleMBean.managed;
    assertEquals("Role", managedBean.getGroup());
    assertEquals("Role", managedBean.getName());
    assertEquals("Security role from a user database", managedBean.getDescription());
    assertEquals("Users", managedBean.getDomain());
    assertEquals("org.apache.catalina.Role", managedBean.getType());
    assertEquals("org.apache.catalina.mbeans.RoleMBean", managedBean.getClassName());
    assertNull(actualRoleMBean.getClassName());
    assertNull(actualRoleMBean.getModelerType());
    assertNull(actualRoleMBean.getObjectName());
    assertNull(actualRoleMBean.getJmxName());
    assertEquals(0, managedBean.getNotifications().length);
    assertEquals(0, managedBean.getOperations().length);
    assertEquals(3, managedBean.getAttributes().length);
  }
}
