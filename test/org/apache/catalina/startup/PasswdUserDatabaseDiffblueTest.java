package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class PasswdUserDatabaseDiffblueTest {
  /**
   * Test {@link PasswdUserDatabase#setUserConfig(UserConfig)}.
   * <p>
   * Method under test: {@link PasswdUserDatabase#setUserConfig(UserConfig)}
   */
  @Test
  public void testSetUserConfig() {
    // Arrange
    PasswdUserDatabase passwdUserDatabase = new PasswdUserDatabase();
    UserConfig userConfig = new UserConfig();

    // Act
    passwdUserDatabase.setUserConfig(userConfig);

    // Assert
    assertSame(userConfig, passwdUserDatabase.getUserConfig());
  }

  /**
   * Test {@link PasswdUserDatabase#getHome(String)}.
   * <p>
   * Method under test: {@link PasswdUserDatabase#getHome(String)}
   */
  @Test
  public void testGetHome() {
    // Arrange, Act and Assert
    assertNull((new PasswdUserDatabase()).getHome("User"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link PasswdUserDatabase}
   *   <li>{@link PasswdUserDatabase#getUserConfig()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new PasswdUserDatabase()).getUserConfig());
  }
}
