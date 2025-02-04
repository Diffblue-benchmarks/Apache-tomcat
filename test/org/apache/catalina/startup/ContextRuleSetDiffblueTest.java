package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.apache.tomcat.util.digester.Rule;
import org.apache.tomcat.util.digester.Rules;
import org.apache.tomcat.util.digester.RulesBase;
import org.apache.tomcat.util.digester.SetNextRule;
import org.apache.tomcat.util.digester.SetPropertiesRule;
import org.junit.Test;

public class ContextRuleSetDiffblueTest {
  /**
   * Test {@link ContextRuleSet#ContextRuleSet()}.
   * <ul>
   *   <li>Then return {@link ContextRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextRuleSet#ContextRuleSet()}
   */
  @Test
  public void testNewContextRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange and Act
    ContextRuleSet actualContextRuleSet = new ContextRuleSet();

    // Assert
    assertEquals("", actualContextRuleSet.prefix);
    assertTrue(actualContextRuleSet.create);
  }

  /**
   * Test {@link ContextRuleSet#ContextRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextRuleSet#ContextRuleSet(String)}
   */
  @Test
  public void testNewContextRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange and Act
    ContextRuleSet actualContextRuleSet = new ContextRuleSet("Prefix");

    // Assert
    assertEquals("Prefix", actualContextRuleSet.prefix);
    assertTrue(actualContextRuleSet.create);
  }

  /**
   * Test {@link ContextRuleSet#ContextRuleSet(String, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextRuleSet#ContextRuleSet(String, boolean)}
   */
  @Test
  public void testNewContextRuleSet_whenTrue_thenReturnPrefix() {
    // Arrange and Act
    ContextRuleSet actualContextRuleSet = new ContextRuleSet("Prefix", true);

    // Assert
    assertEquals("Prefix", actualContextRuleSet.prefix);
    assertTrue(actualContextRuleSet.create);
  }

  /**
   * Test {@link ContextRuleSet#addRuleInstances(Digester)}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is ninety-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstances_whenDigester_thenDigesterRulesRulesSizeIsNinetyFour() {
    // Arrange
    ContextRuleSet contextRuleSet = new ContextRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    contextRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(94, rulesResult.size());
    Rule getResult = rulesResult.get(0);
    assertTrue(getResult instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult2 = rulesResult.get(93);
    assertTrue(getResult2 instanceof SetNextRule);
    Rule getResult3 = rulesResult.get(1);
    assertTrue(getResult3 instanceof SetPropertiesRule);
    Rule getResult4 = rulesResult.get(92);
    assertTrue(getResult4 instanceof SetPropertiesRule);
    assertNull(getResult.getNamespaceURI());
    assertNull(getResult3.getNamespaceURI());
    assertNull(getResult4.getNamespaceURI());
    assertNull(getResult2.getNamespaceURI());
    assertFalse(((SetNextRule) getResult2).isExactMatch());
    assertSame(digester, getResult.getDigester());
    assertSame(digester, getResult3.getDigester());
    assertSame(digester, getResult4.getDigester());
    assertSame(digester, getResult2.getDigester());
  }
}
