package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.tomcat.unittest.TesterResponse;
import org.junit.Test;

public class StandardEngineValveDiffblueTest {
  /**
   * Test new {@link StandardEngineValve} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardEngineValve}
   */
  @Test
  public void testNewStandardEngineValve() {
    // Arrange and Act
    StandardEngineValve actualStandardEngineValve = new StandardEngineValve();

    // Assert
    assertEquals("Catalina", actualStandardEngineValve.getDomain());
    assertEquals("NEW", actualStandardEngineValve.getStateName());
    assertNull(actualStandardEngineValve.getDomainInternal());
    assertNull(actualStandardEngineValve.getObjectName());
    assertNull(actualStandardEngineValve.getContainer());
    assertNull(actualStandardEngineValve.getNext());
    assertEquals(0, actualStandardEngineValve.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualStandardEngineValve.getState());
    assertTrue(actualStandardEngineValve.getThrowOnFailure());
    assertTrue(actualStandardEngineValve.isAsyncSupported());
  }

  /**
   * Test {@link StandardEngineValve#invoke(Request, Response)}.
   * <ul>
   *   <li>Then {@link Response#Response(Response)} with coyoteResponse is {@link org.apache.coyote.Response} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngineValve#invoke(Request, Response)}
   */
  @Test
  public void testInvoke_thenResponseWithCoyoteResponseIsResponseResponseResponseFacade()
      throws ServletException, IOException {
    // Arrange
    StandardEngineValve standardEngineValve = new StandardEngineValve();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    Response response = new Response(new org.apache.coyote.Response());

    // Act
    standardEngineValve.invoke(request, response);

    // Assert
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(404, response2.getStatus());
    assertEquals(404, response.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(404, coyoteResponse.getStatus());
    assertTrue(response.isAppCommitted());
    assertTrue(response.isError());
    assertTrue(response.isErrorReportRequired());
    assertTrue(response.isSuspended());
    assertTrue(coyoteResponse.isError());
    assertTrue(coyoteResponse.isErrorReportRequired());
  }

  /**
   * Test {@link StandardEngineValve#invoke(Request, Response)}.
   * <ul>
   *   <li>When {@link TesterResponse} (default constructor).</li>
   *   <li>Then {@link TesterResponse} (default constructor) Response {@link ResponseFacade}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardEngineValve#invoke(Request, Response)}
   */
  @Test
  public void testInvoke_whenTesterResponse_thenTesterResponseResponseResponseFacade()
      throws ServletException, IOException {
    // Arrange
    StandardEngineValve standardEngineValve = new StandardEngineValve();
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    TesterResponse response = new TesterResponse();

    // Act
    standardEngineValve.invoke(request, response);

    // Assert that nothing has changed
    HttpServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof ResponseFacade);
    assertEquals(200, response2.getStatus());
    assertEquals(200, response.getStatus());
    org.apache.coyote.Response coyoteResponse = response.getCoyoteResponse();
    assertEquals(200, coyoteResponse.getStatus());
    assertFalse(response.isAppCommitted());
    assertFalse(response.isError());
    assertFalse(response.isErrorReportRequired());
    assertFalse(response.isSuspended());
    assertFalse(coyoteResponse.isError());
    assertFalse(coyoteResponse.isErrorReportRequired());
  }
}
