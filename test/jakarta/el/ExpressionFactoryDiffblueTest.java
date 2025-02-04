package jakarta.el;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.stream.StreamELResolverImpl;
import org.junit.Test;

public class ExpressionFactoryDiffblueTest {
  /**
   * Test {@link ExpressionFactory#newInstance()}.
   * <p>
   * Method under test: {@link ExpressionFactory#newInstance()}
   */
  @Test
  public void testNewInstance() {
    // Arrange and Act
    ExpressionFactory actualNewInstanceResult = ExpressionFactory.newInstance();

    // Assert
    assertTrue(actualNewInstanceResult instanceof ExpressionFactoryImpl);
    assertTrue(actualNewInstanceResult.getStreamELResolver() instanceof StreamELResolverImpl);
    assertNull(actualNewInstanceResult.getInitFunctionMap());
  }

  /**
   * Test {@link ExpressionFactory#newInstance(Properties)} with {@code Properties}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionFactory#newInstance(Properties)}
   */
  @Test
  public void testNewInstanceWithProperties_whenNull() {
    // Arrange and Act
    ExpressionFactory actualNewInstanceResult = ExpressionFactory.newInstance(null);

    // Assert
    assertTrue(actualNewInstanceResult instanceof ExpressionFactoryImpl);
    assertTrue(actualNewInstanceResult.getStreamELResolver() instanceof StreamELResolverImpl);
    assertNull(actualNewInstanceResult.getInitFunctionMap());
  }

  /**
   * Test {@link ExpressionFactory#newInstance(Properties)} with {@code Properties}.
   * <ul>
   *   <li>When {@link Properties#Properties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionFactory#newInstance(Properties)}
   */
  @Test
  public void testNewInstanceWithProperties_whenProperties() {
    // Arrange and Act
    ExpressionFactory actualNewInstanceResult = ExpressionFactory.newInstance(new Properties());

    // Assert
    assertTrue(actualNewInstanceResult instanceof ExpressionFactoryImpl);
    assertTrue(actualNewInstanceResult.getStreamELResolver() instanceof StreamELResolverImpl);
    assertNull(actualNewInstanceResult.getInitFunctionMap());
  }

  /**
   * Test {@link ExpressionFactory#getStreamELResolver()}.
   * <p>
   * Method under test: {@link ExpressionFactory#getStreamELResolver()}
   */
  @Test
  public void testGetStreamELResolver() {
    // Arrange and Act
    ELResolver actualStreamELResolver = (new ExpressionFactoryImpl()).getStreamELResolver();

    // Assert
    assertTrue(actualStreamELResolver instanceof StreamELResolverImpl);
    assertNull(actualStreamELResolver.getCommonPropertyType(null, "Base"));
  }

  /**
   * Test {@link ExpressionFactory#getInitFunctionMap()}.
   * <p>
   * Method under test: {@link ExpressionFactory#getInitFunctionMap()}
   */
  @Test
  public void testGetInitFunctionMap() {
    // Arrange, Act and Assert
    assertNull((new ExpressionFactoryImpl()).getInitFunctionMap());
  }
}
