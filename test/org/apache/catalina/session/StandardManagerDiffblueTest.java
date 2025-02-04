package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Deque;
import java.util.List;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.session.ManagerBase.SessionTiming;
import org.junit.Test;

public class StandardManagerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardManager#getName()}
   *   <li>{@link StandardManager#getPathname()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardManager standardManager = new StandardManager();

    // Act
    String actualName = standardManager.getName();

    // Assert
    assertEquals("StandardManager", actualName);
    assertNull(standardManager.getPathname());
  }

  /**
   * Test {@link StandardManager#setPathname(String)}.
   * <p>
   * Method under test: {@link StandardManager#setPathname(String)}
   */
  @Test
  public void testSetPathname() {
    // Arrange
    StandardManager standardManager = new StandardManager();

    // Act
    standardManager.setPathname("Pathname");

    // Assert
    assertEquals("Pathname", standardManager.getPathname());
  }

  /**
   * Test {@link StandardManager#file()}.
   * <ul>
   *   <li>Given {@link StandardManager} (default constructor) Pathname is empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardManager#file()}
   */
  @Test
  public void testFile_givenStandardManagerPathnameIsEmptyString_thenReturnNull() {
    // Arrange
    StandardManager standardManager = new StandardManager();
    standardManager.setPathname("");

    // Act and Assert
    assertNull(standardManager.file());
  }

  /**
   * Test {@link StandardManager#file()}.
   * <ul>
   *   <li>Given {@link StandardManager} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardManager#file()}
   */
  @Test
  public void testFile_givenStandardManager_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardManager()).file());
  }

  /**
   * Test new {@link StandardManager} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardManager}
   */
  @Test
  public void testNewStandardManager() {
    // Arrange and Act
    StandardManager actualStandardManager = new StandardManager();

    // Assert
    Deque<SessionTiming> deque = actualStandardManager.sessionCreationTiming;
    assertTrue(deque instanceof List);
    Deque<SessionTiming> deque2 = actualStandardManager.sessionExpirationTiming;
    assertTrue(deque2 instanceof List);
    assertEquals("NEW", actualStandardManager.getStateName());
    assertEquals("SHA1PRNG", actualStandardManager.getSecureRandomAlgorithm());
    assertEquals("StandardManager", actualStandardManager.getName());
    assertEquals("org.apache.catalina.session.StandardManager", actualStandardManager.getClassName());
    assertNull(actualStandardManager.sessionIdGeneratorClass);
    assertNull(actualStandardManager.getJvmRoute());
    assertNull(actualStandardManager.getSecureRandomClass());
    assertNull(actualStandardManager.getSecureRandomProvider());
    assertNull(actualStandardManager.getSessionAttributeNameFilter());
    assertNull(actualStandardManager.getSessionAttributeValueClassNameFilter());
    assertNull(actualStandardManager.getPathname());
    assertNull(actualStandardManager.getSessionAttributeNamePattern());
    assertNull(actualStandardManager.getSessionAttributeValueClassNamePattern());
    assertNull(actualStandardManager.getObjectName());
    assertNull(actualStandardManager.getContext());
    assertNull(actualStandardManager.getEngine());
    assertNull(actualStandardManager.getSessionIdGenerator());
    assertNull(actualStandardManager.sessionIdGenerator);
    assertEquals(-1, actualStandardManager.getMaxActiveSessions());
    assertEquals(0, actualStandardManager.getActiveSessions());
    assertEquals(0, actualStandardManager.getMaxActive());
    assertEquals(0, actualStandardManager.getRejectedSessions());
    assertEquals(0, actualStandardManager.getSessionAverageAliveTime());
    assertEquals(0, actualStandardManager.getSessionCreateRate());
    assertEquals(0, actualStandardManager.getSessionExpireRate());
    assertEquals(0, actualStandardManager.getSessionMaxAliveTime());
    assertEquals(0, actualStandardManager.findLifecycleListeners().length);
    assertEquals(0L, actualStandardManager.getExpiredSessions());
    assertEquals(0L, actualStandardManager.getProcessingTime());
    assertEquals(0L, actualStandardManager.getSessionCounter());
    assertEquals(6, actualStandardManager.getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, actualStandardManager.getState());
    assertFalse(actualStandardManager.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualStandardManager.getPersistAuthentication());
    assertFalse(actualStandardManager.getSessionActivityCheck());
    assertFalse(actualStandardManager.getSessionLastAccessAtStart());
    assertFalse(actualStandardManager.getWarnOnSessionAttributeFilterFailure());
    assertTrue(deque.isEmpty());
    assertTrue(deque2.isEmpty());
    assertTrue(actualStandardManager.sessions.isEmpty());
    assertTrue(actualStandardManager.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualStandardManager.getThrowOnFailure());
  }
}
