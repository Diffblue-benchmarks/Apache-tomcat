package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PersistentManagerSFDiffblueTest {
  /**
   * Test new {@link PersistentManagerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PersistentManagerSF}
   */
  @Test
  public void testNewPersistentManagerSF() {
    // Arrange and Act
    PersistentManagerSF actualPersistentManagerSF = new PersistentManagerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualPersistentManagerSF.getInfo());
    assertNull(actualPersistentManagerSF.getRegistry());
  }
}
