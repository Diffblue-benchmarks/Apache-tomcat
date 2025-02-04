package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Property;
import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Provider;
import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Providers;
import org.junit.Test;

public class PersistentProviderRegistrationsDiffblueTest {
  /**
   * Test {@link PersistentProviderRegistrations#loadProviders(File)}.
   * <p>
   * Method under test: {@link PersistentProviderRegistrations#loadProviders(File)}
   */
  @Test
  public void testLoadProviders() {
    // Arrange, Act and Assert
    assertThrows(SecurityException.class, () -> PersistentProviderRegistrations
        .loadProviders(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test Property getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Property}
   *   <li>{@link Property#setName(String)}
   *   <li>{@link Property#setValue(String)}
   *   <li>{@link Property#getName()}
   *   <li>{@link Property#getValue()}
   * </ul>
   */
  @Test
  public void testPropertyGettersAndSetters() {
    // Arrange and Act
    Property actualProperty = new Property();
    actualProperty.setName("Name");
    actualProperty.setValue("42");
    String actualName = actualProperty.getName();

    // Assert
    assertEquals("42", actualProperty.getValue());
    assertEquals("Name", actualName);
  }

  /**
   * Test Provider {@link Provider#addProperty(String, String)} with {@code name}, {@code value}.
   * <p>
   * Method under test: {@link Provider#addProperty(String, String)}
   */
  @Test
  public void testProviderAddPropertyWithNameValue() {
    // Arrange
    Provider provider = new Provider();

    // Act
    provider.addProperty("Name", "42");

    // Assert
    Map<String, String> properties = provider.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("Name"));
  }

  /**
   * Test Provider {@link Provider#addProperty(Property)} with {@code property}.
   * <p>
   * Method under test: {@link Provider#addProperty(Property)}
   */
  @Test
  public void testProviderAddPropertyWithProperty() {
    // Arrange
    Provider provider = new Provider();

    Property property = new Property();
    property.setName("Name");
    property.setValue("42");

    // Act
    provider.addProperty(property);

    // Assert
    Map<String, String> properties = provider.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("Name"));
  }

  /**
   * Test Provider getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Provider}
   *   <li>{@link Provider#setAppContext(String)}
   *   <li>{@link Provider#setClassName(String)}
   *   <li>{@link Provider#setDescription(String)}
   *   <li>{@link Provider#setLayer(String)}
   *   <li>{@link Provider#getAppContext()}
   *   <li>{@link Provider#getClassName()}
   *   <li>{@link Provider#getDescription()}
   *   <li>{@link Provider#getLayer()}
   *   <li>{@link Provider#getProperties()}
   * </ul>
   */
  @Test
  public void testProviderGettersAndSetters() {
    // Arrange and Act
    Provider actualProvider = new Provider();
    actualProvider.setAppContext("App Context");
    actualProvider.setClassName("Class Name");
    actualProvider.setDescription("The characteristics of someone or something");
    actualProvider.setLayer("Layer");
    String actualAppContext = actualProvider.getAppContext();
    String actualClassName = actualProvider.getClassName();
    String actualDescription = actualProvider.getDescription();
    String actualLayer = actualProvider.getLayer();

    // Assert
    assertEquals("App Context", actualAppContext);
    assertEquals("Class Name", actualClassName);
    assertEquals("Layer", actualLayer);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualProvider.getProperties().isEmpty());
  }

  /**
   * Test Provider {@link Provider#setProperty(String, String)}.
   * <p>
   * Method under test: {@link Provider#setProperty(String, String)}
   */
  @Test
  public void testProviderSetProperty() {
    // Arrange
    Provider provider = new Provider();

    // Act
    provider.setProperty("Name", "42");

    // Assert
    Map<String, String> properties = provider.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("Name"));
  }

  /**
   * Test Providers {@link Providers#addProvider(Provider)}.
   * <p>
   * Method under test: {@link Providers#addProvider(Provider)}
   */
  @Test
  public void testProvidersAddProvider() {
    // Arrange
    Providers providers = new Providers();
    Provider provider = new Provider();

    // Act
    providers.addProvider(provider);

    // Assert
    List<Provider> providers2 = providers.getProviders();
    assertEquals(1, providers2.size());
    assertSame(provider, providers2.get(0));
  }

  /**
   * Test Providers getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Providers}
   *   <li>{@link Providers#getProviders()}
   * </ul>
   */
  @Test
  public void testProvidersGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue((new Providers()).getProviders().isEmpty());
  }
}
