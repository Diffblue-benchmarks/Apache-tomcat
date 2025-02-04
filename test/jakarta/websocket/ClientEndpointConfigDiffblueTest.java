package jakarta.websocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.ClientEndpointConfig.Builder;
import jakarta.websocket.ClientEndpointConfig.Configurator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.junit.Test;

public class ClientEndpointConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#sslContext(SSLContext)}
   * </ul>
   */
  @Test
  public void testBuilderBuild() throws NoSuchAlgorithmException {
    // Arrange
    Builder createResult = Builder.create();
    Builder configuratorResult = createResult.configurator(new Configurator());
    Builder decodersResult = configuratorResult.decoders(new ArrayList<>());
    Builder encodersResult = decodersResult.encoders(new ArrayList<>());
    Builder extensionsResult = encodersResult.extensions(new ArrayList<>());
    Builder preferredSubprotocolsResult = extensionsResult.preferredSubprotocols(new ArrayList<>());

    // Act and Assert
    assertTrue(
        preferredSubprotocolsResult.sslContext(SSLContext.getDefault()).build() instanceof DefaultClientEndpointConfig);
  }

  /**
   * Test Builder {@link Builder#configurator(Configurator)}.
   * <ul>
   *   <li>Then build return {@link DefaultClientEndpointConfig}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#configurator(Configurator)}
   */
  @Test
  public void testBuilderConfigurator_thenBuildReturnDefaultClientEndpointConfig() {
    // Arrange
    Builder createResult = Builder.create();
    Configurator configurator = new Configurator();

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.configurator(configurator).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    ClientEndpointConfig buildResult2 = createResult.build();
    assertTrue(buildResult2 instanceof DefaultClientEndpointConfig);
    assertSame(configurator, buildResult.getConfigurator());
    assertSame(configurator, buildResult2.getConfigurator());
  }

  /**
   * Test Builder {@link Builder#configurator(Configurator)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return create.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#configurator(Configurator)}
   */
  @Test
  public void testBuilderConfigurator_whenNull_thenReturnCreate() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.configurator(null));
  }

  /**
   * Test Builder {@link Builder#create()}.
   * <p>
   * Method under test: {@link Builder#create()}
   */
  @Test
  public void testBuilderCreate() {
    // Arrange, Act and Assert
    ClientEndpointConfig buildResult = Builder.create().build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    assertNull(buildResult.getSSLContext());
    List<Class<? extends Decoder>> decoders = buildResult.getDecoders();
    assertTrue(decoders.isEmpty());
    assertTrue(buildResult.getUserProperties().isEmpty());
    assertSame(decoders, buildResult.getExtensions());
    assertSame(decoders, buildResult.getPreferredSubprotocols());
    assertSame(decoders, buildResult.getEncoders());
  }

  /**
   * Test Builder {@link Builder#decoders(List)}.
   * <ul>
   *   <li>Then return build Decoders is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#decoders(List)}
   */
  @Test
  public void testBuilderDecoders_thenReturnBuildDecodersIsArrayList() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<Class<? extends Decoder>> decoders = new ArrayList<>();
    Class<Decoder> forNameResult = Decoder.class;
    decoders.add(forNameResult);
    Class<Decoder> forNameResult2 = Decoder.class;
    decoders.add(forNameResult2);

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.decoders(decoders).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    List<Class<? extends Encoder>> encoders = buildResult.getEncoders();
    assertTrue(encoders.isEmpty());
    assertEquals(decoders, buildResult.getDecoders());
    assertSame(encoders, buildResult.getExtensions());
    assertSame(encoders, buildResult.getPreferredSubprotocols());
  }

  /**
   * Test Builder {@link Builder#decoders(List)}.
   * <ul>
   *   <li>Then return build Decoders size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#decoders(List)}
   */
  @Test
  public void testBuilderDecoders_thenReturnBuildDecodersSizeIsOne() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<Class<? extends Decoder>> decoders = new ArrayList<>();
    Class<Decoder> forNameResult = Decoder.class;
    decoders.add(forNameResult);

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.decoders(decoders).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    List<Class<? extends Decoder>> decoders2 = buildResult.getDecoders();
    assertEquals(1, decoders2.size());
    List<Class<? extends Encoder>> encoders = buildResult.getEncoders();
    assertTrue(encoders.isEmpty());
    Class<Decoder> expectedGetResult = Decoder.class;
    assertEquals(expectedGetResult, decoders2.get(0));
    assertSame(encoders, buildResult.getExtensions());
    assertSame(encoders, buildResult.getPreferredSubprotocols());
  }

  /**
   * Test Builder {@link Builder#decoders(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return build Decoders Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#decoders(List)}
   */
  @Test
  public void testBuilderDecoders_whenArrayList_thenReturnBuildDecodersEmpty() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.decoders(new ArrayList<>()).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    List<Class<? extends Decoder>> decoders = buildResult.getDecoders();
    assertTrue(decoders.isEmpty());
    assertSame(decoders, buildResult.getExtensions());
    assertSame(decoders, buildResult.getPreferredSubprotocols());
    assertSame(decoders, buildResult.getEncoders());
  }

  /**
   * Test Builder {@link Builder#decoders(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return build Decoders Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#decoders(List)}
   */
  @Test
  public void testBuilderDecoders_whenNull_thenReturnBuildDecodersEmpty() {
    // Arrange, Act and Assert
    ClientEndpointConfig buildResult = Builder.create().decoders(null).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    List<Class<? extends Decoder>> decoders = buildResult.getDecoders();
    assertTrue(decoders.isEmpty());
    assertSame(decoders, buildResult.getExtensions());
    assertSame(decoders, buildResult.getPreferredSubprotocols());
    assertSame(decoders, buildResult.getEncoders());
  }

  /**
   * Test Builder {@link Builder#encoders(List)}.
   * <ul>
   *   <li>Then return build Encoders is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#encoders(List)}
   */
  @Test
  public void testBuilderEncoders_thenReturnBuildEncodersIsArrayList() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<Class<? extends Encoder>> encoders = new ArrayList<>();
    Class<Encoder> forNameResult = Encoder.class;
    encoders.add(forNameResult);
    Class<Encoder> forNameResult2 = Encoder.class;
    encoders.add(forNameResult2);

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.encoders(encoders).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    assertEquals(encoders, buildResult.getEncoders());
  }

  /**
   * Test Builder {@link Builder#encoders(List)}.
   * <ul>
   *   <li>Then return build Encoders size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#encoders(List)}
   */
  @Test
  public void testBuilderEncoders_thenReturnBuildEncodersSizeIsOne() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<Class<? extends Encoder>> encoders = new ArrayList<>();
    Class<Encoder> forNameResult = Encoder.class;
    encoders.add(forNameResult);

    // Act and Assert
    ClientEndpointConfig buildResult = createResult.encoders(encoders).build();
    assertTrue(buildResult instanceof DefaultClientEndpointConfig);
    List<Class<? extends Encoder>> encoders2 = buildResult.getEncoders();
    assertEquals(1, encoders2.size());
    Class<Encoder> expectedGetResult = Encoder.class;
    assertEquals(expectedGetResult, encoders2.get(0));
  }

  /**
   * Test Builder {@link Builder#encoders(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return create.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#encoders(List)}
   */
  @Test
  public void testBuilderEncoders_whenArrayList_thenReturnCreate() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.encoders(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#encoders(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return create.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#encoders(List)}
   */
  @Test
  public void testBuilderEncoders_whenNull_thenReturnCreate() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.encoders(null));
  }

  /**
   * Test Builder {@link Builder#extensions(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#extensions(List)}
   */
  @Test
  public void testBuilderExtensions_whenArrayList() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.extensions(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#extensions(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#extensions(List)}
   */
  @Test
  public void testBuilderExtensions_whenNull() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.extensions(null));
  }

  /**
   * Test Builder {@link Builder#preferredSubprotocols(List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#preferredSubprotocols(List)}
   */
  @Test
  public void testBuilderPreferredSubprotocols_given42_whenArrayListAdd42() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<String> preferredSubprotocols = new ArrayList<>();
    preferredSubprotocols.add("42");
    preferredSubprotocols.add("foo");

    // Act and Assert
    assertSame(createResult, createResult.preferredSubprotocols(preferredSubprotocols));
  }

  /**
   * Test Builder {@link Builder#preferredSubprotocols(List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#preferredSubprotocols(List)}
   */
  @Test
  public void testBuilderPreferredSubprotocols_givenFoo_whenArrayListAddFoo() {
    // Arrange
    Builder createResult = Builder.create();

    ArrayList<String> preferredSubprotocols = new ArrayList<>();
    preferredSubprotocols.add("foo");

    // Act and Assert
    assertSame(createResult, createResult.preferredSubprotocols(preferredSubprotocols));
  }

  /**
   * Test Builder {@link Builder#preferredSubprotocols(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#preferredSubprotocols(List)}
   */
  @Test
  public void testBuilderPreferredSubprotocols_whenArrayList() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.preferredSubprotocols(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#preferredSubprotocols(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#preferredSubprotocols(List)}
   */
  @Test
  public void testBuilderPreferredSubprotocols_whenNull() {
    // Arrange
    Builder createResult = Builder.create();

    // Act and Assert
    assertSame(createResult, createResult.preferredSubprotocols(null));
  }
}
