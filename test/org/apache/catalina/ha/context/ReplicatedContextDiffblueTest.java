package org.apache.catalina.ha.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardPipeline;
import org.apache.catalina.ha.context.ReplicatedContext.MultiEnumeration;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.junit.Test;

public class ReplicatedContextDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplicatedContext#setMapSendOptions(int)}
   *   <li>{@link ReplicatedContext#objectMadePrimary(Object, Object)}
   *   <li>{@link ReplicatedContext#getMapSendOptions()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ReplicatedContext replicatedContext = new ReplicatedContext();

    // Act
    replicatedContext.setMapSendOptions(3);
    replicatedContext.objectMadePrimary("Key", "Value");

    // Assert
    assertEquals(3, replicatedContext.getMapSendOptions());
  }

  /**
   * Test {@link ReplicatedContext#getClassLoaders()}.
   * <ul>
   *   <li>Given {@link ReplicatedContext} (default constructor).</li>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicatedContext#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_givenReplicatedContext_thenReturnArrayLengthIsOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new ReplicatedContext()).getClassLoaders().length);
  }

  /**
   * Test {@link ReplicatedContext#getClassLoaders()}.
   * <ul>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicatedContext#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_thenReturnArrayLengthIsOne() {
    // Arrange
    ReplicatedContext replicatedContext = new ReplicatedContext();
    replicatedContext.setLoader(new WebappLoader());

    // Act and Assert
    assertEquals(1, replicatedContext.getClassLoaders().length);
  }

  /**
   * Test {@link ReplicatedContext#getClassLoaders()}.
   * <ul>
   *   <li>Then return first element is not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicatedContext#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_thenReturnFirstElementIsNotNull() {
    // Arrange
    WebappLoader loader = new WebappLoader();
    loader.setLoaderInstance(new ParallelWebappClassLoader());

    ReplicatedContext replicatedContext = new ReplicatedContext();
    replicatedContext.setLoader(loader);

    // Act
    ClassLoader[] actualClassLoaders = replicatedContext.getClassLoaders();

    // Assert
    assertNotNull(actualClassLoaders[0]);
    assertEquals(2, actualClassLoaders.length);
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#hasMoreElements()}.
   * <p>
   * Method under test: {@link MultiEnumeration#hasMoreElements()}
   */
  @Test
  public void testMultiEnumerationHasMoreElements() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(
        new Enumeration[]{new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("foo")})});

    // Act and Assert
    assertTrue(multiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#hasMoreElements()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiEnumeration#hasMoreElements()}
   */
  @Test
  public void testMultiEnumerationHasMoreElements_thenReturnFalse() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("")});

    // Act and Assert
    assertFalse(multiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#hasMoreElements()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiEnumeration#hasMoreElements()}
   */
  @Test
  public void testMultiEnumerationHasMoreElements_thenReturnTrue() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("foo")});

    // Act and Assert
    assertTrue(multiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#MultiEnumeration(Enumeration[])}.
   * <p>
   * Method under test: {@link MultiEnumeration#MultiEnumeration(Enumeration[])}
   */
  @Test
  public void testMultiEnumerationNewMultiEnumeration() {
    // Arrange and Act
    MultiEnumeration<Object> actualMultiEnumeration = new MultiEnumeration<>(
        new Enumeration[]{new StringTokenizer("foo")});

    // Assert
    assertTrue(actualMultiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#nextElement()}.
   * <p>
   * Method under test: {@link MultiEnumeration#nextElement()}
   */
  @Test
  public void testMultiEnumerationNextElement() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(
        new Enumeration[]{new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("foo")})});

    // Act and Assert
    assertEquals("foo", multiEnumeration.nextElement());
    assertFalse(multiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#nextElement()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiEnumeration#nextElement()}
   */
  @Test
  public void testMultiEnumerationNextElement_thenReturnFoo() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("foo")});

    // Act and Assert
    assertEquals("foo", multiEnumeration.nextElement());
    assertFalse(multiEnumeration.hasMoreElements());
  }

  /**
   * Test MultiEnumeration {@link MultiEnumeration#nextElement()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiEnumeration#nextElement()}
   */
  @Test
  public void testMultiEnumerationNextElement_thenReturnNull() {
    // Arrange
    MultiEnumeration<Object> multiEnumeration = new MultiEnumeration<>(new Enumeration[]{new StringTokenizer("")});

    // Act and Assert
    assertNull(multiEnumeration.nextElement());
    assertFalse(multiEnumeration.hasMoreElements());
  }

  /**
   * Test new {@link ReplicatedContext} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ReplicatedContext}
   */
  @Test
  public void testNewReplicatedContext() {
    // Arrange and Act
    ReplicatedContext actualReplicatedContext = new ReplicatedContext();

    // Assert
    assertTrue(actualReplicatedContext.getPipeline() instanceof StandardPipeline);
    assertTrue(actualReplicatedContext.getJarScanner() instanceof StandardJarScanner);
    assertEquals("", actualReplicatedContext.getWebappVersion());
    assertEquals("Catalina", actualReplicatedContext.getDomain());
    assertEquals("NEW", actualReplicatedContext.getStateName());
    assertEquals("ROOT", actualReplicatedContext.getBaseName());
    assertEquals("decode", actualReplicatedContext.getEncodedReverseSolidusHandling());
    assertEquals("jsp", actualReplicatedContext.getResourceOnlyServlets());
    assertEquals("none", actualReplicatedContext.getJ2EEApplication());
    assertEquals("none", actualReplicatedContext.getJ2EEServer());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualReplicatedContext.getLogName());
    assertEquals("org.apache.catalina.core.StandardWrapper", actualReplicatedContext.getWrapperClass());
    assertEquals("org.apache.catalina.util.CharsetMapper", actualReplicatedContext.getCharsetMapperClass());
    assertEquals("reject", actualReplicatedContext.getEncodedSolidusHandling());
    assertNull(actualReplicatedContext.getJspConfigDescriptor());
    assertNull(actualReplicatedContext.getCatalinaBase());
    assertNull(actualReplicatedContext.getCatalinaHome());
    assertNull(actualReplicatedContext.getFailCtxIfServletStartFails());
    assertNotNull(actualReplicatedContext.getParentClassLoader());
    assertNull(actualReplicatedContext.getName());
    assertNull(actualReplicatedContext.getAltDDName());
    assertNull(actualReplicatedContext.getContainerSciFilter());
    assertNull(actualReplicatedContext.getDefaultContextXml());
    assertNull(actualReplicatedContext.getDefaultWebXml());
    assertNull(actualReplicatedContext.getDisplayName());
    assertNull(actualReplicatedContext.getDocBase());
    assertNull(actualReplicatedContext.getEncodedPath());
    assertNull(actualReplicatedContext.getOriginalDocBase());
    assertNull(actualReplicatedContext.getPath());
    assertNull(actualReplicatedContext.getPublicId());
    assertNull(actualReplicatedContext.getRequestCharacterEncoding());
    assertNull(actualReplicatedContext.getResponseCharacterEncoding());
    assertNull(actualReplicatedContext.getServer());
    assertNull(actualReplicatedContext.getSessionCookieDomain());
    assertNull(actualReplicatedContext.getSessionCookieName());
    assertNull(actualReplicatedContext.getSessionCookiePath());
    assertNull(actualReplicatedContext.getWorkDir());
    assertNull(actualReplicatedContext.getWorkPath());
    assertNull(actualReplicatedContext.getConfigFile());
    assertNull(actualReplicatedContext.getObjectName());
    assertNull(actualReplicatedContext.getAccessLog());
    assertNull(actualReplicatedContext.getAuthenticator());
    assertNull(actualReplicatedContext.getCluster());
    assertNull(actualReplicatedContext.getParent());
    assertNull(actualReplicatedContext.getLoader());
    assertNull(actualReplicatedContext.getManager());
    assertNull(actualReplicatedContext.getRealm());
    assertNull(actualReplicatedContext.getResources());
    assertNull(actualReplicatedContext.getNamingContextListener());
    assertNull(actualReplicatedContext.getInstanceManager());
    assertNull(actualReplicatedContext.getLoginConfig());
    assertNull(actualReplicatedContext.getCookieProcessor());
    assertEquals(-1, actualReplicatedContext.getBackgroundProcessorDelay());
    assertEquals(-1L, actualReplicatedContext.getMinTime());
    assertEquals(0, actualReplicatedContext.getEffectiveMinorVersion());
    assertEquals(0, actualReplicatedContext.findContainerListeners().length);
    assertEquals(0, actualReplicatedContext.getChildren().length);
    assertEquals(0, actualReplicatedContext.findApplicationListeners().length);
    assertEquals(0, actualReplicatedContext.findApplicationParameters().length);
    assertEquals(0, actualReplicatedContext.findConstraints().length);
    assertEquals(0, actualReplicatedContext.findErrorPages().length);
    assertEquals(0, actualReplicatedContext.findSecurityRoles().length);
    assertEquals(0, actualReplicatedContext.findWatchedResources().length);
    String[] findWelcomeFilesResult = actualReplicatedContext.findWelcomeFiles();
    assertEquals(0, findWelcomeFilesResult.length);
    assertEquals(0, actualReplicatedContext.findWrapperLifecycles().length);
    assertEquals(0, actualReplicatedContext.findWrapperListeners().length);
    assertEquals(0, actualReplicatedContext.getApplicationEventListeners().length);
    assertEquals(0, actualReplicatedContext.getApplicationLifecycleListeners().length);
    assertEquals(0, actualReplicatedContext.findLifecycleListeners().length);
    assertEquals(0L, actualReplicatedContext.getErrorCount());
    assertEquals(0L, actualReplicatedContext.getInProgressAsyncCount());
    assertEquals(0L, actualReplicatedContext.getMaxTime());
    assertEquals(0L, actualReplicatedContext.getProcessingTime());
    assertEquals(0L, actualReplicatedContext.getRequestCount());
    assertEquals(0L, actualReplicatedContext.getStartTime());
    assertEquals(0L, actualReplicatedContext.getStartupTime());
    assertEquals(0L, actualReplicatedContext.getTldScanTime());
    assertEquals(1, actualReplicatedContext.getStartStopThreads());
    assertEquals(1, actualReplicatedContext.getClassLoaders().length);
    assertEquals(1000, actualReplicatedContext.getNotFoundClassResourceCacheSize());
    assertEquals(2, actualReplicatedContext.getMapSendOptions());
    assertEquals(2000L, actualReplicatedContext.getUnloadDelay());
    assertEquals(3, actualReplicatedContext.getEffectiveMajorVersion());
    assertEquals(30, actualReplicatedContext.getSessionTimeout());
    assertEquals(7, actualReplicatedContext.getNotificationInfo().length);
    assertEquals(LifecycleState.NEW, actualReplicatedContext.getState());
    assertEquals(EncodedSolidusHandling.DECODE, actualReplicatedContext.getEncodedReverseSolidusHandlingEnum());
    assertEquals(EncodedSolidusHandling.REJECT, actualReplicatedContext.getEncodedSolidusHandlingEnum());
    assertFalse(actualReplicatedContext.getAddWebinfClassesResources());
    assertFalse(actualReplicatedContext.getAllowCasualMultipartParsing());
    assertFalse(actualReplicatedContext.getAllowMultipleLeadingForwardSlashInPath());
    assertFalse(actualReplicatedContext.getAlwaysAccessSession());
    assertFalse(actualReplicatedContext.getAntiResourceLocking());
    assertFalse(actualReplicatedContext.getClearReferencesStopThreads());
    assertFalse(actualReplicatedContext.getClearReferencesStopTimerThreads());
    assertFalse(actualReplicatedContext.getConfigured());
    assertFalse(actualReplicatedContext.getContextGetResourceRequiresSlash());
    assertFalse(actualReplicatedContext.getCopyXML());
    assertFalse(actualReplicatedContext.getCreateUploadTargets());
    assertFalse(actualReplicatedContext.getCrossContext());
    assertFalse(actualReplicatedContext.getDelegate());
    assertFalse(actualReplicatedContext.getDenyUncoveredHttpMethods());
    assertFalse(actualReplicatedContext.getDispatcherWrapsSameObject());
    assertFalse(actualReplicatedContext.getDistributable());
    assertFalse(actualReplicatedContext.getFireRequestListenersOnForwards());
    assertFalse(actualReplicatedContext.getIgnoreAnnotations());
    assertFalse(actualReplicatedContext.getLogEffectiveWebXml());
    assertFalse(actualReplicatedContext.getMapperDirectoryRedirectEnabled());
    assertFalse(actualReplicatedContext.getMetadataComplete());
    assertFalse(actualReplicatedContext.getOverride());
    assertFalse(actualReplicatedContext.getParallelAnnotationScanning());
    assertFalse(actualReplicatedContext.getPaused());
    assertFalse(actualReplicatedContext.getPreemptiveAuthentication());
    assertFalse(actualReplicatedContext.getPrivileged());
    assertFalse(actualReplicatedContext.getReloadable());
    assertFalse(actualReplicatedContext.getSendRedirectBody());
    assertFalse(actualReplicatedContext.getSessionCookiePathUsesTrailingSlash());
    assertFalse(actualReplicatedContext.getSkipMemoryLeakChecksOnJvmShutdown());
    assertFalse(actualReplicatedContext.getSwallowOutput());
    assertFalse(actualReplicatedContext.getTldValidation());
    assertFalse(actualReplicatedContext.getUsePartitioned());
    assertFalse(actualReplicatedContext.getXmlNamespaceAware());
    assertFalse(actualReplicatedContext.getXmlValidation());
    assertFalse(actualReplicatedContext.isServlet22());
    assertTrue(actualReplicatedContext.findPostConstructMethods().isEmpty());
    assertTrue(actualReplicatedContext.findPreDestroyMethods().isEmpty());
    assertTrue(actualReplicatedContext.getStartChildren());
    assertTrue(actualReplicatedContext.getClearReferencesHttpClientKeepAliveThread());
    assertTrue(actualReplicatedContext.getClearReferencesRmiTargets());
    assertTrue(actualReplicatedContext.getClearReferencesThreadLocals());
    assertTrue(actualReplicatedContext.getCookies());
    assertTrue(actualReplicatedContext.getDispatchersUseEncodedPaths());
    assertTrue(actualReplicatedContext.getJndiExceptionOnFailedWrite());
    assertTrue(actualReplicatedContext.getMapperContextRootRedirectEnabled());
    assertTrue(actualReplicatedContext.getRenewThreadsWhenStoppingContext());
    assertTrue(actualReplicatedContext.getSuspendWrappedResponseAfterForward());
    assertTrue(actualReplicatedContext.getSwallowAbortedUploads());
    assertTrue(actualReplicatedContext.getUnpackWAR());
    assertTrue(actualReplicatedContext.getUseHttpOnly());
    assertTrue(actualReplicatedContext.getUseRelativeRedirects());
    assertTrue(actualReplicatedContext.getValidateClientProvidedNewSessionId());
    assertTrue(actualReplicatedContext.getXmlBlockExternal());
    assertTrue(actualReplicatedContext.isUseNaming());
    assertTrue(actualReplicatedContext.getThrowOnFailure());
    assertSame(findWelcomeFilesResult, actualReplicatedContext.getWelcomeFiles());
  }
}
