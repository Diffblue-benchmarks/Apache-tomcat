package org.apache.catalina.core;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ContextNamingInfoListenerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ContextNamingInfoListener}
   *   <li>{@link ContextNamingInfoListener#setEmptyOnRoot(boolean)}
   *   <li>{@link ContextNamingInfoListener#isEmptyOnRoot()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ContextNamingInfoListener actualContextNamingInfoListener = new ContextNamingInfoListener();
    actualContextNamingInfoListener.setEmptyOnRoot(true);

    // Assert
    assertTrue(actualContextNamingInfoListener.isEmptyOnRoot());
  }
}
