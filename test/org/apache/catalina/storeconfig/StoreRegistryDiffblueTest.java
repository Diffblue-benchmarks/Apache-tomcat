package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.coyote.UpgradeProtocol;
import org.junit.Test;

public class StoreRegistryDiffblueTest {
  /**
   * Test {@link StoreRegistry#findDescription(Class)} with {@code aClass}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findDescription(Class)}
   */
  @Test
  public void testFindDescriptionWithAClass_whenJavaLangObject_thenReturnNull() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();
    Class<Object> aClass = Object.class;

    // Act and Assert
    assertNull(storeRegistry.findDescription(aClass));
  }

  /**
   * Test {@link StoreRegistry#findDescription(Class)} with {@code aClass}.
   * <ul>
   *   <li>When {@code UpgradeProtocol}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findDescription(Class)}
   */
  @Test
  public void testFindDescriptionWithAClass_whenOrgApacheCoyoteUpgradeProtocol_thenReturnNull() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();
    Class<UpgradeProtocol> aClass = UpgradeProtocol.class;

    // Act and Assert
    assertNull(storeRegistry.findDescription(aClass));
  }

  /**
   * Test {@link StoreRegistry#findDescription(String)} with {@code id}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findDescription(String)}
   */
  @Test
  public void testFindDescriptionWithId_when42_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StoreRegistry()).findDescription("42"));
  }

  /**
   * Test {@link StoreRegistry#findDescription(String)} with {@code id}.
   * <ul>
   *   <li>When {@code UpgradeProtocol}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findDescription(String)}
   */
  @Test
  public void testFindDescriptionWithId_whenOrgApacheCoyoteUpgradeProtocol_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StoreRegistry()).findDescription("org.apache.coyote.UpgradeProtocol"));
  }

  /**
   * Test {@link StoreRegistry#findStoreFactory(String)} with {@code aClassName}.
   * <ul>
   *   <li>When {@code A Class Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findStoreFactory(String)}
   */
  @Test
  public void testFindStoreFactoryWithAClassName_whenAClassName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StoreRegistry()).findStoreFactory("A Class Name"));
  }

  /**
   * Test {@link StoreRegistry#findStoreFactory(String)} with {@code aClassName}.
   * <ul>
   *   <li>When {@code UpgradeProtocol}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findStoreFactory(String)}
   */
  @Test
  public void testFindStoreFactoryWithAClassName_whenOrgApacheCoyoteUpgradeProtocol() {
    // Arrange, Act and Assert
    assertNull((new StoreRegistry()).findStoreFactory("org.apache.coyote.UpgradeProtocol"));
  }

  /**
   * Test {@link StoreRegistry#findStoreFactory(Class)} with {@code aClass}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findStoreFactory(Class)}
   */
  @Test
  public void testFindStoreFactoryWithAClass_whenJavaLangObject_thenReturnNull() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();
    Class<Object> aClass = Object.class;

    // Act and Assert
    assertNull(storeRegistry.findStoreFactory(aClass));
  }

  /**
   * Test {@link StoreRegistry#findStoreFactory(Class)} with {@code aClass}.
   * <ul>
   *   <li>When {@code UpgradeProtocol}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#findStoreFactory(Class)}
   */
  @Test
  public void testFindStoreFactoryWithAClass_whenOrgApacheCoyoteUpgradeProtocol_thenReturnNull() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();
    Class<UpgradeProtocol> aClass = UpgradeProtocol.class;

    // Act and Assert
    assertNull(storeRegistry.findStoreFactory(aClass));
  }

  /**
   * Test {@link StoreRegistry#unregisterDescription(StoreDescription)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#unregisterDescription(StoreDescription)}
   */
  @Test
  public void testUnregisterDescription_givenEmptyString() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();

    StoreDescription desc = new StoreDescription();
    desc.setTagClass("");

    // Act and Assert
    assertNull(storeRegistry.unregisterDescription(desc));
  }

  /**
   * Test {@link StoreRegistry#unregisterDescription(StoreDescription)}.
   * <ul>
   *   <li>Given {@code Tag Class}.</li>
   *   <li>When {@link StoreDescription} (default constructor) TagClass is {@code Tag Class}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#unregisterDescription(StoreDescription)}
   */
  @Test
  public void testUnregisterDescription_givenTagClass_whenStoreDescriptionTagClassIsTagClass() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();

    StoreDescription desc = new StoreDescription();
    desc.setTagClass("Tag Class");

    // Act and Assert
    assertNull(storeRegistry.unregisterDescription(desc));
  }

  /**
   * Test {@link StoreRegistry#unregisterDescription(StoreDescription)}.
   * <ul>
   *   <li>When {@link StoreDescription} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreRegistry#unregisterDescription(StoreDescription)}
   */
  @Test
  public void testUnregisterDescription_whenStoreDescription_thenReturnNull() {
    // Arrange
    StoreRegistry storeRegistry = new StoreRegistry();

    // Act and Assert
    assertNull(storeRegistry.unregisterDescription(new StoreDescription()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StoreRegistry}
   *   <li>{@link StoreRegistry#setEncoding(String)}
   *   <li>{@link StoreRegistry#setName(String)}
   *   <li>{@link StoreRegistry#setVersion(String)}
   *   <li>{@link StoreRegistry#getEncoding()}
   *   <li>{@link StoreRegistry#getName()}
   *   <li>{@link StoreRegistry#getVersion()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    StoreRegistry actualStoreRegistry = new StoreRegistry();
    actualStoreRegistry.setEncoding("String");
    actualStoreRegistry.setName("Name");
    actualStoreRegistry.setVersion("1.0.2");
    String actualEncoding = actualStoreRegistry.getEncoding();
    String actualName = actualStoreRegistry.getName();

    // Assert
    assertEquals("1.0.2", actualStoreRegistry.getVersion());
    assertEquals("Name", actualName);
    assertEquals("String", actualEncoding);
  }
}
