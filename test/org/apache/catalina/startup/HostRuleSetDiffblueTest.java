package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.tomcat.util.digester.CallParamRule;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.apache.tomcat.util.digester.Rule;
import org.apache.tomcat.util.digester.Rules;
import org.apache.tomcat.util.digester.RulesBase;
import org.apache.tomcat.util.digester.SetNextRule;
import org.apache.tomcat.util.digester.SetPropertiesRule;
import org.junit.Test;

public class HostRuleSetDiffblueTest {
  /**
   * Test {@link HostRuleSet#HostRuleSet()}.
   * <ul>
   *   <li>Then return {@link HostRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostRuleSet#HostRuleSet()}
   */
  @Test
  public void testNewHostRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new HostRuleSet()).prefix);
  }

  /**
   * Test {@link HostRuleSet#HostRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostRuleSet#HostRuleSet(String)}
   */
  @Test
  public void testNewHostRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new HostRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link HostRuleSet#addRuleInstances(Digester)}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is fifty-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstances_whenDigester_thenDigesterRulesRulesSizeIsFiftyFour() {
    // Arrange
    HostRuleSet hostRuleSet = new HostRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    hostRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(54, rulesResult.size());
    Rule getResult = rulesResult.get(52);
    assertTrue(getResult instanceof CallParamRule);
    Rule getResult2 = rulesResult.get(0);
    assertTrue(getResult2 instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult3 = rulesResult.get(Double.PRECISION);
    assertTrue(getResult3 instanceof SetNextRule);
    Rule getResult4 = rulesResult.get(1);
    assertTrue(getResult4 instanceof SetPropertiesRule);
    assertNull(getResult2.getNamespaceURI());
    assertNull(getResult4.getNamespaceURI());
    assertNull(getResult.getNamespaceURI());
    assertNull(getResult3.getNamespaceURI());
    assertFalse(((SetNextRule) getResult3).isExactMatch());
    assertSame(digester, getResult2.getDigester());
    assertSame(digester, getResult4.getDigester());
    assertSame(digester, getResult.getDigester());
    assertSame(digester, getResult3.getDigester());
  }
}
