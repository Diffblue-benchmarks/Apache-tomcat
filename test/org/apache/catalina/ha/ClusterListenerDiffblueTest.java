package org.apache.catalina.ha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import org.apache.catalina.ha.deploy.FarmWarDeployer;
import org.apache.catalina.ha.session.SessionMessageImpl;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class ClusterListenerDiffblueTest {
  /**
   * Test {@link ClusterListener#getCluster()}.
   * <p>
   * Method under test: {@link ClusterListener#getCluster()}
   */
  @Test
  public void testGetCluster() {
    // Arrange, Act and Assert
    assertNull((new FarmWarDeployer()).getCluster());
  }

  /**
   * Test {@link ClusterListener#setCluster(CatalinaCluster)}.
   * <p>
   * Method under test: {@link ClusterListener#setCluster(CatalinaCluster)}
   */
  @Test
  public void testSetCluster() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    SimpleTcpCluster cluster = new SimpleTcpCluster();

    // Act
    farmWarDeployer.setCluster(cluster);

    // Assert
    assertSame(cluster, farmWarDeployer.getCluster());
  }

  /**
   * Test {@link ClusterListener#accept(Serializable, Member)} with {@code Serializable}, {@code Member}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterListener#accept(Serializable, Member)}
   */
  @Test
  public void testAcceptWithSerializableMember_thenReturnFalse() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertFalse(farmWarDeployer.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link ClusterListener#accept(Serializable, Member)} with {@code Serializable}, {@code Member}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterListener#accept(Serializable, Member)}
   */
  @Test
  public void testAcceptWithSerializableMember_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    SessionMessageImpl msg = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID",
        "Unique ID");

    // Act and Assert
    assertTrue(farmWarDeployer.accept(msg, new MemberImpl()));
  }
}
