package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.catalina.AccessLog;
import org.apache.catalina.Authenticator;
import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.Manager;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.ThreadBindingListener;
import org.apache.catalina.Valve;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.ApplicationServletRegistration;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.core.ThreadLocalLeakPreventionListener;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.catalina.webresources.ExtractingRoot;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.juli.logging.Log;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.SimpleInstanceManager;
import org.apache.tomcat.unittest.TesterContext;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.http.CookieProcessor;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.junit.Test;

public class FailedContextDiffblueTest {
  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenFailedContextParentIsFailedContext_thenReturnCatalina() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new FailedContext());

    // Act and Assert
    assertEquals("Catalina", failedContext.getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenFailedContextParentIsStandardContext() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", failedContext.getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor) Parent is {@link TesterContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenFailedContextParentIsTesterContext_thenReturnNull() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new TesterContext());

    // Act and Assert
    assertNull(failedContext.getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenFailedContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardContext parent = new StandardContext();
    parent.setDomain("Catalina");

    FailedContext failedContext = new FailedContext();
    failedContext.setParent(parent);

    // Act and Assert
    assertEquals("Catalina", failedContext.getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext parent = new StandardContext();
    parent.setParent(new StandardContext());

    FailedContext failedContext = new FailedContext();
    failedContext.setParent(parent);

    // Act and Assert
    assertEquals("Catalina", failedContext.getDomainInternal());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor) Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_givenFailedContextNameIsNumberSignNumberSign() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setName("##");

    // Act and Assert
    assertEquals(",context=/,container0=null", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Then return {@code ,context=/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_thenReturnContext() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new StandardEngine());
    failedContext.setName("/");

    // Act and Assert
    assertEquals(",context=/", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Then return {@code ,context=/,container0=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_thenReturnContextContainer0Null() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setName("/");

    // Act and Assert
    assertEquals(",context=/,container0=null", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Then return {@code ,context=/,container0=null,container1=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_thenReturnContextContainer0NullContainer1Null() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new StandardWrapper());
    failedContext.setName("/");

    // Act and Assert
    assertEquals(",context=/,container0=null,container1=null", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Then return {@code ,context=/,context=,container0=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_thenReturnContextContextContainer0Null() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setName(",context=");

    // Act and Assert
    assertEquals(",context=/,context=,container0=null", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getMBeanKeyProperties()}.
   * <ul>
   *   <li>Then return {@code ,context=/,host=null,container0=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FailedContext#getMBeanKeyProperties()}
   */
  @Test
  public void testGetMBeanKeyProperties_thenReturnContextHostNullContainer0Null() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setParent(new StandardHost());
    failedContext.setName("/");

    // Act and Assert
    assertEquals(",context=/,host=null,container0=null", failedContext.getMBeanKeyProperties());
  }

  /**
   * Test {@link FailedContext#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link FailedContext#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange
    StandardContext parent = new StandardContext();
    parent.setName("foo");

    FailedContext failedContext = new FailedContext();
    failedContext.setParent(parent);
    failedContext.setName("/");

    // Act and Assert
    assertEquals("j2eeType=WebModule,name=//foo/,J2EEApplication=none,J2EEServer=none",
        failedContext.getObjectNameKeyProperties());
  }

  /**
   * Test {@link FailedContext#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link FailedContext#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties2() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    failedContext.setName("j2eeType=WebModule,name=//");
    failedContext.setParent(new FailedContext());

    // Act and Assert
    assertEquals("j2eeType=WebModule,name=//DEFAULT/j2eeType=WebModule,name=//,J2EEApplication=none,J2EEServer=none",
        failedContext.getObjectNameKeyProperties());
  }

  /**
   * Test {@link FailedContext#startInternal()}.
   * <p>
   * Method under test: {@link FailedContext#startInternal()}
   */
  @Test
  public void testStartInternal() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new FailedContext()).startInternal());
  }

  /**
   * Test {@link FailedContext#findWatchedResources()}.
   * <p>
   * Method under test: {@link FailedContext#findWatchedResources()}
   */
  @Test
  public void testFindWatchedResources() {
    // Arrange, Act and Assert
    assertEquals(0, (new FailedContext()).findWatchedResources().length);
  }

  /**
   * Test {@link FailedContext#findChild(String)}.
   * <p>
   * Method under test: {@link FailedContext#findChild(String)}
   */
  @Test
  public void testFindChild() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findChild("Name"));
  }

  /**
   * Test {@link FailedContext#findChildren()}.
   * <p>
   * Method under test: {@link FailedContext#findChildren()}
   */
  @Test
  public void testFindChildren() {
    // Arrange, Act and Assert
    assertEquals(0, (new FailedContext()).findChildren().length);
  }

  /**
   * Test {@link FailedContext#getCharset(Locale)}.
   * <p>
   * Method under test: {@link FailedContext#getCharset(Locale)}
   */
  @Test
  public void testGetCharset() {
    // Arrange
    FailedContext failedContext = new FailedContext();

    // Act and Assert
    assertNull(failedContext.getCharset(Locale.getDefault()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FailedContext#setConfigFile(URL)}
   *   <li>{@link FailedContext#setDocBase(String)}
   *   <li>{@link FailedContext#setName(String)}
   *   <li>{@link FailedContext#setParent(Container)}
   *   <li>{@link FailedContext#setPath(String)}
   *   <li>{@link FailedContext#setWebappVersion(String)}
   *   <li>{@link FailedContext#addApplicationListener(String)}
   *   <li>{@link FailedContext#addApplicationParameter(ApplicationParameter)}
   *   <li>{@link FailedContext#addChild(Container)}
   *   <li>{@link FailedContext#addConstraint(SecurityConstraint)}
   *   <li>{@link FailedContext#addContainerListener(ContainerListener)}
   *   <li>{@link FailedContext#addErrorPage(ErrorPage)}
   *   <li>{@link FailedContext#addFilterDef(FilterDef)}
   *   <li>{@link FailedContext#addFilterMap(FilterMap)}
   *   <li>{@link FailedContext#addFilterMapBefore(FilterMap)}
   *   <li>{@link FailedContext#addLocaleEncodingMappingParameter(String, String)}
   *   <li>{@link FailedContext#addMimeMapping(String, String)}
   *   <li>{@link FailedContext#addParameter(String, String)}
   *   <li>{@link FailedContext#addPostConstructMethod(String, String)}
   *   <li>{@link FailedContext#addPreDestroyMethod(String, String)}
   *   <li>{@link FailedContext#addPropertyChangeListener(PropertyChangeListener)}
   *   <li>{@link FailedContext#addRoleMapping(String, String)}
   *   <li>{@link FailedContext#addSecurityRole(String)}
   *   <li>{@link FailedContext#addServletContainerInitializer(ServletContainerInitializer, Set)}
   *   <li>{@link FailedContext#addServletMappingDecoded(String, String, boolean)}
   *   <li>{@link FailedContext#addValve(Valve)}
   *   <li>{@link FailedContext#addWatchedResource(String)}
   *   <li>{@link FailedContext#addWelcomeFile(String)}
   *   <li>{@link FailedContext#addWrapperLifecycle(String)}
   *   <li>{@link FailedContext#addWrapperListener(String)}
   *   <li>{@link FailedContext#backgroundProcess()}
   *   <li>{@link FailedContext#decrementInProgressAsyncCount()}
   *   <li>{@link FailedContext#fireContainerEvent(String, Object)}
   *   <li>{@link FailedContext#incrementInProgressAsyncCount()}
   *   <li>{@link FailedContext#logAccess(Request, Response, long, boolean)}
   *   <li>{@link FailedContext#reload()}
   *   <li>{@link FailedContext#removeApplicationListener(String)}
   *   <li>{@link FailedContext#removeApplicationParameter(String)}
   *   <li>{@link FailedContext#removeChild(Container)}
   *   <li>{@link FailedContext#removeConstraint(SecurityConstraint)}
   *   <li>{@link FailedContext#removeContainerListener(ContainerListener)}
   *   <li>{@link FailedContext#removeErrorPage(ErrorPage)}
   *   <li>{@link FailedContext#removeFilterDef(FilterDef)}
   *   <li>{@link FailedContext#removeFilterMap(FilterMap)}
   *   <li>{@link FailedContext#removeMimeMapping(String)}
   *   <li>{@link FailedContext#removeParameter(String)}
   *   <li>{@link FailedContext#removePostConstructMethod(String)}
   *   <li>{@link FailedContext#removePreDestroyMethod(String)}
   *   <li>{@link FailedContext#removePropertyChangeListener(PropertyChangeListener)}
   *   <li>{@link FailedContext#removeRoleMapping(String)}
   *   <li>{@link FailedContext#removeSecurityRole(String)}
   *   <li>{@link FailedContext#removeServletMapping(String)}
   *   <li>{@link FailedContext#removeWatchedResource(String)}
   *   <li>{@link FailedContext#removeWelcomeFile(String)}
   *   <li>{@link FailedContext#removeWrapperLifecycle(String)}
   *   <li>{@link FailedContext#removeWrapperListener(String)}
   *   <li>{@link FailedContext#setAddWebinfClassesResources(boolean)}
   *   <li>{@link FailedContext#setAllowCasualMultipartParsing(boolean)}
   *   <li>{@link FailedContext#setAllowMultipleLeadingForwardSlashInPath(boolean)}
   *   <li>{@link FailedContext#setAltDDName(String)}
   *   <li>{@link FailedContext#setAlwaysAccessSession(boolean)}
   *   <li>{@link FailedContext#setApplicationEventListeners(Object[])}
   *   <li>{@link FailedContext#setApplicationLifecycleListeners(Object[])}
   *   <li>{@link FailedContext#setBackgroundProcessorDelay(int)}
   *   <li>{@link FailedContext#setCluster(Cluster)}
   *   <li>{@link FailedContext#setConfigured(boolean)}
   *   <li>{@link FailedContext#setContainerSciFilter(String)}
   *   <li>{@link FailedContext#setContextGetResourceRequiresSlash(boolean)}
   *   <li>{@link FailedContext#setCookieProcessor(CookieProcessor)}
   *   <li>{@link FailedContext#setCookies(boolean)}
   *   <li>{@link FailedContext#setCreateUploadTargets(boolean)}
   *   <li>{@link FailedContext#setCrossContext(boolean)}
   *   <li>{@link FailedContext#setDenyUncoveredHttpMethods(boolean)}
   *   <li>{@link FailedContext#setDispatcherWrapsSameObject(boolean)}
   *   <li>{@link FailedContext#setDispatchersUseEncodedPaths(boolean)}
   *   <li>{@link FailedContext#setDisplayName(String)}
   *   <li>{@link FailedContext#setDistributable(boolean)}
   *   <li>{@link FailedContext#setEffectiveMajorVersion(int)}
   *   <li>{@link FailedContext#setEffectiveMinorVersion(int)}
   *   <li>{@link FailedContext#setFireRequestListenersOnForwards(boolean)}
   *   <li>{@link FailedContext#setIgnoreAnnotations(boolean)}
   *   <li>{@link FailedContext#setInstanceManager(InstanceManager)}
   *   <li>{@link FailedContext#setJarScanner(JarScanner)}
   *   <li>{@link FailedContext#setJspConfigDescriptor(JspConfigDescriptor)}
   *   <li>{@link FailedContext#setLoader(Loader)}
   *   <li>{@link FailedContext#setLogEffectiveWebXml(boolean)}
   *   <li>{@link FailedContext#setLoginConfig(LoginConfig)}
   *   <li>{@link FailedContext#setManager(Manager)}
   *   <li>{@link FailedContext#setMapperContextRootRedirectEnabled(boolean)}
   *   <li>{@link FailedContext#setMapperDirectoryRedirectEnabled(boolean)}
   *   <li>{@link FailedContext#setMetadataComplete(boolean)}
   *   <li>{@link FailedContext#setNamingResources(NamingResourcesImpl)}
   *   <li>{@link FailedContext#setOverride(boolean)}
   *   <li>{@link FailedContext#setParallelAnnotationScanning(boolean)}
   *   <li>{@link FailedContext#setParentClassLoader(ClassLoader)}
   *   <li>{@link FailedContext#setPreemptiveAuthentication(boolean)}
   *   <li>{@link FailedContext#setPrivileged(boolean)}
   *   <li>{@link FailedContext#setPublicId(String)}
   *   <li>{@link FailedContext#setRealm(Realm)}
   *   <li>{@link FailedContext#setReloadable(boolean)}
   *   <li>{@link FailedContext#setRequestCharacterEncoding(String)}
   *   <li>{@link FailedContext#setResourceOnlyServlets(String)}
   *   <li>{@link FailedContext#setResources(WebResourceRoot)}
   *   <li>{@link FailedContext#setResponseCharacterEncoding(String)}
   *   <li>{@link FailedContext#setSendRedirectBody(boolean)}
   *   <li>{@link FailedContext#setSessionCookieDomain(String)}
   *   <li>{@link FailedContext#setSessionCookieName(String)}
   *   <li>{@link FailedContext#setSessionCookiePath(String)}
   *   <li>{@link FailedContext#setSessionCookiePathUsesTrailingSlash(boolean)}
   *   <li>{@link FailedContext#setSessionTimeout(int)}
   *   <li>{@link FailedContext#setStartStopThreads(int)}
   *   <li>{@link FailedContext#setSuspendWrappedResponseAfterForward(boolean)}
   *   <li>{@link FailedContext#setSwallowAbortedUploads(boolean)}
   *   <li>{@link FailedContext#setSwallowOutput(boolean)}
   *   <li>{@link FailedContext#setThreadBindingListener(ThreadBindingListener)}
   *   <li>{@link FailedContext#setTldValidation(boolean)}
   *   <li>{@link FailedContext#setUseHttpOnly(boolean)}
   *   <li>{@link FailedContext#setUsePartitioned(boolean)}
   *   <li>{@link FailedContext#setUseRelativeRedirects(boolean)}
   *   <li>{@link FailedContext#setValidateClientProvidedNewSessionId(boolean)}
   *   <li>{@link FailedContext#setWrapperClass(String)}
   *   <li>{@link FailedContext#setXmlBlockExternal(boolean)}
   *   <li>{@link FailedContext#setXmlNamespaceAware(boolean)}
   *   <li>{@link FailedContext#setXmlValidation(boolean)}
   *   <li>{@link FailedContext#stopInternal()}
   *   <li>{@link FailedContext#unbind(ClassLoader)}
   *   <li>{@link FailedContext#unbind(boolean, ClassLoader)}
   *   <li>{@link FailedContext#toString()}
   *   <li>{@link FailedContext#findApplicationListeners()}
   *   <li>{@link FailedContext#findApplicationParameters()}
   *   <li>{@link FailedContext#findConstraints()}
   *   <li>{@link FailedContext#findContainerListeners()}
   *   <li>{@link FailedContext#findErrorPages()}
   *   <li>{@link FailedContext#findFilterDefs()}
   *   <li>{@link FailedContext#findFilterMaps()}
   *   <li>{@link FailedContext#findMimeMappings()}
   *   <li>{@link FailedContext#findParameters()}
   *   <li>{@link FailedContext#findPostConstructMethods()}
   *   <li>{@link FailedContext#findPreDestroyMethods()}
   *   <li>{@link FailedContext#findSecurityRoles()}
   *   <li>{@link FailedContext#findServletMappings()}
   *   <li>{@link FailedContext#findWelcomeFiles()}
   *   <li>{@link FailedContext#findWrapperLifecycles()}
   *   <li>{@link FailedContext#findWrapperListeners()}
   *   <li>{@link FailedContext#getAccessLog()}
   *   <li>{@link FailedContext#getAddWebinfClassesResources()}
   *   <li>{@link FailedContext#getAllowCasualMultipartParsing()}
   *   <li>{@link FailedContext#getAllowMultipleLeadingForwardSlashInPath()}
   *   <li>{@link FailedContext#getAltDDName()}
   *   <li>{@link FailedContext#getAlwaysAccessSession()}
   *   <li>{@link FailedContext#getApplicationEventListeners()}
   *   <li>{@link FailedContext#getApplicationLifecycleListeners()}
   *   <li>{@link FailedContext#getAuthenticator()}
   *   <li>{@link FailedContext#getBackgroundProcessorDelay()}
   *   <li>{@link FailedContext#getBaseName()}
   *   <li>{@link FailedContext#getCatalinaBase()}
   *   <li>{@link FailedContext#getCatalinaHome()}
   *   <li>{@link FailedContext#getCluster()}
   *   <li>{@link FailedContext#getConfigFile()}
   *   <li>{@link FailedContext#getConfigured()}
   *   <li>{@link FailedContext#getContainerSciFilter()}
   *   <li>{@link FailedContext#getContextGetResourceRequiresSlash()}
   *   <li>{@link FailedContext#getCookieProcessor()}
   *   <li>{@link FailedContext#getCookies()}
   *   <li>{@link FailedContext#getCreateUploadTargets()}
   *   <li>{@link FailedContext#getCrossContext()}
   *   <li>{@link FailedContext#getDenyUncoveredHttpMethods()}
   *   <li>{@link FailedContext#getDispatcherWrapsSameObject()}
   *   <li>{@link FailedContext#getDispatchersUseEncodedPaths()}
   *   <li>{@link FailedContext#getDisplayName()}
   *   <li>{@link FailedContext#getDistributable()}
   *   <li>{@link FailedContext#getDocBase()}
   *   <li>{@link FailedContext#getEffectiveMajorVersion()}
   *   <li>{@link FailedContext#getEffectiveMinorVersion()}
   *   <li>{@link FailedContext#getEncodedPath()}
   *   <li>{@link FailedContext#getFireRequestListenersOnForwards()}
   *   <li>{@link FailedContext#getIgnoreAnnotations()}
   *   <li>{@link FailedContext#getInstanceManager()}
   *   <li>{@link FailedContext#getJarScanner()}
   *   <li>{@link FailedContext#getJspConfigDescriptor()}
   *   <li>{@link FailedContext#getLoader()}
   *   <li>{@link FailedContext#getLogEffectiveWebXml()}
   *   <li>{@link FailedContext#getLogName()}
   *   <li>{@link FailedContext#getLogger()}
   *   <li>{@link FailedContext#getLoginConfig()}
   *   <li>{@link FailedContext#getManager()}
   *   <li>{@link FailedContext#getMapperContextRootRedirectEnabled()}
   *   <li>{@link FailedContext#getMapperDirectoryRedirectEnabled()}
   *   <li>{@link FailedContext#getMetadataComplete()}
   *   <li>{@link FailedContext#getName()}
   *   <li>{@link FailedContext#getNamingResources()}
   *   <li>{@link FailedContext#getNamingToken()}
   *   <li>{@link FailedContext#getOverride()}
   *   <li>{@link FailedContext#getParallelAnnotationScanning()}
   *   <li>{@link FailedContext#getParent()}
   *   <li>{@link FailedContext#getParentClassLoader()}
   *   <li>{@link FailedContext#getPath()}
   *   <li>{@link FailedContext#getPaused()}
   *   <li>{@link FailedContext#getPipeline()}
   *   <li>{@link FailedContext#getPreemptiveAuthentication()}
   *   <li>{@link FailedContext#getPrivileged()}
   *   <li>{@link FailedContext#getPublicId()}
   *   <li>{@link FailedContext#getRealm()}
   *   <li>{@link FailedContext#getReloadable()}
   *   <li>{@link FailedContext#getRequestCharacterEncoding()}
   *   <li>{@link FailedContext#getResourceOnlyServlets()}
   *   <li>{@link FailedContext#getResources()}
   *   <li>{@link FailedContext#getResponseCharacterEncoding()}
   *   <li>{@link FailedContext#getSendRedirectBody()}
   *   <li>{@link FailedContext#getServletContext()}
   *   <li>{@link FailedContext#getSessionCookieDomain()}
   *   <li>{@link FailedContext#getSessionCookieName()}
   *   <li>{@link FailedContext#getSessionCookiePath()}
   *   <li>{@link FailedContext#getSessionCookiePathUsesTrailingSlash()}
   *   <li>{@link FailedContext#getSessionTimeout()}
   *   <li>{@link FailedContext#getStartStopThreads()}
   *   <li>{@link FailedContext#getSuspendWrappedResponseAfterForward()}
   *   <li>{@link FailedContext#getSwallowAbortedUploads()}
   *   <li>{@link FailedContext#getSwallowOutput()}
   *   <li>{@link FailedContext#getThreadBindingListener()}
   *   <li>{@link FailedContext#getTldValidation()}
   *   <li>{@link FailedContext#getUseHttpOnly()}
   *   <li>{@link FailedContext#getUsePartitioned()}
   *   <li>{@link FailedContext#getUseRelativeRedirects()}
   *   <li>{@link FailedContext#getValidateClientProvidedNewSessionId()}
   *   <li>{@link FailedContext#getWebappVersion()}
   *   <li>{@link FailedContext#getWrapperClass()}
   *   <li>{@link FailedContext#getXmlBlockExternal()}
   *   <li>{@link FailedContext#getXmlNamespaceAware()}
   *   <li>{@link FailedContext#getXmlValidation()}
   *   <li>{@link FailedContext#isServlet22()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws MalformedURLException, LifecycleException {
    // Arrange
    FailedContext failedContext = new FailedContext();
    URL configFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act
    failedContext.setConfigFile(configFile);
    failedContext.setDocBase("Doc Base");
    failedContext.setName("Name");
    StandardContext parent = new StandardContext();
    failedContext.setParent(parent);
    failedContext.setPath("Path");
    failedContext.setWebappVersion("1.0.2");
    failedContext.addApplicationListener("Listener");
    ApplicationParameter parameter = new ApplicationParameter();
    parameter.setDescription("The characteristics of someone or something");
    parameter.setName("Name");
    parameter.setOverride(true);
    parameter.setValue("42");
    failedContext.addApplicationParameter(parameter);
    failedContext.addChild(new StandardContext());
    failedContext.addConstraint(new SecurityConstraint());
    failedContext.addContainerListener(new ThreadLocalLeakPreventionListener());
    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setExceptionType("Exception Type");
    errorPage.setLocation("Location");
    failedContext.addErrorPage(errorPage);
    failedContext.addFilterDef(new FilterDef());
    failedContext.addFilterMap(new FilterMap());
    failedContext.addFilterMapBefore(new FilterMap());
    failedContext.addLocaleEncodingMappingParameter("en", "en");
    failedContext.addMimeMapping("Extension", "Mime Type");
    failedContext.addParameter("Name", "42");
    failedContext.addPostConstructMethod("Clazz", "Method");
    failedContext.addPreDestroyMethod("Clazz", "Method");
    failedContext.addPropertyChangeListener(new NamingContextListener());
    failedContext.addRoleMapping("Role", "Link");
    failedContext.addSecurityRole("Role");
    JasperInitializer sci = new JasperInitializer();
    failedContext.addServletContainerInitializer(sci, new HashSet<>());
    failedContext.addServletMappingDecoded("Pattern", "Name", true);
    failedContext.addValve(new BasicAuthenticator());
    failedContext.addWatchedResource("Name");
    failedContext.addWelcomeFile("Name");
    failedContext.addWrapperLifecycle("Listener");
    failedContext.addWrapperListener("Listener");
    failedContext.backgroundProcess();
    failedContext.decrementInProgressAsyncCount();
    failedContext.fireContainerEvent("Type", "Data");
    failedContext.incrementInProgressAsyncCount();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    failedContext.logAccess(request, new Response(new org.apache.coyote.Response()), 10L, true);
    failedContext.reload();
    failedContext.removeApplicationListener("Listener");
    failedContext.removeApplicationParameter("Name");
    failedContext.removeChild(new StandardContext());
    failedContext.removeConstraint(new SecurityConstraint());
    failedContext.removeContainerListener(new ThreadLocalLeakPreventionListener());
    ErrorPage errorPage2 = new ErrorPage();
    errorPage2.setCharset(Charset.forName("UTF-8"));
    errorPage2.setErrorCode(-1);
    errorPage2.setExceptionType("Exception Type");
    errorPage2.setLocation("Location");
    failedContext.removeErrorPage(errorPage2);
    failedContext.removeFilterDef(new FilterDef());
    failedContext.removeFilterMap(new FilterMap());
    failedContext.removeMimeMapping("Extension");
    failedContext.removeParameter("Name");
    failedContext.removePostConstructMethod("Clazz");
    failedContext.removePreDestroyMethod("Clazz");
    failedContext.removePropertyChangeListener(new NamingContextListener());
    failedContext.removeRoleMapping("Role");
    failedContext.removeSecurityRole("Role");
    failedContext.removeServletMapping("Pattern");
    failedContext.removeWatchedResource("Name");
    failedContext.removeWelcomeFile("Name");
    failedContext.removeWrapperLifecycle("Listener");
    failedContext.removeWrapperListener("Listener");
    failedContext.setAddWebinfClassesResources(true);
    failedContext.setAllowCasualMultipartParsing(true);
    failedContext.setAllowMultipleLeadingForwardSlashInPath(true);
    failedContext.setAltDDName("Alt DDName");
    failedContext.setAlwaysAccessSession(true);
    failedContext.setApplicationEventListeners(new Object[]{"Listeners"});
    failedContext.setApplicationLifecycleListeners(new Object[]{"Listeners"});
    failedContext.setBackgroundProcessorDelay(1);
    failedContext.setCluster(new SimpleTcpCluster());
    failedContext.setConfigured(true);
    failedContext.setContainerSciFilter("Container Sci Filter");
    failedContext.setContextGetResourceRequiresSlash(true);
    failedContext.setCookieProcessor(new Rfc6265CookieProcessor());
    failedContext.setCookies(true);
    failedContext.setCreateUploadTargets(true);
    failedContext.setCrossContext(true);
    failedContext.setDenyUncoveredHttpMethods(true);
    failedContext.setDispatcherWrapsSameObject(true);
    failedContext.setDispatchersUseEncodedPaths(true);
    failedContext.setDisplayName("Display Name");
    failedContext.setDistributable(true);
    failedContext.setEffectiveMajorVersion(1);
    failedContext.setEffectiveMinorVersion(1);
    failedContext.setFireRequestListenersOnForwards(true);
    failedContext.setIgnoreAnnotations(true);
    failedContext.setInstanceManager(new SimpleInstanceManager());
    failedContext.setJarScanner(new StandardJarScanner());
    ArrayList<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
    failedContext.setJspConfigDescriptor(new JspConfigDescriptorImpl(jspPropertyGroups, new ArrayList<>()));
    failedContext.setLoader(new WebappLoader());
    failedContext.setLogEffectiveWebXml(true);
    failedContext.setLoginConfig(ContextConfig.DUMMY_LOGIN_CONFIG);
    failedContext.setManager(new BackupManager());
    failedContext.setMapperContextRootRedirectEnabled(true);
    failedContext.setMapperDirectoryRedirectEnabled(true);
    failedContext.setMetadataComplete(true);
    failedContext.setNamingResources(new NamingResourcesImpl());
    failedContext.setOverride(true);
    failedContext.setParallelAnnotationScanning(true);
    failedContext.setParentClassLoader(new ParallelWebappClassLoader());
    failedContext.setPreemptiveAuthentication(true);
    failedContext.setPrivileged(true);
    failedContext.setPublicId("42");
    failedContext.setRealm(new AuthenticatedUserRealm());
    failedContext.setReloadable(true);
    failedContext.setRequestCharacterEncoding("UTF-8");
    failedContext.setResourceOnlyServlets("Resource Only Servlets");
    failedContext.setResources(new ExtractingRoot());
    failedContext.setResponseCharacterEncoding("UTF-8");
    failedContext.setSendRedirectBody(true);
    failedContext.setSessionCookieDomain("Session Cookie Domain");
    failedContext.setSessionCookieName("Session Cookie Name");
    failedContext.setSessionCookiePath("Session Cookie Path");
    failedContext.setSessionCookiePathUsesTrailingSlash(true);
    failedContext.setSessionTimeout(10);
    failedContext.setStartStopThreads(1);
    failedContext.setSuspendWrappedResponseAfterForward(true);
    failedContext.setSwallowAbortedUploads(true);
    failedContext.setSwallowOutput(true);
    failedContext.setThreadBindingListener(null);
    failedContext.setTldValidation(true);
    failedContext.setUseHttpOnly(true);
    failedContext.setUsePartitioned(true);
    failedContext.setUseRelativeRedirects(true);
    failedContext.setValidateClientProvidedNewSessionId(true);
    failedContext.setWrapperClass("Wrapper Class");
    failedContext.setXmlBlockExternal(true);
    failedContext.setXmlNamespaceAware(true);
    failedContext.setXmlValidation(true);
    failedContext.stopInternal();
    failedContext.unbind(new ParallelWebappClassLoader());
    failedContext.unbind(true, new ParallelWebappClassLoader());
    String actualToStringResult = failedContext.toString();
    String[] actualFindApplicationListenersResult = failedContext.findApplicationListeners();
    ApplicationParameter[] actualFindApplicationParametersResult = failedContext.findApplicationParameters();
    SecurityConstraint[] actualFindConstraintsResult = failedContext.findConstraints();
    ContainerListener[] actualFindContainerListenersResult = failedContext.findContainerListeners();
    ErrorPage[] actualFindErrorPagesResult = failedContext.findErrorPages();
    FilterDef[] actualFindFilterDefsResult = failedContext.findFilterDefs();
    FilterMap[] actualFindFilterMapsResult = failedContext.findFilterMaps();
    String[] actualFindMimeMappingsResult = failedContext.findMimeMappings();
    String[] actualFindParametersResult = failedContext.findParameters();
    Map<String, String> actualFindPostConstructMethodsResult = failedContext.findPostConstructMethods();
    Map<String, String> actualFindPreDestroyMethodsResult = failedContext.findPreDestroyMethods();
    String[] actualFindSecurityRolesResult = failedContext.findSecurityRoles();
    String[] actualFindServletMappingsResult = failedContext.findServletMappings();
    String[] actualFindWelcomeFilesResult = failedContext.findWelcomeFiles();
    String[] actualFindWrapperLifecyclesResult = failedContext.findWrapperLifecycles();
    String[] actualFindWrapperListenersResult = failedContext.findWrapperListeners();
    AccessLog actualAccessLog = failedContext.getAccessLog();
    boolean actualAddWebinfClassesResources = failedContext.getAddWebinfClassesResources();
    boolean actualAllowCasualMultipartParsing = failedContext.getAllowCasualMultipartParsing();
    boolean actualAllowMultipleLeadingForwardSlashInPath = failedContext.getAllowMultipleLeadingForwardSlashInPath();
    String actualAltDDName = failedContext.getAltDDName();
    boolean actualAlwaysAccessSession = failedContext.getAlwaysAccessSession();
    Object[] actualApplicationEventListeners = failedContext.getApplicationEventListeners();
    Object[] actualApplicationLifecycleListeners = failedContext.getApplicationLifecycleListeners();
    Authenticator actualAuthenticator = failedContext.getAuthenticator();
    int actualBackgroundProcessorDelay = failedContext.getBackgroundProcessorDelay();
    String actualBaseName = failedContext.getBaseName();
    File actualCatalinaBase = failedContext.getCatalinaBase();
    File actualCatalinaHome = failedContext.getCatalinaHome();
    Cluster actualCluster = failedContext.getCluster();
    URL actualConfigFile = failedContext.getConfigFile();
    boolean actualConfigured = failedContext.getConfigured();
    String actualContainerSciFilter = failedContext.getContainerSciFilter();
    boolean actualContextGetResourceRequiresSlash = failedContext.getContextGetResourceRequiresSlash();
    CookieProcessor actualCookieProcessor = failedContext.getCookieProcessor();
    boolean actualCookies = failedContext.getCookies();
    boolean actualCreateUploadTargets = failedContext.getCreateUploadTargets();
    boolean actualCrossContext = failedContext.getCrossContext();
    boolean actualDenyUncoveredHttpMethods = failedContext.getDenyUncoveredHttpMethods();
    boolean actualDispatcherWrapsSameObject = failedContext.getDispatcherWrapsSameObject();
    boolean actualDispatchersUseEncodedPaths = failedContext.getDispatchersUseEncodedPaths();
    String actualDisplayName = failedContext.getDisplayName();
    boolean actualDistributable = failedContext.getDistributable();
    String actualDocBase = failedContext.getDocBase();
    int actualEffectiveMajorVersion = failedContext.getEffectiveMajorVersion();
    int actualEffectiveMinorVersion = failedContext.getEffectiveMinorVersion();
    String actualEncodedPath = failedContext.getEncodedPath();
    boolean actualFireRequestListenersOnForwards = failedContext.getFireRequestListenersOnForwards();
    boolean actualIgnoreAnnotations = failedContext.getIgnoreAnnotations();
    InstanceManager actualInstanceManager = failedContext.getInstanceManager();
    JarScanner actualJarScanner = failedContext.getJarScanner();
    JspConfigDescriptor actualJspConfigDescriptor = failedContext.getJspConfigDescriptor();
    Loader actualLoader = failedContext.getLoader();
    boolean actualLogEffectiveWebXml = failedContext.getLogEffectiveWebXml();
    String actualLogName = failedContext.getLogName();
    Log actualLogger = failedContext.getLogger();
    LoginConfig actualLoginConfig = failedContext.getLoginConfig();
    Manager actualManager = failedContext.getManager();
    boolean actualMapperContextRootRedirectEnabled = failedContext.getMapperContextRootRedirectEnabled();
    boolean actualMapperDirectoryRedirectEnabled = failedContext.getMapperDirectoryRedirectEnabled();
    boolean actualMetadataComplete = failedContext.getMetadataComplete();
    String actualName = failedContext.getName();
    NamingResourcesImpl actualNamingResources = failedContext.getNamingResources();
    Object actualNamingToken = failedContext.getNamingToken();
    boolean actualOverride = failedContext.getOverride();
    boolean actualParallelAnnotationScanning = failedContext.getParallelAnnotationScanning();
    Container actualParent = failedContext.getParent();
    ClassLoader actualParentClassLoader = failedContext.getParentClassLoader();
    String actualPath = failedContext.getPath();
    boolean actualPaused = failedContext.getPaused();
    Pipeline actualPipeline = failedContext.getPipeline();
    boolean actualPreemptiveAuthentication = failedContext.getPreemptiveAuthentication();
    boolean actualPrivileged = failedContext.getPrivileged();
    String actualPublicId = failedContext.getPublicId();
    Realm actualRealm = failedContext.getRealm();
    boolean actualReloadable = failedContext.getReloadable();
    String actualRequestCharacterEncoding = failedContext.getRequestCharacterEncoding();
    String actualResourceOnlyServlets = failedContext.getResourceOnlyServlets();
    WebResourceRoot actualResources = failedContext.getResources();
    String actualResponseCharacterEncoding = failedContext.getResponseCharacterEncoding();
    boolean actualSendRedirectBody = failedContext.getSendRedirectBody();
    ServletContext actualServletContext = failedContext.getServletContext();
    String actualSessionCookieDomain = failedContext.getSessionCookieDomain();
    String actualSessionCookieName = failedContext.getSessionCookieName();
    String actualSessionCookiePath = failedContext.getSessionCookiePath();
    boolean actualSessionCookiePathUsesTrailingSlash = failedContext.getSessionCookiePathUsesTrailingSlash();
    int actualSessionTimeout = failedContext.getSessionTimeout();
    int actualStartStopThreads = failedContext.getStartStopThreads();
    boolean actualSuspendWrappedResponseAfterForward = failedContext.getSuspendWrappedResponseAfterForward();
    boolean actualSwallowAbortedUploads = failedContext.getSwallowAbortedUploads();
    boolean actualSwallowOutput = failedContext.getSwallowOutput();
    ThreadBindingListener actualThreadBindingListener = failedContext.getThreadBindingListener();
    boolean actualTldValidation = failedContext.getTldValidation();
    boolean actualUseHttpOnly = failedContext.getUseHttpOnly();
    boolean actualUsePartitioned = failedContext.getUsePartitioned();
    boolean actualUseRelativeRedirects = failedContext.getUseRelativeRedirects();
    boolean actualValidateClientProvidedNewSessionId = failedContext.getValidateClientProvidedNewSessionId();
    String actualWebappVersion = failedContext.getWebappVersion();
    String actualWrapperClass = failedContext.getWrapperClass();
    boolean actualXmlBlockExternal = failedContext.getXmlBlockExternal();
    boolean actualXmlNamespaceAware = failedContext.getXmlNamespaceAware();
    boolean actualXmlValidation = failedContext.getXmlValidation();

    // Assert
    assertEquals("1.0.2", actualWebappVersion);
    assertEquals("Doc Base", actualDocBase);
    assertEquals("Name", actualName);
    assertEquals("Name", actualToStringResult);
    assertEquals("Path", actualPath);
    assertNull(actualApplicationEventListeners);
    assertNull(actualApplicationLifecycleListeners);
    assertNull(actualFindApplicationListenersResult);
    assertNull(actualFindMimeMappingsResult);
    assertNull(actualFindParametersResult);
    assertNull(actualFindSecurityRolesResult);
    assertNull(actualFindServletMappingsResult);
    assertNull(actualFindWelcomeFilesResult);
    assertNull(actualFindWrapperLifecyclesResult);
    assertNull(actualFindWrapperListenersResult);
    assertNull(actualFindContainerListenersResult);
    assertNull(actualFindApplicationParametersResult);
    assertNull(actualFindErrorPagesResult);
    assertNull(actualFindFilterDefsResult);
    assertNull(actualFindFilterMapsResult);
    assertNull(actualFindConstraintsResult);
    assertNull(actualServletContext);
    assertNull(actualJspConfigDescriptor);
    assertNull(actualCatalinaBase);
    assertNull(actualCatalinaHome);
    assertNull(actualParentClassLoader);
    assertNull(actualNamingToken);
    assertNull(actualAltDDName);
    assertNull(actualBaseName);
    assertNull(actualContainerSciFilter);
    assertNull(actualDisplayName);
    assertNull(actualEncodedPath);
    assertNull(actualLogName);
    assertNull(actualPublicId);
    assertNull(actualRequestCharacterEncoding);
    assertNull(actualResourceOnlyServlets);
    assertNull(actualResponseCharacterEncoding);
    assertNull(actualSessionCookieDomain);
    assertNull(actualSessionCookieName);
    assertNull(actualSessionCookiePath);
    assertNull(actualWrapperClass);
    assertNull(actualFindPostConstructMethodsResult);
    assertNull(actualFindPreDestroyMethodsResult);
    assertNull(actualAccessLog);
    assertNull(actualAuthenticator);
    assertNull(actualCluster);
    assertNull(actualLoader);
    assertNull(actualManager);
    assertNull(actualPipeline);
    assertNull(actualRealm);
    assertNull(actualThreadBindingListener);
    assertNull(actualResources);
    assertNull(actualNamingResources);
    assertNull(actualLogger);
    assertNull(actualInstanceManager);
    assertNull(actualJarScanner);
    assertNull(actualLoginConfig);
    assertNull(actualCookieProcessor);
    assertEquals(-1, actualBackgroundProcessorDelay);
    assertEquals(0, actualEffectiveMajorVersion);
    assertEquals(0, actualEffectiveMinorVersion);
    assertEquals(0, actualSessionTimeout);
    assertEquals(0, actualStartStopThreads);
    assertFalse(actualAddWebinfClassesResources);
    assertFalse(actualAllowCasualMultipartParsing);
    assertFalse(actualAllowMultipleLeadingForwardSlashInPath);
    assertFalse(actualAlwaysAccessSession);
    assertFalse(actualConfigured);
    assertFalse(actualContextGetResourceRequiresSlash);
    assertFalse(actualCookies);
    assertFalse(actualCreateUploadTargets);
    assertFalse(actualCrossContext);
    assertFalse(actualDenyUncoveredHttpMethods);
    assertFalse(actualDispatcherWrapsSameObject);
    assertFalse(actualDistributable);
    assertFalse(actualFireRequestListenersOnForwards);
    assertFalse(actualIgnoreAnnotations);
    assertFalse(actualLogEffectiveWebXml);
    assertFalse(actualMapperContextRootRedirectEnabled);
    assertFalse(actualMapperDirectoryRedirectEnabled);
    assertFalse(actualMetadataComplete);
    assertFalse(actualOverride);
    assertFalse(actualParallelAnnotationScanning);
    assertFalse(actualPaused);
    assertFalse(actualPreemptiveAuthentication);
    assertFalse(actualPrivileged);
    assertFalse(actualReloadable);
    assertFalse(actualSendRedirectBody);
    assertFalse(actualSessionCookiePathUsesTrailingSlash);
    assertFalse(actualSuspendWrappedResponseAfterForward);
    assertFalse(actualSwallowAbortedUploads);
    assertFalse(actualSwallowOutput);
    assertFalse(actualTldValidation);
    assertFalse(actualUseHttpOnly);
    assertFalse(actualUsePartitioned);
    assertFalse(actualValidateClientProvidedNewSessionId);
    assertFalse(actualXmlNamespaceAware);
    assertFalse(actualXmlValidation);
    assertFalse(failedContext.isServlet22());
    assertTrue(actualDispatchersUseEncodedPaths);
    assertTrue(actualUseRelativeRedirects);
    assertTrue(actualXmlBlockExternal);
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, actualConfigFile.toString());
    assertSame(parent, actualParent);
    assertSame(configFile, actualConfigFile);
  }

  /**
   * Test {@link FailedContext#findErrorPage(int)} with {@code errorCode}.
   * <p>
   * Method under test: {@link FailedContext#findErrorPage(int)}
   */
  @Test
  public void testFindErrorPageWithErrorCode() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findErrorPage(-1));
  }

  /**
   * Test {@link FailedContext#findErrorPage(Throwable)} with {@code throwable}.
   * <p>
   * Method under test: {@link FailedContext#findErrorPage(Throwable)}
   */
  @Test
  public void testFindErrorPageWithThrowable() {
    // Arrange
    FailedContext failedContext = new FailedContext();

    // Act and Assert
    assertNull(failedContext.findErrorPage(new Throwable()));
  }

  /**
   * Test {@link FailedContext#findFilterDef(String)}.
   * <p>
   * Method under test: {@link FailedContext#findFilterDef(String)}
   */
  @Test
  public void testFindFilterDef() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findFilterDef("Filter Name"));
  }

  /**
   * Test {@link FailedContext#findMimeMapping(String)}.
   * <p>
   * Method under test: {@link FailedContext#findMimeMapping(String)}
   */
  @Test
  public void testFindMimeMapping() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findMimeMapping("Extension"));
  }

  /**
   * Test {@link FailedContext#findParameter(String)}.
   * <p>
   * Method under test: {@link FailedContext#findParameter(String)}
   */
  @Test
  public void testFindParameter() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findParameter("Name"));
  }

  /**
   * Test {@link FailedContext#findRoleMapping(String)}.
   * <p>
   * Method under test: {@link FailedContext#findRoleMapping(String)}
   */
  @Test
  public void testFindRoleMapping() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findRoleMapping("Role"));
  }

  /**
   * Test {@link FailedContext#findSecurityRole(String)}.
   * <p>
   * Method under test: {@link FailedContext#findSecurityRole(String)}
   */
  @Test
  public void testFindSecurityRole() {
    // Arrange, Act and Assert
    assertFalse((new FailedContext()).findSecurityRole("Role"));
  }

  /**
   * Test {@link FailedContext#findServletMapping(String)}.
   * <p>
   * Method under test: {@link FailedContext#findServletMapping(String)}
   */
  @Test
  public void testFindServletMapping() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findServletMapping("Pattern"));
  }

  /**
   * Test {@link FailedContext#findWelcomeFile(String)}.
   * <p>
   * Method under test: {@link FailedContext#findWelcomeFile(String)}
   */
  @Test
  public void testFindWelcomeFile() {
    // Arrange, Act and Assert
    assertFalse((new FailedContext()).findWelcomeFile("Name"));
  }

  /**
   * Test {@link FailedContext#createInstanceManager()}.
   * <p>
   * Method under test: {@link FailedContext#createInstanceManager()}
   */
  @Test
  public void testCreateInstanceManager() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).createInstanceManager());
  }

  /**
   * Test {@link FailedContext#createWrapper()}.
   * <p>
   * Method under test: {@link FailedContext#createWrapper()}
   */
  @Test
  public void testCreateWrapper() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).createWrapper());
  }

  /**
   * Test {@link FailedContext#getRealPath(String)}.
   * <p>
   * Method under test: {@link FailedContext#getRealPath(String)}
   */
  @Test
  public void testGetRealPath() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).getRealPath("Path"));
  }

  /**
   * Test {@link FailedContext#addServletSecurity(Dynamic, ServletSecurityElement)}.
   * <p>
   * Method under test: {@link FailedContext#addServletSecurity(Dynamic, ServletSecurityElement)}
   */
  @Test
  public void testAddServletSecurity() {
    // Arrange
    FailedContext failedContext = new FailedContext();
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration registration = new ApplicationServletRegistration(wrapper, new StandardContext());

    // Act and Assert
    assertNull(failedContext.addServletSecurity(registration, new ServletSecurityElement()));
  }

  /**
   * Test {@link FailedContext#isResourceOnlyServlet(String)}.
   * <p>
   * Method under test: {@link FailedContext#isResourceOnlyServlet(String)}
   */
  @Test
  public void testIsResourceOnlyServlet() {
    // Arrange, Act and Assert
    assertFalse((new FailedContext()).isResourceOnlyServlet("Servlet Name"));
  }

  /**
   * Test {@link FailedContext#findPostConstructMethod(String)}.
   * <p>
   * Method under test: {@link FailedContext#findPostConstructMethod(String)}
   */
  @Test
  public void testFindPostConstructMethod() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findPostConstructMethod("Clazz"));
  }

  /**
   * Test {@link FailedContext#findPreDestroyMethod(String)}.
   * <p>
   * Method under test: {@link FailedContext#findPreDestroyMethod(String)}
   */
  @Test
  public void testFindPreDestroyMethod() {
    // Arrange, Act and Assert
    assertNull((new FailedContext()).findPreDestroyMethod("Clazz"));
  }

  /**
   * Test {@link FailedContext#bind(ClassLoader)} with {@code originalClassLoader}.
   * <p>
   * Method under test: {@link FailedContext#bind(ClassLoader)}
   */
  @Test
  public void testBindWithOriginalClassLoader() {
    // Arrange
    FailedContext failedContext = new FailedContext();

    // Act and Assert
    assertNull(failedContext.bind(new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link FailedContext#bind(boolean, ClassLoader)} with {@code usePrivilegedAction}, {@code originalClassLoader}.
   * <p>
   * Method under test: {@link FailedContext#bind(boolean, ClassLoader)}
   */
  @Test
  public void testBindWithUsePrivilegedActionOriginalClassLoader() {
    // Arrange
    FailedContext failedContext = new FailedContext();

    // Act and Assert
    assertNull(failedContext.bind(true, new ParallelWebappClassLoader()));
  }

  /**
   * Test new {@link FailedContext} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FailedContext}
   */
  @Test
  public void testNewFailedContext() {
    // Arrange and Act
    FailedContext actualFailedContext = new FailedContext();

    // Assert
    assertEquals("Catalina", actualFailedContext.getDomain());
    assertEquals("NEW", actualFailedContext.getStateName());
    assertEquals("decode", actualFailedContext.getEncodedReverseSolidusHandling());
    assertEquals("reject", actualFailedContext.getEncodedSolidusHandling());
    assertNull(actualFailedContext.getApplicationEventListeners());
    assertNull(actualFailedContext.getApplicationLifecycleListeners());
    assertNull(actualFailedContext.findApplicationListeners());
    assertNull(actualFailedContext.findMimeMappings());
    assertNull(actualFailedContext.findParameters());
    assertNull(actualFailedContext.findSecurityRoles());
    assertNull(actualFailedContext.findServletMappings());
    assertNull(actualFailedContext.findWelcomeFiles());
    assertNull(actualFailedContext.findWrapperLifecycles());
    assertNull(actualFailedContext.findWrapperListeners());
    assertNull(actualFailedContext.findContainerListeners());
    assertNull(actualFailedContext.findApplicationParameters());
    assertNull(actualFailedContext.findErrorPages());
    assertNull(actualFailedContext.findFilterDefs());
    assertNull(actualFailedContext.findFilterMaps());
    assertNull(actualFailedContext.findConstraints());
    assertNull(actualFailedContext.getServletContext());
    assertNull(actualFailedContext.getJspConfigDescriptor());
    assertNull(actualFailedContext.getCatalinaBase());
    assertNull(actualFailedContext.getCatalinaHome());
    assertNull(actualFailedContext.getParentClassLoader());
    assertNull(actualFailedContext.getNamingToken());
    assertNull(actualFailedContext.getAltDDName());
    assertNull(actualFailedContext.getBaseName());
    assertNull(actualFailedContext.getContainerSciFilter());
    assertNull(actualFailedContext.getDisplayName());
    assertNull(actualFailedContext.getDocBase());
    assertNull(actualFailedContext.getDomainInternal());
    assertNull(actualFailedContext.getEncodedPath());
    assertNull(actualFailedContext.getLogName());
    assertNull(actualFailedContext.getName());
    assertNull(actualFailedContext.getPath());
    assertNull(actualFailedContext.getPublicId());
    assertNull(actualFailedContext.getRequestCharacterEncoding());
    assertNull(actualFailedContext.getResourceOnlyServlets());
    assertNull(actualFailedContext.getResponseCharacterEncoding());
    assertNull(actualFailedContext.getSessionCookieDomain());
    assertNull(actualFailedContext.getSessionCookieName());
    assertNull(actualFailedContext.getSessionCookiePath());
    assertNull(actualFailedContext.getWebappVersion());
    assertNull(actualFailedContext.getWrapperClass());
    assertNull(actualFailedContext.getConfigFile());
    assertNull(actualFailedContext.findPostConstructMethods());
    assertNull(actualFailedContext.findPreDestroyMethods());
    assertNull(actualFailedContext.getObjectName());
    assertNull(actualFailedContext.getAccessLog());
    assertNull(actualFailedContext.getAuthenticator());
    assertNull(actualFailedContext.getCluster());
    assertNull(actualFailedContext.getParent());
    assertNull(actualFailedContext.getLoader());
    assertNull(actualFailedContext.getManager());
    assertNull(actualFailedContext.getPipeline());
    assertNull(actualFailedContext.getRealm());
    assertNull(actualFailedContext.getThreadBindingListener());
    assertNull(actualFailedContext.getResources());
    assertNull(actualFailedContext.getNamingResources());
    assertNull(actualFailedContext.getLogger());
    assertNull(actualFailedContext.getInstanceManager());
    assertNull(actualFailedContext.getJarScanner());
    assertNull(actualFailedContext.getLoginConfig());
    assertNull(actualFailedContext.getCookieProcessor());
    assertEquals(-1, actualFailedContext.getBackgroundProcessorDelay());
    assertEquals(0, actualFailedContext.getEffectiveMajorVersion());
    assertEquals(0, actualFailedContext.getEffectiveMinorVersion());
    assertEquals(0, actualFailedContext.getSessionTimeout());
    assertEquals(0, actualFailedContext.getStartStopThreads());
    assertEquals(0, actualFailedContext.findChildren().length);
    assertEquals(0, actualFailedContext.findWatchedResources().length);
    assertEquals(0, actualFailedContext.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualFailedContext.getState());
    assertEquals(EncodedSolidusHandling.DECODE, actualFailedContext.getEncodedReverseSolidusHandlingEnum());
    assertEquals(EncodedSolidusHandling.REJECT, actualFailedContext.getEncodedSolidusHandlingEnum());
    assertFalse(actualFailedContext.getAddWebinfClassesResources());
    assertFalse(actualFailedContext.getAllowCasualMultipartParsing());
    assertFalse(actualFailedContext.getAllowMultipleLeadingForwardSlashInPath());
    assertFalse(actualFailedContext.getAlwaysAccessSession());
    assertFalse(actualFailedContext.getConfigured());
    assertFalse(actualFailedContext.getContextGetResourceRequiresSlash());
    assertFalse(actualFailedContext.getCookies());
    assertFalse(actualFailedContext.getCreateUploadTargets());
    assertFalse(actualFailedContext.getCrossContext());
    assertFalse(actualFailedContext.getDenyUncoveredHttpMethods());
    assertFalse(actualFailedContext.getDispatcherWrapsSameObject());
    assertFalse(actualFailedContext.getDistributable());
    assertFalse(actualFailedContext.getFireRequestListenersOnForwards());
    assertFalse(actualFailedContext.getIgnoreAnnotations());
    assertFalse(actualFailedContext.getLogEffectiveWebXml());
    assertFalse(actualFailedContext.getMapperContextRootRedirectEnabled());
    assertFalse(actualFailedContext.getMapperDirectoryRedirectEnabled());
    assertFalse(actualFailedContext.getMetadataComplete());
    assertFalse(actualFailedContext.getOverride());
    assertFalse(actualFailedContext.getParallelAnnotationScanning());
    assertFalse(actualFailedContext.getPaused());
    assertFalse(actualFailedContext.getPreemptiveAuthentication());
    assertFalse(actualFailedContext.getPrivileged());
    assertFalse(actualFailedContext.getReloadable());
    assertFalse(actualFailedContext.getSendRedirectBody());
    assertFalse(actualFailedContext.getSessionCookiePathUsesTrailingSlash());
    assertFalse(actualFailedContext.getSuspendWrappedResponseAfterForward());
    assertFalse(actualFailedContext.getSwallowAbortedUploads());
    assertFalse(actualFailedContext.getSwallowOutput());
    assertFalse(actualFailedContext.getTldValidation());
    assertFalse(actualFailedContext.getUseHttpOnly());
    assertFalse(actualFailedContext.getUsePartitioned());
    assertFalse(actualFailedContext.getValidateClientProvidedNewSessionId());
    assertFalse(actualFailedContext.getXmlNamespaceAware());
    assertFalse(actualFailedContext.getXmlValidation());
    assertFalse(actualFailedContext.isServlet22());
    assertTrue(actualFailedContext.getDispatchersUseEncodedPaths());
    assertTrue(actualFailedContext.getUseRelativeRedirects());
    assertTrue(actualFailedContext.getXmlBlockExternal());
    assertTrue(actualFailedContext.getThrowOnFailure());
  }
}
