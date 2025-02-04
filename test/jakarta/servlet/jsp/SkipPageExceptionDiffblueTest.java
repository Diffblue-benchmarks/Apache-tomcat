package jakarta.servlet.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class SkipPageExceptionDiffblueTest {
  /**
   * Test {@link SkipPageException#SkipPageException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SkipPageException#SkipPageException()}
   */
  @Test
  public void testNewSkipPageException_thenReturnMessageIsNull() {
    // Arrange and Act
    SkipPageException actualSkipPageException = new SkipPageException();

    // Assert
    assertNull(actualSkipPageException.getMessage());
    assertNull(actualSkipPageException.getCause());
    assertEquals(0, actualSkipPageException.getSuppressed().length);
  }

  /**
   * Test {@link SkipPageException#SkipPageException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SkipPageException#SkipPageException(String)}
   */
  @Test
  public void testNewSkipPageException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    SkipPageException actualSkipPageException = new SkipPageException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualSkipPageException.getMessage());
    assertNull(actualSkipPageException.getCause());
    assertEquals(0, actualSkipPageException.getSuppressed().length);
  }

  /**
   * Test {@link SkipPageException#SkipPageException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SkipPageException#SkipPageException(String, Throwable)}
   */
  @Test
  public void testNewSkipPageException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    SkipPageException actualSkipPageException = new SkipPageException("An error occurred", rootCause);

    // Assert
    assertEquals("An error occurred", actualSkipPageException.getMessage());
    assertEquals(0, actualSkipPageException.getSuppressed().length);
    assertSame(rootCause, actualSkipPageException.getCause());
  }

  /**
   * Test {@link SkipPageException#SkipPageException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SkipPageException#SkipPageException(Throwable)}
   */
  @Test
  public void testNewSkipPageException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable rootCause = new Throwable();

    // Act
    SkipPageException actualSkipPageException = new SkipPageException(rootCause);

    // Assert
    assertEquals("java.lang.Throwable", actualSkipPageException.getMessage());
    assertEquals(0, actualSkipPageException.getSuppressed().length);
    assertSame(rootCause, actualSkipPageException.getCause());
  }
}
