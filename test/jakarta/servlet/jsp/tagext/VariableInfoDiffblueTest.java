package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VariableInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link VariableInfo#VariableInfo(String, String, boolean, int)}
   *   <li>{@link VariableInfo#getClassName()}
   *   <li>{@link VariableInfo#getDeclare()}
   *   <li>{@link VariableInfo#getScope()}
   *   <li>{@link VariableInfo#getVarName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    VariableInfo actualVariableInfo = new VariableInfo("Var Name", "Class Name", true, 1);
    String actualClassName = actualVariableInfo.getClassName();
    boolean actualDeclare = actualVariableInfo.getDeclare();
    int actualScope = actualVariableInfo.getScope();

    // Assert
    assertEquals("Class Name", actualClassName);
    assertEquals("Var Name", actualVariableInfo.getVarName());
    assertEquals(1, actualScope);
    assertTrue(actualDeclare);
  }
}
