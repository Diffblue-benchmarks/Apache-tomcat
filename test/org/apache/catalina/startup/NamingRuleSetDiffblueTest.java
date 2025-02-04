package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.apache.tomcat.util.digester.Rule;
import org.apache.tomcat.util.digester.Rules;
import org.apache.tomcat.util.digester.RulesBase;
import org.apache.tomcat.util.digester.SetPropertiesRule;
import org.junit.Test;

public class NamingRuleSetDiffblueTest {
  /**
   * Test {@link NamingRuleSet#NamingRuleSet()}.
   * <ul>
   *   <li>Then return {@link NamingRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingRuleSet#NamingRuleSet()}
   */
  @Test
  public void testNewNamingRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new NamingRuleSet()).prefix);
  }

  /**
   * Test {@link NamingRuleSet#NamingRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingRuleSet#NamingRuleSet(String)}
   */
  @Test
  public void testNewNamingRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new NamingRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link NamingRuleSet#addRuleInstances(Digester)}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is twenty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstances_whenDigester_thenDigesterRulesRulesSizeIsTwentyOne() {
    // Arrange
    NamingRuleSet namingRuleSet = new NamingRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    namingRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(21, rulesResult.size());
    Rule getResult = rulesResult.get(20);
    assertTrue(getResult instanceof SetNextNamingRule);
    Rule getResult2 = rulesResult.get(0);
    assertTrue(getResult2 instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult3 = rulesResult.get(1);
    assertTrue(getResult3 instanceof SetPropertiesRule);
    Rule getResult4 = rulesResult.get(19);
    assertTrue(getResult4 instanceof SetPropertiesRule);
    assertEquals("org.apache.tomcat.util.descriptor.web.ContextTransaction", ((SetNextNamingRule) getResult).paramType);
    assertEquals("setTransaction", ((SetNextNamingRule) getResult).methodName);
    assertNull(getResult2.getNamespaceURI());
    assertNull(getResult3.getNamespaceURI());
    assertNull(getResult4.getNamespaceURI());
    assertNull(getResult.getNamespaceURI());
    assertSame(digester, getResult2.getDigester());
    assertSame(digester, getResult3.getDigester());
    assertSame(digester, getResult4.getDigester());
    assertSame(digester, getResult.getDigester());
  }
}
