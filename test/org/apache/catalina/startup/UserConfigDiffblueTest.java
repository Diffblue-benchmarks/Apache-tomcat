package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class UserConfigDiffblueTest {
  /**
   * Test {@link UserConfig#getAllow()}.
   * <ul>
   *   <li>Given {@link UserConfig} (default constructor) Allow is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#getAllow()}
   */
  @Test
  public void testGetAllow_givenUserConfigAllowIsFoo_thenReturnFoo() {
    // Arrange
    UserConfig userConfig = new UserConfig();
    userConfig.setAllow("foo");

    // Act and Assert
    assertEquals("foo", userConfig.getAllow());
  }

  /**
   * Test {@link UserConfig#getAllow()}.
   * <ul>
   *   <li>Given {@link UserConfig} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#getAllow()}
   */
  @Test
  public void testGetAllow_givenUserConfig_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new UserConfig()).getAllow());
  }

  /**
   * Test {@link UserConfig#setAllow(String)}.
   * <ul>
   *   <li>When {@code Allow}.</li>
   *   <li>Then {@link UserConfig} (default constructor) {@link UserConfig#allow} pattern is {@code Allow}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenAllow_thenUserConfigAllowPatternIsAllow() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setAllow("Allow");

    // Assert
    assertEquals("Allow", userConfig.allow.pattern());
    assertEquals("Allow", userConfig.getAllow());
  }

  /**
   * Test {@link UserConfig#setAllow(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link UserConfig} (default constructor) Allow is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenEmptyString_thenUserConfigAllowIsNull() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setAllow("");

    // Assert that nothing has changed
    assertNull(userConfig.getAllow());
  }

  /**
   * Test {@link UserConfig#setAllow(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link UserConfig} (default constructor) Allow is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setAllow(String)}
   */
  @Test
  public void testSetAllow_whenNull_thenUserConfigAllowIsNull() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setAllow(null);

    // Assert that nothing has changed
    assertNull(userConfig.getAllow());
  }

  /**
   * Test {@link UserConfig#getDeny()}.
   * <ul>
   *   <li>Given {@link UserConfig} (default constructor) Deny is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#getDeny()}
   */
  @Test
  public void testGetDeny_givenUserConfigDenyIsFoo_thenReturnFoo() {
    // Arrange
    UserConfig userConfig = new UserConfig();
    userConfig.setDeny("foo");

    // Act and Assert
    assertEquals("foo", userConfig.getDeny());
  }

  /**
   * Test {@link UserConfig#getDeny()}.
   * <ul>
   *   <li>Given {@link UserConfig} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#getDeny()}
   */
  @Test
  public void testGetDeny_givenUserConfig_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new UserConfig()).getDeny());
  }

  /**
   * Test {@link UserConfig#setDeny(String)}.
   * <ul>
   *   <li>When {@code Deny}.</li>
   *   <li>Then {@link UserConfig} (default constructor) {@link UserConfig#deny} pattern is {@code Deny}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenDeny_thenUserConfigDenyPatternIsDeny() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setDeny("Deny");

    // Assert
    assertEquals("Deny", userConfig.deny.pattern());
    assertEquals("Deny", userConfig.getDeny());
  }

  /**
   * Test {@link UserConfig#setDeny(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link UserConfig} (default constructor) Deny is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenEmptyString_thenUserConfigDenyIsNull() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setDeny("");

    // Assert that nothing has changed
    assertNull(userConfig.getDeny());
  }

  /**
   * Test {@link UserConfig#setDeny(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link UserConfig} (default constructor) Deny is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserConfig#setDeny(String)}
   */
  @Test
  public void testSetDeny_whenNull_thenUserConfigDenyIsNull() {
    // Arrange
    UserConfig userConfig = new UserConfig();

    // Act
    userConfig.setDeny(null);

    // Assert that nothing has changed
    assertNull(userConfig.getDeny());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link UserConfig}
   *   <li>{@link UserConfig#setConfigClass(String)}
   *   <li>{@link UserConfig#setContextClass(String)}
   *   <li>{@link UserConfig#setDirectoryName(String)}
   *   <li>{@link UserConfig#setHomeBase(String)}
   *   <li>{@link UserConfig#setUserClass(String)}
   *   <li>{@link UserConfig#getConfigClass()}
   *   <li>{@link UserConfig#getContextClass()}
   *   <li>{@link UserConfig#getDirectoryName()}
   *   <li>{@link UserConfig#getHomeBase()}
   *   <li>{@link UserConfig#getUserClass()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    UserConfig actualUserConfig = new UserConfig();
    actualUserConfig.setConfigClass("Config Class");
    actualUserConfig.setContextClass("Context Class");
    actualUserConfig.setDirectoryName("/directory");
    actualUserConfig.setHomeBase("Home Base");
    actualUserConfig.setUserClass("User Class");
    String actualConfigClass = actualUserConfig.getConfigClass();
    String actualContextClass = actualUserConfig.getContextClass();
    String actualDirectoryName = actualUserConfig.getDirectoryName();
    String actualHomeBase = actualUserConfig.getHomeBase();

    // Assert
    assertEquals("/directory", actualDirectoryName);
    assertEquals("Config Class", actualConfigClass);
    assertEquals("Context Class", actualContextClass);
    assertEquals("Home Base", actualHomeBase);
    assertEquals("User Class", actualUserConfig.getUserClass());
  }
}
