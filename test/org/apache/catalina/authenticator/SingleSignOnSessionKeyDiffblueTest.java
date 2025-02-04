package org.apache.catalina.authenticator;

import static org.junit.Assert.assertNull;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class SingleSignOnSessionKeyDiffblueTest {
  /**
   * Test {@link SingleSignOnSessionKey#SingleSignOnSessionKey(Session)}.
   * <ul>
   *   <li>Then return ContextName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleSignOnSessionKey#SingleSignOnSessionKey(Session)}
   */
  @Test
  public void testNewSingleSignOnSessionKey_thenReturnContextNameIsNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());

    BackupManager manager = new BackupManager();
    manager.setContext(context);

    // Act
    SingleSignOnSessionKey actualSingleSignOnSessionKey = new SingleSignOnSessionKey(new DeltaSession(manager));

    // Assert
    assertNull(actualSingleSignOnSessionKey.getContextName());
    assertNull(actualSingleSignOnSessionKey.getHostName());
    assertNull(actualSingleSignOnSessionKey.getSessionId());
  }
}
