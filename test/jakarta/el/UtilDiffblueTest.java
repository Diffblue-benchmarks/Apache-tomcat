package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.ValueExpressionImpl;
import org.apache.el.stream.StreamELResolverImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class UtilDiffblueTest {
  /**
   * Test {@link Util#message(ELContext, String, Object[])}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#message(ELContext, String, Object[])}
   */
  @Test
  public void testMessage_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());

    // Act and Assert
    assertEquals("Missing Resource: 'Name' for Locale English", Util.message(context, "Name", null));
  }

  /**
   * Test {@link Util#message(ELContext, String, Object[])}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code Missing Resource: 'Name' for Locale English}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#message(ELContext, String, Object[])}
   */
  @Test
  public void testMessage_whenELContextImpl_thenReturnMissingResourceNameForLocaleEnglish() {
    // Arrange, Act and Assert
    assertEquals("Missing Resource: 'Name' for Locale English", Util.message(new ELContextImpl(), "Name", "Props"));
  }

  /**
   * Test {@link Util#message(ELContext, String, Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code Missing Resource: 'Name' for Locale English}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#message(ELContext, String, Object[])}
   */
  @Test
  public void testMessage_whenNull_thenReturnMissingResourceNameForLocaleEnglish() {
    // Arrange, Act and Assert
    assertEquals("Missing Resource: 'Name' for Locale English", Util.message(null, "Name", null));
  }

  /**
   * Test {@link Util#getExpressionFactory()}.
   * <p>
   * Method under test: {@link Util#getExpressionFactory()}
   */
  @Test
  public void testGetExpressionFactory() {
    // Arrange and Act
    ExpressionFactory actualExpressionFactory = Util.getExpressionFactory();

    // Assert
    assertTrue(actualExpressionFactory instanceof ExpressionFactoryImpl);
    assertTrue(actualExpressionFactory.getStreamELResolver() instanceof StreamELResolverImpl);
    assertNull(actualExpressionFactory.getInitFunctionMap());
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;
    ArrayList<String> formalParameters = new ArrayList<>();

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "wait",
        new Class[]{forNameResult}, new Object[]{new LambdaExpression(formalParameters, new ValueExpressionImpl())}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenArrayELResolverWithReadOnlyIsTrue() {
    // Arrange
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "wait",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenArrayOfClassWithNull_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", "Method Name", new Class[]{null}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When empty array of {@link Class}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenEmptyArrayOfClass_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", "Method Name", new Class[]{}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When empty array of {@link Object}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenEmptyArrayOfObject_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", "equals", new Class[]{forNameResult}, new Object[]{}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Method Name}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenMethodName_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "Method Name",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Method Name}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenMethodName_thenThrowMethodNotFoundException2() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, null, "Base", "Method Name",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Method Name}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenMethodName_thenThrowMethodNotFoundException3() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", "Method Name", null, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code Method Name}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenMethodName_thenThrowMethodNotFoundException4() {
    // Arrange, Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(new ELContextImpl(), null, "Base", "Method Name", null, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenNull_thenReturnNull() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;
    Class<?>[] paramTypes = new Class[]{forNameResult};

    // Act and Assert
    assertNull(Util.findMethod(context, clazz, null, "equals", paramTypes, new Object[]{"Param Values"}));
    assertEquals(1, paramTypes.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, paramTypes[0]);
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenNull_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", null, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@link StandardELContext#StandardELContext(ExpressionFactory)} with factory is {@link ExpressionFactoryImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenStandardELContextWithFactoryIsExpressionFactoryImpl() {
    // Arrange
    StandardELContext context = new StandardELContext(new ExpressionFactoryImpl());
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "wait",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@link TesterELContext#TesterELContext()}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenTesterELContext_thenThrowMethodNotFoundException() {
    // Arrange
    TesterELContext context = new TesterELContext();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "wait",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code toString}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenToString_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "toString",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code wait}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenWait_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findMethod(context, clazz, "Base", "wait",
        new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}.
   * <ul>
   *   <li>When {@code wait}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findMethod(ELContext, Class, Object, String, Class[], Object[])}
   */
  @Test
  public void testFindMethod_whenWait_thenThrowMethodNotFoundException2() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findMethod(context, clazz, "Base", "wait", new Class[]{forNameResult}, null));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> src = Object.class;
    Class<Object> target = Object.class;

    // Act and Assert
    assertTrue(Util.isAssignableFrom(src, target));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(Util.isAssignableFrom(null, Boolean.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Boolean#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Boolean.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Character#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse2() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Character.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Byte#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse3() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Byte.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Short#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse4() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Short.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Integer#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse5() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Integer.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Long#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse6() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Long.TYPE));
  }

  /**
   * Test {@link Util#isAssignableFrom(Class, Class)}.
   * <ul>
   *   <li>When {@link Float#TYPE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#isAssignableFrom(Class, Class)}
   */
  @Test
  public void testIsAssignableFrom_whenType_thenReturnFalse7() {
    // Arrange
    Class<Object> src = Object.class;

    // Act and Assert
    assertFalse(Util.isAssignableFrom(src, Float.TYPE));
  }

  /**
   * Test {@link Util#getMethod(Class, Object, Method)}.
   * <p>
   * Method under test: {@link Util#getMethod(Class, Object, Method)}
   */
  @Test
  public void testGetMethod() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Util.getMethod(type, "Base", null));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenArrayELResolverWithReadOnlyIsTrue() {
    // Arrange
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Class<Boolean> clazz = Boolean.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenArrayOfClassWithNull_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{null}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenArrayOfClassWithNull_thenThrowMethodNotFoundException2() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Boolean> clazz = Boolean.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{null}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Class} with {@link Object}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenArrayOfClassWithObject_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Boolean> clazz = Boolean.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When array of {@link Object} with {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenArrayOfObjectWithNull_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class, () -> Util.findConstructor(context, clazz, null, new Object[]{null}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When empty array of {@link Class}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenEmptyArrayOfClass_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenNull_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, null, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenNull_thenThrowMethodNotFoundException2() {
    // Arrange, Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(new ELContextImpl(), null, null, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenNull_thenThrowMethodNotFoundException3() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Boolean> clazz = Boolean.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, null));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@link Object}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenObject_thenThrowMethodNotFoundException() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@link Object}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenObject_thenThrowMethodNotFoundException2() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, null, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@link StandardELContext#StandardELContext(ExpressionFactory)} with factory is {@link ExpressionFactoryImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenStandardELContextWithFactoryIsExpressionFactoryImpl() {
    // Arrange
    StandardELContext context = new StandardELContext(new ExpressionFactoryImpl());
    Class<Boolean> clazz = Boolean.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#findConstructor(ELContext, Class, Class[], Object[])}.
   * <ul>
   *   <li>When {@link TesterELContext#TesterELContext()}.</li>
   *   <li>Then throw {@link MethodNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#findConstructor(ELContext, Class, Class[], Object[])}
   */
  @Test
  public void testFindConstructor_whenTesterELContext_thenThrowMethodNotFoundException() {
    // Arrange
    TesterELContext context = new TesterELContext();
    Class<Boolean> clazz = Boolean.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(MethodNotFoundException.class,
        () -> Util.findConstructor(context, clazz, new Class[]{forNameResult}, new Object[]{"Param Values"}));
  }

  /**
   * Test {@link Util#buildParameters(ELContext, Class[], boolean, Object[])}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>Then return first element is {@code Params}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#buildParameters(ELContext, Class[], boolean, Object[])}
   */
  @Test
  public void testBuildParameters_givenFalse_thenReturnFirstElementIsParams() {
    // Arrange
    ELContextImpl context = new ELContextImpl();
    context.setPropertyResolved(false);
    Class<Object> forNameResult = Object.class;

    // Act
    Object[] actualBuildParametersResult = Util.buildParameters(context, new Class[]{forNameResult}, false,
        new Object[]{"Params"});

    // Assert
    assertEquals("Params", actualBuildParametersResult[0]);
    assertEquals(1, actualBuildParametersResult.length);
  }

  /**
   * Test {@link Util#buildParameters(ELContext, Class[], boolean, Object[])}.
   * <ul>
   *   <li>When empty array of {@link Class}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Util#buildParameters(ELContext, Class[], boolean, Object[])}
   */
  @Test
  public void testBuildParameters_whenEmptyArrayOfClass_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Util.buildParameters(new ELContextImpl(), new Class[]{}, true, new Object[]{"Params"}));
  }
}
