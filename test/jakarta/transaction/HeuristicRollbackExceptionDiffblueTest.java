package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class HeuristicRollbackExceptionDiffblueTest {
  /**
   * Test {@link HeuristicRollbackException#HeuristicRollbackException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicRollbackException#HeuristicRollbackException()}
   */
  @Test
  public void testNewHeuristicRollbackException_thenReturnMessageIsNull() {
    // Arrange and Act
    HeuristicRollbackException actualHeuristicRollbackException = new HeuristicRollbackException();

    // Assert
    assertNull(actualHeuristicRollbackException.getMessage());
    assertNull(actualHeuristicRollbackException.getCause());
    assertEquals(0, actualHeuristicRollbackException.getSuppressed().length);
  }

  /**
   * Test {@link HeuristicRollbackException#HeuristicRollbackException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicRollbackException#HeuristicRollbackException(String)}
   */
  @Test
  public void testNewHeuristicRollbackException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    HeuristicRollbackException actualHeuristicRollbackException = new HeuristicRollbackException("Msg");

    // Assert
    assertEquals("Msg", actualHeuristicRollbackException.getMessage());
    assertNull(actualHeuristicRollbackException.getCause());
    assertEquals(0, actualHeuristicRollbackException.getSuppressed().length);
  }
}
