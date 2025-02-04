package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import org.apache.jasper.el.ELContextImpl;
import org.eclipse.jdt.internal.compiler.parser.JavadocTagConstants;
import org.eclipse.jdt.internal.compiler.parser.JavadocTagConstants.LevelTags;
import org.junit.Test;

public class RecordELResolverDiffblueTest {
  /**
   * Test {@link RecordELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenReturnNull() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();

    // Act and Assert
    assertNull(recordELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link RecordELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenNull_thenReturnNull() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(recordELResolver.getValue(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), null));
  }

  /**
   * Test {@link RecordELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenProperty_thenThrowPropertyNotFoundException() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> recordELResolver.getValue(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), "Property"));
  }

  /**
   * Test {@link RecordELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenReturnNull() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();

    // Act and Assert
    assertNull(recordELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link RecordELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenNull_thenReturnNull() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(recordELResolver.getType(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), null));
  }

  /**
   * Test {@link RecordELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenProperty_thenThrowPropertyNotFoundException() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> recordELResolver.getType(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), "Property"));
  }

  /**
   * Test {@link RecordELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenProperty_thenThrowPropertyNotFoundException() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> recordELResolver.setValue(context,
        new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), "Property", "Value"));
  }

  /**
   * Test {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenReturnFalse() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();

    // Act and Assert
    assertFalse(recordELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenNull_thenReturnFalse() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(recordELResolver.isReadOnly(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), null));
  }

  /**
   * Test {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenProperty_thenThrowPropertyNotFoundException() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> recordELResolver.isReadOnly(context, new LevelTags(1, new char[][]{"AZAZ".toCharArray()}), "Property"));
  }

  /**
   * Test {@link RecordELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_thenReturnObject() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualCommonPropertyType = recordELResolver.getCommonPropertyType(context,
        new LevelTags(1, new char[][]{"AZAZ".toCharArray()}));

    // Assert
    Class<Object> expectedCommonPropertyType = Object.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link RecordELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    RecordELResolver recordELResolver = new RecordELResolver();

    // Act and Assert
    assertNull(recordELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
