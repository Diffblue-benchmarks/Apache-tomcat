package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.el.BeanELResolver.BeanProperties;
import jakarta.el.BeanELResolver.BeanProperty;
import jakarta.el.BeanSupportStandalone.BeanPropertiesStandalone;
import jakarta.el.BeanSupportStandalone.BeanPropertyStandalone;
import jakarta.el.BeanSupportStandalone.WriteMethodComparator;
import java.lang.reflect.Method;
import java.util.Map;
import org.junit.Test;

public class BeanSupportStandaloneDiffblueTest {
  /**
   * Test BeanPropertiesStandalone {@link BeanPropertiesStandalone#BeanPropertiesStandalone(Class)}.
   * <p>
   * Method under test: {@link BeanPropertiesStandalone#BeanPropertiesStandalone(Class)}
   */
  @Test
  public void testBeanPropertiesStandaloneNewBeanPropertiesStandalone() throws ELException {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    BeanPropertiesStandalone actualBeanPropertiesStandalone = new BeanPropertiesStandalone(type);

    // Assert
    Map<String, BeanProperty> stringBeanPropertyMap = actualBeanPropertiesStandalone.properties;
    assertEquals(1, stringBeanPropertyMap.size());
    BeanProperty getResult = stringBeanPropertyMap.get("class");
    assertTrue(getResult instanceof BeanPropertyStandalone);
    assertEquals("class", getResult.getName());
    assertNull(getResult.getWriteMethod());
    Class<Class> expectedPropertyType = Class.class;
    assertEquals(expectedPropertyType, getResult.getPropertyType());
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, actualBeanPropertiesStandalone.type);
  }

  /**
   * Test {@link BeanSupportStandalone#getBeanProperties(Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@link BeanPropertiesStandalone}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanSupportStandalone#getBeanProperties(Class)}
   */
  @Test
  public void testGetBeanProperties_whenJavaLangObject_thenReturnBeanPropertiesStandalone() {
    // Arrange
    BeanSupportStandalone beanSupportStandalone = new BeanSupportStandalone();
    Class<Object> type = Object.class;

    // Act
    BeanProperties actualBeanProperties = beanSupportStandalone.getBeanProperties(type);

    // Assert
    assertTrue(actualBeanProperties instanceof BeanPropertiesStandalone);
    Map<String, BeanProperty> stringBeanPropertyMap = ((BeanPropertiesStandalone) actualBeanProperties).properties;
    assertEquals(1, stringBeanPropertyMap.size());
    BeanProperty getResult = stringBeanPropertyMap.get("class");
    assertTrue(getResult instanceof BeanPropertyStandalone);
    assertEquals("class", getResult.getName());
    assertNull(getResult.getWriteMethod());
    Class<Class> expectedPropertyType = Class.class;
    assertEquals(expectedPropertyType, getResult.getPropertyType());
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, ((BeanPropertiesStandalone) actualBeanProperties).type);
  }

  /**
   * Test WriteMethodComparator {@link WriteMethodComparator#compare(Method, Method)} with {@code m1}, {@code m2}.
   * <p>
   * Method under test: {@link WriteMethodComparator#compare(Method, Method)}
   */
  @Test
  public void testWriteMethodComparatorCompareWithM1M2() {
    // Arrange, Act and Assert
    assertEquals(0, (new WriteMethodComparator()).compare(null, null));
  }
}
