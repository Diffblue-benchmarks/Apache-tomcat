package org.apache.catalina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.apache.catalina.core.StandardContext;
import org.junit.Test;

public class ContainerEventDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ContainerEvent#ContainerEvent(Container, String, Object)}
   *   <li>{@link ContainerEvent#toString()}
   *   <li>{@link ContainerEvent#getData()}
   *   <li>{@link ContainerEvent#getType()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardContext container = new StandardContext();

    // Act
    ContainerEvent actualContainerEvent = new ContainerEvent(container, "Type", "Data");
    String actualToStringResult = actualContainerEvent.toString();
    Object actualData = actualContainerEvent.getData();

    // Assert
    assertEquals("ContainerEvent['StandardContext[null]','Type','Data']", actualToStringResult);
    assertEquals("Data", actualData);
    assertEquals("Type", actualContainerEvent.getType());
    assertSame(container, actualContainerEvent.getSource());
  }

  /**
   * Test {@link ContainerEvent#getContainer()}.
   * <p>
   * Method under test: {@link ContainerEvent#getContainer()}
   */
  @Test
  public void testGetContainer() {
    // Arrange
    StandardContext container = new StandardContext();

    // Act and Assert
    assertSame(container, (new ContainerEvent(container, "Type", "Data")).getContainer());
  }
}
