package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardServer;
import org.junit.Test;

public class GlobalResourcesLifecycleListenerDiffblueTest {
  /**
   * Test {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    GlobalResourcesLifecycleListener globalResourcesLifecycleListener = new GlobalResourcesLifecycleListener();

    // Act
    globalResourcesLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(globalResourcesLifecycleListener.component);
  }

  /**
   * Test {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    GlobalResourcesLifecycleListener globalResourcesLifecycleListener = new GlobalResourcesLifecycleListener();

    // Act
    globalResourcesLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "start", "Data"));

    // Assert
    Lifecycle lifecycle = globalResourcesLifecycleListener.component;
    assertTrue(lifecycle instanceof BasicAuthenticator);
    assertEquals("SHA1PRNG", ((BasicAuthenticator) lifecycle).getSecureRandomAlgorithm());
    assertEquals("UTF-8", ((BasicAuthenticator) lifecycle).getCharset());
    assertEquals("never", ((BasicAuthenticator) lifecycle).getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        ((BasicAuthenticator) lifecycle).getJaspicCallbackHandlerClass());
    assertNull(((BasicAuthenticator) lifecycle).getSecureRandomClass());
    assertNull(((BasicAuthenticator) lifecycle).getSecureRandomProvider());
    assertNull(((BasicAuthenticator) lifecycle).getDomainInternal());
    assertNull(((BasicAuthenticator) lifecycle).getContainer());
    assertNull(((BasicAuthenticator) lifecycle).getNext());
    assertEquals(0, lifecycle.findLifecycleListeners().length);
    assertFalse(((BasicAuthenticator) lifecycle).getAlwaysUseSession());
    assertFalse(((BasicAuthenticator) lifecycle).getSecurePagesWithPragma());
    assertFalse(((BasicAuthenticator) lifecycle).isSendAuthInfoResponseHeaders());
    assertTrue(((BasicAuthenticator) lifecycle).getCache());
    assertTrue(((BasicAuthenticator) lifecycle).getChangeSessionIdOnAuthentication());
    assertTrue(((BasicAuthenticator) lifecycle).getDisableProxyCaching());
    assertTrue(((BasicAuthenticator) lifecycle).isAsyncSupported());
  }

  /**
   * Test {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent3() {
    // Arrange
    GlobalResourcesLifecycleListener globalResourcesLifecycleListener = new GlobalResourcesLifecycleListener();

    // Act
    globalResourcesLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "stop", "Data"));

    // Assert that nothing has changed
    assertNull(globalResourcesLifecycleListener.component);
  }

  /**
   * Test {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link GlobalResourcesLifecycleListener} (default constructor) {@link GlobalResourcesLifecycleListener#component} {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GlobalResourcesLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenGlobalResourcesLifecycleListenerComponentStandardServer() {
    // Arrange
    GlobalResourcesLifecycleListener globalResourcesLifecycleListener = new GlobalResourcesLifecycleListener();

    // Act
    globalResourcesLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "start", "Data"));

    // Assert
    assertTrue(globalResourcesLifecycleListener.component instanceof StandardServer);
  }

  /**
   * Test new {@link GlobalResourcesLifecycleListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link GlobalResourcesLifecycleListener}
   */
  @Test
  public void testNewGlobalResourcesLifecycleListener() {
    // Arrange, Act and Assert
    assertNull((new GlobalResourcesLifecycleListener()).component);
  }
}
