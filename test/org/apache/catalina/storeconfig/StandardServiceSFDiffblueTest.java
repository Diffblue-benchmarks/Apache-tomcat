package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StandardServiceSFDiffblueTest {
  /**
   * Test new {@link StandardServiceSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardServiceSF}
   */
  @Test
  public void testNewStandardServiceSF() {
    // Arrange and Act
    StandardServiceSF actualStandardServiceSF = new StandardServiceSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStandardServiceSF.getInfo());
    assertNull(actualStandardServiceSF.getRegistry());
  }
}
