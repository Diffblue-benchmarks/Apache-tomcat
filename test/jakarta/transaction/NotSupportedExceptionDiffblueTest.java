package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class NotSupportedExceptionDiffblueTest {
  /**
   * Test {@link NotSupportedException#NotSupportedException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotSupportedException#NotSupportedException()}
   */
  @Test
  public void testNewNotSupportedException_thenReturnMessageIsNull() {
    // Arrange and Act
    NotSupportedException actualNotSupportedException = new NotSupportedException();

    // Assert
    assertNull(actualNotSupportedException.getMessage());
    assertNull(actualNotSupportedException.getCause());
    assertEquals(0, actualNotSupportedException.getSuppressed().length);
  }

  /**
   * Test {@link NotSupportedException#NotSupportedException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotSupportedException#NotSupportedException(String)}
   */
  @Test
  public void testNewNotSupportedException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    NotSupportedException actualNotSupportedException = new NotSupportedException("Msg");

    // Assert
    assertEquals("Msg", actualNotSupportedException.getMessage());
    assertNull(actualNotSupportedException.getCause());
    assertEquals(0, actualNotSupportedException.getSuppressed().length);
  }
}
