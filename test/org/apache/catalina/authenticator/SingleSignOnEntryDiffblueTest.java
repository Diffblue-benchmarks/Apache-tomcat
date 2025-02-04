package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import org.junit.Test;

public class SingleSignOnEntryDiffblueTest {
  /**
   * Test {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}.
   * <ul>
   *   <li>When {@code Auth Type}.</li>
   *   <li>Then return {@code Auth Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}
   */
  @Test
  public void testNewSingleSignOnEntry_whenAuthType_thenReturnAuthType() {
    // Arrange
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    SingleSignOnEntry actualSingleSignOnEntry = new SingleSignOnEntry(principal, "Auth Type", "janedoe", "iloveyou");

    // Assert
    assertEquals("Auth Type", actualSingleSignOnEntry.getAuthType());
    assertEquals("iloveyou", actualSingleSignOnEntry.getPassword());
    assertEquals("janedoe", actualSingleSignOnEntry.getUsername());
    assertFalse(actualSingleSignOnEntry.getCanReauthenticate());
    assertSame(principal, actualSingleSignOnEntry.getPrincipal());
  }

  /**
   * Test {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}.
   * <ul>
   *   <li>When {@code BASIC}.</li>
   *   <li>Then return AuthType is {@code BASIC}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}
   */
  @Test
  public void testNewSingleSignOnEntry_whenBasic_thenReturnAuthTypeIsBasic() {
    // Arrange
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    SingleSignOnEntry actualSingleSignOnEntry = new SingleSignOnEntry(principal, "BASIC", "janedoe", "iloveyou");

    // Assert
    assertEquals("BASIC", actualSingleSignOnEntry.getAuthType());
    assertEquals("iloveyou", actualSingleSignOnEntry.getPassword());
    assertEquals("janedoe", actualSingleSignOnEntry.getUsername());
    assertTrue(actualSingleSignOnEntry.getCanReauthenticate());
    assertSame(principal, actualSingleSignOnEntry.getPrincipal());
  }

  /**
   * Test {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}.
   * <ul>
   *   <li>When {@code FORM}.</li>
   *   <li>Then return AuthType is {@code FORM}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOnEntry#SingleSignOnEntry(Principal, String, String, String)}
   */
  @Test
  public void testNewSingleSignOnEntry_whenForm_thenReturnAuthTypeIsForm() {
    // Arrange
    UserPrincipal principal = new UserPrincipal("principal");

    // Act
    SingleSignOnEntry actualSingleSignOnEntry = new SingleSignOnEntry(principal, "FORM", "janedoe", "iloveyou");

    // Assert
    assertEquals("FORM", actualSingleSignOnEntry.getAuthType());
    assertEquals("iloveyou", actualSingleSignOnEntry.getPassword());
    assertEquals("janedoe", actualSingleSignOnEntry.getUsername());
    assertTrue(actualSingleSignOnEntry.getCanReauthenticate());
    assertSame(principal, actualSingleSignOnEntry.getPrincipal());
  }

  /**
   * Test {@link SingleSignOnEntry#findSessions()}.
   * <p>
   * Method under test: {@link SingleSignOnEntry#findSessions()}
   */
  @Test
  public void testFindSessions() {
    // Arrange, Act and Assert
    assertTrue(
        (new SingleSignOnEntry(new UserPrincipal("principal"), "Auth Type", "janedoe", "iloveyou")).findSessions()
            .isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SingleSignOnEntry#getAuthType()}
   *   <li>{@link SingleSignOnEntry#getCanReauthenticate()}
   *   <li>{@link SingleSignOnEntry#getPassword()}
   *   <li>{@link SingleSignOnEntry#getPrincipal()}
   *   <li>{@link SingleSignOnEntry#getUsername()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    UserPrincipal principal = new UserPrincipal("principal");
    SingleSignOnEntry singleSignOnEntry = new SingleSignOnEntry(principal, "Auth Type", "janedoe", "iloveyou");

    // Act
    String actualAuthType = singleSignOnEntry.getAuthType();
    boolean actualCanReauthenticate = singleSignOnEntry.getCanReauthenticate();
    String actualPassword = singleSignOnEntry.getPassword();
    Principal actualPrincipal = singleSignOnEntry.getPrincipal();

    // Assert
    assertEquals("Auth Type", actualAuthType);
    assertEquals("iloveyou", actualPassword);
    assertEquals("janedoe", singleSignOnEntry.getUsername());
    assertFalse(actualCanReauthenticate);
    assertSame(principal, actualPrincipal);
  }

  /**
   * Test {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}.
   * <p>
   * Method under test: {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}
   */
  @Test
  public void testUpdateCredentials() {
    // Arrange
    SingleSignOnEntry singleSignOnEntry = new SingleSignOnEntry(new UserPrincipal("principal"), "Auth Type", "janedoe",
        "iloveyou");

    // Act
    singleSignOnEntry.updateCredentials(new UserPrincipal("principal"), "Auth Type", "janedoe", "iloveyou");

    // Assert that nothing has changed
    assertEquals("Auth Type", singleSignOnEntry.getAuthType());
    assertFalse(singleSignOnEntry.getCanReauthenticate());
  }

  /**
   * Test {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}.
   * <p>
   * Method under test: {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}
   */
  @Test
  public void testUpdateCredentials2() {
    // Arrange
    SingleSignOnEntry singleSignOnEntry = new SingleSignOnEntry(new UserPrincipal("principal"), "Auth Type", "janedoe",
        "iloveyou");

    // Act
    singleSignOnEntry.updateCredentials(new UserPrincipal("principal"), "BASIC", "janedoe", "iloveyou");

    // Assert
    assertEquals("BASIC", singleSignOnEntry.getAuthType());
    assertTrue(singleSignOnEntry.getCanReauthenticate());
  }

  /**
   * Test {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}.
   * <p>
   * Method under test: {@link SingleSignOnEntry#updateCredentials(Principal, String, String, String)}
   */
  @Test
  public void testUpdateCredentials3() {
    // Arrange
    SingleSignOnEntry singleSignOnEntry = new SingleSignOnEntry(new UserPrincipal("principal"), "Auth Type", "janedoe",
        "iloveyou");

    // Act
    singleSignOnEntry.updateCredentials(new UserPrincipal("principal"), "FORM", "janedoe", "iloveyou");

    // Assert
    assertEquals("FORM", singleSignOnEntry.getAuthType());
    assertTrue(singleSignOnEntry.getCanReauthenticate());
  }
}
