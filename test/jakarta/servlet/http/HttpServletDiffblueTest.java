package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.manager.HTMLManagerServlet;
import org.apache.coyote.Request;
import org.junit.Test;

public class HttpServletDiffblueTest {
  /**
   * Test {@link HttpServlet#getLastModified(HttpServletRequest)}.
   * <p>
   * Method under test: {@link HttpServlet#getLastModified(HttpServletRequest)}
   */
  @Test
  public void testGetLastModified() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L, htmlManagerServlet.getLastModified(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link HttpServlet#isSensitiveHeader(String)}.
   * <p>
   * Method under test: {@link HttpServlet#isSensitiveHeader(String)}
   */
  @Test
  public void testIsSensitiveHeader() {
    // Arrange, Act and Assert
    assertFalse((new HTMLManagerServlet()).isSensitiveHeader("https://example.org/example"));
  }
}
