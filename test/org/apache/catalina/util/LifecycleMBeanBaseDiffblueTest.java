package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.util.Hashtable;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.junit.Test;

public class LifecycleMBeanBaseDiffblueTest {
  /**
   * Test {@link LifecycleMBeanBase#initInternal()}.
   * <ul>
   *   <li>Given {@link StandardHost} (default constructor).</li>
   *   <li>Then {@link StandardHost} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#initInternal()}
   */
  @Test
  public void testInitInternal_givenStandardHost_thenStandardHostObjectNameDomainIsCatalina()
      throws LifecycleException {
    // Arrange
    StandardHost standardHost = new StandardHost();

    // Act
    standardHost.initInternal();

    // Assert
    ObjectName objectName = standardHost.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:container0=null,host=null,type=Host", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("Host", keyPropertyList.get("type"));
    assertEquals("container0=null,host=null,type=Host", objectName.getCanonicalKeyPropertyListString());
    assertEquals("null", keyPropertyList.get("container0"));
    assertEquals("null", keyPropertyList.get("host"));
    assertEquals("type=Host,host=null,container0=null", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link LifecycleMBeanBase#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardHostObjectNameDomainIsCatalina() throws LifecycleException {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.addAlias("type=Host");

    // Act
    standardHost.initInternal();

    // Assert
    ObjectName objectName = standardHost.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:container0=null,host=null,type=Host", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("Host", keyPropertyList.get("type"));
    assertEquals("container0=null,host=null,type=Host", objectName.getCanonicalKeyPropertyListString());
    assertEquals("null", keyPropertyList.get("container0"));
    assertEquals("null", keyPropertyList.get("host"));
    assertEquals("type=Host,host=null,container0=null", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link LifecycleMBeanBase#initInternal()}.
   * <ul>
   *   <li>Then {@link StandardHost} (default constructor) ObjectName Domain is {@code type=Host}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#initInternal()}
   */
  @Test
  public void testInitInternal_thenStandardHostObjectNameDomainIsTypeHost() throws LifecycleException {
    // Arrange
    StandardHost standardHost = new StandardHost();
    standardHost.setDomain("type=Host");
    standardHost.addAlias("type=Host");

    // Act
    standardHost.initInternal();

    // Assert
    ObjectName objectName = standardHost.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(3, keyPropertyList.size());
    assertEquals("Host", keyPropertyList.get("type"));
    assertEquals("container0=null,host=null,type=Host", objectName.getCanonicalKeyPropertyListString());
    assertEquals("null", keyPropertyList.get("container0"));
    assertEquals("null", keyPropertyList.get("host"));
    assertEquals("type=Host", objectName.getDomain());
    assertEquals("type=Host,host=null,container0=null", objectName.getKeyPropertyListString());
    assertEquals("type=Host:container0=null,host=null,type=Host", objectName.getCanonicalName());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link LifecycleMBeanBase#setDomain(String)}.
   * <p>
   * Method under test: {@link LifecycleMBeanBase#setDomain(String)}
   */
  @Test
  public void testSetDomain() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();

    // Act
    basicAuthenticator.setDomain("Domain");

    // Assert
    assertEquals("Domain", basicAuthenticator.getDomain());
  }

  /**
   * Test {@link LifecycleMBeanBase#getDomain()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#getDomain()}
   */
  @Test
  public void testGetDomain_givenBasicAuthenticatorContainerIsStandardContext() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setContainer(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", basicAuthenticator.getDomain());
  }

  /**
   * Test {@link LifecycleMBeanBase#getDomain()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Domain is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#getDomain()}
   */
  @Test
  public void testGetDomain_givenBasicAuthenticatorDomainIsFoo_thenReturnFoo() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setDomain("foo");

    // Act and Assert
    assertEquals("foo", basicAuthenticator.getDomain());
  }

  /**
   * Test {@link LifecycleMBeanBase#getDomain()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#getDomain()}
   */
  @Test
  public void testGetDomain_givenBasicAuthenticator_thenReturnCatalina() {
    // Arrange, Act and Assert
    assertEquals("Catalina", (new BasicAuthenticator()).getDomain());
  }

  /**
   * Test {@link LifecycleMBeanBase#getObjectName()}.
   * <p>
   * Method under test: {@link LifecycleMBeanBase#getObjectName()}
   */
  @Test
  public void testGetObjectName() {
    // Arrange, Act and Assert
    assertNull((new BasicAuthenticator()).getObjectName());
  }

  /**
   * Test {@link LifecycleMBeanBase#register(Object, String)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   *   <li>When {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#register(Object, String)}
   */
  @Test
  public void testRegister_givenBasicAuthenticatorContainerIsStandardContext_whenObj() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setContainer(new StandardContext());

    // Act and Assert
    assertNull(basicAuthenticator.register("Obj", "Object Name Key Properties"));
  }

  /**
   * Test {@link LifecycleMBeanBase#register(Object, String)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Domain is {@code foo}.</li>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#register(Object, String)}
   */
  @Test
  public void testRegister_givenBasicAuthenticatorDomainIsFoo_whenNull() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setDomain("foo");

    // Act and Assert
    assertNull(basicAuthenticator.register(null, "Object Name Key Properties"));
  }

  /**
   * Test {@link LifecycleMBeanBase#register(Object, String)}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor).</li>
   *   <li>When {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#register(Object, String)}
   */
  @Test
  public void testRegister_givenBasicAuthenticator_whenObj() {
    // Arrange, Act and Assert
    assertNull((new BasicAuthenticator()).register("Obj", "Object Name Key Properties"));
  }

  /**
   * Test {@link LifecycleMBeanBase#preRegister(MBeanServer, ObjectName)}.
   * <ul>
   *   <li>When Instance is empty string.</li>
   *   <li>Then {@link BasicAuthenticator} (default constructor) Domain is {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LifecycleMBeanBase#preRegister(MBeanServer, ObjectName)}
   */
  @Test
  public void testPreRegister_whenInstanceIsEmptyString_thenBasicAuthenticatorDomainIsAsterisk() throws Exception {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    ObjectName name = ObjectName.getInstance("");

    // Act
    ObjectName actualPreRegisterResult = basicAuthenticator.preRegister(null, name);

    // Assert
    assertEquals("*", basicAuthenticator.getDomain());
    assertSame(name, basicAuthenticator.getObjectName());
    assertSame(name, actualPreRegisterResult);
  }
}
