package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletSecurityElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.FailedContext;
import org.junit.Test;

public class ApplicationServletRegistrationDiffblueTest {
  /**
   * Test {@link ApplicationServletRegistration#ApplicationServletRegistration(Wrapper, Context)}.
   * <p>
   * Method under test: {@link ApplicationServletRegistration#ApplicationServletRegistration(Wrapper, Context)}
   */
  @Test
  public void testNewApplicationServletRegistration() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act
    ApplicationServletRegistration actualApplicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    // Assert
    Collection<String> mappings = actualApplicationServletRegistration.getMappings();
    assertTrue(mappings instanceof Set);
    assertNull(actualApplicationServletRegistration.getClassName());
    assertNull(actualApplicationServletRegistration.getName());
    assertNull(actualApplicationServletRegistration.getRunAsRole());
    assertTrue(mappings.isEmpty());
    assertTrue(actualApplicationServletRegistration.getInitParameters().isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#getClassName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getClassName()}
   */
  @Test
  public void testGetClassName_thenReturnNull() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertNull((new ApplicationServletRegistration(wrapper, new StandardContext())).getClassName());
  }

  /**
   * Test {@link ApplicationServletRegistration#getInitParameter(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getInitParameter(String)}
   */
  @Test
  public void testGetInitParameter_thenReturnNull() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertNull((new ApplicationServletRegistration(wrapper, new StandardContext())).getInitParameter("Name"));
  }

  /**
   * Test {@link ApplicationServletRegistration#getInitParameters()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getInitParameters()}
   */
  @Test
  public void testGetInitParameters_thenReturnEmpty() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertTrue((new ApplicationServletRegistration(wrapper, new StandardContext())).getInitParameters().isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#getInitParameters()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getInitParameters()}
   */
  @Test
  public void testGetInitParameters_thenReturnSizeIsOne() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    wrapper.addInitParameter("Name", "42");

    // Act
    Map<String, String> actualInitParameters = (new ApplicationServletRegistration(wrapper, new StandardContext()))
        .getInitParameters();

    // Assert
    assertEquals(1, actualInitParameters.size());
    assertEquals("42", actualInitParameters.get("Name"));
  }

  /**
   * Test {@link ApplicationServletRegistration#getName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getName()}
   */
  @Test
  public void testGetName_thenReturnNull() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertNull((new ApplicationServletRegistration(wrapper, new StandardContext())).getName());
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_thenReturnFalse() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    wrapper.addInitParameter("Name", "42");
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    // Act
    boolean actualSetInitParameterResult = applicationServletRegistration.setInitParameter("Name", "42");

    // Assert
    Map<String, String> initParameters = applicationServletRegistration.getInitParameters();
    assertEquals(1, initParameters.size());
    assertEquals("42", initParameters.get("Name"));
    assertFalse(actualSetInitParameterResult);
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_when42_thenReturnTrue() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    // Act
    boolean actualSetInitParameterResult = applicationServletRegistration.setInitParameter("Name", "42");

    // Assert
    Map<String, String> initParameters = applicationServletRegistration.getInitParameters();
    assertEquals(1, initParameters.size());
    assertEquals("42", initParameters.get("Name"));
    assertTrue(actualSetInitParameterResult);
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ApplicationServletRegistration(wrapper, new StandardContext())).setInitParameter(null, null));
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ApplicationServletRegistration(wrapper, new StandardContext())).setInitParameter("Name", null));
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameters(Map)}.
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put("foo", "foo");

    // Act
    applicationServletRegistration.setInitParameters(initParameters);

    // Assert
    Map<String, String> initParameters2 = applicationServletRegistration.getInitParameters();
    assertEquals(1, initParameters2.size());
    assertEquals("foo", initParameters2.get("foo"));
    assertEquals(1, initParameters.size());
    assertTrue(initParameters.containsKey("foo"));
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameters(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters_whenHashMapFooIsNull_thenThrowIllegalArgumentException() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put("foo", null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> applicationServletRegistration.setInitParameters(initParameters));
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameters(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code null} is {@code foo}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters_whenHashMapNullIsFoo_thenThrowIllegalArgumentException() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put(null, "foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> applicationServletRegistration.setInitParameters(initParameters));
  }

  /**
   * Test {@link ApplicationServletRegistration#setInitParameters(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link HashMap#HashMap()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters_whenHashMap_thenHashMapEmpty() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());
    HashMap<String, String> initParameters = new HashMap<>();

    // Act
    applicationServletRegistration.setInitParameters(initParameters);

    // Assert that nothing has changed
    assertTrue(initParameters.isEmpty());
    assertTrue(applicationServletRegistration.getInitParameters().isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#setRunAsRole(String)}.
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setRunAsRole(String)}
   */
  @Test
  public void testSetRunAsRole() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    // Act
    applicationServletRegistration.setRunAsRole("Role Name");

    // Assert
    assertEquals("Role Name", applicationServletRegistration.getRunAsRole());
  }

  /**
   * Test {@link ApplicationServletRegistration#setServletSecurity(ServletSecurityElement)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setServletSecurity(ServletSecurityElement)}
   */
  @Test
  public void testSetServletSecurity_thenThrowIllegalArgumentException() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ApplicationServletRegistration(wrapper, new StandardContext())).setServletSecurity(null));
  }

  /**
   * Test {@link ApplicationServletRegistration#setServletSecurity(ServletSecurityElement)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#setServletSecurity(ServletSecurityElement)}
   */
  @Test
  public void testSetServletSecurity_thenThrowIllegalStateException() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();
    ApplicationServletRegistration applicationServletRegistration = new ApplicationServletRegistration(wrapper,
        new StandardContext());

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> applicationServletRegistration.setServletSecurity(new ServletSecurityElement()));
  }

  /**
   * Test {@link ApplicationServletRegistration#addMapping(String[])}.
   * <p>
   * Method under test: {@link ApplicationServletRegistration#addMapping(String[])}
   */
  @Test
  public void testAddMapping() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertTrue(
        (new ApplicationServletRegistration(wrapper, new FailedContext())).addMapping("https://example.org/example")
            .isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#addMapping(String[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#addMapping(String[])}
   */
  @Test
  public void testAddMapping_whenNull_thenReturnEmpty() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertTrue((new ApplicationServletRegistration(wrapper, new StandardContext())).addMapping(null).isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#getMappings()}.
   * <ul>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getMappings()}
   */
  @Test
  public void testGetMappings_thenReturnSet() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act
    Collection<String> actualMappings = (new ApplicationServletRegistration(wrapper, new StandardContext()))
        .getMappings();

    // Assert
    assertTrue(actualMappings instanceof Set);
    assertTrue(actualMappings.isEmpty());
  }

  /**
   * Test {@link ApplicationServletRegistration#getRunAsRole()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationServletRegistration#getRunAsRole()}
   */
  @Test
  public void testGetRunAsRole_thenReturnNull() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act and Assert
    assertNull((new ApplicationServletRegistration(wrapper, new StandardContext())).getRunAsRole());
  }
}
