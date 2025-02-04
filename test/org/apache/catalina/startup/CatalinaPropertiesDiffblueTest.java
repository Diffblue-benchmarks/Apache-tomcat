package org.apache.catalina.startup;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CatalinaPropertiesDiffblueTest {
  /**
   * Test {@link CatalinaProperties#getProperty(String)}.
   * <p>
   * Method under test: {@link CatalinaProperties#getProperty(String)}
   */
  @Test
  public void testGetProperty() {
    // Arrange, Act and Assert
    assertNull(CatalinaProperties.getProperty("Name"));
  }
}
