package org.apache.catalina.manager.host;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import org.apache.catalina.Wrapper;
import org.junit.Test;

public class HostManagerServletDiffblueTest {
  /**
   * Test {@link HostManagerServlet#init()}.
   * <p>
   * Method under test: {@link HostManagerServlet#init()}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange, Act and Assert
    assertThrows(UnavailableException.class, () -> (new HostManagerServlet()).init());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link HostManagerServlet}
   *   <li>{@link HostManagerServlet#destroy()}
   *   <li>{@link HostManagerServlet#getWrapper()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    HostManagerServlet actualHostManagerServlet = new HostManagerServlet();
    actualHostManagerServlet.destroy();
    Wrapper actualWrapper = actualHostManagerServlet.getWrapper();

    // Assert
    assertNull(actualHostManagerServlet.getServletConfig());
    assertNull(actualWrapper);
  }
}
