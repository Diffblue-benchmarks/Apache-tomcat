package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardService;
import org.apache.coyote.RequestInfo;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.net.SocketEvent;
import org.junit.Test;

public class CoyoteAdapterDiffblueTest {
  /**
   * Test {@link CoyoteAdapter#CoyoteAdapter(Connector)}.
   * <p>
   * Method under test: {@link CoyoteAdapter#CoyoteAdapter(Connector)}
   */
  @Test
  public void testNewCoyoteAdapter() {
    // Arrange, Act and Assert
    assertEquals("Catalina", (new CoyoteAdapter(new Connector())).getDomain());
  }

  /**
   * Test {@link CoyoteAdapter#asyncDispatch(Request, Response, SocketEvent)}.
   * <ul>
   *   <li>When {@link org.apache.coyote.Request} (default constructor).</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#asyncDispatch(org.apache.coyote.Request, org.apache.coyote.Response, SocketEvent)}
   */
  @Test
  public void testAsyncDispatch_whenRequest_thenThrowIllegalStateException() throws Exception {
    // Arrange
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(new Connector());
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> coyoteAdapter.asyncDispatch(req, new org.apache.coyote.Response(), SocketEvent.OPEN_READ));
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector(new AjpNio2Protocol());
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@code 8080}.</li>
   *   <li>When {@link org.apache.coyote.Request} (default constructor) ServerPort is {@code 8080}.</li>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) ServerPort is {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_given8080_whenRequestServerPortIs8080_thenRequestServerPortIs8080() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);

    org.apache.coyote.Request req = new org.apache.coyote.Request();
    req.setServerPort(8080);

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(8080, req.getServerPort());
    assertEquals(8080, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} ProxyPort is {@code 8080}.</li>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) ServerPort is {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_givenConnectorProxyPortIs8080_thenRequestServerPortIs8080() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setProxyPort(8080);
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(8080, req.getServerPort());
    assertEquals(8080, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} Scheme is {@code https}.</li>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) scheme String is {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_givenConnectorSchemeIsHttps_thenRequestSchemeStringIsHttps() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setScheme("https");
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("https", schemeResult.getString());
    assertEquals(443, req.getServerPort());
    assertEquals(443, req.getRequestProcessor().getServerPort());
    assertEquals(5, schemeResult.getLength());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} Scheme is {@code null}.</li>
   *   <li>When {@link org.apache.coyote.Request} (default constructor).</li>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) scheme Length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_givenConnectorSchemeIsNull_whenRequest_thenRequestSchemeLengthIsZero() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setScheme(null);
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert that nothing has changed
    assertEquals(0, req.scheme().getLength());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} UseIPVHosts is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_givenConnectorUseIPVHostsIsTrue() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setUseIPVHosts(true);
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} XpoweredBy is {@code true}.</li>
   *   <li>Then {@link org.apache.coyote.Response} (default constructor) MimeHeaders size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_givenConnectorXpoweredByIsTrue_thenResponseMimeHeadersSizeIsOne() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setXpoweredBy(true);
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();
    org.apache.coyote.Response res = new org.apache.coyote.Response();

    // Act
    coyoteAdapter.service(req, res);

    // Assert
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(1, res.getMimeHeaders().size());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) RequestProcessor VirtualHost is {@code HTTP/1.1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_thenRequestRequestProcessorVirtualHostIsHttp11() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setProxyName("HTTP/1.1");
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertEquals("HTTP/1.1", requestProcessor.getVirtualHost());
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) RequestProcessor VirtualHost is {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_thenRequestRequestProcessorVirtualHostIsHttps() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setProxyName("https");
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertEquals("https", requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#service(Request, Response)}.
   * <ul>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) RequestProcessor VirtualHost is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#service(org.apache.coyote.Request, org.apache.coyote.Response)}
   */
  @Test
  public void testService_thenRequestRequestProcessorVirtualHostIsNull() throws Exception {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());

    Connector connector = new Connector();
    connector.setService(service);
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();

    // Act
    coyoteAdapter.service(req, new org.apache.coyote.Response());

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    RequestInfo requestProcessor = req.getRequestProcessor();
    assertNull(requestProcessor.getVirtualHost());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, requestProcessor.getServerPort());
  }

  /**
   * Test {@link CoyoteAdapter#getDomain()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#getDomain()}
   */
  @Test
  public void testGetDomain_givenConnectorDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    Connector connector = new Connector();
    connector.setDomain("Catalina");

    // Act and Assert
    assertEquals("Catalina", (new CoyoteAdapter(connector)).getDomain());
  }

  /**
   * Test {@link CoyoteAdapter#getDomain()}.
   * <ul>
   *   <li>Given {@link Connector#Connector()} Service is {@link StandardService} (default constructor).</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#getDomain()}
   */
  @Test
  public void testGetDomain_givenConnectorServiceIsStandardService_thenReturnCatalina() {
    // Arrange
    Connector connector = new Connector();
    connector.setService(new StandardService());

    // Act and Assert
    assertEquals("Catalina", (new CoyoteAdapter(connector)).getDomain());
  }

  /**
   * Test {@link CoyoteAdapter#getDomain()}.
   * <ul>
   *   <li>Given {@link CoyoteAdapter#CoyoteAdapter(Connector)} with connector is {@link Connector#Connector()}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#getDomain()}
   */
  @Test
  public void testGetDomain_givenCoyoteAdapterWithConnectorIsConnector_thenReturnCatalina() {
    // Arrange, Act and Assert
    assertEquals("Catalina", (new CoyoteAdapter(new Connector())).getDomain());
  }

  /**
   * Test {@link CoyoteAdapter#postParseRequest(Request, Request, Response, Response)}.
   * <ul>
   *   <li>Then {@link org.apache.coyote.Request} (default constructor) scheme String is {@code http}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteAdapter#postParseRequest(org.apache.coyote.Request, Request, org.apache.coyote.Response, Response)}
   */
  @Test
  public void testPostParseRequest_thenRequestSchemeStringIsHttp() throws ServletException, IOException {
    // Arrange
    Connector connector = new Connector();
    connector.setService(new StandardService());
    CoyoteAdapter coyoteAdapter = new CoyoteAdapter(connector);
    org.apache.coyote.Request req = new org.apache.coyote.Request();
    Connector connector2 = new Connector();
    Request request = new Request(connector2, new org.apache.coyote.Request());

    org.apache.coyote.Response res = new org.apache.coyote.Response();

    // Act
    boolean actualPostParseRequestResult = coyoteAdapter.postParseRequest(req, request, res,
        new Response(new org.apache.coyote.Response()));

    // Assert
    MessageBytes schemeResult = req.scheme();
    assertEquals("http", schemeResult.getString());
    assertEquals(1, schemeResult.getType());
    assertEquals(4, schemeResult.getLength());
    assertEquals(80, req.getServerPort());
    assertEquals(80, req.getRequestProcessor().getServerPort());
    assertFalse(schemeResult.isNull());
    assertTrue(actualPostParseRequestResult);
  }
}
