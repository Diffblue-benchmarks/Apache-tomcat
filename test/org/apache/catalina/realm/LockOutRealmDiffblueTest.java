package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.realm.LockOutRealm.LockRecord;
import org.junit.Test;

public class LockOutRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LockOutRealm#setCacheRemovalWarningTime(int)}
   *   <li>{@link LockOutRealm#setCacheSize(int)}
   *   <li>{@link LockOutRealm#setFailureCount(int)}
   *   <li>{@link LockOutRealm#setLockOutTime(int)}
   *   <li>{@link LockOutRealm#getCacheRemovalWarningTime()}
   *   <li>{@link LockOutRealm#getCacheSize()}
   *   <li>{@link LockOutRealm#getFailureCount()}
   *   <li>{@link LockOutRealm#getLockOutTime()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    LockOutRealm lockOutRealm = new LockOutRealm();

    // Act
    lockOutRealm.setCacheRemovalWarningTime(42);
    lockOutRealm.setCacheSize(3);
    lockOutRealm.setFailureCount(3);
    lockOutRealm.setLockOutTime(1);
    int actualCacheRemovalWarningTime = lockOutRealm.getCacheRemovalWarningTime();
    int actualCacheSize = lockOutRealm.getCacheSize();
    int actualFailureCount = lockOutRealm.getFailureCount();

    // Assert
    assertEquals(1, lockOutRealm.getLockOutTime());
    assertEquals(3, actualCacheSize);
    assertEquals(3, actualFailureCount);
    assertEquals(42, actualCacheRemovalWarningTime);
  }

  /**
   * Test LockRecord {@link LockRecord#getFailures()}.
   * <p>
   * Method under test: {@link LockRecord#getFailures()}
   */
  @Test
  public void testLockRecordGetFailures() {
    // Arrange, Act and Assert
    assertEquals(0, (new LockRecord()).getFailures());
  }

  /**
   * Test LockRecord {@link LockRecord#getLastFailureTime()}.
   * <p>
   * Method under test: {@link LockRecord#getLastFailureTime()}
   */
  @Test
  public void testLockRecordGetLastFailureTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new LockRecord()).getLastFailureTime());
  }

  /**
   * Test LockRecord new {@link LockRecord} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LockRecord}
   */
  @Test
  public void testLockRecordNewLockRecord() {
    // Arrange and Act
    LockRecord actualLockRecord = new LockRecord();

    // Assert
    assertEquals(0, actualLockRecord.getFailures());
    assertEquals(0L, actualLockRecord.getLastFailureTime());
  }

  /**
   * Test LockRecord {@link LockRecord#registerFailure()}.
   * <p>
   * Method under test: {@link LockRecord#registerFailure()}
   */
  @Test
  public void testLockRecordRegisterFailure() {
    // Arrange
    LockRecord lockRecord = new LockRecord();

    // Act
    lockRecord.registerFailure();

    // Assert
    assertEquals(1, lockRecord.getFailures());
  }

  /**
   * Test LockRecord {@link LockRecord#setFailures(int)}.
   * <p>
   * Method under test: {@link LockRecord#setFailures(int)}
   */
  @Test
  public void testLockRecordSetFailures() {
    // Arrange
    LockRecord lockRecord = new LockRecord();

    // Act
    lockRecord.setFailures(1);

    // Assert
    assertEquals(1, lockRecord.getFailures());
  }

  /**
   * Test new {@link LockOutRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LockOutRealm}
   */
  @Test
  public void testNewLockOutRealm() {
    // Arrange and Act
    LockOutRealm actualLockOutRealm = new LockOutRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualLockOutRealm.getRealmSuffix());
    assertEquals("/realm0", actualLockOutRealm.getRealmPath());
    assertEquals("NEW", actualLockOutRealm.getStateName());
    assertEquals("strict", actualLockOutRealm.getAllRolesMode());
    assertNull(actualLockOutRealm.getUserAttributes());
    assertNull(actualLockOutRealm.getX509UsernameRetrieverClassName());
    assertNull(actualLockOutRealm.userAttributesList);
    assertNull(actualLockOutRealm.failedUsers);
    assertNull(actualLockOutRealm.getObjectName());
    assertNull(actualLockOutRealm.getContainer());
    assertNull(actualLockOutRealm.getCredentialHandler());
    assertNull(actualLockOutRealm.getServer());
    assertNull(actualLockOutRealm.x509UsernameRetriever);
    assertNull(actualLockOutRealm.containerLog);
    assertEquals(0, actualLockOutRealm.getNestedRealms().length);
    assertEquals(0, actualLockOutRealm.getRealms().length);
    assertEquals(0, actualLockOutRealm.findLifecycleListeners().length);
    assertEquals(1000, actualLockOutRealm.getCacheSize());
    assertEquals(300, actualLockOutRealm.getLockOutTime());
    assertEquals(302, actualLockOutRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(3600, actualLockOutRealm.getCacheRemovalWarningTime());
    assertEquals(5, actualLockOutRealm.getFailureCount());
    assertEquals(LifecycleState.NEW, actualLockOutRealm.getState());
    assertTrue(actualLockOutRealm.realms.isEmpty());
    assertTrue(actualLockOutRealm.isAvailable());
    assertTrue(actualLockOutRealm.getValidate());
    assertTrue(actualLockOutRealm.isStripRealmForGss());
    assertTrue(actualLockOutRealm.getThrowOnFailure());
  }
}
