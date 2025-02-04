package org.apache.catalina.manager.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.manager.DummyProxySession;
import org.junit.Test;

public class SessionUtilsDiffblueTest {
  /**
   * Test {@link SessionUtils#guessLocaleFromSession(HttpSession)} with {@code HttpSession}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(HttpSession)}
   */
  @Test
  public void testGuessLocaleFromSessionWithHttpSession_givenTrue_whenDeltaSessionValidIsTrue() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setValid(true);

    // Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession((HttpSession) in_session));
  }

  /**
   * Test {@link SessionUtils#guessLocaleFromSession(HttpSession)} with {@code HttpSession}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(HttpSession)}
   */
  @Test
  public void testGuessLocaleFromSessionWithHttpSession_whenDeltaSession_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession((HttpSession) new DeltaSession()));
  }

  /**
   * Test {@link SessionUtils#guessLocaleFromSession(HttpSession)} with {@code HttpSession}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(HttpSession)}
   */
  @Test
  public void testGuessLocaleFromSessionWithHttpSession_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession((HttpSession) null));
  }

  /**
   * Test {@link SessionUtils#guessLocaleFromSession(Session)} with {@code Session}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(Session)}
   */
  @Test
  public void testGuessLocaleFromSessionWithSession_givenTrue_whenDeltaSessionValidIsTrue() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setValid(true);

    // Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession((Session) in_session));
  }

  /**
   * Test {@link SessionUtils#guessLocaleFromSession(Session)} with {@code Session}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(Session)}
   */
  @Test
  public void testGuessLocaleFromSessionWithSession_whenDeltaSession_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession((Session) new DeltaSession()));
  }

  /**
   * Test {@link SessionUtils#guessLocaleFromSession(Session)} with {@code Session}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessLocaleFromSession(Session)}
   */
  @Test
  public void testGuessLocaleFromSessionWithSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessLocaleFromSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link SessionUtils#guessUserFromSession(Session)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessUserFromSession(Session)}
   */
  @Test
  public void testGuessUserFromSession_givenTrue_whenDeltaSessionValidIsTrue_thenReturnNull() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setValid(true);

    // Act and Assert
    assertNull(SessionUtils.guessUserFromSession(in_session));
  }

  /**
   * Test {@link SessionUtils#guessUserFromSession(Session)}.
   * <ul>
   *   <li>Then return {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessUserFromSession(Session)}
   */
  @Test
  public void testGuessUserFromSession_thenReturnPrincipal() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setPrincipal(new UserPrincipal("principal"), true);

    // Act and Assert
    assertEquals("principal", SessionUtils.guessUserFromSession(in_session));
  }

  /**
   * Test {@link SessionUtils#guessUserFromSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessUserFromSession(Session)}
   */
  @Test
  public void testGuessUserFromSession_whenDeltaSession_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessUserFromSession(new DeltaSession()));
  }

  /**
   * Test {@link SessionUtils#guessUserFromSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessUserFromSession(Session)}
   */
  @Test
  public void testGuessUserFromSession_whenDummyProxySessionWithSessionIdIs42_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessUserFromSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link SessionUtils#guessUserFromSession(Session)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#guessUserFromSession(Session)}
   */
  @Test
  public void testGuessUserFromSession_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SessionUtils.guessUserFromSession(null));
  }

  /**
   * Test {@link SessionUtils#getUsedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#getUsedTimeForSession(Session)}
   */
  @Test
  public void testGetUsedTimeForSession_whenDeltaSession_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, SessionUtils.getUsedTimeForSession(new DeltaSession()));
  }

  /**
   * Test {@link SessionUtils#getUsedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#getUsedTimeForSession(Session)}
   */
  @Test
  public void testGetUsedTimeForSession_whenDummyProxySessionWithSessionIdIs42_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, SessionUtils.getUsedTimeForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link SessionUtils#getTTLForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#getTTLForSession(Session)}
   */
  @Test
  public void testGetTTLForSession_whenDeltaSession_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, SessionUtils.getTTLForSession(new DeltaSession()));
  }

  /**
   * Test {@link SessionUtils#getInactiveTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionUtils#getInactiveTimeForSession(Session)}
   */
  @Test
  public void testGetInactiveTimeForSession_whenDeltaSession_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L, SessionUtils.getInactiveTimeForSession(new DeltaSession()));
  }
}
