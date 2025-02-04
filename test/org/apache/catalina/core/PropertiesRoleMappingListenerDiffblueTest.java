package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.apache.catalina.LifecycleEvent;
import org.junit.Test;

public class PropertiesRoleMappingListenerDiffblueTest {
  /**
   * Test {@link PropertiesRoleMappingListener#setRoleMappingFile(String)}.
   * <p>
   * Method under test: {@link PropertiesRoleMappingListener#setRoleMappingFile(String)}
   */
  @Test
  public void testSetRoleMappingFile() {
    // Arrange
    PropertiesRoleMappingListener propertiesRoleMappingListener = new PropertiesRoleMappingListener();

    // Act
    propertiesRoleMappingListener.setRoleMappingFile("Role Mapping File");

    // Assert
    assertEquals("Role Mapping File", propertiesRoleMappingListener.getRoleMappingFile());
  }

  /**
   * Test {@link PropertiesRoleMappingListener#setRoleMappingFile(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesRoleMappingListener#setRoleMappingFile(String)}
   */
  @Test
  public void testSetRoleMappingFile_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new PropertiesRoleMappingListener()).setRoleMappingFile(""));
  }

  /**
   * Test {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    PropertiesRoleMappingListener propertiesRoleMappingListener = new PropertiesRoleMappingListener();
    propertiesRoleMappingListener.setRoleMappingFile("configure_start");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> propertiesRoleMappingListener
        .lifecycleEvent(new LifecycleEvent(new StandardContext(), "configure_start", "Data")));
  }

  /**
   * Test {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    PropertiesRoleMappingListener propertiesRoleMappingListener = new PropertiesRoleMappingListener();
    propertiesRoleMappingListener.setRoleMappingFile("Role Mapping File");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> propertiesRoleMappingListener
        .lifecycleEvent(new LifecycleEvent(new StandardContext(), "configure_start", "Data")));
  }

  /**
   * Test {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link PropertiesRoleMappingListener} (default constructor) RoleMappingFile is {@code user.dir}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesRoleMappingListener#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenPropertiesRoleMappingListenerRoleMappingFileIsUserDir() {
    // Arrange
    PropertiesRoleMappingListener propertiesRoleMappingListener = new PropertiesRoleMappingListener();
    propertiesRoleMappingListener.setRoleMappingFile("user.dir");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> propertiesRoleMappingListener
        .lifecycleEvent(new LifecycleEvent(new StandardContext(), "configure_start", "Data")));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link PropertiesRoleMappingListener}
   *   <li>{@link PropertiesRoleMappingListener#setKeyPrefix(String)}
   *   <li>{@link PropertiesRoleMappingListener#getKeyPrefix()}
   *   <li>{@link PropertiesRoleMappingListener#getRoleMappingFile()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    PropertiesRoleMappingListener actualPropertiesRoleMappingListener = new PropertiesRoleMappingListener();
    actualPropertiesRoleMappingListener.setKeyPrefix("Key Prefix");
    String actualKeyPrefix = actualPropertiesRoleMappingListener.getKeyPrefix();

    // Assert
    assertEquals("Key Prefix", actualKeyPrefix);
    assertEquals("webapp:/WEB-INF/role-mapping.properties", actualPropertiesRoleMappingListener.getRoleMappingFile());
  }
}
