package org.apache.catalina.realm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.security.Principal;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RulesBase;
import org.junit.Test;

public class MemoryRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MemoryRealm#setPathname(String)}
   *   <li>{@link MemoryRealm#getPathname()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MemoryRealm memoryRealm = new MemoryRealm();

    // Act
    memoryRealm.setPathname("Pathname");

    // Assert
    assertEquals("Pathname", memoryRealm.getPathname());
  }

  /**
   * Test {@link MemoryRealm#getDigester()}.
   * <p>
   * Method under test: {@link MemoryRealm#getDigester()}
   */
  @Test
  public void testGetDigester() {
    // Arrange and Act
    Digester actualDigester = (new MemoryRealm()).getDigester();

    // Assert
    assertTrue(actualDigester.getRules() instanceof RulesBase);
    assertEquals("", actualDigester.getCurrentElementName());
    assertEquals("", actualDigester.getMatch());
    assertNull(actualDigester.getRoot());
    assertNull(actualDigester.getPublicId());
    assertNull(actualDigester.getGeneratedCode());
    assertNull(actualDigester.getFakeAttributes());
    assertNull(actualDigester.getEntityResolver());
    assertNull(actualDigester.getErrorHandler());
    assertNull(actualDigester.getDocumentLocator());
    assertEquals(0, actualDigester.getCount());
    assertFalse(actualDigester.getNamespaceAware());
    assertFalse(actualDigester.getRulesValidation());
    assertFalse(actualDigester.getUseContextClassLoader());
    assertFalse(actualDigester.getValidating());
  }

  /**
   * Test {@link MemoryRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link MemoryRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange, Act and Assert
    assertNull((new MemoryRealm()).getPassword("janedoe"));
  }

  /**
   * Test {@link MemoryRealm#getPrincipal(String)} with {@code username}.
   * <ul>
   *   <li>Then return {@link GenericPrincipal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRealm#getPrincipal(String)}
   */
  @Test
  public void testGetPrincipalWithUsername_thenReturnGenericPrincipal() {
    // Arrange
    MemoryRealm memoryRealm = new MemoryRealm();
    memoryRealm.addUser("janedoe", "iloveyou", "Roles");

    // Act
    Principal actualPrincipal = memoryRealm.getPrincipal("janedoe");
    String actualName = actualPrincipal.getName();

    // Assert
    assertTrue(actualPrincipal instanceof GenericPrincipal);
    assertEquals("janedoe", actualPrincipal.getName());
    assertEquals("janedoe", actualName);
    assertNull(((GenericPrincipal) actualPrincipal).userPrincipal);
    assertNull(((GenericPrincipal) actualPrincipal).attributes);
    assertNull(((GenericPrincipal) actualPrincipal).loginContext);
    assertNull(((GenericPrincipal) actualPrincipal).getGssCredential());
    assertSame(actualPrincipal, ((GenericPrincipal) actualPrincipal).getUserPrincipal());
    assertArrayEquals(new String[]{"Roles"}, ((GenericPrincipal) actualPrincipal).getRoles());
    assertArrayEquals(new String[]{"Roles"}, ((GenericPrincipal) actualPrincipal).roles);
  }

  /**
   * Test {@link MemoryRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link MemoryRealm} (default constructor) Pathname is {@code file:/}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenMemoryRealmPathnameIsFile_thenThrowLifecycleException() throws LifecycleException {
    // Arrange
    MemoryRealm memoryRealm = new MemoryRealm();
    memoryRealm.setPathname("file:/");

    // Act and Assert
    assertThrows(LifecycleException.class, () -> memoryRealm.startInternal());
  }

  /**
   * Test {@link MemoryRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link MemoryRealm} (default constructor) Pathname is {@code Digester}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenMemoryRealmPathnameIsOrgApacheTomcatUtilDigesterDigester()
      throws LifecycleException {
    // Arrange
    MemoryRealm memoryRealm = new MemoryRealm();
    memoryRealm.setPathname("org.apache.tomcat.util.digester.Digester");

    // Act and Assert
    assertThrows(LifecycleException.class, () -> memoryRealm.startInternal());
  }

  /**
   * Test new {@link MemoryRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MemoryRealm}
   */
  @Test
  public void testNewMemoryRealm() {
    // Arrange and Act
    MemoryRealm actualMemoryRealm = new MemoryRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualMemoryRealm.getRealmSuffix());
    assertEquals("/realm0", actualMemoryRealm.getRealmPath());
    assertEquals("NEW", actualMemoryRealm.getStateName());
    assertEquals("conf/tomcat-users.xml", actualMemoryRealm.getPathname());
    assertEquals("strict", actualMemoryRealm.getAllRolesMode());
    assertNull(actualMemoryRealm.getUserAttributes());
    assertNull(actualMemoryRealm.getX509UsernameRetrieverClassName());
    assertNull(actualMemoryRealm.userAttributesList);
    assertNull(actualMemoryRealm.getObjectName());
    assertNull(actualMemoryRealm.getContainer());
    assertNull(actualMemoryRealm.getCredentialHandler());
    assertNull(actualMemoryRealm.getServer());
    assertNull(actualMemoryRealm.x509UsernameRetriever);
    assertNull(actualMemoryRealm.containerLog);
    assertEquals(0, actualMemoryRealm.findLifecycleListeners().length);
    assertEquals(302, actualMemoryRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualMemoryRealm.getState());
    assertTrue(actualMemoryRealm.isAvailable());
    assertTrue(actualMemoryRealm.getValidate());
    assertTrue(actualMemoryRealm.isStripRealmForGss());
    assertTrue(actualMemoryRealm.getThrowOnFailure());
  }
}
