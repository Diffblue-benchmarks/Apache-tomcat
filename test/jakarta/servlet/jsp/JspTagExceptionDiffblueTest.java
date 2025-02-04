package jakarta.servlet.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class JspTagExceptionDiffblueTest {
  /**
   * Test {@link JspTagException#JspTagException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspTagException#JspTagException()}
   */
  @Test
  public void testNewJspTagException_thenReturnMessageIsNull() {
    // Arrange and Act
    JspTagException actualJspTagException = new JspTagException();

    // Assert
    assertNull(actualJspTagException.getMessage());
    assertNull(actualJspTagException.getCause());
    assertEquals(0, actualJspTagException.getSuppressed().length);
  }

  /**
   * Test {@link JspTagException#JspTagException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspTagException#JspTagException(String, Throwable)}
   */
  @Test
  public void testNewJspTagException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    JspTagException actualJspTagException = new JspTagException("An error occurred", rootCause);

    // Assert
    assertEquals("An error occurred", actualJspTagException.getMessage());
    assertEquals(0, actualJspTagException.getSuppressed().length);
    assertSame(rootCause, actualJspTagException.getCause());
  }

  /**
   * Test {@link JspTagException#JspTagException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspTagException#JspTagException(String)}
   */
  @Test
  public void testNewJspTagException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    JspTagException actualJspTagException = new JspTagException("Msg");

    // Assert
    assertEquals("Msg", actualJspTagException.getMessage());
    assertNull(actualJspTagException.getCause());
    assertEquals(0, actualJspTagException.getSuppressed().length);
  }

  /**
   * Test {@link JspTagException#JspTagException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JspTagException#JspTagException(Throwable)}
   */
  @Test
  public void testNewJspTagException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    JspTagException actualJspTagException = new JspTagException(rootCause);

    // Assert
    assertEquals("java.lang.Throwable", actualJspTagException.getMessage());
    assertEquals(0, actualJspTagException.getSuppressed().length);
    assertSame(rootCause, actualJspTagException.getCause());
  }
}
