package org.apache.catalina.ssi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Response;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.coyote.Request;
import org.junit.Test;

public class ResponseIncludeWrapperDiffblueTest {
  /**
   * Test {@link ResponseIncludeWrapper#ResponseIncludeWrapper(HttpServletResponse, ServletOutputStream)}.
   * <ul>
   *   <li>Then Response return {@link HttpServletResponseWrapper}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#ResponseIncludeWrapper(HttpServletResponse, ServletOutputStream)}
   */
  @Test
  public void testNewResponseIncludeWrapper_thenResponseReturnHttpServletResponseWrapper() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    ResponseIncludeWrapper actualResponseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Assert
    ServletResponse response2 = actualResponseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = actualResponseIncludeWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletOutputStream outputStream = actualResponseIncludeWrapper.getOutputStream();
    assertTrue(outputStream instanceof ByteArrayServletOutputStream);
    assertTrue(actualResponseIncludeWrapper.captureServletOutputStream instanceof ByteArrayServletOutputStream);
    assertNull(actualResponseIncludeWrapper.printWriter);
    assertNull(actualResponseIncludeWrapper.getContentType());
    assertNull(actualResponseIncludeWrapper.getTrailerFields());
    assertEquals(-1L, actualResponseIncludeWrapper.getLastModified());
    assertEquals(200, actualResponseIncludeWrapper.getStatus());
    assertEquals(8192, actualResponseIncludeWrapper.getBufferSize());
    assertFalse(actualResponseIncludeWrapper.isCommitted());
    assertTrue(headerNames.isEmpty());
    assertSame(response, response2);
    ServletOutputStream servletOutputStream = actualResponseIncludeWrapper.captureServletOutputStream;
    assertSame(servletOutputStream, outputStream);
    assertSame(servletOutputStream, actualResponseIncludeWrapper.servletOutputStream);
  }

  /**
   * Test {@link ResponseIncludeWrapper#getWriter()}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#getWriter()}
   */
  @Test
  public void testGetWriter() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response2,
        new ByteArrayServletOutputStream());

    // Act
    PrintWriter actualWriter = responseIncludeWrapper.getWriter();

    // Assert
    ServletResponse response3 = responseIncludeWrapper.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    ServletOutputStream servletOutputStream = responseIncludeWrapper.captureServletOutputStream;
    assertTrue(servletOutputStream instanceof ByteArrayServletOutputStream);
    org.apache.coyote.Response coyoteResponse = ((Response) response4).getCoyoteResponse();
    assertEquals("ISO-8859-1", coyoteResponse.getCharset().name());
    assertEquals("ISO-8859-1", coyoteResponse.getCharacterEncoding());
    assertEquals("ISO-8859-1", coyoteResponse.getCharsetHolder().getName());
    assertNull(responseIncludeWrapper.servletOutputStream);
    assertSame(responseIncludeWrapper.printWriter, actualWriter);
    assertArrayEquals(new byte[]{}, ((ByteArrayServletOutputStream) servletOutputStream).buf.toByteArray());
  }

  /**
   * Test {@link ResponseIncludeWrapper#getOutputStream()}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#getOutputStream()}
   */
  @Test
  public void testGetOutputStream() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    ServletOutputStream actualOutputStream = responseIncludeWrapper.getOutputStream();

    // Assert
    ServletOutputStream servletOutputStream = responseIncludeWrapper.captureServletOutputStream;
    assertTrue(servletOutputStream instanceof ByteArrayServletOutputStream);
    ServletOutputStream servletOutputStream2 = responseIncludeWrapper.servletOutputStream;
    assertTrue(servletOutputStream2 instanceof ByteArrayServletOutputStream);
    ByteArrayOutputStream byteArrayOutputStream = ((ByteArrayServletOutputStream) actualOutputStream).buf;
    assertSame(byteArrayOutputStream, ((ByteArrayServletOutputStream) servletOutputStream).buf);
    assertSame(byteArrayOutputStream, ((ByteArrayServletOutputStream) servletOutputStream2).buf);
    assertSame(responseIncludeWrapper.servletOutputStream, actualOutputStream);
  }

  /**
   * Test {@link ResponseIncludeWrapper#getLastModified()}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#getLastModified()}
   */
  @Test
  public void testGetLastModified() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertEquals(-1L, (new ResponseIncludeWrapper(response, new ByteArrayServletOutputStream())).getLastModified());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("Name", 42L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader2() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("Name", 42L);

    // Assert that nothing has changed
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader3() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("last-modified", 42L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("last-modified", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
    assertEquals(42L, responseIncludeWrapper.getLastModified());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader4() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(
        new ResponseIncludeWrapper(response, new ByteArrayServletOutputStream()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response2,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("Name", 42L);

    // Assert
    ServletResponse response3 = responseIncludeWrapper.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    ServletResponse response5 = ((ResponseIncludeWrapper) response4).getResponse();
    assertTrue(response5 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response6 = ((HttpServletResponseWrapper) response5).getResponse();
    assertTrue(response6 instanceof Response);
    assertTrue(response4 instanceof ResponseIncludeWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response6).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <ul>
   *   <li>When ninety-nine.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenNinetyNine() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("Name", 99L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addDateHeader(String, long)}.
   * <ul>
   *   <li>When seventy-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenSeventyEight() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addDateHeader("Name", 78L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addHeader(String, String)}
   */
  @Test
  public void testAddHeader() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addHeader("Name", "42");

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addHeader(String, String)}
   */
  @Test
  public void testAddHeader2() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(
        new ResponseIncludeWrapper(response, new ByteArrayServletOutputStream()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response2,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addHeader("Name", "42");

    // Assert
    ServletResponse response3 = responseIncludeWrapper.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    ServletResponse response5 = ((ResponseIncludeWrapper) response4).getResponse();
    assertTrue(response5 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response6 = ((HttpServletResponseWrapper) response5).getResponse();
    assertTrue(response6 instanceof Response);
    assertTrue(response4 instanceof ResponseIncludeWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response6).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#addHeader(String, String)}
   */
  @Test
  public void testAddHeader3() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.addHeader("last-modified", "42");

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("last-modified", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setDateHeader("Name", 42L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader2() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setDateHeader("Name", 42L);

    // Assert that nothing has changed
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader3() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setDateHeader("last-modified", 42L);

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("last-modified", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
    assertEquals(42L, responseIncludeWrapper.getLastModified());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader4() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(
        new ResponseIncludeWrapper(response, new ByteArrayServletOutputStream()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response2,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setDateHeader("Name", 42L);

    // Assert
    ServletResponse response3 = responseIncludeWrapper.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    ServletResponse response5 = ((ResponseIncludeWrapper) response4).getResponse();
    assertTrue(response5 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response6 = ((HttpServletResponseWrapper) response5).getResponse();
    assertTrue(response6 instanceof Response);
    assertTrue(response4 instanceof ResponseIncludeWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(-1L, responseIncludeWrapper.getLastModified());
    assertEquals(1, ((Response) response6).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setHeader(String, String)}
   */
  @Test
  public void testSetHeader() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setHeader("Name", "42");

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setHeader(String, String)}
   */
  @Test
  public void testSetHeader2() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(
        new ResponseIncludeWrapper(response, new ByteArrayServletOutputStream()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response2,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setHeader("Name", "42");

    // Assert
    ServletResponse response3 = responseIncludeWrapper.getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    ServletResponse response5 = ((ResponseIncludeWrapper) response4).getResponse();
    assertTrue(response5 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response6 = ((HttpServletResponseWrapper) response5).getResponse();
    assertTrue(response6 instanceof Response);
    assertTrue(response4 instanceof ResponseIncludeWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response6).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseIncludeWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseIncludeWrapper#setHeader(String, String)}
   */
  @Test
  public void testSetHeader3() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));
    ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(response,
        new ByteArrayServletOutputStream());

    // Act
    responseIncludeWrapper.setHeader("last-modified", "42");

    // Assert
    ServletResponse response2 = responseIncludeWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseIncludeWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response3 = ((HttpServletResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof Response);
    assertEquals("last-modified", ((List<String>) headerNames).get(0));
    assertEquals(1, ((Response) response3).getCoyoteResponse().getMimeHeaders().size());
  }
}
