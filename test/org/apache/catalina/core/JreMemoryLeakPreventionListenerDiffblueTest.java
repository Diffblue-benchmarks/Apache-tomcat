package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class JreMemoryLeakPreventionListenerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link JreMemoryLeakPreventionListener}
   *   <li>{@link JreMemoryLeakPreventionListener#setAppContextProtection(boolean)}
   *   <li>{@link JreMemoryLeakPreventionListener#setClassesToInitialize(String)}
   *   <li>{@link JreMemoryLeakPreventionListener#setDriverManagerProtection(boolean)}
   *   <li>{@link JreMemoryLeakPreventionListener#setInitSeedGenerator(boolean)}
   *   <li>{@link JreMemoryLeakPreventionListener#setUrlCacheProtection(boolean)}
   *   <li>{@link JreMemoryLeakPreventionListener#getClassesToInitialize()}
   *   <li>{@link JreMemoryLeakPreventionListener#getInitSeedGenerator()}
   *   <li>{@link JreMemoryLeakPreventionListener#isAppContextProtection()}
   *   <li>{@link JreMemoryLeakPreventionListener#isDriverManagerProtection()}
   *   <li>{@link JreMemoryLeakPreventionListener#isUrlCacheProtection()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    JreMemoryLeakPreventionListener actualJreMemoryLeakPreventionListener = new JreMemoryLeakPreventionListener();
    actualJreMemoryLeakPreventionListener.setAppContextProtection(true);
    actualJreMemoryLeakPreventionListener.setClassesToInitialize("Classes To Initialize");
    actualJreMemoryLeakPreventionListener.setDriverManagerProtection(true);
    actualJreMemoryLeakPreventionListener.setInitSeedGenerator(true);
    actualJreMemoryLeakPreventionListener.setUrlCacheProtection(true);
    String actualClassesToInitialize = actualJreMemoryLeakPreventionListener.getClassesToInitialize();
    boolean actualInitSeedGenerator = actualJreMemoryLeakPreventionListener.getInitSeedGenerator();
    boolean actualIsAppContextProtectionResult = actualJreMemoryLeakPreventionListener.isAppContextProtection();
    boolean actualIsDriverManagerProtectionResult = actualJreMemoryLeakPreventionListener.isDriverManagerProtection();

    // Assert
    assertEquals("Classes To Initialize", actualClassesToInitialize);
    assertTrue(actualInitSeedGenerator);
    assertTrue(actualIsAppContextProtectionResult);
    assertTrue(actualIsDriverManagerProtectionResult);
    assertTrue(actualJreMemoryLeakPreventionListener.isUrlCacheProtection());
  }
}
