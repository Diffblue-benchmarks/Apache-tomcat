package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import javax.accessibility.AccessibleResourceBundle;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ResourceBundleELResolverDiffblueTest {
  /**
   * Test {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenNull_whenELContextImplAddEvaluationListenerNull() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertEquals("???Property???", resourceBundleELResolver.getValue(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_thenELContextImplEvaluationListenersSizeIsOne() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    PropertyResourceBundle propertyResourceBundle = new PropertyResourceBundle(
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Act
    resourceBundleELResolver.getValue(context, propertyResourceBundle, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(propertyResourceBundle, getResult2.getBase());
  }

  /**
   * Test {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(resourceBundleELResolver.getValue(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code ???Property???}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnProperty() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertEquals("???Property???", resourceBundleELResolver.getValue(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenNull_thenReturnNull() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(resourceBundleELResolver.getValue(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), null));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenNull_whenELContextImplAddEvaluationListenerNull() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertNull(resourceBundleELResolver.getType(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_thenELContextImplEvaluationListenersSizeIsOne() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    PropertyResourceBundle propertyResourceBundle = new PropertyResourceBundle(
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Act
    resourceBundleELResolver.getType(context, propertyResourceBundle, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(propertyResourceBundle, getResult2.getBase());
  }

  /**
   * Test {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenELContextImplPropertyResolved() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(resourceBundleELResolver.getType(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(resourceBundleELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> resourceBundleELResolver.setValue(context, new AccessibleResourceBundle(), "Property", "Value"));
  }

  /**
   * Test {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> resourceBundleELResolver.setValue(context, new AccessibleResourceBundle(), "Property", "Value"));
  }

  /**
   * Test {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@link TesterEvaluationListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenTesterEvaluationListener() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> resourceBundleELResolver.setValue(context, new AccessibleResourceBundle(), "Property", "Value"));
  }

  /**
   * Test {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenELContextImpl_thenThrowPropertyNotWritableException() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> resourceBundleELResolver.setValue(context, new AccessibleResourceBundle(), "Property", "Value"));
  }

  /**
   * Test {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act
    boolean actualIsReadOnlyResult = resourceBundleELResolver.isReadOnly(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    PropertyResourceBundle propertyResourceBundle = new PropertyResourceBundle(
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Act
    resourceBundleELResolver.isReadOnly(context, propertyResourceBundle, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(propertyResourceBundle, getResult2.getBase());
  }

  /**
   * Test {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenELContextImplPropertyResolved() throws IOException {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = resourceBundleELResolver.isReadOnly(context,
        new PropertyResourceBundle(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = resourceBundleELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ResourceBundleELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@link AccessibleResourceBundle} (default constructor).</li>
   *   <li>Then return {@link String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenAccessibleResourceBundle_thenReturnString() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualCommonPropertyType = resourceBundleELResolver.getCommonPropertyType(context,
        new AccessibleResourceBundle());

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link ResourceBundleELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceBundleELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    ResourceBundleELResolver resourceBundleELResolver = new ResourceBundleELResolver();

    // Act and Assert
    assertNull(resourceBundleELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
