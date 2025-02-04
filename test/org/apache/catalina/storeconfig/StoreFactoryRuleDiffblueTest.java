package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StoreFactoryRuleDiffblueTest {
  /**
   * Test {@link StoreFactoryRule#StoreFactoryRule(String, String, String, String)}.
   * <p>
   * Method under test: {@link StoreFactoryRule#StoreFactoryRule(String, String, String, String)}
   */
  @Test
  public void testNewStoreFactoryRule() {
    // Arrange and Act
    StoreFactoryRule actualStoreFactoryRule = new StoreFactoryRule("Store Factory Class", "Attribute Name",
        "Store Appender Class", "Appender Attribute Name");

    // Assert
    assertNull(actualStoreFactoryRule.getNamespaceURI());
    assertNull(actualStoreFactoryRule.getDigester());
  }
}
