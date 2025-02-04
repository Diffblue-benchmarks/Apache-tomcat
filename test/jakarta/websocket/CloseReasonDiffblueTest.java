package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.CloseReason.CloseCode;
import jakarta.websocket.CloseReason.CloseCodes;
import org.junit.Test;

public class CloseReasonDiffblueTest {
  /**
   * Test CloseCodes {@link CloseCodes#getCloseCode(int)}.
   * <ul>
   *   <li>When {@code 3000}.</li>
   *   <li>Then return Code is {@code 3000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloseCodes#getCloseCode(int)}
   */
  @Test
  public void testCloseCodesGetCloseCode_when3000_thenReturnCodeIs3000() {
    // Arrange, Act and Assert
    assertEquals(3000, CloseCodes.getCloseCode(3000).getCode());
  }

  /**
   * Test CloseCodes {@link CloseCodes#getCloseCode(int)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return {@link CloseCodes}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloseCodes#getCloseCode(int)}
   */
  @Test
  public void testCloseCodesGetCloseCode_whenOneThousand_thenReturnCloseCodes() {
    // Arrange and Act
    CloseCode actualCloseCode = CloseCodes.getCloseCode(1000);

    // Assert
    assertTrue(actualCloseCode instanceof CloseCodes);
    assertEquals(1000, actualCloseCode.getCode());
    assertEquals(CloseCodes.NORMAL_CLOSURE, actualCloseCode);
  }

  /**
   * Test CloseCodes {@link CloseCodes#getCode()}.
   * <p>
   * Method under test: {@link CloseCodes#getCode()}
   */
  @Test
  public void testCloseCodesGetCode() {
    // Arrange, Act and Assert
    assertEquals(1000, CloseCodes.valueOf("NORMAL_CLOSURE").getCode());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CloseReason#CloseReason(CloseCode, String)}
   *   <li>{@link CloseReason#getCloseCode()}
   *   <li>{@link CloseReason#getReasonPhrase()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CloseReason actualCloseReason = new CloseReason(null, "Just cause");
    CloseCode actualCloseCode = actualCloseReason.getCloseCode();

    // Assert
    assertEquals("Just cause", actualCloseReason.getReasonPhrase());
    assertNull(actualCloseCode);
  }
}
