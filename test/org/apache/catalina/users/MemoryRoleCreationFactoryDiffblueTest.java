package org.apache.catalina.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.UserDatabase;
import org.apache.jasper.util.UniqueAttributesImpl;
import org.junit.Test;
import org.xml.sax.Attributes;

public class MemoryRoleCreationFactoryDiffblueTest {
  /**
   * Test {@link MemoryRoleCreationFactory#MemoryRoleCreationFactory(MemoryUserDatabase)}.
   * <p>
   * Method under test: {@link MemoryRoleCreationFactory#MemoryRoleCreationFactory(MemoryUserDatabase)}
   */
  @Test
  public void testNewMemoryRoleCreationFactory() {
    // Arrange, Act and Assert
    assertNull((new MemoryRoleCreationFactory(new MemoryUserDatabase())).getDigester());
  }

  /**
   * Test {@link MemoryRoleCreationFactory#createObject(Attributes)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return {@link GenericRole}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryRoleCreationFactory#createObject(Attributes)}
   */
  @Test
  public void testCreateObject_given42_thenReturnGenericRole() {
    // Arrange
    MemoryUserDatabase database = new MemoryUserDatabase();
    MemoryRoleCreationFactory memoryRoleCreationFactory = new MemoryRoleCreationFactory(database);

    UniqueAttributesImpl attributes = new UniqueAttributesImpl(true);
    attributes.addAttribute("rolename", "rolename", "rolename", "rolename", "42");

    // Act
    Object actualCreateObjectResult = memoryRoleCreationFactory.createObject(attributes);

    // Assert
    assertTrue(actualCreateObjectResult instanceof GenericRole);
    UserDatabase userDatabase = ((GenericRole<UserDatabase>) actualCreateObjectResult).getUserDatabase();
    assertTrue(userDatabase instanceof MemoryUserDatabase);
    assertEquals("42", ((GenericRole<UserDatabase>) actualCreateObjectResult).getName());
    assertEquals("42", ((GenericRole<UserDatabase>) actualCreateObjectResult).getRolename());
    assertNull(((GenericRole<UserDatabase>) actualCreateObjectResult).getDescription());
    assertSame(database, userDatabase);
  }
}
