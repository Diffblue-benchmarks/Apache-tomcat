package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.junit.Test;

public class SessionConfigDiffblueTest {
  /**
   * Test {@link SessionConfig#getSessionCookieName(Context)}.
   * <ul>
   *   <li>Given {@code Context}.</li>
   *   <li>Then return {@code Context}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionConfig#getSessionCookieName(Context)}
   */
  @Test
  public void testGetSessionCookieName_givenContext_thenReturnContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setSessionCookieName("Context");

    // Act and Assert
    assertEquals("Context", SessionConfig.getSessionCookieName(context));
  }

  /**
   * Test {@link SessionConfig#getSessionCookieName(Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code JSESSIONID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionConfig#getSessionCookieName(Context)}
   */
  @Test
  public void testGetSessionCookieName_whenNull_thenReturnJsessionid() {
    // Arrange, Act and Assert
    assertEquals("JSESSIONID", SessionConfig.getSessionCookieName(null));
  }

  /**
   * Test {@link SessionConfig#getSessionUriParamName(Context)}.
   * <ul>
   *   <li>Given {@code Context}.</li>
   *   <li>Then return {@code Context}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionConfig#getSessionUriParamName(Context)}
   */
  @Test
  public void testGetSessionUriParamName_givenContext_thenReturnContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setSessionCookieName("Context");

    // Act and Assert
    assertEquals("Context", SessionConfig.getSessionUriParamName(context));
  }

  /**
   * Test {@link SessionConfig#getSessionUriParamName(Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code jsessionid}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionConfig#getSessionUriParamName(Context)}
   */
  @Test
  public void testGetSessionUriParamName_whenNull_thenReturnJsessionid() {
    // Arrange, Act and Assert
    assertEquals("jsessionid", SessionConfig.getSessionUriParamName(null));
  }
}
