package jakarta.security.auth.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.MessagePolicy.ProtectionPolicy;
import jakarta.security.auth.message.MessagePolicy.Target;
import jakarta.security.auth.message.MessagePolicy.TargetPolicy;
import org.junit.Test;

public class MessagePolicyDiffblueTest {
  /**
   * Test {@link MessagePolicy#MessagePolicy(TargetPolicy[], boolean)}.
   * <ul>
   *   <li>When array of {@link TargetPolicy} with {@code null}.</li>
   *   <li>Then return Mandatory.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessagePolicy#MessagePolicy(TargetPolicy[], boolean)}
   */
  @Test
  public void testNewMessagePolicy_whenArrayOfTargetPolicyWithNull_thenReturnMandatory() {
    // Arrange
    TargetPolicy[] targetPolicies = new TargetPolicy[]{null};

    // Act
    MessagePolicy actualMessagePolicy = new MessagePolicy(targetPolicies, true);

    // Assert
    assertTrue(actualMessagePolicy.isMandatory());
    assertSame(targetPolicies, actualMessagePolicy.getTargetPolicies());
  }

  /**
   * Test {@link MessagePolicy#MessagePolicy(TargetPolicy[], boolean)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessagePolicy#MessagePolicy(TargetPolicy[], boolean)}
   */
  @Test
  public void testNewMessagePolicy_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new MessagePolicy(null, true));

  }

  /**
   * Test {@link MessagePolicy#getTargetPolicies()}.
   * <ul>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessagePolicy#getTargetPolicies()}
   */
  @Test
  public void testGetTargetPolicies_thenReturnFirstElementIsNull() {
    // Arrange and Act
    TargetPolicy[] actualTargetPolicies = (new MessagePolicy(new TargetPolicy[]{null}, true)).getTargetPolicies();

    // Assert
    assertNull(actualTargetPolicies[0]);
    assertEquals(1, actualTargetPolicies.length);
  }

  /**
   * Test {@link MessagePolicy#getTargetPolicies()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessagePolicy#getTargetPolicies()}
   */
  @Test
  public void testGetTargetPolicies_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new MessagePolicy(new TargetPolicy[]{}, true)).getTargetPolicies());
  }

  /**
   * Test TargetPolicy {@link TargetPolicy#TargetPolicy(Target[], ProtectionPolicy)}.
   * <p>
   * Method under test: {@link TargetPolicy#TargetPolicy(Target[], ProtectionPolicy)}
   */
  @Test
  public void testTargetPolicyNewTargetPolicy() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new TargetPolicy(new Target[]{null}, null));

  }
}
