package jakarta.el;

import static org.junit.Assert.assertSame;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ELContextEventDiffblueTest {
  /**
   * Test {@link ELContextEvent#ELContextEvent(ELContext)}.
   * <p>
   * Method under test: {@link ELContextEvent#ELContextEvent(ELContext)}
   */
  @Test
  public void testNewELContextEvent() {
    // Arrange
    ELContextImpl source = new ELContextImpl();

    // Act and Assert
    assertSame(source, (new ELContextEvent(source)).getSource());
  }

  /**
   * Test {@link ELContextEvent#getELContext()}.
   * <p>
   * Method under test: {@link ELContextEvent#getELContext()}
   */
  @Test
  public void testGetELContext() {
    // Arrange
    ELContextImpl source = new ELContextImpl();

    // Act and Assert
    assertSame(source, (new ELContextEvent(source)).getELContext());
  }
}
