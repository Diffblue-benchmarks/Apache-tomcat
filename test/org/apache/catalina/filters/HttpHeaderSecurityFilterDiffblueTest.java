package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HttpHeaderSecurityFilterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HttpHeaderSecurityFilter#setAntiClickJackingEnabled(boolean)}
   *   <li>{@link HttpHeaderSecurityFilter#setBlockContentTypeSniffingEnabled(boolean)}
   *   <li>{@link HttpHeaderSecurityFilter#setHstsEnabled(boolean)}
   *   <li>{@link HttpHeaderSecurityFilter#setHstsIncludeSubDomains(boolean)}
   *   <li>{@link HttpHeaderSecurityFilter#setHstsPreload(boolean)}
   *   <li>{@link HttpHeaderSecurityFilter#getHstsMaxAgeSeconds()}
   *   <li>{@link HttpHeaderSecurityFilter#getLogger()}
   *   <li>{@link HttpHeaderSecurityFilter#isAntiClickJackingEnabled()}
   *   <li>{@link HttpHeaderSecurityFilter#isBlockContentTypeSniffingEnabled()}
   *   <li>{@link HttpHeaderSecurityFilter#isConfigProblemFatal()}
   *   <li>{@link HttpHeaderSecurityFilter#isHstsEnabled()}
   *   <li>{@link HttpHeaderSecurityFilter#isHstsIncludeSubDomains()}
   *   <li>{@link HttpHeaderSecurityFilter#isHstsPreload()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    HttpHeaderSecurityFilter httpHeaderSecurityFilter = new HttpHeaderSecurityFilter();

    // Act
    httpHeaderSecurityFilter.setAntiClickJackingEnabled(true);
    httpHeaderSecurityFilter.setBlockContentTypeSniffingEnabled(true);
    httpHeaderSecurityFilter.setHstsEnabled(true);
    httpHeaderSecurityFilter.setHstsIncludeSubDomains(true);
    httpHeaderSecurityFilter.setHstsPreload(true);
    int actualHstsMaxAgeSeconds = httpHeaderSecurityFilter.getHstsMaxAgeSeconds();
    httpHeaderSecurityFilter.getLogger();
    boolean actualIsAntiClickJackingEnabledResult = httpHeaderSecurityFilter.isAntiClickJackingEnabled();
    boolean actualIsBlockContentTypeSniffingEnabledResult = httpHeaderSecurityFilter
        .isBlockContentTypeSniffingEnabled();
    boolean actualIsConfigProblemFatalResult = httpHeaderSecurityFilter.isConfigProblemFatal();
    boolean actualIsHstsEnabledResult = httpHeaderSecurityFilter.isHstsEnabled();
    boolean actualIsHstsIncludeSubDomainsResult = httpHeaderSecurityFilter.isHstsIncludeSubDomains();

    // Assert
    assertEquals(0, actualHstsMaxAgeSeconds);
    assertTrue(actualIsAntiClickJackingEnabledResult);
    assertTrue(actualIsBlockContentTypeSniffingEnabledResult);
    assertTrue(actualIsConfigProblemFatalResult);
    assertTrue(actualIsHstsEnabledResult);
    assertTrue(actualIsHstsIncludeSubDomainsResult);
    assertTrue(httpHeaderSecurityFilter.isHstsPreload());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#setHstsMaxAgeSeconds(int)}.
   * <ul>
   *   <li>Then {@link HttpHeaderSecurityFilter} (default constructor) HstsMaxAgeSeconds is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#setHstsMaxAgeSeconds(int)}
   */
  @Test
  public void testSetHstsMaxAgeSeconds_thenHttpHeaderSecurityFilterHstsMaxAgeSecondsIsOne() {
    // Arrange
    HttpHeaderSecurityFilter httpHeaderSecurityFilter = new HttpHeaderSecurityFilter();

    // Act
    httpHeaderSecurityFilter.setHstsMaxAgeSeconds(1);

    // Assert
    assertEquals(1, httpHeaderSecurityFilter.getHstsMaxAgeSeconds());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#setHstsMaxAgeSeconds(int)}.
   * <ul>
   *   <li>Then {@link HttpHeaderSecurityFilter} (default constructor) HstsMaxAgeSeconds is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#setHstsMaxAgeSeconds(int)}
   */
  @Test
  public void testSetHstsMaxAgeSeconds_thenHttpHeaderSecurityFilterHstsMaxAgeSecondsIsZero() {
    // Arrange
    HttpHeaderSecurityFilter httpHeaderSecurityFilter = new HttpHeaderSecurityFilter();

    // Act
    httpHeaderSecurityFilter.setHstsMaxAgeSeconds(-1);

    // Assert that nothing has changed
    assertEquals(0, httpHeaderSecurityFilter.getHstsMaxAgeSeconds());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#getAntiClickJackingOption()}.
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#getAntiClickJackingOption()}
   */
  @Test
  public void testGetAntiClickJackingOption() {
    // Arrange, Act and Assert
    assertEquals("DENY", (new HttpHeaderSecurityFilter()).getAntiClickJackingOption());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#setAntiClickJackingOption(String)}.
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#setAntiClickJackingOption(String)}
   */
  @Test
  public void testSetAntiClickJackingOption() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HttpHeaderSecurityFilter()).setAntiClickJackingOption("https://example.org/example"));
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#getAntiClickJackingUri()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#getAntiClickJackingUri()}
   */
  @Test
  public void testGetAntiClickJackingUri_thenReturnHttpsExampleOrgExample() {
    // Arrange
    HttpHeaderSecurityFilter httpHeaderSecurityFilter = new HttpHeaderSecurityFilter();
    httpHeaderSecurityFilter.setAntiClickJackingUri("https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example", httpHeaderSecurityFilter.getAntiClickJackingUri());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#setAntiClickJackingUri(String)}.
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#setAntiClickJackingUri(String)}
   */
  @Test
  public void testSetAntiClickJackingUri() {
    // Arrange
    HttpHeaderSecurityFilter httpHeaderSecurityFilter = new HttpHeaderSecurityFilter();

    // Act
    httpHeaderSecurityFilter.setAntiClickJackingUri("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", httpHeaderSecurityFilter.getAntiClickJackingUri());
  }

  /**
   * Test {@link HttpHeaderSecurityFilter#setAntiClickJackingUri(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpHeaderSecurityFilter#setAntiClickJackingUri(String)}
   */
  @Test
  public void testSetAntiClickJackingUri_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HttpHeaderSecurityFilter()).setAntiClickJackingUri("Anti Click Jacking Uri"));
  }

  /**
   * Test new {@link HttpHeaderSecurityFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HttpHeaderSecurityFilter}
   */
  @Test
  public void testNewHttpHeaderSecurityFilter() {
    // Arrange and Act
    HttpHeaderSecurityFilter actualHttpHeaderSecurityFilter = new HttpHeaderSecurityFilter();

    // Assert
    assertEquals("DENY", actualHttpHeaderSecurityFilter.getAntiClickJackingOption());
    assertEquals(0, actualHttpHeaderSecurityFilter.getHstsMaxAgeSeconds());
    assertFalse(actualHttpHeaderSecurityFilter.isHstsIncludeSubDomains());
    assertFalse(actualHttpHeaderSecurityFilter.isHstsPreload());
    assertTrue(actualHttpHeaderSecurityFilter.isAntiClickJackingEnabled());
    assertTrue(actualHttpHeaderSecurityFilter.isBlockContentTypeSniffingEnabled());
    assertTrue(actualHttpHeaderSecurityFilter.isConfigProblemFatal());
    assertTrue(actualHttpHeaderSecurityFilter.isHstsEnabled());
  }
}
