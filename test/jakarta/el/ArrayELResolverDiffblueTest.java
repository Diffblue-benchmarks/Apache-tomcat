package jakarta.el;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ArrayELResolverDiffblueTest {
  /**
   * Test {@link ArrayELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ArrayELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull2() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getType(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ArrayELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ArrayELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull2() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getValue(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenArrayELResolverWithReadOnlyIsFalse_thenReturnFalse() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(false);

    // Act and Assert
    assertFalse(arrayELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenArrayELResolverWithReadOnlyIsTrue_thenReturnTrue() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertTrue(arrayELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link ArrayELResolver#ArrayELResolver(boolean)} with readOnly is {@code true}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenArrayELResolverWithReadOnlyIsTrue_whenNull_thenReturnTrue() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertTrue(arrayELResolver.isReadOnly(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ArrayELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }

  /**
   * Test {@link ArrayELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenNull() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);

    // Act and Assert
    assertNull(arrayELResolver.getCommonPropertyType(new ELContextImpl(), null));
  }
}
