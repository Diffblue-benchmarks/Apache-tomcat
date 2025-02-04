package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TransactionRequiredExceptionDiffblueTest {
  /**
   * Test {@link TransactionRequiredException#TransactionRequiredException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionRequiredException#TransactionRequiredException()}
   */
  @Test
  public void testNewTransactionRequiredException_thenReturnMessageIsNull() {
    // Arrange and Act
    TransactionRequiredException actualTransactionRequiredException = new TransactionRequiredException();

    // Assert
    assertNull(actualTransactionRequiredException.getMessage());
    assertNull(actualTransactionRequiredException.getCause());
    assertEquals(0, actualTransactionRequiredException.getSuppressed().length);
  }

  /**
   * Test {@link TransactionRequiredException#TransactionRequiredException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionRequiredException#TransactionRequiredException(String)}
   */
  @Test
  public void testNewTransactionRequiredException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    TransactionRequiredException actualTransactionRequiredException = new TransactionRequiredException("Msg");

    // Assert
    assertEquals("Msg", actualTransactionRequiredException.getMessage());
    assertNull(actualTransactionRequiredException.getCause());
    assertEquals(0, actualTransactionRequiredException.getSuppressed().length);
  }
}
