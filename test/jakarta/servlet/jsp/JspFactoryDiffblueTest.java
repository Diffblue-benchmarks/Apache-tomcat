package jakarta.servlet.jsp;

import static org.junit.Assert.assertSame;
import org.apache.jasper.runtime.JspFactoryImpl;
import org.junit.Test;

public class JspFactoryDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JspFactory#getDefaultFactory()}
   *   <li>{@link JspFactory#setDefaultFactory(JspFactory)}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    JspFactory actualDefaultFactory = JspFactory.getDefaultFactory();
    JspFactory actualDefaultFactory2 = actualDefaultFactory.getDefaultFactory();
    actualDefaultFactory.setDefaultFactory(new JspFactoryImpl());

    // Assert
    assertSame(actualDefaultFactory, actualDefaultFactory2);
  }
}
