package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class RealmSFDiffblueTest {
  /**
   * Test new {@link RealmSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RealmSF}
   */
  @Test
  public void testNewRealmSF() {
    // Arrange and Act
    RealmSF actualRealmSF = new RealmSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualRealmSF.getInfo());
    assertNull(actualRealmSF.getRegistry());
  }
}
