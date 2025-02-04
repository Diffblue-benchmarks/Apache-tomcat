package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class InvalidTransactionExceptionDiffblueTest {
  /**
   * Test {@link InvalidTransactionException#InvalidTransactionException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvalidTransactionException#InvalidTransactionException()}
   */
  @Test
  public void testNewInvalidTransactionException_thenReturnMessageIsNull() {
    // Arrange and Act
    InvalidTransactionException actualInvalidTransactionException = new InvalidTransactionException();

    // Assert
    assertNull(actualInvalidTransactionException.getMessage());
    assertNull(actualInvalidTransactionException.getCause());
    assertEquals(0, actualInvalidTransactionException.getSuppressed().length);
  }

  /**
   * Test {@link InvalidTransactionException#InvalidTransactionException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvalidTransactionException#InvalidTransactionException(String)}
   */
  @Test
  public void testNewInvalidTransactionException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    InvalidTransactionException actualInvalidTransactionException = new InvalidTransactionException("Msg");

    // Assert
    assertEquals("Msg", actualInvalidTransactionException.getMessage());
    assertNull(actualInvalidTransactionException.getCause());
    assertEquals(0, actualInvalidTransactionException.getSuppressed().length);
  }
}
