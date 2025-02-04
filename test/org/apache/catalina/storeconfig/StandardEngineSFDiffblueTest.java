package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StandardEngineSFDiffblueTest {
  /**
   * Test new {@link StandardEngineSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardEngineSF}
   */
  @Test
  public void testNewStandardEngineSF() {
    // Arrange and Act
    StandardEngineSF actualStandardEngineSF = new StandardEngineSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStandardEngineSF.getInfo());
    assertNull(actualStandardEngineSF.getRegistry());
  }
}
