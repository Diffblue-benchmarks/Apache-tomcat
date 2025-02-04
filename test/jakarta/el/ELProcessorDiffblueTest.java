package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import org.junit.Test;

public class ELProcessorDiffblueTest {
  /**
   * Test {@link ELProcessor#getELManager()}.
   * <p>
   * Method under test: {@link ELProcessor#getELManager()}
   */
  @Test
  public void testGetELManager() {
    // Arrange, Act and Assert
    StandardELContext eLContext = (new ELProcessor()).getELManager().getELContext();
    assertTrue(eLContext.getELResolver() instanceof CompositeELResolver);
    assertNull(eLContext.getLocale());
    assertFalse(eLContext.isPropertyResolved());
    assertTrue(eLContext.getEvaluationListeners().isEmpty());
    assertTrue(eLContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link ELProcessor#eval(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return longValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELProcessor#eval(String)}
   */
  @Test
  public void testEval_when42_thenReturnLongValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42L, ((Long) (new ELProcessor()).eval("42")).longValue());
  }

  /**
   * Test {@link ELProcessor#getValue(String, Class)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return longValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELProcessor#getValue(String, Class)}
   */
  @Test
  public void testGetValue_when42_thenReturnLongValueIsFortyTwo() {
    // Arrange
    ELProcessor elProcessor = new ELProcessor();
    Class<Object> expectedType = Object.class;

    // Act and Assert
    assertEquals(42L, ((Long) elProcessor.getValue("42", expectedType)).longValue());
  }

  /**
   * Test {@link ELProcessor#setValue(String, Object)}.
   * <ul>
   *   <li>When {@code Expression}.</li>
   *   <li>Then {@link ELProcessor} (default constructor) ELManager ELContext LocalBeans size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELProcessor#setValue(String, Object)}
   */
  @Test
  public void testSetValue_whenExpression_thenELProcessorELManagerELContextLocalBeansSizeIsOne() {
    // Arrange
    ELProcessor elProcessor = new ELProcessor();

    // Act
    elProcessor.setValue("Expression", "Value");

    // Assert
    StandardELContext eLContext = elProcessor.getELManager().getELContext();
    Map<String, Object> localBeans = eLContext.getLocalBeans();
    assertEquals(1, localBeans.size());
    assertEquals("Value", localBeans.get("Expression"));
    assertTrue(eLContext.isPropertyResolved());
  }

  /**
   * Test {@link ELProcessor#defineBean(String, Object)}.
   * <p>
   * Method under test: {@link ELProcessor#defineBean(String, Object)}
   */
  @Test
  public void testDefineBean() {
    // Arrange
    ELProcessor elProcessor = new ELProcessor();

    // Act
    elProcessor.defineBean("Name", "Bean");

    // Assert
    Map<String, Object> localBeans = elProcessor.getELManager().getELContext().getLocalBeans();
    assertEquals(1, localBeans.size());
    assertEquals("Bean", localBeans.get("Name"));
  }

  /**
   * Test new {@link ELProcessor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ELProcessor}
   */
  @Test
  public void testNewELProcessor() {
    // Arrange, Act and Assert
    StandardELContext eLContext = (new ELProcessor()).getELManager().getELContext();
    assertTrue(eLContext.getELResolver() instanceof CompositeELResolver);
    assertNull(eLContext.getLocale());
    assertFalse(eLContext.isPropertyResolved());
    assertTrue(eLContext.getEvaluationListeners().isEmpty());
    assertTrue(eLContext.getLocalBeans().isEmpty());
  }
}
