package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TrustStoreCallbackDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TrustStoreCallback}
   *   <li>{@link TrustStoreCallback#getTrustStore()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new TrustStoreCallback()).getTrustStore());
  }
}
