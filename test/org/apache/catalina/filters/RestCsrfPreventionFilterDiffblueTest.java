package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.Test;

public class RestCsrfPreventionFilterDiffblueTest {
  /**
   * Test {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}.
   * <p>
   * Method under test: {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}
   */
  @Test
  public void testSetPathsAcceptingParams() {
    // Arrange
    RestCsrfPreventionFilter restCsrfPreventionFilter = new RestCsrfPreventionFilter();

    // Act
    restCsrfPreventionFilter.setPathsAcceptingParams("Paths List");

    // Assert
    Set<String> pathsAcceptingParams = restCsrfPreventionFilter.getPathsAcceptingParams();
    assertEquals(1, pathsAcceptingParams.size());
    assertTrue(pathsAcceptingParams.contains("Paths List"));
  }

  /**
   * Test {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}.
   * <p>
   * Method under test: {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}
   */
  @Test
  public void testSetPathsAcceptingParams2() {
    // Arrange
    RestCsrfPreventionFilter restCsrfPreventionFilter = new RestCsrfPreventionFilter();

    // Act
    restCsrfPreventionFilter.setPathsAcceptingParams(",");

    // Assert that nothing has changed
    assertTrue(restCsrfPreventionFilter.getPathsAcceptingParams().isEmpty());
  }

  /**
   * Test {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}.
   * <p>
   * Method under test: {@link RestCsrfPreventionFilter#setPathsAcceptingParams(String)}
   */
  @Test
  public void testSetPathsAcceptingParams3() {
    // Arrange
    RestCsrfPreventionFilter restCsrfPreventionFilter = new RestCsrfPreventionFilter();

    // Act
    restCsrfPreventionFilter.setPathsAcceptingParams(",Paths List");

    // Assert
    Set<String> pathsAcceptingParams = restCsrfPreventionFilter.getPathsAcceptingParams();
    assertEquals(2, pathsAcceptingParams.size());
    assertTrue(pathsAcceptingParams.contains("Paths List"));
    assertTrue(pathsAcceptingParams.contains(CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link RestCsrfPreventionFilter#getPathsAcceptingParams()}.
   * <p>
   * Method under test: {@link RestCsrfPreventionFilter#getPathsAcceptingParams()}
   */
  @Test
  public void testGetPathsAcceptingParams() {
    // Arrange, Act and Assert
    assertTrue((new RestCsrfPreventionFilter()).getPathsAcceptingParams().isEmpty());
  }

  /**
   * Test new {@link RestCsrfPreventionFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RestCsrfPreventionFilter}
   */
  @Test
  public void testNewRestCsrfPreventionFilter() {
    // Arrange and Act
    RestCsrfPreventionFilter actualRestCsrfPreventionFilter = new RestCsrfPreventionFilter();

    // Assert
    assertEquals(403, actualRestCsrfPreventionFilter.getDenyStatus());
    assertTrue(actualRestCsrfPreventionFilter.getPathsAcceptingParams().isEmpty());
    assertTrue(actualRestCsrfPreventionFilter.isConfigProblemFatal());
  }
}
