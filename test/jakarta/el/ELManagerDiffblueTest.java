package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import aQute.bnd.osgi.Analyzer;
import aQute.bnd.osgi.Processor;
import aQute.bnd.osgi.Processor.CL;
import java.util.List;
import java.util.Map;
import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.stream.StreamELResolverImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ELManagerDiffblueTest {
  /**
   * Test {@link ELManager#getExpressionFactory()}.
   * <p>
   * Method under test: {@link ELManager#getExpressionFactory()}
   */
  @Test
  public void testGetExpressionFactory() {
    // Arrange and Act
    ExpressionFactory actualExpressionFactory = ELManager.getExpressionFactory();

    // Assert
    assertTrue(actualExpressionFactory instanceof ExpressionFactoryImpl);
    assertTrue(actualExpressionFactory.getStreamELResolver() instanceof StreamELResolverImpl);
    assertNull(actualExpressionFactory.getInitFunctionMap());
  }

  /**
   * Test {@link ELManager#getELContext()}.
   * <ul>
   *   <li>Given {@link ELManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#getELContext()}
   */
  @Test
  public void testGetELContext_givenELManager() {
    // Arrange and Act
    StandardELContext actualELContext = (new ELManager()).getELContext();

    // Assert
    assertTrue(actualELContext.getELResolver() instanceof CompositeELResolver);
    assertNull(actualELContext.getLocale());
    assertFalse(actualELContext.isPropertyResolved());
    assertTrue(actualELContext.getEvaluationListeners().isEmpty());
    assertTrue(actualELContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link ELManager#getELContext()}.
   * <ul>
   *   <li>Given {@link ELManager} (default constructor) ELContext is {@link ELContextImpl#ELContextImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#getELContext()}
   */
  @Test
  public void testGetELContext_givenELManagerELContextIsELContextImpl() {
    // Arrange
    ELManager elManager = new ELManager();
    elManager.setELContext(new ELContextImpl());

    // Act
    StandardELContext actualELContext = elManager.getELContext();

    // Assert
    assertTrue(actualELContext.getELResolver() instanceof CompositeELResolver);
    assertNull(actualELContext.getLocale());
    assertFalse(actualELContext.isPropertyResolved());
    assertTrue(actualELContext.getEvaluationListeners().isEmpty());
    assertTrue(actualELContext.getLocalBeans().isEmpty());
  }

  /**
   * Test {@link ELManager#setELContext(ELContext)}.
   * <ul>
   *   <li>When {@link ELContextImpl#ELContextImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#setELContext(ELContext)}
   */
  @Test
  public void testSetELContext_whenELContextImpl_thenReturnNull() {
    // Arrange
    ELManager elManager = new ELManager();

    // Act and Assert
    assertNull(elManager.setELContext(new ELContextImpl()));
  }

  /**
   * Test {@link ELManager#setELContext(ELContext)}.
   * <ul>
   *   <li>When {@link StandardELContext#StandardELContext(ExpressionFactory)} with factory is {@link ExpressionFactoryImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#setELContext(ELContext)}
   */
  @Test
  public void testSetELContext_whenStandardELContextWithFactoryIsExpressionFactoryImpl() {
    // Arrange
    ELManager elManager = new ELManager();

    // Act and Assert
    assertNull(elManager.setELContext(new StandardELContext(new ExpressionFactoryImpl())));
  }

  /**
   * Test {@link ELManager#defineBean(String, Object)}.
   * <ul>
   *   <li>Then {@link ELManager} (default constructor) ELContext LocalBeans Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#defineBean(String, Object)}
   */
  @Test
  public void testDefineBean_thenELManagerELContextLocalBeansEmpty() {
    // Arrange
    ELManager elManager = new ELManager();
    elManager.setELContext(new ELContextImpl());

    // Act and Assert
    assertNull(elManager.defineBean("Name", null));
    assertTrue(elManager.getELContext().getLocalBeans().isEmpty());
  }

  /**
   * Test {@link ELManager#defineBean(String, Object)}.
   * <ul>
   *   <li>Then {@link ELManager} (default constructor) ELContext LocalBeans {@code Name} is {@link Processor.CL#CL(Processor)} with p is {@link Analyzer#Analyzer()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#defineBean(String, Object)}
   */
  @Test
  public void testDefineBean_thenELManagerELContextLocalBeansNameIsClWithPIsAnalyzer() {
    // Arrange
    ELManager elManager = new ELManager();
    CL cl = new CL(new Analyzer());

    // Act and Assert
    assertNull(elManager.defineBean("Name", cl));
    Map<String, Object> localBeans = elManager.getELContext().getLocalBeans();
    assertEquals(1, localBeans.size());
    assertSame(cl, localBeans.get("Name"));
  }

  /**
   * Test {@link ELManager#defineBean(String, Object)}.
   * <ul>
   *   <li>When {@code Bean}.</li>
   *   <li>Then {@link ELManager} (default constructor) ELContext LocalBeans {@code Name} is {@code Bean}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#defineBean(String, Object)}
   */
  @Test
  public void testDefineBean_whenBean_thenELManagerELContextLocalBeansNameIsBean() {
    // Arrange
    ELManager elManager = new ELManager();

    // Act
    Object actualDefineBeanResult = elManager.defineBean("Name", "Bean");

    // Assert
    Map<String, Object> localBeans = elManager.getELContext().getLocalBeans();
    assertEquals(1, localBeans.size());
    assertEquals("Bean", localBeans.get("Name"));
    assertNull(actualDefineBeanResult);
  }

  /**
   * Test {@link ELManager#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Given {@link ELManager} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_givenELManager() {
    // Arrange
    ELManager elManager = new ELManager();
    TesterEvaluationListener listener = new TesterEvaluationListener();

    // Act
    elManager.addEvaluationListener(listener);

    // Assert
    List<EvaluationListener> evaluationListeners = elManager.getELContext().getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    assertSame(listener, evaluationListeners.get(0));
  }

  /**
   * Test {@link ELManager#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Given {@link ELManager} (default constructor) ELContext is {@link ELContextImpl#ELContextImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_givenELManagerELContextIsELContextImpl() {
    // Arrange
    ELManager elManager = new ELManager();
    elManager.setELContext(new ELContextImpl());
    TesterEvaluationListener listener = new TesterEvaluationListener();

    // Act
    elManager.addEvaluationListener(listener);

    // Assert
    List<EvaluationListener> evaluationListeners = elManager.getELContext().getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    assertSame(listener, evaluationListeners.get(0));
  }

  /**
   * Test {@link ELManager#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Then {@link ELManager} (default constructor) ELContext EvaluationListeners first is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_thenELManagerELContextEvaluationListenersFirstIsNull() {
    // Arrange
    ELManager elManager = new ELManager();

    // Act
    elManager.addEvaluationListener(null);

    // Assert
    List<EvaluationListener> evaluationListeners = elManager.getELContext().getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    assertNull(evaluationListeners.get(0));
  }

  /**
   * Test {@link ELManager#addEvaluationListener(EvaluationListener)}.
   * <ul>
   *   <li>Then {@link ELManager} (default constructor) ELContext EvaluationListeners size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ELManager#addEvaluationListener(EvaluationListener)}
   */
  @Test
  public void testAddEvaluationListener_thenELManagerELContextEvaluationListenersSizeIsTwo() {
    // Arrange
    ELManager elManager = new ELManager();
    TesterEvaluationListener listener = new TesterEvaluationListener();
    elManager.addEvaluationListener(listener);
    TesterEvaluationListener listener2 = new TesterEvaluationListener();

    // Act
    elManager.addEvaluationListener(listener2);

    // Assert
    List<EvaluationListener> evaluationListeners = elManager.getELContext().getEvaluationListeners();
    assertEquals(2, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    assertSame(listener, getResult);
    assertSame(listener2, evaluationListeners.get(1));
  }

  /**
   * Test new {@link ELManager} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ELManager}
   */
  @Test
  public void testNewELManager() {
    // Arrange, Act and Assert
    StandardELContext eLContext = (new ELManager()).getELContext();
    assertTrue(eLContext.getELResolver() instanceof CompositeELResolver);
    assertNull(eLContext.getLocale());
    assertFalse(eLContext.isPropertyResolved());
    assertTrue(eLContext.getEvaluationListeners().isEmpty());
    assertTrue(eLContext.getLocalBeans().isEmpty());
  }
}
