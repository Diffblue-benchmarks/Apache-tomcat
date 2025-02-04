package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SetNextNamingRuleDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SetNextNamingRule#SetNextNamingRule(String, String)}
   *   <li>{@link SetNextNamingRule#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    SetNextNamingRule actualSetNextNamingRule = new SetNextNamingRule("Method Name", "Param Type");

    // Assert
    assertEquals("SetNextRule[methodName=Method Name, paramType=Param Type]", actualSetNextNamingRule.toString());
    assertNull(actualSetNextNamingRule.getNamespaceURI());
    assertNull(actualSetNextNamingRule.getDigester());
  }
}
