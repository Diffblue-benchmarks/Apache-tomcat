package org.apache.catalina.mbeans;

import static org.junit.Assert.assertNull;
import javax.management.MalformedObjectNameException;
import org.junit.Test;

public class NamingResourcesMBeanDiffblueTest {
  /**
   * Test {@link NamingResourcesMBean#addEnvironment(String, String, String)}.
   * <p>
   * Method under test: {@link NamingResourcesMBean#addEnvironment(String, String, String)}
   */
  @Test
  public void testAddEnvironment() throws MalformedObjectNameException {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesMBean()).addEnvironment("Env Name", "Type", "42"));
  }

  /**
   * Test {@link NamingResourcesMBean#addResource(String, String)}.
   * <p>
   * Method under test: {@link NamingResourcesMBean#addResource(String, String)}
   */
  @Test
  public void testAddResource() throws MalformedObjectNameException {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesMBean()).addResource("Resource Name", "Type"));
  }

  /**
   * Test {@link NamingResourcesMBean#addResourceLink(String, String)}.
   * <p>
   * Method under test: {@link NamingResourcesMBean#addResourceLink(String, String)}
   */
  @Test
  public void testAddResourceLink() throws MalformedObjectNameException {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesMBean()).addResourceLink("Resource Link Name", "Type"));
  }

  /**
   * Test new {@link NamingResourcesMBean} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NamingResourcesMBean}
   */
  @Test
  public void testNewNamingResourcesMBean() {
    // Arrange and Act
    NamingResourcesMBean actualNamingResourcesMBean = new NamingResourcesMBean();

    // Assert
    assertNull(actualNamingResourcesMBean.getClassName());
    assertNull(actualNamingResourcesMBean.getModelerType());
    assertNull(actualNamingResourcesMBean.getObjectName());
    assertNull(actualNamingResourcesMBean.getJmxName());
    assertNull(actualNamingResourcesMBean.managed);
  }
}
