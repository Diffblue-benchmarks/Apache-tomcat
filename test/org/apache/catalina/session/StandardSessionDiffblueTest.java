package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.PatternSyntaxException;
import listeners.SessionListener;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.authenticator.SingleSignOnListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.startup.FailedContext;
import org.apache.tomcat.websocket.server.WsSessionListener;
import org.junit.Test;

public class StandardSessionDiffblueTest {
  /**
   * Test {@link StandardSession#StandardSession(Manager)}.
   * <ul>
   *   <li>When {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@link StandardSession#accessCount} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#StandardSession(Manager)}
   */
  @Test
  public void testNewStandardSession_whenBackupManager_thenReturnAccessCountIsNull() {
    // Arrange
    BackupManager manager = new BackupManager();

    // Act
    StandardSession actualStandardSession = new StandardSession(manager);

    // Assert
    assertNull(actualStandardSession.accessCount);
    assertFalse(actualStandardSession.activityCheck);
    assertEquals(manager.sessionCreationTiming, actualStandardSession.listeners);
    assertEquals(manager.sessions, actualStandardSession.attributes);
    assertSame(manager, actualStandardSession.getManager());
  }

  /**
   * Test {@link StandardSession#StandardSession(Manager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then Session return {@link StandardSessionFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#StandardSession(Manager)}
   */
  @Test
  public void testNewStandardSession_whenNull_thenSessionReturnStandardSessionFacade() {
    // Arrange and Act
    StandardSession actualStandardSession = new StandardSession(null);

    // Assert
    HttpSession session = actualStandardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertNull(session.getServletContext());
    assertNull(actualStandardSession.getServletContext());
    assertNull(actualStandardSession.getManager());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardSession#setManager(Manager)}
   *   <li>{@link StandardSession#setMaxInactiveInterval(int)}
   *   <li>{@link StandardSession#setNew(boolean)}
   *   <li>{@link StandardSession#setValid(boolean)}
   *   <li>{@link StandardSession#toString()}
   *   <li>{@link StandardSession#getAuthType()}
   *   <li>{@link StandardSession#getCreationTimeInternal()}
   *   <li>{@link StandardSession#getId()}
   *   <li>{@link StandardSession#getIdInternal()}
   *   <li>{@link StandardSession#getLastAccessedTimeInternal()}
   *   <li>{@link StandardSession#getManager()}
   *   <li>{@link StandardSession#getMaxInactiveInterval()}
   *   <li>{@link StandardSession#getPrincipal()}
   *   <li>{@link StandardSession#getThisAccessedTimeInternal()}
   *   <li>{@link StandardSession#isValidInternal()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    BackupManager manager = new BackupManager();

    // Act
    standardSession.setManager(manager);
    standardSession.setMaxInactiveInterval(42);
    standardSession.setNew(true);
    standardSession.setValid(true);
    String actualToStringResult = standardSession.toString();
    String actualAuthType = standardSession.getAuthType();
    long actualCreationTimeInternal = standardSession.getCreationTimeInternal();
    String actualId = standardSession.getId();
    String actualIdInternal = standardSession.getIdInternal();
    long actualLastAccessedTimeInternal = standardSession.getLastAccessedTimeInternal();
    Manager actualManager = standardSession.getManager();
    int actualMaxInactiveInterval = standardSession.getMaxInactiveInterval();
    Principal actualPrincipal = standardSession.getPrincipal();
    long actualThisAccessedTimeInternal = standardSession.getThisAccessedTimeInternal();

    // Assert
    assertEquals("StandardSession[null]", actualToStringResult);
    assertNull(actualAuthType);
    assertNull(actualId);
    assertNull(actualIdInternal);
    assertNull(actualPrincipal);
    assertEquals(0L, actualCreationTimeInternal);
    assertEquals(0L, actualLastAccessedTimeInternal);
    assertEquals(0L, actualThisAccessedTimeInternal);
    assertEquals(42, actualMaxInactiveInterval);
    assertTrue(standardSession.isValidInternal());
    assertSame(manager, actualManager);
  }

  /**
   * Test {@link StandardSession#setAuthType(String)}.
   * <p>
   * Method under test: {@link StandardSession#setAuthType(String)}
   */
  @Test
  public void testSetAuthType() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());

    // Act
    standardSession.setAuthType("Auth Type");

    // Assert
    assertEquals("Auth Type", standardSession.getAuthType());
  }

  /**
   * Test {@link StandardSession#setCreationTime(long)}.
   * <p>
   * Method under test: {@link StandardSession#setCreationTime(long)}
   */
  @Test
  public void testSetCreationTime() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());

    // Act
    standardSession.setCreationTime(10L);

    // Assert
    assertEquals(10L, standardSession.getCreationTimeInternal());
    assertEquals(10L, standardSession.getLastAccessedTimeInternal());
    assertEquals(10L, standardSession.getThisAccessedTimeInternal());
  }

  /**
   * Test {@link StandardSession#setId(String)} with {@code id}.
   * <p>
   * Method under test: {@link StandardSession#setId(String)}
   */
  @Test
  public void testSetIdWithId() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42");

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String)} with {@code id}.
   * <p>
   * Method under test: {@link StandardSession#setId(String)}
   */
  @Test
  public void testSetIdWithId2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42");

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42", true);

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42", true);

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify3() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42", true);

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify_givenBackupManagerContextIsFailedContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42", true);

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String, boolean)} with {@code id}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setId(String, boolean)}
   */
  @Test
  public void testSetIdWithIdNotify_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42", true);

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenBackupManagerContextIsFailedContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42");

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42");

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#setId(String)} with {@code id}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationLifecycleListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setId(String)}
   */
  @Test
  public void testSetIdWithId_givenStandardContextAddApplicationLifecycleListenerListener() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.setId("42");

    // Assert
    Manager manager2 = standardSession.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals("42", session.getId());
    assertEquals("42", standardSession.getId());
    assertEquals("42", standardSession.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertTrue(((BackupManager) manager2).getNewSession().attributes.isEmpty());
    assertSame(standardSession, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link StandardSession#getThisAccessedTime()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getThisAccessedTime()}
   */
  @Test
  public void testGetThisAccessedTime_thenReturnZero() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertEquals(0L, standardSession.getThisAccessedTime());
  }

  /**
   * Test {@link StandardSession#getThisAccessedTime()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getThisAccessedTime()}
   */
  @Test
  public void testGetThisAccessedTime_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getThisAccessedTime());
  }

  /**
   * Test {@link StandardSession#getLastAccessedTime()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getLastAccessedTime()}
   */
  @Test
  public void testGetLastAccessedTime_thenReturnZero() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertEquals(0L, standardSession.getLastAccessedTime());
  }

  /**
   * Test {@link StandardSession#getLastAccessedTime()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getLastAccessedTime()}
   */
  @Test
  public void testGetLastAccessedTime_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getLastAccessedTime());
  }

  /**
   * Test {@link StandardSession#getIdleTime()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getIdleTime()}
   */
  @Test
  public void testGetIdleTime_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getIdleTime());
  }

