package jakarta.el;

import static org.junit.Assert.assertFalse;
import org.apache.el.MethodExpressionLiteral;
import org.junit.Test;

public class MethodExpressionDiffblueTest {
  /**
   * Test {@link MethodExpression#isParametersProvided()}.
   * <p>
   * Method under test: {@link MethodExpression#isParametersProvided()}
   */
  @Test
  public void testIsParametersProvided() {
    // Arrange, Act and Assert
    assertFalse((new MethodExpressionLiteral()).isParametersProvided());
  }
}
