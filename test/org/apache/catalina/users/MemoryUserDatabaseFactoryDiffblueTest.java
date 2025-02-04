package org.apache.catalina.users;

import static org.junit.Assert.assertNull;
import java.util.Hashtable;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.LinkRef;
import javax.naming.Name;
import org.apache.naming.SelectorContext;
import org.junit.Test;

public class MemoryUserDatabaseFactoryDiffblueTest {
  /**
   * Test {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@link LinkRef#LinkRef(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenLinkRefWithFoo() throws Exception {
    // Arrange
    MemoryUserDatabaseFactory memoryUserDatabaseFactory = new MemoryUserDatabaseFactory();
    LinkRef linkRef = new LinkRef("foo");
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(memoryUserDatabaseFactory.getObjectInstance(linkRef, name, nameCtx, new Hashtable<>()));
  }

  /**
   * Test {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenNull() throws Exception {
    // Arrange
    MemoryUserDatabaseFactory memoryUserDatabaseFactory = new MemoryUserDatabaseFactory();
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(memoryUserDatabaseFactory.getObjectInstance(null, name, nameCtx, new Hashtable<>()));
  }

  /**
   * Test {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemoryUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenObj() throws Exception {
    // Arrange
    MemoryUserDatabaseFactory memoryUserDatabaseFactory = new MemoryUserDatabaseFactory();
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(memoryUserDatabaseFactory.getObjectInstance("Obj", name, nameCtx, new Hashtable<>()));
  }
}
