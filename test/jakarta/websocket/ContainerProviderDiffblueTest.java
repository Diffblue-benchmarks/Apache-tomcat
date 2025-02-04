package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.junit.Test;

public class ContainerProviderDiffblueTest {
  /**
   * Test {@link ContainerProvider#getWebSocketContainer()}.
   * <p>
   * Method under test: {@link ContainerProvider#getWebSocketContainer()}
   */
  @Test
  public void testGetWebSocketContainer() {
    // Arrange and Act
    WebSocketContainer actualWebSocketContainer = ContainerProvider.getWebSocketContainer();

    // Assert
    assertTrue(actualWebSocketContainer instanceof WsWebSocketContainer);
    assertEquals(-1L, actualWebSocketContainer.getDefaultAsyncSendTimeout());
    assertEquals(0L, actualWebSocketContainer.getDefaultMaxSessionIdleTimeout());
    assertEquals(10, ((WsWebSocketContainer) actualWebSocketContainer).getProcessPeriod());
    assertEquals(8192, actualWebSocketContainer.getDefaultMaxBinaryMessageBufferSize());
    assertEquals(8192, actualWebSocketContainer.getDefaultMaxTextMessageBufferSize());
    assertTrue(actualWebSocketContainer.getInstalledExtensions().isEmpty());
  }
}
