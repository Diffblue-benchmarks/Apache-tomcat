package jakarta.el;

import static org.junit.Assert.assertNull;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ELResolverDiffblueTest {
  /**
   * Test {@link ELResolver#invoke(ELContext, Object, Object, Class[], Object[])}.
   * <p>
   * Method under test: {@link ELResolver#invoke(ELContext, Object, Object, Class[], Object[])}
   */
  @Test
  public void testInvoke() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);
    ELContextImpl context = new ELContextImpl();
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNull(arrayELResolver.invoke(context, "Base", "Method", new Class[]{forNameResult}, new Object[]{"Params"}));
  }

  /**
   * Test {@link ELResolver#convertToType(ELContext, Object, Class)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELResolver#convertToType(ELContext, Object, Class)}
   */
  @Test
  public void testConvertToType_whenELContextImpl_thenReturnNull() {
    // Arrange
    ArrayELResolver arrayELResolver = new ArrayELResolver(true);
    ELContextImpl context = new ELContextImpl();
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(arrayELResolver.convertToType(context, "Obj", type));
  }
}
