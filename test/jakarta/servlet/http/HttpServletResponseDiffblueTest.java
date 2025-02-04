package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class HttpServletResponseDiffblueTest {
  /**
   * Test {@link HttpServletResponse#sendRedirect(String)} with {@code location}.
   * <p>
   * Method under test: {@link HttpServletResponse#sendRedirect(String)}
   */
  @Test
  public void testSendRedirectWithLocation() throws IOException {
    // Arrange
    org.apache.catalina.connector.Response response = new org.apache.catalina.connector.Response(new Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));

    // Act
    response.sendRedirect("https://example.org/example");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getMimeHeaders().size());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isSuspended());
    assertEquals(HttpServletResponse.SC_FOUND, response2.getStatus());
    assertEquals(HttpServletResponse.SC_FOUND, response.getStatus());
    assertEquals(HttpServletResponse.SC_FOUND, coyoteResponse.getStatus());
  }

  /**
   * Test {@link HttpServletResponse#sendRedirect(String, boolean)} with {@code location}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link HttpServletResponse#sendRedirect(String, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationClearBuffer() throws IOException {
    // Arrange
    org.apache.catalina.connector.Response response = new org.apache.catalina.connector.Response(new Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));

    // Act
    response.sendRedirect("https://example.org/example", true);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getMimeHeaders().size());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isSuspended());
    assertEquals(HttpServletResponse.SC_FOUND, response2.getStatus());
    assertEquals(HttpServletResponse.SC_FOUND, response.getStatus());
    assertEquals(HttpServletResponse.SC_FOUND, coyoteResponse.getStatus());
  }

  /**
   * Test {@link HttpServletResponse#sendRedirect(String, int)} with {@code location}, {@code sc}.
   * <p>
   * Method under test: {@link HttpServletResponse#sendRedirect(String, int)}
   */
  @Test
  public void testSendRedirectWithLocationSc() throws IOException {
    // Arrange
    org.apache.catalina.connector.Response response = new org.apache.catalina.connector.Response(new Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));

    // Act
    response.sendRedirect("https://example.org/example", 1);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getStatus());
    assertEquals(1, coyoteResponse.getMimeHeaders().size());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isSuspended());
  }
}
