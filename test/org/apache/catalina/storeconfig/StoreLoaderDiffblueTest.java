package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.nio.file.Paths;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RulesBase;
import org.junit.Test;

public class StoreLoaderDiffblueTest {
  /**
   * Test {@link StoreLoader#createDigester()}.
   * <p>
   * Method under test: {@link StoreLoader#createDigester()}
   */
  @Test
  public void testCreateDigester() {
    // Arrange and Act
    Digester actualCreateDigesterResult = StoreLoader.createDigester();

    // Assert
    assertTrue(actualCreateDigesterResult.getRules() instanceof RulesBase);
    assertEquals("", actualCreateDigesterResult.getCurrentElementName());
    assertEquals("", actualCreateDigesterResult.getMatch());
    assertNull(actualCreateDigesterResult.getRoot());
    assertNull(actualCreateDigesterResult.getPublicId());
    assertNull(actualCreateDigesterResult.getGeneratedCode());
    assertNull(actualCreateDigesterResult.getFakeAttributes());
    assertNull(actualCreateDigesterResult.getEntityResolver());
    assertNull(actualCreateDigesterResult.getErrorHandler());
    assertNull(actualCreateDigesterResult.getDocumentLocator());
    assertEquals(0, actualCreateDigesterResult.getCount());
    assertFalse(actualCreateDigesterResult.getNamespaceAware());
    assertFalse(actualCreateDigesterResult.getRulesValidation());
    assertFalse(actualCreateDigesterResult.getUseContextClassLoader());
    assertFalse(actualCreateDigesterResult.getValidating());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>Given {@link StoreLoader} (default constructor) Registry is {@code null}.</li>
   *   <li>When {@code Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_givenStoreLoaderRegistryIsNull_whenPath() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();
    storeLoader.setRegistry(null);

    // Act
    storeLoader.load("Path");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>Given {@link StoreLoader} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StoreLoader} (default constructor) Registry Version is {@code 6.0.35}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_givenStoreLoader_whenNull_thenStoreLoaderRegistryVersionIs6035() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load(null);

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>Given {@link StoreLoader} (default constructor).</li>
   *   <li>When {@code Path}.</li>
   *   <li>Then {@link StoreLoader} (default constructor) Registry Version is {@code 6.0.35}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_givenStoreLoader_whenPath_thenStoreLoaderRegistryVersionIs6035() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load("Path");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>When {@code ${}.</li>
   *   <li>Then {@link StoreLoader} (default constructor) Registry Version is {@code 6.0.35}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_whenDollarSignLeftCurlyBracket_thenStoreLoaderRegistryVersionIs6035() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load("${");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>When {@code /org/apache/catalina/storeconfig/server-registry.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_whenOrgApacheCatalinaStoreconfigServerRegistryXml() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load("/org/apache/catalina/storeconfig/server-registry.xml");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>When {@code org.apache.catalina.storeconfig.StoreAppender}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_whenOrgApacheCatalinaStoreconfigStoreAppender() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load("org.apache.catalina.storeconfig.StoreAppender");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test {@link StoreLoader#load(String)}.
   * <ul>
   *   <li>When {@code server-registry.xmlfile:/}.</li>
   *   <li>Then {@link StoreLoader} (default constructor) Registry Version is {@code 6.0.35}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreLoader#load(String)}
   */
  @Test
  public void testLoad_whenServerRegistryXmlfile_thenStoreLoaderRegistryVersionIs6035() throws Exception {
    // Arrange
    StoreLoader storeLoader = new StoreLoader();

    // Act
    storeLoader.load("server-registry.xmlfile:/");

    // Assert
    StoreRegistry registry = storeLoader.getRegistry();
    assertEquals("6.0.35", registry.getVersion());
    assertEquals("Tomcat", registry.getName());
    assertEquals("UTF-8", registry.getEncoding());
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("user.dir"), "output", "classes", "org", "apache", "catalina", "storeconfig",
                "server-registry.xml")
            .toString());
    assertEquals(expectedToStringResult, storeLoader.getRegistryResource().toString());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StoreLoader}
   *   <li>{@link StoreLoader#setRegistry(StoreRegistry)}
   *   <li>{@link StoreLoader#getRegistry()}
   *   <li>{@link StoreLoader#getRegistryResource()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    StoreLoader actualStoreLoader = new StoreLoader();
    StoreRegistry registry = new StoreRegistry();
    actualStoreLoader.setRegistry(registry);
    StoreRegistry actualRegistry = actualStoreLoader.getRegistry();

    // Assert
    assertNull(actualStoreLoader.getRegistryResource());
    assertSame(registry, actualRegistry);
  }
}
