package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Realm;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.StandardEngine.AccessLogListener;
import org.apache.catalina.core.StandardEngine.NoopAccessLog;
import org.apache.catalina.core.TestStandardContext.MyWrapperContainerListener;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.catalina.realm.NullRealm;
import org.apache.tomcat.unittest.TesterHost;
import org.junit.Test;

public class StandardEngineDiffblueTest {
  /**
   * Test new {@link StandardEngine} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardEngine}
   */
  @Test
  public void testNewStandardEngine() {
    // Arrange and Act
    StandardEngine actualStandardEngine = new StandardEngine();

    // Assert
    assertTrue(actualStandardEngine.getPipeline() instanceof StandardPipeline);
    Realm realm = actualStandardEngine.getRealm();
    assertTrue(realm instanceof NullRealm);
    assertEquals("", actualStandardEngine.getMBeanKeyProperties());
    assertEquals("Catalina", actualStandardEngine.getDomain());
    assertEquals("NEW", actualStandardEngine.getStateName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardEngine.getLogName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", actualStandardEngine.logName);
    assertEquals("type=Engine", actualStandardEngine.getObjectNameKeyProperties());
    assertNull(actualStandardEngine.getCatalinaBase());
    assertNull(actualStandardEngine.getCatalinaHome());
    assertNotNull(actualStandardEngine.getParentClassLoader());
    assertNull(actualStandardEngine.parentClassLoader);
    assertNull(actualStandardEngine.getName());
    assertNull(actualStandardEngine.getDefaultHost());
    assertNull(actualStandardEngine.getDomainInternal());
    assertNull(actualStandardEngine.getJvmRoute());
    assertNull(actualStandardEngine.startStopExecutor);
    assertNull(actualStandardEngine.backgroundProcessorFuture);
    assertNull(actualStandardEngine.monitorFuture);
    assertNull(actualStandardEngine.getObjectName());
    assertNull(actualStandardEngine.getAccessLog());
    assertNull(actualStandardEngine.accessLog);
    assertNull(actualStandardEngine.getCluster());
    assertNull(actualStandardEngine.getClusterInternal());
    assertNull(actualStandardEngine.cluster);
    assertNull(actualStandardEngine.getParent());
    assertNull(actualStandardEngine.getService());
    assertEquals(0, actualStandardEngine.findContainerListeners().length);
    assertEquals(0, actualStandardEngine.getChildren().length);
    assertEquals(0, actualStandardEngine.findLifecycleListeners().length);
    assertEquals(1, actualStandardEngine.getStartStopThreads());
    assertEquals(LifecycleState.NEW, actualStandardEngine.getState());
    assertTrue(actualStandardEngine.children.isEmpty());
    assertTrue(actualStandardEngine.listeners.isEmpty());
    assertTrue(actualStandardEngine.getStartChildren());
    assertTrue(actualStandardEngine.getThrowOnFailure());
    assertEquals(ApplicationFilterChain.INCREMENT, actualStandardEngine.getBackgroundProcessorDelay());
    assertSame(realm, actualStandardEngine.getRealmInternal());
  }

  /**
   * Test {@link StandardEngine#getRealm()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>Then Container return {@link StandardEngine}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getRealm()}
   */
  @Test
  public void testGetRealm_givenStandardEngine_thenContainerReturnStandardEngine() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    Realm actualRealm = standardEngine.getRealm();

    // Assert
    Container container = actualRealm.getContainer();
    assertTrue(container instanceof StandardEngine);
    assertTrue(actualRealm instanceof NullRealm);
    assertEquals("/realm0", ((NullRealm) actualRealm).getRealmPath());
    assertEquals("Catalina", ((NullRealm) actualRealm).getDomainInternal());
    assertEquals("Catalina", ((NullRealm) actualRealm).getDomain());
    assertEquals("NEW", ((NullRealm) actualRealm).getStateName());
    assertEquals("strict", ((NullRealm) actualRealm).getAllRolesMode());
    assertEquals("type=Realm,realmPath=/realm0", ((NullRealm) actualRealm).getObjectNameKeyProperties());
    assertNull(((NullRealm) actualRealm).getUserAttributes());
    assertNull(((NullRealm) actualRealm).getX509UsernameRetrieverClassName());
    assertNull(((NullRealm) actualRealm).getObjectName());
    assertNull(actualRealm.getCredentialHandler());
    assertEquals(0, ((NullRealm) actualRealm).findLifecycleListeners().length);
    assertEquals(302, ((NullRealm) actualRealm).getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, ((NullRealm) actualRealm).getState());
    assertTrue(actualRealm.isAvailable());
    assertTrue(((NullRealm) actualRealm).getValidate());
    assertTrue(((NullRealm) actualRealm).isStripRealmForGss());
    assertTrue(((NullRealm) actualRealm).getThrowOnFailure());
    assertSame(standardEngine, container);
  }

