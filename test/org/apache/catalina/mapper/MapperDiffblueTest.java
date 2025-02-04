package org.apache.catalina.mapper;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.management.Descriptor;
import javax.management.MBeanNotificationInfo;
import javax.management.ObjectName;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardPipeline;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.mapper.Mapper.ContextList;
import org.apache.catalina.mapper.Mapper.ContextVersion;
import org.apache.catalina.mapper.Mapper.MapElement;
import org.apache.catalina.mapper.Mapper.MappedContext;
import org.apache.catalina.mapper.Mapper.MappedHost;
import org.apache.catalina.mapper.Mapper.MappedWrapper;
import org.apache.catalina.webresources.ExtractingRoot;
import org.junit.Test;

public class MapperDiffblueTest {
  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then fourth element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenFourthElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("mapper.duplicateHost", new String[]{"*."}, host);
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[3];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals("mapper.duplicateHost", mappedHost.getRealHostName());
    assertEquals("mapper.duplicateHost", mappedHost.name);
    assertEquals(4, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertSame(host, mappedHost.object);
    MappedHost mappedHost2 = mappedHostArray[0];
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then second element {@link MapElement#name} is {@code Aliases}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenSecondElementNameIsAliases() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("mapper.duplicateHost", new String[]{"Aliases"}, new StandardHost());
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals("Aliases", mappedHost.name);
    assertEquals("mapper.duplicateHost", mappedHost.getRealHostName());
    assertEquals(4, mappedHostArray.length);
    assertTrue(mappedHost.isAlias());
    MappedHost expectedRealHost = mappedHostArray[3];
    assertSame(expectedRealHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then second element RealHostName is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenSecondElementRealHostNameIsName() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertEquals(2, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then third element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenThirdElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[2];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    MappedHost mappedHost2 = mappedHostArray[1];
    ContextList contextList = mappedHost2.contextList;
    assertEquals(0, contextList.contexts.length);
    assertEquals(0, contextList.nesting);
    assertEquals(3, mappedHostArray.length);
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
    assertSame(contextList, mappedHost.contextList);
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then third element Aliases size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenThirdElementAliasesSizeIsTwo() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.addHost("Name", new String[]{"*.", "Aliases", "*."}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[2];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(2, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals(3, mappedHostArray.length);
    MappedHost mappedHost2 = mappedHostArray[0];
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
    assertSame(mappedHostArray[1], ((List<MappedHost>) aliases).get(1));
    assertSame(mappedHost, mappedHost2.getRealHost());
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>Then third element {@link MappedHost#contextList} {@link ContextList#nesting} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_thenThirdElementContextListNestingIsZero() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Aliases", new String[]{"*."}, new StandardHost());

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[2];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertTrue(aliases instanceof List);
    ContextList contextList = mappedHost.contextList;
    assertEquals(0, contextList.contexts.length);
    assertEquals(0, contextList.nesting);
    assertEquals(3, mappedHostArray.length);
    assertTrue(aliases.isEmpty());
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then first element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_whenAsteriskDot_thenFirstElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.addHost("*.", new String[]{"Aliases"}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals(".", mappedHost.getRealHostName());
    MappedHost mappedHost2 = mappedHostArray[1];
    assertEquals(".", mappedHost2.getRealHostName());
    assertEquals(2, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertSame(mappedHost, mappedHost.getRealHost());
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
  }

  /**
   * Test {@link Mapper#addHost(String, String[], Host)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then first element {@link MapElement#name} is {@code Aliases}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHost(String, String[], Host)}
   */
  @Test
  public void testAddHost_whenName_thenFirstElementNameIsAliases() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    MappedHost mappedHost2 = mappedHostArray[0];
    assertEquals("Aliases", mappedHost2.name);
    assertEquals("Name", mappedHost.name);
    assertEquals(2, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test ContextList {@link ContextList#addContext(MappedContext, int)}.
   * <ul>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextList#addContext(MappedContext, int)}
   */
  @Test
  public void testContextListAddContext_thenReturnArrayLengthIsOne() {
    // Arrange
    ContextList contextList = new ContextList();
    StandardContext context = new StandardContext();
    MappedContext mappedContext = new MappedContext("Name",
        new ContextVersion("1.0.2", "Path", 3, context, new ExtractingRoot(), new String[]{"Welcome Resources"}));

    // Act
    ContextList actualAddContextResult = contextList.addContext(mappedContext, 3);

    // Assert
    MappedContext[] mappedContextArray = actualAddContextResult.contexts;
    assertEquals(1, mappedContextArray.length);
    assertEquals(3, actualAddContextResult.nesting);
    assertSame(mappedContext, mappedContextArray[0]);
  }

  /**
   * Test ContextList {@link ContextList#ContextList()}.
   * <p>
   * Method under test: {@link ContextList#ContextList()}
   */
  @Test
  public void testContextListNewContextList() {
    // Arrange and Act
    ContextList actualContextList = new ContextList();

    // Assert
    assertEquals(0, actualContextList.contexts.length);
    assertEquals(0, actualContextList.nesting);
  }

  /**
   * Test ContextVersion getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ContextVersion#markPaused()}
   *   <li>{@link ContextVersion#isPaused()}
   * </ul>
   */
  @Test
  public void testContextVersionGettersAndSetters() {
    // Arrange
    StandardContext context = new StandardContext();
    ContextVersion contextVersion = new ContextVersion("1.0.2", "Path", 3, context, new ExtractingRoot(),
        new String[]{"Welcome Resources"});

    // Act
    contextVersion.markPaused();

    // Assert
    assertTrue(contextVersion.isPaused());
  }

  /**
   * Test ContextVersion {@link ContextVersion#ContextVersion(String, String, int, Context, WebResourceRoot, String[])}.
   * <p>
   * Method under test: {@link ContextVersion#ContextVersion(String, String, int, Context, WebResourceRoot, String[])}
   */
  @Test
  public void testContextVersionNewContextVersion() {
    // Arrange
    StandardContext context = new StandardContext();

    // Act
    ContextVersion actualContextVersion = new ContextVersion("1.0.2", "Path", 3, context, new ExtractingRoot(),
        new String[]{"Welcome Resources"});

    // Assert
    assertTrue(actualContextVersion.object instanceof StandardContext);
    assertTrue(actualContextVersion.resources instanceof ExtractingRoot);
    assertEquals("1.0.2", actualContextVersion.name);
    assertEquals("Path", actualContextVersion.path);
    assertNull(actualContextVersion.defaultWrapper);
    assertEquals(0, actualContextVersion.exactWrappers.length);
    assertEquals(0, actualContextVersion.extensionWrappers.length);
    assertEquals(0, actualContextVersion.wildcardWrappers.length);
    assertEquals(0, actualContextVersion.nesting);
    assertEquals(1, actualContextVersion.welcomeResources.length);
    assertEquals(3, actualContextVersion.slashCount);
    assertFalse(actualContextVersion.isPaused());
  }

  /**
   * Test MappedContext {@link MappedContext#MappedContext(String, ContextVersion)}.
   * <p>
   * Method under test: {@link MappedContext#MappedContext(String, ContextVersion)}
   */
  @Test
  public void testMappedContextNewMappedContext() {
    // Arrange
    StandardContext context = new StandardContext();
    ContextVersion firstVersion = new ContextVersion("1.0.2", "Path", 3, context, new ExtractingRoot(),
        new String[]{"Welcome Resources"});

    // Act
    MappedContext actualMappedContext = new MappedContext("Name", firstVersion);

    // Assert
    assertEquals("Name", actualMappedContext.name);
    assertNull(actualMappedContext.object);
    ContextVersion[] contextVersionArray = actualMappedContext.versions;
    assertEquals(1, contextVersionArray.length);
    assertSame(firstVersion, contextVersionArray[0]);
  }

  /**
   * Test MappedHost {@link MappedHost#addAliases(Collection)}.
   * <p>
   * Method under test: {@link MappedHost#addAliases(Collection)}
   */
  @Test
  public void testMappedHostAddAliases() {
    // Arrange
    MappedHost mappedHost = new MappedHost("Name", new StandardHost());
    ArrayList<MappedHost> c = new ArrayList<>();

    // Act
    mappedHost.addAliases(c);

    // Assert that nothing has changed
    assertEquals(c, mappedHost.getAliases());
  }

  /**
   * Test MappedHost {@link MappedHost#addAliases(Collection)}.
   * <p>
   * Method under test: {@link MappedHost#addAliases(Collection)}
   */
  @Test
  public void testMappedHostAddAliases2() {
    // Arrange
    MappedHost mappedHost = new MappedHost("Name", new StandardHost());

    ArrayList<MappedHost> c = new ArrayList<>();
    MappedHost mappedHost2 = new MappedHost("Name", new StandardHost());

    c.add(mappedHost2);

    // Act
    mappedHost.addAliases(c);

    // Assert
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
  }

  /**
   * Test MappedHost {@link MappedHost#addAliases(Collection)}.
   * <p>
   * Method under test: {@link MappedHost#addAliases(Collection)}
   */
  @Test
  public void testMappedHostAddAliases3() {
    // Arrange
    MappedHost mappedHost = new MappedHost("Name", new StandardHost());

    ArrayList<MappedHost> c = new ArrayList<>();
    c.add(new MappedHost("Name", new StandardHost()));
    c.add(new MappedHost("Name", new StandardHost()));

    // Act
    mappedHost.addAliases(c);

    // Assert
    assertEquals(c, mappedHost.getAliases());
  }

  /**
   * Test MappedHost {@link MappedHost#getRealHostName()}.
   * <p>
   * Method under test: {@link MappedHost#getRealHostName()}
   */
  @Test
  public void testMappedHostGetRealHostName() {
    // Arrange, Act and Assert
    assertEquals("Name", (new MappedHost("Name", new StandardHost())).getRealHostName());
  }

  /**
   * Test MappedHost getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MappedHost#getAliases()}
   *   <li>{@link MappedHost#getRealHost()}
   * </ul>
   */
  @Test
  public void testMappedHostGettersAndSetters() {
    // Arrange
    MappedHost mappedHost = new MappedHost("Name", new StandardHost());

    // Act
    Collection<MappedHost> actualAliases = mappedHost.getAliases();

    // Assert
    assertTrue(actualAliases instanceof List);
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test MappedHost {@link MappedHost#isAlias()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MappedHost#isAlias()}
   */
  @Test
  public void testMappedHostIsAlias_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MappedHost("Name", new StandardHost())).isAlias());
  }

  /**
   * Test MappedHost {@link MappedHost#isAlias()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MappedHost#isAlias()}
   */
  @Test
  public void testMappedHostIsAlias_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new MappedHost("Alias", new MappedHost("Name", new StandardHost()))).isAlias());
  }

  /**
   * Test MappedHost {@link MappedHost#MappedHost(String, Host)}.
   * <p>
   * Method under test: {@link MappedHost#MappedHost(String, Host)}
   */
  @Test
  public void testMappedHostNewMappedHost() {
    // Arrange and Act
    MappedHost actualMappedHost = new MappedHost("Name", new StandardHost());

    // Assert
    Collection<MappedHost> aliases = actualMappedHost.getAliases();
    assertTrue(aliases instanceof List);
    assertTrue(actualMappedHost.object instanceof StandardHost);
    assertEquals("Name", actualMappedHost.getRealHostName());
    assertEquals("Name", actualMappedHost.name);
    assertFalse(actualMappedHost.isAlias());
    assertTrue(aliases.isEmpty());
    assertSame(actualMappedHost, actualMappedHost.getRealHost());
  }

  /**
   * Test MappedHost {@link MappedHost#MappedHost(String, MappedHost)}.
   * <p>
   * Method under test: {@link MappedHost#MappedHost(String, MappedHost)}
   */
  @Test
  public void testMappedHostNewMappedHost2() {
    // Arrange
    MappedHost realHost = new MappedHost("Name", new StandardHost());

    // Act
    MappedHost actualMappedHost = new MappedHost("Alias", realHost);

    // Assert
    assertTrue(actualMappedHost.object instanceof StandardHost);
    assertEquals("Alias", actualMappedHost.name);
    assertEquals("Name", actualMappedHost.getRealHostName());
    assertNull(actualMappedHost.getAliases());
    assertTrue(actualMappedHost.isAlias());
    assertSame(realHost, actualMappedHost.getRealHost());
  }

  /**
   * Test MappedWrapper {@link MappedWrapper#MappedWrapper(String, Wrapper, boolean, boolean)}.
   * <p>
   * Method under test: {@link MappedWrapper#MappedWrapper(String, Wrapper, boolean, boolean)}
   */
  @Test
  public void testMappedWrapperNewMappedWrapper() {
    // Arrange and Act
    MappedWrapper actualMappedWrapper = new MappedWrapper("Name", new StandardWrapper(), true, true);

    // Assert
    Wrapper wrapper = actualMappedWrapper.object;
    Pipeline pipeline = wrapper.getPipeline();
    assertTrue(pipeline instanceof StandardPipeline);
    assertTrue(wrapper instanceof StandardWrapper);
    assertEquals(",servlet=null,container0=null", wrapper.getMBeanKeyProperties());
    assertEquals("-1", ((StandardWrapper) wrapper).getLoadOnStartupString());
    assertEquals("Catalina", wrapper.getDomain());
    assertEquals("NEW", wrapper.getStateName());
    assertEquals("NEW", ((StandardPipeline) pipeline).getStateName());
    assertEquals("Name", actualMappedWrapper.name);
    MBeanNotificationInfo[] notificationInfo = ((StandardWrapper) wrapper).getNotificationInfo();
    MBeanNotificationInfo mBeanNotificationInfo = notificationInfo[0];
    assertEquals("javax.management.Notification", mBeanNotificationInfo.getName());
    MBeanNotificationInfo mBeanNotificationInfo2 = notificationInfo[1];
    assertEquals("javax.management.Notification", mBeanNotificationInfo2.getName());
    MBeanNotificationInfo mBeanNotificationInfo3 = notificationInfo[2];
    assertEquals("javax.management.Notification", mBeanNotificationInfo3.getName());
    MBeanNotificationInfo mBeanNotificationInfo4 = notificationInfo[3];
    assertEquals("javax.management.Notification", mBeanNotificationInfo4.getName());
    MBeanNotificationInfo mBeanNotificationInfo5 = notificationInfo[4];
    assertEquals("javax.management.Notification", mBeanNotificationInfo5.getName());
    MBeanNotificationInfo mBeanNotificationInfo6 = notificationInfo[5];
    assertEquals("javax.management.Notification", mBeanNotificationInfo6.getName());
    assertEquals("org.apache.catalina.core.ContainerBase.[/]", wrapper.getLogName());
    assertEquals("servlet is created", mBeanNotificationInfo.getDescription());
    assertEquals("servlet is deleted", mBeanNotificationInfo6.getDescription());
    assertEquals("servlet is running", mBeanNotificationInfo3.getDescription());
    assertEquals("servlet is starting", mBeanNotificationInfo2.getDescription());
    assertEquals("servlet is stopped", mBeanNotificationInfo5.getDescription());
    assertEquals("servlet start to stopped", mBeanNotificationInfo4.getDescription());
    assertNull(wrapper.getMultipartConfigElement());
    assertNull(wrapper.getServlet());
    assertNull(((StandardWrapper) wrapper).getServletContext());
    assertNull(wrapper.getCatalinaBase());
    assertNull(wrapper.getCatalinaHome());
    assertNotNull(wrapper.getParentClassLoader());
    assertNull(wrapper.getName());
    assertNull(wrapper.getRunAs());
    assertNull(wrapper.getServletClass());
    assertNull(((StandardWrapper) wrapper).getServletName());
    assertNull(wrapper.getObjectName());
    ObjectName[] valveObjectNames = ((StandardPipeline) pipeline).getValveObjectNames();
    assertNull(valveObjectNames[0]);
    assertNull(wrapper.getAccessLog());
    assertNull(wrapper.getCluster());
    assertNull(wrapper.getParent());
    assertNull(wrapper.getRealm());
    assertEquals(-1, wrapper.getBackgroundProcessorDelay());
    assertEquals(-1, wrapper.getLoadOnStartup());
    assertEquals(0, ((StandardWrapper) wrapper).getClassLoadTime());
    assertEquals(0, ((StandardWrapper) wrapper).getCountAllocated());
    assertEquals(0, wrapper.findContainerListeners().length);
    assertEquals(0, wrapper.findLifecycleListeners().length);
    assertEquals(0, ((StandardWrapper) wrapper).getChildren().length);
    assertEquals(0, ((StandardPipeline) pipeline).findLifecycleListeners().length);
    assertEquals(0L, wrapper.getAvailable());
    assertEquals(0L, ((StandardWrapper) wrapper).getErrorCount());
    assertEquals(0L, ((StandardWrapper) wrapper).getLoadTime());
    assertEquals(0L, ((StandardWrapper) wrapper).getMaxTime());
    assertEquals(0L, ((StandardWrapper) wrapper).getProcessingTime());
    assertEquals(0L, ((StandardWrapper) wrapper).getRequestCount());
    assertEquals(1, wrapper.getStartStopThreads());
    Valve[] valves = pipeline.getValves();
    assertEquals(1, valves.length);
    assertEquals(1, valveObjectNames.length);
    assertEquals(6, notificationInfo.length);
    assertEquals(LifecycleState.NEW, wrapper.getState());
    assertEquals(LifecycleState.NEW, ((StandardPipeline) pipeline).getState());
    assertFalse(wrapper.isAsyncSupported());
    assertFalse(wrapper.isOverridable());
    assertTrue(pipeline.isAsyncSupported());
    assertTrue(wrapper.isEnabled());
    assertTrue(((StandardWrapper) wrapper).getStartChildren());
    assertTrue(((StandardPipeline) pipeline).getThrowOnFailure());
    assertTrue(((StandardWrapper) wrapper).getThrowOnFailure());
    assertTrue(actualMappedWrapper.jspWildCard);
    assertTrue(actualMappedWrapper.resourceOnly);
    assertEquals(Long.MAX_VALUE, ((StandardWrapper) wrapper).getMinTime());
    Descriptor descriptor = mBeanNotificationInfo.getDescriptor();
    assertSame(descriptor, mBeanNotificationInfo2.getDescriptor());
    assertSame(descriptor, mBeanNotificationInfo3.getDescriptor());
    assertSame(descriptor, mBeanNotificationInfo4.getDescriptor());
    assertSame(descriptor, mBeanNotificationInfo5.getDescriptor());
    assertSame(descriptor, mBeanNotificationInfo6.getDescriptor());
    Valve basic = pipeline.getBasic();
    assertSame(basic, pipeline.getFirst());
    assertSame(basic, valves[0]);
    Wrapper expectedContainer = actualMappedWrapper.object;
    assertSame(expectedContainer, pipeline.getContainer());
    assertArrayEquals(new String[]{"j2ee.object.created"}, mBeanNotificationInfo.getNotifTypes());
    assertArrayEquals(new String[]{"j2ee.object.deleted"}, mBeanNotificationInfo6.getNotifTypes());
    assertArrayEquals(new String[]{"j2ee.object.stopped"}, mBeanNotificationInfo5.getNotifTypes());
    assertArrayEquals(new String[]{"j2ee.state.running"}, mBeanNotificationInfo3.getNotifTypes());
    assertArrayEquals(new String[]{"j2ee.state.starting"}, mBeanNotificationInfo2.getNotifTypes());
    assertArrayEquals(new String[]{"j2ee.state.stopped"}, mBeanNotificationInfo4.getNotifTypes());
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Name", host, "Name", "*.", context, new String[]{"*."}, resources, new ArrayList<>());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("Name");

    // Assert
    assertEquals(1, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("Name");

    // Assert
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>Then first element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_thenFirstElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("", new String[]{"Aliases"}, host);
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("Name");

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    MappedHost mappedHost2 = mappedHostArray[1];
    assertEquals("", mappedHost2.getRealHostName());
    assertEquals("Aliases", mappedHost2.name);
    assertEquals(2, mappedHostArray.length);
    assertSame(host, mappedHost2.object);
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>Then second element RealHostName is {@code .}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_thenSecondElementRealHostNameIsDot() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("*.", new String[]{"Name"}, host);

    // Act
    mapper.removeHost("Name");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals(".", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertEquals(2, mappedHostArray.length);
    assertSame(host, mappedHost.object);
    MappedHost expectedRealHost = mappedHostArray[0];
    assertSame(expectedRealHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then second element RealHostName is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_when42_thenSecondElementRealHostNameIsName() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("Name", new String[]{"*."}, host);
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("42");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertEquals(2, mappedHostArray.length);
    assertSame(host, mappedHost.object);
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenAsteriskDot_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHost("*.");

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then second element RealHostName is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenAsteriskDot_thenSecondElementRealHostNameIsName() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("Name", new String[]{"*."}, host);
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("*.");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertEquals(2, mappedHostArray.length);
    assertSame(host, mappedHost.object);
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenEmptyString_thenArrayLengthIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("");

    // Assert that nothing has changed
    assertEquals(1, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenName_thenArrayLengthIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHost("Name");

    // Assert that nothing has changed
    assertEquals(1, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenName_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHost("Name");

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHost(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenNull_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHost(null);

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Alias", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"Name"}, new StandardHost());
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals("Alias", mappedHost.name);
    MappedHost mappedHost2 = mappedHostArray[2];
    assertEquals("Aliases", mappedHost2.name);
    assertEquals(4, mappedHostArray.length);
    assertTrue(mappedHost2.isAlias());
    assertSame(mappedHost, mappedHost.getRealHost());
    MappedHost expectedRealHost = mappedHostArray[3];
    assertSame(expectedRealHost, mappedHost2.getRealHost());
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <ul>
   *   <li>Then first element {@link MapElement#name} is {@code Host Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias_thenFirstElementNameIsHostName() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    assertEquals("Host Name", mappedHost.name);
    assertEquals(1, mappedHostArray.length);
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <ul>
   *   <li>Then fourth element Aliases size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias_thenFourthElementAliasesSizeIsTwo() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("*.", host, "*.", "Name", context, new String[]{"Name"}, resources, new ArrayList<>());
    StandardHost host2 = new StandardHost();
    mapper.addHost("Name", new String[]{"Aliases"}, host2);

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[3];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(2, aliases.size());
    assertTrue(aliases instanceof List);
    MappedHost mappedHost2 = mappedHostArray[1];
    assertEquals("Alias", mappedHost2.name);
    MappedHost mappedHost3 = mappedHostArray[2];
    assertEquals("Aliases", mappedHost3.name);
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertNull(mappedHost3.getAliases());
    assertEquals(4, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertTrue(mappedHost3.isAlias());
    assertSame(host2, mappedHost.object);
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost, mappedHost3.getRealHost());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <ul>
   *   <li>Then third element Aliases size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias_thenThirdElementAliasesSizeIsTwo() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("Name", new String[]{"Aliases"}, host);

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[2];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(2, aliases.size());
    assertTrue(aliases instanceof List);
    MappedHost mappedHost2 = mappedHostArray[0];
    assertEquals("Alias", mappedHost2.name);
    MappedHost mappedHost3 = mappedHostArray[1];
    assertEquals("Aliases", mappedHost3.name);
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertNull(mappedHost3.getAliases());
    assertEquals(3, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertTrue(mappedHost3.isAlias());
    assertSame(host, mappedHost.object);
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost, mappedHost3.getRealHost());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addHostAlias(String, String)}.
   * <ul>
   *   <li>Then third element RealHost is second element.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addHostAlias(String, String)}
   */
  @Test
  public void testAddHostAlias_thenThirdElementRealHostIsSecondElement() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Alias", new String[]{"Name"}, new StandardHost());
    mapper.addHost("*.", new String[]{"Name"}, new StandardHost());
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());

    // Act
    mapper.addHostAlias("Name", "Alias");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[1];
    assertEquals("Alias", mappedHost.name);
    MappedHost mappedHost2 = mappedHostArray[2];
    assertEquals("Name", mappedHost2.name);
    assertEquals(3, mappedHostArray.length);
    assertTrue(mappedHost2.isAlias());
    assertSame(mappedHost, mappedHost.getRealHost());
    assertSame(mappedHost, mappedHost2.getRealHost());
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"Alias"}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    Collection<MappedHost> aliases = (mappedHostArray[0]).getAliases();
    assertTrue(aliases instanceof List);
    assertEquals(1, mappedHostArray.length);
    assertTrue(aliases.isEmpty());
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias2() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Host Name", host, "*.", "1.0.2", context, new String[]{"*."}, resources,
        new ArrayList<>());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    Collection<MappedHost> aliases = (mappedHostArray[0]).getAliases();
    assertTrue(aliases instanceof List);
    assertEquals(1, mappedHostArray.length);
    assertTrue(aliases.isEmpty());
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias3() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Host Name", host, "Alias", "*.", context, new String[]{"*."}, resources,
        new ArrayList<>());
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(3, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>Then array length is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_thenArrayLengthIsFour() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{""}, new StandardHost());
    mapper.addHost("Alias", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(4, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>Then array length is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_thenArrayLengthIsThree() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("Alias", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(3, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>Then array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_thenArrayLengthIsTwo() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Alias", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(2, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>Then array length is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_thenArrayLengthIsTwo2() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(2, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>Then first element Aliases {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_thenFirstElementAliasesList() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    Collection<MappedHost> aliases = (mappedHostArray[0]).getAliases();
    assertTrue(aliases instanceof List);
    assertEquals(1, mappedHostArray.length);
    assertTrue(aliases.isEmpty());
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>When {@code Alias}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_whenAlias_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHostAlias("Alias");

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_whenAsteriskDot_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHostAlias("*.");

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#removeHostAlias(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#removeHostAlias(String)}
   */
  @Test
  public void testRemoveHostAlias_whenNull_thenArrayLengthIsZero() {
    // Arrange
    Mapper mapper = new Mapper();

    // Act
    mapper.removeHostAlias(null);

    // Assert that nothing has changed
    assertEquals(0, mapper.hosts.length);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();
    StandardWrapper wrapper = new StandardWrapper();
    wrappers.add(new WrapperMappingInfo("*.", wrapper, true, true));

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[0]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    MappedWrapper[] mappedWrapperArray = (contextVersionArray[0]).extensionWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    assertEquals("", mappedWrapper.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, mappedWrapperArray.length);
    assertEquals(1, contextVersionArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, mappedWrapper.object);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion2() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();
    StandardWrapper wrapper = new StandardWrapper();
    wrappers.add(new WrapperMappingInfo("*.", wrapper, true, true));
    wrappers.add(new WrapperMappingInfo("*.", new StandardWrapper(), true, true));

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[0]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    MappedWrapper[] mappedWrapperArray = (contextVersionArray[0]).extensionWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    assertEquals("", mappedWrapper.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, mappedWrapperArray.length);
    assertEquals(1, contextVersionArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, mappedWrapper.object);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion3() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();
    StandardWrapper wrapper = new StandardWrapper();
    wrappers.add(new WrapperMappingInfo("Host Name", wrapper, true, true));

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[0]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    MappedWrapper[] mappedWrapperArray = (contextVersionArray[0]).exactWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    assertEquals("Host Name", mappedWrapper.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, mappedWrapperArray.length);
    assertEquals(1, contextVersionArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, mappedWrapper.object);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion4() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();
    StandardWrapper wrapper = new StandardWrapper();
    wrappers.add(new WrapperMappingInfo("/*", wrapper, true, true));

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[0]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    MappedWrapper[] mappedWrapperArray = (contextVersionArray[0]).wildcardWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    assertEquals("", mappedWrapper.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, mappedWrapperArray.length);
    assertEquals(1, contextVersionArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, mappedWrapper.object);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion5() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();
    StandardWrapper wrapper = new StandardWrapper();
    wrappers.add(new WrapperMappingInfo("", wrapper, true, true));

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[0]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    MappedWrapper[] mappedWrapperArray = (contextVersionArray[0]).exactWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    assertEquals("/", mappedWrapper.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, mappedWrapperArray.length);
    assertEquals(1, contextVersionArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, mappedWrapper.object);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then array length is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenArrayLengthIsFour() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Name", new String[]{"Aliases"}, new StandardHost());
    mapper.addHost("Host Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    assertEquals(".", mappedHost.name);
    assertEquals("Host Name", mappedHost.getRealHostName());
    assertEquals(0, mappedHost.contextList.nesting);
    assertEquals(4, mappedHostArray.length);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then first element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenFirstElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"Host Name"}, new StandardHost());
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals(".", mappedHost.getRealHostName());
    assertEquals(".", mappedHost.name);
    ContextList contextList = mappedHost.contextList;
    assertEquals(0, contextList.contexts.length);
    assertEquals(0, contextList.nesting);
    assertEquals(2, mappedHostArray.length);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then first element RealHostName is {@code Host Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenFirstElementRealHostNameIsHostName() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("Host Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert that nothing has changed
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    assertEquals(".", mappedHost.name);
    assertEquals("Host Name", mappedHost.getRealHostName());
    assertEquals(0, mappedHost.contextList.nesting);
    assertEquals(2, mappedHostArray.length);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then not second element Alias.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenNotSecondElementAlias() {
    // Arrange
    Mapper mapper = new Mapper();
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    assertEquals(2, mappedHostArray.length);
    MappedHost mappedHost = mappedHostArray[1];
    assertFalse(mappedHost.isAlias());
    assertSame(host, mappedHost.object);
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then second element {@link MapElement#name} is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenSecondElementNameIs102() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Host Name", host, "Path", "*.", context, new String[]{"*."}, resources,
        new ArrayList<>());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());
    StandardHost host2 = new StandardHost();
    StandardContext context2 = new StandardContext();
    String[] welcomeResources = new String[]{"Welcome Resources"};
    ExtractingRoot resources2 = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host2, "Path", "1.0.2", context2, welcomeResources, resources2,
        new ArrayList<>());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[1]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    ContextVersion contextVersion = contextVersionArray[1];
    assertEquals("1.0.2", contextVersion.name);
    assertEquals("Path", contextVersion.path);
    assertNull(contextVersion.defaultWrapper);
    assertEquals(0, contextVersion.exactWrappers.length);
    assertEquals(0, contextVersion.extensionWrappers.length);
    assertEquals(0, contextVersion.wildcardWrappers.length);
    assertEquals(0, contextVersion.nesting);
    assertEquals(0, contextVersion.slashCount);
    assertEquals(1, mappedContextArray.length);
    assertEquals(2, mappedHostArray.length);
    assertEquals(2, contextVersionArray.length);
    assertFalse(contextVersion.isPaused());
    assertSame(context2, contextVersion.object);
    assertSame(resources2, contextVersion.resources);
    assertSame(welcomeResources, contextVersion.welcomeResources);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then second element {@link MapElement#object} {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenSecondElementObjectStandardContext() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    mapper.addContextVersion("Host Name", host, "Path", "Host Name", context, new String[]{"*."}, resources,
        new ArrayList<>());
    mapper.addHost("Host Name", new String[]{"*."}, new StandardHost());
    mapper.addHost("*.", new String[]{"*."}, new StandardHost());
    StandardHost host2 = new StandardHost();
    StandardContext context2 = new StandardContext();
    ExtractingRoot resources2 = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host2, "Path", "1.0.2", context2, new String[]{"Welcome Resources"},
        resources2, new ArrayList<>());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedContext[] mappedContextArray = (mappedHostArray[1]).contextList.contexts;
    ContextVersion[] contextVersionArray = (mappedContextArray[0]).versions;
    ContextVersion contextVersion = contextVersionArray[1];
    assertTrue(contextVersion.object instanceof StandardContext);
    assertTrue(contextVersion.resources instanceof ExtractingRoot);
    assertEquals("Host Name", contextVersion.name);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, contextVersion.welcomeResources.length);
    assertEquals(2, mappedHostArray.length);
    assertEquals(2, contextVersionArray.length);
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>Then third element Aliases size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_thenThirdElementAliasesSizeIsOne() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    mapper.addHost("Name", new String[]{"*."}, host);
    StandardHost host2 = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host2, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[2];
    Collection<MappedHost> aliases = mappedHost.getAliases();
    assertEquals(1, aliases.size());
    assertTrue(aliases instanceof List);
    assertEquals("Name", mappedHost.getRealHostName());
    assertEquals("Name", mappedHost.name);
    assertEquals(3, mappedHostArray.length);
    assertFalse(mappedHost.isAlias());
    assertSame(host, mappedHost.object);
    MappedHost mappedHost2 = mappedHostArray[0];
    assertSame(mappedHost2, ((List<MappedHost>) aliases).get(0));
    assertSame(mappedHost, mappedHost2.getRealHost());
    assertSame(mappedHost, mappedHost.getRealHost());
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then first element {@link MapElement#name} is {@code Host Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_whenArrayList_thenFirstElementNameIsHostName() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();

    // Act
    mapper.addContextVersion("Host Name", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    assertEquals("Host Name", mappedHost.getRealHostName());
    assertEquals("Host Name", mappedHost.name);
    assertEquals(0, mappedHost.contextList.nesting);
    assertEquals(1, mappedHostArray.length);
    assertEquals(wrappers, mappedHost.getAliases());
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>When {@code *.}.</li>
   *   <li>Then first element RealHostName is {@code .}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_whenAsteriskDot_thenFirstElementRealHostNameIsDot() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();
    ArrayList<WrapperMappingInfo> wrappers = new ArrayList<>();

    // Act
    mapper.addContextVersion("*.", host, "Path", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        wrappers);

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    MappedHost mappedHost = mappedHostArray[0];
    assertEquals(".", mappedHost.getRealHostName());
    assertEquals(".", mappedHost.name);
    assertEquals(0, mappedHost.contextList.nesting);
    assertEquals(1, mappedHostArray.length);
    assertEquals(wrappers, mappedHost.getAliases());
  }

  /**
   * Test {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then first element {@link ContextVersion#path} is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Mapper#addContextVersion(String, Host, String, String, Context, String[], WebResourceRoot, Collection)}
   */
  @Test
  public void testAddContextVersion_whenSlash_thenFirstElementPathIsSlash() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardHost host = new StandardHost();
    StandardContext context = new StandardContext();
    ExtractingRoot resources = new ExtractingRoot();

    // Act
    mapper.addContextVersion("Host Name", host, "/", "1.0.2", context, new String[]{"Welcome Resources"}, resources,
        new ArrayList<>());

    // Assert
    MappedHost[] mappedHostArray = mapper.hosts;
    ContextList contextList = (mappedHostArray[0]).contextList;
    MappedContext[] mappedContextArray = contextList.contexts;
    MappedContext mappedContext = mappedContextArray[0];
    ContextVersion[] contextVersionArray = mappedContext.versions;
    ContextVersion contextVersion = contextVersionArray[0];
    assertEquals("/", contextVersion.path);
    assertEquals("/", mappedContext.name);
    assertEquals(1, mappedHostArray.length);
    assertEquals(1, mappedContextArray.length);
    assertEquals(1, contextVersionArray.length);
    assertEquals(1, contextList.nesting);
    assertEquals(1, contextVersion.slashCount);
  }

  /**
   * Test {@link Mapper#addWrapper(ContextVersion, String, Wrapper, boolean, boolean)} with {@code context}, {@code path}, {@code wrapper}, {@code jspWildCard}, {@code resourceOnly}.
   * <p>
   * Method under test: {@link Mapper#addWrapper(ContextVersion, String, Wrapper, boolean, boolean)}
   */
  @Test
  public void testAddWrapperWithContextPathWrapperJspWildCardResourceOnly() {
    // Arrange
    Mapper mapper = new Mapper();
    StandardContext context = new StandardContext();
    ContextVersion context2 = new ContextVersion("1.0.2", "Path", 3, context, new ExtractingRoot(),
        new String[]{"Welcome Resources"});

    StandardWrapper wrapper = new StandardWrapper();

    // Act
    mapper.addWrapper(context2, "Path", wrapper, true, true);

    // Assert
    MappedWrapper[] mappedWrapperArray = context2.exactWrappers;
    MappedWrapper mappedWrapper = mappedWrapperArray[0];
    Wrapper wrapper2 = mappedWrapper.object;
    assertTrue(wrapper2 instanceof StandardWrapper);
    assertEquals("Path", mappedWrapper.name);
    assertEquals(1, mappedWrapperArray.length);
    assertTrue(mappedWrapper.jspWildCard);
    assertTrue(mappedWrapper.resourceOnly);
    assertSame(wrapper, wrapper2);
  }

  /**
   * Test new {@link Mapper} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Mapper}
   */
  @Test
  public void testNewMapper() {
    // Arrange, Act and Assert
    assertEquals(0, (new Mapper()).hosts.length);
  }
}
