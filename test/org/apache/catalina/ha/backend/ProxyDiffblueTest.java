package org.apache.catalina.ha.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ProxyDiffblueTest {
  /**
   * Test new {@link Proxy} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Proxy}
   */
  @Test
  public void testNewProxy() {
    // Arrange and Act
    Proxy actualProxy = new Proxy();

    // Assert
    assertNull(actualProxy.address);
    assertEquals(80, actualProxy.port);
  }
}
