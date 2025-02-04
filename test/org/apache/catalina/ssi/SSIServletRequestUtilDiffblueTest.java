package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.junit.Test;

public class SSIServletRequestUtilDiffblueTest {
  /**
   * Test {@link SSIServletRequestUtil#getRelativePath(HttpServletRequest)}.
   * <p>
   * Method under test: {@link SSIServletRequestUtil#getRelativePath(HttpServletRequest)}
   */
  @Test
  public void testGetRelativePath() {
    // Arrange
    Connector connector = new Connector();

    Request request = new Request(connector, new org.apache.coyote.Request());
    request.setAttribute("jakarta.servlet.include.request_uri", "Value");

    // Act and Assert
    assertEquals("/", SSIServletRequestUtil.getRelativePath(new HttpServletRequestWrapper(new RequestFacade(request))));
  }

  /**
   * Test {@link SSIServletRequestUtil#getRelativePath(HttpServletRequest)}.
   * <ul>
   *   <li>When {@link Request#Request(Connector, Request)} with connector is {@link Connector#Connector()} and coyoteRequest is {@link org.apache.coyote.Request} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletRequestUtil#getRelativePath(HttpServletRequest)}
   */
  @Test
  public void testGetRelativePath_whenRequestWithConnectorIsConnectorAndCoyoteRequestIsRequest() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertEquals("/", SSIServletRequestUtil.getRelativePath(
        new HttpServletRequestWrapper(new RequestFacade(new Request(connector, new org.apache.coyote.Request())))));
  }
}
