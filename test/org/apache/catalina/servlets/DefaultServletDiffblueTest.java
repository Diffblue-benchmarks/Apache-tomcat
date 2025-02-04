package org.apache.catalina.servlets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.WebResource;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.filters.TesterHttpServletRequest;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet.CompressionFormat;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.apache.catalina.webresources.EmptyResource;
import org.apache.catalina.webresources.ExtractingRoot;
import org.apache.coyote.Request;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.tomcat.util.http.parser.ContentRange;
import org.apache.tomcat.util.http.parser.Ranges;
import org.apache.tomcat.util.http.parser.Ranges.Entry;
import org.junit.Test;

public class DefaultServletDiffblueTest {
  /**
   * Test CompressionFormat {@link CompressionFormat#CompressionFormat(String, String)}.
   * <p>
   * Method under test: {@link CompressionFormat#CompressionFormat(String, String)}
   */
  @Test
  public void testCompressionFormatNewCompressionFormat() {
    // Arrange and Act
    CompressionFormat actualCompressionFormat = new CompressionFormat("Extension", "UTF-8");

    // Assert
    assertEquals("Extension", actualCompressionFormat.extension);
    assertEquals("UTF-8", actualCompressionFormat.encoding);
  }

  /**
   * Test {@link DefaultServlet#getRelativePath(HttpServletRequest)} with {@code request}.
   * <ul>
   *   <li>Given {@link WebdavServlet} (default constructor).</li>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#getRelativePath(HttpServletRequest)}
   */
  @Test
  public void testGetRelativePathWithRequest_givenWebdavServlet_thenReturnSlash() {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("/", webdavServlet.getRelativePath(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link DefaultServlet#getPathPrefix(HttpServletRequest)}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#getPathPrefix(HttpServletRequest)}
   */
  @Test
  public void testGetPathPrefix_thenReturnEmptyString() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("", defaultServlet.getPathPrefix(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link DefaultServlet#isReadOnly()}.
   * <p>
   * Method under test: {@link DefaultServlet#isReadOnly()}
   */
  @Test
  public void testIsReadOnly() {
    // Arrange, Act and Assert
    assertTrue((new DefaultServlet()).isReadOnly());
  }

  /**
   * Test {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoOptions() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doOptions(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoOptions2() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    RequestFacade req = new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doOptions(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Allow}.</li>
   *   <li>When {@link org.apache.coyote.Response} (default constructor) addHeader {@code Allow} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoOptions_givenAllow_whenResponseAddHeaderAllowAnd42() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Allow", "42");
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    defaultServlet.doOptions(req, resp);

    // Assert that nothing has changed
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Allow}.</li>
   *   <li>When {@link org.apache.coyote.Response} (default constructor) Header {@code Allow} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#doOptions(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoOptions_givenAllow_whenResponseHeaderAllowIs42() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Allow", "42");
    coyoteResponse.addHeader("Allow", "42");
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(coyoteResponse));

    // Act
    defaultServlet.doOptions(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}.
   * <p>
   * Method under test: {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}
   */
  @Test
  public void testDetermineMethodsAllowed() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("OPTIONS, GET, HEAD, POST", defaultServlet.determineMethodsAllowed(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return {@code OPTIONS, GET, HEAD, POST, TRACE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}
   */
  @Test
  public void testDetermineMethodsAllowed_givenTrue_thenReturnOptionsGetHeadPostTrace() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    Connector connector = new Connector();
    connector.setAllowTrace(true);

    // Act and Assert
    assertEquals("OPTIONS, GET, HEAD, POST, TRACE", defaultServlet.determineMethodsAllowed(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
  }

  /**
   * Test {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}.
   * <ul>
   *   <li>Then return {@code OPTIONS, GET, HEAD, POST}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#determineMethodsAllowed(HttpServletRequest)}
   */
  @Test
  public void testDetermineMethodsAllowed_thenReturnOptionsGetHeadPost() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("OPTIONS, GET, HEAD, POST", defaultServlet.determineMethodsAllowed(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
  }

  /**
   * Test {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testSendNotAllowed() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.sendNotAllowed(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testSendNotAllowed2() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    RequestFacade req = new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.sendNotAllowed(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#sendNotAllowed(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testSendNotAllowed3() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    defaultServlet.sendNotAllowed(req, resp);

    // Assert that nothing has changed
    assertEquals(0, resp.getStatus());
  }

  /**
   * Test {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoPut() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doPut(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoPut2() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    RequestFacade req = new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doPut(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doPut(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoPut3() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    defaultServlet.doPut(req, resp);

    // Assert that nothing has changed
    assertEquals(0, resp.getStatus());
  }

  /**
   * Test {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoDelete() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doDelete(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoDelete2() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    RequestFacade req = new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    defaultServlet.doDelete(req, resp);

    // Assert
    Collection<String> headerNames = resp.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("Allow", ((List<String>) headerNames).get(0));
    assertEquals(405, resp.getStatus());
    assertEquals(405, ((Response) response).getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
  }

  /**
   * Test {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link DefaultServlet#doDelete(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoDelete3() throws ServletException, IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act
    defaultServlet.doDelete(req, resp);

    // Assert that nothing has changed
    assertEquals(0, resp.getStatus());
  }

  /**
   * Test {@link DefaultServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfHeaders_thenReturnTrue() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(
        defaultServlet.checkIfHeaders(request, response, new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#rewriteUrl(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#rewriteUrl(String)}
   */
  @Test
  public void testRewriteUrl_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example", (new DefaultServlet()).rewriteUrl("https://example.org/example"));
  }

  /**
   * Test {@link DefaultServlet#rewriteUrl(String)}.
   * <ul>
   *   <li>When {@code "}.</li>
   *   <li>Then return {@code %22}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#rewriteUrl(String)}
   */
  @Test
  public void testRewriteUrl_whenQuotationMark_thenReturn22() {
    // Arrange, Act and Assert
    assertEquals("%22", (new DefaultServlet()).rewriteUrl("\""));
  }

  /**
   * Test {@link DefaultServlet#parseContentRange(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then return Units is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#parseContentRange(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testParseContentRange_thenReturnUnitsIsNull() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    // Act
    ContentRange actualParseContentRangeResult = defaultServlet.parseContentRange(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Assert
    assertNull(actualParseContentRangeResult.getUnits());
    assertEquals(0L, actualParseContentRangeResult.getEnd());
    assertEquals(0L, actualParseContentRangeResult.getLength());
    assertEquals(0L, actualParseContentRangeResult.getStart());
    assertFalse(actualParseContentRangeResult.isValid());
  }

  /**
   * Test {@link DefaultServlet#parseRange(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@link DefaultServlet} (default constructor) {@link DefaultServlet#FULL}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#parseRange(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testParseRange_thenReturnDefaultServletFull() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertSame(defaultServlet.FULL,
        defaultServlet.parseRange(request, response, new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#renderSize(long)}.
   * <ul>
   *   <li>When {@code 1024}.</li>
   *   <li>Then return {@code 1.0 KiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderSize(long)}
   */
  @Test
  public void testRenderSize_when1024_thenReturn10KiB() {
    // Arrange, Act and Assert
    assertEquals("1.0 KiB", (new DefaultServlet()).renderSize(1024L));
  }

  /**
   * Test {@link DefaultServlet#renderSize(long)}.
   * <ul>
   *   <li>When one hundred three.</li>
   *   <li>Then return {@code 0.1 KiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderSize(long)}
   */
  @Test
  public void testRenderSize_whenOneHundredThree_thenReturn01KiB() {
    // Arrange, Act and Assert
    assertEquals("0.1 KiB", (new DefaultServlet()).renderSize(103L));
  }

  /**
   * Test {@link DefaultServlet#renderSize(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 0.1 KiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderSize(long)}
   */
  @Test
  public void testRenderSize_whenThree_thenReturn01KiB() {
    // Arrange, Act and Assert
    assertEquals("0.1 KiB", (new DefaultServlet()).renderSize(3L));
  }

  /**
   * Test {@link DefaultServlet#renderSize(long)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code 0.0 KiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderSize(long)}
   */
  @Test
  public void testRenderSize_whenZero_thenReturn00KiB() {
    // Arrange, Act and Assert
    assertEquals("0.0 KiB", (new DefaultServlet()).renderSize(0L));
  }

  /**
   * Test {@link DefaultServlet#renderTimestamp(long)}.
   * <ul>
   *   <li>When one hundred three.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderTimestamp(long)}
   */
  @Test
  public void testRenderTimestamp_whenOneHundredThree() {
    // Arrange, Act and Assert
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", (new DefaultServlet()).renderTimestamp(103L));
  }

  /**
   * Test {@link DefaultServlet#renderTimestamp(long)}.
   * <ul>
   *   <li>When ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#renderTimestamp(long)}
   */
  @Test
  public void testRenderTimestamp_whenTen() {
    // Arrange, Act and Assert
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", (new DefaultServlet()).renderTimestamp(10L));
  }

  /**
   * Test {@link DefaultServlet#getReadme(WebResource, String)}.
   * <p>
   * Method under test: {@link DefaultServlet#getReadme(WebResource, String)}
   */
  @Test
  public void testGetReadme() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    // Act and Assert
    assertNull(defaultServlet.getReadme(new EmptyResource(new ExtractingRoot(), "Web App Path"), "UTF-8"));
  }

  /**
   * Test {@link DefaultServlet#findXsltSource(WebResource)}.
   * <p>
   * Method under test: {@link DefaultServlet#findXsltSource(WebResource)}
   */
  @Test
  public void testFindXsltSource() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    // Act and Assert
    assertNull(defaultServlet.findXsltSource(new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Entry)}.
   * <ul>
   *   <li>When {@link Connector#Connector(ProtocolHandler)} with protocolHandler is {@link AjpNio2Protocol} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Ranges.Entry)}
   */
  @Test
  public void testCheckSendfile_whenConnectorWithProtocolHandlerIsAjpNio2Protocol() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector(new AjpNio2Protocol());
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    EmptyResource resource = new EmptyResource(new ExtractingRoot(), "Web App Path");

    // Act and Assert
    assertFalse(defaultServlet.checkSendfile(request, response, resource, Long.MAX_VALUE, new Entry(1L, 1L)));
  }

  /**
   * Test {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Entry)}.
   * <ul>
   *   <li>When {@link org.apache.catalina.connector.Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Ranges.Entry)}
   */
  @Test
  public void testCheckSendfile_whenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    EmptyResource resource = new EmptyResource(new ExtractingRoot(), "Web App Path");

    // Act and Assert
    assertFalse(defaultServlet.checkSendfile(request, response, resource, Long.MAX_VALUE, new Entry(1L, 1L)));
  }

  /**
   * Test {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Entry)}.
   * <ul>
   *   <li>When {@link org.apache.catalina.connector.Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Ranges.Entry)}
   */
  @Test
  public void testCheckSendfile_whenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest2() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    RequestFacade request = new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    EmptyResource resource = new EmptyResource(new ExtractingRoot(), "Web App Path");

    // Act and Assert
    assertFalse(defaultServlet.checkSendfile(request, response, resource, Long.MAX_VALUE, new Entry(1L, 1L)));
  }

  /**
   * Test {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Entry)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkSendfile(HttpServletRequest, HttpServletResponse, WebResource, long, Ranges.Entry)}
   */
  @Test
  public void testCheckSendfile_whenThree_thenReturnFalse() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    EmptyResource resource = new EmptyResource(new ExtractingRoot(), "Web App Path");

    // Act and Assert
    assertFalse(defaultServlet.checkSendfile(request, response, resource, 3L, new Entry(1L, 1L)));
  }

  /**
   * Test {@link DefaultServlet#checkIfMatch(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <p>
   * Method under test: {@link DefaultServlet#checkIfMatch(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfMatch() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    boolean actualCheckIfMatchResult = defaultServlet.checkIfMatch(request, response,
        new EmptyResource(new ExtractingRoot(), "Web App Path"));

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(412, response3.getStatus());
    assertEquals(412, response.getStatus());
    assertEquals(412, ((Response) response2).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(412, coyoteResponse.getStatus());
    assertFalse(actualCheckIfMatchResult);
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <p>
   * Method under test: {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfModifiedSince() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(defaultServlet.checkIfModifiedSince(request, response,
        new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Given {@code GET}.</li>
   *   <li>When {@link TesterHttpServletRequest} (default constructor) Method is {@code GET}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfModifiedSince_givenGet_whenTesterHttpServletRequestMethodIsGet() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    TesterHttpServletRequest request = new TesterHttpServletRequest();
    request.setMethod("GET");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(defaultServlet.checkIfModifiedSince(request, response,
        new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Given {@code HEAD}.</li>
   *   <li>When {@link TesterHttpServletRequest} (default constructor) Method is {@code HEAD}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfModifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfModifiedSince_givenHead_whenTesterHttpServletRequestMethodIsHead() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    TesterHttpServletRequest request = new TesterHttpServletRequest();
    request.setMethod("HEAD");
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(defaultServlet.checkIfModifiedSince(request, response,
        new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkIfNoneMatch(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfNoneMatch(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfNoneMatch_thenReturnTrue() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(
        defaultServlet.checkIfNoneMatch(request, response, new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkIfUnmodifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfUnmodifiedSince(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfUnmodifiedSince_thenReturnTrue() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(defaultServlet.checkIfUnmodifiedSince(request, response,
        new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#checkIfRange(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#checkIfRange(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfRange_thenReturnTrue() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(defaultServlet.checkIfRange(request, response, new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#generateETag(WebResource)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#generateETag(WebResource)}
   */
  @Test
  public void testGenerateETag_thenReturnNull() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();

    // Act and Assert
    assertNull(defaultServlet.generateETag(new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link DefaultServlet#copy(InputStream, ServletOutputStream)} with {@code is}, {@code ostream}.
   * <p>
   * Method under test: {@link DefaultServlet#copy(InputStream, ServletOutputStream)}
   */
  @Test
  public void testCopyWithIsOstream() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act
    defaultServlet.copy(is, ostream);

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copy(InputStream, PrintWriter, String)} with {@code is}, {@code writer}, {@code encoding}.
   * <p>
   * Method under test: {@link DefaultServlet#copy(InputStream, PrintWriter, String)}
   */
  @Test
  public void testCopyWithIsWriterEncoding() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    defaultServlet.copy(is, new PrintWriter(new StringWriter()), "UTF-8");

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
  }

  /**
   * Test {@link DefaultServlet#copy(InputStream, PrintWriter, String)} with {@code is}, {@code writer}, {@code encoding}.
   * <p>
   * Method under test: {@link DefaultServlet#copy(InputStream, PrintWriter, String)}
   */
  @Test
  public void testCopyWithIsWriterEncoding2() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    defaultServlet.copy(is, new PrintWriter(new StringWriter()), null);

    // Assert
    assertEquals(-1, is.read(new byte[]{}));
  }

  /**
   * Test {@link DefaultServlet#copy(WebResource, long, ServletOutputStream, Ranges, String)} with {@code resource}, {@code length}, {@code ostream}, {@code ranges}, {@code contentType}.
   * <p>
   * Method under test: {@link DefaultServlet#copy(WebResource, long, ServletOutputStream, Ranges, String)}
   */
  @Test
  public void testCopyWithResourceLengthOstreamRangesContentType() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    EmptyResource resource = new EmptyResource(new ExtractingRoot(), "Web App Path");

    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act
    defaultServlet.copy(resource, 3L, ostream, DefaultServlet.FULL, "text/plain");

    // Assert
    byte[] expectedToByteArrayResult = "\r\n--CATALINA_MIME_BOUNDARY--".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)} with {@code istream}, {@code ostream}, {@code start}, {@code end}.
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)}
   */
  @Test
  public void testCopyRangeWithIstreamOstreamStartEnd() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream istream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act and Assert
    assertNull(defaultServlet.copyRange(istream, ostream, 1L, 1L));
    assertEquals(-1, istream.read(new byte[]{}));
    assertArrayEquals(new byte[]{'X'}, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)} with {@code istream}, {@code ostream}, {@code start}, {@code end}.
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)}
   */
  @Test
  public void testCopyRangeWithIstreamOstreamStartEnd2() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream istream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act and Assert
    assertNull(defaultServlet.copyRange(istream, ostream, 7L, 1L));
    byte[] byteArray = new byte[1];
    assertEquals(1, istream.read(byteArray));
    assertArrayEquals(new byte[]{}, ostream.toByteArray());
    assertArrayEquals(new byte[]{'X'}, byteArray);
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)} with {@code istream}, {@code ostream}, {@code start}, {@code end}.
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)}
   */
  @Test
  public void testCopyRangeWithIstreamOstreamStartEnd3() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream istream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act and Assert
    assertNull(defaultServlet.copyRange(istream, ostream, 1L, 10L));
    assertEquals(-1, istream.read(new byte[]{}));
    byte[] expectedToByteArrayResult = "XAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)} with {@code istream}, {@code ostream}, {@code start}, {@code end}.
   * <ul>
   *   <li>Then return LocalizedMessage is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)}
   */
  @Test
  public void testCopyRangeWithIstreamOstreamStartEnd_thenReturnLocalizedMessageIsAString() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream istream = new ByteArrayInputStream(new byte[]{});

    // Act
    IOException actualCopyRangeResult = defaultServlet.copyRange(istream, new ByteArrayServletOutputStream(), 1L, 1L);

    // Assert
    assertEquals("Read failed because only [0] bytes were available but needed to skip [1] bytes to reach the start of"
        + " the requested range", actualCopyRangeResult.getLocalizedMessage());
    assertEquals("Read failed because only [0] bytes were available but needed to skip [1] bytes to reach the start of"
        + " the requested range", actualCopyRangeResult.getMessage());
    assertNull(actualCopyRangeResult.getCause());
    assertEquals(-1, istream.read(new byte[]{}));
    assertEquals(0, actualCopyRangeResult.getSuppressed().length);
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)} with {@code istream}, {@code ostream}, {@code start}, {@code end}.
   * <ul>
   *   <li>Then return LocalizedMessage is {@code Stream Closed}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream, long, long)}
   */
  @Test
  public void testCopyRangeWithIstreamOstreamStartEnd_thenReturnLocalizedMessageIsStreamClosed() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    FileInputStream istream = new FileInputStream(new FileDescriptor());

    // Act
    IOException actualCopyRangeResult = defaultServlet.copyRange(istream, new ByteArrayServletOutputStream(), 1L, 1L);

    // Assert
    assertEquals("Stream Closed", actualCopyRangeResult.getLocalizedMessage());
    assertEquals("Stream Closed", actualCopyRangeResult.getMessage());
    assertNull(actualCopyRangeResult.getCause());
    assertEquals(0, actualCopyRangeResult.getSuppressed().length);
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream)} with {@code istream}, {@code ostream}.
   * <ul>
   *   <li>Then return LocalizedMessage is {@code Stream Closed}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream)}
   */
  @Test
  public void testCopyRangeWithIstreamOstream_thenReturnLocalizedMessageIsStreamClosed() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    FileInputStream istream = new FileInputStream(new FileDescriptor());
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act
    IOException actualCopyRangeResult = defaultServlet.copyRange(istream, ostream);

    // Assert
    assertEquals("Stream Closed", actualCopyRangeResult.getLocalizedMessage());
    assertEquals("Stream Closed", actualCopyRangeResult.getMessage());
    assertNull(actualCopyRangeResult.getCause());
    assertEquals(0, actualCopyRangeResult.getSuppressed().length);
    assertArrayEquals(new byte[]{}, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copyRange(InputStream, ServletOutputStream)} with {@code istream}, {@code ostream}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(InputStream, ServletOutputStream)}
   */
  @Test
  public void testCopyRangeWithIstreamOstream_thenReturnNull() throws IOException {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    ByteArrayInputStream istream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayServletOutputStream ostream = new ByteArrayServletOutputStream();

    // Act and Assert
    assertNull(defaultServlet.copyRange(istream, ostream));
    assertEquals(-1, istream.read(new byte[]{}));
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, ostream.toByteArray());
  }

  /**
   * Test {@link DefaultServlet#copyRange(Reader, PrintWriter)} with {@code reader}, {@code writer}.
   * <ul>
   *   <li>Then return LocalizedMessage is {@code Stream Closed}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(Reader, PrintWriter)}
   */
  @Test
  public void testCopyRangeWithReaderWriter_thenReturnLocalizedMessageIsStreamClosed() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    FileReader reader = new FileReader(new FileDescriptor());

    // Act
    IOException actualCopyRangeResult = defaultServlet.copyRange(reader, new PrintWriter(new StringWriter()));

    // Assert
    assertEquals("Stream Closed", actualCopyRangeResult.getLocalizedMessage());
    assertEquals("Stream Closed", actualCopyRangeResult.getMessage());
    assertNull(actualCopyRangeResult.getCause());
    assertEquals(0, actualCopyRangeResult.getSuppressed().length);
  }

  /**
   * Test {@link DefaultServlet#copyRange(Reader, PrintWriter)} with {@code reader}, {@code writer}.
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultServlet#copyRange(Reader, PrintWriter)}
   */
  @Test
  public void testCopyRangeWithReaderWriter_whenStringReaderWithFoo_thenReturnNull() {
    // Arrange
    DefaultServlet defaultServlet = new DefaultServlet();
    StringReader reader = new StringReader("foo");

    // Act and Assert
    assertNull(defaultServlet.copyRange(reader, new PrintWriter(new StringWriter())));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link DefaultServlet}
   *   <li>{@link DefaultServlet#destroy()}
   *   <li>{@link DefaultServlet#isListings()}
   *   <li>{@link DefaultServlet#isRangeRequestsSupported()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    DefaultServlet actualDefaultServlet = new DefaultServlet();
    actualDefaultServlet.destroy();
    boolean actualIsListingsResult = actualDefaultServlet.isListings();
    boolean actualIsRangeRequestsSupportedResult = actualDefaultServlet.isRangeRequestsSupported();

    // Assert
    assertNull(actualDefaultServlet.getServletConfig());
    assertFalse(actualIsListingsResult);
    assertTrue(actualIsRangeRequestsSupportedResult);
  }
}
