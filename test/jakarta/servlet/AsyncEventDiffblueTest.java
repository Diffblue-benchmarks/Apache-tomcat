package jakarta.servlet;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AsyncContextImpl;
import org.apache.catalina.filters.TestRemoteIpFilter;
import org.apache.catalina.filters.TestRemoteIpFilter.MockHttpServletRequest;
import org.apache.catalina.filters.TesterHttpServletResponse;
import org.apache.coyote.Request;
import org.junit.Test;

public class AsyncEventDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AsyncEvent#AsyncEvent(AsyncContext, ServletRequest, ServletResponse, Throwable)}
   *   <li>{@link AsyncEvent#getAsyncContext()}
   *   <li>{@link AsyncEvent#getSuppliedRequest()}
   *   <li>{@link AsyncEvent#getSuppliedResponse()}
   *   <li>{@link AsyncEvent#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl context = new AsyncContextImpl(
        new org.apache.catalina.connector.Request(connector, new Request()));
    ServletRequestWrapper request = new ServletRequestWrapper(
        new HttpServletRequestWrapper(new MockHttpServletRequest()));
    ServletResponseWrapper response = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));
    Throwable throwable = new Throwable();

    // Act
    AsyncEvent actualAsyncEvent = new AsyncEvent(context, request, response, throwable);
    AsyncContext actualAsyncContext = actualAsyncEvent.getAsyncContext();
    ServletRequest actualSuppliedRequest = actualAsyncEvent.getSuppliedRequest();
    ServletResponse actualSuppliedResponse = actualAsyncEvent.getSuppliedResponse();

    // Assert
    assertSame(request, actualSuppliedRequest);
    assertSame(response, actualSuppliedResponse);
    assertSame(throwable, actualAsyncEvent.getThrowable());
    assertSame(context, actualAsyncContext);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return SuppliedRequest is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AsyncEvent#AsyncEvent(AsyncContext)}
   *   <li>{@link AsyncEvent#getAsyncContext()}
   *   <li>{@link AsyncEvent#getSuppliedRequest()}
   *   <li>{@link AsyncEvent#getSuppliedResponse()}
   *   <li>{@link AsyncEvent#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_thenReturnSuppliedRequestIsNull() {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl context = new AsyncContextImpl(
        new org.apache.catalina.connector.Request(connector, new Request()));

    // Act
    AsyncEvent actualAsyncEvent = new AsyncEvent(context);
    AsyncContext actualAsyncContext = actualAsyncEvent.getAsyncContext();
    ServletRequest actualSuppliedRequest = actualAsyncEvent.getSuppliedRequest();
    ServletResponse actualSuppliedResponse = actualAsyncEvent.getSuppliedResponse();

    // Assert
    assertNull(actualSuppliedRequest);
    assertNull(actualSuppliedResponse);
    assertNull(actualAsyncEvent.getThrowable());
    assertSame(context, actualAsyncContext);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return Throwable is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AsyncEvent#AsyncEvent(AsyncContext, ServletRequest, ServletResponse)}
   *   <li>{@link AsyncEvent#getAsyncContext()}
   *   <li>{@link AsyncEvent#getSuppliedRequest()}
   *   <li>{@link AsyncEvent#getSuppliedResponse()}
   *   <li>{@link AsyncEvent#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_thenReturnThrowableIsNull() {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl context = new AsyncContextImpl(
        new org.apache.catalina.connector.Request(connector, new Request()));
    ServletRequestWrapper request = new ServletRequestWrapper(
        new HttpServletRequestWrapper(new MockHttpServletRequest()));
    ServletResponseWrapper response = new ServletResponseWrapper(
        new HttpServletResponseWrapper(new TesterHttpServletResponse()));

    // Act
    AsyncEvent actualAsyncEvent = new AsyncEvent(context, request, response);
    AsyncContext actualAsyncContext = actualAsyncEvent.getAsyncContext();
    ServletRequest actualSuppliedRequest = actualAsyncEvent.getSuppliedRequest();
    ServletResponse actualSuppliedResponse = actualAsyncEvent.getSuppliedResponse();

    // Assert
    assertNull(actualAsyncEvent.getThrowable());
    assertSame(request, actualSuppliedRequest);
    assertSame(response, actualSuppliedResponse);
    assertSame(context, actualAsyncContext);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return SuppliedRequest is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AsyncEvent#AsyncEvent(AsyncContext, Throwable)}
   *   <li>{@link AsyncEvent#getAsyncContext()}
   *   <li>{@link AsyncEvent#getSuppliedRequest()}
   *   <li>{@link AsyncEvent#getSuppliedResponse()}
   *   <li>{@link AsyncEvent#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenThrowable_thenReturnSuppliedRequestIsNull() {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl context = new AsyncContextImpl(
        new org.apache.catalina.connector.Request(connector, new Request()));
    Throwable throwable = new Throwable();

    // Act
    AsyncEvent actualAsyncEvent = new AsyncEvent(context, throwable);
    AsyncContext actualAsyncContext = actualAsyncEvent.getAsyncContext();
    ServletRequest actualSuppliedRequest = actualAsyncEvent.getSuppliedRequest();
    ServletResponse actualSuppliedResponse = actualAsyncEvent.getSuppliedResponse();

    // Assert
    assertNull(actualSuppliedRequest);
    assertNull(actualSuppliedResponse);
    assertSame(throwable, actualAsyncEvent.getThrowable());
    assertSame(context, actualAsyncContext);
  }
}
