package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.ValueExpressionImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class OptionalELResolverDiffblueTest {
  /**
   * Test {@link OptionalELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link ArrayELResolver#ArrayELResolver(boolean)} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_thenELContextImplWithResolverIsArrayELResolverPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertNull(optionalELResolver.getValue(context, ofResult, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(optionalELResolver.getValue(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When empty.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenEmpty_thenELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> emptyResult = Optional.empty();

    // Act and Assert
    assertNull(optionalELResolver.getValue(context, emptyResult, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenNull_thenReturn42() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertEquals("42", optionalELResolver.getValue(context, ofResult, null));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link Optional} with {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenOptionalWithHashMap_thenELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of(new HashMap<>());

    // Act and Assert
    assertNull(optionalELResolver.getValue(context, ofResult, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertNull(optionalELResolver.getType(context, ofResult, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Optional<Object> ofResult = Optional.of("42");

    // Act
    optionalELResolver.getType(context, ofResult, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(ofResult, getResult2.getBase());
  }

  /**
   * Test {@link OptionalELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertNull(optionalELResolver.getType(context, ofResult, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(optionalELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> optionalELResolver.setValue(context, ofResult, "Property", "Value"));
  }

  /**
   * Test {@link OptionalELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenELContextImpl_thenThrowPropertyNotWritableException() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> optionalELResolver.setValue(context, ofResult, "Property", "Value"));
  }

  /**
   * Test {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Optional<Object> ofResult = Optional.of("42");

    // Act
    boolean actualIsReadOnlyResult = optionalELResolver.isReadOnly(context, ofResult, "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Optional<Object> ofResult = Optional.of("42");

    // Act
    optionalELResolver.isReadOnly(context, ofResult, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(ofResult, getResult2.getBase());
  }

  /**
   * Test {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");

    // Act
    boolean actualIsReadOnlyResult = optionalELResolver.isReadOnly(context, ofResult, "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = optionalELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link OptionalELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    // Act and Assert
    assertNull(optionalELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link OptionalELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@link Optional} with {@code 42}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenOptionalWith42_thenReturnObject() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");

    // Act
    Class<?> actualCommonPropertyType = optionalELResolver.getCommonPropertyType(context, ofResult);

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    StandardELContext context = new StandardELContext(new ExpressionFactoryImpl());
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType2() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    ArrayList<String> formalParameters = new ArrayList<>();
    Optional<Object> ofResult = Optional.of(new LambdaExpression(formalParameters, new ValueExpressionImpl()));
    Class<Optional> type = Optional.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, ofResult, type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType3() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl(new OptionalELResolver());
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} PropertyResolved is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_givenTrue_whenELContextImplPropertyResolvedIsTrue() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setPropertyResolved(true);
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link ArrayELResolver#ArrayELResolver(boolean)} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_thenELContextImplWithResolverIsArrayELResolverPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@code Optional}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenJavaUtilOptional_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Optional> type = Optional.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, ofResult, type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenObj_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, "Obj", type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@link Optional} with {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenOptionalWith42_thenReturn42() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("42", optionalELResolver.convertToType(context, ofResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@link TesterELContext#TesterELContext()}.</li>
   *   <li>Then {@link TesterELContext#TesterELContext()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenTesterELContext_thenTesterELContextPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    TesterELContext context = new TesterELContext();
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.convertToType(context, emptyResult, type));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link ArrayELResolver#ArrayELResolver(boolean)} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_thenELContextImplWithResolverIsArrayELResolverPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        optionalELResolver.invoke(context, ofResult, "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        optionalELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When empty.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenEmpty_thenReturnNull() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> emptyResult = Optional.empty();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        optionalELResolver.invoke(context, emptyResult, "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code equals}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenEquals_thenReturnFalse() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) optionalELResolver.invoke(context, ofResult, "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code indexOf}.</li>
   *   <li>Then return intValue is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenIndexOf_thenReturnIntValueIsMinusOne() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertEquals(-1, ((Integer) optionalELResolver.invoke(context, ofResult, "indexOf", new Class[]{forNameResult},
        new Object[]{"Params"})).intValue());
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenNull_thenNotELContextImplPropertyResolved() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(optionalELResolver.invoke(context, ofResult, null, new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code valueOf}.</li>
   *   <li>Then return {@code Params}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenValueOf_thenReturnParams() {
    // Arrange
    OptionalELResolver optionalELResolver = new OptionalELResolver();
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertEquals("Params",
        optionalELResolver.invoke(context, ofResult, "valueOf", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }
}
