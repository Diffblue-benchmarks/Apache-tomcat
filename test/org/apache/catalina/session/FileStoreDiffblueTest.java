package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.junit.Test;

public class FileStoreDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileStore#getDirectory()}
   *   <li>{@link FileStore#getStoreName()}
   *   <li>{@link FileStore#getThreadName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FileStore fileStore = new FileStore();

    // Act
    String actualDirectory = fileStore.getDirectory();
    String actualStoreName = fileStore.getStoreName();

    // Assert
    assertEquals(".", actualDirectory);
    assertEquals("FileStore", fileStore.getThreadName());
    assertEquals("fileStore", actualStoreName);
  }

  /**
   * Test {@link FileStore#setDirectory(String)}.
   * <p>
   * Method under test: {@link FileStore#setDirectory(String)}
   */
  @Test
  public void testSetDirectory() {
    // Arrange
    FileStore fileStore = new FileStore();

    // Act
    fileStore.setDirectory("Path");

    // Assert
    assertEquals("Path", fileStore.getDirectory());
  }

  /**
   * Test new {@link FileStore} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FileStore}
   */
  @Test
  public void testNewFileStore() {
    // Arrange and Act
    FileStore actualFileStore = new FileStore();

    // Assert
    assertEquals(".", actualFileStore.getDirectory());
    assertEquals("FileStore", actualFileStore.getThreadName());
    assertEquals("NEW", actualFileStore.getStateName());
    assertEquals("fileStore", actualFileStore.getStoreName());
    assertNull(actualFileStore.getManager());
    assertEquals(0, actualFileStore.support.getPropertyChangeListeners().length);
    assertEquals(0, actualFileStore.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualFileStore.getState());
    assertTrue(actualFileStore.getThrowOnFailure());
  }
}
