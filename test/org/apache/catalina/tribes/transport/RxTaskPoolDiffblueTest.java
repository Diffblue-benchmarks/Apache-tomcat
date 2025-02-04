package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.catalina.tribes.io.ListenCallback;
import org.apache.catalina.tribes.transport.RxTaskPool.TaskCreator;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.apache.catalina.tribes.transport.nio.NioReplicationTask;
import org.junit.Test;

public class RxTaskPoolDiffblueTest {
  /**
   * Test {@link RxTaskPool#RxTaskPool(int, int, TaskCreator)}.
   * <p>
   * Method under test: {@link RxTaskPool#RxTaskPool(int, int, TaskCreator)}
   */
  @Test
  public void testNewRxTaskPool() throws Exception {
    // Arrange
    NioReceiver creator = new NioReceiver();

    // Act
    RxTaskPool actualRxTaskPool = new RxTaskPool(3, 1, creator);

    // Assert
    TaskCreator taskCreator = actualRxTaskPool.getTaskCreator();
    assertTrue(taskCreator instanceof NioReceiver);
    assertEquals(0, actualRxTaskPool.available());
    assertEquals(1, actualRxTaskPool.getMinThreads());
    assertEquals(3, actualRxTaskPool.getMaxThreads());
    assertTrue(actualRxTaskPool.idle.isEmpty());
    assertTrue(actualRxTaskPool.used.isEmpty());
    assertTrue(actualRxTaskPool.running);
    assertSame(creator, taskCreator);
  }

  /**
   * Test {@link RxTaskPool#configureTask(AbstractRxTask)}.
   * <p>
   * Method under test: {@link RxTaskPool#configureTask(AbstractRxTask)}
   */
  @Test
  public void testConfigureTask() throws Exception {
    // Arrange
    RxTaskPool rxTaskPool = new RxTaskPool(3, 1, new NioReceiver());
    NioReceiver callback = new NioReceiver();
    NioReplicationTask task = new NioReplicationTask(callback, new NioReceiver());

    // Act
    rxTaskPool.configureTask(task);

    // Assert
    assertSame(rxTaskPool, task.getTaskPool());
  }

  /**
   * Test {@link RxTaskPool#getRxTask()}.
   * <p>
   * Method under test: {@link RxTaskPool#getRxTask()}
   */
  @Test
  public void testGetRxTask() throws Exception {
    // Arrange
    NioReceiver creator = new NioReceiver();
    RxTaskPool rxTaskPool = new RxTaskPool(3, 1, creator);

    // Act
    AbstractRxTask actualRxTask = rxTaskPool.getRxTask();

    // Assert
    ListenCallback callback = actualRxTask.getCallback();
    assertTrue(callback instanceof NioReceiver);
    assertTrue(actualRxTask instanceof NioReplicationTask);
    assertEquals(1, rxTaskPool.used.size());
    assertEquals(4, actualRxTask.getOptions());
    assertTrue(actualRxTask.getUseBufferPool());
    assertEquals(Constants.DEFAULT_CLUSTER_MSG_BUFFER_SIZE, ((NioReplicationTask) actualRxTask).getRxBufSize());
    assertSame(rxTaskPool, actualRxTask.getTaskPool());
    assertSame(creator, callback);
  }

  /**
   * Test {@link RxTaskPool#available()}.
   * <p>
   * Method under test: {@link RxTaskPool#available()}
   */
  @Test
  public void testAvailable() throws Exception {
    // Arrange, Act and Assert
    assertEquals(0, (new RxTaskPool(3, 1, new NioReceiver())).available());
  }

  /**
   * Test {@link RxTaskPool#returnWorker(AbstractRxTask)}.
   * <p>
   * Method under test: {@link RxTaskPool#returnWorker(AbstractRxTask)}
   */
  @Test
  public void testReturnWorker() throws Exception {
    // Arrange
    RxTaskPool rxTaskPool = new RxTaskPool(3, 1, new NioReceiver());
    NioReceiver callback = new NioReceiver();
    NioReplicationTask worker = new NioReplicationTask(callback, new NioReceiver());

    // Act
    rxTaskPool.returnWorker(worker);

    // Assert
    List<AbstractRxTask> abstractRxTaskList = rxTaskPool.idle;
    assertEquals(1, abstractRxTaskList.size());
    assertEquals(1, rxTaskPool.available());
    assertSame(worker, abstractRxTaskList.get(0));
  }

  /**
   * Test {@link RxTaskPool#returnWorker(AbstractRxTask)}.
   * <p>
   * Method under test: {@link RxTaskPool#returnWorker(AbstractRxTask)}
   */
  @Test
  public void testReturnWorker2() throws Exception {
    // Arrange
    RxTaskPool rxTaskPool = new RxTaskPool(0, 1, new NioReceiver());
    NioReceiver callback = new NioReceiver();

    // Act
    rxTaskPool.returnWorker(new NioReplicationTask(callback, new NioReceiver()));

    // Assert that nothing has changed
    assertEquals(0, rxTaskPool.available());
    assertTrue(rxTaskPool.idle.isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RxTaskPool#setMaxTasks(int)}
   *   <li>{@link RxTaskPool#setMinTasks(int)}
   *   <li>{@link RxTaskPool#getMaxThreads()}
   *   <li>{@link RxTaskPool#getMinThreads()}
   *   <li>{@link RxTaskPool#getTaskCreator()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws Exception {
    // Arrange
    NioReceiver creator = new NioReceiver();
    RxTaskPool rxTaskPool = new RxTaskPool(3, 1, creator);

    // Act
    rxTaskPool.setMaxTasks(3);
    rxTaskPool.setMinTasks(1);
    int actualMaxThreads = rxTaskPool.getMaxThreads();
    int actualMinThreads = rxTaskPool.getMinThreads();

    // Assert
    assertEquals(1, actualMinThreads);
    assertEquals(3, actualMaxThreads);
    assertSame(creator, rxTaskPool.getTaskCreator());
  }

  /**
   * Test {@link RxTaskPool#stop()}.
   * <p>
   * Method under test: {@link RxTaskPool#stop()}
   */
  @Test
  public void testStop() throws Exception {
    // Arrange
    RxTaskPool rxTaskPool = new RxTaskPool(3, 1, new NioReceiver());

    // Act
    rxTaskPool.stop();

    // Assert
    assertFalse(rxTaskPool.running);
  }
}
