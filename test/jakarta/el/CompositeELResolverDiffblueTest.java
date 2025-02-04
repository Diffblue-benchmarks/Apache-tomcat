package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.jasper.el.ELContextImpl;
import org.apache.jasper.el.JasperELResolver;
import org.junit.Test;

public class CompositeELResolverDiffblueTest {
  /**
   * Test new {@link CompositeELResolver} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CompositeELResolver}
   */
  @Test
  public void testNewCompositeELResolver() {
    // Arrange, Act and Assert
    assertNull((new CompositeELResolver()).getCommonPropertyType(null, "Base"));
  }

  /**
   * Test {@link CompositeELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenCompositeELResolverAddArrayELResolverWithReadOnlyIsTrue() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new ArrayELResolver(true));

    // Act and Assert
    assertNull(compositeELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link CompositeELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link CompositeELResolver} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenCompositeELResolverAddCompositeELResolver_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new CompositeELResolver());

    // Act and Assert
    assertNull(compositeELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link CompositeELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenCompositeELResolver_whenELContextImpl_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();

    // Act and Assert
    assertNull(compositeELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new BeanELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) jasperELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke2() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    StandardELContext context = new StandardELContext(new ExpressionFactoryImpl());
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) jasperELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenCompositeELResolverAddArrayELResolverWithReadOnlyIsTrue() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        compositeELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link BeanELResolver#BeanELResolver()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenCompositeELResolverAddBeanELResolver() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new BeanELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) compositeELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link CompositeELResolver} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenCompositeELResolverAddCompositeELResolver_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new CompositeELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        compositeELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor) add {@link OptionalELResolver} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenCompositeELResolverAddOptionalELResolver_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    compositeELResolver.add(new OptionalELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        compositeELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>When {@code Method}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_givenCompositeELResolver_whenMethod_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(
        compositeELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_thenELContextImplPropertyResolved() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) jasperELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl(ELResolver)} with resolver is {@link ArrayELResolver#ArrayELResolver(boolean)} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke_thenELContextImplWithResolverIsArrayELResolverPropertyResolved() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl(new ArrayELResolver(true));
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertFalse((Boolean) jasperELResolver.invoke(context, "Base", "equals", new Class[]{forNameResult},
        new Object[]{"Params"}));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenCompositeELResolver_whenBase_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(compositeELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenHashMap_thenELContextImplPropertyResolved() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualType = jasperELResolver.getType(context, new HashMap<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    Class<Object> expectedType = Object.class;
    assertEquals(expectedType, actualType);
  }

  /**
   * Test {@link CompositeELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenCompositeELResolver_thenNotELContextImplPropertyResolved() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    compositeELResolver.setValue(context, "Base", "Property", "Value");

    // Assert that nothing has changed
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link HashMap#HashMap()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenHashMap_thenHashMapSizeIsOne() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    jasperELResolver.setValue(context, objectObjectMap, "Property", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenCompositeELResolver_whenELContextImpl_thenReturnFalse() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(compositeELResolver.isReadOnly(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));
    ELContextImpl context = new ELContextImpl();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertTrue(jasperELResolver.isReadOnly(context, new ELClass(clazz), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new BeanELResolver());

    // Act
    Class<?> actualCommonPropertyType = jasperELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType2() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new CompositeELResolver());

    // Act
    Class<?> actualCommonPropertyType = jasperELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_givenArrayELResolverWithReadOnlyIsTrue() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new ArrayELResolver(true));

    // Act
    Class<?> actualCommonPropertyType = jasperELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_givenCompositeELResolver_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();

    // Act and Assert
    assertNull(compositeELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link CompositeELResolver#convertToType(ELContext, Object, Class)}.
   * <p>
   * Method under test: {@link CompositeELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new CompositeELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(jasperELResolver.convertToType(context, "Obj", type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>Given {@link CompositeELResolver} (default constructor).</li>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_givenCompositeELResolver_whenObj_thenReturnNull() {
    // Arrange
    CompositeELResolver compositeELResolver = new CompositeELResolver();
    ELContextImpl context = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(compositeELResolver.convertToType(context, "Obj", type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_thenReturnNull() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new OptionalELResolver());
    ELContextImpl context = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(jasperELResolver.convertToType(context, "Obj", type));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link CompositeELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@link Optional} with {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositeELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenOptionalWith42_thenReturn42() {
    // Arrange
    ArrayList<ELResolver> appResolvers = new ArrayList<>();
    JasperELResolver jasperELResolver = new JasperELResolver(appResolvers, new OptionalELResolver());
    ELContextImpl context = new ELContextImpl();
    Optional<Object> ofResult = Optional.of("42");
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("42", jasperELResolver.convertToType(context, ofResult, type));
    assertTrue(context.isPropertyResolved());
  }
}
