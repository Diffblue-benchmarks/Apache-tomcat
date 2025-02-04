package org.apache.catalina.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.core.StandardEngine.AccessLogListener;
import org.apache.catalina.mapper.MapperListener;
import org.apache.catalina.startup.FailedContext;
import org.junit.Test;

public class StandardHostDiffblueTest {
  /**
   * Test new {@link StandardHost} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardHost}
   */
  @Test
  public void testNewStandardHost() throws Exception {
    // Arrange and Act
    StandardHost actualStandardHost = new StandardHost();

    // Assert
    assertTrue(actualStandardHost.getPipeline() instanceof StandardPipeline);
    assertEquals(",host=null,container0=null", actualStandardHost.getMBeanKeyProperties());
    assertEquals("Catalina", actualStandardHost.getDomain());
    assertEquals("NEW", actualStandardHost.getStateName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardHost.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardHost.logName);
    assertEquals("org.apache.catalina.core.StandardContext", actualStandardHost.getContextClass());
    assertEquals("org.apache.catalina.startup.ContextConfig", actualStandardHost.getConfigClass());
    assertEquals("org.apache.catalina.valves.ErrorReportValve", actualStandardHost.getErrorReportValveClass());
    assertEquals("type=Host,host=null,container0=null", actualStandardHost.getObjectNameKeyProperties());
    assertEquals("webapps", actualStandardHost.getAppBase());
    assertEquals("webapps-javaee", actualStandardHost.getLegacyAppBase());
    assertNull(actualStandardHost.getCatalinaBase());
    assertNull(actualStandardHost.getCatalinaHome());
    assertNotNull(actualStandardHost.getParentClassLoader());
    assertNull(actualStandardHost.parentClassLoader);
    assertNull(actualStandardHost.getDomainInternal());
    assertNull(actualStandardHost.getDeployIgnore());
    assertNull(actualStandardHost.getName());
    assertNull(actualStandardHost.getWorkDir());
    assertNull(actualStandardHost.getXmlBase());
    assertNull(actualStandardHost.getStartStopExecutor());
    assertNull(actualStandardHost.backgroundProcessorFuture);
    assertNull(actualStandardHost.monitorFuture);
    assertNull(actualStandardHost.getDeployIgnorePattern());
    assertNull(actualStandardHost.getObjectName());
    assertNull(actualStandardHost.getAccessLog());
    assertNull(actualStandardHost.accessLog);
    assertNull(actualStandardHost.getCluster());
    assertNull(actualStandardHost.getClusterInternal());
    assertNull(actualStandardHost.cluster);
    assertNull(actualStandardHost.getParent());
    assertNull(actualStandardHost.getRealm());
    assertNull(actualStandardHost.getRealmInternal());
    assertEquals(-1, actualStandardHost.getBackgroundProcessorDelay());
    assertEquals(0, actualStandardHost.findContainerListeners().length);
    assertEquals(0, actualStandardHost.getChildren().length);
    String[] findAliasesResult = actualStandardHost.findAliases();
    assertEquals(0, findAliasesResult.length);
    assertEquals(0, actualStandardHost.findLifecycleListeners().length);
    assertEquals(1, actualStandardHost.getStartStopThreads());
    assertEquals(1, actualStandardHost.getValveNames().length);
    assertEquals(LifecycleState.NEW, actualStandardHost.getState());
    assertFalse(actualStandardHost.getUndeployOldVersions());
    assertFalse(actualStandardHost.isCopyXML());
    assertFalse(actualStandardHost.isFailCtxIfServletStartFails());
    assertTrue(actualStandardHost.children.isEmpty());
    assertTrue(actualStandardHost.listeners.isEmpty());
    assertTrue(actualStandardHost.getStartChildren());
    assertTrue(actualStandardHost.getAutoDeploy());
    assertTrue(actualStandardHost.getCreateDirs());
    assertTrue(actualStandardHost.getDeployOnStartup());
    assertTrue(actualStandardHost.isDeployXML());
    assertTrue(actualStandardHost.isUnpackWARs());
    assertTrue(actualStandardHost.getThrowOnFailure());
    assertSame(findAliasesResult, actualStandardHost.getAliases());
  }

