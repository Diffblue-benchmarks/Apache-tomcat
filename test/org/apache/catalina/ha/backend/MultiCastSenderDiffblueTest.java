package org.apache.catalina.ha.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class MultiCastSenderDiffblueTest {
  /**
   * Test {@link MultiCastSender#send(String)}.
   * <ul>
   *   <li>Given {@link HeartbeatListener} (default constructor) Group is {@code multiCastSender.multiCastFailed}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiCastSender#send(String)}
   */
  @Test
  public void testSend_givenHeartbeatListenerGroupIsMultiCastSenderMultiCastFailed() throws Exception {
    // Arrange
    HeartbeatListener config = new HeartbeatListener();
    config.setGroup("multiCastSender.multiCastFailed");

    MultiCastSender multiCastSender = new MultiCastSender();
    multiCastSender.init(config);

    // Act and Assert
    assertEquals(-1, multiCastSender.send("Mess"));
  }

  /**
   * Test {@link MultiCastSender#send(String)}.
   * <ul>
   *   <li>Given {@link MultiCastSender} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiCastSender#send(String)}
   */
  @Test
  public void testSend_givenMultiCastSender_thenReturnMinusOne() throws Exception {
    // Arrange, Act and Assert
    assertEquals(-1, (new MultiCastSender()).send("Mess"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link MultiCastSender}
   *   <li>{@link MultiCastSender#init(HeartbeatListener)}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws Exception {
    // Arrange and Act
    MultiCastSender actualMultiCastSender = new MultiCastSender();
    actualMultiCastSender.init(new HeartbeatListener());

    // Assert
    HeartbeatListener heartbeatListener = actualMultiCastSender.config;
    assertEquals("/HeartbeatListener", heartbeatListener.getProxyURL());
    assertEquals("224.0.1.105", heartbeatListener.getGroup());
    assertNull(heartbeatListener.getHost());
    assertNull(heartbeatListener.getProxyList());
    assertEquals(23364, heartbeatListener.getMultiport());
    assertEquals(8009, heartbeatListener.getPort());
    assertEquals(Short.SIZE, heartbeatListener.getTtl());
  }
}
