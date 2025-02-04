package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpServletResponse;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;
import java.util.List;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.realm.RealmBase.AllRolesMode;
import org.apache.catalina.startup.FailedContext;
import org.apache.juli.logging.Log;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.junit.Test;

public class RealmBaseDiffblueTest {
  /**
   * Test AllRolesMode {@link AllRolesMode#equals(Object)}, and {@link AllRolesMode#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllRolesMode#equals(Object)}
   *   <li>{@link AllRolesMode#hashCode()}
   * </ul>
   */
  @Test
  public void testAllRolesModeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    AllRolesMode toModeResult = AllRolesMode.toMode("authOnly");
    AllRolesMode toModeResult2 = AllRolesMode.toMode("authOnly");

    // Act and Assert
    assertEquals(toModeResult, toModeResult2);
    int expectedHashCodeResult = toModeResult.hashCode();
    assertEquals(expectedHashCodeResult, toModeResult2.hashCode());
  }

  /**
   * Test AllRolesMode {@link AllRolesMode#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllRolesMode#equals(Object)}
   */
  @Test
  public void testAllRolesModeEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(AllRolesMode.toMode("authOnly"), 1);
  }

  /**
   * Test AllRolesMode {@link AllRolesMode#toMode(String)}.
   * <ul>
   *   <li>When {@code authOnly}.</li>
   *   <li>Then return toString is {@code authOnly}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllRolesMode#toMode(String)}
   */
  @Test
  public void testAllRolesModeToMode_whenAuthOnly_thenReturnToStringIsAuthOnly() {
    // Arrange, Act and Assert
    assertEquals("authOnly", AllRolesMode.toMode("authOnly").toString());
  }

  /**
   * Test AllRolesMode {@link AllRolesMode#toMode(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllRolesMode#toMode(String)}
   */
  @Test
  public void testAllRolesModeToMode_whenName_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> AllRolesMode.toMode("Name"));
  }

  /**
   * Test AllRolesMode {@link AllRolesMode#toMode(String)}.
   * <ul>
   *   <li>When {@code strictAuthOnly}.</li>
   *   <li>Then return toString is {@code strictAuthOnly}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllRolesMode#toMode(String)}
   */
  @Test
  public void testAllRolesModeToMode_whenStrictAuthOnly_thenReturnToStringIsStrictAuthOnly() {
    // Arrange, Act and Assert
    assertEquals("strictAuthOnly", AllRolesMode.toMode("strictAuthOnly").toString());
  }

  /**
   * Test AllRolesMode {@link AllRolesMode#toMode(String)}.
   * <ul>
   *   <li>When {@code strict}.</li>
   *   <li>Then return toString is {@code strict}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllRolesMode#toMode(String)}
   */
  @Test
  public void testAllRolesModeToMode_whenStrict_thenReturnToStringIsStrict() {
    // Arrange, Act and Assert
    assertEquals("strict", AllRolesMode.toMode("strict").toString());
  }

  /**
   * Test {@link RealmBase#getTransportGuaranteeRedirectStatus()}.
   * <p>
   * Method under test: {@link RealmBase#getTransportGuaranteeRedirectStatus()}
   */
  @Test
  public void testGetTransportGuaranteeRedirectStatus() {
    // Arrange, Act and Assert
    assertEquals(302, (new AuthenticatedUserRealm()).getTransportGuaranteeRedirectStatus());
  }

  /**
   * Test {@link RealmBase#setTransportGuaranteeRedirectStatus(int)}.
   * <p>
   * Method under test: {@link RealmBase#setTransportGuaranteeRedirectStatus(int)}
   */
  @Test
  public void testSetTransportGuaranteeRedirectStatus() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act
    authenticatedUserRealm.setTransportGuaranteeRedirectStatus(1);

    // Assert
    assertEquals(1, authenticatedUserRealm.getTransportGuaranteeRedirectStatus());
  }

  /**
   * Test {@link RealmBase#getCredentialHandler()}.
   * <p>
   * Method under test: {@link RealmBase#getCredentialHandler()}
   */
  @Test
  public void testGetCredentialHandler() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getCredentialHandler());
  }

  /**
   * Test {@link RealmBase#setCredentialHandler(CredentialHandler)}.
   * <p>
   * Method under test: {@link RealmBase#setCredentialHandler(CredentialHandler)}
   */
  @Test
  public void testSetCredentialHandler() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    MessageDigestCredentialHandler credentialHandler = new MessageDigestCredentialHandler();

    // Act
    authenticatedUserRealm.setCredentialHandler(credentialHandler);

    // Assert
    assertSame(credentialHandler, authenticatedUserRealm.getCredentialHandler());
  }

  /**
   * Test {@link RealmBase#getContainer()}.
   * <p>
   * Method under test: {@link RealmBase#getContainer()}
   */
  @Test
  public void testGetContainer() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getContainer());
  }

  /**
   * Test {@link RealmBase#setContainer(Container)}.
   * <p>
   * Method under test: {@link RealmBase#setContainer(Container)}
   */
  @Test
  public void testSetContainer() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    StandardContext container = new StandardContext();

    // Act
    authenticatedUserRealm.setContainer(container);

    // Assert
    assertEquals("Catalina", authenticatedUserRealm.getDomainInternal());
    assertEquals("Catalina", authenticatedUserRealm.getDomain());
    assertSame(container, authenticatedUserRealm.getContainer());
  }

  /**
   * Test {@link RealmBase#getAllRolesMode()}.
   * <p>
   * Method under test: {@link RealmBase#getAllRolesMode()}
   */
  @Test
  public void testGetAllRolesMode() {
    // Arrange, Act and Assert
    assertEquals("strict", (new AuthenticatedUserRealm()).getAllRolesMode());
  }

  /**
   * Test {@link RealmBase#getValidate()}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor) Validate is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getValidate()}
   */
  @Test
  public void testGetValidate_givenAuthenticatedUserRealmValidateIsFalse_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setValidate(false);

    // Act and Assert
    assertFalse(authenticatedUserRealm.getValidate());
  }

  /**
   * Test {@link RealmBase#getValidate()}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getValidate()}
   */
  @Test
  public void testGetValidate_givenAuthenticatedUserRealm_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AuthenticatedUserRealm()).getValidate());
  }

  /**
   * Test {@link RealmBase#getX509UsernameRetrieverClassName()}.
   * <p>
   * Method under test: {@link RealmBase#getX509UsernameRetrieverClassName()}
   */
  @Test
  public void testGetX509UsernameRetrieverClassName() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getX509UsernameRetrieverClassName());
  }

  /**
   * Test {@link RealmBase#setX509UsernameRetrieverClassName(String)}.
   * <p>
   * Method under test: {@link RealmBase#setX509UsernameRetrieverClassName(String)}
   */
  @Test
  public void testSetX509UsernameRetrieverClassName() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act
    authenticatedUserRealm.setX509UsernameRetrieverClassName("Class Name");

    // Assert
    assertEquals("Class Name", authenticatedUserRealm.getX509UsernameRetrieverClassName());
  }

  /**
   * Test {@link RealmBase#isStripRealmForGss()}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#isStripRealmForGss()}
   */
  @Test
  public void testIsStripRealmForGss_givenAuthenticatedUserRealm_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AuthenticatedUserRealm()).isStripRealmForGss());
  }

  /**
   * Test {@link RealmBase#isStripRealmForGss()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#isStripRealmForGss()}
   */
  @Test
  public void testIsStripRealmForGss_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setStripRealmForGss(false);

    // Act and Assert
    assertFalse(authenticatedUserRealm.isStripRealmForGss());
  }

  /**
   * Test {@link RealmBase#getUserAttributes()}.
   * <p>
   * Method under test: {@link RealmBase#getUserAttributes()}
   */
  @Test
  public void testGetUserAttributes() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getUserAttributes());
  }

  /**
   * Test {@link RealmBase#setUserAttributes(String)}.
   * <p>
   * Method under test: {@link RealmBase#setUserAttributes(String)}
   */
  @Test
  public void testSetUserAttributes() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act
    authenticatedUserRealm.setUserAttributes("User Attributes");

    // Assert
    assertEquals("User Attributes", authenticatedUserRealm.getUserAttributes());
  }

  /**
   * Test {@link RealmBase#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link RealmBase#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    NamingContextListener listener = new NamingContextListener();

    // Act
    authenticatedUserRealm.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = authenticatedUserRealm.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link RealmBase#findSecurityConstraints(Request, Context)}.
   * <ul>
   *   <li>Given {@link SecurityConstraint} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#findSecurityConstraints(Request, Context)}
   */
  @Test
  public void testFindSecurityConstraints_givenSecurityConstraint() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    StandardContext context = new StandardContext();
    context.addConstraint(new SecurityConstraint());

    // Act and Assert
    assertNull(authenticatedUserRealm.findSecurityConstraints(request, context));
  }

  /**
   * Test {@link RealmBase#findSecurityConstraints(Request, Context)}.
   * <ul>
   *   <li>When {@link FailedContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#findSecurityConstraints(Request, Context)}
   */
  @Test
  public void testFindSecurityConstraints_whenFailedContext_thenReturnNull() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(authenticatedUserRealm.findSecurityConstraints(request, new FailedContext()));
  }

  /**
   * Test {@link RealmBase#findSecurityConstraints(Request, Context)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#findSecurityConstraints(Request, Context)}
   */
  @Test
  public void testFindSecurityConstraints_whenStandardContext_thenReturnNull() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertNull(authenticatedUserRealm.findSecurityConstraints(request, new StandardContext()));
  }

  /**
   * Test {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}.
   * <ul>
   *   <li>Given {@code Auth Role}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}
   */
  @Test
  public void testHasResourcePermission_givenAuthRole() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    SecurityConstraint securityConstraint = new SecurityConstraint();
    securityConstraint.addAuthRole("Auth Role");

    // Act
    boolean actualHasResourcePermissionResult = authenticatedUserRealm.hasResourcePermission(request, response,
        new SecurityConstraint[]{securityConstraint}, new StandardContext());

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Access to the requested resource has been denied", response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Access to the requested resource has been denied", coyoteResponse.getMessage());
    assertEquals(403, response2.getStatus());
    assertEquals(403, response.getStatus());
    assertEquals(403, coyoteResponse.getStatus());
    assertFalse(actualHasResourcePermissionResult);
    assertTrue(response.isAppCommitted());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}.
   * <ul>
   *   <li>Given {@link SecurityConstraint#ROLE_ALL_AUTHENTICATED_USERS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}
   */
  @Test
  public void testHasResourcePermission_givenRole_all_authenticated_users() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    SecurityConstraint securityConstraint = new SecurityConstraint();
    securityConstraint.addAuthRole(SecurityConstraint.ROLE_ALL_AUTHENTICATED_USERS);

    // Act
    boolean actualHasResourcePermissionResult = authenticatedUserRealm.hasResourcePermission(request, response,
        new SecurityConstraint[]{securityConstraint}, new StandardContext());

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Access to the requested resource has been denied", response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Access to the requested resource has been denied", coyoteResponse.getMessage());
    assertEquals(403, response2.getStatus());
    assertEquals(403, response.getStatus());
    assertEquals(403, coyoteResponse.getStatus());
    assertFalse(actualHasResourcePermissionResult);
    assertTrue(response.isAppCommitted());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}
   */
  @Test
  public void testHasResourcePermission_thenResponseWithCoyoteResponseIsResponseMessageIsNull() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    // Act
    boolean actualHasResourcePermissionResult = authenticatedUserRealm.hasResourcePermission(request, response,
        new SecurityConstraint[]{new SecurityConstraint()}, new StandardContext());

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getMessage());
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertTrue(actualHasResourcePermissionResult);
  }

  /**
   * Test {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}.
   * <ul>
   *   <li>When empty array of {@link SecurityConstraint}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}
   */
  @Test
  public void testHasResourcePermission_whenEmptyArrayOfSecurityConstraint() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    // Act
    boolean actualHasResourcePermissionResult = authenticatedUserRealm.hasResourcePermission(request, response,
        new SecurityConstraint[]{}, new StandardContext());

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getMessage());
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertTrue(actualHasResourcePermissionResult);
  }

  /**
   * Test {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasResourcePermission(Request, Response, SecurityConstraint[], Context)}
   */
  @Test
  public void testHasResourcePermission_whenNull() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    // Act
    boolean actualHasResourcePermissionResult = authenticatedUserRealm.hasResourcePermission(request, response, null,
        new StandardContext());

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getMessage());
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertTrue(actualHasResourcePermissionResult);
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@link StandardWrapper} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenStandardContext_whenStandardWrapperParentIsStandardContext() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    StandardWrapper wrapper = new StandardWrapper();
    wrapper.setParent(new StandardContext());

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@code *}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenAsterisk_thenReturnTrue() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertTrue(authenticatedUserRealm.hasRole(wrapper, new GenericPrincipal("Name"), "*"));
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@link GenericPrincipal#GenericPrincipal(String)} with {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenGenericPrincipalWithName_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRole(wrapper, new GenericPrincipal("Name"), "Role"));
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticatedUserRealm()).hasRole(null, null, null));
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenNull_thenReturnFalse2() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRole(null, new UserPrincipal("principal"), null));
  }

  /**
   * Test {@link RealmBase#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenStandardWrapper_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link RealmBase#parseUserAttributes(String)}.
   * <ul>
   *   <li>When {@code *}.</li>
   *   <li>Then return first is {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#parseUserAttributes(String)}
   */
  @Test
  public void testParseUserAttributes_whenAsterisk_thenReturnFirstIsAsterisk() {
    // Arrange and Act
    List<String> actualParseUserAttributesResult = (new AuthenticatedUserRealm()).parseUserAttributes("*");

    // Assert
    assertEquals(1, actualParseUserAttributesResult.size());
    assertEquals("*", actualParseUserAttributesResult.get(0));
  }

  /**
   * Test {@link RealmBase#parseUserAttributes(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#parseUserAttributes(String)}
   */
  @Test
  public void testParseUserAttributes_whenComma_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).parseUserAttributes(","));
  }

  /**
   * Test {@link RealmBase#parseUserAttributes(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#parseUserAttributes(String)}
   */
  @Test
  public void testParseUserAttributes_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).parseUserAttributes(""));
  }

  /**
   * Test {@link RealmBase#parseUserAttributes(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#parseUserAttributes(String)}
   */
  @Test
  public void testParseUserAttributes_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).parseUserAttributes(null));
  }

  /**
   * Test {@link RealmBase#parseUserAttributes(String)}.
   * <ul>
   *   <li>When {@code User Attributes}.</li>
   *   <li>Then return first is {@code User Attributes}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#parseUserAttributes(String)}
   */
  @Test
  public void testParseUserAttributes_whenUserAttributes_thenReturnFirstIsUserAttributes() {
    // Arrange and Act
    List<String> actualParseUserAttributesResult = (new AuthenticatedUserRealm())
        .parseUserAttributes("User Attributes");

    // Assert
    assertEquals(1, actualParseUserAttributesResult.size());
    assertEquals("User Attributes", actualParseUserAttributesResult.get(0));
  }

  /**
   * Test {@link RealmBase#hasRoleInternal(Principal, String)}.
   * <ul>
   *   <li>When {@code *}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRoleInternal(Principal, String)}
   */
  @Test
  public void testHasRoleInternal_whenAsterisk_thenReturnTrue() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act and Assert
    assertTrue(authenticatedUserRealm.hasRoleInternal(new GenericPrincipal("Name"), "*"));
  }

  /**
   * Test {@link RealmBase#hasRoleInternal(Principal, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRoleInternal(Principal, String)}
   */
  @Test
  public void testHasRoleInternal_whenNull_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRoleInternal(new GenericPrincipal("Name"), null));
  }

  /**
   * Test {@link RealmBase#hasRoleInternal(Principal, String)}.
   * <ul>
   *   <li>When {@code Role}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRoleInternal(Principal, String)}
   */
  @Test
  public void testHasRoleInternal_whenRole_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRoleInternal(new GenericPrincipal("Name"), "Role"));
  }

  /**
   * Test {@link RealmBase#hasRoleInternal(Principal, String)}.
   * <ul>
   *   <li>When {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasRoleInternal(Principal, String)}
   */
  @Test
  public void testHasRoleInternal_whenUserPrincipalWithNameIsPrincipal_thenReturnFalse() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasRoleInternal(new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}.
   * <ul>
   *   <li>When array of {@link SecurityConstraint} with {@link SecurityConstraint} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}
   */
  @Test
  public void testHasUserDataPermission_whenArrayOfSecurityConstraintWithSecurityConstraint() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    // Act and Assert
    assertTrue(authenticatedUserRealm.hasUserDataPermission(request, response,
        new SecurityConstraint[]{new SecurityConstraint()}));
  }

  /**
   * Test {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}.
   * <ul>
   *   <li>When empty array of {@link SecurityConstraint}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}
   */
  @Test
  public void testHasUserDataPermission_whenEmptyArrayOfSecurityConstraint_thenReturnTrue() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertTrue(authenticatedUserRealm.hasUserDataPermission(request, new Response(new org.apache.coyote.Response()),
        new SecurityConstraint[]{}));
  }

  /**
   * Test {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasUserDataPermission(Request, Response, SecurityConstraint[])}
   */
  @Test
  public void testHasUserDataPermission_whenNull_thenReturnTrue() throws IOException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertTrue(
        authenticatedUserRealm.hasUserDataPermission(request, new Response(new org.apache.coyote.Response()), null));
  }

  /**
   * Test {@link RealmBase#initInternal()}.
   * <ul>
   *   <li>Then {@link AuthenticatedUserRealm} (default constructor) Container {@link StandardEngine}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenAuthenticatedUserRealmContainerStandardEngine() throws LifecycleException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setRealmPath("type=Realm");
    authenticatedUserRealm.addPropertyChangeListener(new NamingContextListener());
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act
    authenticatedUserRealm.initInternal();

    // Assert
    Container container = authenticatedUserRealm.getContainer();
    assertTrue(container instanceof StandardEngine);
    assertTrue(authenticatedUserRealm.x509UsernameRetriever instanceof X509SubjectDnRetriever);
    Log expectedLogger = authenticatedUserRealm.containerLog;
    assertSame(expectedLogger, container.getLogger());
  }

  /**
   * Test {@link RealmBase#initInternal()}.
   * <ul>
   *   <li>Then {@link AuthenticatedUserRealm} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenAuthenticatedUserRealmObjectNameDomainIsCatalina() throws LifecycleException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act
    authenticatedUserRealm.initInternal();

    // Assert
    ObjectName objectName = authenticatedUserRealm.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("/realm0", keyPropertyList.get("realmPath"));
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:realmPath=/realm0,type=Realm", objectName.getCanonicalName());
    assertEquals("Realm", keyPropertyList.get("type"));
    assertEquals("realmPath=/realm0,type=Realm", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Realm,realmPath=/realm0", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link RealmBase#initInternal()}.
   * <ul>
   *   <li>Then {@link AuthenticatedUserRealm} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenAuthenticatedUserRealmObjectNameDomainIsCatalina2() throws LifecycleException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.addPropertyChangeListener(new NamingContextListener());
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act
    authenticatedUserRealm.initInternal();

    // Assert
    ObjectName objectName = authenticatedUserRealm.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("/realm0", keyPropertyList.get("realmPath"));
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:realmPath=/realm0,type=Realm", objectName.getCanonicalName());
    assertEquals("Realm", keyPropertyList.get("type"));
    assertEquals("realmPath=/realm0,type=Realm", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Realm,realmPath=/realm0", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link RealmBase#initInternal()}.
   * <ul>
   *   <li>Then {@link AuthenticatedUserRealm} (default constructor) ObjectName Domain is {@code type=Realm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenAuthenticatedUserRealmObjectNameDomainIsTypeRealm() throws LifecycleException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setDomain("type=Realm");
    authenticatedUserRealm.addPropertyChangeListener(new NamingContextListener());
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act
    authenticatedUserRealm.initInternal();

    // Assert
    ObjectName objectName = authenticatedUserRealm.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("/realm0", keyPropertyList.get("realmPath"));
    assertEquals("Realm", keyPropertyList.get("type"));
    assertEquals("realmPath=/realm0,type=Realm", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Realm", objectName.getDomain());
    assertEquals("type=Realm,realmPath=/realm0", objectName.getKeyPropertyListString());
    assertEquals("type=Realm:realmPath=/realm0,type=Realm", objectName.getCanonicalName());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link RealmBase#initInternal()}.
   * <ul>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenThrowLifecycleException() throws LifecycleException {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setX509UsernameRetrieverClassName("type=Realm");
    authenticatedUserRealm.addPropertyChangeListener(new NamingContextListener());
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act and Assert
    assertThrows(LifecycleException.class, () -> authenticatedUserRealm.initInternal());
  }

  /**
   * Test {@link RealmBase#toString()}.
   * <ul>
   *   <li>Then return {@code AuthenticatedUserRealm[Container is null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#toString()}
   */
  @Test
  public void testToString_thenReturnAuthenticatedUserRealmContainerIsNull() {
    // Arrange, Act and Assert
    assertEquals("AuthenticatedUserRealm[Container is null]", (new AuthenticatedUserRealm()).toString());
  }

  /**
   * Test {@link RealmBase#toString()}.
   * <ul>
   *   <li>Then return {@code AuthenticatedUserRealm[StandardContext[null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#toString()}
   */
  @Test
  public void testToString_thenReturnAuthenticatedUserRealmStandardContextNull() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(new StandardContext());

    // Act and Assert
    assertEquals("AuthenticatedUserRealm[StandardContext[null]]", authenticatedUserRealm.toString());
  }

  /**
   * Test {@link RealmBase#hasMessageDigest(String)}.
   * <p>
   * Method under test: {@link RealmBase#hasMessageDigest(String)}
   */
  @Test
  public void testHasMessageDigest() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setCredentialHandler(new MessageDigestCredentialHandler());

    // Act and Assert
    assertFalse(authenticatedUserRealm.hasMessageDigest("Algorithm"));
  }

  /**
   * Test {@link RealmBase#hasMessageDigest(String)}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#hasMessageDigest(String)}
   */
  @Test
  public void testHasMessageDigest_givenAuthenticatedUserRealm() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticatedUserRealm()).hasMessageDigest("Algorithm"));
  }

  /**
   * Test {@link RealmBase#getServer()}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getServer()}
   */
  @Test
  public void testGetServer_givenAuthenticatedUserRealm() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getServer());
  }

  /**
   * Test {@link RealmBase#getServer()}.
   * <ul>
   *   <li>Given {@link AuthenticatedUserRealm} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getServer()}
   */
  @Test
  public void testGetServer_givenAuthenticatedUserRealmContainerIsStandardEngine() {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(null);

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container);

    // Act and Assert
    assertNull(authenticatedUserRealm.getServer());
  }

  /**
   * Test {@link RealmBase#getServer()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getServer()}
   */
  @Test
  public void testGetServer_givenStandardContextParentIsStandardEngine() {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(null);

    StandardContext container2 = new StandardContext();
    container2.setParent(container);

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container2);

    // Act and Assert
    assertNull(authenticatedUserRealm.getServer());
  }

  /**
   * Test {@link RealmBase#getServer()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Service is {@link StandardService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getServer()}
   */
  @Test
  public void testGetServer_givenStandardEngineServiceIsStandardService() {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(new StandardService());

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container);

    // Act and Assert
    assertNull(authenticatedUserRealm.getServer());
  }

  /**
   * Test {@link RealmBase#getServer()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getServer()}
   */
  @Test
  public void testGetServer_givenStandardHostParentIsStandardEngine() {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(null);

    StandardHost container2 = new StandardHost();
    container2.setParent(container);

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container2);

    // Act and Assert
    assertNull(authenticatedUserRealm.getServer());
  }

  /**
   * Test {@link RealmBase#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Realm,realmPath=/realm0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeRealmRealmPathRealm0() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(new StandardEngine());

    // Act and Assert
    assertEquals("type=Realm,realmPath=/realm0", authenticatedUserRealm.getObjectNameKeyProperties());
  }

  /**
   * Test {@link RealmBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setDomain("Catalina");

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container);

    // Act and Assert
    assertEquals("Catalina", authenticatedUserRealm.getDomainInternal());
  }

  /**
   * Test {@link RealmBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setParent(new StandardContext());

    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(container);

    // Act and Assert
    assertEquals("Catalina", authenticatedUserRealm.getDomainInternal());
  }

  /**
   * Test {@link RealmBase#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RealmBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnCatalina() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();
    authenticatedUserRealm.setContainer(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", authenticatedUserRealm.getDomainInternal());
  }

  /**
   * Test {@link RealmBase#getRealmPath()}.
   * <p>
   * Method under test: {@link RealmBase#getRealmPath()}
   */
  @Test
  public void testGetRealmPath() {
    // Arrange, Act and Assert
    assertEquals("/realm0", (new AuthenticatedUserRealm()).getRealmPath());
  }

  /**
   * Test {@link RealmBase#setRealmPath(String)}.
   * <p>
   * Method under test: {@link RealmBase#setRealmPath(String)}
   */
  @Test
  public void testSetRealmPath() {
    // Arrange
    AuthenticatedUserRealm authenticatedUserRealm = new AuthenticatedUserRealm();

    // Act
    authenticatedUserRealm.setRealmPath("The Realm Path");

    // Assert
    assertEquals(",realmPath=The Realm Path", authenticatedUserRealm.getRealmSuffix());
    assertEquals("The Realm Path", authenticatedUserRealm.getRealmPath());
  }

  /**
   * Test {@link RealmBase#getRealmSuffix()}.
   * <p>
   * Method under test: {@link RealmBase#getRealmSuffix()}
   */
  @Test
  public void testGetRealmSuffix() {
    // Arrange, Act and Assert
    assertEquals(",realmPath=/realm0", (new AuthenticatedUserRealm()).getRealmSuffix());
  }
}
