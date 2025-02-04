package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class SSIServletExternalResolverDiffblueTest {
  /**
   * Test {@link SSIServletExternalResolver#SSIServletExternalResolver(ServletContext, HttpServletRequest, HttpServletResponse, boolean, int, String)}.
   * <ul>
   *   <li>Then {@link SSIServletExternalResolver#req} return {@link HttpServletRequestWrapper}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#SSIServletExternalResolver(ServletContext, HttpServletRequest, HttpServletResponse, boolean, int, String)}
   */
  @Test
  public void testNewSSIServletExternalResolver_thenReqReturnHttpServletRequestWrapper() {
    // Arrange
    Connector connector = new Connector();
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    // Act
    SSIServletExternalResolver actualSsiServletExternalResolver = new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8");

    // Assert
    assertTrue(actualSsiServletExternalResolver.req instanceof HttpServletRequestWrapper);
    assertTrue(actualSsiServletExternalResolver.res instanceof HttpServletResponseWrapper);
    assertEquals("UTF-8", actualSsiServletExternalResolver.inputEncoding);
    assertNull(actualSsiServletExternalResolver.context);
    assertEquals(1, actualSsiServletExternalResolver.debug);
    assertEquals(31, actualSsiServletExternalResolver.VARIABLE_NAMES.length);
    assertTrue(actualSsiServletExternalResolver.isVirtualWebappRelative);
  }

  /**
   * Test {@link SSIServletExternalResolver#getReqAttributeIgnoreCase(String)}.
   * <ul>
   *   <li>When {@code java.}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#getReqAttributeIgnoreCase(String)}
   */
  @Test
  public void testGetReqAttributeIgnoreCase_whenJava_thenReturnNull() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertNull((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .getReqAttributeIgnoreCase("java."));
  }

  /**
   * Test {@link SSIServletExternalResolver#getReqAttributeIgnoreCase(String)}.
   * <ul>
   *   <li>When {@code javax.}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#getReqAttributeIgnoreCase(String)}
   */
  @Test
  public void testGetReqAttributeIgnoreCase_whenJavax_thenReturnNull() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertNull((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .getReqAttributeIgnoreCase("javax."));
  }

  /**
   * Test {@link SSIServletExternalResolver#isNameReserved(String)}.
   * <ul>
   *   <li>When {@code java.}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#isNameReserved(String)}
   */
  @Test
  public void testIsNameReserved_whenJava_thenReturnTrue() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertTrue((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .isNameReserved("java."));
  }

  /**
   * Test {@link SSIServletExternalResolver#isNameReserved(String)}.
   * <ul>
   *   <li>When {@code javax.}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#isNameReserved(String)}
   */
  @Test
  public void testIsNameReserved_whenJavax_thenReturnTrue() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertTrue((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .isNameReserved("javax."));
  }

  /**
   * Test {@link SSIServletExternalResolver#isNameReserved(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#isNameReserved(String)}
   */
  @Test
  public void testIsNameReserved_whenName_thenReturnFalse() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertFalse((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .isNameReserved("Name"));
  }

  /**
   * Test {@link SSIServletExternalResolver#getCGIVariable(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#getCGIVariable(String)}
   */
  @Test
  public void testGetCGIVariable_thenReturnNull() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertNull((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .getCGIVariable("Name"));
  }

  /**
   * Test {@link SSIServletExternalResolver#nullToEmptyString(String)}.
   * <ul>
   *   <li>Then return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#nullToEmptyString(String)}
   */
  @Test
  public void testNullToEmptyString_thenReturnString() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertEquals("String",
        (new SSIServletExternalResolver(null, req,
            new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1,
            "UTF-8")).nullToEmptyString("String"));
  }

  /**
   * Test {@link SSIServletExternalResolver#getPathWithoutFileName(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#getPathWithoutFileName(String)}
   */
  @Test
  public void testGetPathWithoutFileName_thenReturnNull() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertNull((new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8"))
        .getPathWithoutFileName("Servlet Path"));
  }

  /**
   * Test {@link SSIServletExternalResolver#getPathWithoutContext(String, String)}.
   * <ul>
   *   <li>Then return {@code Servlet Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIServletExternalResolver#getPathWithoutContext(String, String)}
   */
  @Test
  public void testGetPathWithoutContext_thenReturnServletPath() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act and Assert
    assertEquals("Servlet Path",
        (new SSIServletExternalResolver(null, req,
            new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1,
            "UTF-8")).getPathWithoutContext("Context Path", "Servlet Path"));
  }
}
