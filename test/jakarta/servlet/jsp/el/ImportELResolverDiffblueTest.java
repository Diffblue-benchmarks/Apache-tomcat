package jakarta.servlet.jsp.el;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import jakarta.el.ELContext;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ImportELResolverDiffblueTest {
  /**
   * Test {@link ImportELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Context {@link Object} is {@code Context Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenJavaLangObject_whenELContextImplContextObjectIsContextObject() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    ELContextImpl context = new ELContextImpl();
    Class<Object> key = Object.class;
    context.putContext(key, "Context Object");

    // Act and Assert
    assertNull(importELResolver.getValue(context, null, "Property"));
  }

  /**
   * Test {@link ImportELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenReturnNull() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImportELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getValue(new ELContextImpl(), null, "Property"));
  }

  /**
   * Test {@link ImportELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenELContextImpl_thenReturnNull2() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getValue(new ELContextImpl(), null, null));
  }

  /**
   * Test {@link ImportELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code java.lang.Boolean}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenJavaLangBoolean_thenReturnNull() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getValue(new ELContextImpl(), null, "java.lang.Boolean"));
  }

  /**
   * Test {@link ImportELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenELContextImpl_thenReturnNull() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImportELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenELContextImpl_thenReturnFalse() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertFalse(importELResolver.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link ImportELResolver#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link ImportELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    ImportELResolver importELResolver = new ImportELResolver();

    // Act and Assert
    assertNull(importELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
