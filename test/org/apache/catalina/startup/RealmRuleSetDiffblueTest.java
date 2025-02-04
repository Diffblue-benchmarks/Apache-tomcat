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

public class RealmRuleSetDiffblueTest {
  /**
   * Test {@link RealmRuleSet#RealmRuleSet()}.
   * <ul>
   *   <li>Then return {@link RealmRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmRuleSet#RealmRuleSet()}
   */
  @Test
  public void testNewRealmRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new RealmRuleSet()).prefix);
  }

  /**
   * Test {@link RealmRuleSet#RealmRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmRuleSet#RealmRuleSet(String)}
   */
  @Test
  public void testNewRealmRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new RealmRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link RealmRuleSet#addRuleInstances(Digester)} with {@code digester}.
   * <ul>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is thirty-six.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstancesWithDigester_thenDigesterRulesRulesSizeIsThirtySix() {
    // Arrange
    RealmRuleSet realmRuleSet = new RealmRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    realmRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(36, rulesResult.size());
    Rule getResult = rulesResult.get(0);
    assertTrue(getResult instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult2 = rulesResult.get(35);
    assertTrue(getResult2 instanceof SetNextRule);
    Rule getResult3 = rulesResult.get(1);
    assertTrue(getResult3 instanceof SetPropertiesRule);
    Rule getResult4 = rulesResult.get(34);
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
