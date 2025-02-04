package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Test;

public class StoreFactoryBaseDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StoreFactoryBase#setRegistry(StoreRegistry)}
   *   <li>{@link StoreFactoryBase#setStoreAppender(StoreAppender)}
   *   <li>{@link StoreFactoryBase#storeChildren(PrintWriter, int, Object, StoreDescription)}
   *   <li>{@link StoreFactoryBase#getInfo()}
   *   <li>{@link StoreFactoryBase#getRegistry()}
   *   <li>{@link StoreFactoryBase#getStoreAppender()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws Exception {
    // Arrange
    StoreFactoryBase storeFactoryBase = new StoreFactoryBase();
    StoreRegistry aRegistry = new StoreRegistry();

    // Act
    storeFactoryBase.setRegistry(aRegistry);
    StoreAppender storeAppender = new StoreAppender();
    storeFactoryBase.setStoreAppender(storeAppender);
    PrintWriter aWriter = new PrintWriter(new StringWriter());
    storeFactoryBase.storeChildren(aWriter, 1, "A Element", new StoreDescription());
    String actualInfo = storeFactoryBase.getInfo();
    StoreRegistry actualRegistry = storeFactoryBase.getRegistry();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualInfo);
    assertSame(storeAppender, storeFactoryBase.getStoreAppender());
    assertSame(aRegistry, actualRegistry);
  }

  /**
   * Test new {@link StoreFactoryBase} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StoreFactoryBase}
   */
  @Test
  public void testNewStoreFactoryBase() {
    // Arrange and Act
    StoreFactoryBase actualStoreFactoryBase = new StoreFactoryBase();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStoreFactoryBase.getInfo());
    assertNull(actualStoreFactoryBase.getRegistry());
  }
}
