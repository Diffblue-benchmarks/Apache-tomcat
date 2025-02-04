package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class AddPortOffsetRuleDiffblueTest {
  /**
   * Test new {@link AddPortOffsetRule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link AddPortOffsetRule}
   */
  @Test
  public void testNewAddPortOffsetRule() {
    // Arrange and Act
    AddPortOffsetRule actualAddPortOffsetRule = new AddPortOffsetRule();

    // Assert
    assertNull(actualAddPortOffsetRule.getNamespaceURI());
    assertNull(actualAddPortOffsetRule.getDigester());
  }
}
