package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class GlobalNamingResourcesSFDiffblueTest {
  /**
   * Test new {@link GlobalNamingResourcesSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link GlobalNamingResourcesSF}
   */
  @Test
  public void testNewGlobalNamingResourcesSF() {
    // Arrange and Act
    GlobalNamingResourcesSF actualGlobalNamingResourcesSF = new GlobalNamingResourcesSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualGlobalNamingResourcesSF.getInfo());
    assertNull(actualGlobalNamingResourcesSF.getRegistry());
  }
}
