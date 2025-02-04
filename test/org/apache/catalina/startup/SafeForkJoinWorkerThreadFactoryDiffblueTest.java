package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import org.junit.Test;

public class SafeForkJoinWorkerThreadFactoryDiffblueTest {
  /**
   * Test {@link SafeForkJoinWorkerThreadFactory#newThread(ForkJoinPool)}.
   * <ul>
   *   <li>When commonPool.</li>
   *   <li>Then return PoolIndex is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SafeForkJoinWorkerThreadFactory#newThread(ForkJoinPool)}
   */
  @Test
  public void testNewThread_whenCommonPool_thenReturnPoolIndexIsZero() {
    // Arrange
    SafeForkJoinWorkerThreadFactory safeForkJoinWorkerThreadFactory = new SafeForkJoinWorkerThreadFactory();
    ForkJoinPool pool = ForkJoinPool.commonPool();

    // Act
    ForkJoinWorkerThread actualNewThreadResult = safeForkJoinWorkerThreadFactory.newThread(pool);

    // Assert
    assertEquals(0, actualNewThreadResult.getPoolIndex());
    assertSame(pool, actualNewThreadResult.getPool());
  }
}
