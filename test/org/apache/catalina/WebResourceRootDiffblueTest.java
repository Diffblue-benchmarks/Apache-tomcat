package org.apache.catalina;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.WebResourceRoot.ArchiveIndexStrategy;
import org.apache.catalina.webresources.ExtractingRoot;
import org.junit.Test;

public class WebResourceRootDiffblueTest {
  /**
   * Test ArchiveIndexStrategy getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ArchiveIndexStrategy#getRetain()}
   *   <li>{@link ArchiveIndexStrategy#getUsesBloom()}
   * </ul>
   */
  @Test
  public void testArchiveIndexStrategyGettersAndSetters() {
    // Arrange
    ArchiveIndexStrategy valueOfResult = ArchiveIndexStrategy.valueOf("SIMPLE");

    // Act
    boolean actualRetain = valueOfResult.getRetain();

    // Assert
    assertFalse(actualRetain);
    assertFalse(valueOfResult.getUsesBloom());
  }

  /**
   * Test {@link WebResourceRoot#isReadOnly()}.
   * <p>
   * Method under test: {@link WebResourceRoot#isReadOnly()}
   */
  @Test
  public void testIsReadOnly() {
    // Arrange, Act and Assert
    assertTrue((new ExtractingRoot()).isReadOnly());
  }
}
