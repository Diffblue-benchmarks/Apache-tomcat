package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.catalina.manager.HTMLManagerServlet;
import org.apache.catalina.manager.JMXProxyServlet;
import org.junit.Test;

public class GenericServletDiffblueTest {
  /**
   * Test {@link GenericServlet#getServletConfig()}.
   * <p>
   * Method under test: {@link GenericServlet#getServletConfig()}
   */
  @Test
  public void testGetServletConfig() {
    // Arrange, Act and Assert
    assertNull((new HTMLManagerServlet()).getServletConfig());
  }

  /**
   * Test {@link GenericServlet#getServletInfo()}.
   * <p>
   * Method under test: {@link GenericServlet#getServletInfo()}
   */
  @Test
  public void testGetServletInfo() {
    // Arrange, Act and Assert
    assertEquals("", (new JMXProxyServlet()).getServletInfo());
  }
}
