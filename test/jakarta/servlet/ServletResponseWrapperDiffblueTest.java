package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.coyote.Response;
import org.junit.Test;

public class ServletResponseWrapperDiffblueTest {
  /**
   * Test {@link ServletResponseWrapper#ServletResponseWrapper(ServletResponse)}.
   * <ul>
   *   <li>Then Response return {@link HttpServletResponseWrapper}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#ServletResponseWrapper(ServletResponse)}
   */
  @Test
  public void testNewServletResponseWrapper_thenResponseReturnHttpServletResponseWrapper() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act
    ServletResponseWrapper actualServletResponseWrapper = new ServletResponseWrapper(response);

    // Assert
    ServletResponse response2 = actualServletResponseWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    assertTrue(actualServletResponseWrapper.getOutputStream() instanceof CoyoteOutputStream);
    assertNull(actualServletResponseWrapper.getContentType());
    assertEquals(8192, actualServletResponseWrapper.getBufferSize());
    assertFalse(actualServletResponseWrapper.isCommitted());
    assertSame(response, response2);
  }

  /**
   * Test {@link ServletResponseWrapper#ServletResponseWrapper(ServletResponse)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#ServletResponseWrapper(ServletResponse)}
   */
  @Test
  public void testNewServletResponseWrapper_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ServletResponseWrapper(null));
  }

  /**
   * Test {@link ServletResponseWrapper#getResponse()}.
   * <p>
   * Method under test: {@link ServletResponseWrapper#getResponse()}
   */
  @Test
  public void testGetResponse() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act and Assert
    assertSame(response, (new ServletResponseWrapper(response)).getResponse());
  }

  /**
   * Test {@link ServletResponseWrapper#setResponse(ServletResponse)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#setResponse(ServletResponse)}
   */
  @Test
  public void testSetResponse_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse())))
            .setResponse(null));
  }

  /**
   * Test {@link ServletResponseWrapper#setCharacterEncoding(Charset)} with {@code encoding}.
   * <p>
   * Method under test: {@link ServletResponseWrapper#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding() {
    // Arrange
    ServletResponseWrapper servletResponseWrapper = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));

    // Act
    servletResponseWrapper.setCharacterEncoding(Charset.forName("UTF-8"));

    // Assert that nothing has changed
    assertTrue(servletResponseWrapper.getResponse() instanceof HttpServletResponseWrapper);
  }

  /**
   * Test {@link ServletResponseWrapper#setCharacterEncoding(Charset)} with {@code encoding}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#setCharacterEncoding(Charset)}
   */
  @Test
  public void testSetCharacterEncodingWithEncoding_whenNull() {
    // Arrange
    ServletResponseWrapper servletResponseWrapper = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));

    // Act
    servletResponseWrapper.setCharacterEncoding((Charset) null);

    // Assert that nothing has changed
    assertTrue(servletResponseWrapper.getResponse() instanceof HttpServletResponseWrapper);
  }

  /**
   * Test {@link ServletResponseWrapper#getCharacterEncoding()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#getCharacterEncoding()}
   */
  @Test
  public void testGetCharacterEncoding_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse())))
        .getCharacterEncoding());
  }

  /**
   * Test {@link ServletResponseWrapper#getOutputStream()}.
   * <p>
   * Method under test: {@link ServletResponseWrapper#getOutputStream()}
   */
  @Test
  public void testGetOutputStream() throws IOException {
    // Arrange, Act and Assert
    assertNull((new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse())))
        .getOutputStream());
  }

  /**
   * Test {@link ServletResponseWrapper#getContentType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#getContentType()}
   */
  @Test
  public void testGetContentType_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        (new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse()))).getContentType());
  }

  /**
   * Test {@link ServletResponseWrapper#getBufferSize()}.
   * <p>
   * Method under test: {@link ServletResponseWrapper#getBufferSize()}
   */
  @Test
  public void testGetBufferSize() {
    // Arrange, Act and Assert
    assertEquals(-1,
        (new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse()))).getBufferSize());
  }

  /**
   * Test {@link ServletResponseWrapper#isCommitted()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#isCommitted()}
   */
  @Test
  public void testIsCommitted_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        (new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse()))).isCommitted());
  }

  /**
   * Test {@link ServletResponseWrapper#getLocale()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#getLocale()}
   */
  @Test
  public void testGetLocale_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        (new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse()))).getLocale());
  }

  /**
   * Test {@link ServletResponseWrapper#isWrapperFor(ServletResponse)} with {@code wrapped}.
   * <p>
   * Method under test: {@link ServletResponseWrapper#isWrapperFor(ServletResponse)}
   */
  @Test
  public void testIsWrapperForWithWrapped() {
    // Arrange
    ServletResponseWrapper servletResponseWrapper = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));

    // Act and Assert
    assertFalse(servletResponseWrapper
        .isWrapperFor(new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse()))));
  }

  /**
   * Test {@link ServletResponseWrapper#isWrapperFor(Class)} with {@code wrappedType}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletResponseWrapper#isWrapperFor(Class)}
   */
  @Test
  public void testIsWrapperForWithWrappedType_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    ServletResponseWrapper servletResponseWrapper = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));
    Class<Object> wrappedType = Object.class;

    // Act and Assert
    assertTrue(servletResponseWrapper.isWrapperFor(wrappedType));
  }
}
