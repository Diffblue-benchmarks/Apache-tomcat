package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.AuthException;
import jakarta.security.auth.message.config.AuthConfigFactory;
import jakarta.security.auth.message.config.ServerAuthConfig;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import org.junit.Test;

public class SimpleAuthConfigProviderDiffblueTest {
  /**
   * Test {@link SimpleAuthConfigProvider#SimpleAuthConfigProvider(Map, AuthConfigFactory)}.
   * <p>
   * Method under test: {@link SimpleAuthConfigProvider#SimpleAuthConfigProvider(Map, AuthConfigFactory)}
   */
  @Test
  public void testNewSimpleAuthConfigProvider() throws AuthException {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    AuthConfigFactoryImpl factory = new AuthConfigFactoryImpl();

    // Act and Assert
    assertNull((new SimpleAuthConfigProvider(properties, factory)).getClientAuthConfig("Layer", "App Context", null));
    assertArrayEquals(new String[]{":"}, factory.getRegistrationIDs(null));
  }

  /**
   * Test {@link SimpleAuthConfigProvider#SimpleAuthConfigProvider(Map, AuthConfigFactory)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleAuthConfigProvider#SimpleAuthConfigProvider(Map, AuthConfigFactory)}
   */
  @Test
  public void testNewSimpleAuthConfigProvider_whenNull() throws AuthException {
    // Arrange, Act and Assert
    assertNull((new SimpleAuthConfigProvider(new HashMap<>(), null)).getClientAuthConfig("Layer", "App Context", null));
  }

  /**
   * Test {@link SimpleAuthConfigProvider#getClientAuthConfig(String, String, CallbackHandler)}.
   * <p>
   * Method under test: {@link SimpleAuthConfigProvider#getClientAuthConfig(String, String, CallbackHandler)}
   */
  @Test
  public void testGetClientAuthConfig() throws AuthException {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    SimpleAuthConfigProvider simpleAuthConfigProvider = new SimpleAuthConfigProvider(properties,
        new AuthConfigFactoryImpl());

    // Act and Assert
    assertNull(simpleAuthConfigProvider.getClientAuthConfig("Layer", "App Context", new CallbackHandlerImpl()));
  }

  /**
   * Test {@link SimpleAuthConfigProvider#getServerAuthConfig(String, String, CallbackHandler)}.
   * <p>
   * Method under test: {@link SimpleAuthConfigProvider#getServerAuthConfig(String, String, CallbackHandler)}
   */
  @Test
  public void testGetServerAuthConfig() throws AuthException {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    SimpleAuthConfigProvider simpleAuthConfigProvider = new SimpleAuthConfigProvider(properties,
        new AuthConfigFactoryImpl());

    // Act
    ServerAuthConfig actualServerAuthConfig = simpleAuthConfigProvider.getServerAuthConfig("Layer", "App Context",
        new CallbackHandlerImpl());

    // Assert
    assertTrue(actualServerAuthConfig instanceof SimpleServerAuthConfig);
    assertEquals("App Context", actualServerAuthConfig.getAppContext());
    assertEquals("Layer", actualServerAuthConfig.getMessageLayer());
    assertFalse(actualServerAuthConfig.isProtected());
  }

  /**
   * Test {@link SimpleAuthConfigProvider#createServerAuthConfig(String, String, CallbackHandler, Map)}.
   * <p>
   * Method under test: {@link SimpleAuthConfigProvider#createServerAuthConfig(String, String, CallbackHandler, Map)}
   */
  @Test
  public void testCreateServerAuthConfig() {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    SimpleAuthConfigProvider simpleAuthConfigProvider = new SimpleAuthConfigProvider(properties,
        new AuthConfigFactoryImpl());
    CallbackHandlerImpl handler = new CallbackHandlerImpl();

    // Act
    ServerAuthConfig actualCreateServerAuthConfigResult = simpleAuthConfigProvider.createServerAuthConfig("Layer",
        "App Context", handler, new HashMap<>());

    // Assert
    assertTrue(actualCreateServerAuthConfigResult instanceof SimpleServerAuthConfig);
    assertEquals("App Context", actualCreateServerAuthConfigResult.getAppContext());
    assertEquals("Layer", actualCreateServerAuthConfigResult.getMessageLayer());
    assertFalse(actualCreateServerAuthConfigResult.isProtected());
  }
}
