package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SparseUserDatabaseMBeanDiffblueTest {
  /**
   * Test new {@link SparseUserDatabaseMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SparseUserDatabaseMBean}
   */
  @Test
  public void testNewSparseUserDatabaseMBean() {
    // Arrange and Act
    SparseUserDatabaseMBean actualSparseUserDatabaseMBean = new SparseUserDatabaseMBean();

    // Assert
    assertNull(actualSparseUserDatabaseMBean.getClassName());
    assertNull(actualSparseUserDatabaseMBean.getModelerType());
    assertNull(actualSparseUserDatabaseMBean.getObjectName());
    assertNull(actualSparseUserDatabaseMBean.getJmxName());
  }
}
