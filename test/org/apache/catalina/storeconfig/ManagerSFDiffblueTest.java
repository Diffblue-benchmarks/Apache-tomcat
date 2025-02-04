package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.session.StandardManager;
import org.junit.Test;

public class ManagerSFDiffblueTest {
  /**
   * Test {@link ManagerSF#isDefaultManager(StandardManager)}.
   * <ul>
   *   <li>Given minus one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerSF#isDefaultManager(StandardManager)}
   */
  @Test
  public void testIsDefaultManager_givenMinusOne_thenReturnTrue() {
    // Arrange
    ManagerSF managerSF = new ManagerSF();

    StandardManager smanager = new StandardManager();
    smanager.setPathname("SESSIONS.ser");
    smanager.setMaxActiveSessions(-1);

    // Act and Assert
    assertTrue(managerSF.isDefaultManager(smanager));
  }

  /**
   * Test {@link ManagerSF#isDefaultManager(StandardManager)}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>When {@link StandardManager} (default constructor) MaxActiveSessions is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerSF#isDefaultManager(StandardManager)}
   */
  @Test
  public void testIsDefaultManager_givenThree_whenStandardManagerMaxActiveSessionsIsThree() {
    // Arrange
    ManagerSF managerSF = new ManagerSF();

    StandardManager smanager = new StandardManager();
    smanager.setPathname("SESSIONS.ser");
    smanager.setMaxActiveSessions(3);

    // Act and Assert
    assertFalse(managerSF.isDefaultManager(smanager));
  }

  /**
   * Test {@link ManagerSF#isDefaultManager(StandardManager)}.
   * <ul>
   *   <li>When {@link StandardManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerSF#isDefaultManager(StandardManager)}
   */
  @Test
  public void testIsDefaultManager_whenStandardManager_thenReturnFalse() {
    // Arrange
    ManagerSF managerSF = new ManagerSF();

    // Act and Assert
    assertFalse(managerSF.isDefaultManager(new StandardManager()));
  }

  /**
   * Test new {@link ManagerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ManagerSF}
   */
  @Test
  public void testNewManagerSF() {
    // Arrange and Act
    ManagerSF actualManagerSF = new ManagerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualManagerSF.getInfo());
    assertNull(actualManagerSF.getRegistry());
  }
}
