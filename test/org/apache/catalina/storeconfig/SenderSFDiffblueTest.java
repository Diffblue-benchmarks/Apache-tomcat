package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SenderSFDiffblueTest {
  /**
   * Test new {@link SenderSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SenderSF}
   */
  @Test
  public void testNewSenderSF() {
    // Arrange and Act
    SenderSF actualSenderSF = new SenderSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualSenderSF.getInfo());
    assertNull(actualSenderSF.getRegistry());
  }
}
