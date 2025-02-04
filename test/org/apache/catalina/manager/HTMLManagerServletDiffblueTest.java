package org.apache.catalina.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.util.ContextName;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.apache.tomcat.util.res.StringManager;
import org.junit.Test;

public class HTMLManagerServletDiffblueTest {
  /**
   * Test {@link HTMLManagerServlet#findleaks(StringManager)} with {@code smClient}.
   * <p>
   * Method under test: {@link HTMLManagerServlet#findleaks(StringManager)}
   */
  @Test
  public void testFindleaksWithSmClient() {
    // Arrange, Act and Assert
    assertEquals("FAIL - Find leaks failed: Host not instance of StandardHost\n",
        (new HTMLManagerServlet()).findleaks(ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#getServletInfo()}.
   * <p>
   * Method under test: {@link HTMLManagerServlet#getServletInfo()}
   */
  @Test
  public void testGetServletInfo() {
    // Arrange, Act and Assert
    assertEquals("HTMLManagerServlet, Copyright (c) 1999-2025, Apache Software Foundation",
        (new HTMLManagerServlet()).getServletInfo());
  }

  /**
   * Test {@link HTMLManagerServlet#getSessionsForName(ContextName, StringManager)}.
   * <ul>
   *   <li>When {@link ContextName#ContextName(String, String)} with {@code Path} and version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#getSessionsForName(ContextName, StringManager)}
   */
  @Test
  public void testGetSessionsForName_whenContextNameWithPathAndVersionIs102() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> htmlManagerServlet.getSessionsForName(new ContextName("Path", "1.0.2"), ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#getSessionsForName(ContextName, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#getSessionsForName(ContextName, StringManager)}
   */
  @Test
  public void testGetSessionsForName_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HTMLManagerServlet()).getSessionsForName(null, ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#getSessionForNameAndId(ContextName, String, StringManager)}.
   * <ul>
   *   <li>When {@link ContextName#ContextName(String, String)} with {@code Path} and version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#getSessionForNameAndId(ContextName, String, StringManager)}
   */
  @Test
  public void testGetSessionForNameAndId_whenContextNameWithPathAndVersionIs102() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> htmlManagerServlet.getSessionForNameAndId(new ContextName("Path", "1.0.2"), "42", ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#getSessionForNameAndId(ContextName, String, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#getSessionForNameAndId(ContextName, String, StringManager)}
   */
  @Test
  public void testGetSessionForNameAndId_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HTMLManagerServlet()).getSessionForNameAndId(null, "42", ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#displaySessionsListPage(ContextName, HttpServletRequest, HttpServletResponse, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#displaySessionsListPage(ContextName, HttpServletRequest, HttpServletResponse, StringManager)}
   */
  @Test
  public void testDisplaySessionsListPage_whenNull_thenThrowIllegalArgumentException()
      throws ServletException, IOException {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> htmlManagerServlet.displaySessionsListPage(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#displaySessionDetailPage(HttpServletRequest, HttpServletResponse, ContextName, String, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#displaySessionDetailPage(HttpServletRequest, HttpServletResponse, ContextName, String, StringManager)}
   */
  @Test
  public void testDisplaySessionDetailPage_whenNull_thenThrowIllegalArgumentException()
      throws ServletException, IOException {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> htmlManagerServlet.displaySessionDetailPage(req,
            new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), null, "42",
            ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}.
   * <ul>
   *   <li>When {@link ContextName#ContextName(String, String)} with {@code Path} and version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}
   */
  @Test
  public void testInvalidateSessions_whenContextNameWithPathAndVersionIs102() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> htmlManagerServlet
        .invalidateSessions(new ContextName("Path", "1.0.2"), new String[]{"Session Ids"}, ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}.
   * <ul>
   *   <li>When empty array of {@link String}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}
   */
  @Test
  public void testInvalidateSessions_whenEmptyArrayOfString_thenReturnZero() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertEquals(0,
        htmlManagerServlet.invalidateSessions(ContextName.extractFromPath("Path"), new String[]{}, ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}.
   * <ul>
   *   <li>When extractFromPath {@code Path}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}
   */
  @Test
  public void testInvalidateSessions_whenExtractFromPathPath_thenReturnZero() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertEquals(0,
        htmlManagerServlet.invalidateSessions(ContextName.extractFromPath("Path"), null, ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#invalidateSessions(ContextName, String[], StringManager)}
   */
  @Test
  public void testInvalidateSessions_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HTMLManagerServlet()).invalidateSessions(null, new String[]{"Session Ids"}, ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#removeSessionAttribute(ContextName, String, String, StringManager)}.
   * <ul>
   *   <li>When {@link ContextName#ContextName(String, String)} with {@code Path} and version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#removeSessionAttribute(ContextName, String, String, StringManager)}
   */
  @Test
  public void testRemoveSessionAttribute_whenContextNameWithPathAndVersionIs102() {
    // Arrange
    HTMLManagerServlet htmlManagerServlet = new HTMLManagerServlet();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> htmlManagerServlet
        .removeSessionAttribute(new ContextName("Path", "1.0.2"), "42", "Attribute Name", ManagerServlet.sm));
  }

  /**
   * Test {@link HTMLManagerServlet#removeSessionAttribute(ContextName, String, String, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTMLManagerServlet#removeSessionAttribute(ContextName, String, String, StringManager)}
   */
  @Test
  public void testRemoveSessionAttribute_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new HTMLManagerServlet()).removeSessionAttribute(null, "42", "Attribute Name", ManagerServlet.sm));
  }

  /**
   * Test new {@link HTMLManagerServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HTMLManagerServlet}
   */
  @Test
  public void testNewHTMLManagerServlet() {
    // Arrange and Act
    HTMLManagerServlet actualHtmlManagerServlet = new HTMLManagerServlet();

    // Assert
    assertNull(actualHtmlManagerServlet.getServletConfig());
    assertNull(actualHtmlManagerServlet.getWrapper());
  }
}
