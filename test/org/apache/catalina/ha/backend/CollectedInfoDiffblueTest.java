package org.apache.catalina.ha.backend;

import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class CollectedInfoDiffblueTest {
  /**
   * Test {@link CollectedInfo#CollectedInfo(String, int)}.
   * <p>
   * Method under test: {@link CollectedInfo#CollectedInfo(String, int)}
   */
  @Test
  public void testNewCollectedInfo() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> new CollectedInfo("localhost", 8080));

  }
}
