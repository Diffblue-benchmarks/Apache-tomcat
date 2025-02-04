package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TooManyActiveSessionsExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TooManyActiveSessionsException#TooManyActiveSessionsException(String, int)}
   *   <li>{@link TooManyActiveSessionsException#getMaxActiveSessions()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    TooManyActiveSessionsException actualTooManyActiveSessionsException = new TooManyActiveSessionsException(
        "An error occurred", 3);
    int actualMaxActiveSessions = actualTooManyActiveSessionsException.getMaxActiveSessions();

    // Assert
    assertEquals("An error occurred", actualTooManyActiveSessionsException.getMessage());
    assertNull(actualTooManyActiveSessionsException.getCause());
    assertEquals(0, actualTooManyActiveSessionsException.getSuppressed().length);
    assertEquals(3, actualMaxActiveSessions);
  }
}
