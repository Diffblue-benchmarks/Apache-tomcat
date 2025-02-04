package jakarta.servlet.jsp.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.el.ELContext;
import jakarta.el.EvaluationListener;
import jakarta.el.TesterEvaluationListener;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.List;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ScopedAttributeELResolverDiffblueTest {
  /**
   * Test {@link ScopedAttributeELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    // Act and Assert
    assertNull(scopedAttributeELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act
    Class<Object> actualType = scopedAttributeELResolver.getType(context, null, "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    Class<Object> expectedType = Object.class;
    assertEquals(expectedType, actualType);
  }

  /**
   * Test {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act
    scopedAttributeELResolver.getType(context, null, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertNull(getResult2.getBase());
  }

  /**
   * Test {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<Object> actualType = scopedAttributeELResolver.getType(context, null, "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    Class<Object> expectedType = Object.class;
    assertEquals(expectedType, actualType);
  }

  /**
   * Test {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(scopedAttributeELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertFalse(scopedAttributeELResolver.isReadOnly(context, null, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act
    scopedAttributeELResolver.isReadOnly(context, null, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertNull(getResult2.getBase());
  }

  /**
   * Test {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenELContextImplPropertyResolved() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(scopedAttributeELResolver.isReadOnly(context, null, "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenNotELContextImplPropertyResolved() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = scopedAttributeELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ScopedAttributeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    // Act and Assert
    assertNull(scopedAttributeELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link ScopedAttributeELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ScopedAttributeELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenNull_thenReturnString() {
    // Arrange
    ScopedAttributeELResolver scopedAttributeELResolver = new ScopedAttributeELResolver();

    // Act
    Class<String> actualCommonPropertyType = scopedAttributeELResolver.getCommonPropertyType(new ELContextImpl(), null);

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }
}
