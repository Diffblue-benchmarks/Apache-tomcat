package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ContainerMBeanDiffblueTest {
  /**
   * Test new {@link ContainerMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ContainerMBean}
   */
  @Test
  public void testNewContainerMBean() {
    // Arrange and Act
    ContainerMBean actualContainerMBean = new ContainerMBean();

    // Assert
    assertNull(actualContainerMBean.getModelerType());
    assertNull(actualContainerMBean.getJmxName());
  }
}
