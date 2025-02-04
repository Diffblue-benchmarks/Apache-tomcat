package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertNull;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import org.junit.Test;

public class CertificateStoreAppenderDiffblueTest {
  /**
   * Test {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}
   */
  @Test
  public void testCheckAttribute_thenReturnNull() throws IntrospectionException {
    // Arrange
    CertificateStoreAppender certificateStoreAppender = new CertificateStoreAppender();
    StoreDescription desc = new StoreDescription();

    // Act and Assert
    assertNull(certificateStoreAppender.checkAttribute(desc,
        new IndexedPropertyDescriptor("foo", null, null, null, null), "Attribute Name", "Bean", "Bean2"));
  }

  /**
   * Test {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}
   */
  @Test
  public void testCheckAttribute_thenReturnNull2() throws IntrospectionException {
    // Arrange
    CertificateStoreAppender certificateStoreAppender = new CertificateStoreAppender();
    StoreDescription desc = new StoreDescription();

    // Act and Assert
    assertNull(certificateStoreAppender.checkAttribute(desc,
        new IndexedPropertyDescriptor("foo", null, null, null, null), "type", "Bean", "Bean2"));
  }

  /**
   * Test {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateStoreAppender#checkAttribute(StoreDescription, PropertyDescriptor, String, Object, Object)}
   */
  @Test
  public void testCheckAttribute_whenNull_thenReturnNull() throws IntrospectionException {
    // Arrange
    CertificateStoreAppender certificateStoreAppender = new CertificateStoreAppender();

    // Act and Assert
    assertNull(certificateStoreAppender.checkAttribute(null,
        new IndexedPropertyDescriptor("foo", null, null, null, null), "type", "Bean", "Bean2"));
  }
}
