package jakarta.el;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class TypeConverterDiffblueTest {
  /**
   * Test {@link TypeConverter#getValue(ELContext, Object, Object)}.
   * <p>
   * Method under test: {@link TypeConverter#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue() {
    // Arrange
    TesterELResolverOne testerELResolverOne = new TesterELResolverOne();

    // Act and Assert
    assertNull(testerELResolverOne.getValue(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link TypeConverter#getType(ELContext, Object, Object)}.
   * <p>
   * Method under test: {@link TypeConverter#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType() {
    // Arrange
    TesterELResolverOne testerELResolverOne = new TesterELResolverOne();

    // Act and Assert
    assertNull(testerELResolverOne.getType(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link TypeConverter#isReadOnly(ELContext, Object, Object)}.
   * <p>
   * Method under test: {@link TypeConverter#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly() {
    // Arrange
    TesterELResolverOne testerELResolverOne = new TesterELResolverOne();

    // Act and Assert
    assertFalse(testerELResolverOne.isReadOnly(new ELContextImpl(), "Base", "Property"));
  }

  /**
   * Test {@link TypeConverter#getCommonPropertyType(ELContext, Object)}.
   * <p>
   * Method under test: {@link TypeConverter#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType() {
    // Arrange
    TesterELResolverOne testerELResolverOne = new TesterELResolverOne();

    // Act and Assert
    assertNull(testerELResolverOne.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
