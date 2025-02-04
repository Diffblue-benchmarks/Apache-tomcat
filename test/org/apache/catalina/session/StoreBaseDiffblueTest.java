package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.beans.PropertyChangeListener;
import org.apache.catalina.Manager;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.junit.Test;

public class StoreBaseDiffblueTest {
  /**
   * Test {@link StoreBase#getStoreName()}.
   * <p>
   * Method under test: {@link StoreBase#getStoreName()}
   */
  @Test
  public void testGetStoreName() {
    // Arrange, Act and Assert
    assertEquals("dataSourceStore", (new DataSourceStore()).getStoreName());
  }

  /**
   * Test {@link StoreBase#setManager(Manager)}.
   * <p>
   * Method under test: {@link StoreBase#setManager(Manager)}
   */
  @Test
  public void testSetManager() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();
    BackupManager manager = new BackupManager();

    // Act
    dataSourceStore.setManager(manager);

    // Assert
    assertSame(manager, dataSourceStore.getManager());
  }

  /**
   * Test {@link StoreBase#getManager()}.
   * <p>
   * Method under test: {@link StoreBase#getManager()}
   */
  @Test
  public void testGetManager() {
    // Arrange, Act and Assert
    assertNull((new DataSourceStore()).getManager());
  }

  /**
   * Test {@link StoreBase#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link StoreBase#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();
    NamingContextListener listener = new NamingContextListener();

    // Act
    dataSourceStore.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = dataSourceStore.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link StoreBase#toString()}.
   * <ul>
   *   <li>Given {@link DataSourceStore} (default constructor).</li>
   *   <li>Then return {@code DataSourceStore[Manager is null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreBase#toString()}
   */
  @Test
  public void testToString_givenDataSourceStore_thenReturnDataSourceStoreManagerIsNull() {
    // Arrange, Act and Assert
    assertEquals("DataSourceStore[Manager is null]", (new DataSourceStore()).toString());
  }

  /**
   * Test {@link StoreBase#toString()}.
   * <ul>
   *   <li>Then return {@code DataSourceStore[BackupManager[Container is null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreBase#toString()}
   */
  @Test
  public void testToString_thenReturnDataSourceStoreBackupManagerContainerIsNull() {
    // Arrange
    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(new BackupManager());

    // Act and Assert
    assertEquals("DataSourceStore[BackupManager[Container is null]]", dataSourceStore.toString());
  }

  /**
   * Test {@link StoreBase#toString()}.
   * <ul>
   *   <li>Then return {@code DataSourceStore[BackupManager[StandardContext[null]]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreBase#toString()}
   */
  @Test
  public void testToString_thenReturnDataSourceStoreBackupManagerStandardContextNull() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DataSourceStore dataSourceStore = new DataSourceStore();
    dataSourceStore.setManager(manager);

    // Act and Assert
    assertEquals("DataSourceStore[BackupManager[StandardContext[null]]]", dataSourceStore.toString());
  }
}
