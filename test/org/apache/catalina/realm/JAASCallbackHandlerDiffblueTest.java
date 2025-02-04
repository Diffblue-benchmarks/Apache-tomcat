package org.apache.catalina.realm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.callback.CertStoreCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.catalina.core.StandardContext;
import org.junit.Test;

public class JAASCallbackHandlerDiffblueTest {
  /**
   * Test {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String)}.
   * <ul>
   *   <li>When {@code iloveyou}.</li>
   *   <li>Then return {@link JAASCallbackHandler#password} is {@code iloveyou}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String)}
   */
  @Test
  public void testNewJAASCallbackHandler_whenIloveyou_thenReturnPasswordIsIloveyou()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    JAASCallbackHandler actualJaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", "iloveyou");
    actualJaasCallbackHandler.handle(new Callback[]{new PasswordCallback("foo", true)});

    // Assert
    assertEquals("iloveyou", actualJaasCallbackHandler.password);
    assertEquals("janedoe", actualJaasCallbackHandler.username);
    assertNull(actualJaasCallbackHandler.algorithm);
    assertNull(actualJaasCallbackHandler.authMethod);
    assertNull(actualJaasCallbackHandler.cnonce);
    assertNull(actualJaasCallbackHandler.digestA2);
    assertNull(actualJaasCallbackHandler.nc);
    assertNull(actualJaasCallbackHandler.nonce);
    assertNull(actualJaasCallbackHandler.qop);
    assertNull(actualJaasCallbackHandler.realmName);
  }

  /**
   * Test {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String, String, String, String, String, String, String, String, String)}.
   * <ul>
   *   <li>When {@code iloveyou}.</li>
   *   <li>Then return {@link JAASCallbackHandler#password} is {@code iloveyou}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  public void testNewJAASCallbackHandler_whenIloveyou_thenReturnPasswordIsIloveyou2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    JAASCallbackHandler actualJaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", "iloveyou",
        "Nonce", "Nc", "Cnonce", "Qop", "Realm Name", "Digest A2", "Algorithm", "Auth Method");
    actualJaasCallbackHandler.handle(new Callback[]{new PasswordCallback("foo", true)});

    // Assert
    assertEquals("Algorithm", actualJaasCallbackHandler.algorithm);
    assertEquals("Auth Method", actualJaasCallbackHandler.authMethod);
    assertEquals("Cnonce", actualJaasCallbackHandler.cnonce);
    assertEquals("Digest A2", actualJaasCallbackHandler.digestA2);
    assertEquals("Nc", actualJaasCallbackHandler.nc);
    assertEquals("Nonce", actualJaasCallbackHandler.nonce);
    assertEquals("Qop", actualJaasCallbackHandler.qop);
    assertEquals("Realm Name", actualJaasCallbackHandler.realmName);
    assertEquals("iloveyou", actualJaasCallbackHandler.password);
    assertEquals("janedoe", actualJaasCallbackHandler.username);
  }

  /**
   * Test {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link JAASCallbackHandler#password} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String)}
   */
  @Test
  public void testNewJAASCallbackHandler_whenNull_thenReturnPasswordIsNull()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    JAASCallbackHandler actualJaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", null);
    actualJaasCallbackHandler.handle(new Callback[]{new PasswordCallback("foo", true)});

    // Assert
    assertEquals("janedoe", actualJaasCallbackHandler.username);
    assertNull(actualJaasCallbackHandler.algorithm);
    assertNull(actualJaasCallbackHandler.authMethod);
    assertNull(actualJaasCallbackHandler.cnonce);
    assertNull(actualJaasCallbackHandler.digestA2);
    assertNull(actualJaasCallbackHandler.nc);
    assertNull(actualJaasCallbackHandler.nonce);
    assertNull(actualJaasCallbackHandler.password);
    assertNull(actualJaasCallbackHandler.qop);
    assertNull(actualJaasCallbackHandler.realmName);
  }

  /**
   * Test {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String, String, String, String, String, String, String, String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link JAASCallbackHandler#password} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#JAASCallbackHandler(JAASRealm, String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  public void testNewJAASCallbackHandler_whenNull_thenReturnPasswordIsNull2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    JAASCallbackHandler actualJaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", null, "Nonce",
        "Nc", "Cnonce", "Qop", "Realm Name", "Digest A2", "Algorithm", "Auth Method");
    actualJaasCallbackHandler.handle(new Callback[]{new PasswordCallback("foo", true)});

    // Assert
    assertEquals("Algorithm", actualJaasCallbackHandler.algorithm);
    assertEquals("Auth Method", actualJaasCallbackHandler.authMethod);
    assertEquals("Cnonce", actualJaasCallbackHandler.cnonce);
    assertEquals("Digest A2", actualJaasCallbackHandler.digestA2);
    assertEquals("Nc", actualJaasCallbackHandler.nc);
    assertEquals("Nonce", actualJaasCallbackHandler.nonce);
    assertEquals("Qop", actualJaasCallbackHandler.qop);
    assertEquals("Realm Name", actualJaasCallbackHandler.realmName);
    assertEquals("janedoe", actualJaasCallbackHandler.username);
    assertNull(actualJaasCallbackHandler.password);
  }

  /**
   * Test {@link JAASCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Given {@link JAASRealm} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   *   <li>Then first element {@link NameCallback}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#handle(Callback[])}
   */
  @Test
  public void testHandle_givenJAASRealmContainerIsStandardContext_thenFirstElementNameCallback()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    JAASRealm realm = new JAASRealm();
    realm.setContainer(new StandardContext());
    JAASCallbackHandler jaasCallbackHandler = new JAASCallbackHandler(realm, "janedoe", "iloveyou");
    Callback[] callbacks = new Callback[]{new NameCallback("foo")};

    // Act
    jaasCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link JAASCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Then first element Password is empty array of {@code char}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#handle(Callback[])}
   */
  @Test
  public void testHandle_thenFirstElementPasswordIsEmptyArrayOfChar() throws IOException, UnsupportedCallbackException {
    // Arrange
    JAASCallbackHandler jaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", null);
    Callback[] callbacks = new Callback[]{new PasswordCallback("foo", true)};

    // Act
    jaasCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    assertArrayEquals(new char[]{}, ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link JAASCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Then first element Password is {@code iloveyou} toCharArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#handle(Callback[])}
   */
  @Test
  public void testHandle_thenFirstElementPasswordIsIloveyouToCharArray()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    JAASCallbackHandler jaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", "iloveyou");
    Callback[] callbacks = new Callback[]{new PasswordCallback("foo", true)};

    // Act
    jaasCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    char[] expectedPassword = "iloveyou".toCharArray();
    assertArrayEquals(expectedPassword, ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link JAASCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>When array of {@link Callback} with {@link CertStoreCallback} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#handle(Callback[])}
   */
  @Test
  public void testHandle_whenArrayOfCallbackWithCertStoreCallback() throws IOException, UnsupportedCallbackException {
    // Arrange
    JAASCallbackHandler jaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", "iloveyou");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> jaasCallbackHandler.handle(new Callback[]{new CertStoreCallback()}));
  }

  /**
   * Test {@link JAASCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>When array of {@link Callback} with {@link TextInputCallback#TextInputCallback(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JAASCallbackHandler#handle(Callback[])}
   */
  @Test
  public void testHandle_whenArrayOfCallbackWithTextInputCallbackWithFoo()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    JAASCallbackHandler jaasCallbackHandler = new JAASCallbackHandler(new JAASRealm(), "janedoe", "iloveyou");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> jaasCallbackHandler.handle(new Callback[]{new TextInputCallback("foo")}));
  }
}
