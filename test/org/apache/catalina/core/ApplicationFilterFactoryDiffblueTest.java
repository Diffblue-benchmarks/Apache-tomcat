package org.apache.catalina.core;

import static org.junit.Assert.assertNull;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.junit.Test;

public class ApplicationFilterFactoryDiffblueTest {
  /**
   * Test {@link ApplicationFilterFactory#createFilterChain(ServletRequest, Wrapper, Servlet)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterFactory#createFilterChain(ServletRequest, Wrapper, Servlet)}
   */
  @Test
  public void testCreateFilterChain_givenNull_thenReturnNull() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setFilterChain(null);

    StandardWrapper wrapper = new StandardWrapper();
    wrapper.setParent(new StandardContext());

    // Act and Assert
    assertNull(ApplicationFilterFactory.createFilterChain(request, wrapper, null));
  }
}
