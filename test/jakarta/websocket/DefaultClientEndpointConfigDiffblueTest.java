package jakarta.websocket;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.ClientEndpointConfig.Configurator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.junit.Test;

public class DefaultClientEndpointConfigDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultClientEndpointConfig#DefaultClientEndpointConfig(List, List, List, List, SSLContext, Configurator)}
   *   <li>{@link DefaultClientEndpointConfig#getConfigurator()}
   *   <li>{@link DefaultClientEndpointConfig#getDecoders()}
   *   <li>{@link DefaultClientEndpointConfig#getEncoders()}
   *   <li>{@link DefaultClientEndpointConfig#getExtensions()}
   *   <li>{@link DefaultClientEndpointConfig#getPreferredSubprotocols()}
   *   <li>{@link DefaultClientEndpointConfig#getSSLContext()}
   *   <li>{@link DefaultClientEndpointConfig#getUserProperties()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws NoSuchAlgorithmException {
    // Arrange
    ArrayList<String> preferredSubprotocols = new ArrayList<>();
    ArrayList<Extension> extensions = new ArrayList<>();
    ArrayList<Class<? extends Encoder>> encoders = new ArrayList<>();
    ArrayList<Class<? extends Decoder>> decoders = new ArrayList<>();
    SSLContext sslContext = SSLContext.getDefault();
    Configurator configurator = new Configurator();

    // Act
    DefaultClientEndpointConfig actualDefaultClientEndpointConfig = new DefaultClientEndpointConfig(
        preferredSubprotocols, extensions, encoders, decoders, sslContext, configurator);
    Configurator actualConfigurator = actualDefaultClientEndpointConfig.getConfigurator();
    List<Class<? extends Decoder>> actualDecoders = actualDefaultClientEndpointConfig.getDecoders();
    List<Class<? extends Encoder>> actualEncoders = actualDefaultClientEndpointConfig.getEncoders();
    List<Extension> actualExtensions = actualDefaultClientEndpointConfig.getExtensions();
    List<String> actualPreferredSubprotocols = actualDefaultClientEndpointConfig.getPreferredSubprotocols();
    SSLContext actualSSLContext = actualDefaultClientEndpointConfig.getSSLContext();
    Map<String, Object> actualUserProperties = actualDefaultClientEndpointConfig.getUserProperties();

    // Assert
    assertTrue(actualDecoders.isEmpty());
    assertTrue(actualEncoders.isEmpty());
    assertTrue(actualExtensions.isEmpty());
    assertTrue(actualPreferredSubprotocols.isEmpty());
    assertTrue(actualUserProperties.isEmpty());
    assertSame(configurator, actualConfigurator);
    assertSame(decoders, actualDecoders);
    assertSame(encoders, actualEncoders);
    assertSame(extensions, actualExtensions);
    assertSame(preferredSubprotocols, actualPreferredSubprotocols);
    assertSame(sslContext, actualSSLContext);
  }
}
