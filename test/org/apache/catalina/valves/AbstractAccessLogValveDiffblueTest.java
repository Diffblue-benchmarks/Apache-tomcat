package org.apache.catalina.valves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.CharArrayWriter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.valves.AbstractAccessLogValve.AccessLogElement;
import org.apache.catalina.valves.AbstractAccessLogValve.RemoteAddrElement;
import org.apache.catalina.valves.AbstractAccessLogValve.StringElement;
import org.apache.coyote.Response;
import org.junit.Test;

public class AbstractAccessLogValveDiffblueTest {
  /**
   * Test {@link AbstractAccessLogValve#getMaxLogMessageBufferSize()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getMaxLogMessageBufferSize()}
   */
  @Test
  public void testGetMaxLogMessageBufferSize() {
    // Arrange, Act and Assert
    assertEquals(256, (new AccessLogValve()).getMaxLogMessageBufferSize());
  }

  /**
   * Test {@link AbstractAccessLogValve#getIpv6Canonical()}.
   * <ul>
   *   <li>Given {@link AccessLogValve} (default constructor) Ipv6Canonical is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getIpv6Canonical()}
   */
  @Test
  public void testGetIpv6Canonical_givenAccessLogValveIpv6CanonicalIsTrue_thenReturnTrue() {
    // Arrange
    AccessLogValve accessLogValve = new AccessLogValve();
    accessLogValve.setIpv6Canonical(true);

    // Act and Assert
    assertTrue(accessLogValve.getIpv6Canonical());
  }

