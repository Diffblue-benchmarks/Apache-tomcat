package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.tomcat.unittest.TesterResponse;
import org.apache.tomcat.util.buf.CharsetHolder;
import org.junit.Test;

public class ResponseDiffblueTest {
  /**
   * Test {@link Response#Response(Response)}.
   * <p>
   * Method under test: {@link Response#Response(org.apache.coyote.Response)}
   */
  @Test
  public void testNewResponse() throws IOException {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();

    // Act
    Response actualResponse = new Response(coyoteResponse);

    // Assert
    Collection<String> headerNames = actualResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletOutputStream outputStream = actualResponse.getOutputStream();
    assertTrue(outputStream instanceof CoyoteOutputStream);
    PrintWriter reporter = actualResponse.getReporter();
    assertTrue(reporter instanceof CoyoteWriter);
    HttpServletResponse response = actualResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertNull(actualResponse.getContentType());
    assertNull(actualResponse.getMessage());
    assertNull(actualResponse.getTrailerFields());
    assertNull(actualResponse.getRequest());
    assertEquals(-1, actualResponse.getContentLength());
    assertEquals(0L, actualResponse.getContentWritten());
    assertEquals(200, actualResponse.getStatus());
    assertFalse(actualResponse.isAppCommitted());
    assertFalse(actualResponse.isClosed());
    assertFalse(actualResponse.isCommitted());
    assertFalse(actualResponse.isError());
    assertFalse(actualResponse.isErrorReportRequired());
    assertFalse(actualResponse.isSuspended());
    assertFalse(actualResponse.appCommitted);
    assertFalse(actualResponse.included);
    assertFalse(actualResponse.usingWriter);
    assertTrue(headerNames.isEmpty());
    assertTrue(actualResponse.usingOutputStream);
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE, actualResponse.getBufferSize());
    assertSame(coyoteResponse, actualResponse.getCoyoteResponse());
    assertSame(outputStream, actualResponse.outputStream);
    assertSame(reporter, actualResponse.writer);
    assertSame(response, actualResponse.facade);
  }

  /**
   * Test {@link Response#Response(Response, int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then HeaderNames return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#Response(org.apache.coyote.Response, int)}
   */
  @Test
  public void testNewResponse_whenThree_thenHeaderNamesReturnList() throws IOException {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();

    // Act
    Response actualResponse = new Response(coyoteResponse, 3);

    // Assert
    Collection<String> headerNames = actualResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletOutputStream outputStream = actualResponse.getOutputStream();
    assertTrue(outputStream instanceof CoyoteOutputStream);
    PrintWriter reporter = actualResponse.getReporter();
    assertTrue(reporter instanceof CoyoteWriter);
    HttpServletResponse response = actualResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertNull(actualResponse.getContentType());
    assertNull(actualResponse.getMessage());
    assertNull(actualResponse.getTrailerFields());
    assertNull(actualResponse.getRequest());
    assertEquals(-1, actualResponse.getContentLength());
    assertEquals(0L, actualResponse.getContentWritten());
    assertEquals(200, actualResponse.getStatus());
    assertEquals(3, actualResponse.getBufferSize());
    assertFalse(actualResponse.isAppCommitted());
    assertFalse(actualResponse.isClosed());
    assertFalse(actualResponse.isCommitted());
    assertFalse(actualResponse.isError());
    assertFalse(actualResponse.isErrorReportRequired());
    assertFalse(actualResponse.isSuspended());
    assertFalse(actualResponse.appCommitted);
    assertFalse(actualResponse.included);
    assertFalse(actualResponse.usingWriter);
    assertTrue(headerNames.isEmpty());
    assertTrue(actualResponse.usingOutputStream);
    assertSame(coyoteResponse, actualResponse.getCoyoteResponse());
    assertSame(outputStream, actualResponse.outputStream);
    assertSame(reporter, actualResponse.writer);
    assertSame(response, actualResponse.facade);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Response#setAppCommitted(boolean)}
   *   <li>{@link Response#setRequest(Request)}
   *   <li>{@link Response#getCoyoteResponse()}
   *   <li>{@link Response#getRequest()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    Response response = new Response(coyoteResponse);

    // Act
    response.setAppCommitted(true);
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    response.setRequest(request);
    org.apache.coyote.Response actualCoyoteResponse = response.getCoyoteResponse();

    // Assert
    assertSame(request, response.getRequest());
    assertSame(coyoteResponse, actualCoyoteResponse);
  }

  /**
   * Test {@link Response#getContentWritten()}.
   * <p>
   * Method under test: {@link Response#getContentWritten()}
   */
  @Test
  public void testGetContentWritten() {
    // Arrange, Act and Assert
    assertEquals(0L, (new Response(new org.apache.coyote.Response())).getContentWritten());
  }

  /**
   * Test {@link Response#isAppCommitted()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isAppCommitted()}
   */
  @Test
  public void testIsAppCommitted_givenResponseAddHeaderContentLengthAnd42_thenReturnFalse() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "42");

    // Act and Assert
    assertFalse((new Response(coyoteResponse)).isAppCommitted());
  }

  /**
   * Test {@link Response#isAppCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isAppCommitted()}
   */
  @Test
  public void testIsAppCommitted_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertTrue(response.isAppCommitted());
  }

  /**
   * Test {@link Response#isAppCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isAppCommitted()}
   */
  @Test
  public void testIsAppCommitted_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertTrue(response.isAppCommitted());
  }

  /**
   * Test {@link Response#isAppCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isAppCommitted()}
   */
  @Test
  public void testIsAppCommitted_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isAppCommitted());
  }

  /**
   * Test {@link Response#isAppCommitted()}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isAppCommitted()}
   */
  @Test
  public void testIsAppCommitted_givenTesterResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new TesterResponse()).isAppCommitted());
  }

  /**
   * Test {@link Response#setResponse(HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setResponse(HttpServletResponse)}
   */
  @Test
  public void testSetResponse_thenThrowIllegalArgumentException() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> response.setResponse(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()))));
  }

  /**
   * Test {@link Response#setSuspended(boolean)}.
   * <p>
   * Method under test: {@link Response#setSuspended(boolean)}
   */
  @Test
  public void testSetSuspended() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setSuspended(true);

    // Assert
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isSuspended());
  }

  /**
   * Test {@link Response#isSuspended()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isSuspended()}
   */
  @Test
  public void testIsSuspended_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isSuspended());
  }

  /**
   * Test {@link Response#isSuspended()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isSuspended()}
   */
  @Test
  public void testIsSuspended_thenReturnTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertTrue(response.isSuspended());
  }

  /**
   * Test {@link Response#isClosed()}.
   * <p>
   * Method under test: {@link Response#isClosed()}
   */
  @Test
  public void testIsClosed() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isClosed());
  }

  /**
   * Test {@link Response#setError()}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Error.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setError()}
   */
  @Test
  public void testSetError_thenResponseWithCoyoteResponseIsResponseError() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setError();

    // Assert
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#isError()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isError()}
   */
  @Test
  public void testIsError_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isError());
  }

  /**
   * Test {@link Response#isErrorReportRequired()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isErrorReportRequired()}
   */
  @Test
  public void testIsErrorReportRequired_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isErrorReportRequired());
  }

  /**
   * Test {@link Response#setErrorReported()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setErrorReported()}
   */
  @Test
  public void testSetErrorReported_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).setErrorReported());
  }

  /**
   * Test {@link Response#getContentLength()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getContentLength()}
   */
  @Test
  public void testGetContentLength_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Response(new org.apache.coyote.Response())).getContentLength());
  }

  /**
   * Test {@link Response#getContentType()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Type} and {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getContentType()}
   */
  @Test
  public void testGetContentType_givenResponseAddHeaderContentTypeAnd42_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Type", "42");

    // Act and Assert
    assertEquals("42", (new Response(coyoteResponse)).getContentType());
  }

  /**
   * Test {@link Response#getContentType()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) CharacterEncoding is {@code UTF-8}.</li>
   *   <li>Then return {@code 42;charset=UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getContentType()}
   */
  @Test
  public void testGetContentType_givenResponseCharacterEncodingIsUtf8_thenReturn42CharsetUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setCharacterEncoding("UTF-8");
    coyoteResponse.addHeader("Content-Type", "42");

    // Act and Assert
    assertEquals("42;charset=UTF-8", (new Response(coyoteResponse)).getContentType());
  }

  /**
   * Test {@link Response#getContentType()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getContentType()}
   */
  @Test
  public void testGetContentType_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Response(new org.apache.coyote.Response())).getContentType());
  }

  /**
   * Test {@link Response#getReporter()}.
   * <p>
   * Method under test: {@link Response#getReporter()}
   */
  @Test
  public void testGetReporter() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    PrintWriter actualReporter = response.getReporter();

    // Assert
    ServletOutputStream outputStream = response.getOutputStream();
    assertTrue(outputStream instanceof CoyoteOutputStream);
    OutputBuffer outputBuffer = ((CoyoteWriter) actualReporter).ob;
    assertSame(outputBuffer, ((CoyoteOutputStream) outputStream).ob);
    assertSame(outputBuffer, response.writer.ob);
    assertSame(response.writer, actualReporter);
  }

  /**
   * Test {@link Response#getReporter()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) CharacterEncoding is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getReporter()}
   */
  @Test
  public void testGetReporter_givenResponseCharacterEncodingIsUtf8() throws IOException {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setCharacterEncoding("UTF-8");
    Response response = new Response(coyoteResponse);

    // Act
    PrintWriter actualReporter = response.getReporter();

    // Assert
    ServletOutputStream outputStream = response.getOutputStream();
    assertTrue(outputStream instanceof CoyoteOutputStream);
    OutputBuffer outputBuffer = ((CoyoteWriter) actualReporter).ob;
    assertSame(outputBuffer, ((CoyoteOutputStream) outputStream).ob);
    assertSame(outputBuffer, response.writer.ob);
    assertSame(response.writer, actualReporter);
  }

  /**
   * Test {@link Response#flushBuffer()}.
   * <ul>
   *   <li>Then not {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Committed.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#flushBuffer()}
   */
  @Test
  public void testFlushBuffer_thenNotResponseWithCoyoteResponseIsResponseCommitted() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act
    response.flushBuffer();

    // Assert that nothing has changed
    assertFalse(response.isCommitted());
    assertFalse(response.getCoyoteResponse().isCommitted());
    assertTrue(response.isAppCommitted());
  }

  /**
   * Test {@link Response#flushBuffer()}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Committed.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#flushBuffer()}
   */
  @Test
  public void testFlushBuffer_thenResponseWithCoyoteResponseIsResponseCommitted() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.flushBuffer();

    // Assert
    assertTrue(response.isAppCommitted());
    assertTrue(response.isCommitted());
    assertTrue(response.getCoyoteResponse().isCommitted());
  }

  /**
   * Test {@link Response#getBufferSize()}.
   * <p>
   * Method under test: {@link Response#getBufferSize()}
   */
  @Test
  public void testGetBufferSize() {
    // Arrange, Act and Assert
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE, (new Response(new org.apache.coyote.Response())).getBufferSize());
  }

  /**
   * Test {@link Response#getOutputStream()}.
   * <p>
   * Method under test: {@link Response#getOutputStream()}
   */
  @Test
  public void testGetOutputStream() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    ServletOutputStream actualOutputStream = response.getOutputStream();

    // Assert
    PrintWriter reporter = response.getReporter();
    assertTrue(reporter instanceof CoyoteWriter);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    OutputBuffer outputBuffer = ((CoyoteOutputStream) actualOutputStream).ob;
    assertSame(outputBuffer, response.outputStream.ob);
    assertSame(outputBuffer, ((CoyoteWriter) reporter).ob);
    CoyoteOutputStream coyoteOutputStream = response.outputStream;
    assertSame(coyoteOutputStream, response2.getOutputStream());
    assertSame(coyoteOutputStream, actualOutputStream);
  }

  /**
   * Test {@link Response#getLocale()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@link Locale#ENGLISH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getLocale()}
   */
  @Test
  public void testGetLocale_givenResponseWithCoyoteResponseIsResponse_thenReturnEnglish() {
    // Arrange and Act
    Locale actualLocale = (new Response(new org.apache.coyote.Response())).getLocale();

    // Assert
    assertSame(actualLocale.ENGLISH, actualLocale);
  }

  /**
   * Test {@link Response#isCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).isCommitted());
  }

  /**
   * Test {@link Response#reset()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#reset()}
   */
  @Test
  public void testReset_givenResponseAddHeaderNameAnd42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.reset();

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#reset()}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#reset()}
   */
  @Test
  public void testReset_givenTesterResponse_thenTesterResponseHeaderNamesList() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.reset();

    // Assert that nothing has changed
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(0, testerResponse.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#reset()}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#reset()}
   */
  @Test
  public void testReset_thenResponseWithCoyoteResponseIsResponseHeaderNamesList() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.reset();

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setContentLength(int)}.
   * <p>
   * Method under test: {@link Response#setContentLength(int)}
   */
  @Test
  public void testSetContentLength() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentLength(3);

    // Assert
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setContentLength(int)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setContentLength(int)}
   */
  @Test
  public void testSetContentLength_givenTesterResponse_thenTesterResponseContentLengthIsThree() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setContentLength(3);

    // Assert
    assertEquals(3, testerResponse.getContentLength());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link Response#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentLengthLong(3L);

    // Assert
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setContentLengthLong(long)}.
   * <ul>
   *   <li>Then {@link TesterResponse} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong_thenTesterResponseContentLengthIsThree() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setContentLengthLong(3L);

    // Assert
    assertEquals(3, testerResponse.getContentLength());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType("application/json");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("application/json", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType2() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType("application/xml");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("application/xml", response2.getContentType());
    assertEquals("application/xml", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("application/xml", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType3() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType("Type");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Type", response2.getContentType());
    assertEquals("Type", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Type", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType4() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType("");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("", response2.getContentType());
    assertEquals("", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType5() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType("/");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("/", response2.getContentType());
    assertEquals("/", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("/", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType_givenTesterResponse_thenTesterResponseResponseResponseFacade()
      throws UnsupportedEncodingException {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setContentType("application/json");

    // Assert
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals("application/json", response.getContentType());
    assertEquals("application/json", testerResponse.getContentType());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals("application/json", coyoteResponse.getContentType());
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setContentType(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setContentType(String)}
   */
  @Test
  public void testSetContentType_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setContentType(null);

    // Assert that nothing has changed
    assertTrue(response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link Response#setCharacterEncoding(Charset)} with {@code charset}.
   * <p>
   * Method under test: {@link Response#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setCharacterEncoding((Charset) null);

    // Assert that nothing has changed
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(coyoteResponse.getCharset());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#setCharacterEncoding(String)} with {@code encoding}.
   * <p>
   * Method under test: {@link Response#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setCharacterEncoding("Encoding");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Encoding", response2.getCharacterEncoding());
    assertEquals("Encoding", response.getCharacterEncoding());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Encoding", coyoteResponse.getCharacterEncoding());
    assertEquals("Encoding", coyoteResponse.getCharsetHolder().getName());
    assertNull(response.writer);
    assertNull(response.outputBuffer.conv);
  }

  /**
   * Test {@link Response#setCharacterEncoding(String)} with {@code encoding}.
   * <p>
   * Method under test: {@link Response#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding2() throws UnsupportedEncodingException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setCharacterEncoding((String) null);

    // Assert that nothing has changed
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link Response#getHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code Value}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseAddHeaderContentLengthAndValue_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertEquals("42", (new Response(coyoteResponse)).getHeader("Name"));
  }

  /**
   * Test {@link Response#getHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseAddHeaderNameAnd42_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertEquals("42", (new Response(coyoteResponse)).getHeader("Name"));
  }

  /**
   * Test {@link Response#getHeader(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Response(new org.apache.coyote.Response())).getHeader("Name"));
  }

  /**
   * Test {@link Response#getHeaderNames()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_givenResponseAddHeaderNameAnd42_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaderNames = (new Response(coyoteResponse)).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertEquals(1, actualHeaderNames.size());
    assertEquals("Name", ((List<String>) actualHeaderNames).get(0));
  }

  /**
   * Test {@link Response#getHeaderNames()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_givenResponseWithCoyoteResponseIsResponse_thenReturnEmpty() {
    // Arrange and Act
    Collection<String> actualHeaderNames = (new Response(new org.apache.coyote.Response())).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertTrue(actualHeaderNames.isEmpty());
  }

  /**
   * Test {@link Response#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code Value}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseAddHeaderContentLengthAndValue_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaders = (new Response(coyoteResponse)).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertEquals(1, actualHeaders.size());
    assertTrue(actualHeaders.contains("42"));
  }

  /**
   * Test {@link Response#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseAddHeaderNameAnd42_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaders = (new Response(coyoteResponse)).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertEquals(1, actualHeaders.size());
    assertTrue(actualHeaders.contains("42"));
  }

  /**
   * Test {@link Response#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseWithCoyoteResponseIsResponse_thenReturnEmpty() {
    // Arrange and Act
    Collection<String> actualHeaders = (new Response(new org.apache.coyote.Response())).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link Response#getMessage()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getMessage()}
   */
  @Test
  public void testGetMessage_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Response(new org.apache.coyote.Response())).getMessage());
  }

  /**
   * Test {@link Response#getStatus()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return two hundred.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#getStatus()}
   */
  @Test
  public void testGetStatus_givenResponseWithCoyoteResponseIsResponse_thenReturnTwoHundred() {
    // Arrange, Act and Assert
    assertEquals(200, (new Response(new org.apache.coyote.Response())).getStatus());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader("Content-Type", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response2.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader2() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader("Content-Length", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_givenTesterResponse_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.addDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader("", 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader(null, 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addDateHeader(String, long)}.
   * <ul>
   *   <li>When three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenThree() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addDateHeader("Name", 3L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Name", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue2() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Content-Type", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue3() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Content-Length", "42");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response2.getContentType());
    assertNull(response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getContentType());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue4() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Content-Type", "/");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("/", response2.getContentType());
    assertEquals("/", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("/", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue5() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Content-Type", "");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("", response2.getContentType());
    assertEquals("", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue6() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Content-Length", "Content-Type");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.addHeader("Name", "42");

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <ul>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue_thenTesterResponseResponseResponseFacade() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.addHeader("Content-Type", "42");

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals("42", response.getContentType());
    assertEquals("42", testerResponse.getContentType());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader(null, "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addHeader(String, String)} with {@code name}, {@code value}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addHeader(String, String)}
   */
  @Test
  public void testAddHeaderWithNameValue_whenNull2() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addHeader("Name", null);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addIntHeader("Content-Length", 42);

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response2.getContentType());
    assertNull(response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertNull(coyoteResponse.getContentType());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_givenTesterResponse_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.addIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_givenTesterResponse_thenTesterResponseResponseResponseFacade() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.addIntHeader("Content-Type", 42);

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals("42", response.getContentType());
    assertEquals("42", testerResponse.getContentType());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response ContentType is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_thenResponseWithCoyoteResponseIsResponseResponseContentTypeIs42() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addIntHeader("Content-Type", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addIntHeader("", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#addIntHeader(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.addIntHeader(null, 42);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.addHeader("Content-Length", "42");

    // Act and Assert
    assertTrue(response.containsHeader("Content-Length"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderContentLengthAndValue_thenReturnTrue() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertTrue((new Response(coyoteResponse)).containsHeader("Name"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Type} and {@code Content-Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderContentTypeAndContentType() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Type", "Content-Type");

    // Act and Assert
    assertTrue((new Response(coyoteResponse)).containsHeader("Content-Type"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderNameAnd42_whenName_thenReturnTrue() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertTrue((new Response(coyoteResponse)).containsHeader("Name"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Content-Length}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenContentLength_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).containsHeader("Content-Length"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Content-Type}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenContentType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).containsHeader("Content-Type"));
  }

  /**
   * Test {@link Response#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Response(new org.apache.coyote.Response())).containsHeader("Name"));
  }

  /**
   * Test {@link Response#sendError(int)} with {@code status}.
   * <p>
   * Method under test: {@link Response#sendError(int)}
   */
  @Test
  public void testSendErrorWithStatus() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.sendError(1);

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#sendError(int)} with {@code status}.
   * <p>
   * Method under test: {@link Response#sendError(int)}
   */
  @Test
  public void testSendErrorWithStatus2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.sendError(103);

    // Assert that nothing has changed
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.outputBuffer.isSuspended());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#sendError(int, String)} with {@code status}, {@code message}.
   * <p>
   * Method under test: {@link Response#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithStatusMessage() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.sendError(1, "Not all who wander are lost");

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Not all who wander are lost", response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Not all who wander are lost", coyoteResponse.getMessage());
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#sendError(int, String)} with {@code status}, {@code message}.
   * <p>
   * Method under test: {@link Response#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithStatusMessage2() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.sendError(103, "Not all who wander are lost");

    // Assert that nothing has changed
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.outputBuffer.isSuspended());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#sendError(int)} with {@code status}.
   * <ul>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#sendError(int)}
   */
  @Test
  public void testSendErrorWithStatus_thenTesterResponseResponseResponseFacade() throws IOException {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.sendError(1);

    // Assert that nothing has changed
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals(200, response.getStatus());
    assertEquals(200, testerResponse.getStatus());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(testerResponse.outputBuffer.isSuspended());
    assertFalse(testerResponse.isAppCommitted());
    assertFalse(testerResponse.isError());
    assertFalse(testerResponse.isErrorReportRequired());
    assertFalse(testerResponse.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setDateHeader("Content-Type", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response2.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_givenTesterResponse_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setDateHeader("Name", 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setDateHeader("Name", 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsTwo2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setDateHeader("Name", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code Content-Length}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenContentLength() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setDateHeader("Content-Length", 42L);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setDateHeader("", 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setDateHeader(null, 42L);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", null);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_givenTesterResponse_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_givenTesterResponse_thenTesterResponseResponseResponseFacade() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setHeader("Content-Type", "42");

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals("42", response.getContentType());
    assertEquals("42", testerResponse.getContentType());
    org.apache.coyote.Response coyoteResponse = testerResponse.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) ContentLength is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseContentLengthIsFortyTwo() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader("Content-Length", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsTwo2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response ContentType is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseResponseContentTypeIs42() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader("Content-Type", "42");

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response ContentType is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseWithCoyoteResponseIsResponseResponseContentTypeIsNull() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setHeader("Name", null);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response2.getContentType());
    assertNull(response.getContentType());
    org.apache.coyote.Response coyoteResponse2 = response.getCoyoteResponse();
    assertNull(coyoteResponse2.getContentType());
    assertEquals(0, coyoteResponse2.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader("", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader(null, "42");

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setHeader(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_whenNull2() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setHeader("Name", null);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setIntHeader("Name", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_givenTesterResponse_thenTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = testerResponse.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, testerResponse.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) ContentLength is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseWithCoyoteResponseIsResponseContentLengthIsFortyTwo() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setIntHeader("Content-Length", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsOne2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setIntHeader("Name", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseWithCoyoteResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    Response response = new Response(coyoteResponse);

    // Act
    response.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response ContentType is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseWithCoyoteResponseIsResponseResponseContentTypeIs42() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setIntHeader("Content-Type", 42);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertTrue(headerNames instanceof List);
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("42", coyoteResponse.getContentType());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_whenEmptyString() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setIntHeader("", 42);

    // Assert that nothing has changed
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setIntHeader(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_whenNull() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setIntHeader(null, 42);

    // Assert that nothing has changed
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link Response#setStatus(int)}.
   * <ul>
   *   <li>Given {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setStatus(int)}
   */
  @Test
  public void testSetStatus_givenTesterResponse_thenTesterResponseResponseResponseFacade() {
    // Arrange
    TesterResponse testerResponse = new TesterResponse();

    // Act
    testerResponse.setStatus(1);

    // Assert
    HttpServletResponse response = testerResponse.getResponse();
    assertTrue(response instanceof ResponseFacade);
    assertEquals(1, response.getStatus());
    assertEquals(1, testerResponse.getStatus());
    assertEquals(1, testerResponse.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link Response#setStatus(int)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response#setStatus(int)}
   */
  @Test
  public void testSetStatus_thenResponseWithCoyoteResponseIsResponseResponseResponseFacade() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());

    // Act
    response.setStatus(1);

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, response.getCoyoteResponse().getStatus());
  }
}
