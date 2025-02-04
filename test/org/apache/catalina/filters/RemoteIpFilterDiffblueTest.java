package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.filters.RemoteIpFilter.XForwardedRequest;
import org.apache.coyote.Request;
import org.junit.Test;

public class RemoteIpFilterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RemoteIpFilter#setChangeLocalName(boolean)}
   *   <li>{@link RemoteIpFilter#setChangeLocalPort(boolean)}
   *   <li>{@link RemoteIpFilter#setEnableLookups(boolean)}
   *   <li>{@link RemoteIpFilter#setHostHeader(String)}
   *   <li>{@link RemoteIpFilter#setHttpServerPort(int)}
   *   <li>{@link RemoteIpFilter#setHttpsServerPort(int)}
   *   <li>{@link RemoteIpFilter#setPortHeader(String)}
   *   <li>{@link RemoteIpFilter#setProtocolHeader(String)}
   *   <li>{@link RemoteIpFilter#setProtocolHeaderHttpsValue(String)}
   *   <li>{@link RemoteIpFilter#setProxiesHeader(String)}
   *   <li>{@link RemoteIpFilter#setRemoteIpHeader(String)}
   *   <li>{@link RemoteIpFilter#setRequestAttributesEnabled(boolean)}
   *   <li>{@link RemoteIpFilter#getEnableLookups()}
   *   <li>{@link RemoteIpFilter#getHttpsServerPort()}
   *   <li>{@link RemoteIpFilter#getInternalProxies()}
   *   <li>{@link RemoteIpFilter#getPortHeader()}
   *   <li>{@link RemoteIpFilter#getProtocolHeader()}
   *   <li>{@link RemoteIpFilter#getProtocolHeaderHttpsValue()}
   *   <li>{@link RemoteIpFilter#getProxiesHeader()}
   *   <li>{@link RemoteIpFilter#getRemoteIpHeader()}
   *   <li>{@link RemoteIpFilter#getRequestAttributesEnabled()}
   *   <li>{@link RemoteIpFilter#getTrustedProxies()}
   *   <li>{@link RemoteIpFilter#isChangeLocalName()}
   *   <li>{@link RemoteIpFilter#isChangeLocalPort()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    RemoteIpFilter remoteIpFilter = new RemoteIpFilter();

    // Act
    remoteIpFilter.setChangeLocalName(true);
    remoteIpFilter.setChangeLocalPort(true);
    remoteIpFilter.setEnableLookups(true);
    remoteIpFilter.setHostHeader("Host Header");
    remoteIpFilter.setHttpServerPort(8080);
    remoteIpFilter.setHttpsServerPort(8080);
    remoteIpFilter.setPortHeader("Port Header");
    remoteIpFilter.setProtocolHeader("Protocol Header");
    remoteIpFilter.setProtocolHeaderHttpsValue("https://example.org/example");
    remoteIpFilter.setProxiesHeader("Proxies Header");
    remoteIpFilter.setRemoteIpHeader("Remote Ip Header");
    remoteIpFilter.setRequestAttributesEnabled(true);
    boolean actualEnableLookups = remoteIpFilter.getEnableLookups();
    int actualHttpsServerPort = remoteIpFilter.getHttpsServerPort();
    Pattern actualInternalProxies = remoteIpFilter.getInternalProxies();
    String actualPortHeader = remoteIpFilter.getPortHeader();
    String actualProtocolHeader = remoteIpFilter.getProtocolHeader();
    String actualProtocolHeaderHttpsValue = remoteIpFilter.getProtocolHeaderHttpsValue();
    String actualProxiesHeader = remoteIpFilter.getProxiesHeader();
    String actualRemoteIpHeader = remoteIpFilter.getRemoteIpHeader();
    boolean actualRequestAttributesEnabled = remoteIpFilter.getRequestAttributesEnabled();
    Pattern actualTrustedProxies = remoteIpFilter.getTrustedProxies();
    boolean actualIsChangeLocalNameResult = remoteIpFilter.isChangeLocalName();
    boolean actualIsChangeLocalPortResult = remoteIpFilter.isChangeLocalPort();

    // Assert
    assertEquals(
        "10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|192\\.168\\.\\d{1,3}\\.\\d{1,3}|169\\.254\\.\\d{1,3}\\.\\d{1,3}|127\\.\\d{1,3}\\.\\d"
            + "{1,3}\\.\\d{1,3}|100\\.6[4-9]{1}\\.\\d{1,3}\\.\\d{1,3}|100\\.[7-9]{1}\\d{1}\\.\\d{1,3}\\.\\d{1,3}|100\\.1[0-1]{1}\\d"
            + "{1}\\.\\d{1,3}\\.\\d{1,3}|100\\.12[0-7]{1}\\.\\d{1,3}\\.\\d{1,3}|172\\.1[6-9]{1}\\.\\d{1,3}\\.\\d{1,3}|172\\.2[0-9]"
            + "{1}\\.\\d{1,3}\\.\\d{1,3}|172\\.3[0-1]{1}\\.\\d{1,3}\\.\\d{1,3}|0:0:0:0:0:0:0:1|::1",
        actualInternalProxies.pattern());
    assertEquals("Port Header", actualPortHeader);
    assertEquals("Protocol Header", actualProtocolHeader);
    assertEquals("Proxies Header", actualProxiesHeader);
    assertEquals("Remote Ip Header", actualRemoteIpHeader);
    assertEquals("https://example.org/example", actualProtocolHeaderHttpsValue);
    assertNull(actualTrustedProxies);
    assertEquals(8080, actualHttpsServerPort);
    assertTrue(actualEnableLookups);
    assertTrue(actualRequestAttributesEnabled);
    assertTrue(actualIsChangeLocalNameResult);
    assertTrue(actualIsChangeLocalPortResult);
  }

  /**
   * Test {@link RemoteIpFilter#setInternalProxies(String)}.
   * <ul>
   *   <li>Then {@link RemoteIpFilter} (default constructor) InternalProxies pattern is {@code Internal Proxies}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteIpFilter#setInternalProxies(String)}
   */
  @Test
  public void testSetInternalProxies_thenRemoteIpFilterInternalProxiesPatternIsInternalProxies() {
    // Arrange
    RemoteIpFilter remoteIpFilter = new RemoteIpFilter();

    // Act
    remoteIpFilter.setInternalProxies("Internal Proxies");

    // Assert
    assertEquals("Internal Proxies", remoteIpFilter.getInternalProxies().pattern());
  }

  /**
   * Test {@link RemoteIpFilter#setInternalProxies(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteIpFilter#setInternalProxies(String)}
   */
  @Test
  public void testSetInternalProxies_whenDefault_allowed_origins() {
    // Arrange
    RemoteIpFilter remoteIpFilter = new RemoteIpFilter();

    // Act
    remoteIpFilter.setInternalProxies(CorsFilter.DEFAULT_ALLOWED_ORIGINS);

    // Assert
    assertNull(remoteIpFilter.getInternalProxies());
  }

  /**
   * Test {@link RemoteIpFilter#setInternalProxies(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RemoteIpFilter} (default constructor) InternalProxies is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteIpFilter#setInternalProxies(String)}
   */
  @Test
  public void testSetInternalProxies_whenNull_thenRemoteIpFilterInternalProxiesIsNull() {
    // Arrange
    RemoteIpFilter remoteIpFilter = new RemoteIpFilter();

    // Act
    remoteIpFilter.setInternalProxies(null);

    // Assert
    assertNull(remoteIpFilter.getInternalProxies());
  }

  /**
   * Test {@link RemoteIpFilter#setTrustedProxies(String)}.
   * <ul>
   *   <li>Then {@link RemoteIpFilter} (default constructor) TrustedProxies pattern is {@code Trusted Proxies}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteIpFilter#setTrustedProxies(String)}
   */
  @Test
  public void testSetTrustedProxies_thenRemoteIpFilterTrustedProxiesPatternIsTrustedProxies() {
    // Arrange
    RemoteIpFilter remoteIpFilter = new RemoteIpFilter();

    // Act
    remoteIpFilter.setTrustedProxies("Trusted Proxies");

    // Assert
    assertEquals("Trusted Proxies", remoteIpFilter.getTrustedProxies().pattern());
  }

  /**
   * Test new {@link RemoteIpFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RemoteIpFilter}
   */
  @Test
  public void testNewRemoteIpFilter() {
    // Arrange and Act
    RemoteIpFilter actualRemoteIpFilter = new RemoteIpFilter();

    // Assert
    assertEquals("X-Forwarded-By", actualRemoteIpFilter.getProxiesHeader());
    assertEquals("X-Forwarded-For", actualRemoteIpFilter.getRemoteIpHeader());
    assertEquals("X-Forwarded-Proto", actualRemoteIpFilter.getProtocolHeader());
    assertEquals("https", actualRemoteIpFilter.getProtocolHeaderHttpsValue());
    assertNull(actualRemoteIpFilter.getFilterConfig());
    assertNull(actualRemoteIpFilter.getPortHeader());
    assertNull(actualRemoteIpFilter.getTrustedProxies());
    assertEquals(443, actualRemoteIpFilter.getHttpsServerPort());
    assertFalse(actualRemoteIpFilter.getEnableLookups());
    assertFalse(actualRemoteIpFilter.isChangeLocalName());
    assertFalse(actualRemoteIpFilter.isChangeLocalPort());
    assertTrue(actualRemoteIpFilter.getRequestAttributesEnabled());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getDateHeader(String)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getDateHeader(String)}
   */
  @Test
  public void testXForwardedRequestGetDateHeader_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L,
        (new XForwardedRequest(new HttpServletRequestWrapper(
            new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))))
            .getDateHeader("Name"));
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getHeaderEntry(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getHeaderEntry(String)}
   */
  @Test
  public void testXForwardedRequestGetHeaderEntry_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))))
        .getHeaderEntry("Name"));
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getHeader(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getHeader(String)}
   */
  @Test
  public void testXForwardedRequestGetHeader_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))).getHeader("Name"));
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getIntHeader(String)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getIntHeader(String)}
   */
  @Test
  public void testXForwardedRequestGetIntHeader_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1,
        (new XForwardedRequest(new HttpServletRequestWrapper(
            new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))))
            .getIntHeader("Name"));
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code http://null:0null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getRequestURL()}
   */
  @Test
  public void testXForwardedRequestGetRequestURL_thenReturnToStringIsHttpNull0null() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    xForwardedRequest.setServerPort(0);
    xForwardedRequest.setScheme("http");

    // Act and Assert
    assertEquals("http://null:0null", xForwardedRequest.getRequestURL().toString());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code http://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getRequestURL()}
   */
  @Test
  public void testXForwardedRequestGetRequestURL_thenReturnToStringIsHttpNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    xForwardedRequest.setServerPort(80);
    xForwardedRequest.setScheme("http");

    // Act and Assert
    assertEquals("http://nullnull", xForwardedRequest.getRequestURL().toString());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code https://null:0null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getRequestURL()}
   */
  @Test
  public void testXForwardedRequestGetRequestURL_thenReturnToStringIsHttpsNull0null() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    xForwardedRequest.setServerPort(0);
    xForwardedRequest.setScheme("https");

    // Act and Assert
    assertEquals("https://null:0null", xForwardedRequest.getRequestURL().toString());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code https://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getRequestURL()}
   */
  @Test
  public void testXForwardedRequestGetRequestURL_thenReturnToStringIsHttpsNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    xForwardedRequest.setServerPort(443);
    xForwardedRequest.setScheme("https");

    // Act and Assert
    assertEquals("https://nullnull", xForwardedRequest.getRequestURL().toString());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code ://://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link XForwardedRequest#getRequestURL()}
   */
  @Test
  public void testXForwardedRequestGetRequestURL_thenReturnToStringIsNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    xForwardedRequest.setScheme("://");

    // Act and Assert
    assertEquals("://://nullnull", xForwardedRequest.getRequestURL().toString());
  }

  /**
   * Test XForwardedRequest getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XForwardedRequest#setLocalName(String)}
   *   <li>{@link XForwardedRequest#setLocalPort(int)}
   *   <li>{@link XForwardedRequest#setRemoteAddr(String)}
   *   <li>{@link XForwardedRequest#setRemoteHost(String)}
   *   <li>{@link XForwardedRequest#setScheme(String)}
   *   <li>{@link XForwardedRequest#setServerName(String)}
   *   <li>{@link XForwardedRequest#setServerPort(int)}
   *   <li>{@link XForwardedRequest#getLocalName()}
   *   <li>{@link XForwardedRequest#getLocalPort()}
   *   <li>{@link XForwardedRequest#getRemoteAddr()}
   *   <li>{@link XForwardedRequest#getRemoteHost()}
   *   <li>{@link XForwardedRequest#getScheme()}
   *   <li>{@link XForwardedRequest#getServerName()}
   *   <li>{@link XForwardedRequest#getServerPort()}
   * </ul>
   */
  @Test
  public void testXForwardedRequestGettersAndSetters() {
    // Arrange
    Connector connector = new Connector();
    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));

    // Act
    xForwardedRequest.setLocalName("Local Name");
    xForwardedRequest.setLocalPort(8080);
    xForwardedRequest.setRemoteAddr("42 Main St");
    xForwardedRequest.setRemoteHost("localhost");
    xForwardedRequest.setScheme("Scheme");
    xForwardedRequest.setServerName("Server Name");
    xForwardedRequest.setServerPort(8080);
    String actualLocalName = xForwardedRequest.getLocalName();
    int actualLocalPort = xForwardedRequest.getLocalPort();
    String actualRemoteAddr = xForwardedRequest.getRemoteAddr();
    String actualRemoteHost = xForwardedRequest.getRemoteHost();
    String actualScheme = xForwardedRequest.getScheme();
    String actualServerName = xForwardedRequest.getServerName();

    // Assert
    assertEquals("42 Main St", actualRemoteAddr);
    assertEquals("Local Name", actualLocalName);
    assertEquals("Scheme", actualScheme);
    assertEquals("Server Name", actualServerName);
    assertEquals("localhost", actualRemoteHost);
    assertEquals(8080, actualLocalPort);
    assertEquals(8080, xForwardedRequest.getServerPort());
  }

  /**
   * Test XForwardedRequest {@link XForwardedRequest#setHeader(String, String)}.
   * <p>
   * Method under test: {@link XForwardedRequest#setHeader(String, String)}
   */
  @Test
  public void testXForwardedRequestSetHeader() {
    // Arrange
    Connector connector = new Connector();
    XForwardedRequest xForwardedRequest = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));

    // Act
    xForwardedRequest.setHeader("Name", "42");

    // Assert
    Map<String, List<String>> stringListMap = xForwardedRequest.headers;
    assertEquals(1, stringListMap.size());
    List<String> getResult = stringListMap.get("Name");
    assertEquals(1, getResult.size());
    assertEquals("42", getResult.get(0));
  }
}
