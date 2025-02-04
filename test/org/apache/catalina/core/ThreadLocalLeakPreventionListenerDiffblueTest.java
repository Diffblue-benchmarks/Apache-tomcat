package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerEvent;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.junit.Test;

public class ThreadLocalLeakPreventionListenerDiffblueTest {
  /**
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#containerEvent(ContainerEvent)}
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
   * Test {@link ThreadLocalLeakPreventionListener#createLifecycleListener(Context)}.
   * <p>
   * Method under test: {@link ThreadLocalLeakPreventionListener#createLifecycleListener(Context)}
   */
  @Test
  public void testCreateLifecycleListener() {
    // Arrange
    ThreadLocalLeakPreventionListener threadLocalLeakPreventionListener = new ThreadLocalLeakPreventionListener();
    StandardContext context = new StandardContext();

    // Act
    LifecycleListener actualCreateLifecycleListenerResult = threadLocalLeakPreventionListener
        .createLifecycleListener(context);
    actualCreateLifecycleListenerResult.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert
    assertTrue(actualCreateLifecycleListenerResult instanceof ThreadLocalLeakPreventionListener);
    assertTrue(context.children.isEmpty());
    assertTrue(((ThreadLocalLeakPreventionListener) actualCreateLifecycleListenerResult).contextListeners.isEmpty());
  }

  /**
   * Test new {@link ThreadLocalLeakPreventionListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ThreadLocalLeakPreventionListener}
   */
  @Test
  public void testNewThreadLocalLeakPreventionListener() {
    // Arrange, Act and Assert
    assertTrue((new ThreadLocalLeakPreventionListener()).contextListeners.isEmpty());
  }
}
