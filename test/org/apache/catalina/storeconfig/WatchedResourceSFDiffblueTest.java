package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class WatchedResourceSFDiffblueTest {
  /**
   * Test new {@link WatchedResourceSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WatchedResourceSF}
   */
  @Test
  public void testNewWatchedResourceSF() {
    // Arrange and Act
    WatchedResourceSF actualWatchedResourceSF = new WatchedResourceSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualWatchedResourceSF.getInfo());
    assertNull(actualWatchedResourceSF.getRegistry());
  }
}
