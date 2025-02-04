package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.authenticator.TesterCallbackHandlerImpl;
import org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl;
import org.junit.Test;

public class JAASMemoryLoginModuleDiffblueTest {
  /**
   * Test new {@link JAASMemoryLoginModule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JAASMemoryLoginModule}
   */
  @Test
  public void testNewJAASMemoryLoginModule() {
    // Arrange and Act
    JAASMemoryLoginModule actualJaasMemoryLoginModule = new JAASMemoryLoginModule();

    // Assert
    assertEquals(",realmPath=/realm0", actualJaasMemoryLoginModule.getRealmSuffix());
    assertEquals("/realm0", actualJaasMemoryLoginModule.getRealmPath());
    assertEquals("NEW", actualJaasMemoryLoginModule.getStateName());
    assertEquals("conf/tomcat-users.xml", actualJaasMemoryLoginModule.getPathname());
    assertEquals("strict", actualJaasMemoryLoginModule.getAllRolesMode());
    assertNull(actualJaasMemoryLoginModule.getUserAttributes());
    assertNull(actualJaasMemoryLoginModule.getX509UsernameRetrieverClassName());
    assertNull(actualJaasMemoryLoginModule.principal);
    assertNull(actualJaasMemoryLoginModule.userAttributesList);
    assertNull(actualJaasMemoryLoginModule.options);
    assertNull(actualJaasMemoryLoginModule.sharedState);
    assertNull(actualJaasMemoryLoginModule.getObjectName());
    assertNull(actualJaasMemoryLoginModule.subject);
    assertNull(actualJaasMemoryLoginModule.callbackHandler);
    assertNull(actualJaasMemoryLoginModule.getContainer());
    assertNull(actualJaasMemoryLoginModule.getCredentialHandler());
    assertNull(actualJaasMemoryLoginModule.getServer());
    assertNull(actualJaasMemoryLoginModule.x509UsernameRetriever);
    assertNull(actualJaasMemoryLoginModule.containerLog);
    assertEquals(0, actualJaasMemoryLoginModule.findLifecycleListeners().length);
    assertEquals(302, actualJaasMemoryLoginModule.getTransportGuaranteeRedirectStatus());
    assertEquals(LifecycleState.NEW, actualJaasMemoryLoginModule.getState());
    assertFalse(actualJaasMemoryLoginModule.committed);
    assertTrue(actualJaasMemoryLoginModule.isAvailable());
    assertTrue(actualJaasMemoryLoginModule.getValidate());
    assertTrue(actualJaasMemoryLoginModule.isStripRealmForGss());
    assertTrue(actualJaasMemoryLoginModule.getThrowOnFailure());
  }

  /**
   * Test {@link JAASMemoryLoginModule#abort()}.
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#abort()}
   */
  @Test
  public void testAbort() throws LoginException {
    // Arrange, Act and Assert
    assertFalse((new JAASMemoryLoginModule()).abort());
  }

