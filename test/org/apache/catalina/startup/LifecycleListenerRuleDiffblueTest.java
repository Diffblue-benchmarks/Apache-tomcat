package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class LifecycleListenerRuleDiffblueTest {
  /**
   * Test {@link LifecycleListenerRule#LifecycleListenerRule(String, String)}.
   * <p>
   * Method under test: {@link LifecycleListenerRule#LifecycleListenerRule(String, String)}
   */
  @Test
  public void testNewLifecycleListenerRule() {
    // Arrange and Act
    LifecycleListenerRule actualLifecycleListenerRule = new LifecycleListenerRule("Listener Class", "Attribute Name");

    // Assert
    assertNull(actualLifecycleListenerRule.getNamespaceURI());
    assertNull(actualLifecycleListenerRule.getDigester());
  }
}
