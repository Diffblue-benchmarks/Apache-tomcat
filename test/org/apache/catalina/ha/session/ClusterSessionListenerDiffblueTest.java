package org.apache.catalina.ha.session;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.ha.ClusterMessage;
import org.junit.Test;

public class ClusterSessionListenerDiffblueTest {
  /**
   * Test new {@link ClusterSessionListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ClusterSessionListener}
   */
  @Test
  public void testNewClusterSessionListener() {
    // Arrange, Act and Assert
    assertNull((new ClusterSessionListener()).getCluster());
  }

  /**
   * Test {@link ClusterSessionListener#accept(ClusterMessage)} with {@code msg}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterSessionListener#accept(ClusterMessage)}
   */
  @Test
  public void testAcceptWithMsg_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    ClusterSessionListener clusterSessionListener = new ClusterSessionListener();

    // Act and Assert
    assertTrue(clusterSessionListener
        .accept(new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID")));
  }

  /**
   * Test {@link ClusterSessionListener#accept(ClusterMessage)} with {@code msg}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterSessionListener#accept(ClusterMessage)}
   */
  @Test
  public void testAcceptWithMsg_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ClusterSessionListener()).accept(null));
  }
}
