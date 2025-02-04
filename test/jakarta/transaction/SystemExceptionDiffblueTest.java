package jakarta.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SystemExceptionDiffblueTest {
  /**
   * Test {@link SystemException#SystemException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemException#SystemException()}
   */
  @Test
  public void testNewSystemException_thenReturnMessageIsNull() {
    // Arrange and Act
    SystemException actualSystemException = new SystemException();

    // Assert
    assertNull(actualSystemException.getMessage());
    assertNull(actualSystemException.getCause());
    assertEquals(0, actualSystemException.getSuppressed().length);
  }

  /**
   * Test {@link SystemException#SystemException(String)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   *   <li>Then return Message is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemException#SystemException(String)}
   */
  @Test
  public void testNewSystemException_whenFoo_thenReturnMessageIsFoo() {
    // Arrange and Act
    SystemException actualSystemException = new SystemException("foo");

    // Assert
    assertEquals("foo", actualSystemException.getMessage());
    assertNull(actualSystemException.getCause());
    assertEquals(0, actualSystemException.getSuppressed().length);
  }

  /**
   * Test {@link SystemException#SystemException(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemException#SystemException(int)}
   */
  @Test
  public void testNewSystemException_whenOne_thenReturnMessageIsNull() {
    // Arrange and Act
    SystemException actualSystemException = new SystemException(1);

    // Assert
    assertNull(actualSystemException.getMessage());
    assertNull(actualSystemException.getCause());
    assertEquals(0, actualSystemException.getSuppressed().length);
  }
}
