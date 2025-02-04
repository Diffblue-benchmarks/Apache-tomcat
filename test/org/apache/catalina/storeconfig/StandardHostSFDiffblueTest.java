package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StandardHostSFDiffblueTest {
  /**
   * Test new {@link StandardHostSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardHostSF}
   */
  @Test
  public void testNewStandardHostSF() {
    // Arrange and Act
    StandardHostSF actualStandardHostSF = new StandardHostSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStandardHostSF.getInfo());
    assertNull(actualStandardHostSF.getRegistry());
  }
}
