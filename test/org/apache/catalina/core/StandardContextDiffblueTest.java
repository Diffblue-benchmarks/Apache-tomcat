package org.apache.catalina.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import javax.management.MBeanNotificationInfo;
import javax.management.ObjectName;
import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.Manager;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.ThreadBindingListener;
import org.apache.catalina.Valve;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.Wrapper;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.catalina.ha.context.ReplicatedContext;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.manager.HTMLManagerServlet;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.catalina.startup.FailedContext;
import org.apache.catalina.util.CharsetMapper;
import org.apache.catalina.webresources.ExtractingRoot;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.webresources.TesterWebResourceRoot;
import org.apache.juli.logging.Log;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.JarScanFilter;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.SimpleInstanceManager;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.http.CookieProcessor;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.junit.Test;

public class StandardContextDiffblueTest {
  /**
   * Test new {@link StandardContext} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardContext}
   */
  @Test
  public void testNewStandardContext() {
    // Arrange and Act
    StandardContext actualStandardContext = new StandardContext();

    // Assert
    assertTrue(actualStandardContext.getPipeline() instanceof StandardPipeline);
    assertTrue(actualStandardContext.getJarScanner() instanceof StandardJarScanner);
    assertEquals("", actualStandardContext.getWebappVersion());
    assertEquals("Catalina", actualStandardContext.getDomain());
    assertEquals("NEW", actualStandardContext.getStateName());
    assertEquals("ROOT", actualStandardContext.getBaseName());
    assertEquals("decode", actualStandardContext.getEncodedReverseSolidusHandling());
    assertEquals("jsp", actualStandardContext.getResourceOnlyServlets());
    assertEquals("none", actualStandardContext.getJ2EEApplication());
    assertEquals("none", actualStandardContext.getJ2EEServer());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardContext.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardContext.logName);
    assertEquals("org.apache.catalina.core.StandardWrapper", actualStandardContext.getWrapperClass());
    assertEquals("org.apache.catalina.util.CharsetMapper", actualStandardContext.getCharsetMapperClass());
    assertEquals("reject", actualStandardContext.getEncodedSolidusHandling());
    assertNull(actualStandardContext.getJspConfigDescriptor());
    assertNull(actualStandardContext.getCatalinaBase());
    assertNull(actualStandardContext.getCatalinaHome());
    assertNull(actualStandardContext.getFailCtxIfServletStartFails());
    assertNotNull(actualStandardContext.getParentClassLoader());
    assertNull(actualStandardContext.parentClassLoader);
    assertNull(actualStandardContext.getDomainInternal());
    assertNull(actualStandardContext.getName());
    assertNull(actualStandardContext.getAltDDName());
    assertNull(actualStandardContext.getContainerSciFilter());
    assertNull(actualStandardContext.getDefaultContextXml());
    assertNull(actualStandardContext.getDefaultWebXml());
    assertNull(actualStandardContext.getDisplayName());
    assertNull(actualStandardContext.getDocBase());
    assertNull(actualStandardContext.getEncodedPath());
    assertNull(actualStandardContext.getOriginalDocBase());
    assertNull(actualStandardContext.getPath());
    assertNull(actualStandardContext.getPublicId());
    assertNull(actualStandardContext.getRequestCharacterEncoding());
    assertNull(actualStandardContext.getResponseCharacterEncoding());
    assertNull(actualStandardContext.getServer());
    assertNull(actualStandardContext.getSessionCookieDomain());
    assertNull(actualStandardContext.getSessionCookieName());
    assertNull(actualStandardContext.getSessionCookiePath());
    assertNull(actualStandardContext.getWorkDir());
    assertNull(actualStandardContext.getWorkPath());
    assertNull(actualStandardContext.getConfigFile());
    assertNull(actualStandardContext.startStopExecutor);
    assertNull(actualStandardContext.backgroundProcessorFuture);
    assertNull(actualStandardContext.monitorFuture);
    assertNull(actualStandardContext.getObjectName());
    assertNull(actualStandardContext.getAccessLog());
    assertNull(actualStandardContext.accessLog);
    assertNull(actualStandardContext.getAuthenticator());
    assertNull(actualStandardContext.getCluster());
    assertNull(actualStandardContext.getClusterInternal());
    assertNull(actualStandardContext.cluster);
    assertNull(actualStandardContext.getParent());
    assertNull(actualStandardContext.getLoader());
    assertNull(actualStandardContext.getManager());
    assertNull(actualStandardContext.manager);
    assertNull(actualStandardContext.getRealm());
    assertNull(actualStandardContext.getRealmInternal());
    assertNull(actualStandardContext.getResources());
    assertNull(actualStandardContext.context);
    assertNull(actualStandardContext.getNamingContextListener());
    assertNull(actualStandardContext.getInstanceManager());
    assertNull(actualStandardContext.getLoginConfig());
    assertNull(actualStandardContext.getCookieProcessor());
    assertEquals(-1, actualStandardContext.getBackgroundProcessorDelay());
    assertEquals(-1L, actualStandardContext.getMinTime());
    assertEquals(0, actualStandardContext.getEffectiveMinorVersion());
    assertEquals(0, actualStandardContext.findContainerListeners().length);
    assertEquals(0, actualStandardContext.getChildren().length);
    assertEquals(0, actualStandardContext.findApplicationListeners().length);
    assertEquals(0, actualStandardContext.findApplicationParameters().length);
    assertEquals(0, actualStandardContext.findConstraints().length);
    assertEquals(0, actualStandardContext.findErrorPages().length);
    assertEquals(0, actualStandardContext.findSecurityRoles().length);
    assertEquals(0, actualStandardContext.findWatchedResources().length);
    String[] findWelcomeFilesResult = actualStandardContext.findWelcomeFiles();
    assertEquals(0, findWelcomeFilesResult.length);
    assertEquals(0, actualStandardContext.findWrapperLifecycles().length);
    assertEquals(0, actualStandardContext.findWrapperListeners().length);
    assertEquals(0, actualStandardContext.getApplicationEventListeners().length);
    assertEquals(0, actualStandardContext.getApplicationLifecycleListeners().length);
    assertEquals(0, actualStandardContext.findLifecycleListeners().length);
    assertEquals(0L, actualStandardContext.getErrorCount());
    assertEquals(0L, actualStandardContext.getInProgressAsyncCount());
    assertEquals(0L, actualStandardContext.getMaxTime());
    assertEquals(0L, actualStandardContext.getProcessingTime());
    assertEquals(0L, actualStandardContext.getRequestCount());
    assertEquals(0L, actualStandardContext.getStartTime());
    assertEquals(0L, actualStandardContext.getStartupTime());
    assertEquals(0L, actualStandardContext.getTldScanTime());
    assertEquals(1, actualStandardContext.getStartStopThreads());
    assertEquals(1000, actualStandardContext.getNotFoundClassResourceCacheSize());
    assertEquals(2000L, actualStandardContext.getUnloadDelay());
    assertEquals(3, actualStandardContext.getEffectiveMajorVersion());
    assertEquals(30, actualStandardContext.getSessionTimeout());
    assertEquals(7, actualStandardContext.getNotificationInfo().length);
    assertEquals(LifecycleState.NEW, actualStandardContext.getState());
    assertEquals(EncodedSolidusHandling.DECODE, actualStandardContext.getEncodedReverseSolidusHandlingEnum());
    assertEquals(EncodedSolidusHandling.REJECT, actualStandardContext.getEncodedSolidusHandlingEnum());
    assertFalse(actualStandardContext.getAddWebinfClassesResources());
    assertFalse(actualStandardContext.getAllowCasualMultipartParsing());
    assertFalse(actualStandardContext.getAllowMultipleLeadingForwardSlashInPath());
    assertFalse(actualStandardContext.getAlwaysAccessSession());
    assertFalse(actualStandardContext.getAntiResourceLocking());
    assertFalse(actualStandardContext.getClearReferencesStopThreads());
    assertFalse(actualStandardContext.getClearReferencesStopTimerThreads());
    assertFalse(actualStandardContext.getComputedFailCtxIfServletStartFails());
    assertFalse(actualStandardContext.getConfigured());
    assertFalse(actualStandardContext.getContextGetResourceRequiresSlash());
    assertFalse(actualStandardContext.getCopyXML());
    assertFalse(actualStandardContext.getCreateUploadTargets());
    assertFalse(actualStandardContext.getCrossContext());
    assertFalse(actualStandardContext.getDelegate());
    assertFalse(actualStandardContext.getDenyUncoveredHttpMethods());
    assertFalse(actualStandardContext.getDispatcherWrapsSameObject());
    assertFalse(actualStandardContext.getDistributable());
    assertFalse(actualStandardContext.getFireRequestListenersOnForwards());
    assertFalse(actualStandardContext.getIgnoreAnnotations());
    assertFalse(actualStandardContext.getLogEffectiveWebXml());
    assertFalse(actualStandardContext.getMapperDirectoryRedirectEnabled());
    assertFalse(actualStandardContext.getMetadataComplete());
    assertFalse(actualStandardContext.getOverride());
    assertFalse(actualStandardContext.getParallelAnnotationScanning());
    assertFalse(actualStandardContext.getPaused());
    assertFalse(actualStandardContext.getPreemptiveAuthentication());
    assertFalse(actualStandardContext.getPrivileged());
    assertFalse(actualStandardContext.getReloadable());
    assertFalse(actualStandardContext.getSendRedirectBody());
    assertFalse(actualStandardContext.getSessionCookiePathUsesTrailingSlash());
    assertFalse(actualStandardContext.getSkipMemoryLeakChecksOnJvmShutdown());
    assertFalse(actualStandardContext.getSwallowOutput());
    assertFalse(actualStandardContext.getTldValidation());
    assertFalse(actualStandardContext.getUsePartitioned());
    assertFalse(actualStandardContext.getXmlNamespaceAware());
    assertFalse(actualStandardContext.getXmlValidation());
    assertFalse(actualStandardContext.isServlet22());
    assertTrue(actualStandardContext.children.isEmpty());
    assertTrue(actualStandardContext.listeners.isEmpty());
    assertTrue(actualStandardContext.findPostConstructMethods().isEmpty());
    assertTrue(actualStandardContext.findPreDestroyMethods().isEmpty());
    assertTrue(actualStandardContext.getStartChildren());
    assertTrue(actualStandardContext.getClearReferencesHttpClientKeepAliveThread());
    assertTrue(actualStandardContext.getClearReferencesRmiTargets());
    assertTrue(actualStandardContext.getClearReferencesThreadLocals());
    assertTrue(actualStandardContext.getCookies());
    assertTrue(actualStandardContext.getDispatchersUseEncodedPaths());
    assertTrue(actualStandardContext.getJndiExceptionOnFailedWrite());
    assertTrue(actualStandardContext.getMapperContextRootRedirectEnabled());
    assertTrue(actualStandardContext.getRenewThreadsWhenStoppingContext());
    assertTrue(actualStandardContext.getSuspendWrappedResponseAfterForward());
    assertTrue(actualStandardContext.getSwallowAbortedUploads());
    assertTrue(actualStandardContext.getUnpackWAR());
    assertTrue(actualStandardContext.getUseHttpOnly());
    assertTrue(actualStandardContext.getUseRelativeRedirects());
    assertTrue(actualStandardContext.getValidateClientProvidedNewSessionId());
    assertTrue(actualStandardContext.getXmlBlockExternal());
    assertTrue(actualStandardContext.isUseNaming());
    assertTrue(actualStandardContext.getThrowOnFailure());
    assertSame(findWelcomeFilesResult, actualStandardContext.getWelcomeFiles());
    ThreadBindingListener expectedThreadBindingListener = actualStandardContext.DEFAULT_NAMING_LISTENER;
    assertSame(expectedThreadBindingListener, actualStandardContext.getThreadBindingListener());
  }

  /**
   * Test {@link StandardContext#getEncodedReverseSolidusHandling()}.
   * <p>
   * Method under test: {@link StandardContext#getEncodedReverseSolidusHandling()}
   */
  @Test
  public void testGetEncodedReverseSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("decode", (new StandardContext()).getEncodedReverseSolidusHandling());
  }

  /**
   * Test {@link StandardContext#getEncodedSolidusHandling()}.
   * <p>
   * Method under test: {@link StandardContext#getEncodedSolidusHandling()}
   */
  @Test
  public void testGetEncodedSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("reject", (new StandardContext()).getEncodedSolidusHandling());
  }

  /**
   * Test {@link StandardContext#setEncodedSolidusHandling(String)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) EncodedSolidusHandling is {@code decode}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setEncodedSolidusHandling(String)}
   */
  @Test
  public void testSetEncodedSolidusHandling_thenStandardContextEncodedSolidusHandlingIsDecode() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setEncodedSolidusHandling("decode");

    // Assert
    assertEquals("decode", standardContext.getEncodedSolidusHandling());
    assertEquals(EncodedSolidusHandling.DECODE, standardContext.getEncodedSolidusHandlingEnum());
  }

  /**
   * Test {@link StandardContext#incrementInProgressAsyncCount()}.
   * <p>
   * Method under test: {@link StandardContext#incrementInProgressAsyncCount()}
   */
  @Test
  public void testIncrementInProgressAsyncCount() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.incrementInProgressAsyncCount();

    // Assert
    assertEquals(1L, standardContext.getInProgressAsyncCount());
  }

  /**
   * Test {@link StandardContext#decrementInProgressAsyncCount()}.
   * <p>
   * Method under test: {@link StandardContext#decrementInProgressAsyncCount()}
   */
  @Test
  public void testDecrementInProgressAsyncCount() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.decrementInProgressAsyncCount();

    // Assert
    assertEquals(-1L, standardContext.getInProgressAsyncCount());
  }

  /**
   * Test {@link StandardContext#getInProgressAsyncCount()}.
   * <p>
   * Method under test: {@link StandardContext#getInProgressAsyncCount()}
   */
  @Test
  public void testGetInProgressAsyncCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardContext()).getInProgressAsyncCount());
  }

  /**
   * Test {@link StandardContext#setResponseCharacterEncoding(String)}.
   * <p>
   * Method under test: {@link StandardContext#setResponseCharacterEncoding(String)}
   */
  @Test
  public void testSetResponseCharacterEncoding() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResponseCharacterEncoding("UTF-8");

    // Assert
    assertEquals("UTF-8", standardContext.getResponseCharacterEncoding());
  }

  /**
   * Test {@link StandardContext#setResponseCharacterEncoding(String)}.
   * <p>
   * Method under test: {@link StandardContext#setResponseCharacterEncoding(String)}
   */
  @Test
  public void testSetResponseCharacterEncoding2() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResponseCharacterEncoding(null);

    // Assert that nothing has changed
    assertNull(standardContext.getResponseCharacterEncoding());
  }

  /**
   * Test {@link StandardContext#setCookieProcessor(CookieProcessor)}.
   * <p>
   * Method under test: {@link StandardContext#setCookieProcessor(CookieProcessor)}
   */
  @Test
  public void testSetCookieProcessor() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();

    // Act
    standardContext.setCookieProcessor(cookieProcessor);

    // Assert
    assertSame(cookieProcessor, standardContext.getCookieProcessor());
  }

  /**
   * Test {@link StandardContext#setCookieProcessor(CookieProcessor)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setCookieProcessor(CookieProcessor)}
   */
  @Test
  public void testSetCookieProcessor_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).setCookieProcessor(null));
  }

  /**
   * Test {@link StandardContext#setWebappVersion(String)}.
   * <ul>
   *   <li>When {@code 1.0.2}.</li>
   *   <li>Then {@link StandardContext} (default constructor) WebappVersion is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setWebappVersion(String)}
   */
  @Test
  public void testSetWebappVersion_when102_thenStandardContextWebappVersionIs102() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setWebappVersion("1.0.2");

    // Assert
    assertEquals("1.0.2", standardContext.getWebappVersion());
    assertEquals("ROOT##1.0.2", standardContext.getBaseName());
  }

  /**
   * Test {@link StandardContext#setWebappVersion(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) WebappVersion is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setWebappVersion(String)}
   */
  @Test
  public void testSetWebappVersion_whenNull_thenStandardContextWebappVersionIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setWebappVersion(null);

    // Assert that nothing has changed
    assertEquals("", standardContext.getWebappVersion());
    assertEquals("ROOT", standardContext.getBaseName());
  }

  /**
   * Test {@link StandardContext#getBaseName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) WebappVersion is {@code 1.0.2}.</li>
   *   <li>Then return {@code ROOT##1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getBaseName()}
   */
  @Test
  public void testGetBaseName_givenStandardContextWebappVersionIs102_thenReturnRoot102() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setWebappVersion("1.0.2");

    // Act and Assert
    assertEquals("ROOT##1.0.2", standardContext.getBaseName());
  }

  /**
   * Test {@link StandardContext#getBaseName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code ROOT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getBaseName()}
   */
  @Test
  public void testGetBaseName_givenStandardContext_thenReturnRoot() {
    // Arrange, Act and Assert
    assertEquals("ROOT", (new StandardContext()).getBaseName());
  }

  /**
   * Test {@link StandardContext#getResourceOnlyServlets()}.
   * <p>
   * Method under test: {@link StandardContext#getResourceOnlyServlets()}
   */
  @Test
  public void testGetResourceOnlyServlets() {
    // Arrange, Act and Assert
    assertEquals("jsp", (new StandardContext()).getResourceOnlyServlets());
  }

  /**
   * Test {@link StandardContext#setResourceOnlyServlets(String)}.
   * <p>
   * Method under test: {@link StandardContext#setResourceOnlyServlets(String)}
   */
  @Test
  public void testSetResourceOnlyServlets() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResourceOnlyServlets("Resource Only Servlets");

    // Assert
    assertEquals("Resource Only Servlets", standardContext.getResourceOnlyServlets());
  }

  /**
   * Test {@link StandardContext#setResourceOnlyServlets(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setResourceOnlyServlets(String)}
   */
  @Test
  public void testSetResourceOnlyServlets_whenEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResourceOnlyServlets("");

    // Assert
    assertEquals("", standardContext.getResourceOnlyServlets());
  }

  /**
   * Test {@link StandardContext#setResourceOnlyServlets(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setResourceOnlyServlets(String)}
   */
  @Test
  public void testSetResourceOnlyServlets_whenNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResourceOnlyServlets(null);

    // Assert
    assertEquals("", standardContext.getResourceOnlyServlets());
  }

  /**
   * Test {@link StandardContext#isResourceOnlyServlet(String)}.
   * <p>
   * Method under test: {@link StandardContext#isResourceOnlyServlet(String)}
   */
  @Test
  public void testIsResourceOnlyServlet() {
    // Arrange, Act and Assert
    assertFalse((new StandardContext()).isResourceOnlyServlet("Servlet Name"));
  }

  /**
   * Test {@link StandardContext#getAuthenticator()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getAuthenticator()}
   */
  @Test
  public void testGetAuthenticator_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getAuthenticator());
  }

  /**
   * Test {@link StandardContext#getAuthenticator()}.
   * <ul>
   *   <li>Then return {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getAuthenticator()}
   */
  @Test
  public void testGetAuthenticator_thenReturnBasicAuthenticator() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    BasicAuthenticator valve = new BasicAuthenticator();
    standardContext.addValve(valve);

    // Act and Assert
    assertSame(valve, standardContext.getAuthenticator());
  }

  /**
   * Test {@link StandardContext#getJarScanner()}.
   * <ul>
   *   <li>Then JarScanFilter return {@link StandardJarScanFilter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getJarScanner()}
   */
  @Test
  public void testGetJarScanner_thenJarScanFilterReturnStandardJarScanFilter() {
    // Arrange and Act
    JarScanner actualJarScanner = (new StandardContext()).getJarScanner();

    // Assert
    JarScanFilter jarScanFilter = actualJarScanner.getJarScanFilter();
    assertTrue(jarScanFilter instanceof StandardJarScanFilter);
    assertTrue(actualJarScanner instanceof StandardJarScanner);
    assertNull(((StandardJarScanFilter) jarScanFilter).getPluggabilityScan());
    assertNull(((StandardJarScanFilter) jarScanFilter).getPluggabilitySkip());
    assertNull(((StandardJarScanFilter) jarScanFilter).getTldScan());
    assertNull(((StandardJarScanFilter) jarScanFilter).getTldSkip());
    assertFalse(jarScanFilter.isSkipAll());
    assertFalse(((StandardJarScanner) actualJarScanner).isScanAllFiles());
    assertFalse(((StandardJarScanner) actualJarScanner).isScanBootstrapClassPath());
    assertTrue(((StandardJarScanFilter) jarScanFilter).isDefaultPluggabilityScan());
    assertTrue(((StandardJarScanFilter) jarScanFilter).isDefaultTldScan());
    assertTrue(((StandardJarScanner) actualJarScanner).isScanAllDirectories());
    assertTrue(((StandardJarScanner) actualJarScanner).isScanClassPath());
    assertTrue(((StandardJarScanner) actualJarScanner).isScanManifest());
  }

  /**
   * Test {@link StandardContext#getJarScanner()}.
   * <ul>
   *   <li>Then return {@link StandardJarScanner} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getJarScanner()}
   */
  @Test
  public void testGetJarScanner_thenReturnStandardJarScanner() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    StandardJarScanner jarScanner = new StandardJarScanner();
    standardContext.setJarScanner(jarScanner);

    // Act and Assert
    assertSame(jarScanner, standardContext.getJarScanner());
  }

  /**
   * Test {@link StandardContext#setDelegate(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setDelegate(boolean)}
   */
  @Test
  public void testSetDelegate() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setDelegate(true);

    // Assert
    assertTrue(standardContext.getDelegate());
  }

  /**
   * Test {@link StandardContext#getApplicationEventListeners()}.
   * <p>
   * Method under test: {@link StandardContext#getApplicationEventListeners()}
   */
  @Test
  public void testGetApplicationEventListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).getApplicationEventListeners().length);
  }

  /**
   * Test {@link StandardContext#setApplicationEventListeners(Object[])}.
   * <ul>
   *   <li>Then first element is {@code Listeners}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setApplicationEventListeners(Object[])}
   */
  @Test
  public void testSetApplicationEventListeners_thenFirstElementIsListeners() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setApplicationEventListeners(new Object[]{"Listeners"});

    // Assert
    Object[] applicationEventListeners = standardContext.getApplicationEventListeners();
    assertEquals("Listeners", applicationEventListeners[0]);
    assertEquals(1, applicationEventListeners.length);
  }

  /**
   * Test {@link StandardContext#setApplicationEventListeners(Object[])}.
   * <ul>
   *   <li>When empty array of {@link Object}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setApplicationEventListeners(Object[])}
   */
  @Test
  public void testSetApplicationEventListeners_whenEmptyArrayOfObject_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setApplicationEventListeners(new Object[]{});

    // Assert that nothing has changed
    assertEquals(0, standardContext.getApplicationEventListeners().length);
  }

  /**
   * Test {@link StandardContext#setApplicationEventListeners(Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setApplicationEventListeners(Object[])}
   */
  @Test
  public void testSetApplicationEventListeners_whenNull_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setApplicationEventListeners(null);

    // Assert that nothing has changed
    assertEquals(0, standardContext.getApplicationEventListeners().length);
  }

  /**
   * Test {@link StandardContext#addApplicationEventListener(Object)}.
   * <p>
   * Method under test: {@link StandardContext#addApplicationEventListener(Object)}
   */
  @Test
  public void testAddApplicationEventListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addApplicationEventListener("Listener");

    // Assert
    Object[] applicationEventListeners = standardContext.getApplicationEventListeners();
    assertEquals("Listener", applicationEventListeners[0]);
    assertEquals(1, applicationEventListeners.length);
  }

  /**
   * Test {@link StandardContext#addApplicationLifecycleListener(Object)}.
   * <p>
   * Method under test: {@link StandardContext#addApplicationLifecycleListener(Object)}
   */
  @Test
  public void testAddApplicationLifecycleListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addApplicationLifecycleListener("Listener");

    // Assert
    Object[] applicationLifecycleListeners = standardContext.getApplicationLifecycleListeners();
    assertEquals("Listener", applicationLifecycleListeners[0]);
    assertEquals(1, applicationLifecycleListeners.length);
  }

  /**
   * Test {@link StandardContext#setAntiResourceLocking(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setAntiResourceLocking(boolean)}
   */
  @Test
  public void testSetAntiResourceLocking() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setAntiResourceLocking(true);

    // Assert
    assertTrue(standardContext.getAntiResourceLocking());
  }

  /**
   * Test {@link StandardContext#setParallelAnnotationScanning(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setParallelAnnotationScanning(boolean)}
   */
  @Test
  public void testSetParallelAnnotationScanning() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setParallelAnnotationScanning(true);

    // Assert
    assertTrue(standardContext.getParallelAnnotationScanning());
  }

  /**
   * Test {@link StandardContext#setCharsetMapper(CharsetMapper)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) CharsetMapper is {@link CharsetMapper#CharsetMapper()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setCharsetMapper(CharsetMapper)}
   */
  @Test
  public void testSetCharsetMapper_thenStandardContextCharsetMapperIsCharsetMapper() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    CharsetMapper mapper = new CharsetMapper();

    // Act
    standardContext.setCharsetMapper(mapper);

    // Assert
    assertSame(mapper, standardContext.getCharsetMapper());
  }

  /**
   * Test {@link StandardContext#getCharset(Locale)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code ISO-8859-1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getCharset(Locale)}
   */
  @Test
  public void testGetCharset_givenStandardContext_thenReturnIso88591() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertEquals("ISO-8859-1", standardContext.getCharset(Locale.getDefault()));
  }

  /**
   * Test {@link StandardContext#getCharset(Locale)}.
   * <ul>
   *   <li>Then return {@code en}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getCharset(Locale)}
   */
  @Test
  public void testGetCharset_thenReturnEn() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addLocaleEncodingMappingParameter("en", "en");

    // Act and Assert
    assertEquals("en", standardContext.getCharset(Locale.getDefault()));
  }

  /**
   * Test {@link StandardContext#setConfigured(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setConfigured(boolean)}
   */
  @Test
  public void testSetConfigured() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setConfigured(true);

    // Assert
    assertTrue(standardContext.getConfigured());
  }

  /**
   * Test {@link StandardContext#setSessionCookieName(String)}.
   * <p>
   * Method under test: {@link StandardContext#setSessionCookieName(String)}
   */
  @Test
  public void testSetSessionCookieName() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSessionCookieName("Session Cookie Name");

    // Assert
    assertEquals("Session Cookie Name", standardContext.getSessionCookieName());
  }

  /**
   * Test {@link StandardContext#setUsePartitioned(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setUsePartitioned(boolean)}
   */
  @Test
  public void testSetUsePartitioned() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setUsePartitioned(true);

    // Assert
    assertTrue(standardContext.getUsePartitioned());
  }

  /**
   * Test {@link StandardContext#setSessionCookieDomain(String)}.
   * <p>
   * Method under test: {@link StandardContext#setSessionCookieDomain(String)}
   */
  @Test
  public void testSetSessionCookieDomain() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSessionCookieDomain("Session Cookie Domain");

    // Assert
    assertEquals("Session Cookie Domain", standardContext.getSessionCookieDomain());
  }

  /**
   * Test {@link StandardContext#setSessionCookiePath(String)}.
   * <p>
   * Method under test: {@link StandardContext#setSessionCookiePath(String)}
   */
  @Test
  public void testSetSessionCookiePath() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSessionCookiePath("Session Cookie Path");

    // Assert
    assertEquals("Session Cookie Path", standardContext.getSessionCookiePath());
  }

  /**
   * Test {@link StandardContext#setCrossContext(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setCrossContext(boolean)}
   */
  @Test
  public void testSetCrossContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setCrossContext(true);

    // Assert
    assertTrue(standardContext.getCrossContext());
  }

  /**
   * Test {@link StandardContext#setAltDDName(String)}.
   * <p>
   * Method under test: {@link StandardContext#setAltDDName(String)}
   */
  @Test
  public void testSetAltDDName() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setAltDDName("Alt DDName");

    // Assert
    assertEquals("Alt DDName", standardContext.getAltDDName());
  }

  /**
   * Test {@link StandardContext#setDisplayName(String)}.
   * <p>
   * Method under test: {@link StandardContext#setDisplayName(String)}
   */
  @Test
  public void testSetDisplayName() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setDisplayName("Display Name");

    // Assert
    assertEquals("Display Name", standardContext.getDisplayName());
  }

  /**
   * Test {@link StandardContext#setDistributable(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setDistributable(boolean)}
   */
  @Test
  public void testSetDistributable() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setDistributable(true);

    // Assert
    assertTrue(standardContext.getDistributable());
  }

  /**
   * Test {@link StandardContext#getLoader()}.
   * <p>
   * Method under test: {@link StandardContext#getLoader()}
   */
  @Test
  public void testGetLoader() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getLoader());
  }

  /**
   * Test {@link StandardContext#setLoader(Loader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Loader is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoader(Loader)}
   */
  @Test
  public void testSetLoader_whenNull_thenStandardContextLoaderIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setLoader(null);

    // Assert that nothing has changed
    assertNull(standardContext.getLoader());
  }

  /**
   * Test {@link StandardContext#setLoader(Loader)}.
   * <ul>
   *   <li>When {@link WebappLoader} (default constructor).</li>
   *   <li>Then {@link WebappLoader} (default constructor) Context {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoader(Loader)}
   */
  @Test
  public void testSetLoader_whenWebappLoader_thenWebappLoaderContextStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    WebappLoader loader = new WebappLoader();

    // Act
    standardContext.setLoader(loader);

    // Assert
    Context context = loader.getContext();
    assertTrue(context instanceof StandardContext);
    assertEquals("Catalina", loader.getDomain());
    assertSame(standardContext, context);
    assertSame(loader, standardContext.getLoader());
  }

  /**
   * Test {@link StandardContext#getManager()}.
   * <p>
   * Method under test: {@link StandardContext#getManager()}
   */
  @Test
  public void testGetManager() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getManager());
  }

  /**
   * Test {@link StandardContext#setManager(Manager)}.
   * <ul>
   *   <li>When {@link BackupManager} (default constructor).</li>
   *   <li>Then {@link BackupManager} (default constructor) Context {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setManager(Manager)}
   */
  @Test
  public void testSetManager_whenBackupManager_thenBackupManagerContextStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    BackupManager manager = new BackupManager();

    // Act
    standardContext.setManager(manager);

    // Assert
    Context context = manager.getContext();
    assertTrue(context instanceof StandardContext);
    assertTrue(standardContext.manager instanceof BackupManager);
    assertEquals("Catalina", manager.getDomainInternal());
    assertEquals("Catalina", manager.getDomain());
    assertEquals(1, manager.getClassLoaders().length);
    assertSame(standardContext, context);
    assertSame(manager, standardContext.getManager());
  }

  /**
   * Test {@link StandardContext#setManager(Manager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Manager is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setManager(Manager)}
   */
  @Test
  public void testSetManager_whenNull_thenStandardContextManagerIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setManager(null);

    // Assert that nothing has changed
    assertNull(standardContext.getManager());
    assertNull(standardContext.manager);
  }

  /**
   * Test {@link StandardContext#setIgnoreAnnotations(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setIgnoreAnnotations(boolean)}
   */
  @Test
  public void testSetIgnoreAnnotations() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setIgnoreAnnotations(true);

    // Assert
    assertTrue(standardContext.getIgnoreAnnotations());
  }

  /**
   * Test {@link StandardContext#setMetadataComplete(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setMetadataComplete(boolean)}
   */
  @Test
  public void testSetMetadataComplete() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setMetadataComplete(true);

    // Assert
    assertTrue(standardContext.getMetadataComplete());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@link LoginConfig#LoginConfig()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_givenStandardContext_whenLoginConfig() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    LoginConfig config = new LoginConfig();

    // Act
    standardContext.setLoginConfig(config);

    // Assert
    assertNull(config.getErrorPage());
    assertNull(config.getLoginPage());
    assertSame(config, standardContext.getLoginConfig());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>Then {@link LoginConfig#LoginConfig()} ErrorPage is {@code /Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_thenLoginConfigErrorPageIsConfig() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    LoginConfig config = new LoginConfig();
    config.setLoginPage(null);
    config.setErrorPage("Config");
    config.setCharset(null);

    // Act
    standardContext.setLoginConfig(config);

    // Assert
    assertEquals("/Config", config.getErrorPage());
    assertNull(config.getLoginPage());
    assertSame(config, standardContext.getLoginConfig());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>Then {@link LoginConfig#LoginConfig()} ErrorPage is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_thenLoginConfigErrorPageIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    LoginConfig config = new LoginConfig();
    config.setLoginPage(null);
    config.setErrorPage("/");
    config.setCharset(null);

    // Act
    standardContext.setLoginConfig(config);

    // Assert
    assertEquals("/", config.getErrorPage());
    assertNull(config.getLoginPage());
    assertSame(config, standardContext.getLoginConfig());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>Then {@link LoginConfig#LoginConfig()} LoginPage is {@code /Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_thenLoginConfigLoginPageIsConfig() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    LoginConfig config = new LoginConfig();
    config.setLoginPage("Config");
    config.setErrorPage(null);
    config.setCharset(null);

    // Act
    standardContext.setLoginConfig(config);

    // Assert
    assertEquals("/Config", config.getLoginPage());
    assertNull(config.getErrorPage());
    assertSame(config, standardContext.getLoginConfig());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>Then {@link LoginConfig#LoginConfig()} LoginPage is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_thenLoginConfigLoginPageIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    LoginConfig config = new LoginConfig();
    config.setLoginPage("/");
    config.setErrorPage(null);
    config.setCharset(null);

    // Act
    standardContext.setLoginConfig(config);

    // Assert
    assertEquals("/", config.getLoginPage());
    assertNull(config.getErrorPage());
    assertSame(config, standardContext.getLoginConfig());
  }

  /**
   * Test {@link StandardContext#setLoginConfig(LoginConfig)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setLoginConfig(LoginConfig)}
   */
  @Test
  public void testSetLoginConfig_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.setLoginConfig(null));
  }

  /**
   * Test {@link StandardContext#getNamingResources()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then Container return {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getNamingResources()}
   */
  @Test
  public void testGetNamingResources_givenStandardContext_thenContainerReturnStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    NamingResourcesImpl actualNamingResources = standardContext.getNamingResources();

    // Assert
    Object container = actualNamingResources.getContainer();
    assertTrue(container instanceof StandardContext);
    assertEquals("Catalina", actualNamingResources.getDomain());
    assertEquals("NEW", actualNamingResources.getStateName());
    assertNull(actualNamingResources.getObjectName());
    assertNull(actualNamingResources.getTransaction());
    assertEquals(0, actualNamingResources.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualNamingResources.getState());
    assertTrue(actualNamingResources.getThrowOnFailure());
    assertSame(standardContext, container);
  }

  /**
   * Test {@link StandardContext#getNamingResources()}.
   * <ul>
   *   <li>Then return {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getNamingResources()}
   */
  @Test
  public void testGetNamingResources_thenReturnNamingResourcesImpl() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    NamingResourcesImpl namingResources = new NamingResourcesImpl();
    standardContext.setNamingResources(namingResources);

    // Act and Assert
    assertSame(namingResources, standardContext.getNamingResources());
  }

  /**
   * Test {@link StandardContext#setNamingResources(NamingResourcesImpl)}.
   * <ul>
   *   <li>Then {@link NamingResourcesImpl} (default constructor) Container {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setNamingResources(NamingResourcesImpl)}
   */
  @Test
  public void testSetNamingResources_thenNamingResourcesImplContainerStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    NamingResourcesImpl namingResources = new NamingResourcesImpl();

    // Act
    standardContext.setNamingResources(namingResources);

    // Assert
    Object container = namingResources.getContainer();
    assertTrue(container instanceof StandardContext);
    assertSame(standardContext, container);
    assertSame(namingResources, standardContext.getNamingResources());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_givenStandardContextNameIsSlash_thenStandardContextNameIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");

    // Act
    standardContext.setPath("Path");

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(",context=/,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("/", standardContext.getName());
    assertEquals("/Path", standardContext.getEncodedPath());
    assertEquals("/Path", standardContext.getPath());
    assertEquals("Path", standardContext.getBaseName());
    assertEquals("type=Valve,context=/,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_givenStandardContext_whenNull_thenStandardContextNameIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPath(null);

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("", standardContext.getName());
    assertEquals("", standardContext.getEncodedPath());
    assertEquals("", standardContext.getPath());
    assertEquals(",context=/,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("ROOT", standardContext.getBaseName());
    assertEquals("type=Valve,context=/,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@code /}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_givenStandardContext_whenSlash_thenStandardContextNameIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPath("/");

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("", standardContext.getName());
    assertEquals("", standardContext.getEncodedPath());
    assertEquals("", standardContext.getPath());
    assertEquals(",context=/,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("ROOT", standardContext.getBaseName());
    assertEquals("type=Valve,context=/,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>Then {@link ReplicatedContext} (default constructor) Pipeline Basic {@link StandardContextValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_thenReplicatedContextPipelineBasicStandardContextValve() {
    // Arrange
    ReplicatedContext replicatedContext = new ReplicatedContext();

    // Act
    replicatedContext.setPath("Path");

    // Assert
    Pipeline pipeline = replicatedContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(",context=/Path,container0=null", replicatedContext.getMBeanKeyProperties());
    assertEquals("/Path", replicatedContext.getName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/Path]", replicatedContext.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/Path]", replicatedContext.logName);
    assertEquals("type=Valve,context=/Path,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) MBeanKeyProperties is {@code ,context=/Path,container0=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_thenStandardContextMBeanKeyPropertiesIsContextPathContainer0Null() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPath("Path");

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(",context=/Path,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("/Path", standardContext.getName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/Path]", standardContext.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/Path]", standardContext.logName);
    assertEquals("type=Valve,context=/Path,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPath(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setPath(String)}
   */
  @Test
  public void testSetPath_whenEmptyString_thenStandardContextNameIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPath("");

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("", standardContext.getName());
    assertEquals("", standardContext.getEncodedPath());
    assertEquals("", standardContext.getPath());
    assertEquals(",context=/,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("ROOT", standardContext.getBaseName());
    assertEquals("type=Valve,context=/,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#setPublicId(String)}.
   * <p>
   * Method under test: {@link StandardContext#setPublicId(String)}
   */
  @Test
  public void testSetPublicId() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPublicId("42");

    // Assert
    assertEquals("42", standardContext.getPublicId());
  }

  /**
   * Test {@link StandardContext#getParentClassLoader()}.
   * <p>
   * Method under test: {@link StandardContext#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParentClassLoader(new ParallelWebappClassLoader());
    standardContext.setPrivileged(false);
    standardContext.setParent(null);

    // Act and Assert
    assertNotNull(standardContext.getParentClassLoader());
  }

  /**
   * Test {@link StandardContext#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardContext() {
    // Arrange, Act and Assert
    assertNotNull((new StandardContext()).getParentClassLoader());
  }

  /**
   * Test {@link StandardContext#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) ParentClassLoader is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardContextParentClassLoaderIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParentClassLoader(null);
    standardContext.setPrivileged(false);
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNotNull(standardContext.getParentClassLoader());
  }

  /**
   * Test {@link StandardContext#setPrivileged(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setPrivileged(boolean)}
   */
  @Test
  public void testSetPrivileged() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setPrivileged(true);

    // Assert
    assertTrue(standardContext.getPrivileged());
  }

  /**
   * Test {@link StandardContext#setReloadable(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setReloadable(boolean)}
   */
  @Test
  public void testSetReloadable() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setReloadable(true);

    // Assert
    assertTrue(standardContext.getReloadable());
  }

  /**
   * Test {@link StandardContext#setOverride(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setOverride(boolean)}
   */
  @Test
  public void testSetOverride() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setOverride(true);

    // Assert
    assertTrue(standardContext.getOverride());
  }

  /**
   * Test {@link StandardContext#setSessionTimeout(int)}.
   * <ul>
   *   <li>When {@link ApplicationFilterChain#INCREMENT}.</li>
   *   <li>Then {@link StandardContext} (default constructor) SessionTimeout is {@link ApplicationFilterChain#INCREMENT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setSessionTimeout(int)}
   */
  @Test
  public void testSetSessionTimeout_whenIncrement_thenStandardContextSessionTimeoutIsIncrement() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSessionTimeout(ApplicationFilterChain.INCREMENT);

    // Assert
    assertEquals(ApplicationFilterChain.INCREMENT, standardContext.getSessionTimeout());
  }

  /**
   * Test {@link StandardContext#setSessionTimeout(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link StandardContext} (default constructor) SessionTimeout is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setSessionTimeout(int)}
   */
  @Test
  public void testSetSessionTimeout_whenZero_thenStandardContextSessionTimeoutIsMinusOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSessionTimeout(0);

    // Assert
    assertEquals(-1, standardContext.getSessionTimeout());
  }

  /**
   * Test {@link StandardContext#setSwallowOutput(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setSwallowOutput(boolean)}
   */
  @Test
  public void testSetSwallowOutput() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setSwallowOutput(true);

    // Assert
    assertTrue(standardContext.getSwallowOutput());
  }

  /**
   * Test {@link StandardContext#setUnloadDelay(long)}.
   * <p>
   * Method under test: {@link StandardContext#setUnloadDelay(long)}
   */
  @Test
  public void testSetUnloadDelay() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setUnloadDelay(1L);

    // Assert
    assertEquals(1L, standardContext.getUnloadDelay());
  }

  /**
   * Test {@link StandardContext#setWrapperClass(String)}.
   * <p>
   * Method under test: {@link StandardContext#setWrapperClass(String)}
   */
  @Test
  public void testSetWrapperClass() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).setWrapperClass("Wrapper Class Name"));
  }

  /**
   * Test {@link StandardContext#getResources()}.
   * <p>
   * Method under test: {@link StandardContext#getResources()}
   */
  @Test
  public void testGetResources() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getResources());
  }

  /**
   * Test {@link StandardContext#setResources(WebResourceRoot)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Resources is {@link TesterWebResourceRoot} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setResources(WebResourceRoot)}
   */
  @Test
  public void testSetResources_thenStandardContextResourcesIsTesterWebResourceRoot() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    TesterWebResourceRoot resources = new TesterWebResourceRoot();

    // Act
    standardContext.setResources(resources);

    // Assert
    assertSame(resources, standardContext.getResources());
  }

  /**
   * Test {@link StandardContext#setResources(WebResourceRoot)}.
   * <ul>
   *   <li>When {@link ExtractingRoot} (default constructor).</li>
   *   <li>Then {@link ExtractingRoot} (default constructor) Context {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setResources(WebResourceRoot)}
   */
  @Test
  public void testSetResources_whenExtractingRoot_thenExtractingRootContextStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    standardContext.setResources(resources);

    // Assert
    Context context = resources.getContext();
    assertTrue(context instanceof StandardContext);
    assertEquals("Catalina", resources.getDomain());
    assertSame(standardContext, context);
    assertSame(resources, standardContext.getResources());
  }

  /**
   * Test {@link StandardContext#setResources(WebResourceRoot)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Resources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#setResources(WebResourceRoot)}
   */
  @Test
  public void testSetResources_whenNull_thenStandardContextResourcesIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setResources(null);

    // Assert that nothing has changed
    assertNull(standardContext.getResources());
  }

  /**
   * Test {@link StandardContext#setCharsetMapperClass(String)}.
   * <p>
   * Method under test: {@link StandardContext#setCharsetMapperClass(String)}
   */
  @Test
  public void testSetCharsetMapperClass() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setCharsetMapperClass("Mapper");

    // Assert
    assertEquals("Mapper", standardContext.getCharsetMapperClass());
  }

  /**
   * Test {@link StandardContext#getWorkPath()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getWorkPath()}
   */
  @Test
  public void testGetWorkPath_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getWorkPath());
  }

  /**
   * Test {@link StandardContext#setWorkDir(String)}.
   * <p>
   * Method under test: {@link StandardContext#setWorkDir(String)}
   */
  @Test
  public void testSetWorkDir() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setWorkDir("Work Dir");

    // Assert
    assertEquals("Work Dir", standardContext.getWorkDir());
  }

  /**
   * Test {@link StandardContext#setClearReferencesStopThreads(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setClearReferencesStopThreads(boolean)}
   */
  @Test
  public void testSetClearReferencesStopThreads() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setClearReferencesStopThreads(true);

    // Assert
    assertTrue(standardContext.getClearReferencesStopThreads());
  }

  /**
   * Test {@link StandardContext#setClearReferencesStopTimerThreads(boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setClearReferencesStopTimerThreads(boolean)}
   */
  @Test
  public void testSetClearReferencesStopTimerThreads() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setClearReferencesStopTimerThreads(true);

    // Assert
    assertTrue(standardContext.getClearReferencesStopTimerThreads());
  }

  /**
   * Test {@link StandardContext#setFailCtxIfServletStartFails(Boolean)}.
   * <p>
   * Method under test: {@link StandardContext#setFailCtxIfServletStartFails(Boolean)}
   */
  @Test
  public void testSetFailCtxIfServletStartFails() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setFailCtxIfServletStartFails(true);

    // Assert
    assertTrue(standardContext.getComputedFailCtxIfServletStartFails());
    assertTrue(standardContext.getFailCtxIfServletStartFails());
  }

  /**
   * Test {@link StandardContext#getComputedFailCtxIfServletStartFails()}.
   * <p>
   * Method under test: {@link StandardContext#getComputedFailCtxIfServletStartFails()}
   */
  @Test
  public void testGetComputedFailCtxIfServletStartFails() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setFailCtxIfServletStartFails(null);
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertFalse(standardContext.getComputedFailCtxIfServletStartFails());
  }

  /**
   * Test {@link StandardContext#getComputedFailCtxIfServletStartFails()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getComputedFailCtxIfServletStartFails()}
   */
  @Test
  public void testGetComputedFailCtxIfServletStartFails_givenStandardContext_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardContext()).getComputedFailCtxIfServletStartFails());
  }

  /**
   * Test {@link StandardContext#getComputedFailCtxIfServletStartFails()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getComputedFailCtxIfServletStartFails()}
   */
  @Test
  public void testGetComputedFailCtxIfServletStartFails_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setFailCtxIfServletStartFails(true);
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertTrue(standardContext.getComputedFailCtxIfServletStartFails());
  }

  /**
   * Test {@link StandardContext#addApplicationListener(String)}.
   * <p>
   * Method under test: {@link StandardContext#addApplicationListener(String)}
   */
  @Test
  public void testAddApplicationListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addApplicationListener("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findApplicationListeners());
  }

  /**
   * Test {@link StandardContext#addApplicationListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addApplicationListener(String)}
   */
  @Test
  public void testAddApplicationListener_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addApplicationListener("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findApplicationListeners());
  }

  /**
   * Test {@link StandardContext#addApplicationListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addApplicationListener(String)}
   */
  @Test
  public void testAddApplicationListener_givenStandardContextAddApplicationListenerListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationListener("Listener");

    // Act
    standardContext.addApplicationListener("Listener");

    // Assert that nothing has changed
    assertArrayEquals(new String[]{"Listener"}, standardContext.findApplicationListeners());
  }

  /**
   * Test {@link StandardContext#addApplicationParameter(ApplicationParameter)}.
   * <p>
   * Method under test: {@link StandardContext#addApplicationParameter(ApplicationParameter)}
   */
  @Test
  public void testAddApplicationParameter() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("addApplicationParameter");
    parameter.setOverride(true);
    parameter.setValue("42");

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationParameter(parameter);

    ApplicationParameter parameter2 = new ApplicationParameter();
    parameter2.setDescription("The characteristics of someone or something");
    parameter2.setName("Name");
    parameter2.setOverride(true);
    parameter2.setValue("42");

    // Act
    standardContext.addApplicationParameter(parameter2);

    // Assert
    ApplicationParameter[] findApplicationParametersResult = standardContext.findApplicationParameters();
    assertEquals(2, findApplicationParametersResult.length);
    assertSame(parameter, findApplicationParametersResult[0]);
    assertSame(parameter2, findApplicationParametersResult[1]);
  }

  /**
   * Test {@link StandardContext#addApplicationParameter(ApplicationParameter)}.
   * <p>
   * Method under test: {@link StandardContext#addApplicationParameter(ApplicationParameter)}
   */
  @Test
  public void testAddApplicationParameter2() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    // Act
    standardContext.addApplicationParameter(parameter);

    // Assert
    ApplicationParameter[] findApplicationParametersResult = standardContext.findApplicationParameters();
    assertEquals(1, findApplicationParametersResult.length);
    assertSame(parameter, findApplicationParametersResult[0]);
  }

  /**
   * Test {@link StandardContext#addApplicationParameter(ApplicationParameter)}.
   * <ul>
   *   <li>Given {@link ApplicationParameter} (default constructor) Description is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addApplicationParameter(ApplicationParameter)}
   */
  @Test
  public void testAddApplicationParameter_givenApplicationParameterDescriptionIsName() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("addApplicationParameter");
    parameter.setOverride(true);
    parameter.setValue("42");

    ApplicationParameter parameter2 = new ApplicationParameter();
    parameter2.setDescription("Name");
    parameter2.setName("Name");
    parameter2.setOverride(false);
    parameter2.setValue("Name");

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationParameter(parameter2);
    standardContext.addApplicationParameter(parameter);

    ApplicationParameter parameter3 = new ApplicationParameter();
    parameter3.setDescription("The characteristics of someone or something");
    parameter3.setName("Name");
    parameter3.setOverride(true);
    parameter3.setValue("42");

    // Act
    standardContext.addApplicationParameter(parameter3);

    // Assert that nothing has changed
    ApplicationParameter[] findApplicationParametersResult = standardContext.findApplicationParameters();
    assertEquals(2, findApplicationParametersResult.length);
    assertSame(parameter2, findApplicationParametersResult[0]);
    assertSame(parameter, findApplicationParametersResult[1]);
  }

  /**
   * Test {@link StandardContext#addApplicationParameter(ApplicationParameter)}.
   * <ul>
   *   <li>Given {@link ApplicationParameter} (default constructor) Name is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addApplicationParameter(ApplicationParameter)}
   */
  @Test
  public void testAddApplicationParameter_givenApplicationParameterNameIsName() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationParameter(parameter);

    ApplicationParameter parameter2 = new ApplicationParameter();
    parameter2.setDescription("The characteristics of someone or something");
    parameter2.setName("Name");
    parameter2.setOverride(true);
    parameter2.setValue("42");

    // Act
    standardContext.addApplicationParameter(parameter2);

    // Assert
    ApplicationParameter[] findApplicationParametersResult = standardContext.findApplicationParameters();
    assertEquals(2, findApplicationParametersResult.length);
    assertSame(parameter, findApplicationParametersResult[0]);
    assertSame(parameter2, findApplicationParametersResult[1]);
  }

  /**
   * Test {@link StandardContext#addApplicationParameter(ApplicationParameter)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addApplicationParameter(ApplicationParameter)}
   */
  @Test
  public void testAddApplicationParameter_givenStandardContext_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    // Act
    standardContext.addApplicationParameter(parameter);

    // Assert
    ApplicationParameter[] findApplicationParametersResult = standardContext.findApplicationParameters();
    assertEquals(1, findApplicationParametersResult.length);
    assertSame(parameter, findApplicationParametersResult[0]);
  }

  /**
   * Test {@link StandardContext#addChild(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addChild(Container)}
   */
  @Test
  public void testAddChild_whenStandardContext_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addChild(new StandardContext()));
  }

  /**
   * Test {@link StandardContext#addChild(Container)}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) Parent {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addChild(Container)}
   */
  @Test
  public void testAddChild_whenStandardWrapper_thenStandardWrapperParentStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    StandardWrapper child = new StandardWrapper();

    // Act
    standardContext.addChild(child);

    // Assert
    Container parent = child.getParent();
    assertTrue(parent instanceof StandardContext);
    assertEquals("Catalina", child.getDomainInternal());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.logName);
    HashMap<String, Container> stringContainerMap = standardContext.children;
    assertEquals(1, stringContainerMap.size());
    assertEquals(1, standardContext.getChildren().length);
    assertTrue(stringContainerMap.containsKey(null));
    assertTrue(child.parameters.isEmpty());
    assertTrue(child.references.isEmpty());
    assertEquals(Long.MAX_VALUE, standardContext.getMinTime());
    assertSame(standardContext, parent);
  }

  /**
   * Test {@link StandardContext#addConstraint(SecurityConstraint)}.
   * <ul>
   *   <li>Given {@link SecurityCollection#SecurityCollection()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addConstraint(SecurityConstraint)}
   */
  @Test
  public void testAddConstraint_givenSecurityCollection() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    SecurityConstraint constraint = new SecurityConstraint();
    constraint.addCollection(new SecurityCollection());

    // Act
    standardContext.addConstraint(constraint);

    // Assert
    SecurityConstraint[] findConstraintsResult = standardContext.findConstraints();
    assertEquals(1, findConstraintsResult.length);
    assertSame(constraint, findConstraintsResult[0]);
  }

  /**
   * Test {@link StandardContext#addConstraint(SecurityConstraint)}.
   * <ul>
   *   <li>When {@link SecurityConstraint} (default constructor).</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addConstraint(SecurityConstraint)}
   */
  @Test
  public void testAddConstraint_whenSecurityConstraint_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    SecurityConstraint constraint = new SecurityConstraint();

    // Act
    standardContext.addConstraint(constraint);

    // Assert
    SecurityConstraint[] findConstraintsResult = standardContext.findConstraints();
    assertEquals(1, findConstraintsResult.length);
    assertSame(constraint, findConstraintsResult[0]);
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setExceptionType("Exception Type");
    errorPage.setLocation("Location");

    // Act
    standardContext.addErrorPage(errorPage);

    // Assert
    assertEquals("/Location", errorPage.getLocation());
    ErrorPage[] findErrorPagesResult = standardContext.findErrorPages();
    assertEquals(1, findErrorPagesResult.length);
    assertSame(errorPage, findErrorPagesResult[0]);
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <ul>
   *   <li>Given forName {@code UTF-8}.</li>
   *   <li>Then {@link ErrorPage} (default constructor) Location is {@code /Location}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage_givenForNameUtf8_thenErrorPageLocationIsLocation() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setExceptionType("Exception Type");
    errorPage.setLocation("Location");

    // Act
    standardContext.addErrorPage(errorPage);

    // Assert
    assertEquals("/Location", errorPage.getLocation());
    ErrorPage[] findErrorPagesResult = standardContext.findErrorPages();
    assertEquals(1, findErrorPagesResult.length);
    assertSame(errorPage, findErrorPagesResult[0]);
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <ul>
   *   <li>Given {@code /}.</li>
   *   <li>Then {@link ErrorPage} (default constructor) Location is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage_givenSlash_thenErrorPageLocationIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    ErrorPage errorPage = new ErrorPage();
    errorPage.setErrorCode(-1);
    errorPage.setLocation("/");
    errorPage.setExceptionType(null);
    errorPage.setCharset(null);

    // Act
    standardContext.addErrorPage(errorPage);

    // Assert
    assertEquals("/", errorPage.getLocation());
    ErrorPage[] findErrorPagesResult = standardContext.findErrorPages();
    assertEquals(1, findErrorPagesResult.length);
    assertSame(errorPage, findErrorPagesResult[0]);
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage_givenStandardContext_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setExceptionType("Exception Type");
    errorPage.setLocation("Location");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addErrorPage(errorPage));
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <ul>
   *   <li>When {@link ErrorPage} (default constructor) Location is {@code null}.</li>
   *   <li>Then {@link ErrorPage} (default constructor) Location is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage_whenErrorPageLocationIsNull_thenErrorPageLocationIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    ErrorPage errorPage = new ErrorPage();
    errorPage.setErrorCode(-1);
    errorPage.setLocation(null);
    errorPage.setExceptionType(null);
    errorPage.setCharset(null);

    // Act
    standardContext.addErrorPage(errorPage);

    // Assert
    assertNull(errorPage.getLocation());
    ErrorPage[] findErrorPagesResult = standardContext.findErrorPages();
    assertEquals(1, findErrorPagesResult.length);
    assertSame(errorPage, findErrorPagesResult[0]);
  }

  /**
   * Test {@link StandardContext#addErrorPage(ErrorPage)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addErrorPage(ErrorPage)}
   */
  @Test
  public void testAddErrorPage_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addErrorPage(null));
  }

  /**
   * Test {@link StandardContext#addFilterMap(FilterMap)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addFilterMap(FilterMap)}
   */
  @Test
  public void testAddFilterMap_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addFilterMap(new FilterMap()));
  }

  /**
   * Test {@link StandardContext#addFilterMap(FilterMap)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addFilterDef {@link FilterDef} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addFilterMap(FilterMap)}
   */
  @Test
  public void testAddFilterMap_givenStandardContextAddFilterDefFilterDef() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addFilterMap(new FilterMap()));
  }

  /**
   * Test {@link StandardContext#addFilterMapBefore(FilterMap)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addFilterMapBefore(FilterMap)}
   */
  @Test
  public void testAddFilterMapBefore_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addFilterMapBefore(new FilterMap()));
  }

  /**
   * Test {@link StandardContext#addFilterMapBefore(FilterMap)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addFilterDef {@link FilterDef} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addFilterMapBefore(FilterMap)}
   */
  @Test
  public void testAddFilterMapBefore_givenStandardContextAddFilterDefFilterDef() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addFilterMapBefore(new FilterMap()));
  }

  /**
   * Test {@link StandardContext#addParameter(String, String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addParameter {@code Name} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addParameter(String, String)}
   */
  @Test
  public void testAddParameter_givenStandardContextAddParameterNameAnd42() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addParameter("Name", "42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addParameter("Name", "42"));
  }

  /**
   * Test {@link StandardContext#addParameter(String, String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addParameter(String, String)}
   */
  @Test
  public void testAddParameter_givenStandardContext_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addParameter(null, null));
  }

  /**
   * Test {@link StandardContext#addParameter(String, String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addParameter(String, String)}
   */
  @Test
  public void testAddParameter_givenStandardContext_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addParameter("Name", null));
  }

  /**
   * Test {@link StandardContext#addSecurityRole(String)}.
   * <p>
   * Method under test: {@link StandardContext#addSecurityRole(String)}
   */
  @Test
  public void testAddSecurityRole() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addSecurityRole("Role");

    // Assert
    assertArrayEquals(new String[]{"Role"}, standardContext.findSecurityRoles());
  }

  /**
   * Test {@link StandardContext#addSecurityRole(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addSecurityRole(String)}
   */
  @Test
  public void testAddSecurityRole_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addSecurityRole("Role");

    // Assert
    assertArrayEquals(new String[]{"Role"}, standardContext.findSecurityRoles());
  }

  /**
   * Test {@link StandardContext#addServletMappingDecoded(String, String, boolean)} with {@code pattern}, {@code name}, {@code jspWildCard}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addServletMappingDecoded(String, String, boolean)}
   */
  @Test
  public void testAddServletMappingDecodedWithPatternNameJspWildCard_whenName() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new StandardContext()).addServletMappingDecoded("Pattern", "Name", true));
  }

  /**
   * Test {@link StandardContext#addServletMappingDecoded(String, String, boolean)} with {@code pattern}, {@code name}, {@code jspWildCard}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addServletMappingDecoded(String, String, boolean)}
   */
  @Test
  public void testAddServletMappingDecodedWithPatternNameJspWildCard_whenNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new StandardContext()).addServletMappingDecoded("Pattern", null, true));
  }

  /**
   * Test {@link StandardContext#addWatchedResource(String)}.
   * <p>
   * Method under test: {@link StandardContext#addWatchedResource(String)}
   */
  @Test
  public void testAddWatchedResource() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addWatchedResource("Name");

    // Assert
    assertArrayEquals(new String[]{"Name"}, standardContext.findWatchedResources());
  }

  /**
   * Test {@link StandardContext#addWatchedResource(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addWatchedResource(String)}
   */
  @Test
  public void testAddWatchedResource_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addWatchedResource("Name");

    // Assert
    assertArrayEquals(new String[]{"Name"}, standardContext.findWatchedResources());
  }

  /**
   * Test {@link StandardContext#addWelcomeFile(String)}.
   * <p>
   * Method under test: {@link StandardContext#addWelcomeFile(String)}
   */
  @Test
  public void testAddWelcomeFile() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.setReplaceWelcomeFiles(true);

    // Act
    standardContext.addWelcomeFile("Name");

    // Assert
    assertArrayEquals(new String[]{"Name"}, standardContext.findWelcomeFiles());
  }

  /**
   * Test {@link StandardContext#addWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addWelcomeFile(String)}
   */
  @Test
  public void testAddWelcomeFile_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addWelcomeFile("Name");

    // Assert
    assertArrayEquals(new String[]{"Name"}, standardContext.findWelcomeFiles());
  }

  /**
   * Test {@link StandardContext#addWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) ReplaceWelcomeFiles is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addWelcomeFile(String)}
   */
  @Test
  public void testAddWelcomeFile_givenStandardContextReplaceWelcomeFilesIsTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setReplaceWelcomeFiles(true);

    // Act
    standardContext.addWelcomeFile("Name");

    // Assert
    assertArrayEquals(new String[]{"Name"}, standardContext.findWelcomeFiles());
  }

  /**
   * Test {@link StandardContext#addWrapperLifecycle(String)}.
   * <p>
   * Method under test: {@link StandardContext#addWrapperLifecycle(String)}
   */
  @Test
  public void testAddWrapperLifecycle() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addWrapperLifecycle("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findWrapperLifecycles());
  }

  /**
   * Test {@link StandardContext#addWrapperLifecycle(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addWrapperLifecycle(String)}
   */
  @Test
  public void testAddWrapperLifecycle_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addWrapperLifecycle("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findWrapperLifecycles());
  }

  /**
   * Test {@link StandardContext#addWrapperListener(String)}.
   * <p>
   * Method under test: {@link StandardContext#addWrapperListener(String)}
   */
  @Test
  public void testAddWrapperListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addWrapperListener("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findWrapperListeners());
  }

  /**
   * Test {@link StandardContext#addWrapperListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addWrapperListener(String)}
   */
  @Test
  public void testAddWrapperListener_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addWrapperListener("Listener");

    // Assert
    assertArrayEquals(new String[]{"Listener"}, standardContext.findWrapperListeners());
  }

  /**
   * Test {@link StandardContext#createWrapper()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addWrapperLifecycle {@code Listener}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#createWrapper()}
   */
  @Test
  public void testCreateWrapper_givenStandardContextAddWrapperLifecycleListener_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperLifecycle("Listener");

    // Act and Assert
    assertNull(standardContext.createWrapper());
  }

  /**
   * Test {@link StandardContext#createWrapper()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addWrapperListener {@code Listener}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#createWrapper()}
   */
  @Test
  public void testCreateWrapper_givenStandardContextAddWrapperListenerListener_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperListener("Listener");

    // Act and Assert
    assertNull(standardContext.createWrapper());
  }

  /**
   * Test {@link StandardContext#createWrapper()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then Pipeline return {@link StandardPipeline}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#createWrapper()}
   */
  @Test
  public void testCreateWrapper_givenStandardContext_thenPipelineReturnStandardPipeline() {
    // Arrange and Act
    Wrapper actualCreateWrapperResult = (new StandardContext()).createWrapper();

    // Assert
    assertTrue(actualCreateWrapperResult.getPipeline() instanceof StandardPipeline);
    assertTrue(actualCreateWrapperResult instanceof StandardWrapper);
    assertEquals(",servlet=null,container0=null", actualCreateWrapperResult.getMBeanKeyProperties());
    assertEquals("-1", ((StandardWrapper) actualCreateWrapperResult).getLoadOnStartupString());
    assertEquals("Catalina", actualCreateWrapperResult.getDomain());
    assertEquals("NEW", actualCreateWrapperResult.getStateName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualCreateWrapperResult.getLogName());
    assertNull(actualCreateWrapperResult.getMultipartConfigElement());
    assertNull(actualCreateWrapperResult.getServlet());
    assertNull(((StandardWrapper) actualCreateWrapperResult).getServletContext());
    assertNull(actualCreateWrapperResult.getCatalinaBase());
    assertNull(actualCreateWrapperResult.getCatalinaHome());
    assertNotNull(actualCreateWrapperResult.getParentClassLoader());
    assertNull(((StandardWrapper) actualCreateWrapperResult).parentClassLoader);
    assertNull(actualCreateWrapperResult.getName());
    assertNull(actualCreateWrapperResult.getRunAs());
    assertNull(actualCreateWrapperResult.getServletClass());
    assertNull(((StandardWrapper) actualCreateWrapperResult).getDomainInternal());
    assertNull(((StandardWrapper) actualCreateWrapperResult).getServletName());
    assertNull(((StandardWrapper) actualCreateWrapperResult).startStopExecutor);
    assertNull(((StandardWrapper) actualCreateWrapperResult).backgroundProcessorFuture);
    assertNull(((StandardWrapper) actualCreateWrapperResult).monitorFuture);
    assertNull(actualCreateWrapperResult.getObjectName());
    assertNull(((StandardWrapper) actualCreateWrapperResult).jspMonitorON);
    assertNull(actualCreateWrapperResult.getAccessLog());
    assertNull(((StandardWrapper) actualCreateWrapperResult).accessLog);
    assertNull(actualCreateWrapperResult.getCluster());
    assertNull(((StandardWrapper) actualCreateWrapperResult).getClusterInternal());
    assertNull(((StandardWrapper) actualCreateWrapperResult).cluster);
    assertNull(actualCreateWrapperResult.getParent());
    assertNull(actualCreateWrapperResult.getRealm());
    assertNull(((StandardWrapper) actualCreateWrapperResult).getRealmInternal());
    assertEquals(-1, actualCreateWrapperResult.getBackgroundProcessorDelay());
    assertEquals(-1, actualCreateWrapperResult.getLoadOnStartup());
    assertEquals(-1, ((StandardWrapper) actualCreateWrapperResult).loadOnStartup);
    assertEquals(0, ((StandardWrapper) actualCreateWrapperResult).getClassLoadTime());
    assertEquals(0, ((StandardWrapper) actualCreateWrapperResult).getCountAllocated());
    assertEquals(0, actualCreateWrapperResult.findContainerListeners().length);
    assertEquals(0, actualCreateWrapperResult.findLifecycleListeners().length);
    assertEquals(0, ((StandardWrapper) actualCreateWrapperResult).getChildren().length);
    assertEquals(0L, actualCreateWrapperResult.getAvailable());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).getErrorCount());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).getLoadTime());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).getMaxTime());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).getProcessingTime());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).getRequestCount());
    assertEquals(0L, ((StandardWrapper) actualCreateWrapperResult).sequenceNumber);
    assertEquals(1, actualCreateWrapperResult.getStartStopThreads());
    assertEquals(2000L, ((StandardWrapper) actualCreateWrapperResult).unloadDelay);
    assertEquals(6, ((StandardWrapper) actualCreateWrapperResult).getNotificationInfo().length);
    assertEquals(LifecycleState.NEW, actualCreateWrapperResult.getState());
    assertFalse(actualCreateWrapperResult.isAsyncSupported());
    assertFalse(actualCreateWrapperResult.isOverridable());
    assertFalse(((StandardWrapper) actualCreateWrapperResult).instanceInitialized);
    assertFalse(((StandardWrapper) actualCreateWrapperResult).isJspServlet);
    assertFalse(((StandardWrapper) actualCreateWrapperResult).swallowOutput);
    assertFalse(((StandardWrapper) actualCreateWrapperResult).unloading);
    assertTrue(((StandardWrapper) actualCreateWrapperResult).mappings.isEmpty());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).children.isEmpty());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).parameters.isEmpty());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).references.isEmpty());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).listeners.isEmpty());
    assertTrue(actualCreateWrapperResult.isEnabled());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).getStartChildren());
    assertTrue(((StandardWrapper) actualCreateWrapperResult).getThrowOnFailure());
    assertEquals(Long.MAX_VALUE, ((StandardWrapper) actualCreateWrapperResult).getMinTime());
  }

  /**
   * Test {@link StandardContext#findApplicationListeners()}.
   * <p>
   * Method under test: {@link StandardContext#findApplicationListeners()}
   */
  @Test
  public void testFindApplicationListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findApplicationListeners().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardContext#setAddWebinfClassesResources(boolean)}
   *   <li>{@link StandardContext#setAllowCasualMultipartParsing(boolean)}
   *   <li>{@link StandardContext#setAllowMultipleLeadingForwardSlashInPath(boolean)}
   *   <li>{@link StandardContext#setAlwaysAccessSession(boolean)}
   *   <li>{@link StandardContext#setApplicationLifecycleListeners(Object[])}
   *   <li>{@link StandardContext#setClearReferencesHttpClientKeepAliveThread(boolean)}
   *   <li>{@link StandardContext#setConfigFile(URL)}
   *   <li>{@link StandardContext#setContainerSciFilter(String)}
   *   <li>{@link StandardContext#setContextGetResourceRequiresSlash(boolean)}
   *   <li>{@link StandardContext#setCopyXML(boolean)}
   *   <li>{@link StandardContext#setCreateUploadTargets(boolean)}
   *   <li>{@link StandardContext#setDefaultContextXml(String)}
   *   <li>{@link StandardContext#setDefaultWebXml(String)}
   *   <li>{@link StandardContext#setDenyUncoveredHttpMethods(boolean)}
   *   <li>{@link StandardContext#setDispatcherWrapsSameObject(boolean)}
   *   <li>{@link StandardContext#setDispatchersUseEncodedPaths(boolean)}
   *   <li>{@link StandardContext#setDocBase(String)}
   *   <li>{@link StandardContext#setEffectiveMajorVersion(int)}
   *   <li>{@link StandardContext#setEffectiveMinorVersion(int)}
   *   <li>{@link StandardContext#setFireRequestListenersOnForwards(boolean)}
   *   <li>{@link StandardContext#setInstanceManager(InstanceManager)}
   *   <li>{@link StandardContext#setJ2EEApplication(String)}
   *   <li>{@link StandardContext#setJ2EEServer(String)}
   *   <li>{@link StandardContext#setJarScanner(JarScanner)}
   *   <li>{@link StandardContext#setJndiExceptionOnFailedWrite(boolean)}
   *   <li>{@link StandardContext#setJspConfigDescriptor(JspConfigDescriptor)}
   *   <li>{@link StandardContext#setLogEffectiveWebXml(boolean)}
   *   <li>{@link StandardContext#setMapperContextRootRedirectEnabled(boolean)}
   *   <li>{@link StandardContext#setMapperDirectoryRedirectEnabled(boolean)}
   *   <li>{@link StandardContext#setNamingContextListener(NamingContextListener)}
   *   <li>{@link StandardContext#setNotFoundClassResourceCacheSize(int)}
   *   <li>{@link StandardContext#setOriginalDocBase(String)}
   *   <li>{@link StandardContext#setPreemptiveAuthentication(boolean)}
   *   <li>{@link StandardContext#setRequestCharacterEncoding(String)}
   *   <li>{@link StandardContext#setSendRedirectBody(boolean)}
   *   <li>{@link StandardContext#setServer(String)}
   *   <li>{@link StandardContext#setSessionCookiePathUsesTrailingSlash(boolean)}
   *   <li>{@link StandardContext#setSkipMemoryLeakChecksOnJvmShutdown(boolean)}
   *   <li>{@link StandardContext#setStartupTime(long)}
   *   <li>{@link StandardContext#setSuspendWrappedResponseAfterForward(boolean)}
   *   <li>{@link StandardContext#setSwallowAbortedUploads(boolean)}
   *   <li>{@link StandardContext#setThreadBindingListener(ThreadBindingListener)}
   *   <li>{@link StandardContext#setTldScanTime(long)}
   *   <li>{@link StandardContext#setTldValidation(boolean)}
   *   <li>{@link StandardContext#setUnpackWAR(boolean)}
   *   <li>{@link StandardContext#setUseNaming(boolean)}
   *   <li>{@link StandardContext#setUseRelativeRedirects(boolean)}
   *   <li>{@link StandardContext#setValidateClientProvidedNewSessionId(boolean)}
   *   <li>{@link StandardContext#setXmlBlockExternal(boolean)}
   *   <li>{@link StandardContext#setXmlNamespaceAware(boolean)}
   *   <li>{@link StandardContext#setXmlValidation(boolean)}
   *   <li>{@link StandardContext#findApplicationParameters()}
   *   <li>{@link StandardContext#findConstraints()}
   *   <li>{@link StandardContext#findPostConstructMethods()}
   *   <li>{@link StandardContext#findPreDestroyMethods()}
   *   <li>{@link StandardContext#findSecurityRoles()}
   *   <li>{@link StandardContext#findWatchedResources()}
   *   <li>{@link StandardContext#findWelcomeFiles()}
   *   <li>{@link StandardContext#findWrapperLifecycles()}
   *   <li>{@link StandardContext#findWrapperListeners()}
   *   <li>{@link StandardContext#getAddWebinfClassesResources()}
   *   <li>{@link StandardContext#getAllowCasualMultipartParsing()}
   *   <li>{@link StandardContext#getAllowMultipleLeadingForwardSlashInPath()}
   *   <li>{@link StandardContext#getAltDDName()}
   *   <li>{@link StandardContext#getAlwaysAccessSession()}
   *   <li>{@link StandardContext#getAntiResourceLocking()}
   *   <li>{@link StandardContext#getApplicationLifecycleListeners()}
   *   <li>{@link StandardContext#getCharsetMapperClass()}
   *   <li>{@link StandardContext#getClearReferencesHttpClientKeepAliveThread()}
   *   <li>{@link StandardContext#getClearReferencesRmiTargets()}
   *   <li>{@link StandardContext#getClearReferencesStopThreads()}
   *   <li>{@link StandardContext#getClearReferencesStopTimerThreads()}
   *   <li>{@link StandardContext#getClearReferencesThreadLocals()}
   *   <li>{@link StandardContext#getConfigFile()}
   *   <li>{@link StandardContext#getConfigured()}
   *   <li>{@link StandardContext#getContainerSciFilter()}
   *   <li>{@link StandardContext#getContextGetResourceRequiresSlash()}
   *   <li>{@link StandardContext#getCookieProcessor()}
   *   <li>{@link StandardContext#getCookies()}
   *   <li>{@link StandardContext#getCopyXML()}
   *   <li>{@link StandardContext#getCreateUploadTargets()}
   *   <li>{@link StandardContext#getCrossContext()}
   *   <li>{@link StandardContext#getDefaultContextXml()}
   *   <li>{@link StandardContext#getDefaultWebXml()}
   *   <li>{@link StandardContext#getDelegate()}
   *   <li>{@link StandardContext#getDenyUncoveredHttpMethods()}
   *   <li>{@link StandardContext#getDispatcherWrapsSameObject()}
   *   <li>{@link StandardContext#getDispatchersUseEncodedPaths()}
   *   <li>{@link StandardContext#getDisplayName()}
   *   <li>{@link StandardContext#getDistributable()}
   *   <li>{@link StandardContext#getDocBase()}
   *   <li>{@link StandardContext#getEffectiveMajorVersion()}
   *   <li>{@link StandardContext#getEffectiveMinorVersion()}
   *   <li>{@link StandardContext#getEncodedPath()}
   *   <li>{@link StandardContext#getEncodedReverseSolidusHandlingEnum()}
   *   <li>{@link StandardContext#getEncodedSolidusHandlingEnum()}
   *   <li>{@link StandardContext#getFailCtxIfServletStartFails()}
   *   <li>{@link StandardContext#getFireRequestListenersOnForwards()}
   *   <li>{@link StandardContext#getIgnoreAnnotations()}
   *   <li>{@link StandardContext#getInstanceManager()}
   *   <li>{@link StandardContext#getJ2EEApplication()}
   *   <li>{@link StandardContext#getJ2EEServer()}
   *   <li>{@link StandardContext#getJndiExceptionOnFailedWrite()}
   *   <li>{@link StandardContext#getJspConfigDescriptor()}
   *   <li>{@link StandardContext#getLogEffectiveWebXml()}
   *   <li>{@link StandardContext#getLoginConfig()}
   *   <li>{@link StandardContext#getMapperContextRootRedirectEnabled()}
   *   <li>{@link StandardContext#getMapperDirectoryRedirectEnabled()}
   *   <li>{@link StandardContext#getMetadataComplete()}
   *   <li>{@link StandardContext#getNamingContextListener()}
   *   <li>{@link StandardContext#getNamingToken()}
   *   <li>{@link StandardContext#getNotFoundClassResourceCacheSize()}
   *   <li>{@link StandardContext#getOriginalDocBase()}
   *   <li>{@link StandardContext#getOverride()}
   *   <li>{@link StandardContext#getParallelAnnotationScanning()}
   *   <li>{@link StandardContext#getPath()}
   *   <li>{@link StandardContext#getPaused()}
   *   <li>{@link StandardContext#getPreemptiveAuthentication()}
   *   <li>{@link StandardContext#getPrivileged()}
   *   <li>{@link StandardContext#getPublicId()}
   *   <li>{@link StandardContext#getReloadable()}
   *   <li>{@link StandardContext#getRenewThreadsWhenStoppingContext()}
   *   <li>{@link StandardContext#getRequestCharacterEncoding()}
   *   <li>{@link StandardContext#getResponseCharacterEncoding()}
   *   <li>{@link StandardContext#getSendRedirectBody()}
   *   <li>{@link StandardContext#getServer()}
   *   <li>{@link StandardContext#getSessionCookieDomain()}
   *   <li>{@link StandardContext#getSessionCookieName()}
   *   <li>{@link StandardContext#getSessionCookiePath()}
   *   <li>{@link StandardContext#getSessionCookiePathUsesTrailingSlash()}
   *   <li>{@link StandardContext#getSessionTimeout()}
   *   <li>{@link StandardContext#getSkipMemoryLeakChecksOnJvmShutdown()}
   *   <li>{@link StandardContext#getStartTime()}
   *   <li>{@link StandardContext#getStartupTime()}
   *   <li>{@link StandardContext#getSuspendWrappedResponseAfterForward()}
   *   <li>{@link StandardContext#getSwallowAbortedUploads()}
   *   <li>{@link StandardContext#getSwallowOutput()}
   *   <li>{@link StandardContext#getThreadBindingListener()}
   *   <li>{@link StandardContext#getTldScanTime()}
   *   <li>{@link StandardContext#getTldValidation()}
   *   <li>{@link StandardContext#getUnloadDelay()}
   *   <li>{@link StandardContext#getUnpackWAR()}
   *   <li>{@link StandardContext#getUseHttpOnly()}
   *   <li>{@link StandardContext#getUsePartitioned()}
   *   <li>{@link StandardContext#getUseRelativeRedirects()}
   *   <li>{@link StandardContext#getValidateClientProvidedNewSessionId()}
   *   <li>{@link StandardContext#getWebappVersion()}
   *   <li>{@link StandardContext#getWorkDir()}
   *   <li>{@link StandardContext#getWrapperClass()}
   *   <li>{@link StandardContext#getXmlBlockExternal()}
   *   <li>{@link StandardContext#getXmlNamespaceAware()}
   *   <li>{@link StandardContext#getXmlValidation()}
   *   <li>{@link StandardContext#isUseNaming()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws MalformedURLException {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setAddWebinfClassesResources(true);
    standardContext.setAllowCasualMultipartParsing(true);
    standardContext.setAllowMultipleLeadingForwardSlashInPath(true);
    standardContext.setAlwaysAccessSession(true);
    Object[] listeners = new Object[]{"Listeners"};
    standardContext.setApplicationLifecycleListeners(listeners);
    standardContext.setClearReferencesHttpClientKeepAliveThread(true);
    URL configFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    standardContext.setConfigFile(configFile);
    standardContext.setContainerSciFilter("Container Sci Filter");
    standardContext.setContextGetResourceRequiresSlash(true);
    standardContext.setCopyXML(true);
    standardContext.setCreateUploadTargets(true);
    standardContext.setDefaultContextXml("Default Context Xml");
    standardContext.setDefaultWebXml("Default Web Xml");
    standardContext.setDenyUncoveredHttpMethods(true);
    standardContext.setDispatcherWrapsSameObject(true);
    standardContext.setDispatchersUseEncodedPaths(true);
    standardContext.setDocBase("Doc Base");
    standardContext.setEffectiveMajorVersion(1);
    standardContext.setEffectiveMinorVersion(1);
    standardContext.setFireRequestListenersOnForwards(true);
    SimpleInstanceManager instanceManager = new SimpleInstanceManager();
    standardContext.setInstanceManager(instanceManager);
    standardContext.setJ2EEApplication("J2 EEApplication");
    standardContext.setJ2EEServer("J2 EEServer");
    standardContext.setJarScanner(new StandardJarScanner());
    standardContext.setJndiExceptionOnFailedWrite(true);
    ArrayList<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
    JspConfigDescriptorImpl descriptor = new JspConfigDescriptorImpl(jspPropertyGroups, new ArrayList<>());

    standardContext.setJspConfigDescriptor(descriptor);
    standardContext.setLogEffectiveWebXml(true);
    standardContext.setMapperContextRootRedirectEnabled(true);
    standardContext.setMapperDirectoryRedirectEnabled(true);
    NamingContextListener namingContextListener = new NamingContextListener();
    standardContext.setNamingContextListener(namingContextListener);
    standardContext.setNotFoundClassResourceCacheSize(3);
    standardContext.setOriginalDocBase("Doc Base");
    standardContext.setPreemptiveAuthentication(true);
    standardContext.setRequestCharacterEncoding("UTF-8");
    standardContext.setSendRedirectBody(true);
    String actualSetServerResult = standardContext.setServer("Server");
    standardContext.setSessionCookiePathUsesTrailingSlash(true);
    standardContext.setSkipMemoryLeakChecksOnJvmShutdown(true);
    standardContext.setStartupTime(1L);
    standardContext.setSuspendWrappedResponseAfterForward(true);
    standardContext.setSwallowAbortedUploads(true);
    ThreadBindingListener threadBindingListener = StandardContext.DEFAULT_NAMING_LISTENER;
    standardContext.setThreadBindingListener(threadBindingListener);
    standardContext.setTldScanTime(1L);
    standardContext.setTldValidation(true);
    standardContext.setUnpackWAR(true);
    standardContext.setUseNaming(true);
    standardContext.setUseRelativeRedirects(true);
    standardContext.setValidateClientProvidedNewSessionId(true);
    standardContext.setXmlBlockExternal(true);
    standardContext.setXmlNamespaceAware(true);
    standardContext.setXmlValidation(true);
    ApplicationParameter[] actualFindApplicationParametersResult = standardContext.findApplicationParameters();
    SecurityConstraint[] actualFindConstraintsResult = standardContext.findConstraints();
    Map<String, String> actualFindPostConstructMethodsResult = standardContext.findPostConstructMethods();
    Map<String, String> actualFindPreDestroyMethodsResult = standardContext.findPreDestroyMethods();
    String[] actualFindSecurityRolesResult = standardContext.findSecurityRoles();
    String[] actualFindWatchedResourcesResult = standardContext.findWatchedResources();
    String[] actualFindWelcomeFilesResult = standardContext.findWelcomeFiles();
    String[] actualFindWrapperLifecyclesResult = standardContext.findWrapperLifecycles();
    String[] actualFindWrapperListenersResult = standardContext.findWrapperListeners();
    boolean actualAddWebinfClassesResources = standardContext.getAddWebinfClassesResources();
    boolean actualAllowCasualMultipartParsing = standardContext.getAllowCasualMultipartParsing();
    boolean actualAllowMultipleLeadingForwardSlashInPath = standardContext.getAllowMultipleLeadingForwardSlashInPath();
    String actualAltDDName = standardContext.getAltDDName();
    boolean actualAlwaysAccessSession = standardContext.getAlwaysAccessSession();
    boolean actualAntiResourceLocking = standardContext.getAntiResourceLocking();
    Object[] actualApplicationLifecycleListeners = standardContext.getApplicationLifecycleListeners();
    String actualCharsetMapperClass = standardContext.getCharsetMapperClass();
    boolean actualClearReferencesHttpClientKeepAliveThread = standardContext
        .getClearReferencesHttpClientKeepAliveThread();
    boolean actualClearReferencesRmiTargets = standardContext.getClearReferencesRmiTargets();
    boolean actualClearReferencesStopThreads = standardContext.getClearReferencesStopThreads();
    boolean actualClearReferencesStopTimerThreads = standardContext.getClearReferencesStopTimerThreads();
    boolean actualClearReferencesThreadLocals = standardContext.getClearReferencesThreadLocals();
    URL actualConfigFile = standardContext.getConfigFile();
    boolean actualConfigured = standardContext.getConfigured();
    String actualContainerSciFilter = standardContext.getContainerSciFilter();
    boolean actualContextGetResourceRequiresSlash = standardContext.getContextGetResourceRequiresSlash();
    CookieProcessor actualCookieProcessor = standardContext.getCookieProcessor();
    boolean actualCookies = standardContext.getCookies();
    boolean actualCopyXML = standardContext.getCopyXML();
    boolean actualCreateUploadTargets = standardContext.getCreateUploadTargets();
    boolean actualCrossContext = standardContext.getCrossContext();
    String actualDefaultContextXml = standardContext.getDefaultContextXml();
    String actualDefaultWebXml = standardContext.getDefaultWebXml();
    boolean actualDelegate = standardContext.getDelegate();
    boolean actualDenyUncoveredHttpMethods = standardContext.getDenyUncoveredHttpMethods();
    boolean actualDispatcherWrapsSameObject = standardContext.getDispatcherWrapsSameObject();
    boolean actualDispatchersUseEncodedPaths = standardContext.getDispatchersUseEncodedPaths();
    String actualDisplayName = standardContext.getDisplayName();
    boolean actualDistributable = standardContext.getDistributable();
    String actualDocBase = standardContext.getDocBase();
    int actualEffectiveMajorVersion = standardContext.getEffectiveMajorVersion();
    int actualEffectiveMinorVersion = standardContext.getEffectiveMinorVersion();
    String actualEncodedPath = standardContext.getEncodedPath();
    EncodedSolidusHandling actualEncodedReverseSolidusHandlingEnum = standardContext
        .getEncodedReverseSolidusHandlingEnum();
    EncodedSolidusHandling actualEncodedSolidusHandlingEnum = standardContext.getEncodedSolidusHandlingEnum();
    Boolean actualFailCtxIfServletStartFails = standardContext.getFailCtxIfServletStartFails();
    boolean actualFireRequestListenersOnForwards = standardContext.getFireRequestListenersOnForwards();
    boolean actualIgnoreAnnotations = standardContext.getIgnoreAnnotations();
    InstanceManager actualInstanceManager = standardContext.getInstanceManager();
    String actualJ2EEApplication = standardContext.getJ2EEApplication();
    String actualJ2EEServer = standardContext.getJ2EEServer();
    boolean actualJndiExceptionOnFailedWrite = standardContext.getJndiExceptionOnFailedWrite();
    JspConfigDescriptor actualJspConfigDescriptor = standardContext.getJspConfigDescriptor();
    boolean actualLogEffectiveWebXml = standardContext.getLogEffectiveWebXml();
    LoginConfig actualLoginConfig = standardContext.getLoginConfig();
    boolean actualMapperContextRootRedirectEnabled = standardContext.getMapperContextRootRedirectEnabled();
    boolean actualMapperDirectoryRedirectEnabled = standardContext.getMapperDirectoryRedirectEnabled();
    boolean actualMetadataComplete = standardContext.getMetadataComplete();
    NamingContextListener actualNamingContextListener = standardContext.getNamingContextListener();
    standardContext.getNamingToken();
    int actualNotFoundClassResourceCacheSize = standardContext.getNotFoundClassResourceCacheSize();
    String actualOriginalDocBase = standardContext.getOriginalDocBase();
    boolean actualOverride = standardContext.getOverride();
    boolean actualParallelAnnotationScanning = standardContext.getParallelAnnotationScanning();
    String actualPath = standardContext.getPath();
    boolean actualPaused = standardContext.getPaused();
    boolean actualPreemptiveAuthentication = standardContext.getPreemptiveAuthentication();
    boolean actualPrivileged = standardContext.getPrivileged();
    String actualPublicId = standardContext.getPublicId();
    boolean actualReloadable = standardContext.getReloadable();
    boolean actualRenewThreadsWhenStoppingContext = standardContext.getRenewThreadsWhenStoppingContext();
    String actualRequestCharacterEncoding = standardContext.getRequestCharacterEncoding();
    String actualResponseCharacterEncoding = standardContext.getResponseCharacterEncoding();
    boolean actualSendRedirectBody = standardContext.getSendRedirectBody();
    String actualServer = standardContext.getServer();
    String actualSessionCookieDomain = standardContext.getSessionCookieDomain();
    String actualSessionCookieName = standardContext.getSessionCookieName();
    String actualSessionCookiePath = standardContext.getSessionCookiePath();
    boolean actualSessionCookiePathUsesTrailingSlash = standardContext.getSessionCookiePathUsesTrailingSlash();
    int actualSessionTimeout = standardContext.getSessionTimeout();
    boolean actualSkipMemoryLeakChecksOnJvmShutdown = standardContext.getSkipMemoryLeakChecksOnJvmShutdown();
    long actualStartTime = standardContext.getStartTime();
    long actualStartupTime = standardContext.getStartupTime();
    boolean actualSuspendWrappedResponseAfterForward = standardContext.getSuspendWrappedResponseAfterForward();
    boolean actualSwallowAbortedUploads = standardContext.getSwallowAbortedUploads();
    boolean actualSwallowOutput = standardContext.getSwallowOutput();
    ThreadBindingListener actualThreadBindingListener = standardContext.getThreadBindingListener();
    long actualTldScanTime = standardContext.getTldScanTime();
    boolean actualTldValidation = standardContext.getTldValidation();
    long actualUnloadDelay = standardContext.getUnloadDelay();
    boolean actualUnpackWAR = standardContext.getUnpackWAR();
    boolean actualUseHttpOnly = standardContext.getUseHttpOnly();
    boolean actualUsePartitioned = standardContext.getUsePartitioned();
    boolean actualUseRelativeRedirects = standardContext.getUseRelativeRedirects();
    boolean actualValidateClientProvidedNewSessionId = standardContext.getValidateClientProvidedNewSessionId();
    String actualWebappVersion = standardContext.getWebappVersion();
    String actualWorkDir = standardContext.getWorkDir();
    String actualWrapperClass = standardContext.getWrapperClass();
    boolean actualXmlBlockExternal = standardContext.getXmlBlockExternal();
    boolean actualXmlNamespaceAware = standardContext.getXmlNamespaceAware();
    boolean actualXmlValidation = standardContext.getXmlValidation();
    boolean actualIsUseNamingResult = standardContext.isUseNaming();

    // Assert
    assertTrue(actualInstanceManager instanceof SimpleInstanceManager);
    assertEquals("", actualWebappVersion);
    assertEquals("Container Sci Filter", actualContainerSciFilter);
    assertEquals("Default Context Xml", actualDefaultContextXml);
    assertEquals("Default Web Xml", actualDefaultWebXml);
    assertEquals("Doc Base", actualDocBase);
    assertEquals("Doc Base", actualOriginalDocBase);
    assertEquals("J2 EEApplication", actualJ2EEApplication);
    assertEquals("J2 EEServer", actualJ2EEServer);
    assertEquals("Listeners", actualApplicationLifecycleListeners[0]);
    assertEquals("Server", actualServer);
    assertEquals("Server", actualSetServerResult);
    assertEquals("UTF-8", actualRequestCharacterEncoding);
    assertEquals("org.apache.catalina.core.StandardWrapper", actualWrapperClass);
    assertEquals("org.apache.catalina.util.CharsetMapper", actualCharsetMapperClass);
    assertNull(actualFailCtxIfServletStartFails);
    assertNull(actualAltDDName);
    assertNull(actualDisplayName);
    assertNull(actualEncodedPath);
    assertNull(actualPath);
    assertNull(actualPublicId);
    assertNull(actualResponseCharacterEncoding);
    assertNull(actualSessionCookieDomain);
    assertNull(actualSessionCookieName);
    assertNull(actualSessionCookiePath);
    assertNull(actualWorkDir);
    assertNull(actualLoginConfig);
    assertNull(actualCookieProcessor);
    assertEquals(0, actualFindApplicationParametersResult.length);
    assertEquals(0, actualFindConstraintsResult.length);
    assertEquals(0, actualFindSecurityRolesResult.length);
    assertEquals(0, actualFindWatchedResourcesResult.length);
    assertEquals(0, actualFindWelcomeFilesResult.length);
    assertEquals(0, actualFindWrapperLifecyclesResult.length);
    assertEquals(0, actualFindWrapperListenersResult.length);
    assertEquals(0L, actualStartTime);
    assertEquals(1, actualEffectiveMajorVersion);
    assertEquals(1, actualEffectiveMinorVersion);
    assertEquals(1, actualApplicationLifecycleListeners.length);
    assertEquals(1L, actualStartupTime);
    assertEquals(1L, actualTldScanTime);
    assertEquals(2000L, actualUnloadDelay);
    assertEquals(3, actualNotFoundClassResourceCacheSize);
    assertEquals(30, actualSessionTimeout);
    assertEquals(EncodedSolidusHandling.DECODE, actualEncodedReverseSolidusHandlingEnum);
    assertEquals(EncodedSolidusHandling.REJECT, actualEncodedSolidusHandlingEnum);
    assertFalse(actualAntiResourceLocking);
    assertFalse(actualClearReferencesStopThreads);
    assertFalse(actualClearReferencesStopTimerThreads);
    assertFalse(actualConfigured);
    assertFalse(actualCrossContext);
    assertFalse(actualDelegate);
    assertFalse(actualDistributable);
    assertFalse(actualIgnoreAnnotations);
    assertFalse(actualMetadataComplete);
    assertFalse(actualOverride);
    assertFalse(actualParallelAnnotationScanning);
    assertFalse(actualPaused);
    assertFalse(actualPrivileged);
    assertFalse(actualReloadable);
    assertFalse(actualSwallowOutput);
    assertFalse(actualUsePartitioned);
    assertTrue(actualFindPostConstructMethodsResult.isEmpty());
    assertTrue(actualFindPreDestroyMethodsResult.isEmpty());
    assertTrue(actualAddWebinfClassesResources);
    assertTrue(actualAllowCasualMultipartParsing);
    assertTrue(actualAllowMultipleLeadingForwardSlashInPath);
    assertTrue(actualAlwaysAccessSession);
    assertTrue(actualClearReferencesHttpClientKeepAliveThread);
    assertTrue(actualClearReferencesRmiTargets);
    assertTrue(actualClearReferencesThreadLocals);
    assertTrue(actualContextGetResourceRequiresSlash);
    assertTrue(actualCookies);
    assertTrue(actualCopyXML);
    assertTrue(actualCreateUploadTargets);
    assertTrue(actualDenyUncoveredHttpMethods);
    assertTrue(actualDispatcherWrapsSameObject);
    assertTrue(actualDispatchersUseEncodedPaths);
    assertTrue(actualFireRequestListenersOnForwards);
    assertTrue(actualJndiExceptionOnFailedWrite);
    assertTrue(actualLogEffectiveWebXml);
    assertTrue(actualMapperContextRootRedirectEnabled);
    assertTrue(actualMapperDirectoryRedirectEnabled);
    assertTrue(actualPreemptiveAuthentication);
    assertTrue(actualRenewThreadsWhenStoppingContext);
    assertTrue(actualSendRedirectBody);
    assertTrue(actualSessionCookiePathUsesTrailingSlash);
    assertTrue(actualSkipMemoryLeakChecksOnJvmShutdown);
    assertTrue(actualSuspendWrappedResponseAfterForward);
    assertTrue(actualSwallowAbortedUploads);
    assertTrue(actualTldValidation);
    assertTrue(actualUnpackWAR);
    assertTrue(actualUseHttpOnly);
    assertTrue(actualUseRelativeRedirects);
    assertTrue(actualValidateClientProvidedNewSessionId);
    assertTrue(actualXmlBlockExternal);
    assertTrue(actualXmlNamespaceAware);
    assertTrue(actualXmlValidation);
    assertTrue(actualIsUseNamingResult);
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, actualConfigFile.toString());
    assertSame(namingContextListener, actualNamingContextListener);
    assertSame(instanceManager, actualInstanceManager);
    assertSame(descriptor, actualJspConfigDescriptor);
    assertSame(configFile, actualConfigFile);
    assertSame(listeners, actualApplicationLifecycleListeners);
    assertSame(threadBindingListener, actualThreadBindingListener);
  }

  /**
   * Test {@link StandardContext#findErrorPage(int)} with {@code errorCode}.
   * <p>
   * Method under test: {@link StandardContext#findErrorPage(int)}
   */
  @Test
  public void testFindErrorPageWithErrorCode() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findErrorPage(-1));
  }

  /**
   * Test {@link StandardContext#findErrorPage(Throwable)} with {@code exceptionType}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findErrorPage(Throwable)}
   */
  @Test
  public void testFindErrorPageWithExceptionType_whenNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findErrorPage(null));
  }

  /**
   * Test {@link StandardContext#findErrorPage(Throwable)} with {@code exceptionType}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findErrorPage(Throwable)}
   */
  @Test
  public void testFindErrorPageWithExceptionType_whenThrowable() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertNull(standardContext.findErrorPage(new Throwable()));
  }

  /**
   * Test {@link StandardContext#findErrorPages()}.
   * <p>
   * Method under test: {@link StandardContext#findErrorPages()}
   */
  @Test
  public void testFindErrorPages() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findErrorPages().length);
  }

  /**
   * Test {@link StandardContext#findFilterDef(String)}.
   * <p>
   * Method under test: {@link StandardContext#findFilterDef(String)}
   */
  @Test
  public void testFindFilterDef() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findFilterDef("Filter Name"));
  }

  /**
   * Test {@link StandardContext#findFilterDefs()}.
   * <p>
   * Method under test: {@link StandardContext#findFilterDefs()}
   */
  @Test
  public void testFindFilterDefs() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findFilterDefs().length);
  }

  /**
   * Test {@link StandardContext#findFilterMaps()}.
   * <p>
   * Method under test: {@link StandardContext#findFilterMaps()}
   */
  @Test
  public void testFindFilterMaps() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findFilterMaps().length);
  }

  /**
   * Test {@link StandardContext#findMessageDestination(String)}.
   * <p>
   * Method under test: {@link StandardContext#findMessageDestination(String)}
   */
  @Test
  public void testFindMessageDestination() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findMessageDestination("Name"));
  }

  /**
   * Test {@link StandardContext#findMessageDestinations()}.
   * <p>
   * Method under test: {@link StandardContext#findMessageDestinations()}
   */
  @Test
  public void testFindMessageDestinations() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findMessageDestinations().length);
  }

  /**
   * Test {@link StandardContext#findMimeMapping(String)}.
   * <p>
   * Method under test: {@link StandardContext#findMimeMapping(String)}
   */
  @Test
  public void testFindMimeMapping() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findMimeMapping("Extension"));
  }

  /**
   * Test {@link StandardContext#findMimeMappings()}.
   * <p>
   * Method under test: {@link StandardContext#findMimeMappings()}
   */
  @Test
  public void testFindMimeMappings() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findMimeMappings().length);
  }

  /**
   * Test {@link StandardContext#findParameter(String)}.
   * <p>
   * Method under test: {@link StandardContext#findParameter(String)}
   */
  @Test
  public void testFindParameter() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findParameter("Name"));
  }

  /**
   * Test {@link StandardContext#findParameters()}.
   * <p>
   * Method under test: {@link StandardContext#findParameters()}
   */
  @Test
  public void testFindParameters() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findParameters().length);
  }

  /**
   * Test {@link StandardContext#findRoleMapping(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addRoleMapping {@code Role} and {@code Link}.</li>
   *   <li>Then return {@code Link}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findRoleMapping(String)}
   */
  @Test
  public void testFindRoleMapping_givenStandardContextAddRoleMappingRoleAndLink_thenReturnLink() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addRoleMapping("Role", "Link");

    // Act and Assert
    assertEquals("Link", standardContext.findRoleMapping("Role"));
  }

  /**
   * Test {@link StandardContext#findRoleMapping(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code Role}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findRoleMapping(String)}
   */
  @Test
  public void testFindRoleMapping_givenStandardContext_thenReturnRole() {
    // Arrange, Act and Assert
    assertEquals("Role", (new StandardContext()).findRoleMapping("Role"));
  }

  /**
   * Test {@link StandardContext#findSecurityRole(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addSecurityRole empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findSecurityRole(String)}
   */
  @Test
  public void testFindSecurityRole_givenStandardContextAddSecurityRoleEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addSecurityRole("");
    standardContext.addSecurityRole("Role");

    // Act and Assert
    assertTrue(standardContext.findSecurityRole("Role"));
  }

  /**
   * Test {@link StandardContext#findSecurityRole(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addSecurityRole {@code Role}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findSecurityRole(String)}
   */
  @Test
  public void testFindSecurityRole_givenStandardContextAddSecurityRoleRole_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addSecurityRole("Role");

    // Act and Assert
    assertTrue(standardContext.findSecurityRole("Role"));
  }

  /**
   * Test {@link StandardContext#findSecurityRole(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findSecurityRole(String)}
   */
  @Test
  public void testFindSecurityRole_givenStandardContext_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardContext()).findSecurityRole("Role"));
  }

  /**
   * Test {@link StandardContext#findServletMapping(String)}.
   * <p>
   * Method under test: {@link StandardContext#findServletMapping(String)}
   */
  @Test
  public void testFindServletMapping() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findServletMapping("Pattern"));
  }

  /**
   * Test {@link StandardContext#findServletMappings()}.
   * <p>
   * Method under test: {@link StandardContext#findServletMappings()}
   */
  @Test
  public void testFindServletMappings() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findServletMappings().length);
  }

  /**
   * Test {@link StandardContext#findWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addWelcomeFile empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findWelcomeFile(String)}
   */
  @Test
  public void testFindWelcomeFile_givenStandardContextAddWelcomeFileEmptyString_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWelcomeFile("");
    standardContext.addWelcomeFile("Name");

    // Act and Assert
    assertTrue(standardContext.findWelcomeFile("Name"));
  }

  /**
   * Test {@link StandardContext#findWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addWelcomeFile {@code Name}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findWelcomeFile(String)}
   */
  @Test
  public void testFindWelcomeFile_givenStandardContextAddWelcomeFileName_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWelcomeFile("Name");

    // Act and Assert
    assertTrue(standardContext.findWelcomeFile("Name"));
  }

  /**
   * Test {@link StandardContext#findWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#findWelcomeFile(String)}
   */
  @Test
  public void testFindWelcomeFile_givenStandardContext_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardContext()).findWelcomeFile("Name"));
  }

  /**
   * Test {@link StandardContext#reload()}.
   * <p>
   * Method under test: {@link StandardContext#reload()}
   */
  @Test
  public void testReload() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardContext()).reload());
  }

  /**
   * Test {@link StandardContext#removeApplicationListener(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeApplicationListener(String)}
   */
  @Test
  public void testRemoveApplicationListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addApplicationListener("Listener");

    // Act
    standardContext.removeApplicationListener("Listener");

    // Assert
    assertEquals(0, standardContext.findApplicationListeners().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeApplicationListener(String)}
   */
  @Test
  public void testRemoveApplicationListener_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeApplicationListener("Listener");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findApplicationListeners().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeApplicationListener(String)}
   */
  @Test
  public void testRemoveApplicationListener_givenStandardContextAddApplicationListenerListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationListener("Listener");

    // Act
    standardContext.removeApplicationListener("Listener");

    // Assert
    assertEquals(0, standardContext.findApplicationListeners().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationParameter(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeApplicationParameter(String)}
   */
  @Test
  public void testRemoveApplicationParameter() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addApplicationParameter(parameter);

    // Act
    standardContext.removeApplicationParameter("Name");

    // Assert
    assertEquals(0, standardContext.findApplicationParameters().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationParameter(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeApplicationParameter(String)}
   */
  @Test
  public void testRemoveApplicationParameter_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeApplicationParameter("Name");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findApplicationParameters().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationParameter(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeApplicationParameter(String)}
   */
  @Test
  public void testRemoveApplicationParameter_thenArrayLengthIsOne() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    ApplicationParameter parameter2 = new ApplicationParameter();
    parameter2.setDescription("Name");
    parameter2.setName("removeApplicationParameter");
    parameter2.setOverride(false);
    parameter2.setValue("Name");

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationParameter(parameter2);
    standardContext.addApplicationParameter(parameter);

    // Act
    standardContext.removeApplicationParameter("Name");

    // Assert
    assertEquals(1, standardContext.findApplicationParameters().length);
  }

  /**
   * Test {@link StandardContext#removeApplicationParameter(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeApplicationParameter(String)}
   */
  @Test
  public void testRemoveApplicationParameter_thenArrayLengthIsZero() {
    // Arrange
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationParameter(parameter);

    // Act
    standardContext.removeApplicationParameter("Name");

    // Assert
    assertEquals(0, standardContext.findApplicationParameters().length);
  }

  /**
   * Test {@link StandardContext#removeChild(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenStandardContext_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.removeChild(new StandardContext()));
  }

  /**
   * Test {@link StandardContext#removeChild(Container)}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) Pipeline {@link StandardPipeline}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenStandardWrapper_thenStandardWrapperPipelineStandardPipeline() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    StandardWrapper child = new StandardWrapper();

    // Act
    standardContext.removeChild(child);

    // Assert
    Pipeline pipeline = child.getPipeline();
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("DESTROYED", child.getStateName());
    StandardWrapperValve standardWrapperValve = child.swValve;
    assertEquals("DESTROYED", standardWrapperValve.getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertNull(standardWrapperValve.getDomainInternal());
    assertNull(standardWrapperValve.getContainer());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.DESTROYED, standardWrapperValve.getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
  }

  /**
   * Test {@link StandardContext#removeSecurityRole(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeSecurityRole(String)}
   */
  @Test
  public void testRemoveSecurityRole() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addSecurityRole("Role");

    // Act
    standardContext.removeSecurityRole("Role");

    // Assert
    assertEquals(0, standardContext.findSecurityRoles().length);
  }

  /**
   * Test {@link StandardContext#removeSecurityRole(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeSecurityRole(String)}
   */
  @Test
  public void testRemoveSecurityRole_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeSecurityRole("Role");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findSecurityRoles().length);
  }

  /**
   * Test {@link StandardContext#removeSecurityRole(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeSecurityRole(String)}
   */
  @Test
  public void testRemoveSecurityRole_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addSecurityRole("removeSecurityRole");
    standardContext.addSecurityRole("Role");

    // Act
    standardContext.removeSecurityRole("Role");

    // Assert
    assertEquals(1, standardContext.findSecurityRoles().length);
  }

  /**
   * Test {@link StandardContext#removeSecurityRole(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeSecurityRole(String)}
   */
  @Test
  public void testRemoveSecurityRole_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addSecurityRole("Role");

    // Act
    standardContext.removeSecurityRole("Role");

    // Assert
    assertEquals(0, standardContext.findSecurityRoles().length);
  }

  /**
   * Test {@link StandardContext#removeWatchedResource(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeWatchedResource(String)}
   */
  @Test
  public void testRemoveWatchedResource() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addWatchedResource("Name");

    // Act
    standardContext.removeWatchedResource("Name");

    // Assert
    assertEquals(0, standardContext.findWatchedResources().length);
  }

  /**
   * Test {@link StandardContext#removeWatchedResource(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWatchedResource(String)}
   */
  @Test
  public void testRemoveWatchedResource_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeWatchedResource("Name");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findWatchedResources().length);
  }

  /**
   * Test {@link StandardContext#removeWatchedResource(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWatchedResource(String)}
   */
  @Test
  public void testRemoveWatchedResource_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWatchedResource("removeWatchedResource");
    standardContext.addWatchedResource("Name");

    // Act
    standardContext.removeWatchedResource("Name");

    // Assert
    assertEquals(1, standardContext.findWatchedResources().length);
  }

  /**
   * Test {@link StandardContext#removeWatchedResource(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWatchedResource(String)}
   */
  @Test
  public void testRemoveWatchedResource_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWatchedResource("Name");

    // Act
    standardContext.removeWatchedResource("Name");

    // Assert
    assertEquals(0, standardContext.findWatchedResources().length);
  }

  /**
   * Test {@link StandardContext#removeWelcomeFile(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWelcomeFile(String)}
   */
  @Test
  public void testRemoveWelcomeFile_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeWelcomeFile("Name");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findWelcomeFiles().length);
  }

  /**
   * Test {@link StandardContext#removeWelcomeFile(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWelcomeFile(String)}
   */
  @Test
  public void testRemoveWelcomeFile_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWelcomeFile("");
    standardContext.addWelcomeFile("Name");

    // Act
    standardContext.removeWelcomeFile("Name");

    // Assert
    assertEquals(1, standardContext.findWelcomeFiles().length);
  }

  /**
   * Test {@link StandardContext#removeWelcomeFile(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWelcomeFile(String)}
   */
  @Test
  public void testRemoveWelcomeFile_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWelcomeFile("Name");

    // Act
    standardContext.removeWelcomeFile("Name");

    // Assert
    assertEquals(0, standardContext.findWelcomeFiles().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperLifecycle(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeWrapperLifecycle(String)}
   */
  @Test
  public void testRemoveWrapperLifecycle() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addWrapperLifecycle("Listener");

    // Act
    standardContext.removeWrapperLifecycle("Listener");

    // Assert
    assertEquals(0, standardContext.findWrapperLifecycles().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperLifecycle(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperLifecycle(String)}
   */
  @Test
  public void testRemoveWrapperLifecycle_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeWrapperLifecycle("Listener");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findWrapperLifecycles().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperLifecycle(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperLifecycle(String)}
   */
  @Test
  public void testRemoveWrapperLifecycle_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperLifecycle("removeWrapperLifecycle");
    standardContext.addWrapperLifecycle("Listener");

    // Act
    standardContext.removeWrapperLifecycle("Listener");

    // Assert
    assertEquals(1, standardContext.findWrapperLifecycles().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperLifecycle(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperLifecycle(String)}
   */
  @Test
  public void testRemoveWrapperLifecycle_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperLifecycle("Listener");

    // Act
    standardContext.removeWrapperLifecycle("Listener");

    // Assert
    assertEquals(0, standardContext.findWrapperLifecycles().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperListener(String)}.
   * <p>
   * Method under test: {@link StandardContext#removeWrapperListener(String)}
   */
  @Test
  public void testRemoveWrapperListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardContext.addWrapperListener("Listener");

    // Act
    standardContext.removeWrapperListener("Listener");

    // Assert
    assertEquals(0, standardContext.findWrapperListeners().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperListener(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperListener(String)}
   */
  @Test
  public void testRemoveWrapperListener_givenStandardContext_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.removeWrapperListener("Listener");

    // Assert that nothing has changed
    assertEquals(0, standardContext.findWrapperListeners().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperListener(String)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperListener(String)}
   */
  @Test
  public void testRemoveWrapperListener_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperListener("removeWrapperListener");
    standardContext.addWrapperListener("Listener");

    // Act
    standardContext.removeWrapperListener("Listener");

    // Assert
    assertEquals(1, standardContext.findWrapperListeners().length);
  }

  /**
   * Test {@link StandardContext#removeWrapperListener(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#removeWrapperListener(String)}
   */
  @Test
  public void testRemoveWrapperListener_thenArrayLengthIsZero() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addWrapperListener("Listener");

    // Act
    standardContext.removeWrapperListener("Listener");

    // Assert
    assertEquals(0, standardContext.findWrapperListeners().length);
  }

  /**
   * Test {@link StandardContext#getProcessingTime()}.
   * <p>
   * Method under test: {@link StandardContext#getProcessingTime()}
   */
  @Test
  public void testGetProcessingTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardContext()).getProcessingTime());
  }

  /**
   * Test {@link StandardContext#getMaxTime()}.
   * <p>
   * Method under test: {@link StandardContext#getMaxTime()}
   */
  @Test
  public void testGetMaxTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardContext()).getMaxTime());
  }

  /**
   * Test {@link StandardContext#getMinTime()}.
   * <p>
   * Method under test: {@link StandardContext#getMinTime()}
   */
  @Test
  public void testGetMinTime() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new StandardContext()).getMinTime());
  }

  /**
   * Test {@link StandardContext#getRequestCount()}.
   * <p>
   * Method under test: {@link StandardContext#getRequestCount()}
   */
  @Test
  public void testGetRequestCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardContext()).getRequestCount());
  }

  /**
   * Test {@link StandardContext#getErrorCount()}.
   * <p>
   * Method under test: {@link StandardContext#getErrorCount()}
   */
  @Test
  public void testGetErrorCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardContext()).getErrorCount());
  }

  /**
   * Test {@link StandardContext#getRealPath(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Resources is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getRealPath(String)}
   */
  @Test
  public void testGetRealPath_givenStandardContextResourcesIsNull_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setResources(null);

    // Act and Assert
    assertNull(standardContext.getRealPath(""));
  }

  /**
   * Test {@link StandardContext#getRealPath(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@code Path}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#getRealPath(String)}
   */
  @Test
  public void testGetRealPath_givenStandardContext_whenPath_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getRealPath("Path"));
  }

  /**
   * Test {@link StandardContext#wasCreatedDynamicServlet(Servlet)}.
   * <p>
   * Method under test: {@link StandardContext#wasCreatedDynamicServlet(Servlet)}
   */
  @Test
  public void testWasCreatedDynamicServlet() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertFalse(standardContext.wasCreatedDynamicServlet(new HTMLManagerServlet()));
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link FilterDef} (default constructor) Filter is {@link AddDefaultCharsetFilter} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenFilterDefFilterIsAddDefaultCharsetFilter_thenReturnFalse() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    filterDef.setFilter(new AddDefaultCharsetFilter());

    StandardContext standardContext = new StandardContext();
    standardContext.addFilterDef(filterDef);

    // Act and Assert
    assertFalse(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addFilterDef {@link FilterDef} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenStandardContextAddFilterDefFilterDef_thenReturnFalse() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertFalse(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenStandardContextNameIsSlash_thenReturnFalse() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertFalse(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenStandardContextParentIsStandardContext_thenReturnFalse() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertFalse(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenStandardContextParentIsStandardHost_thenReturnFalse() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardHost());
    standardContext.addFilterDef(new FilterDef());

    // Act and Assert
    assertFalse(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStart()}
   */
  @Test
  public void testFilterStart_givenStandardContext_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertTrue(standardContext.filterStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextNameIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("");

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextNameIsNumberSignNumberSign() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("##");

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextNameIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextParentIsFailedContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new FailedContext());

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#filterStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#filterStop()}
   */
  @Test
  public void testFilterStop_givenStandardContextParentIsStandardHost() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertTrue(standardContext.filterStop());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#findFilterConfig(String)}.
   * <p>
   * Method under test: {@link StandardContext#findFilterConfig(String)}
   */
  @Test
  public void testFindFilterConfig() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findFilterConfig("Name"));
  }

  /**
   * Test {@link StandardContext#listenerStart()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#listenerStart()}
   */
  @Test
  public void testListenerStart_thenReturnFalse() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setParent(new StandardContext());

    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationListener("getContext");
    standardContext.setParent(container);

    // Act and Assert
    assertFalse(standardContext.listenerStart());
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#listenerStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#listenerStop()}
   */
  @Test
  public void testListenerStop_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    boolean actualListenerStopResult = standardContext.listenerStop();

    // Assert
    assertNull(standardContext.getApplicationLifecycleListeners());
    assertEquals(0, standardContext.getApplicationEventListeners().length);
    assertTrue(actualListenerStopResult);
  }

  /**
   * Test {@link StandardContext#listenerStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationEventListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#listenerStop()}
   */
  @Test
  public void testListenerStop_givenStandardContextAddApplicationEventListenerListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationEventListener("Listener");

    // Act
    boolean actualListenerStopResult = standardContext.listenerStop();

    // Assert
    assertNull(standardContext.getApplicationLifecycleListeners());
    assertEquals(0, standardContext.getApplicationEventListeners().length);
    assertTrue(actualListenerStopResult);
  }

  /**
   * Test {@link StandardContext#listenerStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationEventListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#listenerStop()}
   */
  @Test
  public void testListenerStop_givenStandardContextAddApplicationEventListenerNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addApplicationEventListener(null);

    // Act
    boolean actualListenerStopResult = standardContext.listenerStop();

    // Assert
    assertNull(standardContext.getApplicationLifecycleListeners());
    assertEquals(0, standardContext.getApplicationEventListeners().length);
    assertTrue(actualListenerStopResult);
  }

  /**
   * Test {@link StandardContext#resourcesStart()}.
   * <p>
   * Method under test: {@link StandardContext#resourcesStart()}
   */
  @Test
  public void testResourcesStart() throws LifecycleException {
    // Arrange
    ExtractingRoot resources = new ExtractingRoot();
    resources.addLifecycleListener(new AprLifecycleListener());

    StandardContext standardContext = new StandardContext();
    standardContext.setName("before_init");
    standardContext.setResources(resources);

    // Act
    standardContext.resourcesStart();

    // Assert
    WebResourceRoot resources2 = standardContext.getResources();
    assertTrue(resources2 instanceof ExtractingRoot);
    ObjectName objectName = ((ExtractingRoot) resources2).getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("/before_init", keyPropertyList.get("context"));
    assertEquals("Catalina:container0=null,context=/before_init,type=WebResourceRoot", objectName.getCanonicalName());
    assertEquals("container0=null,context=/before_init,type=WebResourceRoot",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=WebResourceRoot,context=/before_init,container0=null", objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("container0"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardContext#resourcesStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStart()}
   */
  @Test
  public void testResourcesStart_givenStandardContextNameIsNumberSignNumberSign() throws LifecycleException {
    // Arrange
    ExtractingRoot resources = new ExtractingRoot();
    resources.addLifecycleListener(new AprLifecycleListener());

    StandardContext standardContext = new StandardContext();
    standardContext.setName("##");
    standardContext.setResources(resources);

    // Act
    standardContext.resourcesStart();

    // Assert
    WebResourceRoot resources2 = standardContext.getResources();
    assertTrue(resources2 instanceof ExtractingRoot);
    ObjectName objectName = ((ExtractingRoot) resources2).getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("/", keyPropertyList.get("context"));
    assertEquals("Catalina:container0=null,context=/,type=WebResourceRoot", objectName.getCanonicalName());
    assertEquals("container0=null,context=/,type=WebResourceRoot", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=WebResourceRoot,context=/,container0=null", objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("container0"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardContext#resourcesStart()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStart()}
   */
  @Test
  public void testResourcesStart_givenStandardContextNameIsSlash() throws LifecycleException {
    // Arrange
    ExtractingRoot resources = new ExtractingRoot();
    resources.addLifecycleListener(new AprLifecycleListener());

    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");
    standardContext.setResources(resources);

    // Act
    standardContext.resourcesStart();

    // Assert
    WebResourceRoot resources2 = standardContext.getResources();
    assertTrue(resources2 instanceof ExtractingRoot);
    ObjectName objectName = ((ExtractingRoot) resources2).getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("/", keyPropertyList.get("context"));
    assertEquals("Catalina:container0=null,context=/,type=WebResourceRoot", objectName.getCanonicalName());
    assertEquals("container0=null,context=/,type=WebResourceRoot", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=WebResourceRoot,context=/,container0=null", objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("container0"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardContext#resourcesStart()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Resources {@link StandardRoot}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStart()}
   */
  @Test
  public void testResourcesStart_thenStandardContextResourcesStandardRoot() throws LifecycleException {
    // Arrange
    StandardRoot resources = new StandardRoot();
    resources.addLifecycleListener(new AprLifecycleListener());

    StandardContext standardContext = new StandardContext();
    standardContext.setName("before_init");
    standardContext.setResources(resources);

    // Act
    standardContext.resourcesStart();

    // Assert
    WebResourceRoot resources2 = standardContext.getResources();
    assertTrue(resources2 instanceof StandardRoot);
    ObjectName objectName = ((StandardRoot) resources2).getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("/before_init", keyPropertyList.get("context"));
    assertEquals("Catalina:container0=null,context=/before_init,type=WebResourceRoot", objectName.getCanonicalName());
    assertEquals("container0=null,context=/before_init,type=WebResourceRoot",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=WebResourceRoot,context=/before_init,container0=null", objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("container0"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardContext#resourcesStart()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Resources StateName is {@code STARTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStart()}
   */
  @Test
  public void testResourcesStart_thenStandardContextResourcesStateNameIsStarted() throws LifecycleException {
    // Arrange
    ExtractingRoot resources = new ExtractingRoot();
    resources.addLifecycleListener(new AprLifecycleListener());

    StandardContext standardContext = new StandardContext();
    standardContext.setName("type=WebResourceRoot");
    standardContext.setResources(resources);

    // Act
    standardContext.resourcesStart();

    // Assert
    WebResourceRoot resources2 = standardContext.getResources();
    assertTrue(resources2 instanceof ExtractingRoot);
    assertEquals("STARTED", resources2.getStateName());
    assertEquals(LifecycleState.STARTED, resources2.getState());
  }

  /**
   * Test {@link StandardContext#resourcesStop()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStop()}
   */
  @Test
  public void testResourcesStop_givenStandardContext() {
    // Arrange, Act and Assert
    assertTrue((new StandardContext()).resourcesStop());
  }

  /**
   * Test {@link StandardContext#resourcesStop()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Resources {@link ExtractingRoot}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#resourcesStop()}
   */
  @Test
  public void testResourcesStop_thenStandardContextResourcesExtractingRoot() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setResources(new ExtractingRoot());

    // Act
    boolean actualResourcesStopResult = standardContext.resourcesStop();

    // Assert
    WebResourceRoot resources = standardContext.getResources();
    assertTrue(resources instanceof ExtractingRoot);
    assertEquals("STOPPED", resources.getStateName());
    assertEquals(LifecycleState.STOPPED, resources.getState());
    assertTrue(actualResourcesStopResult);
  }

  /**
   * Test {@link StandardContext#loadOnStartup(Container[])}.
   * <p>
   * Method under test: {@link StandardContext#loadOnStartup(Container[])}
   */
  @Test
  public void testLoadOnStartup() {
    // Arrange
    StandardHost container = new StandardHost();
    container.setFailCtxIfServletStartFails(false);

    StandardContext standardContext = new StandardContext();
    standardContext.setParent(container);
    standardContext.setFailCtxIfServletStartFails(null);
    StandardWrapper standardWrapper = new StandardWrapper();
    StandardWrapper standardWrapper2 = new StandardWrapper();
    StandardWrapper standardWrapper3 = new StandardWrapper();
    StandardWrapper standardWrapper4 = new StandardWrapper();
    StandardWrapper standardWrapper5 = new StandardWrapper();
    StandardWrapper standardWrapper6 = new StandardWrapper();
    StandardWrapper standardWrapper7 = new StandardWrapper();
    StandardWrapper standardWrapper8 = new StandardWrapper();
    StandardWrapper standardWrapper9 = new StandardWrapper();
    StandardWrapper standardWrapper10 = new StandardWrapper();
    StandardWrapper standardWrapper11 = new StandardWrapper();
    StandardWrapper standardWrapper12 = new StandardWrapper();
    StandardWrapper standardWrapper13 = new StandardWrapper();
    StandardWrapper standardWrapper14 = new StandardWrapper();
    StandardWrapper standardWrapper15 = new StandardWrapper();
    StandardWrapper standardWrapper16 = new StandardWrapper();
    StandardWrapper standardWrapper17 = new StandardWrapper();

    // Act and Assert
    assertTrue(standardContext.loadOnStartup(new Container[]{standardWrapper, standardWrapper2, standardWrapper3,
        standardWrapper4, standardWrapper5, standardWrapper6, standardWrapper7, standardWrapper8, standardWrapper9,
        standardWrapper10, standardWrapper11, standardWrapper12, standardWrapper13, standardWrapper14,
        standardWrapper15, standardWrapper16, standardWrapper17, new StandardWrapper()}));
  }

  /**
   * Test {@link StandardContext#createInstanceManager()}.
   * <ul>
   *   <li>Then return {@link DefaultInstanceManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#createInstanceManager()}
   */
  @Test
  public void testCreateInstanceManager_thenReturnDefaultInstanceManager() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(new WebappLoader());

    // Act
    InstanceManager actualCreateInstanceManagerResult = standardContext.createInstanceManager();

    // Assert
    assertTrue(actualCreateInstanceManagerResult instanceof DefaultInstanceManager);
    assertNull(((DefaultInstanceManager) actualCreateInstanceManagerResult).classLoader);
    assertEquals(0, ((DefaultInstanceManager) actualCreateInstanceManagerResult).getAnnotationCacheSize());
    assertFalse(((DefaultInstanceManager) actualCreateInstanceManagerResult).ignoreAnnotations);
    assertFalse(((DefaultInstanceManager) actualCreateInstanceManagerResult).metadataComplete);
    assertFalse(((DefaultInstanceManager) actualCreateInstanceManagerResult).privileged);
    Log expectedLogger = standardContext.logger;
    assertSame(expectedLogger, standardContext.getLogger());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardContext() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.destroyInternal();

    // Assert that nothing has changed
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(1, pipeline.getValves().length);
    assertEquals(1, ((StandardPipeline) pipeline).getValveObjectNames().length);
    assertSame(basic, pipeline.getFirst());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Cluster {@link SimpleTcpCluster}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextClusterSimpleTcpCluster() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setCluster(new SimpleTcpCluster());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    Cluster cluster = standardContext.getCluster();
    assertTrue(cluster instanceof SimpleTcpCluster);
    Cluster cluster2 = standardContext.cluster;
    assertTrue(cluster2 instanceof SimpleTcpCluster);
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster).getStateName());
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster2).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster).getState());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster2).getState());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Loader {@link WebappLoader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextLoaderWebappLoader() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(new WebappLoader());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    Loader loader = standardContext.getLoader();
    assertTrue(loader instanceof WebappLoader);
    assertEquals("DESTROYED", ((WebappLoader) loader).getStateName());
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, ((WebappLoader) loader).getState());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Manager {@link BackupManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextManagerBackupManager() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setManager(new BackupManager());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    Manager manager = standardContext.getManager();
    assertTrue(manager instanceof BackupManager);
    Manager manager2 = standardContext.manager;
    assertTrue(manager2 instanceof BackupManager);
    assertEquals("DESTROYED", ((BackupManager) manager).getStateName());
    assertEquals("DESTROYED", ((BackupManager) manager2).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((BackupManager) manager).getState());
    assertEquals(LifecycleState.DESTROYED, ((BackupManager) manager2).getState());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) NamingResources StateName is {@code DESTROYED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextNamingResourcesStateNameIsDestroyed() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setNamingResources(new NamingResourcesImpl());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("DESTROYED", namingResources.getStateName());
    assertNull(standardContext.getAuthenticator());
    assertNull(((StandardPipeline) pipeline).first);
    assertEquals(1, pipeline.getValves().length);
    assertEquals(1, ((StandardPipeline) pipeline).getValveObjectNames().length);
    assertEquals(LifecycleState.DESTROYED, namingResources.getState());
    assertSame(basic, pipeline.getFirst());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Pipeline Basic Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextPipelineBasicDomainIsCatalina() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addValve(new BasicAuthenticator());

    // Act
    standardContext.destroyInternal();

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("Catalina", ((StandardContextValve) basic).getDomain());
    assertNull(((StandardContextValve) basic).getObjectName());
    assertNull(standardContext.getAuthenticator());
    assertNull(basic.getNext());
    assertNull(((StandardPipeline) pipeline).first);
    assertEquals(0, ((StandardContextValve) basic).findLifecycleListeners().length);
    Valve[] valves = pipeline.getValves();
    assertEquals(1, valves.length);
    assertEquals(1, ((StandardPipeline) pipeline).getValveObjectNames().length);
    assertTrue(basic.isAsyncSupported());
    assertTrue(((StandardContextValve) basic).getThrowOnFailure());
    assertSame(basic, pipeline.getFirst());
    assertSame(basic, valves[0]);
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Pipeline Basic {@link StandardContextValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextPipelineBasicStandardContextValve() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert that nothing has changed
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(1, pipeline.getValves().length);
    assertEquals(1, ((StandardPipeline) pipeline).getValveObjectNames().length);
    assertSame(basic, pipeline.getFirst());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Realm {@link AuthenticatedUserRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextRealmAuthenticatedUserRealm() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setRealm(new AuthenticatedUserRealm());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    Realm realm = standardContext.getRealm();
    assertTrue(realm instanceof AuthenticatedUserRealm);
    assertEquals("DESTROYED", ((AuthenticatedUserRealm) realm).getStateName());
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, ((AuthenticatedUserRealm) realm).getState());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Resources {@link ExtractingRoot}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardContextResourcesExtractingRoot() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setResources(new ExtractingRoot());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.destroyInternal();

    // Assert
    WebResourceRoot resources = standardContext.getResources();
    assertTrue(resources instanceof ExtractingRoot);
    assertEquals("DESTROYED", resources.getStateName());
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, resources.getState());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#destroyInternal()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenThrowIllegalArgumentException() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.destroyInternal());
  }

  /**
   * Test {@link StandardContext#adjustURLPattern(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#adjustURLPattern(String)}
   */
  @Test
  public void testAdjustURLPattern_givenStandardContext_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new StandardContext()).adjustURLPattern("https://example.org/example"));
  }

  /**
   * Test {@link StandardContext#adjustURLPattern(String)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then return {@code *.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#adjustURLPattern(String)}
   */
  @Test
  public void testAdjustURLPattern_whenAsteriskDot_thenReturnAsteriskDot() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertEquals("*.", standardContext.adjustURLPattern("*."));
  }

  /**
   * Test {@link StandardContext#adjustURLPattern(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code /https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#adjustURLPattern(String)}
   */
  @Test
  public void testAdjustURLPattern_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertEquals("/https://example.org/example", standardContext.adjustURLPattern("https://example.org/example"));
  }

  /**
   * Test {@link StandardContext#adjustURLPattern(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#adjustURLPattern(String)}
   */
  @Test
  public void testAdjustURLPattern_whenNull_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertNull(standardContext.adjustURLPattern(null));
  }

  /**
   * Test {@link StandardContext#adjustURLPattern(String)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#adjustURLPattern(String)}
   */
  @Test
  public void testAdjustURLPattern_whenSlash_thenReturnSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertEquals("/", standardContext.adjustURLPattern("/"));
  }

  /**
   * Test {@link StandardContext#isServlet22()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#isServlet22()}
   */
  @Test
  public void testIsServlet22_givenStandardContext_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardContext()).isServlet22());
  }

  /**
   * Test {@link StandardContext#isServlet22()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#isServlet22()}
   */
  @Test
  public void testIsServlet22_thenReturnTrue() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setPublicId("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN");

    // Act and Assert
    assertTrue(standardContext.isServlet22());
  }

  /**
   * Test {@link StandardContext#addServletSecurity(Dynamic, ServletSecurityElement)}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addServletSecurity(Dynamic, ServletSecurityElement)}
   */
  @Test
  public void testAddServletSecurity_thenReturnEmpty() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration registration = new ApplicationServletRegistration(wrapper, new StandardContext());

    // Act and Assert
    assertTrue(standardContext.addServletSecurity(registration, new ServletSecurityElement()).isEmpty());
  }

  /**
   * Test {@link StandardContext#bindThread()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Loader is {@link WebappLoader} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bindThread()}
   */
  @Test
  public void testBindThread_givenStandardContextLoaderIsWebappLoader_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(new WebappLoader());

    // Act and Assert
    assertNull(standardContext.bindThread());
  }

  /**
   * Test {@link StandardContext#bindThread()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bindThread()}
   */
  @Test
  public void testBindThread_givenStandardContextParentIsStandardContext_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNull(standardContext.bindThread());
  }

  /**
   * Test {@link StandardContext#bindThread()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bindThread()}
   */
  @Test
  public void testBindThread_givenStandardContextParentIsStandardHost_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertNull(standardContext.bindThread());
  }

  /**
   * Test {@link StandardContext#bindThread()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bindThread()}
   */
  @Test
  public void testBindThread_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).bindThread());
  }

  /**
   * Test {@link StandardContext#bind(ClassLoader)} with {@code originalClassLoader}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bind(ClassLoader)}
   */
  @Test
  public void testBindWithOriginalClassLoader_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertNull(standardContext.bind(new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link StandardContext#bind(ClassLoader)} with {@code originalClassLoader}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Loader is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bind(ClassLoader)}
   */
  @Test
  public void testBindWithOriginalClassLoader_givenStandardContextLoaderIsNull_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(null);
    standardContext.setThreadBindingListener(null);

    // Act and Assert
    assertNull(standardContext.bind(null));
  }

  /**
   * Test {@link StandardContext#bind(ClassLoader)} with {@code originalClassLoader}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bind(ClassLoader)}
   */
  @Test
  public void testBindWithOriginalClassLoader_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(new WebappLoader());
    standardContext.setThreadBindingListener(null);

    // Act and Assert
    assertNull(standardContext.bind(null));
  }

  /**
   * Test {@link StandardContext#bind(boolean, ClassLoader)} with {@code usePrivilegedAction}, {@code originalClassLoader}.
   * <p>
   * Method under test: {@link StandardContext#bind(boolean, ClassLoader)}
   */
  @Test
  public void testBindWithUsePrivilegedActionOriginalClassLoader() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(null);
    standardContext.setThreadBindingListener(null);

    // Act and Assert
    assertNull(standardContext.bind(true, null));
  }

  /**
   * Test {@link StandardContext#bind(boolean, ClassLoader)} with {@code usePrivilegedAction}, {@code originalClassLoader}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bind(boolean, ClassLoader)}
   */
  @Test
  public void testBindWithUsePrivilegedActionOriginalClassLoader_givenStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertNull(standardContext.bind(true, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link StandardContext#bind(boolean, ClassLoader)} with {@code usePrivilegedAction}, {@code originalClassLoader}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#bind(boolean, ClassLoader)}
   */
  @Test
  public void testBindWithUsePrivilegedActionOriginalClassLoader_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setLoader(new WebappLoader());
    standardContext.setThreadBindingListener(null);

    // Act and Assert
    assertNull(standardContext.bind(true, null));
  }

  /**
   * Test {@link StandardContext#addPostConstructMethod(String, String)}.
   * <p>
   * Method under test: {@link StandardContext#addPostConstructMethod(String, String)}
   */
  @Test
  public void testAddPostConstructMethod() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addPostConstructMethod("Clazz", "Method");

    // Assert
    Map<String, String> findPostConstructMethodsResult = standardContext.findPostConstructMethods();
    assertEquals(1, findPostConstructMethodsResult.size());
    assertEquals("Method", findPostConstructMethodsResult.get("Clazz"));
  }

  /**
   * Test {@link StandardContext#addPostConstructMethod(String, String)}.
   * <p>
   * Method under test: {@link StandardContext#addPostConstructMethod(String, String)}
   */
  @Test
  public void testAddPostConstructMethod2() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addPostConstructMethod("Clazz", "addPostConstructMethod");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addPostConstructMethod("Clazz", "Method"));
  }

  /**
   * Test {@link StandardContext#addPostConstructMethod(String, String)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) findPostConstructMethods size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPostConstructMethod(String, String)}
   */
  @Test
  public void testAddPostConstructMethod_thenStandardContextFindPostConstructMethodsSizeIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addPostConstructMethod("Clazz", "Method");

    // Assert
    Map<String, String> findPostConstructMethodsResult = standardContext.findPostConstructMethods();
    assertEquals(1, findPostConstructMethodsResult.size());
    assertEquals("Method", findPostConstructMethodsResult.get("Clazz"));
  }

  /**
   * Test {@link StandardContext#addPostConstructMethod(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPostConstructMethod(String, String)}
   */
  @Test
  public void testAddPostConstructMethod_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addPostConstructMethod(null, "Method"));
  }

  /**
   * Test {@link StandardContext#addPostConstructMethod(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPostConstructMethod(String, String)}
   */
  @Test
  public void testAddPostConstructMethod_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addPostConstructMethod("Clazz", null));
  }

  /**
   * Test {@link StandardContext#addPreDestroyMethod(String, String)}.
   * <p>
   * Method under test: {@link StandardContext#addPreDestroyMethod(String, String)}
   */
  @Test
  public void testAddPreDestroyMethod() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardContext.addPreDestroyMethod("Clazz", "Method");

    // Assert
    Map<String, String> findPreDestroyMethodsResult = standardContext.findPreDestroyMethods();
    assertEquals(1, findPreDestroyMethodsResult.size());
    assertEquals("Method", findPreDestroyMethodsResult.get("Clazz"));
  }

  /**
   * Test {@link StandardContext#addPreDestroyMethod(String, String)}.
   * <p>
   * Method under test: {@link StandardContext#addPreDestroyMethod(String, String)}
   */
  @Test
  public void testAddPreDestroyMethod2() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addPreDestroyMethod("Clazz", "addPreDestroyMethod");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardContext.addPreDestroyMethod("Clazz", "Method"));
  }

  /**
   * Test {@link StandardContext#addPreDestroyMethod(String, String)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) findPreDestroyMethods size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPreDestroyMethod(String, String)}
   */
  @Test
  public void testAddPreDestroyMethod_thenStandardContextFindPreDestroyMethodsSizeIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.addPreDestroyMethod("Clazz", "Method");

    // Assert
    Map<String, String> findPreDestroyMethodsResult = standardContext.findPreDestroyMethods();
    assertEquals(1, findPreDestroyMethodsResult.size());
    assertEquals("Method", findPreDestroyMethodsResult.get("Clazz"));
  }

  /**
   * Test {@link StandardContext#addPreDestroyMethod(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPreDestroyMethod(String, String)}
   */
  @Test
  public void testAddPreDestroyMethod_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addPreDestroyMethod(null, "Method"));
  }

  /**
   * Test {@link StandardContext#addPreDestroyMethod(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#addPreDestroyMethod(String, String)}
   */
  @Test
  public void testAddPreDestroyMethod_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).addPreDestroyMethod("Clazz", null));
  }

  /**
   * Test {@link StandardContext#findPostConstructMethod(String)}.
   * <p>
   * Method under test: {@link StandardContext#findPostConstructMethod(String)}
   */
  @Test
  public void testFindPostConstructMethod() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findPostConstructMethod("Clazz"));
  }

  /**
   * Test {@link StandardContext#findPreDestroyMethod(String)}.
   * <p>
   * Method under test: {@link StandardContext#findPreDestroyMethod(String)}
   */
  @Test
  public void testFindPreDestroyMethod() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findPreDestroyMethod("Clazz"));
  }

  /**
   * Test {@link StandardContext#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardContext#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setName("foo");

    StandardContext standardContext = new StandardContext();
    standardContext.setParent(container);
    standardContext.setName("/");

    // Act and Assert
    assertEquals("j2eeType=WebModule,name=//foo/,J2EEApplication=none,J2EEServer=none",
        standardContext.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardContext#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties2() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("j2eeType=WebModule,");
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertEquals("j2eeType=WebModule,name=//DEFAULT/j2eeType=WebModule,,J2EEApplication=none,J2EEServer=none",
        standardContext.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("DEFAULT");
    standardContext.setParent(new StandardHost());

    // Act
    standardContext.initInternal();

    // Assert
    ObjectName objectName = standardContext.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(4, keyPropertyList.size());
    assertEquals("//DEFAULT/DEFAULT", keyPropertyList.get("name"));
    assertEquals("Catalina:J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/DEFAULT",
        objectName.getCanonicalName());
    assertEquals("J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/DEFAULT",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/DEFAULT,J2EEApplication=none,J2EEServer=none",
        objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("J2EEApplication"));
    assertTrue(keyPropertyList.containsKey("J2EEServer"));
    assertTrue(keyPropertyList.containsKey("j2eeType"));
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardContextNameIsEmptyString() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("");
    standardContext.setParent(new StandardHost());

    // Act
    standardContext.initInternal();

    // Assert
    ObjectName objectName = standardContext.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(4, keyPropertyList.size());
    assertEquals("//DEFAULT/", keyPropertyList.get("name"));
    assertEquals("Catalina:J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/",
        objectName.getCanonicalName());
    assertEquals("J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("J2EEApplication"));
    assertTrue(keyPropertyList.containsKey("J2EEServer"));
    assertTrue(keyPropertyList.containsKey("j2eeType"));
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardContextNameIsSlash() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");
    standardContext.setParent(new StandardHost());

    // Act
    standardContext.initInternal();

    // Assert
    ObjectName objectName = standardContext.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(4, keyPropertyList.size());
    assertEquals("//DEFAULT/", keyPropertyList.get("name"));
    assertEquals("Catalina:J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/",
        objectName.getCanonicalName());
    assertEquals("J2EEApplication=none,J2EEServer=none,j2eeType=WebModule,name=//DEFAULT/",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        objectName.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("J2EEApplication"));
    assertTrue(keyPropertyList.containsKey("J2EEServer"));
    assertTrue(keyPropertyList.containsKey("j2eeType"));
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardContextParentIsFailedContext() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("j2eeType=WebModule,");
    standardContext.setParent(new FailedContext());

    // Act
    standardContext.initInternal();

    // Assert that nothing has changed
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardContextParentIsStandardEngine() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("j2eeType=WebModule,");
    standardContext.setParent(new StandardEngine());

    // Act
    standardContext.initInternal();

    // Assert that nothing has changed
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Domain is {@code j2eeType=WebModule,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardHostDomainIsJ2eeTypeWebModule() throws LifecycleException {
    // Arrange
    StandardHost container = new StandardHost();
    container.setDomain("j2eeType=WebModule,");

    StandardContext standardContext = new StandardContext();
    standardContext.setName("j2eeType=WebModule,");
    standardContext.setParent(container);

    // Act
    standardContext.initInternal();

    // Assert that nothing has changed
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) NamingResources ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardContextNamingResourcesObjectNameDomainIsCatalina()
      throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setNamingResources(new NamingResourcesImpl());
    standardContext.setName("DEFAULT");
    standardContext.setParent(new StandardHost());

    // Act
    standardContext.initInternal();

    // Assert
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    ObjectName objectName = namingResources.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:container0=null,context=/DEFAULT,host=null,type=NamingResources",
        objectName.getCanonicalName());
    assertEquals("INITIALIZED", namingResources.getStateName());
    assertEquals("container0=null,context=/DEFAULT,host=null,type=NamingResources",
        objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources,host=null,context=/DEFAULT,container0=null",
        objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(4, keyPropertyList.size());
    assertEquals(LifecycleState.INITIALIZED, namingResources.getState());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("context"));
    assertTrue(keyPropertyList.containsKey("host"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardContext#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) NamingResources StateName is {@code NEW}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContext#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardContextNamingResourcesStateNameIsNew() throws LifecycleException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("j2eeType=WebModule,");
    standardContext.setParent(new StandardHost());

    // Act
    standardContext.initInternal();

    // Assert that nothing has changed
    NamingResourcesImpl namingResources = standardContext.getNamingResources();
    assertEquals("NEW", namingResources.getStateName());
    assertEquals(LifecycleState.NEW, namingResources.getState());
  }

  /**
   * Test {@link StandardContext#getNotificationInfo()}.
   * <p>
   * Method under test: {@link StandardContext#getNotificationInfo()}
   */
  @Test
  public void testGetNotificationInfo() {
    // Arrange and Act
    MBeanNotificationInfo[] actualNotificationInfo = (new StandardContext()).getNotificationInfo();

    // Assert
    MBeanNotificationInfo mBeanNotificationInfo = actualNotificationInfo[1];
    assertEquals("change web application is starting", mBeanNotificationInfo.getDescription());
    MBeanNotificationInfo mBeanNotificationInfo2 = actualNotificationInfo[0];
    assertEquals("javax.management.Notification", mBeanNotificationInfo2.getName());
    assertEquals("javax.management.Notification", mBeanNotificationInfo.getName());
    MBeanNotificationInfo mBeanNotificationInfo3 = actualNotificationInfo[2];
    assertEquals("javax.management.Notification", mBeanNotificationInfo3.getName());
    MBeanNotificationInfo mBeanNotificationInfo4 = actualNotificationInfo[3];
    assertEquals("javax.management.Notification", mBeanNotificationInfo4.getName());
    MBeanNotificationInfo mBeanNotificationInfo5 = actualNotificationInfo[4];
    assertEquals("javax.management.Notification", mBeanNotificationInfo5.getName());
    MBeanNotificationInfo mBeanNotificationInfo6 = actualNotificationInfo[5];
    assertEquals("javax.management.Notification", mBeanNotificationInfo6.getName());
    MBeanNotificationInfo mBeanNotificationInfo7 = actualNotificationInfo[6];
    assertEquals("javax.management.Notification", mBeanNotificationInfo7.getName());
    assertEquals("web application failed", mBeanNotificationInfo7.getDescription());
    assertEquals("web application is created", mBeanNotificationInfo2.getDescription());
    assertEquals("web application is deleted", mBeanNotificationInfo6.getDescription());
    assertEquals("web application is running", mBeanNotificationInfo3.getDescription());
    assertEquals("web application is stopped", mBeanNotificationInfo5.getDescription());
    assertEquals("web application start to stopped", mBeanNotificationInfo4.getDescription());
    assertEquals(1, mBeanNotificationInfo2.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo3.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo4.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo5.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo6.getNotifTypes().length);
    assertEquals(1, mBeanNotificationInfo7.getNotifTypes().length);
    assertEquals(7, actualNotificationInfo.length);
  }

  /**
   * Test {@link StandardContext#getWelcomeFiles()}.
   * <p>
   * Method under test: {@link StandardContext#getWelcomeFiles()}
   */
  @Test
  public void testGetWelcomeFiles() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).getWelcomeFiles().length);
  }
}
