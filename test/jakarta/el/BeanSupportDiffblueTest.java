package jakarta.el;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BeanSupportDiffblueTest {
  /**
   * Test {@link BeanSupport#getInstance()}.
   * <p>
   * Method under test: {@link BeanSupport#getInstance()}
   */
  @Test
  public void testGetInstance() {
    // Arrange, Act and Assert
    assertTrue(BeanSupport.getInstance() instanceof BeanSupportFull);
  }
}
