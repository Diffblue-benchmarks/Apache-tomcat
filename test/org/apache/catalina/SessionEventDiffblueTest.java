package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class SessionEventDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SessionEvent#SessionEvent(Session, String, Object)}
   *   <li>{@link SessionEvent#toString()}
   *   <li>{@link SessionEvent#getData()}
   *   <li>{@link SessionEvent#getSession()}
   *   <li>{@link SessionEvent#getType()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DeltaSession session = new DeltaSession();

    // Act
    SessionEvent actualSessionEvent = new SessionEvent(session, "Type", "Data");
    String actualToStringResult = actualSessionEvent.toString();
    Object actualData = actualSessionEvent.getData();
    Session actualSession = actualSessionEvent.getSession();

    // Assert
    assertEquals("Data", actualData);
    assertEquals("SessionEvent['DeltaSession[null]','Type']", actualToStringResult);
    assertEquals("Type", actualSessionEvent.getType());
    assertSame(session, actualSessionEvent.getSource());
    assertSame(session, actualSession);
  }
}
