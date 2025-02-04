package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ContextMBeanDiffblueTest {
  /**
   * Test new {@link ContextMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ContextMBean}
   */
  @Test
  public void testNewContextMBean() {
    // Arrange and Act
    ContextMBean actualContextMBean = new ContextMBean();

    // Assert
    assertNull(actualContextMBean.getModelerType());
    assertNull(actualContextMBean.getJmxName());
  }
}
