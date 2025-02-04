package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerEvent;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.LifecycleListener;
import org.junit.Test;

public class FrameworkListenerDiffblueTest {
  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.containerEvent(new ContainerEvent(new StandardContext(), "Type", "Data"));

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent2() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.containerEvent(new ContainerEvent(new StandardContext(), "addChild", "Data"));

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent3() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.containerEvent(new ContainerEvent(new StandardContext(), "removeChild", "Data"));

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent4() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext container = new StandardContext();
    StandardContext standardContext = new StandardContext();
    ContainerEvent event = new ContainerEvent(container, "addChild", standardContext);

    // Act
    threadLocalLeakPreventionListener.containerEvent(event);

    // Assert
    Object data = event.getData();
    assertTrue(data instanceof StandardContext);
    assertEquals(1, threadLocalLeakPreventionListener.contextListeners.size());
    LifecycleListener[] findLifecycleListenersResult = ((StandardContext) data).findLifecycleListeners();
    assertEquals(1, findLifecycleListenersResult.length);
    assertSame(threadLocalLeakPreventionListener, findLifecycleListenersResult[0]);
    assertSame(standardContext.children, ((StandardContext) data).children);
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent5() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.containerEvent(new ContainerEvent(new StandardContext(), "addChild", null));

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent6() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext container = new StandardContext();
    StandardEngine standardEngine = new StandardEngine();
    ContainerEvent event = new ContainerEvent(container, "addChild", standardEngine);

    // Act
    threadLocalLeakPreventionListener.containerEvent(event);

    // Assert that nothing has changed
    Object data = event.getData();
    assertTrue(data instanceof StandardEngine);
    assertEquals(0, ((StandardEngine) data).findLifecycleListeners().length);
    HashMap<String, Container> stringContainerMap = ((StandardEngine) data).children;
    assertTrue(stringContainerMap.isEmpty());
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
    assertSame(standardEngine.children, stringContainerMap);
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent7() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext container = new StandardContext();
    StandardHost standardHost = new StandardHost();
    ContainerEvent event = new ContainerEvent(container, "addChild", standardHost);

    // Act
    threadLocalLeakPreventionListener.containerEvent(event);

    // Assert that nothing has changed
    Object data = event.getData();
    assertTrue(data instanceof StandardHost);
    assertEquals(0, ((StandardHost) data).findLifecycleListeners().length);
    HashMap<String, Container> stringContainerMap = ((StandardHost) data).children;
    assertTrue(stringContainerMap.isEmpty());
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
    assertSame(standardHost.children, stringContainerMap);
  }

  /**
   * Test {@link FrameworkListener#containerEvent(ContainerEvent)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameworkListener#containerEvent(ContainerEvent)}
   */
  @Test
  public void testContainerEvent_whenNull() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.containerEvent(null);

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#registerListenersForEngine(Engine)}.
   * <ul>
   *   <li>Then {@link StandardEngine} (default constructor) {@link ContainerBase#children} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameworkListener#registerListenersForEngine(Engine)}
   */
  @Test
  public void testRegisterListenersForEngine_thenStandardEngineChildrenSizeIsOne() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    StandardEngine engine = new StandardEngine();
    StandardHost child = new StandardHost();
    engine.addChild(child);

    // Act
    threadLocalLeakPreventionListener.registerListenersForEngine(engine);

    // Assert
    HashMap<String, Container> stringContainerMap = engine.children;
    assertEquals(1, stringContainerMap.size());
    Container getResult = stringContainerMap.get(null);
    assertTrue(getResult instanceof StandardHost);
    ContainerListener[] findContainerListenersResult = getResult.findContainerListeners();
    assertEquals(1, findContainerListenersResult.length);
    assertSame(threadLocalLeakPreventionListener, findContainerListenersResult[0]);
    assertSame(child.listeners, ((StandardHost) getResult).listeners);
  }

  /**
   * Test {@link FrameworkListener#registerContextListener(Context)}.
   * <p>
   * Method under test: {@link FrameworkListener#registerContextListener(Context)}
   */
  @Test
  public void testRegisterContextListener() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext context = new StandardContext();

    // Act
    threadLocalLeakPreventionListener.registerContextListener(context);

    // Assert
    assertEquals(1, threadLocalLeakPreventionListener.contextListeners.size());
    LifecycleListener[] findLifecycleListenersResult = context.findLifecycleListeners();
    assertEquals(1, findLifecycleListenersResult.length);
    assertSame(threadLocalLeakPreventionListener, findLifecycleListenersResult[0]);
  }

  /**
   * Test {@link FrameworkListener#processContainerAddChild(Container)}.
   * <p>
   * Method under test: {@link FrameworkListener#processContainerAddChild(Container)}
   */
  @Test
  public void testProcessContainerAddChild() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext child = new StandardContext();

    // Act
    threadLocalLeakPreventionListener.processContainerAddChild(child);

    // Assert
    assertEquals(1, threadLocalLeakPreventionListener.contextListeners.size());
    LifecycleListener[] findLifecycleListenersResult = child.findLifecycleListeners();
    assertEquals(1, findLifecycleListenersResult.length);
    assertSame(threadLocalLeakPreventionListener, findLifecycleListenersResult[0]);
  }

  /**
   * Test {@link FrameworkListener#processContainerAddChild(Container)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameworkListener#processContainerAddChild(Container)}
   */
  @Test
  public void testProcessContainerAddChild_whenNull() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();

    // Act
    threadLocalLeakPreventionListener.processContainerAddChild(null);

    // Assert that nothing has changed
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#processContainerAddChild(Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameworkListener#processContainerAddChild(Container)}
   */
  @Test
  public void testProcessContainerAddChild_whenStandardEngine_thenArrayLengthIsZero() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardEngine child = new StandardEngine();

    // Act
    threadLocalLeakPreventionListener.processContainerAddChild(child);

    // Assert that nothing has changed
    assertEquals(0, child.findLifecycleListeners().length);
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }

  /**
   * Test {@link FrameworkListener#processContainerAddChild(Container)}.
   * <ul>
   *   <li>When {@link StandardHost} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameworkListener#processContainerAddChild(Container)}
   */
  @Test
  public void testProcessContainerAddChild_whenStandardHost_thenArrayLengthIsZero() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardHost child = new StandardHost();

    // Act
    threadLocalLeakPreventionListener.processContainerAddChild(child);

    // Assert that nothing has changed
    assertEquals(0, child.findLifecycleListeners().length);
    assertTrue(threadLocalLeakPreventionListener.contextListeners.isEmpty());
  }
}
