package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class ELExceptionDiffblueTest {
  /**
   * Test {@link ELException#ELException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELException#ELException()}
   */
  @Test
  public void testNewELException_thenReturnMessageIsNull() {
    // Arrange and Act
    ELException actualElException = new ELException();

    // Assert
    assertNull(actualElException.getMessage());
    assertNull(actualElException.getCause());
    assertEquals(0, actualElException.getSuppressed().length);
  }

  /**
   * Test {@link ELException#ELException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELException#ELException(String)}
   */
  @Test
  public void testNewELException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    ELException actualElException = new ELException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualElException.getMessage());
    assertNull(actualElException.getCause());
    assertEquals(0, actualElException.getSuppressed().length);
  }

  /**
   * Test {@link ELException#ELException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELException#ELException(String, Throwable)}
   */
  @Test
  public void testNewELException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ELException actualElException = new ELException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualElException.getMessage());
    assertEquals(0, actualElException.getSuppressed().length);
    assertSame(cause, actualElException.getCause());
  }

  /**
   * Test {@link ELException#ELException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELException#ELException(Throwable)}
   */
  @Test
  public void testNewELException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ELException actualElException = new ELException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualElException.getMessage());
    assertEquals(0, actualElException.getSuppressed().length);
    assertSame(cause, actualElException.getCause());
  }
}
