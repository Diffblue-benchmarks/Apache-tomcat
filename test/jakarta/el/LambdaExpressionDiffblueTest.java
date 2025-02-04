package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.ValueExpressionImpl;
import org.apache.el.ValueExpressionLiteral;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class LambdaExpressionDiffblueTest {
  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(new ELContextImpl());

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs2() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    ArrayList<String> formalParameters = new ArrayList<>();

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());
    lambdaExpression.setELContext(context);

    // Act and Assert
    assertNull(lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs3() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(new TesterELContext());

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs4() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(new StandardELContext(new ExpressionFactoryImpl()));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenArrayELResolverWithReadOnlyIsTrue_thenReturnValue() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(new ELContextImpl(new ArrayELResolver(true)));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>When {@code Args}.</li>
   *   <li>Then throw {@link ELException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenArrayListAddEmptyString_whenArgs_thenThrowELException() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("");
    formalParameters.add("42");

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionImpl());
    lambdaExpression.setELContext(new ELContextImpl());

    // Act and Assert
    assertThrows(ELException.class, () -> lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>When {@code Args}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenArrayListAddFoo_whenArgs_thenReturnValue() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("foo");
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(new ELContextImpl());

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenELContextImplAddEvaluationListenerNull_thenReturnNull() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    ArrayList<String> formalParameters = new ArrayList<>();

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());
    lambdaExpression.setELContext(context);

    // Act and Assert
    assertNull(lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   *   <li>Then throw {@link ELException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenELContextImplLocaleIsDefault_thenThrowELException() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("");
    formalParameters.add("42");

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionImpl());
    lambdaExpression.setELContext(context);

    // Act and Assert
    assertThrows(ELException.class, () -> lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()} PropertyResolved is {@code true}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_givenELContextImplPropertyResolvedIsTrue_thenReturnValue() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    context.setPropertyResolved(true);
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));
    lambdaExpression.setELContext(context);

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Then return {@link LambdaExpression}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_thenReturnLambdaExpression() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    ArrayList<String> formalParameters2 = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters2, new ValueExpressionImpl());

    Class<Object> expectedType = Object.class;

    LambdaExpression lambdaExpression2 = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral(lambdaExpression, expectedType));
    lambdaExpression2.setELContext(new ELContextImpl());

    // Act
    Object actualInvokeResult = lambdaExpression2.invoke("Args");

    // Assert
    assertTrue(actualInvokeResult instanceof LambdaExpression);
    assertSame(lambdaExpression, actualInvokeResult);
  }

  /**
   * Test {@link LambdaExpression#invoke(Object[])} with {@code args}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(Object[])}
   */
  @Test
  public void testInvokeWithArgs_thenReturnNull() {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();

    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());
    lambdaExpression.setELContext(new ELContextImpl());

    // Act and Assert
    assertNull(lambdaExpression.invoke("Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(new StandardELContext(new ExpressionFactoryImpl()), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then throw {@link ELException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_givenArrayListAdd42_thenThrowELException() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("42");
    formalParameters.add("foo");
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionImpl());

    // Act and Assert
    assertThrows(ELException.class, () -> lambdaExpression.invoke(new ELContextImpl(), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_givenArrayListAddFoo_whenELContextImpl_thenReturnValue() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("foo");
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(new ELContextImpl(), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_givenDefault_whenELContextImplLocaleIsDefault() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    formalParameters.add("42");
    formalParameters.add("foo");
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionImpl());

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());

    // Act and Assert
    assertThrows(ELException.class, () -> lambdaExpression.invoke(context, "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_givenNull_whenELContextImplAddEvaluationListenerNull() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertNull(lambdaExpression.invoke(context, "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} PropertyResolved is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_givenTrue_whenELContextImplPropertyResolvedIsTrue() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    ELContextImpl context = new ELContextImpl();
    context.setPropertyResolved(true);

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(context, "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_thenELContextImplEvaluationListenersSizeIsOne() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act
    Object actualInvokeResult = lambdaExpression.invoke(context, "Args");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    assertNull(actualInvokeResult);
    List<String> afterEvaluationExpressions = ((TesterEvaluationListener) getResult).getAfterEvaluationExpressions();
    assertEquals(1, afterEvaluationExpressions.size());
    assertNull(afterEvaluationExpressions.get(0));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Then return {@link LambdaExpression}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_thenReturnLambdaExpression() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    ArrayList<String> formalParameters2 = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters2, new ValueExpressionImpl());

    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression2 = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral(lambdaExpression, expectedType));

    // Act
    Object actualInvokeResult = lambdaExpression2.invoke(new ELContextImpl(), "Args");

    // Assert
    assertTrue(actualInvokeResult instanceof LambdaExpression);
    assertSame(lambdaExpression, actualInvokeResult);
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_thenReturnNull() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionLiteral());

    // Act and Assert
    assertNull(lambdaExpression.invoke(new ELContextImpl(), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_thenReturnValue() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(new ELContextImpl(), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>When {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_whenArrayELResolverWithReadOnlyIsTrue_thenReturnValue() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(new ELContextImpl(new ArrayELResolver(true)), "Args"));
  }

  /**
   * Test {@link LambdaExpression#invoke(ELContext, Object[])} with {@code context}, {@code args}.
   * <ul>
   *   <li>When {@link TesterELContext#TesterELContext()}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpression#invoke(ELContext, Object[])}
   */
  @Test
  public void testInvokeWithContextArgs_whenTesterELContext_thenReturnValue() throws ELException {
    // Arrange
    ArrayList<String> formalParameters = new ArrayList<>();
    Class<Object> expectedType = Object.class;
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters,
        new ValueExpressionLiteral("Value", expectedType));

    // Act and Assert
    assertEquals("Value", lambdaExpression.invoke(new TesterELContext(), "Args"));
  }
}
