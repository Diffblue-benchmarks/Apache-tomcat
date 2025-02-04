package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpSession;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import listeners.SessionListener;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.manager.DummyProxySession;
import org.apache.catalina.session.ManagerBase.SessionTiming;
import org.apache.catalina.startup.FailedContext;
import org.apache.catalina.util.StandardSessionIdGenerator;
import org.apache.tomcat.unittest.TesterContext;
import org.apache.tomcat.websocket.server.WsSessionListener;
import org.junit.Test;

public class ManagerBaseDiffblueTest {
  /**
   * Test {@link ManagerBase#getNotifyAttributeListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getNotifyAttributeListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyAttributeListenerOnUnchangedValue_givenBackupManager_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BackupManager()).getNotifyAttributeListenerOnUnchangedValue());
  }

  /**
   * Test {@link ManagerBase#getNotifyAttributeListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getNotifyAttributeListenerOnUnchangedValue()}
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
   * Test {@link ManagerBase#getNotifyBindingListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getNotifyBindingListenerOnUnchangedValue()}
   */
  @Test
  public void testGetNotifyBindingListenerOnUnchangedValue_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getNotifyBindingListenerOnUnchangedValue());
  }

  /**
   * Test {@link ManagerBase#getNotifyBindingListenerOnUnchangedValue()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getNotifyBindingListenerOnUnchangedValue()}
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
   * Test SessionTiming getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SessionTiming#getDuration()}
   *   <li>{@link SessionTiming#getTimestamp()}
   * </ul>
   */
  @Test
  public void testSessionTimingGettersAndSetters() {
    // Arrange
    SessionTiming sessionTiming = new SessionTiming(10L, 1);

    // Act
    int actualDuration = sessionTiming.getDuration();

    // Assert
    assertEquals(1, actualDuration);
    assertEquals(10L, sessionTiming.getTimestamp());
  }

  /**
   * Test SessionTiming {@link SessionTiming#SessionTiming(long, int)}.
   * <p>
   * Method under test: {@link SessionTiming#SessionTiming(long, int)}
   */
  @Test
  public void testSessionTimingNewSessionTiming() {
    // Arrange and Act
    SessionTiming actualSessionTiming = new SessionTiming(10L, 1);

    // Assert
    assertEquals(1, actualSessionTiming.getDuration());
    assertEquals(10L, actualSessionTiming.getTimestamp());
  }

  /**
   * Test {@link ManagerBase#setNotifyBindingListenerOnUnchangedValue(boolean)}.
   * <p>
   * Method under test: {@link ManagerBase#setNotifyBindingListenerOnUnchangedValue(boolean)}
   */
  @Test
  public void testSetNotifyBindingListenerOnUnchangedValue() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setNotifyBindingListenerOnUnchangedValue(true);

    // Assert
    assertTrue(backupManager.getNotifyBindingListenerOnUnchangedValue());
  }

  /**
   * Test {@link ManagerBase#getSessionActivityCheck()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionActivityCheck()}
   */
  @Test
  public void testGetSessionActivityCheck_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getSessionActivityCheck());
  }

  /**
   * Test {@link ManagerBase#getSessionActivityCheck()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionActivityCheck()}
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
   * Test {@link ManagerBase#getSessionLastAccessAtStart()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionLastAccessAtStart()}
   */
  @Test
  public void testGetSessionLastAccessAtStart_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getSessionLastAccessAtStart());
  }

  /**
   * Test {@link ManagerBase#getSessionLastAccessAtStart()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionLastAccessAtStart()}
   */
  @Test
  public void testGetSessionLastAccessAtStart_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionLastAccessAtStart(true);

    // Act and Assert
    assertTrue(backupManager.getSessionLastAccessAtStart());
  }

  /**
   * Test {@link ManagerBase#setSessionLastAccessAtStart(boolean)}.
   * <p>
   * Method under test: {@link ManagerBase#setSessionLastAccessAtStart(boolean)}
   */
  @Test
  public void testSetSessionLastAccessAtStart() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionLastAccessAtStart(true);

    // Assert
    assertTrue(backupManager.getSessionLastAccessAtStart());
    assertTrue(backupManager.getNewSession().lastAccessAtStart);
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeNameFilter()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeNameFilter()}
   */
  @Test
  public void testGetSessionAttributeNameFilter_givenBackupManager_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionAttributeNameFilter());
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeNameFilter()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeNameFilter()}
   */
  @Test
  public void testGetSessionAttributeNameFilter_thenReturnFoo() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("foo");

    // Act and Assert
    assertEquals("foo", backupManager.getSessionAttributeNameFilter());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeNameFilter(String)}.
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeNameFilter() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeNameFilter("Session Attribute Name Filter");

    // Assert
    assertEquals("Session Attribute Name Filter", backupManager.getSessionAttributeNamePattern().pattern());
    assertEquals("Session Attribute Name Filter", backupManager.getSessionAttributeNameFilter());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeNameFilter(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeNameFilter_whenEmptyString() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeNameFilter("");

    // Assert that nothing has changed
    assertNull(backupManager.getSessionAttributeNameFilter());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeNameFilter(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeNameFilter_whenNull() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeNameFilter(null);

    // Assert that nothing has changed
    assertNull(backupManager.getSessionAttributeNameFilter());
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeNamePattern()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeNamePattern()}
   */
  @Test
  public void testGetSessionAttributeNamePattern() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionAttributeNamePattern());
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeValueClassNameFilter()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeValueClassNameFilter()}
   */
  @Test
  public void testGetSessionAttributeValueClassNameFilter_givenBackupManager_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeValueClassNameFilter()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeValueClassNameFilter()}
   */
  @Test
  public void testGetSessionAttributeValueClassNameFilter_thenReturnFoo() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeValueClassNameFilter("foo");

    // Act and Assert
    assertEquals("foo", backupManager.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ManagerBase#getSessionAttributeValueClassNamePattern()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttributeValueClassNamePattern()}
   */
  @Test
  public void testGetSessionAttributeValueClassNamePattern() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionAttributeValueClassNamePattern());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}.
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeValueClassNameFilter() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeValueClassNameFilter("42");

    // Assert
    assertEquals("42", backupManager.getSessionAttributeValueClassNamePattern().pattern());
    assertEquals("42", backupManager.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeValueClassNameFilter_whenEmptyString() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeValueClassNameFilter("");

    // Assert that nothing has changed
    assertNull(backupManager.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setSessionAttributeValueClassNameFilter(String)}
   */
  @Test
  public void testSetSessionAttributeValueClassNameFilter_whenNull() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionAttributeValueClassNameFilter(null);

    // Assert that nothing has changed
    assertNull(backupManager.getSessionAttributeValueClassNameFilter());
  }

  /**
   * Test {@link ManagerBase#getWarnOnSessionAttributeFilterFailure()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getWarnOnSessionAttributeFilterFailure()}
   */
  @Test
  public void testGetWarnOnSessionAttributeFilterFailure_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getWarnOnSessionAttributeFilterFailure());
  }

  /**
   * Test {@link ManagerBase#getWarnOnSessionAttributeFilterFailure()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getWarnOnSessionAttributeFilterFailure()}
   */
  @Test
  public void testGetWarnOnSessionAttributeFilterFailure_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setWarnOnSessionAttributeFilterFailure(true);

    // Act and Assert
    assertTrue(backupManager.getWarnOnSessionAttributeFilterFailure());
  }

  /**
   * Test {@link ManagerBase#setWarnOnSessionAttributeFilterFailure(boolean)}.
   * <p>
   * Method under test: {@link ManagerBase#setWarnOnSessionAttributeFilterFailure(boolean)}
   */
  @Test
  public void testSetWarnOnSessionAttributeFilterFailure() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setWarnOnSessionAttributeFilterFailure(true);

    // Assert
    assertTrue(backupManager.getWarnOnSessionAttributeFilterFailure());
  }

  /**
   * Test {@link ManagerBase#getContext()}.
   * <p>
   * Method under test: {@link ManagerBase#getContext()}
   */
  @Test
  public void testGetContext() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getContext());
  }

  /**
   * Test {@link ManagerBase#setContext(Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link BackupManager} (default constructor) Context is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setContext(Context)}
   */
  @Test
  public void testSetContext_whenNull_thenBackupManagerContextIsNull() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setContext(null);

    // Assert that nothing has changed
    assertNull(backupManager.getContext());
  }

  /**
   * Test {@link ManagerBase#setContext(Context)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then {@link BackupManager} (default constructor) DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setContext(Context)}
   */
  @Test
  public void testSetContext_whenStandardContext_thenBackupManagerDomainInternalIsCatalina() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    StandardContext context = new StandardContext();

    // Act
    backupManager.setContext(context);

    // Assert
    assertEquals("Catalina", backupManager.getDomainInternal());
    assertEquals("Catalina", backupManager.getDomain());
    assertEquals(1, backupManager.getClassLoaders().length);
    assertSame(context, backupManager.getContext());
  }

  /**
   * Test {@link ManagerBase#getClassName()}.
   * <p>
   * Method under test: {@link ManagerBase#getClassName()}
   */
  @Test
  public void testGetClassName() {
    // Arrange, Act and Assert
    assertEquals("org.apache.catalina.ha.session.BackupManager", (new BackupManager()).getClassName());
  }

  /**
   * Test {@link ManagerBase#getSessionIdGenerator()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionIdGenerator()}
   */
  @Test
  public void testGetSessionIdGenerator() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionIdGenerator());
  }

  /**
   * Test {@link ManagerBase#setSessionIdGenerator(SessionIdGenerator)}.
   * <p>
   * Method under test: {@link ManagerBase#setSessionIdGenerator(SessionIdGenerator)}
   */
  @Test
  public void testSetSessionIdGenerator() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    StandardSessionIdGenerator sessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    backupManager.setSessionIdGenerator(sessionIdGenerator);

    // Assert
    SessionIdGenerator sessionIdGenerator2 = backupManager.sessionIdGenerator;
    assertTrue(sessionIdGenerator2 instanceof StandardSessionIdGenerator);
    assertEquals("", sessionIdGenerator2.getJvmRoute());
    assertEquals("NEW", ((StandardSessionIdGenerator) sessionIdGenerator2).getStateName());
    assertEquals("SHA1PRNG", ((StandardSessionIdGenerator) sessionIdGenerator2).getSecureRandomAlgorithm());
    assertNull(((StandardSessionIdGenerator) sessionIdGenerator2).getSecureRandomClass());
    assertNull(((StandardSessionIdGenerator) sessionIdGenerator2).getSecureRandomProvider());
    assertEquals(0, ((StandardSessionIdGenerator) sessionIdGenerator2).findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, ((StandardSessionIdGenerator) sessionIdGenerator2).getState());
    assertTrue(((StandardSessionIdGenerator) sessionIdGenerator2).getThrowOnFailure());
    Class<StandardSessionIdGenerator> expectedResultClass = StandardSessionIdGenerator.class;
    assertEquals(expectedResultClass, backupManager.sessionIdGeneratorClass);
    assertEquals(Short.SIZE, sessionIdGenerator2.getSessionIdLength());
    assertSame(sessionIdGenerator, backupManager.getSessionIdGenerator());
  }

  /**
   * Test {@link ManagerBase#getName()}.
   * <p>
   * Method under test: {@link ManagerBase#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getName());
  }

  /**
   * Test {@link ManagerBase#getSecureRandomClass()}.
   * <p>
   * Method under test: {@link ManagerBase#getSecureRandomClass()}
   */
  @Test
  public void testGetSecureRandomClass() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSecureRandomClass());
  }

  /**
   * Test {@link ManagerBase#setSecureRandomClass(String)}.
   * <p>
   * Method under test: {@link ManagerBase#setSecureRandomClass(String)}
   */
  @Test
  public void testSetSecureRandomClass() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSecureRandomClass("Secure Random Class");

    // Assert
    assertEquals("Secure Random Class", backupManager.getSecureRandomClass());
  }

  /**
   * Test {@link ManagerBase#getSecureRandomAlgorithm()}.
   * <p>
   * Method under test: {@link ManagerBase#getSecureRandomAlgorithm()}
   */
  @Test
  public void testGetSecureRandomAlgorithm() {
    // Arrange, Act and Assert
    assertEquals("SHA1PRNG", (new BackupManager()).getSecureRandomAlgorithm());
  }

  /**
   * Test {@link ManagerBase#setSecureRandomAlgorithm(String)}.
   * <p>
   * Method under test: {@link ManagerBase#setSecureRandomAlgorithm(String)}
   */
  @Test
  public void testSetSecureRandomAlgorithm() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSecureRandomAlgorithm("Secure Random Algorithm");

    // Assert
    assertEquals("Secure Random Algorithm", backupManager.getSecureRandomAlgorithm());
  }

  /**
   * Test {@link ManagerBase#getSecureRandomProvider()}.
   * <p>
   * Method under test: {@link ManagerBase#getSecureRandomProvider()}
   */
  @Test
  public void testGetSecureRandomProvider() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSecureRandomProvider());
  }

  /**
   * Test {@link ManagerBase#setSecureRandomProvider(String)}.
   * <p>
   * Method under test: {@link ManagerBase#setSecureRandomProvider(String)}
   */
  @Test
  public void testSetSecureRandomProvider() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSecureRandomProvider("Secure Random Provider");

    // Assert
    assertEquals("Secure Random Provider", backupManager.getSecureRandomProvider());
  }

  /**
   * Test {@link ManagerBase#getRejectedSessions()}.
   * <p>
   * Method under test: {@link ManagerBase#getRejectedSessions()}
   */
  @Test
  public void testGetRejectedSessions() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getRejectedSessions());
  }

  /**
   * Test {@link ManagerBase#getExpiredSessions()}.
   * <p>
   * Method under test: {@link ManagerBase#getExpiredSessions()}
   */
  @Test
  public void testGetExpiredSessions() {
    // Arrange, Act and Assert
    assertEquals(0L, (new BackupManager()).getExpiredSessions());
  }

  /**
   * Test {@link ManagerBase#getProcessingTime()}.
   * <p>
   * Method under test: {@link ManagerBase#getProcessingTime()}
   */
  @Test
  public void testGetProcessingTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new BackupManager()).getProcessingTime());
  }

  /**
   * Test {@link ManagerBase#setProcessingTime(long)}.
   * <p>
   * Method under test: {@link ManagerBase#setProcessingTime(long)}
   */
  @Test
  public void testSetProcessingTime() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setProcessingTime(1L);

    // Assert
    assertEquals(1L, backupManager.getProcessingTime());
  }

  /**
   * Test {@link ManagerBase#getProcessExpiresFrequency()}.
   * <p>
   * Method under test: {@link ManagerBase#getProcessExpiresFrequency()}
   */
  @Test
  public void testGetProcessExpiresFrequency() {
    // Arrange, Act and Assert
    assertEquals(6, (new BackupManager()).getProcessExpiresFrequency());
  }

  /**
   * Test {@link ManagerBase#setProcessExpiresFrequency(int)}.
   * <ul>
   *   <li>Then {@link BackupManager} (default constructor) ProcessExpiresFrequency is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setProcessExpiresFrequency(int)}
   */
  @Test
  public void testSetProcessExpiresFrequency_thenBackupManagerProcessExpiresFrequencyIsOne() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setProcessExpiresFrequency(1);

    // Assert
    assertEquals(1, backupManager.getProcessExpiresFrequency());
  }

  /**
   * Test {@link ManagerBase#setProcessExpiresFrequency(int)}.
   * <ul>
   *   <li>Then {@link BackupManager} (default constructor) ProcessExpiresFrequency is six.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#setProcessExpiresFrequency(int)}
   */
  @Test
  public void testSetProcessExpiresFrequency_thenBackupManagerProcessExpiresFrequencyIsSix() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setProcessExpiresFrequency(0);

    // Assert that nothing has changed
    assertEquals(6, backupManager.getProcessExpiresFrequency());
  }

  /**
   * Test {@link ManagerBase#getPersistAuthentication()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getPersistAuthentication()}
   */
  @Test
  public void testGetPersistAuthentication_givenBackupManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new BackupManager()).getPersistAuthentication());
  }

  /**
   * Test {@link ManagerBase#getPersistAuthentication()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getPersistAuthentication()}
   */
  @Test
  public void testGetPersistAuthentication_thenReturnTrue() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setPersistAuthentication(true);

    // Act and Assert
    assertTrue(backupManager.getPersistAuthentication());
  }

  /**
   * Test {@link ManagerBase#setPersistAuthentication(boolean)}.
   * <p>
   * Method under test: {@link ManagerBase#setPersistAuthentication(boolean)}
   */
  @Test
  public void testSetPersistAuthentication() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setPersistAuthentication(true);

    // Assert
    assertTrue(backupManager.getPersistAuthentication());
  }

  /**
   * Test {@link ManagerBase#backgroundProcess()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then {@link BackupManager} (default constructor) ProcessingTime is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#backgroundProcess()}
   */
  @Test
  public void testBackgroundProcess_givenBackupManager_thenBackupManagerProcessingTimeIsZero() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.backgroundProcess();

    // Assert that nothing has changed
    assertEquals(0L, backupManager.getProcessingTime());
  }

  /**
   * Test {@link ManagerBase#add(Session)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) MaxActive is three.</li>
   *   <li>Then {@link BackupManager} (default constructor) MaxActive is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#add(Session)}
   */
  @Test
  public void testAdd_givenBackupManagerMaxActiveIsThree_thenBackupManagerMaxActiveIsThree() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setMaxActive(3);
    DummyProxySession session = new DummyProxySession("42");

    // Act
    backupManager.add(session);

    // Assert
    Map<String, Session> stringSessionMap = backupManager.sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, backupManager.getActiveSessions());
    assertEquals(1L, backupManager.getSessionCounter());
    assertEquals(3, backupManager.getMaxActive());
    assertSame(session, stringSessionMap.get("42"));
  }

  /**
   * Test {@link ManagerBase#add(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   *   <li>Then {@link BackupManager} (default constructor) MaxActive is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#add(Session)}
   */
  @Test
  public void testAdd_whenDummyProxySessionWithSessionIdIs42_thenBackupManagerMaxActiveIsOne() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    DummyProxySession session = new DummyProxySession("42");

    // Act
    backupManager.add(session);

    // Assert
    Map<String, Session> stringSessionMap = backupManager.sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, backupManager.getActiveSessions());
    assertEquals(1, backupManager.getMaxActive());
    assertEquals(1L, backupManager.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
  }

  /**
   * Test {@link ManagerBase#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link ManagerBase#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    NamingContextListener listener = new NamingContextListener();

    // Act
    backupManager.addPropertyChangeListener(listener);

    // Assert
    assertEquals(0, backupManager.getNewSession().support.getPropertyChangeListeners().length);
    PropertyChangeListener[] propertyChangeListeners = backupManager.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new SessionListener());

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act
    Session actualCreateSessionResult = backupManager.createSession("42");

    // Assert
    Manager manager = actualCreateSessionResult.getManager();
    Context context2 = manager.getContext();
    assertTrue(context2 instanceof StandardContext);
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateSessionResult instanceof DeltaSession);
    HttpSession session = actualCreateSessionResult.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(1800, session.getMaxInactiveInterval());
    assertEquals(1800, actualCreateSessionResult.getMaxInactiveInterval());
    byte[] diff = ((DeltaSession) actualCreateSessionResult).getDiff();
    assertEquals(193, diff.length);
    assertEquals((byte) 7, diff[191]);
    assertEquals('\b', diff[192]);
    assertSame(context, context2);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession2() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener(new WsSessionListener(null));

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act
    Session actualCreateSessionResult = backupManager.createSession("42");

    // Assert
    Manager manager = actualCreateSessionResult.getManager();
    Context context2 = manager.getContext();
    assertTrue(context2 instanceof StandardContext);
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateSessionResult instanceof DeltaSession);
    HttpSession session = actualCreateSessionResult.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(1800, session.getMaxInactiveInterval());
    assertEquals(1800, actualCreateSessionResult.getMaxInactiveInterval());
    byte[] diff = ((DeltaSession) actualCreateSessionResult).getDiff();
    assertEquals(193, diff.length);
    assertEquals((byte) 7, diff[191]);
    assertEquals('\b', diff[192]);
    assertSame(context, context2);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) addApplicationLifecycleListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession_givenStandardContextAddApplicationLifecycleListenerListener() throws IOException {
    // Arrange
    StandardContext context = new StandardContext();
    context.addApplicationLifecycleListener("Listener");

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act
    Session actualCreateSessionResult = backupManager.createSession("42");

    // Assert
    Manager manager = actualCreateSessionResult.getManager();
    Context context2 = manager.getContext();
    assertTrue(context2 instanceof StandardContext);
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateSessionResult instanceof DeltaSession);
    HttpSession session = actualCreateSessionResult.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(1800, session.getMaxInactiveInterval());
    assertEquals(1800, actualCreateSessionResult.getMaxInactiveInterval());
    byte[] diff = ((DeltaSession) actualCreateSessionResult).getDiff();
    assertEquals(193, diff.length);
    assertEquals((byte) 7, diff[191]);
    assertEquals('\b', diff[192]);
    assertSame(context, context2);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <ul>
   *   <li>Then Manager Context return {@link FailedContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession_thenManagerContextReturnFailedContext() throws IOException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    FailedContext context = new FailedContext();
    backupManager.setContext(context);

    // Act
    Session actualCreateSessionResult = backupManager.createSession("42");

    // Assert
    Manager manager = actualCreateSessionResult.getManager();
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateSessionResult instanceof DeltaSession);
    HttpSession session = actualCreateSessionResult.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    Context context2 = manager.getContext();
    assertTrue(context2 instanceof FailedContext);
    assertNull(session.getServletContext());
    assertNull(((DeltaSession) actualCreateSessionResult).getServletContext());
    assertEquals(0, session.getMaxInactiveInterval());
    assertEquals(0, actualCreateSessionResult.getMaxInactiveInterval());
    byte[] diff = ((DeltaSession) actualCreateSessionResult).getDiff();
    assertEquals((byte) 0, diff[191]);
    assertEquals((byte) 0, diff[192]);
    assertEquals(193, diff.length);
    assertSame(context, context2);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <ul>
   *   <li>Then Manager Context return {@link StandardContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession_thenManagerContextReturnStandardContext() throws IOException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    StandardContext context = new StandardContext();
    backupManager.setContext(context);

    // Act
    Session actualCreateSessionResult = backupManager.createSession("42");

    // Assert
    Manager manager = actualCreateSessionResult.getManager();
    Context context2 = manager.getContext();
    assertTrue(context2 instanceof StandardContext);
    assertTrue(manager instanceof BackupManager);
    assertTrue(actualCreateSessionResult instanceof DeltaSession);
    HttpSession session = actualCreateSessionResult.getSession();
    assertTrue(session instanceof StandardSessionFacade);
    assertEquals(1800, session.getMaxInactiveInterval());
    assertEquals(1800, actualCreateSessionResult.getMaxInactiveInterval());
    byte[] diff = ((DeltaSession) actualCreateSessionResult).getDiff();
    assertEquals(193, diff.length);
    assertEquals((byte) 7, diff[191]);
    assertEquals('\b', diff[192]);
    assertSame(context, context2);
  }

  /**
   * Test {@link ManagerBase#createSession(String)}.
   * <ul>
   *   <li>Then throw {@link TooManyActiveSessionsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#createSession(String)}
   */
  @Test
  public void testCreateSession_thenThrowTooManyActiveSessionsException() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setMaxActiveSessions(0);

    // Act and Assert
    assertThrows(TooManyActiveSessionsException.class, () -> backupManager.createSession(null));
  }

  /**
   * Test {@link ManagerBase#findSession(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#findSession(String)}
   */
  @Test
  public void testFindSession_when42() throws IOException {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).findSession("42"));
  }

  /**
   * Test {@link ManagerBase#findSession(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#findSession(String)}
   */
  @Test
  public void testFindSession_whenNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).findSession(null));
  }

  /**
   * Test {@link ManagerBase#findSessions()}.
   * <p>
   * Method under test: {@link ManagerBase#findSessions()}
   */
  @Test
  public void testFindSessions() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).findSessions().length);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String)} with {@code session}, {@code newId}.
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewId() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    StandardContext context = new StandardContext();
    context.addApplicationEventListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42");

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)} with {@code session}, {@code newId}, {@code notifySessionListeners}, {@code notifyContainerListeners}.
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewIdNotifySessionListenersNotifyContainerListeners() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42", true, true);

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)} with {@code session}, {@code newId}, {@code notifySessionListeners}, {@code notifyContainerListeners}.
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewIdNotifySessionListenersNotifyContainerListeners2() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42", true, true);

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)} with {@code session}, {@code newId}, {@code notifySessionListeners}, {@code notifyContainerListeners}.
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String, boolean, boolean)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewIdNotifySessionListenersNotifyContainerListeners3() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    StandardContext context = new StandardContext();
    context.addApplicationEventListener("Listener");

    BackupManager manager = new BackupManager();
    manager.setContext(context);
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42", true, true);

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String)} with {@code session}, {@code newId}.
   * <ul>
   *   <li>Given {@link FailedContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewId_givenFailedContext() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    BackupManager manager = new BackupManager();
    manager.setContext(new FailedContext());
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42");

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#changeSessionId(Session, String)} with {@code session}, {@code newId}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#changeSessionId(Session, String)}
   */
  @Test
  public void testChangeSessionIdWithSessionNewId_givenStandardContext() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());
    StandardSession session = new StandardSession(manager);

    // Act
    backupManager.changeSessionId(session, "42");

    // Assert
    Manager manager2 = session.getManager();
    assertTrue(manager2 instanceof BackupManager);
    HttpSession session2 = session.getSession();
    assertTrue(session2 instanceof StandardSessionFacade);
    assertEquals("42", session2.getId());
    assertEquals("42", session.getId());
    assertEquals("42", session.getIdInternal());
    Map<String, Session> stringSessionMap = ((BackupManager) manager2).sessions;
    assertEquals(1, stringSessionMap.size());
    assertEquals(1, manager2.getActiveSessions());
    assertEquals(1, manager2.getMaxActive());
    assertEquals(1L, manager2.getSessionCounter());
    assertSame(session, stringSessionMap.get("42"));
    assertSame(manager.sessions, stringSessionMap);
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("foo");
    backupManager.setWarnOnSessionAttributeFilterFailure(false);
    backupManager.setSessionAttributeValueClassNameFilter(null);

    // Act and Assert
    assertFalse(backupManager.willAttributeDistribute("Name", null));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) SessionAttributeNameFilter is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_givenBackupManagerSessionAttributeNameFilterIsFoo()
      throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("foo");
    backupManager.setWarnOnSessionAttributeFilterFailure(true);
    backupManager.setSessionAttributeValueClassNameFilter("foo");

    // Act and Assert
    assertFalse(backupManager.willAttributeDistribute("Name", "Value"));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) SessionAttributeNameFilter is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_givenBackupManagerSessionAttributeNameFilterIsNull()
      throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter(null);
    backupManager.setWarnOnSessionAttributeFilterFailure(false);
    backupManager.setSessionAttributeValueClassNameFilter("foo");

    // Act and Assert
    assertFalse(backupManager.willAttributeDistribute("Name", "Value"));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) SessionAttributeNameFilter is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_givenBackupManagerSessionAttributeNameFilterIsNull2()
      throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter(null);
    backupManager.setWarnOnSessionAttributeFilterFailure(true);
    backupManager.setSessionAttributeValueClassNameFilter("foo");

    // Act and Assert
    assertFalse(backupManager.willAttributeDistribute("Name", "Value"));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_givenBackupManager_whenName_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BackupManager()).willAttributeDistribute("Name", "Value"));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_thenReturnTrue() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter(null);
    backupManager.setWarnOnSessionAttributeFilterFailure(false);
    backupManager.setSessionAttributeValueClassNameFilter(null);

    // Act and Assert
    assertTrue(backupManager.willAttributeDistribute("Name", null));
  }

  /**
   * Test {@link ManagerBase#willAttributeDistribute(String, Object)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#willAttributeDistribute(String, Object)}
   */
  @Test
  public void testWillAttributeDistribute_whenFoo() throws PatternSyntaxException {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setSessionAttributeNameFilter("foo");
    backupManager.setWarnOnSessionAttributeFilterFailure(true);
    backupManager.setSessionAttributeValueClassNameFilter("foo");

    // Act and Assert
    assertFalse(backupManager.willAttributeDistribute("foo", "Value"));
  }

  /**
   * Test {@link ManagerBase#getNewSession()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then Session return {@link StandardSessionFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getNewSession()}
   */
  @Test
  public void testGetNewSession_givenBackupManager_thenSessionReturnStandardSessionFacade() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    StandardSession actualNewSession = backupManager.getNewSession();

    // Assert
    assertTrue(actualNewSession.getSession() instanceof StandardSessionFacade);
    assertNull(actualNewSession.getAuthType());
    assertNull(actualNewSession.getId());
    assertNull(actualNewSession.getIdInternal());
    assertNull(actualNewSession.getPrincipal());
    assertNull(actualNewSession.accessCount);
    assertEquals(-1, actualNewSession.getMaxInactiveInterval());
    assertEquals(0L, actualNewSession.getCreationTimeInternal());
    assertEquals(0L, actualNewSession.getLastAccessedTimeInternal());
    assertEquals(0L, actualNewSession.getThisAccessedTimeInternal());
    assertFalse(actualNewSession.getNoteNames().hasNext());
    assertFalse(actualNewSession.isValid());
    assertFalse(actualNewSession.isValidInternal());
    assertFalse(actualNewSession.activityCheck);
    assertFalse(actualNewSession.expiring);
    assertFalse(actualNewSession.isNew);
    assertFalse(actualNewSession.lastAccessAtStart);
    assertTrue(actualNewSession.listeners.isEmpty());
    assertTrue(actualNewSession.attributes.isEmpty());
    assertTrue(actualNewSession.notes.isEmpty());
    assertSame(backupManager, actualNewSession.getManager());
  }

  /**
   * Test {@link ManagerBase#getEngine()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getEngine()}
   */
  @Test
  public void testGetEngine_givenBackupManager() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getEngine());
  }

  /**
   * Test {@link ManagerBase#getEngine()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getEngine()}
   */
  @Test
  public void testGetEngine_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertNull(backupManager.getEngine());
  }

  /**
   * Test {@link ManagerBase#getJvmRoute()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getJvmRoute()}
   */
  @Test
  public void testGetJvmRoute_givenBackupManager() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getJvmRoute());
  }

  /**
   * Test {@link ManagerBase#getJvmRoute()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getJvmRoute()}
   */
  @Test
  public void testGetJvmRoute_givenBackupManagerContextIsStandardContext() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertNull(backupManager.getJvmRoute());
  }

  /**
   * Test {@link ManagerBase#getSessionCounter()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionCounter()}
   */
  @Test
  public void testGetSessionCounter() {
    // Arrange, Act and Assert
    assertEquals(0L, (new BackupManager()).getSessionCounter());
  }

  /**
   * Test {@link ManagerBase#getActiveSessions()}.
   * <p>
   * Method under test: {@link ManagerBase#getActiveSessions()}
   */
  @Test
  public void testGetActiveSessions() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getActiveSessions());
  }

  /**
   * Test {@link ManagerBase#getMaxActive()}.
   * <p>
   * Method under test: {@link ManagerBase#getMaxActive()}
   */
  @Test
  public void testGetMaxActive() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getMaxActive());
  }

  /**
   * Test {@link ManagerBase#setMaxActive(int)}.
   * <p>
   * Method under test: {@link ManagerBase#setMaxActive(int)}
   */
  @Test
  public void testSetMaxActive() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setMaxActive(3);

    // Assert
    assertEquals(3, backupManager.getMaxActive());
  }

  /**
   * Test {@link ManagerBase#getMaxActiveSessions()}.
   * <p>
   * Method under test: {@link ManagerBase#getMaxActiveSessions()}
   */
  @Test
  public void testGetMaxActiveSessions() {
    // Arrange, Act and Assert
    assertEquals(-1, (new BackupManager()).getMaxActiveSessions());
  }

  /**
   * Test {@link ManagerBase#setMaxActiveSessions(int)}.
   * <p>
   * Method under test: {@link ManagerBase#setMaxActiveSessions(int)}
   */
  @Test
  public void testSetMaxActiveSessions() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setMaxActiveSessions(3);

    // Assert
    assertEquals(3, backupManager.getMaxActiveSessions());
  }

  /**
   * Test {@link ManagerBase#getSessionMaxAliveTime()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionMaxAliveTime()}
   */
  @Test
  public void testGetSessionMaxAliveTime() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getSessionMaxAliveTime());
  }

  /**
   * Test {@link ManagerBase#setSessionMaxAliveTime(int)}.
   * <p>
   * Method under test: {@link ManagerBase#setSessionMaxAliveTime(int)}
   */
  @Test
  public void testSetSessionMaxAliveTime() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.setSessionMaxAliveTime(1);

    // Assert
    assertEquals(1, backupManager.getSessionMaxAliveTime());
  }

  /**
   * Test {@link ManagerBase#updateSessionMaxAliveTime(int)}.
   * <ul>
   *   <li>Then {@link BackupManager} (default constructor) SessionMaxAliveTime is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#updateSessionMaxAliveTime(int)}
   */
  @Test
  public void testUpdateSessionMaxAliveTime_thenBackupManagerSessionMaxAliveTimeIsZero() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.updateSessionMaxAliveTime(0);

    // Assert that nothing has changed
    assertEquals(0, backupManager.getSessionMaxAliveTime());
  }

  /**
   * Test {@link ManagerBase#updateSessionMaxAliveTime(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link BackupManager} (default constructor) SessionMaxAliveTime is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#updateSessionMaxAliveTime(int)}
   */
  @Test
  public void testUpdateSessionMaxAliveTime_whenOne_thenBackupManagerSessionMaxAliveTimeIsOne() {
    // Arrange
    BackupManager backupManager = new BackupManager();

    // Act
    backupManager.updateSessionMaxAliveTime(1);

    // Assert
    assertEquals(1, backupManager.getSessionMaxAliveTime());
  }

  /**
   * Test {@link ManagerBase#getSessionAverageAliveTime()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionAverageAliveTime()}
   */
  @Test
  public void testGetSessionAverageAliveTime() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getSessionAverageAliveTime());
  }

  /**
   * Test {@link ManagerBase#getSessionCreateRate()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionCreateRate()}
   */
  @Test
  public void testGetSessionCreateRate() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getSessionCreateRate());
  }

  /**
   * Test {@link ManagerBase#getSessionExpireRate()}.
   * <p>
   * Method under test: {@link ManagerBase#getSessionExpireRate()}
   */
  @Test
  public void testGetSessionExpireRate() {
    // Arrange, Act and Assert
    assertEquals(0, (new BackupManager()).getSessionExpireRate());
  }

  /**
   * Test {@link ManagerBase#listSessionIds()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#listSessionIds()}
   */
  @Test
  public void testListSessionIds_givenBackupManager_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new BackupManager()).listSessionIds());
  }

  /**
   * Test {@link ManagerBase#listSessionIds()}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#listSessionIds()}
   */
  @Test
  public void testListSessionIds_thenReturn42() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.add(new DummyProxySession("42"));

    // Act and Assert
    assertEquals("42 ", backupManager.listSessionIds());
  }

  /**
   * Test {@link ManagerBase#getSessionAttribute(String, String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSessionAttribute(String, String)}
   */
  @Test
  public void testGetSessionAttribute_givenBackupManager_when42_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSessionAttribute("42", "Key"));
  }

  /**
   * Test {@link ManagerBase#getSession(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getSession(String)}
   */
  @Test
  public void testGetSession_givenBackupManager_when42_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new BackupManager()).getSession("42"));
  }

  /**
   * Test {@link ManagerBase#getThisAccessedTimestamp(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getThisAccessedTimestamp(String)}
   */
  @Test
  public void testGetThisAccessedTimestamp_givenBackupManager_when42_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new BackupManager()).getThisAccessedTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#getThisAccessedTimestamp(String)}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getThisAccessedTimestamp(String)}
   */
  @Test
  public void testGetThisAccessedTimestamp_thenReturnZero() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.add(new DummyProxySession("42"));

    // Act and Assert
    assertEquals(0L, backupManager.getThisAccessedTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#getThisAccessedTime(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getThisAccessedTime(String)}
   */
  @Test
  public void testGetThisAccessedTime_givenBackupManager_when42_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new BackupManager()).getThisAccessedTime("42"));
  }

  /**
   * Test {@link ManagerBase#getLastAccessedTimestamp(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getLastAccessedTimestamp(String)}
   */
  @Test
  public void testGetLastAccessedTimestamp_givenBackupManager_when42_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new BackupManager()).getLastAccessedTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#getLastAccessedTimestamp(String)}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getLastAccessedTimestamp(String)}
   */
  @Test
  public void testGetLastAccessedTimestamp_thenReturnZero() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.add(new DummyProxySession("42"));

    // Act and Assert
    assertEquals(0L, backupManager.getLastAccessedTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#getLastAccessedTime(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getLastAccessedTime(String)}
   */
  @Test
  public void testGetLastAccessedTime_givenBackupManager_when42_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new BackupManager()).getLastAccessedTime("42"));
  }

  /**
   * Test {@link ManagerBase#getCreationTime(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getCreationTime(String)}
   */
  @Test
  public void testGetCreationTime_givenBackupManager_when42_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new BackupManager()).getCreationTime("42"));
  }

  /**
   * Test {@link ManagerBase#getCreationTimestamp(String)}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getCreationTimestamp(String)}
   */
  @Test
  public void testGetCreationTimestamp_givenBackupManager_when42_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new BackupManager()).getCreationTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#getCreationTimestamp(String)}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getCreationTimestamp(String)}
   */
  @Test
  public void testGetCreationTimestamp_thenReturnZero() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.add(new DummyProxySession("42"));

    // Act and Assert
    assertEquals(0L, backupManager.getCreationTimestamp("42"));
  }

  /**
   * Test {@link ManagerBase#toString()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor).</li>
   *   <li>Then return {@code BackupManager[Container is null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#toString()}
   */
  @Test
  public void testToString_givenBackupManager_thenReturnBackupManagerContainerIsNull() {
    // Arrange, Act and Assert
    assertEquals("BackupManager[Container is null]", (new BackupManager()).toString());
  }

  /**
   * Test {@link ManagerBase#toString()}.
   * <ul>
   *   <li>Then return {@code BackupManager[StandardContext[null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#toString()}
   */
  @Test
  public void testToString_thenReturnBackupManagerStandardContextNull() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertEquals("BackupManager[StandardContext[null]]", backupManager.toString());
  }

  /**
   * Test {@link ManagerBase#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Manager,host=null,context=/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeManagerHostNullContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());
    context.setName("/");

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act and Assert
    assertEquals("type=Manager,host=null,context=/", backupManager.getObjectNameKeyProperties());
  }

  /**
   * Test {@link ManagerBase#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Manager,host=null,context=/type=Manager}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeManagerHostNullContextTypeManager() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());
    context.setName("type=Manager");

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act and Assert
    assertEquals("type=Manager,host=null,context=/type=Manager", backupManager.getObjectNameKeyProperties());
  }

  /**
   * Test {@link ManagerBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link BackupManager} (default constructor) Context is {@link TesterContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenBackupManagerContextIsTesterContext_thenReturnNull() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new TesterContext());

    // Act and Assert
    assertNull(backupManager.getDomainInternal());
  }

  /**
   * Test {@link ManagerBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDomain("Catalina");

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act and Assert
    assertEquals("Catalina", backupManager.getDomainInternal());
  }

  /**
   * Test {@link ManagerBase#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());

    BackupManager backupManager = new BackupManager();
    backupManager.setContext(context);

    // Act and Assert
    assertEquals("Catalina", backupManager.getDomainInternal());
  }

  /**
   * Test {@link ManagerBase#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerBase#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnCatalina() {
    // Arrange
    BackupManager backupManager = new BackupManager();
    backupManager.setContext(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", backupManager.getDomainInternal());
  }
}
