package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.el.ValueExpressionLiteral;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ValueExpressionDiffblueTest {
  /**
   * Test {@link ValueExpression#getValueReference(ELContext)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValueExpression#getValueReference(ELContext)}
   */
  @Test
  public void testGetValueReference_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ValueExpressionLiteral valueExpressionLiteral = new ValueExpressionLiteral();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertNull(valueExpressionLiteral.getValueReference(context));
  }

  /**
   * Test {@link ValueExpression#getValueReference(ELContext)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValueExpression#getValueReference(ELContext)}
   */
  @Test
  public void testGetValueReference_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ValueExpressionLiteral valueExpressionLiteral = new ValueExpressionLiteral();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act
    ValueReference actualValueReference = valueExpressionLiteral.getValueReference(context);

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    assertNull(actualValueReference);
    List<String> afterEvaluationExpressions = ((TesterEvaluationListener) getResult).getAfterEvaluationExpressions();
    assertEquals(1, afterEvaluationExpressions.size());
    assertNull(afterEvaluationExpressions.get(0));
  }

  /**
   * Test {@link ValueExpression#getValueReference(ELContext)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValueExpression#getValueReference(ELContext)}
   */
  @Test
  public void testGetValueReference_whenELContextImpl_thenReturnNull() {
    // Arrange
    ValueExpressionLiteral valueExpressionLiteral = new ValueExpressionLiteral();

    // Act and Assert
    assertNull(valueExpressionLiteral.getValueReference(new ELContextImpl()));
  }
}
