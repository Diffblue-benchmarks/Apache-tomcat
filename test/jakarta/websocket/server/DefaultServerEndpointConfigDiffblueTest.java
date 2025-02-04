package jakarta.websocket.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.Decoder;
import jakarta.websocket.Encoder;
import jakarta.websocket.Extension;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class DefaultServerEndpointConfigDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultServerEndpointConfig#DefaultServerEndpointConfig(Class, String, List, List, List, List, Configurator)}
   *   <li>{@link DefaultServerEndpointConfig#getConfigurator()}
   *   <li>{@link DefaultServerEndpointConfig#getDecoders()}
   *   <li>{@link DefaultServerEndpointConfig#getEncoders()}
   *   <li>{@link DefaultServerEndpointConfig#getEndpointClass()}
   *   <li>{@link DefaultServerEndpointConfig#getExtensions()}
   *   <li>{@link DefaultServerEndpointConfig#getPath()}
   *   <li>{@link DefaultServerEndpointConfig#getSubprotocols()}
   *   <li>{@link DefaultServerEndpointConfig#getUserProperties()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> endpointClass = Object.class;
    ArrayList<String> subprotocols = new ArrayList<>();
    ArrayList<Extension> extensions = new ArrayList<>();
    ArrayList<Class<? extends Encoder>> encoders = new ArrayList<>();
    ArrayList<Class<? extends Decoder>> decoders = new ArrayList<>();
    Configurator serverEndpointConfigurator = Configurator.fetchContainerDefaultConfigurator();

    // Act
    DefaultServerEndpointConfig actualDefaultServerEndpointConfig = new DefaultServerEndpointConfig(endpointClass,
        "Path", subprotocols, extensions, encoders, decoders, serverEndpointConfigurator);
    Configurator actualConfigurator = actualDefaultServerEndpointConfig.getConfigurator();
    List<Class<? extends Decoder>> actualDecoders = actualDefaultServerEndpointConfig.getDecoders();
    List<Class<? extends Encoder>> actualEncoders = actualDefaultServerEndpointConfig.getEncoders();
    Class<?> actualEndpointClass = actualDefaultServerEndpointConfig.getEndpointClass();
    List<Extension> actualExtensions = actualDefaultServerEndpointConfig.getExtensions();
    String actualPath = actualDefaultServerEndpointConfig.getPath();
    List<String> actualSubprotocols = actualDefaultServerEndpointConfig.getSubprotocols();
    Map<String, Object> actualUserProperties = actualDefaultServerEndpointConfig.getUserProperties();

    // Assert
    assertEquals("Path", actualPath);
    assertTrue(actualDecoders.isEmpty());
    assertTrue(actualEncoders.isEmpty());
    assertTrue(actualExtensions.isEmpty());
    assertTrue(actualSubprotocols.isEmpty());
    assertTrue(actualUserProperties.isEmpty());
    Class<Object> expectedEndpointClass = Object.class;
    assertEquals(expectedEndpointClass, actualEndpointClass);
    assertSame(decoders, actualDecoders);
    assertSame(encoders, actualEncoders);
    assertSame(extensions, actualExtensions);
    assertSame(subprotocols, actualSubprotocols);
    assertSame(serverEndpointConfigurator, actualConfigurator);
    assertSame(endpointClass, actualEndpointClass);
  }
}
