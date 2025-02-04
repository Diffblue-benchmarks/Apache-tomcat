package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FunctionInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FunctionInfo#FunctionInfo(String, String, String)}
   *   <li>{@link FunctionInfo#getFunctionClass()}
   *   <li>{@link FunctionInfo#getFunctionSignature()}
   *   <li>{@link FunctionInfo#getName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    FunctionInfo actualFunctionInfo = new FunctionInfo("Name", "Klass", "Signature");
    String actualFunctionClass = actualFunctionInfo.getFunctionClass();
    String actualFunctionSignature = actualFunctionInfo.getFunctionSignature();

    // Assert
    assertEquals("Klass", actualFunctionClass);
    assertEquals("Name", actualFunctionInfo.getName());
    assertEquals("Signature", actualFunctionSignature);
  }
}
