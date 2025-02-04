package org.apache.catalina;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.ha.session.BackupManager;
import org.junit.Test;

public class ManagerDiffblueTest {
  /**
   * Test {@link Manager#getNotifyBindingListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getNotifyBindingListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyBindingListenerOnUnchangedValue_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getNotifyBindingListenerOnUnchangedValue());
  }

  /**
   * Test {@link Manager#getNotifyBindingListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getNotifyBindingListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyBindingListenerOnUnchangedValue_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setNotifyBindingListenerOnUnchangedValue(true);

    // Act and Assert
    assertTrue(backupManager.getNotifyBindingListenerOnUnchangedValue());
  }

  /**
   * Test {@link Manager#getNotifyAttributeListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getNotifyAttributeListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyAttributeListenerOnUnchangedValue_givenBackupManager_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BackupManager()).getNotifyAttributeListenerOnUnchangedValue());
  }

  /**
   * Test {@link Manager#getNotifyAttributeListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getNotifyAttributeListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyAttributeListenerOnUnchangedValue_thenReturnFalse() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setNotifyAttributeListenerOnUnchangedValue(false);

    // Act and Assert
    assertFalse(backupManager.getNotifyAttributeListenerOnUnchangedValue());
  }

  /**
   * Test {@link Manager#getSessionActivityCheck()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getSessionActivityCheck()}
   */
  @Test
  public void testGetSessionActivityCheck_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getSessionActivityCheck());
  }

  /**
   * Test {@link Manager#getSessionActivityCheck()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getSessionActivityCheck()}
   */
  @Test
  public void testGetSessionActivityCheck_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionActivityCheck(true);

    // Act and Assert
    assertTrue(backupManager.getSessionActivityCheck());
  }

  /**
   * Test {@link Manager#getSessionLastAccessAtStart()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getSessionLastAccessAtStart()}
   */
  @Test
  public void testGetSessionLastAccessAtStart_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getSessionLastAccessAtStart());
  }

  /**
   * Test {@link Manager#getSessionLastAccessAtStart()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Manager#getSessionLastAccessAtStart()}
   */
  @Test
  public void testGetSessionLastAccessAtStart_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionLastAccessAtStart(true);

    // Act and Assert
    assertTrue(backupManager.getSessionLastAccessAtStart());
  }
}
