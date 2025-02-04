package org.apache.catalina.ha.session;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.session.StandardSessionFacade;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class DeltaManagerDiffblueTest {
  /**
   * Test new {@link DeltaManager} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DeltaManager}
   */
  @Test
  public void testNewDeltaManager() {
    // Arrange and Act
    DeltaManager actualDeltaManager = new DeltaManager();

    // Assert
    assertEquals("NEW", actualDeltaManager.getStateName());
    assertEquals("SHA1PRNG", actualDeltaManager.getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.ha.session.DeltaManager", actualDeltaManager.getClassName());
    assertNull(actualDeltaManager.getName());
    assertNull(actualDeltaManager.getJvmRoute());
    assertNull(actualDeltaManager.getSecureRandomClass());
    assertNull(actualDeltaManager.getSecureRandomProvider());
    assertNull(actualDeltaManager.getSessionAttributeNameFilter());
    assertNull(actualDeltaManager.getSessionAttributeValueClassNameFilter());
    assertNull(actualDeltaManager.getObjectName());
    assertNull(actualDeltaManager.getContext());
    assertNull(actualDeltaManager.getEngine());
    assertNull(actualDeltaManager.getSessionIdGenerator());
    assertNull(actualDeltaManager.getCluster());
    assertEquals(-1, actualDeltaManager.getMaxActiveSessions());
    assertEquals(0, actualDeltaManager.getCounterNoStateTransferred());
    assertEquals(0, actualDeltaManager.getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, actualDeltaManager.getCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, actualDeltaManager.getReceivedQueueSize());
    assertEquals(0, actualDeltaManager.getActiveSessions());
    assertEquals(0, actualDeltaManager.getMaxActive());
    assertEquals(0, actualDeltaManager.getRejectedSessions());
    assertEquals(0, actualDeltaManager.getSessionAverageAliveTime());
    assertEquals(0, actualDeltaManager.getSessionCreateRate());
    assertEquals(0, actualDeltaManager.getSessionExpireRate());
    assertEquals(0, actualDeltaManager.getSessionMaxAliveTime());
    assertEquals(0, actualDeltaManager.getInvalidatedSessions().length);
    assertEquals(0, actualDeltaManager.findLifecycleListeners().length);
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_ALL_SESSION_DATA());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_SESSION_ACCESSED());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_SESSION_CREATED());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_SESSION_DELTA());
    assertEquals(0L, actualDeltaManager.getCounterReceive_EVT_SESSION_EXPIRED());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_ALL_SESSION_DATA());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_SESSION_ACCESSED());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_SESSION_CREATED());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_SESSION_DELTA());
    assertEquals(0L, actualDeltaManager.getCounterSend_EVT_SESSION_EXPIRED());
    assertEquals(0L, actualDeltaManager.getSessionReplaceCounter());
    assertEquals(0L, actualDeltaManager.getExpiredSessions());
    assertEquals(0L, actualDeltaManager.getProcessingTime());
    assertEquals(0L, actualDeltaManager.getSessionCounter());
    assertEquals(1000, actualDeltaManager.getSendAllSessionsSize());
    assertEquals(2000, actualDeltaManager.getSendAllSessionsWaitTime());
    assertEquals(6, actualDeltaManager.getProcessExpiresFrequency());
    assertEquals(60, actualDeltaManager.getStateTransferTimeout());
    assertEquals(LifecycleState.NEW, actualDeltaManager.getState());
    assertFalse(actualDeltaManager.isRecordAllActions());
    assertFalse(actualDeltaManager.getStateTransferred());
    assertFalse(actualDeltaManager.isExpireSessionsOnShutdown());
    assertFalse(actualDeltaManager.isNoContextManagerReceived());
    assertFalse(actualDeltaManager.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualDeltaManager.getPersistAuthentication());
    assertFalse(actualDeltaManager.getSessionActivityCheck());
    assertFalse(actualDeltaManager.getSessionLastAccessAtStart());
    assertFalse(actualDeltaManager.getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualDeltaManager.isNotifyListenersOnReplication());
    assertTrue(actualDeltaManager.isNotifyContainerListenersOnReplication());
    assertTrue(actualDeltaManager.isNotifySessionListenersOnReplication());
    assertTrue(actualDeltaManager.isSendAllSessions());
    assertTrue(actualDeltaManager.isStateTimestampDrop());
    assertTrue(actualDeltaManager.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualDeltaManager.getThrowOnFailure());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeltaManager#setExpireSessionsOnShutdown(boolean)}
   *   <li>{@link DeltaManager#setName(String)}
   *   <li>{@link DeltaManager#setNoContextManagerReceived(boolean)}
   *   <li>{@link DeltaManager#setNotifyContainerListenersOnReplication(boolean)}
   *   <li>{@link DeltaManager#setNotifySessionListenersOnReplication(boolean)}
   *   <li>{@link DeltaManager#setSendAllSessions(boolean)}
   *   <li>{@link DeltaManager#setSendAllSessionsSize(int)}
   *   <li>{@link DeltaManager#setSendAllSessionsWaitTime(int)}
   *   <li>{@link DeltaManager#setStateTimestampDrop(boolean)}
   *   <li>{@link DeltaManager#setStateTransferTimeout(int)}
   *   <li>{@link DeltaManager#setStateTransferred(boolean)}
   *   <li>{@link DeltaManager#getCounterNoStateTransferred()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_ALL_SESSION_DATA()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_CHANGE_SESSION_ID()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_GET_ALL_SESSIONS()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_SESSION_ACCESSED()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_SESSION_CREATED()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_SESSION_DELTA()}
   *   <li>{@link DeltaManager#getCounterReceive_EVT_SESSION_EXPIRED()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_ALL_SESSION_DATA()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_CHANGE_SESSION_ID()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_GET_ALL_SESSIONS()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_SESSION_ACCESSED()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_SESSION_CREATED()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_SESSION_DELTA()}
   *   <li>{@link DeltaManager#getCounterSend_EVT_SESSION_EXPIRED()}
   *   <li>{@link DeltaManager#getInvalidatedSessions()}
   *   <li>{@link DeltaManager#getName()}
   *   <li>{@link DeltaManager#getSendAllSessionsSize()}
   *   <li>{@link DeltaManager#getSendAllSessionsWaitTime()}
   *   <li>{@link DeltaManager#getSessionReplaceCounter()}
   *   <li>{@link DeltaManager#getStateTransferTimeout()}
   *   <li>{@link DeltaManager#getStateTransferred()}
   *   <li>{@link DeltaManager#isExpireSessionsOnShutdown()}
   *   <li>{@link DeltaManager#isNoContextManagerReceived()}
   *   <li>{@link DeltaManager#isNotifyContainerListenersOnReplication()}
   *   <li>{@link DeltaManager#isNotifySessionListenersOnReplication()}
   *   <li>{@link DeltaManager#isSendAllSessions()}
   *   <li>{@link DeltaManager#isStateTimestampDrop()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();

    // Act
    deltaManager.setExpireSessionsOnShutdown(true);
    deltaManager.setName("Name");
    deltaManager.setNoContextManagerReceived(true);
    deltaManager.setNotifyContainerListenersOnReplication(true);
    deltaManager.setNotifySessionListenersOnReplication(true);
    deltaManager.setSendAllSessions(true);
    deltaManager.setSendAllSessionsSize(3);
    deltaManager.setSendAllSessionsWaitTime(3);
    deltaManager.setStateTimestampDrop(true);
    deltaManager.setStateTransferTimeout(10);
    deltaManager.setStateTransferred(true);
    int actualCounterNoStateTransferred = deltaManager.getCounterNoStateTransferred();
    long actualCounterReceive_EVT_ALL_SESSION_DATA = deltaManager.getCounterReceive_EVT_ALL_SESSION_DATA();
    long actualCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER = deltaManager
        .getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER();
    int actualCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE = deltaManager
        .getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE();
    long actualCounterReceive_EVT_CHANGE_SESSION_ID = deltaManager.getCounterReceive_EVT_CHANGE_SESSION_ID();
    long actualCounterReceive_EVT_GET_ALL_SESSIONS = deltaManager.getCounterReceive_EVT_GET_ALL_SESSIONS();
    long actualCounterReceive_EVT_SESSION_ACCESSED = deltaManager.getCounterReceive_EVT_SESSION_ACCESSED();
    long actualCounterReceive_EVT_SESSION_CREATED = deltaManager.getCounterReceive_EVT_SESSION_CREATED();
    long actualCounterReceive_EVT_SESSION_DELTA = deltaManager.getCounterReceive_EVT_SESSION_DELTA();
    long actualCounterReceive_EVT_SESSION_EXPIRED = deltaManager.getCounterReceive_EVT_SESSION_EXPIRED();
    long actualCounterSend_EVT_ALL_SESSION_DATA = deltaManager.getCounterSend_EVT_ALL_SESSION_DATA();
    int actualCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE = deltaManager
        .getCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE();
    long actualCounterSend_EVT_CHANGE_SESSION_ID = deltaManager.getCounterSend_EVT_CHANGE_SESSION_ID();
    long actualCounterSend_EVT_GET_ALL_SESSIONS = deltaManager.getCounterSend_EVT_GET_ALL_SESSIONS();
    long actualCounterSend_EVT_SESSION_ACCESSED = deltaManager.getCounterSend_EVT_SESSION_ACCESSED();
    long actualCounterSend_EVT_SESSION_CREATED = deltaManager.getCounterSend_EVT_SESSION_CREATED();
    long actualCounterSend_EVT_SESSION_DELTA = deltaManager.getCounterSend_EVT_SESSION_DELTA();
    long actualCounterSend_EVT_SESSION_EXPIRED = deltaManager.getCounterSend_EVT_SESSION_EXPIRED();
    String[] actualInvalidatedSessions = deltaManager.getInvalidatedSessions();
    String actualName = deltaManager.getName();
    int actualSendAllSessionsSize = deltaManager.getSendAllSessionsSize();
    int actualSendAllSessionsWaitTime = deltaManager.getSendAllSessionsWaitTime();
    long actualSessionReplaceCounter = deltaManager.getSessionReplaceCounter();
    int actualStateTransferTimeout = deltaManager.getStateTransferTimeout();
    boolean actualStateTransferred = deltaManager.getStateTransferred();
    boolean actualIsExpireSessionsOnShutdownResult = deltaManager.isExpireSessionsOnShutdown();
    boolean actualIsNoContextManagerReceivedResult = deltaManager.isNoContextManagerReceived();
    boolean actualIsNotifyContainerListenersOnReplicationResult = deltaManager
        .isNotifyContainerListenersOnReplication();
    boolean actualIsNotifySessionListenersOnReplicationResult = deltaManager.isNotifySessionListenersOnReplication();
    boolean actualIsSendAllSessionsResult = deltaManager.isSendAllSessions();

    // Assert
    assertEquals("Name", actualName);
    assertEquals(0, actualCounterNoStateTransferred);
    assertEquals(0, actualCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE);
    assertEquals(0, actualCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE);
    assertEquals(0, actualInvalidatedSessions.length);
    assertEquals(0L, actualCounterReceive_EVT_ALL_SESSION_DATA);
    assertEquals(0L, actualCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER);
    assertEquals(0L, actualCounterReceive_EVT_CHANGE_SESSION_ID);
    assertEquals(0L, actualCounterReceive_EVT_GET_ALL_SESSIONS);
    assertEquals(0L, actualCounterReceive_EVT_SESSION_ACCESSED);
    assertEquals(0L, actualCounterReceive_EVT_SESSION_CREATED);
    assertEquals(0L, actualCounterReceive_EVT_SESSION_DELTA);
    assertEquals(0L, actualCounterReceive_EVT_SESSION_EXPIRED);
    assertEquals(0L, actualCounterSend_EVT_ALL_SESSION_DATA);
    assertEquals(0L, actualCounterSend_EVT_CHANGE_SESSION_ID);
    assertEquals(0L, actualCounterSend_EVT_GET_ALL_SESSIONS);
    assertEquals(0L, actualCounterSend_EVT_SESSION_ACCESSED);
    assertEquals(0L, actualCounterSend_EVT_SESSION_CREATED);
    assertEquals(0L, actualCounterSend_EVT_SESSION_DELTA);
    assertEquals(0L, actualCounterSend_EVT_SESSION_EXPIRED);
    assertEquals(0L, actualSessionReplaceCounter);
    assertEquals(10, actualStateTransferTimeout);
    assertEquals(3, actualSendAllSessionsSize);
    assertEquals(3, actualSendAllSessionsWaitTime);
    assertTrue(actualStateTransferred);
    assertTrue(actualIsExpireSessionsOnShutdownResult);
    assertTrue(actualIsNoContextManagerReceivedResult);
    assertTrue(actualIsNotifyContainerListenersOnReplicationResult);
    assertTrue(actualIsNotifySessionListenersOnReplicationResult);
    assertTrue(actualIsSendAllSessionsResult);
    assertTrue(deltaManager.isStateTimestampDrop());
  }

  /**
   * Test {@link DeltaManager#getReceivedQueueSize()}.
   * <p>
   * Method under test: {@link DeltaManager#getReceivedQueueSize()}
   */
  @Test
  public void testGetReceivedQueueSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new DeltaManager()).getReceivedQueueSize());
  }

  /**
   * Test {@link DeltaManager#createEmptySession()}.
   * <p>
   * Method under test: {@link DeltaManager#createEmptySession()}
   */
  @Test
  public void testCreateEmptySession() {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();

    // Act
    Session actualCreateEmptySessionResult = deltaManager.createEmptySession();

    // Assert
    assertTrue(((DeltaSession) actualCreateEmptySessionResult).diffLock instanceof WriteLock);
    Manager manager = actualCreateEmptySessionResult.getManager();
    assertTrue(manager instanceof DeltaManager);
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
    assertSame(deltaManager, manager);
  }

  /**
   * Test {@link DeltaManager#serializeSessionId(String)}.
   * <p>
   * Method under test: {@link DeltaManager#serializeSessionId(String)}
   */
  @Test
  public void testSerializeSessionId() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', 4, 0, 2, '4', '2'},
        (new DeltaManager()).serializeSessionId("42"));
  }

  /**
   * Test {@link DeltaManager#serializeSessions(Session[])}.
   * <ul>
   *   <li>Then return second element is minus nineteen.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#serializeSessions(Session[])}
   */
  @Test
  public void testSerializeSessions_thenReturnSecondElementIsMinusNineteen() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();

    // Act
    byte[] actualSerializeSessionsResult = deltaManager.serializeSessions(new Session[]{new DeltaSession()});

    // Assert
    assertEquals((byte) -19, actualSerializeSessionsResult[1]);
    assertEquals((byte) -84, actualSerializeSessionsResult[0]);
    assertEquals((byte) 0, actualSerializeSessionsResult[2]);
    assertEquals((byte) 0, actualSerializeSessionsResult[201]);
    assertEquals((byte) 0, actualSerializeSessionsResult[203]);
    assertEquals((byte) 0, actualSerializeSessionsResult[207]);
    assertEquals((byte) 0, actualSerializeSessionsResult[213]);
    assertEquals((byte) 0, actualSerializeSessionsResult[215]);
    assertEquals((byte) 0, actualSerializeSessionsResult[216]);
    assertEquals((byte) 0, actualSerializeSessionsResult[217]);
    assertEquals((byte) 0, actualSerializeSessionsResult[218]);
    assertEquals((byte) 0, actualSerializeSessionsResult[219]);
    assertEquals((byte) 0, actualSerializeSessionsResult[220]);
    assertEquals((byte) 0, actualSerializeSessionsResult[222]);
    assertEquals((byte) 0, actualSerializeSessionsResult[224]);
    assertEquals((byte) 0, actualSerializeSessionsResult[6]);
    assertEquals((byte) 17, actualSerializeSessionsResult[7]);
    assertEquals((byte) 1, actualSerializeSessionsResult[206]);
    assertEquals(226, actualSerializeSessionsResult.length);
    assertEquals((byte) 4, actualSerializeSessionsResult[204]);
    assertEquals((byte) 5, actualSerializeSessionsResult[3]);
    assertEquals('.', actualSerializeSessionsResult[17]);
    assertEquals('.', actualSerializeSessionsResult[SessionMessage.EVT_ALL_SESSION_DATA]);
    assertEquals('I', actualSerializeSessionsResult[18]);
    assertEquals('\b', actualSerializeSessionsResult[225]);
    assertEquals('a', actualSerializeSessionsResult[11]);
    assertEquals('a', actualSerializeSessionsResult[9]);
    assertEquals('a', actualSerializeSessionsResult[SessionMessage.EVT_ALL_SESSION_TRANSFERCOMPLETE]);
    assertEquals('e', actualSerializeSessionsResult[21]);
    assertEquals('e', actualSerializeSessionsResult[23]);
    assertEquals('g', actualSerializeSessionsResult[22]);
    assertEquals('g', actualSerializeSessionsResult[SessionMessage.EVT_ALL_SESSION_NOCONTEXTMANAGER]);
    assertEquals('j', actualSerializeSessionsResult[8]);
    assertEquals('l', actualSerializeSessionsResult[SessionMessage.EVT_SESSION_DELTA]);
    assertEquals('n', actualSerializeSessionsResult[19]);
    assertEquals('n', actualSerializeSessionsResult[SessionMessage.EVT_CHANGE_SESSION_ID]);
    assertEquals('p', actualSerializeSessionsResult[208]);
    assertEquals('p', actualSerializeSessionsResult[209]);
    assertEquals('p', actualSerializeSessionsResult[210]);
    assertEquals('q', actualSerializeSessionsResult[212]);
    assertEquals('q', actualSerializeSessionsResult[221]);
    assertEquals('r', actualSerializeSessionsResult[5]);
    assertEquals('r', actualSerializeSessionsResult[Float.PRECISION]);
    assertEquals('s', actualSerializeSessionsResult[211]);
    assertEquals('s', actualSerializeSessionsResult[4]);
    assertEquals('t', actualSerializeSessionsResult[20]);
    assertEquals('v', actualSerializeSessionsResult[10]);
    assertEquals('w', actualSerializeSessionsResult[205]);
    assertEquals('~', actualSerializeSessionsResult[202]);
    assertEquals('~', actualSerializeSessionsResult[214]);
    assertEquals('~', actualSerializeSessionsResult[223]);
  }

  /**
   * Test {@link DeltaManager#findSessionMasterMember()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#findSessionMasterMember()}
   */
  @Test
  public void testFindSessionMasterMember_thenReturnNull() {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    deltaManager.setCluster(new SimpleTcpCluster());

    // Act and Assert
    assertNull(deltaManager.findSessionMasterMember());
  }

  /**
   * Test {@link DeltaManager#waitForSendAllSessions(long)}.
   * <ul>
   *   <li>Then {@link DeltaManager} (default constructor) CounterNoStateTransferred is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#waitForSendAllSessions(long)}
   */
  @Test
  public void testWaitForSendAllSessions_thenDeltaManagerCounterNoStateTransferredIsOne() {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    deltaManager.setStateTransferTimeout(1);
    deltaManager.setStateTransferred(false);
    deltaManager.setNoContextManagerReceived(false);

    // Act
    deltaManager.waitForSendAllSessions(1L);

    // Assert
    assertEquals(1, deltaManager.getCounterNoStateTransferred());
  }

  /**
   * Test {@link DeltaManager#waitForSendAllSessions(long)}.
   * <ul>
   *   <li>Then {@link DeltaManager} (default constructor) CounterNoStateTransferred is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#waitForSendAllSessions(long)}
   */
  @Test
  public void testWaitForSendAllSessions_thenDeltaManagerCounterNoStateTransferredIsZero() {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    deltaManager.setStateTransferTimeout(0);
    deltaManager.setStateTransferred(false);
    deltaManager.setNoContextManagerReceived(false);

    // Act
    deltaManager.waitForSendAllSessions(1L);

    // Assert that nothing has changed
    assertEquals(0, deltaManager.getCounterNoStateTransferred());
  }

  /**
   * Test {@link DeltaManager#requestCompleted(String, boolean)} with {@code sessionId}, {@code expires}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#requestCompleted(String, boolean)}
   */
  @Test
  public void testRequestCompletedWithSessionIdExpires_when42() {
    // Arrange, Act and Assert
    assertNull((new DeltaManager()).requestCompleted("42", true));
  }

  /**
   * Test {@link DeltaManager#requestCompleted(String, boolean)} with {@code sessionId}, {@code expires}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#requestCompleted(String, boolean)}
   */
  @Test
  public void testRequestCompletedWithSessionIdExpires_whenNull() {
    // Arrange, Act and Assert
    assertNull((new DeltaManager()).requestCompleted(null, true));
  }

  /**
   * Test {@link DeltaManager#requestCompleted(String)} with {@code sessionId}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#requestCompleted(String)}
   */
  @Test
  public void testRequestCompletedWithSessionId_when42() {
    // Arrange, Act and Assert
    assertNull((new DeltaManager()).requestCompleted("42"));
  }

  /**
   * Test {@link DeltaManager#requestCompleted(String)} with {@code sessionId}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#requestCompleted(String)}
   */
  @Test
  public void testRequestCompletedWithSessionId_whenNull() {
    // Arrange, Act and Assert
    assertNull((new DeltaManager()).requestCompleted(null));
  }

  /**
   * Test {@link DeltaManager#handleALL_SESSION_TRANSFERCOMPLETE(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleALL_SESSION_TRANSFERCOMPLETE(SessionMessage, Member)}
   */
  @Test
  public void testHandleALL_SESSION_TRANSFERCOMPLETE() throws UnsupportedEncodingException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleALL_SESSION_TRANSFERCOMPLETE(msg, new MemberImpl());

    // Assert
    assertEquals(1, deltaManager.getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertTrue(deltaManager.getStateTransferred());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_DELTA(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_DELTA(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_DELTA() throws IOException, ClassNotFoundException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleSESSION_DELTA(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_DELTA());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_DELTA(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_DELTA(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_DELTA2() throws IOException, ClassNotFoundException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), null, "Unique ID");

    // Act
    deltaManager.handleSESSION_DELTA(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_DELTA());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_ACCESSED(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_ACCESSED(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_ACCESSED() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleSESSION_ACCESSED(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_ACCESSED());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_ACCESSED(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_ACCESSED(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_ACCESSED2() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), null, "Unique ID");

    // Act
    deltaManager.handleSESSION_ACCESSED(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_ACCESSED());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_EXPIRED(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_EXPIRED(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_EXPIRED() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleSESSION_EXPIRED(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_EXPIRED());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_EXPIRED(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_EXPIRED(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_EXPIRED2() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), null, "Unique ID");

    // Act
    deltaManager.handleSESSION_EXPIRED(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_EXPIRED());
  }

  /**
   * Test {@link DeltaManager#handleSESSION_CREATED(SessionMessage, Member)}.
   * <ul>
   *   <li>Then {@link DeltaManager} (default constructor) ActiveSessions is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaManager#handleSESSION_CREATED(SessionMessage, Member)}
   */
  @Test
  public void testHandleSESSION_CREATED_thenDeltaManagerActiveSessionsIsOne() throws UnsupportedEncodingException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    deltaManager.setContext(new StandardContext());
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleSESSION_CREATED(msg, new MemberImpl());

    // Assert
    assertEquals(1, deltaManager.getActiveSessions());
    assertEquals(1, deltaManager.getMaxActive());
    assertEquals(1L, deltaManager.getCounterReceive_EVT_SESSION_CREATED());
    assertEquals(1L, deltaManager.getSessionCounter());
  }

  /**
   * Test {@link DeltaManager#handleCHANGE_SESSION_ID(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleCHANGE_SESSION_ID(SessionMessage, Member)}
   */
  @Test
  public void testHandleCHANGE_SESSION_ID() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleCHANGE_SESSION_ID(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_CHANGE_SESSION_ID());
  }

  /**
   * Test {@link DeltaManager#handleCHANGE_SESSION_ID(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleCHANGE_SESSION_ID(SessionMessage, Member)}
   */
  @Test
  public void testHandleCHANGE_SESSION_ID2() throws IOException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), null, "Unique ID");

    // Act
    deltaManager.handleCHANGE_SESSION_ID(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_CHANGE_SESSION_ID());
  }

  /**
   * Test {@link DeltaManager#handleALL_SESSION_NOCONTEXTMANAGER(SessionMessage, Member)}.
   * <p>
   * Method under test: {@link DeltaManager#handleALL_SESSION_NOCONTEXTMANAGER(SessionMessage, Member)}
   */
  @Test
  public void testHandleALL_SESSION_NOCONTEXTMANAGER() throws UnsupportedEncodingException {
    // Arrange
    DeltaManager deltaManager = new DeltaManager();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act
    deltaManager.handleALL_SESSION_NOCONTEXTMANAGER(msg, new MemberImpl());

    // Assert
    assertEquals(1L, deltaManager.getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER());
    assertTrue(deltaManager.isNoContextManagerReceived());
  }

  /**
   * Test {@link DeltaManager#cloneFromTemplate()}.
   * <p>
   * Method under test: {@link DeltaManager#cloneFromTemplate()}
   */
  @Test
  public void testCloneFromTemplate() {
    // Arrange and Act
    ClusterManager actualCloneFromTemplateResult = (new DeltaManager()).cloneFromTemplate();

    // Assert
    assertTrue(actualCloneFromTemplateResult instanceof DeltaManager);
    assertEquals("Clone-from-null", actualCloneFromTemplateResult.getName());
    assertEquals("NEW", ((DeltaManager) actualCloneFromTemplateResult).getStateName());
    assertEquals("SHA1PRNG", ((DeltaManager) actualCloneFromTemplateResult).getSecureRandomAlgorithm());
    assertEquals("org.apache.catalina.ha.session.DeltaManager",
        ((DeltaManager) actualCloneFromTemplateResult).getClassName());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getJvmRoute());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getSecureRandomClass());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getSecureRandomProvider());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getSessionAttributeNameFilter());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getSessionAttributeValueClassNameFilter());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getObjectName());
    assertNull(actualCloneFromTemplateResult.getContext());
    assertNull(((DeltaManager) actualCloneFromTemplateResult).getEngine());
    assertNull(actualCloneFromTemplateResult.getSessionIdGenerator());
    assertNull(actualCloneFromTemplateResult.getCluster());
    assertEquals(-1, ((DeltaManager) actualCloneFromTemplateResult).getMaxActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getActiveSessions());
    assertEquals(0, actualCloneFromTemplateResult.getMaxActive());
    assertEquals(0, actualCloneFromTemplateResult.getRejectedSessions());
    assertEquals(0, actualCloneFromTemplateResult.getSessionAverageAliveTime());
    assertEquals(0, actualCloneFromTemplateResult.getSessionCreateRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionExpireRate());
    assertEquals(0, actualCloneFromTemplateResult.getSessionMaxAliveTime());
    assertEquals(0, ((DeltaManager) actualCloneFromTemplateResult).getCounterNoStateTransferred());
    assertEquals(0,
        ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_ALL_SESSION_TRANSFERCOMPLETE());
    assertEquals(0, ((DeltaManager) actualCloneFromTemplateResult).getReceivedQueueSize());
    assertEquals(0, actualCloneFromTemplateResult.getInvalidatedSessions().length);
    assertEquals(0, ((DeltaManager) actualCloneFromTemplateResult).findLifecycleListeners().length);
    assertEquals(0L, actualCloneFromTemplateResult.getExpiredSessions());
    assertEquals(0L, actualCloneFromTemplateResult.getSessionCounter());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_ALL_SESSION_DATA());
    assertEquals(0L,
        ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_ALL_SESSION_NOCONTEXTMANAGER());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_SESSION_ACCESSED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_SESSION_CREATED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_SESSION_DELTA());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterReceive_EVT_SESSION_EXPIRED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_ALL_SESSION_DATA());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_CHANGE_SESSION_ID());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_GET_ALL_SESSIONS());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_SESSION_ACCESSED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_SESSION_CREATED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_SESSION_DELTA());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getCounterSend_EVT_SESSION_EXPIRED());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getSessionReplaceCounter());
    assertEquals(0L, ((DeltaManager) actualCloneFromTemplateResult).getProcessingTime());
    assertEquals(1000, ((DeltaManager) actualCloneFromTemplateResult).getSendAllSessionsSize());
    assertEquals(2000, ((DeltaManager) actualCloneFromTemplateResult).getSendAllSessionsWaitTime());
    assertEquals(6, ((DeltaManager) actualCloneFromTemplateResult).getProcessExpiresFrequency());
    assertEquals(60, ((DeltaManager) actualCloneFromTemplateResult).getStateTransferTimeout());
    assertEquals(LifecycleState.NEW, ((DeltaManager) actualCloneFromTemplateResult).getState());
    assertFalse(actualCloneFromTemplateResult.getNotifyBindingListenerOnUnchangedValue());
    assertFalse(actualCloneFromTemplateResult.getSessionActivityCheck());
    assertFalse(actualCloneFromTemplateResult.getSessionLastAccessAtStart());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).isRecordAllActions());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).getStateTransferred());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).isExpireSessionsOnShutdown());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).isNoContextManagerReceived());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).getPersistAuthentication());
    assertFalse(((DeltaManager) actualCloneFromTemplateResult).getWarnOnSessionAttributeFilterFailure());
    assertTrue(actualCloneFromTemplateResult.getNotifyAttributeListenerOnUnchangedValue());
    assertTrue(actualCloneFromTemplateResult.isNotifyListenersOnReplication());
    assertTrue(((DeltaManager) actualCloneFromTemplateResult).isNotifyContainerListenersOnReplication());
    assertTrue(((DeltaManager) actualCloneFromTemplateResult).isNotifySessionListenersOnReplication());
    assertTrue(((DeltaManager) actualCloneFromTemplateResult).isSendAllSessions());
    assertTrue(((DeltaManager) actualCloneFromTemplateResult).isStateTimestampDrop());
    assertTrue(((DeltaManager) actualCloneFromTemplateResult).getThrowOnFailure());
  }
}
