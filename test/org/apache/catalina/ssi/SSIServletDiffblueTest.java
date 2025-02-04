package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteWriter;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.coyote.Request;
import org.junit.Test;

public class SSIServletDiffblueTest {
  /**
   * Test {@link SSIServlet#processSSI(HttpServletRequest, HttpServletResponse, URL)}.
   * <p>
   * Method under test: {@link SSIServlet#processSSI(HttpServletRequest, HttpServletResponse, URL)}
   */
  @Test
  public void testProcessSSI() throws ServletException, IOException {
    // Arrange
    SSIServlet ssiServlet = new SSIServlet();
    ssiServlet.init(new StandardWrapper());
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    Response response = new Response(new org.apache.coyote.Response());
    Connector connector2 = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector2, new Request()));
    HttpServletResponseWrapper res = new HttpServletResponseWrapper(response);

    // Act
    ssiServlet.processSSI(req, res, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    Collection<String> headerNames = res.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    PrintWriter writer = res.getWriter();
    assertTrue(writer instanceof CoyoteWriter);
    ServletResponse response2 = res.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("ISO-8859-1", ((Response) response2).getCoyoteResponse().getCharacterEncoding());
    assertEquals("last-modified", ((List<String>) headerNames).get(0));
    assertNull(((Response) response2).getReporter());
    assertEquals(81L, ((Response) response2).getContentWritten());
    assertEquals(81L, ((ResponseFacade) response3).getContentWritten());
    assertSame(writer, response2.getWriter());
    assertSame(writer, response3.getWriter());
  }

  /**
   * Test {@link SSIServlet#processSSI(HttpServletRequest, HttpServletResponse, URL)}.
   * <p>
   * Method under test: {@link SSIServlet#processSSI(HttpServletRequest, HttpServletResponse, URL)}
   */
  @Test
  public void testProcessSSI2() throws ServletException, IOException {
    // Arrange
    SSIServlet ssiServlet = new SSIServlet();
    ssiServlet.init(new StandardWrapper());
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper res = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    ssiServlet.processSSI(req, res, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert that nothing has changed
    assertNull(res.getHeaderNames());
  }

  /**
   * Test new {@link SSIServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SSIServlet}
   */
  @Test
  public void testNewSSIServlet() {
    // Arrange, Act and Assert
    assertNull((new SSIServlet()).getServletConfig());
  }
}
