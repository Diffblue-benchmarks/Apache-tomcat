package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TransactionRolledbackExceptionDiffblueTest {
  /**
   * Test {@link TransactionRolledbackException#TransactionRolledbackException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionRolledbackException#TransactionRolledbackException()}
   */
  @Test
  public void testNewTransactionRolledbackException_thenReturnMessageIsNull() {
    // Arrange and Act
    TransactionRolledbackException actualTransactionRolledbackException = new TransactionRolledbackException();

    // Assert
    assertNull(actualTransactionRolledbackException.getMessage());
    assertNull(actualTransactionRolledbackException.getCause());
    assertEquals(0, actualTransactionRolledbackException.getSuppressed().length);
  }

  /**
   * Test {@link TransactionRolledbackException#TransactionRolledbackException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionRolledbackException#TransactionRolledbackException(String)}
   */
  @Test
  public void testNewTransactionRolledbackException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    TransactionRolledbackException actualTransactionRolledbackException = new TransactionRolledbackException("Msg");

    // Assert
    assertEquals("Msg", actualTransactionRolledbackException.getMessage());
    assertNull(actualTransactionRolledbackException.getCause());
    assertEquals(0, actualTransactionRolledbackException.getSuppressed().length);
  }
}
