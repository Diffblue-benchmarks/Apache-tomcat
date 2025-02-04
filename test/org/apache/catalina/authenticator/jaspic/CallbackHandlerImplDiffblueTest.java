package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import jakarta.security.auth.message.callback.CallerPrincipalCallback;
import java.io.IOException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.catalina.Container;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.GenericPrincipal;
import org.junit.Test;

public class CallbackHandlerImplDiffblueTest {
  /**
   * Test {@link CallbackHandlerImpl#handle(Callback[])}.
   * <p>
   * Method under test: {@link CallbackHandlerImpl#handle(Callback[])}
   */
  @Test
  public void testHandle() throws IOException, UnsupportedCallbackException {
    // Arrange
    CallbackHandlerImpl callbackHandlerImpl = new CallbackHandlerImpl();
    Callback[] callbacks = new Callback[]{
        new CallerPrincipalCallback(new Subject(), "callbackHandlerImpl.jaspicCallbackMissing")};

    // Act
    callbackHandlerImpl.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof CallerPrincipalCallback);
    assertEquals(1, ((CallerPrincipalCallback) callback).getSubject().getPrivateCredentials().size());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link CallbackHandlerImpl#handle(Callback[])}.
   * <p>
   * Method under test: {@link CallbackHandlerImpl#handle(Callback[])}
   */
  @Test
  public void testHandle2() throws IOException, UnsupportedCallbackException {
    // Arrange
    CallbackHandlerImpl callbackHandlerImpl = new CallbackHandlerImpl();
    Subject subject = new Subject();
    Callback[] callbacks = new Callback[]{new CallerPrincipalCallback(subject, new GenericPrincipal("Name"))};

    // Act
    callbackHandlerImpl.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof CallerPrincipalCallback);
    assertEquals(1, ((CallerPrincipalCallback) callback).getSubject().getPrivateCredentials().size());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link CallbackHandlerImpl#handle(Callback[])}.
   * <ul>
   *   <li>Then first element Subject PrivateCredentials Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CallbackHandlerImpl#handle(Callback[])}
   */
  @Test
  public void testHandle_thenFirstElementSubjectPrivateCredentialsEmpty()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    CallbackHandlerImpl callbackHandlerImpl = new CallbackHandlerImpl();
    Callback[] callbacks = new Callback[]{new CallerPrincipalCallback(new Subject(), (String) null)};

    // Act
    callbackHandlerImpl.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof CallerPrincipalCallback);
    assertEquals(1, callbacks.length);
    assertTrue(((CallerPrincipalCallback) callback).getSubject().getPrivateCredentials().isEmpty());
  }

  /**
   * Test {@link CallbackHandlerImpl#handle(Callback[])}.
   * <ul>
   *   <li>When {@link UserPrincipal#UserPrincipal(String)} with name is {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CallbackHandlerImpl#handle(Callback[])}
   */
  @Test
  public void testHandle_whenUserPrincipalWithNameIsPrincipal() throws IOException, UnsupportedCallbackException {
    // Arrange
    CallbackHandlerImpl callbackHandlerImpl = new CallbackHandlerImpl();
    Subject subject = new Subject();
    Callback[] callbacks = new Callback[]{new CallerPrincipalCallback(subject, new UserPrincipal("principal"))};

    // Act
    callbackHandlerImpl.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof CallerPrincipalCallback);
    assertEquals(1, ((CallerPrincipalCallback) callback).getSubject().getPrivateCredentials().size());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CallbackHandlerImpl#setContainer(Container)}
   *   <li>{@link CallbackHandlerImpl#getContainer()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    CallbackHandlerImpl callbackHandlerImpl = new CallbackHandlerImpl();
    StandardContext container = new StandardContext();

    // Act
    callbackHandlerImpl.setContainer(container);

    // Assert
    assertSame(container, callbackHandlerImpl.getContainer());
  }

  /**
   * Test new {@link CallbackHandlerImpl} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CallbackHandlerImpl}
   */
  @Test
  public void testNewCallbackHandlerImpl() {
    // Arrange, Act and Assert
    assertNull((new CallbackHandlerImpl()).getContainer());
  }
}
