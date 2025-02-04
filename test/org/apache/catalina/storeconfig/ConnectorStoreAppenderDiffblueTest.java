package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.beans.IntrospectionException;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.junit.Test;

public class ConnectorStoreAppenderDiffblueTest {
  /**
   * Test {@link ConnectorStoreAppender#getPropertyKeys(Connector)}.
   * <ul>
   *   <li>Given {@link StandardThreadExecutor} (default constructor).</li>
   *   <li>Then return size is sixty-six.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#getPropertyKeys(Connector)}
   */
  @Test
  public void testGetPropertyKeys_givenStandardThreadExecutor_thenReturnSizeIsSixtySix() throws IntrospectionException {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    AjpNio2Protocol protocolHandler = new AjpNio2Protocol();
    protocolHandler.setExecutor(new StandardThreadExecutor());

    // Act
    List<String> actualPropertyKeys = connectorStoreAppender.getPropertyKeys(new Connector(protocolHandler));

    // Assert
    assertEquals(66, actualPropertyKeys.size());
    assertEquals("socket.soLingerTime", actualPropertyKeys.get(60));
    assertEquals("socket.soReuseAddress", actualPropertyKeys.get(61));
    assertEquals("socket.soTimeout", actualPropertyKeys.get(62));
    assertEquals("socket.timeoutInterval", actualPropertyKeys.get(63));
    assertEquals("socket.txBufSize", actualPropertyKeys.get(Double.SIZE));
    assertEquals("socket.unlockTimeout", actualPropertyKeys.get(65));
  }

  /**
   * Test {@link ConnectorStoreAppender#getPropertyKeys(Connector)}.
   * <ul>
   *   <li>Then return size is sixty-nine.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#getPropertyKeys(Connector)}
   */
  @Test
  public void testGetPropertyKeys_thenReturnSizeIsSixtyNine() throws IntrospectionException {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    // Act
    List<String> actualPropertyKeys = connectorStoreAppender.getPropertyKeys(new Connector(new AjpNio2Protocol()));

    // Assert
    assertEquals(69, actualPropertyKeys.size());
    assertEquals("socket.soLingerTime", actualPropertyKeys.get(63));
    assertEquals("socket.soReuseAddress", actualPropertyKeys.get(Double.SIZE));
    assertEquals("socket.soTimeout", actualPropertyKeys.get(65));
    assertEquals("socket.timeoutInterval", actualPropertyKeys.get(66));
    assertEquals("socket.txBufSize", actualPropertyKeys.get(67));
    assertEquals("socket.unlockTimeout", actualPropertyKeys.get(68));
  }

  /**
   * Test {@link ConnectorStoreAppender#getPropertyKeys(Connector)}.
   * <ul>
   *   <li>When {@link Connector#Connector()}.</li>
   *   <li>Then return size is ninety-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#getPropertyKeys(Connector)}
   */
  @Test
  public void testGetPropertyKeys_whenConnector_thenReturnSizeIsNinetyOne() throws IntrospectionException {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    // Act
    List<String> actualPropertyKeys = connectorStoreAppender.getPropertyKeys(new Connector());

    // Assert
    assertEquals(91, actualPropertyKeys.size());
    assertEquals("socket.soLingerTime", actualPropertyKeys.get(85));
    assertEquals("socket.soReuseAddress", actualPropertyKeys.get(86));
    assertEquals("socket.soTimeout", actualPropertyKeys.get(87));
    assertEquals("socket.timeoutInterval", actualPropertyKeys.get(88));
    assertEquals("socket.txBufSize", actualPropertyKeys.get(89));
    assertEquals("socket.unlockTimeout", actualPropertyKeys.get(90));
  }

  /**
   * Test {@link ConnectorStoreAppender#isPrintValue(Object, Object, String, StoreDescription)}.
   * <ul>
   *   <li>When {@code Bean}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#isPrintValue(Object, Object, String, StoreDescription)}
   */
  @Test
  public void testIsPrintValue_whenBean_thenReturnTrue() {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    // Act and Assert
    assertTrue(connectorStoreAppender.isPrintValue("Bean", "Bean2", "Attr Name", new StoreDescription()));
  }

  /**
   * Test {@link ConnectorStoreAppender#getJkHomeBase(String, File)}.
   * <ul>
   *   <li>When {@code Jk Home}.</li>
   *   <li>Then return Name is {@code Jk Home}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#getJkHomeBase(String, File)}
   */
  @Test
  public void testGetJkHomeBase_whenJkHome_thenReturnNameIsJkHome() {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    // Act
    File actualJkHomeBase = connectorStoreAppender.getJkHomeBase("Jk Home",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals("Jk Home", actualJkHomeBase.getName());
    assertTrue(actualJkHomeBase.isAbsolute());
  }

  /**
   * Test {@link ConnectorStoreAppender#getJkHomeBase(String, File)}.
   * <ul>
   *   <li>When {@code />}.</li>
   *   <li>Then return Name is {@code >}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConnectorStoreAppender#getJkHomeBase(String, File)}
   */
  @Test
  public void testGetJkHomeBase_whenSlashGreaterThanSign_thenReturnNameIsGreaterThanSign() {
    // Arrange
    ConnectorStoreAppender connectorStoreAppender = new ConnectorStoreAppender();

    // Act
    File actualJkHomeBase = connectorStoreAppender.getJkHomeBase("/>",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(">", actualJkHomeBase.getName());
    assertTrue(actualJkHomeBase.isAbsolute());
  }
}
