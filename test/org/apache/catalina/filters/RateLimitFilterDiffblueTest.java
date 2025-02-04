package org.apache.catalina.filters;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import org.apache.catalina.valves.FilterValve;
import org.junit.Test;

public class RateLimitFilterDiffblueTest {
  /**
   * Test {@link RateLimitFilter#init(FilterConfig)}.
   * <p>
   * Method under test: {@link RateLimitFilter#init(FilterConfig)}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange
    RateLimitFilter rateLimitFilter = new RateLimitFilter();
    rateLimitFilter.setRateLimitClassName("jakarta.servlet.http.HttpServletResponse");

    // Act and Assert
    assertThrows(ServletException.class, () -> rateLimitFilter.init(new FilterValve()));
  }

  /**
   * Test {@link RateLimitFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@link RateLimitFilter} (default constructor) RateLimitClassName is {@code filterValve.noContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RateLimitFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenRateLimitFilterRateLimitClassNameIsFilterValveNoContext() throws ServletException {
    // Arrange
    RateLimitFilter rateLimitFilter = new RateLimitFilter();
    rateLimitFilter.setRateLimitClassName("filterValve.noContext");

    // Act and Assert
    assertThrows(ServletException.class, () -> rateLimitFilter.init(new FilterValve()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RateLimitFilter#setBucketDuration(int)}
   *   <li>{@link RateLimitFilter#setBucketRequests(int)}
   *   <li>{@link RateLimitFilter#setEnforce(boolean)}
   *   <li>{@link RateLimitFilter#setExposeHeaders(boolean)}
   *   <li>{@link RateLimitFilter#setPolicyName(String)}
   *   <li>{@link RateLimitFilter#setRateLimitClassName(String)}
   *   <li>{@link RateLimitFilter#setStatusCode(int)}
   *   <li>{@link RateLimitFilter#setStatusMessage(String)}
   *   <li>{@link RateLimitFilter#getLogger()}
   *   <li>{@link RateLimitFilter#isConfigProblemFatal()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    RateLimitFilter rateLimitFilter = new RateLimitFilter();

    // Act
    rateLimitFilter.setBucketDuration(1);
    rateLimitFilter.setBucketRequests(1);
    rateLimitFilter.setEnforce(true);
    rateLimitFilter.setExposeHeaders(true);
    rateLimitFilter.setPolicyName("Policy Name");
    rateLimitFilter.setRateLimitClassName("Rate Limit Class Name");
    rateLimitFilter.setStatusCode(1);
    rateLimitFilter.setStatusMessage("Status Message");
    rateLimitFilter.getLogger();

    // Assert
    assertTrue(rateLimitFilter.isConfigProblemFatal());
  }

  /**
   * Test new {@link RateLimitFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RateLimitFilter}
   */
  @Test
  public void testNewRateLimitFilter() {
    // Arrange and Act
    RateLimitFilter actualRateLimitFilter = new RateLimitFilter();

    // Assert
    assertNull(actualRateLimitFilter.rateLimiter);
    assertTrue(actualRateLimitFilter.isConfigProblemFatal());
  }
}
