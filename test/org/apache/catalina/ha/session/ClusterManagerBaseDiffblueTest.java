package org.apache.catalina.ha.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.regex.PatternSyntaxException;
import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.junit.Test;

public class ClusterManagerBaseDiffblueTest {
  /**
   * Test {@link ClusterManagerBase#getCluster()}.
   * <p>
   * Method under test: {@link ClusterManagerBase#getCluster()}
   */
  @Test
  public void testGetCluster() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getCluster());
  }

  /**
   * Test {@link ClusterManagerBase#setCluster(CatalinaCluster)}.
   * <p>
   * Method under test: {@link ClusterManagerBase#setCluster(CatalinaCluster)}
   */
  @Test
  public void testSetCluster() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    SimpleTcpCluster cluster = new SimpleTcpCluster();

    // Act
    backupManager.setCluster(cluster);

    // Assert
    assertSame(cluster, backupManager.getCluster());
  }

  /**
   * Test {@link ClusterManagerBase#isNotifyListenersOnReplication()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#isNotifyListenersOnReplication()}
   */
  @Test
  public void testIsNotifyListenersOnReplication_givenBackupManager_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BackupManager()).isNotifyListenersOnReplication());
  }

  /**
   * Test {@link ClusterManagerBase#isNotifyListenersOnReplication()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#isNotifyListenersOnReplication()}
   */
  @Test
  public void testIsNotifyListenersOnReplication_thenReturnFalse() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setNotifyListenersOnReplication(false);

    // Act and Assert
    assertFalse(backupManager.isNotifyListenersOnReplication());
  }

  /**
   * Test {@link ClusterManagerBase#isRecordAllActions()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) RecordAllActions is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#isRecordAllActions()}
   */
  @Test
  public void testIsRecordAllActions_givenBackupManagerRecordAllActionsIsTrue_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setRecordAllActions(true);

    // Act and Assert
    assertTrue(backupManager.isRecordAllActions());
  }

  /**
   * Test {@link ClusterManagerBase#isRecordAllActions()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#isRecordAllActions()}
   */
  @Test
  public void testIsRecordAllActions_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).isRecordAllActions());
  }

  /**
   * Test {@link ClusterManagerBase#setRecordAllActions(boolean)}.
   * <p>
   * Method under test: {@link ClusterManagerBase#setRecordAllActions(boolean)}
   */
  @Test
  public void testSetRecordAllActions() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setRecordAllActions(true);

    // Assert
    assertTrue(backupManager.isRecordAllActions());
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders(Context)} with {@code Context}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders(Context)}
   */
  @Test
  public void testGetClassLoadersWithContext_givenWebappLoader_thenReturnArrayLengthIsOne() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setLoader(new WebappLoader());

    // Act and Assert
    assertEquals(1, ClusterManagerBase.getClassLoaders(context).length);
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders(Context)} with {@code Context}.
   * <ul>
   *   <li>Then return first element is not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders(Context)}
   */
  @Test
  public void testGetClassLoadersWithContext_thenReturnFirstElementIsNotNull() {
    // Arrange
    WebappLoader loader = new WebappLoader();
    loader.setLoaderInstance(new ParallelWebappClassLoader());

    StandardContext context = new StandardContext();
    context.setLoader(loader);

    // Act
    ClassLoader[] actualClassLoaders = ClusterManagerBase.getClassLoaders(context);

    // Assert
    assertNotNull(actualClassLoaders[0]);
    assertEquals(2, actualClassLoaders.length);
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders(Context)} with {@code Context}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders(Context)}
   */
  @Test
  public void testGetClassLoadersWithContext_whenStandardContext_thenReturnArrayLengthIsOne() {
    // Arrange, Act and Assert
    assertEquals(1, ClusterManagerBase.getClassLoaders(new StandardContext()).length);
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders()}.
   * <ul>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_thenReturnArrayLengthIsOne() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertEquals(1, backupManager.getClassLoaders().length);
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders()}.
   * <ul>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_thenReturnArrayLengthIsOne2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setLoader(new WebappLoader());

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act and Assert
    assertEquals(1, backupManager.getClassLoaders().length);
  }

  /**
   * Test {@link ClusterManagerBase#getClassLoaders()}.
   * <ul>
   *   <li>Then return first element is not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterManagerBase#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_thenReturnFirstElementIsNotNull() {
    // Arrange
    WebappLoader loader = new WebappLoader();
    loader.setLoaderInstance(new ParallelWebappClassLoader());

    StandardContext context = new StandardContext();
    context.setLoader(loader);

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act
    ClassLoader[] actualClassLoaders = backupManager.getClassLoaders();

    // Assert
    assertNotNull(actualClassLoaders[0]);
    assertEquals(2, actualClassLoaders.length);
  }

  /**
   * Test {@link ClusterManagerBase#clone(ClusterManagerBase)} with {@code ClusterManagerBase}.
   * <p>
   * Method under test: {@link ClusterManagerBase#clone(ClusterManagerBase)}
   */
  @Test
  public void testCloneWithClusterManagerBase() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    BackupManager copy = new BackupManager();

    // Act
    backupManager.clone(copy);

    // Assert
    assertEquals("Clone-from-null", copy.getName());
    assertNull(copy.getSessionAttributeNameFilter());
    assertNull(copy.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ClusterManagerBase#clone(ClusterManagerBase)} with {@code ClusterManagerBase}.
   * <p>
   * Method under test: {@link ClusterManagerBase#clone(ClusterManagerBase)}
   */
  @Test
  public void testCloneWithClusterManagerBase2() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("maxActiveSessions");
    BackupManager copy = new BackupManager();

    // Act
    backupManager.clone(copy);

    // Assert
    assertEquals("Clone-from-null", copy.getName());
    assertEquals("maxActiveSessions", copy.getSessionAttributeNameFilter());
    assertNull(copy.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ClusterManagerBase#clone(ClusterManagerBase)} with {@code ClusterManagerBase}.
   * <p>
   * Method under test: {@link ClusterManagerBase#clone(ClusterManagerBase)}
   */
  @Test
  public void testCloneWithClusterManagerBase3() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeValueClassNameFilter("42");
    BackupManager copy = new BackupManager();

    // Act
    backupManager.clone(copy);

    // Assert
    assertEquals("42", copy.getSessionAttributeValueClassNameFilter());
    assertEquals("Clone-from-null", copy.getName());
    assertNull(copy.getSessionAttributeNameFilter());
  }
}
