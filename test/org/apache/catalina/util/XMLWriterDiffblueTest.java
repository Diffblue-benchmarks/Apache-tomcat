package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

public class XMLWriterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XMLWriter#XMLWriter()}
   *   <li>{@link XMLWriter#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    XMLWriter actualXmlWriter = new XMLWriter();
    String actualToStringResult = actualXmlWriter.toString();

    // Assert
    assertEquals("", actualXmlWriter.buffer.toString());
    assertEquals("", actualToStringResult);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link StringWriter#StringWriter()}.</li>
   *   <li>Then return {@link XMLWriter#writer} toString is empty string.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XMLWriter#XMLWriter(Writer)}
   *   <li>{@link XMLWriter#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenStringWriter_thenReturnWriterToStringIsEmptyString() {
    // Arrange and Act
    XMLWriter actualXmlWriter = new XMLWriter(new StringWriter());
    String actualToStringResult = actualXmlWriter.toString();

    // Assert
    assertEquals("", actualXmlWriter.writer.toString());
    assertEquals("", actualXmlWriter.buffer.toString());
    assertEquals("", actualToStringResult);
  }

  /**
   * Test {@link XMLWriter#writeProperty(String, String, String)}.
   * <p>
   * Method under test: {@link XMLWriter#writeProperty(String, String, String)}
   */
  @Test
  public void testWriteProperty() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();
    xmlWriter.writeElement("Namespace", "Namespace Info", "Name", 0);

    // Act
    xmlWriter.writeProperty("Namespace", "Name", "42");

    // Assert
    assertEquals("<Namespace:Name xmlns:Namespace=\"Namespace Info\">\n<Namespace:Name>42</Namespace:Name>\n",
        xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeProperty(String, String, String)}.
   * <p>
   * Method under test: {@link XMLWriter#writeProperty(String, String, String)}
   */
  @Test
  public void testWriteProperty2() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();
    xmlWriter.writeElement("Namespace", "Namespace Info", "Name", 0);

    // Act
    xmlWriter.writeProperty(null, "Name", "42");

    // Assert
    assertEquals("<Namespace:Name xmlns:Namespace=\"Namespace Info\">\n<Name>42</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeProperty(String, String, String)}.
   * <ul>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Namespace:Name>42</Namespace:Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeProperty(String, String, String)}
   */
  @Test
  public void testWriteProperty_thenXMLWriterBufferToStringIsNamespaceName42NamespaceName() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeProperty("Namespace", "Name", "42");

    // Assert
    assertEquals("<Namespace:Name>42</Namespace:Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeProperty(String, String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Name>42</Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeProperty(String, String, String)}
   */
  @Test
  public void testWriteProperty_whenEmptyString_thenXMLWriterBufferToStringIsName42Name() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeProperty("", "Name", "42");

    // Assert
    assertEquals("<Name>42</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeProperty(String, String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Name>42</Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeProperty(String, String, String)}
   */
  @Test
  public void testWriteProperty_whenNull_thenXMLWriterBufferToStringIsName42Name() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeProperty(null, "Name", "42");

    // Assert
    assertEquals("<Name>42</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Namespace:Name/>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_thenXMLWriterBufferToStringIsNamespaceName() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Name", 19088743);

    // Assert
    assertEquals("<Namespace:Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code </Namespace:Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_thenXMLWriterBufferToStringIsNamespaceName2() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Name", 1);

    // Assert
    assertEquals("</Namespace:Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Namespace:Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_thenXMLWriterBufferToStringIsNamespaceName3() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Name", 0);

    // Assert
    assertEquals("<Namespace:Name>", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_whenEmptyString() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("", "Name", 19088743);

    // Assert
    assertEquals("<Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Name/>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_whenNull_thenXMLWriterBufferToStringIsName() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Name", 19088743);

    // Assert
    assertEquals("<Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code </Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_whenOne_thenXMLWriterBufferToStringIsName() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Name", 1);

    // Assert
    assertEquals("</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, int)} with {@code namespace}, {@code name}, {@code type}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code <Name>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNameType_whenZero_thenXMLWriterBufferToStringIsName() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Name", 0);

    // Assert
    assertEquals("<Name>", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Namespace Info", "Name", 19088743);

    // Assert
    assertEquals("<Namespace:Name xmlns:Namespace=\"Namespace Info\"/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType2() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Namespace Info", "Name", 19088743);

    // Assert
    assertEquals("<Name xmlns=\"Namespace Info\"/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType3() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("", "Namespace Info", "Name", 19088743);

    // Assert
    assertEquals("<Name xmlns=\"Namespace Info\"/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType4() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", null, "Name", 19088743);

    // Assert
    assertEquals("<Namespace:Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType5() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Namespace Info", "Name", 1);

    // Assert
    assertEquals("</Namespace:Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType6() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", "Namespace Info", "Name", 0);

    // Assert
    assertEquals("<Namespace:Name xmlns:Namespace=\"Namespace Info\">", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType7() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, null, "Name", 19088743);

    // Assert
    assertEquals("<Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType8() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "", "Name", 19088743);

    // Assert
    assertEquals("<Name/>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType9() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Namespace Info", "Name", 1);

    // Assert
    assertEquals("</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType10() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, "Namespace Info", "Name", 0);

    // Assert
    assertEquals("<Name xmlns=\"Namespace Info\">", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType11() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement("Namespace", null, "Name", 0);

    // Assert
    assertEquals("<Namespace:Name>", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType12() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, null, "Name", 1);

    // Assert
    assertEquals("</Name>\n", xmlWriter.buffer.toString());
    assertFalse(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeElement(String, String, String, int)} with {@code namespace}, {@code namespaceInfo}, {@code name}, {@code type}.
   * <p>
   * Method under test: {@link XMLWriter#writeElement(String, String, String, int)}
   */
  @Test
  public void testWriteElementWithNamespaceNamespaceInfoNameType13() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeElement(null, null, "Name", 0);

    // Assert
    assertEquals("<Name>", xmlWriter.buffer.toString());
    assertTrue(xmlWriter.lastWriteWasOpen);
  }

  /**
   * Test {@link XMLWriter#writeText(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeText(String)}
   */
  @Test
  public void testWriteText_whenNull_thenXMLWriterBufferToStringIsNull() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeText(null);

    // Assert
    assertEquals("null", xmlWriter.buffer.toString());
  }

  /**
   * Test {@link XMLWriter#writeText(String)}.
   * <ul>
   *   <li>When {@code Text}.</li>
   *   <li>Then {@link XMLWriter#XMLWriter()} {@link XMLWriter#buffer} toString is {@code Text}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XMLWriter#writeText(String)}
   */
  @Test
  public void testWriteText_whenText_thenXMLWriterBufferToStringIsText() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeText("Text");

    // Assert
    assertEquals("Text", xmlWriter.buffer.toString());
  }

  /**
   * Test {@link XMLWriter#writeRaw(String)}.
   * <p>
   * Method under test: {@link XMLWriter#writeRaw(String)}
   */
  @Test
  public void testWriteRaw() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeRaw("Raw");

    // Assert
    assertEquals("Raw", xmlWriter.buffer.toString());
  }

  /**
   * Test {@link XMLWriter#writeData(String)}.
   * <p>
   * Method under test: {@link XMLWriter#writeData(String)}
   */
  @Test
  public void testWriteData() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeData("Data");

    // Assert
    assertEquals("<![CDATA[Data]]>", xmlWriter.buffer.toString());
  }

  /**
   * Test {@link XMLWriter#writeXMLHeader()}.
   * <p>
   * Method under test: {@link XMLWriter#writeXMLHeader()}
   */
  @Test
  public void testWriteXMLHeader() {
    // Arrange
    XMLWriter xmlWriter = new XMLWriter();

    // Act
    xmlWriter.writeXMLHeader();

    // Assert
    assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n", xmlWriter.buffer.toString());
  }
}
