package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.startup.ContextConfig;
import org.junit.Test;

public class LifecycleBaseDiffblueTest {
  /**
   * Test {@link LifecycleBase#getThrowOnFailure()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#getThrowOnFailure()}
   */
  @Test
  public void testGetThrowOnFailure_givenBasicAuthenticator_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new BasicAuthenticator()).getThrowOnFailure());
  }

  /**
   * Test {@link LifecycleBase#getThrowOnFailure()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#getThrowOnFailure()}
   */
  @Test
  public void testGetThrowOnFailure_thenReturnFalse() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setThrowOnFailure(false);

    // Act and Assert
    assertFalse(basicAuthenticator.getThrowOnFailure());
  }

  /**
   * Test {@link LifecycleBase#addLifecycleListener(LifecycleListener)}.
   * <p>
   * Method under test: {@link LifecycleBase#addLifecycleListener(LifecycleListener)}
   */
  @Test
  public void testAddLifecycleListener() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    AprLifecycleListener listener = new AprLifecycleListener();

    // Act
    basicAuthenticator.addLifecycleListener(listener);

    // Assert
    LifecycleListener[] findLifecycleListenersResult = basicAuthenticator.findLifecycleListeners();
    assertEquals(1, findLifecycleListenersResult.length);
    assertSame(listener, findLifecycleListenersResult[0]);
  }

  /**
   * Test {@link LifecycleBase#findLifecycleListeners()}.
   * <p>
   * Method under test: {@link LifecycleBase#findLifecycleListeners()}
   */
  @Test
  public void testFindLifecycleListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new BasicAuthenticator()).findLifecycleListeners().length);
  }

  /**
   * Test {@link LifecycleBase#stop()}.
   * <p>
   * Method under test: {@link LifecycleBase#stop()}
   */
  @Test
  public void testStop() throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.stop();

    // Assert
    assertEquals("STOPPED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.STOPPED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#destroy()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) addLifecycleListener {@link AprLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#destroy()}
   */
  @Test
  public void testDestroy_givenBasicAuthenticatorAddLifecycleListenerAprLifecycleListener() throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.addLifecycleListener(new AprLifecycleListener());

    // Act
    basicAuthenticator.destroy();

    // Assert
    assertEquals("DESTROYED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.DESTROYED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#destroy()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) addLifecycleListener {@link ContextConfig} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#destroy()}
   */
  @Test
  public void testDestroy_givenBasicAuthenticatorAddLifecycleListenerContextConfig() throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.addLifecycleListener(new ContextConfig());

    // Act
    basicAuthenticator.destroy();

    // Assert
    assertEquals("DESTROYED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.DESTROYED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#destroy()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) StateName is {@code DESTROYED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#destroy()}
   */
  @Test
  public void testDestroy_givenBasicAuthenticator_thenBasicAuthenticatorStateNameIsDestroyed()
      throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.destroy();

    // Assert
    assertEquals("DESTROYED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.DESTROYED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#getState()}.
   * <p>
   * Method under test: {@link LifecycleBase#getState()}
   */
  @Test
  public void testGetState() {
    // Arrange, Act and Assert
    assertEquals(LifecycleState.NEW, (new BasicAuthenticator()).getState());
  }

  /**
   * Test {@link LifecycleBase#getStateName()}.
   * <p>
   * Method under test: {@link LifecycleBase#getStateName()}
   */
  @Test
  public void testGetStateName() {
    // Arrange, Act and Assert
    assertEquals("NEW", (new BasicAuthenticator()).getStateName());
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState, Object)} with {@code state}, {@code data}.
   * <ul>
   *   <li>When {@link LifecycleState#FAILED}.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) StateName is {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState, Object)}
   */
  @Test
  public void testSetStateWithStateData_whenFailed_thenBasicAuthenticatorStateNameIsFailed() throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setState(LifecycleState.FAILED, "Data");

    // Assert
    assertEquals("FAILED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.FAILED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState, Object)} with {@code state}, {@code data}.
   * <ul>
   *   <li>When {@code NEW}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState, Object)}
   */
  @Test
  public void testSetStateWithStateData_whenNew_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new BasicAuthenticator()).setState(LifecycleState.NEW, "Data"));
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState, Object)} with {@code state}, {@code data}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState, Object)}
   */
  @Test
  public void testSetStateWithStateData_whenNull_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new BasicAuthenticator()).setState(null, "Data"));
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState)} with {@code state}.
   * <ul>
   *   <li>When {@link LifecycleState#FAILED}.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) StateName is {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState)}
   */
  @Test
  public void testSetStateWithState_whenFailed_thenBasicAuthenticatorStateNameIsFailed() throws LifecycleException {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setState(LifecycleState.FAILED);

    // Assert
    assertEquals("FAILED", basicAuthenticator.getStateName());
    assertEquals(LifecycleState.FAILED, basicAuthenticator.getState());
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState)} with {@code state}.
   * <ul>
   *   <li>When {@code NEW}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState)}
   */
  @Test
  public void testSetStateWithState_whenNew_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new BasicAuthenticator()).setState(LifecycleState.NEW));
  }

  /**
   * Test {@link LifecycleBase#setState(LifecycleState)} with {@code state}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleBase#setState(LifecycleState)}
   */
  @Test
  public void testSetStateWithState_whenNull_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new BasicAuthenticator()).setState(null));
  }
}
