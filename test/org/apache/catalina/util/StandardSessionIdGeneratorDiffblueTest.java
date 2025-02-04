package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class StandardSessionIdGeneratorDiffblueTest {
  /**
   * Test {@link StandardSessionIdGenerator#generateSessionId(String)} with {@code String}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionIdGenerator#generateSessionId(String)}
   */
  @Test
  public void testGenerateSessionIdWithString_thenReturnEmptyString() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
    standardSessionIdGenerator.setSessionIdLength(0);
    standardSessionIdGenerator.setJvmRoute(null);

    // Act and Assert
    assertEquals("", standardSessionIdGenerator.generateSessionId(null));
  }

  /**
   * Test {@link StandardSessionIdGenerator#generateSessionId(String)} with {@code String}.
   * <ul>
   *   <li>Then return {@code .foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionIdGenerator#generateSessionId(String)}
   */
  @Test
  public void testGenerateSessionIdWithString_thenReturnFoo() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
    standardSessionIdGenerator.setSessionIdLength(0);
    standardSessionIdGenerator.setJvmRoute("foo");

    // Act and Assert
    assertEquals(".foo", standardSessionIdGenerator.generateSessionId(null));
  }

  /**
   * Test new {@link StandardSessionIdGenerator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardSessionIdGenerator}
   */
  @Test
  public void testNewStandardSessionIdGenerator() {
    // Arrange and Act
    StandardSessionIdGenerator actualStandardSessionIdGenerator = new StandardSessionIdGenerator();

    // Assert
    assertEquals("", actualStandardSessionIdGenerator.getJvmRoute());
    assertEquals("NEW", actualStandardSessionIdGenerator.getStateName());
    assertNull(actualStandardSessionIdGenerator.getSecureRandomClass());
    assertNull(actualStandardSessionIdGenerator.getSecureRandomProvider());
    assertEquals(0, actualStandardSessionIdGenerator.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualStandardSessionIdGenerator.getState());
    assertTrue(actualStandardSessionIdGenerator.getThrowOnFailure());
    assertEquals(Short.SIZE, actualStandardSessionIdGenerator.getSessionIdLength());
    assertEquals(SessionIdGeneratorBase.DEFAULT_SECURE_RANDOM_ALGORITHM,
        actualStandardSessionIdGenerator.getSecureRandomAlgorithm());
  }
}
