package org.apache.catalina.mbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Hashtable;
import javax.management.DynamicMBean;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import org.apache.catalina.UserDatabase;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.users.DataSourceUserDatabase;
import org.apache.catalina.users.MemoryUserDatabase;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.ContextResourceLink;
import org.junit.Test;

public class MBeanUtilsDiffblueTest {
  /**
   * Test {@link MBeanUtils#createManagedName(Object)}.
   * <p>
   * Method under test: {@link MBeanUtils#createManagedName(Object)}
   */
  @Test
  public void testCreateManagedName() {
    // Arrange, Act and Assert
    assertEquals("String", MBeanUtils.createManagedName("Component"));
  }

  /**
   * Test {@link MBeanUtils#createMBean(UserDatabase)} with {@code userDatabase}.
   * <p>
   * Method under test: {@link MBeanUtils#createMBean(UserDatabase)}
   */
  @Test
  public void testCreateMBeanWithUserDatabase() throws Exception {
    // Arrange
    DataSourceUserDatabase userDatabase = new DataSourceUserDatabase(new BasicDataSource(), null);

    // Act
    DynamicMBean actualCreateMBeanResult = MBeanUtils.createMBean(userDatabase);

    // Assert
    assertTrue(actualCreateMBeanResult instanceof DataSourceUserDatabaseMBean);
    Object managedResource = ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getManagedResource();
    assertTrue(managedResource instanceof DataSourceUserDatabase);
    assertEquals("Catalina:database=null,type=UserDatabase",
        ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getJmxName().getCanonicalName());
    assertEquals("Catalina:type=UserDatabase,database=null",
        ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getObjectName());
    assertNull(((DataSourceUserDatabase) managedResource).getId());
    assertEquals(2, ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getNotificationInfo().length);
    assertSame(userDatabase, managedResource);
  }

