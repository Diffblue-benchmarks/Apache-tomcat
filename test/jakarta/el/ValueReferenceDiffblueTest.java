package jakarta.el;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ValueReferenceDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ValueReference#ValueReference(Object, Object)}
   *   <li>{@link ValueReference#getBase()}
   *   <li>{@link ValueReference#getProperty()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ValueReference actualValueReference = new ValueReference("Base", "Property");
    Object actualBase = actualValueReference.getBase();

    // Assert
    assertEquals("Base", actualBase);
    assertEquals("Property", actualValueReference.getProperty());
  }
}
