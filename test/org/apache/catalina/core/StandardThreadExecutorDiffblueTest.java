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

public class StandardThreadExecutorDiffblueTest {
  /**
   * Test new {@link StandardThreadExecutor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardThreadExecutor}
   */
  @Test
  public void testNewStandardThreadExecutor() {
    // Arrange and Act
    StandardThreadExecutor actualStandardThreadExecutor = new StandardThreadExecutor();

    // Assert
    assertEquals("Catalina", actualStandardThreadExecutor.getDomain());
    assertEquals("NEW", actualStandardThreadExecutor.getStateName());
    assertEquals("tomcat-exec-", actualStandardThreadExecutor.getNamePrefix());
    assertEquals("type=Executor,name=null", actualStandardThreadExecutor.getObjectNameKeyProperties());
    assertNull(actualStandardThreadExecutor.getDomainInternal());
    assertNull(actualStandardThreadExecutor.getName());
    assertNull(actualStandardThreadExecutor.getObjectName());
    assertNull(actualStandardThreadExecutor.executor);
    assertEquals(-1, actualStandardThreadExecutor.getQueueSize());
    assertEquals(0, actualStandardThreadExecutor.getActiveCount());
    assertEquals(0, actualStandardThreadExecutor.getCorePoolSize());
    assertEquals(0, actualStandardThreadExecutor.getLargestPoolSize());
    assertEquals(0, actualStandardThreadExecutor.getPoolSize());
    assertEquals(0, actualStandardThreadExecutor.findLifecycleListeners().length);
    assertEquals(0L, actualStandardThreadExecutor.getCompletedTaskCount());
    assertEquals(1000L, actualStandardThreadExecutor.getThreadRenewalDelay());
    assertEquals(200, actualStandardThreadExecutor.getMaxThreads());
    assertEquals(25, actualStandardThreadExecutor.getMinSpareThreads());
    assertEquals(5, actualStandardThreadExecutor.getThreadPriority());
    assertEquals(60000, actualStandardThreadExecutor.getMaxIdleTime());
    assertEquals(LifecycleState.NEW, actualStandardThreadExecutor.getState());
    assertTrue(actualStandardThreadExecutor.isDaemon());
    assertTrue(actualStandardThreadExecutor.getThrowOnFailure());
    assertEquals(Integer.MAX_VALUE, actualStandardThreadExecutor.getMaxQueueSize());
  }

