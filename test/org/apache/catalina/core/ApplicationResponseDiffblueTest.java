package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.junit.Test;

public class ApplicationResponseDiffblueTest {
  /**
   * Test {@link ApplicationResponse#ApplicationResponse(ServletResponse, boolean)}.
   * <ul>
   *   <li>Then Response return {@link ServletResponseWrapper}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationResponse#ApplicationResponse(ServletResponse, boolean)}
   */
  @Test
  public void testNewApplicationResponse_thenResponseReturnServletResponseWrapper() throws IOException {
    // Arrange
    ServletResponseWrapper response = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));

    // Act
    ApplicationResponse actualApplicationResponse = new ApplicationResponse(response, true);

    // Assert
    ServletResponse response2 = actualApplicationResponse.getResponse();
    assertTrue(response2 instanceof ServletResponseWrapper);
    assertNull(actualApplicationResponse.getOutputStream());
    assertNull(actualApplicationResponse.getCharacterEncoding());
    assertNull(actualApplicationResponse.getContentType());
    assertNull(actualApplicationResponse.getLocale());
    assertEquals(-1, actualApplicationResponse.getBufferSize());
    assertFalse(actualApplicationResponse.isCommitted());
    assertTrue(actualApplicationResponse.included);
    assertSame(response, response2);
  }
}
