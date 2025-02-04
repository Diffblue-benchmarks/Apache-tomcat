package org.apache.catalina.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.catalina.WebResource;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.servlets.WebdavServlet.MemoryPropertyStore;
import org.apache.catalina.servlets.WebdavServlet.PropertyUpdateType;
import org.apache.catalina.servlets.WebdavServlet.ProppatchOperation;
import org.apache.catalina.util.XMLWriter;
import org.apache.catalina.webresources.EmptyResource;
import org.apache.catalina.webresources.ExtractingRoot;
import org.junit.Test;
import org.w3c.dom.Node;

public class WebdavServletDiffblueTest {
  /**
   * Test {@link WebdavServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfHeaders_thenReturnTrue() throws IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act and Assert
    assertTrue(
        webdavServlet.checkIfHeaders(request, response, new EmptyResource(new ExtractingRoot(), "Web App Path")));
  }

  /**
   * Test {@link WebdavServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#checkIfHeaders(HttpServletRequest, HttpServletResponse, WebResource)}
   */
  @Test
  public void testCheckIfHeaders_thenReturnTrue2() throws IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));

    // Act and Assert
    assertTrue(webdavServlet.checkIfHeaders(request,
        new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response())), null));
  }

  /**
   * Test {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)} with {@code request}, {@code allowEmptyPath}.
   * <p>
   * Method under test: {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)}
   */
  @Test
  public void testGetRelativePathWithRequestAllowEmptyPath() {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("/",
        webdavServlet.getRelativePath(
            new HttpServletRequestWrapper(new RequestFacade(new Request(connector, new org.apache.coyote.Request()))),
            true));
  }

  /**
   * Test {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)} with {@code request}, {@code allowEmptyPath}.
   * <p>
   * Method under test: {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)}
   */
  @Test
  public void testGetRelativePathWithRequestAllowEmptyPath2() {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAttribute("jakarta.servlet.include.request_uri", "Value");

    // Act and Assert
    assertEquals("/", webdavServlet.getRelativePath(new HttpServletRequestWrapper(new RequestFacade(request)), true));
  }

  /**
   * Test {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)} with {@code request}, {@code allowEmptyPath}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#getRelativePath(HttpServletRequest, boolean)}
   */
  @Test
  public void testGetRelativePathWithRequestAllowEmptyPath_whenFalse_thenReturnSlash() {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("/",
        webdavServlet.getRelativePath(
            new HttpServletRequestWrapper(new RequestFacade(new Request(connector, new org.apache.coyote.Request()))),
            false));
  }

  /**
   * Test {@link WebdavServlet#getPathPrefix(HttpServletRequest)}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#getPathPrefix(HttpServletRequest)}
   */
  @Test
  public void testGetPathPrefix_thenReturnEmptyString() {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("", webdavServlet.getPathPrefix(
        new HttpServletRequestWrapper(new RequestFacade(new Request(connector, new org.apache.coyote.Request())))));
  }

  /**
   * Test {@link WebdavServlet#doCopy(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link HttpServletResponseWrapper#HttpServletResponseWrapper(HttpServletResponse)} with response is {@link Response#Response(Response)} Response {@link Response}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#doCopy(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoCopy_thenHttpServletResponseWrapperWithResponseIsResponseResponseResponse() throws IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    webdavServlet.doCopy(req, resp);

    // Assert
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(403, response2.getStatus());
    assertEquals(403, resp.getStatus());
    assertEquals(403, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(403, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link WebdavServlet#doMove(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link HttpServletResponseWrapper#HttpServletResponseWrapper(HttpServletResponse)} with response is {@link Response#Response(Response)} Response {@link Response}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#doMove(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoMove_thenHttpServletResponseWrapperWithResponseIsResponseResponseResponse() throws IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    webdavServlet.doMove(req, resp);

    // Assert
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(403, response2.getStatus());
    assertEquals(403, resp.getStatus());
    assertEquals(403, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(403, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link WebdavServlet#doLock(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link HttpServletResponseWrapper#HttpServletResponseWrapper(HttpServletResponse)} with response is {@link Response#Response(Response)} Response {@link Response}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebdavServlet#doLock(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoLock_thenHttpServletResponseWrapperWithResponseIsResponseResponseResponse()
      throws ServletException, IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    webdavServlet.doLock(req, resp);

    // Assert
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(403, response2.getStatus());
    assertEquals(403, resp.getStatus());
    assertEquals(403, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(403, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link WebdavServlet#doUnlock(HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link WebdavServlet#doUnlock(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testDoUnlock() throws IOException {
    // Arrange
    WebdavServlet webdavServlet = new WebdavServlet();
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new Request(connector, new org.apache.coyote.Request())));
    HttpServletResponseWrapper resp = new HttpServletResponseWrapper(new Response(new org.apache.coyote.Response()));

    // Act
    webdavServlet.doUnlock(req, resp);

    // Assert
    ServletResponse response = resp.getResponse();
    assertTrue(response instanceof Response);
    HttpServletResponse response2 = ((Response) response).getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(403, response2.getStatus());
    assertEquals(403, resp.getStatus());
    assertEquals(403, ((Response) response).getStatus());
    org.apache.coyote.Response coyoteResponse = ((Response) response).getCoyoteResponse();
    assertEquals(403, coyoteResponse.getStatus());
    assertTrue(((Response) response).isAppCommitted());
    assertTrue(((Response) response).isError());
    assertTrue(((Response) response).isErrorReportRequired());
    assertTrue(((Response) response).isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test MemoryPropertyStore {@link MemoryPropertyStore#propfind(String, Node, boolean, XMLWriter)}.
   * <ul>
   *   <li>When {@code Resource}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryPropertyStore#propfind(String, Node, boolean, XMLWriter)}
   */
  @Test
  public void testMemoryPropertyStorePropfind_whenResource_thenReturnFalse() {
    // Arrange
    MemoryPropertyStore memoryPropertyStore = new MemoryPropertyStore();
    IIOMetadataNode property = new IIOMetadataNode("foo");

    // Act and Assert
    assertFalse(memoryPropertyStore.propfind("Resource", property, true, new XMLWriter()));
  }

  /**
   * Test new {@link WebdavServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WebdavServlet}
   */
  @Test
  public void testNewWebdavServlet() {
    // Arrange and Act
    WebdavServlet actualWebdavServlet = new WebdavServlet();

    // Assert
    assertNull(actualWebdavServlet.getServletConfig());
    assertFalse(actualWebdavServlet.isListings());
  }

  /**
   * Test ProppatchOperation getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ProppatchOperation#setStatusCode(int)}
   *   <li>{@link ProppatchOperation#getPropertyNode()}
   *   <li>{@link ProppatchOperation#getProtectedProperty()}
   *   <li>{@link ProppatchOperation#getStatusCode()}
   *   <li>{@link ProppatchOperation#getUpdateType()}
   * </ul>
   */
  @Test
  public void testProppatchOperationGettersAndSetters() {
    // Arrange
    IIOMetadataNode propertyNode = new IIOMetadataNode("foo");
    ProppatchOperation proppatchOperation = new ProppatchOperation(PropertyUpdateType.SET, propertyNode);

    // Act
    proppatchOperation.setStatusCode(1);
    Node actualPropertyNode = proppatchOperation.getPropertyNode();
    boolean actualProtectedProperty = proppatchOperation.getProtectedProperty();
    int actualStatusCode = proppatchOperation.getStatusCode();

    // Assert
    assertEquals(1, actualStatusCode);
    assertEquals(PropertyUpdateType.SET, proppatchOperation.getUpdateType());
    assertFalse(actualProtectedProperty);
    assertSame(propertyNode, actualPropertyNode);
  }

  /**
   * Test ProppatchOperation {@link ProppatchOperation#ProppatchOperation(PropertyUpdateType, Node)}.
   * <p>
   * Method under test: {@link ProppatchOperation#ProppatchOperation(PropertyUpdateType, Node)}
   */
  @Test
  public void testProppatchOperationNewProppatchOperation() {
    // Arrange
    IIOMetadataNode propertyNode = new IIOMetadataNode("foo");

    // Act
    ProppatchOperation actualProppatchOperation = new ProppatchOperation(PropertyUpdateType.SET, propertyNode);

    // Assert
    Node propertyNode2 = actualProppatchOperation.getPropertyNode();
    assertTrue(propertyNode2 instanceof IIOMetadataNode);
    assertEquals(200, actualProppatchOperation.getStatusCode());
    assertEquals(PropertyUpdateType.SET, actualProppatchOperation.getUpdateType());
    assertFalse(actualProppatchOperation.getProtectedProperty());
    assertSame(propertyNode, propertyNode2);
  }
}
