package org.apache.catalina;

import static org.junit.Assert.assertTrue;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.junit.Test;

public class RealmDiffblueTest {
  /**
   * Test {@link Realm#isAvailable()}.
   * <p>
   * Method under test: {@link Realm#isAvailable()}
   */
  @Test
  public void testIsAvailable() {
    // Arrange, Act and Assert
    assertTrue((new AuthenticatedUserRealm()).isAvailable());
  }
}
