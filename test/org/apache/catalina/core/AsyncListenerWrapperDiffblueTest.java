package org.apache.catalina.core;

import static org.junit.Assert.assertSame;
import async.AsyncStockServlet;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.filters.TestRemoteIpFilter;
import org.apache.catalina.filters.TestRemoteIpFilter.MockHttpServletRequest;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.junit.Test;

public class AsyncListenerWrapperDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link AsyncListenerWrapper}
   *   <li>{@link AsyncListenerWrapper#setListener(AsyncListener)}
   *   <li>{@link AsyncListenerWrapper#setServletRequest(ServletRequest)}
   *   <li>{@link AsyncListenerWrapper#setServletResponse(ServletResponse)}
   *   <li>{@link AsyncListenerWrapper#getListener()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    AsyncListenerWrapper actualAsyncListenerWrapper = new AsyncListenerWrapper();
    AsyncStockServlet listener = new AsyncStockServlet();
    actualAsyncListenerWrapper.setListener(listener);
    actualAsyncListenerWrapper
        .setServletRequest(new ServletRequestWrapper(new HttpServletRequestWrapper(new MockHttpServletRequest())));
    actualAsyncListenerWrapper.setServletResponse(
        new ServletResponseWrapper(new HttpServletResponseWrapper(new TesterHttpServletResponse())));

    // Assert
    assertSame(listener, actualAsyncListenerWrapper.getListener());
  }
}
