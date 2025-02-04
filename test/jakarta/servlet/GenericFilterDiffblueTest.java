package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import java.util.Set;
import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.valves.FilterValve;
import org.junit.Test;

public class GenericFilterDiffblueTest {
  /**
   * Test {@link GenericFilter#getInitParameter(String)}.
   * <ul>
   *   <li>Given {@link CorsFilter} (default constructor) init {@link FilterValve} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericFilter#getInitParameter(String)}
   */
  @Test
  public void testGetInitParameter_givenCorsFilterInitFilterValve_thenReturnNull() throws ServletException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    corsFilter.init(new FilterValve());

    // Act and Assert
    assertNull(corsFilter.getInitParameter("Name"));
  }

  /**
   * Test {@link GenericFilter#getFilterConfig()}.
   * <p>
   * Method under test: {@link GenericFilter#getFilterConfig()}
   */
  @Test
  public void testGetFilterConfig() {
    // Arrange, Act and Assert
    assertNull((new CorsFilter()).getFilterConfig());
  }

  /**
   * Test {@link GenericFilter#init(FilterConfig)} with {@code FilterConfig}.
   * <ul>
   *   <li>Given {@link FilterValve} (default constructor).</li>
   *   <li>Then {@link CorsFilter} (default constructor) FilterConfig {@link FilterValve}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericFilter#init(FilterConfig)}
   */
  @Test
  public void testInitWithFilterConfig_givenFilterValve_thenCorsFilterFilterConfigFilterValve()
      throws ServletException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();

    CorsFilter filterConfig = new CorsFilter();
    FilterValve filterConfig2 = new FilterValve();
    filterConfig.init(filterConfig2);

    // Act
    corsFilter.init(filterConfig);

    // Assert that nothing has changed
    Collection<String> allowedHttpHeaders = filterConfig.getAllowedHttpHeaders();
    assertEquals(6, allowedHttpHeaders.size());
    assertTrue(allowedHttpHeaders instanceof Set);
    Collection<String> allowedHttpMethods = filterConfig.getAllowedHttpMethods();
    assertEquals(4, allowedHttpMethods.size());
    assertTrue(allowedHttpMethods instanceof Set);
    FilterConfig filterConfig3 = filterConfig.getFilterConfig();
    assertTrue(filterConfig3 instanceof FilterValve);
    assertEquals(1800L, filterConfig.getPreflightMaxAge());
    assertTrue(filterConfig.isDecorateRequest());
    assertSame(filterConfig2, filterConfig3);
  }

  /**
   * Test {@link GenericFilter#init(FilterConfig)} with {@code FilterConfig}.
   * <ul>
   *   <li>When {@link FilterValve} (default constructor).</li>
   *   <li>Then {@link CorsFilter} (default constructor) FilterName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericFilter#init(FilterConfig)}
   */
  @Test
  public void testInitWithFilterConfig_whenFilterValve_thenCorsFilterFilterNameIsNull() throws ServletException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    FilterValve filterConfig = new FilterValve();

    // Act
    corsFilter.init(filterConfig);

    // Assert
    Collection<String> allowedHttpHeaders = corsFilter.getAllowedHttpHeaders();
    assertEquals(6, allowedHttpHeaders.size());
    assertTrue(allowedHttpHeaders instanceof Set);
    Collection<String> allowedHttpMethods = corsFilter.getAllowedHttpMethods();
    assertEquals(4, allowedHttpMethods.size());
    assertTrue(allowedHttpMethods instanceof Set);
    assertNull(corsFilter.getFilterName());
    assertEquals(1800L, corsFilter.getPreflightMaxAge());
    assertTrue(corsFilter.isDecorateRequest());
    assertSame(filterConfig, corsFilter.getFilterConfig());
  }

  /**
   * Test {@link GenericFilter#getFilterName()}.
   * <ul>
   *   <li>Given {@link CorsFilter} (default constructor) init {@link FilterValve} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericFilter#getFilterName()}
   */
  @Test
  public void testGetFilterName_givenCorsFilterInitFilterValve_thenReturnNull() throws ServletException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    corsFilter.init(new FilterValve());

    // Act and Assert
    assertNull(corsFilter.getFilterName());
  }
}
