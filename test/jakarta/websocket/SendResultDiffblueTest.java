package jakarta.websocket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.ClientEndpointConfig.Builder;
import jakarta.websocket.ClientEndpointConfig.Configurator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.net.ssl.SSLContext;
import org.apache.tomcat.websocket.AsyncChannelWrapperNonSecure;
import org.apache.tomcat.websocket.EndpointHolder;
import org.apache.tomcat.websocket.WsRemoteEndpointImplClient;
import org.apache.tomcat.websocket.WsSession;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.apache.tomcat.websocket.pojo.PojoEndpointServer;
import org.junit.Test;

public class SendResultDiffblueTest {
  /**
   * Test {@link SendResult#SendResult()}.
   * <p>
   * Method under test: {@link SendResult#SendResult()}
   */
  @Test
  public void testNewSendResult() {
    // Arrange and Act
    SendResult actualSendResult = new SendResult();

    // Assert
    assertNull(actualSendResult.getSession());
    assertNull(actualSendResult.getException());
    assertTrue(actualSendResult.isOK());
  }

  /**
   * Test {@link SendResult#SendResult(Session, Throwable)}.
   * <ul>
   *   <li>Then return not OK.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendResult#SendResult(Session, Throwable)}
   */
  @Test
  public void testNewSendResult_thenReturnNotOk() throws DeploymentException, NoSuchAlgorithmException {
    // Arrange
    EndpointHolder clientEndpointHolder = new EndpointHolder(new PojoEndpointServer(new HashMap<>(), "Pojo"));
    WsRemoteEndpointImplClient wsRemoteEndpoint = new WsRemoteEndpointImplClient(
        new AsyncChannelWrapperNonSecure(null));
    WsWebSocketContainer wsWebSocketContainer = new WsWebSocketContainer();
    ArrayList<Extension> negotiatedExtensions = new ArrayList<>();
    HashMap<String, String> pathParameters = new HashMap<>();
    Builder createResult = Builder.create();
    Builder configuratorResult = createResult.configurator(new Configurator());
    Builder decodersResult = configuratorResult.decoders(new ArrayList<>());
    Builder encodersResult = decodersResult.encoders(new ArrayList<>());
    Builder extensionsResult = encodersResult.extensions(new ArrayList<>());
    Builder preferredSubprotocolsResult = extensionsResult.preferredSubprotocols(new ArrayList<>());
    ClientEndpointConfig clientEndpointConfig = preferredSubprotocolsResult.sslContext(SSLContext.getDefault()).build();
    WsSession session = new WsSession(clientEndpointHolder, wsRemoteEndpoint, wsWebSocketContainer,
        negotiatedExtensions, "Sub Protocol", pathParameters, true, clientEndpointConfig);

    Throwable exception = new Throwable();

    // Act
    SendResult actualSendResult = new SendResult(session, exception);

    // Assert
    assertFalse(actualSendResult.isOK());
    assertSame(exception, actualSendResult.getException());
  }

  /**
   * Test {@link SendResult#SendResult(Session)}.
   * <ul>
   *   <li>Then Session return {@link WsSession}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendResult#SendResult(Session)}
   */
  @Test
  public void testNewSendResult_thenSessionReturnWsSession() throws DeploymentException, NoSuchAlgorithmException {
    // Arrange
    EndpointHolder clientEndpointHolder = new EndpointHolder(new PojoEndpointServer(new HashMap<>(), "Pojo"));
    WsRemoteEndpointImplClient wsRemoteEndpoint = new WsRemoteEndpointImplClient(
        new AsyncChannelWrapperNonSecure(null));
    WsWebSocketContainer wsWebSocketContainer = new WsWebSocketContainer();
    ArrayList<Extension> negotiatedExtensions = new ArrayList<>();
    HashMap<String, String> pathParameters = new HashMap<>();
    Builder createResult = Builder.create();
    Builder configuratorResult = createResult.configurator(new Configurator());
    Builder decodersResult = configuratorResult.decoders(new ArrayList<>());
    Builder encodersResult = decodersResult.encoders(new ArrayList<>());
    Builder extensionsResult = encodersResult.extensions(new ArrayList<>());
    Builder preferredSubprotocolsResult = extensionsResult.preferredSubprotocols(new ArrayList<>());
    ClientEndpointConfig clientEndpointConfig = preferredSubprotocolsResult.sslContext(SSLContext.getDefault()).build();
    WsSession session = new WsSession(clientEndpointHolder, wsRemoteEndpoint, wsWebSocketContainer,
        negotiatedExtensions, "Sub Protocol", pathParameters, true, clientEndpointConfig);

    // Act
    SendResult actualSendResult = new SendResult(session);

    // Assert
    Session session2 = actualSendResult.getSession();
    assertTrue(session2 instanceof WsSession);
    assertNull(actualSendResult.getException());
    assertTrue(actualSendResult.isOK());
    assertSame(session, session2);
  }

