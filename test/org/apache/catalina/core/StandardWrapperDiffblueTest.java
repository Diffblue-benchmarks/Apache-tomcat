package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.TestServletSecurityMappings;
import jakarta.servlet.annotation.TestServletSecurityMappings.SecureDefault;
import java.util.ArrayList;
import java.util.HashMap;
import javax.management.MBeanNotificationInfo;
import org.apache.catalina.Container;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.manager.HTMLManagerServlet;
import org.apache.catalina.manager.JMXProxyServlet;
import org.apache.catalina.manager.StatusManagerServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.junit.Test;

public class StandardWrapperDiffblueTest {
  /**
   * Test new {@link StandardWrapper} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardWrapper}
   */
  @Test
  public void testNewStandardWrapper() {
    // Arrange and Act
    StandardWrapper actualStandardWrapper = new StandardWrapper();

    // Assert
    assertTrue(actualStandardWrapper.getPipeline() instanceof StandardPipeline);
    assertEquals(",servlet=null,container0=null", actualStandardWrapper.getMBeanKeyProperties());
    assertEquals("-1", actualStandardWrapper.getLoadOnStartupString());
    assertEquals("Catalina", actualStandardWrapper.getDomain());
    assertEquals("NEW", actualStandardWrapper.getStateName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardWrapper.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardWrapper.logName);
    assertNull(actualStandardWrapper.getMultipartConfigElement());
    assertNull(actualStandardWrapper.getServlet());
    assertNull(actualStandardWrapper.getServletContext());
    assertNull(actualStandardWrapper.getCatalinaBase());
    assertNull(actualStandardWrapper.getCatalinaHome());
    assertNotNull(actualStandardWrapper.getParentClassLoader());
    assertNull(actualStandardWrapper.parentClassLoader);
    assertNull(actualStandardWrapper.getDomainInternal());
    assertNull(actualStandardWrapper.getName());
    assertNull(actualStandardWrapper.getRunAs());
    assertNull(actualStandardWrapper.getServletClass());
    assertNull(actualStandardWrapper.getServletName());
    assertNull(actualStandardWrapper.startStopExecutor);
    assertNull(actualStandardWrapper.backgroundProcessorFuture);
    assertNull(actualStandardWrapper.monitorFuture);
    assertNull(actualStandardWrapper.getObjectName());
    assertNull(actualStandardWrapper.jspMonitorON);
    assertNull(actualStandardWrapper.getAccessLog());
    assertNull(actualStandardWrapper.accessLog);
    assertNull(actualStandardWrapper.getCluster());
    assertNull(actualStandardWrapper.getClusterInternal());
    assertNull(actualStandardWrapper.cluster);
    assertNull(actualStandardWrapper.getParent());
    assertNull(actualStandardWrapper.getRealm());
    assertNull(actualStandardWrapper.getRealmInternal());
    assertEquals(-1, actualStandardWrapper.getBackgroundProcessorDelay());
    assertEquals(-1, actualStandardWrapper.getLoadOnStartup());
    assertEquals(-1, actualStandardWrapper.loadOnStartup);
    assertEquals(0, actualStandardWrapper.getClassLoadTime());
    assertEquals(0, actualStandardWrapper.getCountAllocated());
    assertEquals(0, actualStandardWrapper.findContainerListeners().length);
    assertEquals(0, actualStandardWrapper.getChildren().length);
    assertEquals(0, actualStandardWrapper.findLifecycleListeners().length);
    assertEquals(0L, actualStandardWrapper.getAvailable());
    assertEquals(0L, actualStandardWrapper.getErrorCount());
    assertEquals(0L, actualStandardWrapper.getLoadTime());
    assertEquals(0L, actualStandardWrapper.getMaxTime());
    assertEquals(0L, actualStandardWrapper.getProcessingTime());
    assertEquals(0L, actualStandardWrapper.getRequestCount());
    assertEquals(0L, actualStandardWrapper.sequenceNumber);
    assertEquals(1, actualStandardWrapper.getStartStopThreads());
    assertEquals(2000L, actualStandardWrapper.unloadDelay);
    MBeanNotificationInfo[] notificationInfo = actualStandardWrapper.getNotificationInfo();
    assertEquals(6, notificationInfo.length);
    assertEquals(LifecycleState.NEW, actualStandardWrapper.getState());
    assertFalse(actualStandardWrapper.isAsyncSupported());
    assertFalse(actualStandardWrapper.isOverridable());
    assertFalse(actualStandardWrapper.instanceInitialized);
    assertFalse(actualStandardWrapper.isJspServlet);
    assertFalse(actualStandardWrapper.swallowOutput);
    assertFalse(actualStandardWrapper.unloading);
    assertTrue(actualStandardWrapper.mappings.isEmpty());
    assertTrue(actualStandardWrapper.children.isEmpty());
    assertTrue(actualStandardWrapper.parameters.isEmpty());
    assertTrue(actualStandardWrapper.references.isEmpty());
    assertTrue(actualStandardWrapper.listeners.isEmpty());
    assertTrue(actualStandardWrapper.getStartChildren());
    assertTrue(actualStandardWrapper.isEnabled());
    assertTrue(actualStandardWrapper.getThrowOnFailure());
    assertEquals(Long.MAX_VALUE, actualStandardWrapper.getMinTime());
    assertSame(notificationInfo, actualStandardWrapper.notificationInfo);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardWrapper#setAsyncSupported(boolean)}
   *   <li>{@link StandardWrapper#setEnabled(boolean)}
   *   <li>{@link StandardWrapper#setMultipartConfigElement(MultipartConfigElement)}
   *   <li>{@link StandardWrapper#setOverridable(boolean)}
   *   <li>{@link StandardWrapper#setServlet(Servlet)}
   *   <li>{@link StandardWrapper#getAvailable()}
   *   <li>{@link StandardWrapper#getClassLoadTime()}
   *   <li>{@link StandardWrapper#getLoadTime()}
   *   <li>{@link StandardWrapper#getMultipartConfigElement()}
   *   <li>{@link StandardWrapper#getRunAs()}
   *   <li>{@link StandardWrapper#getServlet()}
   *   <li>{@link StandardWrapper#getServletClass()}
   *   <li>{@link StandardWrapper#isAsyncSupported()}
   *   <li>{@link StandardWrapper#isEnabled()}
   *   <li>{@link StandardWrapper#isOverridable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setAsyncSupported(true);
    standardWrapper.setEnabled(true);
    MultipartConfigElement multipartConfigElement = new MultipartConfigElement("Location");
    standardWrapper.setMultipartConfigElement(multipartConfigElement);
    standardWrapper.setOverridable(true);
    HTMLManagerServlet servlet = new HTMLManagerServlet();
    standardWrapper.setServlet(servlet);
    long actualAvailable = standardWrapper.getAvailable();
    int actualClassLoadTime = standardWrapper.getClassLoadTime();
    long actualLoadTime = standardWrapper.getLoadTime();
    MultipartConfigElement actualMultipartConfigElement = standardWrapper.getMultipartConfigElement();
    String actualRunAs = standardWrapper.getRunAs();
    Servlet actualServlet = standardWrapper.getServlet();
    String actualServletClass = standardWrapper.getServletClass();
    boolean actualIsAsyncSupportedResult = standardWrapper.isAsyncSupported();
    boolean actualIsEnabledResult = standardWrapper.isEnabled();

    // Assert
    assertNull(actualRunAs);
    assertNull(actualServletClass);
    assertEquals(0, actualClassLoadTime);
    assertEquals(0L, actualAvailable);
    assertEquals(0L, actualLoadTime);
    assertTrue(actualIsAsyncSupportedResult);
    assertTrue(actualIsEnabledResult);
    assertTrue(standardWrapper.isOverridable());
    assertSame(multipartConfigElement, actualMultipartConfigElement);
    assertSame(servlet, actualServlet);
  }

  /**
   * Test {@link StandardWrapper#setAvailable(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then {@link StandardWrapper} (default constructor) Available is {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setAvailable(long)}
   */
  @Test
  public void testSetAvailable_whenMax_value_thenStandardWrapperAvailableIsMax_value() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setAvailable(Long.MAX_VALUE);

    // Assert
    assertEquals(Long.MAX_VALUE, standardWrapper.getAvailable());
  }

  /**
   * Test {@link StandardWrapper#getCountAllocated()}.
   * <p>
   * Method under test: {@link StandardWrapper#getCountAllocated()}
   */
  @Test
  public void testGetCountAllocated() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardWrapper()).getCountAllocated());
  }

  /**
   * Test {@link StandardWrapper#getLoadOnStartup()}.
   * <p>
   * Method under test: {@link StandardWrapper#getLoadOnStartup()}
   */
  @Test
  public void testGetLoadOnStartup() {
    // Arrange, Act and Assert
    assertEquals(-1, (new StandardWrapper()).getLoadOnStartup());
  }

  /**
   * Test {@link StandardWrapper#setLoadOnStartup(int)}.
   * <p>
   * Method under test: {@link StandardWrapper#setLoadOnStartup(int)}
   */
  @Test
  public void testSetLoadOnStartup() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setLoadOnStartup(42);

    // Assert
    assertEquals("42", standardWrapper.getLoadOnStartupString());
    assertEquals(42, standardWrapper.getLoadOnStartup());
    assertEquals(42, standardWrapper.loadOnStartup);
  }

  /**
   * Test {@link StandardWrapper#setLoadOnStartupString(String)}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) LoadOnStartupString is {@code 0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setLoadOnStartupString(String)}
   */
  @Test
  public void testSetLoadOnStartupString_thenStandardWrapperLoadOnStartupStringIs0() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setLoadOnStartupString("loadOnStartup");

    // Assert
    assertEquals("0", standardWrapper.getLoadOnStartupString());
    assertEquals(0, standardWrapper.getLoadOnStartup());
    assertEquals(0, standardWrapper.loadOnStartup);
  }

  /**
   * Test {@link StandardWrapper#setLoadOnStartupString(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link StandardWrapper} (default constructor) LoadOnStartupString is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setLoadOnStartupString(String)}
   */
  @Test
  public void testSetLoadOnStartupString_when42_thenStandardWrapperLoadOnStartupStringIs42() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setLoadOnStartupString("42");

    // Assert
    assertEquals("42", standardWrapper.getLoadOnStartupString());
    assertEquals(42, standardWrapper.getLoadOnStartup());
    assertEquals(42, standardWrapper.loadOnStartup);
  }

  /**
   * Test {@link StandardWrapper#getLoadOnStartupString()}.
   * <p>
   * Method under test: {@link StandardWrapper#getLoadOnStartupString()}
   */
  @Test
  public void testGetLoadOnStartupString() {
    // Arrange, Act and Assert
    assertEquals("-1", (new StandardWrapper()).getLoadOnStartupString());
  }

  /**
   * Test {@link StandardWrapper#setParent(Container)}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) LogName is {@code org.apache.catalina.core.ContainerBase.[/]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setParent(Container)}
   */
  @Test
  public void testSetParent_thenStandardWrapperLogNameIsOrgApacheCatalinaCoreContainerBase() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setParent(null);

    // Assert that nothing has changed
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", standardWrapper.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", standardWrapper.logName);
  }

  /**
   * Test {@link StandardWrapper#setParent(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setParent(Container)}
   */
  @Test
  public void testSetParent_whenStandardContext_thenStandardWrapperDomainInternalIsCatalina() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    StandardContext container = new StandardContext();

    // Act
    standardWrapper.setParent(container);

    // Assert
    assertEquals("Catalina", standardWrapper.getDomainInternal());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", standardWrapper.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", standardWrapper.logName);
    assertSame(container, standardWrapper.getParent());
  }

  /**
   * Test {@link StandardWrapper#setParent(Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setParent(Container)}
   */
  @Test
  public void testSetParent_whenStandardEngine_thenThrowIllegalArgumentException() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardWrapper.setParent(new StandardEngine()));
  }

  /**
   * Test {@link StandardWrapper#setRunAs(String)}.
   * <p>
   * Method under test: {@link StandardWrapper#setRunAs(String)}
   */
  @Test
  public void testSetRunAs() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setRunAs("Run As");

    // Assert
    assertEquals("Run As", standardWrapper.getRunAs());
  }

