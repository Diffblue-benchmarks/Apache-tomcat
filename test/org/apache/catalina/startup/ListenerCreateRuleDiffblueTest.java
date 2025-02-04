package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Set;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.startup.ListenerCreateRule.OptionalListener;
import org.apache.jasper.util.UniqueAttributesImpl;
import org.apache.tomcat.util.digester.Digester;
import org.junit.Test;
import org.xml.sax.Attributes;

public class ListenerCreateRuleDiffblueTest {
  /**
   * Test {@link ListenerCreateRule#ListenerCreateRule(String, String)}.
   * <p>
   * Method under test: {@link ListenerCreateRule#ListenerCreateRule(String, String)}
   */
  @Test
  public void testNewListenerCreateRule() {
    // Arrange and Act
    ListenerCreateRule actualListenerCreateRule = new ListenerCreateRule("Class Name", "Attribute Name");

    // Assert
    assertNull(actualListenerCreateRule.getNamespaceURI());
    assertNull(actualListenerCreateRule.getDigester());
  }

  /**
   * Test {@link ListenerCreateRule#begin(String, String, Attributes)}.
   * <p>
   * Method under test: {@link ListenerCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin() throws Exception {
    // Arrange
    ListenerCreateRule listenerCreateRule = new ListenerCreateRule("Class Name", "Attribute Name");
    listenerCreateRule.setDigester(new Digester());

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("optional", "optional", "optional", "optional", Boolean.TRUE.toString());

    // Act
    listenerCreateRule.begin("Namespace", "Name", attributes);

    // Assert
    Digester digester = listenerCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof OptionalListener);
    assertEquals("Class Name", ((OptionalListener) root).getClassName());
    assertEquals(1, digester.getCount());
    assertTrue(((OptionalListener) root).properties.isEmpty());
    assertTrue(((OptionalListener) root).getProperties().isEmpty());
  }

  /**
   * Test {@link ListenerCreateRule#begin(String, String, Attributes)}.
   * <p>
   * Method under test: {@link ListenerCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin2() throws Exception {
    // Arrange
    ListenerCreateRule listenerCreateRule = new ListenerCreateRule(null, "Attribute Name");
    listenerCreateRule.setDigester(new Digester());

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("optional", "optional", "optional", "optional", Boolean.TRUE.toString());

    // Act
    listenerCreateRule.begin("Namespace", "Name", attributes);

    // Assert
    Digester digester = listenerCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof OptionalListener);
    assertNull(((OptionalListener) root).getClassName());
    assertEquals(1, digester.getCount());
    assertTrue(((OptionalListener) root).properties.isEmpty());
    assertTrue(((OptionalListener) root).getProperties().isEmpty());
  }

  /**
   * Test {@link ListenerCreateRule#begin(String, String, Attributes)}.
   * <p>
   * Method under test: {@link ListenerCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin3() throws Exception {
    // Arrange
    ListenerCreateRule listenerCreateRule = new ListenerCreateRule("Class Name", "optional");
    listenerCreateRule.setDigester(new Digester());

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("optional", "optional", "optional", "optional", Boolean.TRUE.toString());

    // Act
    listenerCreateRule.begin("Namespace", "Name", attributes);

    // Assert
    Digester digester = listenerCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof OptionalListener);
    assertEquals(1, digester.getCount());
    assertTrue(((OptionalListener) root).properties.isEmpty());
    assertTrue(((OptionalListener) root).getProperties().isEmpty());
    String expectedClassName = Boolean.TRUE.toString();
    assertEquals(expectedClassName, ((OptionalListener) root).getClassName());
  }

  /**
   * Test {@link ListenerCreateRule#begin(String, String, Attributes)}.
   * <p>
   * Method under test: {@link ListenerCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin4() throws Exception {
    // Arrange
    ListenerCreateRule listenerCreateRule = new ListenerCreateRule("Class Name", null);
    listenerCreateRule.setDigester(new Digester());

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("optional", "optional", "optional", "optional", Boolean.TRUE.toString());

    // Act
    listenerCreateRule.begin("Namespace", "Name", attributes);

    // Assert
    Digester digester = listenerCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof OptionalListener);
    assertEquals("Class Name", ((OptionalListener) root).getClassName());
    assertEquals(1, digester.getCount());
    assertTrue(((OptionalListener) root).properties.isEmpty());
    assertTrue(((OptionalListener) root).getProperties().isEmpty());
  }

  /**
   * Test OptionalListener {@link OptionalListener#getProperties()}.
   * <p>
   * Method under test: {@link OptionalListener#getProperties()}
   */
  @Test
  public void testOptionalListenerGetProperties() {
    // Arrange, Act and Assert
    assertTrue((new OptionalListener("Class Name")).getProperties().isEmpty());
  }

  /**
   * Test OptionalListener {@link OptionalListener#getProperty(String)}.
   * <p>
   * Method under test: {@link OptionalListener#getProperty(String)}
   */
  @Test
  public void testOptionalListenerGetProperty() {
    // Arrange, Act and Assert
    assertNull((new OptionalListener("Class Name")).getProperty("Name"));
  }

  /**
   * Test OptionalListener getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link OptionalListener#OptionalListener(String)}
   *   <li>{@link OptionalListener#lifecycleEvent(LifecycleEvent)}
   *   <li>{@link OptionalListener#getClassName()}
   * </ul>
   */
  @Test
  public void testOptionalListenerGettersAndSetters() {
    // Arrange and Act
    OptionalListener actualOptionalListener = new OptionalListener("Class Name");
    actualOptionalListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert
    assertEquals("Class Name", actualOptionalListener.getClassName());
  }

  /**
   * Test OptionalListener {@link OptionalListener#setProperty(String, String)}.
   * <p>
   * Method under test: {@link OptionalListener#setProperty(String, String)}
   */
  @Test
  public void testOptionalListenerSetProperty() {
    // Arrange
    OptionalListener optionalListener = new OptionalListener("Class Name");

    // Act
    boolean actualSetPropertyResult = optionalListener.setProperty("Name", "42");

    // Assert
    HashMap<String, String> stringStringMap = optionalListener.properties;
    assertEquals(1, stringStringMap.size());
    assertEquals("42", stringStringMap.get("Name"));
    Set<String> properties = optionalListener.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.contains("Name"));
    assertTrue(actualSetPropertyResult);
  }
}
