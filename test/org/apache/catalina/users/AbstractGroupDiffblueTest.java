package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.apache.catalina.UserDatabase;
import org.junit.Test;

public class AbstractGroupDiffblueTest {
  /**
   * Test {@link AbstractGroup#getDescription()}.
   * <p>
   * Method under test: {@link AbstractGroup#getDescription()}
   */
  @Test
  public void testGetDescription() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertEquals("The characteristics of someone or something", genericGroup.getDescription());
  }

  /**
   * Test {@link AbstractGroup#getGroupname()}.
   * <p>
   * Method under test: {@link AbstractGroup#getGroupname()}
   */
  @Test
  public void testGetGroupname() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertEquals("Groupname", genericGroup.getGroupname());
  }

  /**
   * Test {@link AbstractGroup#getName()}.
   * <p>
   * Method under test: {@link AbstractGroup#getName()}
   */
  @Test
  public void testGetName() {
    // Arrange
    MemoryUserDatabase memoryUserDatabase = new MemoryUserDatabase();
    GenericGroup<UserDatabase> genericGroup = new GenericGroup<>(memoryUserDatabase, "Groupname",
        "The characteristics of someone or something", new ArrayList<>());

    // Act and Assert
    assertEquals("Groupname", genericGroup.getName());
  }
}
