package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import javax.management.MalformedObjectNameException;
import javax.naming.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.naming.NamingContext;
import org.apache.tomcat.util.descriptor.web.ContextLocalEjb;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.MessageDestinationRef;
import org.junit.Test;

public class NamingContextListenerDiffblueTest {
  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(new StandardContext(), "Type", "Data"));

    // Assert
    assertTrue(namingContextListener.container instanceof StandardContext);
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    StandardContext lifecycle = new StandardContext();
    lifecycle.addPropertyChangeListener(new NamingContextListener());

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(lifecycle, "Type", "Data"));

    // Assert
    assertTrue(namingContextListener.container instanceof StandardContext);
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent3() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(new StandardContext(), "configure_stop", "Data"));

    // Assert
    assertTrue(namingContextListener.container instanceof StandardContext);
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenNamingResourcesImpl() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    StandardContext lifecycle = new StandardContext();
    lifecycle.setNamingResources(new NamingResourcesImpl());
    lifecycle.addPropertyChangeListener(new NamingContextListener());

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(lifecycle, "Type", "Data"));

    // Assert
    assertTrue(namingContextListener.container instanceof StandardContext);
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link NamingContextListener} (default constructor) {@link NamingContextListener#compCtx} {@link NamingContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenNamingContextListenerCompCtxNamingContext() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "configure_start", "Data"));

    // Assert
    assertTrue(namingContextListener.compCtx instanceof NamingContext);
    assertTrue(namingContextListener.initialized);
    NamingContext expectedEnvContext = namingContextListener.namingContext;
    assertSame(expectedEnvContext, namingContextListener.getEnvContext());
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link NamingContextListener} (default constructor) {@link NamingContextListener#container} {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenNamingContextListenerContainerBasicAuthenticator() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert
    Object object = namingContextListener.container;
    assertTrue(object instanceof BasicAuthenticator);
    assertEquals("SHA1PRNG", ((BasicAuthenticator) object).getSecureRandomAlgorithm());
    assertEquals("UTF-8", ((BasicAuthenticator) object).getCharset());
    assertEquals("never", ((BasicAuthenticator) object).getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        ((BasicAuthenticator) object).getJaspicCallbackHandlerClass());
    assertNull(((BasicAuthenticator) object).getSecureRandomClass());
    assertNull(((BasicAuthenticator) object).getSecureRandomProvider());
    assertNull(((BasicAuthenticator) object).getDomainInternal());
    assertNull(((BasicAuthenticator) object).getContainer());
    assertNull(((BasicAuthenticator) object).getNext());
    assertFalse(((BasicAuthenticator) object).getAlwaysUseSession());
    assertFalse(((BasicAuthenticator) object).getSecurePagesWithPragma());
    assertFalse(((BasicAuthenticator) object).isSendAuthInfoResponseHeaders());
    assertTrue(((BasicAuthenticator) object).getCache());
    assertTrue(((BasicAuthenticator) object).getChangeSessionIdOnAuthentication());
    assertTrue(((BasicAuthenticator) object).getDisableProxyCaching());
    assertTrue(((BasicAuthenticator) object).isAsyncSupported());
  }

  /**
   * Test {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link NamingContextListener} (default constructor) {@link NamingContextListener#container} {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingContextListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenNamingContextListenerContainerStandardServer() {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    // Act
    namingContextListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "Type", "Data"));

    // Assert
    Object object = namingContextListener.container;
    assertTrue(object instanceof StandardServer);
    assertNull(((StandardServer) object).getGlobalNamingContext());
    assertEquals(1, ((StandardServer) object).findLifecycleListeners().length);
  }

  /**
   * Test {@link NamingContextListener#createObjectName(ContextResource)}.
   * <ul>
   *   <li>Given {@code Catalina}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingContextListener#createObjectName(ContextResource)}
   */
  @Test
  public void testCreateObjectName_givenCatalina_thenReturnNull() throws MalformedObjectNameException {
    // Arrange
    NamingContextListener namingContextListener = new NamingContextListener();

    ContextResource resource = new ContextResource();
    resource.setName("Catalina");

    // Act and Assert
    assertNull(namingContextListener.createObjectName(resource));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link NamingContextListener}
   *   <li>{@link NamingContextListener#setExceptionOnFailedWrite(boolean)}
   *   <li>{@link NamingContextListener#setName(String)}
   *   <li>{@link NamingContextListener#addLocalEjb(ContextLocalEjb)}
   *   <li>{@link NamingContextListener#addMessageDestinationRef(MessageDestinationRef)}
   *   <li>{@link NamingContextListener#getEnvContext()}
   *   <li>{@link NamingContextListener#getExceptionOnFailedWrite()}
   *   <li>{@link NamingContextListener#getName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    NamingContextListener actualNamingContextListener = new NamingContextListener();
    actualNamingContextListener.setExceptionOnFailedWrite(true);
    actualNamingContextListener.setName("Name");
    actualNamingContextListener.addLocalEjb(new ContextLocalEjb());
    actualNamingContextListener.addMessageDestinationRef(new MessageDestinationRef());
    Context actualEnvContext = actualNamingContextListener.getEnvContext();
    boolean actualExceptionOnFailedWrite = actualNamingContextListener.getExceptionOnFailedWrite();

    // Assert
    assertEquals("Name", actualNamingContextListener.getName());
    assertNull(actualEnvContext);
    assertTrue(actualExceptionOnFailedWrite);
  }
}
