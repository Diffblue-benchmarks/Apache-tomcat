package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.catalina.filters.RemoteIpFilter.XForwardedRequest;
import org.apache.coyote.Request;
import org.apache.tomcat.unittest.TesterRequest;
import org.junit.Test;

public class RequestUtilDiffblueTest {
  /**
   * Test {@link RequestUtil#getRequestURL(HttpServletRequest)}.
   * <ul>
   *   <li>Given {@code ://}.</li>
   *   <li>Then return toString is {@code ://://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestUtil#getRequestURL(HttpServletRequest)}
   */
  @Test
  public void testGetRequestURL_givenColonSlashSlash_thenReturnToStringIsNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest request = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    request.setServerPort(0);
    request.setScheme("://");

    // Act and Assert
    assertEquals("://://nullnull", RequestUtil.getRequestURL(request).toString());
  }

  /**
   * Test {@link RequestUtil#getRequestURL(HttpServletRequest)}.
   * <ul>
   *   <li>Given four hundred forty-three.</li>
   *   <li>Then return toString is {@code https://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestUtil#getRequestURL(HttpServletRequest)}
   */
  @Test
  public void testGetRequestURL_givenFourHundredFortyThree_thenReturnToStringIsHttpsNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest request = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    request.setServerPort(443);
    request.setScheme("https");

    // Act and Assert
    assertEquals("https://nullnull", RequestUtil.getRequestURL(request).toString());
  }

  /**
   * Test {@link RequestUtil#getRequestURL(HttpServletRequest)}.
   * <ul>
   *   <li>Given minus one.</li>
   *   <li>Then return toString is {@code http://nullnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestUtil#getRequestURL(HttpServletRequest)}
   */
  @Test
  public void testGetRequestURL_givenMinusOne_thenReturnToStringIsHttpNullnull() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest request = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    request.setServerPort(-1);
    request.setScheme("http");

    // Act and Assert
    assertEquals("http://nullnull", RequestUtil.getRequestURL(request).toString());
  }

  /**
   * Test {@link RequestUtil#getRequestURL(HttpServletRequest)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>Then return toString is {@code https://null:0null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestUtil#getRequestURL(HttpServletRequest)}
   */
  @Test
  public void testGetRequestURL_givenZero_thenReturnToStringIsHttpsNull0null() {
    // Arrange
    Connector connector = new Connector();

    XForwardedRequest request = new XForwardedRequest(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))));
    request.setServerPort(0);
    request.setScheme("https");

    // Act and Assert
    assertEquals("https://null:0null", RequestUtil.getRequestURL(request).toString());
  }

  /**
   * Test {@link RequestUtil#getRequestURL(HttpServletRequest)}.
   * <ul>
   *   <li>Then return toString is {@code http://localhost:8080/level1/level2/foo.html}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestUtil#getRequestURL(HttpServletRequest)}
   */
  @Test
  public void testGetRequestURL_thenReturnToStringIsHttpLocalhost8080Level1Level2FooHtml() {
    // Arrange, Act and Assert
    assertEquals("http://localhost:8080/level1/level2/foo.html",
        RequestUtil.getRequestURL(new HttpServletRequestWrapper(new RequestFacade(new TesterRequest()))).toString());
  }
}
