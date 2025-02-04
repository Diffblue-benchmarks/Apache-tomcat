package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.authenticator.BasicAuthenticator.BasicCredentials;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.tomcat.unittest.TesterResponse;
import org.apache.tomcat.util.buf.ByteChunk;
import org.junit.Test;

public class BasicAuthenticatorDiffblueTest {
  /**
   * Test BasicCredentials {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}.
   * <ul>
   *   <li>Given wrap {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}
   */
  @Test
  public void testBasicCredentialsNewBasicCredentials_givenWrapAxaxaxaxBytesIsUtf8()
      throws IOException, IllegalArgumentException {
    // Arrange
    ByteChunk input = new ByteChunk(1);
    input.append(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new BasicCredentials(input, Charset.forName("UTF-8")));

  }

  /**
   * Test BasicCredentials {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}.
   * <ul>
   *   <li>Given wrap {@code bXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}
   */
  @Test
  public void testBasicCredentialsNewBasicCredentials_givenWrapBXAXAXAXBytesIsUtf8()
      throws IOException, IllegalArgumentException {
    // Arrange
    ByteChunk input = new ByteChunk(1);
    input.append(ByteBuffer.wrap("bXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new BasicCredentials(input, Charset.forName("UTF-8")));

  }

  /**
   * Test BasicCredentials {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}.
   * <ul>
   *   <li>When {@link ByteChunk#ByteChunk()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}
   */
  @Test
  public void testBasicCredentialsNewBasicCredentials_whenByteChunk() throws IllegalArgumentException {
    // Arrange
    ByteChunk input = new ByteChunk();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new BasicCredentials(input, Charset.forName("UTF-8")));

  }

  /**
   * Test BasicCredentials {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}.
   * <ul>
   *   <li>When {@link ByteChunk#ByteChunk(int)} with initial is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicCredentials#BasicCredentials(ByteChunk, Charset)}
   */
  @Test
  public void testBasicCredentialsNewBasicCredentials_whenByteChunkWithInitialIsOne() throws IllegalArgumentException {
    // Arrange
    ByteChunk input = new ByteChunk(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new BasicCredentials(input, Charset.forName("UTF-8")));

  }

  /**
   * Test {@link BasicAuthenticator#setCharset(String)}.
   * <ul>
   *   <li>When {@code Charset String}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#setCharset(String)}
   */
  @Test
  public void testSetCharset_whenCharsetString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new BasicAuthenticator()).setCharset("Charset String"));
  }

  /**
   * Test {@link BasicAuthenticator#setCharset(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Charset is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#setCharset(String)}
   */
  @Test
  public void testSetCharset_whenEmptyString_thenBasicAuthenticatorCharsetIsEmptyString() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setCharset("");

    // Assert
    assertEquals("", basicAuthenticator.getCharset());
  }

  /**
   * Test {@link BasicAuthenticator#setCharset(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Charset is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#setCharset(String)}
   */
  @Test
  public void testSetCharset_whenNull_thenBasicAuthenticatorCharsetIsNull() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setCharset(null);

    // Assert
    assertNull(basicAuthenticator.getCharset());
  }

  /**
   * Test {@link BasicAuthenticator#setCharset(String)}.
   * <ul>
   *   <li>When {@code UTF-8}.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Charset is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#setCharset(String)}
   */
  @Test
  public void testSetCharset_whenUtf8_thenBasicAuthenticatorCharsetIsUtf8() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setCharset("UTF-8");

    // Assert that nothing has changed
    assertEquals("UTF-8", basicAuthenticator.getCharset());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, response.getStatus());
    assertEquals(401, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate2() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader(Constants.REQ_SSOID_NOTE, "42");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(1));
    assertEquals(2, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate3() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("WWW-Authenticate", "42");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate4() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    boolean actualDoAuthenticateResult = basicAuthenticator.doAuthenticate(request, response);

    // Assert
    assertNull(response.getHeaderNames());
    assertEquals(0, response.getStatus());
    assertFalse(actualDoAuthenticateResult);
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate5() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterResponse());

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert that nothing has changed
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof TesterResponse);
    assertEquals(200, response.getStatus());
    assertEquals(200, ((TesterResponse) response2).getStatus());
    assertFalse(((TesterResponse) response2).isAppCommitted());
    assertFalse(((TesterResponse) response2).isError());
    assertFalse(((TesterResponse) response2).isErrorReportRequired());
    assertFalse(((TesterResponse) response2).isSuspended());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate6() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    boolean actualDoAuthenticateResult = basicAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(200, response.getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isError());
    assertFalse(((Response) response2).isErrorReportRequired());
    assertFalse(((Response) response2).isSuspended());
    assertTrue(headerNames.isEmpty());
    assertTrue(actualDoAuthenticateResult);
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenBasicAuthenticatorContainerIsStandardContext() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, response.getStatus());
    assertEquals(401, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link org.apache.coyote.Request} (default constructor) RemoteUserNeedsAuthorization is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenTrue_whenRequestRemoteUserNeedsAuthorizationIsTrue() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setRemoteUserNeedsAuthorization(true);
    Request request = new Request(new Connector(), coyoteRequest);

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, response.getStatus());
    assertEquals(401, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BasicAuthenticator#getAuthMethod()}
   *   <li>{@link BasicAuthenticator#getCharset()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    String actualAuthMethod = basicAuthenticator.getAuthMethod();

    // Assert
    assertEquals("BASIC", actualAuthMethod);
    assertEquals("UTF-8", basicAuthenticator.getCharset());
  }

  /**
   * Test {@link BasicAuthenticator#isPreemptiveAuthPossible(Request)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicAuthenticator#isPreemptiveAuthPossible(Request)}
   */
  @Test
  public void testIsPreemptiveAuthPossible_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(basicAuthenticator.isPreemptiveAuthPossible(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test new {@link BasicAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BasicAuthenticator}
   */
  @Test
  public void testNewBasicAuthenticator() {
    // Arrange and Act
    BasicAuthenticator actualBasicAuthenticator = new BasicAuthenticator();

    // Assert
    assertEquals("BASIC", actualBasicAuthenticator.getAuthMethod());
    assertEquals("Catalina", actualBasicAuthenticator.getDomain());
    assertEquals("NEW", actualBasicAuthenticator.getStateName());
    assertEquals("SHA1PRNG", actualBasicAuthenticator.getSecureRandomAlgorithm());
    assertEquals("UTF-8", actualBasicAuthenticator.getCharset());
    assertEquals("never", actualBasicAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualBasicAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualBasicAuthenticator.getSecureRandomClass());
    assertNull(actualBasicAuthenticator.getSecureRandomProvider());
    assertNull(actualBasicAuthenticator.getDomainInternal());
    assertNull(actualBasicAuthenticator.getObjectName());
    assertNull(actualBasicAuthenticator.getContainer());
    assertNull(actualBasicAuthenticator.getNext());
    assertNull(actualBasicAuthenticator.sso);
    assertNull(actualBasicAuthenticator.sessionIdGenerator);
    assertEquals(0, actualBasicAuthenticator.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualBasicAuthenticator.getState());
    assertFalse(actualBasicAuthenticator.getAlwaysUseSession());
    assertFalse(actualBasicAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualBasicAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualBasicAuthenticator.getCache());
    assertTrue(actualBasicAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualBasicAuthenticator.getDisableProxyCaching());
    assertTrue(actualBasicAuthenticator.getThrowOnFailure());
    assertTrue(actualBasicAuthenticator.isAsyncSupported());
  }
}
