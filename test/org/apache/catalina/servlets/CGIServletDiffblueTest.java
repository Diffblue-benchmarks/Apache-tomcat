package org.apache.catalina.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.filters.TesterHttpServletRequest;
import org.apache.catalina.servlets.CGIServlet.CGIRunner;
import org.apache.catalina.servlets.CGIServlet.HTTPHeaderInputStream;
import org.apache.coyote.Request;
import org.junit.Test;

public class CGIServletDiffblueTest {
  /**
   * Test CGIRunner {@link CGIRunner#isReady()}.
   * <p>
   * Method under test: {@link CGIRunner#isReady()}
   */
  @Test
  public void testCGIRunnerIsReady() {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    HashMap<String, String> stringStringMap = new HashMap<>();
    File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertFalse((cgiServlet.new CGIRunner("foo", stringStringMap, toFileResult, new ArrayList<>())).isReady());
  }

  /**
   * Test CGIRunner {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}
   */
  @Test
  public void testCGIRunnerNewCGIRunner_given42_whenArrayListAdd42() {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    HashMap<String, String> stringStringMap = new HashMap<>();
    File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");

    // Act and Assert
    assertFalse((cgiServlet.new CGIRunner("foo", stringStringMap, toFileResult, stringList)).isReady());
  }

  /**
   * Test CGIRunner {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}
   */
  @Test
  public void testCGIRunnerNewCGIRunner_givenFoo_whenArrayListAddFoo() {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    HashMap<String, String> stringStringMap = new HashMap<>();
    File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    // Act and Assert
    assertFalse((cgiServlet.new CGIRunner("foo", stringStringMap, toFileResult, stringList)).isReady());
  }

  /**
   * Test CGIRunner {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}
   */
  @Test
  public void testCGIRunnerNewCGIRunner_whenArrayList() {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    HashMap<String, String> stringStringMap = new HashMap<>();
    File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertFalse((cgiServlet.new CGIRunner("foo", stringStringMap, toFileResult, new ArrayList<>())).isReady());
  }

  /**
   * Test CGIRunner {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIRunner#CGIRunner(CGIServlet, String, Map, File, ArrayList)}
   */
  @Test
  public void testCGIRunnerNewCGIRunner_whenNull() {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    HashMap<String, String> stringStringMap = new HashMap<>();
    File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertFalse((cgiServlet.new CGIRunner(null, stringStringMap, toFileResult, new ArrayList<>())).isReady());
  }

