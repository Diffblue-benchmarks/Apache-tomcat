package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SSLHostConfigSFDiffblueTest {
  /**
   * Test new {@link SSLHostConfigSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SSLHostConfigSF}
   */
  @Test
  public void testNewSSLHostConfigSF() {
    // Arrange and Act
    SSLHostConfigSF actualSslHostConfigSF = new SSLHostConfigSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualSslHostConfigSF.getInfo());
    assertNull(actualSslHostConfigSF.getRegistry());
  }
}
