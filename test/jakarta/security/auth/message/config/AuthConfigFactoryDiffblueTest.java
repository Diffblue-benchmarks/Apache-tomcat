package jakarta.security.auth.message.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.junit.Test;

public class AuthConfigFactoryDiffblueTest {
  /**
   * Test {@link AuthConfigFactory#getFactory()}.
   * <p>
   * Method under test: {@link AuthConfigFactory#getFactory()}
   */
  @Test
  public void testGetFactory() {
    // Arrange and Act
    AuthConfigFactory actualFactory = AuthConfigFactory.getFactory();

    // Assert
    assertTrue(actualFactory instanceof AuthConfigFactoryImpl);
    assertNull(actualFactory.getRegistrationContext("Registration ID"));
    assertEquals(0, actualFactory.getRegistrationIDs(null).length);
    assertFalse(actualFactory.removeRegistration("Registration ID"));
  }
}
