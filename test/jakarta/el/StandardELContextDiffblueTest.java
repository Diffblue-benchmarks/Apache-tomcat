package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class StandardELContextDiffblueTest {
  /**
   * Test {@link StandardELContext#StandardELContext(ExpressionFactory)}.
   * <ul>
   *   <li>Then ELResolver return {@link CompositeELResolver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#StandardELContext(ExpressionFactory)}
   */
  @Test
  public void testNewStandardELContext_thenELResolverReturnCompositeELResolver() {
    // Arrange and Act
    StandardELContext actualStandardELContext = new StandardELContext(new ExpressionFactoryImpl());

    // Assert
    assertTrue(actualStandardELContext.getELResolver() instanceof CompositeELResolver);
    assertNull(actualStandardELContext.getLocale());
    assertFalse(actualStandardELContext.isPropertyResolved());
    assertTrue(actualStandardELContext.getEvaluationListeners().isEmpty());
    assertTrue(actualStandardELContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link StandardELContext#StandardELContext(ELContext)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#StandardELContext(ELContext)}
   */
  @Test
  public void testNewStandardELContext_whenELContextImpl() {
    // Arrange and Act
    StandardELContext actualStandardELContext = new StandardELContext(new ELContextImpl());

    // Assert
    assertTrue(actualStandardELContext.getELResolver() instanceof CompositeELResolver);
    assertNull(actualStandardELContext.getLocale());
    assertFalse(actualStandardELContext.isPropertyResolved());
    assertTrue(actualStandardELContext.getEvaluationListeners().isEmpty());
    assertTrue(actualStandardELContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link StandardELContext#StandardELContext(ELContext)}.
   * <ul>
   *   <li>When {@link StandardELContext#StandardELContext(ExpressionFactory)} with factory is {@link ExpressionFactoryImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#StandardELContext(ELContext)}
   */
  @Test
  public void testNewStandardELContext_whenStandardELContextWithFactoryIsExpressionFactoryImpl() {
    // Arrange and Act
    StandardELContext actualStandardELContext = new StandardELContext(
        new StandardELContext(new ExpressionFactoryImpl()));

    // Assert
    assertTrue(actualStandardELContext.getELResolver() instanceof CompositeELResolver);
    assertNull(actualStandardELContext.getLocale());
    assertFalse(actualStandardELContext.isPropertyResolved());
    assertTrue(actualStandardELContext.getEvaluationListeners().isEmpty());
    assertTrue(actualStandardELContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link StandardELContext#getContext(Class)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code Context Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_givenJavaLangObject_whenJavaLangObject_thenReturnContextObject() {
    // Arrange
    StandardELContext standardELContext = new StandardELContext(new ExpressionFactoryImpl());
    Class<Object> key = Object.class;
    standardELContext.putContext(key, "Context Object");
    Class<Object> key2 = Object.class;

    // Act and Assert
    assertEquals("Context Object", standardELContext.getContext(key2));
  }

  /**
   * Test {@link StandardELContext#getContext(Class)}.
   * <ul>
   *   <li>Given {@link StandardELContext#StandardELContext(ELContext)} with context is {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_givenStandardELContextWithContextIsELContextImpl_thenReturnNull() {
    // Arrange
    StandardELContext standardELContext = new StandardELContext(new ELContextImpl());
    Class<Object> key = Object.class;

    // Act and Assert
    assertNull(standardELContext.getContext(key));
  }

  /**
   * Test {@link StandardELContext#getContext(Class)}.
   * <ul>
   *   <li>Given {@link StandardELContext#StandardELContext(ELContext)} with context is {@link StandardELContext#StandardELContext(ExpressionFactory)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_givenStandardELContextWithContextIsStandardELContext() {
    // Arrange
    StandardELContext standardELContext = new StandardELContext(new StandardELContext(new ExpressionFactoryImpl()));
    Class<Object> key = Object.class;

    // Act and Assert
    assertNull(standardELContext.getContext(key));
  }

  /**
   * Test {@link StandardELContext#getContext(Class)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardELContext#getContext(Class)}
   */
  @Test
  public void testGetContext_thenReturnNull() {
    // Arrange
    StandardELContext standardELContext = new StandardELContext(new ExpressionFactoryImpl());
    Class<Object> key = Object.class;

    // Act and Assert
    assertNull(standardELContext.getContext(key));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardELContext#getELResolver()}
   *   <li>{@link StandardELContext#getFunctionMapper()}
   *   <li>{@link StandardELContext#getLocalBeans()}
   *   <li>{@link StandardELContext#getVariableMapper()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardELContext standardELContext = new StandardELContext(new ExpressionFactoryImpl());

    // Act
    ELResolver actualELResolver = standardELContext.getELResolver();
    standardELContext.getFunctionMapper();
    Map<String, Object> actualLocalBeans = standardELContext.getLocalBeans();
    standardELContext.getVariableMapper();

    // Assert
    assertTrue(actualELResolver instanceof CompositeELResolver);
    assertTrue(actualLocalBeans.isEmpty());
  }
}
