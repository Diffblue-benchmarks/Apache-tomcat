package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class StandardWrapperValveDiffblueTest {
  /**
   * Test new {@link StandardWrapperValve} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardWrapperValve}
   */
  @Test
  public void testNewStandardWrapperValve() {
    // Arrange and Act
    StandardWrapperValve actualStandardWrapperValve = new StandardWrapperValve();

    // Assert
    assertEquals("Catalina", actualStandardWrapperValve.getDomain());
    assertEquals("NEW", actualStandardWrapperValve.getStateName());
    assertNull(actualStandardWrapperValve.getDomainInternal());
    assertNull(actualStandardWrapperValve.getObjectName());
    assertNull(actualStandardWrapperValve.getContainer());
    assertNull(actualStandardWrapperValve.getNext());
    assertEquals(0, actualStandardWrapperValve.findLifecycleListeners().length);
    assertEquals(0L, actualStandardWrapperValve.getErrorCount());
    assertEquals(0L, actualStandardWrapperValve.getMaxTime());
    assertEquals(0L, actualStandardWrapperValve.getProcessingTime());
    assertEquals(0L, actualStandardWrapperValve.getRequestCount());
    assertEquals(LifecycleState.NEW, actualStandardWrapperValve.getState());
    assertTrue(actualStandardWrapperValve.getThrowOnFailure());
    assertTrue(actualStandardWrapperValve.isAsyncSupported());
    assertEquals(Long.MAX_VALUE, actualStandardWrapperValve.getMinTime());
  }

  /**
   * Test {@link StandardWrapperValve#getProcessingTime()}.
   * <p>
   * Method under test: {@link StandardWrapperValve#getProcessingTime()}
   */
  @Test
  public void testGetProcessingTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapperValve()).getProcessingTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardWrapperValve#initInternal()}
   *   <li>{@link StandardWrapperValve#getMaxTime()}
   *   <li>{@link StandardWrapperValve#getMinTime()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws LifecycleException {
    // Arrange
    StandardWrapperValve standardWrapperValve = new StandardWrapperValve();

    // Act
    standardWrapperValve.initInternal();
    long actualMaxTime = standardWrapperValve.getMaxTime();

    // Assert
    assertEquals(0L, actualMaxTime);
    assertEquals(Long.MAX_VALUE, standardWrapperValve.getMinTime());
  }

  /**
   * Test {@link StandardWrapperValve#getRequestCount()}.
   * <p>
   * Method under test: {@link StandardWrapperValve#getRequestCount()}
   */
  @Test
  public void testGetRequestCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapperValve()).getRequestCount());
  }

  /**
   * Test {@link StandardWrapperValve#getErrorCount()}.
   * <p>
   * Method under test: {@link StandardWrapperValve#getErrorCount()}
   */
  @Test
  public void testGetErrorCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new StandardWrapperValve()).getErrorCount());
  }

  /**
   * Test {@link StandardWrapperValve#incrementErrorCount()}.
   * <p>
   * Method under test: {@link StandardWrapperValve#incrementErrorCount()}
   */
  @Test
  public void testIncrementErrorCount() {
    // Arrange
    StandardWrapperValve standardWrapperValve = new StandardWrapperValve();

    // Act
    standardWrapperValve.incrementErrorCount();

    // Assert
    assertEquals(1L, standardWrapperValve.getErrorCount());
  }
}
