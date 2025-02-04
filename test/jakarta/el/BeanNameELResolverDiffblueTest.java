package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class BeanNameELResolverDiffblueTest {
  /**
   * Test {@link BeanNameELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getValue(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenOne_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getValue(new ELContextImpl(), null, 1));
  }

  /**
   * Test {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());
    ELContextImpl context = new ELContextImpl();

    // Act
    beanNameELResolver.setValue(context, "Base", "Property", "Value");

    // Assert that nothing has changed
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@link TesterBeanNameResolver#EXCEPTION_TRIGGER_NAME}.</li>
   *   <li>Then throw {@link ELException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenException_trigger_name_thenThrowELException() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertThrows(ELException.class, () -> beanNameELResolver.setValue(new ELContextImpl(), null,
        TesterBeanNameResolver.EXCEPTION_TRIGGER_NAME, "Value"));
  }

  /**
   * Test {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenOne_thenNotELContextImplPropertyResolved() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());
    ELContextImpl context = new ELContextImpl();

    // Act
    beanNameELResolver.setValue(context, null, 1, "Value");

    // Assert that nothing has changed
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenProperty_thenELContextImplPropertyResolved() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());
    ELContextImpl context = new ELContextImpl();

    // Act
    beanNameELResolver.setValue(context, null, "Property", "Value");

    // Assert
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link BeanNameELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getType(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenOne_thenReturnNull() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertNull(beanNameELResolver.getType(new ELContextImpl(), null, 1));
  }

  /**
   * Test {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenReturnFalse() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertFalse(beanNameELResolver.isReadOnly(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenReturnFalse() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertFalse(beanNameELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenOne_thenReturnFalse() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act and Assert
    assertFalse(beanNameELResolver.isReadOnly(new ELContextImpl(), null, 1));
  }

  /**
   * Test {@link BeanNameELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link BeanNameELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    BeanNameELResolver beanNameELResolver = new BeanNameELResolver(new TesterBeanNameResolver());

    // Act
    Class<?> actualCommonPropertyType = beanNameELResolver.getCommonPropertyType(new ELContextImpl(), "Base");

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }
}
