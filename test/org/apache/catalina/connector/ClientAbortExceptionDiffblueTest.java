package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class ClientAbortExceptionDiffblueTest {
  /**
   * Test {@link ClientAbortException#ClientAbortException(String)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientAbortException#ClientAbortException(String)}
   */
  @Test
  public void testNewClientAbortException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    ClientAbortException actualClientAbortException = new ClientAbortException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualClientAbortException.getMessage());
    assertNull(actualClientAbortException.getCause());
    assertEquals(0, actualClientAbortException.getSuppressed().length);
  }

  /**
   * Test {@link ClientAbortException#ClientAbortException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientAbortException#ClientAbortException(String, Throwable)}
   */
  @Test
  public void testNewClientAbortException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    ClientAbortException actualClientAbortException = new ClientAbortException("An error occurred", throwable);

    // Assert
    assertEquals("An error occurred", actualClientAbortException.getMessage());
    assertEquals(0, actualClientAbortException.getSuppressed().length);
    assertSame(throwable, actualClientAbortException.getCause());
  }

  /**
   * Test {@link ClientAbortException#ClientAbortException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientAbortException#ClientAbortException()}
   */
  @Test
  public void testNewClientAbortException_thenReturnMessageIsNull() {
    // Arrange and Act
    ClientAbortException actualClientAbortException = new ClientAbortException();

    // Assert
    assertNull(actualClientAbortException.getMessage());
    assertNull(actualClientAbortException.getCause());
    assertEquals(0, actualClientAbortException.getSuppressed().length);
  }

  /**
   * Test {@link ClientAbortException#ClientAbortException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientAbortException#ClientAbortException(Throwable)}
   */
  @Test
  public void testNewClientAbortException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    ClientAbortException actualClientAbortException = new ClientAbortException(throwable);

    // Assert
    assertEquals("java.lang.Throwable", actualClientAbortException.getMessage());
    assertEquals(0, actualClientAbortException.getSuppressed().length);
    assertSame(throwable, actualClientAbortException.getCause());
  }
}
