package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.realm.JNDIRealm.JNDIConnection;
import org.apache.catalina.realm.JNDIRealm.User;
import org.junit.Test;

public class JNDIRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JNDIRealm#setAdCompat(boolean)}
   *   <li>{@link JNDIRealm#setAlternateURL(String)}
   *   <li>{@link JNDIRealm#setAuthentication(String)}
   *   <li>{@link JNDIRealm#setCipherSuites(String)}
   *   <li>{@link JNDIRealm#setCommonRole(String)}
   *   <li>{@link JNDIRealm#setConnectionName(String)}
   *   <li>{@link JNDIRealm#setConnectionPassword(String)}
   *   <li>{@link JNDIRealm#setConnectionPoolSize(int)}
   *   <li>{@link JNDIRealm#setConnectionTimeout(String)}
   *   <li>{@link JNDIRealm#setConnectionURL(String)}
   *   <li>{@link JNDIRealm#setContextFactory(String)}
   *   <li>{@link JNDIRealm#setDerefAliases(String)}
   *   <li>{@link JNDIRealm#setForceDnHexEscape(boolean)}
   *   <li>{@link JNDIRealm#setProtocol(String)}
   *   <li>{@link JNDIRealm#setReadTimeout(String)}
   *   <li>{@link JNDIRealm#setReferrals(String)}
   *   <li>{@link JNDIRealm#setRoleName(String)}
   *   <li>{@link JNDIRealm#setRoleNested(boolean)}
   *   <li>{@link JNDIRealm#setRoleSearchAsUser(boolean)}
   *   <li>{@link JNDIRealm#setRoleSubtree(boolean)}
   *   <li>{@link JNDIRealm#setSizeLimit(long)}
   *   <li>{@link JNDIRealm#setSpnegoDelegationQop(String)}
   *   <li>{@link JNDIRealm#setSslProtocol(String)}
   *   <li>{@link JNDIRealm#setSslSocketFactoryClassName(String)}
   *   <li>{@link JNDIRealm#setTimeLimit(int)}
   *   <li>{@link JNDIRealm#setUseContextClassLoader(boolean)}
   *   <li>{@link JNDIRealm#setUseDelegatedCredential(boolean)}
   *   <li>{@link JNDIRealm#setUseStartTls(boolean)}
   *   <li>{@link JNDIRealm#setUserBase(String)}
   *   <li>{@link JNDIRealm#setUserPassword(String)}
   *   <li>{@link JNDIRealm#setUserRoleAttribute(String)}
   *   <li>{@link JNDIRealm#setUserRoleName(String)}
   *   <li>{@link JNDIRealm#setUserSearchAsUser(boolean)}
   *   <li>{@link JNDIRealm#setUserSubtree(boolean)}
   *   <li>{@link JNDIRealm#getAdCompat()}
   *   <li>{@link JNDIRealm#getAlternateURL()}
   *   <li>{@link JNDIRealm#getAuthentication()}
   *   <li>{@link JNDIRealm#getCommonRole()}
   *   <li>{@link JNDIRealm#getConnectionName()}
   *   <li>{@link JNDIRealm#getConnectionPassword()}
   *   <li>{@link JNDIRealm#getConnectionPoolSize()}
   *   <li>{@link JNDIRealm#getConnectionTimeout()}
   *   <li>{@link JNDIRealm#getConnectionURL()}
   *   <li>{@link JNDIRealm#getContextFactory()}
   *   <li>{@link JNDIRealm#getDerefAliases()}
   *   <li>{@link JNDIRealm#getForceDnHexEscape()}
   *   <li>{@link JNDIRealm#getProtocol()}
   *   <li>{@link JNDIRealm#getReadTimeout()}
   *   <li>{@link JNDIRealm#getReferrals()}
   *   <li>{@link JNDIRealm#getRoleBase()}
   *   <li>{@link JNDIRealm#getRoleName()}
   *   <li>{@link JNDIRealm#getRoleNested()}
   *   <li>{@link JNDIRealm#getRoleSearch()}
   *   <li>{@link JNDIRealm#getRoleSubtree()}
   *   <li>{@link JNDIRealm#getSizeLimit()}
   *   <li>{@link JNDIRealm#getSpnegoDelegationQop()}
   *   <li>{@link JNDIRealm#getTimeLimit()}
   *   <li>{@link JNDIRealm#getUseStartTls()}
   *   <li>{@link JNDIRealm#getUserBase()}
   *   <li>{@link JNDIRealm#getUserPassword()}
   *   <li>{@link JNDIRealm#getUserPattern()}
   *   <li>{@link JNDIRealm#getUserRoleAttribute()}
   *   <li>{@link JNDIRealm#getUserRoleName()}
   *   <li>{@link JNDIRealm#getUserSearch()}
   *   <li>{@link JNDIRealm#getUserSubtree()}
   *   <li>{@link JNDIRealm#isRoleSearchAsUser()}
   *   <li>{@link JNDIRealm#isUseContextClassLoader()}
   *   <li>{@link JNDIRealm#isUseDelegatedCredential()}
   *   <li>{@link JNDIRealm#isUserSearchAsUser()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    JNDIRealm jndiRealm = new JNDIRealm();

    // Act
    jndiRealm.setAdCompat(true);
    jndiRealm.setAlternateURL("https://example.org/example");
    jndiRealm.setAuthentication("Authentication");
    jndiRealm.setCipherSuites("Suites");
    jndiRealm.setCommonRole("Common Role");
    jndiRealm.setConnectionName("Connection Name");
    jndiRealm.setConnectionPassword("iloveyou");
    jndiRealm.setConnectionPoolSize(3);
    jndiRealm.setConnectionTimeout("Timeout");
    jndiRealm.setConnectionURL("https://example.org/example");
    jndiRealm.setContextFactory("Context Factory");
    jndiRealm.setDerefAliases("Deref Aliases");
    jndiRealm.setForceDnHexEscape(true);
    jndiRealm.setProtocol("Protocol");
    jndiRealm.setReadTimeout("Timeout");
    jndiRealm.setReferrals("Referrals");
    jndiRealm.setRoleName("Role Name");
    jndiRealm.setRoleNested(true);
    jndiRealm.setRoleSearchAsUser(true);
    jndiRealm.setRoleSubtree(true);
    jndiRealm.setSizeLimit(3L);
    jndiRealm.setSpnegoDelegationQop("Spnego Delegation Qop");
    jndiRealm.setSslProtocol("Protocol");
    jndiRealm.setSslSocketFactoryClassName("Factory Class Name");
    jndiRealm.setTimeLimit(1);
    jndiRealm.setUseContextClassLoader(true);
    jndiRealm.setUseDelegatedCredential(true);
    jndiRealm.setUseStartTls(true);
    jndiRealm.setUserBase("User Base");
    jndiRealm.setUserPassword("iloveyou");
    jndiRealm.setUserRoleAttribute("User Role Attribute");
    jndiRealm.setUserRoleName("User Role Name");
    jndiRealm.setUserSearchAsUser(true);
    jndiRealm.setUserSubtree(true);
    boolean actualAdCompat = jndiRealm.getAdCompat();
    String actualAlternateURL = jndiRealm.getAlternateURL();
    String actualAuthentication = jndiRealm.getAuthentication();
    String actualCommonRole = jndiRealm.getCommonRole();
    String actualConnectionName = jndiRealm.getConnectionName();
    String actualConnectionPassword = jndiRealm.getConnectionPassword();
    int actualConnectionPoolSize = jndiRealm.getConnectionPoolSize();
    String actualConnectionTimeout = jndiRealm.getConnectionTimeout();
    String actualConnectionURL = jndiRealm.getConnectionURL();
    String actualContextFactory = jndiRealm.getContextFactory();
    String actualDerefAliases = jndiRealm.getDerefAliases();
    boolean actualForceDnHexEscape = jndiRealm.getForceDnHexEscape();
    String actualProtocol = jndiRealm.getProtocol();
    String actualReadTimeout = jndiRealm.getReadTimeout();
    String actualReferrals = jndiRealm.getReferrals();
    String actualRoleBase = jndiRealm.getRoleBase();
    String actualRoleName = jndiRealm.getRoleName();
    boolean actualRoleNested = jndiRealm.getRoleNested();
    String actualRoleSearch = jndiRealm.getRoleSearch();
    boolean actualRoleSubtree = jndiRealm.getRoleSubtree();
    long actualSizeLimit = jndiRealm.getSizeLimit();
    String actualSpnegoDelegationQop = jndiRealm.getSpnegoDelegationQop();
    int actualTimeLimit = jndiRealm.getTimeLimit();
    boolean actualUseStartTls = jndiRealm.getUseStartTls();
    String actualUserBase = jndiRealm.getUserBase();
    String actualUserPassword = jndiRealm.getUserPassword();
    String actualUserPattern = jndiRealm.getUserPattern();
    String actualUserRoleAttribute = jndiRealm.getUserRoleAttribute();
    String actualUserRoleName = jndiRealm.getUserRoleName();
    String actualUserSearch = jndiRealm.getUserSearch();
    boolean actualUserSubtree = jndiRealm.getUserSubtree();
    boolean actualIsRoleSearchAsUserResult = jndiRealm.isRoleSearchAsUser();
    boolean actualIsUseContextClassLoaderResult = jndiRealm.isUseContextClassLoader();
    boolean actualIsUseDelegatedCredentialResult = jndiRealm.isUseDelegatedCredential();

    // Assert
    assertEquals("", actualRoleBase);
    assertEquals("Authentication", actualAuthentication);
    assertEquals("Common Role", actualCommonRole);
    assertEquals("Connection Name", actualConnectionName);
    assertEquals("Context Factory", actualContextFactory);
    assertEquals("Deref Aliases", actualDerefAliases);
    assertEquals("Protocol", actualProtocol);
    assertEquals("Referrals", actualReferrals);
    assertEquals("Role Name", actualRoleName);
    assertEquals("Spnego Delegation Qop", actualSpnegoDelegationQop);
    assertEquals("Timeout", actualConnectionTimeout);
    assertEquals("Timeout", actualReadTimeout);
    assertEquals("User Base", actualUserBase);
    assertEquals("User Role Attribute", actualUserRoleAttribute);
    assertEquals("User Role Name", actualUserRoleName);
    assertEquals("https://example.org/example", actualAlternateURL);
    assertEquals("https://example.org/example", actualConnectionURL);
    assertEquals("iloveyou", actualConnectionPassword);
    assertEquals("iloveyou", actualUserPassword);
    assertNull(actualRoleSearch);
    assertNull(actualUserPattern);
    assertNull(actualUserSearch);
    assertEquals(1, actualTimeLimit);
    assertEquals(3, actualConnectionPoolSize);
    assertEquals(3L, actualSizeLimit);
    assertTrue(actualAdCompat);
    assertTrue(actualForceDnHexEscape);
    assertTrue(actualRoleNested);
    assertTrue(actualRoleSubtree);
    assertTrue(actualUseStartTls);
    assertTrue(actualUserSubtree);
    assertTrue(actualIsRoleSearchAsUserResult);
    assertTrue(actualIsUseContextClassLoaderResult);
    assertTrue(actualIsUseDelegatedCredentialResult);
    assertTrue(jndiRealm.isUserSearchAsUser());
  }

  /**
   * Test JNDIConnection {@link JNDIConnection#JNDIConnection(String, String[], String, String)}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JNDIConnection#JNDIConnection(String, String[], String, String)}
   */
  @Test
  public void testJNDIConnectionNewJNDIConnection_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    JNDIConnection actualJndiConnection = new JNDIConnection("User Search", new String[]{"User Pattern Array"},
        "Role Base", "Role Search");

    // Assert
    MessageFormat messageFormat = actualJndiConnection.roleBaseFormat;
    assertEquals(0, messageFormat.getFormats().length);
    MessageFormat messageFormat2 = actualJndiConnection.roleFormat;
    assertEquals(0, messageFormat2.getFormats().length);
    MessageFormat messageFormat3 = actualJndiConnection.userSearchFormat;
    assertEquals(0, messageFormat3.getFormats().length);
    assertEquals(0, messageFormat.getFormatsByArgumentIndex().length);
    assertEquals(0, messageFormat2.getFormatsByArgumentIndex().length);
    assertEquals(0, messageFormat3.getFormatsByArgumentIndex().length);
    assertEquals(1, actualJndiConnection.userPatternFormatArray.length);
  }

  /**
   * Test JNDIConnection {@link JNDIConnection#JNDIConnection(String, String[], String, String)}.
   * <ul>
   *   <li>Then return {@link JNDIConnection#userPatternFormatArray} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JNDIConnection#JNDIConnection(String, String[], String, String)}
   */
  @Test
  public void testJNDIConnectionNewJNDIConnection_thenReturnUserPatternFormatArrayIsNull() {
    // Arrange and Act
    JNDIConnection actualJndiConnection = new JNDIConnection(null, null, null, null);

    // Assert
    assertNull(actualJndiConnection.userPatternFormatArray);
    assertNull(actualJndiConnection.roleBaseFormat);
    assertNull(actualJndiConnection.roleFormat);
    assertNull(actualJndiConnection.userSearchFormat);
  }

  /**
   * Test {@link JNDIRealm#setUserSearch(String)}.
   * <p>
   * Method under test: {@link JNDIRealm#setUserSearch(String)}
   */
  @Test
  public void testSetUserSearch() {
    // Arrange
    JNDIRealm jndiRealm = new JNDIRealm();

    // Act
    jndiRealm.setUserSearch("User Search");

    // Assert
    assertEquals("User Search", jndiRealm.getUserSearch());
    MessageFormat messageFormat = jndiRealm.singleConnection.userSearchFormat;
    assertEquals(0, messageFormat.getFormats().length);
    assertEquals(0, messageFormat.getFormatsByArgumentIndex().length);
  }

  /**
   * Test {@link JNDIRealm#setRoleBase(String)}.
   * <p>
   * Method under test: {@link JNDIRealm#setRoleBase(String)}
   */
  @Test
  public void testSetRoleBase() {
    // Arrange
    JNDIRealm jndiRealm = new JNDIRealm();

    // Act
    jndiRealm.setRoleBase("Role Base");

    // Assert
    assertEquals("Role Base", jndiRealm.getRoleBase());
  }

  /**
   * Test {@link JNDIRealm#setRoleSearch(String)}.
   * <p>
   * Method under test: {@link JNDIRealm#setRoleSearch(String)}
   */
  @Test
  public void testSetRoleSearch() {
    // Arrange
    JNDIRealm jndiRealm = new JNDIRealm();

    // Act
    jndiRealm.setRoleSearch("Role Search");

    // Assert
    assertEquals("Role Search", jndiRealm.getRoleSearch());
    MessageFormat messageFormat = jndiRealm.singleConnection.roleFormat;
    assertEquals(0, messageFormat.getFormats().length);
    assertEquals(0, messageFormat.getFormatsByArgumentIndex().length);
  }

  /**
   * Test {@link JNDIRealm#convertToHexEscape(String)}.
   * <p>
   * Method under test: {@link JNDIRealm#convertToHexEscape(String)}
   */
  @Test
  public void testConvertToHexEscape() {
    // Arrange, Act and Assert
    assertEquals("0123456789ABCDEF", JNDIRealm.convertToHexEscape("0123456789ABCDEF"));
  }

  /**
   * Test User getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link User#getDN()}
   *   <li>{@link User#getPassword()}
   *   <li>{@link User#getRoles()}
   *   <li>{@link User#getUserName()}
   *   <li>{@link User#getUserRoleId()}
   * </ul>
   */
  @Test
  public void testUserGettersAndSetters() {
    // Arrange
    User user = new User("janedoe", "Dn", "iloveyou", new ArrayList<>(), "42");

    // Act
    String actualDN = user.getDN();
    String actualPassword = user.getPassword();
    List<String> actualRoles = user.getRoles();
    String actualUserName = user.getUserName();

    // Assert
    assertEquals("42", user.getUserRoleId());
    assertEquals("Dn", actualDN);
    assertEquals("iloveyou", actualPassword);
    assertEquals("janedoe", actualUserName);
    assertTrue(actualRoles.isEmpty());
  }

  /**
   * Test User {@link User#User(String, String, String, List, String)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return Roles is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link User#User(String, String, String, List, String)}
   */
  @Test
  public void testUserNewUser_given42_whenArrayListAdd42_thenReturnRolesIsArrayList() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("42");
    roles.add("foo");

    // Act
    User actualUser = new User("janedoe", "Dn", "iloveyou", roles, "42");

    // Assert
    assertEquals("42", actualUser.getUserRoleId());
    assertEquals("Dn", actualUser.getDN());
    assertEquals("iloveyou", actualUser.getPassword());
    assertEquals("janedoe", actualUser.getUserName());
    assertEquals(roles, actualUser.getRoles());
  }

  /**
   * Test User {@link User#User(String, String, String, List, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return Roles is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link User#User(String, String, String, List, String)}
   */
  @Test
  public void testUserNewUser_givenFoo_whenArrayListAddFoo_thenReturnRolesIsArrayList() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("foo");

    // Act
    User actualUser = new User("janedoe", "Dn", "iloveyou", roles, "42");

    // Assert
    assertEquals("42", actualUser.getUserRoleId());
    assertEquals("Dn", actualUser.getDN());
    assertEquals("iloveyou", actualUser.getPassword());
    assertEquals("janedoe", actualUser.getUserName());
    assertEquals(roles, actualUser.getRoles());
  }

  /**
   * Test User {@link User#User(String, String, String, List, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Roles Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link User#User(String, String, String, List, String)}
   */
  @Test
  public void testUserNewUser_whenArrayList_thenReturnRolesEmpty() {
    // Arrange and Act
    User actualUser = new User("janedoe", "Dn", "iloveyou", new ArrayList<>(), "42");

    // Assert
    assertEquals("42", actualUser.getUserRoleId());
    assertEquals("Dn", actualUser.getDN());
    assertEquals("iloveyou", actualUser.getPassword());
    assertEquals("janedoe", actualUser.getUserName());
    assertTrue(actualUser.getRoles().isEmpty());
  }

  /**
   * Test User {@link User#User(String, String, String, List, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Roles Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link User#User(String, String, String, List, String)}
   */
  @Test
  public void testUserNewUser_whenNull_thenReturnRolesEmpty() {
    // Arrange and Act
    User actualUser = new User("janedoe", "Dn", "iloveyou", null, "42");

    // Assert
    assertEquals("42", actualUser.getUserRoleId());
    assertEquals("Dn", actualUser.getDN());
    assertEquals("iloveyou", actualUser.getPassword());
    assertEquals("janedoe", actualUser.getUserName());
    assertTrue(actualUser.getRoles().isEmpty());
  }
}
