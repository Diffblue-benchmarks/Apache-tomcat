package org.apache.catalina.filters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import org.apache.catalina.valves.FilterValve;
import org.junit.Test;

public class FilterBaseDiffblueTest {
  /**
   * Test {@link FilterBase#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterBase#init(FilterConfig)}
   */
  @Test
  public void testInit_givenDefault_allowed_origins() throws ServletException {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam(CorsFilter.DEFAULT_ALLOWED_ORIGINS, "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> remoteAddrFilter.init(filterConfig));
  }

  /**
   * Test {@link FilterBase#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code filterValve.illegalWrapping}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterBase#init(FilterConfig)}
   */
  @Test
  public void testInit_givenFilterValveIllegalWrapping() throws ServletException {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> remoteAddrFilter.init(filterConfig));
  }

  /**
   * Test {@link FilterBase#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code getDenyStatus}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code getDenyStatus} and {@code doFilter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterBase#init(FilterConfig)}
   */
  @Test
  public void testInit_givenGetDenyStatus_whenFilterValveAddInitParamGetDenyStatusAndDoFilter()
      throws ServletException {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("getDenyStatus", "doFilter");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> remoteAddrFilter.init(filterConfig));
  }

  /**
   * Test {@link FilterBase#isConfigProblemFatal()}.
   * <p>
   * Method under test: {@link FilterBase#isConfigProblemFatal()}
   */
  @Test
  public void testIsConfigProblemFatal() {
    // Arrange, Act and Assert
    assertFalse((new AddDefaultCharsetFilter()).isConfigProblemFatal());
  }
}
