package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ApplicationContextDiffblueTest {
  /**
   * Test {@link ApplicationContext#stripPathParams(String)}.
   * <p>
   * Method under test: {@link ApplicationContext#stripPathParams(String)}
   */
  @Test
  public void testStripPathParams() {
    // Arrange, Act and Assert
    assertEquals("Input", ApplicationContext.stripPathParams("Input"));
  }
}
