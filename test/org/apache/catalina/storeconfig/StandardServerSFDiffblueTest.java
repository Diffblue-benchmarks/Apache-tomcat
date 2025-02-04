package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StandardServerSFDiffblueTest {
  /**
   * Test new {@link StandardServerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardServerSF}
   */
  @Test
  public void testNewStandardServerSF() {
    // Arrange and Act
    StandardServerSF actualStandardServerSF = new StandardServerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStandardServerSF.getInfo());
    assertNull(actualStandardServerSF.getRegistry());
  }
}
