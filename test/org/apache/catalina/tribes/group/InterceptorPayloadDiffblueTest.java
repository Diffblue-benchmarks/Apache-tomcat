package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertNull;
import org.apache.catalina.tribes.ErrorHandler;
import org.junit.Test;

public class InterceptorPayloadDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link InterceptorPayload}
   *   <li>{@link InterceptorPayload#setErrorHandler(ErrorHandler)}
   *   <li>{@link InterceptorPayload#getErrorHandler()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    InterceptorPayload actualInterceptorPayload = new InterceptorPayload();
    actualInterceptorPayload.setErrorHandler(null);

    // Assert
    assertNull(actualInterceptorPayload.getErrorHandler());
  }
}
