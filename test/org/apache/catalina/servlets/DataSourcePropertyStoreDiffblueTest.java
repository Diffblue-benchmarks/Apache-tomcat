package org.apache.catalina.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.catalina.servlets.WebdavServlet.PropertyUpdateType;
import org.apache.catalina.servlets.WebdavServlet.ProppatchOperation;
import org.apache.catalina.util.XMLWriter;
import org.junit.Test;
import org.w3c.dom.Node;

public class DataSourcePropertyStoreDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DataSourcePropertyStore#setDataSourceName(String)}
   *   <li>{@link DataSourcePropertyStore#setTableName(String)}
   *   <li>{@link DataSourcePropertyStore#destroy()}
   *   <li>{@link DataSourcePropertyStore#periodicEvent()}
   *   <li>{@link DataSourcePropertyStore#getDataSourceName()}
   *   <li>{@link DataSourcePropertyStore#getTableName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DataSourcePropertyStore dataSourcePropertyStore = new DataSourcePropertyStore();

    // Act
    dataSourcePropertyStore.setDataSourceName("Data Source Name");
    dataSourcePropertyStore.setTableName("Table Name");
    dataSourcePropertyStore.destroy();
    dataSourcePropertyStore.periodicEvent();
    String actualDataSourceName = dataSourcePropertyStore.getDataSourceName();

    // Assert
    assertEquals("Data Source Name", actualDataSourceName);
    assertEquals("Table Name", dataSourcePropertyStore.getTableName());
  }

  /**
   * Test {@link DataSourcePropertyStore#propfind(String, Node, boolean, XMLWriter)}.
   * <p>
   * Method under test: {@link DataSourcePropertyStore#propfind(String, Node, boolean, XMLWriter)}
   */
  @Test
  public void testPropfind() {
    // Arrange
    DataSourcePropertyStore dataSourcePropertyStore = new DataSourcePropertyStore();
    IIOMetadataNode property = new IIOMetadataNode("foo");

    // Act and Assert
    assertFalse(dataSourcePropertyStore.propfind("Resource", property, true, new XMLWriter()));
  }

  /**
   * Test {@link DataSourcePropertyStore#proppatch(String, ArrayList)}.
   * <ul>
   *   <li>Given {@link IIOMetadataNode#IIOMetadataNode(String)} with {@code foo}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourcePropertyStore#proppatch(String, ArrayList)}
   */
  @Test
  public void testProppatch_givenIIOMetadataNodeWithFoo_thenArrayListSizeIsOne() {
    // Arrange
    DataSourcePropertyStore dataSourcePropertyStore = new DataSourcePropertyStore();

    ArrayList<ProppatchOperation> operations = new ArrayList<>();
    operations.add(new ProppatchOperation(PropertyUpdateType.SET, new IIOMetadataNode("foo")));

    // Act
    dataSourcePropertyStore.proppatch("Resource", operations);

    // Assert
    assertEquals(1, operations.size());
    assertEquals(500, operations.get(0).getStatusCode());
  }

  /**
   * Test {@link DataSourcePropertyStore#proppatch(String, ArrayList)}.
   * <ul>
   *   <li>Given {@link IIOMetadataNode#IIOMetadataNode(String)} with {@code foo}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourcePropertyStore#proppatch(String, ArrayList)}
   */
  @Test
  public void testProppatch_givenIIOMetadataNodeWithFoo_thenArrayListSizeIsTwo() {
    // Arrange
    DataSourcePropertyStore dataSourcePropertyStore = new DataSourcePropertyStore();

    ArrayList<ProppatchOperation> operations = new ArrayList<>();
    operations.add(new ProppatchOperation(PropertyUpdateType.SET, new IIOMetadataNode("foo")));
    operations.add(new ProppatchOperation(PropertyUpdateType.SET, new IIOMetadataNode("foo")));

    // Act
    dataSourcePropertyStore.proppatch("Resource", operations);

    // Assert
    assertEquals(2, operations.size());
    assertEquals(500, operations.get(0).getStatusCode());
    assertEquals(500, operations.get(1).getStatusCode());
  }

  /**
   * Test new {@link DataSourcePropertyStore} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DataSourcePropertyStore}
   */
  @Test
  public void testNewDataSourcePropertyStore() {
    // Arrange and Act
    DataSourcePropertyStore actualDataSourcePropertyStore = new DataSourcePropertyStore();

    // Assert
    assertEquals("WebdavPropertyStore", actualDataSourcePropertyStore.getDataSourceName());
    assertEquals("properties", actualDataSourcePropertyStore.getTableName());
    assertNull(actualDataSourcePropertyStore.dataSource);
  }
}
