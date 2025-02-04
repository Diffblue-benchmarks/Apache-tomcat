package org.apache.catalina.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.junit.Test;

public class FilterUtilDiffblueTest {
  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenAsterisk() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("*");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code *.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenAsteriskDot() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("*.");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code *.}.</li>
   *   <li>When {@code Request Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenAsteriskDot_whenRequestPath() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("*.");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertFalse(FilterUtil.matchFiltersURL(filterMap, "Request Path"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code https://example.org/example/*}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenHttpsExampleOrgExample() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("https://example.org/example/*");
    filterMap.addURLPattern("Url Pattern");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenNull() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern(null);

    // Act and Assert
    assertFalse(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code /*}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenSlashAsterisk() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("/*");
    filterMap.addURLPattern("Url Pattern");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Given {@code Url Pattern}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_givenUrlPattern() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("Url Pattern");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_thenReturnTrue() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertTrue(FilterUtil.matchFiltersURL(filterMap, "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>When {@link FilterMap} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_whenFilterMap_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(FilterUtil.matchFiltersURL(new FilterMap(), "https://example.org/example"));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>When {@link FilterMap} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_whenFilterMap_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(FilterUtil.matchFiltersURL(new FilterMap(), null));
  }

  /**
   * Test {@link FilterUtil#matchFiltersURL(FilterMap, String)} with {@code filterMap}, {@code requestPath}.
   * <ul>
   *   <li>When {@code /*}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterUtil#matchFiltersURL(FilterMap, String)}
   */
  @Test
  public void testMatchFiltersURLWithFilterMapRequestPath_whenSlashAsterisk() {
    // Arrange
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("https://example.org/example/*");
    filterMap.addURLPattern("Url Pattern");
    filterMap.addURLPattern("https://example.org/example");

    // Act and Assert
    assertFalse(FilterUtil.matchFiltersURL(filterMap, "/*"));
  }
}
