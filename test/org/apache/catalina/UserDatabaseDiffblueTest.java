package org.apache.catalina;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.users.MemoryUserDatabase;
import org.junit.Test;

public class UserDatabaseDiffblueTest {
  /**
   * Test {@link UserDatabase#isAvailable()}.
   * <p>
   * Method under test: {@link UserDatabase#isAvailable()}
   */
  @Test
  public void testIsAvailable() {
    // Arrange, Act and Assert
    assertTrue((new MemoryUserDatabase()).isAvailable());
  }

  /**
   * Test {@link UserDatabase#isSparse()}.
   * <p>
   * Method under test: {@link UserDatabase#isSparse()}
   */
  @Test
  public void testIsSparse() {
    // Arrange, Act and Assert
    assertFalse((new MemoryUserDatabase()).isSparse());
  }
}