  /**
   * Test {@link StandardSession#setPrincipal(Principal)}.
   * <p>
   * Method under test: {@link StandardSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    standardSession.setPrincipal(principal);

    // Assert
    assertSame(principal, standardSession.getPrincipal());
  }

  /**
   * Test {@link StandardSession#setPrincipal(Principal)}.
   * <ul>
   *   <li>Then {@link DeltaSession#DeltaSession()} Principal is {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal_thenDeltaSessionPrincipalIsUserPrincipalWithNameIsPrincipal() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    deltaSession.setPrincipal(principal);

    // Assert
    assertSame(principal, deltaSession.getPrincipal());
  }

  /**
   * Test {@link StandardSession#isValid()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) SessionActivityCheck is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isValid()}
   */
  @Test
  public void testIsValid_givenBackupManagerSessionActivityCheckIsTrue_thenReturnTrue() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setSessionActivityCheck(true);

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act and Assert
    assertTrue(standardSession.isValid());
  }

  /**
   * Test {@link StandardSession#isValid()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} MaxInactiveInterval is forty-two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isValid()}
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
   * Test {@link StandardSession#isValid()}.
   * <ul>
   *   <li>Given {@link StandardSession#StandardSession(Manager)} with manager is {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isValid()}
   */
  @Test
  public void testIsValid_givenStandardSessionWithManagerIsBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardSession(new BackupManager())).isValid());
  }

  /**
   * Test {@link StandardSession#isValid()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isValid()}
   */
  @Test
  public void testIsValid_thenReturnTrue() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertTrue(standardSession.isValid());
  }

  /**
   * Test {@link StandardSession#endAccess()}.
   * <ul>
   *   <li>Then {@link StandardSession#StandardSession(Manager)} with manager is {@link BackupManager} (default constructor) {@link StandardSession#accessCount} is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#endAccess()}
   */
  @Test
  public void testEndAccess_thenStandardSessionWithManagerIsBackupManagerAccessCountIsMinusOne() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setSessionActivityCheck(true);
    StandardSession standardSession = new StandardSession(manager);

    // Act
    standardSession.endAccess();

    // Assert
    AtomicInteger atomicInteger = standardSession.accessCount;
    assertEquals(-1, atomicInteger.get());
    assertEquals(-1, atomicInteger.getAndDecrement());
    assertEquals(-2, atomicInteger.getAndIncrement());
  }

  /**
   * Test {@link StandardSession#addSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link StandardSession#addSessionListener(SessionListener)}
   */
  @Test
  public void testAddSessionListener() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    SingleSignOnListener listener = new SingleSignOnListener("42");

    // Act
    standardSession.addSessionListener(listener);

    // Assert
    ArrayList<org.apache.catalina.SessionListener> sessionListenerList = standardSession.listeners;
    assertEquals(1, sessionListenerList.size());
    assertSame(listener, sessionListenerList.get(0));
  }

  /**
   * Test {@link StandardSession#expire(boolean)} with {@code boolean}.
   * <ul>
   *   <li>Then {@link StandardSession#StandardSession(Manager)} with manager is {@code null} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#expire(boolean)}
   */
  @Test
  public void testExpireWithBoolean_thenStandardSessionWithManagerIsNullValid() {
    // Arrange
    StandardSession standardSession = new StandardSession(null);
    standardSession.setValid(true);

    // Act
    standardSession.expire(true);

    // Assert that nothing has changed
    assertTrue(standardSession.isValid());
    assertTrue(standardSession.isValidInternal());
  }

  /**
   * Test {@link StandardSession#expire()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then not {@link DeltaSession#DeltaSession()} ValidInternal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#expire()}
   */
  @Test
  public void testExpire_givenDeltaSession_thenNotDeltaSessionValidInternal() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();

    // Act
    deltaSession.expire();

    // Assert that nothing has changed
    assertFalse(deltaSession.isValidInternal());
  }

  /**
   * Test {@link StandardSession#expire()}.
   * <ul>
   *   <li>Then {@link StandardSession#StandardSession(Manager)} with manager is {@code null} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#expire()}
   */
  @Test
  public void testExpire_thenStandardSessionWithManagerIsNullValid() {
    // Arrange
    StandardSession standardSession = new StandardSession(null);
    standardSession.setValid(true);

    // Act
    standardSession.expire();

    // Assert that nothing has changed
    assertTrue(standardSession.isValid());
    assertTrue(standardSession.isValidInternal());
  }

  /**
   * Test {@link StandardSession#getNote(String)}.
   * <p>
   * Method under test: {@link StandardSession#getNote(String)}
   */
  @Test
  public void testGetNote() {
    // Arrange, Act and Assert
    assertNull((new StandardSession(new BackupManager())).getNote("Name"));
  }

  /**
   * Test {@link StandardSession#getNoteNames()}.
   * <p>
   * Method under test: {@link StandardSession#getNoteNames()}
   */
  @Test
  public void testGetNoteNames() {
    // Arrange, Act and Assert
    assertFalse((new StandardSession(new BackupManager())).getNoteNames().hasNext());
  }

  /**
   * Test {@link StandardSession#recycle()}.
   * <p>
   * Method under test: {@link StandardSession#recycle()}
   */
  @Test
  public void testRecycle() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());

    // Act
    standardSession.recycle();

    // Assert
    HttpSession session = standardSession.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertNull(session.getServletContext());
    assertNull(standardSession.getServletContext());
    assertNull(standardSession.getManager());
  }

  /**
   * Test {@link StandardSession#setNote(String, Object)}.
   * <p>
   * Method under test: {@link StandardSession#setNote(String, Object)}
   */
  @Test
  public void testSetNote() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());

    // Act
    standardSession.setNote("Name", "Value");

    // Assert
    Iterator<String> noteNames = standardSession.getNoteNames();
    assertEquals("Name", noteNames.next());
    Map<String, Object> stringObjectMap = standardSession.notes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
    assertFalse(noteNames.hasNext());
  }

  /**
   * Test {@link StandardSession#getCreationTime()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getCreationTime()}
   */
  @Test
  public void testGetCreationTime_thenReturnZero() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertEquals(0L, standardSession.getCreationTime());
  }

  /**
   * Test {@link StandardSession#getCreationTime()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getCreationTime()}
   */
  @Test
  public void testGetCreationTime_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getCreationTime());
  }

  /**
   * Test {@link StandardSession#getServletContext()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getServletContext()}
   */
  @Test
  public void testGetServletContext_thenReturnNull() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setManager(null);

    // Act and Assert
    assertNull(standardSession.getServletContext());
  }

  /**
   * Test {@link StandardSession#getAttribute(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenReturnNull() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertNull(standardSession.getAttribute("Name"));
  }

  /**
   * Test {@link StandardSession#getAttribute(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getAttribute("Name"));
  }

  /**
   * Test {@link StandardSession#getAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenNull_thenReturnNull() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertNull(standardSession.getAttribute(null));
  }

  /**
   * Test {@link StandardSession#getAttributeNames()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#getAttributeNames()}
   */
  @Test
  public void testGetAttributeNames_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).getAttributeNames());
  }

  /**
   * Test {@link StandardSession#invalidate()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then {@link DeltaSession#DeltaSession()} ValidInternal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#invalidate()}
   */
  @Test
  public void testInvalidate_givenDeltaSessionValidIsTrue_thenDeltaSessionValidInternal() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(true);

    // Act
    deltaSession.invalidate();

    // Assert that nothing has changed
    assertTrue(deltaSession.isValidInternal());
  }

  /**
   * Test {@link StandardSession#invalidate()}.
   * <ul>
   *   <li>Then {@link StandardSession#StandardSession(Manager)} with manager is {@code null} Valid.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#invalidate()}
   */
  @Test
  public void testInvalidate_thenStandardSessionWithManagerIsNullValid() {
    // Arrange
    StandardSession standardSession = new StandardSession(null);
    standardSession.setValid(true);

    // Act
    standardSession.invalidate();

    // Assert that nothing has changed
    assertTrue(standardSession.isValid());
    assertTrue(standardSession.isValidInternal());
  }

  /**
   * Test {@link StandardSession#invalidate()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#invalidate()}
   */
  @Test
  public void testInvalidate_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).invalidate());
  }

  /**
   * Test {@link StandardSession#isNew()}.
   * <ul>
   *   <li>Given {@link StandardSession#StandardSession(Manager)} with manager is {@link BackupManager} (default constructor) New is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isNew()}
   */
  @Test
  public void testIsNew_givenStandardSessionWithManagerIsBackupManagerNewIsTrue_thenReturnTrue() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setNew(true);
    standardSession.setValid(true);

    // Act and Assert
    assertTrue(standardSession.isNew());
  }

  /**
   * Test {@link StandardSession#isNew()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isNew()}
   */
  @Test
  public void testIsNew_thenReturnFalse() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act and Assert
    assertFalse(standardSession.isNew());
  }

  /**
   * Test {@link StandardSession#isNew()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isNew()}
   */
  @Test
  public void testIsNew_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).isNew());
  }

  /**
   * Test {@link StandardSession#removeAttribute(String, boolean)} with {@code name}, {@code notify}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#removeAttribute(String, boolean)}
   */
  @Test
  public void testRemoveAttributeWithNameNotify_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new StandardSession(new BackupManager())).removeAttribute("Name", true));
  }

  /**
   * Test {@link StandardSession#removeAttribute(String)} with {@code name}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#removeAttribute(String)}
   */
  @Test
  public void testRemoveAttributeWithName_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new StandardSession(new BackupManager())).removeAttribute("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", null, true);

    // Assert that nothing has changed
    assertTrue(standardSession.attributes.isEmpty());
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify2() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationEventListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", "Value", true);

    // Assert
    ConcurrentMap<String, Object> stringObjectMap = standardSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify3() {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationEventListener(new SessionListener());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", "Value", true);

    // Assert
    ConcurrentMap<String, Object> stringObjectMap = standardSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_givenBackupManagerContextIsFailedContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", "Value", true);

    // Assert
    ConcurrentMap<String, Object> stringObjectMap = standardSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", "Value", true);

    // Assert
    ConcurrentMap<String, Object> stringObjectMap = standardSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Distributable is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_givenStandardContextDistributableIsTrue() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDistributable(true);
    context.addApplicationEventListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    StandardSession standardSession = new StandardSession(manager);
    standardSession.setValid(true);

    // Act
    standardSession.setAttribute("Name", "Value", true);

    // Assert
    ConcurrentMap<String, Object> stringObjectMap = standardSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertEquals("Value", stringObjectMap.get("Name"));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Then {@link DeltaSession#DeltaSession()} {@link StandardSession#attributes} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_thenDeltaSessionAttributesEmpty() {
    // Arrange
    DeltaSession deltaSession = new DeltaSession();
    deltaSession.setValid(true);

    // Act
    deltaSession.setAttribute("Name", null, true);

    // Assert that nothing has changed
    assertTrue(deltaSession.attributes.isEmpty());
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new StandardSession(new BackupManager())).setAttribute(null, "Value", true));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new StandardSession(new BackupManager())).setAttribute("Name", "Value", true));
  }

  /**
   * Test {@link StandardSession#setAttribute(String, Object, boolean)} with {@code name}, {@code value}, {@code notify}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#setAttribute(String, Object, boolean)}
   */
  @Test
  public void testSetAttributeWithNameValueNotify_thenThrowIllegalStateException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new StandardSession(new BackupManager())).setAttribute("Name", null, true));
  }

  /**
   * Test {@link StandardSession#isAttributeDistributable(String, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isAttributeDistributable(String, Object)}
   */
  @Test
  public void testIsAttributeDistributable_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardSession(new BackupManager())).isAttributeDistributable("Name", null));
  }

  /**
   * Test {@link StandardSession#isAttributeDistributable(String, Object)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#isAttributeDistributable(String, Object)}
   */
  @Test
  public void testIsAttributeDistributable_whenValue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new StandardSession(new BackupManager())).isAttributeDistributable("Name", "Value"));
  }

  /**
   * Test {@link StandardSession#exclude(String, Object)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) WarnOnSessionAttributeFilterFailure is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#exclude(String, Object)}
   */
  @Test
  public void testExclude_givenBackupManagerWarnOnSessionAttributeFilterFailureIsTrue() throws PatternSyntaxException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setWarnOnSessionAttributeFilterFailure(true);
    manager.setSessionAttributeNameFilter("Session Attribute Name Filter");

    // Act and Assert
    assertTrue((new StandardSession(manager)).exclude("Name", "Value"));
  }

  /**
   * Test {@link StandardSession#exclude(String, Object)}.
   * <ul>
   *   <li>Given {@link StandardSession#StandardSession(Manager)} with manager is {@link BackupManager} (default constructor) Manager is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#exclude(String, Object)}
   */
  @Test
  public void testExclude_givenStandardSessionWithManagerIsBackupManagerManagerIsNull() {
    // Arrange
    StandardSession standardSession = new StandardSession(new BackupManager());
    standardSession.setManager(null);

    // Act and Assert
    assertFalse(standardSession.exclude("Name", "Value"));
  }

  /**
   * Test {@link StandardSession#exclude(String, Object)}.
   * <ul>
   *   <li>Given {@link StandardSession#StandardSession(Manager)} with manager is {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#exclude(String, Object)}
   */
  @Test
  public void testExclude_givenStandardSessionWithManagerIsBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StandardSession(new BackupManager())).exclude("Name", "Value"));
  }

  /**
   * Test {@link StandardSession#exclude(String, Object)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSession#exclude(String, Object)}
   */
  @Test
  public void testExclude_thenReturnTrue() throws PatternSyntaxException {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setSessionAttributeNameFilter("Session Attribute Name Filter");

    // Act and Assert
    assertTrue((new StandardSession(manager)).exclude("Name", "Value"));
  }

  /**
   * Test {@link StandardSession#keys()}.
   * <p>
   * Method under test: {@link StandardSession#keys()}
   */
  @Test
  public void testKeys() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardSession(new BackupManager())).keys().length);
  }
}
