package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpUpgradeHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.apache.catalina.valves.TestRemoteIpValve;
import org.apache.catalina.valves.TestRemoteIpValve.MockRequest;
import org.apache.tomcat.unittest.TesterRequest;
import org.junit.Test;

public class RequestFacadeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RequestFacade#RequestFacade(Request)}
   *   <li>{@link RequestFacade#clear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Connector connector = new Connector();

    // Act
    RequestFacade actualRequestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    actualRequestFacade.clear();

    // Assert
    assertNull(actualRequestFacade.request);
  }

  /**
   * Test {@link RequestFacade#clone()}.
   * <p>
   * Method under test: {@link RequestFacade#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(CloneNotSupportedException.class,
        () -> (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).clone());
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("Name"));
    assertFalse(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenReturnValue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAttribute("Name", "Value");
    RequestFacade requestFacade = new RequestFacade(request);

    // Act and Assert
    assertEquals("Value", requestFacade.getAttribute("Name"));
    assertFalse(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getAttribute("Name"));
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.cipher_suite}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestCipherSuite() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.cipher_suite"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.key_size}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestKeySize() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.key_size"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.secure_protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSecureProtocol() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.secure_protocol"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_id}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSslSessionId() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.ssl_session_id"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_mgr}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestSslSessionMgr() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.ssl_session_mgr"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.X509Certificate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenJakartaServletRequestX509Certificate() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("jakarta.servlet.request.X509Certificate"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_protocol_version}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureProtocolVersion() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("org.apache.tomcat.util.net.secure_protocol_version"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_requested_ciphers}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureRequestedCiphers() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("org.apache.tomcat.util.net.secure_requested_ciphers"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_requested_protocol_versions}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenOrgApacheTomcatUtilNetSecureRequestedProtocolVersions() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act and Assert
    assertNull(requestFacade.getAttribute("org.apache.tomcat.util.net.secure_requested_protocol_versions"));
    assertTrue(requestFacade.request.sslAttributesParsed);
  }

  /**
   * Test {@link RequestFacade#getAttributeNames()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAttributeNames()}
   */
  @Test
  public void testGetAttributeNames_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getAttributeNames());
  }

  /**
   * Test {@link RequestFacade#setCharacterEncoding(Charset)} with {@code Charset}.
   * <p>
   * Method under test: {@link RequestFacade#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();

    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setCharacterEncoding((Charset) null);

    // Assert that nothing has changed
    assertTrue(requestFacade.request.getRequest() instanceof RequestFacade);
  }

  /**
   * Test {@link RequestFacade#setCharacterEncoding(Charset)} with {@code Charset}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset_thenThrowIllegalStateException() {
    // Arrange
    RequestFacade requestFacade = new RequestFacade(null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> requestFacade.setCharacterEncoding(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link RequestFacade#setCharacterEncoding(String)} with {@code String}.
   * <p>
   * Method under test: {@link RequestFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithString() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act
    requestFacade.setCharacterEncoding("UTF-8");

    // Assert
    Request request = requestFacade.request;
    HttpServletRequest request2 = request.getRequest();
    assertTrue(request2 instanceof RequestFacade);
    assertEquals("UTF-8", request2.getCharacterEncoding());
    assertEquals("UTF-8", request.getCharacterEncoding());
    assertEquals("UTF-8", requestFacade.getCharacterEncoding());
    org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();
    assertEquals("UTF-8", coyoteRequest.getCharacterEncoding());
    assertEquals("UTF-8", coyoteRequest.getCharsetHolder().getName());
  }

  /**
   * Test {@link RequestFacade#setCharacterEncoding(String)} with {@code String}.
   * <p>
   * Method under test: {@link RequestFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithString2() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();

    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setCharacterEncoding((String) null);

    // Assert that nothing has changed
    assertTrue(requestFacade.request.getRequest() instanceof RequestFacade);
  }

  /**
   * Test {@link RequestFacade#setCharacterEncoding(String)} with {@code String}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithString_thenThrowIllegalStateException() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).setCharacterEncoding("UTF-8"));
  }

  /**
   * Test {@link RequestFacade#getContentLength()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is {@code 2147483647}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLength()}
   */
  @Test
  public void testGetContentLength_givenRequestContentLengthIs2147483647_thenReturnMinusOne() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(2147483647L);

    // Act and Assert
    assertEquals(-1, (new RequestFacade(new Request(new Connector(), coyoteRequest))).getContentLength());
  }

  /**
   * Test {@link RequestFacade#getContentLength()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLength()}
   */
  @Test
  public void testGetContentLength_givenRequestContentLengthIsThree_thenReturnThree() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(3L);

    // Act and Assert
    assertEquals(3, (new RequestFacade(new Request(new Connector(), coyoteRequest))).getContentLength());
  }

  /**
   * Test {@link RequestFacade#getContentLength()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLength()}
   */
  @Test
  public void testGetContentLength_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getContentLength());
  }

  /**
   * Test {@link RequestFacade#getContentLength()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLength()}
   */
  @Test
  public void testGetContentLength_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getContentLength());
  }

  /**
   * Test {@link RequestFacade#getContentType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentType()}
   */
  @Test
  public void testGetContentType_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getContentType());
  }

  /**
   * Test {@link RequestFacade#getContentType()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentType()}
   */
  @Test
  public void testGetContentType_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getContentType());
  }

  /**
   * Test {@link RequestFacade#getInputStream()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getInputStream()}
   */
  @Test
  public void testGetInputStream_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getInputStream());
  }

  /**
   * Test {@link RequestFacade#getParameter(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getParameter(String)}
   */
  @Test
  public void testGetParameter_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getParameter("Name"));
  }

  /**
   * Test {@link RequestFacade#getParameterNames()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getParameterNames()}
   */
  @Test
  public void testGetParameterNames_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getParameterNames());
  }

  /**
   * Test {@link RequestFacade#getParameterValues(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getParameterValues(String)}
   */
  @Test
  public void testGetParameterValues_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getParameterValues("Name"));
  }

  /**
   * Test {@link RequestFacade#getParameterMap()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getParameterMap()}
   */
  @Test
  public void testGetParameterMap_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getParameterMap());
  }

  /**
   * Test {@link RequestFacade#getProtocol()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getProtocol()}
   */
  @Test
  public void testGetProtocol_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getProtocol());
  }

  /**
   * Test {@link RequestFacade#getProtocol()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getProtocol()}
   */
  @Test
  public void testGetProtocol_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getProtocol());
  }

  /**
   * Test {@link RequestFacade#getScheme()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getScheme()}
   */
  @Test
  public void testGetScheme_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getScheme());
  }

  /**
   * Test {@link RequestFacade#getScheme()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return {@code http}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getScheme()}
   */
  @Test
  public void testGetScheme_givenRequestFacadeWithRequestIsTesterRequest_thenReturnHttp() {
    // Arrange, Act and Assert
    assertEquals("http", (new RequestFacade(new TesterRequest())).getScheme());
  }

  /**
   * Test {@link RequestFacade#getScheme()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getScheme()}
   */
  @Test
  public void testGetScheme_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getScheme());
  }

  /**
   * Test {@link RequestFacade#getServerPort()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServerPort()}
   */
  @Test
  public void testGetServerPort_givenRequestFacadeWithRequestIsTesterRequest_thenReturn8080() {
    // Arrange, Act and Assert
    assertEquals(8080, (new RequestFacade(new TesterRequest())).getServerPort());
  }

  /**
   * Test {@link RequestFacade#getServerPort()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServerPort()}
   */
  @Test
  public void testGetServerPort_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getServerPort());
  }

  /**
   * Test {@link RequestFacade#getServerPort()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServerPort()}
   */
  @Test
  public void testGetServerPort_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getServerPort());
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();

    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setAttribute("org.apache.tomcat.", null);

    // Assert that nothing has changed
    assertTrue(requestFacade.request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute2() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();

    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setAttribute("org.apache.tomcat.", "42");

    // Assert
    HashMap<String, Object> attributes = requestFacade.request.getCoyoteRequest().getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("42", attributes.get("org.apache.tomcat."));
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute3() throws UnsupportedEncodingException {
    // Arrange
    Connector connector = new Connector();

    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setAttribute("Name", null);

    // Assert that nothing has changed
    assertTrue(requestFacade.request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link Request#Request(Connector, Request)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_givenRequestFacadeWithRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act
    requestFacade.setAttribute("Name", "42");

    // Assert that nothing has changed
    assertTrue(requestFacade.request.getCoyoteRequest().getAttributes().isEmpty());
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <ul>
   *   <li>Then {@link RequestFacade#RequestFacade(Request)} with request is {@link TestRemoteIpValve.MockRequest#MockRequest(Request)} {@link RequestFacade#request} {@link TestRemoteIpValve.MockRequest}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_thenRequestFacadeWithRequestIsMockRequestRequestMockRequest()
      throws UnsupportedEncodingException {
    // Arrange
    RequestFacade requestFacade = new RequestFacade(new MockRequest(new org.apache.coyote.Request()));
    requestFacade.setCharacterEncoding((String) null);

    // Act
    requestFacade.setAttribute("org.apache.tomcat.", null);

    // Assert
    Request request = requestFacade.request;
    assertTrue(request instanceof MockRequest);
    HashMap<String, Object> attributes = request.getCoyoteRequest().getAttributes();
    assertEquals(1, attributes.size());
    assertNull(attributes.get("org.apache.tomcat."));
  }

  /**
   * Test {@link RequestFacade#setAttribute(String, Object)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).setAttribute("Name", "42"));
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getLocale());
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();
    RequestFacade requestFacade = new RequestFacade(new Request(connector, new org.apache.coyote.Request()));

    // Act
    Locale actualLocale = requestFacade.getLocale();

    // Assert
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, requestFacade.request.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()} addHeader {@code accept-language} and {@code ;}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_givenTesterRequestAddHeaderAcceptLanguageAndSemicolon() {
    // Arrange
    TesterRequest request = new TesterRequest();
    request.addHeader("accept-language", ";");
    RequestFacade requestFacade = new RequestFacade(request);

    // Act
    Locale actualLocale = requestFacade.getLocale();

    // Assert
    Request request2 = requestFacade.request;
    assertTrue(request2 instanceof TesterRequest);
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request2.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Then {@link RequestFacade#RequestFacade(Request)} with request is {@link Request#Request(Connector, Request)} {@link RequestFacade#request} {@link Request#locales} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_thenRequestFacadeWithRequestIsRequestRequestLocalesSizeIsOne() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.addLocale(Request.defaultLocale);
    RequestFacade requestFacade = new RequestFacade(request);

    // Act
    Locale actualLocale = requestFacade.getLocale();

    // Assert
    Request request2 = requestFacade.request;
    ArrayList<Locale> localeList = request2.locales;
    assertEquals(1, localeList.size());
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, localeList.get(0));
    assertSame(locale, request2.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Then {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()} {@link RequestFacade#request} Locale is {@link Locale#ENGLISH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_thenRequestFacadeWithRequestIsTesterRequestRequestLocaleIsEnglish() {
    // Arrange
    RequestFacade requestFacade = new RequestFacade(new TesterRequest());

    // Act
    Locale actualLocale = requestFacade.getLocale();

    // Assert
    Request request = requestFacade.request;
    assertTrue(request instanceof TesterRequest);
    Locale locale = actualLocale.ENGLISH;
    assertSame(locale, request.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link RequestFacade#getLocale()}.
   * <ul>
   *   <li>Then {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()} {@link RequestFacade#request} {@link Request#locales} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocale()}
   */
  @Test
  public void testGetLocale_thenRequestFacadeWithRequestIsTesterRequestRequestLocalesSizeIsOne() {
    // Arrange
    TesterRequest request = new TesterRequest();
    request.addHeader("accept-language", "42");
    RequestFacade requestFacade = new RequestFacade(request);

    // Act
    Locale actualLocale = requestFacade.getLocale();

    // Assert
    Request request2 = requestFacade.request;
    assertTrue(request2 instanceof TesterRequest);
    ArrayList<Locale> localeList = ((TesterRequest) request2).locales;
    assertEquals(1, localeList.size());
    Locale locale = actualLocale.ROOT;
    assertSame(locale, localeList.get(0));
    assertSame(locale, request2.getLocale());
    assertSame(locale, actualLocale);
  }

  /**
   * Test {@link RequestFacade#getLocales()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocales()}
   */
  @Test
  public void testGetLocales_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getLocales());
  }

  /**
   * Test {@link RequestFacade#isSecure()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isSecure()}
   */
  @Test
  public void testIsSecure_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isSecure());
  }

  /**
   * Test {@link RequestFacade#isSecure()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isSecure()}
   */
  @Test
  public void testIsSecure_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isSecure());
  }

  /**
   * Test {@link RequestFacade#isSecure()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isSecure()}
   */
  @Test
  public void testIsSecure_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setSecure(true);

    // Act and Assert
    assertTrue((new RequestFacade(request)).isSecure());
  }

  /**
   * Test {@link RequestFacade#getRequestDispatcher(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestDispatcher(String)}
   */
  @Test
  public void testGetRequestDispatcher_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull(
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getRequestDispatcher("Path"));
  }

  /**
   * Test {@link RequestFacade#getRequestDispatcher(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestDispatcher(String)}
   */
  @Test
  public void testGetRequestDispatcher_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRequestDispatcher("Path"));
  }

  /**
   * Test {@link RequestFacade#getAuthType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAuthType()}
   */
  @Test
  public void testGetAuthType_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getAuthType());
  }

  /**
   * Test {@link RequestFacade#getAuthType()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAuthType()}
   */
  @Test
  public void testGetAuthType_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getAuthType());
  }

  /**
   * Test {@link RequestFacade#getCookies()}.
   * <p>
   * Method under test: {@link RequestFacade#getCookies()}
   */
  @Test
  public void testGetCookies() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.addCookie(new Cookie("Name", "https://example.org/example"));

    // Act and Assert
    assertSame(request.cookies, (new RequestFacade(request)).getCookies());
  }

  /**
   * Test {@link RequestFacade#getCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is minus one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getCookies()}
   */
  @Test
  public void testGetCookies_givenConnectorMaxCookieCountIsMinusOne_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(-1);

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getCookies());
  }

  /**
   * Test {@link RequestFacade#getCookies()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} MaxCookieCount is three.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getCookies()}
   */
  @Test
  public void testGetCookies_givenConnectorMaxCookieCountIsThree_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();
    connector.setMaxCookieCount(3);

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getCookies());
  }

  /**
   * Test {@link RequestFacade#getCookies()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getCookies()}
   */
  @Test
  public void testGetCookies_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getCookies());
  }

  /**
   * Test {@link RequestFacade#getCookies()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getCookies()}
   */
  @Test
  public void testGetCookies_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getCookies());
  }

  /**
   * Test {@link RequestFacade#getDateHeader(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L,
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getDateHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getDateHeader(String)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new RequestFacade(new TesterRequest())).getDateHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getDateHeader(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getDateHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenRequestFacadeWithRequestIsTesterRequest_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new RequestFacade(new TesterRequest())).getHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getHeaders(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getHeaders("Name"));
  }

  /**
   * Test {@link RequestFacade#getHeaderNames()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getHeaderNames());
  }

  /**
   * Test {@link RequestFacade#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenRequestFacadeWithRequestIsTesterRequest_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new RequestFacade(new TesterRequest())).getIntHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1, (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getIntHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getIntHeader(String)}.
   * <ul>
   *   <li>Given {@link TesterRequest#TesterRequest()} addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_givenTesterRequestAddHeaderNameAnd42_thenReturnFortyTwo() {
    // Arrange
    TesterRequest request = new TesterRequest();
    request.addHeader("Name", "42");
    request.addHeader("Name", "");

    // Act and Assert
    assertEquals(42, (new RequestFacade(request)).getIntHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getIntHeader(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getIntHeader("Name"));
  }

  /**
   * Test {@link RequestFacade#getHttpServletMapping()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getHttpServletMapping()}
   */
  @Test
  public void testGetHttpServletMapping_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getHttpServletMapping());
  }

  /**
   * Test {@link RequestFacade#getMethod()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getMethod()}
   */
  @Test
  public void testGetMethod_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getMethod());
  }

  /**
   * Test {@link RequestFacade#getMethod()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@link TesterRequest#TesterRequest()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getMethod()}
   */
  @Test
  public void testGetMethod_givenRequestFacadeWithRequestIsTesterRequest_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new RequestFacade(new TesterRequest())).getMethod());
  }

  /**
   * Test {@link RequestFacade#getMethod()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getMethod()}
   */
  @Test
  public void testGetMethod_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getMethod());
  }

  /**
   * Test {@link RequestFacade#getPathInfo()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getPathInfo()}
   */
  @Test
  public void testGetPathInfo_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getPathInfo());
  }

  /**
   * Test {@link RequestFacade#getPathInfo()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getPathInfo()}
   */
  @Test
  public void testGetPathInfo_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getPathInfo());
  }

  /**
   * Test {@link RequestFacade#getContextPath()}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContextPath()}
   */
  @Test
  public void testGetContextPath_thenReturnEmptyString() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("", (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getContextPath());
  }

  /**
   * Test {@link RequestFacade#getContextPath()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContextPath()}
   */
  @Test
  public void testGetContextPath_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getContextPath());
  }

  /**
   * Test {@link RequestFacade#getQueryString()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getQueryString()}
   */
  @Test
  public void testGetQueryString_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getQueryString());
  }

  /**
   * Test {@link RequestFacade#getQueryString()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getQueryString()}
   */
  @Test
  public void testGetQueryString_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getQueryString());
  }

  /**
   * Test {@link RequestFacade#getRemoteUser()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getRemoteUser());
  }

  /**
   * Test {@link RequestFacade#getRemoteUser()}.
   * <ul>
   *   <li>Then return {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenReturnPrincipal() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));

    // Act and Assert
    assertEquals("principal", (new RequestFacade(request)).getRemoteUser());
  }

  /**
   * Test {@link RequestFacade#getRemoteUser()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRemoteUser());
  }

  /**
   * Test {@link RequestFacade#isUserInRole(String)}.
   * <p>
   * Method under test: {@link RequestFacade#isUserInRole(String)}
   */
  @Test
  public void testIsUserInRole() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));

    // Act and Assert
    assertFalse((new RequestFacade(request)).isUserInRole("Role"));
  }

  /**
   * Test {@link RequestFacade#isUserInRole(String)}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isUserInRole(String)}
   */
  @Test
  public void testIsUserInRole_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isUserInRole("Role"));
  }

  /**
   * Test {@link RequestFacade#isUserInRole(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isUserInRole(String)}
   */
  @Test
  public void testIsUserInRole_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isUserInRole("Role"));
  }

  /**
   * Test {@link RequestFacade#getRequestedSessionId()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestedSessionId()}
   */
  @Test
  public void testGetRequestedSessionId_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getRequestedSessionId());
  }

  /**
   * Test {@link RequestFacade#getRequestedSessionId()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestedSessionId()}
   */
  @Test
  public void testGetRequestedSessionId_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRequestedSessionId());
  }

  /**
   * Test {@link RequestFacade#getRequestURI()}.
   * <ul>
   *   <li>Then return {@code /level1/level2/foo.html}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestURI()}
   */
  @Test
  public void testGetRequestURI_thenReturnLevel1Level2FooHtml() {
    // Arrange, Act and Assert
    assertEquals("/level1/level2/foo.html", (new RequestFacade(new TesterRequest())).getRequestURI());
  }

  /**
   * Test {@link RequestFacade#getRequestURI()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestURI()}
   */
  @Test
  public void testGetRequestURI_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getRequestURI());
  }

  /**
   * Test {@link RequestFacade#getRequestURI()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestURI()}
   */
  @Test
  public void testGetRequestURI_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRequestURI());
  }

  /**
   * Test {@link RequestFacade#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code http://localhost:8080/level1/level2/foo.html}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestURL()}
   */
  @Test
  public void testGetRequestURL_thenReturnToStringIsHttpLocalhost8080Level1Level2FooHtml() {
    // Arrange, Act and Assert
    assertEquals("http://localhost:8080/level1/level2/foo.html",
        (new RequestFacade(new TesterRequest())).getRequestURL().toString());
  }

  /**
   * Test {@link RequestFacade#getRequestURL()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestURL()}
   */
  @Test
  public void testGetRequestURL_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRequestURL());
  }

  /**
   * Test {@link RequestFacade#getServletPath()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServletPath()}
   */
  @Test
  public void testGetServletPath_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getServletPath());
  }

  /**
   * Test {@link RequestFacade#getServletPath()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServletPath()}
   */
  @Test
  public void testGetServletPath_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getServletPath());
  }

  /**
   * Test {@link RequestFacade#changeSessionId()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#changeSessionId()}
   */
  @Test
  public void testChangeSessionId_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).changeSessionId());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromCookie()}.
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionId("42");

    // Act and Assert
    assertFalse((new RequestFacade(request)).isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionCookie(true);
    request.setRequestedSessionId("42");

    // Act and Assert
    assertTrue((new RequestFacade(request)).isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromURL()}.
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionId("42");

    // Act and Assert
    assertFalse((new RequestFacade(request)).isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setRequestedSessionURL(true);
    request.setRequestedSessionId("42");

    // Act and Assert
    assertTrue((new RequestFacade(request)).isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link RequestFacade#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link RequestFacade#getLocalAddr()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalAddr()}
   */
  @Test
  public void testGetLocalAddr_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getLocalAddr());
  }

  /**
   * Test {@link RequestFacade#getLocalAddr()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalAddr()}
   */
  @Test
  public void testGetLocalAddr_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getLocalAddr());
  }

  /**
   * Test {@link RequestFacade#getLocalName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalName()}
   */
  @Test
  public void testGetLocalName_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getLocalName());
  }

  /**
   * Test {@link RequestFacade#getLocalName()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalName()}
   */
  @Test
  public void testGetLocalName_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getLocalName());
  }

  /**
   * Test {@link RequestFacade#getLocalPort()}.
   * <ul>
   *   <li>Then return {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_thenReturn8080() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setLocalPort(8080);

    // Act and Assert
    assertEquals(8080, (new RequestFacade(request)).getLocalPort());
  }

  /**
   * Test {@link RequestFacade#getLocalPort()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_thenReturnZero() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(0, (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getLocalPort());
  }

  /**
   * Test {@link RequestFacade#getLocalPort()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getLocalPort()}
   */
  @Test
  public void testGetLocalPort_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getLocalPort());
  }

  /**
   * Test {@link RequestFacade#getRemotePort()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRemotePort()}
   */
  @Test
  public void testGetRemotePort_thenReturnZero() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(0, (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getRemotePort());
  }

  /**
   * Test {@link RequestFacade#getRemotePort()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRemotePort()}
   */
  @Test
  public void testGetRemotePort_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRemotePort());
  }

  /**
   * Test {@link RequestFacade#isAsyncStarted()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isAsyncStarted()}
   */
  @Test
  public void testIsAsyncStarted_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isAsyncStarted());
  }

  /**
   * Test {@link RequestFacade#isAsyncStarted()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isAsyncStarted()}
   */
  @Test
  public void testIsAsyncStarted_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isAsyncStarted());
  }

  /**
   * Test {@link RequestFacade#isAsyncSupported()}.
   * <p>
   * Method under test: {@link RequestFacade#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertTrue((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isAsyncSupported());
  }

  /**
   * Test {@link RequestFacade#isAsyncSupported()}.
   * <p>
   * Method under test: {@link RequestFacade#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported2() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAsyncSupported(true);

    // Act and Assert
    assertTrue((new RequestFacade(request)).isAsyncSupported());
  }

  /**
   * Test {@link RequestFacade#isAsyncSupported()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAsyncSupported(false);

    // Act and Assert
    assertFalse((new RequestFacade(request)).isAsyncSupported());
  }

  /**
   * Test {@link RequestFacade#isAsyncSupported()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isAsyncSupported());
  }

  /**
   * Test {@link RequestFacade#getAsyncContext()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAsyncContext()}
   */
  @Test
  public void testGetAsyncContext_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getAsyncContext());
  }

  /**
   * Test {@link RequestFacade#getDispatcherType()}.
   * <ul>
   *   <li>Then return {@code REQUEST}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getDispatcherType()}
   */
  @Test
  public void testGetDispatcherType_thenReturnRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(DispatcherType.REQUEST,
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getDispatcherType());
  }

  /**
   * Test {@link RequestFacade#getDispatcherType()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getDispatcherType()}
   */
  @Test
  public void testGetDispatcherType_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getDispatcherType());
  }

  /**
   * Test {@link RequestFacade#authenticate(HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#authenticate(HttpServletResponse)}
   */
  @Test
  public void testAuthenticate_thenThrowIllegalStateException() throws ServletException, IOException {
    // Arrange
    RequestFacade requestFacade = new RequestFacade(null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> requestFacade
        .authenticate(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))));
  }

  /**
   * Test {@link RequestFacade#login(String, String)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#login(String, String)}
   */
  @Test
  public void testLogin_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() throws ServletException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).login("janedoe", "iloveyou"));
  }

  /**
   * Test {@link RequestFacade#logout()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#logout()}
   */
  @Test
  public void testLogout_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException() throws ServletException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).logout());
  }

  /**
   * Test {@link RequestFacade#getParts()}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getParts()}
   */
  @Test
  public void testGetParts_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException()
      throws ServletException, IOException, IllegalStateException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getParts());
  }

  /**
   * Test {@link RequestFacade#getPart(String)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getPart(String)}
   */
  @Test
  public void testGetPart_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException()
      throws ServletException, IOException, IllegalStateException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getPart("Name"));
  }

  /**
   * Test {@link RequestFacade#getAllowTrace()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} AllowTrace is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAllowTrace()}
   */
  @Test
  public void testGetAllowTrace_givenConnectorAllowTraceIsTrue_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();
    connector.setAllowTrace(true);

    // Act and Assert
    assertTrue((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getAllowTrace());
  }

  /**
   * Test {@link RequestFacade#getAllowTrace()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAllowTrace()}
   */
  @Test
  public void testGetAllowTrace_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getAllowTrace());
  }

  /**
   * Test {@link RequestFacade#getAllowTrace()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getAllowTrace()}
   */
  @Test
  public void testGetAllowTrace_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getAllowTrace());
  }

  /**
   * Test {@link RequestFacade#getContentLengthLong()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Request} (default constructor) ContentLength is three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLengthLong()}
   */
  @Test
  public void testGetContentLengthLong_givenRequestContentLengthIsThree_thenReturnThree() {
    // Arrange
    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setContentLength(3L);

    // Act and Assert
    assertEquals(3L, (new RequestFacade(new Request(new Connector(), coyoteRequest))).getContentLengthLong());
  }

  /**
   * Test {@link RequestFacade#getContentLengthLong()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLengthLong()}
   */
  @Test
  public void testGetContentLengthLong_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L,
        (new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).getContentLengthLong());
  }

  /**
   * Test {@link RequestFacade#getContentLengthLong()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getContentLengthLong()}
   */
  @Test
  public void testGetContentLengthLong_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getContentLengthLong());
  }

  /**
   * Test {@link RequestFacade#upgrade(Class)}.
   * <ul>
   *   <li>Given {@link RequestFacade#RequestFacade(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#upgrade(Class)}
   */
  @Test
  public void testUpgrade_givenRequestFacadeWithRequestIsNull_thenThrowIllegalStateException()
      throws ServletException, IOException {
    // Arrange
    RequestFacade requestFacade = new RequestFacade(null);
    Class<HttpUpgradeHandler> httpUpgradeHandlerClass = HttpUpgradeHandler.class;

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> requestFacade.upgrade(httpUpgradeHandlerClass));
  }

  /**
   * Test {@link RequestFacade#isTrailerFieldsReady()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new RequestFacade(new Request(connector, new org.apache.coyote.Request()))).isTrailerFieldsReady());
  }

  /**
   * Test {@link RequestFacade#isTrailerFieldsReady()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).isTrailerFieldsReady());
  }

  /**
   * Test {@link RequestFacade#getTrailerFields()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getTrailerFields()}
   */
  @Test
  public void testGetTrailerFields_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getTrailerFields());
  }

  /**
   * Test {@link RequestFacade#getRequestId()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getRequestId()}
   */
  @Test
  public void testGetRequestId_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getRequestId());
  }

  /**
   * Test {@link RequestFacade#getProtocolRequestId()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getProtocolRequestId()}
   */
  @Test
  public void testGetProtocolRequestId_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getProtocolRequestId());
  }

  /**
   * Test {@link RequestFacade#getServletConnection()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFacade#getServletConnection()}
   */
  @Test
  public void testGetServletConnection_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new RequestFacade(null)).getServletConnection());
  }
}
