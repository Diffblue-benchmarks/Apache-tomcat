package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.Context;
import org.apache.catalina.Server;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.junit.Test;

public class StoreConfigDiffblueTest {
  /**
   * Test {@link StoreConfig#store(Context)} with {@code aContext}.
   * <ul>
   *   <li>Given {@link StoreConfig} (default constructor) Registry is {@link StoreRegistry} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfig#store(Context)}
   */
  @Test
  public void testStoreWithAContext_givenStoreConfigRegistryIsStoreRegistry_thenReturnTrue() {
    // Arrange
    StoreConfig storeConfig = new StoreConfig();
    storeConfig.setRegistry(new StoreRegistry());

    // Act and Assert
    assertTrue(storeConfig.store(new StandardContext()));
  }

  /**
   * Test {@link StoreConfig#store(Context)} with {@code aContext}.
   * <ul>
   *   <li>Given {@link StoreConfig} (default constructor).</li>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreConfig#store(Context)}
   */
  @Test
  public void testStoreWithAContext_givenStoreConfig_whenStandardContext_thenReturnFalse() {
    // Arrange
    StoreConfig storeConfig = new StoreConfig();

    // Act and Assert
    assertFalse(storeConfig.store(new StandardContext()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StoreConfig}
   *   <li>{@link StoreConfig#setRegistry(StoreRegistry)}
   *   <li>{@link StoreConfig#setServer(Server)}
   *   <li>{@link StoreConfig#setServerFilename(String)}
   *   <li>{@link StoreConfig#getRegistry()}
   *   <li>{@link StoreConfig#getServer()}
   *   <li>{@link StoreConfig#getServerFilename()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    StoreConfig actualStoreConfig = new StoreConfig();
    StoreRegistry aRegistry = new StoreRegistry();
    actualStoreConfig.setRegistry(aRegistry);
    StandardServer aServer = new StandardServer();
    actualStoreConfig.setServer(aServer);
    actualStoreConfig.setServerFilename("String");
    StoreRegistry actualRegistry = actualStoreConfig.getRegistry();
    Server actualServer = actualStoreConfig.getServer();

    // Assert
    assertEquals("String", actualStoreConfig.getServerFilename());
    assertSame(aServer, actualServer);
    assertSame(aRegistry, actualRegistry);
  }
}
