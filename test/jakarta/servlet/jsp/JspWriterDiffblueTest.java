package jakarta.servlet.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.jasper.runtime.JspWriterImpl;
import org.junit.Test;

public class JspWriterDiffblueTest {
  /**
   * Test {@link JspWriter#getBufferSize()}.
   * <p>
   * Method under test: {@link JspWriter#getBufferSize()}
   */
  @Test
  public void testGetBufferSize() {
    // Arrange, Act and Assert
    assertEquals(8192, (new JspWriterImpl()).getBufferSize());
  }

  /**
   * Test {@link JspWriter#isAutoFlush()}.
   * <ul>
   *   <li>Given {@link JspWriterImpl#JspWriterImpl()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspWriter#isAutoFlush()}
   */
  @Test
  public void testIsAutoFlush_givenJspWriterImpl_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new JspWriterImpl()).isAutoFlush());
  }

  /**
   * Test {@link JspWriter#isAutoFlush()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspWriter#isAutoFlush()}
   */
  @Test
  public void testIsAutoFlush_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        (new JspWriterImpl(new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse())),
            3, false)).isAutoFlush());
  }
}
