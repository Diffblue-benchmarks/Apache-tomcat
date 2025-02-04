package org.apache.catalina.tribes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class RemoteProcessExceptionDiffblueTest {
  /**
   * Test {@link RemoteProcessException#RemoteProcessException(String)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteProcessException#RemoteProcessException(String)}
   */
  @Test
  public void testNewRemoteProcessException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    RemoteProcessException actualRemoteProcessException = new RemoteProcessException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualRemoteProcessException.getMessage());
    assertNull(actualRemoteProcessException.getCause());
    assertEquals(0, actualRemoteProcessException.getSuppressed().length);
  }

  /**
   * Test {@link RemoteProcessException#RemoteProcessException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteProcessException#RemoteProcessException(String, Throwable)}
   */
  @Test
  public void testNewRemoteProcessException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    RemoteProcessException actualRemoteProcessException = new RemoteProcessException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualRemoteProcessException.getMessage());
    assertEquals(0, actualRemoteProcessException.getSuppressed().length);
    assertSame(cause, actualRemoteProcessException.getCause());
  }

  /**
   * Test {@link RemoteProcessException#RemoteProcessException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteProcessException#RemoteProcessException()}
   */
  @Test
  public void testNewRemoteProcessException_thenReturnMessageIsNull() {
    // Arrange and Act
    RemoteProcessException actualRemoteProcessException = new RemoteProcessException();

    // Assert
    assertNull(actualRemoteProcessException.getMessage());
    assertNull(actualRemoteProcessException.getCause());
    assertEquals(0, actualRemoteProcessException.getSuppressed().length);
  }

  /**
   * Test {@link RemoteProcessException#RemoteProcessException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoteProcessException#RemoteProcessException(Throwable)}
   */
  @Test
  public void testNewRemoteProcessException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    RemoteProcessException actualRemoteProcessException = new RemoteProcessException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualRemoteProcessException.getMessage());
    assertEquals(0, actualRemoteProcessException.getSuppressed().length);
    assertSame(cause, actualRemoteProcessException.getCause());
  }
}
