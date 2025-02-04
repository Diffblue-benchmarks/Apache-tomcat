package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ServiceMBeanDiffblueTest {
  /**
   * Test new {@link ServiceMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ServiceMBean}
   */
  @Test
  public void testNewServiceMBean() {
    // Arrange and Act
    ServiceMBean actualServiceMBean = new ServiceMBean();

    // Assert
    assertNull(actualServiceMBean.getModelerType());
    assertNull(actualServiceMBean.getJmxName());
  }
}
