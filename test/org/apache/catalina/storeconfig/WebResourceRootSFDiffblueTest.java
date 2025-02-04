package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class WebResourceRootSFDiffblueTest {
  /**
   * Test new {@link WebResourceRootSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WebResourceRootSF}
   */
  @Test
  public void testNewWebResourceRootSF() {
    // Arrange and Act
    WebResourceRootSF actualWebResourceRootSF = new WebResourceRootSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualWebResourceRootSF.getInfo());
    assertNull(actualWebResourceRootSF.getRegistry());
  }
}
