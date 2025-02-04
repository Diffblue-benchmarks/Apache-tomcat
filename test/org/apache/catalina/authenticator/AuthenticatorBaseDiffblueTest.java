package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.tomcat.unittest.TesterResponse;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.junit.Test;

public class AuthenticatorBaseDiffblueTest {
  /**
   * Test {@link AuthenticatorBase#getRealmName(Context)}.
   * <ul>
   *   <li>Given {@link LoginConfig#LoginConfig()} RealmName is {@code Context}.</li>
   *   <li>Then return {@code Context}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getRealmName(Context)}
   */
  @Test
  public void testGetRealmName_givenLoginConfigRealmNameIsContext_thenReturnContext() {
    // Arrange
    LoginConfig config = new LoginConfig();
    config.setRealmName("Context");

    StandardContext context = new StandardContext();
    context.setLoginConfig(config);

    // Act and Assert
    assertEquals("Context", AuthenticatorBase.getRealmName(context));
  }

  /**
   * Test {@link AuthenticatorBase#getRealmName(Context)}.
   * <ul>
   *   <li>Given {@link LoginConfig#LoginConfig()}.</li>
   *   <li>When {@link StandardContext} (default constructor) LoginConfig is {@link LoginConfig#LoginConfig()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getRealmName(Context)}
   */
  @Test
  public void testGetRealmName_givenLoginConfig_whenStandardContextLoginConfigIsLoginConfig() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setLoginConfig(new LoginConfig());

