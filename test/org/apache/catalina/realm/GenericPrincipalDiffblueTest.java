package org.apache.catalina.realm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.auth.login.LoginContext;
import org.ietf.jgss.GSSCredential;
import org.junit.Test;

public class GenericPrincipalDiffblueTest {
  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String)}.
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String)}
   */
  @Test
  public void testNewGenericPrincipal() {
    // Arrange and Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name");

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.userPrincipal);
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    assertSame(actualGenericPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}
   */
  @Test
  public void testNewGenericPrincipal_given42_thenReturnRolesIsArrayOfStringWith42() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("42");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"),
        null);

    // Assert
    assertArrayEquals(new String[]{"42"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"42"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code 42} and {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List)}
   */
  @Test
  public void testNewGenericPrincipal_given42_thenReturnRolesIsArrayOfStringWith42AndFoo() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("42");
    roles.add("foo");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles);

    // Assert
    assertArrayEquals(new String[]{"42", "foo"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"42", "foo"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code 42} and {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}
   */
  @Test
  public void testNewGenericPrincipal_given42_thenReturnRolesIsArrayOfStringWith42AndFoo2() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("42");
    roles.add("foo");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"));

    // Assert
    assertArrayEquals(new String[]{"42", "foo"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"42", "foo"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}
   */
  @Test
  public void testNewGenericPrincipal_given42_thenReturnRolesIsArrayOfStringWith422() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("42");
    UserPrincipal userPrincipal = new UserPrincipal("userPrincipal");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, userPrincipal, null, null,
        new HashMap<>());

    // Assert
    assertArrayEquals(new String[]{"42"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"42"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List)}
   */
  @Test
  public void testNewGenericPrincipal_givenFoo_thenReturnRolesIsArrayOfStringWithFoo() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("foo");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles);

    // Assert
    assertArrayEquals(new String[]{"foo"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"foo"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return Roles is array of {@link String} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}
   */
  @Test
  public void testNewGenericPrincipal_givenFoo_thenReturnRolesIsArrayOfStringWithFoo2() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("foo");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"));

    // Assert
    assertArrayEquals(new String[]{"foo"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"foo"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}.
   * <ul>
   *   <li>Then return Roles is array of {@link String} with empty string and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}
   */
  @Test
  public void testNewGenericPrincipal_thenReturnRolesIsArrayOfStringWithEmptyStringAnd42() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("");
    roles.add("42");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"),
        null);

    // Assert
    assertArrayEquals(new String[]{"", "42"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"", "42"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}.
   * <ul>
   *   <li>Then return Roles is array of {@link String} with empty string and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}
   */
  @Test
  public void testNewGenericPrincipal_thenReturnRolesIsArrayOfStringWithEmptyStringAnd422() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("");
    roles.add("42");
    UserPrincipal userPrincipal = new UserPrincipal("userPrincipal");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, userPrincipal, null, null,
        new HashMap<>());

    // Assert
    assertArrayEquals(new String[]{"", "42"}, actualGenericPrincipal.getRoles());
    assertArrayEquals(new String[]{"", "42"}, actualGenericPrincipal.roles);
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List)}
   */
  @Test
  public void testNewGenericPrincipal_whenArrayList_thenReturnName() {
    // Arrange and Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", new ArrayList<>());

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.userPrincipal);
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    assertSame(actualGenericPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}
   */
  @Test
  public void testNewGenericPrincipal_whenArrayList_thenReturnName2() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"));

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    Principal expectedUserPrincipal = actualGenericPrincipal.userPrincipal;
    assertSame(expectedUserPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext)}
   */
  @Test
  public void testNewGenericPrincipal_whenArrayList_thenReturnName3() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, new UserPrincipal("userPrincipal"),
        null);

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    Principal expectedUserPrincipal = actualGenericPrincipal.userPrincipal;
    assertSame(expectedUserPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal, LoginContext, GSSCredential, Map)}
   */
  @Test
  public void testNewGenericPrincipal_whenArrayList_thenReturnName4() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    UserPrincipal userPrincipal = new UserPrincipal("userPrincipal");

    // Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", roles, userPrincipal, null, null,
        new HashMap<>());

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    assertTrue(actualGenericPrincipal.attributes.isEmpty());
    Principal expectedUserPrincipal = actualGenericPrincipal.userPrincipal;
    assertSame(expectedUserPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List)}
   */
  @Test
  public void testNewGenericPrincipal_whenNull_thenReturnName() {
    // Arrange and Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", null);

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.userPrincipal);
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    assertSame(actualGenericPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#GenericPrincipal(String, List, Principal)}
   */
  @Test
  public void testNewGenericPrincipal_whenNull_thenReturnName2() {
    // Arrange and Act
    GenericPrincipal actualGenericPrincipal = new GenericPrincipal("Name", null, new UserPrincipal("userPrincipal"));

    // Assert
    assertEquals("Name", actualGenericPrincipal.getName());
    assertNull(actualGenericPrincipal.attributes);
    assertNull(actualGenericPrincipal.loginContext);
    assertNull(actualGenericPrincipal.getGssCredential());
    assertEquals(0, actualGenericPrincipal.getRoles().length);
    assertEquals(0, actualGenericPrincipal.roles.length);
    Principal expectedUserPrincipal = actualGenericPrincipal.userPrincipal;
    assertSame(expectedUserPrincipal, actualGenericPrincipal.getUserPrincipal());
  }

  /**
   * Test {@link GenericPrincipal#getRoles()}.
   * <p>
   * Method under test: {@link GenericPrincipal#getRoles()}
   */
  @Test
  public void testGetRoles() {
    // Arrange, Act and Assert
    assertEquals(0, (new GenericPrincipal("Name")).getRoles().length);
  }

  /**
   * Test {@link GenericPrincipal#getUserPrincipal()}.
   * <p>
   * Method under test: {@link GenericPrincipal#getUserPrincipal()}
   */
  @Test
  public void testGetUserPrincipal() {
    // Arrange and Act
    Principal actualUserPrincipal = (new GenericPrincipal("Name")).getUserPrincipal();
    String actualName = actualUserPrincipal.getName();

    // Assert
    assertTrue(actualUserPrincipal instanceof GenericPrincipal);
    assertEquals("Name", actualUserPrincipal.getName());
    assertEquals("Name", actualName);
    assertNull(((GenericPrincipal) actualUserPrincipal).userPrincipal);
    assertNull(((GenericPrincipal) actualUserPrincipal).attributes);
    assertNull(((GenericPrincipal) actualUserPrincipal).loginContext);
    assertNull(((GenericPrincipal) actualUserPrincipal).getGssCredential());
    assertEquals(0, ((GenericPrincipal) actualUserPrincipal).getRoles().length);
    assertEquals(0, ((GenericPrincipal) actualUserPrincipal).roles.length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GenericPrincipal#setGssCredential(GSSCredential)}
   *   <li>{@link GenericPrincipal#getGssCredential()}
   *   <li>{@link GenericPrincipal#getName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    GenericPrincipal genericPrincipal = new GenericPrincipal("Name");

    // Act
    genericPrincipal.setGssCredential(null);
    GSSCredential actualGssCredential = genericPrincipal.getGssCredential();

    // Assert
    assertEquals("Name", genericPrincipal.getName());
    assertNull(actualGssCredential);
  }

  /**
   * Test {@link GenericPrincipal#hasRole(String)}.
   * <ul>
   *   <li>When {@code *}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#hasRole(String)}
   */
  @Test
  public void testHasRole_whenAsterisk_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new GenericPrincipal("Name")).hasRole("*"));
  }

  /**
   * Test {@link GenericPrincipal#hasRole(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#hasRole(String)}
   */
  @Test
  public void testHasRole_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new GenericPrincipal("Name")).hasRole(null));
  }

  /**
   * Test {@link GenericPrincipal#hasRole(String)}.
   * <ul>
   *   <li>When {@code Role}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#hasRole(String)}
   */
  @Test
  public void testHasRole_whenRole_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new GenericPrincipal("Name")).hasRole("Role"));
  }

  /**
   * Test {@link GenericPrincipal#toString()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code GenericPrincipal[GenericPrincipal[(foo,)]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#toString()}
   */
  @Test
  public void testToString_givenArrayListAddFoo_thenReturnGenericPrincipalGenericPrincipalFoo() {
    // Arrange
    ArrayList<String> roles = new ArrayList<>();
    roles.add("foo");

    // Act and Assert
    assertEquals("GenericPrincipal[GenericPrincipal[(foo,)]",
        (new GenericPrincipal("GenericPrincipal[", roles)).toString());
  }

  /**
   * Test {@link GenericPrincipal#toString()}.
   * <ul>
   *   <li>Given {@link GenericPrincipal#GenericPrincipal(String)} with {@code Name}.</li>
   *   <li>Then return {@code GenericPrincipal[Name()]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenericPrincipal#toString()}
   */
  @Test
  public void testToString_givenGenericPrincipalWithName_thenReturnGenericPrincipalName() {
    // Arrange, Act and Assert
    assertEquals("GenericPrincipal[Name()]", (new GenericPrincipal("Name")).toString());
  }

  /**
   * Test {@link GenericPrincipal#getAttribute(String)}.
   * <p>
   * Method under test: {@link GenericPrincipal#getAttribute(String)}
   */
  @Test
  public void testGetAttribute() {
    // Arrange, Act and Assert
    assertNull((new GenericPrincipal("Name")).getAttribute("Name"));
  }
}