  /**
   * Test {@link StandardHost#getAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getAppBaseFile()}
   */
  @Test
  public void testGetAppBaseFile_givenStandardHost() {
    // Arrange and Act
    File actualAppBaseFile = (new StandardHost()).getAppBaseFile();

    // Assert
    assertEquals("webapps", actualAppBaseFile.getName());
    assertTrue(actualAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getAppBaseFile()}
   */
  @Test
  public void testGetAppBaseFile_givenStandardHostParentIsFailedContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new FailedContext());

    // Act
    File actualAppBaseFile = standardHost.getAppBaseFile();

    // Assert
    assertEquals("webapps", actualAppBaseFile.getName());
    assertTrue(actualAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getAppBaseFile()}
   */
  @Test
  public void testGetAppBaseFile_givenStandardHostParentIsStandardContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardContext());

    // Act
    File actualAppBaseFile = standardHost.getAppBaseFile();

    // Assert
    assertEquals("webapps", actualAppBaseFile.getName());
    assertTrue(actualAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#setAppBase(String)}.
   * <ul>
   *   <li>When {@code App Base}.</li>
   *   <li>Then {@link StandardHost} (default constructor) AppBaseFile Name is {@code App Base}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setAppBase(String)}
   */
  @Test
  public void testSetAppBase_whenAppBase_thenStandardHostAppBaseFileNameIsAppBase() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setAppBase("App Base");

    // Assert
    assertEquals("App Base", standardHost.getAppBaseFile().getName());
    assertEquals("App Base", standardHost.getAppBase());
  }

  /**
   * Test {@link StandardHost#setAppBase(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link StandardHost} (default constructor) AppBase is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setAppBase(String)}
   */
  @Test
  public void testSetAppBase_whenEmptyString_thenStandardHostAppBaseIsEmptyString() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setAppBase("");

    // Assert
    assertEquals("", standardHost.getAppBase());
    assertEquals("Apache-tomcat", standardHost.getAppBaseFile().getName());
  }

  /**
   * Test {@link StandardHost#getLegacyAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getLegacyAppBaseFile()}
   */
  @Test
  public void testGetLegacyAppBaseFile_givenStandardHost() {
    // Arrange and Act
    File actualLegacyAppBaseFile = (new StandardHost()).getLegacyAppBaseFile();

    // Assert
    assertEquals("webapps-javaee", actualLegacyAppBaseFile.getName());
    assertTrue(actualLegacyAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getLegacyAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getLegacyAppBaseFile()}
   */
  @Test
  public void testGetLegacyAppBaseFile_givenStandardHostParentIsFailedContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new FailedContext());

    // Act
    File actualLegacyAppBaseFile = standardHost.getLegacyAppBaseFile();

    // Assert
    assertEquals("webapps-javaee", actualLegacyAppBaseFile.getName());
    assertTrue(actualLegacyAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getLegacyAppBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getLegacyAppBaseFile()}
   */
  @Test
  public void testGetLegacyAppBaseFile_givenStandardHostParentIsStandardContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardContext());

    // Act
    File actualLegacyAppBaseFile = standardHost.getLegacyAppBaseFile();

    // Assert
    assertEquals("webapps-javaee", actualLegacyAppBaseFile.getName());
    assertTrue(actualLegacyAppBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#setLegacyAppBase(String)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) LegacyAppBaseFile Name is {@code Legacy App Base}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setLegacyAppBase(String)}
   */
  @Test
  public void testSetLegacyAppBase_thenStandardHostLegacyAppBaseFileNameIsLegacyAppBase() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setLegacyAppBase("Legacy App Base");

    // Assert
    assertEquals("Legacy App Base", standardHost.getLegacyAppBaseFile().getName());
    assertEquals("Legacy App Base", standardHost.getLegacyAppBase());
  }

  /**
   * Test {@link StandardHost#setLegacyAppBase(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link StandardHost} (default constructor) LegacyAppBase is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setLegacyAppBase(String)}
   */
  @Test
  public void testSetLegacyAppBase_whenEmptyString_thenStandardHostLegacyAppBaseIsEmptyString() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setLegacyAppBase("");

    // Assert
    assertEquals("", standardHost.getLegacyAppBase());
    assertEquals("Apache-tomcat", standardHost.getLegacyAppBaseFile().getName());
  }

  /**
   * Test {@link StandardHost#setXmlBase(String)}.
   * <p>
   * Method under test: {@link StandardHost#setXmlBase(String)}
   */
  @Test
  public void testSetXmlBase() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setXmlBase("Xml Base");

    // Assert
    assertEquals("Xml Base", standardHost.getConfigBaseFile().getName());
    assertEquals("Xml Base", standardHost.getXmlBase());
  }

  /**
   * Test {@link StandardHost#getConfigBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getConfigBaseFile()}
   */
  @Test
  public void testGetConfigBaseFile_givenStandardHostParentIsFailedContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new FailedContext());

    // Act
    File actualConfigBaseFile = standardHost.getConfigBaseFile();

    // Assert
    assertEquals("null", actualConfigBaseFile.getName());
    assertTrue(actualConfigBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getConfigBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getConfigBaseFile()}
   */
  @Test
  public void testGetConfigBaseFile_givenStandardHostParentIsStandardContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardContext());

    // Act
    File actualConfigBaseFile = standardHost.getConfigBaseFile();

    // Assert
    assertEquals("null", actualConfigBaseFile.getName());
    assertTrue(actualConfigBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getConfigBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getConfigBaseFile()}
   */
  @Test
  public void testGetConfigBaseFile_givenStandardHostParentIsStandardEngine() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardEngine());

    // Act
    File actualConfigBaseFile = standardHost.getConfigBaseFile();

    // Assert
    assertEquals("null", actualConfigBaseFile.getName());
    assertTrue(actualConfigBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getConfigBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) XmlBase is {@code conf}.</li>
   *   <li>Then return Name is {@code conf}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getConfigBaseFile()}
   */
  @Test
  public void testGetConfigBaseFile_givenStandardHostXmlBaseIsConf_thenReturnNameIsConf() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setXmlBase("conf");

    // Act
    File actualConfigBaseFile = standardHost.getConfigBaseFile();

    // Assert
    assertEquals("conf", actualConfigBaseFile.getName());
    assertTrue(actualConfigBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#getConfigBaseFile()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>Then return Name is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getConfigBaseFile()}
   */
  @Test
  public void testGetConfigBaseFile_givenStandardHost_thenReturnNameIsNull() {
    // Arrange and Act
    File actualConfigBaseFile = (new StandardHost()).getConfigBaseFile();

    // Assert
    assertEquals("null", actualConfigBaseFile.getName());
    assertTrue(actualConfigBaseFile.isAbsolute());
  }

  /**
   * Test {@link StandardHost#setConfigClass(String)}.
   * <p>
   * Method under test: {@link StandardHost#setConfigClass(String)}
   */
  @Test
  public void testSetConfigClass() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setConfigClass("Config Class");

    // Assert
    assertEquals("Config Class", standardHost.getConfigClass());
  }

  /**
   * Test {@link StandardHost#setContextClass(String)}.
   * <p>
   * Method under test: {@link StandardHost#setContextClass(String)}
   */
  @Test
  public void testSetContextClass() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setContextClass("Context Class");

    // Assert
    assertEquals("Context Class", standardHost.getContextClass());
  }

  /**
   * Test {@link StandardHost#setErrorReportValveClass(String)}.
   * <p>
   * Method under test: {@link StandardHost#setErrorReportValveClass(String)}
   */
  @Test
  public void testSetErrorReportValveClass() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setErrorReportValveClass("An error occurred");

    // Assert
    assertEquals("An error occurred", standardHost.getErrorReportValveClass());
  }

  /**
   * Test {@link StandardHost#setName(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then {@link StandardHost} (default constructor) Pipeline Basic {@link StandardHostValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setName(String)}
   */
  @Test
  public void testSetName_whenName_thenStandardHostPipelineBasicStandardHostValve() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setName("Name");

    // Assert
    Pipeline pipeline = standardHost.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardHostValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(",host=name,container0=null", standardHost.getMBeanKeyProperties());
    assertEquals("name", standardHost.getConfigBaseFile().getName());
    assertEquals("name", standardHost.getName());
    assertEquals("org.apache.catalina.core.ContainerBase.[name]", standardHost.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[name]", standardHost.logName);
    assertEquals("type=Host,host=name,container0=null", standardHost.getObjectNameKeyProperties());
    assertEquals("type=Valve,host=name,container0=null,name=StandardHostValve",
        ((StandardHostValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#setName(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setName(String)}
   */
  @Test
  public void testSetName_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardHost()).setName(null));
  }

  /**
   * Test {@link StandardHost#getDeployIgnore()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) DeployIgnore is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getDeployIgnore()}
   */
  @Test
  public void testGetDeployIgnore_givenStandardHostDeployIgnoreIsFoo_thenReturnFoo() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setDeployIgnore("foo");

    // Act and Assert
    assertEquals("foo", standardHost.getDeployIgnore());
  }

  /**
   * Test {@link StandardHost#getDeployIgnore()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getDeployIgnore()}
   */
  @Test
  public void testGetDeployIgnore_givenStandardHost_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardHost()).getDeployIgnore());
  }

  /**
   * Test {@link StandardHost#setDeployIgnore(String)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) DeployIgnore is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setDeployIgnore(String)}
   */
  @Test
  public void testSetDeployIgnore_givenStandardHostDeployIgnoreIsFoo() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setDeployIgnore("foo");

    // Act
    standardHost.setDeployIgnore(null);

    // Assert
    assertNull(standardHost.getDeployIgnore());
    assertNull(standardHost.getDeployIgnorePattern());
  }

  /**
   * Test {@link StandardHost#setDeployIgnore(String)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) DeployIgnore is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setDeployIgnore(String)}
   */
  @Test
  public void testSetDeployIgnore_givenStandardHostDeployIgnoreIsNull() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setDeployIgnore(null);

    // Act
    standardHost.setDeployIgnore(null);

    // Assert that nothing has changed
    assertNull(standardHost.getDeployIgnore());
    assertNull(standardHost.getDeployIgnorePattern());
  }

  /**
   * Test {@link StandardHost#setDeployIgnore(String)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) DeployIgnorePattern pattern is {@code Deploy Ignore}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#setDeployIgnore(String)}
   */
  @Test
  public void testSetDeployIgnore_thenStandardHostDeployIgnorePatternPatternIsDeployIgnore() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setDeployIgnore("Deploy Ignore");

    // Assert
    assertEquals("Deploy Ignore", standardHost.getDeployIgnorePattern().pattern());
    assertEquals("Deploy Ignore", standardHost.getDeployIgnore());
  }

  /**
   * Test {@link StandardHost#setFailCtxIfServletStartFails(boolean)}.
   * <p>
   * Method under test: {@link StandardHost#setFailCtxIfServletStartFails(boolean)}
   */
  @Test
  public void testSetFailCtxIfServletStartFails() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setFailCtxIfServletStartFails(true);

    // Assert
    assertTrue(standardHost.isFailCtxIfServletStartFails());
  }

  /**
   * Test {@link StandardHost#addAlias(String)}.
   * <p>
   * Method under test: {@link StandardHost#addAlias(String)}
   */
  @Test
  public void testAddAlias() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardHost.addAlias("addAlias");

    // Act
    standardHost.addAlias("Alias");

    // Assert
    assertArrayEquals(new String[]{"addalias", "alias"}, standardHost.findAliases());
  }

  /**
   * Test {@link StandardHost#addAlias(String)}.
   * <p>
   * Method under test: {@link StandardHost#addAlias(String)}
   */
  @Test
  public void testAddAlias2() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addContainerListener(new MapperListener(new StandardService()));
    standardHost.addAlias("addAlias");

    // Act
    standardHost.addAlias("Alias");

    // Assert
    assertArrayEquals(new String[]{"addalias", "alias"}, standardHost.findAliases());
  }

  /**
   * Test {@link StandardHost#addAlias(String)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) findAliases is array of {@link String} with {@code addalias} and {@code alias}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addAlias(String)}
   */
  @Test
  public void testAddAlias_thenStandardHostFindAliasesIsArrayOfStringWithAddaliasAndAlias() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addAlias("addAlias");

    // Act
    standardHost.addAlias("Alias");

    // Assert
    assertArrayEquals(new String[]{"addalias", "alias"}, standardHost.findAliases());
  }

  /**
   * Test {@link StandardHost#addAlias(String)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) findAliases is array of {@link String} with {@code alias}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addAlias(String)}
   */
  @Test
  public void testAddAlias_thenStandardHostFindAliasesIsArrayOfStringWithAlias() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.addAlias("Alias");

    // Assert
    assertArrayEquals(new String[]{"alias"}, standardHost.findAliases());
  }

  /**
   * Test {@link StandardHost#addAlias(String)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) findAliases is array of {@link String} with {@code alias} and {@code addalias}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addAlias(String)}
   */
  @Test
  public void testAddAlias_thenStandardHostFindAliasesIsArrayOfStringWithAliasAndAddalias() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addAlias("alias");
    standardHost.addAlias("addAlias");

    // Act
    standardHost.addAlias("Alias");

    // Assert that nothing has changed
    assertArrayEquals(new String[]{"alias", "addalias"}, standardHost.findAliases());
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Given {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_givenNumberSignNumberSign() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    StandardContext child = new StandardContext();
    child.setDocBase("##");

    // Act
    standardHost.addChild(child);

    // Assert
    assertEquals("", child.getName());
    assertEquals("", child.getEncodedPath());
    assertEquals("", child.getPath());
    assertEquals(",host=null,context=/,container0=null", child.getMBeanKeyProperties());
    assertEquals("Catalina", child.getDomainInternal());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        child.getObjectNameKeyProperties());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.logName);
    HashMap<String, Container> stringContainerMap = standardHost.children;
    assertEquals(1, stringContainerMap.size());
    assertEquals(1, standardHost.getChildren().length);
    assertEquals(1, child.findLifecycleListeners().length);
    assertTrue(stringContainerMap.containsKey(""));
    assertSame(standardHost, child.getParent());
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Given {@code /}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_givenSlash_thenStandardContextNameIsEmptyString() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    StandardContext child = new StandardContext();
    child.setDocBase("/");

    // Act
    standardHost.addChild(child);

    // Assert
    assertEquals("", child.getName());
    assertEquals("", child.getEncodedPath());
    assertEquals("", child.getPath());
    assertEquals(",host=null,context=/,container0=null", child.getMBeanKeyProperties());
    assertEquals("Catalina", child.getDomainInternal());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        child.getObjectNameKeyProperties());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.logName);
    HashMap<String, Container> stringContainerMap = standardHost.children;
    assertEquals(1, stringContainerMap.size());
    assertEquals(1, standardHost.getChildren().length);
    assertEquals(1, child.findLifecycleListeners().length);
    assertTrue(stringContainerMap.containsKey(""));
    assertSame(standardHost, child.getParent());
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_givenStandardHost_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardHost()).addChild(null));
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Given {@code .war}.</li>
   *   <li>When {@link StandardContext} (default constructor) DocBase is {@code .war}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_givenWar_whenStandardContextDocBaseIsWar() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    StandardContext child = new StandardContext();
    child.setDocBase(".war");

    // Act
    standardHost.addChild(child);

    // Assert
    assertEquals("", child.getName());
    assertEquals("", child.getEncodedPath());
    assertEquals("", child.getPath());
    assertEquals(",host=null,context=/,container0=null", child.getMBeanKeyProperties());
    assertEquals("Catalina", child.getDomainInternal());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        child.getObjectNameKeyProperties());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.logName);
    HashMap<String, Container> stringContainerMap = standardHost.children;
    assertEquals(1, stringContainerMap.size());
    assertEquals(1, standardHost.getChildren().length);
    assertEquals(1, child.findLifecycleListeners().length);
    assertTrue(stringContainerMap.containsKey(""));
    assertSame(standardHost, child.getParent());
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Given {@code .xml}.</li>
   *   <li>When {@link StandardContext} (default constructor) DocBase is {@code .xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_givenXml_whenStandardContextDocBaseIsXml() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    StandardContext child = new StandardContext();
    child.setDocBase(".xml");

    // Act
    standardHost.addChild(child);

    // Assert
    assertEquals("", child.getName());
    assertEquals("", child.getEncodedPath());
    assertEquals("", child.getPath());
    assertEquals(",host=null,context=/,container0=null", child.getMBeanKeyProperties());
    assertEquals("Catalina", child.getDomainInternal());
    assertEquals("j2eeType=WebModule,name=//DEFAULT/,J2EEApplication=none,J2EEServer=none",
        child.getObjectNameKeyProperties());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", child.logName);
    HashMap<String, Container> stringContainerMap = standardHost.children;
    assertEquals(1, stringContainerMap.size());
    assertEquals(1, standardHost.getChildren().length);
    assertEquals(1, child.findLifecycleListeners().length);
    assertTrue(stringContainerMap.containsKey(""));
    assertSame(standardHost, child.getParent());
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Then first element {@link AccessLogListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_thenFirstElementAccessLogListener() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    StandardEngine engine = new StandardEngine();
    StandardHost host = new StandardHost();
    AccessLogListener listener = new AccessLogListener(engine, host, new StandardContext());

    standardHost.addContainerListener(listener);

    StandardContext child = new StandardContext();
    child.setDocBase("/");

    // Act
    standardHost.addChild(child);

    // Assert
    Container parent = child.getParent();
    ContainerListener[] findContainerListenersResult = parent.findContainerListeners();
    ContainerListener containerListener = findContainerListenersResult[0];
    assertTrue(containerListener instanceof AccessLogListener);
    assertTrue(parent instanceof StandardHost);
    List<ContainerListener> containerListenerList = ((StandardHost) parent).listeners;
    assertEquals(1, containerListenerList.size());
    assertEquals(1, findContainerListenersResult.length);
    assertTrue(child.children.isEmpty());
    assertSame(listener, containerListenerList.get(0));
    assertSame(listener, containerListener);
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Then first element {@link MapperListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_thenFirstElementMapperListener() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    MapperListener listener = new MapperListener(new StandardService());
    standardHost.addContainerListener(listener);

    StandardContext child = new StandardContext();
    child.setDocBase("/");

    // Act
    standardHost.addChild(child);

    // Assert
    ContainerListener[] findContainerListenersResult = child.findContainerListeners();
    ContainerListener containerListener = findContainerListenersResult[0];
    assertTrue(containerListener instanceof MapperListener);
    List<ContainerListener> containerListenerList = standardHost.listeners;
    assertEquals(1, containerListenerList.size());
    assertEquals(1, findContainerListenersResult.length);
    ContainerListener[] findContainerListenersResult2 = standardHost.findContainerListeners();
    assertEquals(1, findContainerListenersResult2.length);
    LifecycleListener[] findLifecycleListenersResult = child.findLifecycleListeners();
    assertEquals(2, findLifecycleListenersResult.length);
    assertEquals(standardHost.listeners, child.listeners);
    assertSame(listener, containerListenerList.get(0));
    assertSame(listener, containerListener);
    assertSame(listener, findContainerListenersResult2[0]);
    assertSame(listener, findLifecycleListenersResult[1]);
  }

  /**
   * Test {@link StandardHost#addChild(Container)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) {@link ContainerBase#listeners} first is {@link ThreadLocalLeakPreventionListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#addChild(Container)}
   */
  @Test
  public void testAddChild_thenStandardHostListenersFirstIsThreadLocalLeakPreventionListener() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    ThreadLocalLeakPreventionListener listener = new ThreadLocalLeakPreventionListener();
    standardHost.addContainerListener(listener);

    StandardContext child = new StandardContext();
    child.setDocBase("/");

    // Act
    standardHost.addChild(child);

    // Assert
    Container parent = child.getParent();
    assertTrue(parent instanceof StandardHost);
    List<ContainerListener> containerListenerList = standardHost.listeners;
    assertEquals(1, containerListenerList.size());
    List<ContainerListener> containerListenerList2 = ((StandardHost) parent).listeners;
    assertEquals(1, containerListenerList2.size());
    ContainerListener[] findContainerListenersResult = parent.findContainerListeners();
    assertEquals(1, findContainerListenersResult.length);
    ContainerListener[] findContainerListenersResult2 = standardHost.findContainerListeners();
    assertEquals(1, findContainerListenersResult2.length);
    LifecycleListener[] findLifecycleListenersResult = child.findLifecycleListeners();
    assertEquals(2, findLifecycleListenersResult.length);
    assertSame(listener, containerListenerList.get(0));
    assertSame(listener, containerListenerList2.get(0));
    assertSame(listener, findContainerListenersResult[0]);
    assertSame(listener, findContainerListenersResult2[0]);
    assertSame(listener, findLifecycleListenersResult[1]);
  }

  /**
   * Test {@link StandardHost#findReloadedContextMemoryLeaks()}.
   * <p>
   * Method under test: {@link StandardHost#findReloadedContextMemoryLeaks()}
   */
  @Test
  public void testFindReloadedContextMemoryLeaks() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardHost()).findReloadedContextMemoryLeaks().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardHost#setCopyXML(boolean)}
   *   <li>{@link StandardHost#setCreateDirs(boolean)}
   *   <li>{@link StandardHost#setDeployXML(boolean)}
   *   <li>{@link StandardHost#setUndeployOldVersions(boolean)}
   *   <li>{@link StandardHost#setUnpackWARs(boolean)}
   *   <li>{@link StandardHost#setWorkDir(String)}
   *   <li>{@link StandardHost#findAliases()}
   *   <li>{@link StandardHost#getAliases()}
   *   <li>{@link StandardHost#getAppBase()}
   *   <li>{@link StandardHost#getAutoDeploy()}
   *   <li>{@link StandardHost#getConfigClass()}
   *   <li>{@link StandardHost#getContextClass()}
   *   <li>{@link StandardHost#getCreateDirs()}
   *   <li>{@link StandardHost#getDeployIgnorePattern()}
   *   <li>{@link StandardHost#getDeployOnStartup()}
   *   <li>{@link StandardHost#getErrorReportValveClass()}
   *   <li>{@link StandardHost#getLegacyAppBase()}
   *   <li>{@link StandardHost#getName()}
   *   <li>{@link StandardHost#getStartStopExecutor()}
   *   <li>{@link StandardHost#getUndeployOldVersions()}
   *   <li>{@link StandardHost#getWorkDir()}
   *   <li>{@link StandardHost#getXmlBase()}
   *   <li>{@link StandardHost#isCopyXML()}
   *   <li>{@link StandardHost#isDeployXML()}
   *   <li>{@link StandardHost#isFailCtxIfServletStartFails()}
   *   <li>{@link StandardHost#isUnpackWARs()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.setCopyXML(true);
    standardHost.setCreateDirs(true);
    standardHost.setDeployXML(true);
    standardHost.setUndeployOldVersions(true);
    standardHost.setUnpackWARs(true);
    standardHost.setWorkDir("Work Dir");
    String[] actualFindAliasesResult = standardHost.findAliases();
    String[] actualAliases = standardHost.getAliases();
    String actualAppBase = standardHost.getAppBase();
    boolean actualAutoDeploy = standardHost.getAutoDeploy();
    String actualConfigClass = standardHost.getConfigClass();
    String actualContextClass = standardHost.getContextClass();
    boolean actualCreateDirs = standardHost.getCreateDirs();
    Pattern actualDeployIgnorePattern = standardHost.getDeployIgnorePattern();
    boolean actualDeployOnStartup = standardHost.getDeployOnStartup();
    String actualErrorReportValveClass = standardHost.getErrorReportValveClass();
    String actualLegacyAppBase = standardHost.getLegacyAppBase();
    String actualName = standardHost.getName();
    ExecutorService actualStartStopExecutor = standardHost.getStartStopExecutor();
    boolean actualUndeployOldVersions = standardHost.getUndeployOldVersions();
    String actualWorkDir = standardHost.getWorkDir();
    String actualXmlBase = standardHost.getXmlBase();
    boolean actualIsCopyXMLResult = standardHost.isCopyXML();
    boolean actualIsDeployXMLResult = standardHost.isDeployXML();
    boolean actualIsFailCtxIfServletStartFailsResult = standardHost.isFailCtxIfServletStartFails();

    // Assert
    assertEquals("Work Dir", actualWorkDir);
    assertEquals("org.apache.catalina.core.StandardContext", actualContextClass);
    assertEquals("org.apache.catalina.startup.ContextConfig", actualConfigClass);
    assertEquals("org.apache.catalina.valves.ErrorReportValve", actualErrorReportValveClass);
    assertEquals("webapps", actualAppBase);
    assertEquals("webapps-javaee", actualLegacyAppBase);
    assertNull(actualName);
    assertNull(actualXmlBase);
    assertNull(actualStartStopExecutor);
    assertNull(actualDeployIgnorePattern);
    assertEquals(0, actualFindAliasesResult.length);
    assertFalse(actualIsFailCtxIfServletStartFailsResult);
    assertTrue(actualAutoDeploy);
    assertTrue(actualCreateDirs);
    assertTrue(actualDeployOnStartup);
    assertTrue(actualUndeployOldVersions);
    assertTrue(actualIsCopyXMLResult);
    assertTrue(actualIsDeployXMLResult);
    assertTrue(standardHost.isUnpackWARs());
    assertSame(actualFindAliasesResult, actualAliases);
  }

  /**
   * Test {@link StandardHost#removeAlias(String)}.
   * <p>
   * Method under test: {@link StandardHost#removeAlias(String)}
   */
  @Test
  public void testRemoveAlias() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addContainerListener(new ThreadLocalLeakPreventionListener());
    standardHost.addAlias("Alias");

    // Act
    standardHost.removeAlias("Alias");

    // Assert
    assertEquals(0, standardHost.findAliases().length);
  }

  /**
   * Test {@link StandardHost#removeAlias(String)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) addAlias {@code Alias}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#removeAlias(String)}
   */
  @Test
  public void testRemoveAlias_givenStandardHostAddAliasAlias_thenArrayLengthIsZero() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addAlias("Alias");

    // Act
    standardHost.removeAlias("Alias");

    // Assert
    assertEquals(0, standardHost.findAliases().length);
  }

  /**
   * Test {@link StandardHost#removeAlias(String)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) addAlias {@code removeAlias}.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#removeAlias(String)}
   */
  @Test
  public void testRemoveAlias_givenStandardHostAddAliasRemoveAlias_thenArrayLengthIsOne() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addAlias("removeAlias");
    standardHost.addAlias("Alias");

    // Act
    standardHost.removeAlias("Alias");

    // Assert
    assertEquals(1, standardHost.findAliases().length);
  }

  /**
   * Test {@link StandardHost#removeAlias(String)}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#removeAlias(String)}
   */
  @Test
  public void testRemoveAlias_givenStandardHost_thenArrayLengthIsZero() {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.removeAlias("Alias");

    // Assert that nothing has changed
    assertEquals(0, standardHost.findAliases().length);
  }

  /**
   * Test {@link StandardHost#getValveNames()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>Then return array of {@link String} with {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getValveNames()}
   */
  @Test
  public void testGetValveNames_givenStandardHost_thenReturnArrayOfStringWithNull() throws Exception {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{null}, (new StandardHost()).getValveNames());
  }

  /**
   * Test {@link StandardHost#getValveNames()}.
   * <ul>
   *   <li>Then return array of {@link String} with {@code null} and {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getValveNames()}
   */
  @Test
  public void testGetValveNames_thenReturnArrayOfStringWithNullAndNull() throws Exception {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addValve(new StandardContextValve());

    // Act and Assert
    assertArrayEquals(new String[]{null, null}, standardHost.getValveNames());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardWrapper());

    // Act and Assert
    assertEquals("type=Host,servlet=null,host=null,container0=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor) Name is {@code type=Host}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenFailedContextNameIsTypeHost() {
    // Arrange
    FailedContext container = new FailedContext();
    container.setName("type=Host");

    StandardHost standardHost = new StandardHost();
    standardHost.setParent(container);

    // Act and Assert
    assertEquals("type=Host,context=/type=Host,host=null,container0=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenStandardContextNameIsNumberSignNumberSign() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setName("##");

    StandardHost standardHost = new StandardHost();
    standardHost.setParent(container);

    // Act and Assert
    assertEquals("type=Host,context=/,host=null,container0=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenStandardContextNameIsSlash() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setName("/");

    StandardHost standardHost = new StandardHost();
    standardHost.setParent(container);

    // Act and Assert
    assertEquals("type=Host,context=/,host=null,container0=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code type=Host}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenStandardContextNameIsTypeHost() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setName("type=Host");

    StandardHost standardHost = new StandardHost();
    standardHost.setParent(container);

    // Act and Assert
    assertEquals("type=Host,context=/type=Host,host=null,container0=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Host,host=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeHostHostNull() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardEngine());

    // Act and Assert
    assertEquals("type=Host,host=null", standardHost.getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardHost#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Host,host=null,container0=null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardHost#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeHostHostNullContainer0Null() {
    // Arrange, Act and Assert
    assertEquals("type=Host,host=null,container0=null", (new StandardHost()).getObjectNameKeyProperties());
  }
}
