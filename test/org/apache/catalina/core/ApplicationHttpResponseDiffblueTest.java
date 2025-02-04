package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.coyote.Request;
import org.junit.Test;

public class ApplicationHttpResponseDiffblueTest {
  /**
   * Test {@link ApplicationHttpResponse#ApplicationHttpResponse(HttpServletResponse, boolean)}.
   * <ul>
   *   <li>Then Response return {@link HttpServletResponseWrapper}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationHttpResponse#ApplicationHttpResponse(HttpServletResponse, boolean)}
   */
  @Test
  public void testNewApplicationHttpResponse_thenResponseReturnHttpServletResponseWrapper() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    ApplicationHttpResponse actualApplicationHttpResponse = new ApplicationHttpResponse(response, true);

    // Assert
    ServletResponse response2 = actualApplicationHttpResponse.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = actualApplicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(actualApplicationHttpResponse.getOutputStream() instanceof CoyoteOutputStream);
    assertNull(actualApplicationHttpResponse.getContentType());
    assertNull(actualApplicationHttpResponse.getTrailerFields());
    assertEquals(200, actualApplicationHttpResponse.getStatus());
    assertEquals(8192, actualApplicationHttpResponse.getBufferSize());
    assertFalse(actualApplicationHttpResponse.isCommitted());
    assertTrue(headerNames.isEmpty());
    assertTrue(actualApplicationHttpResponse.included);
    assertSame(response, response2);
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLength(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLength(int)}
   */
  @Test
  public void testSetContentLength() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setContentLength(3);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(-1, ((Response) response2).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLength(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLength(int)}
   */
  @Test
  public void testSetContentLength2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLength(3);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(3, ((Response) response2).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLength(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLength(int)}
   */
  @Test
  public void testSetContentLength3() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))),
        true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLength(3);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals(3, ((Response) response3).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response3).getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLength(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLength(int)}
   */
  @Test
  public void testSetContentLength4() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLength(3);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    ServletResponse response2 = ((ApplicationHttpResponse) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(-1, ((Response) response3).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response3).getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setContentLengthLong(3L);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(-1, ((Response) response2).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLengthLong(3L);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(3, ((Response) response2).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong3() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))),
        true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLengthLong(3L);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals(3, ((Response) response3).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response3).getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong4() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentLengthLong(3L);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    ServletResponse response2 = ((ApplicationHttpResponse) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(-1, ((Response) response3).getContentLength());
    org.apache.coyote.Response coyoteResponse = ((Response) response3).getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertTrue(((Response) response2).getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("application/json", response.getContentType());
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response3.getContentType());
    assertEquals("application/json", applicationHttpResponse.getContentType());
    assertEquals("application/json", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType3() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))),
        true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    HttpServletResponse response4 = ((Response) response3).getResponse();
    assertTrue(response4 instanceof ResponseFacade);
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response3.getContentType());
    assertEquals("application/json", response4.getContentType());
    assertEquals("application/json", ((Response) response3).getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType4() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(new TesterHttpServletResponse(),
        true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert that nothing has changed
    assertNull(applicationHttpResponse.getContentType());
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType5() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new ApplicationHttpResponse(
            new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true)),
        true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    assertTrue(((HttpServletResponseWrapper) response).getResponse() instanceof ApplicationHttpResponse);
  }

  /**
   * Test {@link ApplicationHttpResponse#setContentType(String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setContentType(String)}
   */
  @Test
  public void testSetContentType6() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setContentType("application/json");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    ServletResponse response2 = ((ApplicationHttpResponse) response).getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertTrue(((Response) response3).getResponse() instanceof ResponseFacade);
    assertTrue(response instanceof ApplicationHttpResponse);
  }

  /**
   * Test {@link ApplicationHttpResponse#setLocale(Locale)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setLocale(Locale)}
   */
  @Test
  public void testSetLocale() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setLocale(Locale.getDefault());

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    assertTrue(((HttpServletResponseWrapper) response).getResponse() instanceof Response);
  }

  /**
   * Test {@link ApplicationHttpResponse#setLocale(Locale)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setLocale(Locale)}
   */
  @Test
  public void testSetLocale2() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));

    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(response), true);
    applicationHttpResponse.setIncluded(false);

    // Act
    applicationHttpResponse.setLocale(Locale.getDefault());

    // Assert
    ServletResponse response2 = applicationHttpResponse.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("en", ((Response) response3).getCoyoteResponse().getContentLanguage());
  }

  /**
   * Test {@link ApplicationHttpResponse#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.addDateHeader("https://example.org/example", 42L);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.addDateHeader("https://example.org/example", 42L);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addHeader(String, String)}
   */
  @Test
  public void testAddHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.addHeader("https://example.org/example", "https://example.org/example");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addHeader(String, String)}
   */
  @Test
  public void testAddHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.addHeader("https://example.org/example", "https://example.org/example");

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.addIntHeader("https://example.org/example", 42);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.addIntHeader("https://example.org/example", 42);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendError(1);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isError());
    assertFalse(((Response) response2).isErrorReportRequired());
    assertFalse(((Response) response2).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc2() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.sendError(1);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(1, applicationHttpResponse.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendError(1, "https://example.org/example");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isError());
    assertFalse(((Response) response2).isErrorReportRequired());
    assertFalse(((Response) response2).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg2() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.sendError(1, "https://example.org/example");

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((Response) response2).getMessage());
    assertEquals(1, applicationHttpResponse.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String)} with {@code location}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String)}
   */
  @Test
  public void testSendRedirectWithLocation() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertEquals(200, ((Response) response2).getCoyoteResponse().getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isSuspended());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String)} with {@code location}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String)}
   */
  @Test
  public void testSendRedirectWithLocation2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) response2);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example");

    // Assert
    ServletResponse response3 = applicationHttpResponse.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    assertEquals(302, response5.getStatus());
    assertEquals(302, applicationHttpResponse.getStatus());
    assertEquals(302, ((HttpServletResponseWrapper) response3).getStatus());
    assertEquals(302, ((Response) response4).getStatus());
    assertEquals(302, ((Response) response4).getCoyoteResponse().getStatus());
    assertTrue(((Response) response4).isAppCommitted());
    assertTrue(((Response) response4).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String)} with {@code location}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String)}
   */
  @Test
  public void testSendRedirectWithLocation3() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new HttpServletResponseWrapper(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true)));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String)} with {@code location}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String)}
   */
  @Test
  public void testSendRedirectWithLocation4() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example");

    // Assert that nothing has changed
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((ApplicationHttpResponse) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, boolean)} with {@code location}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationClearBuffer() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", true);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertEquals(200, ((Response) response2).getCoyoteResponse().getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isSuspended());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, boolean)} with {@code location}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationClearBuffer2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) response2);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", true);

    // Assert
    ServletResponse response3 = applicationHttpResponse.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    assertEquals(302, response5.getStatus());
    assertEquals(302, applicationHttpResponse.getStatus());
    assertEquals(302, ((HttpServletResponseWrapper) response3).getStatus());
    assertEquals(302, ((Response) response4).getStatus());
    assertEquals(302, ((Response) response4).getCoyoteResponse().getStatus());
    assertTrue(((Response) response4).isAppCommitted());
    assertTrue(((Response) response4).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, boolean)} with {@code location}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationClearBuffer3() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new HttpServletResponseWrapper(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true)));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", true);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, boolean)} with {@code location}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationClearBuffer4() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", true);

    // Assert that nothing has changed
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((ApplicationHttpResponse) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int)} with {@code location}, {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int)}
   */
  @Test
  public void testSendRedirectWithLocationSc() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertEquals(200, ((Response) response2).getCoyoteResponse().getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isSuspended());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int)} with {@code location}, {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int)}
   */
  @Test
  public void testSendRedirectWithLocationSc2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) response2);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1);

    // Assert
    ServletResponse response3 = applicationHttpResponse.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    assertEquals(1, response5.getStatus());
    assertEquals(1, applicationHttpResponse.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response3).getStatus());
    assertEquals(1, ((Response) response4).getStatus());
    assertEquals(1, ((Response) response4).getCoyoteResponse().getStatus());
    assertTrue(((Response) response4).isAppCommitted());
    assertTrue(((Response) response4).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int)} with {@code location}, {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int)}
   */
  @Test
  public void testSendRedirectWithLocationSc3() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new HttpServletResponseWrapper(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true)));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int)} with {@code location}, {@code sc}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int)}
   */
  @Test
  public void testSendRedirectWithLocationSc4() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1);

    // Assert that nothing has changed
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((ApplicationHttpResponse) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)} with {@code location}, {@code sc}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationScClearBuffer() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1, true);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertEquals(200, ((Response) response2).getCoyoteResponse().getStatus());
    assertFalse(((Response) response2).isAppCommitted());
    assertFalse(((Response) response2).isSuspended());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)} with {@code location}, {@code sc}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationScClearBuffer2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) response2);

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1, true);

    // Assert
    ServletResponse response3 = applicationHttpResponse.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertEquals("Location", ((List<String>) headerNames).get(0));
    assertEquals(1, response5.getStatus());
    assertEquals(1, applicationHttpResponse.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response3).getStatus());
    assertEquals(1, ((Response) response4).getStatus());
    assertEquals(1, ((Response) response4).getCoyoteResponse().getStatus());
    assertTrue(((Response) response4).isAppCommitted());
    assertTrue(((Response) response4).isSuspended());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)} with {@code location}, {@code sc}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationScClearBuffer3() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new HttpServletResponseWrapper(new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true)));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1, true);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)} with {@code location}, {@code sc}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationScClearBuffer4() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true));

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1, true);

    // Assert that nothing has changed
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof ApplicationHttpResponse);
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((ApplicationHttpResponse) response).getStatus());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)} with {@code location}, {@code sc}, {@code clearBuffer}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#sendRedirect(String, int, boolean)}
   */
  @Test
  public void testSendRedirectWithLocationScClearBuffer5() throws IOException {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse.setResponse((ServletResponse) new TesterHttpServletResponse());

    // Act
    applicationHttpResponse.sendRedirect("https://example.org/example", 1, true);

    // Assert that nothing has changed
    assertEquals(0, applicationHttpResponse.getStatus());
  }

  /**
   * Test {@link ApplicationHttpResponse#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setDateHeader("https://example.org/example", 42L);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.setDateHeader("https://example.org/example", 42L);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setHeader(String, String)}
   */
  @Test
  public void testSetHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setHeader("https://example.org/example", "https://example.org/example");

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setHeader(String, String)}
   */
  @Test
  public void testSetHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.setHeader("https://example.org/example", "https://example.org/example");

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setIntHeader("https://example.org/example", 42);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals(0, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ApplicationHttpResponse#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.setIntHeader("https://example.org/example", 42);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = applicationHttpResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ApplicationHttpResponse#setStatus(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setStatus(int)}
   */
  @Test
  public void testSetStatus() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);

    // Act
    applicationHttpResponse.setStatus(1);

    // Assert that nothing has changed
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, applicationHttpResponse.getStatus());
    assertEquals(200, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(200, ((Response) response2).getStatus());
    assertEquals(200, ((Response) response2).getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ApplicationHttpResponse#setStatus(int)}.
   * <p>
   * Method under test: {@link ApplicationHttpResponse#setStatus(int)}
   */
  @Test
  public void testSetStatus2() {
    // Arrange
    ApplicationHttpResponse applicationHttpResponse = new ApplicationHttpResponse(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), true);
    applicationHttpResponse.setIncluded(false);
    applicationHttpResponse
        .setResponse((ServletResponse) new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    applicationHttpResponse.setStatus(1);

    // Assert
    ServletResponse response = applicationHttpResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(1, response3.getStatus());
    assertEquals(1, applicationHttpResponse.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    assertEquals(1, ((Response) response2).getCoyoteResponse().getStatus());
  }
}
