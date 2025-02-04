package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.UserDatabase;
import org.apache.jasper.util.UniqueAttributesImpl;
import org.junit.Test;
import org.xml.sax.Attributes;

public class MemoryGroupCreationFactoryDiffblueTest {
  /**
   * Test {@link MemoryGroupCreationFactory#MemoryGroupCreationFactory(MemoryUserDatabase)}.
   * <p>
   * Method under test: {@link MemoryGroupCreationFactory#MemoryGroupCreationFactory(MemoryUserDatabase)}
   */
  @Test
  public void testNewMemoryGroupCreationFactory() {
    // Arrange, Act and Assert
    assertNull((new MemoryGroupCreationFactory(new MemoryUserDatabase())).getDigester());
  }

  /**
   * Test {@link MemoryGroupCreationFactory#createObject(Attributes)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return {@link GenericGroup}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryGroupCreationFactory#createObject(Attributes)}
   */
  @Test
  public void testCreateObject_given42_thenReturnGenericGroup() {
    // Arrange
    MemoryUserDatabase database = new MemoryUserDatabase();
    MemoryGroupCreationFactory memoryGroupCreationFactory = new MemoryGroupCreationFactory(database);

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("groupname", "groupname", "groupname", "groupname", "42");

    // Act
    Object actualCreateObjectResult = memoryGroupCreationFactory.createObject(attributes);

    // Assert
    assertTrue(actualCreateObjectResult instanceof GenericGroup);
    UserDatabase userDatabase = ((GenericGroup<UserDatabase>) actualCreateObjectResult).getUserDatabase();
    assertTrue(userDatabase instanceof MemoryUserDatabase);
    assertEquals("42", ((GenericGroup<UserDatabase>) actualCreateObjectResult).getGroupname());
    assertEquals("42", ((GenericGroup<UserDatabase>) actualCreateObjectResult).getName());
    assertNull(((GenericGroup<UserDatabase>) actualCreateObjectResult).getDescription());
    assertFalse(((GenericGroup<UserDatabase>) actualCreateObjectResult).getRoles().hasNext());
    assertFalse(((GenericGroup<UserDatabase>) actualCreateObjectResult).getUsers().hasNext());
    assertTrue(((GenericGroup<UserDatabase>) actualCreateObjectResult).roles.isEmpty());
    assertSame(database, userDatabase);
  }
}
