package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class WrapperListenerSFDiffblueTest {
  /**
   * Test new {@link WrapperListenerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WrapperListenerSF}
   */
  @Test
  public void testNewWrapperListenerSF() {
    // Arrange and Act
    WrapperListenerSF actualWrapperListenerSF = new WrapperListenerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualWrapperListenerSF.getInfo());
    assertNull(actualWrapperListenerSF.getRegistry());
  }
}
