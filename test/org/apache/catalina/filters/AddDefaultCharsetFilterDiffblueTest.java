package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.apache.catalina.valves.FilterValve;
import org.junit.Test;

public class AddDefaultCharsetFilterDiffblueTest {
  /**
   * Test {@link AddDefaultCharsetFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddDefaultCharsetFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_thenThrowIllegalArgumentException() throws ServletException {
    // Arrange
    AddDefaultCharsetFilter addDefaultCharsetFilter = new AddDefaultCharsetFilter();
    addDefaultCharsetFilter.setEncoding("Encoding");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> addDefaultCharsetFilter.init(new FilterValve()));
  }

  /**
   * Test new {@link AddDefaultCharsetFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link AddDefaultCharsetFilter}
   */
  @Test
  public void testNewAddDefaultCharsetFilter() {
    // Arrange, Act and Assert
    assertFalse((new AddDefaultCharsetFilter()).isConfigProblemFatal());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#addHeader(String, String)}
   */
  @Test
  public void testResponseWrapperAddHeader() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.addHeader("Name", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertNull(response.getContentType());
    assertNull(response2.getContentType());
    assertNull(response3.getContentType());
    assertNull(responseWrapper.getContentType());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertNull(coyoteResponse.getContentType());
    assertEquals(1, coyoteResponse.getMimeHeaders().size());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#addHeader(String, String)}
   */
  @Test
  public void testResponseWrapperAddHeader2() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(new HttpServletResponseWrapper(
        new ResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8")),
        "UTF-8");

    // Act
    responseWrapper.addHeader("Name", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    ServletResponse response3 = ((ResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    assertTrue(response2 instanceof ResponseWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertNull(response.getContentType());
    assertNull(responseWrapper.getContentType());
    assertEquals(1, ((Response) response4).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#addHeader(String, String)}
   */
  @Test
  public void testResponseWrapperAddHeader3() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.addHeader("content-type", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("42", response.getContentType());
    assertEquals("42", response2.getContentType());
    assertEquals("42", response3.getContentType());
    assertEquals("42", responseWrapper.getContentType());
    assertEquals("42", ((Response) response2).getCoyoteResponse().getContentType());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#ResponseWrapper(HttpServletResponse, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#ResponseWrapper(HttpServletResponse, String)}
   */
  @Test
  public void testResponseWrapperNewResponseWrapper() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    ResponseWrapper actualResponseWrapper = new ResponseWrapper(response, "UTF-8");

    // Assert
    ServletResponse response2 = actualResponseWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = actualResponseWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(actualResponseWrapper.getOutputStream() instanceof CoyoteOutputStream);
    assertNull(actualResponseWrapper.getContentType());
    assertNull(actualResponseWrapper.getTrailerFields());
    assertEquals(200, actualResponseWrapper.getStatus());
    assertEquals(8192, actualResponseWrapper.getBufferSize());
    assertFalse(actualResponseWrapper.isCommitted());
    assertTrue(headerNames.isEmpty());
    assertSame(response, response2);
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setCharacterEncoding(String)} with {@code charset}.
   * <p>
   * Method under test: {@link ResponseWrapper#setCharacterEncoding(String)}
   */
  @Test
  public void testResponseWrapperSetCharacterEncodingWithCharset() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setCharacterEncoding("UTF-8");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("UTF-8", response2.getCharacterEncoding());
    assertEquals("UTF-8", response3.getCharacterEncoding());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals("UTF-8", coyoteResponse.getCharset().name());
    assertEquals("UTF-8", coyoteResponse.getCharacterEncoding());
    assertEquals("UTF-8", coyoteResponse.getCharsetHolder().getName());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setCharacterEncoding(String)} with {@code charset}.
   * <p>
   * Method under test: {@link ResponseWrapper#setCharacterEncoding(String)}
   */
  @Test
  public void testResponseWrapperSetCharacterEncodingWithCharset2() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(new HttpServletResponseWrapper(
        new ResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8")),
        "UTF-8");

    // Act
    responseWrapper.setCharacterEncoding("UTF-8");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    ServletResponse response3 = ((ResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertTrue(response2 instanceof ResponseWrapper);
    assertEquals("UTF-8", response2.getCharacterEncoding());
    assertEquals("UTF-8", response4.getCharacterEncoding());
    assertEquals("UTF-8", response3.getCharacterEncoding());
    assertEquals("UTF-8", response5.getCharacterEncoding());
    assertEquals("UTF-8", ((Response) response4).getCoyoteResponse().getCharacterEncoding());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setContentType(String)}
   */
  @Test
  public void testResponseWrapperSetContentType() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setContentType("application/json");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("application/json", response.getContentType());
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response3.getContentType());
    assertEquals("application/json", responseWrapper.getContentType());
    assertEquals("application/json", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setContentType(String)}
   */
  @Test
  public void testResponseWrapperSetContentType2() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setContentType("text/");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("text/;charset=UTF-8", response.getContentType());
    assertEquals("text/;charset=UTF-8", response2.getContentType());
    assertEquals("text/;charset=UTF-8", response3.getContentType());
    assertEquals("text/;charset=UTF-8", responseWrapper.getContentType());
    assertEquals("text/;charset=UTF-8", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setContentType(String)}
   */
  @Test
  public void testResponseWrapperSetContentType3() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setContentType("text/plain; charset=UTF-8");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("UTF-8", response.getCharacterEncoding());
    assertEquals("UTF-8", response2.getCharacterEncoding());
    assertEquals("UTF-8", response3.getCharacterEncoding());
    assertEquals("UTF-8", responseWrapper.getCharacterEncoding());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertEquals("UTF-8", coyoteResponse.getCharacterEncoding());
    assertEquals("text/plain;charset=UTF-8", response.getContentType());
    assertEquals("text/plain;charset=UTF-8", response2.getContentType());
    assertEquals("text/plain;charset=UTF-8", response3.getContentType());
    assertEquals("text/plain;charset=UTF-8", responseWrapper.getContentType());
    assertEquals("text/plain;charset=UTF-8", coyoteResponse.getContentType());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setContentType(String)}
   */
  @Test
  public void testResponseWrapperSetContentType4() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(new HttpServletResponseWrapper(
        new ResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8")),
        "UTF-8");

    // Act
    responseWrapper.setContentType("application/json");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    ServletResponse response3 = ((ResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    HttpServletResponse response5 = ((Response) response4).getResponse();
    assertTrue(response5 instanceof ResponseFacade);
    assertTrue(response2 instanceof ResponseWrapper);
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response4.getContentType());
    assertEquals("application/json", response3.getContentType());
    assertEquals("application/json", response5.getContentType());
    assertEquals("application/json", ((Response) response4).getCoyoteResponse().getContentType());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setContentType(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseWrapper#setContentType(String)}
   */
  @Test
  public void testResponseWrapperSetContentType_whenNull() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setContentType(null);

    // Assert that nothing has changed
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    assertTrue(((Response) response2).getResponse() instanceof ResponseFacade);
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setHeader(String, String)}
   */
  @Test
  public void testResponseWrapperSetHeader() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setHeader("Name", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertNull(response.getContentType());
    assertNull(response2.getContentType());
    assertNull(response3.getContentType());
    assertNull(responseWrapper.getContentType());
    org.apache.coyote.Response coyoteResponse = ((Response) response2).getCoyoteResponse();
    assertNull(coyoteResponse.getContentType());
    assertEquals(1, coyoteResponse.getMimeHeaders().size());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setHeader(String, String)}
   */
  @Test
  public void testResponseWrapperSetHeader2() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(new HttpServletResponseWrapper(
        new ResponseWrapper(new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8")),
        "UTF-8");

    // Act
    responseWrapper.setHeader("Name", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    ServletResponse response3 = ((ResponseWrapper) response2).getResponse();
    assertTrue(response3 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    ServletResponse response4 = ((HttpServletResponseWrapper) response3).getResponse();
    assertTrue(response4 instanceof Response);
    assertTrue(response2 instanceof ResponseWrapper);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertNull(response.getContentType());
    assertNull(responseWrapper.getContentType());
    assertEquals(1, ((Response) response4).getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test ResponseWrapper {@link ResponseWrapper#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseWrapper#setHeader(String, String)}
   */
  @Test
  public void testResponseWrapperSetHeader3() {
    // Arrange
    ResponseWrapper responseWrapper = new ResponseWrapper(
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), "UTF-8");

    // Act
    responseWrapper.setHeader("content-type", "42");

    // Assert
    ServletResponse response = responseWrapper.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = responseWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    ServletResponse response2 = ((HttpServletResponseWrapper) response).getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("42", response.getContentType());
    assertEquals("42", response2.getContentType());
    assertEquals("42", response3.getContentType());
    assertEquals("42", responseWrapper.getContentType());
    assertEquals("42", ((Response) response2).getCoyoteResponse().getContentType());
    assertTrue(headerNames.isEmpty());
  }
}
