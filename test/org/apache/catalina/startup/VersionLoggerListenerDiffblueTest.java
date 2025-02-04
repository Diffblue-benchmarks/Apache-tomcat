package org.apache.catalina.startup;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VersionLoggerListenerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link VersionLoggerListener}
   *   <li>{@link VersionLoggerListener#setLogArgs(boolean)}
   *   <li>{@link VersionLoggerListener#setLogEnv(boolean)}
   *   <li>{@link VersionLoggerListener#setLogProps(boolean)}
   *   <li>{@link VersionLoggerListener#getLogArgs()}
   *   <li>{@link VersionLoggerListener#getLogEnv()}
   *   <li>{@link VersionLoggerListener#getLogProps()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    VersionLoggerListener actualVersionLoggerListener = new VersionLoggerListener();
    actualVersionLoggerListener.setLogArgs(true);
    actualVersionLoggerListener.setLogEnv(true);
    actualVersionLoggerListener.setLogProps(true);
    boolean actualLogArgs = actualVersionLoggerListener.getLogArgs();
    boolean actualLogEnv = actualVersionLoggerListener.getLogEnv();

    // Assert
    assertTrue(actualLogArgs);
    assertTrue(actualLogEnv);
    assertTrue(actualVersionLoggerListener.getLogProps());
  }
}
