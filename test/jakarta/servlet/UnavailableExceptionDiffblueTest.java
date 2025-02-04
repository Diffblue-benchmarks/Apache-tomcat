package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UnavailableExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UnavailableException#UnavailableException(String)}
   *   <li>{@link UnavailableException#isPermanent()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    UnavailableException actualUnavailableException = new UnavailableException("Msg");
    boolean actualIsPermanentResult = actualUnavailableException.isPermanent();

    // Assert
    assertEquals("Msg", actualUnavailableException.getMessage());
    assertNull(actualUnavailableException.getCause());
    assertEquals(0, actualUnavailableException.getSuppressed().length);
    assertTrue(actualIsPermanentResult);
  }

  /**
   * Test {@link UnavailableException#UnavailableException(String, int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return UnavailableSeconds is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnavailableException#UnavailableException(String, int)}
   */
  @Test
  public void testNewUnavailableException_whenOne_thenReturnUnavailableSecondsIsOne() {
    // Arrange and Act
    UnavailableException actualUnavailableException = new UnavailableException("Msg", 1);

    // Assert
    assertEquals("Msg", actualUnavailableException.getLocalizedMessage());
    assertEquals("Msg", actualUnavailableException.getMessage());
    assertNull(actualUnavailableException.getRootCause());
    assertNull(actualUnavailableException.getCause());
    assertEquals(0, actualUnavailableException.getSuppressed().length);
    assertEquals(1, actualUnavailableException.getUnavailableSeconds());
    assertFalse(actualUnavailableException.isPermanent());
  }

  /**
   * Test {@link UnavailableException#UnavailableException(String, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return UnavailableSeconds is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnavailableException#UnavailableException(String, int)}
   */
  @Test
  public void testNewUnavailableException_whenZero_thenReturnUnavailableSecondsIsMinusOne() {
    // Arrange and Act
    UnavailableException actualUnavailableException = new UnavailableException("Msg", 0);

    // Assert
    assertEquals("Msg", actualUnavailableException.getLocalizedMessage());
    assertEquals("Msg", actualUnavailableException.getMessage());
    assertNull(actualUnavailableException.getRootCause());
    assertNull(actualUnavailableException.getCause());
    assertEquals(-1, actualUnavailableException.getUnavailableSeconds());
    assertEquals(0, actualUnavailableException.getSuppressed().length);
    assertFalse(actualUnavailableException.isPermanent());
  }

  /**
   * Test {@link UnavailableException#getUnavailableSeconds()}.
   * <ul>
   *   <li>Given {@link UnavailableException#UnavailableException(String)} with {@code Msg}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnavailableException#getUnavailableSeconds()}
   */
  @Test
  public void testGetUnavailableSeconds_givenUnavailableExceptionWithMsg_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new UnavailableException("Msg")).getUnavailableSeconds());
  }

  /**
   * Test {@link UnavailableException#getUnavailableSeconds()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnavailableException#getUnavailableSeconds()}
   */
  @Test
  public void testGetUnavailableSeconds_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new UnavailableException("Msg", 1)).getUnavailableSeconds());
  }
}
