package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class HeuristicCommitExceptionDiffblueTest {
  /**
   * Test {@link HeuristicCommitException#HeuristicCommitException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicCommitException#HeuristicCommitException()}
   */
  @Test
  public void testNewHeuristicCommitException_thenReturnMessageIsNull() {
    // Arrange and Act
    HeuristicCommitException actualHeuristicCommitException = new HeuristicCommitException();

    // Assert
    assertNull(actualHeuristicCommitException.getMessage());
    assertNull(actualHeuristicCommitException.getCause());
    assertEquals(0, actualHeuristicCommitException.getSuppressed().length);
  }

  /**
   * Test {@link HeuristicCommitException#HeuristicCommitException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicCommitException#HeuristicCommitException(String)}
   */
  @Test
  public void testNewHeuristicCommitException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    HeuristicCommitException actualHeuristicCommitException = new HeuristicCommitException("Msg");

    // Assert
    assertEquals("Msg", actualHeuristicCommitException.getMessage());
    assertNull(actualHeuristicCommitException.getCause());
    assertEquals(0, actualHeuristicCommitException.getSuppressed().length);
  }
}
