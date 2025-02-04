package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class HttpSessionBindingEventDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return Value is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HttpSessionBindingEvent#HttpSessionBindingEvent(HttpSession, String)}
   *   <li>{@link HttpSessionBindingEvent#getName()}
   *   <li>{@link HttpSessionBindingEvent#getValue()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenDeltaSession_thenReturnValueIsNull() {
    // Arrange
    DeltaSession session = new DeltaSession();

    // Act
    HttpSessionBindingEvent actualHttpSessionBindingEvent = new HttpSessionBindingEvent(session,
        "https://example.org/example");
    String actualName = actualHttpSessionBindingEvent.getName();

    // Assert
    assertEquals("https://example.org/example", actualName);
    assertNull(actualHttpSessionBindingEvent.getValue());
    assertSame(session, actualHttpSessionBindingEvent.getSource());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HttpSessionBindingEvent#HttpSessionBindingEvent(HttpSession, String, Object)}
   *   <li>{@link HttpSessionBindingEvent#getName()}
   *   <li>{@link HttpSessionBindingEvent#getValue()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenValue_thenReturnValue() {
    // Arrange
    DeltaSession session = new DeltaSession();

    // Act
    HttpSessionBindingEvent actualHttpSessionBindingEvent = new HttpSessionBindingEvent(session,
        "https://example.org/example", "Value");
    String actualName = actualHttpSessionBindingEvent.getName();

    // Assert
    assertEquals("Value", actualHttpSessionBindingEvent.getValue());
    assertEquals("https://example.org/example", actualName);
    assertSame(session, actualHttpSessionBindingEvent.getSource());
  }

  /**
   * Test {@link HttpSessionBindingEvent#getSession()}.
   * <p>
   * Method under test: {@link HttpSessionBindingEvent#getSession()}
   */
  @Test
  public void testGetSession() {
    // Arrange
    DeltaSession session = new DeltaSession();

    // Act and Assert
    assertSame(session, (new HttpSessionBindingEvent(session, "https://example.org/example")).getSession());
  }
}
