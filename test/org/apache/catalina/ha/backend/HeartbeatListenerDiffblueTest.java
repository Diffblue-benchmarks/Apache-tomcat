package org.apache.catalina.ha.backend;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HeartbeatListenerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link HeartbeatListener}
   *   <li>{@link HeartbeatListener#setGroup(String)}
   *   <li>{@link HeartbeatListener#setHost(String)}
   *   <li>{@link HeartbeatListener#setMultiport(int)}
   *   <li>{@link HeartbeatListener#setPort(int)}
   *   <li>{@link HeartbeatListener#setProxyList(String)}
   *   <li>{@link HeartbeatListener#setProxyURLString(String)}
   *   <li>{@link HeartbeatListener#setTtl(int)}
   *   <li>{@link HeartbeatListener#getGroup()}
   *   <li>{@link HeartbeatListener#getHost()}
   *   <li>{@link HeartbeatListener#getMultiport()}
   *   <li>{@link HeartbeatListener#getPort()}
   *   <li>{@link HeartbeatListener#getProxyList()}
   *   <li>{@link HeartbeatListener#getProxyURL()}
   *   <li>{@link HeartbeatListener#getTtl()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    HeartbeatListener actualHeartbeatListener = new HeartbeatListener();
    actualHeartbeatListener.setGroup("Group");
    actualHeartbeatListener.setHost("localhost");
    actualHeartbeatListener.setMultiport(8080);
    actualHeartbeatListener.setPort(8080);
    actualHeartbeatListener.setProxyList("Proxy List");
    actualHeartbeatListener.setProxyURLString("https://example.org/example");
    actualHeartbeatListener.setTtl(1);
    String actualGroup = actualHeartbeatListener.getGroup();
    String actualHost = actualHeartbeatListener.getHost();
    int actualMultiport = actualHeartbeatListener.getMultiport();
    int actualPort = actualHeartbeatListener.getPort();
    String actualProxyList = actualHeartbeatListener.getProxyList();
    String actualProxyURL = actualHeartbeatListener.getProxyURL();

    // Assert
    assertEquals("Group", actualGroup);
    assertEquals("Proxy List", actualProxyList);
    assertEquals("https://example.org/example", actualProxyURL);
    assertEquals("localhost", actualHost);
    assertEquals(1, actualHeartbeatListener.getTtl());
    assertEquals(8080, actualMultiport);
    assertEquals(8080, actualPort);
  }
}
