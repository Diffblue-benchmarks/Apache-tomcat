package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.net.InetAddress;
import org.junit.Test;

public class StoreAppenderDiffblueTest {
  /**
   * Test {@link StoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}
   */
  @Test
  public void testCheckAttribute_thenReturnNull() throws IntrospectionException {
    // Arrange
    StoreAppender storeAppender = new StoreAppender();
    StoreDescription desc = new StoreDescription();

    // Act and Assert
    assertNull(storeAppender.checkAttribute(desc, new IndexedPropertyDescriptor("foo", null, null, null, null),
        "Attribute Name", "Bean", "Bean2"));
  }

  /**
   * Test {@link StoreAppender#isPrintValue(Object, Object, String, StoreDescription)}.
   * <p>
   * Method under test: {@link StoreAppender#isPrintValue(Object, Object, String, StoreDescription)}
   */
  @Test
  public void testIsPrintValue() {
    // Arrange
    StoreAppender storeAppender = new StoreAppender();

    // Act and Assert
    assertTrue(storeAppender.isPrintValue("Bean", "Bean2", "Attr Name", new StoreDescription()));
  }

  /**
   * Test {@link StoreAppender#defaultInstance(Object)}.
   * <ul>
   *   <li>When {@code Bean}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreAppender#defaultInstance(Object)}
   */
  @Test
  public void testDefaultInstance_whenBean_thenReturnEmptyString() throws ReflectiveOperationException {
    // Arrange, Act and Assert
    assertEquals("", (new StoreAppender()).defaultInstance("Bean"));
  }

  /**
   * Test {@link StoreAppender#isPersistable(Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreAppender#isPersistable(Class)}
   */
  @Test
  public void testIsPersistable_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    StoreAppender storeAppender = new StoreAppender();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(storeAppender.isPersistable(clazz));
  }

  /**
   * Test {@link StoreAppender#isPersistable(Class)}.
   * <ul>
   *   <li>When {@code InetAddress}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreAppender#isPersistable(Class)}
   */
  @Test
  public void testIsPersistable_whenJavaNetInetAddress_thenReturnTrue() {
    // Arrange
    StoreAppender storeAppender = new StoreAppender();
    Class<InetAddress> clazz = InetAddress.class;

    // Act and Assert
    assertTrue(storeAppender.isPersistable(clazz));
  }
}
