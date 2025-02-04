package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class ServletExceptionDiffblueTest {
  /**
   * Test {@link ServletException#ServletException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletException#ServletException()}
   */
  @Test
  public void testNewServletException_thenReturnMessageIsNull() {
    // Arrange and Act
    ServletException actualServletException = new ServletException();

    // Assert
    assertNull(actualServletException.getMessage());
    assertNull(actualServletException.getCause());
    assertEquals(0, actualServletException.getSuppressed().length);
  }

  /**
   * Test {@link ServletException#ServletException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletException#ServletException(String)}
   */
  @Test
  public void testNewServletException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    ServletException actualServletException = new ServletException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualServletException.getMessage());
    assertNull(actualServletException.getCause());
    assertEquals(0, actualServletException.getSuppressed().length);
  }

  /**
   * Test {@link ServletException#ServletException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletException#ServletException(String, Throwable)}
   */
  @Test
  public void testNewServletException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    ServletException actualServletException = new ServletException("An error occurred", rootCause);

    // Assert
    assertEquals("An error occurred", actualServletException.getMessage());
    assertEquals(0, actualServletException.getSuppressed().length);
    assertSame(rootCause, actualServletException.getCause());
  }

  /**
   * Test {@link ServletException#ServletException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletException#ServletException(Throwable)}
   */
  @Test
  public void testNewServletException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    ServletException actualServletException = new ServletException(rootCause);

    // Assert
    assertEquals("java.lang.Throwable", actualServletException.getMessage());
    assertEquals(0, actualServletException.getSuppressed().length);
    assertSame(rootCause, actualServletException.getCause());
  }

  /**
   * Test {@link ServletException#getRootCause()}.
   * <p>
   * Method under test: {@link ServletException#getRootCause()}
   */
  @Test
  public void testGetRootCause() {
    // Arrange, Act and Assert
    assertNull((new ServletException("An error occurred")).getRootCause());
  }
}
