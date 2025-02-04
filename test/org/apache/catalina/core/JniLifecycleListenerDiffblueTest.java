package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.junit.Test;

public class JniLifecycleListenerDiffblueTest {
  /**
   * Test {@link JniLifecycleListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link JniLifecycleListener} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JniLifecycleListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenJniLifecycleListener_thenThrowIllegalArgumentException() {
    // Arrange
    JniLifecycleListener jniLifecycleListener = new JniLifecycleListener();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jniLifecycleListener
        .lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "before_start", "Data")));
  }

  /**
   * Test {@link JniLifecycleListener#setLibraryName(String)}.
   * <ul>
   *   <li>Then {@link JniLifecycleListener} (default constructor) LibraryName is {@code Library Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JniLifecycleListener#setLibraryName(String)}
   */
  @Test
  public void testSetLibraryName_thenJniLifecycleListenerLibraryNameIsLibraryName() {
    // Arrange
    JniLifecycleListener jniLifecycleListener = new JniLifecycleListener();

    // Act
    jniLifecycleListener.setLibraryName("Library Name");

    // Assert
    assertEquals("Library Name", jniLifecycleListener.getLibraryName());
  }

  /**
   * Test {@link JniLifecycleListener#setLibraryName(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JniLifecycleListener#setLibraryName(String)}
   */
  @Test
  public void testSetLibraryName_thenThrowIllegalArgumentException() {
    // Arrange
    JniLifecycleListener jniLifecycleListener = new JniLifecycleListener();
    jniLifecycleListener.setLibraryPath("Library Path");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jniLifecycleListener.setLibraryName("Library Name"));
  }

  /**
   * Test {@link JniLifecycleListener#setLibraryPath(String)}.
   * <ul>
   *   <li>Then {@link JniLifecycleListener} (default constructor) LibraryPath is {@code Library Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JniLifecycleListener#setLibraryPath(String)}
   */
  @Test
  public void testSetLibraryPath_thenJniLifecycleListenerLibraryPathIsLibraryPath() {
    // Arrange
    JniLifecycleListener jniLifecycleListener = new JniLifecycleListener();

    // Act
    jniLifecycleListener.setLibraryPath("Library Path");

    // Assert
    assertEquals("Library Path", jniLifecycleListener.getLibraryPath());
  }

  /**
   * Test {@link JniLifecycleListener#setLibraryPath(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JniLifecycleListener#setLibraryPath(String)}
   */
  @Test
  public void testSetLibraryPath_thenThrowIllegalArgumentException() {
    // Arrange
    JniLifecycleListener jniLifecycleListener = new JniLifecycleListener();
    jniLifecycleListener.setLibraryName("Library Name");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jniLifecycleListener.setLibraryPath("Library Path"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link JniLifecycleListener}
   *   <li>{@link JniLifecycleListener#getLibraryName()}
   *   <li>{@link JniLifecycleListener#getLibraryPath()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    JniLifecycleListener actualJniLifecycleListener = new JniLifecycleListener();
    String actualLibraryName = actualJniLifecycleListener.getLibraryName();

    // Assert
    assertEquals("", actualLibraryName);
    assertEquals("", actualJniLifecycleListener.getLibraryPath());
  }
}
