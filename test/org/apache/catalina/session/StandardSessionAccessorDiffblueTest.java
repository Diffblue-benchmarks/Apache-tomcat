package org.apache.catalina.session;

import static org.junit.Assert.assertThrows;
import java.util.function.Consumer;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.session.TestStandardSessionAccessor.SessionUpdater;
import org.junit.Test;

public class StandardSessionAccessorDiffblueTest {
  /**
   * Test {@link StandardSessionAccessor#access(Consumer)}.
   * <p>
   * Method under test: {@link StandardSessionAccessor#access(Consumer)}
   */
  @Test
  public void testAccess() {
    // Arrange
    StandardSessionAccessor standardSessionAccessor = new StandardSessionAccessor(new BackupManager(), "42");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> standardSessionAccessor.access(new SessionUpdater()::accept));
  }
}
