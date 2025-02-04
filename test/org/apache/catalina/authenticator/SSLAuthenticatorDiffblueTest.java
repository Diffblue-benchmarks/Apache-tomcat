package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.coyote.Response;
import org.junit.Test;

public class SSLAuthenticatorDiffblueTest {
  /**
   * Test {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link GenericPrincipal#GenericPrincipal(String)} with name is {@link Constants#REQ_SSOID_NOTE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenGenericPrincipalWithNameIsReq_ssoid_note() throws IOException {
    // Arrange
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new GenericPrincipal(Constants.REQ_SSOID_NOTE));

    // Act and Assert
    assertTrue(sslAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response()))));
  }

  /**
   * Test {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenUserPrincipalWithNameIsPrincipal() throws IOException {
    // Arrange
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setUserPrincipal(new UserPrincipal("principal"));

    // Act and Assert
    assertTrue(sslAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response()))));
  }

  /**
   * Test {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link UserPrincipal#UserPrincipal(String)} with name is {@code userPrincipal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSLAuthenticator#doAuthenticate(Request, HttpServletResponse)}
   */
  @Test
  public void testDoAuthenticate_givenUserPrincipalWithNameIsUserPrincipal() throws IOException {
    // Arrange
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    ArrayList<String> roles = new ArrayList<>();
    request.setUserPrincipal(new GenericPrincipal(Constants.REQ_SSOID_NOTE, roles, new UserPrincipal("userPrincipal")));

    // Act and Assert
    assertTrue(sslAuthenticator.doAuthenticate(request,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response()))));
  }

  /**
   * Test {@link SSLAuthenticator#getAuthMethod()}.
   * <p>
   * Method under test: {@link SSLAuthenticator#getAuthMethod()}
   */
  @Test
  public void testGetAuthMethod() {
    // Arrange, Act and Assert
    assertEquals("CLIENT_CERT", (new SSLAuthenticator()).getAuthMethod());
  }

  /**
   * Test {@link SSLAuthenticator#isPreemptiveAuthPossible(Request)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSLAuthenticator#isPreemptiveAuthPossible(Request)}
   */
  @Test
  public void testIsPreemptiveAuthPossible_thenReturnFalse() {
    // Arrange
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(sslAuthenticator.isPreemptiveAuthPossible(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link SSLAuthenticator#getRequestCertificates(Request)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSLAuthenticator#getRequestCertificates(Request)}
   */
  @Test
  public void testGetRequestCertificates_thenReturnNull() throws IllegalStateException {
    // Arrange
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    Connector connector = new Connector();

    // Act and Assert
    assertNull(sslAuthenticator.getRequestCertificates(new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test new {@link SSLAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SSLAuthenticator}
   */
  @Test
  public void testNewSSLAuthenticator() {
    // Arrange and Act
    SSLAuthenticator actualSslAuthenticator = new SSLAuthenticator();

    // Assert
    assertEquals("CLIENT_CERT", actualSslAuthenticator.getAuthMethod());
    assertEquals("Catalina", actualSslAuthenticator.getDomain());
    assertEquals("NEW", actualSslAuthenticator.getStateName());
    assertEquals("SHA1PRNG", actualSslAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualSslAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualSslAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualSslAuthenticator.getSecureRandomClass());
    assertNull(actualSslAuthenticator.getSecureRandomProvider());
    assertNull(actualSslAuthenticator.getDomainInternal());
    assertNull(actualSslAuthenticator.getObjectName());
    assertNull(actualSslAuthenticator.getContainer());
    assertNull(actualSslAuthenticator.getNext());
    assertNull(actualSslAuthenticator.sso);
    assertNull(actualSslAuthenticator.sessionIdGenerator);
    assertEquals(0, actualSslAuthenticator.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualSslAuthenticator.getState());
    assertFalse(actualSslAuthenticator.getAlwaysUseSession());
    assertFalse(actualSslAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualSslAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualSslAuthenticator.getCache());
    assertTrue(actualSslAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualSslAuthenticator.getDisableProxyCaching());
    assertTrue(actualSslAuthenticator.getThrowOnFailure());
    assertTrue(actualSslAuthenticator.isAsyncSupported());
  }
}
