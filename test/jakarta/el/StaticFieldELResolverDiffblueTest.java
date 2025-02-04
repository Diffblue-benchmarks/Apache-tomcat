package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.List;
import java.util.Locale;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class StaticFieldELResolverDiffblueTest {
  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getValue(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getValue(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link TesterEvaluationListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenTesterEvaluationListener() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getValue(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    // Act and Assert
    assertNull(staticFieldELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenThrowPropertyNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getValue(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenOne_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertNull(staticFieldELResolver.getValue(context, new ELClass(clazz), 1));
  }

  /**
   * Test {@link StaticFieldELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> staticFieldELResolver.setValue(context, new ELClass(clazz), "Property", "Value"));
  }

  /**
   * Test {@link StaticFieldELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenELContextImpl_thenThrowPropertyNotWritableException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> staticFieldELResolver.setValue(context, new ELClass(clazz), "Property", "Value"));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenArrayOfClassWithNull_thenThrowMethodNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> staticFieldELResolver.invoke(context, new ELClass(clazz),
        "equals", new Class[]{null}, new Object[]{"Params"}));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@link String}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenArrayOfClassWithString_thenThrowMethodNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    ELClass elClass = new ELClass(clazz);
    Class<String> forNameResult = String.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> staticFieldELResolver.invoke(context, elClass, "equals",
        new Class[]{forNameResult}, new Object[]{"Params"}));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenBase_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        staticFieldELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@link ELClass#ELClass(Class)} with clazz is {@link String}.</li>
   *   <li>Then return {@code Params}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenELClassWithClazzIsString_thenReturnParams() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<String> clazz = String.class;
    ELClass elClass = new ELClass(clazz);
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertEquals("Params",
        staticFieldELResolver.invoke(context, elClass, "<init>", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@link ELClass#ELClass(Class)} with clazz is {@link String}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenELClassWithClazzIsString_thenThrowMethodNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<String> clazz = String.class;
    ELClass elClass = new ELClass(clazz);
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> staticFieldELResolver.invoke(context, elClass, "equals",
        new Class[]{forNameResult}, new Object[]{"Params"}));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code equals}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenEquals_thenThrowMethodNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    ELClass elClass = new ELClass(clazz);
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> staticFieldELResolver.invoke(context, elClass, "equals",
        new Class[]{forNameResult}, new Object[]{"Params"}));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenNull_thenThrowMethodNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> staticFieldELResolver.invoke(context, new ELClass(clazz), "equals", null, new Object[]{"Params"}));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenNull_thenThrowMethodNotFoundException2() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    ELClass elClass = new ELClass(clazz);
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> staticFieldELResolver.invoke(context, elClass, "equals", new Class[]{forNameResult}, null));
  }

  /**
   * Test {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_whenOne_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    ELClass elClass = new ELClass(clazz);
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(staticFieldELResolver.invoke(context, elClass, 1, new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getType(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getType(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link TesterEvaluationListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenTesterEvaluationListener() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getType(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    // Act and Assert
    assertNull(staticFieldELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenThrowPropertyNotFoundException() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> staticFieldELResolver.getType(context, new ELClass(clazz), "Property"));
  }

  /**
   * Test {@link StaticFieldELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenOne_thenReturnNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertNull(staticFieldELResolver.getType(context, new ELClass(clazz), 1));
  }

  /**
   * Test {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Class<Object> clazz = Object.class;

    // Act
    boolean actualIsReadOnlyResult = staticFieldELResolver.isReadOnly(context, new ELClass(clazz), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> clazz = Object.class;
    ELClass elClass = new ELClass(clazz);

    // Act
    staticFieldELResolver.isReadOnly(context, elClass, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    Object base = getResult2.getBase();
    assertTrue(base instanceof ELClass);
    assertTrue(getResult instanceof TesterEvaluationListener);
    assertEquals("Property", getResult2.getProperty());
    Class<Object> expectedKlass = Object.class;
    assertEquals(expectedKlass, ((ELClass) base).getKlass());
    assertSame(elClass, base);
  }

  /**
   * Test {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = staticFieldELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act
    boolean actualIsReadOnlyResult = staticFieldELResolver.isReadOnly(context, new ELClass(clazz), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticFieldELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenOne_thenNotELContextImplPropertyResolved() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act
    boolean actualIsReadOnlyResult = staticFieldELResolver.isReadOnly(context, new ELClass(clazz), 1);

    // Assert
    assertFalse(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link StaticFieldELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link StaticFieldELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    StaticFieldELResolver staticFieldELResolver = new StaticFieldELResolver();

    // Act
    Class<?> actualCommonPropertyType = staticFieldELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }
}
