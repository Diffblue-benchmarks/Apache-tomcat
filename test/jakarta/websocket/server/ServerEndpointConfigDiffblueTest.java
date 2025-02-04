package jakarta.websocket.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.websocket.Extension;
import jakarta.websocket.server.ServerEndpointConfig.Builder;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.websocket.server.DefaultServerEndpointConfigurator;
import org.junit.Test;

public class ServerEndpointConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#create(Class, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#create(Class, String)}
   */
  @Test
  public void testBuilderCreate_whenEmptyString() {
    // Arrange
    Class<Object> endpointClass = Object.class;

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Builder.create(endpointClass, ""));
  }

  /**
   * Test Builder {@link Builder#create(Class, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#create(Class, String)}
   */
  @Test
  public void testBuilderCreate_whenNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Builder.create(null, null));
  }

  /**
   * Test Builder {@link Builder#create(Class, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#create(Class, String)}
   */
  @Test
  public void testBuilderCreate_whenNull2() {
    // Arrange
    Class<Object> endpointClass = Object.class;

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Builder.create(endpointClass, null));
  }

  /**
   * Test Builder {@link Builder#create(Class, String)}.
   * <ul>
   *   <li>When {@code Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#create(Class, String)}
   */
  @Test
  public void testBuilderCreate_whenPath() {
    // Arrange
    Class<Object> endpointClass = Object.class;

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Builder.create(endpointClass, "Path"));
  }

  /**
   * Test Configurator {@link Configurator#checkOrigin(String)}.
   * <ul>
   *   <li>Given {@link Configurator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#checkOrigin(String)}
   */
  @Test
  public void testConfiguratorCheckOrigin_givenConfigurator() {
    // Arrange, Act and Assert
    assertTrue((new Configurator()).checkOrigin("42"));
  }

  /**
   * Test Configurator {@link Configurator#checkOrigin(String)}.
   * <ul>
   *   <li>Given fetchContainerDefaultConfigurator.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#checkOrigin(String)}
   */
  @Test
  public void testConfiguratorCheckOrigin_givenFetchContainerDefaultConfigurator() {
    // Arrange, Act and Assert
    assertTrue(Configurator.fetchContainerDefaultConfigurator().checkOrigin("42"));
  }

  /**
   * Test Configurator {@link Configurator#fetchContainerDefaultConfigurator()}.
   * <p>
   * Method under test: {@link Configurator#fetchContainerDefaultConfigurator()}
   */
  @Test
  public void testConfiguratorFetchContainerDefaultConfigurator() {
    // Arrange and Act
    Configurator actualFetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();

    // Assert
    assertTrue(actualFetchContainerDefaultConfiguratorResult instanceof DefaultServerEndpointConfigurator);
    assertSame(actualFetchContainerDefaultConfiguratorResult,
        actualFetchContainerDefaultConfiguratorResult.getContainerDefaultConfigurator());
  }

  /**
   * Test Configurator {@link Configurator#getContainerDefaultConfigurator()}.
   * <p>
   * Method under test: {@link Configurator#getContainerDefaultConfigurator()}
   */
  @Test
  public void testConfiguratorGetContainerDefaultConfigurator() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();

    // Act
    Configurator actualContainerDefaultConfigurator = fetchContainerDefaultConfiguratorResult
        .getContainerDefaultConfigurator();

    // Assert
    assertTrue(actualContainerDefaultConfigurator instanceof DefaultServerEndpointConfigurator);
    assertTrue(actualContainerDefaultConfigurator.checkOrigin("42"));
    assertSame(fetchContainerDefaultConfiguratorResult, actualContainerDefaultConfigurator);
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedExtensions(List, List)}.
   * <ul>
   *   <li>Given {@link Configurator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedExtensions(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedExtensions_givenConfigurator() {
    // Arrange
    Configurator configurator = new Configurator();
    ArrayList<Extension> installed = new ArrayList<>();

    // Act and Assert
    assertTrue(configurator.getNegotiatedExtensions(installed, new ArrayList<>()).isEmpty());
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedExtensions(List, List)}.
   * <ul>
   *   <li>Given fetchContainerDefaultConfigurator.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedExtensions(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedExtensions_givenFetchContainerDefaultConfigurator() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();
    ArrayList<Extension> installed = new ArrayList<>();

    // Act and Assert
    assertTrue(fetchContainerDefaultConfiguratorResult.getNegotiatedExtensions(installed, new ArrayList<>()).isEmpty());
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_given42_whenArrayListAdd42() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();

    ArrayList<String> supported = new ArrayList<>();
    supported.add("42");
    supported.add("foo");

    // Act and Assert
    assertEquals("", fetchContainerDefaultConfiguratorResult.getNegotiatedSubprotocol(supported, new ArrayList<>()));
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_given42_whenArrayListAdd422() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();
    ArrayList<String> supported = new ArrayList<>();

    ArrayList<String> requested = new ArrayList<>();
    requested.add("42");
    requested.add("foo");

    // Act and Assert
    assertEquals("", fetchContainerDefaultConfiguratorResult.getNegotiatedSubprotocol(supported, requested));
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given {@link Configurator} (default constructor).</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_givenConfigurator_whenArrayList() {
    // Arrange
    Configurator configurator = new Configurator();
    ArrayList<String> supported = new ArrayList<>();

    // Act and Assert
    assertEquals("", configurator.getNegotiatedSubprotocol(supported, new ArrayList<>()));
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given fetchContainerDefaultConfigurator.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_givenFetchContainerDefaultConfigurator() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();
    ArrayList<String> supported = new ArrayList<>();

    // Act and Assert
    assertEquals("", fetchContainerDefaultConfiguratorResult.getNegotiatedSubprotocol(supported, new ArrayList<>()));
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_givenFoo_whenArrayListAddFoo() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();

    ArrayList<String> supported = new ArrayList<>();
    supported.add("foo");

    // Act and Assert
    assertEquals("", fetchContainerDefaultConfiguratorResult.getNegotiatedSubprotocol(supported, new ArrayList<>()));
  }

  /**
   * Test Configurator {@link Configurator#getNegotiatedSubprotocol(List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Configurator#getNegotiatedSubprotocol(List, List)}
   */
  @Test
  public void testConfiguratorGetNegotiatedSubprotocol_givenFoo_whenArrayListAddFoo2() {
    // Arrange
    Configurator fetchContainerDefaultConfiguratorResult = Configurator.fetchContainerDefaultConfigurator();
    ArrayList<String> supported = new ArrayList<>();

    ArrayList<String> requested = new ArrayList<>();
    requested.add("foo");

    // Act and Assert
    assertEquals("", fetchContainerDefaultConfiguratorResult.getNegotiatedSubprotocol(supported, requested));
  }

  /**
   * Test Configurator new {@link Configurator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Configurator}
   */
  @Test
  public void testConfiguratorNewConfigurator() {
    // Arrange, Act and Assert
    Configurator containerDefaultConfigurator = (new Configurator()).getContainerDefaultConfigurator();
    assertTrue(containerDefaultConfigurator instanceof DefaultServerEndpointConfigurator);
    assertSame(containerDefaultConfigurator.getContainerDefaultConfigurator(),
        containerDefaultConfigurator.getContainerDefaultConfigurator());
  }
}
