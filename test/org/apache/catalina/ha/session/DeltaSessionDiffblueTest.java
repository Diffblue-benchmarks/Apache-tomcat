package org.apache.catalina.ha.session;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import listeners.SessionListener;
import org.apache.catalina.Manager;
import org.apache.catalina.authenticator.SingleSignOnListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.session.PersistentManager;
import org.apache.catalina.session.StandardSessionFacade;
import org.apache.catalina.startup.FailedContext;
import org.apache.tomcat.websocket.server.WsSessionListener;
import org.junit.Test;

public class DeltaSessionDiffblueTest {
  /**
   * Test {@link DeltaSession#DeltaSession()}.
   * <p>
   * Method under test: {@link DeltaSession#DeltaSession()}
   */
  @Test
  public void testNewDeltaSession() {
    // Arrange and Act
    DeltaSession actualDeltaSession = new DeltaSession();

    // Assert
    assertTrue(actualDeltaSession.diffLock instanceof WriteLock);
    assertTrue(actualDeltaSession.getSession() instanceof StandardSessionFacade);
    assertNull(actualDeltaSession.getClassLoaders());
    assertNull(actualDeltaSession.getServletContext());
    assertNull(actualDeltaSession.getAuthType());
    assertNull(actualDeltaSession.getId());
    assertNull(actualDeltaSession.getIdInternal());
    assertNull(actualDeltaSession.getPrincipal());
    assertNull(actualDeltaSession.getManager());
    assertEquals(-1, actualDeltaSession.getMaxInactiveInterval());
    assertEquals(0L, actualDeltaSession.getVersion());
    assertEquals(0L, actualDeltaSession.getCreationTimeInternal());
    assertEquals(0L, actualDeltaSession.getLastAccessedTimeInternal());
    assertEquals(0L, actualDeltaSession.getThisAccessedTimeInternal());
    assertFalse(actualDeltaSession.getNoteNames().hasNext());
    assertFalse(actualDeltaSession.isDirty());
    assertFalse(actualDeltaSession.isValid());
    assertTrue(actualDeltaSession.isDiffable());
    assertTrue(actualDeltaSession.isPrimarySession());
  }

  /**
   * Test {@link DeltaSession#DeltaSession(Manager)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link BackupManager} (default constructor) RecordAllActions is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#DeltaSession(Manager)}
   */
  @Test
  public void testNewDeltaSession_givenTrue_whenBackupManagerRecordAllActionsIsTrue() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setRecordAllActions(true);

