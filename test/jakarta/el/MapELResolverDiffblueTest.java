package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class MapELResolverDiffblueTest {
  /**
   * Test {@link MapELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link MapELResolver#MapELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenMapELResolverWithReadOnlyIsFalse_thenReturnObject() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualType = mapELResolver.getType(context, new HashMap<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    Class<Object> expectedType = Object.class;
    assertEquals(expectedType, actualType);
  }

  /**
   * Test {@link MapELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenNull_whenELContextImplAddEvaluationListenerNull_thenReturnNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertNull(mapELResolver.getType(context, new HashMap<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    mapELResolver.getType(context, objectObjectMap, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(objectObjectMap, getResult2.getBase());
  }

  /**
   * Test {@link MapELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(mapELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(mapELResolver.getType(context, new HashMap<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertNull(mapELResolver.getValue(context, new HashMap<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    mapELResolver.getValue(context, objectObjectMap, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(objectObjectMap, getResult2.getBase());
  }

  /**
   * Test {@link MapELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(mapELResolver.getValue(context, new HashMap<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(mapELResolver.getValue(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> mapELResolver.setValue(context, new HashMap<>(), "Property", "Value"));
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@link MapELResolver#MapELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then {@link HashMap#HashMap()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenMapELResolverWithReadOnlyIsFalse_thenHashMapSizeIsOne() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(false);
    ELContextImpl context = new ELContextImpl();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    mapELResolver.setValue(context, objectObjectMap, "Property", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> mapELResolver.setValue(context, new HashMap<>(), "Property", "Value"));
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@link TesterEvaluationListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenTesterEvaluationListener() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> mapELResolver.setValue(context, new HashMap<>(), "Property", "Value"));
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    mapELResolver.setValue(context, "Base", "Property", "Value");

    // Assert that nothing has changed
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenELContextImpl_thenThrowPropertyNotWritableException() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> mapELResolver.setValue(context, new HashMap<>(), "Property", "Value"));
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link MapELResolver#MapELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenMapELResolverWithReadOnlyIsFalse_thenReturnFalse() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(mapELResolver.isReadOnly(context, new HashMap<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act
    boolean actualIsReadOnlyResult = mapELResolver.isReadOnly(context, new HashMap<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    mapELResolver.isReadOnly(context, objectObjectMap, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(objectObjectMap, getResult2.getBase());
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenNotELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = mapELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = mapELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link MapELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = mapELResolver.isReadOnly(context, new HashMap<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link MapELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);

    // Act and Assert
    assertNull(mapELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link MapELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenHashMap_thenReturnObject() {
    // Arrange
    MapELResolver mapELResolver = new MapELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualCommonPropertyType = mapELResolver.getCommonPropertyType(context, new HashMap<>());

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }
}
