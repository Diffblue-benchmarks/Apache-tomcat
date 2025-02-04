package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CatalinaClusterSFDiffblueTest {
  /**
   * Test new {@link CatalinaClusterSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CatalinaClusterSF}
   */
  @Test
  public void testNewCatalinaClusterSF() {
    // Arrange and Act
    CatalinaClusterSF actualCatalinaClusterSF = new CatalinaClusterSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualCatalinaClusterSF.getInfo());
    assertNull(actualCatalinaClusterSF.getRegistry());
  }
}
