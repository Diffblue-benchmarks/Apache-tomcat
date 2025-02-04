package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class LifecycleExceptionDiffblueTest {
  /**
   * Test {@link LifecycleException#LifecycleException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleException#LifecycleException()}
   */
  @Test
  public void testNewLifecycleException_thenReturnMessageIsNull() {
    // Arrange and Act
    LifecycleException actualLifecycleException = new LifecycleException();

    // Assert
    assertNull(actualLifecycleException.getMessage());
    assertNull(actualLifecycleException.getCause());
    assertEquals(0, actualLifecycleException.getSuppressed().length);
  }

  /**
   * Test {@link LifecycleException#LifecycleException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleException#LifecycleException(String)}
   */
  @Test
  public void testNewLifecycleException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    LifecycleException actualLifecycleException = new LifecycleException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualLifecycleException.getMessage());
    assertNull(actualLifecycleException.getCause());
    assertEquals(0, actualLifecycleException.getSuppressed().length);
  }

  /**
   * Test {@link LifecycleException#LifecycleException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleException#LifecycleException(String, Throwable)}
   */
  @Test
  public void testNewLifecycleException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    LifecycleException actualLifecycleException = new LifecycleException("An error occurred", throwable);

    // Assert
    assertEquals("An error occurred", actualLifecycleException.getMessage());
    assertEquals(0, actualLifecycleException.getSuppressed().length);
    assertSame(throwable, actualLifecycleException.getCause());
  }

  /**
   * Test {@link LifecycleException#LifecycleException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleException#LifecycleException(Throwable)}
   */
  @Test
  public void testNewLifecycleException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    LifecycleException actualLifecycleException = new LifecycleException(throwable);

    // Assert
    assertEquals("java.lang.Throwable", actualLifecycleException.getMessage());
    assertEquals(0, actualLifecycleException.getSuppressed().length);
    assertSame(throwable, actualLifecycleException.getCause());
  }
}
