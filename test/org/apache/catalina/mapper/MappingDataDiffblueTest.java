package org.apache.catalina.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class MappingDataDiffblueTest {
  /**
   * Test new {@link MappingData} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MappingData}
   */
  @Test
  public void testNewMappingData() {
    // Arrange and Act
    MappingData actualMappingData = new MappingData();

    // Assert
    assertNull(actualMappingData.contexts);
    assertNull(actualMappingData.matchType);
    assertNull(actualMappingData.context);
    assertNull(actualMappingData.host);
    assertNull(actualMappingData.wrapper);
    assertEquals(0, actualMappingData.contextSlashCount);
    assertFalse(actualMappingData.jspWildCard);
  }
}
