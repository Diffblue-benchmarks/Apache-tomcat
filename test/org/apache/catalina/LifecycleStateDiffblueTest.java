package org.apache.catalina;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class LifecycleStateDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LifecycleState#getLifecycleEvent()}
   *   <li>{@link LifecycleState#isAvailable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    LifecycleState valueOfResult = LifecycleState.valueOf("NEW");

    // Act
    String actualLifecycleEvent = valueOfResult.getLifecycleEvent();

    // Assert
    assertNull(actualLifecycleEvent);
    assertFalse(valueOfResult.isAvailable());
  }
}
