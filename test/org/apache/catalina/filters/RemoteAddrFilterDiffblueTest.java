package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RemoteAddrFilterDiffblueTest {
  /**
   * Test new {@link RemoteAddrFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RemoteAddrFilter}
   */
  @Test
  public void testNewRemoteAddrFilter() {
    // Arrange and Act
    RemoteAddrFilter actualRemoteAddrFilter = new RemoteAddrFilter();

    // Assert
    assertNull(actualRemoteAddrFilter.getAllow());
    assertNull(actualRemoteAddrFilter.getDeny());
    assertNull(actualRemoteAddrFilter.allow);
    assertNull(actualRemoteAddrFilter.deny);
    assertEquals(403, actualRemoteAddrFilter.getDenyStatus());
    assertTrue(actualRemoteAddrFilter.isConfigProblemFatal());
  }
}
