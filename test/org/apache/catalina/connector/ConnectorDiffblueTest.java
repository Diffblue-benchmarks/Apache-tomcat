package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.nio.charset.Charset;
import java.util.HashSet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.Response;
import org.apache.coyote.UpgradeProtocol;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.coyote.http2.Http2Protocol;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.junit.Test;

public class ConnectorDiffblueTest {
  /**
   * Test {@link Connector#Connector()}.
   * <p>
   * Method under test: {@link Connector#Connector()}
   */
  @Test
  public void testNewConnector() {
    // Arrange and Act
    Connector actualConnector = new Connector();

    // Assert
    assertTrue(actualConnector.getProtocolHandler() instanceof Http11NioProtocol);
    assertEquals("Catalina", actualConnector.getDomain());
    assertEquals("HTTP/1.1", actualConnector.getProtocol());
    assertEquals("NEW", actualConnector.getStateName());
    assertEquals("POST", actualConnector.getParseBodyMethods());
    assertEquals("UTF-8", actualConnector.getURIEncoding());
    assertEquals("decode", actualConnector.getEncodedReverseSolidusHandling());
    assertEquals("http", actualConnector.getScheme());
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualConnector.getProtocolHandlerClassName());
    assertEquals("reject", actualConnector.getEncodedSolidusHandling());
    assertNull(actualConnector.getDomainInternal());
    assertNull(actualConnector.getProxyName());
    assertNull(actualConnector.parseBodyMethodsSet);
    assertNull(actualConnector.getObjectName());
    assertNull(actualConnector.getService());
    assertNull(actualConnector.adapter);
    assertEquals(-1, actualConnector.getLocalPort());
    assertEquals(-1, actualConnector.getPort());
    assertEquals(-1, actualConnector.getPortWithOffset());
    assertEquals(0, actualConnector.getPortOffset());
    assertEquals(0, actualConnector.getProxyPort());
    assertEquals(0, actualConnector.findSslHostConfigs().length);
    assertEquals(0, actualConnector.findUpgradeProtocols().length);
    assertEquals(0, actualConnector.findLifecycleListeners().length);
    assertEquals(200, actualConnector.getMaxCookieCount());
    assertEquals(2097152, actualConnector.getMaxPostSize());
    assertEquals(30000L, actualConnector.getAsyncTimeout());
    assertEquals(4096, actualConnector.getMaxSavePostSize());
    assertEquals(443, actualConnector.getRedirectPort());
    assertEquals(443, actualConnector.getRedirectPortWithOffset());
    assertEquals(LifecycleState.NEW, actualConnector.getState());
    assertEquals(EncodedSolidusHandling.DECODE, actualConnector.getEncodedReverseSolidusHandlingInternal());
    assertEquals(EncodedSolidusHandling.REJECT, actualConnector.getEncodedSolidusHandlingInternal());
    assertFalse(actualConnector.getAllowBackslash());
    assertFalse(actualConnector.getAllowTrace());
    assertFalse(actualConnector.getEnableLookups());
    assertFalse(actualConnector.getRejectSuspiciousURIs());
    assertFalse(actualConnector.getSecure());
    assertFalse(actualConnector.getUseBodyEncodingForURI());
    assertFalse(actualConnector.getUseIPVHosts());
    assertFalse(actualConnector.getXpoweredBy());
    assertFalse(actualConnector.getThrowOnFailure());
    assertTrue(actualConnector.getDiscardFacades());
    assertTrue(actualConnector.getEnforceEncodingInGetWriter());
    assertEquals(Connector.INTERNAL_EXECUTOR_NAME, actualConnector.getExecutorName());
    assertEquals(TestMaxConnections.connectTimeout, actualConnector.getMaxParameterCount());
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>Then return Protocol is {@code AjpNioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_thenReturnProtocolIsOrgApacheCoyoteAjpAjpNioProtocol() {
    // Arrange and Act
    Connector actualConnector = new Connector("org.apache.coyote.ajp.AjpNioProtocol");

    // Assert
    ProtocolHandler protocolHandler = actualConnector.getProtocolHandler();
    assertTrue(protocolHandler instanceof AjpNioProtocol);
    assertEquals("org.apache.coyote.ajp.AjpNioProtocol", actualConnector.getProtocol());
    assertEquals("org.apache.coyote.ajp.AjpNioProtocol", actualConnector.getProtocolHandlerClassName());
    assertEquals(-1, ((AjpNioProtocol) protocolHandler).getConnectionTimeout());
    assertEquals(-1, ((AjpNioProtocol) protocolHandler).getKeepAliveTimeout());
    assertEquals(0, protocolHandler.findSslHostConfigs().length);
    assertEquals(0, protocolHandler.findUpgradeProtocols().length);
    assertEquals(8184, protocolHandler.getDesiredBufferSize());
    assertFalse(protocolHandler.isSendfileSupported());
    assertFalse(((AjpNioProtocol) protocolHandler).getTomcatAuthorization());
    assertTrue(((AjpNioProtocol) protocolHandler).getAjpFlush());
    assertTrue(((AjpNioProtocol) protocolHandler).getSecretRequired());
    assertTrue(((AjpNioProtocol) protocolHandler).getTomcatAuthentication());
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE, ((AjpNioProtocol) protocolHandler).getPacketSize());
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>Then return Protocol is {@code Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_thenReturnProtocolIsOrgApacheCoyoteHttp11Http11NioProtocol() {
    // Arrange and Act
    Connector actualConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

    // Assert
    assertTrue(actualConnector.getProtocolHandler() instanceof Http11NioProtocol);
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualConnector.getProtocol());
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualConnector.getProtocolHandlerClassName());
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>When {@code AJP/1.3}.</li>
   *   <li>Then return Protocol is {@code AJP/1.3}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_whenAjp13_thenReturnProtocolIsAjp13() {
    // Arrange and Act
    Connector actualConnector = new Connector("AJP/1.3");

    // Assert
    ProtocolHandler protocolHandler = actualConnector.getProtocolHandler();
    assertTrue(protocolHandler instanceof AjpNioProtocol);
    assertEquals("AJP/1.3", actualConnector.getProtocol());
    assertEquals("org.apache.coyote.ajp.AjpNioProtocol", actualConnector.getProtocolHandlerClassName());
    assertEquals(-1, ((AjpNioProtocol) protocolHandler).getConnectionTimeout());
    assertEquals(-1, ((AjpNioProtocol) protocolHandler).getKeepAliveTimeout());
    assertEquals(0, protocolHandler.findSslHostConfigs().length);
    assertEquals(0, protocolHandler.findUpgradeProtocols().length);
    assertEquals(8184, protocolHandler.getDesiredBufferSize());
    assertFalse(protocolHandler.isSendfileSupported());
    assertFalse(((AjpNioProtocol) protocolHandler).getTomcatAuthorization());
    assertTrue(((AjpNioProtocol) protocolHandler).getAjpFlush());
    assertTrue(((AjpNioProtocol) protocolHandler).getSecretRequired());
    assertTrue(((AjpNioProtocol) protocolHandler).getTomcatAuthentication());
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE, ((AjpNioProtocol) protocolHandler).getPacketSize());
  }

