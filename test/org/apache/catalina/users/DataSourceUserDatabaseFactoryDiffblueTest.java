package org.apache.catalina.users;

import static org.junit.Assert.assertNull;
import java.util.Hashtable;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.LinkRef;
import javax.naming.Name;
import org.apache.naming.SelectorContext;
import org.junit.Test;

public class DataSourceUserDatabaseFactoryDiffblueTest {
  /**
   * Test {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@link LinkRef#LinkRef(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenLinkRefWithFoo() throws Exception {
    // Arrange
    DataSourceUserDatabaseFactory dataSourceUserDatabaseFactory = new DataSourceUserDatabaseFactory();
    LinkRef linkRef = new LinkRef("foo");
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(dataSourceUserDatabaseFactory.getObjectInstance(linkRef, name, nameCtx, new Hashtable<>()));
  }

  /**
   * Test {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenNull() throws Exception {
    // Arrange
    DataSourceUserDatabaseFactory dataSourceUserDatabaseFactory = new DataSourceUserDatabaseFactory();
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(dataSourceUserDatabaseFactory.getObjectInstance(null, name, nameCtx, new Hashtable<>()));
  }

  /**
   * Test {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataSourceUserDatabaseFactory#getObjectInstance(Object, Name, Context, Hashtable)}
   */
  @Test
  public void testGetObjectInstance_whenObj() throws Exception {
    // Arrange
    DataSourceUserDatabaseFactory dataSourceUserDatabaseFactory = new DataSourceUserDatabaseFactory();
    CompositeName name = new CompositeName();
    SelectorContext nameCtx = new SelectorContext(new Hashtable<>());

    // Act and Assert
    assertNull(dataSourceUserDatabaseFactory.getObjectInstance("Obj", name, nameCtx, new Hashtable<>()));
  }
}
