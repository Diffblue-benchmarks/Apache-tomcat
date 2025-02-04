package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.BeanELResolver.BeanProperties;
import jakarta.el.BeanELResolver.BeanProperty;
import jakarta.el.BeanSupportFull.BeanPropertyFull;
import jakarta.el.TesterEvaluationListener.Pair;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.ValueExpressionImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class BeanELResolverDiffblueTest {
  /**
   * Test BeanProperty {@link BeanProperty#getPropertyType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanProperty#getPropertyType()}
   */
  @Test
  public void testBeanPropertyGetPropertyType_thenReturnNull() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act and Assert
    assertNull(
        (new BeanPropertyFull(owner, new IndexedPropertyDescriptor("foo", null, null, null, null))).getPropertyType());
  }

  /**
   * Test BeanProperty {@link BeanProperty#isReadOnly(Object)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanProperty#isReadOnly(Object)}
   */
  @Test
  public void testBeanPropertyIsReadOnly_thenReturnTrue() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act and Assert
    assertTrue(
        (new BeanPropertyFull(owner, new IndexedPropertyDescriptor("foo", null, null, null, null))).isReadOnly("Base"));
  }

  /**
   * Test {@link BeanELResolver#BeanELResolver()}.
   * <p>
   * Method under test: {@link BeanELResolver#BeanELResolver()}
   */
  @Test
  public void testNewBeanELResolver() {
    // Arrange and Act
    BeanELResolver actualBeanELResolver = new BeanELResolver();

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualBeanELResolver.getCommonPropertyType(null, "Base"));
  }

  /**
   * Test {@link BeanELResolver#BeanELResolver(boolean)}.
   * <p>
   * Method under test: {@link BeanELResolver#BeanELResolver(boolean)}
   */
  @Test
  public void testNewBeanELResolver2() {
    // Arrange and Act
    BeanELResolver actualBeanELResolver = new BeanELResolver(true);

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualBeanELResolver.getCommonPropertyType(null, "Base"));
  }

  /**
   * Test {@link BeanELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenBeanELResolver_whenNull_thenReturnNull() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act and Assert
    assertNull(beanELResolver.getType(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link BeanELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenBeanELResolver_whenNull_thenReturnNull2() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act and Assert
    assertNull(beanELResolver.getType(new ELContextImpl(), "Base", null));
  }

  /**
   * Test {@link BeanELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act and Assert
    assertNull(beanELResolver.getValue(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link BeanELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull2() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act and Assert
    assertNull(beanELResolver.getValue(new ELContextImpl(), "Base", null));
  }

  /**
   * Test {@link BeanELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_thenThrowPropertyNotWritableException() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver(true);

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> beanELResolver.setValue(new ELContextImpl(), "Base", "Property", "Value"));
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    StandardELContext context = new StandardELContext(new ExpressionFactoryImpl());
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse(
        (Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke2() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;
    ArrayList<String> formalParameters = new ArrayList<>();

    // Act and Assert
    assertFalse((Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{new LambdaExpression(formalParameters, new ValueExpressionImpl())}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When array of {@link Class} with {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenArrayOfClassWithNull_thenReturnFalse() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse((Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{null}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code equals}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenEquals_thenReturnFalse() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse(
        (Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code indexOf}.</li>
   *   <li>Then return intValue is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenIndexOf_thenReturnIntValueIsMinusOne() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertEquals(-1, ((Integer) beanELResolver.invoke(context, "Base", "indexOf", new Class[]{forNameResult},
        new Object[]{"Params"})).intValue());
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code length}.</li>
   *   <li>Then return intValue is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenLength_thenReturnIntValueIsFour() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertEquals(4,
        ((Integer) beanELResolver.invoke(context, "Base", "length", new Class[]{}, new Object[]{"Params"})).intValue());
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenNull_thenReturnNull() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(beanELResolver.invoke(context, null, "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenNull_thenReturnNull2() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(beanELResolver.invoke(context, "Base", null, new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code valueOf}.</li>
   *   <li>Then return {@code Params}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenBeanELResolver_whenValueOf_thenReturnParams() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertEquals("Params",
        beanELResolver.invoke(context, "Base", "valueOf", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> forNameResult = Object.class;

    // Act
    beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"});

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Base", getResult2.getBase());
    assertEquals("equals", getResult2.getProperty());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link ArrayELResolver#ArrayELResolver(boolean)} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_thenELContextImplWithResolverIsArrayELResolverPropertyResolved() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse(
        (Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code BeanELResolver$BeanProperties}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenJakartaElBeanELResolverBeanProperties_thenReturnFalse() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<BeanProperties> forNameResult = BeanProperties.class;

    // Act and Assert
    assertFalse(
        (Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@link TesterELContext#TesterELContext()}.</li>
   *   <li>Then {@link TesterELContext#TesterELContext()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenTesterELContext_thenTesterELContextPropertyResolved() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    TesterELContext context = new TesterELContext();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse(
        (Boolean) beanELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenBeanELResolverWithReadOnlyIsTrue_thenReturnTrue() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertTrue(beanELResolver.isReadOnly(context, "Base", "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenBeanELResolver_whenNull_thenReturnFalse() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(beanELResolver.isReadOnly(context, null, "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link BeanELResolver#BeanELResolver()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenBeanELResolver_whenNull_thenReturnFalse2() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(beanELResolver.isReadOnly(context, "Base", null));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnObject() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act
    Class<?> actualCommonPropertyType = beanELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link BeanELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenNull_thenReturnNull() {
    // Arrange
    BeanELResolver beanELResolver = new BeanELResolver();

    // Act and Assert
    assertNull(beanELResolver.getCommonPropertyType(new ELContextImpl(), null));
  }
}
