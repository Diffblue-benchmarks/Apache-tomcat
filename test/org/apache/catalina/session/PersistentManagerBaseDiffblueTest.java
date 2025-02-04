package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.catalina.Manager;
import org.apache.catalina.Store;
import org.apache.catalina.manager.DummyProxySession;
import org.junit.Test;

public class PersistentManagerBaseDiffblueTest {
  /**
   * Test {@link PersistentManagerBase#getMaxIdleBackup()}.
   * <p>
   * Method under test: {@link PersistentManagerBase#getMaxIdleBackup()}
   */
  @Test
  public void testGetMaxIdleBackup() {
    // Arrange, Act and Assert
    assertEquals(-1, (new PersistentManager()).getMaxIdleBackup());
  }

  /**
   * Test {@link PersistentManagerBase#setMaxIdleBackup(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MaxIdleBackup is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMaxIdleBackup(int)}
   */
  @Test
  public void testSetMaxIdleBackup_whenMinusOne_thenPersistentManagerMaxIdleBackupIsMinusOne() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMaxIdleBackup(-1);

    // Assert that nothing has changed
    assertEquals(-1, persistentManager.getMaxIdleBackup());
  }

  /**
   * Test {@link PersistentManagerBase#setMaxIdleBackup(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MaxIdleBackup is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMaxIdleBackup(int)}
   */
  @Test
  public void testSetMaxIdleBackup_whenOne_thenPersistentManagerMaxIdleBackupIsOne() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMaxIdleBackup(1);

    // Assert
    assertEquals(1, persistentManager.getMaxIdleBackup());
  }

  /**
   * Test {@link PersistentManagerBase#getMaxIdleSwap()}.
   * <p>
   * Method under test: {@link PersistentManagerBase#getMaxIdleSwap()}
   */
  @Test
  public void testGetMaxIdleSwap() {
    // Arrange, Act and Assert
    assertEquals(-1, (new PersistentManager()).getMaxIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#setMaxIdleSwap(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MaxIdleSwap is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMaxIdleSwap(int)}
   */
  @Test
  public void testSetMaxIdleSwap_whenMinusOne_thenPersistentManagerMaxIdleSwapIsMinusOne() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMaxIdleSwap(-1);

    // Assert that nothing has changed
    assertEquals(-1, persistentManager.getMaxIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#setMaxIdleSwap(int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MaxIdleSwap is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMaxIdleSwap(int)}
   */
  @Test
  public void testSetMaxIdleSwap_whenThree_thenPersistentManagerMaxIdleSwapIsThree() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMaxIdleSwap(3);

    // Assert
    assertEquals(3, persistentManager.getMaxIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#getMinIdleSwap()}.
   * <p>
   * Method under test: {@link PersistentManagerBase#getMinIdleSwap()}
   */
  @Test
  public void testGetMinIdleSwap() {
    // Arrange, Act and Assert
    assertEquals(-1, (new PersistentManager()).getMinIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#setMinIdleSwap(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MinIdleSwap is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMinIdleSwap(int)}
   */
  @Test
  public void testSetMinIdleSwap_whenMinusOne_thenPersistentManagerMinIdleSwapIsMinusOne() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMinIdleSwap(-1);

    // Assert that nothing has changed
    assertEquals(-1, persistentManager.getMinIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#setMinIdleSwap(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link PersistentManager} (default constructor) MinIdleSwap is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setMinIdleSwap(int)}
   */
  @Test
  public void testSetMinIdleSwap_whenOne_thenPersistentManagerMinIdleSwapIsOne() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setMinIdleSwap(1);

    // Assert
    assertEquals(1, persistentManager.getMinIdleSwap());
  }

  /**
   * Test {@link PersistentManagerBase#isLoaded(String)}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#isLoaded(String)}
   */
  @Test
  public void testIsLoaded_givenPersistentManager_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new PersistentManager()).isLoaded("42"));
  }

  /**
   * Test {@link PersistentManagerBase#isLoaded(String)}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#isLoaded(String)}
   */
  @Test
  public void testIsLoaded_givenPersistentManager_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new PersistentManager()).isLoaded(null));
  }

  /**
   * Test {@link PersistentManagerBase#isLoaded(String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#isLoaded(String)}
   */
  @Test
  public void testIsLoaded_thenReturnTrue() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();
    persistentManager.add(new DummyProxySession("42"));

    // Act and Assert
    assertTrue(persistentManager.isLoaded("42"));
  }

  /**
   * Test {@link PersistentManagerBase#getName()}.
   * <p>
   * Method under test: {@link PersistentManagerBase#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("PersistentManager", (new PersistentManager()).getName());
  }

  /**
   * Test {@link PersistentManagerBase#setStore(Store)}.
   * <ul>
   *   <li>When {@link DataSourceStore} (default constructor).</li>
   *   <li>Then {@link DataSourceStore} (default constructor) Manager {@link PersistentManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setStore(Store)}
   */
  @Test
  public void testSetStore_whenDataSourceStore_thenDataSourceStoreManagerPersistentManager() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();
    DataSourceStore store = new DataSourceStore();

    // Act
    persistentManager.setStore(store);

    // Assert
    Manager manager = store.getManager();
    assertTrue(manager instanceof PersistentManager);
    assertSame(store, persistentManager.getStore());
    assertSame(persistentManager, manager);
  }

  /**
   * Test {@link PersistentManagerBase#getStore()}.
   * <p>
   * Method under test: {@link PersistentManagerBase#getStore()}
   */
  @Test
  public void testGetStore() {
    // Arrange, Act and Assert
    assertNull((new PersistentManager()).getStore());
  }

  /**
   * Test {@link PersistentManagerBase#getSaveOnRestart()}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor) SaveOnRestart is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#getSaveOnRestart()}
   */
  @Test
  public void testGetSaveOnRestart_givenPersistentManagerSaveOnRestartIsFalse_thenReturnFalse() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();
    persistentManager.setSaveOnRestart(false);

    // Act and Assert
    assertFalse(persistentManager.getSaveOnRestart());
  }

  /**
   * Test {@link PersistentManagerBase#getSaveOnRestart()}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#getSaveOnRestart()}
   */
  @Test
  public void testGetSaveOnRestart_givenPersistentManager_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new PersistentManager()).getSaveOnRestart());
  }

  /**
   * Test {@link PersistentManagerBase#setSaveOnRestart(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then not {@link PersistentManager} (default constructor) SaveOnRestart.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setSaveOnRestart(boolean)}
   */
  @Test
  public void testSetSaveOnRestart_whenFalse_thenNotPersistentManagerSaveOnRestart() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setSaveOnRestart(false);

    // Assert
    assertFalse(persistentManager.getSaveOnRestart());
  }

  /**
   * Test {@link PersistentManagerBase#setSaveOnRestart(boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then {@link PersistentManager} (default constructor) SaveOnRestart.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#setSaveOnRestart(boolean)}
   */
  @Test
  public void testSetSaveOnRestart_whenTrue_thenPersistentManagerSaveOnRestart() {
    // Arrange
    PersistentManager persistentManager = new PersistentManager();

    // Act
    persistentManager.setSaveOnRestart(true);

    // Assert that nothing has changed
    assertTrue(persistentManager.getSaveOnRestart());
  }

  /**
   * Test {@link PersistentManagerBase#findSession(String)}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#findSession(String)}
   */
  @Test
  public void testFindSession_givenPersistentManager_when42_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new PersistentManager()).findSession("42"));
  }

  /**
   * Test {@link PersistentManagerBase#findSession(String)}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#findSession(String)}
   */
  @Test
  public void testFindSession_givenPersistentManager_whenNull_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new PersistentManager()).findSession(null));
  }

  /**
   * Test {@link PersistentManagerBase#swapIn(String)}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentManagerBase#swapIn(String)}
   */
  @Test
  public void testSwapIn_givenPersistentManager_when42_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new PersistentManager()).swapIn("42"));
  }
}
