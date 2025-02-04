package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TagVariableInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagVariableInfo#TagVariableInfo(String, String, String, boolean, int)}
   *   <li>{@link TagVariableInfo#getClassName()}
   *   <li>{@link TagVariableInfo#getDeclare()}
   *   <li>{@link TagVariableInfo#getNameFromAttribute()}
   *   <li>{@link TagVariableInfo#getNameGiven()}
   *   <li>{@link TagVariableInfo#getScope()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    TagVariableInfo actualTagVariableInfo = new TagVariableInfo("Name Given", "jane.doe@example.org", "Class Name",
        true, 1);
    String actualClassName = actualTagVariableInfo.getClassName();
    boolean actualDeclare = actualTagVariableInfo.getDeclare();
    String actualNameFromAttribute = actualTagVariableInfo.getNameFromAttribute();
    String actualNameGiven = actualTagVariableInfo.getNameGiven();

    // Assert
    assertEquals("Class Name", actualClassName);
    assertEquals("Name Given", actualNameGiven);
    assertEquals("jane.doe@example.org", actualNameFromAttribute);
    assertEquals(1, actualTagVariableInfo.getScope());
    assertTrue(actualDeclare);
  }
}
