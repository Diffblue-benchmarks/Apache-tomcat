package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.valves.FilterValve;
import org.apache.coyote.Request;
import org.junit.Test;

public class CsrfPreventionFilterBaseDiffblueTest {
  /**
   * Test {@link CsrfPreventionFilterBase#getDenyStatus()}.
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#getDenyStatus()}
   */
  @Test
  public void testGetDenyStatus() {
    // Arrange, Act and Assert
    assertEquals(403, (new CsrfPreventionFilter()).getDenyStatus());
  }

  /**
   * Test {@link CsrfPreventionFilterBase#setDenyStatus(int)}.
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#setDenyStatus(int)}
   */
  @Test
  public void testSetDenyStatus() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();

    // Act
    csrfPreventionFilter.setDenyStatus(1);

    // Assert
    assertEquals(1, csrfPreventionFilter.getDenyStatus());
  }

  /**
   * Test {@link CsrfPreventionFilterBase#init(FilterConfig)}.
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#init(FilterConfig)}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    csrfPreventionFilter.setRandomClass("org.apache.catalina.filters.CsrfPreventionFilterBase");

    // Act and Assert
    assertThrows(ServletException.class, () -> csrfPreventionFilter.init(new FilterValve()));
  }

  /**
   * Test {@link CsrfPreventionFilterBase#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@link CsrfPreventionFilter} (default constructor) RandomClass is {@code filterValve.noContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#init(FilterConfig)}
   */
  @Test
  public void testInit_givenCsrfPreventionFilterRandomClassIsFilterValveNoContext() throws ServletException {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    csrfPreventionFilter.setRandomClass("filterValve.noContext");

    // Act and Assert
    assertThrows(ServletException.class, () -> csrfPreventionFilter.init(new FilterValve()));
  }

  /**
   * Test {@link CsrfPreventionFilterBase#isConfigProblemFatal()}.
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#isConfigProblemFatal()}
   */
  @Test
  public void testIsConfigProblemFatal() {
    // Arrange, Act and Assert
    assertTrue((new CsrfPreventionFilter()).isConfigProblemFatal());
  }

  /**
   * Test {@link CsrfPreventionFilterBase#getRequestedPath(HttpServletRequest)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilterBase#getRequestedPath(HttpServletRequest)}
   */
  @Test
  public void testGetRequestedPath_thenReturnNull() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertNull(csrfPreventionFilter.getRequestedPath(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }
}
