package jakarta.mail;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class AuthenticatorDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Authenticator}
   *   <li>{@link Authenticator#getPasswordAuthentication()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new Authenticator()).getPasswordAuthentication());
  }
}
