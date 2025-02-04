package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ValidationMessageDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ValidationMessage#ValidationMessage(String, String)}
   *   <li>{@link ValidationMessage#getId()}
   *   <li>{@link ValidationMessage#getMessage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ValidationMessage actualValidationMessage = new ValidationMessage("42", "Not all who wander are lost");
    String actualId = actualValidationMessage.getId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Not all who wander are lost", actualValidationMessage.getMessage());
  }
}
