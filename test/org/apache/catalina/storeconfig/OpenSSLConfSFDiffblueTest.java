package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class OpenSSLConfSFDiffblueTest {
  /**
   * Test new {@link OpenSSLConfSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link OpenSSLConfSF}
   */
  @Test
  public void testNewOpenSSLConfSF() {
    // Arrange and Act
    OpenSSLConfSF actualOpenSSLConfSF = new OpenSSLConfSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualOpenSSLConfSF.getInfo());
    assertNull(actualOpenSSLConfSF.getRegistry());
  }
}
