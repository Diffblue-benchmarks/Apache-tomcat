package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.filters.TesterHttpServletRequest;
import org.apache.coyote.Request;
import org.apache.tomcat.unittest.TesterRequest;
import org.junit.Test;

public class HttpServletRequestWrapperDiffblueTest {
  /**
   * Test {@link HttpServletRequestWrapper#getAuthType()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getAuthType()}
   */
  @Test
  public void testGetAuthType() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getAuthType());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getAuthType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getAuthType()}
   */
  @Test
  public void testGetAuthType_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getAuthType());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getCookies()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getCookies()}
   */
  @Test
  public void testGetCookies() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getCookies());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getCookies()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getCookies()}
   */
  @Test
  public void testGetCookies_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getCookies());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getDateHeader(String)}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertEquals(-1L, httpServletRequestWrapper.getDateHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getDateHeader(String)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getDateHeader(String)}
   */
  @Test
  public void testGetDateHeader_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L,
        (new HttpServletRequestWrapper(
            new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
            .getDateHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getHeader(String)}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getHeader(String)}
   */
  @Test
  public void testGetHeader() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getHeader(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getHeader(String)}
   */
  @Test
  public void testGetHeader_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .getHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getIntHeader(String)}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertEquals(-1, httpServletRequestWrapper.getIntHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getIntHeader(String)}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getIntHeader(String)}
   */
  @Test
  public void testGetIntHeader_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1,
        (new HttpServletRequestWrapper(
            new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
            .getIntHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getMethod()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getMethod()}
   */
  @Test
  public void testGetMethod() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getMethod());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getMethod()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getMethod()}
   */
  @Test
  public void testGetMethod_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getMethod());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getPathInfo()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getPathInfo()}
   */
  @Test
  public void testGetPathInfo() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getPathInfo());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getPathInfo()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getPathInfo()}
   */
  @Test
  public void testGetPathInfo_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getPathInfo());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getPathTranslated()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getPathTranslated()}
   */
  @Test
  public void testGetPathTranslated() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getPathTranslated());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getPathTranslated()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getPathTranslated()}
   */
  @Test
  public void testGetPathTranslated_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getPathTranslated());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getContextPath()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getContextPath()}
   */
  @Test
  public void testGetContextPath() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertEquals("", httpServletRequestWrapper.getContextPath());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getContextPath()}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getContextPath()}
   */
  @Test
  public void testGetContextPath_thenReturnEmptyString() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("", (new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getContextPath());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getQueryString()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getQueryString()}
   */
  @Test
  public void testGetQueryString() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getQueryString());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getQueryString()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getQueryString()}
   */
  @Test
  public void testGetQueryString_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getQueryString());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRemoteUser()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getRemoteUser());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRemoteUser()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRemoteUser()}
   */
  @Test
  public void testGetRemoteUser_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getRemoteUser());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isUserInRole(String)}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isUserInRole(String)}
   */
  @Test
  public void testIsUserInRole() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertFalse(httpServletRequestWrapper.isUserInRole("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#isUserInRole(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isUserInRole(String)}
   */
  @Test
  public void testIsUserInRole_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isUserInRole("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRequestedSessionId()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRequestedSessionId()}
   */
  @Test
  public void testGetRequestedSessionId() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getRequestedSessionId());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRequestedSessionId()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRequestedSessionId()}
   */
  @Test
  public void testGetRequestedSessionId_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .getRequestedSessionId());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRequestURI()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRequestURI()}
   */
  @Test
  public void testGetRequestURI() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getRequestURI());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRequestURI()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRequestURI()}
   */
  @Test
  public void testGetRequestURI_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getRequestURI());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getRequestURL()}.
   * <ul>
   *   <li>Then return toString is {@code http://localhost:8080/level1/level2/foo.html}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getRequestURL()}
   */
  @Test
  public void testGetRequestURL_thenReturnToStringIsHttpLocalhost8080Level1Level2FooHtml() {
    // Arrange, Act and Assert
    assertEquals("http://localhost:8080/level1/level2/foo.html",
        (new HttpServletRequestWrapper(new RequestFacade(new TesterRequest()))).getRequestURL().toString());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getServletPath()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getServletPath()}
   */
  @Test
  public void testGetServletPath() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getServletPath());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getServletPath()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getServletPath()}
   */
  @Test
  public void testGetServletPath_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getServletPath());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getSession()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getSession()}
   */
  @Test
  public void testGetSession() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getSession());
  }

  /**
   * Test {@link HttpServletRequestWrapper#getSession(boolean)} with {@code boolean}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getSession(boolean)}
   */
  @Test
  public void testGetSessionWithBoolean() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.getSession(true));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getSession(boolean)} with {@code boolean}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getSession(boolean)}
   */
  @Test
  public void testGetSessionWithBoolean_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getSession(true));
  }

  /**
   * Test {@link HttpServletRequestWrapper#getSession()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#getSession()}
   */
  @Test
  public void testGetSession_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).getSession());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdValid()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdValid()}
   */
  @Test
  public void testIsRequestedSessionIdValid() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertFalse(httpServletRequestWrapper.isRequestedSessionIdValid());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdValid()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdValid()}
   */
  @Test
  public void testIsRequestedSessionIdValid_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isRequestedSessionIdValid());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdFromCookie()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertFalse(httpServletRequestWrapper.isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdFromCookie()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdFromCookie()}
   */
  @Test
  public void testIsRequestedSessionIdFromCookie_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isRequestedSessionIdFromCookie());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdFromURL()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertFalse(httpServletRequestWrapper.isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isRequestedSessionIdFromURL()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isRequestedSessionIdFromURL()}
   */
  @Test
  public void testIsRequestedSessionIdFromURL_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isRequestedSessionIdFromURL());
  }

  /**
   * Test {@link HttpServletRequestWrapper#upgrade(Class)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#upgrade(Class)}
   */
  @Test
  public void testUpgrade_thenReturnNull() throws ServletException, IOException {
    // Arrange
    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(new TesterHttpServletRequest());
    Class<HttpUpgradeHandler> httpUpgradeHandlerClass = HttpUpgradeHandler.class;

    // Act and Assert
    assertNull(httpServletRequestWrapper.upgrade(httpUpgradeHandlerClass));
  }

  /**
   * Test {@link HttpServletRequestWrapper#newPushBuilder()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#newPushBuilder()}
   */
  @Test
  public void testNewPushBuilder() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertNull(httpServletRequestWrapper.newPushBuilder());
  }

  /**
   * Test {@link HttpServletRequestWrapper#newPushBuilder()}.
   * <ul>
   *   <li>Given {@link HttpServletRequestWrapper#HttpServletRequestWrapper(HttpServletRequest)} with request is {@link RequestFacade#RequestFacade(Request)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#newPushBuilder()}
   */
  @Test
  public void testNewPushBuilder_givenHttpServletRequestWrapperWithRequestIsRequestFacade() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))).newPushBuilder());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isTrailerFieldsReady()}.
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady() {
    // Arrange
    Connector connector = new Connector();

    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    Connector connector2 = new Connector();
    httpServletRequestWrapper.setRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector2, new Request()))));

    // Act and Assert
    assertFalse(httpServletRequestWrapper.isTrailerFieldsReady());
  }

  /**
   * Test {@link HttpServletRequestWrapper#isTrailerFieldsReady()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletRequestWrapper#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isTrailerFieldsReady());
  }
}
