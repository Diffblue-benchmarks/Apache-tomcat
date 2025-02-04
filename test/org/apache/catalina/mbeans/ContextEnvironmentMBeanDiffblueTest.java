package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ContextEnvironmentMBeanDiffblueTest {
  /**
   * Test new {@link ContextEnvironmentMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ContextEnvironmentMBean}
   */
  @Test
  public void testNewContextEnvironmentMBean() {
    // Arrange and Act
    ContextEnvironmentMBean actualContextEnvironmentMBean = new ContextEnvironmentMBean();

    // Assert
    assertNull(actualContextEnvironmentMBean.getModelerType());
    assertNull(actualContextEnvironmentMBean.getJmxName());
  }
}
