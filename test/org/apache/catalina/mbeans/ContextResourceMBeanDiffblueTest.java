package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.junit.Test;

public class ContextResourceMBeanDiffblueTest {
  /**
   * Test {@link ContextResourceMBean#getAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextResourceMBean#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ContextResourceMBean()).getAttribute(null));
  }

  /**
   * Test {@link ContextResourceMBean#setAttribute(Attribute)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextResourceMBean#setAttribute(Attribute)}
   */
  @Test
  public void testSetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ContextResourceMBean()).setAttribute(null));
  }

  /**
   * Test new {@link ContextResourceMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ContextResourceMBean}
   */
  @Test
  public void testNewContextResourceMBean() {
    // Arrange and Act
    ContextResourceMBean actualContextResourceMBean = new ContextResourceMBean();

    // Assert
    assertNull(actualContextResourceMBean.getModelerType());
    assertNull(actualContextResourceMBean.getJmxName());
  }
}
