package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CoyotePrincipalDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CoyotePrincipal#CoyotePrincipal(String)}
   *   <li>{@link CoyotePrincipal#toString()}
   *   <li>{@link CoyotePrincipal#getName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CoyotePrincipal actualCoyotePrincipal = new CoyotePrincipal("Name");
    String actualToStringResult = actualCoyotePrincipal.toString();

    // Assert
    assertEquals("CoyotePrincipal[Name]", actualToStringResult);
    assertEquals("Name", actualCoyotePrincipal.getName());
  }
}
