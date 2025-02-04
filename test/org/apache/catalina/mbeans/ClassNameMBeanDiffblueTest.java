package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ClassNameMBeanDiffblueTest {
  /**
   * Test new {@link ClassNameMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ClassNameMBean}
   */
  @Test
  public void testNewClassNameMBean() {
    // Arrange and Act
    ClassNameMBean<Object> actualClassNameMBean = new ClassNameMBean<>();

    // Assert
    assertNull(actualClassNameMBean.getModelerType());
    assertNull(actualClassNameMBean.getJmxName());
  }
}
