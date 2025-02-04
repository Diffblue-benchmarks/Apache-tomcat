package org.apache.catalina.ha.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.regex.PatternSyntaxException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.ClusterMessage;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.session.StandardSessionFacade;
import org.junit.Test;

public class BackupManagerDiffblueTest {
  /**
   * Test new {@link BackupManager} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BackupManager}
   */
  @Test
  public void testNewBackupManager() {
    // Arrange and Act
    BackupManager actualBackupManager = new BackupManager();

    // Assert
    assertEquals("NEW", actualBackupManager.getStateName());
    assertEquals("SHA1PRNG", actualBackupManager.getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.ha.session.BackupManager", actualBackupManager.getClassName());
    assertEquals("sync, use_ack", actualBackupManager.getMapSendOptionsName());
    assertNull(actualBackupManager.getName());
    assertNull(actualBackupManager.getJvmRoute());
    assertNull(actualBackupManager.getSecureRandomClass());
    assertNull(actualBackupManager.getSecureRandomProvider());
    assertNull(actualBackupManager.getSessionAttributeNameFilter());
    assertNull(actualBackupManager.getSessionAttributeValueClassNameFilter());
    assertNull(actualBackupManager.getObjectName());
    assertNull(actualBackupManager.getContext());
    assertNull(actualBackupManager.getEngine());
    assertNull(actualBackupManager.getSessionIdGenerator());
    assertNull(actualBackupManager.getCluster());
    assertEquals(-1, actualBackupManager.getMaxActiveSessions());
    assertEquals(0, actualBackupManager.getActiveSessions());
    assertEquals(0, actualBackupManager.getMaxActive());
    assertEquals(0, actualBackupManager.getRejectedSessions());
    assertEquals(0, actualBackupManager.getSessionAverageAliveTime());
    assertEquals(0, actualBackupManager.getSessionCreateRate());
    assertEquals(0, actualBackupManager.getSessionExpireRate());
    assertEquals(0, actualBackupManager.getSessionMaxAliveTime());
    assertEquals(0, actualBackupManager.getInvalidatedSessions().length);
    assertEquals(0, actualBackupManager.findLifecycleListeners().length);
    assertEquals(0L, actualBackupManager.getExpiredSessions());
    assertEquals(0L, actualBackupManager.getProcessingTime());
    assertEquals(0L, actualBackupManager.getSessionCounter());
    assertEquals(15000L, actualBackupManager.getRpcTimeout());
    assertEquals(5000L, actualBackupManager.getAccessTimeout());
    assertEquals(6, actualBackupManager.getMapSendOptions());
    assertEquals(6, actualBackupManager.getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, actualBackupManager.getState());
    assertFalse(actualBackupManager.isTerminateOnStartFailure());
    assertFalse(actualBackupManager.isRecordAllActions());
    assertFalse(actualBackupManager.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualBackupManager.getPersistAuthentication());
    assertFalse(actualBackupManager.getSessionActivityCheck());
    assertFalse(actualBackupManager.getSessionLastAccessAtStart());
    assertFalse(actualBackupManager.getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualBackupManager.isNotifyListenersOnReplication());
    assertTrue(actualBackupManager.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualBackupManager.getThrowOnFailure());
  }

  /**
   * Test {@link BackupManager#requestCompleted(String)}.
   * <p>
   * Method under test: {@link BackupManager#requestCompleted(String)}
   */
  @Test
  public void testRequestCompleted() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).requestCompleted("42"));
  }

  /**
   * Test {@link BackupManager#createEmptySession()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#createEmptySession()}
   */
  @Test
  public void testCreateEmptySession_givenBackupManager() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    Session actualCreateEmptySessionResult = backupManager.createEmptySession();

    // Assert
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).diffLock instanceof WriteLock);
    Manager manager = actualCreateEmptySessionResult.getManager();
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateEmptySessionResult instanceof DeltaSession);
    assertTrue(actualCreateEmptySessionResult.getSession() instanceof StandardSessionFacade);
    assertNull(actualCreateEmptySessionResult.getAuthType());
    assertNull(actualCreateEmptySessionResult.getId());
    assertNull(actualCreateEmptySessionResult.getIdInternal());
    assertNull(actualCreateEmptySessionResult.getPrincipal());
    assertEquals(-1, actualCreateEmptySessionResult.getMaxInactiveInterval());
    assertEquals(0L, actualCreateEmptySessionResult.getCreationTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getLastAccessedTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getThisAccessedTimeInternal());
    assertEquals(0L, ((DeltaSession) actualCreateEmptySessionResult).getVersion());
    assertFalse(actualCreateEmptySessionResult.getNoteNames().hasNext());
    assertFalse(actualCreateEmptySessionResult.isValid());
    assertFalse(((DeltaSession) actualCreateEmptySessionResult).isDirty());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isDiffable());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isPrimarySession());
    assertSame(backupManager, manager);
  }

  /**
   * Test {@link BackupManager#createEmptySession()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) RecordAllActions is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#createEmptySession()}
   */
  @Test
  public void testCreateEmptySession_givenBackupManagerRecordAllActionsIsTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setRecordAllActions(true);

    // Act
    Session actualCreateEmptySessionResult = backupManager.createEmptySession();

    // Assert
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).diffLock instanceof WriteLock);
    Manager manager = actualCreateEmptySessionResult.getManager();
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateEmptySessionResult instanceof DeltaSession);
    assertTrue(actualCreateEmptySessionResult.getSession() instanceof StandardSessionFacade);
    assertNull(actualCreateEmptySessionResult.getAuthType());
    assertNull(actualCreateEmptySessionResult.getId());
    assertNull(actualCreateEmptySessionResult.getIdInternal());
    assertNull(actualCreateEmptySessionResult.getPrincipal());
    assertEquals(-1, actualCreateEmptySessionResult.getMaxInactiveInterval());
    assertEquals(0L, actualCreateEmptySessionResult.getCreationTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getLastAccessedTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getThisAccessedTimeInternal());
    assertEquals(0L, ((DeltaSession) actualCreateEmptySessionResult).getVersion());
    assertFalse(actualCreateEmptySessionResult.getNoteNames().hasNext());
    assertFalse(actualCreateEmptySessionResult.isValid());
    assertFalse(((DeltaSession) actualCreateEmptySessionResult).isDirty());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isDiffable());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isPrimarySession());
    assertSame(backupManager, manager);
  }

  /**
   * Test {@link BackupManager#createEmptySession()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) SessionActivityCheck is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#createEmptySession()}
   */
  @Test
  public void testCreateEmptySession_givenBackupManagerSessionActivityCheckIsTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionActivityCheck(true);

    // Act
    Session actualCreateEmptySessionResult = backupManager.createEmptySession();

    // Assert
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).diffLock instanceof WriteLock);
    Manager manager = actualCreateEmptySessionResult.getManager();
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateEmptySessionResult instanceof DeltaSession);
    assertTrue(actualCreateEmptySessionResult.getSession() instanceof StandardSessionFacade);
    assertNull(actualCreateEmptySessionResult.getAuthType());
    assertNull(actualCreateEmptySessionResult.getId());
    assertNull(actualCreateEmptySessionResult.getIdInternal());
    assertNull(actualCreateEmptySessionResult.getPrincipal());
    assertEquals(-1, actualCreateEmptySessionResult.getMaxInactiveInterval());
    assertEquals(0L, actualCreateEmptySessionResult.getCreationTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getLastAccessedTimeInternal());
    assertEquals(0L, actualCreateEmptySessionResult.getThisAccessedTimeInternal());
    assertEquals(0L, ((DeltaSession) actualCreateEmptySessionResult).getVersion());
    assertFalse(actualCreateEmptySessionResult.getNoteNames().hasNext());
    assertFalse(actualCreateEmptySessionResult.isValid());
    assertFalse(((DeltaSession) actualCreateEmptySessionResult).isDirty());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isDiffable());
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).isPrimarySession());
    assertSame(backupManager, manager);
  }

  /**
   * Test {@link BackupManager#startInternal()}.
   * <ul>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#startInternal()}
   */
  @Test
  public void testStartInternal_thenThrowLifecycleException() throws LifecycleException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertThrows(LifecycleException.class, () -> backupManager.startInternal());
  }

  /**
   * Test {@link BackupManager#getMapName()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null-map}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#getMapName()}
   */
  @Test
  public void testGetMapName_givenBackupManagerContextIsStandardContext_thenReturnNullMap() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());
    backupManager.setCluster(new SimpleTcpCluster());

    // Act and Assert
    assertEquals("null-map", backupManager.getMapName());
  }

  /**
   * Test {@link BackupManager#getMapName()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Name is {@code Name}.</li>
   *   <li>Then return {@code Name-map}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#getMapName()}
   */
  @Test
  public void testGetMapName_givenBackupManagerNameIsName_thenReturnNameMap() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setName("Name");
    backupManager.setCluster(new SimpleTcpCluster());

    // Act and Assert
    assertEquals("Name-map", backupManager.getMapName());
  }

  /**
   * Test {@link BackupManager#setMapSendOptions(String)} with {@code String}.
   * <p>
   * Method under test: {@link BackupManager#setMapSendOptions(String)}
   */
  @Test
  public void testSetMapSendOptionsWithString() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setMapSendOptions("42");

    // Assert
    assertEquals("udp, async, use_ack", backupManager.getMapSendOptionsName());
    assertEquals(42, backupManager.getMapSendOptions());
  }

  /**
   * Test {@link BackupManager#getMapSendOptionsName()}.
   * <p>
   * Method under test: {@link BackupManager#getMapSendOptionsName()}
   */
  @Test
  public void testGetMapSendOptionsName() {
    // Arrange, Act and Assert
    assertEquals("sync, use_ack", (new BackupManager()).getMapSendOptionsName());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BackupManager#setAccessTimeout(long)}
   *   <li>{@link BackupManager#setMapSendOptions(int)}
   *   <li>{@link BackupManager#setName(String)}
   *   <li>{@link BackupManager#setRpcTimeout(long)}
   *   <li>{@link BackupManager#setTerminateOnStartFailure(boolean)}
   *   <li>{@link BackupManager#messageDataReceived(ClusterMessage)}
   *   <li>{@link BackupManager#getAccessTimeout()}
   *   <li>{@link BackupManager#getMapSendOptions()}
   *   <li>{@link BackupManager#getName()}
   *   <li>{@link BackupManager#getRpcTimeout()}
   *   <li>{@link BackupManager#isTerminateOnStartFailure()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setAccessTimeout(1L);
    backupManager.setMapSendOptions(3);
    backupManager.setName("Name");
    backupManager.setRpcTimeout(1L);
    backupManager.setTerminateOnStartFailure(true);
    backupManager.messageDataReceived(
        new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"));
    long actualAccessTimeout = backupManager.getAccessTimeout();
    int actualMapSendOptions = backupManager.getMapSendOptions();
    String actualName = backupManager.getName();
    long actualRpcTimeout = backupManager.getRpcTimeout();

    // Assert
    assertEquals("Name", actualName);
    assertEquals(1L, actualAccessTimeout);
    assertEquals(1L, actualRpcTimeout);
    assertEquals(3, actualMapSendOptions);
    assertTrue(backupManager.isTerminateOnStartFailure());
  }

  /**
   * Test {@link BackupManager#getInvalidatedSessions()}.
   * <p>
   * Method under test: {@link BackupManager#getInvalidatedSessions()}
   */
  @Test
  public void testGetInvalidatedSessions() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getInvalidatedSessions().length);
  }

  /**
   * Test {@link BackupManager#cloneFromTemplate()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#cloneFromTemplate()}
   */
  @Test
  public void testCloneFromTemplate_givenBackupManager() {
    // Arrange and Act
    ClusterManager actualCloneFromTemplateResult = (new BackupManager()).cloneFromTemplate();

    // Assert
    assertTrue(actualCloneFromTemplateResult instanceof BackupManager);
    assertEquals("Clone-from-null", actualCloneFromTemplateResult.getName());
    assertEquals("NEW", ((BackupManager) actualCloneFromTemplateResult).getStateName());
    assertEquals("SHA1PRNG", ((BackupManager) actualCloneFromTemplateResult).getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.ha.session.BackupManager",
        ((BackupManager) actualCloneFromTemplateResult).getClassName());
    assertEquals("sync, use_ack", ((BackupManager) actualCloneFromTemplateResult).getMapSendOptionsName());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getJvmRoute());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomClass());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomProvider());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSessionAttributeNameFilter());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSessionAttributeValueClassNameFilter());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getObjectName());
    assertNull(actualCloneFromTemplateResult.getContext());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getEngine());
    assertNull(actualCloneFromTemplateResult.getSessionIdGenerator());
    assertNull(actualCloneFromTemplateResult.getCluster());
    assertEquals(-1, ((BackupManager) actualCloneFromTemplateResult).getMaxActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getMaxActive());
    assertEquals(0, actualCloneFromTemplateResult.getRejectedSessions());
    assertEquals(0, actualCloneFromTemplateResult.getSessionAverageAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getSessionCreateRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionExpireRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionMaxAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getInvalidatedSessions().length);
    assertEquals(0, ((BackupManager) actualCloneFromTemplateResult).findLifecycleListeners().length);
    assertEquals(0L, actualCloneFromTemplateResult.getExpiredSessions());
    assertEquals(0L, actualCloneFromTemplateResult.getSessionCounter());
    assertEquals(0L, ((BackupManager) actualCloneFromTemplateResult).getProcessingTime());
    assertEquals(15000L, ((BackupManager) actualCloneFromTemplateResult).getRpcTimeout());
    assertEquals(5000L, ((BackupManager) actualCloneFromTemplateResult).getAccessTimeout());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getMapSendOptions());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, ((BackupManager) actualCloneFromTemplateResult).getState());
    assertFalse(actualCloneFromTemplateResult.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualCloneFromTemplateResult.getSessionActivityCheck());
    assertFalse(actualCloneFromTemplateResult.getSessionLastAccessAtStart());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isTerminateOnStartFailure());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isRecordAllActions());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getPersistAuthentication());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualCloneFromTemplateResult.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualCloneFromTemplateResult.isNotifyListenersOnReplication());
    assertTrue(((BackupManager) actualCloneFromTemplateResult).getThrowOnFailure());
  }

  /**
   * Test {@link BackupManager#cloneFromTemplate()}.
   * <ul>
   *   <li>Then return SessionAttributeNameFilter is {@code maxActiveSessions}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#cloneFromTemplate()}
   */
  @Test
  public void testCloneFromTemplate_thenReturnSessionAttributeNameFilterIsMaxActiveSessions()
      throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("maxActiveSessions");

    // Act
    ClusterManager actualCloneFromTemplateResult = backupManager.cloneFromTemplate();

    // Assert
    assertTrue(actualCloneFromTemplateResult instanceof BackupManager);
    assertEquals("Clone-from-null", actualCloneFromTemplateResult.getName());
    assertEquals("NEW", ((BackupManager) actualCloneFromTemplateResult).getStateName());
    assertEquals("SHA1PRNG", ((BackupManager) actualCloneFromTemplateResult).getSecureRandomAlgorithm());
    assertEquals("maxActiveSessions", ((BackupManager) actualCloneFromTemplateResult).getSessionAttributeNameFilter());
    assertEquals("org.apache.catalina.ha.session.BackupManager",
        ((BackupManager) actualCloneFromTemplateResult).getClassName());
    assertEquals("sync, use_ack", ((BackupManager) actualCloneFromTemplateResult).getMapSendOptionsName());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getJvmRoute());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomClass());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomProvider());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSessionAttributeValueClassNameFilter());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getObjectName());
    assertNull(actualCloneFromTemplateResult.getContext());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getEngine());
    assertNull(actualCloneFromTemplateResult.getSessionIdGenerator());
    assertNull(actualCloneFromTemplateResult.getCluster());
    assertEquals(-1, ((BackupManager) actualCloneFromTemplateResult).getMaxActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getMaxActive());
    assertEquals(0, actualCloneFromTemplateResult.getRejectedSessions());
    assertEquals(0, actualCloneFromTemplateResult.getSessionAverageAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getSessionCreateRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionExpireRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionMaxAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getInvalidatedSessions().length);
    assertEquals(0, ((BackupManager) actualCloneFromTemplateResult).findLifecycleListeners().length);
    assertEquals(0L, actualCloneFromTemplateResult.getExpiredSessions());
    assertEquals(0L, actualCloneFromTemplateResult.getSessionCounter());
    assertEquals(0L, ((BackupManager) actualCloneFromTemplateResult).getProcessingTime());
    assertEquals(15000L, ((BackupManager) actualCloneFromTemplateResult).getRpcTimeout());
    assertEquals(5000L, ((BackupManager) actualCloneFromTemplateResult).getAccessTimeout());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getMapSendOptions());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, ((BackupManager) actualCloneFromTemplateResult).getState());
    assertFalse(actualCloneFromTemplateResult.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualCloneFromTemplateResult.getSessionActivityCheck());
    assertFalse(actualCloneFromTemplateResult.getSessionLastAccessAtStart());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isTerminateOnStartFailure());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isRecordAllActions());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getPersistAuthentication());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualCloneFromTemplateResult.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualCloneFromTemplateResult.isNotifyListenersOnReplication());
    assertTrue(((BackupManager) actualCloneFromTemplateResult).getThrowOnFailure());
  }

  /**
   * Test {@link BackupManager#cloneFromTemplate()}.
   * <ul>
   *   <li>Then return SessionAttributeValueClassNameFilter is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackupManager#cloneFromTemplate()}
   */
  @Test
  public void testCloneFromTemplate_thenReturnSessionAttributeValueClassNameFilterIs42() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeValueClassNameFilter("42");

    // Act
    ClusterManager actualCloneFromTemplateResult = backupManager.cloneFromTemplate();

    // Assert
    assertTrue(actualCloneFromTemplateResult instanceof BackupManager);
    assertEquals("42", ((BackupManager) actualCloneFromTemplateResult).getSessionAttributeValueClassNameFilter());
    assertEquals("Clone-from-null", actualCloneFromTemplateResult.getName());
    assertEquals("NEW", ((BackupManager) actualCloneFromTemplateResult).getStateName());
    assertEquals("SHA1PRNG", ((BackupManager) actualCloneFromTemplateResult).getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.ha.session.BackupManager",
        ((BackupManager) actualCloneFromTemplateResult).getClassName());
    assertEquals("sync, use_ack", ((BackupManager) actualCloneFromTemplateResult).getMapSendOptionsName());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getJvmRoute());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomClass());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSecureRandomProvider());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getSessionAttributeNameFilter());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getObjectName());
    assertNull(actualCloneFromTemplateResult.getContext());
    assertNull(((BackupManager) actualCloneFromTemplateResult).getEngine());
    assertNull(actualCloneFromTemplateResult.getSessionIdGenerator());
    assertNull(actualCloneFromTemplateResult.getCluster());
    assertEquals(-1, ((BackupManager) actualCloneFromTemplateResult).getMaxActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getMaxActive());
    assertEquals(0, actualCloneFromTemplateResult.getRejectedSessions());
    assertEquals(0, actualCloneFromTemplateResult.getSessionAverageAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getSessionCreateRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionExpireRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionMaxAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getInvalidatedSessions().length);
    assertEquals(0, ((BackupManager) actualCloneFromTemplateResult).findLifecycleListeners().length);
    assertEquals(0L, actualCloneFromTemplateResult.getExpiredSessions());
    assertEquals(0L, actualCloneFromTemplateResult.getSessionCounter());
    assertEquals(0L, ((BackupManager) actualCloneFromTemplateResult).getProcessingTime());
    assertEquals(15000L, ((BackupManager) actualCloneFromTemplateResult).getRpcTimeout());
    assertEquals(5000L, ((BackupManager) actualCloneFromTemplateResult).getAccessTimeout());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getMapSendOptions());
    assertEquals(6, ((BackupManager) actualCloneFromTemplateResult).getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, ((BackupManager) actualCloneFromTemplateResult).getState());
    assertFalse(actualCloneFromTemplateResult.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualCloneFromTemplateResult.getSessionActivityCheck());
    assertFalse(actualCloneFromTemplateResult.getSessionLastAccessAtStart());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isTerminateOnStartFailure());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).isRecordAllActions());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getPersistAuthentication());
    assertFalse(((BackupManager) actualCloneFromTemplateResult).getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualCloneFromTemplateResult.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualCloneFromTemplateResult.isNotifyListenersOnReplication());
    assertTrue(((BackupManager) actualCloneFromTemplateResult).getThrowOnFailure());
  }
}