  /**
   * Test {@link MBeanUtils#createMBean(UserDatabase)} with {@code userDatabase}.
   * <ul>
   *   <li>Then return ManagedResource Id is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createMBean(UserDatabase)}
   */
  @Test
  public void testCreateMBeanWithUserDatabase_thenReturnManagedResourceIdIs42() throws Exception {
    // Arrange
    DataSourceUserDatabase userDatabase = new DataSourceUserDatabase(new BasicDataSource(), "42");

    // Act
    DynamicMBean actualCreateMBeanResult = MBeanUtils.createMBean(userDatabase);

    // Assert
    assertTrue(actualCreateMBeanResult instanceof DataSourceUserDatabaseMBean);
    Object managedResource = ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getManagedResource();
    assertTrue(managedResource instanceof DataSourceUserDatabase);
    assertEquals("42", ((DataSourceUserDatabase) managedResource).getId());
    ObjectName jmxName = ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getJmxName();
    assertEquals("Catalina:database=42,type=UserDatabase", jmxName.getCanonicalName());
    assertEquals("Catalina:type=UserDatabase,database=42",
        ((DataSourceUserDatabaseMBean) actualCreateMBeanResult).getObjectName());
    assertEquals("database=42,type=UserDatabase", jmxName.getCanonicalKeyPropertyListString());
    assertEquals("type=UserDatabase,database=42", jmxName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = jmxName.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertTrue(keyPropertyList.containsKey("database"));
    assertTrue(keyPropertyList.containsKey("type"));
    assertSame(userDatabase, managedResource);
  }

  /**
   * Test {@link MBeanUtils#createMBean(UserDatabase)} with {@code userDatabase}.
   * <ul>
   *   <li>Then return {@link MemoryUserDatabaseMBean}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createMBean(UserDatabase)}
   */
  @Test
  public void testCreateMBeanWithUserDatabase_thenReturnMemoryUserDatabaseMBean() throws Exception {
    // Arrange
    MemoryUserDatabase userDatabase = new MemoryUserDatabase();

    // Act
    DynamicMBean actualCreateMBeanResult = MBeanUtils.createMBean(userDatabase);

    // Assert
    assertTrue(actualCreateMBeanResult instanceof MemoryUserDatabaseMBean);
    Object managedResource = ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getManagedResource();
    assertTrue(managedResource instanceof MemoryUserDatabase);
    assertEquals("Users:type=UserDatabase,database=null",
        ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getObjectName());
    assertEquals("org.apache.catalina.users.MemoryUserDatabase",
        ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getClassName());
    assertEquals("org.apache.catalina.users.MemoryUserDatabase",
        ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getModelerType());
    assertEquals(0, ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getGroups().length);
    assertEquals(0, ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getRoles().length);
    assertEquals(0, ((MemoryUserDatabaseMBean) actualCreateMBeanResult).getUsers().length);
    assertSame(userDatabase, managedResource);
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextEnvironment)} with {@code domain}, {@code environment}.
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextEnvironment)}
   */
  @Test
  public void testCreateObjectNameWithDomainEnvironment() throws MalformedObjectNameException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.setName("##");

    NamingResourcesImpl resources = new NamingResourcesImpl();
    resources.setContainer(standardContext);

    ContextEnvironment environment = new ContextEnvironment();
    environment.setNamingResources(resources);

    // Act
    ObjectName actualCreateObjectNameResult = MBeanUtils.createObjectName("Domain", environment);

    // Assert
    Hashtable<String, String> keyPropertyList = actualCreateObjectNameResult.getKeyPropertyList();
    assertEquals(5, keyPropertyList.size());
    assertEquals("/", keyPropertyList.get("context"));
    assertEquals("Domain:context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalName());
    assertEquals("context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalKeyPropertyListString());
    assertEquals("type=Environment,resourcetype=Context,host=null,context=/,name=null",
        actualCreateObjectNameResult.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("host"));
    assertTrue(keyPropertyList.containsKey("name"));
    assertTrue(keyPropertyList.containsKey("resourcetype"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextEnvironment)} with {@code domain}, {@code environment}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code ROOT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextEnvironment)}
   */
  @Test
  public void testCreateObjectNameWithDomainEnvironment_givenStandardContextNameIsRoot()
      throws MalformedObjectNameException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.setName("ROOT");

    NamingResourcesImpl resources = new NamingResourcesImpl();
    resources.setContainer(standardContext);

    ContextEnvironment environment = new ContextEnvironment();
    environment.setNamingResources(resources);

    // Act
    ObjectName actualCreateObjectNameResult = MBeanUtils.createObjectName("Domain", environment);

    // Assert
    Hashtable<String, String> keyPropertyList = actualCreateObjectNameResult.getKeyPropertyList();
    assertEquals(5, keyPropertyList.size());
    assertEquals("/", keyPropertyList.get("context"));
    assertEquals("Domain:context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalName());
    assertEquals("context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalKeyPropertyListString());
    assertEquals("type=Environment,resourcetype=Context,host=null,context=/,name=null",
        actualCreateObjectNameResult.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("host"));
    assertTrue(keyPropertyList.containsKey("name"));
    assertTrue(keyPropertyList.containsKey("resourcetype"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextEnvironment)} with {@code domain}, {@code environment}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextEnvironment)}
   */
  @Test
  public void testCreateObjectNameWithDomainEnvironment_givenStandardContextNameIsSlash()
      throws MalformedObjectNameException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.setName("/");

    NamingResourcesImpl resources = new NamingResourcesImpl();
    resources.setContainer(standardContext);

    ContextEnvironment environment = new ContextEnvironment();
    environment.setNamingResources(resources);

    // Act
    ObjectName actualCreateObjectNameResult = MBeanUtils.createObjectName("Domain", environment);

    // Assert
    Hashtable<String, String> keyPropertyList = actualCreateObjectNameResult.getKeyPropertyList();
    assertEquals(5, keyPropertyList.size());
    assertEquals("/", keyPropertyList.get("context"));
    assertEquals("Domain:context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalName());
    assertEquals("context=/,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalKeyPropertyListString());
    assertEquals("type=Environment,resourcetype=Context,host=null,context=/,name=null",
        actualCreateObjectNameResult.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("host"));
    assertTrue(keyPropertyList.containsKey("name"));
    assertTrue(keyPropertyList.containsKey("resourcetype"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextEnvironment)} with {@code domain}, {@code environment}.
   * <ul>
   *   <li>Then return KeyPropertyList {@code context} is {@code /Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextEnvironment)}
   */
  @Test
  public void testCreateObjectNameWithDomainEnvironment_thenReturnKeyPropertyListContextIsName()
      throws MalformedObjectNameException {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setParent(new StandardContext());
    standardContext.setName("Name");

    NamingResourcesImpl resources = new NamingResourcesImpl();
    resources.setContainer(standardContext);

    ContextEnvironment environment = new ContextEnvironment();
    environment.setNamingResources(resources);

    // Act
    ObjectName actualCreateObjectNameResult = MBeanUtils.createObjectName("Domain", environment);

    // Assert
    Hashtable<String, String> keyPropertyList = actualCreateObjectNameResult.getKeyPropertyList();
    assertEquals(5, keyPropertyList.size());
    assertEquals("/Name", keyPropertyList.get("context"));
    assertEquals("Domain:context=/Name,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalName());
    assertEquals("context=/Name,host=null,name=null,resourcetype=Context,type=Environment",
        actualCreateObjectNameResult.getCanonicalKeyPropertyListString());
    assertEquals("type=Environment,resourcetype=Context,host=null,context=/Name,name=null",
        actualCreateObjectNameResult.getKeyPropertyListString());
    assertTrue(keyPropertyList.containsKey("host"));
    assertTrue(keyPropertyList.containsKey("name"));
    assertTrue(keyPropertyList.containsKey("resourcetype"));
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextEnvironment)} with {@code domain}, {@code environment}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextEnvironment)}
   */
  @Test
  public void testCreateObjectNameWithDomainEnvironment_thenReturnNull() throws MalformedObjectNameException {
    // Arrange
    ContextEnvironment environment = new ContextEnvironment();
    environment.setNamingResources(new NamingResourcesImpl());

    // Act and Assert
    assertNull(MBeanUtils.createObjectName("Domain", environment));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextResourceLink)} with {@code domain}, {@code resourceLink}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextResourceLink)}
   */
  @Test
  public void testCreateObjectNameWithDomainResourceLink_thenReturnNull() throws MalformedObjectNameException {
    // Arrange
    ContextResourceLink resourceLink = new ContextResourceLink();
    resourceLink.setNamingResources(new NamingResourcesImpl());
    resourceLink.setName("Name");

    // Act and Assert
    assertNull(MBeanUtils.createObjectName("Domain", resourceLink));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, ContextResource)} with {@code domain}, {@code resource}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, ContextResource)}
   */
  @Test
  public void testCreateObjectNameWithDomainResource_givenNamingResourcesImpl_thenReturnNull()
      throws MalformedObjectNameException {
    // Arrange
    ContextResource resource = new ContextResource();
    resource.setNamingResources(new NamingResourcesImpl());
    resource.setName("Name");

    // Act and Assert
    assertNull(MBeanUtils.createObjectName("Domain", resource));
  }

  /**
   * Test {@link MBeanUtils#createObjectName(String, UserDatabase)} with {@code domain}, {@code userDatabase}.
   * <ul>
   *   <li>Then return {@code Domain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanUtils#createObjectName(String, UserDatabase)}
   */
  @Test
  public void testCreateObjectNameWithDomainUserDatabase_thenReturnDomain() throws MalformedObjectNameException {
    // Arrange and Act
    ObjectName actualCreateObjectNameResult = MBeanUtils.createObjectName("Domain", new MemoryUserDatabase());

    // Assert
    assertEquals("Domain", actualCreateObjectNameResult.getDomain());
    assertEquals("Domain:database=null,type=UserDatabase", actualCreateObjectNameResult.getCanonicalName());
    Hashtable<String, String> keyPropertyList = actualCreateObjectNameResult.getKeyPropertyList();
    assertEquals(2, keyPropertyList.size());
    assertEquals("UserDatabase", keyPropertyList.get("type"));
    assertEquals("database=null,type=UserDatabase", actualCreateObjectNameResult.getCanonicalKeyPropertyListString());
    assertEquals("null", keyPropertyList.get("database"));
    assertEquals("type=UserDatabase,database=null", actualCreateObjectNameResult.getKeyPropertyListString());
    assertFalse(actualCreateObjectNameResult.isDomainPattern());
    assertFalse(actualCreateObjectNameResult.isPattern());
    assertFalse(actualCreateObjectNameResult.isPropertyListPattern());
    assertFalse(actualCreateObjectNameResult.isPropertyPattern());
    assertFalse(actualCreateObjectNameResult.isPropertyValuePattern());
  }
}