    // Act and Assert
    assertEquals("Authentication required", AuthenticatorBase.getRealmName(context));
  }

  /**
   * Test {@link AuthenticatorBase#getRealmName(Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code Authentication required}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getRealmName(Context)}
   */
  @Test
  public void testGetRealmName_whenNull_thenReturnAuthenticationRequired() {
    // Arrange, Act and Assert
    assertEquals("Authentication required", AuthenticatorBase.getRealmName(null));
  }

  /**
   * Test {@link AuthenticatorBase#getRealmName(Context)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code Authentication required}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getRealmName(Context)}
   */
  @Test
  public void testGetRealmName_whenStandardContext_thenReturnAuthenticationRequired() {
    // Arrange, Act and Assert
    assertEquals("Authentication required", AuthenticatorBase.getRealmName(new StandardContext()));
  }

  /**
   * Test {@link AuthenticatorBase#getAllowCorsPreflight()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getAllowCorsPreflight()}
   */
  @Test
  public void testGetAllowCorsPreflight() {
    // Arrange, Act and Assert
    assertEquals("never", (new BasicAuthenticator()).getAllowCorsPreflight());
  }

  /**
   * Test {@link AuthenticatorBase#getAlwaysUseSession()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getAlwaysUseSession()}
   */
  @Test
  public void testGetAlwaysUseSession_givenBasicAuthenticator_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BasicAuthenticator()).getAlwaysUseSession());
  }

  /**
   * Test {@link AuthenticatorBase#getAlwaysUseSession()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getAlwaysUseSession()}
   */
  @Test
  public void testGetAlwaysUseSession_thenReturnTrue() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setAlwaysUseSession(true);

    // Act and Assert
    assertTrue(basicAuthenticator.getAlwaysUseSession());
  }

  /**
   * Test {@link AuthenticatorBase#setAlwaysUseSession(boolean)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setAlwaysUseSession(boolean)}
   */
  @Test
  public void testSetAlwaysUseSession() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setAlwaysUseSession(true);

    // Assert
    assertTrue(basicAuthenticator.getAlwaysUseSession());
  }

  /**
   * Test {@link AuthenticatorBase#getCache()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Cache is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getCache()}
   */
  @Test
  public void testGetCache_givenBasicAuthenticatorCacheIsFalse_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setCache(false);

    // Act and Assert
    assertFalse(basicAuthenticator.getCache());
  }

  /**
   * Test {@link AuthenticatorBase#getCache()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getCache()}
   */
  @Test
  public void testGetCache_givenBasicAuthenticator_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BasicAuthenticator()).getCache());
  }

  /**
   * Test {@link AuthenticatorBase#getContainer()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getContainer()}
   */
  @Test
  public void testGetContainer() {
    // Arrange, Act and Assert
    assertNull((new BasicAuthenticator()).getContainer());
  }

  /**
   * Test {@link AuthenticatorBase#setContainer(Container)}.
   * <ul>
   *   <li>Then {@link BasicAuthenticator} (default constructor) DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#setContainer(Container)}
   */
  @Test
  public void testSetContainer_thenBasicAuthenticatorDomainInternalIsCatalina() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    StandardContext container = new StandardContext();

    // Act
    basicAuthenticator.setContainer(container);

    // Assert
    assertEquals("Catalina", basicAuthenticator.getDomainInternal());
    assertSame(container, basicAuthenticator.getContainer());
  }

  /**
   * Test {@link AuthenticatorBase#setContainer(Container)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) DomainInternal is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#setContainer(Container)}
   */
  @Test
  public void testSetContainer_whenNull_thenBasicAuthenticatorDomainInternalIsNull() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setContainer(null);

    // Assert that nothing has changed
    assertNull(basicAuthenticator.getDomainInternal());
    assertNull(basicAuthenticator.getContainer());
  }

  /**
   * Test {@link AuthenticatorBase#setContainer(Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#setContainer(Container)}
   */
  @Test
  public void testSetContainer_whenStandardEngine_thenThrowIllegalArgumentException() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> basicAuthenticator.setContainer(new StandardEngine()));
  }

  /**
   * Test {@link AuthenticatorBase#getDisableProxyCaching()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getDisableProxyCaching()}
   */
  @Test
  public void testGetDisableProxyCaching_givenBasicAuthenticator_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BasicAuthenticator()).getDisableProxyCaching());
  }

  /**
   * Test {@link AuthenticatorBase#getDisableProxyCaching()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getDisableProxyCaching()}
   */
  @Test
  public void testGetDisableProxyCaching_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setDisableProxyCaching(false);

    // Act and Assert
    assertFalse(basicAuthenticator.getDisableProxyCaching());
  }

  /**
   * Test {@link AuthenticatorBase#getSecurePagesWithPragma()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getSecurePagesWithPragma()}
   */
  @Test
  public void testGetSecurePagesWithPragma_givenBasicAuthenticator_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BasicAuthenticator()).getSecurePagesWithPragma());
  }

  /**
   * Test {@link AuthenticatorBase#getSecurePagesWithPragma()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getSecurePagesWithPragma()}
   */
  @Test
  public void testGetSecurePagesWithPragma_thenReturnTrue() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setSecurePagesWithPragma(true);

    // Act and Assert
    assertTrue(basicAuthenticator.getSecurePagesWithPragma());
  }

  /**
   * Test {@link AuthenticatorBase#setSecurePagesWithPragma(boolean)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setSecurePagesWithPragma(boolean)}
   */
  @Test
  public void testSetSecurePagesWithPragma() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setSecurePagesWithPragma(true);

    // Assert
    assertTrue(basicAuthenticator.getSecurePagesWithPragma());
  }

  /**
   * Test {@link AuthenticatorBase#getChangeSessionIdOnAuthentication()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getChangeSessionIdOnAuthentication()}
   */
  @Test
  public void testGetChangeSessionIdOnAuthentication_givenBasicAuthenticator_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BasicAuthenticator()).getChangeSessionIdOnAuthentication());
  }

  /**
   * Test {@link AuthenticatorBase#getChangeSessionIdOnAuthentication()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#getChangeSessionIdOnAuthentication()}
   */
  @Test
  public void testGetChangeSessionIdOnAuthentication_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setChangeSessionIdOnAuthentication(false);

    // Act and Assert
    assertFalse(basicAuthenticator.getChangeSessionIdOnAuthentication());
  }

  /**
   * Test {@link AuthenticatorBase#getSecureRandomClass()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getSecureRandomClass()}
   */
  @Test
  public void testGetSecureRandomClass() {
    // Arrange, Act and Assert
    assertNull((new BasicAuthenticator()).getSecureRandomClass());
  }

  /**
   * Test {@link AuthenticatorBase#setSecureRandomClass(String)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setSecureRandomClass(String)}
   */
  @Test
  public void testSetSecureRandomClass() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setSecureRandomClass("Secure Random Class");

    // Assert
    assertEquals("Secure Random Class", basicAuthenticator.getSecureRandomClass());
  }

  /**
   * Test {@link AuthenticatorBase#getSecureRandomAlgorithm()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getSecureRandomAlgorithm()}
   */
  @Test
  public void testGetSecureRandomAlgorithm() {
    // Arrange, Act and Assert
    assertEquals("SHA1PRNG", (new BasicAuthenticator()).getSecureRandomAlgorithm());
  }

  /**
   * Test {@link AuthenticatorBase#setSecureRandomAlgorithm(String)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setSecureRandomAlgorithm(String)}
   */
  @Test
  public void testSetSecureRandomAlgorithm() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setSecureRandomAlgorithm("Secure Random Algorithm");

    // Assert
    assertEquals("Secure Random Algorithm", basicAuthenticator.getSecureRandomAlgorithm());
  }

  /**
   * Test {@link AuthenticatorBase#getSecureRandomProvider()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getSecureRandomProvider()}
   */
  @Test
  public void testGetSecureRandomProvider() {
    // Arrange, Act and Assert
    assertNull((new BasicAuthenticator()).getSecureRandomProvider());
  }

  /**
   * Test {@link AuthenticatorBase#setSecureRandomProvider(String)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setSecureRandomProvider(String)}
   */
  @Test
  public void testSetSecureRandomProvider() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setSecureRandomProvider("Secure Random Provider");

    // Assert
    assertEquals("Secure Random Provider", basicAuthenticator.getSecureRandomProvider());
  }

  /**
   * Test {@link AuthenticatorBase#getJaspicCallbackHandlerClass()}.
   * <p>
   * Method under test: {@link AuthenticatorBase#getJaspicCallbackHandlerClass()}
   */
  @Test
  public void testGetJaspicCallbackHandlerClass() {
    // Arrange, Act and Assert
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        (new BasicAuthenticator()).getJaspicCallbackHandlerClass());
  }

  /**
   * Test {@link AuthenticatorBase#setJaspicCallbackHandlerClass(String)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setJaspicCallbackHandlerClass(String)}
   */
  @Test
  public void testSetJaspicCallbackHandlerClass() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setJaspicCallbackHandlerClass("Jaspic Callback Handler Class");

    // Assert
    assertEquals("Jaspic Callback Handler Class", basicAuthenticator.getJaspicCallbackHandlerClass());
  }

  /**
   * Test {@link AuthenticatorBase#isSendAuthInfoResponseHeaders()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#isSendAuthInfoResponseHeaders()}
   */
  @Test
  public void testIsSendAuthInfoResponseHeaders_givenBasicAuthenticator_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BasicAuthenticator()).isSendAuthInfoResponseHeaders());
  }

  /**
   * Test {@link AuthenticatorBase#isSendAuthInfoResponseHeaders()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#isSendAuthInfoResponseHeaders()}
   */
  @Test
  public void testIsSendAuthInfoResponseHeaders_thenReturnTrue() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setSendAuthInfoResponseHeaders(true);

    // Act and Assert
    assertTrue(basicAuthenticator.isSendAuthInfoResponseHeaders());
  }

  /**
   * Test {@link AuthenticatorBase#setSendAuthInfoResponseHeaders(boolean)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#setSendAuthInfoResponseHeaders(boolean)}
   */
  @Test
  public void testSetSendAuthInfoResponseHeaders() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setSendAuthInfoResponseHeaders(true);

    // Assert
    assertTrue(basicAuthenticator.isSendAuthInfoResponseHeaders());
  }

  /**
   * Test {@link AuthenticatorBase#allowCorsPreflightBypass(Request)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#allowCorsPreflightBypass(Request)}
   */
  @Test
  public void testAllowCorsPreflightBypass() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(basicAuthenticator.allowCorsPreflightBypass(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert
    Collection<String> headerNames = httpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, httpResponse.getStatus());
    assertEquals(401, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate2() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("en", "42");
    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert
    Collection<String> headerNames = httpResponse.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(1));
    assertEquals("en", ((List<String>) headerNames).get(0));
    assertEquals(2, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate3() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("WWW-Authenticate", "42");
    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert that nothing has changed
    Collection<String> headerNames = httpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate4() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(new TesterResponse());

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert that nothing has changed
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof TesterResponse);
    assertEquals(200, httpResponse.getStatus());
    assertEquals(200, ((TesterResponse) response).getStatus());
    assertFalse(((TesterResponse) response).isAppCommitted());
    assertFalse(((TesterResponse) response).isError());
    assertFalse(((TesterResponse) response).isErrorReportRequired());
    assertFalse(((TesterResponse) response).isSuspended());
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) addLifecycleListener {@link AprLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate_givenBasicAuthenticatorAddLifecycleListenerAprLifecycleListener() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.addLifecycleListener(new AprLifecycleListener());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert
    Collection<String> headerNames = httpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, httpResponse.getStatus());
    assertEquals(401, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#authenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testAuthenticate_givenBasicAuthenticatorContainerIsStandardContext() throws IOException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setContainer(new StandardContext());
    basicAuthenticator.addLifecycleListener(new AprLifecycleListener());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    basicAuthenticator.authenticate(request, httpResponse);

    // Assert
    Collection<String> headerNames = httpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpResponse.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("WWW-Authenticate", ((List<String>) headerNames).get(0));
    assertEquals(401, httpResponse.getStatus());
    assertEquals(401, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link AuthenticatorBase#isContinuationRequired(Request)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#isContinuationRequired(Request)}
   */
  @Test
  public void testIsContinuationRequired() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(basicAuthenticator.isContinuationRequired(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}
   */
  @Test
  public void testCheckForCachedAuthentication_givenTrue() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
    coyoteRequest.setRemoteUserNeedsAuthorization(true);
    Request request = new Request(new Connector(), coyoteRequest);

    // Act and Assert
    assertFalse(basicAuthenticator.checkForCachedAuthentication(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));
  }

  /**
   * Test {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}
   */
  @Test
  public void testCheckForCachedAuthentication_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertFalse(basicAuthenticator.checkForCachedAuthentication(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));
  }

  /**
   * Test {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}
   */
  @Test
  public void testCheckForCachedAuthentication_thenReturnTrue() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));

    // Act and Assert
    assertTrue(basicAuthenticator.checkForCachedAuthentication(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));
  }

  /**
   * Test {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#checkForCachedAuthentication(Request, HttpServletResponse, boolean)}
   */
  @Test
  public void testCheckForCachedAuthentication_whenFalse_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertFalse(basicAuthenticator.checkForCachedAuthentication(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), false));
  }

  /**
   * Test {@link AuthenticatorBase#reauthenticateFromSSO(String, Request)}.
   * <p>
   * Method under test: {@link AuthenticatorBase#reauthenticateFromSSO(String, Request)}
   */
  @Test
  public void testReauthenticateFromSSO() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(
        basicAuthenticator.reauthenticateFromSSO("42", new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link AuthenticatorBase#isPreemptiveAuthPossible(Request)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#isPreemptiveAuthPossible(Request)}
   */
  @Test
  public void testIsPreemptiveAuthPossible_givenBasicAuthenticator() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(basicAuthenticator.isPreemptiveAuthPossible(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link AuthenticatorBase#isPreemptiveAuthPossible(Request)}.
   * <ul>
   *   <li>Given {@link FormAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthenticatorBase#isPreemptiveAuthPossible(Request)}
   */
  @Test
  public void testIsPreemptiveAuthPossible_givenFormAuthenticator() {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(formAuthenticator.isPreemptiveAuthPossible(new Request(connector, new org.apache.coyote.Request())));
  }
}
