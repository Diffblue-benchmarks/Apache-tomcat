package org.apache.catalina.manager.host;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class HTMLHostManagerServletDiffblueTest {
  /**
   * Test new {@link HTMLHostManagerServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HTMLHostManagerServlet}
   */
  @Test
  public void testNewHTMLHostManagerServlet() {
    // Arrange and Act
    HTMLHostManagerServlet actualHtmlHostManagerServlet = new HTMLHostManagerServlet();

    // Assert
    assertNull(actualHtmlHostManagerServlet.getServletConfig());
    assertNull(actualHtmlHostManagerServlet.getWrapper());
  }
}
