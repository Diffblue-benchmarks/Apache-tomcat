package org.apache.catalina.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StatusManagerServletDiffblueTest {
  /**
   * Test new {@link StatusManagerServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StatusManagerServlet}
   */
  @Test
  public void testNewStatusManagerServlet() {
    // Arrange and Act
    StatusManagerServlet actualStatusManagerServlet = new StatusManagerServlet();

    // Assert
    assertEquals("", actualStatusManagerServlet.getServletInfo());
    assertNull(actualStatusManagerServlet.getServletConfig());
    assertNull(actualStatusManagerServlet.mBeanServer);
    assertTrue(actualStatusManagerServlet.globalRequestProcessors.isEmpty());
    assertTrue(actualStatusManagerServlet.requestProcessors.isEmpty());
    assertTrue(actualStatusManagerServlet.threadPools.isEmpty());
  }
}
