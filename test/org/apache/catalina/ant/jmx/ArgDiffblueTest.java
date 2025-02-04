package org.apache.catalina.ant.jmx;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArgDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Arg}
   *   <li>{@link Arg#setType(String)}
   *   <li>{@link Arg#setValue(String)}
   *   <li>{@link Arg#getType()}
   *   <li>{@link Arg#getValue()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    Arg actualArg = new Arg();
    actualArg.setType("Type");
    actualArg.setValue("42");
    String actualType = actualArg.getType();

    // Assert
    assertEquals("42", actualArg.getValue());
    assertEquals("Type", actualType);
  }
}
