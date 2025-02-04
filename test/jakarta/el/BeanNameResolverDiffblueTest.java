package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BeanNameResolverDiffblueTest {
  /**
   * Test {@link BeanNameResolver#isNameResolved(String)}.
   * <p>
   * Method under test: {@link BeanNameResolver#isNameResolved(String)}
   */
  @Test
  public void testIsNameResolved() {
    // Arrange, Act and Assert
    assertFalse((new TesterBeanNameResolver()).isNameResolved("Bean Name"));
  }

  /**
   * Test {@link BeanNameResolver#getBean(String)}.
   * <p>
   * Method under test: {@link BeanNameResolver#getBean(String)}
   */
  @Test
  public void testGetBean() {
    // Arrange, Act and Assert
    assertNull((new TesterBeanNameResolver()).getBean("Bean Name"));
  }

  /**
   * Test {@link BeanNameResolver#setBeanValue(String, Object)}.
   * <p>
   * Method under test: {@link BeanNameResolver#setBeanValue(String, Object)}
   */
  @Test
  public void testSetBeanValue() throws PropertyNotWritableException {
    // Arrange
    TesterBeanNameResolver testerBeanNameResolver = new TesterBeanNameResolver();

    // Act
    testerBeanNameResolver.setBeanValue("Bean Name", "Value");

    // Assert
    assertEquals("Value", testerBeanNameResolver.getBean("Bean Name"));
    assertTrue(testerBeanNameResolver.isNameResolved("Bean Name"));
  }

  /**
   * Test {@link BeanNameResolver#isReadOnly(String)}.
   * <ul>
   *   <li>When {@code Bean Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameResolver#isReadOnly(String)}
   */
  @Test
  public void testIsReadOnly_whenBeanName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new TesterBeanNameResolver()).isReadOnly("Bean Name"));
  }

  /**
   * Test {@link BeanNameResolver#isReadOnly(String)}.
   * <ul>
   *   <li>When {@link TesterBeanNameResolver#READ_ONLY_NAME}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameResolver#isReadOnly(String)}
   */
  @Test
  public void testIsReadOnly_whenRead_only_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new TesterBeanNameResolver()).isReadOnly(TesterBeanNameResolver.READ_ONLY_NAME));
  }

  /**
   * Test {@link BeanNameResolver#canCreateBean(String)}.
   * <ul>
   *   <li>Given {@link TesterBeanNameResolver} (default constructor) AllowCreate is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameResolver#canCreateBean(String)}
   */
  @Test
  public void testCanCreateBean_givenTesterBeanNameResolverAllowCreateIsFalse_thenReturnFalse() {
    // Arrange
    TesterBeanNameResolver testerBeanNameResolver = new TesterBeanNameResolver();
    testerBeanNameResolver.setAllowCreate(false);

    // Act and Assert
    assertFalse(testerBeanNameResolver.canCreateBean("Bean Name"));
  }

  /**
   * Test {@link BeanNameResolver#canCreateBean(String)}.
   * <ul>
   *   <li>Given {@link TesterBeanNameResolver} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanNameResolver#canCreateBean(String)}
   */
  @Test
  public void testCanCreateBean_givenTesterBeanNameResolver_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new TesterBeanNameResolver()).canCreateBean("Bean Name"));
  }
}
