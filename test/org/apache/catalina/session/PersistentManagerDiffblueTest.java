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

public class PersistentManagerDiffblueTest {
  /**
   * Test {@link PersistentManager#getName()}.
   * <p>
   * Method under test: {@link PersistentManager#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("PersistentManager", (new PersistentManager()).getName());
  }

  /**
   * Test new {@link PersistentManager} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PersistentManager}
   */
  @Test
  public void testNewPersistentManager() {
    // Arrange and Act
    PersistentManager actualPersistentManager = new PersistentManager();

    // Assert
    Deque<SessionTiming> deque = actualPersistentManager.sessionCreationTiming;
    assertTrue(deque instanceof List);
    Deque<SessionTiming> deque2 = actualPersistentManager.sessionExpirationTiming;
    assertTrue(deque2 instanceof List);
    assertEquals("NEW", actualPersistentManager.getStateName());
    assertEquals("PersistentManager", actualPersistentManager.getName());
    assertEquals("SHA1PRNG", actualPersistentManager.getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.session.PersistentManager", actualPersistentManager.getClassName());
    assertNull(actualPersistentManager.sessionIdGeneratorClass);
    assertNull(actualPersistentManager.getJvmRoute());
    assertNull(actualPersistentManager.getSecureRandomClass());
    assertNull(actualPersistentManager.getSecureRandomProvider());
    assertNull(actualPersistentManager.getSessionAttributeNameFilter());
    assertNull(actualPersistentManager.getSessionAttributeValueClassNameFilter());
    assertNull(actualPersistentManager.getSessionAttributeNamePattern());
    assertNull(actualPersistentManager.getSessionAttributeValueClassNamePattern());
    assertNull(actualPersistentManager.getObjectName());
    assertNull(actualPersistentManager.getContext());
    assertNull(actualPersistentManager.getEngine());
    assertNull(actualPersistentManager.getSessionIdGenerator());
    assertNull(actualPersistentManager.sessionIdGenerator);
    assertNull(actualPersistentManager.getStore());
    assertEquals(-1, actualPersistentManager.getMaxActiveSessions());
    assertEquals(-1, actualPersistentManager.getMaxIdleBackup());
    assertEquals(-1, actualPersistentManager.getMaxIdleSwap());
    assertEquals(-1, actualPersistentManager.getMinIdleSwap());
    assertEquals(0, actualPersistentManager.getActiveSessions());
    assertEquals(0, actualPersistentManager.getMaxActive());
    assertEquals(0, actualPersistentManager.getRejectedSessions());
    assertEquals(0, actualPersistentManager.getSessionAverageAliveTime());
    assertEquals(0, actualPersistentManager.getSessionCreateRate());
    assertEquals(0, actualPersistentManager.getSessionExpireRate());
    assertEquals(0, actualPersistentManager.getSessionMaxAliveTime());
    assertEquals(0, actualPersistentManager.findLifecycleListeners().length);
    assertEquals(0L, actualPersistentManager.getExpiredSessions());
    assertEquals(0L, actualPersistentManager.getProcessingTime());
    assertEquals(0L, actualPersistentManager.getSessionCounter());
    assertEquals(6, actualPersistentManager.getProcessExpiresFrequency());
    assertEquals(LifecycleState.NEW, actualPersistentManager.getState());
    assertFalse(actualPersistentManager.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualPersistentManager.getPersistAuthentication());
    assertFalse(actualPersistentManager.getSessionActivityCheck());
    assertFalse(actualPersistentManager.getSessionLastAccessAtStart());
    assertFalse(actualPersistentManager.getWarnOnSessionAttributeFilterFailure());
    assertTrue(deque.isEmpty());
    assertTrue(deque2.isEmpty());
    assertTrue(actualPersistentManager.sessions.isEmpty());
    assertTrue(actualPersistentManager.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualPersistentManager.getSaveOnRestart());
    assertTrue(actualPersistentManager.getThrowOnFailure());
  }
}
