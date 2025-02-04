package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.apache.el.ValueExpressionImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ELContextDiffblueTest {
  /**
   * Test {@link ELContext#setPropertyResolved(Object, Object)} with {@code base}, {@code property}.
   * <p>
   * Method under test: {@link ELContext#setPropertyResolved(Object, Object)}
   */
  @Test
  public void testSetPropertyResolvedWithBaseProperty() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.addEvaluationListener(new TesterEvaluationListener());

    // Act
    elContextImpl.setPropertyResolved("Base", "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Base", getResult2.getBase());
    assertEquals("Property", getResult2.getProperty());
  }

  /**
   * Test {@link ELContext#setPropertyResolved(Object, Object)} with {@code base}, {@code property}.
   * <p>
   * Method under test: {@link ELContext#setPropertyResolved(Object, Object)}
   */
  @Test
  public void testSetPropertyResolvedWithBaseProperty2() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.addEvaluationListener(null);

    // Act
    elContextImpl.setPropertyResolved("Base", "Property");

    // Assert
    assertTrue(elContextImpl.isPropertyResolved());
  }

  /**
   * Test {@link ELContext#setPropertyResolved(Object, Object)} with {@code base}, {@code property}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#setPropertyResolved(Object, Object)}
   */
  @Test
  public void testSetPropertyResolvedWithBaseProperty_givenELContextImpl() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();

    // Act
    elContextImpl.setPropertyResolved("Base", "Property");

    // Assert
    assertTrue(elContextImpl.isPropertyResolved());
  }

  /**
   * Test {@link ELContext#setPropertyResolved(boolean)} with {@code resolved}.
   * <p>
   * Method under test: {@link ELContext#setPropertyResolved(boolean)}
   */
  @Test
  public void testSetPropertyResolvedWithResolved() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();

    // Act
    elContextImpl.setPropertyResolved(true);

    // Assert
    assertTrue(elContextImpl.isPropertyResolved());
  }

  /**
   * Test {@link ELContext#isPropertyResolved()}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()} PropertyResolved is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#isPropertyResolved()}
   */
  @Test
  public void testIsPropertyResolved_givenELContextImplPropertyResolvedIsTrue_thenReturnTrue() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.setPropertyResolved(true);

    // Act and Assert
    assertTrue(elContextImpl.isPropertyResolved());
  }

  /**
   * Test {@link ELContext#isPropertyResolved()}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#isPropertyResolved()}
   */
  @Test
  public void testIsPropertyResolved_givenELContextImpl_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ELContextImpl()).isPropertyResolved());
  }

  /**
   * Test {@link ELContext#getContext(Class)}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_givenELContextImpl_whenJavaLangObject_thenReturnNull() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    Class<Object> key = Object.class;

    // Act and Assert
    assertNull(elContextImpl.getContext(key));
  }

  /**
   * Test {@link ELContext#getContext(Class)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code Context Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_givenJavaLangObject_whenJavaLangObject_thenReturnContextObject() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    Class<Object> key = Object.class;
    elContextImpl.putContext(key, "Context Object");
    Class<Object> key2 = Object.class;

    // Act and Assert
    assertEquals("Context Object", elContextImpl.getContext(key2));
  }

  /**
   * Test {@link ELContext#getImportHandler()}.
   * <p>
   * Method under test: {@link ELContext#getImportHandler()}
   */
  @Test
  public void testGetImportHandler() {
    // Arrange and Act
    ImportHandler actualImportHandler = (new ELContextImpl()).getImportHandler();

    // Assert
    assertNull(actualImportHandler.resolveClass("Name"));
    assertNull(actualImportHandler.resolveStatic("Name"));
  }

  /**
   * Test {@link ELContext#getLocale()}.
   * <p>
   * Method under test: {@link ELContext#getLocale()}
   */
  @Test
  public void testGetLocale() {
    // Arrange, Act and Assert
    assertNull((new ELContextImpl()).getLocale());
  }

  /**
   * Test {@link ELContext#setLocale(Locale)}.
   * <p>
   * Method under test: {@link ELContext#setLocale(Locale)}
   */
  @Test
  public void testSetLocale() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    Locale locale = Locale.getDefault();

    // Act
    elContextImpl.setLocale(locale);

    // Assert
    Locale expectedLocale = locale.ENGLISH;
    assertSame(expectedLocale, elContextImpl.getLocale());
  }

  /**
   * Test {@link ELContext#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    TesterEvaluationListener listener = new TesterEvaluationListener();

    // Act
    elContextImpl.addEvaluationListener(listener);

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    assertSame(listener, evaluationListeners.get(0));
  }

  /**
   * Test {@link ELContext#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_thenELContextImplEvaluationListenersSizeIsTwo() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    TesterEvaluationListener listener = new TesterEvaluationListener();
    elContextImpl.addEvaluationListener(listener);
    TesterEvaluationListener listener2 = new TesterEvaluationListener();

    // Act
    elContextImpl.addEvaluationListener(listener2);

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(2, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    assertSame(listener, getResult);
    assertSame(listener2, evaluationListeners.get(1));
  }

  /**
   * Test {@link ELContext#getEvaluationListeners()}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#getEvaluationListeners()}
   */
  @Test
  public void testGetEvaluationListeners_givenELContextImpl_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new ELContextImpl()).getEvaluationListeners().isEmpty());
  }

  /**
   * Test {@link ELContext#getEvaluationListeners()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#getEvaluationListeners()}
   */
  @Test
  public void testGetEvaluationListeners_thenReturnSizeIsOne() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    TesterEvaluationListener listener = new TesterEvaluationListener();
    elContextImpl.addEvaluationListener(listener);

    // Act
    List<EvaluationListener> actualEvaluationListeners = elContextImpl.getEvaluationListeners();

    // Assert
    assertEquals(1, actualEvaluationListeners.size());
    assertSame(listener, actualEvaluationListeners.get(0));
  }

  /**
   * Test {@link ELContext#notifyBeforeEvaluation(String)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#notifyBeforeEvaluation(String)}
   */
  @Test
  public void testNotifyBeforeEvaluation_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.addEvaluationListener(new TesterEvaluationListener());

    // Act
    elContextImpl.notifyBeforeEvaluation("Expression");

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<String> beforeEvaluationExpressions = ((TesterEvaluationListener) getResult).getBeforeEvaluationExpressions();
    assertEquals(1, beforeEvaluationExpressions.size());
    assertEquals("Expression", beforeEvaluationExpressions.get(0));
  }

  /**
   * Test {@link ELContext#notifyAfterEvaluation(String)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#notifyAfterEvaluation(String)}
   */
  @Test
  public void testNotifyAfterEvaluation_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.addEvaluationListener(new TesterEvaluationListener());

    // Act
    elContextImpl.notifyAfterEvaluation("Expression");

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<String> afterEvaluationExpressions = ((TesterEvaluationListener) getResult).getAfterEvaluationExpressions();
    assertEquals(1, afterEvaluationExpressions.size());
    assertEquals("Expression", afterEvaluationExpressions.get(0));
  }

  /**
   * Test {@link ELContext#notifyPropertyResolved(Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#notifyPropertyResolved(Object, Object)}
   */
  @Test
  public void testNotifyPropertyResolved_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.addEvaluationListener(new TesterEvaluationListener());

    // Act
    elContextImpl.notifyPropertyResolved("Base", "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = elContextImpl.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Base", getResult2.getBase());
    assertEquals("Property", getResult2.getProperty());
  }

  /**
   * Test {@link ELContext#isLambdaArgument(String)}.
   * <p>
   * Method under test: {@link ELContext#isLambdaArgument(String)}
   */
  @Test
  public void testIsLambdaArgument() {
    // Arrange, Act and Assert
    assertFalse((new ELContextImpl()).isLambdaArgument("Name"));
  }

  /**
   * Test {@link ELContext#getLambdaArgument(String)}.
   * <p>
   * Method under test: {@link ELContext#getLambdaArgument(String)}
   */
  @Test
  public void testGetLambdaArgument() {
    // Arrange, Act and Assert
    assertNull((new ELContextImpl()).getLambdaArgument("Name"));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenArrayELResolverWithReadOnlyIsTrue_whenObj_thenReturnObj() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl(new ArrayELResolver(true));
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()} PropertyResolved is {@code true}.</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenELContextImplPropertyResolvedIsTrue_whenObj_thenReturnObj() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    elContextImpl.setPropertyResolved(true);
    Class<Object> key = Object.class;
    elContextImpl.putContext(key, "Context Object");
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@code null}.</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenELContextImplWithResolverIsNull_whenObj_thenReturnObj() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl(null);
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link OptionalELResolver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenELContextImplWithResolverIsOptionalELResolver() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl(new OptionalELResolver());
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenELContextImpl_whenObj_thenReturnObj() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_givenJavaLangObject_whenObj_thenReturnObj() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    Class<Object> key = Object.class;
    elContextImpl.putContext(key, "Context Object");
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("Obj", elContextImpl.convertToType("Obj", type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>Then return {@link LambdaExpression}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_thenReturnLambdaExpression() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl();
    ArrayList<String> formalParameters = new ArrayList<>();
    LambdaExpression lambdaExpression = new LambdaExpression(formalParameters, new ValueExpressionImpl());

    Class<Object> type = Object.class;

    // Act
    Object actualConvertToTypeResult = elContextImpl.convertToType(lambdaExpression, type);

    // Assert
    assertTrue(actualConvertToTypeResult instanceof LambdaExpression);
    assertSame(lambdaExpression, actualConvertToTypeResult);
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>When empty.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_whenEmpty_thenReturnNull() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl(new OptionalELResolver());
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(elContextImpl.convertToType(emptyResult, type));
  }

  /**
   * Test {@link ELContext#convertToType(Object, Class)}.
   * <ul>
   *   <li>When {@link Optional} with {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#convertToType(Object, Class)}
   */
  @Test
  public void testConvertToType_whenOptionalWith42_thenReturn42() {
    // Arrange
    ELContextImpl elContextImpl = new ELContextImpl(new OptionalELResolver());
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("42", elContextImpl.convertToType(ofResult, type));
  }

  /**
   * Test {@link ELContext#isFunctionalInterface(Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#isFunctionalInterface(Class)}
   */
  @Test
  public void testIsFunctionalInterface_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertFalse(ELContext.isFunctionalInterface(type));
  }

  /**
   * Test {@link ELContext#isFunctionalInterface(Class)}.
   * <ul>
   *   <li>When {@code Map}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELContext#isFunctionalInterface(Class)}
   */
  @Test
  public void testIsFunctionalInterface_whenJavaUtilMap_thenReturnFalse() {
    // Arrange
    Class<Map> type = Map.class;

    // Act and Assert
    assertFalse(ELContext.isFunctionalInterface(type));
  }
}
