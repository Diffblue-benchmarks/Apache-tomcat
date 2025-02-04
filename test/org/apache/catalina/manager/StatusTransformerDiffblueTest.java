package org.apache.catalina.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.Test;

public class StatusTransformerDiffblueTest {
  /**
   * Test {@link StatusTransformer#setContentType(HttpServletResponse, int)}.
   * <p>
   * Method under test: {@link StatusTransformer#setContentType(HttpServletResponse, int)}
   */
  @Test
  public void testSetContentType() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    StatusTransformer.setContentType(response, 1);

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("text/xml;charset=utf-8", response2.getContentType());
    assertEquals("text/xml;charset=utf-8", response3.getContentType());
    assertEquals("text/xml;charset=utf-8", response.getContentType());
    assertEquals("text/xml;charset=utf-8", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link StatusTransformer#setContentType(HttpServletResponse, int)}.
   * <p>
   * Method under test: {@link StatusTransformer#setContentType(HttpServletResponse, int)}
   */
  @Test
  public void testSetContentType2() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    StatusTransformer.setContentType(response, 0);

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("text/html;charset=utf-8", response2.getContentType());
    assertEquals("text/html;charset=utf-8", response3.getContentType());
    assertEquals("text/html;charset=utf-8", response.getContentType());
    assertEquals("text/html;charset=utf-8", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link StatusTransformer#setContentType(HttpServletResponse, int)}.
   * <p>
   * Method under test: {@link StatusTransformer#setContentType(HttpServletResponse, int)}
   */
  @Test
  public void testSetContentType3() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    StatusTransformer.setContentType(response, 2);

    // Assert
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    HttpServletResponse response3 = ((Response) response2).getResponse();
    assertTrue(response3 instanceof ResponseFacade);
    assertEquals("application/json;charset=utf-8", response2.getContentType());
    assertEquals("application/json;charset=utf-8", response3.getContentType());
    assertEquals("application/json;charset=utf-8", response.getContentType());
    assertEquals("application/json;charset=utf-8", ((Response) response2).getCoyoteResponse().getContentType());
  }

  /**
   * Test {@link StatusTransformer#setContentType(HttpServletResponse, int)}.
   * <ul>
   *   <li>When three.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#setContentType(HttpServletResponse, int)}
   */
  @Test
  public void testSetContentType_whenThree() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    StatusTransformer.setContentType(response, 3);

    // Assert that nothing has changed
    ServletResponse response2 = response.getResponse();
    assertTrue(response2 instanceof Response);
    assertTrue(((Response) response2).getResponse() instanceof ResponseFacade);
  }

  /**
   * Test {@link StatusTransformer#getConnectorName(String)}.
   * <ul>
   *   <li>When {@code Name-auto-}.</li>
   *   <li>Then return {@code Name-auto"}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#getConnectorName(String)}
   */
  @Test
  public void testGetConnectorName_whenNameAuto_thenReturnNameAuto() {
    // Arrange, Act and Assert
    assertEquals("Name-auto\"", StatusTransformer.getConnectorName("Name-auto-"));
  }

  /**
   * Test {@link StatusTransformer#getConnectorName(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#getConnectorName(String)}
   */
  @Test
  public void testGetConnectorName_whenName_thenReturnName() {
    // Arrange, Act and Assert
    assertEquals("Name", StatusTransformer.getConnectorName("Name"));
  }

  /**
   * Test {@link StatusTransformer#formatSize(Object, boolean)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code 8796093022207.99 MiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSize(Object, boolean)}
   */
  @Test
  public void testFormatSize_whenMax_value_thenReturn879609302220799MiB() {
    // Arrange, Act and Assert
    assertEquals("8796093022207.99 MiB", StatusTransformer.formatSize(Long.MAX_VALUE, true));
  }

  /**
   * Test {@link StatusTransformer#formatSize(Object, boolean)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code -0.00 MiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSize(Object, boolean)}
   */
  @Test
  public void testFormatSize_whenMinusOne_thenReturn000MiB() {
    // Arrange, Act and Assert
    assertEquals("-0.00 MiB", StatusTransformer.formatSize(-1, true));
  }

  /**
   * Test {@link StatusTransformer#formatSize(Object, boolean)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code -0.00 MiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSize(Object, boolean)}
   */
  @Test
  public void testFormatSize_whenObj_thenReturn000MiB() {
    // Arrange, Act and Assert
    assertEquals("-0.00 MiB", StatusTransformer.formatSize("Obj", true));
  }

  /**
   * Test {@link StatusTransformer#formatSize(Object, boolean)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 0.00 MiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSize(Object, boolean)}
   */
  @Test
  public void testFormatSize_whenOne_thenReturn000MiB() {
    // Arrange, Act and Assert
    assertEquals("0.00 MiB", StatusTransformer.formatSize(1L, true));
  }

  /**
   * Test {@link StatusTransformer#formatSize(Object, boolean)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code 0 KiB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSize(Object, boolean)}
   */
  @Test
  public void testFormatSize_whenZero_thenReturn0KiB() {
    // Arrange, Act and Assert
    assertEquals("0 KiB", StatusTransformer.formatSize(0L, false));
  }

  /**
   * Test {@link StatusTransformer#formatTime(Object, boolean)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code -0.001 s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatTime(Object, boolean)}
   */
  @Test
  public void testFormatTime_whenObj_thenReturn0001S() {
    // Arrange, Act and Assert
    assertEquals("-0.001 s", StatusTransformer.formatTime("Obj", true));
  }

  /**
   * Test {@link StatusTransformer#formatTime(Object, boolean)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1 ms}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatTime(Object, boolean)}
   */
  @Test
  public void testFormatTime_whenOne_thenReturn1Ms() {
    // Arrange, Act and Assert
    assertEquals("1 ms", StatusTransformer.formatTime(1L, false));
  }

  /**
   * Test {@link StatusTransformer#formatTime(Object, boolean)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1 ms}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatTime(Object, boolean)}
   */
  @Test
  public void testFormatTime_whenOne_thenReturn1Ms2() {
    // Arrange, Act and Assert
    assertEquals("1 ms", StatusTransformer.formatTime(1, false));
  }

  /**
   * Test {@link StatusTransformer#formatSeconds(Object)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code -1 s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSeconds(Object)}
   */
  @Test
  public void testFormatSeconds_whenObj_thenReturn1S() {
    // Arrange, Act and Assert
    assertEquals("-1 s", StatusTransformer.formatSeconds("Obj"));
  }

  /**
   * Test {@link StatusTransformer#formatSeconds(Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1 s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSeconds(Object)}
   */
  @Test
  public void testFormatSeconds_whenOne_thenReturn1S() {
    // Arrange, Act and Assert
    assertEquals("1 s", StatusTransformer.formatSeconds(1L));
  }

  /**
   * Test {@link StatusTransformer#formatSeconds(Object)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1 s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StatusTransformer#formatSeconds(Object)}
   */
  @Test
  public void testFormatSeconds_whenOne_thenReturn1S2() {
    // Arrange, Act and Assert
    assertEquals("1 s", StatusTransformer.formatSeconds(1));
  }
}
