package jakarta.servlet.http;

import static org.junit.Assert.assertSame;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class HttpSessionEventDiffblueTest {
  /**
   * Test {@link HttpSessionEvent#HttpSessionEvent(HttpSession)}.
   * <p>
   * Method under test: {@link HttpSessionEvent#HttpSessionEvent(HttpSession)}
   */
  @Test
  public void testNewHttpSessionEvent() {
    // Arrange
    DeltaSession source = new DeltaSession();

    // Act and Assert
    assertSame(source, (new HttpSessionEvent(source)).getSource());
  }

  /**
   * Test {@link HttpSessionEvent#getSession()}.
   * <p>
   * Method under test: {@link HttpSessionEvent#getSession()}
   */
  @Test
  public void testGetSession() {
    // Arrange
    DeltaSession source = new DeltaSession();

    // Act and Assert
    assertSame(source, (new HttpSessionEvent(source)).getSession());
  }
}
