package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardEngine;
import org.junit.Test;

public class EngineConfigDiffblueTest {
  /**
   * Test {@link EngineConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link EngineConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    EngineConfig engineConfig = new EngineConfig();

    // Act
    engineConfig.lifecycleEvent(new LifecycleEvent(new StandardEngine(), "start", "Data"));

    // Assert
    assertTrue(engineConfig.engine instanceof StandardEngine);
  }

  /**
   * Test {@link EngineConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link EngineConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    EngineConfig engineConfig = new EngineConfig();

    // Act
    engineConfig.lifecycleEvent(new LifecycleEvent(new StandardEngine(), "stop", "Data"));

    // Assert
    assertTrue(engineConfig.engine instanceof StandardEngine);
  }

  /**
   * Test {@link EngineConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link EngineConfig} (default constructor) {@link EngineConfig#engine} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EngineConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenEngineConfigEngineIsNull() {
    // Arrange
    EngineConfig engineConfig = new EngineConfig();

    // Act
    engineConfig.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(engineConfig.engine);
  }

  /**
   * Test {@link EngineConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)} with lifecycle is {@link StandardEngine} (default constructor) and {@code Type} and {@code Data}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EngineConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenLifecycleEventWithLifecycleIsStandardEngineAndTypeAndData() {
    // Arrange
    EngineConfig engineConfig = new EngineConfig();

    // Act
    engineConfig.lifecycleEvent(new LifecycleEvent(new StandardEngine(), "Type", "Data"));

    // Assert
    assertTrue(engineConfig.engine instanceof StandardEngine);
  }

  /**
   * Test new {@link EngineConfig} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link EngineConfig}
   */
  @Test
  public void testNewEngineConfig() {
    // Arrange, Act and Assert
    assertNull((new EngineConfig()).engine);
  }
}
