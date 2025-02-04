package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import org.apache.catalina.Authenticator;
import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.Valve;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardEngine.AccessLogListener;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.catalina.realm.JAASRealm;
import org.apache.catalina.realm.NullRealm;
import org.apache.catalina.startup.FailedContext;
import org.apache.catalina.webresources.ExtractingRoot;
import org.junit.Test;

public class ContainerBaseDiffblueTest {
  /**
   * Test {@link ContainerBase#getStartStopThreads()}.
   * <p>
   * Method under test: {@link ContainerBase#getStartStopThreads()}
   */
  @Test
  public void testGetStartStopThreads() {
    // Arrange, Act and Assert
    assertEquals(1, (new StandardContext()).getStartStopThreads());
  }

  /**
   * Test {@link ContainerBase#getBackgroundProcessorDelay()}.
   * <p>
   * Method under test: {@link ContainerBase#getBackgroundProcessorDelay()}
   */
  @Test
  public void testGetBackgroundProcessorDelay() {
    // Arrange, Act and Assert
    assertEquals(-1, (new StandardContext()).getBackgroundProcessorDelay());
  }

  /**
   * Test {@link ContainerBase#setBackgroundProcessorDelay(int)}.
   * <p>
   * Method under test: {@link ContainerBase#setBackgroundProcessorDelay(int)}
   */
  @Test
  public void testSetBackgroundProcessorDelay() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setBackgroundProcessorDelay(1);

