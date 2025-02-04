package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.el.BeanELResolver.BeanProperties;
import jakarta.el.BeanELResolver.BeanProperty;
import jakarta.el.BeanSupportFull.BeanPropertiesFull;
import jakarta.el.BeanSupportFull.BeanPropertyFull;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Map;
import org.junit.Test;

public class BeanSupportFullDiffblueTest {
  /**
   * Test BeanPropertiesFull {@link BeanPropertiesFull#BeanPropertiesFull(Class)}.
   * <ul>
   *   <li>Then return {@link BeanProperties#properties} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanPropertiesFull#BeanPropertiesFull(Class)}
   */
  @Test
  public void testBeanPropertiesFullNewBeanPropertiesFull_thenReturnPropertiesSizeIsOne() throws ELException {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    BeanPropertiesFull actualBeanPropertiesFull = new BeanPropertiesFull(type);

    // Assert
    Map<String, BeanProperty> stringBeanPropertyMap = actualBeanPropertiesFull.properties;
    assertEquals(1, stringBeanPropertyMap.size());
    BeanProperty getResult = stringBeanPropertyMap.get("class");
    assertTrue(getResult instanceof BeanPropertyFull);
    assertEquals("class", getResult.getName());
    assertNull(getResult.getWriteMethod());
    Class<Class> expectedPropertyType = Class.class;
    assertEquals(expectedPropertyType, getResult.getPropertyType());
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, actualBeanPropertiesFull.type);
  }

  /**
   * Test BeanPropertyFull {@link BeanPropertyFull#getName()}.
   * <ul>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanPropertyFull#getName()}
   */
  @Test
  public void testBeanPropertyFullGetName_thenReturnFoo() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act and Assert
    assertEquals("foo",
        (new BeanPropertyFull(owner, new IndexedPropertyDescriptor("foo", null, null, null, null))).getName());
  }

  /**
   * Test BeanPropertyFull {@link BeanPropertyFull#getReadMethod()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanPropertyFull#getReadMethod()}
   */
  @Test
  public void testBeanPropertyFullGetReadMethod_thenReturnNull() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act and Assert
    assertNull(
        (new BeanPropertyFull(owner, new IndexedPropertyDescriptor("foo", null, null, null, null))).getReadMethod());
  }

  /**
   * Test BeanPropertyFull {@link BeanPropertyFull#getWriteMethod()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanPropertyFull#getWriteMethod()}
   */
  @Test
  public void testBeanPropertyFullGetWriteMethod_thenReturnNull() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act and Assert
    assertNull(
        (new BeanPropertyFull(owner, new IndexedPropertyDescriptor("foo", null, null, null, null))).getWriteMethod());
  }

  /**
   * Test BeanPropertyFull {@link BeanPropertyFull#BeanPropertyFull(Class, PropertyDescriptor)}.
   * <ul>
   *   <li>Then return Name is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanPropertyFull#BeanPropertyFull(Class, PropertyDescriptor)}
   */
  @Test
  public void testBeanPropertyFullNewBeanPropertyFull_thenReturnNameIsFoo() throws IntrospectionException {
    // Arrange
    Class<Object> owner = Object.class;

    // Act
    BeanPropertyFull actualBeanPropertyFull = new BeanPropertyFull(owner,
        new IndexedPropertyDescriptor("foo", null, null, null, null));

    // Assert
    assertEquals("foo", actualBeanPropertyFull.getName());
    assertNull(actualBeanPropertyFull.getPropertyType());
    assertNull(actualBeanPropertyFull.getReadMethod());
    assertNull(actualBeanPropertyFull.getWriteMethod());
  }

  /**
   * Test {@link BeanSupportFull#getBeanProperties(Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@link BeanPropertiesFull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BeanSupportFull#getBeanProperties(Class)}
   */
  @Test
  public void testGetBeanProperties_whenJavaLangObject_thenReturnBeanPropertiesFull() {
    // Arrange
    BeanSupportFull beanSupportFull = new BeanSupportFull();
    Class<Object> type = Object.class;

    // Act
    BeanProperties actualBeanProperties = beanSupportFull.getBeanProperties(type);

    // Assert
    assertTrue(actualBeanProperties instanceof BeanPropertiesFull);
    Map<String, BeanProperty> stringBeanPropertyMap = ((BeanPropertiesFull) actualBeanProperties).properties;
    assertEquals(1, stringBeanPropertyMap.size());
    BeanProperty getResult = stringBeanPropertyMap.get("class");
    assertTrue(getResult instanceof BeanPropertyFull);
    assertEquals("class", getResult.getName());
    assertNull(getResult.getWriteMethod());
    Class<Class> expectedPropertyType = Class.class;
    assertEquals(expectedPropertyType, getResult.getPropertyType());
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, ((BeanPropertiesFull) actualBeanProperties).type);
  }
}
