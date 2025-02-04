package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.Rule;
import org.apache.tomcat.util.digester.Rules;
import org.apache.tomcat.util.digester.RulesBase;
import org.junit.Test;

public class MemoryRuleSetDiffblueTest {
  /**
   * Test {@link MemoryRuleSet#MemoryRuleSet()}.
   * <ul>
   *   <li>Then return {@link MemoryRuleSet#prefix} is {@code tomcat-users/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRuleSet#MemoryRuleSet()}
   */
  @Test
  public void testNewMemoryRuleSet_thenReturnPrefixIsTomcatUsers() {
    // Arrange, Act and Assert
    assertEquals("tomcat-users/", (new MemoryRuleSet()).prefix);
  }

  /**
   * Test {@link MemoryRuleSet#MemoryRuleSet(String)}.
   * <ul>
   *   <li>When {@code Prefix}.</li>
   *   <li>Then return {@code Prefix}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRuleSet#MemoryRuleSet(String)}
   */
  @Test
  public void testNewMemoryRuleSet_whenPrefix_thenReturnPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", (new MemoryRuleSet("Prefix")).prefix);
  }

  /**
   * Test {@link MemoryRuleSet#addRuleInstances(Digester)}.
   * <ul>
   *   <li>When {@link Digester} (default constructor).</li>
   *   <li>Then {@link Digester} (default constructor) Rules rules size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRuleSet#addRuleInstances(Digester)}
   */
  @Test
  public void testAddRuleInstances_whenDigester_thenDigesterRulesRulesSizeIsOne() {
    // Arrange
    MemoryRuleSet memoryRuleSet = new MemoryRuleSet("Prefix");
    Digester digester = new Digester();

    // Act
    memoryRuleSet.addRuleInstances(digester);

    // Assert
    Rules rules = digester.getRules();
    List<Rule> rulesResult = rules.rules();
    assertEquals(1, rulesResult.size());
    Rule getResult = rulesResult.get(0);
    assertTrue(getResult instanceof MemoryUserRule);
    assertTrue(rules instanceof RulesBase);
    assertNull(getResult.getNamespaceURI());
    assertSame(digester, getResult.getDigester());
  }
}