  /**
   * Test {@link SendResult#SendResult(Session, Throwable)}.
   * <ul>
   *   <li>Then Session return {@link WsSession}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendResult#SendResult(Session, Throwable)}
   */
  @Test
  public void testNewSendResult_thenSessionReturnWsSession2() throws DeploymentException, NoSuchAlgorithmException {
    // Arrange
    EndpointHolder clientEndpointHolder = new EndpointHolder(new PojoEndpointServer(new HashMap<>(), "Pojo"));
    WsRemoteEndpointImplClient wsRemoteEndpoint = new WsRemoteEndpointImplClient(
        new AsyncChannelWrapperNonSecure(null));
    WsWebSocketContainer wsWebSocketContainer = new WsWebSocketContainer();
    ArrayList<Extension> negotiatedExtensions = new ArrayList<>();
    HashMap<String, String> pathParameters = new HashMap<>();
    Builder createResult = Builder.create();
    Builder configuratorResult = createResult.configurator(new Configurator());
    Builder decodersResult = configuratorResult.decoders(new ArrayList<>());
    Builder encodersResult = decodersResult.encoders(new ArrayList<>());
    Builder extensionsResult = encodersResult.extensions(new ArrayList<>());
    Builder preferredSubprotocolsResult = extensionsResult.preferredSubprotocols(new ArrayList<>());
    ClientEndpointConfig clientEndpointConfig = preferredSubprotocolsResult.sslContext(SSLContext.getDefault()).build();
    WsSession session = new WsSession(clientEndpointHolder, wsRemoteEndpoint, wsWebSocketContainer,
        negotiatedExtensions, "Sub Protocol", pathParameters, true, clientEndpointConfig);

    // Act
    SendResult actualSendResult = new SendResult(session, null);

    // Assert
    Session session2 = actualSendResult.getSession();
    assertTrue(session2 instanceof WsSession);
    assertNull(actualSendResult.getException());
    assertTrue(actualSendResult.isOK());
    assertSame(session, session2);
  }

  /**
   * Test {@link SendResult#SendResult(Throwable)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Session is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendResult#SendResult(Throwable)}
   */
  @Test
  public void testNewSendResult_whenNull_thenReturnSessionIsNull() {
    // Arrange and Act
    SendResult actualSendResult = new SendResult((Throwable) null);

    // Assert
    assertNull(actualSendResult.getSession());
    assertNull(actualSendResult.getException());
    assertTrue(actualSendResult.isOK());
  }

  /**
   * Test {@link SendResult#SendResult(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return not OK.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendResult#SendResult(Throwable)}
   */
  @Test
  public void testNewSendResult_whenThrowable_thenReturnNotOk() {
    // Arrange
    Throwable exception = new Throwable();

    // Act
    SendResult actualSendResult = new SendResult(exception);

    // Assert
    assertFalse(actualSendResult.isOK());
    assertSame(exception, actualSendResult.getException());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SendResult#getException()}
   *   <li>{@link SendResult#getSession()}
   *   <li>{@link SendResult#isOK()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    SendResult sendResult = new SendResult();

    // Act
    Throwable actualException = sendResult.getException();
    Session actualSession = sendResult.getSession();

    // Assert
    assertNull(actualSession);
    assertNull(actualException);
    assertTrue(sendResult.isOK());
  }
}
