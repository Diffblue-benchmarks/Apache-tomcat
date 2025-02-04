package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import validators.DebugValidator;

public class TagLibraryValidatorDiffblueTest {
  /**
   * Test {@link TagLibraryValidator#setInitParameters(Map)}.
   * <p>
   * Method under test: {@link TagLibraryValidator#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters() {
    // Arrange
    DebugValidator debugValidator = new DebugValidator();

    // Act
    debugValidator.setInitParameters(new HashMap<>());

    // Assert
    assertTrue(debugValidator.getInitParameters().isEmpty());
  }

  /**
   * Test {@link TagLibraryValidator#getInitParameters()}.
   * <p>
   * Method under test: {@link TagLibraryValidator#getInitParameters()}
   */
  @Test
  public void testGetInitParameters() {
    // Arrange, Act and Assert
    assertNull((new DebugValidator()).getInitParameters());
  }
}
