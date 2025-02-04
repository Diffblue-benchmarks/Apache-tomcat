package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import javax.security.auth.Subject;
import org.junit.Test;

public class GroupPrincipalCallbackDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GroupPrincipalCallback#GroupPrincipalCallback(Subject, String[])}
   *   <li>{@link GroupPrincipalCallback#getGroups()}
   *   <li>{@link GroupPrincipalCallback#getSubject()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Subject subject = new Subject();
    String[] groups = new String[]{"Groups"};

    // Act
    GroupPrincipalCallback actualGroupPrincipalCallback = new GroupPrincipalCallback(subject, groups);
    String[] actualGroups = actualGroupPrincipalCallback.getGroups();

    // Assert
    assertSame(subject, actualGroupPrincipalCallback.getSubject());
    assertSame(groups, actualGroups);
    assertArrayEquals(new String[]{"Groups"}, actualGroups);
  }
}
