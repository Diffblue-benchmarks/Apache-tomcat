package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Realm;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.junit.Test;

public class SingleSignOnDiffblueTest {
  /**
   * Test new {@link SingleSignOn} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SingleSignOn}
   */
  @Test
  public void testNewSingleSignOn() {
    // Arrange and Act
    SingleSignOn actualSingleSignOn = new SingleSignOn();

    // Assert
    assertEquals("Catalina", actualSingleSignOn.getDomain());
    assertEquals("NEW", actualSingleSignOn.getStateName());
    assertNull(actualSingleSignOn.getCookieDomain());
    assertNull(actualSingleSignOn.getDomainInternal());
    assertNull(actualSingleSignOn.getObjectName());
    assertNull(actualSingleSignOn.getContainer());
    assertNull(actualSingleSignOn.getNext());
    assertEquals(0, actualSingleSignOn.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualSingleSignOn.getState());
    assertFalse(actualSingleSignOn.getRequireReauthentication());
    assertTrue(actualSingleSignOn.cache.isEmpty());
    assertTrue(actualSingleSignOn.getThrowOnFailure());
    assertTrue(actualSingleSignOn.isAsyncSupported());
    assertEquals(Constants.SINGLE_SIGN_ON_COOKIE, actualSingleSignOn.getCookieName());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SingleSignOn#setCookieName(String)}
   *   <li>{@link SingleSignOn#setRequireReauthentication(boolean)}
   *   <li>{@link SingleSignOn#getCookieDomain()}
   *   <li>{@link SingleSignOn#getCookieName()}
   *   <li>{@link SingleSignOn#getRequireReauthentication()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();

    // Act
    singleSignOn.setCookieName("Cookie Name");
    singleSignOn.setRequireReauthentication(true);
    String actualCookieDomain = singleSignOn.getCookieDomain();
    String actualCookieName = singleSignOn.getCookieName();

    // Assert
    assertEquals("Cookie Name", actualCookieName);
    assertNull(actualCookieDomain);
    assertTrue(singleSignOn.getRequireReauthentication());
  }

  /**
   * Test {@link SingleSignOn#setCookieDomain(String)}.
   * <ul>
   *   <li>When {@code Cookie Domain}.</li>
   *   <li>Then {@link SingleSignOn} (default constructor) CookieDomain is {@code Cookie Domain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#setCookieDomain(String)}
   */
  @Test
  public void testSetCookieDomain_whenCookieDomain_thenSingleSignOnCookieDomainIsCookieDomain() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();

    // Act
    singleSignOn.setCookieDomain("Cookie Domain");

    // Assert
    assertEquals("Cookie Domain", singleSignOn.getCookieDomain());
  }

  /**
   * Test {@link SingleSignOn#setCookieDomain(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link SingleSignOn} (default constructor) CookieDomain is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#setCookieDomain(String)}
   */
  @Test
  public void testSetCookieDomain_whenEmptyString_thenSingleSignOnCookieDomainIsNull() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();

    // Act
    singleSignOn.setCookieDomain("");

    // Assert that nothing has changed
    assertNull(singleSignOn.getCookieDomain());
  }

  /**
   * Test {@link SingleSignOn#setCookieDomain(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link SingleSignOn} (default constructor) CookieDomain is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#setCookieDomain(String)}
   */
  @Test
  public void testSetCookieDomain_whenNull_thenSingleSignOnCookieDomainIsNull() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();

    // Act
    singleSignOn.setCookieDomain(null);

    // Assert that nothing has changed
    assertNull(singleSignOn.getCookieDomain());
  }

  /**
   * Test {@link SingleSignOn#reauthenticate(String, Realm, Request)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#reauthenticate(String, Realm, Request)}
   */
  @Test
  public void testReauthenticate_when42() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();
    AuthenticatedUserRealm realm = new AuthenticatedUserRealm();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(singleSignOn.reauthenticate("42", realm, new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link SingleSignOn#reauthenticate(String, Realm, Request)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#reauthenticate(String, Realm, Request)}
   */
  @Test
  public void testReauthenticate_when422() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(singleSignOn.reauthenticate("42", null, new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link SingleSignOn#reauthenticate(String, Realm, Request)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#reauthenticate(String, Realm, Request)}
   */
  @Test
  public void testReauthenticate_whenNull() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();
    AuthenticatedUserRealm realm = new AuthenticatedUserRealm();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(singleSignOn.reauthenticate(null, realm, new Request(connector, new org.apache.coyote.Request())));
  }

  /**
   * Test {@link SingleSignOn#update(String, Principal, String, String, String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOn#update(String, Principal, String, String, String)}
   */
  @Test
  public void testUpdate_when42_thenReturnFalse() {
    // Arrange
    SingleSignOn singleSignOn = new SingleSignOn();

    // Act and Assert
    assertFalse(singleSignOn.update("42", new UserPrincipal("principal"), "Auth Type", "janedoe", "iloveyou"));
  }

  /**
   * Test {@link SingleSignOn#getSessionListener(String)}.
   * <p>
   * Method under test: {@link SingleSignOn#getSessionListener(String)}
   */
  @Test
  public void testGetSessionListener() {
    // Arrange and Act
    SessionListener actualSessionListener = (new SingleSignOn()).getSessionListener("42");
    actualSessionListener.sessionEvent(new SessionEvent(new DeltaSession(), "Type", "Data"));

    // Assert
    assertTrue(actualSessionListener instanceof SingleSignOnListener);
  }
}
