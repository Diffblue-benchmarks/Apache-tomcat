package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class NullRealmDiffblueTest {
  /**
   * Test {@link NullRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link NullRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange, Act and Assert
    assertNull((new NullRealm()).getPassword("janedoe"));
  }

  /**
   * Test new {@link NullRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NullRealm}
   */
  @Test
  public void testNewNullRealm() {
    // Arrange and Act
    NullRealm actualNullRealm = new NullRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualNullRealm.getRealmSuffix());
    assertEquals("/realm0", actualNullRealm.getRealmPath());
    assertEquals("NEW", actualNullRealm.getStateName());
    assertEquals("strict", actualNullRealm.getAllRolesMode());
    assertNull(actualNullRealm.getUserAttributes());
    assertNull(actualNullRealm.getX509UsernameRetrieverClassName());
    assertNull(actualNullRealm.userAttributesList);
    assertNull(actualNullRealm.getObjectName());
    assertNull(actualNullRealm.getContainer());
    assertNull(actualNullRealm.getCredentialHandler());
    assertNull(actualNullRealm.getServer());
    assertNull(actualNullRealm.x509UsernameRetriever);
    assertNull(actualNullRealm.containerLog);
    assertEquals(0, actualNullRealm.findLifecycleListeners().length);
    assertEquals(302, actualNullRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualNullRealm.getState());
    assertTrue(actualNullRealm.isAvailable());
    assertTrue(actualNullRealm.getValidate());
    assertTrue(actualNullRealm.isStripRealmForGss());
    assertTrue(actualNullRealm.getThrowOnFailure());
  }
}
