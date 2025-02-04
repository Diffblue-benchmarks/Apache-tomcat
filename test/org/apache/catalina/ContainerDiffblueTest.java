package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.junit.Test;

public class ContainerDiffblueTest {
  /**
   * Test {@link Container#getConfigPath(Container, String)}.
   * <ul>
   *   <li>Given {@code Container}.</li>
   *   <li>Then return {@code Container/Resource Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getConfigPath(Container, String)}
   */
  @Test
  public void testGetConfigPath_givenContainer_thenReturnContainerResourceName() {
    // Arrange
    StandardHost container = new StandardHost();
    container.setXmlBase("Container");

    // Act and Assert
    assertEquals("Container/Resource Name", Container.getConfigPath(container, "Resource Name"));
  }

  /**
   * Test {@link Container#getConfigPath(Container, String)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link StandardHost} (default constructor) XmlBase is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getConfigPath(Container, String)}
   */
  @Test
  public void testGetConfigPath_givenNull_whenStandardHostXmlBaseIsNull() {
    // Arrange
    StandardHost container = new StandardHost();
    container.setXmlBase(null);

    // Act and Assert
    assertEquals("conf/null/Resource Name", Container.getConfigPath(container, "Resource Name"));
  }

  /**
   * Test {@link Container#getConfigPath(Container, String)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code conf/Resource Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getConfigPath(Container, String)}
   */
  @Test
  public void testGetConfigPath_whenStandardContext_thenReturnConfResourceName() {
    // Arrange, Act and Assert
    assertEquals("conf/Resource Name", Container.getConfigPath(new StandardContext(), "Resource Name"));
  }

  /**
   * Test {@link Container#getConfigPath(Container, String)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then return {@code conf/null/Resource Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getConfigPath(Container, String)}
   */
  @Test
  public void testGetConfigPath_whenStandardEngine_thenReturnConfNullResourceName() {
    // Arrange, Act and Assert
    assertEquals("conf/null/Resource Name", Container.getConfigPath(new StandardEngine(), "Resource Name"));
  }

  /**
   * Test {@link Container#getService(Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getService(Container)}
   */
  @Test
  public void testGetService_whenStandardContext() {
    // Arrange, Act and Assert
    assertNull(Container.getService(new StandardContext()));
  }

  /**
   * Test {@link Container#getService(Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Container#getService(Container)}
   */
  @Test
  public void testGetService_whenStandardEngine() {
    // Arrange, Act and Assert
    assertNull(Container.getService(new StandardEngine()));
  }
}
