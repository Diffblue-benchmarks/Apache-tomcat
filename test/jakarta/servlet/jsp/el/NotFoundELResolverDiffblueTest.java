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

public class NotFoundELResolverDiffblueTest {
  /**
   * Test {@link NotFoundELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenJavaLangObject_thenReturnNull() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    ELContextImpl context = new ELContextImpl();
    Class<Object> key = Object.class;
    context.putContext(key, "Context Object");

    // Act and Assert
    assertNull(notFoundELResolver.getValue(context, "Base", "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link NotFoundELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenNull_whenELContextImplAddEvaluationListenerNull_thenReturnNull() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);
    Class<Object> key = Object.class;
    context.putContext(key, "Context Object");

    // Act and Assert
    assertNull(notFoundELResolver.getValue(context, "Base", "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link NotFoundELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    Class<Object> key = Object.class;
    context.putContext(key, "Context Object");

    // Act
    notFoundELResolver.getValue(context, "Base", "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
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
   * Test {@link NotFoundELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(notFoundELResolver.getValue(context, "Base", "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link NotFoundELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    // Act and Assert
    assertNull(notFoundELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link NotFoundELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NotFoundELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenReturnFalse() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    // Act and Assert
    assertFalse(notFoundELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link NotFoundELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link NotFoundELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    NotFoundELResolver notFoundELResolver = new NotFoundELResolver();

    // Act and Assert
    assertNull(notFoundELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
