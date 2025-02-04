package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class RollbackExceptionDiffblueTest {
  /**
   * Test {@link RollbackException#RollbackException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RollbackException#RollbackException()}
   */
  @Test
  public void testNewRollbackException_thenReturnMessageIsNull() {
    // Arrange and Act
    RollbackException actualRollbackException = new RollbackException();

    // Assert
    assertNull(actualRollbackException.getMessage());
    assertNull(actualRollbackException.getCause());
    assertEquals(0, actualRollbackException.getSuppressed().length);
  }

  /**
   * Test {@link RollbackException#RollbackException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RollbackException#RollbackException(String)}
   */
  @Test
  public void testNewRollbackException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    RollbackException actualRollbackException = new RollbackException("Msg");

    // Assert
    assertEquals("Msg", actualRollbackException.getMessage());
    assertNull(actualRollbackException.getCause());
    assertEquals(0, actualRollbackException.getSuppressed().length);
  }
}
