package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class StandardVirtualThreadExecutorDiffblueTest {
  /**
   * Test {@link StandardVirtualThreadExecutor#execute(Runnable)}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#execute(Runnable)}
   */
  @Test
  public void testExecute() {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardVirtualThreadExecutor.execute(new NioReceiver()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardVirtualThreadExecutor#setName(String)}
   *   <li>{@link StandardVirtualThreadExecutor#setNamePrefix(String)}
   *   <li>{@link StandardVirtualThreadExecutor#shutdown()}
   *   <li>{@link StandardVirtualThreadExecutor#getDomainInternal()}
   *   <li>{@link StandardVirtualThreadExecutor#getName()}
   *   <li>{@link StandardVirtualThreadExecutor#getNamePrefix()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act
    standardVirtualThreadExecutor.setName("Name");
    standardVirtualThreadExecutor.setNamePrefix("Name Prefix");
    standardVirtualThreadExecutor.shutdown();
    String actualDomainInternal = standardVirtualThreadExecutor.getDomainInternal();
    String actualName = standardVirtualThreadExecutor.getName();

    // Assert
    assertEquals("Name Prefix", standardVirtualThreadExecutor.getNamePrefix());
    assertEquals("Name", actualName);
    assertNull(actualDomainInternal);
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange, Act and Assert
    assertEquals("type=Executor,name=null", (new StandardVirtualThreadExecutor()).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#shutdownNow()}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#shutdownNow()}
   */
  @Test
  public void testShutdownNow() {
    // Arrange, Act and Assert
    assertTrue((new StandardVirtualThreadExecutor()).shutdownNow().isEmpty());
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#isShutdown()}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#isShutdown()}
   */
  @Test
  public void testIsShutdown() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardVirtualThreadExecutor()).isShutdown());
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#isTerminated()}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#isTerminated()}
   */
  @Test
  public void testIsTerminated() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardVirtualThreadExecutor()).isTerminated());
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#awaitTermination(long, TimeUnit)}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#awaitTermination(long, TimeUnit)}
   */
  @Test
  public void testAwaitTermination() throws InterruptedException {
    // Arrange, Act and Assert
    assertFalse((new StandardVirtualThreadExecutor()).awaitTermination(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#submit(Runnable)} with {@code Runnable}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#submit(Runnable)}
   */
  @Test
  public void testSubmitWithRunnable() {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardVirtualThreadExecutor.submit(new NioReceiver()));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#submit(Runnable, Object)} with {@code Runnable}, {@code Object}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#submit(Runnable, Object)}
   */
  @Test
  public void testSubmitWithRunnableObject() {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardVirtualThreadExecutor.submit(new NioReceiver(), "Result"));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#invokeAll(Collection)} with {@code tasks}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#invokeAll(Collection)}
   */
  @Test
  public void testInvokeAllWithTasks() throws InterruptedException {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardVirtualThreadExecutor.invokeAll(new ArrayList<>()));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#invokeAll(Collection, long, TimeUnit)} with {@code tasks}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#invokeAll(Collection, long, TimeUnit)}
   */
  @Test
  public void testInvokeAllWithTasksTimeoutUnit() throws InterruptedException {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> standardVirtualThreadExecutor.invokeAll(new ArrayList<>(), 10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#invokeAny(Collection)} with {@code tasks}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#invokeAny(Collection)}
   */
  @Test
  public void testInvokeAnyWithTasks() throws InterruptedException, ExecutionException {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardVirtualThreadExecutor.invokeAny(new ArrayList<>()));
  }

  /**
   * Test {@link StandardVirtualThreadExecutor#invokeAny(Collection, long, TimeUnit)} with {@code tasks}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link StandardVirtualThreadExecutor#invokeAny(Collection, long, TimeUnit)}
   */
  @Test
  public void testInvokeAnyWithTasksTimeoutUnit() throws InterruptedException, ExecutionException, TimeoutException {
    // Arrange
    StandardVirtualThreadExecutor standardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> standardVirtualThreadExecutor.invokeAny(new ArrayList<>(), 10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test new {@link StandardVirtualThreadExecutor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardVirtualThreadExecutor}
   */
  @Test
  public void testNewStandardVirtualThreadExecutor() {
    // Arrange and Act
    StandardVirtualThreadExecutor actualStandardVirtualThreadExecutor = new StandardVirtualThreadExecutor();

    // Assert
    assertEquals("Catalina", actualStandardVirtualThreadExecutor.getDomain());
    assertEquals("NEW", actualStandardVirtualThreadExecutor.getStateName());
    assertEquals("tomcat-virt-", actualStandardVirtualThreadExecutor.getNamePrefix());
    assertEquals("type=Executor,name=null", actualStandardVirtualThreadExecutor.getObjectNameKeyProperties());
    assertNull(actualStandardVirtualThreadExecutor.getDomainInternal());
    assertNull(actualStandardVirtualThreadExecutor.getName());
    assertNull(actualStandardVirtualThreadExecutor.getObjectName());
    assertEquals(0, actualStandardVirtualThreadExecutor.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualStandardVirtualThreadExecutor.getState());
    assertTrue(actualStandardVirtualThreadExecutor.getThrowOnFailure());
  }
}
