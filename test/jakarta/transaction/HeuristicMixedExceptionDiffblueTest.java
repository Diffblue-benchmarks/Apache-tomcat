package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class HeuristicMixedExceptionDiffblueTest {
  /**
   * Test {@link HeuristicMixedException#HeuristicMixedException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicMixedException#HeuristicMixedException()}
   */
  @Test
  public void testNewHeuristicMixedException_thenReturnMessageIsNull() {
    // Arrange and Act
    HeuristicMixedException actualHeuristicMixedException = new HeuristicMixedException();

    // Assert
    assertNull(actualHeuristicMixedException.getMessage());
    assertNull(actualHeuristicMixedException.getCause());
    assertEquals(0, actualHeuristicMixedException.getSuppressed().length);
  }

  /**
   * Test {@link HeuristicMixedException#HeuristicMixedException(String)}.
   * <ul>
   *   <li>When {@code Msg}.</li>
   *   <li>Then return Message is {@code Msg}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HeuristicMixedException#HeuristicMixedException(String)}
   */
  @Test
  public void testNewHeuristicMixedException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange and Act
    HeuristicMixedException actualHeuristicMixedException = new HeuristicMixedException("Msg");

    // Assert
    assertEquals("Msg", actualHeuristicMixedException.getMessage());
    assertNull(actualHeuristicMixedException.getCause());
    assertEquals(0, actualHeuristicMixedException.getSuppressed().length);
  }
}
