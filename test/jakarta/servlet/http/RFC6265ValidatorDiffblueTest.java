package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.BitSet;
import org.junit.Test;

public class RFC6265ValidatorDiffblueTest {
  /**
   * Test new {@link RFC6265Validator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RFC6265Validator}
   */
  @Test
  public void testNewRFC6265Validator() {
    // Arrange, Act and Assert
    BitSet bitSet = (new RFC6265Validator()).allowed;
    assertEquals(128, bitSet.size());
    assertFalse(bitSet.isEmpty());
  }
}
