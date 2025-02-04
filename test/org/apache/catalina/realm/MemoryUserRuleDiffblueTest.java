package org.apache.catalina.realm;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class MemoryUserRuleDiffblueTest {
  /**
   * Test new {@link MemoryUserRule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MemoryUserRule}
   */
  @Test
  public void testNewMemoryUserRule() {
    // Arrange and Act
    MemoryUserRule actualMemoryUserRule = new MemoryUserRule();

    // Assert
    assertNull(actualMemoryUserRule.getNamespaceURI());
    assertNull(actualMemoryUserRule.getDigester());
  }
}
