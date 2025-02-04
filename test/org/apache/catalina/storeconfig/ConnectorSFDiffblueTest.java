package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ConnectorSFDiffblueTest {
  /**
   * Test new {@link ConnectorSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConnectorSF}
   */
  @Test
  public void testNewConnectorSF() {
    // Arrange and Act
    ConnectorSF actualConnectorSF = new ConnectorSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualConnectorSF.getInfo());
    assertNull(actualConnectorSF.getRegistry());
  }
}
