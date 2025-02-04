package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.util.Map;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class MessageInfoImplDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MessageInfoImpl#MessageInfoImpl()}
   *   <li>{@link MessageInfoImpl#getMap()}
   *   <li>{@link MessageInfoImpl#getRequestMessage()}
   *   <li>{@link MessageInfoImpl#getResponseMessage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    MessageInfoImpl actualMessageInfoImpl = new MessageInfoImpl();
    Map<String, Object> actualMap = actualMessageInfoImpl.getMap();
    Object actualRequestMessage = actualMessageInfoImpl.getRequestMessage();

    // Assert
    assertNull(actualRequestMessage);
    assertNull(actualMessageInfoImpl.getResponseMessage());
    assertTrue(actualMap.isEmpty());
  }

  /**
   * Test {@link MessageInfoImpl#MessageInfoImpl(HttpServletRequest, HttpServletResponse, boolean)}.
   * <p>
   * Method under test: {@link MessageInfoImpl#MessageInfoImpl(HttpServletRequest, HttpServletResponse, boolean)}
   */
  @Test
  public void testNewMessageInfoImpl() {
    // Arrange
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act
    MessageInfoImpl actualMessageInfoImpl = new MessageInfoImpl(request, response, true);

    // Assert
    Object requestMessage = actualMessageInfoImpl.getRequestMessage();
    assertTrue(requestMessage instanceof HttpServletRequestWrapper);
    Object responseMessage = actualMessageInfoImpl.getResponseMessage();
    assertTrue(responseMessage instanceof HttpServletResponseWrapper);
    Map<String, Object> map = actualMessageInfoImpl.getMap();
    assertEquals(1, map.size());
    assertTrue(map.containsKey(MessageInfoImpl.IS_MANDATORY));
    assertSame(request, requestMessage);
    assertSame(response, responseMessage);
  }

  /**
   * Test {@link MessageInfoImpl#setRequestMessage(Object)}.
   * <p>
   * Method under test: {@link MessageInfoImpl#setRequestMessage(Object)}
   */
  @Test
  public void testSetRequestMessage() {
    // Arrange
    MessageInfoImpl messageInfoImpl = new MessageInfoImpl();
    Connector connector = new Connector();
    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    // Act
    messageInfoImpl.setRequestMessage(httpServletRequestWrapper);

    // Assert
    assertSame(httpServletRequestWrapper, messageInfoImpl.getRequestMessage());
  }

  /**
   * Test {@link MessageInfoImpl#setRequestMessage(Object)}.
   * <ul>
   *   <li>When {@code Request}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageInfoImpl#setRequestMessage(Object)}
   */
  @Test
  public void testSetRequestMessage_whenRequest_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new MessageInfoImpl()).setRequestMessage("Request"));
  }

  /**
   * Test {@link MessageInfoImpl#setResponseMessage(Object)}.
   * <p>
   * Method under test: {@link MessageInfoImpl#setResponseMessage(Object)}
   */
  @Test
  public void testSetResponseMessage() {
    // Arrange
    MessageInfoImpl messageInfoImpl = new MessageInfoImpl();
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act
    messageInfoImpl.setResponseMessage(httpServletResponseWrapper);

    // Assert
    assertSame(httpServletResponseWrapper, messageInfoImpl.getResponseMessage());
  }

  /**
   * Test {@link MessageInfoImpl#setResponseMessage(Object)}.
   * <ul>
   *   <li>When {@code Response}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageInfoImpl#setResponseMessage(Object)}
   */
  @Test
  public void testSetResponseMessage_whenResponse_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new MessageInfoImpl()).setResponseMessage("Response"));
  }
}
