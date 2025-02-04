package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class InterceptorSFDiffblueTest {
  /**
   * Test new {@link InterceptorSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link InterceptorSF}
   */
  @Test
  public void testNewInterceptorSF() {
    // Arrange and Act
    InterceptorSF actualInterceptorSF = new InterceptorSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualInterceptorSF.getInfo());
    assertNull(actualInterceptorSF.getRegistry());
  }
}
