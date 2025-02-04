package org.apache.catalina.ha.tcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.session.BackupManager;
import org.junit.Test;

public class ReplicationValveDiffblueTest {
  /**
   * Test new {@link ReplicationValve} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ReplicationValve}
   */
  @Test
  public void testNewReplicationValve() {
    // Arrange and Act
    ReplicationValve actualReplicationValve = new ReplicationValve();

    // Assert
    assertEquals("Catalina", actualReplicationValve.getDomain());
    assertEquals("NEW", actualReplicationValve.getStateName());
    assertEquals("org.apache.catalina.ha.tcp.isPrimarySession", actualReplicationValve.getPrimaryIndicatorName());
    assertNull(actualReplicationValve.getFilter());
    assertNull(actualReplicationValve.getDomainInternal());
    assertNull(actualReplicationValve.filter);
    assertNull(actualReplicationValve.getObjectName());
    assertNull(actualReplicationValve.getContainer());
    assertNull(actualReplicationValve.getNext());
    assertNull(actualReplicationValve.getCluster());
    assertEquals(0, actualReplicationValve.findLifecycleListeners().length);
    assertEquals(0L, actualReplicationValve.getLastSendTime());
    assertEquals(0L, actualReplicationValve.getNrOfCrossContextSendRequests());
    assertEquals(0L, actualReplicationValve.getNrOfFilterRequests());
    assertEquals(0L, actualReplicationValve.getNrOfRequests());
    assertEquals(0L, actualReplicationValve.getNrOfSendRequests());
    assertEquals(0L, actualReplicationValve.getTotalRequestTime());
    assertEquals(0L, actualReplicationValve.getTotalSendTime());
    assertEquals(LifecycleState.NEW, actualReplicationValve.getState());
    assertFalse(actualReplicationValve.isPrimaryIndicator());
    assertFalse(actualReplicationValve.doProcessingStats);
    assertTrue(actualReplicationValve.getThrowOnFailure());
    assertTrue(actualReplicationValve.isAsyncSupported());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplicationValve#setCluster(CatalinaCluster)}
   *   <li>{@link ReplicationValve#setPrimaryIndicator(boolean)}
   *   <li>{@link ReplicationValve#setPrimaryIndicatorName(String)}
   *   <li>{@link ReplicationValve#setStatistics(boolean)}
   *   <li>{@link ReplicationValve#getCluster()}
   *   <li>{@link ReplicationValve#getPrimaryIndicatorName()}
   *   <li>{@link ReplicationValve#isPrimaryIndicator()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    SimpleTcpCluster cluster = new SimpleTcpCluster();

    // Act
    replicationValve.setCluster(cluster);
    replicationValve.setPrimaryIndicator(true);
    replicationValve.setPrimaryIndicatorName("Primary Indicator Name");
    replicationValve.setStatistics(true);
    CatalinaCluster actualCluster = replicationValve.getCluster();
    String actualPrimaryIndicatorName = replicationValve.getPrimaryIndicatorName();

    // Assert
    assertEquals("Primary Indicator Name", actualPrimaryIndicatorName);
    assertTrue(replicationValve.isPrimaryIndicator());
    assertSame(cluster, actualCluster);
  }

  /**
   * Test {@link ReplicationValve#getFilter()}.
   * <ul>
   *   <li>Given {@link ReplicationValve} (default constructor) Filter is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#getFilter()}
   */
  @Test
  public void testGetFilter_givenReplicationValveFilterIsFoo_thenReturnFoo() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    replicationValve.setFilter("foo");

    // Act and Assert
    assertEquals("foo", replicationValve.getFilter());
  }

  /**
   * Test {@link ReplicationValve#getFilter()}.
   * <ul>
   *   <li>Given {@link ReplicationValve} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#getFilter()}
   */
  @Test
  public void testGetFilter_givenReplicationValve_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ReplicationValve()).getFilter());
  }

  /**
   * Test {@link ReplicationValve#setFilter(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) Filter is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#setFilter(String)}
   */
  @Test
  public void testSetFilter_whenEmptyString_thenReplicationValveFilterIsNull() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();

    // Act
    replicationValve.setFilter("");

    // Assert that nothing has changed
    assertNull(replicationValve.getFilter());
  }

  /**
   * Test {@link ReplicationValve#setFilter(String)}.
   * <ul>
   *   <li>When {@code Filter}.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) {@link ReplicationValve#filter} pattern is {@code Filter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#setFilter(String)}
   */
  @Test
  public void testSetFilter_whenFilter_thenReplicationValveFilterPatternIsFilter() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();

    // Act
    replicationValve.setFilter("Filter");

    // Assert
    assertEquals("Filter", replicationValve.filter.pattern());
    assertEquals("Filter", replicationValve.getFilter());
  }

  /**
   * Test {@link ReplicationValve#setFilter(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) Filter is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#setFilter(String)}
   */
  @Test
  public void testSetFilter_whenNull_thenReplicationValveFilterIsNull() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();

    // Act
    replicationValve.setFilter(null);

