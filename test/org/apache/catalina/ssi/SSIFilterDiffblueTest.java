package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SSIFilterDiffblueTest {
  /**
   * Test new {@link SSIFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SSIFilter}
   */
  @Test
  public void testNewSSIFilter() {
    // Arrange and Act
    SSIFilter actualSsiFilter = new SSIFilter();

    // Assert
    assertEquals("text/x-server-parsed-html(;.*)?", actualSsiFilter.shtmlRegEx.pattern());
    assertNull(actualSsiFilter.getFilterConfig());
    assertNull(actualSsiFilter.expires);
    assertNull(actualSsiFilter.contentTypeRegEx);
    assertEquals(0, actualSsiFilter.debug);
    assertFalse(actualSsiFilter.allowExec);
    assertFalse(actualSsiFilter.isVirtualWebappRelative);
  }
}
