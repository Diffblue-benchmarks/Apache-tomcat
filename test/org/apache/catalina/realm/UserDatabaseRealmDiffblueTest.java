package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Hashtable;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.naming.NamingContext;
import org.apache.naming.SelectorContext;
import org.junit.Test;

public class UserDatabaseRealmDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UserDatabaseRealm#setLocalJndiResource(boolean)}
   *   <li>{@link UserDatabaseRealm#setResourceName(String)}
   *   <li>{@link UserDatabaseRealm#setUseStaticPrincipal(boolean)}
   *   <li>{@link UserDatabaseRealm#getLocalJndiResource()}
   *   <li>{@link UserDatabaseRealm#getResourceName()}
   *   <li>{@link UserDatabaseRealm#getUseStaticPrincipal()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();

    // Act
    userDatabaseRealm.setLocalJndiResource(true);
    userDatabaseRealm.setResourceName("Resource Name");
    userDatabaseRealm.setUseStaticPrincipal(true);
    boolean actualLocalJndiResource = userDatabaseRealm.getLocalJndiResource();
    String actualResourceName = userDatabaseRealm.getResourceName();

    // Assert
    assertEquals("Resource Name", actualResourceName);
    assertTrue(actualLocalJndiResource);
    assertTrue(userDatabaseRealm.getUseStaticPrincipal());
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword() {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new SelectorContext(new Hashtable<>()));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword2() {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new NamingContext(new Hashtable<>(), "contextBindings.noContextBoundToCL"));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword3() {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new SelectorContext(new Hashtable<>(), true));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Service is {@link StandardService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenStandardEngineServiceIsStandardService() {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(new StandardService());

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Server is {@link StandardServer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenStandardServiceServerIsStandardServer() {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenUserDatabaseRealm() {
    // Arrange, Act and Assert
    assertNull((new UserDatabaseRealm()).getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenUserDatabaseRealmContainerIsStandardContext() {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardContext());

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenUserDatabaseRealmContainerIsStandardEngine() {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardEngine());

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenUserDatabaseRealmContainerIsStandardHost() {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardHost());

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#getPassword(String)}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) LocalJndiResource is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#getPassword(String)}
   */
  @Test
  public void testGetPassword_givenUserDatabaseRealmLocalJndiResourceIsTrue() {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setLocalJndiResource(true);

    // Act and Assert
    assertNull(userDatabaseRealm.getPassword("janedoe"));
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal() throws LifecycleException {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new SelectorContext(new Hashtable<>()));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal2() throws LifecycleException {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new NamingContext(new Hashtable<>(), "contextBindings.noContextBoundToCL"));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal3() throws LifecycleException {
    // Arrange
    StandardServer server = new StandardServer();
    server.setGlobalNamingContext(new SelectorContext(new Hashtable<>(), true));

    StandardService service = new StandardService();
    service.setServer(server);

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link StandardEngine} (default constructor) Service is {@link StandardService} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenStandardEngineServiceIsStandardService() throws LifecycleException {
    // Arrange
    StandardEngine container = new StandardEngine();
    container.setService(new StandardService());

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Server is {@link StandardServer} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenStandardServiceServerIsStandardServer() throws LifecycleException {
    // Arrange
    StandardService service = new StandardService();
    service.setServer(new StandardServer());

    StandardEngine container = new StandardEngine();
    container.setService(service);

    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(container);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenUserDatabaseRealmContainerIsStandardContext() throws LifecycleException {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardContext());

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenUserDatabaseRealmContainerIsStandardEngine() throws LifecycleException {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardEngine());

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor) Container is {@link StandardHost} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenUserDatabaseRealmContainerIsStandardHost() throws LifecycleException {
    // Arrange
    UserDatabaseRealm userDatabaseRealm = new UserDatabaseRealm();
    userDatabaseRealm.setContainer(new StandardHost());

    // Act and Assert
    assertThrows(LifecycleException.class, () -> userDatabaseRealm.startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#startInternal()}.
   * <ul>
   *   <li>Given {@link UserDatabaseRealm} (default constructor).</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDatabaseRealm#startInternal()}
   */
  @Test
  public void testStartInternal_givenUserDatabaseRealm_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new UserDatabaseRealm()).startInternal());
  }

  /**
   * Test {@link UserDatabaseRealm#isAvailable()}.
   * <p>
   * Method under test: {@link UserDatabaseRealm#isAvailable()}
   */
  @Test
  public void testIsAvailable() {
    // Arrange, Act and Assert
    assertFalse((new UserDatabaseRealm()).isAvailable());
  }

  /**
   * Test new {@link UserDatabaseRealm} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link UserDatabaseRealm}
   */
  @Test
  public void testNewUserDatabaseRealm() {
    // Arrange and Act
    UserDatabaseRealm actualUserDatabaseRealm = new UserDatabaseRealm();

    // Assert
    assertEquals(",realmPath=/realm0", actualUserDatabaseRealm.getRealmSuffix());
    assertEquals("/realm0", actualUserDatabaseRealm.getRealmPath());
    assertEquals("NEW", actualUserDatabaseRealm.getStateName());
    assertEquals("UserDatabase", actualUserDatabaseRealm.getResourceName());
    assertEquals("strict", actualUserDatabaseRealm.getAllRolesMode());
    assertNull(actualUserDatabaseRealm.getUserAttributes());
    assertNull(actualUserDatabaseRealm.getX509UsernameRetrieverClassName());
    assertNull(actualUserDatabaseRealm.userAttributesList);
    assertNull(actualUserDatabaseRealm.getObjectName());
    assertNull(actualUserDatabaseRealm.getContainer());
    assertNull(actualUserDatabaseRealm.getCredentialHandler());
    assertNull(actualUserDatabaseRealm.getServer());
    assertNull(actualUserDatabaseRealm.database);
    assertNull(actualUserDatabaseRealm.x509UsernameRetriever);
    assertNull(actualUserDatabaseRealm.containerLog);
    assertEquals(0, actualUserDatabaseRealm.findLifecycleListeners().length);
    assertEquals(302, actualUserDatabaseRealm.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualUserDatabaseRealm.getState());
    assertFalse(actualUserDatabaseRealm.getLocalJndiResource());
    assertFalse(actualUserDatabaseRealm.getUseStaticPrincipal());
    assertFalse(actualUserDatabaseRealm.isAvailable());
    assertTrue(actualUserDatabaseRealm.getValidate());
    assertTrue(actualUserDatabaseRealm.isStripRealmForGss());
    assertTrue(actualUserDatabaseRealm.getThrowOnFailure());
  }
}
