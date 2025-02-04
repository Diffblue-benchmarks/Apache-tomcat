package org.apache.catalina.core;

import static org.junit.Assert.assertNull;
import org.junit.Test;

public class StandardWrapperFacadeDiffblueTest {
  /**
   * Test {@link StandardWrapperFacade#StandardWrapperFacade(StandardWrapper)}.
   * <p>
   * Method under test: {@link StandardWrapperFacade#StandardWrapperFacade(StandardWrapper)}
   */
  @Test
  public void testNewStandardWrapperFacade() {
    // Arrange and Act
    StandardWrapperFacade actualStandardWrapperFacade = new StandardWrapperFacade(new StandardWrapper());

    // Assert
    assertNull(actualStandardWrapperFacade.getServletContext());
    assertNull(actualStandardWrapperFacade.getServletName());
  }

  /**
   * Test {@link StandardWrapperFacade#getServletName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapperFacade#getServletName()}
   */
  @Test
  public void testGetServletName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapperFacade(new StandardWrapper())).getServletName());
  }

  /**
   * Test {@link StandardWrapperFacade#getServletContext()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapperFacade#getServletContext()}
   */
  @Test
  public void testGetServletContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapperFacade(new StandardWrapper())).getServletContext());
  }

  /**
   * Test {@link StandardWrapperFacade#getInitParameter(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardWrapperFacade#getInitParameter(String)}
   */
  @Test
  public void testGetInitParameter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardWrapperFacade(new StandardWrapper())).getInitParameter("Name"));
  }
}
