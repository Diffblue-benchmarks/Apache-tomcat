package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.AuthException;
import jakarta.security.auth.message.AuthStatus;
import jakarta.security.auth.message.config.ServerAuthContext;
import jakarta.security.auth.message.module.ServerAuthModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import org.junit.Test;

public class SimpleServerAuthConfigDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SimpleServerAuthConfig#SimpleServerAuthConfig(String, String, CallbackHandler, Map)}
   *   <li>{@link SimpleServerAuthConfig#refresh()}
   *   <li>{@link SimpleServerAuthConfig#getAppContext()}
   *   <li>{@link SimpleServerAuthConfig#getMessageLayer()}
   *   <li>{@link SimpleServerAuthConfig#isProtected()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    CallbackHandlerImpl handler = new CallbackHandlerImpl();

    // Act
    SimpleServerAuthConfig actualSimpleServerAuthConfig = new SimpleServerAuthConfig("Layer", "App Context", handler,
        new HashMap<>());
    actualSimpleServerAuthConfig.refresh();
    String actualAppContext = actualSimpleServerAuthConfig.getAppContext();
    String actualMessageLayer = actualSimpleServerAuthConfig.getMessageLayer();

    // Assert
    assertEquals("App Context", actualAppContext);
    assertEquals("Layer", actualMessageLayer);
    assertFalse(actualSimpleServerAuthConfig.isProtected());
  }

  /**
   * Test {@link SimpleServerAuthConfig#createServerAuthContext(List)}.
   * <p>
   * Method under test: {@link SimpleServerAuthConfig#createServerAuthContext(List)}
   */
  @Test
  public void testCreateServerAuthContext() throws AuthException {
    // Arrange
    CallbackHandlerImpl handler = new CallbackHandlerImpl();
    SimpleServerAuthConfig simpleServerAuthConfig = new SimpleServerAuthConfig("Layer", "App Context", handler,
        new HashMap<>());

    ArrayList<ServerAuthModule> modules = new ArrayList<>();
    modules.add(new TesterServerAuthModuleA());

    // Act
    ServerAuthContext actualCreateServerAuthContextResult = simpleServerAuthConfig.createServerAuthContext(modules);
    MessageInfoImpl messageInfoImpl = new MessageInfoImpl();
    Subject subject = new Subject();

    // Assert
    assertNull(actualCreateServerAuthContextResult.validateRequest(messageInfoImpl, subject, new Subject()));
    Map<String, Object> map = messageInfoImpl.getMap();
    assertEquals(1, map.size());
    assertEquals(0, ((Integer) map.get("moduleIndex")).intValue());
  }

  /**
   * Test {@link SimpleServerAuthConfig#createServerAuthContext(List)}.
   * <p>
   * Method under test: {@link SimpleServerAuthConfig#createServerAuthContext(List)}
   */
  @Test
  public void testCreateServerAuthContext2() throws AuthException {
    // Arrange
    CallbackHandlerImpl handler = new CallbackHandlerImpl();
    SimpleServerAuthConfig simpleServerAuthConfig = new SimpleServerAuthConfig("Layer", "App Context", handler,
        new HashMap<>());

    ArrayList<ServerAuthModule> modules = new ArrayList<>();
    modules.add(new TesterServerAuthModuleA());
    modules.add(new TesterServerAuthModuleA());

    // Act
    ServerAuthContext actualCreateServerAuthContextResult = simpleServerAuthConfig.createServerAuthContext(modules);
    MessageInfoImpl messageInfoImpl = new MessageInfoImpl();
    Subject subject = new Subject();

    // Assert
    assertNull(actualCreateServerAuthContextResult.validateRequest(messageInfoImpl, subject, new Subject()));
    Map<String, Object> map = messageInfoImpl.getMap();
    assertEquals(1, map.size());
    assertEquals(0, ((Integer) map.get("moduleIndex")).intValue());
  }

  /**
   * Test {@link SimpleServerAuthConfig#createServerAuthContext(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link SimpleServerAuthContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleServerAuthConfig#createServerAuthContext(List)}
   */
  @Test
  public void testCreateServerAuthContext_whenArrayList_thenReturnSimpleServerAuthContext() throws AuthException {
    // Arrange
    CallbackHandlerImpl handler = new CallbackHandlerImpl();
    SimpleServerAuthConfig simpleServerAuthConfig = new SimpleServerAuthConfig("Layer", "App Context", handler,
        new HashMap<>());

    // Act
    ServerAuthContext actualCreateServerAuthContextResult = simpleServerAuthConfig
        .createServerAuthContext(new ArrayList<>());
    MessageInfoImpl messageInfoImpl = new MessageInfoImpl();
    Subject subject = new Subject();
    AuthStatus actualValidateRequestResult = actualCreateServerAuthContextResult.validateRequest(messageInfoImpl,
        subject, new Subject());

    // Assert
    assertTrue(actualCreateServerAuthContextResult instanceof SimpleServerAuthContext);
    assertEquals("SEND_FAILURE", actualValidateRequestResult.toString());
    assertTrue(messageInfoImpl.getMap().isEmpty());
    assertSame(actualValidateRequestResult.SEND_FAILURE, actualValidateRequestResult);
  }
}
