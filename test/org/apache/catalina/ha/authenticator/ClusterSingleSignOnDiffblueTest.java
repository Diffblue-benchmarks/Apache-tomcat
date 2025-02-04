package org.apache.catalina.ha.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.junit.Test;

public class ClusterSingleSignOnDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClusterSingleSignOn#setAccessTimeout(long)}
   *   <li>{@link ClusterSingleSignOn#setCluster(CatalinaCluster)}
   *   <li>{@link ClusterSingleSignOn#setMapSendOptions(int)}
   *   <li>{@link ClusterSingleSignOn#setRpcTimeout(long)}
   *   <li>{@link ClusterSingleSignOn#setTerminateOnStartFailure(boolean)}
   *   <li>{@link ClusterSingleSignOn#objectMadePrimary(Object, Object)}
   *   <li>{@link ClusterSingleSignOn#getAccessTimeout()}
   *   <li>{@link ClusterSingleSignOn#getCluster()}
   *   <li>{@link ClusterSingleSignOn#getMapSendOptions()}
   *   <li>{@link ClusterSingleSignOn#getRpcTimeout()}
   *   <li>{@link ClusterSingleSignOn#getTerminateOnStartFailure()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ClusterSingleSignOn clusterSingleSignOn = new ClusterSingleSignOn();

    // Act
    clusterSingleSignOn.setAccessTimeout(1L);
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    clusterSingleSignOn.setCluster(cluster);
    clusterSingleSignOn.setMapSendOptions(3);
    clusterSingleSignOn.setRpcTimeout(1L);
    clusterSingleSignOn.setTerminateOnStartFailure(true);
    clusterSingleSignOn.objectMadePrimary("Key", "Value");
    long actualAccessTimeout = clusterSingleSignOn.getAccessTimeout();
    CatalinaCluster actualCluster = clusterSingleSignOn.getCluster();
    int actualMapSendOptions = clusterSingleSignOn.getMapSendOptions();
    long actualRpcTimeout = clusterSingleSignOn.getRpcTimeout();

    // Assert
    assertEquals(1L, actualAccessTimeout);
    assertEquals(1L, actualRpcTimeout);
    assertEquals(3, actualMapSendOptions);
    assertTrue(clusterSingleSignOn.getTerminateOnStartFailure());
    assertSame(cluster, actualCluster);
  }

  /**
   * Test {@link ClusterSingleSignOn#update(String, Principal, String, String, String)}.
   * <p>
   * Method under test: {@link ClusterSingleSignOn#update(String, Principal, String, String, String)}
   */
  @Test
  public void testUpdate() {
    // Arrange
    ClusterSingleSignOn clusterSingleSignOn = new ClusterSingleSignOn();

    // Act and Assert
    assertFalse(clusterSingleSignOn.update("42", new UserPrincipal("principal"), "Auth Type", "janedoe", "iloveyou"));
  }

  /**
   * Test {@link ClusterSingleSignOn#getSessionListener(String)}.
   * <p>
   * Method under test: {@link ClusterSingleSignOn#getSessionListener(String)}
   */
  @Test
  public void testGetSessionListener() {
    // Arrange and Act
    SessionListener actualSessionListener = (new ClusterSingleSignOn()).getSessionListener("42");
    actualSessionListener.sessionEvent(new SessionEvent(new DeltaSession(), "Type", "Data"));

    // Assert
    assertTrue(actualSessionListener instanceof ClusterSingleSignOnListener);
  }

  /**
   * Test {@link ClusterSingleSignOn#startInternal()}.
   * <ul>
   *   <li>Given {@link ClusterSingleSignOn} (default constructor).</li>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterSingleSignOn#startInternal()}
   */
  @Test
  public void testStartInternal_givenClusterSingleSignOn_thenThrowLifecycleException() throws LifecycleException {
    // Arrange, Act and Assert
    assertThrows(LifecycleException.class, () -> (new ClusterSingleSignOn()).startInternal());
  }

  /**
   * Test {@link ClusterSingleSignOn#startInternal()}.
   * <ul>
   *   <li>Then throw {@link LifecycleException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterSingleSignOn#startInternal()}
   */
  @Test
  public void testStartInternal_thenThrowLifecycleException() throws LifecycleException {
    // Arrange
    ClusterSingleSignOn clusterSingleSignOn = new ClusterSingleSignOn();
    clusterSingleSignOn.setCluster(null);
    clusterSingleSignOn.setContainer(new StandardHost());
    clusterSingleSignOn.setTerminateOnStartFailure(false);

    // Act and Assert
    assertThrows(LifecycleException.class, () -> clusterSingleSignOn.startInternal());
  }

  /**
   * Test new {@link ClusterSingleSignOn} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ClusterSingleSignOn}
   */
  @Test
  public void testNewClusterSingleSignOn() {
    // Arrange and Act
    ClusterSingleSignOn actualClusterSingleSignOn = new ClusterSingleSignOn();

    // Assert
    assertEquals("Catalina", actualClusterSingleSignOn.getDomain());
    assertEquals("JSESSIONIDSSO", actualClusterSingleSignOn.getCookieName());
    assertEquals("NEW", actualClusterSingleSignOn.getStateName());
    assertNull(actualClusterSingleSignOn.getCookieDomain());
    assertNull(actualClusterSingleSignOn.getDomainInternal());
    assertNull(actualClusterSingleSignOn.getObjectName());
    assertNull(actualClusterSingleSignOn.getContainer());
    assertNull(actualClusterSingleSignOn.getNext());
    assertNull(actualClusterSingleSignOn.getCluster());
    assertEquals(0, actualClusterSingleSignOn.findLifecycleListeners().length);
    assertEquals(15000L, actualClusterSingleSignOn.getRpcTimeout());
    assertEquals(5000L, actualClusterSingleSignOn.getAccessTimeout());
    assertEquals(6, actualClusterSingleSignOn.getMapSendOptions());
    assertEquals(LifecycleState.NEW, actualClusterSingleSignOn.getState());
    assertFalse(actualClusterSingleSignOn.getRequireReauthentication());
    assertFalse(actualClusterSingleSignOn.getTerminateOnStartFailure());
    assertTrue(actualClusterSingleSignOn.getThrowOnFailure());
    assertTrue(actualClusterSingleSignOn.isAsyncSupported());
  }
}
