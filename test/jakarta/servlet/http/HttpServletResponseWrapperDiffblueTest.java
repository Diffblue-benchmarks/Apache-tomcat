package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.junit.Test;

public class HttpServletResponseWrapperDiffblueTest {
  /**
   * Test {@link HttpServletResponseWrapper#HttpServletResponseWrapper(HttpServletResponse)}.
   * <ul>
   *   <li>Then HeaderNames return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#HttpServletResponseWrapper(HttpServletResponse)}
   */
  @Test
  public void testNewHttpServletResponseWrapper_thenHeaderNamesReturnList() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    HttpServletResponseWrapper actualHttpServletResponseWrapper = new HttpServletResponseWrapper(response);

    // Assert
    Collection<String> headerNames = actualHttpServletResponseWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(actualHttpServletResponseWrapper.getOutputStream() instanceof CoyoteOutputStream);
    ServletResponse response2 = actualHttpServletResponseWrapper.getResponse();
    assertTrue(response2 instanceof Response);
    assertNull(actualHttpServletResponseWrapper.getContentType());
    assertNull(actualHttpServletResponseWrapper.getTrailerFields());
    assertEquals(8192, actualHttpServletResponseWrapper.getBufferSize());
    assertFalse(actualHttpServletResponseWrapper.isCommitted());
    assertTrue(headerNames.isEmpty());
    assertEquals(HttpServletResponse.SC_OK, actualHttpServletResponseWrapper.getStatus());
    assertSame(response, response2);
  }

  /**
   * Test {@link HttpServletResponseWrapper#containsHeader(String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#containsHeader(String)}
   */
  @Test
  public void testContainsHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act and Assert
    assertFalse(httpServletResponseWrapper.containsHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#containsHeader(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())))
        .containsHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#containsHeader(String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_thenReturnTrue() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper.addDateHeader("https://example.org/example", 5L);

    // Act and Assert
    assertTrue(httpServletResponseWrapper.containsHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#encodeURL(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#encodeURL(String)}
   */
  @Test
  public void testEncodeURL_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        (new HttpServletResponseWrapper(new TesterHttpServletResponse())).encodeURL("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#encodeRedirectURL(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#encodeRedirectURL(String)}
   */
  @Test
  public void testEncodeRedirectURL_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new HttpServletResponseWrapper(new TesterHttpServletResponse()))
        .encodeRedirectURL("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc() throws IOException {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.sendError(1);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link HttpServletResponseWrapper#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc2() throws IOException {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.sendError(1);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(1, response3.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link HttpServletResponseWrapper#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg() throws IOException {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.sendError(1, "https://example.org/example");

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("https://example.org/example", ((Response) response).getMessage());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals("https://example.org/example", coyoteResponse.getMessage());
    assertEquals(1, response2.getStatus());
    assertEquals(1, ((Response) response).getStatus());
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link HttpServletResponseWrapper#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg2() throws IOException {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.sendError(1, "https://example.org/example");

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((Response) response2).getMessage());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    assertTrue(((Response) response2).isAppCommitted());
    assertTrue(((Response) response2).isError());
    assertTrue(((Response) response2).isErrorReportRequired());
    assertTrue(((Response) response2).isSuspended());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.setDateHeader("https://example.org/example", 1L);

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.setDateHeader("https://example.org/example", 1L);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.addDateHeader("https://example.org/example", 1L);

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.addDateHeader("https://example.org/example", 1L);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setHeader(String, String)}
   */
  @Test
  public void testSetHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.setHeader("https://example.org/example", "https://example.org/example");

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setHeader(String, String)}
   */
  @Test
  public void testSetHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.setHeader("https://example.org/example", "https://example.org/example");

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addHeader(String, String)}
   */
  @Test
  public void testAddHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.addHeader("https://example.org/example", "https://example.org/example");

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addHeader(String, String)}
   */
  @Test
  public void testAddHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.addHeader("https://example.org/example", "https://example.org/example");

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.setIntHeader("https://example.org/example", 42);

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.setIntHeader("https://example.org/example", 42);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.addIntHeader("https://example.org/example", 42);

    // Assert
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.addIntHeader("https://example.org/example", 42);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = httpServletResponseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertEquals("https://example.org/example", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response2).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setStatus(int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setStatus(int)}
   */
  @Test
  public void testSetStatus() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    httpServletResponseWrapper.setStatus(1);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, httpServletResponseWrapper.getStatus());
    assertEquals(1, ((Response) response).getStatus());
    assertEquals(1, ((Response) response).getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link HttpServletResponseWrapper#setStatus(int)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#setStatus(int)}
   */
  @Test
  public void testSetStatus2() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    httpServletResponseWrapper.setStatus(1);

    // Assert
    ServletResponse response = httpServletResponseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(1, response3.getStatus());
    assertEquals(1, ((HttpServletResponseWrapper) response).getStatus());
    assertEquals(1, ((Response) response2).getStatus());
    assertEquals(1, ((Response) response2).getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getStatus()}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getStatus()}
   */
  @Test
  public void testGetStatus() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act and Assert
    assertEquals(HttpServletResponse.SC_OK, httpServletResponseWrapper.getStatus());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getStatus()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@link HttpServletResponse#SC_OK}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getStatus()}
   */
  @Test
  public void testGetStatus_givenResponseWithCoyoteResponseIsResponse_thenReturnSc_ok() {
    // Arrange, Act and Assert
    assertEquals(HttpServletResponse.SC_OK,
        (new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))).getStatus());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeader(String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeader(String)}
   */
  @Test
  public void testGetHeader() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act and Assert
    assertNull(httpServletResponseWrapper.getHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeader(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())))
        .getHeader("https://example.org/example"));
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeaders(String)}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeaders(String)}
   */
  @Test
  public void testGetHeaders() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    Collection<String> actualHeaders = httpServletResponseWrapper.getHeaders("https://example.org/example");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseWithCoyoteResponseIsResponse_thenReturnSet() {
    // Arrange and Act
    Collection<String> actualHeaders = (new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())))
        .getHeaders("https://example.org/example");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeaderNames()}.
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames() {
    // Arrange
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    httpServletResponseWrapper
        .setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())));

    // Act
    Collection<String> actualHeaderNames = httpServletResponseWrapper.getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertTrue(actualHeaderNames.isEmpty());
  }

  /**
   * Test {@link HttpServletResponseWrapper#getHeaderNames()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpServletResponseWrapper#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_givenResponseWithCoyoteResponseIsResponse_thenReturnList() {
    // Arrange and Act
    Collection<String> actualHeaderNames = (new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()))).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertTrue(actualHeaderNames.isEmpty());
  }
}
