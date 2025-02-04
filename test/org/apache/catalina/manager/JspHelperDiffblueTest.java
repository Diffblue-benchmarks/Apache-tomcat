package org.apache.catalina.manager;

import static org.junit.Assert.assertEquals;
import com.sun.security.auth.UserPrincipal;
import org.apache.catalina.Session;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class JspHelperDiffblueTest {
  /**
   * Test {@link JspHelper#guessDisplayLocaleFromSession(Session)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayLocaleFromSession(Session)}
   */
  @Test
  public void testGuessDisplayLocaleFromSession_givenTrue_whenDeltaSessionValidIsTrue() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setValid(true);

    // Act and Assert
    assertEquals("", JspHelper.guessDisplayLocaleFromSession(in_session));
  }

  /**
   * Test {@link JspHelper#guessDisplayLocaleFromSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayLocaleFromSession(Session)}
   */
  @Test
  public void testGuessDisplayLocaleFromSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.guessDisplayLocaleFromSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#guessDisplayLocaleFromSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayLocaleFromSession(Session)}
   */
  @Test
  public void testGuessDisplayLocaleFromSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.guessDisplayLocaleFromSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#guessDisplayUserFromSession(Session)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayUserFromSession(Session)}
   */
  @Test
  public void testGuessDisplayUserFromSession_givenTrue_whenDeltaSessionValidIsTrue() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setValid(true);

    // Act and Assert
    assertEquals("", JspHelper.guessDisplayUserFromSession(in_session));
  }

  /**
   * Test {@link JspHelper#guessDisplayUserFromSession(Session)}.
   * <ul>
   *   <li>Then return {@code principal}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayUserFromSession(Session)}
   */
  @Test
  public void testGuessDisplayUserFromSession_thenReturnPrincipal() {
    // Arrange
    DeltaSession in_session = new DeltaSession();
    in_session.setPrincipal(new UserPrincipal("principal"), true);

    // Act and Assert
    assertEquals("principal", JspHelper.guessDisplayUserFromSession(in_session));
  }

  /**
   * Test {@link JspHelper#guessDisplayUserFromSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayUserFromSession(Session)}
   */
  @Test
  public void testGuessDisplayUserFromSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.guessDisplayUserFromSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#guessDisplayUserFromSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayUserFromSession(Session)}
   */
  @Test
  public void testGuessDisplayUserFromSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.guessDisplayUserFromSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#guessDisplayUserFromSession(Session)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#guessDisplayUserFromSession(Session)}
   */
  @Test
  public void testGuessDisplayUserFromSession_whenNull_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.guessDisplayUserFromSession(null));
  }

  /**
   * Test {@link JspHelper#getDisplayCreationTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayCreationTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayCreationTimeForSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayCreationTimeForSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#getDisplayCreationTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayCreationTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayCreationTimeForSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayCreationTimeForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#getDisplayLastAccessedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayLastAccessedTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayLastAccessedTimeForSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayLastAccessedTimeForSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#getDisplayLastAccessedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayLastAccessedTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayLastAccessedTimeForSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayLastAccessedTimeForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#getDisplayUsedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayUsedTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayUsedTimeForSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayUsedTimeForSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#getDisplayUsedTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayUsedTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayUsedTimeForSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayUsedTimeForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#getDisplayTTLForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayTTLForSession(Session)}
   */
  @Test
  public void testGetDisplayTTLForSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayTTLForSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#getDisplayTTLForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayTTLForSession(Session)}
   */
  @Test
  public void testGetDisplayTTLForSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayTTLForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#getDisplayInactiveTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayInactiveTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayInactiveTimeForSession_whenDeltaSession_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayInactiveTimeForSession(new DeltaSession()));
  }

  /**
   * Test {@link JspHelper#getDisplayInactiveTimeForSession(Session)}.
   * <ul>
   *   <li>When {@link DummyProxySession#DummyProxySession(String)} with sessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#getDisplayInactiveTimeForSession(Session)}
   */
  @Test
  public void testGetDisplayInactiveTimeForSession_whenDummyProxySessionWithSessionIdIs42() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.getDisplayInactiveTimeForSession(new DummyProxySession("42")));
  }

  /**
   * Test {@link JspHelper#secondsToTimeString(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code 2562047788015215:30:07}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#secondsToTimeString(long)}
   */
  @Test
  public void testSecondsToTimeString_whenMax_value_thenReturn25620477880152153007() {
    // Arrange, Act and Assert
    assertEquals("2562047788015215:30:07", JspHelper.secondsToTimeString(Long.MAX_VALUE));
  }

  /**
   * Test {@link JspHelper#secondsToTimeString(long)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code -00:00:01}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#secondsToTimeString(long)}
   */
  @Test
  public void testSecondsToTimeString_whenMinusOne_thenReturn000001() {
    // Arrange, Act and Assert
    assertEquals("-00:00:01", JspHelper.secondsToTimeString(-1L));
  }

  /**
   * Test {@link JspHelper#secondsToTimeString(long)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 00:00:01}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#secondsToTimeString(long)}
   */
  @Test
  public void testSecondsToTimeString_whenOne_thenReturn000001() {
    // Arrange, Act and Assert
    assertEquals("00:00:01", JspHelper.secondsToTimeString(1L));
  }

  /**
   * Test {@link JspHelper#secondsToTimeString(long)}.
   * <ul>
   *   <li>When ten.</li>
   *   <li>Then return {@code 00:00:10}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#secondsToTimeString(long)}
   */
  @Test
  public void testSecondsToTimeString_whenTen_thenReturn000010() {
    // Arrange, Act and Assert
    assertEquals("00:00:10", JspHelper.secondsToTimeString(10L));
  }

  /**
   * Test {@link JspHelper#escapeXml(String)} with {@code buffer}.
   * <ul>
   *   <li>When {@code &#039;}.</li>
   *   <li>Then return {@code &amp;#039;}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(String)}
   */
  @Test
  public void testEscapeXmlWithBuffer_when039_thenReturnAmp039() {
    // Arrange, Act and Assert
    assertEquals("&amp;#039;", JspHelper.escapeXml("&#039;"));
  }

  /**
   * Test {@link JspHelper#escapeXml(String)} with {@code buffer}.
   * <ul>
   *   <li>When {@code Buffer}.</li>
   *   <li>Then return {@code Buffer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(String)}
   */
  @Test
  public void testEscapeXmlWithBuffer_whenBuffer_thenReturnBuffer() {
    // Arrange, Act and Assert
    assertEquals("Buffer", JspHelper.escapeXml("Buffer"));
  }

  /**
   * Test {@link JspHelper#escapeXml(String)} with {@code buffer}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(String)}
   */
  @Test
  public void testEscapeXmlWithBuffer_whenNull_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.escapeXml((String) null));
  }

  /**
   * Test {@link JspHelper#escapeXml(Object)} with {@code obj}.
   * <ul>
   *   <li>When {@code &#039;}.</li>
   *   <li>Then return {@code &amp;#039;}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(Object)}
   */
  @Test
  public void testEscapeXmlWithObj_when039_thenReturnAmp039() {
    // Arrange, Act and Assert
    assertEquals("&amp;#039;", JspHelper.escapeXml((Object) "&#039;"));
  }

  /**
   * Test {@link JspHelper#escapeXml(Object)} with {@code obj}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(Object)}
   */
  @Test
  public void testEscapeXmlWithObj_whenNull_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JspHelper.escapeXml((Object) null));
  }

  /**
   * Test {@link JspHelper#escapeXml(Object)} with {@code obj}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspHelper#escapeXml(Object)}
   */
  @Test
  public void testEscapeXmlWithObj_whenObj_thenReturnObj() {
    // Arrange, Act and Assert
    assertEquals("Obj", JspHelper.escapeXml((Object) "Obj"));
  }

  /**
   * Test {@link JspHelper#formatNumber(long)}.
   * <p>
   * Method under test: {@link JspHelper#formatNumber(long)}
   */
  @Test
  public void testFormatNumber() {
    // Arrange, Act and Assert
    assertEquals("1", JspHelper.formatNumber(1L));
  }
}
