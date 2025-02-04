package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.junit.Test;

public class ContextResourceLinkMBeanDiffblueTest {
  /**
   * Test {@link ContextResourceLinkMBean#getAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextResourceLinkMBean#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ContextResourceLinkMBean()).getAttribute(null));
  }

  /**
   * Test {@link ContextResourceLinkMBean#setAttribute(Attribute)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextResourceLinkMBean#setAttribute(Attribute)}
   */
  @Test
  public void testSetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ContextResourceLinkMBean()).setAttribute(null));
  }

  /**
   * Test new {@link ContextResourceLinkMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ContextResourceLinkMBean}
   */
  @Test
  public void testNewContextResourceLinkMBean() {
    // Arrange and Act
    ContextResourceLinkMBean actualContextResourceLinkMBean = new ContextResourceLinkMBean();

    // Assert
    assertNull(actualContextResourceLinkMBean.getModelerType());
    assertNull(actualContextResourceLinkMBean.getJmxName());
  }
}
