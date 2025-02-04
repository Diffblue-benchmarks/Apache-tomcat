package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class MemoryUserDatabaseMBeanDiffblueTest {
  /**
   * Test new {@link MemoryUserDatabaseMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MemoryUserDatabaseMBean}
   */
  @Test
  public void testNewMemoryUserDatabaseMBean() {
    // Arrange and Act
    MemoryUserDatabaseMBean actualMemoryUserDatabaseMBean = new MemoryUserDatabaseMBean();

    // Assert
    assertNull(actualMemoryUserDatabaseMBean.getClassName());
    assertNull(actualMemoryUserDatabaseMBean.getModelerType());
    assertNull(actualMemoryUserDatabaseMBean.getObjectName());
    assertNull(actualMemoryUserDatabaseMBean.getJmxName());
  }
}
