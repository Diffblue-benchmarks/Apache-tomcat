package jakarta.security.auth.message;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AuthStatusDiffblueTest {
  /**
   * Test {@link AuthStatus#toString()}.
   * <p>
   * Method under test: {@link AuthStatus#toString()}
   */
  @Test
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("FAILURE", AuthStatus.FAILURE.toString());
  }
}
