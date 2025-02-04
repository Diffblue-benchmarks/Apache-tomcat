package jakarta.servlet.jsp;

import static org.junit.Assert.assertNull;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

public class JspContextDiffblueTest {
  /**
   * Test {@link JspContext#pushBody(Writer)}.
   * <p>
   * Method under test: {@link JspContext#pushBody(Writer)}
   */
  @Test
  public void testPushBody() {
    // Arrange
    TesterPageContext testerPageContext = new TesterPageContext();

    // Act and Assert
    assertNull(testerPageContext.pushBody(new StringWriter()));
  }

  /**
   * Test {@link JspContext#popBody()}.
   * <p>
   * Method under test: {@link JspContext#popBody()}
   */
  @Test
  public void testPopBody() {
    // Arrange, Act and Assert
    assertNull((new TesterPageContext()).popBody());
  }
}
