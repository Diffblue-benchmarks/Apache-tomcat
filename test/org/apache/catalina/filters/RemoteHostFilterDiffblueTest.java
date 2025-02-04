package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RemoteHostFilterDiffblueTest {
  /**
   * Test new {@link RemoteHostFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RemoteHostFilter}
   */
  @Test
  public void testNewRemoteHostFilter() {
    // Arrange and Act
    RemoteHostFilter actualRemoteHostFilter = new RemoteHostFilter();

    // Assert
    assertNull(actualRemoteHostFilter.getAllow());
    assertNull(actualRemoteHostFilter.getDeny());
    assertNull(actualRemoteHostFilter.allow);
    assertNull(actualRemoteHostFilter.deny);
    assertEquals(403, actualRemoteHostFilter.getDenyStatus());
    assertTrue(actualRemoteHostFilter.isConfigProblemFatal());
  }
}