    // Assert
    assertEquals(1, standardContext.getBackgroundProcessorDelay());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContextNameIsEmptyString() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("");

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContextNameIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContextParentIsFailedContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new FailedContext());

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContextParentIsStandardHost() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardHost());

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/].[/]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code ContainerBase.[/]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_givenStandardContext_thenReturnOrgApacheCatalinaCoreContainerBase() {
    // Arrange, Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", (new StandardContext()).getLogName());
  }

  /**
   * Test {@link ContainerBase#getLogName()}.
   * <ul>
   *   <li>Then return {@code ContainerBase.[/##]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getLogName()}
   */
  @Test
  public void testGetLogName_thenReturnOrgApacheCatalinaCoreContainerBase() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("##");

    // Act and Assert
    assertEquals("org.apache.catalina.core.ContainerBase.[/##]", standardContext.getLogName());
  }

  /**
   * Test {@link ContainerBase#getCluster()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Cluster is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCluster()}
   */
  @Test
  public void testGetCluster_givenStandardContextClusterIsNull_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setCluster(null);
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNull(standardContext.getCluster());
  }

  /**
   * Test {@link ContainerBase#getCluster()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCluster()}
   */
  @Test
  public void testGetCluster_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getCluster());
  }

  /**
   * Test {@link ContainerBase#getCluster()}.
   * <ul>
   *   <li>Then return {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCluster()}
   */
  @Test
  public void testGetCluster_thenReturnSimpleTcpCluster() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    standardContext.setCluster(cluster);
    standardContext.setParent(null);

    // Act and Assert
    assertSame(cluster, standardContext.getCluster());
  }

  /**
   * Test {@link ContainerBase#getClusterInternal()}.
   * <p>
   * Method under test: {@link ContainerBase#getClusterInternal()}
   */
  @Test
  public void testGetClusterInternal() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getClusterInternal());
  }

  /**
   * Test {@link ContainerBase#setCluster(Cluster)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Cluster is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setCluster(Cluster)}
   */
  @Test
  public void testSetCluster_whenNull_thenStandardContextClusterIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setCluster(null);

    // Assert that nothing has changed
    assertNull(standardContext.getCluster());
    assertNull(standardContext.getClusterInternal());
    assertNull(standardContext.cluster);
  }

  /**
   * Test {@link ContainerBase#setCluster(Cluster)}.
   * <ul>
   *   <li>When {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then {@link SimpleTcpCluster} (default constructor) Container {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setCluster(Cluster)}
   */
  @Test
  public void testSetCluster_whenSimpleTcpCluster_thenSimpleTcpClusterContainerStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    SimpleTcpCluster cluster = new SimpleTcpCluster();

    // Act
    standardContext.setCluster(cluster);

    // Assert
    Container container = cluster.getContainer();
    assertTrue(container instanceof StandardContext);
    assertTrue(standardContext.cluster instanceof SimpleTcpCluster);
    assertSame(standardContext, container);
    assertSame(cluster, standardContext.getCluster());
    assertSame(cluster, standardContext.getClusterInternal());
  }

  /**
   * Test {@link ContainerBase#getName()}.
   * <p>
   * Method under test: {@link ContainerBase#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getName());
  }

  /**
   * Test {@link ContainerBase#setName(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Pipeline Basic {@link StandardContextValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setName(String)}
   */
  @Test
  public void testSetName_whenName_thenStandardContextPipelineBasicStandardContextValve() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setName("Name");

    // Assert
    Pipeline pipeline = standardContext.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(",context=/Name,container0=null", standardContext.getMBeanKeyProperties());
    assertEquals("Name", standardContext.getName());
    assertEquals("org.apache.catalina.core.ContainerBase.[Name]", standardContext.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[Name]", standardContext.logName);
    assertEquals("type=Valve,context=/Name,container0=null,name=StandardContextValve",
        ((StandardContextValve) basic).getObjectNameKeyProperties());
  }

  /**
   * Test {@link ContainerBase#setName(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setName(String)}
   */
  @Test
  public void testSetName_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new StandardContext()).setName(null));
  }

  /**
   * Test {@link ContainerBase#getStartChildren()}.
   * <p>
   * Method under test: {@link ContainerBase#getStartChildren()}
   */
  @Test
  public void testGetStartChildren() {
    // Arrange, Act and Assert
    assertTrue((new StandardContext()).getStartChildren());
  }

  /**
   * Test {@link ContainerBase#getParent()}.
   * <p>
   * Method under test: {@link ContainerBase#getParent()}
   */
  @Test
  public void testGetParent() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getParent());
  }

  /**
   * Test {@link ContainerBase#getParentClassLoader()}.
   * <p>
   * Method under test: {@link ContainerBase#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParentClassLoader(new ParallelWebappClassLoader());

    // Act and Assert
    assertNotNull(standardHost.getParentClassLoader());
  }

  /**
   * Test {@link ContainerBase#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardHost() {
    // Arrange, Act and Assert
    assertNotNull((new StandardHost()).getParentClassLoader());
  }

  /**
   * Test {@link ContainerBase#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardHostParentIsStandardContext() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardContext());

    // Act and Assert
    assertNotNull(standardHost.getParentClassLoader());
  }

  /**
   * Test {@link ContainerBase#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor) Parent is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardHostParentIsStandardHost() {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setParent(new StandardHost());

    // Act and Assert
    assertNotNull(standardHost.getParentClassLoader());
  }

  /**
   * Test {@link ContainerBase#setParentClassLoader(ClassLoader)}.
   * <p>
   * Method under test: {@link ContainerBase#setParentClassLoader(ClassLoader)}
   */
  @Test
  public void testSetParentClassLoader() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    ParallelWebappClassLoader parent = new ParallelWebappClassLoader();

    // Act
    standardContext.setParentClassLoader(parent);

    // Assert
    assertNotNull(standardContext.parentClassLoader);
    assertSame(parent, standardContext.getParentClassLoader());
  }

  /**
   * Test {@link ContainerBase#getPipeline()}.
   * <p>
   * Method under test: {@link ContainerBase#getPipeline()}
   */
  @Test
  public void testGetPipeline() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act and Assert
    assertSame(standardContext.pipeline, standardContext.getPipeline());
  }

  /**
   * Test {@link ContainerBase#getRealm()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Realm is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getRealm()}
   */
  @Test
  public void testGetRealm_givenStandardContextRealmIsNull_thenReturnNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setRealm(null);
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNull(standardContext.getRealm());
  }

  /**
   * Test {@link ContainerBase#getRealm()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getRealm()}
   */
  @Test
  public void testGetRealm_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getRealm());
  }

  /**
   * Test {@link ContainerBase#getRealm()}.
   * <ul>
   *   <li>Then return {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getRealm()}
   */
  @Test
  public void testGetRealm_thenReturnAuthenticatedUserRealm() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    AuthenticatedUserRealm realm = new AuthenticatedUserRealm();
    standardContext.setRealm(realm);
    standardContext.setParent(null);

    // Act and Assert
    assertSame(realm, standardContext.getRealm());
  }

  /**
   * Test {@link ContainerBase#getRealmInternal()}.
   * <p>
   * Method under test: {@link ContainerBase#getRealmInternal()}
   */
  @Test
  public void testGetRealmInternal() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getRealmInternal());
  }

  /**
   * Test {@link ContainerBase#setRealm(Realm)}.
   * <ul>
   *   <li>Then {@link AuthenticatedUserRealm} (default constructor) Container {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setRealm(Realm)}
   */
  @Test
  public void testSetRealm_thenAuthenticatedUserRealmContainerStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    AuthenticatedUserRealm realm = new AuthenticatedUserRealm();

    // Act
    standardContext.setRealm(realm);

    // Assert
    Container container = realm.getContainer();
    assertTrue(container instanceof StandardContext);
    Pipeline pipeline = container.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(1, pipeline.getValves().length);
    assertSame(realm, container.getRealm());
    assertSame(realm, standardContext.getRealm());
    assertSame(realm, standardContext.getRealmInternal());
    assertSame(realm, ((StandardContext) container).getRealmInternal());
    assertSame(container, pipeline.getContainer());
    assertSame(container, ((StandardContext) container).getNamingResources().getContainer());
    assertSame(basic, pipeline.getFirst());
  }

  /**
   * Test {@link ContainerBase#setRealm(Realm)}.
   * <ul>
   *   <li>When {@link JAASRealm} (default constructor).</li>
   *   <li>Then {@link JAASRealm} (default constructor) Container {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setRealm(Realm)}
   */
  @Test
  public void testSetRealm_whenJAASRealm_thenJAASRealmContainerStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    JAASRealm realm = new JAASRealm();

    // Act
    standardContext.setRealm(realm);

    // Assert
    Container container = realm.getContainer();
    assertTrue(container instanceof StandardContext);
    assertEquals("other", realm.getAppName());
    assertSame(realm, container.getRealm());
    assertSame(realm, standardContext.getRealm());
    assertSame(realm, standardContext.getRealmInternal());
    assertSame(realm, ((StandardContext) container).getRealmInternal());
  }

  /**
   * Test {@link ContainerBase#setRealm(Realm)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardContext} (default constructor) Realm is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#setRealm(Realm)}
   */
  @Test
  public void testSetRealm_whenNull_thenStandardContextRealmIsNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();

    // Act
    standardContext.setRealm(null);

    // Assert that nothing has changed
    assertNull(standardContext.getRealm());
    assertNull(standardContext.getRealmInternal());
  }

  /**
   * Test {@link ContainerBase#addChild(Container)}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link StandardWrapper} (default constructor) Parent {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#addChild(Container)}
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
   * Test {@link ContainerBase#addContainerListener(ContainerListener)}.
   * <p>
   * Method under test: {@link ContainerBase#addContainerListener(ContainerListener)}
   */
  @Test
  public void testAddContainerListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    ThreadLocalLeakPreventionListener listener = new ThreadLocalLeakPreventionListener();

    // Act
    standardContext.addContainerListener(listener);

    // Assert
    List<ContainerListener> containerListenerList = standardContext.listeners;
    assertEquals(1, containerListenerList.size());
    ContainerListener[] findContainerListenersResult = standardContext.findContainerListeners();
    assertEquals(1, findContainerListenersResult.length);
    assertSame(listener, containerListenerList.get(0));
    assertSame(listener, findContainerListenersResult[0]);
  }

  /**
   * Test {@link ContainerBase#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link ContainerBase#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    NamingContextListener listener = new NamingContextListener();

    // Act
    standardContext.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = standardContext.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link ContainerBase#findChild(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#findChild(String)}
   */
  @Test
  public void testFindChild_whenName() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findChild("Name"));
  }

  /**
   * Test {@link ContainerBase#findChild(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#findChild(String)}
   */
  @Test
  public void testFindChild_whenNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).findChild(null));
  }

  /**
   * Test {@link ContainerBase#findChildren()}.
   * <p>
   * Method under test: {@link ContainerBase#findChildren()}
   */
  @Test
  public void testFindChildren() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findChildren().length);
  }

  /**
   * Test {@link ContainerBase#findContainerListeners()}.
   * <p>
   * Method under test: {@link ContainerBase#findContainerListeners()}
   */
  @Test
  public void testFindContainerListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).findContainerListeners().length);
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Given {@link AprLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_givenAprLifecycleListener() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.addLifecycleListener(new AprLifecycleListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    assertEquals("DESTROYED", child.getStateName());
    NamingResourcesImpl namingResources = child.getNamingResources();
    assertEquals("FAILED", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.FAILED, namingResources.getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Given {@link ExtractingRoot} (default constructor).</li>
   *   <li>Then {@link StandardContext} (default constructor) Resources {@link ExtractingRoot}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_givenExtractingRoot_thenStandardContextResourcesExtractingRoot() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.setResources(new ExtractingRoot());
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    WebResourceRoot resources = child.getResources();
    assertTrue(resources instanceof ExtractingRoot);
    assertEquals("DESTROYED", resources.getStateName());
    assertEquals("DESTROYED", child.getStateName());
    assertEquals(LifecycleState.DESTROYED, resources.getState());
    assertEquals(LifecycleState.DESTROYED, child.getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>Then {@link StandardContext} (default constructor) Cluster {@link SimpleTcpCluster}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_givenSimpleTcpCluster_thenStandardContextClusterSimpleTcpCluster() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.setCluster(new SimpleTcpCluster());
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    Cluster cluster = child.getCluster();
    assertTrue(cluster instanceof SimpleTcpCluster);
    Cluster cluster2 = child.cluster;
    assertTrue(cluster2 instanceof SimpleTcpCluster);
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster).getStateName());
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster2).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster).getState());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster2).getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then {@link StandardContext} (default constructor) StateName is {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_givenStandardContext_thenStandardContextStateNameIsFailed() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.setParent(new StandardContext());
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    assertEquals("FAILED", child.getStateName());
    NamingResourcesImpl namingResources = child.getNamingResources();
    assertEquals("FAILED", namingResources.getStateName());
    assertEquals(LifecycleState.FAILED, child.getState());
    assertEquals(LifecycleState.FAILED, namingResources.getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then {@link StandardContext} (default constructor) Loader {@link WebappLoader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_givenWebappLoader_thenStandardContextLoaderWebappLoader() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.setLoader(new WebappLoader());
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    Loader loader = child.getLoader();
    assertTrue(loader instanceof WebappLoader);
    assertEquals("DESTROYED", child.getStateName());
    assertEquals("DESTROYED", ((WebappLoader) loader).getStateName());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.DESTROYED, ((WebappLoader) loader).getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) NamingResources StateName is {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_thenStandardContextNamingResourcesStateNameIsFailed() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    assertEquals("DESTROYED", child.getStateName());
    NamingResourcesImpl namingResources = child.getNamingResources();
    assertEquals("FAILED", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.FAILED, namingResources.getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Pipeline Basic {@link StandardContextValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_thenStandardContextPipelineBasicStandardContextValve() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.addValve(new BasicAuthenticator());

    // Act
    standardEngine.removeChild(child);

    // Assert
    Pipeline pipeline = child.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardContextValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("Catalina", ((StandardContextValve) basic).getDomain());
    assertNull(((StandardContextValve) basic).getObjectName());
    assertNull(child.getAuthenticator());
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
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>Then {@link StandardContext} (default constructor) Realm {@link AuthenticatedUserRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_thenStandardContextRealmAuthenticatedUserRealm() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    StandardContext child = new StandardContext();
    child.setRealm(new AuthenticatedUserRealm());
    child.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.removeChild(child);

    // Assert
    Realm realm = child.getRealm();
    assertTrue(realm instanceof AuthenticatedUserRealm);
    assertEquals("DESTROYED", child.getStateName());
    assertEquals("DESTROYED", ((AuthenticatedUserRealm) realm).getStateName());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.DESTROYED, ((AuthenticatedUserRealm) realm).getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardEngine} (default constructor) Pipeline Basic DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenNull_thenStandardEnginePipelineBasicDomainInternalIsCatalina() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.removeChild(null);

    // Assert that nothing has changed
    Pipeline pipeline = standardEngine.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("Catalina", ((StandardEngineValve) basic).getDomainInternal());
    assertEquals("NEW", standardEngine.getStateName());
    assertEquals("NEW", ((StandardEngineValve) basic).getStateName());
    assertEquals("NEW", ((StandardPipeline) pipeline).getStateName());
    assertEquals(LifecycleState.NEW, standardEngine.getState());
    assertEquals(LifecycleState.NEW, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.NEW, ((StandardPipeline) pipeline).getState());
    assertSame(standardEngine, ((StandardEngineValve) basic).getContainer());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenStandardContext() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardContext child = new StandardContext();

    // Act
    standardEngine.removeChild(child);

    // Assert
    assertEquals("DESTROYED", child.getStateName());
    NamingResourcesImpl namingResources = child.getNamingResources();
    assertEquals("FAILED", namingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.FAILED, namingResources.getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then {@link StandardEngine} (default constructor) StateName is {@code DESTROYED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenStandardEngine_thenStandardEngineStateNameIsDestroyed() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardEngine child = new StandardEngine();

    // Act
    standardEngine.removeChild(child);

    // Assert
    Pipeline pipeline = child.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("DESTROYED", child.getStateName());
    assertEquals("DESTROYED", ((StandardEngineValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertNull(((StandardEngineValve) basic).getDomainInternal());
    assertNull(((StandardEngineValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, child.getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
  }

  /**
   * Test {@link ContainerBase#removeChild(Container)}.
   * <ul>
   *   <li>When {@link StandardHost} (default constructor).</li>
   *   <li>Then {@link StandardHost} (default constructor) Pipeline Basic {@link StandardHostValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#removeChild(Container)}
   */
  @Test
  public void testRemoveChild_whenStandardHost_thenStandardHostPipelineBasicStandardHostValve() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardHost child = new StandardHost();

    // Act
    standardEngine.removeChild(child);

    // Assert
    Pipeline pipeline = child.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardHostValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("DESTROYED", ((StandardHostValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertNull(((StandardHostValve) basic).getDomainInternal());
    assertNull(((StandardHostValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, ((StandardHostValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal() throws LifecycleException {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    cluster.addLifecycleListener(new AprLifecycleListener());

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setCluster(cluster);
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.destroyInternal();

    // Assert
    Cluster cluster2 = standardEngine.getCluster();
    assertTrue(cluster2 instanceof SimpleTcpCluster);
    Cluster cluster3 = standardEngine.cluster;
    assertTrue(cluster3 instanceof SimpleTcpCluster);
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster2).getStateName());
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster3).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster2).getState());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster3).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal2() throws LifecycleException {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    StandardEngine engine = new StandardEngine();
    StandardHost host = new StandardHost();
    cluster.addLifecycleListener(new AccessLogListener(engine, host, new StandardContext()));

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setCluster(cluster);
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.destroyInternal();

    // Assert
    Cluster cluster2 = standardEngine.getCluster();
    assertTrue(cluster2 instanceof SimpleTcpCluster);
    Cluster cluster3 = standardEngine.cluster;
    assertTrue(cluster3 instanceof SimpleTcpCluster);
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster2).getStateName());
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster3).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster2).getState());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster3).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>Then {@link StandardEngine} (default constructor) Realm {@link NullRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardEngine_thenStandardEngineRealmNullRealm() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.destroyInternal();

    // Assert
    Pipeline pipeline = standardEngine.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    Realm realm = standardEngine.getRealm();
    assertTrue(realm instanceof NullRealm);
    assertEquals("DESTROYED", ((StandardEngineValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertEquals("NEW", ((NullRealm) realm).getStateName());
    assertNull(((StandardEngineValve) basic).getDomainInternal());
    assertNull(((StandardEngineValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
    assertEquals(LifecycleState.NEW, ((NullRealm) realm).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardEngine} (default constructor) Cluster {@link SimpleTcpCluster}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardEngineClusterSimpleTcpCluster() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setCluster(new SimpleTcpCluster());
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.destroyInternal();

    // Assert
    Cluster cluster = standardEngine.getCluster();
    assertTrue(cluster instanceof SimpleTcpCluster);
    Cluster cluster2 = standardEngine.cluster;
    assertTrue(cluster2 instanceof SimpleTcpCluster);
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster).getStateName());
    assertEquals("DESTROYED", ((SimpleTcpCluster) cluster2).getStateName());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster).getState());
    assertEquals(LifecycleState.DESTROYED, ((SimpleTcpCluster) cluster2).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardEngine} (default constructor) Realm {@link AuthenticatedUserRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardEngineRealmAuthenticatedUserRealm() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setRealm(new AuthenticatedUserRealm());
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.destroyInternal();

    // Assert
    Pipeline pipeline = standardEngine.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    Realm realm = standardEngine.getRealm();
    assertTrue(realm instanceof AuthenticatedUserRealm);
    assertEquals("DESTROYED", ((StandardEngineValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertEquals("DESTROYED", ((AuthenticatedUserRealm) realm).getStateName());
    assertNull(((StandardEngineValve) basic).getDomainInternal());
    assertNull(((StandardEngineValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
    assertEquals(LifecycleState.DESTROYED, ((AuthenticatedUserRealm) realm).getState());
  }

  /**
   * Test {@link ContainerBase#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardEngine} (default constructor) Realm {@link NullRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardEngineRealmNullRealm() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.destroyInternal();

    // Assert
    Pipeline pipeline = standardEngine.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    Realm realm = standardEngine.getRealm();
    assertTrue(realm instanceof NullRealm);
    assertEquals("DESTROYED", ((StandardEngineValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertEquals("NEW", ((NullRealm) realm).getStateName());
    assertNull(((StandardEngineValve) basic).getDomainInternal());
    assertNull(((StandardEngineValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
    assertEquals(LifecycleState.NEW, ((NullRealm) realm).getState());
  }

  /**
   * Test {@link ContainerBase#getAccessLog()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getAccessLog()}
   */
  @Test
  public void testGetAccessLog_givenStandardContext() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getAccessLog());
  }

  /**
   * Test {@link ContainerBase#getAccessLog()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addValve {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getAccessLog()}
   */
  @Test
  public void testGetAccessLog_givenStandardContextAddValveBasicAuthenticator() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addValve(new BasicAuthenticator());

    // Act and Assert
    assertNull(standardContext.getAccessLog());
  }

  /**
   * Test {@link ContainerBase#addValve(Valve)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#addValve(Valve)}
   */
  @Test
  public void testAddValve_givenStandardContext_thenArrayLengthIsTwo() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardContext.addValve(valve);

    // Assert
    Container container = valve.getContainer();
    assertTrue(container instanceof StandardContext);
    Pipeline pipeline = container.getPipeline();
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(2, pipeline.getValves().length);
    assertEquals(2, ((StandardPipeline) pipeline).getValveObjectNames().length);
    assertSame(valve, standardContext.getAuthenticator());
  }

  /**
   * Test {@link ContainerBase#addValve(Valve)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#addValve(Valve)}
   */
  @Test
  public void testAddValve_thenArrayLengthIsOne() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    ThreadLocalLeakPreventionListener listener = new ThreadLocalLeakPreventionListener();
    standardContext.addContainerListener(listener);
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardContext.addValve(valve);

    // Assert
    Container container = valve.getContainer();
    assertTrue(container instanceof StandardContext);
    ContainerListener[] findContainerListenersResult = container.findContainerListeners();
    assertEquals(1, findContainerListenersResult.length);
    assertSame(listener, findContainerListenersResult[0]);
    assertSame(standardContext.listeners, ((StandardContext) container).listeners);
  }

  /**
   * Test {@link ContainerBase#addValve(Valve)}.
   * <ul>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Container Authenticator {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#addValve(Valve)}
   */
  @Test
  public void testAddValve_thenBasicAuthenticatorContainerAuthenticatorBasicAuthenticator() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    BasicAuthenticator valve = new BasicAuthenticator();
    standardContext.addValve(valve);
    BasicAuthenticator valve2 = new BasicAuthenticator();

    // Act
    standardContext.addValve(valve2);

    // Assert
    Container container = valve2.getContainer();
    assertTrue(((StandardContext) container).getAuthenticator() instanceof BasicAuthenticator);
    assertTrue(container instanceof StandardContext);
    assertTrue(container.getPipeline() instanceof StandardPipeline);
    assertSame(valve, standardContext.getAuthenticator());
  }

  /**
   * Test {@link ContainerBase#addValve(Valve)}.
   * <ul>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Container Authenticator Next {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#addValve(Valve)}
   */
  @Test
  public void testAddValve_thenBasicAuthenticatorContainerAuthenticatorNextBasicAuthenticator() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.addValve(new BasicAuthenticator());
    standardContext.addValve(new BasicAuthenticator());
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardContext.addValve(valve);

    // Assert
    Container container = valve.getContainer();
    Authenticator authenticator = ((StandardContext) container).getAuthenticator();
    assertTrue(authenticator instanceof BasicAuthenticator);
    assertTrue(((BasicAuthenticator) authenticator).getNext() instanceof BasicAuthenticator);
    assertTrue(container instanceof StandardContext);
    Pipeline pipeline = container.getPipeline();
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals(4, pipeline.getValves().length);
    assertEquals(4, ((StandardPipeline) pipeline).getValveObjectNames().length);
  }

  /**
   * Test {@link ContainerBase#getCatalinaBase()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_givenStandardContext() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getCatalinaBase());
  }

  /**
   * Test {@link ContainerBase#getCatalinaBase()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNull(standardContext.getCatalinaBase());
  }

  /**
   * Test {@link ContainerBase#getCatalinaHome()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_givenStandardContext() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getCatalinaHome());
  }

  /**
   * Test {@link ContainerBase#getCatalinaHome()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertNull(standardContext.getCatalinaHome());
  }

  /**
   * Test {@link ContainerBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardContext()).getDomainInternal());
  }

  /**
   * Test {@link ContainerBase#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnCatalina() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", standardContext.getDomainInternal());
  }

  /**
   * Test {@link ContainerBase#getChildren()}.
   * <p>
   * Method under test: {@link ContainerBase#getChildren()}
   */
  @Test
  public void testGetChildren() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardContext()).getChildren().length);
  }

  /**
   * Test {@link ContainerBase#toString()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code StandardContext[null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#toString()}
   */
  @Test
  public void testToString_givenStandardContext_thenReturnStandardContextNull() {
    // Arrange, Act and Assert
    assertEquals("StandardContext[null]", (new StandardContext()).toString());
  }

  /**
   * Test {@link ContainerBase#toString()}.
   * <ul>
   *   <li>Then return {@code StandardContext[null].StandardContext[null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainerBase#toString()}
   */
  @Test
  public void testToString_thenReturnStandardContextNullStandardContextNull() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());

    // Act and Assert
    assertEquals("StandardContext[null].StandardContext[null]", standardContext.toString());
  }
}
