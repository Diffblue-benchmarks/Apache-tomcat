package org.apache.catalina.core;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ApplicationFilterChainDiffblueTest {
  /**
   * Test {@link ApplicationFilterChain#getLastServicedRequest()}.
   * <p>
   * Method under test: {@link ApplicationFilterChain#getLastServicedRequest()}
   */
  @Test
  public void testGetLastServicedRequest() {
    // Arrange, Act and Assert
    assertNull(ApplicationFilterChain.getLastServicedRequest());
  }

  /**
   * Test {@link ApplicationFilterChain#getLastServicedResponse()}.
   * <p>
   * Method under test: {@link ApplicationFilterChain#getLastServicedResponse()}
   */
  @Test
  public void testGetLastServicedResponse() {
    // Arrange, Act and Assert
    assertNull(ApplicationFilterChain.getLastServicedResponse());
  }
}
