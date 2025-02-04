package jakarta.servlet.jsp;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PageContextDiffblueTest {
  /**
   * Test {@link PageContext#pushBody()}.
   * <p>
   * Method under test: {@link PageContext#pushBody()}
   */
  @Test
  public void testPushBody() {
    // Arrange, Act and Assert
    assertNull((new TesterPageContext()).pushBody());
  }
}
