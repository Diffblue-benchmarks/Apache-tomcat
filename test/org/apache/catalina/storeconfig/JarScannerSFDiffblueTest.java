package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class JarScannerSFDiffblueTest {
  /**
   * Test new {@link JarScannerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JarScannerSF}
   */
  @Test
  public void testNewJarScannerSF() {
    // Arrange and Act
    JarScannerSF actualJarScannerSF = new JarScannerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualJarScannerSF.getInfo());
    assertNull(actualJarScannerSF.getRegistry());
  }
}
