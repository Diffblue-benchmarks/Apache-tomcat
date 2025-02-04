package jakarta.servlet.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class JspExceptionDiffblueTest {
  /**
   * Test {@link JspException#JspException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspException#JspException()}
   */
  @Test
  public void testNewJspException_thenReturnMessageIsNull() {
    // Arrange and Act
    JspException actualJspException = new JspException();

    // Assert
    assertNull(actualJspException.getMessage());
    assertNull(actualJspException.getCause());
    assertEquals(0, actualJspException.getSuppressed().length);
  }

  /**
   * Test {@link JspException#JspException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspException#JspException(String, Throwable)}
   */
  @Test
  public void testNewJspException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JspException actualJspException = new JspException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualJspException.getMessage());
    assertEquals(0, actualJspException.getSuppressed().length);
    assertSame(cause, actualJspException.getCause());
  }

  /**
   * Test {@link JspException#JspException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspException#JspException(String)}
   */
  @Test
  public void testNewJspException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    JspException actualJspException = new JspException("Msg");

    // Assert
    assertEquals("Msg", actualJspException.getMessage());
    assertNull(actualJspException.getCause());
    assertEquals(0, actualJspException.getSuppressed().length);
  }

  /**
   * Test {@link JspException#JspException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspException#JspException(Throwable)}
   */
  @Test
  public void testNewJspException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JspException actualJspException = new JspException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualJspException.getMessage());
    assertEquals(0, actualJspException.getSuppressed().length);
    assertSame(cause, actualJspException.getCause());
  }
}