  /**
   * Test {@link StandardWrapper#setServletClass(String)}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) LoadOnStartupString is {@code 2147483647}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setServletClass(String)}
   */
  @Test
  public void testSetServletClass_thenStandardWrapperLoadOnStartupStringIs2147483647() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setServletClass(Constants.JSP_SERVLET_CLASS);

    // Assert
    assertEquals("2147483647", standardWrapper.getLoadOnStartupString());
    assertTrue(standardWrapper.isJspServlet);
    assertEquals(Integer.MAX_VALUE, standardWrapper.getLoadOnStartup());
    assertEquals(Constants.JSP_SERVLET_CLASS, standardWrapper.getServletClass());
  }

  /**
   * Test {@link StandardWrapper#setServletClass(String)}.
   * <ul>
   *   <li>When {@code Servlet Class}.</li>
   *   <li>Then {@link StandardWrapper} (default constructor) LoadOnStartupString is {@code -1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#setServletClass(String)}
   */
  @Test
  public void testSetServletClass_whenServletClass_thenStandardWrapperLoadOnStartupStringIs1() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setServletClass("Servlet Class");

    // Assert
    assertEquals("-1", standardWrapper.getLoadOnStartupString());
    assertEquals("Servlet Class", standardWrapper.getServletClass());
    assertEquals(-1, standardWrapper.getLoadOnStartup());
    assertFalse(standardWrapper.isJspServlet);
  }

  /**
   * Test {@link StandardWrapper#setServletName(String)}.
   * <p>
   * Method under test: {@link StandardWrapper#setServletName(String)}
   */
  @Test
  public void testSetServletName() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.setServletName("Name");

    // Assert
    assertEquals(",servlet=Name,container0=null", standardWrapper.getMBeanKeyProperties());
    assertEquals("Name", standardWrapper.getName());
    assertEquals("Name", standardWrapper.getServletName());
    assertEquals("Name", standardWrapper.facade.getServletName());
    assertEquals("org.apache.catalina.core.ContainerBase.[Name]", standardWrapper.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[Name]", standardWrapper.logName);
    assertEquals("type=Valve,servlet=Name,container0=null,name=StandardWrapperValve",
        standardWrapper.swValve.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardWrapper#isUnavailable()}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor) Enabled is {@code false}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#isUnavailable()}
   */
  @Test
  public void testIsUnavailable_givenStandardWrapperEnabledIsFalse_thenReturnTrue() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setEnabled(false);
    standardWrapper.setAvailable(0L);

    // Act and Assert
    assertTrue(standardWrapper.isUnavailable());
  }

  /**
   * Test {@link StandardWrapper#isUnavailable()}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#isUnavailable()}
   */
  @Test
  public void testIsUnavailable_givenStandardWrapper_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardWrapper()).isUnavailable());
  }

  /**
   * Test {@link StandardWrapper#getServletMethods()}.
   * <p>
   * Method under test: {@link StandardWrapper#getServletMethods()}
   */
  @Test
  public void testGetServletMethods() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new SecureDefault());

    // Act
    existingStandardWrapper.getServletMethods();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof SecureDefault);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof SecureDefault);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#getServletMethods()}.
   * <p>
   * Method under test: {@link StandardWrapper#getServletMethods()}
   */
  @Test
  public void testGetServletMethods2() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new JMXProxyServlet());

    // Act
    existingStandardWrapper.getServletMethods();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof JMXProxyServlet);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof JMXProxyServlet);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#getServletMethods()}.
   * <p>
   * Method under test: {@link StandardWrapper#getServletMethods()}
   */
  @Test
  public void testGetServletMethods3() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new StatusManagerServlet());

    // Act
    existingStandardWrapper.getServletMethods();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof StatusManagerServlet);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof StatusManagerServlet);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#getServletMethods()}.
   * <ul>
   *   <li>Then return fourth element is {@code GET}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#getServletMethods()}
   */
  @Test
  public void testGetServletMethods_thenReturnFourthElementIsGet() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setServlet(new HTMLManagerServlet());

    // Act
    String[] actualServletMethods = standardWrapper.getServletMethods();

    // Assert
    assertEquals("GET", actualServletMethods[3]);
    assertEquals("OPTIONS", actualServletMethods[4]);
    assertEquals("POST", actualServletMethods[2]);
    assertEquals("PUT", actualServletMethods[5]);
    assertEquals(6, actualServletMethods.length);
  }

  /**
   * Test {@link StandardWrapper#getRootCause(ServletException)}.
   * <ul>
   *   <li>Given {@link Throwable#Throwable()}.</li>
   *   <li>Then return LocalizedMessage is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#getRootCause(ServletException)}
   */
  @Test
  public void testGetRootCause_givenThrowable_thenReturnLocalizedMessageIsNull() {
    // Arrange
    ServletException e = new ServletException("An error occurred");
    e.initCause(new Throwable());

    // Act
    Throwable actualRootCause = StandardWrapper.getRootCause(e);

    // Assert
    assertNull(actualRootCause.getLocalizedMessage());
    assertNull(actualRootCause.getMessage());
    assertNull(actualRootCause.getCause());
    assertEquals(0, actualRootCause.getSuppressed().length);
  }

  /**
   * Test {@link StandardWrapper#getRootCause(ServletException)}.
   * <ul>
   *   <li>Then return {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#getRootCause(ServletException)}
   */
  @Test
  public void testGetRootCause_thenReturnServletException() {
    // Arrange and Act
    Throwable actualRootCause = StandardWrapper.getRootCause(new ServletException("An error occurred"));

    // Assert
    assertTrue(actualRootCause instanceof ServletException);
    assertEquals("An error occurred", actualRootCause.getLocalizedMessage());
    assertEquals("An error occurred", actualRootCause.getMessage());
    assertNull(((ServletException) actualRootCause).getRootCause());
  }

  /**
   * Test {@link StandardWrapper#addChild(Container)}.
   * <p>
   * Method under test: {@link StandardWrapper#addChild(Container)}
   */
  @Test
  public void testAddChild() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardWrapper.addChild(new StandardContext()));
  }

  /**
   * Test {@link StandardWrapper#addInitParameter(String, String)}.
   * <p>
   * Method under test: {@link StandardWrapper#addInitParameter(String, String)}
   */
  @Test
  public void testAddInitParameter() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardWrapper.addInitParameter("Name", "42");

    // Assert
    HashMap<String, String> stringStringMap = standardWrapper.parameters;
    assertEquals(1, stringStringMap.size());
    assertEquals("42", stringStringMap.get("Name"));
  }

  /**
   * Test {@link StandardWrapper#addInitParameter(String, String)}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) {@link StandardWrapper#parameters} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#addInitParameter(String, String)}
   */
  @Test
  public void testAddInitParameter_givenStandardWrapper_thenStandardWrapperParametersSizeIsOne() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.addInitParameter("Name", "42");

    // Assert
    HashMap<String, String> stringStringMap = standardWrapper.parameters;
    assertEquals(1, stringStringMap.size());
    assertEquals("42", stringStringMap.get("Name"));
  }

  /**
   * Test {@link StandardWrapper#addMapping(String)}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) {@link StandardWrapper#mappings} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#addMapping(String)}
   */
  @Test
  public void testAddMapping_thenStandardWrapperMappingsSizeIsOne() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setParent(new StandardContext());

    // Act
    standardWrapper.addMapping("Mapping");

    // Assert
    ArrayList<String> stringList = standardWrapper.mappings;
    assertEquals(1, stringList.size());
    assertEquals("Mapping", stringList.get(0));
  }

  /**
   * Test {@link StandardWrapper#addSecurityReference(String, String)}.
   * <p>
   * Method under test: {@link StandardWrapper#addSecurityReference(String, String)}
   */
  @Test
  public void testAddSecurityReference() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardWrapper.addSecurityReference("Name", "Link");

    // Assert
    HashMap<String, String> stringStringMap = standardWrapper.references;
    assertEquals(1, stringStringMap.size());
    assertEquals("Link", stringStringMap.get("Name"));
  }

  /**
   * Test {@link StandardWrapper#addSecurityReference(String, String)}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#addSecurityReference(String, String)}
   */
  @Test
  public void testAddSecurityReference_givenStandardWrapper() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.addSecurityReference("Name", "Link");

    // Assert
    HashMap<String, String> stringStringMap = standardWrapper.references;
    assertEquals(1, stringStringMap.size());
    assertEquals("Link", stringStringMap.get("Name"));
  }

  /**
   * Test {@link StandardWrapper#findInitParameter(String)}.
   * <p>
   * Method under test: {@link StandardWrapper#findInitParameter(String)}
   */
  @Test
  public void testFindInitParameter() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapper()).findInitParameter("Name"));
  }

  /**
   * Test {@link StandardWrapper#findInitParameters()}.
   * <p>
   * Method under test: {@link StandardWrapper#findInitParameters()}
   */
  @Test
  public void testFindInitParameters() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardWrapper()).findInitParameters().length);
  }

  /**
   * Test {@link StandardWrapper#findMappings()}.
   * <p>
   * Method under test: {@link StandardWrapper#findMappings()}
   */
  @Test
  public void testFindMappings() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardWrapper()).findMappings().length);
  }

  /**
   * Test {@link StandardWrapper#findSecurityReference(String)}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#findSecurityReference(String)}
   */
  @Test
  public void testFindSecurityReference_givenStandardWrapper_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapper()).findSecurityReference("Name"));
  }

  /**
   * Test {@link StandardWrapper#findSecurityReference(String)}.
   * <ul>
   *   <li>Then return {@code ,J2EEApplication=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#findSecurityReference(String)}
   */
  @Test
  public void testFindSecurityReference_thenReturnJ2EEApplication() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.addSecurityReference("Name", ",J2EEApplication=");
    standardWrapper.setParent(new StandardContext());

    // Act and Assert
    assertEquals(",J2EEApplication=", standardWrapper.findSecurityReference("Name"));
  }

  /**
   * Test {@link StandardWrapper#findSecurityReference(String)}.
   * <ul>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#findSecurityReference(String)}
   */
  @Test
  public void testFindSecurityReference_thenReturnName() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setParent(new StandardContext());

    // Act and Assert
    assertEquals("Name", standardWrapper.findSecurityReference("Name"));
  }

  /**
   * Test {@link StandardWrapper#findSecurityReferences()}.
   * <p>
   * Method under test: {@link StandardWrapper#findSecurityReferences()}
   */
  @Test
  public void testFindSecurityReferences() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardWrapper()).findSecurityReferences().length);
  }

  /**
   * Test {@link StandardWrapper#load()}.
   * <p>
   * Method under test: {@link StandardWrapper#load()}
   */
  @Test
  public void testLoad() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new SecureDefault());

    // Act
    existingStandardWrapper.load();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof SecureDefault);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof SecureDefault);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#load()}.
   * <p>
   * Method under test: {@link StandardWrapper#load()}
   */
  @Test
  public void testLoad2() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new JMXProxyServlet());

    // Act
    existingStandardWrapper.load();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof JMXProxyServlet);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof JMXProxyServlet);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#load()}.
   * <p>
   * Method under test: {@link StandardWrapper#load()}
   */
  @Test
  public void testLoad3() throws ServletException {
    // Arrange
    ExistingStandardWrapper existingStandardWrapper = new ExistingStandardWrapper(new StatusManagerServlet());

    // Act
    existingStandardWrapper.load();

    // Assert
    Servlet servlet = existingStandardWrapper.getServlet();
    assertTrue(servlet instanceof StatusManagerServlet);
    Servlet servlet2 = existingStandardWrapper.instance;
    assertTrue(servlet2 instanceof StatusManagerServlet);
    StandardWrapperFacade expectedServletConfig = existingStandardWrapper.facade;
    assertSame(expectedServletConfig, servlet2.getServletConfig());
    assertSame(existingStandardWrapper.instance, servlet);
  }

  /**
   * Test {@link StandardWrapper#load()}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) Servlet {@link JMXProxyServlet}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#load()}
   */
  @Test
  public void testLoad_thenStandardWrapperServletJMXProxyServlet() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setServlet(new JMXProxyServlet());

    // Act
    standardWrapper.load();

    // Assert
    Servlet servlet = standardWrapper.getServlet();
    assertTrue(servlet instanceof JMXProxyServlet);
    assertNull(((JMXProxyServlet) servlet).getServletContext());
    assertNull(((JMXProxyServlet) servlet).getServletName());
    assertTrue(standardWrapper.instanceInitialized);
    StandardWrapperFacade expectedServletConfig = standardWrapper.facade;
    assertSame(expectedServletConfig, servlet.getServletConfig());
  }

  /**
   * Test {@link StandardWrapper#load()}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) Servlet {@link SecureDefault}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#load()}
   */
  @Test
  public void testLoad_thenStandardWrapperServletSecureDefault() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setServlet(new SecureDefault());

    // Act
    standardWrapper.load();

    // Assert
    Servlet servlet = standardWrapper.getServlet();
    assertTrue(servlet instanceof SecureDefault);
    assertNull(((SecureDefault) servlet).getServletContext());
    assertNull(((SecureDefault) servlet).getServletName());
    assertTrue(standardWrapper.instanceInitialized);
    StandardWrapperFacade expectedServletConfig = standardWrapper.facade;
    assertSame(expectedServletConfig, servlet.getServletConfig());
  }

  /**
   * Test {@link StandardWrapper#loadServlet()}.
   * <ul>
   *   <li>Then return {@link HTMLManagerServlet} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#loadServlet()}
   */
  @Test
  public void testLoadServlet_thenReturnHTMLManagerServlet() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    HTMLManagerServlet servlet = new HTMLManagerServlet();
    standardWrapper.setServlet(servlet);

    // Act and Assert
    assertSame(servlet, standardWrapper.loadServlet());
  }

  /**
   * Test {@link StandardWrapper#loadServlet()}.
   * <ul>
   *   <li>Then return {@link JMXProxyServlet} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#loadServlet()}
   */
  @Test
  public void testLoadServlet_thenReturnJMXProxyServlet() throws ServletException {
    // Arrange
    JMXProxyServlet existing = new JMXProxyServlet();

    // Act and Assert
    assertSame(existing, (new ExistingStandardWrapper(existing)).loadServlet());
  }

  /**
   * Test {@link StandardWrapper#loadServlet()}.
   * <ul>
   *   <li>Then return {@link SecureDefault} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#loadServlet()}
   */
  @Test
  public void testLoadServlet_thenReturnSecureDefault() throws ServletException {
    // Arrange
    SecureDefault existing = new SecureDefault();

    // Act and Assert
    assertSame(existing, (new ExistingStandardWrapper(existing)).loadServlet());
  }

  /**
   * Test {@link StandardWrapper#loadServlet()}.
   * <ul>
   *   <li>Then return {@link StatusManagerServlet} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#loadServlet()}
   */
  @Test
  public void testLoadServlet_thenReturnStatusManagerServlet() throws ServletException {
    // Arrange
    StatusManagerServlet existing = new StatusManagerServlet();

    // Act and Assert
    assertSame(existing, (new ExistingStandardWrapper(existing)).loadServlet());
  }

  /**
   * Test {@link StandardWrapper#unload()}.
   * <p>
   * Method under test: {@link StandardWrapper#unload()}
   */
  @Test
  public void testUnload() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardWrapper.setServlet(new HTMLManagerServlet());

    // Act
    standardWrapper.unload();

    // Assert
    assertNull(standardWrapper.getServlet());
  }

  /**
   * Test {@link StandardWrapper#unload()}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) Servlet is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#unload()}
   */
  @Test
  public void testUnload_givenStandardWrapper_thenStandardWrapperServletIsNull() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.unload();

    // Assert that nothing has changed
    assertNull(standardWrapper.getServlet());
  }

  /**
   * Test {@link StandardWrapper#unload()}.
   * <ul>
   *   <li>Then {@link StandardWrapper} (default constructor) Servlet is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#unload()}
   */
  @Test
  public void testUnload_thenStandardWrapperServletIsNull() throws ServletException {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setServlet(new HTMLManagerServlet());

    // Act
    standardWrapper.unload();

    // Assert
    assertNull(standardWrapper.getServlet());
  }

  /**
   * Test {@link StandardWrapper#getInitParameter(String)}.
   * <p>
   * Method under test: {@link StandardWrapper#getInitParameter(String)}
   */
  @Test
  public void testGetInitParameter() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapper()).getInitParameter("Name"));
  }

  /**
   * Test {@link StandardWrapper#getServletContext()}.
   * <ul>
   *   <li>Given {@link StandardWrapper} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapper#getServletContext()}
   */
  @Test
  public void testGetServletContext_givenStandardWrapper_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapper()).getServletContext());
  }

  /**
   * Test {@link StandardWrapper#getServletName()}.
   * <p>
   * Method under test: {@link StandardWrapper#getServletName()}
   */
  @Test
  public void testGetServletName() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapper()).getServletName());
  }

  /**
   * Test {@link StandardWrapper#getProcessingTime()}.
   * <p>
   * Method under test: {@link StandardWrapper#getProcessingTime()}
   */
  @Test
  public void testGetProcessingTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapper()).getProcessingTime());
  }

  /**
   * Test {@link StandardWrapper#getMaxTime()}.
   * <p>
   * Method under test: {@link StandardWrapper#getMaxTime()}
   */
  @Test
  public void testGetMaxTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapper()).getMaxTime());
  }

  /**
   * Test {@link StandardWrapper#getMinTime()}.
   * <p>
   * Method under test: {@link StandardWrapper#getMinTime()}
   */
  @Test
  public void testGetMinTime() {
    // Arrange, Act and Assert
    assertEquals(Long.MAX_VALUE, (new StandardWrapper()).getMinTime());
  }

  /**
   * Test {@link StandardWrapper#getRequestCount()}.
   * <p>
   * Method under test: {@link StandardWrapper#getRequestCount()}
   */
  @Test
  public void testGetRequestCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapper()).getRequestCount());
  }

  /**
   * Test {@link StandardWrapper#getErrorCount()}.
   * <p>
   * Method under test: {@link StandardWrapper#getErrorCount()}
   */
  @Test
  public void testGetErrorCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapper()).getErrorCount());
  }

  /**
   * Test {@link StandardWrapper#incrementErrorCount()}.
   * <p>
   * Method under test: {@link StandardWrapper#incrementErrorCount()}
   */
  @Test
  public void testIncrementErrorCount() {
    // Arrange
    StandardWrapper standardWrapper = new StandardWrapper();

    // Act
    standardWrapper.incrementErrorCount();

    // Assert
    assertEquals(1L, standardWrapper.getErrorCount());
    assertEquals(1L, standardWrapper.swValve.getErrorCount());
  }

  /**
   * Test {@link StandardWrapper#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardWrapper#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setName("foo");

    StandardContext container2 = new StandardContext();
    container2.setParent(container);
    container2.setName("/");

    StandardWrapper standardWrapper = new StandardWrapper();
    standardWrapper.setName("j2eeType=Servlet");
    standardWrapper.setParent(container2);

    // Act and Assert
    assertEquals("j2eeType=Servlet,WebModule=//foo/,name=\"j2eeType=Servlet\",J2EEApplication=none,J2EEServer=none",
        standardWrapper.getObjectNameKeyProperties());
  }
}
