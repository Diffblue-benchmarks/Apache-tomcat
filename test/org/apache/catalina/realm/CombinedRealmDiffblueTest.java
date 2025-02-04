package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.List;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Realm;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardWrapper;
import org.junit.Test;

public class CombinedRealmDiffblueTest {
  /**
   * Test {@link CombinedRealm#addRealm(Realm)}.
   * <p>
   * Method under test: {@link CombinedRealm#addRealm(Realm)}
   */
  @Test
  public void testAddRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    AuthenticatedUserRealm theRealm = new AuthenticatedUserRealm();

    // Act
    combinedRealm.addRealm(theRealm);

    // Assert
    ObjectName[] realms = combinedRealm.getRealms();
    assertNull(realms[0]);
    List<Realm> realmList = combinedRealm.realms;
    assertEquals(1, realmList.size());
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    assertEquals(1, nestedRealms.length);
    assertEquals(1, realms.length);
    assertSame(theRealm, realmList.get(0));
    assertSame(theRealm, nestedRealms[0]);
  }

  /**
   * Test {@link CombinedRealm#getRealms()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#getRealms()}
   */
  @Test
  public void testGetRealms_givenCombinedRealmAddRealmAuthenticatedUserRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    // Act
    ObjectName[] actualRealms = combinedRealm.getRealms();

    // Assert
    assertNull(actualRealms[0]);
    assertEquals(1, actualRealms.length);
  }

  /**
   * Test {@link CombinedRealm#getRealms()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@code null}.</li>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#getRealms()}
   */
  @Test
  public void testGetRealms_givenCombinedRealmAddRealmNull_thenReturnFirstElementIsNull() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(null);

    // Act
    ObjectName[] actualRealms = combinedRealm.getRealms();

    // Assert
    assertNull(actualRealms[0]);
    assertEquals(1, actualRealms.length);
  }

  /**
   * Test {@link CombinedRealm#getRealms()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor).</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#getRealms()}
   */
  @Test
  public void testGetRealms_givenCombinedRealm_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new CombinedRealm()).getRealms().length);
  }

  /**
   * Test {@link CombinedRealm#getNestedRealms()}.
   * <p>
   * Method under test: {@link CombinedRealm#getNestedRealms()}
   */
  @Test
  public void testGetNestedRealms() {
    // Arrange, Act and Assert
    assertEquals(0, (new CombinedRealm()).getNestedRealms().length);
  }

  /**
   * Test {@link CombinedRealm#setContainer(Container)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link JAASRealm} (default constructor).</li>
   *   <li>Then first element {@link JAASRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_givenCombinedRealmAddRealmJAASRealm_thenFirstElementJAASRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new JAASRealm());
    StandardContext container = new StandardContext();

    // Act
    combinedRealm.setContainer(container);

    // Assert
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    Realm realm = nestedRealms[0];
    assertTrue(realm instanceof JAASRealm);
    assertEquals(",realmPath=/realm0/realm0", ((JAASRealm) realm).getRealmSuffix());
    assertEquals("/realm0/realm0", ((JAASRealm) realm).getRealmPath());
    assertEquals("Catalina", ((JAASRealm) realm).getDomainInternal());
    assertEquals("Catalina", ((JAASRealm) realm).getDomain());
    assertEquals("other", ((JAASRealm) realm).getAppName());
    assertEquals(1, nestedRealms.length);
    assertSame(container, realm.getContainer());
  }

  /**
   * Test {@link CombinedRealm#setContainer(Container)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor).</li>
   *   <li>Then {@link CombinedRealm} (default constructor) DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_givenCombinedRealm_thenCombinedRealmDomainInternalIsCatalina() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    StandardContext container = new StandardContext();

    // Act
    combinedRealm.setContainer(container);

    // Assert
    assertEquals("Catalina", combinedRealm.getDomainInternal());
    assertEquals("Catalina", combinedRealm.getDomain());
    assertSame(container, combinedRealm.getContainer());
  }

  /**
   * Test {@link CombinedRealm#setContainer(Container)}.
   * <ul>
   *   <li>Then first element {@link AuthenticatedUserRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_thenFirstElementAuthenticatedUserRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());
    StandardContext container = new StandardContext();

    // Act
    combinedRealm.setContainer(container);

    // Assert
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    Realm realm = nestedRealms[0];
    assertTrue(realm instanceof AuthenticatedUserRealm);
    assertEquals(",realmPath=/realm0/realm0", ((AuthenticatedUserRealm) realm).getRealmSuffix());
    assertEquals("/realm0/realm0", ((AuthenticatedUserRealm) realm).getRealmPath());
    assertEquals("Catalina", ((AuthenticatedUserRealm) realm).getDomainInternal());
    assertEquals("Catalina", ((AuthenticatedUserRealm) realm).getDomain());
    assertEquals(1, nestedRealms.length);
    assertSame(container, realm.getContainer());
  }

  /**
   * Test {@link CombinedRealm#setContainer(Container)}.
   * <ul>
   *   <li>Then first element {@link CombinedRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_thenFirstElementCombinedRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new CombinedRealm());
    StandardContext container = new StandardContext();

    // Act
    combinedRealm.setContainer(container);

    // Assert
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    Realm realm = nestedRealms[0];
    assertTrue(realm instanceof CombinedRealm);
    assertEquals(",realmPath=/realm0/realm0", ((CombinedRealm) realm).getRealmSuffix());
    assertEquals("/realm0/realm0", ((CombinedRealm) realm).getRealmPath());
    assertEquals("Catalina", ((CombinedRealm) realm).getDomainInternal());
    assertEquals("Catalina", ((CombinedRealm) realm).getDomain());
    assertEquals(1, nestedRealms.length);
    assertSame(container, realm.getContainer());
  }

  /**
   * Test {@link CombinedRealm#destroyInternal()}.
   * <ul>
   *   <li>Then first element {@link AuthenticatedUserRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenFirstElementAuthenticatedUserRealm() throws LifecycleException {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    // Act
    combinedRealm.destroyInternal();

    // Assert
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    Realm realm = nestedRealms[0];
    assertTrue(realm instanceof AuthenticatedUserRealm);
    assertEquals("DESTROYED", ((AuthenticatedUserRealm) realm).getStateName());
    assertEquals(1, nestedRealms.length);
    assertEquals(LifecycleState.DESTROYED, ((AuthenticatedUserRealm) realm).getState());
  }

  /**
   * Test {@link CombinedRealm#destroyInternal()}.
   * <ul>
   *   <li>Then first element {@link CombinedRealm}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_thenFirstElementCombinedRealm() throws LifecycleException {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new CombinedRealm());

    // Act
    combinedRealm.destroyInternal();

    // Assert
    Realm[] nestedRealms = combinedRealm.getNestedRealms();
    Realm realm = nestedRealms[0];
    assertTrue(realm instanceof CombinedRealm);
    assertEquals("DESTROYED", ((CombinedRealm) realm).getStateName());
    assertEquals(1, nestedRealms.length);
    assertEquals(LifecycleState.DESTROYED, ((CombinedRealm) realm).getState());
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link AuthenticatedUserRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenCombinedRealmAddRealmAuthenticatedUserRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(combinedRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link AuthenticatedUserRealm} (default constructor).</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenCombinedRealmAddRealmAuthenticatedUserRealm_whenNull() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    // Act and Assert
    assertFalse(combinedRealm.hasRole(null, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link AuthenticatedUserRealm} (default constructor).</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenCombinedRealmAddRealmAuthenticatedUserRealm_whenNull2() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    // Act and Assert
    assertFalse(combinedRealm.hasRole(new StandardWrapper(), null, "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link CombinedRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenCombinedRealmAddRealmCombinedRealm() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new CombinedRealm());
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(combinedRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor).</li>
   *   <li>When {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenCombinedRealm_whenUserPrincipalWithNameIsPrincipal() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(combinedRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor).</li>
   *   <li>When {@link StandardWrapper} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_givenStandardContext_whenStandardWrapperParentIsStandardContext() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    StandardWrapper wrapper = new StandardWrapper();
    wrapper.setParent(new StandardContext());

    // Act and Assert
    assertFalse(combinedRealm.hasRole(wrapper, new UserPrincipal("principal"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@code *}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenAsterisk_thenReturnTrue() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertTrue(combinedRealm.hasRole(wrapper, new GenericPrincipal("Name"), "*"));
  }

  /**
   * Test {@link CombinedRealm#hasRole(Wrapper, Principal, String)}.
   * <ul>
   *   <li>When {@link GenericPrincipal#GenericPrincipal(String)} with {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#hasRole(Wrapper, Principal, String)}
   */
  @Test
  public void testHasRole_whenGenericPrincipalWithName() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertFalse(combinedRealm.hasRole(wrapper, new GenericPrincipal("Name"), "Role"));
  }

  /**
   * Test {@link CombinedRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link CombinedRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new CombinedRealm()).getPassword("janedoe"));
  }

  /**
   * Test {@link CombinedRealm#isAvailable()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link AuthenticatedUserRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#isAvailable()}
   */
  @Test
  public void testIsAvailable_givenCombinedRealmAddRealmAuthenticatedUserRealm_thenReturnTrue() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new AuthenticatedUserRealm());

    // Act and Assert
    assertTrue(combinedRealm.isAvailable());
  }

  /**
   * Test {@link CombinedRealm#isAvailable()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link CombinedRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#isAvailable()}
   */
  @Test
  public void testIsAvailable_givenCombinedRealmAddRealmCombinedRealm_thenReturnTrue() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new CombinedRealm());

    // Act and Assert
    assertTrue(combinedRealm.isAvailable());
  }

  /**
   * Test {@link CombinedRealm#isAvailable()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor) addRealm {@link DataSourceRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#isAvailable()}
   */
  @Test
  public void testIsAvailable_givenCombinedRealmAddRealmDataSourceRealm_thenReturnTrue() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    combinedRealm.addRealm(new DataSourceRealm());

    // Act and Assert
    assertTrue(combinedRealm.isAvailable());
  }

  /**
   * Test {@link CombinedRealm#isAvailable()}.
   * <ul>
   *   <li>Given {@link CombinedRealm} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CombinedRealm#isAvailable()}
   */
  @Test
  public void testIsAvailable_givenCombinedRealm_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new CombinedRealm()).isAvailable());
  }

  /**
   * Test {@link CombinedRealm#setCredentialHandler(CredentialHandler)}.
   * <p>
   * Method under test: {@link CombinedRealm#setCredentialHandler(CredentialHandler)}
   */
  @Test
  public void testSetCredentialHandler() {
    // Arrange
    CombinedRealm combinedRealm = new CombinedRealm();
    MessageDigestCredentialHandler credentialHandler = new MessageDigestCredentialHandler();

    // Act
    combinedRealm.setCredentialHandler(credentialHandler);

    // Assert
    assertSame(credentialHandler, combinedRealm.getCredentialHandler());
  }

  /**
   * Test new {@link CombinedRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CombinedRealm}
   */
  @Test
  public void testNewCombinedRealm() {
    // Arrange and Act
    CombinedRealm actualCombinedRealm = new CombinedRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualCombinedRealm.getRealmSuffix());
    assertEquals("/realm0", actualCombinedRealm.getRealmPath());
    assertEquals("NEW", actualCombinedRealm.getStateName());
    assertEquals("strict", actualCombinedRealm.getAllRolesMode());
    assertNull(actualCombinedRealm.getUserAttributes());
    assertNull(actualCombinedRealm.getX509UsernameRetrieverClassName());
    assertNull(actualCombinedRealm.userAttributesList);
    assertNull(actualCombinedRealm.getObjectName());
    assertNull(actualCombinedRealm.getContainer());
    assertNull(actualCombinedRealm.getCredentialHandler());
    assertNull(actualCombinedRealm.getServer());
    assertNull(actualCombinedRealm.x509UsernameRetriever);
    assertNull(actualCombinedRealm.containerLog);
    assertEquals(0, actualCombinedRealm.getNestedRealms().length);
    assertEquals(0, actualCombinedRealm.getRealms().length);
    assertEquals(0, actualCombinedRealm.findLifecycleListeners().length);
    assertEquals(302, actualCombinedRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualCombinedRealm.getState());
    assertTrue(actualCombinedRealm.realms.isEmpty());
    assertTrue(actualCombinedRealm.isAvailable());
    assertTrue(actualCombinedRealm.getValidate());
    assertTrue(actualCombinedRealm.isStripRealmForGss());
    assertTrue(actualCombinedRealm.getThrowOnFailure());
  }
}
