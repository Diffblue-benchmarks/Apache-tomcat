package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class FastRateLimiterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FastRateLimiter#setDuration(int)}
   *   <li>{@link FastRateLimiter#setPolicyName(String)}
   *   <li>{@link FastRateLimiter#setRequests(int)}
   *   <li>{@link FastRateLimiter#getBucketCounter()}
   *   <li>{@link FastRateLimiter#getDuration()}
   *   <li>{@link FastRateLimiter#getPolicyName()}
   *   <li>{@link FastRateLimiter#getRequests()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FastRateLimiter fastRateLimiter = new FastRateLimiter();

    // Act
    fastRateLimiter.setDuration(1);
    fastRateLimiter.setPolicyName("Name");
    fastRateLimiter.setRequests(1);
    TimeBucketCounter actualBucketCounter = fastRateLimiter.getBucketCounter();
    int actualDuration = fastRateLimiter.getDuration();
    String actualPolicyName = fastRateLimiter.getPolicyName();

    // Assert
    assertEquals("Name", actualPolicyName);
    assertNull(actualBucketCounter);
    assertEquals(0, actualDuration);
    assertEquals(0, fastRateLimiter.getRequests());
  }

  /**
   * Test new {@link FastRateLimiter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FastRateLimiter}
   */
  @Test
  public void testNewFastRateLimiter() {
    // Arrange and Act
    FastRateLimiter actualFastRateLimiter = new FastRateLimiter();

    // Assert
    assertNull(actualFastRateLimiter.getBucketCounter());
    assertEquals(0, actualFastRateLimiter.getDuration());
    assertEquals(0, actualFastRateLimiter.getRequests());
    assertEquals(0, actualFastRateLimiter.duration);
    assertEquals(0, actualFastRateLimiter.requests);
  }
}