  /**
   * Test {@link StandardEngine#getRealm()}.
   * <ul>
   *   <li>Then return {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getRealm()}
   */
  @Test
  public void testGetRealm_thenReturnAuthenticatedUserRealm() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    AuthenticatedUserRealm realm = new AuthenticatedUserRealm();
    standardEngine.setRealm(realm);

    // Act and Assert
    assertSame(realm, standardEngine.getRealm());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardEngine#setJvmRoute(String)}
   *   <li>{@link StandardEngine#setService(Service)}
   *   <li>{@link StandardEngine#getDefaultHost()}
   *   <li>{@link StandardEngine#getJvmRoute()}
   *   <li>{@link StandardEngine#getObjectNameKeyProperties()}
   *   <li>{@link StandardEngine#getService()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.setJvmRoute("42");
    StandardService service = new StandardService();
    standardEngine.setService(service);
    String actualDefaultHost = standardEngine.getDefaultHost();
    String actualJvmRoute = standardEngine.getJvmRoute();
    String actualObjectNameKeyProperties = standardEngine.getObjectNameKeyProperties();

    // Assert
    assertEquals("42", actualJvmRoute);
    assertEquals("type=Engine", actualObjectNameKeyProperties);
    assertNull(actualDefaultHost);
    assertSame(service, standardEngine.getService());
  }

  /**
   * Test NoopAccessLog getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link NoopAccessLog}
   *   <li>{@link NoopAccessLog#log(Request, Response, long)}
   *   <li>{@link NoopAccessLog#setRequestAttributesEnabled(boolean)}
   *   <li>{@link NoopAccessLog#getRequestAttributesEnabled()}
   * </ul>
   */
  @Test
  public void testNoopAccessLogGettersAndSetters() {
    // Arrange and Act
    NoopAccessLog actualNoopAccessLog = new NoopAccessLog();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    actualNoopAccessLog.log(request, new Response(new org.apache.coyote.Response()), 10L);
    actualNoopAccessLog.setRequestAttributesEnabled(true);

    // Assert
    assertFalse(actualNoopAccessLog.getRequestAttributesEnabled());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardEngine engine = new StandardEngine();
    StandardHost host = new StandardHost();
    standardEngine.addPropertyChangeListener(new AccessLogListener(engine, host, new StandardContext()));

    // Act
    standardEngine.setDefaultHost("localhost");

    // Assert
    assertEquals("localhost", standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost2() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardEngine engine = new StandardEngine();
    standardEngine.addPropertyChangeListener(new AccessLogListener(engine, null, new StandardContext()));

    // Act
    standardEngine.setDefaultHost("localhost");

    // Assert
    assertEquals("localhost", standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost3() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardEngine engine = new StandardEngine();
    TesterHost host = new TesterHost();
    standardEngine.addPropertyChangeListener(new AccessLogListener(engine, host, new StandardContext()));

    // Act
    standardEngine.setDefaultHost("localhost");

    // Assert
    assertEquals("localhost", standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost4() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardEngine engine = new StandardEngine();
    standardEngine.addPropertyChangeListener(new AccessLogListener(engine, new StandardHost(), null));

    // Act
    standardEngine.setDefaultHost("localhost");

    // Assert
    assertEquals("localhost", standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>Then {@link StandardEngine} (default constructor) DefaultHost is {@code localhost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost_givenStandardEngine_thenStandardEngineDefaultHostIsLocalhost() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.setDefaultHost("localhost");

    // Assert
    assertEquals("localhost", standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#setDefaultHost(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardEngine} (default constructor) DefaultHost is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#setDefaultHost(String)}
   */
  @Test
  public void testSetDefaultHost_whenNull_thenStandardEngineDefaultHostIsNull() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.setDefaultHost(null);

    // Assert that nothing has changed
    assertNull(standardEngine.getDefaultHost());
  }

  /**
   * Test {@link StandardEngine#addChild(Container)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>When {@link StandardHost} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#addChild(Container)}
   */
  @Test
  public void testAddChild_givenStandardEngine_whenStandardHost_thenArrayLengthIsZero() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    StandardHost child = new StandardHost();

    // Act
    standardEngine.addChild(child);

    // Assert
    Container parent = child.getParent();
    assertTrue(parent instanceof StandardEngine);
    assertEquals(0, parent.findContainerListeners().length);
    assertTrue(child.children.isEmpty());
    assertTrue(((StandardEngine) parent).listeners.isEmpty());
  }

  /**
   * Test {@link StandardEngine#addChild(Container)}.
   * <ul>
   *   <li>Then first element {@link MyWrapperContainerListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#addChild(Container)}
   */
  @Test
  public void testAddChild_thenFirstElementMyWrapperContainerListener() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    MyWrapperContainerListener listener = new MyWrapperContainerListener();
    standardEngine.addContainerListener(listener);
    StandardHost child = new StandardHost();

    // Act
    standardEngine.addChild(child);

    // Assert
    Container parent = child.getParent();
    assertTrue(parent instanceof StandardEngine);
    ContainerListener[] findContainerListenersResult = parent.findContainerListeners();
    ContainerListener containerListener = findContainerListenersResult[0];
    assertTrue(containerListener instanceof MyWrapperContainerListener);
    assertEquals(1, findContainerListenersResult.length);
    assertSame(listener, containerListener);
    assertSame(standardEngine.listeners, ((StandardEngine) parent).listeners);
  }

  /**
   * Test {@link StandardEngine#addChild(Container)}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) Parent {@link ContainerBase#listeners} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#addChild(Container)}
   */
  @Test
  public void testAddChild_thenStandardHostParentListenersSizeIsOne() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    ThreadLocalLeakPreventionListener listener = new ThreadLocalLeakPreventionListener();
    standardEngine.addContainerListener(listener);
    StandardHost child = new StandardHost();

    // Act
    standardEngine.addChild(child);

    // Assert
    Container parent = child.getParent();
    assertTrue(parent instanceof StandardEngine);
    List<ContainerListener> containerListenerList = ((StandardEngine) parent).listeners;
    assertEquals(1, containerListenerList.size());
    ContainerListener[] findContainerListenersResult = parent.findContainerListeners();
    assertEquals(1, findContainerListenersResult.length);
    assertTrue(child.children.isEmpty());
    assertSame(listener, containerListenerList.get(0));
    assertSame(listener, findContainerListenersResult[0]);
  }

  /**
   * Test {@link StandardEngine#addChild(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#addChild(Container)}
   */
  @Test
  public void testAddChild_whenStandardContext_thenThrowIllegalArgumentException() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardEngine.addChild(new StandardContext()));
  }

  /**
   * Test {@link StandardEngine#setParent(Container)}.
   * <p>
   * Method under test: {@link StandardEngine#setParent(Container)}
   */
  @Test
  public void testSetParent() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> standardEngine.setParent(new StandardContext()));
  }

  /**
   * Test {@link StandardEngine#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardEngine() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();

    // Act
    standardEngine.initInternal();

    // Assert
    ObjectName objectName = standardEngine.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Engine", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Engine", keyPropertyList.get("type"));
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link StandardEngine#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Domain is {@code /realm0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardEngineDomainIsRealm0() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setDomain("/realm0");
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.initInternal();

    // Assert
    ObjectName objectName = standardEngine.getObjectName();
    assertEquals("/realm0", objectName.getDomain());
    assertEquals("/realm0:type=Engine", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Engine", keyPropertyList.get("type"));
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link StandardEngine#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Name is {@code /realm0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardEngineNameIsRealm0() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setName("/realm0");
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.initInternal();

    // Assert
    ObjectName objectName = standardEngine.getObjectName();
    assertEquals("/realm0", objectName.getDomain());
    assertEquals("/realm0:type=Engine", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Engine", keyPropertyList.get("type"));
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link StandardEngine#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Realm is {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardEngineRealmIsAuthenticatedUserRealm() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setRealm(new AuthenticatedUserRealm());
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.initInternal();

    // Assert
    ObjectName objectName = standardEngine.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Engine", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Engine", keyPropertyList.get("type"));
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link StandardEngine#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardEngine} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardEngineObjectNameDomainIsCatalina() throws LifecycleException {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.addContainerListener(new ThreadLocalLeakPreventionListener());

    // Act
    standardEngine.initInternal();

    // Assert
    ObjectName objectName = standardEngine.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Engine", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("Engine", keyPropertyList.get("type"));
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link StandardEngine#logAccess(Request, Response, long, boolean)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) DefaultHost is {@code localhost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#logAccess(Request, Response, long, boolean)}
   */
  @Test
  public void testLogAccess_givenStandardEngineDefaultHostIsLocalhost() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setDefaultHost("localhost");
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    standardEngine.logAccess(request, new Response(new org.apache.coyote.Response()), 10L, true);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = standardEngine.support.getPropertyChangeListeners();
    assertTrue(propertyChangeListeners[0] instanceof AccessLogListener);
    assertEquals(1, propertyChangeListeners.length);
  }

  /**
   * Test {@link StandardEngine#logAccess(Request, Response, long, boolean)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>When {@code false}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#logAccess(Request, Response, long, boolean)}
   */
  @Test
  public void testLogAccess_givenStandardEngine_whenFalse_thenArrayLengthIsZero() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    standardEngine.logAccess(request, new Response(new org.apache.coyote.Response()), 10L, false);

    // Assert that nothing has changed
    assertEquals(0, standardEngine.support.getPropertyChangeListeners().length);
  }

  /**
   * Test {@link StandardEngine#logAccess(Request, Response, long, boolean)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>When {@code true}.</li>
   *   <li>Then first element {@link AccessLogListener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#logAccess(Request, Response, long, boolean)}
   */
  @Test
  public void testLogAccess_givenStandardEngine_whenTrue_thenFirstElementAccessLogListener() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    standardEngine.logAccess(request, new Response(new org.apache.coyote.Response()), 10L, true);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = standardEngine.support.getPropertyChangeListeners();
    assertTrue(propertyChangeListeners[0] instanceof AccessLogListener);
    assertEquals(1, propertyChangeListeners.length);
  }

