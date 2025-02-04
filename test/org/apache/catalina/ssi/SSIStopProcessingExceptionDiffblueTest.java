package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class SSIStopProcessingExceptionDiffblueTest {
  /**
   * Test {@link SSIStopProcessingException#SSIStopProcessingException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIStopProcessingException#SSIStopProcessingException(Throwable)}
   */
  @Test
  public void testNewSSIStopProcessingException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SSIStopProcessingException actualSsiStopProcessingException = new SSIStopProcessingException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualSsiStopProcessingException.getMessage());
    assertEquals(0, actualSsiStopProcessingException.getSuppressed().length);
    assertSame(cause, actualSsiStopProcessingException.getCause());
  }

  /**
   * Test {@link SSIStopProcessingException#SSIStopProcessingException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIStopProcessingException#SSIStopProcessingException()}
   */
  @Test
  public void testNewSSIStopProcessingException_thenReturnMessageIsNull() {
    // Arrange and Act
    SSIStopProcessingException actualSsiStopProcessingException = new SSIStopProcessingException();

    // Assert
    assertNull(actualSsiStopProcessingException.getMessage());
    assertNull(actualSsiStopProcessingException.getCause());
    assertEquals(0, actualSsiStopProcessingException.getSuppressed().length);
  }
}
