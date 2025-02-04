package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ChannelSFDiffblueTest {
  /**
   * Test new {@link ChannelSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ChannelSF}
   */
  @Test
  public void testNewChannelSF() {
    // Arrange and Act
    ChannelSF actualChannelSF = new ChannelSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualChannelSF.getInfo());
    assertNull(actualChannelSF.getRegistry());
  }
}