  /**
   * Test {@link StandardEngine#getParentClassLoader()}.
   * <p>
   * Method under test: {@link StandardEngine#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setParentClassLoader(new ParallelWebappClassLoader());
    standardEngine.setService(null);

    // Act and Assert
    assertNotNull(standardEngine.getParentClassLoader());
  }

  /**
   * Test {@link StandardEngine#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardEngine() {
    // Arrange, Act and Assert
    assertNotNull((new StandardEngine()).getParentClassLoader());
  }

  /**
   * Test {@link StandardEngine#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) ParentClassLoader is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardEngineParentClassLoaderIsNull() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setParentClassLoader(null);
    standardEngine.setService(new StandardService());

    // Act and Assert
    assertNotNull(standardEngine.getParentClassLoader());
  }

  /**
   * Test {@link StandardEngine#getCatalinaBase()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Service is {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_givenStandardEngineServiceIsStandardService_thenReturnNull() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(new StandardService());

    // Act and Assert
    assertNull(standardEngine.getCatalinaBase());
  }

  /**
   * Test {@link StandardEngine#getCatalinaBase()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_givenStandardEngine_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardEngine()).getCatalinaBase());
  }

  /**
   * Test {@link StandardEngine#getCatalinaBase()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Server is {@link StandardServer} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_givenStandardServiceServerIsStandardServer_thenReturnNull() {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(service);

    // Act and Assert
    assertNull(standardEngine.getCatalinaBase());
  }

  /**
   * Test {@link StandardEngine#getCatalinaBase()}.
   * <ul>
   *   <li>Then return Name is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase_thenReturnNameIsTestTxt() {
    // Arrange
    StandardServer server = new StandardServer();
    server.setCatalinaBase(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(service);

    // Act
    File actualCatalinaBase = standardEngine.getCatalinaBase();

    // Assert
    assertEquals("test.txt", actualCatalinaBase.getName());
    assertTrue(actualCatalinaBase.isAbsolute());
  }

  /**
   * Test {@link StandardEngine#getCatalinaHome()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Service is {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_givenStandardEngineServiceIsStandardService_thenReturnNull() {
    // Arrange
    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(new StandardService());

    // Act and Assert
    assertNull(standardEngine.getCatalinaHome());
  }

  /**
   * Test {@link StandardEngine#getCatalinaHome()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_givenStandardEngine_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardEngine()).getCatalinaHome());
  }

  /**
   * Test {@link StandardEngine#getCatalinaHome()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Server is {@link StandardServer} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_givenStandardServiceServerIsStandardServer_thenReturnNull() {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(service);

    // Act and Assert
    assertNull(standardEngine.getCatalinaHome());
  }

  /**
   * Test {@link StandardEngine#getCatalinaHome()}.
   * <ul>
   *   <li>Then return Name is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngine#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome_thenReturnNameIsTestTxt() {
    // Arrange
    StandardServer server = new StandardServer();
    server.setCatalinaHome(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine standardEngine = new StandardEngine();
    standardEngine.setService(service);

    // Act
    File actualCatalinaHome = standardEngine.getCatalinaHome();

    // Assert
    assertEquals("test.txt", actualCatalinaHome.getName());
    assertTrue(actualCatalinaHome.isAbsolute());
  }

  /**
   * Test {@link StandardEngine#getDomainInternal()}.
   * <p>
   * Method under test: {@link StandardEngine#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal() {
    // Arrange, Act and Assert
    assertNull((new StandardEngine()).getDomainInternal());
  }
}
