package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.junit.Test;

public class UserMBeanDiffblueTest {
  /**
   * Test new {@link UserMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link UserMBean}
   */
  @Test
  public void testNewUserMBean() {
    // Arrange and Act
    UserMBean actualUserMBean = new UserMBean();

    // Assert
    ManagedBean managedBean = actualUserMBean.managed;
    assertEquals("User from a user database", managedBean.getDescription());
    assertEquals("User", managedBean.getGroup());
    assertEquals("User", managedBean.getName());
    assertEquals("Users", managedBean.getDomain());
    assertEquals("org.apache.catalina.User", managedBean.getType());
    assertEquals("org.apache.catalina.mbeans.UserMBean", managedBean.getClassName());
    assertNull(actualUserMBean.getClassName());
    assertNull(actualUserMBean.getModelerType());
    assertNull(actualUserMBean.getObjectName());
    assertNull(actualUserMBean.getJmxName());
    assertEquals(0, managedBean.getNotifications().length);
    assertEquals(6, managedBean.getAttributes().length);
    assertEquals(6, managedBean.getOperations().length);
  }
}
