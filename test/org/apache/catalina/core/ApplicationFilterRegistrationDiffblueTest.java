package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.junit.Test;

public class ApplicationFilterRegistrationDiffblueTest {
  /**
   * Test {@link ApplicationFilterRegistration#ApplicationFilterRegistration(FilterDef, Context)}.
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#ApplicationFilterRegistration(FilterDef, Context)}
   */
  @Test
  public void testNewApplicationFilterRegistration() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act
    ApplicationFilterRegistration actualApplicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    // Assert
    Collection<String> servletNameMappings = actualApplicationFilterRegistration.getServletNameMappings();
    assertTrue(servletNameMappings instanceof Set);
    Collection<String> urlPatternMappings = actualApplicationFilterRegistration.getUrlPatternMappings();
    assertTrue(urlPatternMappings instanceof Set);
    assertNull(actualApplicationFilterRegistration.getClassName());
    assertNull(actualApplicationFilterRegistration.getName());
    assertTrue(servletNameMappings.isEmpty());
    assertTrue(urlPatternMappings.isEmpty());
    assertTrue(actualApplicationFilterRegistration.getInitParameters().isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#getServletNameMappings()}.
   * <ul>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getServletNameMappings()}
   */
  @Test
  public void testGetServletNameMappings_thenReturnSet() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act
    Collection<String> actualServletNameMappings = (new ApplicationFilterRegistration(filterDef, new StandardContext()))
        .getServletNameMappings();

    // Assert
    assertTrue(actualServletNameMappings instanceof Set);
    assertTrue(actualServletNameMappings.isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#getUrlPatternMappings()}.
   * <ul>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getUrlPatternMappings()}
   */
  @Test
  public void testGetUrlPatternMappings_thenReturnSet() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act
    Collection<String> actualUrlPatternMappings = (new ApplicationFilterRegistration(filterDef, new StandardContext()))
        .getUrlPatternMappings();

    // Assert
    assertTrue(actualUrlPatternMappings instanceof Set);
    assertTrue(actualUrlPatternMappings.isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#getClassName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getClassName()}
   */
  @Test
  public void testGetClassName_thenReturnNull() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertNull((new ApplicationFilterRegistration(filterDef, new StandardContext())).getClassName());
  }

  /**
   * Test {@link ApplicationFilterRegistration#getInitParameter(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getInitParameter(String)}
   */
  @Test
  public void testGetInitParameter_thenReturnNull() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertNull((new ApplicationFilterRegistration(filterDef, new StandardContext())).getInitParameter("Name"));
  }

  /**
   * Test {@link ApplicationFilterRegistration#getInitParameters()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getInitParameters()}
   */
  @Test
  public void testGetInitParameters_thenReturnEmpty() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertTrue((new ApplicationFilterRegistration(filterDef, new StandardContext())).getInitParameters().isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#getName()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#getName()}
   */
  @Test
  public void testGetName_thenReturnNull() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertNull((new ApplicationFilterRegistration(filterDef, new StandardContext())).getName());
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameter(String, String)}.
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    // Act
    boolean actualSetInitParameterResult = applicationFilterRegistration.setInitParameter("Name", "42");

    // Assert
    Map<String, String> initParameters = applicationFilterRegistration.getInitParameters();
    assertEquals(1, initParameters.size());
    assertEquals("42", initParameters.get("Name"));
    assertTrue(actualSetInitParameterResult);
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameter(String, String)}.
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter2() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    filterDef.addInitParameter("Name", null);
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    // Act
    boolean actualSetInitParameterResult = applicationFilterRegistration.setInitParameter("Name", "42");

    // Assert
    Map<String, String> initParameters = applicationFilterRegistration.getInitParameters();
    assertEquals(1, initParameters.size());
    assertNull(initParameters.get("Name"));
    assertTrue(actualSetInitParameterResult);
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>Given {@link FilterDef} (default constructor) addInitParameter {@code Name} and {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_givenFilterDefAddInitParameterNameAnd42_thenReturnFalse() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    filterDef.addInitParameter("Name", "42");
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    // Act
    boolean actualSetInitParameterResult = applicationFilterRegistration.setInitParameter("Name", "42");

    // Assert
    Map<String, String> initParameters = applicationFilterRegistration.getInitParameters();
    assertEquals(1, initParameters.size());
    assertEquals("42", initParameters.get("Name"));
    assertFalse(actualSetInitParameterResult);
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ApplicationFilterRegistration(filterDef, new StandardContext())).setInitParameter(null, "42"));
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameter(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameter(String, String)}
   */
  @Test
  public void testSetInitParameter_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange
    FilterDef filterDef = new FilterDef();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ApplicationFilterRegistration(filterDef, new StandardContext())).setInitParameter("Name", null));
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameters(Map)}.
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    // Act
    Set<String> actualSetInitParametersResult = applicationFilterRegistration.setInitParameters(new HashMap<>());

    // Assert
    assertTrue(applicationFilterRegistration.getInitParameters().isEmpty());
    assertTrue(actualSetInitParametersResult.isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameters(Map)}.
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters2() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put("foo", "foo");

    // Act
    Set<String> actualSetInitParametersResult = applicationFilterRegistration.setInitParameters(initParameters);

    // Assert
    Map<String, String> initParameters2 = applicationFilterRegistration.getInitParameters();
    assertEquals(1, initParameters2.size());
    assertEquals("foo", initParameters2.get("foo"));
    assertTrue(actualSetInitParametersResult.isEmpty());
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameters(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters_whenHashMapFooIsNull_thenThrowIllegalArgumentException() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put("foo", null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> applicationFilterRegistration.setInitParameters(initParameters));
  }

  /**
   * Test {@link ApplicationFilterRegistration#setInitParameters(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code null} is {@code foo}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationFilterRegistration#setInitParameters(Map)}
   */
  @Test
  public void testSetInitParameters_whenHashMapNullIsFoo_thenThrowIllegalArgumentException() {
    // Arrange
    FilterDef filterDef = new FilterDef();
    ApplicationFilterRegistration applicationFilterRegistration = new ApplicationFilterRegistration(filterDef,
        new StandardContext());

    HashMap<String, String> initParameters = new HashMap<>();
    initParameters.put(null, "foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> applicationFilterRegistration.setInitParameters(initParameters));
  }
}
