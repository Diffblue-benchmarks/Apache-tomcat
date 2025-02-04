package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.management.ObjectName;
import org.apache.catalina.Engine;
import org.apache.catalina.Executor;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Valve;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardEngine.AccessLogListener;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.mapper.MapperListener;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.junit.Test;

public class StandardServiceDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardService#setGracefulStopAwaitMillis(long)}
   *   <li>{@link StandardService#setName(String)}
   *   <li>{@link StandardService#setServer(Server)}
   *   <li>{@link StandardService#toString()}
   *   <li>{@link StandardService#getContainer()}
   *   <li>{@link StandardService#getGracefulStopAwaitMillis()}
   *   <li>{@link StandardService#getMapper()}
   *   <li>{@link StandardService#getName()}
   *   <li>{@link StandardService#getObjectNameKeyProperties()}
   *   <li>{@link StandardService#getServer()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardService standardService = new StandardService();

    // Act
    standardService.setGracefulStopAwaitMillis(1L);
    standardService.setName("Name");
    StandardServer server = new StandardServer();
    standardService.setServer(server);
    String actualToStringResult = standardService.toString();
    Engine actualContainer = standardService.getContainer();
    long actualGracefulStopAwaitMillis = standardService.getGracefulStopAwaitMillis();
    standardService.getMapper();
    String actualName = standardService.getName();
    String actualObjectNameKeyProperties = standardService.getObjectNameKeyProperties();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("StandardService[Name]", actualToStringResult);
    assertEquals("type=Service", actualObjectNameKeyProperties);
    assertNull(actualContainer);
    assertEquals(1L, actualGracefulStopAwaitMillis);
    assertSame(server, standardService.getServer());
  }

  /**
   * Test {@link StandardService#setContainer(Engine)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StandardService} (default constructor) Container is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#setContainer(Engine)}
   */
  @Test
  public void testSetContainer_whenNull_thenStandardServiceContainerIsNull() {
    // Arrange
    StandardService standardService = new StandardService();

    // Act
    standardService.setContainer(null);

    // Assert that nothing has changed
    assertNull(standardService.getContainer());
  }

  /**
   * Test {@link StandardService#setContainer(Engine)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then {@link StandardService} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#setContainer(Engine)}
   */
  @Test
  public void testSetContainer_whenStandardEngine_thenStandardServiceContainerIsStandardEngine() {
    // Arrange
    StandardService standardService = new StandardService();
    StandardEngine engine = new StandardEngine();

    // Act
    standardService.setContainer(engine);

    // Assert
    assertSame(engine, standardService.getContainer());
    assertSame(standardService, engine.getService());
  }

  /**
   * Test {@link StandardService#addConnector(Connector)}.
   * <ul>
   *   <li>When {@link Connector#Connector()}.</li>
   *   <li>Then {@link Connector#Connector()} Service {@link StandardService}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#addConnector(Connector)}
   */
  @Test
  public void testAddConnector_whenConnector_thenConnectorServiceStandardService() {
    // Arrange
    StandardService standardService = new StandardService();
    Connector connector = new Connector();

    // Act
    standardService.addConnector(connector);

    // Assert
    Service service = connector.getService();
    assertTrue(service instanceof StandardService);
    assertEquals(1, standardService.getConnectorNames().length);
    assertEquals(1, standardService.connectors.length);
    assertSame(standardService, service);
  }

  /**
   * Test {@link StandardService#getConnectorNames()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor).</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getConnectorNames()}
   */
  @Test
  public void testGetConnectorNames_givenStandardService_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardService()).getConnectorNames().length);
  }

  /**
   * Test {@link StandardService#getConnectorNames()}.
   * <ul>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getConnectorNames()}
   */
  @Test
  public void testGetConnectorNames_thenReturnFirstElementIsNull() {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector());

    // Act
    ObjectName[] actualConnectorNames = standardService.getConnectorNames();

    // Assert
    assertNull(actualConnectorNames[0]);
    assertEquals(1, actualConnectorNames.length);
  }

  /**
   * Test {@link StandardService#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link StandardService#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    StandardService standardService = new StandardService();
    NamingContextListener listener = new NamingContextListener();

    // Act
    standardService.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = standardService.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link StandardService#findConnectors()}.
   * <p>
   * Method under test: {@link StandardService#findConnectors()}
   */
  @Test
  public void testFindConnectors() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardService()).findConnectors().length);
  }

  /**
   * Test {@link StandardService#addExecutor(Executor)}.
   * <p>
   * Method under test: {@link StandardService#addExecutor(Executor)}
   */
  @Test
  public void testAddExecutor() {
    // Arrange
    StandardService standardService = new StandardService();
    StandardThreadExecutor ex = new StandardThreadExecutor();

    // Act
    standardService.addExecutor(ex);

    // Assert
    ArrayList<Executor> executorList = standardService.executors;
    assertEquals(1, executorList.size());
    assertSame(ex, executorList.get(0));
  }

  /**
   * Test {@link StandardService#findExecutors()}.
   * <p>
   * Method under test: {@link StandardService#findExecutors()}
   */
  @Test
  public void testFindExecutors() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardService()).findExecutors().length);
  }

  /**
   * Test {@link StandardService#getExecutor(String)}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addExecutor {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getExecutor(String)}
   */
  @Test
  public void testGetExecutor_givenStandardServiceAddExecutorStandardThreadExecutor() {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addExecutor(new StandardThreadExecutor());

    // Act and Assert
    assertNull(standardService.getExecutor("Executor Name"));
  }

  /**
   * Test {@link StandardService#getExecutor(String)}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getExecutor(String)}
   */
  @Test
  public void testGetExecutor_givenStandardService_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardService()).getExecutor("Executor Name"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector("type=Service"));

    // Act
    standardService.initInternal();

    // Assert
    ObjectName[] connectorNames = standardService.getConnectorNames();
    ObjectName objectName = connectorNames[0];
    assertEquals("Catalina:port=auto-null,type=Connector", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("auto-null", keyPropertyList.get("port"));
    assertEquals("port=auto-null,type=Connector", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Connector,port=auto-null", objectName.getKeyPropertyListString());
    assertEquals(1, connectorNames.length);
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <ul>
   *   <li>Then first element ProtocolHandler {@link AjpNio2Protocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal_thenFirstElementProtocolHandlerAjpNio2Protocol() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector(new AjpNio2Protocol()));

    // Act
    standardService.initInternal();

    // Assert
    Connector[] connectorArray = standardService.connectors;
    ProtocolHandler protocolHandler = (connectorArray[0]).getProtocolHandler();
    assertTrue(protocolHandler instanceof AjpNio2Protocol);
    ObjectName[] connectorNames = standardService.getConnectorNames();
    assertEquals(1, connectorNames.length);
    assertEquals(1, connectorArray.length);
    Hashtable<String, String> keyPropertyList = ((AjpNio2Protocol) protocolHandler).getGlobalRequestProcessorMBeanName()
        .getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    Hashtable<String, String> keyPropertyList2 = ((AjpNio2Protocol) protocolHandler).getObjectName()
        .getKeyPropertyList();
    assertEquals(3, keyPropertyList2.size());
    Hashtable<String, String> keyPropertyList3 = (connectorNames[0]).getKeyPropertyList();
    assertEquals(3, keyPropertyList3.size());
    assertTrue(keyPropertyList.containsKey("type"));
    assertTrue(keyPropertyList2.containsKey("port"));
    assertTrue(keyPropertyList2.containsKey("type"));
    assertTrue(keyPropertyList3.containsKey("port"));
    assertTrue(keyPropertyList3.containsKey("type"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <ul>
   *   <li>Then first element ProtocolHandler {@link Http11NioProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal_thenFirstElementProtocolHandlerHttp11NioProtocol() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector());

    // Act
    standardService.initInternal();

    // Assert
    Connector[] connectorArray = standardService.connectors;
    ProtocolHandler protocolHandler = (connectorArray[0]).getProtocolHandler();
    assertTrue(protocolHandler instanceof Http11NioProtocol);
    ObjectName globalRequestProcessorMBeanName = ((Http11NioProtocol) protocolHandler)
        .getGlobalRequestProcessorMBeanName();
    assertEquals("Catalina:name=\"http-nio--1\",type=GlobalRequestProcessor",
        globalRequestProcessorMBeanName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = globalRequestProcessorMBeanName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("\"http-nio--1\"", keyPropertyList.get("name"));
    assertEquals("name=\"http-nio--1\",type=GlobalRequestProcessor",
        globalRequestProcessorMBeanName.getCanonicalKeyPropertyListString());
    assertEquals("type=GlobalRequestProcessor,name=\"http-nio--1\"",
        globalRequestProcessorMBeanName.getKeyPropertyListString());
    assertEquals(1, connectorArray.length);
    Hashtable<String, String> keyPropertyList2 = ((Http11NioProtocol) protocolHandler).getObjectName()
        .getKeyPropertyList();
    assertEquals(2, keyPropertyList2.size());
    assertTrue(keyPropertyList.containsKey("type"));
    assertTrue(keyPropertyList2.containsKey("port"));
    assertTrue(keyPropertyList2.containsKey("type"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardService} (default constructor) Container {@link StandardEngine}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardServiceContainerStandardEngine() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.setContainer(new StandardEngine());

    // Act
    standardService.initInternal();

    // Assert
    Engine container = standardService.getContainer();
    assertTrue(container instanceof StandardEngine);
    ObjectName objectName = container.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Engine", objectName.getCanonicalName());
    assertEquals("INITIALIZED", container.getStateName());
    assertEquals("type=Engine", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Engine", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(LifecycleState.INITIALIZED, container.getState());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardService} (default constructor) {@link StandardService#executors} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardServiceExecutorsSizeIsOne() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addExecutor(new StandardThreadExecutor());
    standardService.addConnector(new Connector());

    // Act
    standardService.initInternal();

    // Assert
    ArrayList<Executor> executorList = standardService.executors;
    assertEquals(1, executorList.size());
    Executor getResult = executorList.get(0);
    assertTrue(getResult instanceof StandardThreadExecutor);
    ObjectName objectName = ((StandardThreadExecutor) getResult).getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:name=null,type=Executor", objectName.getCanonicalName());
    assertEquals("INITIALIZED", getResult.getStateName());
    assertEquals("name=null,type=Executor", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Executor,name=null", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals(LifecycleState.INITIALIZED, getResult.getState());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("name"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardService#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardService} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardServiceObjectNameDomainIsCatalina() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();

    // Act
    standardService.initInternal();

    // Assert
    ObjectName objectName = standardService.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("INITIALIZED", mapperListener.getStateName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(LifecycleState.INITIALIZED, mapperListener.getState());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector("abstractProtocolHandler.destroy"));

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal2() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector(new AjpNio2Protocol()));

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal3() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector(new AjpNioProtocol()));

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal4() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector(new Http11Nio2Protocol()));

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal5() throws LifecycleException {
    // Arrange
    Connector connector = new Connector();
    StandardEngine engine = new StandardEngine();
    StandardHost host = new StandardHost();
    connector.addLifecycleListener(new AccessLogListener(engine, host, new StandardContext()));

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal6() throws LifecycleException {
    // Arrange
    Connector connector = new Connector("abstractProtocolHandler.destroy");
    connector.addLifecycleListener(null);

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    Connector[] connectorArray = standardService.connectors;
    Connector connector2 = connectorArray[0];
    assertEquals("FAILED", connector2.getStateName());
    assertEquals(1, standardService.getConnectorNames().length);
    assertEquals(1, connectorArray.length);
    assertEquals(LifecycleState.FAILED, connector2.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal7() throws LifecycleException {
    // Arrange
    Connector connector = new Connector(new AjpNio2Protocol());
    connector.addLifecycleListener(null);

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    Connector[] connectorArray = standardService.connectors;
    Connector connector2 = connectorArray[0];
    assertEquals("FAILED", connector2.getStateName());
    assertEquals(1, standardService.getConnectorNames().length);
    assertEquals(1, connectorArray.length);
    assertEquals(LifecycleState.FAILED, connector2.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} addLifecycleListener {@link AprLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenConnectorAddLifecycleListenerAprLifecycleListener() throws LifecycleException {
    // Arrange
    Connector connector = new Connector();
    connector.addLifecycleListener(new AprLifecycleListener());

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} addLifecycleListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenConnectorAddLifecycleListenerNull() throws LifecycleException {
    // Arrange
    Connector connector = new Connector();
    connector.addLifecycleListener(null);

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    Connector[] connectorArray = standardService.connectors;
    Connector connector2 = connectorArray[0];
    assertEquals("FAILED", connector2.getStateName());
    assertEquals(1, standardService.getConnectorNames().length);
    assertEquals(1, connectorArray.length);
    assertEquals(LifecycleState.FAILED, connector2.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} addLifecycleListener {@link OpenSSLLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenConnectorAddLifecycleListenerOpenSSLLifecycleListener()
      throws LifecycleException {
    // Arrange
    Connector connector = new Connector();
    connector.addLifecycleListener(new OpenSSLLifecycleListener());

    StandardService standardService = new StandardService();
    standardService.addConnector(connector);

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardService() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addConnector {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardServiceAddConnectorConnector() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector());

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addConnector {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardServiceAddConnectorConnector2() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addConnector(new Connector());
    standardService.addConnector(new Connector());

    // Act
    standardService.destroyInternal();

    // Assert
    MapperListener mapperListener = standardService.mapperListener;
    assertEquals("DESTROYED", mapperListener.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, mapperListener.getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardService} (default constructor) Container {@link StandardEngine}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardServiceContainerStandardEngine() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.setContainer(new StandardEngine());

    // Act
    standardService.destroyInternal();

    // Assert
    Engine container = standardService.getContainer();
    assertTrue(container instanceof StandardEngine);
    Pipeline pipeline = container.getPipeline();
    Valve basic = pipeline.getBasic();
    assertTrue(basic instanceof StandardEngineValve);
    assertTrue(pipeline instanceof StandardPipeline);
    assertEquals("DESTROYED", container.getStateName());
    assertEquals("DESTROYED", ((StandardEngineValve) basic).getStateName());
    assertEquals("DESTROYED", ((StandardPipeline) pipeline).getStateName());
    assertNull(((StandardEngineValve) basic).getDomainInternal());
    assertNull(((StandardEngineValve) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, container.getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardEngineValve) basic).getState());
    assertEquals(LifecycleState.DESTROYED, ((StandardPipeline) pipeline).getState());
  }

  /**
   * Test {@link StandardService#destroyInternal()}.
   * <ul>
   *   <li>Then {@link StandardService} (default constructor) {@link StandardService#executors} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenStandardServiceExecutorsSizeIsOne() throws LifecycleException {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.addExecutor(new StandardThreadExecutor());
    standardService.addConnector(new Connector());

    // Act
    standardService.destroyInternal();

    // Assert
    ArrayList<Executor> executorList = standardService.executors;
    assertEquals(1, executorList.size());
    Executor getResult = executorList.get(0);
    assertTrue(getResult instanceof StandardThreadExecutor);
    assertEquals("DESTROYED", getResult.getStateName());
    assertEquals(0, standardService.getConnectorNames().length);
    assertEquals(0, standardService.connectors.length);
    assertEquals(LifecycleState.DESTROYED, getResult.getState());
  }

  /**
   * Test {@link StandardService#getParentClassLoader()}.
   * <p>
   * Method under test: {@link StandardService#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader() {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.setParentClassLoader(new ParallelWebappClassLoader());
    standardService.setServer(null);

    // Act and Assert
    assertNotNull(standardService.getParentClassLoader());
  }

  /**
   * Test {@link StandardService#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardService() {
    // Arrange, Act and Assert
    assertNotNull((new StandardService()).getParentClassLoader());
  }

  /**
   * Test {@link StandardService#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) ParentClassLoader is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenStandardServiceParentClassLoaderIsNull() {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.setParentClassLoader(null);
    standardService.setServer(new StandardServer());

    // Act and Assert
    assertNotNull(standardService.getParentClassLoader());
  }

  /**
   * Test {@link StandardService#setParentClassLoader(ClassLoader)}.
   * <p>
   * Method under test: {@link StandardService#setParentClassLoader(ClassLoader)}
   */
  @Test
  public void testSetParentClassLoader() {
    // Arrange
    StandardService standardService = new StandardService();
    ParallelWebappClassLoader parent = new ParallelWebappClassLoader();

    // Act
    standardService.setParentClassLoader(parent);

    // Assert
    assertSame(parent, standardService.getParentClassLoader());
  }

  /**
   * Test {@link StandardService#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Name is {@code Name}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardEngineNameIsName_thenReturnName() {
    // Arrange
    StandardEngine engine = new StandardEngine();
    engine.setName("Name");

    StandardService standardService = new StandardService();
    standardService.setContainer(engine);

    // Act and Assert
    assertEquals("Name", standardService.getDomainInternal());
  }

  /**
   * Test {@link StandardService#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardService_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardService()).getDomainInternal());
  }

  /**
   * Test {@link StandardService#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardService#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnNull() {
    // Arrange
    StandardService standardService = new StandardService();
    standardService.setContainer(new StandardEngine());

    // Act and Assert
    assertNull(standardService.getDomainInternal());
  }

  /**
   * Test new {@link StandardService} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardService}
   */
  @Test
  public void testNewStandardService() {
    // Arrange and Act
    StandardService actualStandardService = new StandardService();

    // Assert
    assertEquals("Catalina", actualStandardService.getDomain());
    assertEquals("NEW", actualStandardService.getStateName());
    assertEquals("type=Service", actualStandardService.getObjectNameKeyProperties());
    assertNotNull(actualStandardService.getParentClassLoader());
    assertNull(actualStandardService.getDomainInternal());
    assertNull(actualStandardService.getName());
    assertNull(actualStandardService.getObjectName());
    assertNull(actualStandardService.getContainer());
    assertNull(actualStandardService.getServer());
    assertEquals(0, actualStandardService.getConnectorNames().length);
    assertEquals(0, actualStandardService.findLifecycleListeners().length);
    assertEquals(0, actualStandardService.connectors.length);
    assertEquals(0L, actualStandardService.getGracefulStopAwaitMillis());
    assertEquals(LifecycleState.NEW, actualStandardService.getState());
    assertTrue(actualStandardService.executors.isEmpty());
    assertTrue(actualStandardService.getThrowOnFailure());
  }
}
