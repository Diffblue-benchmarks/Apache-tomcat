package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import jakarta.security.auth.message.config.AuthConfigProvider;
import jakarta.security.auth.message.config.RegistrationListener;
import jakarta.security.auth.message.module.ServerAuthModule;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.jasper.JasperException;
import org.apache.jasper.servlet.JspCServletContext;
import org.junit.Test;

public class AuthConfigFactoryImplDiffblueTest {
  /**
   * Test new {@link AuthConfigFactoryImpl} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link AuthConfigFactoryImpl}
   */
  @Test
  public void testNewAuthConfigFactoryImpl() {
    // Arrange and Act
    AuthConfigFactoryImpl actualAuthConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Assert
    assertNull(actualAuthConfigFactoryImpl.getRegistrationContext("Registration ID"));
    assertEquals(0, actualAuthConfigFactoryImpl.getRegistrationIDs(null).length);
    assertFalse(actualAuthConfigFactoryImpl.removeRegistration("Registration ID"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}
   */
  @Test
  public void testGetConfigProvider_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.getConfigProvider("", "App Context", new BasicAuthenticator()));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}.
   * <ul>
   *   <li>When {@code Layer}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}
   */
  @Test
  public void testGetConfigProvider_whenLayer_thenReturnNull() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertNull(authConfigFactoryImpl.getConfigProvider("Layer", "App Context", new BasicAuthenticator()));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}.
   * <ul>
   *   <li>When {@code Layer}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getConfigProvider(String, String, RegistrationListener)}
   */
  @Test
  public void testGetConfigProvider_whenLayer_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.getConfigProvider("Layer", "", new BasicAuthenticator()));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)} with {@code className}, {@code properties}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithClassNamePropertiesLayerAppContextDescription() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(SecurityException.class, () -> authConfigFactoryImpl.registerConfigProvider("Class Name",
        new HashMap<>(), "Layer", "App Context", "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)} with {@code className}, {@code properties}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithClassNamePropertiesLayerAppContextDescription2() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> authConfigFactoryImpl.registerConfigProvider(null,
        new HashMap<>(), "", "App Context", "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)} with {@code className}, {@code properties}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithClassNamePropertiesLayerAppContextDescription3() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> authConfigFactoryImpl.registerConfigProvider(null,
        new HashMap<>(), "Layer", "", "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)} with {@code className}, {@code properties}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(String, Map, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithClassNamePropertiesLayerAppContextDescription4() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(SecurityException.class, () -> authConfigFactoryImpl.registerConfigProvider("java.lang.String",
        new HashMap<>(), "Layer", "App Context", "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertEquals("Layer:App Context",
        authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), "Layer", "App Context",
            "The characteristics of someone or something"));
    assertArrayEquals(new String[]{"Layer:App Context"}, authConfigFactoryImpl.getRegistrationIDs(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription2() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), "", "App Context",
            "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription3() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertEquals(":App Context",
        authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), null, "App Context",
            "The characteristics of someone or something"));
    assertArrayEquals(new String[]{":App Context"}, authConfigFactoryImpl.getRegistrationIDs(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription4() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), "Layer", "",
            "The characteristics of someone or something"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <ul>
   *   <li>Then return {@code :}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription_thenReturnColon() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertEquals(":",
        authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), null, null,
            "The characteristics of someone or something"));
    assertArrayEquals(new String[]{":"}, authConfigFactoryImpl.getRegistrationIDs(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)} with {@code provider}, {@code layer}, {@code appContext}, {@code description}.
   * <ul>
   *   <li>Then return {@code Layer:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerConfigProvider(AuthConfigProvider, String, String, String)}
   */
  @Test
  public void testRegisterConfigProviderWithProviderLayerAppContextDescription_thenReturnLayer() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertEquals("Layer:",
        authConfigFactoryImpl.registerConfigProvider(
            new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl()), "Layer", null,
            "The characteristics of someone or something"));
    assertArrayEquals(new String[]{"Layer:"}, authConfigFactoryImpl.getRegistrationIDs(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#removeRegistration(String)}.
   * <ul>
   *   <li>When {@code :}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#removeRegistration(String)}
   */
  @Test
  public void testRemoveRegistration_whenColon_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AuthConfigFactoryImpl()).removeRegistration(":"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#removeRegistration(String)}.
   * <ul>
   *   <li>When {@code Registration ID}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#removeRegistration(String)}
   */
  @Test
  public void testRemoveRegistration_whenRegistrationId_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AuthConfigFactoryImpl()).removeRegistration("Registration ID"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}
   */
  @Test
  public void testDetachListener_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.detachListener(new BasicAuthenticator(), "", "App Context"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}.
   * <ul>
   *   <li>When {@code Layer}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}
   */
  @Test
  public void testDetachListener_whenLayer_thenReturnArrayLengthIsZero() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertEquals(0, authConfigFactoryImpl.detachListener(new BasicAuthenticator(), "Layer", "App Context").length);
  }

  /**
   * Test {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}.
   * <ul>
   *   <li>When {@code Layer}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#detachListener(RegistrationListener, String, String)}
   */
  @Test
  public void testDetachListener_whenLayer_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.detachListener(new BasicAuthenticator(), "Layer", ""));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getRegistrationIDs(AuthConfigProvider)}.
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getRegistrationIDs(AuthConfigProvider)}
   */
  @Test
  public void testGetRegistrationIDs() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    HashMap<String, Object> properties = new HashMap<>();

    // Act and Assert
    assertEquals(0, authConfigFactoryImpl
        .getRegistrationIDs(new SimpleAuthConfigProvider(properties, new AuthConfigFactoryImpl())).length);
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getRegistrationIDs(AuthConfigProvider)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getRegistrationIDs(AuthConfigProvider)}
   */
  @Test
  public void testGetRegistrationIDs_whenNull() {
    // Arrange, Act and Assert
    assertEquals(0, (new AuthConfigFactoryImpl()).getRegistrationIDs(null).length);
  }

  /**
   * Test {@link AuthConfigFactoryImpl#getRegistrationContext(String)}.
   * <ul>
   *   <li>When {@code Registration ID}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#getRegistrationContext(String)}
   */
  @Test
  public void testGetRegistrationContext_whenRegistrationId_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new AuthConfigFactoryImpl()).getRegistrationContext("Registration ID"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}.
   * <ul>
   *   <li>Then return {@code HttpServlet:null null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}
   */
  @Test
  public void testRegisterServerAuthModule_thenReturnHttpServletNullNull()
      throws MalformedURLException, JasperException {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    TesterServerAuthModuleA serverAuthModule = new TesterServerAuthModuleA();
    PrintWriter aLogWriter = new PrintWriter(new StringWriter());
    URL aResourceBaseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertEquals("HttpServlet:null null", authConfigFactoryImpl.registerServerAuthModule(serverAuthModule,
        new JspCServletContext(aLogWriter, aResourceBaseURL, new ParallelWebappClassLoader(), true, true)));
    assertArrayEquals(new String[]{"HttpServlet:null null"}, authConfigFactoryImpl.getRegistrationIDs(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}.
   * <ul>
   *   <li>When {@code Context}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}
   */
  @Test
  public void testRegisterServerAuthModule_whenContext_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.registerServerAuthModule(new TesterServerAuthModuleA(), "Context"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#registerServerAuthModule(ServerAuthModule, Object)}
   */
  @Test
  public void testRegisterServerAuthModule_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> authConfigFactoryImpl.registerServerAuthModule(new TesterServerAuthModuleA(), null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}.
   * <ul>
   *   <li>When {@code Context}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}
   */
  @Test
  public void testRemoveServerAuthModule_whenContext() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new AuthConfigFactoryImpl()).removeServerAuthModule("Context"));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}
   */
  @Test
  public void testRemoveServerAuthModule_whenNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new AuthConfigFactoryImpl()).removeServerAuthModule(null));
  }

  /**
   * Test {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}.
   * <ul>
   *   <li>When {@link PrintWriter#PrintWriter(Writer)} with {@link StringWriter#StringWriter()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthConfigFactoryImpl#removeServerAuthModule(Object)}
   */
  @Test
  public void testRemoveServerAuthModule_whenPrintWriterWithStringWriter()
      throws MalformedURLException, JasperException {
    // Arrange
    AuthConfigFactoryImpl authConfigFactoryImpl = new AuthConfigFactoryImpl();
    PrintWriter aLogWriter = new PrintWriter(new StringWriter());
    URL aResourceBaseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> authConfigFactoryImpl.removeServerAuthModule(
        new JspCServletContext(aLogWriter, aResourceBaseURL, new ParallelWebappClassLoader(), true, true)));
  }
}
