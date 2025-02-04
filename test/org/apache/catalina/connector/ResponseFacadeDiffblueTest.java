package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.catalina.valves.TestRequestFilterValve;
import org.apache.catalina.valves.TestRequestFilterValve.MockResponse;
import org.apache.tomcat.unittest.TesterResponse;
import org.apache.tomcat.util.buf.CharsetHolder;
import org.junit.Test;

public class ResponseFacadeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResponseFacade#ResponseFacade(Response)}
   *   <li>{@link ResponseFacade#clear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ResponseFacade actualResponseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    actualResponseFacade.clear();

    // Assert
    assertNull(actualResponseFacade.response);
  }

  /**
   * Test {@link ResponseFacade#clone()}.
   * <p>
   * Method under test: {@link ResponseFacade#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(CloneNotSupportedException.class,
        () -> (new ResponseFacade(new Response(new org.apache.coyote.Response()))).clone());
  }

  /**
   * Test {@link ResponseFacade#finish()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#finish()}
   */
  @Test
  public void testFinish_givenResponseFacadeWithResponseIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).finish());
  }

  /**
   * Test {@link ResponseFacade#finish()}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} {@link ResponseFacade#response} {@link Response#outputBuffer} Suspended.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#finish()}
   */
  @Test
  public void testFinish_thenResponseFacadeWithResponseIsResponseResponseOutputBufferSuspended() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.finish();

    // Assert
    Response response = responseFacade.response;
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isSuspended());
  }

  /**
   * Test {@link ResponseFacade#finish()}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link TesterResponse} (default constructor) {@link ResponseFacade#response} {@link TesterResponse}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#finish()}
   */
  @Test
  public void testFinish_thenResponseFacadeWithResponseIsTesterResponseResponseTesterResponse() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.finish();

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertFalse(response.isAppCommitted());
    assertFalse(response.isSuspended());
  }

  /**
   * Test {@link ResponseFacade#isFinished()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isFinished()}
   */
  @Test
  public void testIsFinished_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new Response(new org.apache.coyote.Response()))).isFinished());
  }

  /**
   * Test {@link ResponseFacade#isFinished()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isFinished()}
   */
  @Test
  public void testIsFinished_thenReturnTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertTrue((new ResponseFacade(response)).isFinished());
  }

  /**
   * Test {@link ResponseFacade#isFinished()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isFinished()}
   */
  @Test
  public void testIsFinished_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).isFinished());
  }

  /**
   * Test {@link ResponseFacade#getContentWritten()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getContentWritten()}
   */
  @Test
  public void testGetContentWritten_givenResponseWithCoyoteResponseIsResponse_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ResponseFacade(new Response(new org.apache.coyote.Response()))).getContentWritten());
  }

  /**
   * Test {@link ResponseFacade#getContentWritten()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getContentWritten()}
   */
  @Test
  public void testGetContentWritten_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getContentWritten());
  }

  /**
   * Test {@link ResponseFacade#getOutputStream()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getOutputStream()}
   */
  @Test
  public void testGetOutputStream_givenResponseWithCoyoteResponseIsResponse() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    ServletOutputStream actualOutputStream = responseFacade.getOutputStream();

    // Assert
    Response response2 = responseFacade.response;
    assertSame(((CoyoteOutputStream) actualOutputStream).ob, response2.outputBuffer);
    CoyoteOutputStream coyoteOutputStream = response.outputStream;
    assertSame(coyoteOutputStream, response2.getOutputStream());
    assertSame(coyoteOutputStream, actualOutputStream);
    assertSame(coyoteOutputStream, response2.outputStream);
  }

  /**
   * Test {@link ResponseFacade#getOutputStream()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getOutputStream()}
   */
  @Test
  public void testGetOutputStream_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    ServletOutputStream actualOutputStream = responseFacade.getOutputStream();

    // Assert
    Response response2 = responseFacade.response;
    assertSame(((CoyoteOutputStream) actualOutputStream).ob, response2.outputBuffer);
    CoyoteOutputStream coyoteOutputStream = response.outputStream;
    assertSame(coyoteOutputStream, response2.getOutputStream());
    assertSame(coyoteOutputStream, actualOutputStream);
    assertSame(coyoteOutputStream, response2.outputStream);
  }

  /**
   * Test {@link ResponseFacade#getOutputStream()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getOutputStream()}
   */
  @Test
  public void testGetOutputStream_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getOutputStream());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setContentLength(3);

    // Assert
    Response response = responseFacade.response;
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setContentLength(3);

    // Assert
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} ContentLength is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength_givenResponseFacadeWithResponseIsResponseContentLengthIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setContentLength(3);

    // Assert
    Response response = responseFacade.response;
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentLength(3);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentLength(3);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLength(int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentLength(int)}
   */
  @Test
  public void testSetContentLength_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setContentLength(3));
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setContentLengthLong(3L);

    // Assert
    Response response = responseFacade.response;
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setContentLengthLong(3L);

    // Assert
    Response response = responseFacade.response;
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setContentLengthLong(3L);

    // Assert
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals(3, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(3, coyoteResponse.getContentLength());
    assertEquals(3L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong4() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentLengthLong(3L);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong5() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentLengthLong(3L);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setContentLengthLong(long)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentLengthLong(long)}
   */
  @Test
  public void testSetContentLengthLong_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setContentLengthLong(3L));
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setContentType("application/json");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response.getContentType());
    assertEquals("application/json", responseFacade.getContentType());
    assertEquals("application/json", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setContentType("Type");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Type", response2.getContentType());
    assertEquals("Type", response.getContentType());
    assertEquals("Type", responseFacade.getContentType());
    assertEquals("Type", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setContentType("application/json");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertTrue(response instanceof TesterResponse);
    assertEquals("application/json", response2.getContentType());
    assertEquals("application/json", response.getContentType());
    assertEquals("application/json", responseFacade.getContentType());
    assertEquals("application/json", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} ContentLength is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType_givenResponseFacadeWithResponseIsResponseContentLengthIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setContentType(null);

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentType("application/json");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setContentType("application/json");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#setContentType(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setContentType(String)}
   */
  @Test
  public void testSetContentType_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setContentType("application/json"));
  }

  /**
   * Test {@link ResponseFacade#setBufferSize(int)}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setBufferSize(int)}
   */
  @Test
  public void testSetBufferSize_givenResponseFacadeWithResponseIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setBufferSize(3));
  }

  /**
   * Test {@link ResponseFacade#setBufferSize(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setBufferSize(int)}
   */
  @Test
  public void testSetBufferSize_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).setBufferSize(3));
  }

  /**
   * Test {@link ResponseFacade#setBufferSize(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setBufferSize(int)}
   */
  @Test
  public void testSetBufferSize_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).setBufferSize(3));
  }

  /**
   * Test {@link ResponseFacade#getBufferSize()}.
   * <ul>
   *   <li>Then return {@link InputBuffer#DEFAULT_BUFFER_SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getBufferSize()}
   */
  @Test
  public void testGetBufferSize_thenReturnDefault_buffer_size() {
    // Arrange, Act and Assert
    assertEquals(InputBuffer.DEFAULT_BUFFER_SIZE,
        (new ResponseFacade(new Response(new org.apache.coyote.Response()))).getBufferSize());
  }

  /**
   * Test {@link ResponseFacade#getBufferSize()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getBufferSize()}
   */
  @Test
  public void testGetBufferSize_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getBufferSize());
  }

  /**
   * Test {@link ResponseFacade#flushBuffer()}.
   * <ul>
   *   <li>Then not {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} {@link ResponseFacade#response} Committed.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#flushBuffer()}
   */
  @Test
  public void testFlushBuffer_thenNotResponseFacadeWithResponseIsResponseResponseCommitted() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.flushBuffer();

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertFalse(response2.isCommitted());
    assertFalse(response2.getCoyoteResponse().isCommitted());
    assertFalse(response2.appCommitted);
    assertTrue(response2.isAppCommitted());
  }

  /**
   * Test {@link ResponseFacade#flushBuffer()}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} {@link ResponseFacade#response} Committed.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#flushBuffer()}
   */
  @Test
  public void testFlushBuffer_thenResponseFacadeWithResponseIsResponseResponseCommitted() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.flushBuffer();

    // Assert
    Response response = responseFacade.response;
    assertTrue(response.isAppCommitted());
    assertTrue(response.isCommitted());
    assertTrue(response.getCoyoteResponse().isCommitted());
    assertTrue(response.appCommitted);
  }

  /**
   * Test {@link ResponseFacade#flushBuffer()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#flushBuffer()}
   */
  @Test
  public void testFlushBuffer_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).flushBuffer());
  }

  /**
   * Test {@link ResponseFacade#resetBuffer()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#resetBuffer()}
   */
  @Test
  public void testResetBuffer_givenResponseFacadeWithResponseIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).resetBuffer());
  }

  /**
   * Test {@link ResponseFacade#resetBuffer()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#resetBuffer()}
   */
  @Test
  public void testResetBuffer_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).resetBuffer());
  }

  /**
   * Test {@link ResponseFacade#resetBuffer()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#resetBuffer()}
   */
  @Test
  public void testResetBuffer_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).resetBuffer());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} ContentLength is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseFacadeWithResponseIsResponseContentLengthIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act and Assert
    assertFalse(responseFacade.isCommitted());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link TesterResponse} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseFacadeWithResponseIsTesterResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new TesterResponse())).isCommitted());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertTrue((new ResponseFacade(response)).isCommitted());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertTrue((new ResponseFacade(response)).isCommitted());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_givenResponseWithCoyoteResponseIsResponse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new Response(new org.apache.coyote.Response()))).isCommitted());
  }

  /**
   * Test {@link ResponseFacade#isCommitted()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#isCommitted()}
   */
  @Test
  public void testIsCommitted_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).isCommitted());
  }

  /**
   * Test {@link ResponseFacade#reset()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code coyoteResponse.reset.ise} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#reset()}
   */
  @Test
  public void testReset_givenResponseAddHeaderCoyoteResponseResetIseAnd42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("coyoteResponse.reset.ise", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.reset();

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse2 = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse2.getContentLength());
    assertEquals(-1L, coyoteResponse2.getContentLengthLong());
    assertEquals(0, coyoteResponse2.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#reset()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#reset()}
   */
  @Test
  public void testReset_givenResponseContentLengthIsThree() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setContentLength(3L);
    coyoteResponse.addHeader("coyoteResponse.reset.ise", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.reset();

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse2 = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse2.getContentLength());
    assertEquals(-1L, coyoteResponse2.getContentLengthLong());
    assertEquals(0, coyoteResponse2.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#reset()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#reset()}
   */
  @Test
  public void testReset_givenResponseFacadeWithResponseIsNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).reset());
  }

  /**
   * Test {@link ResponseFacade#reset()}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#reset()}
   */
  @Test
  public void testReset_thenResponseFacadeWithResponseIsResponseHeaderNamesList() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.reset();

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#reset()}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link TesterResponse} (default constructor) HeaderNames {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#reset()}
   */
  @Test
  public void testReset_thenResponseFacadeWithResponseIsTesterResponseHeaderNamesList() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.reset();

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#getLocale()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@link Locale#ENGLISH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getLocale()}
   */
  @Test
  public void testGetLocale_givenResponseWithCoyoteResponseIsResponse_thenReturnEnglish() {
    // Arrange and Act
    Locale actualLocale = (new ResponseFacade(new Response(new org.apache.coyote.Response()))).getLocale();

    // Assert
    assertSame(actualLocale.ENGLISH, actualLocale);
  }

  /**
   * Test {@link ResponseFacade#getLocale()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getLocale()}
   */
  @Test
  public void testGetLocale_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getLocale());
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderContentLengthAndValue_thenReturnTrue() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertTrue((new ResponseFacade(new Response(coyoteResponse))).containsHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Type} and {@code 42}.</li>
   *   <li>When {@code Content-Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderContentTypeAnd42_whenContentType() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Type", "42");

    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));
    responseFacade.setContentLength(1);

    // Act and Assert
    assertTrue(responseFacade.containsHeader("Content-Type"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseAddHeaderNameAnd42_whenName_thenReturnTrue() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertTrue((new ResponseFacade(new Response(coyoteResponse))).containsHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) CharacterEncoding is {@code UTF-8}.</li>
   *   <li>When {@code Content-Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_givenResponseCharacterEncodingIsUtf8_whenContentType()
      throws UnsupportedEncodingException {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setCharacterEncoding("UTF-8");
    coyoteResponse.addHeader("Content-Type", "42");

    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));
    responseFacade.setContentLength(1);

    // Act and Assert
    assertTrue(responseFacade.containsHeader("Content-Type"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).containsHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Content-Length}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenContentLength_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new Response(new org.apache.coyote.Response()))).containsHeader("Content-Length"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Content-Length}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenContentLength_thenReturnTrue() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act and Assert
    assertTrue(responseFacade.containsHeader("Content-Length"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Content-Type}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenContentType_thenReturnFalse() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act and Assert
    assertFalse(responseFacade.containsHeader("Content-Type"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>When {@code coyoteResponse.reset.ise}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenCoyoteResponseResetIse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new Response(new org.apache.coyote.Response())))
        .containsHeader("coyoteResponse.reset.ise"));
  }

  /**
   * Test {@link ResponseFacade#containsHeader(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#containsHeader(String)}
   */
  @Test
  public void testContainsHeader_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ResponseFacade(new Response(new org.apache.coyote.Response()))).containsHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.sendError(1);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, responseFacade.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc2() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.sendError(103);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.outputBuffer.isSuspended());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertFalse(response.appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc3() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.sendError(1);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertTrue(response instanceof TesterResponse);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertTrue(response.isAppCommitted());
    assertTrue(((TesterResponse) response).appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc4() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.sendError(103);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertTrue(response instanceof TesterResponse);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
    assertFalse(((TesterResponse) response).appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.sendError(1, "Msg");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Msg", response.getMessage());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Msg", coyoteResponse.getMessage());
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, responseFacade.getStatus());
    assertEquals(1, coyoteResponse.getStatus());
    assertTrue(response.outputBuffer.isSuspended());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg2() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.sendError(103, "Msg");

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(response.appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg3() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.sendError(1, "Msg");

    // Assert
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertNull(response.getMessage());
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertTrue(response.isAppCommitted());
    assertTrue(((TesterResponse) response).appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg4() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).sendError(1, "Msg"));
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg5() throws IOException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.sendError(103, "Msg");

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals(200, response.getStatus());
    assertEquals(200, responseFacade.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(((TesterResponse) response).appCommitted);
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg_givenResponseFacadeWithResponseIsNull() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).sendError(1, "Msg"));
  }

  /**
   * Test {@link ResponseFacade#sendError(int, String)} with {@code sc}, {@code msg}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int, String)}
   */
  @Test
  public void testSendErrorWithScMsg_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).sendError(1, "Msg"));
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc_givenResponseFacadeWithResponseIsNull() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).sendError(1));
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).sendError(1));
  }

  /**
   * Test {@link ResponseFacade#sendError(int)} with {@code sc}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#sendError(int)}
   */
  @Test
  public void testSendErrorWithSc_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() throws IOException {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(response)).sendError(1));
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader("coyoteResponse.reset.ise", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader("Content-Type", 1L);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response2.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", responseFacade.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_givenResponseContentLengthIsThree() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setContentLength(3L);
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsTwo2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setDateHeader("Name", 1L));
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code Content-Length}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenContentLength() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader("Content-Length", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader("", 1L);

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#setDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setDateHeader(String, long)}
   */
  @Test
  public void testSetDateHeader_whenNull() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setDateHeader(null, 1L);

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addDateHeader("Content-Type", 1L);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response2.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", responseFacade.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addDateHeader("Content-Length", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addDateHeader("coyoteResponse.reset.ise", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader4() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.addDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader5() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(Integer.MAX_VALUE);

    // Act
    responseFacade.addDateHeader("Content-Type", 1L);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response2.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", responseFacade.getContentType());
    assertEquals("Thu, 01 Jan 1970 00:00:00 GMT", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addDateHeader("Name", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response2 = responseFacade.response;
    assertTrue(response2.getResponse() instanceof ResponseFacade);
    assertEquals(0, response2.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addDateHeader("Name", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response2 = responseFacade.response;
    assertTrue(response2.getResponse() instanceof ResponseFacade);
    assertEquals(0, response2.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addDateHeader("Name", 1L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).addDateHeader("Name", 1L));
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addDateHeader("", 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenNull() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addDateHeader(null, 1L);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(0, response.getCoyoteResponse().getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addDateHeader(String, long)}.
   * <ul>
   *   <li>When six.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addDateHeader(String, long)}
   */
  @Test
  public void testAddDateHeader_whenSix() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addDateHeader("Name", 6L);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader(null, "42");

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("coyoteResponse.reset.ise", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("Content-Type", "42");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    assertEquals("42", responseFacade.getContentType());
    assertEquals("42", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader4() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("Content-Length", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader5() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("Name", null);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader6() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", null);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertNull(response2.getContentType());
    assertNull(response.getContentType());
    assertNull(responseFacade.getContentType());
    org.apache.coyote.Response coyoteResponse2 = response.getCoyoteResponse();
    assertNull(coyoteResponse2.getContentType());
    assertEquals(-1, response.getContentLength());
    assertEquals(-1, coyoteResponse2.getContentLength());
    assertEquals(-1L, coyoteResponse2.getContentLengthLong());
    assertEquals(0, coyoteResponse2.getMimeHeaders().size());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader7() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", null);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_givenResponseContentLengthIsThree() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setContentLength(3L);
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsTwo2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenResponseFacadeWithResponseIsTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setHeader("Name", "42"));
  }

  /**
   * Test {@link ResponseFacade#setHeader(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setHeader(String, String)}
   */
  @Test
  public void testSetHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setHeader("", "42");

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("coyoteResponse.reset.ise", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("Content-Type", "42");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    assertEquals("42", responseFacade.getContentType());
    assertEquals("42", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("Content-Length", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader4() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.addHeader("Content-Type", "42");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertTrue(response instanceof TesterResponse);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    assertEquals("42", responseFacade.getContentType());
    assertEquals("42", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_givenResponseAddHeaderContentLengthAnd42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.addHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addHeader("Name", "42");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addHeader("Name", "42");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link TesterResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_thenResponseFacadeWithResponseIsTesterResponseHeaderNamesSizeIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.addHeader("Name", "42");

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).addHeader("Name", "42"));
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("", "42");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_whenNull() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader(null, "42");

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#addHeader(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_whenNull2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addHeader("Name", null);

    // Assert that nothing has changed
    assertTrue(responseFacade.response.getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Content-Length", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader("coyoteResponse.reset.ise", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader4() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader("Content-Type", 42);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    assertEquals("42", responseFacade.getContentType());
    assertEquals("42", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader5() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader("Content-Length", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) ContentLength is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_givenResponseContentLengthIsThree() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setContentLength(3L);
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_givenResponseHeaderNameIs42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsOne2() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert that nothing has changed
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesSizeIsTwo() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");
    ResponseFacade responseFacade = new ResponseFacade(new Response(coyoteResponse));

    // Act
    responseFacade.setIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(2, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(2, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setIntHeader("Name", 42));
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader("", 42);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setIntHeader(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setIntHeader(String, int)}
   */
  @Test
  public void testSetIntHeader_whenNull() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setIntHeader(null, 42);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addIntHeader(null, 42);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(1, coyoteResponse.getContentLength());
    assertEquals(1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addIntHeader("Content-Type", 42);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("42", response2.getContentType());
    assertEquals("42", response.getContentType());
    assertEquals("42", responseFacade.getContentType());
    assertEquals("42", response.getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader3() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.addIntHeader("Content-Length", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(0, coyoteResponse.getMimeHeaders().size());
    assertEquals(42, response.getContentLength());
    assertEquals(42, coyoteResponse.getContentLength());
    assertEquals(42L, coyoteResponse.getContentLengthLong());
    assertTrue(headerNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader4() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.addIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    Response response = responseFacade.response;
    assertTrue(response instanceof TesterResponse);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader5() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addIntHeader("coyoteResponse.reset.ise", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("coyoteResponse.reset.ise", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader6() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(Integer.MAX_VALUE);

    // Act
    responseFacade.addIntHeader(null, 42);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(2147483647L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addIntHeader("Name", 42);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertTrue(response2.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.addIntHeader("Name", 42);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    assertTrue(response2.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response2.getContentLength());
    org.apache.coyote.Response coyoteResponse = response2.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <ul>
   *   <li>Then {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} HeaderNames first is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_thenResponseFacadeWithResponseIsResponseHeaderNamesFirstIsName() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addIntHeader("Name", 42);

    // Assert
    Collection<String> headerNames = responseFacade.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof List);
    assertEquals("Name", ((List<String>) headerNames).get(0));
    assertEquals(1, responseFacade.response.getCoyoteResponse().getMimeHeaders().size());
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).addIntHeader("Name", 42));
  }

  /**
   * Test {@link ResponseFacade#addIntHeader(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#addIntHeader(String, int)}
   */
  @Test
  public void testAddIntHeader_whenEmptyString() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.addIntHeader("", 42);

    // Assert that nothing has changed
    Response response = responseFacade.response;
    assertTrue(response.getResponse() instanceof ResponseFacade);
    assertEquals(-1, response.getContentLength());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(-1, coyoteResponse.getContentLength());
    assertEquals(-1L, coyoteResponse.getContentLengthLong());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setStatus(1);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, responseFacade.getStatus());
    assertEquals(1, response.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new TesterResponse());

    // Act
    responseFacade.setStatus(1);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertTrue(response instanceof TesterResponse);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, responseFacade.getStatus());
    assertEquals(1, response.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link Response#Response(Response)} ContentLength is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus_givenResponseFacadeWithResponseIsResponseContentLengthIsOne() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setStatus(1);

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(1, response2.getStatus());
    assertEquals(1, response.getStatus());
    assertEquals(1, responseFacade.getStatus());
    assertEquals(1, response.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) AppCommitted is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus_givenResponseWithCoyoteResponseIsResponseAppCommittedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setAppCommitted(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setStatus(1);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    HttpServletResponse response3 = response2.getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, response2.getStatus());
    assertEquals(200, responseFacade.getStatus());
    assertEquals(200, response2.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Suspended is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus_givenResponseWithCoyoteResponseIsResponseSuspendedIsTrue() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    response.setSuspended(true);
    ResponseFacade responseFacade = new ResponseFacade(response);

    // Act
    responseFacade.setStatus(1);

    // Assert that nothing has changed
    Response response2 = responseFacade.response;
    HttpServletResponse response3 = response2.getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals(200, response3.getStatus());
    assertEquals(200, response2.getStatus());
    assertEquals(200, responseFacade.getStatus());
    assertEquals(200, response2.getCoyoteResponse().getStatus());
  }

  /**
   * Test {@link ResponseFacade#setStatus(int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setStatus(int)}
   */
  @Test
  public void testSetStatus_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setStatus(1));
  }

  /**
   * Test {@link ResponseFacade#getContentType()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Type} and {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getContentType()}
   */
  @Test
  public void testGetContentType_givenResponseAddHeaderContentTypeAnd42_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Type", "42");

    // Act and Assert
    assertEquals("42", (new ResponseFacade(new Response(coyoteResponse))).getContentType());
  }

  /**
   * Test {@link ResponseFacade#getContentType()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getContentType()}
   */
  @Test
  public void testGetContentType_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ResponseFacade(new Response(new org.apache.coyote.Response()))).getContentType());
  }

  /**
   * Test {@link ResponseFacade#getContentType()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getContentType()}
   */
  @Test
  public void testGetContentType_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getContentType());
  }

  /**
   * Test {@link ResponseFacade#setCharacterEncoding(Charset)} with {@code charset}.
   * <p>
   * Method under test: {@link ResponseFacade#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset() throws UnsupportedEncodingException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setCharacterEncoding((Charset) null);

    // Assert that nothing has changed
    org.apache.coyote.Response coyoteResponse = responseFacade.response.getCoyoteResponse();
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(coyoteResponse.getCharset());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link ResponseFacade#setCharacterEncoding(Charset)} with {@code charset}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithCharset_thenThrowIllegalStateException() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> responseFacade.setCharacterEncoding(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link ResponseFacade#setCharacterEncoding(String)} with {@code encoding}.
   * <p>
   * Method under test: {@link ResponseFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding() throws UnsupportedEncodingException {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));
    responseFacade.setContentLength(1);

    // Act
    responseFacade.setCharacterEncoding((String) null);

    // Assert that nothing has changed
    org.apache.coyote.Response coyoteResponse = responseFacade.response.getCoyoteResponse();
    assertNull(coyoteResponse.getCharacterEncoding());
    CharsetHolder charsetHolder = coyoteResponse.getCharsetHolder();
    assertNull(charsetHolder.getName());
    assertNull(charsetHolder.getCharset());
    assertNull(charsetHolder.getValidatedCharset());
  }

  /**
   * Test {@link ResponseFacade#setCharacterEncoding(String)} with {@code encoding}.
   * <p>
   * Method under test: {@link ResponseFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding2() {
    // Arrange
    ResponseFacade responseFacade = new ResponseFacade(new Response(new org.apache.coyote.Response()));

    // Act
    responseFacade.setCharacterEncoding("Encoding");

    // Assert
    Response response = responseFacade.response;
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals("Encoding", response2.getCharacterEncoding());
    assertEquals("Encoding", response.getCharacterEncoding());
    assertEquals("Encoding", responseFacade.getCharacterEncoding());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals("Encoding", coyoteResponse.getCharacterEncoding());
    assertEquals("Encoding", coyoteResponse.getCharsetHolder().getName());
    assertNull(response.writer);
    assertNull(response.outputBuffer.conv);
  }

  /**
   * Test {@link ResponseFacade#setCharacterEncoding(String)} with {@code encoding}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#setCharacterEncoding(String)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).setCharacterEncoding("UTF-8"));
  }

  /**
   * Test {@link ResponseFacade#getStatus()}.
   * <ul>
   *   <li>Given {@link ResponseFacade#ResponseFacade(Response)} with response is {@link MockResponse} (default constructor).</li>
   *   <li>Then return two hundred.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getStatus()}
   */
  @Test
  public void testGetStatus_givenResponseFacadeWithResponseIsMockResponse_thenReturnTwoHundred() {
    // Arrange, Act and Assert
    assertEquals(200, (new ResponseFacade(new MockResponse())).getStatus());
  }

  /**
   * Test {@link ResponseFacade#getStatus()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return two hundred.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getStatus()}
   */
  @Test
  public void testGetStatus_givenResponseWithCoyoteResponseIsResponse_thenReturnTwoHundred() {
    // Arrange, Act and Assert
    assertEquals(200, (new ResponseFacade(new Response(new org.apache.coyote.Response()))).getStatus());
  }

  /**
   * Test {@link ResponseFacade#getStatus()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getStatus()}
   */
  @Test
  public void testGetStatus_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getStatus());
  }

  /**
   * Test {@link ResponseFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Content-Length} and {@code Value}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseAddHeaderContentLengthAndValue_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Content-Length", "Value");
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertEquals("42", (new ResponseFacade(new Response(coyoteResponse))).getHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseAddHeaderNameAnd42_thenReturn42() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act and Assert
    assertEquals("42", (new ResponseFacade(new Response(coyoteResponse))).getHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#getHeader(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenResponseWithCoyoteResponseIsResponse_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ResponseFacade(new Response(new org.apache.coyote.Response()))).getHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#getHeader(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeader(String)}
   */
  @Test
  public void testGetHeader_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getHeader("Name"));
  }

  /**
   * Test {@link ResponseFacade#getHeaderNames()}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_givenResponseAddHeaderNameAnd42_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaderNames = (new ResponseFacade(new Response(coyoteResponse))).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertEquals(1, actualHeaderNames.size());
    assertEquals("Name", ((List<String>) actualHeaderNames).get(0));
  }

  /**
   * Test {@link ResponseFacade#getHeaderNames()}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_givenResponseWithCoyoteResponseIsResponse_thenReturnEmpty() {
    // Arrange and Act
    Collection<String> actualHeaderNames = (new ResponseFacade(new Response(new org.apache.coyote.Response())))
        .getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertTrue(actualHeaderNames.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#getHeaderNames()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getHeaderNames());
  }

  /**
   * Test {@link ResponseFacade#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseAddHeaderNameAnd42_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaders = (new ResponseFacade(new Response(coyoteResponse))).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertEquals(1, actualHeaders.size());
    assertTrue(actualHeaders.contains("42"));
  }

  /**
   * Test {@link ResponseFacade#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link org.apache.coyote.Response} (default constructor) Header {@code Name} is {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseHeaderNameIs42_thenReturnSizeIsOne() {
    // Arrange
    org.apache.coyote.Response coyoteResponse = new org.apache.coyote.Response();
    coyoteResponse.setHeader("Name", "42");
    coyoteResponse.addHeader("Name", "42");

    // Act
    Collection<String> actualHeaders = (new ResponseFacade(new Response(coyoteResponse))).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertEquals(1, actualHeaders.size());
    assertTrue(actualHeaders.contains("42"));
  }

  /**
   * Test {@link ResponseFacade#getHeaders(String)}.
   * <ul>
   *   <li>Given {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_givenResponseWithCoyoteResponseIsResponse_thenReturnEmpty() {
    // Arrange and Act
    Collection<String> actualHeaders = (new ResponseFacade(new Response(new org.apache.coyote.Response())))
        .getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link ResponseFacade#getHeaders(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseFacade#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ResponseFacade(null)).getHeaders("Name"));
  }
}
