package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class ELClassDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ELClass#ELClass(Class)}
   *   <li>{@link ELClass#getKlass()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Class<?> actualKlass = (new ELClass(clazz)).getKlass();

    // Assert
    Class<Object> expectedKlass = Object.class;
    assertEquals(expectedKlass, actualKlass);
    assertSame(clazz, actualKlass);
  }
}
