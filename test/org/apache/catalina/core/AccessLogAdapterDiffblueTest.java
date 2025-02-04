package org.apache.catalina.core;

import static org.junit.Assert.assertFalse;
import org.apache.catalina.AccessLog;
import org.apache.catalina.core.StandardEngine.NoopAccessLog;
import org.junit.Test;

public class AccessLogAdapterDiffblueTest {
  /**
   * Test {@link AccessLogAdapter#AccessLogAdapter(AccessLog)}.
   * <ul>
   *   <li>When {@link NoopAccessLog} (default constructor).</li>
   *   <li>Then return not RequestAttributesEnabled.</li>
   * </ul>
   * <p>
   * Method under test: {@link AccessLogAdapter#AccessLogAdapter(AccessLog)}
   */
  @Test
  public void testNewAccessLogAdapter_whenNoopAccessLog_thenReturnNotRequestAttributesEnabled() {
    // Arrange, Act and Assert
    assertFalse((new AccessLogAdapter(new NoopAccessLog())).getRequestAttributesEnabled());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AccessLogAdapter#setRequestAttributesEnabled(boolean)}
   *   <li>{@link AccessLogAdapter#getRequestAttributesEnabled()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    AccessLogAdapter accessLogAdapter = new AccessLogAdapter(new NoopAccessLog());

    // Act
    accessLogAdapter.setRequestAttributesEnabled(true);

    // Assert
    assertFalse(accessLogAdapter.getRequestAttributesEnabled());
  }
}
