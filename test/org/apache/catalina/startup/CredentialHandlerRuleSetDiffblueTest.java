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

public class CredentialHandlerRuleSetDiffblueTest {
  /**
   * Test {@link CredentialHandlerRuleSet#CredentialHandlerRuleSet()}.
   * <ul>
   *   <li>Then return {@link CredentialHandlerRuleSet#prefix} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CredentialHandlerRuleSet#CredentialHandlerRuleSet()}
   */
  @Test
  public void testNewCredentialHandlerRuleSet_thenReturnPrefixIsEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new CredentialHandlerRuleSet()).prefix);
  }

  /**
   * Test {@link CredentialHandlerRuleSet#CredentialHandlerRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CredentialHandlerRuleSet#CredentialHandlerRuleSet(String)}
   */
  @Test
  public void testNewCredentialHandlerRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new CredentialHandlerRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link CredentialHandlerRuleSet#addRuleInstances(Digester)} with {@code digester}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is nine.</li>
   * </ul>
   * <p>
   * Method under test: {@link CredentialHandlerRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstancesWithDigester_whenDigester_thenDigesterRulesRulesSizeIsNine() {
    // Arrange
    CredentialHandlerRuleSet credentialHandlerRuleSet = new CredentialHandlerRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    credentialHandlerRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(9, rulesResult.size());
    Rule getResult = rulesResult.get(0);
    assertTrue(getResult instanceof ObjectCreateRule);
    assertTrue(rules instanceof RulesBase);
    Rule getResult2 = rulesResult.get(8);
    assertTrue(getResult2 instanceof SetNextRule);
    Rule getResult3 = rulesResult.get(1);
    assertTrue(getResult3 instanceof SetPropertiesRule);
    Rule getResult4 = rulesResult.get(7);
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
