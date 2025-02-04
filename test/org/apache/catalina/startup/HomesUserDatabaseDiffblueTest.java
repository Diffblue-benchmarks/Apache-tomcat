package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class HomesUserDatabaseDiffblueTest {
  /**
   * Test {@link HomesUserDatabase#setUserConfig(UserConfig)}.
   * <ul>
   *   <li>Given {@code Home Base}.</li>
   *   <li>Then {@link HomesUserDatabase} (default constructor) UserConfig is {@link UserConfig} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link HomesUserDatabase#setUserConfig(UserConfig)}
   */
  @Test
  public void testSetUserConfig_givenHomeBase_thenHomesUserDatabaseUserConfigIsUserConfig() {
    // Arrange
    HomesUserDatabase homesUserDatabase = new HomesUserDatabase();

    UserConfig userConfig = new UserConfig();
    userConfig.setHomeBase("Home Base");

    // Act
    homesUserDatabase.setUserConfig(userConfig);

    // Assert
    assertSame(userConfig, homesUserDatabase.getUserConfig());
  }

  /**
   * Test {@link HomesUserDatabase#getHome(String)}.
   * <p>
   * Method under test: {@link HomesUserDatabase#getHome(String)}
   */
  @Test
  public void testGetHome() {
    // Arrange, Act and Assert
    assertNull((new HomesUserDatabase()).getHome("User"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link HomesUserDatabase}
   *   <li>{@link HomesUserDatabase#getUserConfig()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new HomesUserDatabase()).getUserConfig());
  }
}
