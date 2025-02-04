package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class NamingResourcesSFDiffblueTest {
  /**
   * Test new {@link NamingResourcesSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NamingResourcesSF}
   */
  @Test
  public void testNewNamingResourcesSF() {
    // Arrange and Act
    NamingResourcesSF actualNamingResourcesSF = new NamingResourcesSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualNamingResourcesSF.getInfo());
    assertNull(actualNamingResourcesSF.getRegistry());
  }
}