  /**
   * Test HTTPHeaderInputStream {@link HTTPHeaderInputStream#read()}.
   * <ul>
   *   <li>Then return sixty-five.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTTPHeaderInputStream#read()}
   */
  @Test
  public void testHTTPHeaderInputStreamRead_thenReturnSixtyFive() throws IOException {
    // Arrange, Act and Assert
    assertEquals(65, (new HTTPHeaderInputStream(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))).read());
  }

  /**
   * Test HTTPHeaderInputStream {@link HTTPHeaderInputStream#read()}.
   * <ul>
   *   <li>Then return ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTTPHeaderInputStream#read()}
   */
  @Test
  public void testHTTPHeaderInputStreamRead_thenReturnTen() throws IOException {
    // Arrange, Act and Assert
    assertEquals(10, (new HTTPHeaderInputStream(new ByteArrayInputStream("\nXAXAXAX".getBytes("UTF-8")))).read());
  }

  /**
   * Test HTTPHeaderInputStream {@link HTTPHeaderInputStream#read()}.
   * <ul>
   *   <li>Then return thirteen.</li>
   * </ul>
   * <p>
   * Method under test: {@link HTTPHeaderInputStream#read()}
   */
  @Test
  public void testHTTPHeaderInputStreamRead_thenReturnThirteen() throws IOException {
    // Arrange, Act and Assert
    assertEquals(13, (new HTTPHeaderInputStream(new ByteArrayInputStream("\rXAXAXAX".getBytes("UTF-8")))).read());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code cgiMethods}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenCgiMethods() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("cgiMethods", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code enableCmdLineArguments}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenEnableCmdLineArguments() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("enableCmdLineArguments", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code envHttpHeaders}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenEnvHttpHeaders() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("envHttpHeaders", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code environment-variable-}.</li>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenEnvironmentVariable_thenThrowServletException() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("environment-variable-", "42");

    // Act and Assert
    assertThrows(ServletException.class, () -> cgiServlet.init(config));
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code executable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenExecutable() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("executable", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code executable-arg-1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenExecutableArg1() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("executable-arg-1", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>Given {@code parameterEncoding}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_givenParameterEncoding() throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("parameterEncoding", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor) addInitParameter {@code cgiPathPrefix} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_whenStandardWrapperAddInitParameterCgiPathPrefixAnd42()
      throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("cgiPathPrefix", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor) addInitParameter {@code stderrTimeout} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_whenStandardWrapperAddInitParameterStderrTimeoutAnd42()
      throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    StandardWrapper config = new StandardWrapper();
    config.addInitParameter("stderrTimeout", "42");

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#init(ServletConfig)} with {@code ServletConfig}.
   * <ul>
   *   <li>When {@link StandardWrapper} (default constructor).</li>
   *   <li>Then {@link CGIServlet} (default constructor) ServletContext is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CGIServlet#init(ServletConfig)}
   */
  @Test
  public void testInitWithServletConfig_whenStandardWrapper_thenCGIServletServletContextIsNull()
      throws ServletException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    StandardWrapper config = new StandardWrapper();

    // Act
    cgiServlet.init(config);

    // Assert
    assertNull(cgiServlet.getServletContext());
    assertNull(cgiServlet.getServletName());
    assertSame(config, cgiServlet.getServletConfig());
  }

  /**
   * Test {@link CGIServlet#service(HttpServletRequest, HttpServletResponse)} with {@code HttpServletRequest}, {@code HttpServletResponse}.
   * <p>
   * Method under test: {@link CGIServlet#service(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testServiceWithHttpServletRequestHttpServletResponse() throws ServletException, IOException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper res = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    cgiServlet.service((HttpServletRequest) req, res);

    // Assert
    Collection<String> headerNames = res.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response = res.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(405, response2.getStatus());
    assertEquals(405, res.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(405, coyoteResponse.getStatus());
    assertTrue(headerNames.isEmpty());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link CGIServlet#service(HttpServletRequest, HttpServletResponse)} with {@code HttpServletRequest}, {@code HttpServletResponse}.
   * <p>
   * Method under test: {@link CGIServlet#service(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testServiceWithHttpServletRequestHttpServletResponse2() throws ServletException, IOException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();

    TesterHttpServletRequest req = new TesterHttpServletRequest();
    req.setMethod("OPTIONS");
    HttpServletResponseWrapper res = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    cgiServlet.service(req, (HttpServletResponse) res);

    // Assert
    Collection<String> headerNames = res.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = res.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("allow", ((List<String>) headerNames).get(0));
    assertEquals(200, res.getStatus());
    assertEquals(200, ((Response) response).getStatus());
    assertFalse(((Response) response).isAppCommitted());
    assertFalse(((Response) response).isError());
    assertFalse(((Response) response).isErrorReportRequired());
    assertFalse(((Response) response).isSuspended());
  }

  /**
   * Test {@link CGIServlet#doOptions(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link CGIServlet#doOptions(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoOptions() throws ServletException, IOException {
    // Arrange
    CGIServlet cgiServlet = new CGIServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper res = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    cgiServlet.doOptions(req, res);

    // Assert
    Collection<String> headerNames = res.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = res.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("allow", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test new {@link CGIServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CGIServlet}
   */
  @Test
  public void testNewCGIServlet() {
    // Arrange and Act
    CGIServlet actualCgiServlet = new CGIServlet();

    // Assert
    assertEquals("", actualCgiServlet.getServletInfo());
    assertNull(actualCgiServlet.getServletConfig());
  }
}
