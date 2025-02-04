package org.apache.catalina.ha.deploy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.ha.ClusterMessage;
import org.apache.catalina.ha.session.SessionMessageImpl;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class FarmWarDeployerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link FarmWarDeployer}
   *   <li>{@link FarmWarDeployer#setDeployDir(String)}
   *   <li>{@link FarmWarDeployer#setMaxValidTime(int)}
   *   <li>{@link FarmWarDeployer#setTempDir(String)}
   *   <li>{@link FarmWarDeployer#setWatchDir(String)}
   *   <li>{@link FarmWarDeployer#setWatchEnabled(boolean)}
   *   <li>{@link FarmWarDeployer#getDeployDir()}
   *   <li>{@link FarmWarDeployer#getMaxValidTime()}
   *   <li>{@link FarmWarDeployer#getProcessDeployFrequency()}
   *   <li>{@link FarmWarDeployer#getTempDir()}
   *   <li>{@link FarmWarDeployer#getWatchDir()}
   *   <li>{@link FarmWarDeployer#getWatchEnabled()}
   *   <li>{@link FarmWarDeployer#isWatchEnabled()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    FarmWarDeployer actualFarmWarDeployer = new FarmWarDeployer();
    actualFarmWarDeployer.setDeployDir("Deploy Dir");
    actualFarmWarDeployer.setMaxValidTime(1);
    actualFarmWarDeployer.setTempDir("Temp Dir");
    actualFarmWarDeployer.setWatchDir("Watch Dir");
    actualFarmWarDeployer.setWatchEnabled(true);
    String actualDeployDir = actualFarmWarDeployer.getDeployDir();
    int actualMaxValidTime = actualFarmWarDeployer.getMaxValidTime();
    int actualProcessDeployFrequency = actualFarmWarDeployer.getProcessDeployFrequency();
    String actualTempDir = actualFarmWarDeployer.getTempDir();
    String actualWatchDir = actualFarmWarDeployer.getWatchDir();
    boolean actualWatchEnabled = actualFarmWarDeployer.getWatchEnabled();
    boolean actualIsWatchEnabledResult = actualFarmWarDeployer.isWatchEnabled();

    // Assert
    assertEquals("Deploy Dir", actualDeployDir);
    assertEquals("Temp Dir", actualTempDir);
    assertEquals("Watch Dir", actualWatchDir);
    assertNull(actualFarmWarDeployer.getCluster());
    assertEquals(1, actualMaxValidTime);
    assertEquals(2, actualProcessDeployFrequency);
    assertTrue(actualWatchEnabled);
    assertTrue(actualIsWatchEnabledResult);
  }

  /**
   * Test {@link FarmWarDeployer#start()}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) {@link FarmWarDeployer#host} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#start()}
   */
  @Test
  public void testStart_thenFarmWarDeployerHostIsNull() throws Exception {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(new SimpleTcpCluster());

    // Act
    farmWarDeployer.start();

    // Assert that nothing has changed
    assertNull(farmWarDeployer.host);
  }

  /**
   * Test {@link FarmWarDeployer#start()}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) {@link FarmWarDeployer#host} {@link StandardHost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#start()}
   */
  @Test
  public void testStart_thenFarmWarDeployerHostStandardHost() throws Exception {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    cluster.setContainer(new StandardHost());

    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(cluster);

    // Act
    farmWarDeployer.start();

    // Assert
    assertTrue(farmWarDeployer.host instanceof StandardHost);
  }

  /**
   * Test {@link FarmWarDeployer#stop()}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) Cluster is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#stop()}
   */
  @Test
  public void testStop_thenFarmWarDeployerClusterIsNull() throws LifecycleException {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(new SimpleTcpCluster());

    // Act
    farmWarDeployer.stop();

    // Assert
    assertNull(farmWarDeployer.getCluster());
  }

  /**
   * Test {@link FarmWarDeployer#accept(ClusterMessage)} with {@code msg}.
   * <p>
   * Method under test: {@link FarmWarDeployer#accept(ClusterMessage)}
   */
  @Test
  public void testAcceptWithMsg() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();

    // Act and Assert
    assertTrue(farmWarDeployer.accept(new FileMessage(new MemberImpl(), "foo.txt", "Context Name")));
  }

  /**
   * Test {@link FarmWarDeployer#accept(ClusterMessage)} with {@code msg}.
   * <p>
   * Method under test: {@link FarmWarDeployer#accept(ClusterMessage)}
   */
  @Test
  public void testAcceptWithMsg2() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();

    // Act and Assert
    assertTrue(farmWarDeployer.accept(new UndeployMessage(new MemberImpl(), 10L, "42", "Context Name")));
  }

  /**
   * Test {@link FarmWarDeployer#accept(ClusterMessage)} with {@code msg}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#accept(ClusterMessage)}
   */
  @Test
  public void testAcceptWithMsg_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();

    // Act and Assert
    assertFalse(farmWarDeployer
        .accept(new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID")));
  }

  /**
   * Test {@link FarmWarDeployer#getDeployDirFile()}.
   * <ul>
   *   <li>Then return Name is {@code .war}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#getDeployDirFile()}
   */
  @Test
  public void testGetDeployDirFile_thenReturnNameIsWar() {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    cluster.setContainer(new StandardContext());

    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(cluster);
    farmWarDeployer.setDeployDir(".war");

    // Act
    File actualDeployDirFile = farmWarDeployer.getDeployDirFile();

    // Assert
    assertEquals(".war", actualDeployDirFile.getName());
    assertTrue(actualDeployDirFile.isAbsolute());
  }

  /**
   * Test {@link FarmWarDeployer#getTempDirFile()}.
   * <ul>
   *   <li>Then return Name is {@code .war}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#getTempDirFile()}
   */
  @Test
  public void testGetTempDirFile_thenReturnNameIsWar() {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    cluster.setContainer(new StandardContext());

    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(cluster);
    farmWarDeployer.setTempDir(".war");

    // Act
    File actualTempDirFile = farmWarDeployer.getTempDirFile();

    // Assert
    assertEquals(".war", actualTempDirFile.getName());
    assertTrue(actualTempDirFile.isAbsolute());
  }

  /**
   * Test {@link FarmWarDeployer#getWatchDirFile()}.
   * <ul>
   *   <li>Then return Name is {@code .war}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#getWatchDirFile()}
   */
  @Test
  public void testGetWatchDirFile_thenReturnNameIsWar() {
    // Arrange
    SimpleTcpCluster cluster = new SimpleTcpCluster();
    cluster.setContainer(new StandardContext());

    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    farmWarDeployer.setCluster(cluster);
    farmWarDeployer.setWatchDir(".war");

    // Act
    File actualWatchDirFile = farmWarDeployer.getWatchDirFile();

    // Assert
    assertEquals(".war", actualWatchDirFile.getName());
    assertTrue(actualWatchDirFile.isAbsolute());
  }

  /**
   * Test {@link FarmWarDeployer#setProcessDeployFrequency(int)}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) ProcessDeployFrequency is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#setProcessDeployFrequency(int)}
   */
  @Test
  public void testSetProcessDeployFrequency_thenFarmWarDeployerProcessDeployFrequencyIsOne() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();

    // Act
    farmWarDeployer.setProcessDeployFrequency(1);

    // Assert
    assertEquals(1, farmWarDeployer.getProcessDeployFrequency());
  }

  /**
   * Test {@link FarmWarDeployer#setProcessDeployFrequency(int)}.
   * <ul>
   *   <li>Then {@link FarmWarDeployer} (default constructor) ProcessDeployFrequency is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#setProcessDeployFrequency(int)}
   */
  @Test
  public void testSetProcessDeployFrequency_thenFarmWarDeployerProcessDeployFrequencyIsTwo() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();

    // Act
    farmWarDeployer.setProcessDeployFrequency(0);

    // Assert that nothing has changed
    assertEquals(2, farmWarDeployer.getProcessDeployFrequency());
  }

  /**
   * Test {@link FarmWarDeployer#copy(File, File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code farmWarDeployer.fileCopyFail} and {@code unknown} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#copy(File, File)}
   */
  @Test
  public void testCopy_whenPropertyIsJavaIoTmpdirIsFarmWarDeployerFileCopyFailAndUnknownToFile() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    File from = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertFalse(farmWarDeployer.copy(from,
        Paths.get(System.getProperty("java.io.tmpdir"), "farmWarDeployer.fileCopyFail", "unknown").toFile()));
  }

  /**
   * Test {@link FarmWarDeployer#copy(File, File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FarmWarDeployer#copy(File, File)}
   */
  @Test
  public void testCopy_whenPropertyIsJavaIoTmpdirIsTestTxtToFile() {
    // Arrange
    FarmWarDeployer farmWarDeployer = new FarmWarDeployer();
    File from = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertFalse(farmWarDeployer.copy(from, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }
}
