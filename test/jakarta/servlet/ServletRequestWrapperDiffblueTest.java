package jakarta.servlet;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.filters.TesterHttpServletRequest;
import org.apache.coyote.Request;
import org.junit.Test;

public class ServletRequestWrapperDiffblueTest {
  /**
   * Test {@link ServletRequestWrapper#ServletRequestWrapper(ServletRequest)}.
   * <p>
   * Method under test: {@link ServletRequestWrapper#ServletRequestWrapper(ServletRequest)}
   */
  @Test
  public void testNewServletRequestWrapper() {
    // Arrange
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertSame(request, (new ServletRequestWrapper(request)).getRequest());
  }

  /**
   * Test {@link ServletRequestWrapper#ServletRequestWrapper(ServletRequest)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletRequestWrapper#ServletRequestWrapper(ServletRequest)}
   */
  @Test
  public void testNewServletRequestWrapper_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ServletRequestWrapper(null));
  }

  /**
   * Test {@link ServletRequestWrapper#getRequest()}.
   * <p>
   * Method under test: {@link ServletRequestWrapper#getRequest()}
   */
  @Test
  public void testGetRequest() {
    // Arrange
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act
    ServletRequest actualRequest = (new ServletRequestWrapper(request)).getRequest();

    // Assert
    assertTrue(actualRequest instanceof HttpServletRequestWrapper);
    assertSame(request, actualRequest);
  }

  /**
   * Test {@link ServletRequestWrapper#getParameterMap()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletRequestWrapper#getParameterMap()}
   */
  @Test
  public void testGetParameterMap_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(
        (new ServletRequestWrapper(new HttpServletRequestWrapper(new TesterHttpServletRequest()))).getParameterMap()
            .isEmpty());
  }

  /**
   * Test {@link ServletRequestWrapper#getRequestDispatcher(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletRequestWrapper#getRequestDispatcher(String)}
   */
  @Test
  public void testGetRequestDispatcher_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new ServletRequestWrapper(new HttpServletRequestWrapper(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))))
        .getRequestDispatcher("Path"));
  }
}
