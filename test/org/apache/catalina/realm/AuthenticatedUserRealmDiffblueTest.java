package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.security.Principal;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class AuthenticatedUserRealmDiffblueTest {
  /**
   * Test {@link AuthenticatedUserRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link AuthenticatedUserRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange, Act and Assert
    assertNull((new AuthenticatedUserRealm()).getPassword("janedoe"));
  }

  /**
   * Test {@link AuthenticatedUserRealm#getPrincipal(String)} with {@code username}.
   * <p>
   * Method under test: {@link AuthenticatedUserRealm#getPrincipal(String)}
   */
  @Test
  public void testGetPrincipalWithUsername() {
    // Arrange and Act
    Principal actualPrincipal = (new AuthenticatedUserRealm()).getPrincipal("janedoe");
    String actualName = actualPrincipal.getName();

    // Assert
    assertTrue(actualPrincipal instanceof GenericPrincipal);
    assertEquals("janedoe", actualPrincipal.getName());
    assertEquals("janedoe", actualName);
    assertNull(((GenericPrincipal) actualPrincipal).userPrincipal);
    assertNull(((GenericPrincipal) actualPrincipal).attributes);
    assertNull(((GenericPrincipal) actualPrincipal).loginContext);
    assertNull(((GenericPrincipal) actualPrincipal).getGssCredential());
    assertEquals(0, ((GenericPrincipal) actualPrincipal).getRoles().length);
    assertEquals(0, ((GenericPrincipal) actualPrincipal).roles.length);
    assertSame(actualPrincipal, ((GenericPrincipal) actualPrincipal).getUserPrincipal());
  }

  /**
   * Test new {@link AuthenticatedUserRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link AuthenticatedUserRealm}
   */
  @Test
  public void testNewAuthenticatedUserRealm() {
    // Arrange and Act
    AuthenticatedUserRealm actualAuthenticatedUserRealm = new AuthenticatedUserRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualAuthenticatedUserRealm.getRealmSuffix());
    assertEquals("/realm0", actualAuthenticatedUserRealm.getRealmPath());
    assertEquals("NEW", actualAuthenticatedUserRealm.getStateName());
    assertEquals("strict", actualAuthenticatedUserRealm.getAllRolesMode());
    assertNull(actualAuthenticatedUserRealm.getUserAttributes());
    assertNull(actualAuthenticatedUserRealm.getX509UsernameRetrieverClassName());
    assertNull(actualAuthenticatedUserRealm.userAttributesList);
    assertNull(actualAuthenticatedUserRealm.getObjectName());
    assertNull(actualAuthenticatedUserRealm.getContainer());
    assertNull(actualAuthenticatedUserRealm.getCredentialHandler());
    assertNull(actualAuthenticatedUserRealm.getServer());
    assertNull(actualAuthenticatedUserRealm.x509UsernameRetriever);
    assertNull(actualAuthenticatedUserRealm.containerLog);
    assertEquals(0, actualAuthenticatedUserRealm.findLifecycleListeners().length);
    assertEquals(302, actualAuthenticatedUserRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualAuthenticatedUserRealm.getState());
    assertTrue(actualAuthenticatedUserRealm.isAvailable());
    assertTrue(actualAuthenticatedUserRealm.getValidate());
    assertTrue(actualAuthenticatedUserRealm.isStripRealmForGss());
    assertTrue(actualAuthenticatedUserRealm.getThrowOnFailure());
  }
}
