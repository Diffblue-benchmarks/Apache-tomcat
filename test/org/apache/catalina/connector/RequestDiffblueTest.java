package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.catalina.core.AsyncContextImpl;
import org.apache.catalina.mapper.MappingData;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.valves.TestRemoteIpValve;
import org.apache.catalina.valves.TestRemoteIpValve.MockRequest;
import org.apache.tomcat.unittest.TesterRequest;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.buf.CharChunk;
import org.apache.tomcat.util.buf.MessageBytes;
import org.junit.Test;

public class RequestDiffblueTest {
  /**
   * Test {@link Request#getPathParameter(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getPathParameter(String)}
   */
  @Test
  public void testGetPathParameter_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getPathParameter("Name"));
  }

  /**
   * Test {@link Request#setAsyncSupported(boolean)}.
   * <p>
   * Method under test: {@link Request#setAsyncSupported(boolean)}
   */
  @Test
  public void testSetAsyncSupported() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setAsyncSupported(true);

    // Assert
    assertTrue(request.asyncSupported);
  }

  /**
   * Test {@link Request#recycleSessionInfo()}.
   * <p>
   * Method under test: {@link Request#recycleSessionInfo()}
   */
  @Test
  public void testRecycleSessionInfo() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.recycleSessionInfo();

    // Assert that nothing has changed
    assertTrue(request.getRequest() instanceof RequestFacade);
  }

  /**
   * Test {@link Request#recycleSessionInfo()}.
   * <p>
   * Method under test: {@link Request#recycleSessionInfo()}
   */
  @Test
  public void testRecycleSessionInfo2() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest(true);

    // Act
    testerRequest.recycleSessionInfo();

    // Assert
    HttpServletRequest request = testerRequest.getRequest();
    assertTrue(request instanceof RequestFacade);
    assertNull(request.getSession());
    assertNull(testerRequest.getSession());
    assertNull(testerRequest.getSessionInternal());
    assertNull(testerRequest.session);
  }

  /**
   * Test {@link Request#getHost()}.
   * <p>
   * Method under test: {@link Request#getHost()}
   */
  @Test
  public void testGetHost() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getHost());
  }

  /**
   * Test {@link Request#setRequest(HttpServletRequest)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#setRequest(HttpServletRequest)}
   */
  @Test
  public void testSetRequest_thenThrowIllegalArgumentException() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    Connector connector2 = new Connector();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> request.setRequest(
        new HttpServletRequestWrapper(new RequestFacade(new Request(connector2, new org.apache.coyote.Request())))));
  }

  /**
   * Test {@link Request#getWrapper()}.
   * <p>
   * Method under test: {@link Request#getWrapper()}
   */
  @Test
  public void testGetWrapper() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getWrapper());
  }

  /**
   * Test {@link Request#getNote(String)}.
   * <p>
   * Method under test: {@link Request#getNote(String)}
   */
  @Test
  public void testGetNote() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getNote("Name"));
  }

  /**
   * Test {@link Request#setServerPort(int)}.
   * <p>
   * Method under test: {@link Request#setServerPort(int)}
   */
  @Test
  public void testSetServerPort() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setServerPort(8080);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    assertEquals(8080, request2.getServerPort());
    assertEquals(8080, request.getServerPort());
    org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();
    assertEquals(8080, coyoteRequest.getServerPort());
    assertEquals(8080, coyoteRequest.getRequestProcessor().getServerPort());
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("Name"));
    assertFalse(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenReturnValue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAttribute("Name", "Value");

    // Act and Assert
    assertEquals("Value", request.getAttribute("Name"));
    assertFalse(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.cipher_suite}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestCipherSuite() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.cipher_suite"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.key_size}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestKeySize() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.key_size"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.secure_protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSecureProtocol() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.secure_protocol"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_id}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSslSessionId() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.ssl_session_id"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_mgr}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSslSessionMgr() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.ssl_session_mgr"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.X509Certificate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestX509Certificate() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("jakarta.servlet.request.X509Certificate"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_protocol_version}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureProtocolVersion() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("org.apache.tomcat.util.net.secure_protocol_version"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_requested_ciphers}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureRequestedCiphers() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("org.apache.tomcat.util.net.secure_requested_ciphers"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_requested_protocol_versions}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureRequestedProtocolVersions() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(request.getAttribute("org.apache.tomcat.util.net.secure_requested_protocol_versions"));
    assertTrue(request.sslAttributesParsed);
  }

  /**
   * Test {@link Request#getContentLengthLong()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentLengthLong()}
   */
  @Test
  public void testGetContentLengthLong_givenRequestContentLengthIsThree_thenReturnThree() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(3L);

    // Act and Assert
    assertEquals(3L, (new Request(new Connector(), coyoteRequest)).getContentLengthLong());
  }

  /**
   * Test {@link Request#getContentLengthLong()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentLengthLong()}
   */
  @Test
  public void testGetContentLengthLong_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L, (new Request(connector, new org.apache.coyote.Request())).getContentLengthLong());
  }

  /**
   * Test {@link Request#getContentLength()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is {@code 2147483647}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentLength()}
   */
  @Test
  public void testGetContentLength_givenRequestContentLengthIs2147483647_thenReturnMinusOne() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(2147483647L);

    // Act and Assert
    assertEquals(-1, (new Request(new Connector(), coyoteRequest)).getContentLength());
  }

  /**
   * Test {@link Request#getContentLength()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentLength()}
   */
  @Test
  public void testGetContentLength_givenRequestContentLengthIsThree_thenReturnThree() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(3L);

    // Act and Assert
    assertEquals(3, (new Request(new Connector(), coyoteRequest)).getContentLength());
  }

  /**
   * Test {@link Request#getContentLength()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentLength()}
   */
  @Test
  public void testGetContentLength_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new Request(connector, new org.apache.coyote.Request())).getContentLength());
  }

  /**
   * Test {@link Request#getContentType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getContentType()}
   */
  @Test
  public void testGetContentType_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getContentType());
  }

  /**
   * Test {@link Request#getLocale()}.
   * <p>
   * Method under test: {@link Request#getLocale()}
   */
  @Test
  public void testGetLocale() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.addLocale(Request.defaultLocale);

    // Act
    Locale actualLocale = request.getLocale();

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    ArrayList<Locale> localeList = request.locales;
    assertEquals(1, localeList.size());
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request2.getLocale());
    assertSame(locale, localeList.get(0));
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link Request#getLocale()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocale()}
   */
  @Test
  public void testGetLocale_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    Locale actualLocale = request.getLocale();

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request2.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link Request#getLocale()}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()} addHeader {@code accept-language} and {@code ;}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocale()}
   */
  @Test
  public void testGetLocale_givenTesterRequestAddHeaderAcceptLanguageAndSemicolon() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest();
    testerRequest.addHeader("accept-language", ";");

    // Act
    Locale actualLocale = testerRequest.getLocale();

    // Assert
    HttpServletRequest request = testerRequest.getRequest();
    assertTrue(request instanceof RequestFacade);
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link Request#getLocale()}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then {@link TesterRequest#TesterRequest()} Request Locale is {@link Locale#ENGLISH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocale()}
   */
  @Test
  public void testGetLocale_givenTesterRequest_thenTesterRequestRequestLocaleIsEnglish() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest();

    // Act
    Locale actualLocale = testerRequest.getLocale();

    // Assert
    HttpServletRequest request = testerRequest.getRequest();
    assertTrue(request instanceof RequestFacade);
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link Request#getLocale()}.
   * <ul>
   *   <li>Then {@link TesterRequest#TesterRequest()} {@link Request#locales} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocale()}
   */
  @Test
  public void testGetLocale_thenTesterRequestLocalesSizeIsOne() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest();
    testerRequest.addHeader("accept-language", "42");

    // Act
    Locale actualLocale = testerRequest.getLocale();

    // Assert
    HttpServletRequest request = testerRequest.getRequest();
    assertTrue(request instanceof RequestFacade);
    ArrayList<Locale> localeList = testerRequest.locales;
    assertEquals(1, localeList.size());
    Locale locale = actualLocale.ROOT;
    assertSame(locale, request.getLocale());
    assertSame(locale, localeList.get(0));
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link Request#getProtocol()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getProtocol()}
   */
  @Test
  public void testGetProtocol_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getProtocol());
  }

  /**
   * Test {@link Request#getRemoteAddr()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRemoteAddr()}
   */
  @Test
  public void testGetRemoteAddr_thenReturnFoo() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRemoteAddr("foo");
    request.setServerPort(1);

    // Act and Assert
    assertEquals("foo", request.getRemoteAddr());
  }

  /**
   * Test {@link Request#getRemoteAddr()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRemoteAddr()}
   */
  @Test
  public void testGetRemoteAddr_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getRemoteAddr());
  }

  /**
   * Test {@link Request#getPeerAddr()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getPeerAddr()}
   */
  @Test
  public void testGetPeerAddr_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getPeerAddr());
  }

  /**
   * Test {@link Request#getRemotePort()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRemotePort()}
   */
  @Test
  public void testGetRemotePort_thenReturnZero() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(0, (new Request(connector, new org.apache.coyote.Request())).getRemotePort());
  }

  /**
   * Test {@link Request#getLocalName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocalName()}
   */
  @Test
  public void testGetLocalName_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getLocalName());
  }

  /**
   * Test {@link Request#getLocalAddr()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocalAddr()}
   */
  @Test
  public void testGetLocalAddr_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getLocalAddr());
  }

  /**
   * Test {@link Request#getLocalPort()}.
   * <ul>
   *   <li>Then return {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_thenReturn8080() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setLocalPort(8080);

    // Act and Assert
    assertEquals(8080, request.getLocalPort());
  }

  /**
   * Test {@link Request#getLocalPort()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_thenReturnZero() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(0, (new Request(connector, new org.apache.coyote.Request())).getLocalPort());
  }

  /**
   * Test {@link Request#getRequestDispatcher(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRequestDispatcher(String)}
   */
  @Test
  public void testGetRequestDispatcher_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getRequestDispatcher("Path"));
  }

  /**
   * Test {@link Request#getScheme()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getScheme()}
   */
  @Test
  public void testGetScheme_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getScheme());
  }

  /**
   * Test {@link Request#getServerName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getServerName()}
   */
  @Test
  public void testGetServerName_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getServerName());
  }

  /**
   * Test {@link Request#getServerPort()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getServerPort()}
   */
  @Test
  public void testGetServerPort_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new Request(connector, new org.apache.coyote.Request())).getServerPort());
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setAttribute("org.apache.tomcat.", "Value");

    // Assert
    HashMap<String, Object> attributes = request.getCoyoteRequest().getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("Value", attributes.get("org.apache.tomcat."));
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute2() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setAttribute("org.apache.tomcat.", null);

    // Assert that nothing has changed
    assertTrue(request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute3() {
    // Arrange
    MockRequest mockRequest = new MockRequest(new org.apache.coyote.Request());

    // Act
    mockRequest.setAttribute(null, "Value");

    // Assert
    HashMap<String, Object> attributes = mockRequest.getCoyoteRequest().getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("Value", attributes.get(null));
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_thenThrowIllegalArgumentException() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new Request(connector, new org.apache.coyote.Request())).setAttribute(null, "Value"));
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_whenName() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setAttribute("Name", "Value");

    // Assert that nothing has changed
    assertTrue(request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link Request#setAttribute(String, Object)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_whenName2() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setAttribute("Name", null);

    // Assert that nothing has changed
    assertTrue(request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link Request#setCharacterEncoding(Charset)} with {@code charset}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset_whenNull() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setCharacterEncoding((Charset) null);

    // Assert that nothing has changed
    assertTrue(request.getRequest() instanceof RequestFacade);
  }

  /**
   * Test {@link Request#isAsyncStarted()}.
   * <p>
   * Method under test: {@link Request#isAsyncStarted()}
   */
  @Test
  public void testIsAsyncStarted() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isAsyncStarted());
  }

  /**
   * Test {@link Request#isAsyncDispatching()}.
   * <p>
   * Method under test: {@link Request#isAsyncDispatching()}
   */
  @Test
  public void testIsAsyncDispatching() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isAsyncDispatching());
  }

  /**
   * Test {@link Request#isAsyncCompleting()}.
   * <p>
   * Method under test: {@link Request#isAsyncCompleting()}
   */
  @Test
  public void testIsAsyncCompleting() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isAsyncCompleting());
  }

  /**
   * Test {@link Request#isAsync()}.
   * <p>
   * Method under test: {@link Request#isAsync()}
   */
  @Test
  public void testIsAsync() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isAsync());
  }

  /**
   * Test {@link Request#isAsyncSupported()}.
   * <p>
   * Method under test: {@link Request#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertTrue((new Request(connector, new org.apache.coyote.Request())).isAsyncSupported());
  }

  /**
   * Test {@link Request#isAsyncSupported()}.
   * <p>
   * Method under test: {@link Request#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported2() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAsyncSupported(true);

    // Act and Assert
    assertTrue(request.isAsyncSupported());
  }

  /**
   * Test {@link Request#isAsyncSupported()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAsyncSupported(false);

    // Act and Assert
    assertFalse(request.isAsyncSupported());
  }

  /**
   * Test {@link Request#getAsyncContext()}.
   * <p>
   * Method under test: {@link Request#getAsyncContext()}
   */
  @Test
  public void testGetAsyncContext() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new Request(connector, new org.apache.coyote.Request())).getAsyncContext());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Request#setAuthType(String)}
   *   <li>{@link Request#setFilterChain(FilterChain)}
   *   <li>{@link Request#setLocalPort(int)}
   *   <li>{@link Request#setRemoteAddr(String)}
   *   <li>{@link Request#setRemoteHost(String)}
   *   <li>{@link Request#setRequestedSessionCookie(boolean)}
   *   <li>{@link Request#setRequestedSessionId(String)}
   *   <li>{@link Request#setRequestedSessionSSL(boolean)}
   *   <li>{@link Request#setRequestedSessionURL(boolean)}
   *   <li>{@link Request#setResponse(Response)}
   *   <li>{@link Request#setSecure(boolean)}
   *   <li>{@link Request#setURIConverter(B2CConverter)}
   *   <li>{@link Request#setUserPrincipal(Principal)}
   *   <li>{@link Request#getAsyncContextInternal()}
   *   <li>{@link Request#getAuthType()}
   *   <li>{@link Request#getConnector()}
   *   <li>{@link Request#getCoyoteRequest()}
   *   <li>{@link Request#getFilterChain()}
   *   <li>{@link Request#getMappingData()}
   *   <li>{@link Request#getPrincipal()}
   *   <li>{@link Request#getRequestedSessionId()}
   *   <li>{@link Request#getResponse()}
   *   <li>{@link Request#getURIConverter()}
   *   <li>{@link Request#isParametersParsed()}
   *   <li>{@link Request#isSecure()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Connector connector = new Connector();
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    Request request = new Request(connector, coyoteRequest);

    // Act
    request.setAuthType("Type");
    ApplicationFilterChain filterChain = new ApplicationFilterChain();
    request.setFilterChain(filterChain);
    request.setLocalPort(8080);
    request.setRemoteAddr("42 Main St");
    request.setRemoteHost("localhost");
    request.setRequestedSessionCookie(true);
    request.setRequestedSessionId("42");
    request.setRequestedSessionSSL(true);
    request.setRequestedSessionURL(true);
    Response response = new Response(new org.apache.coyote.Response());
    request.setResponse(response);
    request.setSecure(true);
    B2CConverter URIConverter = new B2CConverter(Charset.forName("UTF-8"));
    request.setURIConverter(URIConverter);
    UserPrincipal principal = new UserPrincipal("principal");
    request.setUserPrincipal(principal);
    AsyncContextImpl actualAsyncContextInternal = request.getAsyncContextInternal();
    String actualAuthType = request.getAuthType();
    Connector actualConnector = request.getConnector();
    org.apache.coyote.Request actualCoyoteRequest = request.getCoyoteRequest();
    FilterChain actualFilterChain = request.getFilterChain();
    MappingData actualMappingData = request.getMappingData();
    Principal actualPrincipal = request.getPrincipal();
    String actualRequestedSessionId = request.getRequestedSessionId();
    Response actualResponse = request.getResponse();
    B2CConverter actualURIConverter = request.getURIConverter();
    boolean actualIsParametersParsedResult = request.isParametersParsed();
    boolean actualIsSecureResult = request.isSecure();

    // Assert
    assertTrue(actualFilterChain instanceof ApplicationFilterChain);
    assertEquals("42", actualRequestedSessionId);
    assertEquals("Type", actualAuthType);
    assertNull(actualMappingData.contexts);
    assertNull(actualMappingData.matchType);
    MessageBytes messageBytes = actualMappingData.pathInfo;
    assertNull(messageBytes.getString());
    MessageBytes messageBytes2 = actualMappingData.redirectPath;
    assertNull(messageBytes2.getString());
    MessageBytes messageBytes3 = actualMappingData.requestPath;
    assertNull(messageBytes3.getString());
    MessageBytes messageBytes4 = actualMappingData.wrapperPath;
    assertNull(messageBytes4.getString());
    assertNull(actualMappingData.context);
    assertNull(actualMappingData.host);
    assertNull(actualMappingData.wrapper);
    assertNull(actualAsyncContextInternal);
    assertEquals(0, messageBytes.getLength());
    assertEquals(0, messageBytes2.getLength());
    assertEquals(0, messageBytes3.getLength());
    assertEquals(0, messageBytes4.getLength());
    assertEquals(0, messageBytes.getType());
    assertEquals(0, messageBytes2.getType());
    assertEquals(0, messageBytes3.getType());
    assertEquals(0, messageBytes4.getType());
    assertEquals(0, actualMappingData.contextSlashCount);
    assertFalse(actualIsParametersParsedResult);
    assertFalse(actualMappingData.jspWildCard);
    assertTrue(actualIsSecureResult);
    assertTrue(messageBytes.isNull());
    assertTrue(messageBytes2.isNull());
    assertTrue(messageBytes3.isNull());
    assertTrue(messageBytes4.isNull());
    CharChunk charChunk = actualResponse.redirectURLCC;
    assertEquals(charChunk, messageBytes.getCharChunk());
    assertEquals(charChunk, messageBytes2.getCharChunk());
    assertEquals(charChunk, messageBytes3.getCharChunk());
    assertEquals(charChunk, messageBytes4.getCharChunk());
    assertSame(principal, actualPrincipal);
    assertSame(connector, actualConnector);
    assertSame(response, actualResponse);
    assertSame(filterChain, actualFilterChain);
    assertSame(coyoteRequest, actualCoyoteRequest);
    assertSame(URIConverter, actualURIConverter);
  }

  /**
   * Test {@link Request#getDispatcherType()}.
   * <p>
   * Method under test: {@link Request#getDispatcherType()}
   */
  @Test
  public void testGetDispatcherType() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(DispatcherType.REQUEST, (new Request(connector, new org.apache.coyote.Request())).getDispatcherType());
  }

  /**
   * Test {@link Request#addCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is minus one.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#addCookie(Cookie)}
   */
  @Test
  public void testAddCookie_givenConnectorMaxCookieCountIsMinusOne_thenArrayLengthIsOne() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(-1);
    Request request = new Request(connector, new org.apache.coyote.Request());
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    request.addCookie(cookie);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    Cookie[] cookies = request.getCookies();
    assertEquals(1, cookies.length);
    Cookie[] cookieArray = request.cookies;
    assertEquals(1, cookieArray.length);
    assertSame(cookie, cookies[0]);
    assertSame(cookie, cookieArray[0]);
    Cookie[] expectedCookies = request.cookies;
    assertSame(expectedCookies, request2.getCookies());
  }

  /**
   * Test {@link Request#addCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is three.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#addCookie(Cookie)}
   */
  @Test
  public void testAddCookie_givenConnectorMaxCookieCountIsThree_thenArrayLengthIsOne() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(3);
    Request request = new Request(connector, new org.apache.coyote.Request());
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    request.addCookie(cookie);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    Cookie[] cookies = request.getCookies();
    assertEquals(1, cookies.length);
    Cookie[] cookieArray = request.cookies;
    assertEquals(1, cookieArray.length);
    assertSame(cookie, cookies[0]);
    assertSame(cookie, cookieArray[0]);
    Cookie[] expectedCookies = request.cookies;
    assertSame(expectedCookies, request2.getCookies());
  }

  /**
   * Test {@link Request#addCookie(Cookie)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#addCookie(Cookie)}
   */
  @Test
  public void testAddCookie_thenArrayLengthIsOne() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    request.addCookie(cookie);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    Cookie[] cookies = request.getCookies();
    assertEquals(1, cookies.length);
    Cookie[] cookieArray = request.cookies;
    assertEquals(1, cookieArray.length);
    assertSame(cookie, cookies[0]);
    assertSame(cookie, cookieArray[0]);
    Cookie[] expectedCookies = request.cookies;
    assertSame(expectedCookies, request2.getCookies());
  }

  /**
   * Test {@link Request#addCookie(Cookie)}.
   * <ul>
   *   <li>Then array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#addCookie(Cookie)}
   */
  @Test
  public void testAddCookie_thenArrayLengthIsTwo() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    request.addCookie(cookie);
    Cookie cookie2 = new Cookie("Name", "https://example.org/example");

    // Act
    request.addCookie(cookie2);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    Cookie[] cookies = request.getCookies();
    assertEquals(2, cookies.length);
    Cookie[] cookieArray = request.cookies;
    assertEquals(2, cookieArray.length);
    assertSame(cookie, cookies[0]);
    assertSame(cookie2, cookies[1]);
    assertSame(cookie, cookieArray[0]);
    assertSame(cookie2, cookieArray[1]);
    Cookie[] expectedCookies = request.cookies;
    assertSame(expectedCookies, request2.getCookies());
  }

  /**
   * Test {@link Request#addLocale(Locale)}.
   * <p>
   * Method under test: {@link Request#addLocale(Locale)}
   */
  @Test
  public void testAddLocale() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    Locale locale = Request.defaultLocale;

    // Act
    request.addLocale(locale);

    // Assert
    ArrayList<Locale> localeList = request.locales;
    assertEquals(1, localeList.size());
    Locale expectedGetResult = locale.ENGLISH;
    assertSame(expectedGetResult, localeList.get(0));
  }

  /**
   * Test {@link Request#setPathInfo(String)}.
   * <p>
   * Method under test: {@link Request#setPathInfo(String)}
   */
  @Test
  public void testSetPathInfo() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setPathInfo("Path");

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    assertEquals("Path", request2.getPathInfo());
    assertEquals("Path", request.getPathInfo());
    MessageBytes messageBytes = request.getMappingData().pathInfo;
    assertEquals("Path", messageBytes.getString());
    assertEquals(1, messageBytes.getType());
    assertEquals(4, messageBytes.getLength());
    assertFalse(messageBytes.isNull());
  }

  /**
   * Test {@link Request#setPathInfo(String)}.
   * <p>
   * Method under test: {@link Request#setPathInfo(String)}
   */
  @Test
  public void testSetPathInfo2() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    request.setPathInfo(null);

    // Assert that nothing has changed
    assertTrue(request.getRequest() instanceof RequestFacade);
    MessageBytes messageBytes = request.getMappingData().pathInfo;
    assertEquals(0, messageBytes.getLength());
    assertEquals(0, messageBytes.getType());
    assertTrue(messageBytes.isNull());
  }

  /**
   * Test {@link Request#getDecodedRequestURI()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getDecodedRequestURI()}
   */
  @Test
  public void testGetDecodedRequestURI_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getDecodedRequestURI());
  }

  /**
   * Test {@link Request#getDecodedRequestURIMB()}.
   * <ul>
   *   <li>Then return String is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getDecodedRequestURIMB()}
   */
  @Test
  public void testGetDecodedRequestURIMB_thenReturnStringIsNull() {
    // Arrange
    Connector connector = new Connector();

    // Act
    MessageBytes actualDecodedRequestURIMB = (new Request(connector, new org.apache.coyote.Request()))
        .getDecodedRequestURIMB();

    // Assert
    assertNull(actualDecodedRequestURIMB.getString());
    assertEquals(0, actualDecodedRequestURIMB.getLength());
    assertEquals(0, actualDecodedRequestURIMB.getType());
    assertTrue(actualDecodedRequestURIMB.isNull());
  }

  /**
   * Test {@link Request#isTrailerFieldsReady()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isTrailerFieldsReady());
  }

  /**
   * Test {@link Request#getTrailerFields()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getTrailerFields()}
   */
  @Test
  public void testGetTrailerFields_thenThrowIllegalStateException() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new Request(connector, new org.apache.coyote.Request())).getTrailerFields());
  }

  /**
   * Test {@link Request#getContextPath()}.
   * <p>
   * Method under test: {@link Request#getContextPath()}
   */
  @Test
  public void testGetContextPath() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("", (new Request(connector, new org.apache.coyote.Request())).getContextPath());
  }

  /**
   * Test {@link Request#getCookies()}.
   * <p>
   * Method under test: {@link Request#getCookies()}
   */
  @Test
  public void testGetCookies() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.addCookie(new Cookie("Name", "https://example.org/example"));

    // Act and Assert
    assertSame(request.cookies, request.getCookies());
  }

  /**
   * Test {@link Request#getCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is minus one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getCookies()}
   */
  @Test
  public void testGetCookies_givenConnectorMaxCookieCountIsMinusOne_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(-1);

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getCookies());
  }

  /**
   * Test {@link Request#getCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is three.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getCookies()}
   */
  @Test
  public void testGetCookies_givenConnectorMaxCookieCountIsThree_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(3);

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getCookies());
  }

  /**
   * Test {@link Request#getCookies()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getCookies()}
   */
  @Test
  public void testGetCookies_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getCookies());
  }

  /**
   * Test {@link Request#getServerCookies()}.
   * <p>
   * Method under test: {@link Request#getServerCookies()}
   */
  @Test
  public void testGetServerCookies() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(0, (new Request(connector, new org.apache.coyote.Request())).getServerCookies().getCookieCount());
  }

  /**
   * Test {@link Request#getServerCookies()}.
   * <p>
   * Method under test: {@link Request#getServerCookies()}
   */
  @Test
  public void testGetServerCookies2() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.addCookie(new Cookie("Name", "https://example.org/example"));

    // Act and Assert
    assertEquals(0, request.getServerCookies().getCookieCount());
  }

  /**
   * Test {@link Request#getServerCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getServerCookies()}
   */
  @Test
  public void testGetServerCookies_givenConnectorMaxCookieCountIsMinusOne() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(-1);

    // Act and Assert
    assertEquals(0, (new Request(connector, new org.apache.coyote.Request())).getServerCookies().getCookieCount());
  }

  /**
   * Test {@link Request#getServerCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getServerCookies()}
   */
  @Test
  public void testGetServerCookies_givenConnectorMaxCookieCountIsThree() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(3);

    // Act and Assert
    assertEquals(0, (new Request(connector, new org.apache.coyote.Request())).getServerCookies().getCookieCount());
  }

  /**
   * Test {@link Request#getDateHeader(String)}.
   * <p>
   * Method under test: {@link Request#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest(true);
    testerRequest.addHeader("Name", "");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> testerRequest.getDateHeader("Name"));
  }

  /**
   * Test {@link Request#getDateHeader(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L, (new Request(connector, new org.apache.coyote.Request())).getDateHeader("Name"));
  }

  /**
   * Test {@link Request#getDateHeader(String)}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()} addHeader {@code Name} and empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_givenTesterRequestAddHeaderNameAndEmptyString() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest();
    testerRequest.addHeader("Name", "");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> testerRequest.getDateHeader("Name"));
  }

  /**
   * Test {@link Request#getDateHeader(String)}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_givenTesterRequest_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new TesterRequest()).getDateHeader("Name"));
  }

  /**
   * Test {@link Request#getHeader(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getHeader(String)}
   */
  @Test
  public void testGetHeader_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getHeader("Name"));
  }

  /**
   * Test {@link Request#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new Request(connector, new org.apache.coyote.Request())).getIntHeader("Name"));
  }

  /**
   * Test {@link Request#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()} addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenTesterRequestAddHeaderNameAnd42_thenReturnFortyTwo() {
    // Arrange
    TesterRequest testerRequest = new TesterRequest();
    testerRequest.addHeader("Name", "42");
    testerRequest.addHeader("Name", "");

    // Act and Assert
    assertEquals(42, testerRequest.getIntHeader("Name"));
  }

  /**
   * Test {@link Request#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenTesterRequest_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new TesterRequest()).getIntHeader("Name"));
  }

  /**
   * Test {@link Request#getMethod()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getMethod()}
   */
  @Test
  public void testGetMethod_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getMethod());
  }

  /**
   * Test {@link Request#getPathInfo()}.
   * <p>
   * Method under test: {@link Request#getPathInfo()}
   */
  @Test
  public void testGetPathInfo() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getPathInfo());
  }

  /**
   * Test {@link Request#getQueryString()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getQueryString()}
   */
  @Test
  public void testGetQueryString_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getQueryString());
  }

  /**
   * Test {@link Request#getRemoteUser()}.
   * <ul>
   *   <li>Then return {@code John Smith}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenReturnJohnSmith() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("John Smith"));

    // Act and Assert
    assertEquals("John Smith", request.getRemoteUser());
  }

  /**
   * Test {@link Request#getRemoteUser()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getRemoteUser());
  }

  /**
   * Test {@link Request#getRequestPathMB()}.
   * <p>
   * Method under test: {@link Request#getRequestPathMB()}
   */
  @Test
  public void testGetRequestPathMB() {
    // Arrange
    Connector connector = new Connector();

    // Act
    MessageBytes actualRequestPathMB = (new Request(connector, new org.apache.coyote.Request())).getRequestPathMB();

    // Assert
    assertNull(actualRequestPathMB.getString());
    assertEquals(0, actualRequestPathMB.getLength());
    assertEquals(0, actualRequestPathMB.getType());
    assertTrue(actualRequestPathMB.isNull());
  }

  /**
   * Test {@link Request#getRequestURI()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRequestURI()}
   */
  @Test
  public void testGetRequestURI_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getRequestURI());
  }

  /**
   * Test {@link Request#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code http://localhost:8080/level1/level2/foo.html}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getRequestURL()}
   */
  @Test
  public void testGetRequestURL_thenReturnToStringIsHttpLocalhost8080Level1Level2FooHtml() {
    // Arrange, Act and Assert
    assertEquals("http://localhost:8080/level1/level2/foo.html", (new TesterRequest()).getRequestURL().toString());
  }

  /**
   * Test {@link Request#getServletPath()}.
   * <p>
   * Method under test: {@link Request#getServletPath()}
   */
  @Test
  public void testGetServletPath() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).getServletPath());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromCookie()}.
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionId("foo");

    // Act and Assert
    assertFalse(request.isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionCookie(true);
    request.setRequestedSessionId("foo");

    // Act and Assert
    assertTrue(request.isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromURL()}.
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionId("foo");

    // Act and Assert
    assertFalse(request.isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link Request#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionURL(true);
    request.setRequestedSessionId("foo");

    // Act and Assert
    assertTrue(request.isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link Request#getUserPrincipal()}.
   * <ul>
   *   <li>Then return {@link GenericPrincipal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#getUserPrincipal()}
   */
  @Test
  public void testGetUserPrincipal_thenReturnGenericPrincipal() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new GenericPrincipal("Name"));

    // Act
    Principal actualUserPrincipal = request.getUserPrincipal();
    String actualName = actualUserPrincipal.getName();

    // Assert
    assertTrue(actualUserPrincipal instanceof GenericPrincipal);
    assertEquals("Name", actualUserPrincipal.getName());
    assertEquals("Name", actualName);
    assertNull(((GenericPrincipal) actualUserPrincipal).getGssCredential());
    assertEquals(0, ((GenericPrincipal) actualUserPrincipal).getRoles().length);
    assertSame(actualUserPrincipal, ((GenericPrincipal) actualUserPrincipal).getUserPrincipal());
  }

  /**
   * Test {@link Request#isFinished()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#isFinished()}
   */
  @Test
  public void testIsFinished_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new Request(connector, new org.apache.coyote.Request())).isFinished());
  }

  /**
   * Test {@link Request#login(String, String)}.
   * <p>
   * Method under test: {@link Request#login(String, String)}
   */
  @Test
  public void testLogin() throws ServletException {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAuthType(null);
    request.setUserPrincipal(new GenericPrincipal("Name"));

    // Act and Assert
    assertThrows(ServletException.class, () -> request.login("janedoe", "iloveyou"));
  }

  /**
   * Test {@link Request#login(String, String)}.
   * <p>
   * Method under test: {@link Request#login(String, String)}
   */
  @Test
  public void testLogin2() throws ServletException {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAuthType("foo");
    request.setUserPrincipal(new GenericPrincipal("Name"));

    // Act and Assert
    assertThrows(ServletException.class, () -> request.login("janedoe", "iloveyou"));
  }

  /**
   * Test {@link Request#login(String, String)}.
   * <p>
   * Method under test: {@link Request#login(String, String)}
   */
  @Test
  public void testLogin3() throws ServletException {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAuthType(null);
    request.setUserPrincipal(new GenericPrincipal(null));

    // Act and Assert
    assertThrows(ServletException.class, () -> request.login("janedoe", "iloveyou"));
  }

  /**
   * Test {@link Request#unescape(String)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#unescape(String)}
   */
  @Test
  public void testUnescape_whenFoo_thenReturnFoo() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("foo", (new Request(connector, new org.apache.coyote.Request())).unescape("foo"));
  }

  /**
   * Test {@link Request#unescape(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#unescape(String)}
   */
  @Test
  public void testUnescape_whenNull_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new Request(connector, new org.apache.coyote.Request())).unescape(null));
  }

  /**
   * Test {@link Request#parseLocalesHeader(String, TreeMap)}.
   * <p>
   * Method under test: {@link Request#parseLocalesHeader(String, TreeMap)}
   */
  @Test
  public void testParseLocalesHeader() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    TreeMap<Double, ArrayList<Locale>> locales = new TreeMap<>();

    // Act
    request.parseLocalesHeader("en", locales);

    // Assert
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    assertEquals(1, locales.size());
    ArrayList<Locale> getResult = locales.get(-1.0d);
    assertEquals(1, getResult.size());
    Locale locale = request.defaultLocale;
    assertSame(locale, request2.getLocale());
    assertSame(locale, getResult.get(0));
    assertSame(locale, request.getLocale());
  }

  /**
   * Test {@link Request#parseLocalesHeader(String, TreeMap)}.
   * <ul>
   *   <li>Then {@link TreeMap#TreeMap()} minus one first DisplayLanguage is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#parseLocalesHeader(String, TreeMap)}
   */
  @Test
  public void testParseLocalesHeader_thenTreeMapMinusOneFirstDisplayLanguageIsEmptyString()
      throws MissingResourceException {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    TreeMap<Double, ArrayList<Locale>> locales = new TreeMap<>();

    // Act
    request.parseLocalesHeader("application/x-www-form-urlencoded", locales);

    // Assert
    assertEquals(1, locales.size());
    ArrayList<Locale> getResult = locales.get(-1.0d);
    assertEquals(1, getResult.size());
    Locale getResult2 = getResult.get(0);
    assertEquals("", getResult2.getDisplayLanguage());
    assertEquals("", getResult2.getDisplayName());
    assertEquals("", getResult2.getISO3Language());
    assertEquals("", getResult2.getLanguage());
  }

  /**
   * Test {@link Request#parseLocalesHeader(String, TreeMap)}.
   * <ul>
   *   <li>When {@code ;}.</li>
   *   <li>Then {@link TreeMap#TreeMap()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Request#parseLocalesHeader(String, TreeMap)}
   */
  @Test
  public void testParseLocalesHeader_whenSemicolon_thenTreeMapEmpty() {
    // Arrange
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());
    TreeMap<Double, ArrayList<Locale>> locales = new TreeMap<>();

    // Act
    request.parseLocalesHeader(";", locales);

    // Assert that nothing has changed
    assertTrue(locales.isEmpty());
    Locale locale = request.getLocale();
    Set<Character> extensionKeys = locale.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, locale.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, locale.getUnicodeLocaleKeys());
  }
}
