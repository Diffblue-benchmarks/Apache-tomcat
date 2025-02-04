package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RequestFilterDiffblueTest {
  /**
   * Test {@link RequestFilter#getAllow()}.
   * <ul>
   *   <li>Given {@link RemoteAddrFilter} (default constructor) Allow is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#getAllow()}
   */
  @Test
  public void testGetAllow_givenRemoteAddrFilterAllowIsFoo_thenReturnFoo() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();
    remoteAddrFilter.setAllow("foo");

    // Act and Assert
    assertEquals("foo", remoteAddrFilter.getAllow());
  }

  /**
   * Test {@link RequestFilter#getAllow()}.
   * <ul>
   *   <li>Given {@link RemoteAddrFilter} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#getAllow()}
   */
  @Test
  public void testGetAllow_givenRemoteAddrFilter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new RemoteAddrFilter()).getAllow());
  }

  /**
   * Test {@link RequestFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@code Allow}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) {@link RequestFilter#allow} pattern is {@code Allow}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenAllow_thenRemoteAddrFilterAllowPatternIsAllow() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setAllow("Allow");

    // Assert
    assertEquals("Allow", remoteAddrFilter.allow.pattern());
    assertEquals("Allow", remoteAddrFilter.getAllow());
  }

  /**
   * Test {@link RequestFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) Allow is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenDefault_allowed_origins_thenRemoteAddrFilterAllowIsNull() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setAllow(CorsFilter.DEFAULT_ALLOWED_ORIGINS);

    // Assert that nothing has changed
    assertNull(remoteAddrFilter.getAllow());
  }

  /**
   * Test {@link RequestFilter#setAllow(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) Allow is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenNull_thenRemoteAddrFilterAllowIsNull() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setAllow(null);

    // Assert that nothing has changed
    assertNull(remoteAddrFilter.getAllow());
  }

  /**
   * Test {@link RequestFilter#getDeny()}.
   * <ul>
   *   <li>Given {@link RemoteAddrFilter} (default constructor) Deny is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#getDeny()}
   */
  @Test
  public void testGetDeny_givenRemoteAddrFilterDenyIsFoo_thenReturnFoo() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();
    remoteAddrFilter.setDeny("foo");

    // Act and Assert
    assertEquals("foo", remoteAddrFilter.getDeny());
  }

  /**
   * Test {@link RequestFilter#getDeny()}.
   * <ul>
   *   <li>Given {@link RemoteAddrFilter} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#getDeny()}
   */
  @Test
  public void testGetDeny_givenRemoteAddrFilter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new RemoteAddrFilter()).getDeny());
  }

  /**
   * Test {@link RequestFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) Deny is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenDefault_allowed_origins_thenRemoteAddrFilterDenyIsNull() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setDeny(CorsFilter.DEFAULT_ALLOWED_ORIGINS);

    // Assert that nothing has changed
    assertNull(remoteAddrFilter.getDeny());
  }

  /**
   * Test {@link RequestFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@code Deny}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) {@link RequestFilter#deny} pattern is {@code Deny}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenDeny_thenRemoteAddrFilterDenyPatternIsDeny() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setDeny("Deny");

    // Assert
    assertEquals("Deny", remoteAddrFilter.deny.pattern());
    assertEquals("Deny", remoteAddrFilter.getDeny());
  }

  /**
   * Test {@link RequestFilter#setDeny(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RemoteAddrFilter} (default constructor) Deny is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestFilter#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenNull_thenRemoteAddrFilterDenyIsNull() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setDeny(null);

    // Assert that nothing has changed
    assertNull(remoteAddrFilter.getDeny());
  }

  /**
   * Test {@link RequestFilter#getDenyStatus()}.
   * <p>
   * Method under test: {@link RequestFilter#getDenyStatus()}
   */
  @Test
  public void testGetDenyStatus() {
    // Arrange, Act and Assert
    assertEquals(403, (new RemoteAddrFilter()).getDenyStatus());
  }

  /**
   * Test {@link RequestFilter#setDenyStatus(int)}.
   * <p>
   * Method under test: {@link RequestFilter#setDenyStatus(int)}
   */
  @Test
  public void testSetDenyStatus() {
    // Arrange
    RemoteAddrFilter remoteAddrFilter = new RemoteAddrFilter();

    // Act
    remoteAddrFilter.setDenyStatus(1);

    // Assert
    assertEquals(1, remoteAddrFilter.getDenyStatus());
  }

  /**
   * Test {@link RequestFilter#isConfigProblemFatal()}.
   * <p>
   * Method under test: {@link RequestFilter#isConfigProblemFatal()}
   */
  @Test
  public void testIsConfigProblemFatal() {
    // Arrange, Act and Assert
    assertTrue((new RemoteAddrFilter()).isConfigProblemFatal());
  }
}
