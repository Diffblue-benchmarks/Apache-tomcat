package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.catalina.WebResourceLockSet.ResourceLock;
import org.junit.Test;

public class WebResourceLockSetDiffblueTest {
  /**
   * Test ResourceLock {@link ResourceLock#ResourceLock(String)}.
   * <p>
   * Method under test: {@link ResourceLock#ResourceLock(String)}
   */
  @Test
  public void testResourceLockNewResourceLock() {
    // Arrange and Act
    ResourceLock actualResourceLock = new ResourceLock("Key");

    // Assert
    assertEquals("Key", actualResourceLock.key);
    ReentrantReadWriteLock reentrantReadWriteLock = actualResourceLock.reentrantLock;
    assertEquals(0, reentrantReadWriteLock.getQueueLength());
    assertEquals(0, reentrantReadWriteLock.getReadHoldCount());
    assertEquals(0, reentrantReadWriteLock.getReadLockCount());
    assertEquals(0, reentrantReadWriteLock.getWriteHoldCount());
    assertFalse(reentrantReadWriteLock.hasQueuedThreads());
    assertFalse(reentrantReadWriteLock.isFair());
    assertFalse(reentrantReadWriteLock.isWriteLocked());
    assertFalse(reentrantReadWriteLock.isWriteLockedByCurrentThread());
  }
}
