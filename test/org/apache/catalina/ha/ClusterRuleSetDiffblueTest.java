package org.apache.catalina.ha;

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

public class ClusterRuleSetDiffblueTest {
  /**
   * Test {@link ClusterRuleSet#ClusterRuleSet()}.
   * <ul>
   *   <li>Then return {@link ClusterRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterRuleSet#ClusterRuleSet()}
   */
  @Test
  public void testNewClusterRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new ClusterRuleSet()).prefix);
  }

  /**
   * Test {@link ClusterRuleSet#ClusterRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterRuleSet#ClusterRuleSet(String)}
   */
  @Test
  public void testNewClusterRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new ClusterRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link ClusterRuleSet#addRuleInstances(Digester)}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is fifty-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstances_whenDigester_thenDigesterRulesRulesSizeIsFiftyFour() {
    // Arrange
    ClusterRuleSet clusterRuleSet = new ClusterRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    clusterRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(54, rulesResult.size());
    Rule getResult = rulesResult.get(0);
    assertTrue(getResult instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult2 = rulesResult.get(Double.PRECISION);
    assertTrue(getResult2 instanceof SetNextRule);
    Rule getResult3 = rulesResult.get(1);
    assertTrue(getResult3 instanceof SetPropertiesRule);
    Rule getResult4 = rulesResult.get(52);
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
