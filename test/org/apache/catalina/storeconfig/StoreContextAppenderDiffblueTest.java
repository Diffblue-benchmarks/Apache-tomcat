package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.nio.file.Paths;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardPipeline;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.junit.Test;

public class StoreContextAppenderDiffblueTest {
  /**
   * Test {@link StoreContextAppender#isPrintValue(Object, Object, String, StoreDescription)}.
   * <p>
   * Method under test: {@link StoreContextAppender#isPrintValue(Object, Object, String, StoreDescription)}
   */
  @Test
  public void testIsPrintValue() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertTrue(storeContextAppender.isPrintValue(standardContext, "Bean2", "Attr Name", new StoreDescription()));
  }

  /**
   * Test {@link StoreContextAppender#getAppBase(StandardHost)}.
   * <ul>
   *   <li>When {@link StandardHost} (default constructor).</li>
   *   <li>Then return Name is {@code webapps}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getAppBase(StandardHost)}
   */
  @Test
  public void testGetAppBase_whenStandardHost_thenReturnNameIsWebapps() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    // Act
    File actualAppBase = storeContextAppender.getAppBase(new StandardHost());

    // Assert
    assertEquals("webapps", actualAppBase.getName());
    assertTrue(actualAppBase.isAbsolute());
  }

  /**
   * Test {@link StoreContextAppender#getDocBase(StandardContext, File)}.
   * <ul>
   *   <li>Given {@code Context}.</li>
   *   <li>Then return Name is {@code Context}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDocBase(StandardContext, File)}
   */
  @Test
  public void testGetDocBase_givenContext_thenReturnNameIsContext() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardContext context = new StandardContext();
    context.setOriginalDocBase("Context");

    // Act
    File actualDocBase = storeContextAppender.getDocBase(context,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals("Context", actualDocBase.getName());
    assertTrue(actualDocBase.isAbsolute());
  }

  /**
   * Test {@link StoreContextAppender#getDocBase(StandardContext, File)}.
   * <ul>
   *   <li>Given {@code /}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDocBase(StandardContext, File)}
   */
  @Test
  public void testGetDocBase_givenSlash_thenReturnNameIsEmptyString() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardContext context = new StandardContext();
    context.setOriginalDocBase("/");

    // Act
    File actualDocBase = storeContextAppender.getDocBase(context,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals("", actualDocBase.getName());
    assertTrue(actualDocBase.isAbsolute());
  }

  /**
   * Test {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}
   */
  @Test
  public void testGetDefaultWorkDir_givenEmptyString_whenStandardContextNameIsEmptyString() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardContext context = new StandardContext();
    context.setName("");

    // Act and Assert
    assertNull(storeContextAppender.getDefaultWorkDir(context));
  }

  /**
   * Test {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link StandardContext} (default constructor) Name is {@code Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}
   */
  @Test
  public void testGetDefaultWorkDir_givenName_whenStandardContextNameIsName_thenReturnNull() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardContext context = new StandardContext();
    context.setName("Name");

    // Act and Assert
    assertNull(storeContextAppender.getDefaultWorkDir(context));
  }

  /**
   * Test {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}
   */
  @Test
  public void testGetDefaultWorkDir_givenStandardHostParentIsStandardContext() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardHost container = new StandardHost();
    container.setParent(new StandardContext());
    container.setWorkDir(null);

    StandardContext context = new StandardContext();
    context.setName("/");
    context.setParent(container);

    // Act and Assert
    assertEquals("work/null/null/", storeContextAppender.getDefaultWorkDir(context));
  }

  /**
   * Test {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}
   */
  @Test
  public void testGetDefaultWorkDir_givenStandardHostParentIsStandardHost() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardHost container = new StandardHost();
    container.setParent(new StandardHost());
    container.setWorkDir(null);

    StandardContext context = new StandardContext();
    context.setName("/");
    context.setParent(container);

    // Act and Assert
    assertEquals("work/null/null/", storeContextAppender.getDefaultWorkDir(context));
  }

  /**
   * Test {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) WorkDir is {@code Context}.</li>
   *   <li>Then return {@code Context/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#getDefaultWorkDir(StandardContext)}
   */
  @Test
  public void testGetDefaultWorkDir_givenStandardHostWorkDirIsContext_thenReturnContext() {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    StandardHost container = new StandardHost();
    container.setWorkDir("Context");

    StandardContext context = new StandardContext();
    context.setName("/");
    context.setParent(container);

    // Act and Assert
    assertEquals("Context/", storeContextAppender.getDefaultWorkDir(context));
  }

  /**
   * Test {@link StoreContextAppender#defaultInstance(Object)}.
   * <ul>
   *   <li>When {@code Bean}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#defaultInstance(Object)}
   */
  @Test
  public void testDefaultInstance_whenBean_thenReturnEmptyString() throws ReflectiveOperationException {
    // Arrange, Act and Assert
    assertEquals("", (new StoreContextAppender()).defaultInstance("Bean"));
  }

  /**
   * Test {@link StoreContextAppender#defaultInstance(Object)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreContextAppender#defaultInstance(Object)}
   */
  @Test
  public void testDefaultInstance_whenStandardContext_thenReturnStandardContext() throws ReflectiveOperationException {
    // Arrange
    StoreContextAppender storeContextAppender = new StoreContextAppender();

    // Act
    Object actualDefaultInstanceResult = storeContextAppender.defaultInstance(new StandardContext());

    // Assert
    assertTrue(actualDefaultInstanceResult instanceof StandardContext);
    assertTrue(((StandardContext) actualDefaultInstanceResult).getPipeline() instanceof StandardPipeline);
    assertTrue(((StandardContext) actualDefaultInstanceResult).getJarScanner() instanceof StandardJarScanner);
    assertEquals("", ((StandardContext) actualDefaultInstanceResult).getWebappVersion());
    assertEquals("Catalina", ((StandardContext) actualDefaultInstanceResult).getDomain());
    assertEquals("NEW", ((StandardContext) actualDefaultInstanceResult).getStateName());
    assertEquals("ROOT", ((StandardContext) actualDefaultInstanceResult).getBaseName());
    assertEquals("decode", ((StandardContext) actualDefaultInstanceResult).getEncodedReverseSolidusHandling());
    assertEquals("jsp", ((StandardContext) actualDefaultInstanceResult).getResourceOnlyServlets());
    assertEquals("none", ((StandardContext) actualDefaultInstanceResult).getJ2EEApplication());
    assertEquals("none", ((StandardContext) actualDefaultInstanceResult).getJ2EEServer());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]",
        ((StandardContext) actualDefaultInstanceResult).getLogName());
    assertEquals("org.apache.catalina.core.StandardWrapper",
        ((StandardContext) actualDefaultInstanceResult).getWrapperClass());
    assertEquals("org.apache.catalina.util.CharsetMapper",
        ((StandardContext) actualDefaultInstanceResult).getCharsetMapperClass());
    assertEquals("reject", ((StandardContext) actualDefaultInstanceResult).getEncodedSolidusHandling());
    assertNull(((StandardContext) actualDefaultInstanceResult).getJspConfigDescriptor());
    assertNull(((StandardContext) actualDefaultInstanceResult).getCatalinaBase());
    assertNull(((StandardContext) actualDefaultInstanceResult).getCatalinaHome());
    assertNull(((StandardContext) actualDefaultInstanceResult).getFailCtxIfServletStartFails());
    assertNotNull(((StandardContext) actualDefaultInstanceResult).getParentClassLoader());
    assertNull(((StandardContext) actualDefaultInstanceResult).getName());
    assertNull(((StandardContext) actualDefaultInstanceResult).getAltDDName());
    assertNull(((StandardContext) actualDefaultInstanceResult).getContainerSciFilter());
    assertNull(((StandardContext) actualDefaultInstanceResult).getDefaultContextXml());
    assertNull(((StandardContext) actualDefaultInstanceResult).getDefaultWebXml());
    assertNull(((StandardContext) actualDefaultInstanceResult).getDisplayName());
    assertNull(((StandardContext) actualDefaultInstanceResult).getDocBase());
    assertNull(((StandardContext) actualDefaultInstanceResult).getEncodedPath());
    assertNull(((StandardContext) actualDefaultInstanceResult).getOriginalDocBase());
    assertNull(((StandardContext) actualDefaultInstanceResult).getPath());
    assertNull(((StandardContext) actualDefaultInstanceResult).getPublicId());
    assertNull(((StandardContext) actualDefaultInstanceResult).getRequestCharacterEncoding());
    assertNull(((StandardContext) actualDefaultInstanceResult).getResponseCharacterEncoding());
    assertNull(((StandardContext) actualDefaultInstanceResult).getServer());
    assertNull(((StandardContext) actualDefaultInstanceResult).getSessionCookieDomain());
    assertNull(((StandardContext) actualDefaultInstanceResult).getSessionCookieName());
    assertNull(((StandardContext) actualDefaultInstanceResult).getSessionCookiePath());
    assertNull(((StandardContext) actualDefaultInstanceResult).getWorkDir());
    assertNull(((StandardContext) actualDefaultInstanceResult).getWorkPath());
    assertNull(((StandardContext) actualDefaultInstanceResult).getConfigFile());
    assertNull(((StandardContext) actualDefaultInstanceResult).getObjectName());
    assertNull(((StandardContext) actualDefaultInstanceResult).getAccessLog());
    assertNull(((StandardContext) actualDefaultInstanceResult).getAuthenticator());
    assertNull(((StandardContext) actualDefaultInstanceResult).getCluster());
    assertNull(((StandardContext) actualDefaultInstanceResult).getParent());
    assertNull(((StandardContext) actualDefaultInstanceResult).getLoader());
    assertNull(((StandardContext) actualDefaultInstanceResult).getManager());
    assertNull(((StandardContext) actualDefaultInstanceResult).getRealm());
    assertNull(((StandardContext) actualDefaultInstanceResult).getResources());
    assertNull(((StandardContext) actualDefaultInstanceResult).getNamingContextListener());
    assertNull(((StandardContext) actualDefaultInstanceResult).getInstanceManager());
    assertNull(((StandardContext) actualDefaultInstanceResult).getLoginConfig());
    assertNull(((StandardContext) actualDefaultInstanceResult).getCookieProcessor());
    assertEquals(-1, ((StandardContext) actualDefaultInstanceResult).getBackgroundProcessorDelay());
    assertEquals(-1L, ((StandardContext) actualDefaultInstanceResult).getMinTime());
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).getEffectiveMinorVersion());
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findContainerListeners().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).getChildren().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findApplicationListeners().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findApplicationParameters().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findConstraints().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findErrorPages().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findSecurityRoles().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findWatchedResources().length);
    String[] findWelcomeFilesResult = ((StandardContext) actualDefaultInstanceResult).findWelcomeFiles();
    assertEquals(0, findWelcomeFilesResult.length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findWrapperLifecycles().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findWrapperListeners().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).getApplicationEventListeners().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).getApplicationLifecycleListeners().length);
    assertEquals(0, ((StandardContext) actualDefaultInstanceResult).findLifecycleListeners().length);
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getErrorCount());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getInProgressAsyncCount());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getMaxTime());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getProcessingTime());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getRequestCount());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getStartTime());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getStartupTime());
    assertEquals(0L, ((StandardContext) actualDefaultInstanceResult).getTldScanTime());
    assertEquals(1, ((StandardContext) actualDefaultInstanceResult).getStartStopThreads());
    assertEquals(1000, ((StandardContext) actualDefaultInstanceResult).getNotFoundClassResourceCacheSize());
    assertEquals(2000L, ((StandardContext) actualDefaultInstanceResult).getUnloadDelay());
    assertEquals(3, ((StandardContext) actualDefaultInstanceResult).getEffectiveMajorVersion());
    assertEquals(30, ((StandardContext) actualDefaultInstanceResult).getSessionTimeout());
    assertEquals(7, ((StandardContext) actualDefaultInstanceResult).getNotificationInfo().length);
    assertEquals(LifecycleState.NEW, ((StandardContext) actualDefaultInstanceResult).getState());
    assertEquals(EncodedSolidusHandling.DECODE,
        ((StandardContext) actualDefaultInstanceResult).getEncodedReverseSolidusHandlingEnum());
    assertEquals(EncodedSolidusHandling.REJECT,
        ((StandardContext) actualDefaultInstanceResult).getEncodedSolidusHandlingEnum());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getAddWebinfClassesResources());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getAllowCasualMultipartParsing());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getAllowMultipleLeadingForwardSlashInPath());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getAlwaysAccessSession());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getAntiResourceLocking());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getClearReferencesStopThreads());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getClearReferencesStopTimerThreads());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getConfigured());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getContextGetResourceRequiresSlash());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getCopyXML());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getCreateUploadTargets());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getCrossContext());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getDelegate());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getDenyUncoveredHttpMethods());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getDispatcherWrapsSameObject());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getDistributable());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getFireRequestListenersOnForwards());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getIgnoreAnnotations());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getLogEffectiveWebXml());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getMapperDirectoryRedirectEnabled());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getMetadataComplete());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getOverride());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getParallelAnnotationScanning());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getPaused());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getPreemptiveAuthentication());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getPrivileged());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getReloadable());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getSendRedirectBody());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getSessionCookiePathUsesTrailingSlash());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getSkipMemoryLeakChecksOnJvmShutdown());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getSwallowOutput());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getTldValidation());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getUsePartitioned());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getXmlNamespaceAware());
    assertFalse(((StandardContext) actualDefaultInstanceResult).getXmlValidation());
    assertFalse(((StandardContext) actualDefaultInstanceResult).isServlet22());
    assertTrue(((StandardContext) actualDefaultInstanceResult).findPostConstructMethods().isEmpty());
    assertTrue(((StandardContext) actualDefaultInstanceResult).findPreDestroyMethods().isEmpty());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getStartChildren());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getClearReferencesHttpClientKeepAliveThread());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getClearReferencesRmiTargets());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getClearReferencesThreadLocals());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getCookies());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getDispatchersUseEncodedPaths());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getJndiExceptionOnFailedWrite());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getMapperContextRootRedirectEnabled());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getRenewThreadsWhenStoppingContext());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getSuspendWrappedResponseAfterForward());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getSwallowAbortedUploads());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getUnpackWAR());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getUseHttpOnly());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getUseRelativeRedirects());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getValidateClientProvidedNewSessionId());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getXmlBlockExternal());
    assertTrue(((StandardContext) actualDefaultInstanceResult).isUseNaming());
    assertTrue(((StandardContext) actualDefaultInstanceResult).getThrowOnFailure());
    assertSame(findWelcomeFilesResult, ((StandardContext) actualDefaultInstanceResult).getWelcomeFiles());
  }
}