  /**
   * Test {@link StandardThreadExecutor#execute(Runnable)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#execute(Runnable)}
   */
  @Test
  public void testExecute() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardThreadExecutor.execute(new NioReceiver()));
  }

  /**
   * Test {@link StandardThreadExecutor#setMaxIdleTime(int)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#setMaxIdleTime(int)}
   */
  @Test
  public void testSetMaxIdleTime() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act
    standardThreadExecutor.setMaxIdleTime(1);

    // Assert
    assertEquals(1, standardThreadExecutor.getMaxIdleTime());
  }

  /**
   * Test {@link StandardThreadExecutor#setMaxThreads(int)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#setMaxThreads(int)}
   */
  @Test
  public void testSetMaxThreads() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act
    standardThreadExecutor.setMaxThreads(3);

    // Assert
    assertEquals(3, standardThreadExecutor.getMaxThreads());
  }

  /**
   * Test {@link StandardThreadExecutor#setMinSpareThreads(int)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#setMinSpareThreads(int)}
   */
  @Test
  public void testSetMinSpareThreads() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act
    standardThreadExecutor.setMinSpareThreads(1);

    // Assert
    assertEquals(1, standardThreadExecutor.getMinSpareThreads());
  }

  /**
   * Test {@link StandardThreadExecutor#setThreadRenewalDelay(long)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#setThreadRenewalDelay(long)}
   */
  @Test
  public void testSetThreadRenewalDelay() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act
    standardThreadExecutor.setThreadRenewalDelay(1L);

    // Assert
    assertEquals(1L, standardThreadExecutor.getThreadRenewalDelay());
  }

  /**
   * Test {@link StandardThreadExecutor#getActiveCount()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getActiveCount()}
   */
  @Test
  public void testGetActiveCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardThreadExecutor()).getActiveCount());
  }

  /**
   * Test {@link StandardThreadExecutor#getCompletedTaskCount()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getCompletedTaskCount()}
   */
  @Test
  public void testGetCompletedTaskCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardThreadExecutor()).getCompletedTaskCount());
  }

  /**
   * Test {@link StandardThreadExecutor#getCorePoolSize()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getCorePoolSize()}
   */
  @Test
  public void testGetCorePoolSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardThreadExecutor()).getCorePoolSize());
  }

  /**
   * Test {@link StandardThreadExecutor#getLargestPoolSize()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getLargestPoolSize()}
   */
  @Test
  public void testGetLargestPoolSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardThreadExecutor()).getLargestPoolSize());
  }

  /**
   * Test {@link StandardThreadExecutor#getPoolSize()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getPoolSize()}
   */
  @Test
  public void testGetPoolSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardThreadExecutor()).getPoolSize());
  }

  /**
   * Test {@link StandardThreadExecutor#getQueueSize()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getQueueSize()}
   */
  @Test
  public void testGetQueueSize() {
    // Arrange, Act and Assert
    assertEquals(-1, (new StandardThreadExecutor()).getQueueSize());
  }

  /**
   * Test {@link StandardThreadExecutor#resizePool(int, int)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#resizePool(int, int)}
   */
  @Test
  public void testResizePool() {
    // Arrange, Act and Assert
    assertFalse((new StandardThreadExecutor()).resizePool(3, 3));
  }

  /**
   * Test {@link StandardThreadExecutor#resizeQueue(int)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#resizeQueue(int)}
   */
  @Test
  public void testResizeQueue() {
    // Arrange, Act and Assert
    assertFalse((new StandardThreadExecutor()).resizeQueue(3));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardThreadExecutor#setDaemon(boolean)}
   *   <li>{@link StandardThreadExecutor#setMaxQueueSize(int)}
   *   <li>{@link StandardThreadExecutor#setName(String)}
   *   <li>{@link StandardThreadExecutor#setNamePrefix(String)}
   *   <li>{@link StandardThreadExecutor#setThreadPriority(int)}
   *   <li>{@link StandardThreadExecutor#shutdown()}
   *   <li>{@link StandardThreadExecutor#getDomainInternal()}
   *   <li>{@link StandardThreadExecutor#getMaxIdleTime()}
   *   <li>{@link StandardThreadExecutor#getMaxQueueSize()}
   *   <li>{@link StandardThreadExecutor#getMaxThreads()}
   *   <li>{@link StandardThreadExecutor#getMinSpareThreads()}
   *   <li>{@link StandardThreadExecutor#getName()}
   *   <li>{@link StandardThreadExecutor#getNamePrefix()}
   *   <li>{@link StandardThreadExecutor#getThreadPriority()}
   *   <li>{@link StandardThreadExecutor#getThreadRenewalDelay()}
   *   <li>{@link StandardThreadExecutor#isDaemon()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act
    standardThreadExecutor.setDaemon(true);
    standardThreadExecutor.setMaxQueueSize(3);
    standardThreadExecutor.setName("Name");
    standardThreadExecutor.setNamePrefix("Name Prefix");
    standardThreadExecutor.setThreadPriority(1);
    standardThreadExecutor.shutdown();
    String actualDomainInternal = standardThreadExecutor.getDomainInternal();
    int actualMaxIdleTime = standardThreadExecutor.getMaxIdleTime();
    int actualMaxQueueSize = standardThreadExecutor.getMaxQueueSize();
    int actualMaxThreads = standardThreadExecutor.getMaxThreads();
    int actualMinSpareThreads = standardThreadExecutor.getMinSpareThreads();
    String actualName = standardThreadExecutor.getName();
    String actualNamePrefix = standardThreadExecutor.getNamePrefix();
    int actualThreadPriority = standardThreadExecutor.getThreadPriority();
    long actualThreadRenewalDelay = standardThreadExecutor.getThreadRenewalDelay();

    // Assert
    assertEquals("Name Prefix", actualNamePrefix);
    assertEquals("Name", actualName);
    assertNull(actualDomainInternal);
    assertEquals(1, actualThreadPriority);
    assertEquals(1000L, actualThreadRenewalDelay);
    assertEquals(200, actualMaxThreads);
    assertEquals(25, actualMinSpareThreads);
    assertEquals(3, actualMaxQueueSize);
    assertEquals(60000, actualMaxIdleTime);
    assertTrue(standardThreadExecutor.isDaemon());
  }

  /**
   * Test {@link StandardThreadExecutor#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange, Act and Assert
    assertEquals("type=Executor,name=null", (new StandardThreadExecutor()).getObjectNameKeyProperties());
  }

  /**
   * Test {@link StandardThreadExecutor#shutdownNow()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#shutdownNow()}
   */
  @Test
  public void testShutdownNow() {
    // Arrange, Act and Assert
    assertTrue((new StandardThreadExecutor()).shutdownNow().isEmpty());
  }

  /**
   * Test {@link StandardThreadExecutor#isShutdown()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#isShutdown()}
   */
  @Test
  public void testIsShutdown() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardThreadExecutor()).isShutdown());
  }

  /**
   * Test {@link StandardThreadExecutor#isTerminated()}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#isTerminated()}
   */
  @Test
  public void testIsTerminated() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardThreadExecutor()).isTerminated());
  }

  /**
   * Test {@link StandardThreadExecutor#awaitTermination(long, TimeUnit)}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#awaitTermination(long, TimeUnit)}
   */
  @Test
  public void testAwaitTermination() throws InterruptedException {
    // Arrange, Act and Assert
    assertFalse((new StandardThreadExecutor()).awaitTermination(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link StandardThreadExecutor#submit(Runnable)} with {@code Runnable}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#submit(Runnable)}
   */
  @Test
  public void testSubmitWithRunnable() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardThreadExecutor.submit(new NioReceiver()));
  }

  /**
   * Test {@link StandardThreadExecutor#submit(Runnable, Object)} with {@code Runnable}, {@code Object}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#submit(Runnable, Object)}
   */
  @Test
  public void testSubmitWithRunnableObject() {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardThreadExecutor.submit(new NioReceiver(), "Result"));
  }

  /**
   * Test {@link StandardThreadExecutor#invokeAll(Collection)} with {@code tasks}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#invokeAll(Collection)}
   */
  @Test
  public void testInvokeAllWithTasks() throws InterruptedException {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardThreadExecutor.invokeAll(new ArrayList<>()));
  }

  /**
   * Test {@link StandardThreadExecutor#invokeAll(Collection, long, TimeUnit)} with {@code tasks}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#invokeAll(Collection, long, TimeUnit)}
   */
  @Test
  public void testInvokeAllWithTasksTimeoutUnit() throws InterruptedException {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> standardThreadExecutor.invokeAll(new ArrayList<>(), 10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link StandardThreadExecutor#invokeAny(Collection)} with {@code tasks}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#invokeAny(Collection)}
   */
  @Test
  public void testInvokeAnyWithTasks() throws InterruptedException, ExecutionException {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardThreadExecutor.invokeAny(new ArrayList<>()));
  }

  /**
   * Test {@link StandardThreadExecutor#invokeAny(Collection, long, TimeUnit)} with {@code tasks}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link StandardThreadExecutor#invokeAny(Collection, long, TimeUnit)}
   */
  @Test
  public void testInvokeAnyWithTasksTimeoutUnit() throws InterruptedException, ExecutionException, TimeoutException {
    // Arrange
    StandardThreadExecutor standardThreadExecutor = new StandardThreadExecutor();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> standardThreadExecutor.invokeAny(new ArrayList<>(), 10L, TimeUnit.NANOSECONDS));
  }
}
