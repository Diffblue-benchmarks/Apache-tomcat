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
import java.util.ArrayList;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.junit.Test;

public class FormAuthenticatorDiffblueTest {
  /**
   * Test {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link GenericPrincipal#GenericPrincipal(String)} with name is {@link Constants#REQ_SSOID_NOTE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenGenericPrincipalWithNameIsReq_ssoid_note() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new GenericPrincipal(Constants.REQ_SSOID_NOTE));

    // Act and Assert
    assertTrue(formAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))));
  }

  /**
   * Test {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenUserPrincipalWithNameIsPrincipal() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));

    // Act and Assert
    assertTrue(formAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))));
  }

  /**
   * Test {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link UserPrincipal#UserPrincipal(String)} with name is {@code userPrincipal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FormAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenUserPrincipalWithNameIsUserPrincipal() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    ArrayList<String> roles = new ArrayList<>();
    request.setUserPrincipal(new GenericPrincipal(Constants.REQ_SSOID_NOTE, roles, new UserPrincipal("userPrincipal")));

    // Act and Assert
    assertTrue(formAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FormAuthenticator#setAuthenticationSessionTimeout(int)}
   *   <li>{@link FormAuthenticator#setCharacterEncoding(String)}
   *   <li>{@link FormAuthenticator#setLandingPage(String)}
   *   <li>{@link FormAuthenticator#getAuthMethod()}
   *   <li>{@link FormAuthenticator#getAuthenticationSessionTimeout()}
   *   <li>{@link FormAuthenticator#getCharacterEncoding()}
   *   <li>{@link FormAuthenticator#getLandingPage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();

    // Act
    formAuthenticator.setAuthenticationSessionTimeout(10);
    formAuthenticator.setCharacterEncoding("UTF-8");
    formAuthenticator.setLandingPage("Landing Page");
    String actualAuthMethod = formAuthenticator.getAuthMethod();
    int actualAuthenticationSessionTimeout = formAuthenticator.getAuthenticationSessionTimeout();
    String actualCharacterEncoding = formAuthenticator.getCharacterEncoding();

    // Assert
    assertEquals("FORM", actualAuthMethod);
    assertEquals("Landing Page", formAuthenticator.getLandingPage());
    assertEquals("UTF-8", actualCharacterEncoding);
    assertEquals(10, actualAuthenticationSessionTimeout);
  }

  /**
   * Test {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToLoginPage() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    formAuthenticator.forwardToLoginPage(request, response, new LoginConfig());

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("No login page was defined for FORM authentication in context [null]",
        ((Response) response2).getMessage());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals("No login page was defined for FORM authentication in context [null]", coyoteResponse.getMessage());
    assertEquals(500, response3.getStatus());
    assertEquals(500, ((Response) response2).getStatus());
    assertEquals(500, coyoteResponse.getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToLoginPage2() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    formAuthenticator.forwardToLoginPage(request, response, new LoginConfig());

    // Assert that nothing has changed
    assertEquals(0, response.getStatus());
    assertFalse(response.isCommitted());
  }

  /**
   * Test {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToLoginPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToLoginPage3() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new ResponseFacade(new Response(new org.apache.coyote.Response())));

    // Act
    formAuthenticator.forwardToLoginPage(request, response, new LoginConfig());

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(500, response.getStatus());
    assertEquals(500, ((ResponseFacade) response2).getStatus());
    assertTrue(response.isCommitted());
  }

  /**
   * Test {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToErrorPage() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    formAuthenticator.forwardToErrorPage(request, response, new LoginConfig());

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("No error page was defined for FORM authentication in context [null]",
        ((Response) response2).getMessage());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals("No error page was defined for FORM authentication in context [null]", coyoteResponse.getMessage());
    assertEquals(500, response3.getStatus());
    assertEquals(500, ((Response) response2).getStatus());
    assertEquals(500, coyoteResponse.getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToErrorPage2() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    formAuthenticator.forwardToErrorPage(request, response, new LoginConfig());

    // Assert that nothing has changed
    assertEquals(0, response.getStatus());
    assertFalse(response.isCommitted());
  }

  /**
   * Test {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}.
   * <p>
   * Method under test: {@link FormAuthenticator#forwardToErrorPage(Request, HttpServletResponse, LoginConfig)}
   */
  @Test
  public void testForwardToErrorPage3() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    formAuthenticator.setContainer(new StandardContext());
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new ResponseFacade(new Response(new org.apache.coyote.Response())));

    // Act
    formAuthenticator.forwardToErrorPage(request, response, new LoginConfig());

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(500, response.getStatus());
    assertEquals(500, ((ResponseFacade) response2).getStatus());
    assertTrue(response.isCommitted());
  }

  /**
   * Test {@link FormAuthenticator#restoreRequest(Request, Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FormAuthenticator#restoreRequest(Request, Session)}
   */
  @Test
  public void testRestoreRequest_whenDeltaSession_thenReturnFalse() throws IOException {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertFalse(formAuthenticator.restoreRequest(request, new DeltaSession()));
  }

  /**
   * Test {@link FormAuthenticator#savedRequestURL(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FormAuthenticator#savedRequestURL(Session)}
   */
  @Test
  public void testSavedRequestURL_whenDeltaSession_thenReturnNull() {
    // Arrange
    FormAuthenticator formAuthenticator = new FormAuthenticator();

    // Act and Assert
    assertNull(formAuthenticator.savedRequestURL(new DeltaSession()));
  }

  /**
   * Test new {@link FormAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FormAuthenticator}
   */
  @Test
  public void testNewFormAuthenticator() {
    // Arrange and Act
    FormAuthenticator actualFormAuthenticator = new FormAuthenticator();

    // Assert
    assertEquals("Catalina", actualFormAuthenticator.getDomain());
    assertEquals("FORM", actualFormAuthenticator.getAuthMethod());
    assertEquals("NEW", actualFormAuthenticator.getStateName());
    assertEquals("SHA1PRNG", actualFormAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualFormAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualFormAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualFormAuthenticator.getSecureRandomClass());
    assertNull(actualFormAuthenticator.getSecureRandomProvider());
    assertNull(actualFormAuthenticator.getCharacterEncoding());
    assertNull(actualFormAuthenticator.getLandingPage());
    assertNull(actualFormAuthenticator.getDomainInternal());
    assertNull(actualFormAuthenticator.getObjectName());
    assertNull(actualFormAuthenticator.getContainer());
    assertNull(actualFormAuthenticator.getNext());
    assertNull(actualFormAuthenticator.sso);
    assertNull(actualFormAuthenticator.sessionIdGenerator);
    assertEquals(0, actualFormAuthenticator.findLifecycleListeners().length);
    assertEquals(120, actualFormAuthenticator.getAuthenticationSessionTimeout());
    assertEquals(LifecycleState.NEW, actualFormAuthenticator.getState());
    assertFalse(actualFormAuthenticator.getAlwaysUseSession());
    assertFalse(actualFormAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualFormAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualFormAuthenticator.getCache());
    assertTrue(actualFormAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualFormAuthenticator.getDisableProxyCaching());
    assertTrue(actualFormAuthenticator.getThrowOnFailure());
    assertTrue(actualFormAuthenticator.isAsyncSupported());
  }
}
