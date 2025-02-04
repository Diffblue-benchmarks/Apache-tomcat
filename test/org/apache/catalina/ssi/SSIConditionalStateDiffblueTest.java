package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class SSIConditionalStateDiffblueTest {
  /**
   * Test new {@link SSIConditionalState} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SSIConditionalState}
   */
  @Test
  public void testNewSSIConditionalState() {
    // Arrange and Act
    SSIConditionalState actualSsiConditionalState = new SSIConditionalState();

    // Assert
    assertEquals(0, actualSsiConditionalState.nestingCount);
    assertFalse(actualSsiConditionalState.branchTaken);
    assertFalse(actualSsiConditionalState.processConditionalCommandsOnly);
  }
}
