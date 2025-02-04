package org.apache.catalina.mbeans;

import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class MBeanFactoryDiffblueTest {
  /**
   * Test {@link MBeanFactory#createAjpConnector(String, String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createAjpConnector(String, String, int)}
   */
  @Test
  public void testCreateAjpConnector_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createAjpConnector("", "42 Main St", 8080));
  }

  /**
   * Test {@link MBeanFactory#createDataSourceRealm(String, String, String, String, String, String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createDataSourceRealm(String, String, String, String, String, String, String)}
   */
  @Test
  public void testCreateDataSourceRealm_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createDataSourceRealm("", "Data Source Name",
        "Role Name Col", "User Cred Col", "janedoe", "User Role Table", "User Table"));
  }

  /**
   * Test {@link MBeanFactory#createHttpConnector(String, String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createHttpConnector(String, String, int)}
   */
  @Test
  public void testCreateHttpConnector_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class,
        () -> (new MBeanFactory()).createHttpConnector("", "https://example.org/example", 8080));
  }

  /**
   * Test {@link MBeanFactory#createHttpsConnector(String, String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createHttpsConnector(String, String, int)}
   */
  @Test
  public void testCreateHttpsConnector_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class,
        () -> (new MBeanFactory()).createHttpsConnector("", "https://example.org/example", 8080));
  }

  /**
   * Test {@link MBeanFactory#createJNDIRealm(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createJNDIRealm(String)}
   */
  @Test
  public void testCreateJNDIRealm_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createJNDIRealm(""));
  }

  /**
   * Test {@link MBeanFactory#createMemoryRealm(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createMemoryRealm(String)}
   */
  @Test
  public void testCreateMemoryRealm_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createMemoryRealm(""));
  }

  /**
   * Test {@link MBeanFactory#createStandardContext(String, String, String, boolean, boolean)} with {@code parent}, {@code path}, {@code docBase}, {@code xmlValidation}, {@code xmlNamespaceAware}.
   * <p>
   * Method under test: {@link MBeanFactory#createStandardContext(String, String, String, boolean, boolean)}
   */
  @Test
  public void testCreateStandardContextWithParentPathDocBaseXmlValidationXmlNamespaceAware() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createStandardContext("", "Path", "Doc Base", true, true));
  }

  /**
   * Test {@link MBeanFactory#createStandardContext(String, String, String)} with {@code parent}, {@code path}, {@code docBase}.
   * <ul>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createStandardContext(String, String, String)}
   */
  @Test
  public void testCreateStandardContextWithParentPathDocBase_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createStandardContext("", "Path", "Doc Base"));
  }

  /**
   * Test {@link MBeanFactory#createStandardHost(String, String, String, boolean, boolean, boolean, boolean)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createStandardHost(String, String, String, boolean, boolean, boolean, boolean)}
   */
  @Test
  public void testCreateStandardHost_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class,
        () -> (new MBeanFactory()).createStandardHost("", "Name", "App Base", true, true, true, true));
  }

  /**
   * Test {@link MBeanFactory#createStandardServiceEngine(String, String, String)}.
   * <p>
   * Method under test: {@link MBeanFactory#createStandardServiceEngine(String, String, String)}
   */
  @Test
  public void testCreateStandardServiceEngine() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class,
        () -> (new MBeanFactory()).createStandardServiceEngine("Domain", "localhost", "Base Dir"));
  }

  /**
   * Test {@link MBeanFactory#createStandardManager(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createStandardManager(String)}
   */
  @Test
  public void testCreateStandardManager_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createStandardManager(""));
  }

  /**
   * Test {@link MBeanFactory#createUserDatabaseRealm(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createUserDatabaseRealm(String, String)}
   */
  @Test
  public void testCreateUserDatabaseRealm_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createUserDatabaseRealm("", "Resource Name"));
  }

  /**
   * Test {@link MBeanFactory#createValve(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createValve(String, String)}
   */
  @Test
  public void testCreateValve_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createValve("Class Name", ""));
  }

  /**
   * Test {@link MBeanFactory#createWebappLoader(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#createWebappLoader(String)}
   */
  @Test
  public void testCreateWebappLoader_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).createWebappLoader(""));
  }

  /**
   * Test {@link MBeanFactory#removeConnector(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeConnector(String)}
   */
  @Test
  public void testRemoveConnector_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeConnector(""));
  }

  /**
   * Test {@link MBeanFactory#removeContext(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeContext(String)}
   */
  @Test
  public void testRemoveContext_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeContext(""));
  }

  /**
   * Test {@link MBeanFactory#removeHost(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeHost(String)}
   */
  @Test
  public void testRemoveHost_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeHost(""));
  }

  /**
   * Test {@link MBeanFactory#removeLoader(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeLoader(String)}
   */
  @Test
  public void testRemoveLoader_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeLoader(""));
  }

  /**
   * Test {@link MBeanFactory#removeManager(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeManager(String)}
   */
  @Test
  public void testRemoveManager_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeManager(""));
  }

  /**
   * Test {@link MBeanFactory#removeRealm(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeRealm(String)}
   */
  @Test
  public void testRemoveRealm_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeRealm(""));
  }

  /**
   * Test {@link MBeanFactory#removeService(String)}.
   * <p>
   * Method under test: {@link MBeanFactory#removeService(String)}
   */
  @Test
  public void testRemoveService() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeService("Name"));
  }

  /**
   * Test {@link MBeanFactory#removeValve(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MBeanFactory#removeValve(String)}
   */
  @Test
  public void testRemoveValve_whenEmptyString_thenThrowException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(Exception.class, () -> (new MBeanFactory()).removeValve(""));
  }
}