    // Assert that nothing has changed
    assertNull(replicationValve.getFilter());
  }

  /**
   * Test {@link ReplicationValve#doStatistics()}.
   * <ul>
   *   <li>Given {@link ReplicationValve} (default constructor) Statistics is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#doStatistics()}
   */
  @Test
  public void testDoStatistics_givenReplicationValveStatisticsIsTrue_thenReturnTrue() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    replicationValve.setStatistics(true);

    // Act and Assert
    assertTrue(replicationValve.doStatistics());
  }

  /**
   * Test {@link ReplicationValve#doStatistics()}.
   * <ul>
   *   <li>Given {@link ReplicationValve} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#doStatistics()}
   */
  @Test
  public void testDoStatistics_givenReplicationValve_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ReplicationValve()).doStatistics());
  }

  /**
   * Test {@link ReplicationValve#getLastSendTime()}.
   * <p>
   * Method under test: {@link ReplicationValve#getLastSendTime()}
   */
  @Test
  public void testGetLastSendTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getLastSendTime());
  }

  /**
   * Test {@link ReplicationValve#getNrOfRequests()}.
   * <p>
   * Method under test: {@link ReplicationValve#getNrOfRequests()}
   */
  @Test
  public void testGetNrOfRequests() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getNrOfRequests());
  }

  /**
   * Test {@link ReplicationValve#getNrOfFilterRequests()}.
   * <p>
   * Method under test: {@link ReplicationValve#getNrOfFilterRequests()}
   */
  @Test
  public void testGetNrOfFilterRequests() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getNrOfFilterRequests());
  }

  /**
   * Test {@link ReplicationValve#getNrOfCrossContextSendRequests()}.
   * <p>
   * Method under test: {@link ReplicationValve#getNrOfCrossContextSendRequests()}
   */
  @Test
  public void testGetNrOfCrossContextSendRequests() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getNrOfCrossContextSendRequests());
  }

  /**
   * Test {@link ReplicationValve#getNrOfSendRequests()}.
   * <p>
   * Method under test: {@link ReplicationValve#getNrOfSendRequests()}
   */
  @Test
  public void testGetNrOfSendRequests() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getNrOfSendRequests());
  }

  /**
   * Test {@link ReplicationValve#getTotalRequestTime()}.
   * <p>
   * Method under test: {@link ReplicationValve#getTotalRequestTime()}
   */
  @Test
  public void testGetTotalRequestTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getTotalRequestTime());
  }

  /**
   * Test {@link ReplicationValve#getTotalSendTime()}.
   * <p>
   * Method under test: {@link ReplicationValve#getTotalSendTime()}
   */
  @Test
  public void testGetTotalSendTime() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ReplicationValve()).getTotalSendTime());
  }

  /**
   * Test {@link ReplicationValve#sendReplicationMessage(Request, long, boolean, boolean, ClusterManager)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) {@link ReplicationValve#nrOfRequests} sum is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#sendReplicationMessage(Request, long, boolean, boolean, ClusterManager)}
   */
  @Test
  public void testSendReplicationMessage_whenFalse_thenReplicationValveNrOfRequestsSumIsOne() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    replicationValve.setStatistics(true);
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act
    replicationValve.sendReplicationMessage(request, 1L, true, false, new BackupManager());

    // Assert
    assertEquals(1L, replicationValve.nrOfRequests.sum());
    assertEquals(1L, replicationValve.getNrOfRequests());
  }

  /**
   * Test {@link ReplicationValve#isRequestWithoutSessionChange(String)}.
   * <ul>
   *   <li>Given {@link ReplicationValve} (default constructor).</li>
   *   <li>When {@code Uri}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#isRequestWithoutSessionChange(String)}
   */
  @Test
  public void testIsRequestWithoutSessionChange_givenReplicationValve_whenUri_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ReplicationValve()).isRequestWithoutSessionChange("Uri"));
  }

  /**
   * Test {@link ReplicationValve#isRequestWithoutSessionChange(String)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#isRequestWithoutSessionChange(String)}
   */
  @Test
  public void testIsRequestWithoutSessionChange_whenFoo_thenReturnTrue() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    replicationValve.setFilter("foo");

    // Act and Assert
    assertTrue(replicationValve.isRequestWithoutSessionChange("foo"));
  }

  /**
   * Test {@link ReplicationValve#isRequestWithoutSessionChange(String)}.
   * <ul>
   *   <li>When {@code Uri}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#isRequestWithoutSessionChange(String)}
   */
  @Test
  public void testIsRequestWithoutSessionChange_whenUri_thenReturnFalse() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();
    replicationValve.setFilter("foo");

    // Act and Assert
    assertFalse(replicationValve.isRequestWithoutSessionChange("Uri"));
  }

  /**
   * Test {@link ReplicationValve#updateStats(long, long, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) {@link ReplicationValve#nrOfRequests} sum is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#updateStats(long, long, boolean)}
   */
  @Test
  public void testUpdateStats_whenFalse_thenReplicationValveNrOfRequestsSumIsOne() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();

    // Act
    replicationValve.updateStats(1L, 1L, false);

    // Assert
    assertEquals(1L, replicationValve.nrOfRequests.sum());
    assertEquals(1L, replicationValve.getNrOfRequests());
  }

  /**
   * Test {@link ReplicationValve#updateStats(long, long, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then {@link ReplicationValve} (default constructor) {@link ReplicationValve#nrOfRequests} sum is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplicationValve#updateStats(long, long, boolean)}
   */
  @Test
  public void testUpdateStats_whenTrue_thenReplicationValveNrOfRequestsSumIsZero() {
    // Arrange
    ReplicationValve replicationValve = new ReplicationValve();

    // Act
    replicationValve.updateStats(1L, 1L, true);

    // Assert
    assertEquals(0L, replicationValve.nrOfRequests.sum());
    assertEquals(0L, replicationValve.getNrOfRequests());
  }
}
