package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CredentialHandlerSFDiffblueTest {
  /**
   * Test new {@link CredentialHandlerSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CredentialHandlerSF}
   */
  @Test
  public void testNewCredentialHandlerSF() {
    // Arrange and Act
    CredentialHandlerSF actualCredentialHandlerSF = new CredentialHandlerSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualCredentialHandlerSF.getInfo());
    assertNull(actualCredentialHandlerSF.getRegistry());
  }
}
