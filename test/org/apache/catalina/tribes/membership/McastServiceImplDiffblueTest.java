package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertFalse;
import org.apache.catalina.tribes.membership.McastServiceImpl.RecoveryThread;
import org.junit.Test;

public class McastServiceImplDiffblueTest {
  /**
   * Test RecoveryThread {@link RecoveryThread#startService()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecoveryThread#startService()}
   */
  @Test
  public void testRecoveryThreadStartService_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new RecoveryThread(null)).startService());
  }

  /**
   * Test RecoveryThread {@link RecoveryThread#stopService()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecoveryThread#stopService()}
   */
  @Test
  public void testRecoveryThreadStopService_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new RecoveryThread(null)).stopService());
  }
}
