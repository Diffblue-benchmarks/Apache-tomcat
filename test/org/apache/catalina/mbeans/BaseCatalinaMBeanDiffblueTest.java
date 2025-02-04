package org.apache.catalina.mbeans;

import static org.junit.Assert.assertThrows;
import javax.management.MBeanException;
import org.junit.Test;

public class BaseCatalinaMBeanDiffblueTest {
  /**
   * Test {@link BaseCatalinaMBean#doGetManagedResource()}.
   * <p>
   * Method under test: {@link BaseCatalinaMBean#doGetManagedResource()}
   */
  @Test
  public void testDoGetManagedResource() throws MBeanException {
    // Arrange
    ClassNameMBean<Object> classNameMBean = new ClassNameMBean<>();

    // Act and Assert
    assertThrows(MBeanException.class, () -> classNameMBean.doGetManagedResource());
  }

  /**
   * Test {@link BaseCatalinaMBean#newInstance(String)}.
   * <p>
   * Method under test: {@link BaseCatalinaMBean#newInstance(String)}
   */
  @Test
  public void testNewInstance() throws MBeanException {
    // Arrange, Act and Assert
    assertThrows(MBeanException.class, () -> BaseCatalinaMBean.newInstance("Type"));
  }
}
