package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.junit.Test;

public class LifecycleEventDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)}
   *   <li>{@link LifecycleEvent#getData()}
   *   <li>{@link LifecycleEvent#getType()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    BasicAuthenticator lifecycle = new BasicAuthenticator();

    // Act
    LifecycleEvent actualLifecycleEvent = new LifecycleEvent(lifecycle, "Type", "Data");
    Object actualData = actualLifecycleEvent.getData();

    // Assert
    assertEquals("Data", actualData);
    assertEquals("Type", actualLifecycleEvent.getType());
    assertSame(lifecycle, actualLifecycleEvent.getSource());
  }

  /**
   * Test {@link LifecycleEvent#getLifecycle()}.
   * <p>
   * Method under test: {@link LifecycleEvent#getLifecycle()}
   */
  @Test
  public void testGetLifecycle() {
    // Arrange
    BasicAuthenticator lifecycle = new BasicAuthenticator();

    // Act and Assert
    assertSame(lifecycle, (new LifecycleEvent(lifecycle, "Type", "Data")).getLifecycle());
  }
}
