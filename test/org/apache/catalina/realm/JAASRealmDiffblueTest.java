package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.Container;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;
import org.junit.Test;

public class JAASRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JAASRealm#setAppName(String)}
   *   <li>{@link JAASRealm#setConfigFile(String)}
   *   <li>{@link JAASRealm#setRoleClassNames(String)}
   *   <li>{@link JAASRealm#setUseContextClassLoader(boolean)}
   *   <li>{@link JAASRealm#setUserClassNames(String)}
   *   <li>{@link JAASRealm#getAppName()}
   *   <li>{@link JAASRealm#getConfigFile()}
   *   <li>{@link JAASRealm#getRoleClassNames()}
   *   <li>{@link JAASRealm#getUserClassNames()}
   *   <li>{@link JAASRealm#isAvailable()}
   *   <li>{@link JAASRealm#isUseContextClassLoader()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();

    // Act
    jaasRealm.setAppName("Name");
    jaasRealm.setConfigFile("Config File");
    jaasRealm.setRoleClassNames("Role Class Names");
    jaasRealm.setUseContextClassLoader(true);
    jaasRealm.setUserClassNames("User Class Names");
    String actualAppName = jaasRealm.getAppName();
    String actualConfigFile = jaasRealm.getConfigFile();
    String actualRoleClassNames = jaasRealm.getRoleClassNames();
    String actualUserClassNames = jaasRealm.getUserClassNames();
    boolean actualIsAvailableResult = jaasRealm.isAvailable();

    // Assert
    assertEquals("Config File", actualConfigFile);
    assertEquals("Name", actualAppName);
    assertEquals("Role Class Names", actualRoleClassNames);
    assertEquals("User Class Names", actualUserClassNames);
    assertTrue(actualIsAvailableResult);
    assertTrue(jaasRealm.isUseContextClassLoader());
  }

  /**
   * Test {@link JAASRealm#setContainer(Container)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) AppName is {@code foo}.</li>
   *   <li>Then {@link JAASRealm} (default constructor) AppName is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_givenJAASRealmAppNameIsFoo_thenJAASRealmAppNameIsFoo() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setAppName("foo");

    StandardContext container = new StandardContext();
    container.setName("/");

    // Act
    jaasRealm.setContainer(container);

    // Assert
    assertEquals("Catalina", jaasRealm.getDomainInternal());
    assertEquals("Catalina", jaasRealm.getDomain());
    assertEquals("foo", jaasRealm.getAppName());
    assertEquals("type=Realm,realmPath=/realm0,context=/,container0=null", jaasRealm.getObjectNameKeyProperties());
    assertSame(container, jaasRealm.getContainer());
  }

  /**
   * Test {@link JAASRealm#setContainer(Container)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) AppName is {@code null}.</li>
   *   <li>Then {@link JAASRealm} (default constructor) AppName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_givenJAASRealmAppNameIsNull_thenJAASRealmAppNameIsEmptyString() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setAppName(null);

    StandardContext container = new StandardContext();
    container.setName("/");

    // Act
    jaasRealm.setContainer(container);

    // Assert
    assertEquals("", jaasRealm.getAppName());
    assertEquals("Catalina", jaasRealm.getDomainInternal());
    assertEquals("Catalina", jaasRealm.getDomain());
    assertEquals("type=Realm,realmPath=/realm0,context=/,container0=null", jaasRealm.getObjectNameKeyProperties());
    assertSame(container, jaasRealm.getContainer());
  }

  /**
   * Test {@link JAASRealm#setContainer(Container)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor).</li>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then {@link JAASRealm} (default constructor) AppName is {@code other}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#setContainer(Container)}
   */
  @Test
  public void testSetContainer_givenJAASRealm_whenStandardContext_thenJAASRealmAppNameIsOther() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    StandardContext container = new StandardContext();

    // Act
    jaasRealm.setContainer(container);

    // Assert
    assertEquals("Catalina", jaasRealm.getDomainInternal());
    assertEquals("Catalina", jaasRealm.getDomain());
    assertEquals("other", jaasRealm.getAppName());
    assertSame(container, jaasRealm.getContainer());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) UseContextClassLoader is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_givenJAASRealmUseContextClassLoaderIsFalse() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setUseContextClassLoader(false);
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames("Class Names String", classNamesList);

    // Assert that nothing has changed
    assertTrue(classNamesList.isEmpty());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) UseContextClassLoader is {@code false}.</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_givenJAASRealmUseContextClassLoaderIsFalse_whenNull() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setUseContextClassLoader(false);
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames(null, classNamesList);

    // Assert that nothing has changed
    assertTrue(classNamesList.isEmpty());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor).</li>
   *   <li>When {@code Class Names String}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_givenJAASRealm_whenClassNamesString_thenArrayListEmpty() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames("Class Names String", classNamesList);

    // Assert that nothing has changed
    assertTrue(classNamesList.isEmpty());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor).</li>
   *   <li>When empty string.</li>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_givenJAASRealm_whenEmptyString_thenArrayListEmpty() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames("", classNamesList);

    // Assert that nothing has changed
    assertTrue(classNamesList.isEmpty());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor).</li>
   *   <li>When {@code javax.security.auth.login.Configuration}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_givenJAASRealm_whenJavaxSecurityAuthLoginConfiguration() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames("javax.security.auth.login.Configuration", classNamesList);

    // Assert that nothing has changed
    assertTrue(classNamesList.isEmpty());
  }

  /**
   * Test {@link JAASRealm#parseClassNames(String, List)}.
   * <ul>
   *   <li>When {@code java.security.Principal}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#parseClassNames(String, List)}
   */
  @Test
  public void testParseClassNames_whenJavaSecurityPrincipal_thenArrayListSizeIsOne() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    ArrayList<String> classNamesList = new ArrayList<>();

    // Act
    jaasRealm.parseClassNames("java.security.Principal", classNamesList);

    // Assert
    assertEquals(1, classNamesList.size());
    assertEquals("java.security.Principal", classNamesList.get(0));
  }

  /**
   * Test {@link JAASRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link JAASRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange, Act and Assert
    assertNull((new JAASRealm()).getPassword("janedoe"));
  }

  /**
   * Test {@link JAASRealm#makeLegalForJAAS(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code other}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#makeLegalForJAAS(String)}
   */
  @Test
  public void testMakeLegalForJAAS_whenNull_thenReturnOther() {
    // Arrange, Act and Assert
    assertEquals("other", (new JAASRealm()).makeLegalForJAAS(null));
  }

  /**
   * Test {@link JAASRealm#makeLegalForJAAS(String)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#makeLegalForJAAS(String)}
   */
  @Test
  public void testMakeLegalForJAAS_whenSlash_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new JAASRealm()).makeLegalForJAAS("/"));
  }

  /**
   * Test {@link JAASRealm#makeLegalForJAAS(String)}.
   * <ul>
   *   <li>When {@code Src}.</li>
   *   <li>Then return {@code Src}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#makeLegalForJAAS(String)}
   */
  @Test
  public void testMakeLegalForJAAS_whenSrc_thenReturnSrc() {
    // Arrange, Act and Assert
    assertEquals("Src", (new JAASRealm()).makeLegalForJAAS("Src"));
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is {@code com.sun.security.auth.login.ConfigFile}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsComSunSecurityAuthLoginConfigFile() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("com.sun.security.auth.login.ConfigFile");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is {@code com.sun.security.auth.login.ConfigFilefile:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsComSunSecurityAuthLoginConfigFilefile() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("com.sun.security.auth.login.ConfigFilefile:/");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is {@code Config File}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsConfigFile_thenThrowRuntimeException() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("Config File");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is empty string.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsEmptyString_thenThrowRuntimeException() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is {@code file:/}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsFile_thenThrowRuntimeException() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("file:/");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) ConfigFile is {@code /}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealmConfigFileIsSlash_thenThrowRuntimeException() {
    // Arrange
    JAASRealm jaasRealm = new JAASRealm();
    jaasRealm.setConfigFile("/");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> jaasRealm.getConfig());
  }

  /**
   * Test {@link JAASRealm#getConfig()}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASRealm#getConfig()}
   */
  @Test
  public void testGetConfig_givenJAASRealm_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new JAASRealm()).getConfig());
  }

  /**
   * Test new {@link JAASRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JAASRealm}
   */
  @Test
  public void testNewJAASRealm() {
    // Arrange and Act
    JAASRealm actualJaasRealm = new JAASRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualJaasRealm.getRealmSuffix());
    assertEquals("/realm0", actualJaasRealm.getRealmPath());
    assertEquals("NEW", actualJaasRealm.getStateName());
    assertEquals("strict", actualJaasRealm.getAllRolesMode());
    assertNull(actualJaasRealm.getAppName());
    assertNull(actualJaasRealm.getConfigFile());
    assertNull(actualJaasRealm.getRoleClassNames());
    assertNull(actualJaasRealm.getUserClassNames());
    assertNull(actualJaasRealm.getUserAttributes());
    assertNull(actualJaasRealm.getX509UsernameRetrieverClassName());
    assertNull(actualJaasRealm.userAttributesList);
    assertNull(actualJaasRealm.getObjectName());
    assertNull(actualJaasRealm.getConfig());
    assertNull(actualJaasRealm.jaasConfiguration);
    assertNull(actualJaasRealm.getContainer());
    assertNull(actualJaasRealm.getCredentialHandler());
    assertNull(actualJaasRealm.getServer());
    assertNull(actualJaasRealm.x509UsernameRetriever);
    assertNull(actualJaasRealm.containerLog);
    assertEquals(0, actualJaasRealm.findLifecycleListeners().length);
    assertEquals(302, actualJaasRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualJaasRealm.getState());
    assertTrue(actualJaasRealm.roleClasses.isEmpty());
    assertTrue(actualJaasRealm.userClasses.isEmpty());
    assertTrue(actualJaasRealm.isAvailable());
    assertTrue(actualJaasRealm.isUseContextClassLoader());
    assertTrue(actualJaasRealm.getValidate());
    assertTrue(actualJaasRealm.isStripRealmForGss());
    assertTrue(actualJaasRealm.getThrowOnFailure());
  }
}
