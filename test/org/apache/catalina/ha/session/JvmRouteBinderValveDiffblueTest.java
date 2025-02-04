package org.apache.catalina.ha.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.junit.Test;

public class JvmRouteBinderValveDiffblueTest {
  /**
   * Test new {@link JvmRouteBinderValve} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JvmRouteBinderValve}
   */
  @Test
  public void testNewJvmRouteBinderValve() {
    // Arrange and Act
    JvmRouteBinderValve actualJvmRouteBinderValve = new JvmRouteBinderValve();

    // Assert
    assertEquals("Catalina", actualJvmRouteBinderValve.getDomain());
    assertEquals("NEW", actualJvmRouteBinderValve.getStateName());
    assertEquals("org.apache.catalina.ha.session.JvmRouteOriginalSessionID",
        actualJvmRouteBinderValve.getSessionIdAttribute());
    assertNull(actualJvmRouteBinderValve.getDomainInternal());
    assertNull(actualJvmRouteBinderValve.getObjectName());
    assertNull(actualJvmRouteBinderValve.getContainer());
    assertNull(actualJvmRouteBinderValve.getNext());
    assertNull(actualJvmRouteBinderValve.getCluster());
    assertEquals(0, actualJvmRouteBinderValve.findLifecycleListeners().length);
    assertEquals(0L, actualJvmRouteBinderValve.getNumberOfSessions());
    assertEquals(LifecycleState.NEW, actualJvmRouteBinderValve.getState());
    assertTrue(actualJvmRouteBinderValve.getEnabled());
    assertTrue(actualJvmRouteBinderValve.getThrowOnFailure());
    assertTrue(actualJvmRouteBinderValve.isAsyncSupported());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JvmRouteBinderValve#setCluster(CatalinaCluster)}
   *   <li>{@link JvmRouteBinderValve#setEnabled(boolean)}
   *   <li>{@link JvmRouteBinderValve#setSessionIdAttribute(String)}
   *   <li>{@link JvmRouteBinderValve#getCluster()}
   *   <li>{@link JvmRouteBinderValve#getEnabled()}
   *   <li>{@link JvmRouteBinderValve#getNumberOfSessions()}
   *   <li>{@link JvmRouteBinderValve#getSessionIdAttribute()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    JvmRouteBinderValve jvmRouteBinderValve = new JvmRouteBinderValve();
    SimpleTcpCluster cluster = new SimpleTcpCluster();

    // Act
    jvmRouteBinderValve.setCluster(cluster);
    jvmRouteBinderValve.setEnabled(true);
    jvmRouteBinderValve.setSessionIdAttribute("Session Id Attribute");
    CatalinaCluster actualCluster = jvmRouteBinderValve.getCluster();
    boolean actualEnabled = jvmRouteBinderValve.getEnabled();
    long actualNumberOfSessions = jvmRouteBinderValve.getNumberOfSessions();

    // Assert
    assertEquals("Session Id Attribute", jvmRouteBinderValve.getSessionIdAttribute());
    assertEquals(0L, actualNumberOfSessions);
    assertTrue(actualEnabled);
    assertSame(cluster, actualCluster);
  }
}
