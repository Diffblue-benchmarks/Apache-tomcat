package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import org.junit.Test;

public class MBeanDumperDiffblueTest {
  /**
   * Test {@link MBeanDumper#dumpBeans(MBeanServer, Set)}.
   * <ul>
   *   <li>Given Instance is empty string.</li>
   *   <li>Then return {@code Name: *:*}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanDumper#dumpBeans(MBeanServer, Set)}
   */
  @Test
  public void testDumpBeans_givenInstanceIsEmptyString_thenReturnName()
      throws NullPointerException, MalformedObjectNameException {
    // Arrange
    HashSet<ObjectName> names = new HashSet<>();
    names.add(ObjectName.getInstance(""));

    // Act and Assert
    assertEquals("Name: *:*\r\n\r\n", MBeanDumper.dumpBeans(null, names));
  }

  /**
   * Test {@link MBeanDumper#dumpBeans(MBeanServer, Set)}.
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanDumper#dumpBeans(MBeanServer, Set)}
   */
  @Test
  public void testDumpBeans_whenHashSet_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", MBeanDumper.dumpBeans(null, new HashSet<>()));
  }

  /**
   * Test {@link MBeanDumper#escape(String)}.
   * <p>
   * Method under test: {@link MBeanDumper#escape(String)}
   */
  @Test
  public void testEscape() {
    // Arrange, Act and Assert
    assertEquals("42", MBeanDumper.escape("42"));
  }
}
