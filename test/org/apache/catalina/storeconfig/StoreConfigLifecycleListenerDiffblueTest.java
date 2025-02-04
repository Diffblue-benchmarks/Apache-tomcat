package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import javax.management.DynamicMBean;
import javax.management.MBeanInfo;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.Server;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.mbeans.ClassNameMBean;
import org.junit.Test;

public class StoreConfigLifecycleListenerDiffblueTest {
  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "after_start", "Data"));

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent3() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "after_stop", "Data"));

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent4() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    StandardServer lifecycle = new StandardServer();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(lifecycle, "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(lifecycle, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent5() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("/org/apache/catalina/storeconfig/server-registry.xml");
    StandardServer lifecycle = new StandardServer();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(lifecycle, "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(lifecycle, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent6() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("http://xml.org/sax/properties/lexical-handler");

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent7() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("Catalina:type=StoreConfig");
    StandardServer lifecycle = new StandardServer();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(lifecycle, "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(lifecycle, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent8() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreConfigClass("org.apache.catalina.storeconfig.IStoreConfig");

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "after_start", "Data"));

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreConfigClass is {@code after_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenStoreConfigLifecycleListenerStoreConfigClassIsAfterStart() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreConfigClass("after_start");

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "after_start", "Data"));

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code after_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenStoreConfigLifecycleListenerStoreRegistryIsAfterStart() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("after_start");
    StandardServer lifecycle = new StandardServer();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(lifecycle, "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(lifecycle, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code conf/server.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenStoreConfigLifecycleListenerStoreRegistryIsConfServerXml() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("conf/server.xml");

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenStoreConfigLifecycleListenerStoreRegistryIsFile() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("file:/");

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(new StandardServer(), "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code Store Registry}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenStoreConfigLifecycleListenerStoreRegistryIsStoreRegistry() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("Store Registry");
    StandardServer lifecycle = new StandardServer();

    // Act
    storeConfigLifecycleListener.lifecycleEvent(new LifecycleEvent(lifecycle, "after_start", "Data"));

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(lifecycle, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("org.apache.tomcat.util.digester.Digester.sax");
    StandardServer server = new StandardServer();

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean2() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreConfigClass("org.apache.tomcat.util.digester.Digester.sax");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean3() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("/org/apache/catalina/storeconfig/server-registry.xml");
    StandardServer server = new StandardServer();

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean4() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("http://xml.org/sax/properties/lexical-handler");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean5() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("Catalina:type=StoreConfig");
    StandardServer server = new StandardServer();

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean6() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreConfigClass("org.apache.catalina.storeconfig.IStoreConfig");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean7() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreConfigClass("org.apache.catalina.storeconfig.StoreConfigLifecycleListener");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert that nothing has changed
    assertNull(storeConfigLifecycleListener.getStoreConfig());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean8() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("/org/apache/catalina/storeconfig/server-registry.xml");

    StandardServer server = new StandardServer();
    server.addLifecycleListener(new AprLifecycleListener());
    server.addLifecycleListener(new AprLifecycleListener());

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean9() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("org.apache.tomcat.util.digester.Digester.sax");

    StandardServer server = new StandardServer();
    server.setGlobalNamingResources(new NamingResourcesImpl());
    server.addLifecycleListener(new AprLifecycleListener());

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean_givenNamingResourcesImpl() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("/org/apache/catalina/storeconfig/server-registry.xml");

    StandardServer server = new StandardServer();
    server.setGlobalNamingResources(new NamingResourcesImpl());
    server.addLifecycleListener(new AprLifecycleListener());

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean_givenStoreConfigLifecycleListener() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    StandardServer server = new StandardServer();

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code conf/server.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean_givenStoreConfigLifecycleListenerStoreRegistryIsConfServerXml() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("conf/server.xml");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean_givenStoreConfigLifecycleListenerStoreRegistryIsFile() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("file:/");

    // Act
    storeConfigLifecycleListener.createMBean(new StandardServer());

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    assertEquals("conf/server.xml", ((StoreConfig) storeConfig).getServerFilename());
    assertNull(storeConfig.getServer());
    assertNull(storeConfig.getRegistry());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#createMBean(Server)}.
   * <ul>
   *   <li>Given {@link StoreConfigLifecycleListener} (default constructor) StoreRegistry is {@code Store Registry}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#createMBean(Server)}
   */
  @Test
  public void testCreateMBean_givenStoreConfigLifecycleListenerStoreRegistryIsStoreRegistry() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    storeConfigLifecycleListener.setStoreRegistry("Store Registry");
    StandardServer server = new StandardServer();

    // Act
    storeConfigLifecycleListener.createMBean(server);

    // Assert
    IStoreConfig storeConfig = storeConfigLifecycleListener.getStoreConfig();
    assertTrue(storeConfig instanceof StoreConfig);
    StoreRegistry registry = storeConfig.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    assertSame(server, storeConfig.getServer());
  }

  /**
   * Test {@link StoreConfigLifecycleListener#getManagedBean(Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return ModelerType is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#getManagedBean(Object)}
   */
  @Test
  public void testGetManagedBean_whenNull_thenReturnModelerTypeIsNull() throws Exception {
    // Arrange and Act
    DynamicMBean actualManagedBean = (new StoreConfigLifecycleListener()).getManagedBean(null);

    // Assert
    assertTrue(actualManagedBean instanceof ClassNameMBean);
    assertNull(((ClassNameMBean<Object>) actualManagedBean).getModelerType());
    MBeanInfo mBeanInfo = actualManagedBean.getMBeanInfo();
    assertEquals(1, mBeanInfo.getAttributes().length);
    assertEquals(2, ((ClassNameMBean<Object>) actualManagedBean).getNotificationInfo().length);
    assertEquals(5, mBeanInfo.getOperations().length);
  }

  /**
   * Test {@link StoreConfigLifecycleListener#getManagedBean(Object)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return ManagedResource is {@code Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfigLifecycleListener#getManagedBean(Object)}
   */
  @Test
  public void testGetManagedBean_whenObject_thenReturnManagedResourceIsObject() throws Exception {
    // Arrange and Act
    DynamicMBean actualManagedBean = (new StoreConfigLifecycleListener()).getManagedBean("Object");

    // Assert
    assertTrue(actualManagedBean instanceof ClassNameMBean);
    assertEquals("Object", ((ClassNameMBean<Object>) actualManagedBean).getManagedResource());
    assertEquals("java.lang.String", ((ClassNameMBean<Object>) actualManagedBean).getClassName());
    assertEquals("java.lang.String", ((ClassNameMBean<Object>) actualManagedBean).getModelerType());
    assertEquals(2, ((ClassNameMBean<Object>) actualManagedBean).getNotificationInfo().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StoreConfigLifecycleListener#setStoreConfig(IStoreConfig)}
   *   <li>{@link StoreConfigLifecycleListener#setStoreConfigClass(String)}
   *   <li>{@link StoreConfigLifecycleListener#setStoreRegistry(String)}
   *   <li>{@link StoreConfigLifecycleListener#getStoreConfig()}
   *   <li>{@link StoreConfigLifecycleListener#getStoreConfigClass()}
   *   <li>{@link StoreConfigLifecycleListener#getStoreRegistry()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StoreConfigLifecycleListener storeConfigLifecycleListener = new StoreConfigLifecycleListener();
    StoreConfig storeConfig = new StoreConfig();

    // Act
    storeConfigLifecycleListener.setStoreConfig(storeConfig);
    storeConfigLifecycleListener.setStoreConfigClass("Store Config Class");
    storeConfigLifecycleListener.setStoreRegistry("Store Registry");
    IStoreConfig actualStoreConfig = storeConfigLifecycleListener.getStoreConfig();
    String actualStoreConfigClass = storeConfigLifecycleListener.getStoreConfigClass();

    // Assert
    assertEquals("Store Config Class", actualStoreConfigClass);
    assertEquals("Store Registry", storeConfigLifecycleListener.getStoreRegistry());
    assertSame(storeConfig, actualStoreConfig);
  }

  /**
   * Test new {@link StoreConfigLifecycleListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StoreConfigLifecycleListener}
   */
  @Test
  public void testNewStoreConfigLifecycleListener() {
    // Arrange and Act
    StoreConfigLifecycleListener actualStoreConfigLifecycleListener = new StoreConfigLifecycleListener();

    // Assert
    assertEquals("org.apache.catalina.storeconfig.StoreConfig",
        actualStoreConfigLifecycleListener.getStoreConfigClass());
    assertNull(actualStoreConfigLifecycleListener.getStoreRegistry());
    assertNull(actualStoreConfigLifecycleListener.getStoreConfig());
  }
}
