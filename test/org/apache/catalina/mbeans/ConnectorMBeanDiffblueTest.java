package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.junit.Test;

public class ConnectorMBeanDiffblueTest {
  /**
   * Test {@link ConnectorMBean#getAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorMBean#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ConnectorMBean()).getAttribute(null));
  }

  /**
   * Test {@link ConnectorMBean#setAttribute(Attribute)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeOperationsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorMBean#setAttribute(Attribute)}
   */
  @Test
  public void testSetAttribute_whenNull_thenThrowRuntimeOperationsException()
      throws AttributeNotFoundException, MBeanException, ReflectionException {
    // Arrange, Act and Assert
    assertThrows(RuntimeOperationsException.class, () -> (new ConnectorMBean()).setAttribute(null));
  }

  /**
   * Test new {@link ConnectorMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConnectorMBean}
   */
  @Test
  public void testNewConnectorMBean() {
    // Arrange and Act
    ConnectorMBean actualConnectorMBean = new ConnectorMBean();

    // Assert
    assertNull(actualConnectorMBean.getModelerType());
    assertNull(actualConnectorMBean.getJmxName());
  }
}
