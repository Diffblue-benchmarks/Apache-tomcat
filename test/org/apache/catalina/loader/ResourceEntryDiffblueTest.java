package org.apache.catalina.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ResourceEntryDiffblueTest {
  /**
   * Test new {@link ResourceEntry} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ResourceEntry}
   */
  @Test
  public void testNewResourceEntry() {
    // Arrange and Act
    ResourceEntry actualResourceEntry = new ResourceEntry();

    // Assert
    assertNull(actualResourceEntry.loadedClass);
    assertEquals(-1L, actualResourceEntry.lastModified);
  }
}
