package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CopyParentClassLoaderRuleDiffblueTest {
  /**
   * Test new {@link CopyParentClassLoaderRule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CopyParentClassLoaderRule}
   */
  @Test
  public void testNewCopyParentClassLoaderRule() {
    // Arrange and Act
    CopyParentClassLoaderRule actualCopyParentClassLoaderRule = new CopyParentClassLoaderRule();

    // Assert
    assertNull(actualCopyParentClassLoaderRule.getNamespaceURI());
    assertNull(actualCopyParentClassLoaderRule.getDigester());
  }
}