  /**
   * Test {@link Connector#Connector(ProtocolHandler)}.
   * <ul>
   *   <li>When {@link AjpNio2Protocol} (default constructor).</li>
   *   <li>Then ProtocolHandler return {@link AjpNio2Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(ProtocolHandler)}
   */
  @Test
  public void testNewConnector_whenAjpNio2Protocol_thenProtocolHandlerReturnAjpNio2Protocol() {
    // Arrange
    AjpNio2Protocol protocolHandler = new AjpNio2Protocol();

    // Act
    Connector actualConnector = new Connector(protocolHandler);

    // Assert
    ProtocolHandler protocolHandler2 = actualConnector.getProtocolHandler();
    assertTrue(protocolHandler2 instanceof AjpNio2Protocol);
    assertEquals("Catalina", actualConnector.getDomain());
    assertEquals("NEW", actualConnector.getStateName());
    assertEquals("POST", actualConnector.getParseBodyMethods());
    assertEquals("UTF-8", actualConnector.getURIEncoding());
    assertEquals("decode", actualConnector.getEncodedReverseSolidusHandling());
    assertEquals("http", actualConnector.getScheme());
    assertEquals("org.apache.coyote.ajp.AjpNio2Protocol", actualConnector.getProtocol());
    assertEquals("org.apache.coyote.ajp.AjpNio2Protocol", actualConnector.getProtocolHandlerClassName());
    assertEquals("reject", actualConnector.getEncodedSolidusHandling());
    assertNull(actualConnector.getDomainInternal());
    assertNull(actualConnector.getProxyName());
    assertNull(actualConnector.parseBodyMethodsSet);
    assertNull(actualConnector.getObjectName());
    assertNull(actualConnector.getService());
    assertNull(actualConnector.adapter);
    assertEquals(-1, actualConnector.getLocalPort());
    assertEquals(-1, actualConnector.getPort());
    assertEquals(-1, actualConnector.getPortWithOffset());
    assertEquals(0, actualConnector.getPortOffset());
    assertEquals(0, actualConnector.getProxyPort());
    assertEquals(0, actualConnector.findSslHostConfigs().length);
    assertEquals(0, actualConnector.findUpgradeProtocols().length);
    assertEquals(0, actualConnector.findLifecycleListeners().length);
    assertEquals(200, actualConnector.getMaxCookieCount());
    assertEquals(2097152, actualConnector.getMaxPostSize());
    assertEquals(30000L, actualConnector.getAsyncTimeout());
    assertEquals(4096, actualConnector.getMaxSavePostSize());
    assertEquals(443, actualConnector.getRedirectPort());
    assertEquals(443, actualConnector.getRedirectPortWithOffset());
    assertEquals(LifecycleState.NEW, actualConnector.getState());
    assertEquals(EncodedSolidusHandling.DECODE, actualConnector.getEncodedReverseSolidusHandlingInternal());
    assertEquals(EncodedSolidusHandling.REJECT, actualConnector.getEncodedSolidusHandlingInternal());
    assertFalse(actualConnector.getAllowBackslash());
    assertFalse(actualConnector.getAllowTrace());
    assertFalse(actualConnector.getEnableLookups());
    assertFalse(actualConnector.getRejectSuspiciousURIs());
    assertFalse(actualConnector.getSecure());
    assertFalse(actualConnector.getUseBodyEncodingForURI());
    assertFalse(actualConnector.getUseIPVHosts());
    assertFalse(actualConnector.getXpoweredBy());
    assertFalse(actualConnector.getThrowOnFailure());
    assertTrue(actualConnector.getDiscardFacades());
    assertTrue(actualConnector.getEnforceEncodingInGetWriter());
    assertEquals(Connector.INTERNAL_EXECUTOR_NAME, actualConnector.getExecutorName());
    assertEquals(TestMaxConnections.connectTimeout, actualConnector.getMaxParameterCount());
    assertSame(protocolHandler, protocolHandler2);
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>When {@code HTTP/1.1}.</li>
   *   <li>Then return Protocol is {@code HTTP/1.1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_whenHttp11_thenReturnProtocolIsHttp11() {
    // Arrange and Act
    Connector actualConnector = new Connector("HTTP/1.1");

    // Assert
    assertTrue(actualConnector.getProtocolHandler() instanceof Http11NioProtocol);
    assertEquals("HTTP/1.1", actualConnector.getProtocol());
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualConnector.getProtocolHandlerClassName());
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Protocol is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_whenNull_thenReturnProtocolIsNull() {
    // Arrange and Act
    Connector actualConnector = new Connector((String) null);

    // Assert
    assertTrue(actualConnector.getProtocolHandler() instanceof Http11NioProtocol);
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualConnector.getProtocolHandlerClassName());
    assertNull(actualConnector.getProtocol());
  }

  /**
   * Test {@link Connector#Connector(String)}.
   * <ul>
   *   <li>When {@code Protocol}.</li>
   *   <li>Then return {@code Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#Connector(String)}
   */
  @Test
  public void testNewConnector_whenProtocol_thenReturnProtocol() {
    // Arrange and Act
    Connector actualConnector = new Connector("Protocol");

    // Assert
    assertEquals("Protocol", actualConnector.getProtocol());
    assertEquals("Protocol", actualConnector.getProtocolHandlerClassName());
    assertEquals("type=Connector,port=auto-null", actualConnector.getObjectNameKeyProperties());
    assertNull(actualConnector.getProtocolHandler());
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with protocol is {@code getName}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_givenConnectorWithProtocolIsGetName_whenName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Connector("getName")).getProperty("Name"));
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_givenConnector_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Connector()).getProperty(""));
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code getName}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_givenConnector_whenGetName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Connector()).getProperty("getName"));
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code "http-nio--1"}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_givenConnector_whenName_thenReturnHttpNio1() {
    // Arrange, Act and Assert
    assertEquals("\"http-nio--1\"", (new Connector()).getProperty("Name"));
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_givenConnector_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Connector()).getProperty(null));
  }

  /**
   * Test {@link Connector#getProperty(String)}.
   * <ul>
   *   <li>Then return {@code "http-nio2--1"}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getProperty(String)}
   */
  @Test
  public void testGetProperty_thenReturnHttpNio21() {
    // Arrange, Act and Assert
    assertEquals("\"http-nio2--1\"", (new Connector(new Http11Nio2Protocol())).getProperty("Name"));
  }

  /**
   * Test {@link Connector#setProperty(String, String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with protocol is {@code setName}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_givenConnectorWithProtocolIsSetName_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Connector("setName")).setProperty("Name", "42"));
  }

  /**
   * Test {@link Connector#setProperty(String, String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_givenConnector_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Connector()).setProperty("", "42"));
  }

  /**
   * Test {@link Connector#setProperty(String, String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_givenConnector_whenName_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new Connector()).setProperty("Name", "42"));
  }

  /**
   * Test {@link Connector#setProperty(String, String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_givenConnector_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Connector()).setProperty(null, "42"));
  }

  /**
   * Test {@link Connector#setProperty(String, String)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When {@code setName}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_givenConnector_whenSetName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Connector()).setProperty("setName", "42"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Connector#setAllowBackslash(boolean)}
   *   <li>{@link Connector#setAllowTrace(boolean)}
   *   <li>{@link Connector#setAsyncTimeout(long)}
   *   <li>{@link Connector#setDiscardFacades(boolean)}
   *   <li>{@link Connector#setEnableLookups(boolean)}
   *   <li>{@link Connector#setEnforceEncodingInGetWriter(boolean)}
   *   <li>{@link Connector#setMaxCookieCount(int)}
   *   <li>{@link Connector#setMaxParameterCount(int)}
   *   <li>{@link Connector#setMaxPostSize(int)}
   *   <li>{@link Connector#setProxyPort(int)}
   *   <li>{@link Connector#setRedirectPort(int)}
   *   <li>{@link Connector#setRejectSuspiciousURIs(boolean)}
   *   <li>{@link Connector#setScheme(String)}
   *   <li>{@link Connector#setService(Service)}
   *   <li>{@link Connector#setUseBodyEncodingForURI(boolean)}
   *   <li>{@link Connector#setUseIPVHosts(boolean)}
   *   <li>{@link Connector#setXpoweredBy(boolean)}
   *   <li>{@link Connector#getAllowBackslash()}
   *   <li>{@link Connector#getAllowTrace()}
   *   <li>{@link Connector#getAsyncTimeout()}
   *   <li>{@link Connector#getDiscardFacades()}
   *   <li>{@link Connector#getEnableLookups()}
   *   <li>{@link Connector#getEncodedReverseSolidusHandlingInternal()}
   *   <li>{@link Connector#getEncodedSolidusHandlingInternal()}
   *   <li>{@link Connector#getEnforceEncodingInGetWriter()}
   *   <li>{@link Connector#getMaxCookieCount()}
   *   <li>{@link Connector#getMaxParameterCount()}
   *   <li>{@link Connector#getMaxPostSize()}
   *   <li>{@link Connector#getMaxSavePostSize()}
   *   <li>{@link Connector#getParseBodyMethods()}
   *   <li>{@link Connector#getProtocol()}
   *   <li>{@link Connector#getProtocolHandler()}
   *   <li>{@link Connector#getProtocolHandlerClassName()}
   *   <li>{@link Connector#getProxyName()}
   *   <li>{@link Connector#getProxyPort()}
   *   <li>{@link Connector#getRedirectPort()}
   *   <li>{@link Connector#getRejectSuspiciousURIs()}
   *   <li>{@link Connector#getScheme()}
   *   <li>{@link Connector#getSecure()}
   *   <li>{@link Connector#getService()}
   *   <li>{@link Connector#getURICharset()}
   *   <li>{@link Connector#getUseBodyEncodingForURI()}
   *   <li>{@link Connector#getUseIPVHosts()}
   *   <li>{@link Connector#getXpoweredBy()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setAllowBackslash(true);
    connector.setAllowTrace(true);
    connector.setAsyncTimeout(1L);
    connector.setDiscardFacades(true);
    connector.setEnableLookups(true);
    connector.setEnforceEncodingInGetWriter(true);
    connector.setMaxCookieCount(3);
    connector.setMaxParameterCount(3);
    connector.setMaxPostSize(3);
    connector.setProxyPort(8080);
    connector.setRedirectPort(8080);
    connector.setRejectSuspiciousURIs(true);
    connector.setScheme("Scheme");
    StandardService service = new StandardService();
    connector.setService(service);
    connector.setUseBodyEncodingForURI(true);
    connector.setUseIPVHosts(true);
    connector.setXpoweredBy(true);
    boolean actualAllowBackslash = connector.getAllowBackslash();
    boolean actualAllowTrace = connector.getAllowTrace();
    long actualAsyncTimeout = connector.getAsyncTimeout();
    boolean actualDiscardFacades = connector.getDiscardFacades();
    boolean actualEnableLookups = connector.getEnableLookups();
    EncodedSolidusHandling actualEncodedReverseSolidusHandlingInternal = connector
        .getEncodedReverseSolidusHandlingInternal();
    EncodedSolidusHandling actualEncodedSolidusHandlingInternal = connector.getEncodedSolidusHandlingInternal();
    boolean actualEnforceEncodingInGetWriter = connector.getEnforceEncodingInGetWriter();
    int actualMaxCookieCount = connector.getMaxCookieCount();
    int actualMaxParameterCount = connector.getMaxParameterCount();
    int actualMaxPostSize = connector.getMaxPostSize();
    int actualMaxSavePostSize = connector.getMaxSavePostSize();
    String actualParseBodyMethods = connector.getParseBodyMethods();
    String actualProtocol = connector.getProtocol();
    ProtocolHandler actualProtocolHandler = connector.getProtocolHandler();
    String actualProtocolHandlerClassName = connector.getProtocolHandlerClassName();
    String actualProxyName = connector.getProxyName();
    int actualProxyPort = connector.getProxyPort();
    int actualRedirectPort = connector.getRedirectPort();
    boolean actualRejectSuspiciousURIs = connector.getRejectSuspiciousURIs();
    String actualScheme = connector.getScheme();
    boolean actualSecure = connector.getSecure();
    Service actualService = connector.getService();
    Charset actualURICharset = connector.getURICharset();
    boolean actualUseBodyEncodingForURI = connector.getUseBodyEncodingForURI();
    boolean actualUseIPVHosts = connector.getUseIPVHosts();
    boolean actualXpoweredBy = connector.getXpoweredBy();

    // Assert
    assertTrue(actualProtocolHandler instanceof Http11NioProtocol);
    assertEquals("HTTP/1.1", actualProtocol);
    assertEquals("POST", actualParseBodyMethods);
    assertEquals("Scheme", actualScheme);
    assertEquals("UTF-8", actualURICharset.name());
    assertEquals("org.apache.coyote.http11.Http11NioProtocol", actualProtocolHandlerClassName);
    assertNull(actualProxyName);
    assertEquals(1L, actualAsyncTimeout);
    assertEquals(3, actualMaxCookieCount);
    assertEquals(3, actualMaxParameterCount);
    assertEquals(3, actualMaxPostSize);
    assertEquals(4096, actualMaxSavePostSize);
    assertEquals(8080, actualProxyPort);
    assertEquals(8080, actualRedirectPort);
    assertEquals(EncodedSolidusHandling.DECODE, actualEncodedReverseSolidusHandlingInternal);
    assertEquals(EncodedSolidusHandling.REJECT, actualEncodedSolidusHandlingInternal);
    assertFalse(actualSecure);
    assertTrue(actualAllowBackslash);
    assertTrue(actualAllowTrace);
    assertTrue(actualDiscardFacades);
    assertTrue(actualEnableLookups);
    assertTrue(actualEnforceEncodingInGetWriter);
    assertTrue(actualRejectSuspiciousURIs);
    assertTrue(actualUseBodyEncodingForURI);
    assertTrue(actualUseIPVHosts);
    assertTrue(actualXpoweredBy);
    assertSame(service, actualService);
  }

  /**
   * Test {@link Connector#setMaxSavePostSize(int)}.
   * <p>
   * Method under test: {@link Connector#setMaxSavePostSize(int)}
   */
  @Test
  public void testSetMaxSavePostSize() {
    // Arrange
    Connector connector = new Connector("maxSavePostSize");

    // Act
    connector.setMaxSavePostSize(3);

    // Assert
    assertEquals(3, connector.getMaxSavePostSize());
  }

  /**
   * Test {@link Connector#setMaxSavePostSize(int)}.
   * <p>
   * Method under test: {@link Connector#setMaxSavePostSize(int)}
   */
  @Test
  public void testSetMaxSavePostSize2() {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());

    // Act
    connector.setMaxSavePostSize(3);

    // Assert
    assertEquals(3, connector.getMaxSavePostSize());
  }

  /**
   * Test {@link Connector#setMaxSavePostSize(int)}.
   * <ul>
   *   <li>Then {@link Connector#Connector()} ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setMaxSavePostSize(int)}
   */
  @Test
  public void testSetMaxSavePostSize_thenConnectorProtocolHandlerHttp11NioProtocol() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setMaxSavePostSize(3);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    assertEquals(3, connector.getMaxSavePostSize());
    assertEquals(3, ((Http11NioProtocol) protocolHandler).getMaxSavePostSize());
  }

  /**
   * Test {@link Connector#setParseBodyMethods(String)}.
   * <ul>
   *   <li>Then {@link Connector#Connector()} ParseBodyMethods is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setParseBodyMethods(String)}
   */
  @Test
  public void testSetParseBodyMethods_thenConnectorParseBodyMethodsIsEmptyString() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setParseBodyMethods("");

    // Assert
    assertEquals("", connector.getParseBodyMethods());
    assertTrue(connector.parseBodyMethodsSet.isEmpty());
  }

  /**
   * Test {@link Connector#setParseBodyMethods(String)}.
   * <ul>
   *   <li>When {@code Methods}.</li>
   *   <li>Then {@link Connector#Connector()} ParseBodyMethods is {@code Methods}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setParseBodyMethods(String)}
   */
  @Test
  public void testSetParseBodyMethods_whenMethods_thenConnectorParseBodyMethodsIsMethods() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setParseBodyMethods("Methods");

    // Assert
    assertEquals("Methods", connector.getParseBodyMethods());
    HashSet<String> stringSet = connector.parseBodyMethodsSet;
    assertEquals(1, stringSet.size());
    assertTrue(stringSet.contains("Methods"));
  }

  /**
   * Test {@link Connector#setParseBodyMethods(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link Connector#Connector()} ParseBodyMethods is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setParseBodyMethods(String)}
   */
  @Test
  public void testSetParseBodyMethods_whenNull_thenConnectorParseBodyMethodsIsNull() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setParseBodyMethods(null);

    // Assert
    assertNull(connector.getParseBodyMethods());
    assertTrue(connector.parseBodyMethodsSet.isEmpty());
  }

  /**
   * Test {@link Connector#setParseBodyMethods(String)}.
   * <ul>
   *   <li>When {@code TRACE}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setParseBodyMethods(String)}
   */
  @Test
  public void testSetParseBodyMethods_whenTrace_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new Connector()).setParseBodyMethods("TRACE"));
  }

  /**
   * Test {@link Connector#getPort()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPort()}
   */
  @Test
  public void testGetPort_givenConnector() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Connector()).getPort());
  }

  /**
   * Test {@link Connector#getPort()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with {@code Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPort()}
   */
  @Test
  public void testGetPort_givenConnectorWithProtocol() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Connector("Protocol")).getPort());
  }

  /**
   * Test {@link Connector#setPort(int)}.
   * <p>
   * Method under test: {@link Connector#setPort(int)}
   */
  @Test
  public void testSetPort() {
    // Arrange
    Connector connector = new Connector("port");

    // Act
    connector.setPort(8080);

    // Assert that nothing has changed
    assertEquals("type=Connector,port=auto-null", connector.getObjectNameKeyProperties());
    assertEquals(-1, connector.getPort());
    assertEquals(-1, connector.getPortWithOffset());
  }

  /**
   * Test {@link Connector#setPort(int)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then {@link Connector#Connector()} ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setPort(int)}
   */
  @Test
  public void testSetPort_givenConnector_thenConnectorProtocolHandlerHttp11NioProtocol() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setPort(8080);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    assertEquals("\"http-nio-8080\"", ((Http11NioProtocol) protocolHandler).getName());
    assertEquals("type=Connector,port=8080", connector.getObjectNameKeyProperties());
    assertEquals(8080, connector.getPort());
    assertEquals(8080, connector.getPortWithOffset());
    assertEquals(8080, ((Http11NioProtocol) protocolHandler).getPort());
    assertEquals(8080, ((Http11NioProtocol) protocolHandler).getPortWithOffset());
  }

  /**
   * Test {@link Connector#getPortOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPortOffset()}
   */
  @Test
  public void testGetPortOffset_givenConnector() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector()).getPortOffset());
  }

  /**
   * Test {@link Connector#getPortOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with {@code Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPortOffset()}
   */
  @Test
  public void testGetPortOffset_givenConnectorWithProtocol() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector("Protocol")).getPortOffset());
  }

  /**
   * Test {@link Connector#setPortOffset(int)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When minus one.</li>
   *   <li>Then {@link Connector#Connector()} PortOffset is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setPortOffset(int)}
   */
  @Test
  public void testSetPortOffset_givenConnector_whenMinusOne_thenConnectorPortOffsetIsZero() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setPortOffset(-1);

    // Assert that nothing has changed
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    assertEquals(0, connector.getPortOffset());
    assertEquals(0, ((Http11NioProtocol) protocolHandler).getPortOffset());
    assertEquals(443, connector.getRedirectPortWithOffset());
  }

  /**
   * Test {@link Connector#setPortOffset(int)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>When one.</li>
   *   <li>Then {@link Connector#Connector()} PortOffset is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setPortOffset(int)}
   */
  @Test
  public void testSetPortOffset_givenConnector_whenOne_thenConnectorPortOffsetIsOne() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setPortOffset(1);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    assertEquals(1, connector.getPortOffset());
    assertEquals(1, ((Http11NioProtocol) protocolHandler).getPortOffset());
    assertEquals(444, connector.getRedirectPortWithOffset());
  }

  /**
   * Test {@link Connector#setPortOffset(int)}.
   * <ul>
   *   <li>Then {@link Connector#Connector(String)} with protocol is {@code portOffset} PortOffset is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setPortOffset(int)}
   */
  @Test
  public void testSetPortOffset_thenConnectorWithProtocolIsPortOffsetPortOffsetIsZero() {
    // Arrange
    Connector connector = new Connector("portOffset");

    // Act
    connector.setPortOffset(1);

    // Assert that nothing has changed
    assertEquals(0, connector.getPortOffset());
    assertEquals(443, connector.getRedirectPortWithOffset());
  }

  /**
   * Test {@link Connector#getPortWithOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPortWithOffset()}
   */
  @Test
  public void testGetPortWithOffset_givenConnector() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Connector()).getPortWithOffset());
  }

  /**
   * Test {@link Connector#getPortWithOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with {@code Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getPortWithOffset()}
   */
  @Test
  public void testGetPortWithOffset_givenConnectorWithProtocol() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Connector("Protocol")).getPortWithOffset());
  }

  /**
   * Test {@link Connector#getLocalPort()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_givenConnector_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Connector()).getLocalPort());
  }

  /**
   * Test {@link Connector#setProxyName(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link Connector#Connector()} ProxyName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProxyName(String)}
   */
  @Test
  public void testSetProxyName_whenEmptyString_thenConnectorProxyNameIsNull() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setProxyName("");

    // Assert that nothing has changed
    assertNull(connector.getProxyName());
  }

  /**
   * Test {@link Connector#setProxyName(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link Connector#Connector()} ProxyName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProxyName(String)}
   */
  @Test
  public void testSetProxyName_whenNull_thenConnectorProxyNameIsNull() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setProxyName(null);

    // Assert that nothing has changed
    assertNull(connector.getProxyName());
  }

  /**
   * Test {@link Connector#setProxyName(String)}.
   * <ul>
   *   <li>When {@code Proxy Name}.</li>
   *   <li>Then {@link Connector#Connector()} ProxyName is {@code Proxy Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setProxyName(String)}
   */
  @Test
  public void testSetProxyName_whenProxyName_thenConnectorProxyNameIsProxyName() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setProxyName("Proxy Name");

    // Assert
    assertEquals("Proxy Name", connector.getProxyName());
  }

  /**
   * Test {@link Connector#getRedirectPortWithOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getRedirectPortWithOffset()}
   */
  @Test
  public void testGetRedirectPortWithOffset_givenConnector() {
    // Arrange, Act and Assert
    assertEquals(443, (new Connector()).getRedirectPortWithOffset());
  }

  /**
   * Test {@link Connector#getRedirectPortWithOffset()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with {@code Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getRedirectPortWithOffset()}
   */
  @Test
  public void testGetRedirectPortWithOffset_givenConnectorWithProtocol() {
    // Arrange, Act and Assert
    assertEquals(443, (new Connector("Protocol")).getRedirectPortWithOffset());
  }

  /**
   * Test {@link Connector#setSecure(boolean)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then {@link Connector#Connector()} ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setSecure(boolean)}
   */
  @Test
  public void testSetSecure_givenConnector_thenConnectorProtocolHandlerHttp11NioProtocol() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setSecure(true);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    assertTrue(connector.getSecure());
    assertTrue(((Http11NioProtocol) protocolHandler).getSecure());
  }

  /**
   * Test {@link Connector#setSecure(boolean)}.
   * <ul>
   *   <li>Then {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor) Secure.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setSecure(boolean)}
   */
  @Test
  public void testSetSecure_thenConnectorWithProtocolHandlerIsAjpNio2ProtocolSecure() {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());

    // Act
    connector.setSecure(true);

    // Assert
    assertTrue(connector.getSecure());
  }

  /**
   * Test {@link Connector#setSecure(boolean)}.
   * <ul>
   *   <li>Then {@link Connector#Connector(String)} with protocol is {@code secure} Secure.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setSecure(boolean)}
   */
  @Test
  public void testSetSecure_thenConnectorWithProtocolIsSecureSecure() {
    // Arrange
    Connector connector = new Connector("secure");

    // Act
    connector.setSecure(true);

    // Assert
    assertTrue(connector.getSecure());
  }

  /**
   * Test {@link Connector#getURIEncoding()}.
   * <p>
   * Method under test: {@link Connector#getURIEncoding()}
   */
  @Test
  public void testGetURIEncoding() {
    // Arrange, Act and Assert
    assertEquals("UTF-8", (new Connector()).getURIEncoding());
  }

  /**
   * Test {@link Connector#getExecutorName()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return {@link Connector#INTERNAL_EXECUTOR_NAME}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getExecutorName()}
   */
  @Test
  public void testGetExecutorName_givenConnector_thenReturnInternal_executor_name() {
    // Arrange, Act and Assert
    assertEquals(Connector.INTERNAL_EXECUTOR_NAME, (new Connector()).getExecutorName());
  }

  /**
   * Test {@link Connector#addSslHostConfig(SSLHostConfig)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addSslHostConfig(SSLHostConfig)}
   */
  @Test
  public void testAddSslHostConfig_givenConnectorWithProtocolHandlerIsAjpNio2Protocol() {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());

    // Act
    connector.addSslHostConfig(new SSLHostConfig());

    // Assert that nothing has changed
    assertEquals(0, connector.findSslHostConfigs().length);
  }

  /**
   * Test {@link Connector#addSslHostConfig(SSLHostConfig)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNioProtocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addSslHostConfig(SSLHostConfig)}
   */
  @Test
  public void testAddSslHostConfig_givenConnectorWithProtocolHandlerIsAjpNioProtocol() {
    // Arrange
    Connector connector = new Connector(new AjpNioProtocol());

    // Act
    connector.addSslHostConfig(new SSLHostConfig());

    // Assert that nothing has changed
    assertEquals(0, connector.findSslHostConfigs().length);
  }

  /**
   * Test {@link Connector#addSslHostConfig(SSLHostConfig)}.
   * <ul>
   *   <li>Then {@link Connector#Connector()} ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addSslHostConfig(SSLHostConfig)}
   */
  @Test
  public void testAddSslHostConfig_thenConnectorProtocolHandlerHttp11NioProtocol() {
    // Arrange
    Connector connector = new Connector();
    SSLHostConfig sslHostConfig = new SSLHostConfig();

    // Act
    connector.addSslHostConfig(sslHostConfig);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    SSLHostConfig[] findSslHostConfigsResult = connector.findSslHostConfigs();
    assertEquals(1, findSslHostConfigsResult.length);
    SSLHostConfig[] findSslHostConfigsResult2 = protocolHandler.findSslHostConfigs();
    assertEquals(1, findSslHostConfigsResult2.length);
    assertSame(sslHostConfig, findSslHostConfigsResult[0]);
    assertSame(sslHostConfig, findSslHostConfigsResult2[0]);
  }

  /**
   * Test {@link Connector#findSslHostConfigs()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#findSslHostConfigs()}
   */
  @Test
  public void testFindSslHostConfigs_givenConnectorWithProtocolHandlerIsAjpNio2Protocol() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector(new AjpNio2Protocol())).findSslHostConfigs().length);
  }

  /**
   * Test {@link Connector#findSslHostConfigs()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#findSslHostConfigs()}
   */
  @Test
  public void testFindSslHostConfigs_givenConnector_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector()).findSslHostConfigs().length);
  }

  /**
   * Test {@link Connector#addUpgradeProtocol(UpgradeProtocol)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addUpgradeProtocol(UpgradeProtocol)}
   */
  @Test
  public void testAddUpgradeProtocol_givenConnectorWithProtocolHandlerIsAjpNio2Protocol() {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());

    // Act
    connector.addUpgradeProtocol(new Http2Protocol());

    // Assert that nothing has changed
    assertEquals(0, connector.findUpgradeProtocols().length);
  }

  /**
   * Test {@link Connector#addUpgradeProtocol(UpgradeProtocol)}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNioProtocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addUpgradeProtocol(UpgradeProtocol)}
   */
  @Test
  public void testAddUpgradeProtocol_givenConnectorWithProtocolHandlerIsAjpNioProtocol() {
    // Arrange
    Connector connector = new Connector(new AjpNioProtocol());

    // Act
    connector.addUpgradeProtocol(new Http2Protocol());

    // Assert that nothing has changed
    assertEquals(0, connector.findUpgradeProtocols().length);
  }

  /**
   * Test {@link Connector#addUpgradeProtocol(UpgradeProtocol)}.
   * <ul>
   *   <li>Then {@link Connector#Connector()} ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#addUpgradeProtocol(UpgradeProtocol)}
   */
  @Test
  public void testAddUpgradeProtocol_thenConnectorProtocolHandlerHttp11NioProtocol() {
    // Arrange
    Connector connector = new Connector();
    Http2Protocol upgradeProtocol = new Http2Protocol();

    // Act
    connector.addUpgradeProtocol(upgradeProtocol);

    // Assert
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    UpgradeProtocol[] findUpgradeProtocolsResult = connector.findUpgradeProtocols();
    assertEquals(1, findUpgradeProtocolsResult.length);
    UpgradeProtocol[] findUpgradeProtocolsResult2 = protocolHandler.findUpgradeProtocols();
    assertEquals(1, findUpgradeProtocolsResult2.length);
    assertSame(upgradeProtocol, findUpgradeProtocolsResult[0]);
    assertSame(upgradeProtocol, findUpgradeProtocolsResult2[0]);
  }

  /**
   * Test {@link Connector#findUpgradeProtocols()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#findUpgradeProtocols()}
   */
  @Test
  public void testFindUpgradeProtocols_givenConnectorWithProtocolHandlerIsAjpNio2Protocol() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector(new AjpNio2Protocol())).findUpgradeProtocols().length);
  }

  /**
   * Test {@link Connector#findUpgradeProtocols()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#findUpgradeProtocols()}
   */
  @Test
  public void testFindUpgradeProtocols_givenConnector_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new Connector()).findUpgradeProtocols().length);
  }

  /**
   * Test {@link Connector#getEncodedReverseSolidusHandling()}.
   * <p>
   * Method under test: {@link Connector#getEncodedReverseSolidusHandling()}
   */
  @Test
  public void testGetEncodedReverseSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("decode", (new Connector()).getEncodedReverseSolidusHandling());
  }

  /**
   * Test {@link Connector#getEncodedSolidusHandling()}.
   * <p>
   * Method under test: {@link Connector#getEncodedSolidusHandling()}
   */
  @Test
  public void testGetEncodedSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("reject", (new Connector()).getEncodedSolidusHandling());
  }

  /**
   * Test {@link Connector#setEncodedSolidusHandling(String)}.
   * <ul>
   *   <li>Then {@link Connector#Connector()} EncodedSolidusHandling is {@code decode}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#setEncodedSolidusHandling(String)}
   */
  @Test
  public void testSetEncodedSolidusHandling_thenConnectorEncodedSolidusHandlingIsDecode() {
    // Arrange
    Connector connector = new Connector();

    // Act
    connector.setEncodedSolidusHandling("decode");

    // Assert
    assertEquals("decode", connector.getEncodedSolidusHandling());
    assertEquals(EncodedSolidusHandling.DECODE, connector.getEncodedSolidusHandlingInternal());
  }

  /**
   * Test {@link Connector#createResponse(Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return BufferSize is {@link InputBuffer#DEFAULT_BUFFER_SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#createResponse(Response)}
   */
  @Test
  public void testCreateResponse_givenConnector_thenReturnBufferSizeIsDefault_buffer_size() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE, connector.createResponse(new Response()).getBufferSize());
  }

  /**
   * Test {@link Connector#createResponse(Response)}.
   * <ul>
   *   <li>Then return BufferSize is {@code 8184}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#createResponse(Response)}
   */
  @Test
  public void testCreateResponse_thenReturnBufferSizeIs8184() {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());

    // Act and Assert
    assertEquals(8184, connector.createResponse(new Response()).getBufferSize());
  }

  /**
   * Test {@link Connector#createObjectNameKeyProperties(String)}.
   * <ul>
   *   <li>Then return {@code type=Type,port=auto-null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#createObjectNameKeyProperties(String)}
   */
  @Test
  public void testCreateObjectNameKeyProperties_thenReturnTypeTypePortAutoNull() {
    // Arrange, Act and Assert
    assertEquals("type=Type,port=auto-null", (new Connector("address")).createObjectNameKeyProperties("Type"));
  }

  /**
   * Test {@link Connector#initInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#initInternal()}
   */
  @Test
  public void testInitInternal_givenConnector() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector()).initInternal());
  }

  /**
   * Test {@link Connector#initInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#initInternal()}
   */
  @Test
  public void testInitInternal_givenConnectorWithProtocolHandlerIsAjpNio2Protocol() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector(new AjpNio2Protocol())).initInternal());
  }

  /**
   * Test {@link Connector#initInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with protocol is {@code Connector}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#initInternal()}
   */
  @Test
  public void testInitInternal_givenConnectorWithProtocolIsConnector() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector("Connector")).initInternal());
  }

  /**
   * Test {@link Connector#startInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector(String)} with protocol is {@code coyoteConnector.invalidPort}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#startInternal()}
   */
  @Test
  public void testStartInternal_givenConnectorWithProtocolIsCoyoteConnectorInvalidPort() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector("coyoteConnector.invalidPort")).startInternal());
  }

  /**
   * Test {@link Connector#startInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#startInternal()}
   */
  @Test
  public void testStartInternal_givenConnector_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector()).startInternal());
  }

  /**
   * Test {@link Connector#startInternal()}.
   * <ul>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#startInternal()}
   */
  @Test
  public void testStartInternal_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new Connector(new AjpNio2Protocol())).startInternal());
  }

  /**
   * Test {@link Connector#toString()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return {@code Connector["http-nio--1"]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#toString()}
   */
  @Test
  public void testToString_givenConnector_thenReturnConnectorHttpNio1() {
    // Arrange, Act and Assert
    assertEquals("Connector[\"http-nio--1\"]", (new Connector()).toString());
  }

  /**
   * Test {@link Connector#toString()}.
   * <ul>
   *   <li>Then return {@code Connector[Connector[-auto-null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#toString()}
   */
  @Test
  public void testToString_thenReturnConnectorConnectorAutoNull() {
    // Arrange, Act and Assert
    assertEquals("Connector[Connector[-auto-null]", (new Connector("Connector[")).toString());
  }

  /**
   * Test {@link Connector#toString()}.
   * <ul>
   *   <li>Then return {@code Connector["http-nio2--1"]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#toString()}
   */
  @Test
  public void testToString_thenReturnConnectorHttpNio21() {
    // Arrange, Act and Assert
    assertEquals("Connector[\"http-nio2--1\"]", (new Connector(new Http11Nio2Protocol())).toString());
  }

  /**
   * Test {@link Connector#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} Service is {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenConnectorServiceIsStandardService_thenReturnCatalina() {
    // Arrange
    Connector connector = new Connector();
    connector.setService(new StandardService());

    // Act and Assert
    assertEquals("Catalina", connector.getDomainInternal());
  }

  /**
   * Test {@link Connector#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenConnector_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Connector()).getDomainInternal());
  }

  /**
   * Test {@link Connector#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardServiceDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardService service = new StandardService();
    service.setDomain("Catalina");

    Connector connector = new Connector();
    connector.setService(service);

    // Act and Assert
    assertEquals("Catalina", connector.getDomainInternal());
  }

  /**
   * Test {@link Connector#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Name is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardServiceNameIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardService service = new StandardService();
    service.setName("Catalina");

    Connector connector = new Connector();
    connector.setService(service);

    // Act and Assert
    assertEquals("Catalina", connector.getDomainInternal());
  }

  /**
   * Test {@link Connector#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Connector,port=auto-null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Connector#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeConnectorPortAutoNull() {
    // Arrange, Act and Assert
    assertEquals("type=Connector,port=auto-null", (new Connector("Connector")).getObjectNameKeyProperties());
  }
}