    // Act and Assert
    Manager manager2 = (new DeltaSession(manager)).getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertSame(manager, manager2);
  }

  /**
   * Test {@link DeltaSession#DeltaSession(Manager)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link BackupManager} (default constructor) SessionActivityCheck is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#DeltaSession(Manager)}
   */
  @Test
  public void testNewDeltaSession_givenTrue_whenBackupManagerSessionActivityCheckIsTrue() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setSessionActivityCheck(true);

    // Act and Assert
    Manager manager2 = (new DeltaSession(manager)).getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertSame(manager, manager2);
  }

  /**
   * Test {@link DeltaSession#DeltaSession(Manager)}.
   * <ul>
   *   <li>When {@link BackupManager} (default constructor).</li>
   *   <li>Then Manager return {@link BackupManager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#DeltaSession(Manager)}
   */
  @Test
  public void testNewDeltaSession_whenBackupManager_thenManagerReturnBackupManager() {
    // Arrange
    BackupManager manager = new BackupManager();

    // Act and Assert
    Manager manager2 = (new DeltaSession(manager)).getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertSame(manager, manager2);
  }

  /**
   * Test {@link DeltaSession#DeltaSession(Manager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then Session return {@link StandardSessionFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#DeltaSession(Manager)}
   */
  @Test
  public void testNewDeltaSession_whenNull_thenSessionReturnStandardSessionFacade() {
    // Arrange and Act
    DeltaSession actualDeltaSession = new DeltaSession(null);

    // Assert
    HttpSession session = actualDeltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertNull(actualDeltaSession.getClassLoaders());
    assertNull(session.getServletContext());
    assertNull(actualDeltaSession.getServletContext());
    assertNull(actualDeltaSession.getManager());
  }

  /**
   * Test {@link DeltaSession#createRequest(String, boolean)} with {@code String}, {@code boolean}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return SessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#createRequest(String, boolean)}
   */
  @Test
  public void testCreateRequestWithStringBoolean_when42_thenReturnSessionIdIs42() {
    // Arrange and Act
    DeltaRequest actualCreateRequestResult = (new DeltaSession()).createRequest("42", true);

    // Assert
    assertEquals("42", actualCreateRequestResult.getSessionId());
    assertEquals(0, actualCreateRequestResult.getSize());
  }

  /**
   * Test {@link DeltaSession#createRequest(String, boolean)} with {@code String}, {@code boolean}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return SessionId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#createRequest(String, boolean)}
   */
  @Test
  public void testCreateRequestWithStringBoolean_whenNull_thenReturnSessionIdIsNull() {
    // Arrange and Act
    DeltaRequest actualCreateRequestResult = (new DeltaSession()).createRequest(null, true);

    // Assert
    assertNull(actualCreateRequestResult.getSessionId());
    assertEquals(0, actualCreateRequestResult.getSize());
  }

  /**
   * Test {@link DeltaSession#isDirty()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isDirty()}
   */
  @Test
  public void testIsDirty_givenDeltaSessionNewTrueIsTrue_thenReturnTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act and Assert
    assertTrue(deltaSession.isDirty());
  }

  /**
   * Test {@link DeltaSession#isDirty()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isDirty()}
   */
  @Test
  public void testIsDirty_givenDeltaSession_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new DeltaSession()).isDirty());
  }

  /**
   * Test {@link DeltaSession#getClassLoaders()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);

    // Act and Assert
    assertEquals(1, deltaSession.getClassLoaders().length);
  }

  /**
   * Test {@link DeltaSession#getClassLoaders()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_givenDeltaSession_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new DeltaSession()).getClassLoaders());
  }

  /**
   * Test {@link DeltaSession#getClassLoaders()}.
   * <ul>
   *   <li>Given {@link PersistentManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_givenPersistentManagerContextIsStandardContext() {
    // Arrange
    PersistentManager manager = new PersistentManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);

    // Act and Assert
    assertEquals(1, deltaSession.getClassLoaders().length);
  }

  /**
   * Test {@link DeltaSession#getClassLoaders()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Loader is {@link WebappLoader} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#getClassLoaders()}
   */
  @Test
  public void testGetClassLoaders_givenStandardContextLoaderIsWebappLoader() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setLoader(new WebappLoader());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);

    // Act and Assert
    assertEquals(1, deltaSession.getClassLoaders().length);
  }

  /**
   * Test {@link DeltaSession#setOwner(Object)}.
   * <p>
   * Method under test: {@link DeltaSession#setOwner(Object)}
   */
  @Test
  public void testSetOwner() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession(new BackupManager());

    // Act
    deltaSession.setOwner(new BackupManager());

    // Assert that nothing has changed
    assertEquals(0L, deltaSession.getLastAccessedTimeInternal());
    assertEquals(0L, deltaSession.getThisAccessedTimeInternal());
  }

  /**
   * Test {@link DeltaSession#setOwner(Object)}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setOwner(Object)}
   */
  @Test
  public void testSetOwner_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setOwner(new BackupManager());

    // Assert
    assertTrue(deltaSession.getSession() instanceof StandardSessionFacade);
  }

  /**
   * Test {@link DeltaSession#setOwner(Object)}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} Session {@link StandardSessionFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setOwner(Object)}
   */
  @Test
  public void testSetOwner_givenDeltaSession_thenDeltaSessionSessionStandardSessionFacade() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setOwner(new BackupManager());

    // Assert
    assertTrue(deltaSession.getSession() instanceof StandardSessionFacade);
  }

  /**
   * Test {@link DeltaSession#setOwner(Object)}.
   * <ul>
   *   <li>Given {@link SimpleTcpCluster} (default constructor).</li>
   *   <li>When {@link BackupManager} (default constructor) Cluster is {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setOwner(Object)}
   */
  @Test
  public void testSetOwner_givenSimpleTcpCluster_whenBackupManagerClusterIsSimpleTcpCluster() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    BackupManager backupManager = new BackupManager();
    backupManager.setCluster(new SimpleTcpCluster());

    // Act
    deltaSession.setOwner(backupManager);

    // Assert
    assertTrue(deltaSession.getSession() instanceof StandardSessionFacade);
  }

  /**
   * Test {@link DeltaSession#setOwner(Object)}.
   * <ul>
   *   <li>When {@code Owner}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} LastAccessedTimeInternal is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setOwner(Object)}
   */
  @Test
  public void testSetOwner_whenOwner_thenDeltaSessionLastAccessedTimeInternalIsZero() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setOwner("Owner");

    // Assert that nothing has changed
    assertEquals(0L, deltaSession.getLastAccessedTimeInternal());
    assertEquals(0L, deltaSession.getThisAccessedTimeInternal());
  }

  /**
   * Test {@link DeltaSession#isAccessReplicate()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isAccessReplicate()}
   */
  @Test
  public void testIsAccessReplicate_givenDeltaSession_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new DeltaSession()).isAccessReplicate());
  }

  /**
   * Test {@link DeltaSession#accessEntry()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Cluster is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#accessEntry()}
   */
  @Test
  public void testAccessEntry_givenBackupManagerClusterIsNull() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.registerSessionAtReplicationValve(null);
    manager.setCluster(null);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);

    // Act
    deltaSession.accessEntry();

    // Assert
    assertFalse(deltaSession.isPrimarySession());
  }

  /**
   * Test {@link DeltaSession#accessEntry()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Cluster is {@link SimpleTcpCluster} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#accessEntry()}
   */
  @Test
  public void testAccessEntry_givenBackupManagerClusterIsSimpleTcpCluster() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.registerSessionAtReplicationValve(null);
    manager.setCluster(new SimpleTcpCluster());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);

    // Act
    deltaSession.accessEntry();

    // Assert
    assertFalse(deltaSession.isPrimarySession());
  }

  /**
   * Test {@link DeltaSession#accessEntry()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#accessEntry()}
   */
  @Test
  public void testAccessEntry_givenDeltaSession() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.accessEntry();

    // Assert
    assertFalse(deltaSession.isPrimarySession());
  }

  /**
   * Test {@link DeltaSession#setId(String)} with {@code id}.
   * <p>
   * Method under test: {@link DeltaSession#setId(String)}
   */
  @Test
  public void testSetIdWithId() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42");

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String)} with {@code id}.
   * <p>
   * Method under test: {@link DeltaSession#setId(String)}
   */
  @Test
  public void testSetIdWithId2() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42");

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42", true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify2() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42", true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify3() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42", true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify_givenBackupManagerContextIsFailedContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42", true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42", true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenBackupManagerContextIsFailedContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42");

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenBackupManagerContextIsStandardContext() throws IOException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42");

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationLifecycleListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenStandardContextAddApplicationLifecycleListenerListener() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    DeltaSession deltaSession = new DeltaSession(manager);

    // Act
    deltaSession.setId("42");

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", deltaSession.getDeltaRequest().getSessionId());
    assertEquals("42", deltaSession.getId());
    assertEquals("42", deltaSession.getIdInternal());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 0, 0, 0, 0, 0}, deltaSession.getDiff());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int)} with {@code interval}.
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithInterval() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setMaxInactiveInterval(42);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int)} with {@code interval}.
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithInterval2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setMaxInactiveInterval(42);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int, boolean)} with {@code interval}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int, boolean)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithIntervalAddDeltaRequest() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setMaxInactiveInterval(42, true);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int, boolean)} with {@code interval}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int, boolean)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithIntervalAddDeltaRequest2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setMaxInactiveInterval(42, true);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int, boolean)} with {@code interval}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int, boolean)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithIntervalAddDeltaRequest3() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setMaxInactiveInterval(42, true);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int, boolean)} with {@code interval}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int, boolean)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithIntervalAddDeltaRequest_givenDeltaSession_whenTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setMaxInactiveInterval(42, true);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int, boolean)} with {@code interval}, {@code addDeltaRequest}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int, boolean)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithIntervalAddDeltaRequest_whenFalse() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setMaxInactiveInterval(42, false);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int)} with {@code interval}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithInterval_givenDeltaSession() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setMaxInactiveInterval(42);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setMaxInactiveInterval(int)} with {@code interval}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveIntervalWithInterval_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setMaxInactiveInterval(42);

    // Assert
    HttpSession session = deltaSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(42, session.getMaxInactiveInterval());
    assertEquals(42, deltaSession.getMaxInactiveInterval());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    GenericPrincipal principal = new GenericPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    GenericPrincipal principal = new GenericPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, true);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, true);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest3() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setAuthType("principal", true);
    deltaSession.setNew(true, true);
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, true);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest_givenDeltaSession() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, true);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, true);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest_givenDeltaSession_whenFalse() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal, false);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal, boolean)} with {@code principal}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Then {@link DeltaSession#DeltaSession()} Principal is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal, boolean)}
   */
  @Test
  public void testSetPrincipalWithPrincipalAddDeltaRequest_thenDeltaSessionPrincipalIsNull() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setPrincipal(null, true);

    // Assert that nothing has changed
    assertNull(deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal_givenDeltaSession() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} AuthType {@code principal} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal_givenDeltaSessionAuthTypePrincipalIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setAuthType("principal", true);
    deltaSession.setNew(true, true);
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setPrincipal(Principal)} with {@code principal}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} Principal is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipalWithPrincipal_whenNull_thenDeltaSessionPrincipalIsNull() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setPrincipal(null);

    // Assert that nothing has changed
    assertNull(deltaSession.getPrincipal());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String)} with {@code authType}.
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String)}
   */
  @Test
  public void testSetAuthTypeWithAuthType() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setAuthType("Auth Type");

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String)} with {@code authType}.
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String)}
   */
  @Test
  public void testSetAuthTypeWithAuthType2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setAuthType("Auth Type");

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String, boolean)} with {@code authType}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String, boolean)}
   */
  @Test
  public void testSetAuthTypeWithAuthTypeAddDeltaRequest() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setAuthType("Auth Type", true);

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String, boolean)} with {@code authType}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String, boolean)}
   */
  @Test
  public void testSetAuthTypeWithAuthTypeAddDeltaRequest2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setAuthType("Auth Type", true);

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String, boolean)} with {@code authType}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String, boolean)}
   */
  @Test
  public void testSetAuthTypeWithAuthTypeAddDeltaRequest_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setAuthType("Auth Type", true);

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String, boolean)} with {@code authType}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String, boolean)}
   */
  @Test
  public void testSetAuthTypeWithAuthTypeAddDeltaRequest_givenDeltaSession_whenFalse() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setAuthType("Auth Type", false);

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String, boolean)} with {@code authType}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String, boolean)}
   */
  @Test
  public void testSetAuthTypeWithAuthTypeAddDeltaRequest_givenDeltaSession_whenTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setAuthType("Auth Type", true);

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String)} with {@code authType}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String)}
   */
  @Test
  public void testSetAuthTypeWithAuthType_givenDeltaSession() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setAuthType("Auth Type");

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#setAuthType(String)} with {@code authType}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAuthType(String)}
   */
  @Test
  public void testSetAuthTypeWithAuthType_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setAuthType("Auth Type");

    // Assert
    assertEquals("Auth Type", deltaSession.getAuthType());
  }

  /**
   * Test {@link DeltaSession#isValid()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} MaxInactiveInterval is forty-two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isValid()}
   */
  @Test
  public void testIsValid_givenDeltaSessionMaxInactiveIntervalIsFortyTwo_thenReturnTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setMaxInactiveInterval(42);
    deltaSession.setValid(true);

    // Act and Assert
    assertTrue(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#isValid()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isValid()}
   */
  @Test
  public void testIsValid_givenDeltaSessionValidIsTrue_thenReturnTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(true);

    // Act and Assert
    assertTrue(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#isValid()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#isValid()}
   */
  @Test
  public void testIsValid_givenDeltaSession_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new DeltaSession()).isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.addSessionListener(new SingleSignOnListener("42"));
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify3() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.addSessionListener(new SingleSignOnListener("42"));
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster3() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster4() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster_givenBackupManagerContextIsFailedContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then not {@link DeltaSession#DeltaSession()} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster_givenDeltaSession_thenNotDeltaSessionValid() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.expire(true, true);

    // Assert that nothing has changed
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean, boolean)} with {@code notify}, {@code notifyCluster}.
   * <ul>
   *   <li>Then {@link DeltaSession#DeltaSession()} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean, boolean)}
   */
  @Test
  public void testExpireWithNotifyNotifyCluster_thenDeltaSessionValid() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true, true);

    // Assert that nothing has changed
    assertTrue(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify_givenBackupManagerContextIsFailedContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify_givenDeltaSessionValidIsTrue_thenDeltaSessionValid() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert that nothing has changed
    assertTrue(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then not {@link DeltaSession#DeltaSession()} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify_givenDeltaSession_thenNotDeltaSessionValid() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.expire(true);

    // Assert that nothing has changed
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test {@link DeltaSession#expire(boolean)} with {@code notify}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationLifecycleListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#expire(boolean)}
   */
  @Test
  public void testExpireWithNotify_givenStandardContextAddApplicationLifecycleListenerListener() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setManager(manager);
    deltaSession.setValid(true);

    // Act
    deltaSession.expire(true);

    // Assert
    Manager manager2 = deltaSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    assertEquals(1L, manager2.getExpiredSessions());
    assertEquals(1L, manager2.getSessionCounter());
    assertFalse(deltaSession.isValid());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeltaSession#setLastTimeReplicated(long)}
   *   <li>{@link DeltaSession#setPrimarySession(boolean)}
   *   <li>{@link DeltaSession#setVersion(long)}
   *   <li>{@link DeltaSession#lock()}
   *   <li>{@link DeltaSession#unlock()}
   *   <li>{@link DeltaSession#toString()}
   *   <li>{@link DeltaSession#getDeltaRequest()}
   *   <li>{@link DeltaSession#getLastTimeReplicated()}
   *   <li>{@link DeltaSession#getVersion()}
   *   <li>{@link DeltaSession#isDiffable()}
   *   <li>{@link DeltaSession#isPrimarySession()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setLastTimeReplicated(1L);
    deltaSession.setPrimarySession(true);
    deltaSession.setVersion(1L);
    deltaSession.lock();
    deltaSession.unlock();
    String actualToStringResult = deltaSession.toString();
    DeltaRequest actualDeltaRequest = deltaSession.getDeltaRequest();
    long actualLastTimeReplicated = deltaSession.getLastTimeReplicated();
    long actualVersion = deltaSession.getVersion();
    boolean actualIsDiffableResult = deltaSession.isDiffable();
    boolean actualIsPrimarySessionResult = deltaSession.isPrimarySession();

    // Assert
    assertEquals("DeltaSession[null]", actualToStringResult);
    assertNull(actualDeltaRequest.getSessionId());
    assertEquals(0, actualDeltaRequest.getSize());
    assertEquals(1L, actualLastTimeReplicated);
    assertEquals(1L, actualVersion);
    assertTrue(actualIsDiffableResult);
    assertTrue(actualIsPrimarySessionResult);
  }

  /**
   * Test {@link DeltaSession#replaceDeltaRequest(DeltaRequest)}.
   * <p>
   * Method under test: {@link DeltaSession#replaceDeltaRequest(DeltaRequest)}
   */
  @Test
  public void testReplaceDeltaRequest() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    DeltaRequest actualReplaceDeltaRequestResult = deltaSession.replaceDeltaRequest(deltaRequest);

    // Assert
    assertNull(deltaRequest.getSessionId());
    assertNull(actualReplaceDeltaRequestResult.getSessionId());
    assertEquals(0, actualReplaceDeltaRequestResult.getSize());
  }

  /**
   * Test {@link DeltaSession#replaceDeltaRequest(DeltaRequest)}.
   * <p>
   * Method under test: {@link DeltaSession#replaceDeltaRequest(DeltaRequest)}
   */
  @Test
  public void testReplaceDeltaRequest2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setId("42", false);
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    DeltaRequest actualReplaceDeltaRequestResult = deltaSession.replaceDeltaRequest(deltaRequest);

    // Assert
    assertEquals("42", deltaRequest.getSessionId());
    assertEquals("42", actualReplaceDeltaRequestResult.getSessionId());
    assertEquals(0, actualReplaceDeltaRequestResult.getSize());
  }

  /**
   * Test {@link DeltaSession#removeAttribute(String, boolean, boolean)} with {@code name}, {@code notify}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#removeAttribute(String, boolean, boolean)}
   */
  @Test
  public void testRemoveAttributeWithNameNotifyAddDeltaRequest_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DeltaSession()).removeAttribute("Name", true, true));
  }

  /**
   * Test {@link DeltaSession#removeAttribute(String, boolean)} with {@code name}, {@code notify}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#removeAttribute(String, boolean)}
   */
  @Test
  public void testRemoveAttributeWithNameNotify_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new DeltaSession()).removeAttribute("Name", true));
  }

  /**
   * Test {@link DeltaSession#setAttribute(String, Object, boolean, boolean)} with {@code name}, {@code value}, {@code notify}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setAttribute(String, Object, boolean, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotifyAddDeltaRequest() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDistributable(false);

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    manager.setNotifyBindingListenerOnUnchangedValue(false);
    manager.setNotifyAttributeListenerOnUnchangedValue(false);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(false);
    deltaSession.setManager(manager);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> deltaSession.setAttribute(null, null, false, false));
  }

  /**
   * Test {@link DeltaSession#setAttribute(String, Object, boolean, boolean)} with {@code name}, {@code value}, {@code notify}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setAttribute(String, Object, boolean, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotifyAddDeltaRequest2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDistributable(false);

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    manager.setNotifyBindingListenerOnUnchangedValue(false);
    manager.setNotifyAttributeListenerOnUnchangedValue(false);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(false);
    deltaSession.setManager(manager);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> deltaSession.setAttribute("Name", null, false, false));
  }

  /**
   * Test {@link DeltaSession#setAttribute(String, Object)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttributeWithNameValue_thenThrowIllegalArgumentException() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDistributable(false);

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    manager.setNotifyBindingListenerOnUnchangedValue(false);
    manager.setNotifyAttributeListenerOnUnchangedValue(false);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(false);
    deltaSession.setManager(manager);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> deltaSession.setAttribute(null, null));
  }

  /**
   * Test {@link DeltaSession#setAttribute(String, Object)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttributeWithNameValue_thenThrowIllegalStateException() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDistributable(false);

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    manager.setNotifyBindingListenerOnUnchangedValue(false);
    manager.setNotifyAttributeListenerOnUnchangedValue(false);

    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(false);
    deltaSession.setManager(manager);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> deltaSession.setAttribute("Name", null));
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object)}
   */
  @Test
  public void testSetNoteWithNameValue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setNote("Name", "Value");

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object)}
   */
  @Test
  public void testSetNoteWithNameValue2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setNote("Name", "Value");

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.replaceDeltaRequest(new DeltaRequest("42", true));

    // Act
    deltaSession.setNote("Name", "Value", true);

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest2() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setPrincipal(new UserPrincipal("principal"), true);
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setNote("Name", "Value", true);

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setNote("Name", "Value", true);

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest_givenDeltaSession_whenFalse() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", null, false);

    // Assert that nothing has changed
    assertFalse(deltaSession.getNoteNames().hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest_givenDeltaSession_whenNull() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", null, true);

    // Assert that nothing has changed
    assertFalse(deltaSession.getNoteNames().hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <ul>
   *   <li>Then {@link DeltaSession#DeltaSession()} NoteNames next is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest_thenDeltaSessionNoteNamesNextIsName() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", "Value", true);

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object, boolean)} with {@code name}, {@code value}, {@code addDeltaRequest}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object, boolean)}
   */
  @Test
  public void testSetNoteWithNameValueAddDeltaRequest_whenFalse() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", "Value", false);

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object)}
   */
  @Test
  public void testSetNoteWithNameValue_givenDeltaSessionNewTrueIsTrue() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setNew(true, true);

    // Act
    deltaSession.setNote("Name", "Value");

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} NoteNames next is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object)}
   */
  @Test
  public void testSetNoteWithNameValue_givenDeltaSession_thenDeltaSessionNoteNamesNextIsName() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", "Value");

    // Assert
    Iterator<String> noteNames = deltaSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link DeltaSession#setNote(String, Object)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaSession#setNote(String, Object)}
   */
  @Test
  public void testSetNoteWithNameValue_givenDeltaSession_whenNull() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.setNote("Name", null);

    // Assert that nothing has changed
    assertFalse(deltaSession.getNoteNames().hasNext());
  }
}
