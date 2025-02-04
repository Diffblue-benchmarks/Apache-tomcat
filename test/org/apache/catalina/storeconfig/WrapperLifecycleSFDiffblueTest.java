package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class WrapperLifecycleSFDiffblueTest {
  /**
   * Test new {@link WrapperLifecycleSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WrapperLifecycleSF}
   */
  @Test
  public void testNewWrapperLifecycleSF() {
    // Arrange and Act
    WrapperLifecycleSF actualWrapperLifecycleSF = new WrapperLifecycleSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualWrapperLifecycleSF.getInfo());
    assertNull(actualWrapperLifecycleSF.getRegistry());
  }
}
