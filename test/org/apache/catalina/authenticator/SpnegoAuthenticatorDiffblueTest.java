package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.tomcat.unittest.TesterResponse;
import org.junit.Test;

public class SpnegoAuthenticatorDiffblueTest {
  /**
   * Test {@link SpnegoAuthenticator#getNoKeepAliveUserAgents()}.
   * <ul>
   *   <li>Given {@link SpnegoAuthenticator} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#getNoKeepAliveUserAgents()}
   */
  @Test
  public void testGetNoKeepAliveUserAgents_givenSpnegoAuthenticator_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SpnegoAuthenticator()).getNoKeepAliveUserAgents());
  }

  /**
   * Test {@link SpnegoAuthenticator#getNoKeepAliveUserAgents()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#getNoKeepAliveUserAgents()}
   */
  @Test
  public void testGetNoKeepAliveUserAgents_thenReturnFoo() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    spnegoAuthenticator.setNoKeepAliveUserAgents("foo");

    // Act and Assert
    assertEquals("foo", spnegoAuthenticator.getNoKeepAliveUserAgents());
  }

  /**
   * Test {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}
   */
  @Test
  public void testSetNoKeepAliveUserAgents() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();

    // Act
    spnegoAuthenticator.setNoKeepAliveUserAgents("No Keep Alive User Agents");

    // Assert
    assertEquals("No Keep Alive User Agents", spnegoAuthenticator.getNoKeepAliveUserAgents());
  }

  /**
   * Test {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}
   */
  @Test
  public void testSetNoKeepAliveUserAgents_whenEmptyString() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();

    // Act
    spnegoAuthenticator.setNoKeepAliveUserAgents("");

    // Assert that nothing has changed
    assertNull(spnegoAuthenticator.getNoKeepAliveUserAgents());
  }

  /**
   * Test {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#setNoKeepAliveUserAgents(String)}
   */
  @Test
  public void testSetNoKeepAliveUserAgents_whenNull() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();

    // Act
    spnegoAuthenticator.setNoKeepAliveUserAgents(null);

    // Assert that nothing has changed
    assertNull(spnegoAuthenticator.getNoKeepAliveUserAgents());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SpnegoAuthenticator#setApplyJava8u40Fix(boolean)}
   *   <li>{@link SpnegoAuthenticator#setLoginConfigName(String)}
   *   <li>{@link SpnegoAuthenticator#setStoreDelegatedCredential(boolean)}
   *   <li>{@link SpnegoAuthenticator#getApplyJava8u40Fix()}
   *   <li>{@link SpnegoAuthenticator#getAuthMethod()}
   *   <li>{@link SpnegoAuthenticator#getLoginConfigName()}
   *   <li>{@link SpnegoAuthenticator#isStoreDelegatedCredential()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();

    // Act
    spnegoAuthenticator.setApplyJava8u40Fix(true);
    spnegoAuthenticator.setLoginConfigName("Login Config Name");
    spnegoAuthenticator.setStoreDelegatedCredential(true);
    boolean actualApplyJava8u40Fix = spnegoAuthenticator.getApplyJava8u40Fix();
    String actualAuthMethod = spnegoAuthenticator.getAuthMethod();
    String actualLoginConfigName = spnegoAuthenticator.getLoginConfigName();

    // Assert
    assertEquals("Login Config Name", actualLoginConfigName);
    assertTrue(actualApplyJava8u40Fix);
    assertTrue(spnegoAuthenticator.isStoreDelegatedCredential());
    assertEquals(Constants.SPNEGO_METHOD, actualAuthMethod);
  }

  /**
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    spnegoAuthenticator.doAuthenticate(request, response);

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
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate2() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader(Constants.REQ_SSOID_NOTE, "42");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    spnegoAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(1));
    assertEquals(401, response.getStatus());
    assertEquals(401, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
    assertEquals(Constants.REQ_SSOID_NOTE, ((List<String>) headerNames).get(0));
  }

  /**
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate3() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    boolean actualDoAuthenticateResult = spnegoAuthenticator.doAuthenticate(request, response);

    // Assert
    assertNull(response.getHeaderNames());
    assertEquals(0, response.getStatus());
    assertFalse(actualDoAuthenticateResult);
  }

  /**
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate4() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterResponse());

    // Act
    spnegoAuthenticator.doAuthenticate(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof TesterResponse);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(200, response.getStatus());
    assertEquals(200, ((TesterResponse) response2).getStatus());
    assertFalse(((TesterResponse) response2).isAppCommitted());
    assertFalse(((TesterResponse) response2).isError());
    assertFalse(((TesterResponse) response2).isErrorReportRequired());
    assertFalse(((TesterResponse) response2).isSuspended());
  }

  /**
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate5() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    boolean actualDoAuthenticateResult = spnegoAuthenticator.doAuthenticate(request, response);

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
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link org.apache.coyote.Request} (default constructor) RemoteUserNeedsAuthorization is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenTrue_whenRequestRemoteUserNeedsAuthorizationIsTrue() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();

    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setRemoteUserNeedsAuthorization(true);
    Request request = new Request(new Connector(), coyoteRequest);

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    spnegoAuthenticator.doAuthenticate(request, response);

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
   * Test {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code WWW-Authenticate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenWwwAuthenticate() throws IOException {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("WWW-Authenticate", "42");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    spnegoAuthenticator.doAuthenticate(request, response);

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
   * Test {@link SpnegoAuthenticator#isPreemptiveAuthPossible(Request)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoAuthenticator#isPreemptiveAuthPossible(Request)}
   */
  @Test
  public void testIsPreemptiveAuthPossible_thenReturnFalse() {
    // Arrange
    SpnegoAuthenticator spnegoAuthenticator = new SpnegoAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(spnegoAuthenticator.isPreemptiveAuthPossible(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test new {@link SpnegoAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SpnegoAuthenticator}
   */
  @Test
  public void testNewSpnegoAuthenticator() {
    // Arrange and Act
    SpnegoAuthenticator actualSpnegoAuthenticator = new SpnegoAuthenticator();

    // Assert
    assertEquals("Catalina", actualSpnegoAuthenticator.getDomain());
    assertEquals("NEW", actualSpnegoAuthenticator.getStateName());
    assertEquals("SHA1PRNG", actualSpnegoAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualSpnegoAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualSpnegoAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualSpnegoAuthenticator.getSecureRandomClass());
    assertNull(actualSpnegoAuthenticator.getSecureRandomProvider());
    assertNull(actualSpnegoAuthenticator.getNoKeepAliveUserAgents());
    assertNull(actualSpnegoAuthenticator.getDomainInternal());
    assertNull(actualSpnegoAuthenticator.getObjectName());
    assertNull(actualSpnegoAuthenticator.getContainer());
    assertNull(actualSpnegoAuthenticator.getNext());
    assertNull(actualSpnegoAuthenticator.sso);
    assertNull(actualSpnegoAuthenticator.sessionIdGenerator);
    assertEquals(0, actualSpnegoAuthenticator.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualSpnegoAuthenticator.getState());
    assertFalse(actualSpnegoAuthenticator.getAlwaysUseSession());
    assertFalse(actualSpnegoAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualSpnegoAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualSpnegoAuthenticator.getCache());
    assertTrue(actualSpnegoAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualSpnegoAuthenticator.getDisableProxyCaching());
    assertTrue(actualSpnegoAuthenticator.getApplyJava8u40Fix());
    assertTrue(actualSpnegoAuthenticator.isStoreDelegatedCredential());
    assertTrue(actualSpnegoAuthenticator.getThrowOnFailure());
    assertTrue(actualSpnegoAuthenticator.isAsyncSupported());
    assertEquals(Constants.DEFAULT_LOGIN_MODULE_NAME, actualSpnegoAuthenticator.getLoginConfigName());
    assertEquals(Constants.SPNEGO_METHOD, actualSpnegoAuthenticator.getAuthMethod());
  }
}
