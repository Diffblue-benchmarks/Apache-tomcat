package jakarta.el;

import static org.junit.Assert.assertEquals;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.List;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class EvaluationListenerDiffblueTest {
  /**
   * Test {@link EvaluationListener#beforeEvaluation(ELContext, String)}.
   * <p>
   * Method under test: {@link EvaluationListener#beforeEvaluation(ELContext, String)}
   */
  @Test
  public void testBeforeEvaluation() {
    // Arrange
    TesterEvaluationListener testerEvaluationListener = new TesterEvaluationListener();

    // Act
    testerEvaluationListener.beforeEvaluation(new ELContextImpl(), "Expression");

    // Assert
    List<String> beforeEvaluationExpressions = testerEvaluationListener.getBeforeEvaluationExpressions();
    assertEquals(1, beforeEvaluationExpressions.size());
    assertEquals("Expression", beforeEvaluationExpressions.get(0));
  }

  /**
   * Test {@link EvaluationListener#afterEvaluation(ELContext, String)}.
   * <p>
   * Method under test: {@link EvaluationListener#afterEvaluation(ELContext, String)}
   */
  @Test
  public void testAfterEvaluation() {
    // Arrange
    TesterEvaluationListener testerEvaluationListener = new TesterEvaluationListener();

    // Act
    testerEvaluationListener.afterEvaluation(new ELContextImpl(), "Expression");

    // Assert
    List<String> afterEvaluationExpressions = testerEvaluationListener.getAfterEvaluationExpressions();
    assertEquals(1, afterEvaluationExpressions.size());
    assertEquals("Expression", afterEvaluationExpressions.get(0));
  }

  /**
   * Test {@link EvaluationListener#propertyResolved(ELContext, Object, Object)}.
   * <p>
   * Method under test: {@link EvaluationListener#propertyResolved(ELContext, Object, Object)}
   */
  @Test
  public void testPropertyResolved() {
    // Arrange
    TesterEvaluationListener testerEvaluationListener = new TesterEvaluationListener();

    // Act
    testerEvaluationListener.propertyResolved(new ELContextImpl(), "Base", "Property");

    // Assert
    List<Pair> resolvedProperties = testerEvaluationListener.getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult = resolvedProperties.get(0);
    assertEquals("Base", getResult.getBase());
    assertEquals("Property", getResult.getProperty());
  }
}
