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

public class MemoryUserCreationFactoryDiffblueTest {
  /**
   * Test {@link MemoryUserCreationFactory#MemoryUserCreationFactory(MemoryUserDatabase)}.
   * <p>
   * Method under test: {@link MemoryUserCreationFactory#MemoryUserCreationFactory(MemoryUserDatabase)}
   */
  @Test
  public void testNewMemoryUserCreationFactory() {
    // Arrange, Act and Assert
    assertNull((new MemoryUserCreationFactory(new MemoryUserDatabase())).getDigester());
  }

  /**
   * Test {@link MemoryUserCreationFactory#createObject(Attributes)}.
   * <ul>
   *   <li>Then return {@link GenericUser}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserCreationFactory#createObject(Attributes)}
   */
  @Test
  public void testCreateObject_thenReturnGenericUser() {
    // Arrange
    MemoryUserDatabase database = new MemoryUserDatabase();
    MemoryUserCreationFactory memoryUserCreationFactory = new MemoryUserCreationFactory(database);

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("username", "username", "username", "username", "42");

    // Act
    Object actualCreateObjectResult = memoryUserCreationFactory.createObject(attributes);

    // Assert
    assertTrue(actualCreateObjectResult instanceof GenericUser);
    UserDatabase userDatabase = ((GenericUser<UserDatabase>) actualCreateObjectResult).getUserDatabase();
    assertTrue(userDatabase instanceof MemoryUserDatabase);
    assertEquals("42", ((GenericUser<UserDatabase>) actualCreateObjectResult).getName());
    assertEquals("42", ((GenericUser<UserDatabase>) actualCreateObjectResult).getUsername());
    assertNull(((GenericUser<UserDatabase>) actualCreateObjectResult).getFullName());
    assertNull(((GenericUser<UserDatabase>) actualCreateObjectResult).getPassword());
    assertFalse(((GenericUser<UserDatabase>) actualCreateObjectResult).getGroups().hasNext());
    assertFalse(((GenericUser<UserDatabase>) actualCreateObjectResult).getRoles().hasNext());
    assertTrue(((GenericUser<UserDatabase>) actualCreateObjectResult).groups.isEmpty());
    assertSame(database, userDatabase);
  }
}
