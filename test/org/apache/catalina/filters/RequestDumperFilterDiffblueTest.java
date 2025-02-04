package org.apache.catalina.filters;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class RequestDumperFilterDiffblueTest {
  /**
   * Test new {@link RequestDumperFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RequestDumperFilter}
   */
  @Test
  public void testNewRequestDumperFilter() {
    // Arrange, Act and Assert
    assertNull((new RequestDumperFilter()).getFilterConfig());
  }
}
