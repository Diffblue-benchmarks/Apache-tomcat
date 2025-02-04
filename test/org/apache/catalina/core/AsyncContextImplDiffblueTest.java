package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import async.AsyncStockServlet;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.apache.jasper.JasperException;
import org.apache.jasper.servlet.JspCServletContext;
import org.junit.Test;

public class AsyncContextImplDiffblueTest {
  /**
   * Test {@link AsyncContextImpl#AsyncContextImpl(Request)}.
   * <p>
   * Method under test: {@link AsyncContextImpl#AsyncContextImpl(Request)}
   */
  @Test
  public void testNewAsyncContextImpl() {
    // Arrange
    Connector connector = new Connector();

    // Act
    AsyncContextImpl actualAsyncContextImpl = new AsyncContextImpl(
        new Request(connector, new org.apache.coyote.Request()));

    // Assert
    assertEquals(-1L, actualAsyncContextImpl.getTimeout());
    assertFalse(actualAsyncContextImpl.isAvailable());
  }

  /**
   * Test {@link AsyncContextImpl#complete()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#complete()}
   */
  @Test
  public void testComplete_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).complete());
  }

  /**
   * Test {@link AsyncContextImpl#timeout()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#timeout()}
   */
  @Test
  public void testTimeout_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertTrue((new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).timeout());
  }

  /**
   * Test {@link AsyncContextImpl#dispatch(String)} with {@code path}.
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch(String)}
   */
  @Test
  public void testDispatchWithPath() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).dispatch("Path"));
  }

  /**
   * Test {@link AsyncContextImpl#dispatch(String)} with {@code path}.
   * <ul>
   *   <li>Given {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch(String)}
   */
  @Test
  public void testDispatchWithPath_givenAsyncContextImplWithRequestIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).dispatch("Path"));
  }

  /**
   * Test {@link AsyncContextImpl#dispatch(ServletContext, String)} with {@code servletContext}, {@code path}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch(ServletContext, String)}
   */
  @Test
  public void testDispatchWithServletContextPath_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).dispatch(null, "Path"));
  }

  /**
   * Test {@link AsyncContextImpl#dispatch(ServletContext, String)} with {@code servletContext}, {@code path}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch(ServletContext, String)}
   */
  @Test
  public void testDispatchWithServletContextPath_thenThrowUnsupportedOperationException()
      throws MalformedURLException, JasperException {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl asyncContextImpl = new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()));
    PrintWriter aLogWriter = new PrintWriter(new StringWriter());
    URL aResourceBaseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> asyncContextImpl.dispatch(
        new JspCServletContext(aLogWriter, aResourceBaseURL, new ParallelWebappClassLoader(), true, true), "Path"));
  }

  /**
   * Test {@link AsyncContextImpl#dispatch()}.
   * <ul>
   *   <li>Given {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch()}
   */
  @Test
  public void testDispatch_givenAsyncContextImplWithRequestIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).dispatch());
  }

  /**
   * Test {@link AsyncContextImpl#dispatch()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#dispatch()}
   */
  @Test
  public void testDispatch_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).dispatch());
  }

  /**
   * Test {@link AsyncContextImpl#getRequest()}.
   * <ul>
   *   <li>Given {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getRequest()}
   */
  @Test
  public void testGetRequest_givenAsyncContextImplWithRequestIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).getRequest());
  }

  /**
   * Test {@link AsyncContextImpl#getRequest()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getRequest()}
   */
  @Test
  public void testGetRequest_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).getRequest());
  }

  /**
   * Test {@link AsyncContextImpl#getResponse()}.
   * <ul>
   *   <li>Given {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getResponse()}
   */
  @Test
  public void testGetResponse_givenAsyncContextImplWithRequestIsNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).getResponse());
  }

  /**
   * Test {@link AsyncContextImpl#getResponse()}.
   * <ul>
   *   <li>Given {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getResponse()}
   */
  @Test
  public void testGetResponse_givenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).getResponse());
  }

  /**
   * Test {@link AsyncContextImpl#start(Runnable)}.
   * <ul>
   *   <li>Given {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@code null}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#start(Runnable)}
   */
  @Test
  public void testStart_givenAsyncContextImplWithRequestIsNull_thenThrowIllegalStateException() {
    // Arrange
    AsyncContextImpl asyncContextImpl = new AsyncContextImpl(null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> asyncContextImpl.start(new NioReceiver()));
  }

  /**
   * Test {@link AsyncContextImpl#addListener(AsyncListener)} with {@code listener}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#addListener(AsyncListener)}
   */
  @Test
  public void testAddListenerWithListener_thenThrowIllegalStateException() {
    // Arrange
    AsyncContextImpl asyncContextImpl = new AsyncContextImpl(null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> asyncContextImpl.addListener(new AsyncStockServlet()));
  }

  /**
   * Test {@link AsyncContextImpl#createListener(Class)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#createListener(Class)}
   */
  @Test
  public void testCreateListener_thenThrowIllegalStateException() throws ServletException {
    // Arrange
    AsyncContextImpl asyncContextImpl = new AsyncContextImpl(null);
    Class<AsyncListener> clazz = AsyncListener.class;

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> asyncContextImpl.createListener(clazz));
  }

  /**
   * Test {@link AsyncContextImpl#isStarted()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#isStarted()}
   */
  @Test
  public void testIsStarted_thenReturnFalse() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).isStarted());
  }

  /**
   * Test {@link AsyncContextImpl#isStarted()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#isStarted()}
   */
  @Test
  public void testIsStarted_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).isStarted());
  }

  /**
   * Test {@link AsyncContextImpl#hasOriginalRequestAndResponse()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#hasOriginalRequestAndResponse()}
   */
  @Test
  public void testHasOriginalRequestAndResponse_thenReturnTrue() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertTrue((new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request())))
        .hasOriginalRequestAndResponse());
  }

  /**
   * Test {@link AsyncContextImpl#hasOriginalRequestAndResponse()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#hasOriginalRequestAndResponse()}
   */
  @Test
  public void testHasOriginalRequestAndResponse_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).hasOriginalRequestAndResponse());
  }

  /**
   * Test {@link AsyncContextImpl#getTimeout()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getTimeout()}
   */
  @Test
  public void testGetTimeout_thenReturnMinusOne() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(-1L, (new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).getTimeout());
  }

  /**
   * Test {@link AsyncContextImpl#getTimeout()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#getTimeout()}
   */
  @Test
  public void testGetTimeout_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).getTimeout());
  }

  /**
   * Test {@link AsyncContextImpl#setTimeout(long)}.
   * <ul>
   *   <li>Then {@link AsyncContextImpl#AsyncContextImpl(Request)} with request is {@link Request#Request(Connector, Request)} Timeout is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#setTimeout(long)}
   */
  @Test
  public void testSetTimeout_thenAsyncContextImplWithRequestIsRequestTimeoutIsTen() {
    // Arrange
    Connector connector = new Connector();
    AsyncContextImpl asyncContextImpl = new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()));

    // Act
    asyncContextImpl.setTimeout(10L);

    // Assert
    assertEquals(10L, asyncContextImpl.getTimeout());
  }

  /**
   * Test {@link AsyncContextImpl#setTimeout(long)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncContextImpl#setTimeout(long)}
   */
  @Test
  public void testSetTimeout_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new AsyncContextImpl(null)).setTimeout(10L));
  }

  /**
   * Test {@link AsyncContextImpl#isAvailable()}.
   * <p>
   * Method under test: {@link AsyncContextImpl#isAvailable()}
   */
  @Test
  public void testIsAvailable() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new AsyncContextImpl(new Request(connector, new org.apache.coyote.Request()))).isAvailable());
  }
}
