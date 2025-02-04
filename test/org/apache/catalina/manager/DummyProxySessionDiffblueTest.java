package org.apache.catalina.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Iterator;
import org.apache.catalina.Manager;
import org.apache.catalina.SessionListener;
import org.apache.catalina.authenticator.SingleSignOnListener;
import org.apache.catalina.ha.session.BackupManager;
import org.junit.Test;

public class DummyProxySessionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DummyProxySession#DummyProxySession(String)}
   *   <li>{@link DummyProxySession#setId(String)}
   *   <li>{@link DummyProxySession#access()}
   *   <li>{@link DummyProxySession#addSessionListener(SessionListener)}
   *   <li>{@link DummyProxySession#endAccess()}
   *   <li>{@link DummyProxySession#expire()}
   *   <li>{@link DummyProxySession#recycle()}
   *   <li>{@link DummyProxySession#removeNote(String)}
   *   <li>{@link DummyProxySession#removeSessionListener(SessionListener)}
   *   <li>{@link DummyProxySession#setAuthType(String)}
   *   <li>{@link DummyProxySession#setCreationTime(long)}
   *   <li>{@link DummyProxySession#setManager(Manager)}
   *   <li>{@link DummyProxySession#setMaxInactiveInterval(int)}
   *   <li>{@link DummyProxySession#setNew(boolean)}
   *   <li>{@link DummyProxySession#setNote(String, Object)}
   *   <li>{@link DummyProxySession#setPrincipal(Principal)}
   *   <li>{@link DummyProxySession#setValid(boolean)}
   *   <li>{@link DummyProxySession#tellChangedSessionId(String, String, boolean, boolean)}
   *   <li>{@link DummyProxySession#getAuthType()}
   *   <li>{@link DummyProxySession#getCreationTime()}
   *   <li>{@link DummyProxySession#getCreationTimeInternal()}
   *   <li>{@link DummyProxySession#getId()}
   *   <li>{@link DummyProxySession#getIdInternal()}
   *   <li>{@link DummyProxySession#getIdleTime()}
   *   <li>{@link DummyProxySession#getIdleTimeInternal()}
   *   <li>{@link DummyProxySession#getLastAccessedTime()}
   *   <li>{@link DummyProxySession#getLastAccessedTimeInternal()}
   *   <li>{@link DummyProxySession#getManager()}
   *   <li>{@link DummyProxySession#getMaxInactiveInterval()}
   *   <li>{@link DummyProxySession#getNoteNames()}
   *   <li>{@link DummyProxySession#getPrincipal()}
   *   <li>{@link DummyProxySession#getSession()}
   *   <li>{@link DummyProxySession#getThisAccessedTime()}
   *   <li>{@link DummyProxySession#getThisAccessedTimeInternal()}
   *   <li>{@link DummyProxySession#isNew()}
   *   <li>{@link DummyProxySession#isValid()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    DummyProxySession actualDummyProxySession = new DummyProxySession("42");
    actualDummyProxySession.setId("42");
    actualDummyProxySession.access();
    actualDummyProxySession.addSessionListener(new SingleSignOnListener("42"));
    actualDummyProxySession.endAccess();
    actualDummyProxySession.expire();
    actualDummyProxySession.recycle();
    actualDummyProxySession.removeNote("Name");
    actualDummyProxySession.removeSessionListener(new SingleSignOnListener("42"));
    actualDummyProxySession.setAuthType("Auth Type");
    actualDummyProxySession.setCreationTime(10L);
    actualDummyProxySession.setManager(new BackupManager());
    actualDummyProxySession.setMaxInactiveInterval(42);
    actualDummyProxySession.setNew(true);
    actualDummyProxySession.setNote("Name", "Value");
    actualDummyProxySession.setPrincipal(new UserPrincipal("principal"));
    actualDummyProxySession.setValid(true);
    actualDummyProxySession.tellChangedSessionId("42", "42", true, true);
    String actualAuthType = actualDummyProxySession.getAuthType();
    long actualCreationTime = actualDummyProxySession.getCreationTime();
    long actualCreationTimeInternal = actualDummyProxySession.getCreationTimeInternal();
    String actualId = actualDummyProxySession.getId();
    String actualIdInternal = actualDummyProxySession.getIdInternal();
    long actualIdleTime = actualDummyProxySession.getIdleTime();
    long actualIdleTimeInternal = actualDummyProxySession.getIdleTimeInternal();
    long actualLastAccessedTime = actualDummyProxySession.getLastAccessedTime();
    long actualLastAccessedTimeInternal = actualDummyProxySession.getLastAccessedTimeInternal();
    Manager actualManager = actualDummyProxySession.getManager();
    int actualMaxInactiveInterval = actualDummyProxySession.getMaxInactiveInterval();
    Iterator<String> actualNoteNames = actualDummyProxySession.getNoteNames();
    Principal actualPrincipal = actualDummyProxySession.getPrincipal();
    HttpSession actualSession = actualDummyProxySession.getSession();
    long actualThisAccessedTime = actualDummyProxySession.getThisAccessedTime();
    long actualThisAccessedTimeInternal = actualDummyProxySession.getThisAccessedTimeInternal();
    boolean actualIsNewResult = actualDummyProxySession.isNew();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualIdInternal);
    assertNull(actualSession);
    assertNull(actualAuthType);
    assertNull(actualPrincipal);
    assertNull(actualNoteNames);
    assertNull(actualManager);
    assertEquals(0, actualMaxInactiveInterval);
    assertEquals(0L, actualCreationTime);
    assertEquals(0L, actualCreationTimeInternal);
    assertEquals(0L, actualIdleTime);
    assertEquals(0L, actualIdleTimeInternal);
    assertEquals(0L, actualLastAccessedTime);
    assertEquals(0L, actualLastAccessedTimeInternal);
    assertEquals(0L, actualThisAccessedTime);
    assertEquals(0L, actualThisAccessedTimeInternal);
    assertFalse(actualIsNewResult);
    assertFalse(actualDummyProxySession.isValid());
  }

  /**
   * Test {@link DummyProxySession#getNote(String)}.
   * <p>
   * Method under test: {@link DummyProxySession#getNote(String)}
   */
  @Test
  public void testGetNote() {
    // Arrange, Act and Assert
    assertNull((new DummyProxySession("42")).getNote("Name"));
  }

  /**
   * Test {@link DummyProxySession#isAttributeDistributable(String, Object)}.
   * <p>
   * Method under test: {@link DummyProxySession#isAttributeDistributable(String, Object)}
   */
  @Test
  public void testIsAttributeDistributable() {
    // Arrange, Act and Assert
    assertFalse((new DummyProxySession("42")).isAttributeDistributable("Name", "Value"));
  }
}
