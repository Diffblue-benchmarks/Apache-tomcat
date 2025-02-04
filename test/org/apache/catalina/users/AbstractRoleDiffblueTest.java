package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class AbstractRoleDiffblueTest {
  /**
   * Test {@link AbstractRole#getDescription()}.
   * <p>
   * Method under test: {@link AbstractRole#getDescription()}
   */
  @Test
  public void testGetDescription() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertEquals("The characteristics of someone or something", genericRole.getDescription());
  }

  /**
   * Test {@link AbstractRole#getRolename()}.
   * <p>
   * Method under test: {@link AbstractRole#getRolename()}
   */
  @Test
  public void testGetRolename() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertEquals("Rolename", genericRole.getRolename());
  }

  /**
   * Test {@link AbstractRole#getName()}.
   * <p>
   * Method under test: {@link AbstractRole#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange
    GenericRole<UserDatabase> genericRole = new GenericRole<>(new MemoryUserDatabase(), "Rolename",
        "The characteristics of someone or something");

    // Act and Assert
    assertEquals("Rolename", genericRole.getName());
  }
}
