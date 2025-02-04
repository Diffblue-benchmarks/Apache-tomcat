package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.junit.Test;

public class GroupMBeanDiffblueTest {
  /**
   * Test new {@link GroupMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link GroupMBean}
   */
  @Test
  public void testNewGroupMBean() {
    // Arrange and Act
    GroupMBean actualGroupMBean = new GroupMBean();

    // Assert
    ManagedBean managedBean = actualGroupMBean.managed;
    assertEquals("Group from a user database", managedBean.getDescription());
    assertEquals("Group", managedBean.getGroup());
    assertEquals("Group", managedBean.getName());
    assertEquals("Users", managedBean.getDomain());
    assertEquals("org.apache.catalina.Group", managedBean.getType());
    assertEquals("org.apache.catalina.mbeans.GroupMBean", managedBean.getClassName());
    assertNull(actualGroupMBean.getClassName());
    assertNull(actualGroupMBean.getModelerType());
    assertNull(actualGroupMBean.getObjectName());
    assertNull(actualGroupMBean.getJmxName());
    assertEquals(0, managedBean.getNotifications().length);
    assertEquals(3, managedBean.getOperations().length);
    assertEquals(5, managedBean.getAttributes().length);
  }
}
