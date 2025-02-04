package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardService;
import org.apache.jasper.util.UniqueAttributesImpl;
import org.apache.tomcat.util.digester.Digester;
import org.junit.Test;
import org.xml.sax.Attributes;

public class ConnectorCreateRuleDiffblueTest {
  /**
   * Test {@link ConnectorCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Given {@link ConnectorCreateRule} (default constructor) Digester is createDigester {@code digester.emptyStack}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_givenConnectorCreateRuleDigesterIsCreateDigesterDigesterEmptyStack() throws Exception {
    // Arrange
    ConnectorCreateRule connectorCreateRule = new ConnectorCreateRule();
    connectorCreateRule.setDigester(HostConfig.createDigester("digester.emptyStack"));

    // Act
    connectorCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester = connectorCreateRule.getDigester();
    assertTrue(digester.getRoot() instanceof Connector);
    assertEquals(1, digester.getCount());
  }

  /**
   * Test {@link ConnectorCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Then {@link ConnectorCreateRule} (default constructor) Digester Root {@link Connector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_thenConnectorCreateRuleDigesterRootConnector() throws Exception {
    // Arrange
    ConnectorCreateRule connectorCreateRule = new ConnectorCreateRule();
    connectorCreateRule.setDigester(new Digester());

    // Act
    connectorCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester = connectorCreateRule.getDigester();
    assertTrue(digester.getRoot() instanceof Connector);
    assertEquals(1, digester.getCount());
  }

  /**
   * Test {@link ConnectorCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Then {@link ConnectorCreateRule} (default constructor) Digester Root {@link StandardService}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_thenConnectorCreateRuleDigesterRootStandardService() throws Exception {
    // Arrange
    Digester digester = new Digester();
    StandardService standardService = new StandardService();
    digester.push(standardService);

    ConnectorCreateRule connectorCreateRule = new ConnectorCreateRule();
    connectorCreateRule.setDigester(digester);

    // Act
    connectorCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester2 = connectorCreateRule.getDigester();
    Object root = digester2.getRoot();
    assertTrue(root instanceof StandardService);
    assertEquals(2, digester2.getCount());
    assertSame(standardService, root);
  }

  /**
   * Test {@link ConnectorCreateRule#end(String, String)}.
   * <ul>
   *   <li>Given {@link Digester} (default constructor) push {@code Object}.</li>
   *   <li>Then {@link ConnectorCreateRule} (default constructor) Digester Count is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorCreateRule#end(String, String)}
   */
  @Test
  public void testEnd_givenDigesterPushObject_thenConnectorCreateRuleDigesterCountIsZero() throws Exception {
    // Arrange
    Digester digester = new Digester();
    digester.push("Object");

    ConnectorCreateRule connectorCreateRule = new ConnectorCreateRule();
    connectorCreateRule.setDigester(digester);

    // Act
    connectorCreateRule.end("Namespace", "Name");

    // Assert
    assertEquals(0, connectorCreateRule.getDigester().getCount());
  }

  /**
   * Test {@link ConnectorCreateRule#end(String, String)}.
   * <ul>
   *   <li>Then {@link ConnectorCreateRule} (default constructor) Digester Count is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorCreateRule#end(String, String)}
   */
  @Test
  public void testEnd_thenConnectorCreateRuleDigesterCountIsZero() throws Exception {
    // Arrange
    ConnectorCreateRule connectorCreateRule = new ConnectorCreateRule();
    connectorCreateRule.setDigester(new Digester());

    // Act
    connectorCreateRule.end("Namespace", "Name");

    // Assert that nothing has changed
    assertEquals(0, connectorCreateRule.getDigester().getCount());
  }

  /**
   * Test new {@link ConnectorCreateRule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConnectorCreateRule}
   */
  @Test
  public void testNewConnectorCreateRule() {
    // Arrange and Act
    ConnectorCreateRule actualConnectorCreateRule = new ConnectorCreateRule();

    // Assert
    assertNull(actualConnectorCreateRule.getNamespaceURI());
    assertNull(actualConnectorCreateRule.getDigester());
  }
}
