package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class NonLoginAuthenticatorDiffblueTest {
  /**
   * Test {@link NonLoginAuthenticator#getAuthMethod()}.
   * <p>
   * Method under test: {@link NonLoginAuthenticator#getAuthMethod()}
   */
  @Test
  public void testGetAuthMethod() {
    // Arrange, Act and Assert
    assertEquals("NONE", (new NonLoginAuthenticator()).getAuthMethod());
  }

  /**
   * Test new {@link NonLoginAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NonLoginAuthenticator}
   */
  @Test
  public void testNewNonLoginAuthenticator() {
    // Arrange and Act
    NonLoginAuthenticator actualNonLoginAuthenticator = new NonLoginAuthenticator();

    // Assert
    assertEquals("Catalina", actualNonLoginAuthenticator.getDomain());
    assertEquals("NEW", actualNonLoginAuthenticator.getStateName());
    assertEquals("NONE", actualNonLoginAuthenticator.getAuthMethod());
    assertEquals("SHA1PRNG", actualNonLoginAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualNonLoginAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualNonLoginAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualNonLoginAuthenticator.getSecureRandomClass());
    assertNull(actualNonLoginAuthenticator.getSecureRandomProvider());
    assertNull(actualNonLoginAuthenticator.getDomainInternal());
    assertNull(actualNonLoginAuthenticator.getObjectName());
    assertNull(actualNonLoginAuthenticator.getContainer());
    assertNull(actualNonLoginAuthenticator.getNext());
    assertNull(actualNonLoginAuthenticator.sso);
    assertNull(actualNonLoginAuthenticator.sessionIdGenerator);
    assertEquals(0, actualNonLoginAuthenticator.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualNonLoginAuthenticator.getState());
    assertFalse(actualNonLoginAuthenticator.getAlwaysUseSession());
    assertFalse(actualNonLoginAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualNonLoginAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualNonLoginAuthenticator.getCache());
    assertTrue(actualNonLoginAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualNonLoginAuthenticator.getDisableProxyCaching());
    assertTrue(actualNonLoginAuthenticator.getThrowOnFailure());
    assertTrue(actualNonLoginAuthenticator.isAsyncSupported());
  }
}
