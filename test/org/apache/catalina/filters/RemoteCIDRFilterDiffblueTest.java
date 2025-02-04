package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RemoteCIDRFilterDiffblueTest {
  /**
   * Test {@link RemoteCIDRFilter#getAllow()}.
   * <p>
   * Method under test: {@link RemoteCIDRFilter#getAllow()}
   */
  @Test
  public void testGetAllow() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, (new RemoteCIDRFilter()).getAllow());
  }

  /**
   * Test {@link RemoteCIDRFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Allow is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_when42_thenRemoteCIDRFilterAllowIs42() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setAllow("42");

    // Assert
    assertEquals("42", remoteCIDRFilter.getAllow());
  }

  /**
   * Test {@link RemoteCIDRFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Allow is {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenComma_thenRemoteCIDRFilterAllowIsDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setAllow(",");

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getAllow());
  }

  /**
   * Test {@link RemoteCIDRFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setAllow(CorsFilter.DEFAULT_ALLOWED_ORIGINS);

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getAllow());
  }

  /**
   * Test {@link RemoteCIDRFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Allow is {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenNull_thenRemoteCIDRFilterAllowIsDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setAllow(null);

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getAllow());
  }

  /**
   * Test {@link RemoteCIDRFilter#getDeny()}.
   * <p>
   * Method under test: {@link RemoteCIDRFilter#getDeny()}
   */
  @Test
  public void testGetDeny() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, (new RemoteCIDRFilter()).getDeny());
  }

  /**
   * Test {@link RemoteCIDRFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Deny is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_when42_thenRemoteCIDRFilterDenyIs42() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setDeny("42");

    // Assert
    assertEquals("42", remoteCIDRFilter.getDeny());
  }

  /**
   * Test {@link RemoteCIDRFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Deny is {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenComma_thenRemoteCIDRFilterDenyIsDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setDeny(",");

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getDeny());
  }

  /**
   * Test {@link RemoteCIDRFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setDeny(CorsFilter.DEFAULT_ALLOWED_ORIGINS);

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getDeny());
  }

  /**
   * Test {@link RemoteCIDRFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RemoteCIDRFilter} (default constructor) Deny is {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteCIDRFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenNull_thenRemoteCIDRFilterDenyIsDefault_allowed_origins() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.setDeny(null);

    // Assert that nothing has changed
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, remoteCIDRFilter.getDeny());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RemoteCIDRFilter#getLogger()}
   *   <li>{@link RemoteCIDRFilter#isConfigProblemFatal()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    RemoteCIDRFilter remoteCIDRFilter = new RemoteCIDRFilter();

    // Act
    remoteCIDRFilter.getLogger();

    // Assert
    assertTrue(remoteCIDRFilter.isConfigProblemFatal());
  }

  /**
   * Test new {@link RemoteCIDRFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RemoteCIDRFilter}
   */
  @Test
  public void testNewRemoteCIDRFilter() {
    // Arrange and Act
    RemoteCIDRFilter actualRemoteCIDRFilter = new RemoteCIDRFilter();

    // Assert
    assertTrue(actualRemoteCIDRFilter.isConfigProblemFatal());
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, actualRemoteCIDRFilter.getAllow());
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, actualRemoteCIDRFilter.getDeny());
  }
}
