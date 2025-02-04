package jakarta.servlet.jsp.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import jakarta.el.ELContext;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ImplicitObjectELResolverDiffblueTest {
  /**
   * Test {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenReturnNull() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getValue(new ELContextImpl(), null, null));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull2() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getValue(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenReturnNull() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getType(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull2() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getType(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull3() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getType(new ELContextImpl(), null, null));
  }

  /**
   * Test {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenReturnFalse() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertFalse(implicitObjectELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenReturnFalse() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertFalse(implicitObjectELResolver.isReadOnly(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenReturnFalse2() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertFalse(implicitObjectELResolver.isReadOnly(new ELContextImpl(), null, null));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act and Assert
    assertNull(implicitObjectELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link ImplicitObjectELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImplicitObjectELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenNull_thenReturnString() {
    // Arrange
    ImplicitObjectELResolver implicitObjectELResolver = new ImplicitObjectELResolver();

    // Act
    Class<String> actualCommonPropertyType = implicitObjectELResolver.getCommonPropertyType(new ELContextImpl(), null);

    // Assert
    Class<String> expectedCommonPropertyType = String.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }
}