  /**
   * Test {@link AbstractAccessLogValve#getIpv6Canonical()}.
   * <ul>
   *   <li>Given {@link AccessLogValve} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getIpv6Canonical()}
   */
  @Test
  public void testGetIpv6Canonical_givenAccessLogValve_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AccessLogValve()).getIpv6Canonical());
  }

  /**
   * Test {@link AbstractAccessLogValve#getRequestAttributesEnabled()}.
   * <ul>
   *   <li>Given {@link AccessLogValve} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getRequestAttributesEnabled()}
   */
  @Test
  public void testGetRequestAttributesEnabled_givenAccessLogValve_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AccessLogValve()).getRequestAttributesEnabled());
  }

  /**
   * Test {@link AbstractAccessLogValve#getRequestAttributesEnabled()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getRequestAttributesEnabled()}
   */
  @Test
  public void testGetRequestAttributesEnabled_thenReturnTrue() {
    // Arrange
    AccessLogValve accessLogValve = new AccessLogValve();
    accessLogValve.setRequestAttributesEnabled(true);

    // Act and Assert
    assertTrue(accessLogValve.getRequestAttributesEnabled());
  }

  /**
   * Test {@link AbstractAccessLogValve#getEnabled()}.
   * <ul>
   *   <li>Given {@link AccessLogValve} (default constructor) Enabled is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getEnabled()}
   */
  @Test
  public void testGetEnabled_givenAccessLogValveEnabledIsFalse_thenReturnFalse() {
    // Arrange
    AccessLogValve accessLogValve = new AccessLogValve();
    accessLogValve.setEnabled(false);

    // Act and Assert
    assertFalse(accessLogValve.getEnabled());
  }

  /**
   * Test {@link AbstractAccessLogValve#getEnabled()}.
   * <ul>
   *   <li>Given {@link AccessLogValve} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getEnabled()}
   */
  @Test
  public void testGetEnabled_givenAccessLogValve_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AccessLogValve()).getEnabled());
  }

  /**
   * Test {@link AbstractAccessLogValve#getPattern()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getPattern()}
   */
  @Test
  public void testGetPattern() {
    // Arrange, Act and Assert
    assertNull((new AccessLogValve()).getPattern());
  }

  /**
   * Test {@link AbstractAccessLogValve#getCondition()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getCondition()}
   */
  @Test
  public void testGetCondition() {
    // Arrange, Act and Assert
    assertNull((new AccessLogValve()).getCondition());
  }

  /**
   * Test {@link AbstractAccessLogValve#setCondition(String)}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#setCondition(String)}
   */
  @Test
  public void testSetCondition() {
    // Arrange
    AccessLogValve accessLogValve = new AccessLogValve();

    // Act
    accessLogValve.setCondition("Condition");

    // Assert
    assertEquals("Condition", accessLogValve.getCondition());
    assertEquals("Condition", accessLogValve.getConditionUnless());
  }

  /**
   * Test {@link AbstractAccessLogValve#getConditionUnless()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getConditionUnless()}
   */
  @Test
  public void testGetConditionUnless() {
    // Arrange, Act and Assert
    assertNull((new AccessLogValve()).getConditionUnless());
  }

  /**
   * Test {@link AbstractAccessLogValve#getConditionIf()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getConditionIf()}
   */
  @Test
  public void testGetConditionIf() {
    // Arrange, Act and Assert
    assertNull((new AccessLogValve()).getConditionIf());
  }

  /**
   * Test {@link AbstractAccessLogValve#getLocale()}.
   * <p>
   * Method under test: {@link AbstractAccessLogValve#getLocale()}
   */
  @Test
  public void testGetLocale() {
    // Arrange, Act and Assert
    assertEquals("en", (new AccessLogValve()).getLocale());
  }

  /**
   * Test {@link AbstractAccessLogValve#findLocale(String, Locale)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#findLocale(String, Locale)}
   */
  @Test
  public void testFindLocale_whenEmptyString() throws MissingResourceException {
    // Arrange and Act
    Locale actualFindLocaleResult = AbstractAccessLogValve.findLocale("", Locale.getDefault());

    // Assert
    assertEquals("", actualFindLocaleResult.getCountry());
    assertEquals("", actualFindLocaleResult.getDisplayCountry());
    assertEquals("", actualFindLocaleResult.getDisplayScript());
    assertEquals("", actualFindLocaleResult.getDisplayVariant());
    assertEquals("", actualFindLocaleResult.getISO3Country());
    assertEquals("", actualFindLocaleResult.getScript());
    assertEquals("", actualFindLocaleResult.getVariant());
    assertEquals("English", actualFindLocaleResult.getDisplayLanguage());
    assertEquals("English", actualFindLocaleResult.getDisplayName());
    assertEquals("en", actualFindLocaleResult.getLanguage());
    assertEquals("eng", actualFindLocaleResult.getISO3Language());
    assertFalse(actualFindLocaleResult.hasExtensions());
    Set<Character> extensionKeys = actualFindLocaleResult.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleKeys());
  }

  /**
   * Test {@link AbstractAccessLogValve#findLocale(String, Locale)}.
   * <ul>
   *   <li>When {@code en}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#findLocale(String, Locale)}
   */
  @Test
  public void testFindLocale_whenEn() throws MissingResourceException {
    // Arrange and Act
    Locale actualFindLocaleResult = AbstractAccessLogValve.findLocale("en", Locale.getDefault());

    // Assert
    assertEquals("", actualFindLocaleResult.getCountry());
    assertEquals("", actualFindLocaleResult.getDisplayCountry());
    assertEquals("", actualFindLocaleResult.getDisplayScript());
    assertEquals("", actualFindLocaleResult.getDisplayVariant());
    assertEquals("", actualFindLocaleResult.getISO3Country());
    assertEquals("", actualFindLocaleResult.getScript());
    assertEquals("", actualFindLocaleResult.getVariant());
    assertEquals("English", actualFindLocaleResult.getDisplayLanguage());
    assertEquals("English", actualFindLocaleResult.getDisplayName());
    assertEquals("en", actualFindLocaleResult.getLanguage());
    assertEquals("eng", actualFindLocaleResult.getISO3Language());
    assertFalse(actualFindLocaleResult.hasExtensions());
    Set<Character> extensionKeys = actualFindLocaleResult.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleKeys());
  }

  /**
   * Test {@link AbstractAccessLogValve#findLocale(String, Locale)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#findLocale(String, Locale)}
   */
  @Test
  public void testFindLocale_whenName() throws MissingResourceException {
    // Arrange and Act
    Locale actualFindLocaleResult = AbstractAccessLogValve.findLocale("Name", Locale.getDefault());

    // Assert
    assertEquals("", actualFindLocaleResult.getCountry());
    assertEquals("", actualFindLocaleResult.getDisplayCountry());
    assertEquals("", actualFindLocaleResult.getDisplayScript());
    assertEquals("", actualFindLocaleResult.getDisplayVariant());
    assertEquals("", actualFindLocaleResult.getISO3Country());
    assertEquals("", actualFindLocaleResult.getScript());
    assertEquals("", actualFindLocaleResult.getVariant());
    assertEquals("English", actualFindLocaleResult.getDisplayLanguage());
    assertEquals("English", actualFindLocaleResult.getDisplayName());
    assertEquals("en", actualFindLocaleResult.getLanguage());
    assertEquals("eng", actualFindLocaleResult.getISO3Language());
    assertFalse(actualFindLocaleResult.hasExtensions());
    Set<Character> extensionKeys = actualFindLocaleResult.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleKeys());
  }

  /**
   * Test {@link AbstractAccessLogValve#findLocale(String, Locale)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#findLocale(String, Locale)}
   */
  @Test
  public void testFindLocale_whenNull() throws MissingResourceException {
    // Arrange and Act
    Locale actualFindLocaleResult = AbstractAccessLogValve.findLocale(null, Locale.getDefault());

    // Assert
    assertEquals("", actualFindLocaleResult.getCountry());
    assertEquals("", actualFindLocaleResult.getDisplayCountry());
    assertEquals("", actualFindLocaleResult.getDisplayScript());
    assertEquals("", actualFindLocaleResult.getDisplayVariant());
    assertEquals("", actualFindLocaleResult.getISO3Country());
    assertEquals("", actualFindLocaleResult.getScript());
    assertEquals("", actualFindLocaleResult.getVariant());
    assertEquals("English", actualFindLocaleResult.getDisplayLanguage());
    assertEquals("English", actualFindLocaleResult.getDisplayName());
    assertEquals("en", actualFindLocaleResult.getLanguage());
    assertEquals("eng", actualFindLocaleResult.getISO3Language());
    assertFalse(actualFindLocaleResult.hasExtensions());
    Set<Character> extensionKeys = actualFindLocaleResult.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, actualFindLocaleResult.getUnicodeLocaleKeys());
  }

  /**
   * Test {@link AbstractAccessLogValve#createAccessLogElement(String, char)} with {@code name}, {@code pattern}.
   * <ul>
   *   <li>When {@code a}.</li>
   *   <li>Then return {@link RemoteAddrElement}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#createAccessLogElement(String, char)}
   */
  @Test
  public void testCreateAccessLogElementWithNamePattern_whenA_thenReturnRemoteAddrElement() {
    // Arrange and Act
    AccessLogElement actualCreateAccessLogElementResult = (new AccessLogValve()).createAccessLogElement("Name", 'a');
    CharArrayWriter charArrayWriter = new CharArrayWriter(1);
    Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    actualCreateAccessLogElementResult.addElement(charArrayWriter, fromResult, request,
        new org.apache.catalina.connector.Response(new Response()), 1L);

    // Assert
    assertTrue(actualCreateAccessLogElementResult instanceof RemoteAddrElement);
    assertEquals(4, charArrayWriter.size());
  }

  /**
   * Test {@link AbstractAccessLogValve#createAccessLogElement(String, char)} with {@code name}, {@code pattern}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@link StringElement}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#createAccessLogElement(String, char)}
   */
  @Test
  public void testCreateAccessLogElementWithNamePattern_whenA_thenReturnStringElement() {
    // Arrange and Act
    AccessLogElement actualCreateAccessLogElementResult = (new AccessLogValve()).createAccessLogElement("Name", 'A');
    CharArrayWriter charArrayWriter = new CharArrayWriter(1);
    Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    actualCreateAccessLogElementResult.addElement(charArrayWriter, fromResult, request,
        new org.apache.catalina.connector.Response(new Response()), 1L);

    // Assert
    assertTrue(actualCreateAccessLogElementResult instanceof StringElement);
    assertEquals(3, charArrayWriter.size());
  }

  /**
   * Test {@link AbstractAccessLogValve#createAccessLogElement(char)} with {@code pattern}.
   * <ul>
   *   <li>When {@code a}.</li>
   *   <li>Then return {@link RemoteAddrElement}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#createAccessLogElement(char)}
   */
  @Test
  public void testCreateAccessLogElementWithPattern_whenA_thenReturnRemoteAddrElement() {
    // Arrange and Act
    AccessLogElement actualCreateAccessLogElementResult = (new AccessLogValve()).createAccessLogElement('a');
    CharArrayWriter charArrayWriter = new CharArrayWriter(1);
    Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    actualCreateAccessLogElementResult.addElement(charArrayWriter, fromResult, request,
        new org.apache.catalina.connector.Response(new Response()), 1L);

    // Assert
    assertTrue(actualCreateAccessLogElementResult instanceof RemoteAddrElement);
    assertEquals(4, charArrayWriter.size());
  }

  /**
   * Test {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}
   */
  @Test
  public void testEscapeAndAppend_whenEmptyString_thenCharArrayWriterWithOneSizeIsOne() {
    // Arrange
    CharArrayWriter dest = new CharArrayWriter(1);

    // Act
    AbstractAccessLogValve.escapeAndAppend("", dest);

    // Assert
    assertEquals(1, dest.size());
  }

  /**
   * Test {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}.
   * <ul>
   *   <li>When {@code Input}.</li>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}
   */
  @Test
  public void testEscapeAndAppend_whenInput_thenCharArrayWriterWithOneSizeIsFive() {
    // Arrange
    CharArrayWriter dest = new CharArrayWriter(1);

    // Act
    AbstractAccessLogValve.escapeAndAppend("Input", dest);

    // Assert
    assertEquals(5, dest.size());
  }

  /**
   * Test {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAccessLogValve#escapeAndAppend(String, CharArrayWriter)}
   */
  @Test
  public void testEscapeAndAppend_whenNull_thenCharArrayWriterWithOneSizeIsOne() {
    // Arrange
    CharArrayWriter dest = new CharArrayWriter(1);

    // Act
    AbstractAccessLogValve.escapeAndAppend(null, dest);

    // Assert
    assertEquals(1, dest.size());
  }
}