  /**
   * Test {@link JAASMemoryLoginModule#commit()}.
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#commit()}
   */
  @Test
  public void testCommit() throws LoginException {
    // Arrange, Act and Assert
    assertFalse((new JAASMemoryLoginModule()).commit());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("callbackHandlerImpl.jaspicCallbackMissing", "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get("callbackHandlerImpl.jaspicCallbackMissing"));
    assertTrue(sharedState.isEmpty());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} empty string is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_givenEmptyString_thenJAASMemoryLoginModuleOptionsEmptyStringIs42() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("", "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get(""));
    assertTrue(sharedState.isEmpty());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Given five.</li>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_givenFive_thenJAASMemoryLoginModuleOptionsSizeIsTwo() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("credentialHandlerClassName", 5);
    options.put("pathname", "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(2, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get("pathname"));
    assertEquals(5, ((Integer) stringObjectMap.get("credentialHandlerClassName")).intValue());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} {@code null} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_givenNull_thenJAASMemoryLoginModuleOptionsNullIs42() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put(null, "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get(null));
    assertTrue(sharedState.isEmpty());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Given {@code pathname}.</li>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} {@code pathname} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_givenPathname_thenJAASMemoryLoginModuleOptionsPathnameIs42() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("pathname", "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get("pathname"));
    assertTrue(sharedState.isEmpty());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#callbackHandler} {@link CallbackHandlerImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_thenJAASMemoryLoginModuleCallbackHandlerCallbackHandlerImpl() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();
    HashMap<String, Object> options = new HashMap<>();

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    CallbackHandler callbackHandler2 = jaasMemoryLoginModule.callbackHandler;
    assertTrue(callbackHandler2 instanceof CallbackHandlerImpl);
    assertNull(((CallbackHandlerImpl) callbackHandler2).getContainer());
    assertTrue(jaasMemoryLoginModule.options.isEmpty());
    assertEquals(options, sharedState);
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#callbackHandler} {@link TesterCallbackHandlerImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_thenJAASMemoryLoginModuleCallbackHandlerTesterCallbackHandlerImpl() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    TesterCallbackHandlerImpl callbackHandler = new TesterCallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();
    HashMap<String, Object> options = new HashMap<>();

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    assertTrue(jaasMemoryLoginModule.callbackHandler instanceof TesterCallbackHandlerImpl);
    assertTrue(jaasMemoryLoginModule.options.isEmpty());
    assertEquals(options, sharedState);
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} {@code catalinaBase} intValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_thenJAASMemoryLoginModuleOptionsCatalinaBaseIntValueIsFortyTwo() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    TesterCallbackHandlerImpl callbackHandler = new TesterCallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("catalinaBase", 42);

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    assertTrue(jaasMemoryLoginModule.callbackHandler instanceof TesterCallbackHandlerImpl);
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals(42, ((Integer) stringObjectMap.get("catalinaBase")).intValue());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#options} {@code catalinaBase} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_thenJAASMemoryLoginModuleOptionsCatalinaBaseIs42() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("catalinaBase", "42");

    // Act
    jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options);

    // Assert
    Map<String, ?> stringObjectMap = jaasMemoryLoginModule.options;
    assertEquals(1, stringObjectMap.size());
    assertEquals("42", stringObjectMap.get("catalinaBase"));
    assertTrue(sharedState.isEmpty());
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_thenThrowIllegalArgumentException() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    CallbackHandlerImpl callbackHandler = new CallbackHandlerImpl();
    HashMap<String, Object> sharedState = new HashMap<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("credentialHandlerClassName", "42");
    options.put("pathname", "42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> jaasMemoryLoginModule.initialize(subject, callbackHandler, sharedState, options));
  }

  /**
   * Test {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link JAASMemoryLoginModule} (default constructor) {@link JAASMemoryLoginModule#callbackHandler} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#initialize(Subject, CallbackHandler, Map, Map)}
   */
  @Test
  public void testInitialize_whenNull_thenJAASMemoryLoginModuleCallbackHandlerIsNull() {
    // Arrange
    JAASMemoryLoginModule jaasMemoryLoginModule = new JAASMemoryLoginModule();
    Subject subject = new Subject();
    HashMap<String, Object> sharedState = new HashMap<>();
    HashMap<String, Object> options = new HashMap<>();

    // Act
    jaasMemoryLoginModule.initialize(subject, null, sharedState, options);

    // Assert
    assertNull(jaasMemoryLoginModule.callbackHandler);
    assertTrue(jaasMemoryLoginModule.options.isEmpty());
    assertEquals(options, sharedState);
  }

  /**
   * Test {@link JAASMemoryLoginModule#login()}.
   * <p>
   * Method under test: {@link JAASMemoryLoginModule#login()}
   */
  @Test
  public void testLogin() throws LoginException {
    // Arrange, Act and Assert
    assertThrows(LoginException.class, () -> (new JAASMemoryLoginModule()).login());
  }
}
