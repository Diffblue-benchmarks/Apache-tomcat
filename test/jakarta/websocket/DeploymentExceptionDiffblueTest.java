package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class DeploymentExceptionDiffblueTest {
  /**
   * Test {@link DeploymentException#DeploymentException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeploymentException#DeploymentException(String)}
   */
  @Test
  public void testNewDeploymentException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    DeploymentException actualDeploymentException = new DeploymentException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDeploymentException.getMessage());
    assertNull(actualDeploymentException.getCause());
    assertEquals(0, actualDeploymentException.getSuppressed().length);
  }

  /**
   * Test {@link DeploymentException#DeploymentException(String, Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Cause is {@link Throwable#Throwable()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeploymentException#DeploymentException(String, Throwable)}
   */
  @Test
  public void testNewDeploymentException_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DeploymentException actualDeploymentException = new DeploymentException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDeploymentException.getMessage());
    assertEquals(0, actualDeploymentException.getSuppressed().length);
    assertSame(cause, actualDeploymentException.getCause());
  }
}
