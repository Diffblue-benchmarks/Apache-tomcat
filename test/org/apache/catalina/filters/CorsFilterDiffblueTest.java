package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.catalina.filters.CorsFilter.CORSRequestType;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class CorsFilterDiffblueTest {
  /**
   * Test {@link CorsFilter#handleSimpleCORS(HttpServletRequest, HttpServletResponse, FilterChain)}.
   * <p>
   * Method under test: {@link CorsFilter#handleSimpleCORS(HttpServletRequest, HttpServletResponse, FilterChain)}
   */
  @Test
  public void testHandleSimpleCORS() throws ServletException, IOException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> corsFilter.handleSimpleCORS(request, response, new ApplicationFilterChain()));
  }

  /**
   * Test {@link CorsFilter#handleSimpleCORS(HttpServletRequest, HttpServletResponse, FilterChain)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#handleSimpleCORS(HttpServletRequest, HttpServletResponse, FilterChain)}
   */
  @Test
  public void testHandleSimpleCORS_whenNull_thenThrowIllegalArgumentException() throws ServletException, IOException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> corsFilter.handleSimpleCORS(null, response, new ApplicationFilterChain()));
  }

  /**
   * Test {@link CorsFilter#handlePreflightCORS(HttpServletRequest, HttpServletResponse, FilterChain)}.
   * <p>
   * Method under test: {@link CorsFilter#handlePreflightCORS(HttpServletRequest, HttpServletResponse, FilterChain)}
   */
  @Test
  public void testHandlePreflightCORS() throws ServletException, IOException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> corsFilter.handlePreflightCORS(request, response, new ApplicationFilterChain()));
  }

  /**
   * Test {@link CorsFilter#handlePreflightCORS(HttpServletRequest, HttpServletResponse, FilterChain)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#handlePreflightCORS(HttpServletRequest, HttpServletResponse, FilterChain)}
   */
  @Test
  public void testHandlePreflightCORS_whenNull_thenThrowIllegalArgumentException()
      throws ServletException, IOException {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> corsFilter.handlePreflightCORS(null, response, new ApplicationFilterChain()));
  }

  /**
   * Test {@link CorsFilter#decorateCORSProperties(HttpServletRequest, CORSRequestType)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#decorateCORSProperties(HttpServletRequest, CORSRequestType)}
   */
  @Test
  public void testDecorateCORSProperties_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CorsFilter.decorateCORSProperties(null, null));
  }

  /**
   * Test {@link CorsFilter#decorateCORSProperties(HttpServletRequest, CORSRequestType)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#decorateCORSProperties(HttpServletRequest, CORSRequestType)}
   */
  @Test
  public void testDecorateCORSProperties_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CorsFilter.decorateCORSProperties(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))), null));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code 42Join Separator,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_given42_whenArrayListAdd42_thenReturn42JoinSeparator() {
    // Arrange
    ArrayList<String> elements = new ArrayList<>();
    elements.add("42");
    elements.add(",");

    // Act and Assert
    assertEquals("42Join Separator,", CorsFilter.join(elements, "Join Separator"));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>Given {@code Elements}.</li>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code Elements}.</li>
   *   <li>Then return {@code Elements}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_givenElements_whenLinkedHashSetAddElements_thenReturnElements() {
    // Arrange
    LinkedHashSet<String> elements = new LinkedHashSet<>();
    elements.add("Elements");

    // Act and Assert
    assertEquals("Elements", CorsFilter.join(elements, null));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code null}.</li>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_givenNull_whenLinkedHashSetAddNull_thenReturnDefault_allowed_origins() {
    // Arrange
    LinkedHashSet<String> elements = new LinkedHashSet<>();
    elements.add(null);

    // Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, CorsFilter.join(elements, null));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_whenArrayList_thenReturnDefault_allowed_origins() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, CorsFilter.join(new ArrayList<>(), "Join Separator"));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_whenArrayList_thenReturnDefault_allowed_origins2() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, CorsFilter.join(new ArrayList<>(), null));
  }

  /**
   * Test {@link CorsFilter#join(Collection, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#join(Collection, String)}
   */
  @Test
  public void testJoin_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CorsFilter.join(null, null));
  }

  /**
   * Test {@link CorsFilter#checkRequestType(HttpServletRequest)}.
   * <ul>
   *   <li>Then return {@code NOT_CORS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#checkRequestType(HttpServletRequest)}
   */
  @Test
  public void testCheckRequestType_thenReturnNotCors() {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertEquals(CORSRequestType.NOT_CORS, corsFilter.checkRequestType(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link CorsFilter#checkRequestType(HttpServletRequest)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CorsFilter#checkRequestType(HttpServletRequest)}
   */
  @Test
  public void testCheckRequestType_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new CorsFilter()).checkRequestType(null));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CorsFilter#getAllowedHttpHeaders()}
   *   <li>{@link CorsFilter#getAllowedHttpMethods()}
   *   <li>{@link CorsFilter#getAllowedOrigins()}
   *   <li>{@link CorsFilter#getExposedHeaders()}
   *   <li>{@link CorsFilter#getPreflightMaxAge()}
   *   <li>{@link CorsFilter#isAnyOriginAllowed()}
   *   <li>{@link CorsFilter#isDecorateRequest()}
   *   <li>{@link CorsFilter#isSupportsCredentials()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    CorsFilter corsFilter = new CorsFilter();

    // Act
    Collection<String> actualAllowedHttpHeaders = corsFilter.getAllowedHttpHeaders();
    Collection<String> actualAllowedHttpMethods = corsFilter.getAllowedHttpMethods();
    Collection<String> actualAllowedOrigins = corsFilter.getAllowedOrigins();
    Collection<String> actualExposedHeaders = corsFilter.getExposedHeaders();
    long actualPreflightMaxAge = corsFilter.getPreflightMaxAge();
    boolean actualIsAnyOriginAllowedResult = corsFilter.isAnyOriginAllowed();
    boolean actualIsDecorateRequestResult = corsFilter.isDecorateRequest();

    // Assert
    assertTrue(actualAllowedHttpHeaders instanceof Set);
    assertTrue(actualAllowedHttpMethods instanceof Set);
    assertTrue(actualAllowedOrigins instanceof Set);
    assertTrue(actualExposedHeaders instanceof Set);
    assertEquals(0L, actualPreflightMaxAge);
    assertFalse(actualIsAnyOriginAllowedResult);
    assertFalse(actualIsDecorateRequestResult);
    assertFalse(corsFilter.isSupportsCredentials());
  }

  /**
   * Test new {@link CorsFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CorsFilter}
   */
  @Test
  public void testNewCorsFilter() {
    // Arrange and Act
    CorsFilter actualCorsFilter = new CorsFilter();

    // Assert
    Collection<String> allowedHttpHeaders = actualCorsFilter.getAllowedHttpHeaders();
    assertTrue(allowedHttpHeaders instanceof Set);
    assertNull(actualCorsFilter.getFilterConfig());
    assertEquals(0L, actualCorsFilter.getPreflightMaxAge());
    assertFalse(actualCorsFilter.isAnyOriginAllowed());
    assertFalse(actualCorsFilter.isDecorateRequest());
    assertFalse(actualCorsFilter.isSupportsCredentials());
    assertTrue(allowedHttpHeaders.isEmpty());
    assertEquals(allowedHttpHeaders, actualCorsFilter.getAllowedHttpMethods());
    assertEquals(allowedHttpHeaders, actualCorsFilter.getAllowedOrigins());
    assertEquals(allowedHttpHeaders, actualCorsFilter.getExposedHeaders());
  }
}
