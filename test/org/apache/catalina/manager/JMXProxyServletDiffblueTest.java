package org.apache.catalina.manager;

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
import java.util.Collection;
import java.util.List;
import javax.management.MBeanServer;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteWriter;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.coyote.Request;
import org.junit.Test;

public class JMXProxyServletDiffblueTest {
  /**
   * Test {@link JMXProxyServlet#init()}.
   * <p>
   * Method under test: {@link JMXProxyServlet#init()}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange
    JMXProxyServlet jmxProxyServlet = new JMXProxyServlet();

    // Act
    jmxProxyServlet.init();

    // Assert
    MBeanServer expectedMBeanServer = jmxProxyServlet.mBeanServer;
    assertSame(expectedMBeanServer, jmxProxyServlet.registry.getMBeanServer());
  }

  /**
   * Test {@link JMXProxyServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link JMXProxyServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoGet() throws ServletException, IOException {
    // Arrange
    JMXProxyServlet jmxProxyServlet = new JMXProxyServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    Response response = new Response(new org.apache.coyote.Response());
    Connector connector2 = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector2, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    // Act
    jmxProxyServlet.doGet(request, response2);

    // Assert
    Collection<String> headerNames = response2.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    PrintWriter writer = response2.getWriter();
    assertTrue(writer instanceof CoyoteWriter);
    ServletResponse response3 = response2.getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("X-Content-Type-Options", ((List<String>) headerNames).get(0));
    assertEquals("text/plain;charset=utf-8", response3.getContentType());
    assertEquals("text/plain;charset=utf-8", response2.getContentType());
    assertNull(((Response) response3).getReporter());
    assertEquals(24L, ((Response) response3).getContentWritten());
    assertEquals(Constants.CHARSET, response3.getCharacterEncoding());
    assertEquals(Constants.CHARSET, response2.getCharacterEncoding());
    assertSame(writer, response3.getWriter());
  }

  /**
   * Test {@link JMXProxyServlet#isSupported(String)}.
   * <p>
   * Method under test: {@link JMXProxyServlet#isSupported(String)}
   */
  @Test
  public void testIsSupported() {
    // Arrange, Act and Assert
    assertTrue((new JMXProxyServlet()).isSupported("Type"));
  }

  /**
   * Test new {@link JMXProxyServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JMXProxyServlet}
   */
  @Test
  public void testNewJMXProxyServlet() {
    // Arrange, Act and Assert
    assertNull((new JMXProxyServlet()).getServletConfig());
  }
}
