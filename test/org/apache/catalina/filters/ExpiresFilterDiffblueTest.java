package org.apache.catalina.filters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.filters.ExpiresFilter.Duration;
import org.apache.catalina.filters.ExpiresFilter.DurationUnit;
import org.apache.catalina.filters.ExpiresFilter.ExpiresConfiguration;
import org.apache.catalina.filters.ExpiresFilter.StartingPoint;
import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.apache.catalina.valves.FilterValve;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class ExpiresFilterDiffblueTest {
  /**
   * Test {@link ExpiresFilter#commaDelimitedListToIntArray(String)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#commaDelimitedListToIntArray(String)}
   */
  @Test
  public void testCommaDelimitedListToIntArray_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ExpiresFilter.commaDelimitedListToIntArray("Comma Delimited Ints"));
  }

  /**
   * Test {@link ExpiresFilter#commaDelimitedListToIntArray(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return array of {@code int} with forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#commaDelimitedListToIntArray(String)}
   */
  @Test
  public void testCommaDelimitedListToIntArray_when42_thenReturnArrayOfIntWithFortyTwo() {
    // Arrange, Act and Assert
    assertArrayEquals(new int[]{42}, ExpiresFilter.commaDelimitedListToIntArray("42"));
  }

  /**
   * Test {@link ExpiresFilter#commaDelimitedListToIntArray(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#commaDelimitedListToIntArray(String)}
   */
  @Test
  public void testCommaDelimitedListToIntArray_whenDefault_allowed_origins() {
    // Arrange, Act and Assert
    assertArrayEquals(new int[]{}, ExpiresFilter.commaDelimitedListToIntArray(CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link ExpiresFilter#commaDelimitedListToIntArray(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return empty array of {@code int}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#commaDelimitedListToIntArray(String)}
   */
  @Test
  public void testCommaDelimitedListToIntArray_whenNull_thenReturnEmptyArrayOfInt() {
    // Arrange, Act and Assert
    assertArrayEquals(new int[]{}, ExpiresFilter.commaDelimitedListToIntArray(null));
  }

  /**
   * Test {@link ExpiresFilter#contains(String, String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#contains(String, String)}
   */
  @Test
  public void testContains_whenDefault_allowed_origins_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExpiresFilter.contains("Str", CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link ExpiresFilter#contains(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#contains(String, String)}
   */
  @Test
  public void testContains_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.contains(null, null));
  }

  /**
   * Test {@link ExpiresFilter#contains(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#contains(String, String)}
   */
  @Test
  public void testContains_whenNull_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.contains("Str", null));
  }

  /**
   * Test {@link ExpiresFilter#contains(String, String)}.
   * <ul>
   *   <li>When {@code Search Str}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#contains(String, String)}
   */
  @Test
  public void testContains_whenSearchStr_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.contains("Str", "Search Str"));
  }

  /**
   * Test Duration getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Duration#Duration(int, DurationUnit)}
   *   <li>{@link Duration#toString()}
   *   <li>{@link Duration#getAmount()}
   *   <li>{@link Duration#getUnit()}
   * </ul>
   */
  @Test
  public void testDurationGettersAndSetters() {
    // Arrange and Act
    Duration actualDuration = new Duration(10, DurationUnit.DAY);
    String actualToStringResult = actualDuration.toString();
    int actualAmount = actualDuration.getAmount();

    // Assert
    assertEquals("10 DAY", actualToStringResult);
    assertEquals(10, actualAmount);
    assertEquals(DurationUnit.DAY, actualDuration.getUnit());
  }

  /**
   * Test DurationUnit {@link DurationUnit#getCalendardField()}.
   * <p>
   * Method under test: {@link DurationUnit#getCalendardField()}
   */
  @Test
  public void testDurationUnitGetCalendardField() {
    // Arrange, Act and Assert
    assertEquals(6, DurationUnit.valueOf("DAY").getCalendardField());
  }

  /**
   * Test ExpiresConfiguration getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ExpiresConfiguration#ExpiresConfiguration(StartingPoint, List)}
   *   <li>{@link ExpiresConfiguration#toString()}
   *   <li>{@link ExpiresConfiguration#getDurations()}
   *   <li>{@link ExpiresConfiguration#getStartingPoint()}
   * </ul>
   */
  @Test
  public void testExpiresConfigurationGettersAndSetters() {
    // Arrange
    ArrayList<Duration> durations = new ArrayList<>();

    // Act
    ExpiresConfiguration actualExpiresConfiguration = new ExpiresConfiguration(StartingPoint.ACCESS_TIME, durations);
    String actualToStringResult = actualExpiresConfiguration.toString();
    List<Duration> actualDurations = actualExpiresConfiguration.getDurations();

    // Assert
    assertEquals("ExpiresConfiguration[startingPoint=ACCESS_TIME, duration=[]]", actualToStringResult);
    assertEquals(StartingPoint.ACCESS_TIME, actualExpiresConfiguration.getStartingPoint());
    assertTrue(actualDurations.isEmpty());
    assertSame(durations, actualDurations);
  }

  /**
   * Test {@link ExpiresFilter#intsToCommaDelimitedString(int[])}.
   * <ul>
   *   <li>When array of {@code int} with one and minus one.</li>
   *   <li>Then return {@code 1, -1, 1, -1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#intsToCommaDelimitedString(int[])}
   */
  @Test
  public void testIntsToCommaDelimitedString_whenArrayOfIntWithOneAndMinusOne_thenReturn1111() {
    // Arrange, Act and Assert
    assertEquals("1, -1, 1, -1", ExpiresFilter.intsToCommaDelimitedString(new int[]{1, -1, 1, -1}));
  }

  /**
   * Test {@link ExpiresFilter#intsToCommaDelimitedString(int[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#intsToCommaDelimitedString(int[])}
   */
  @Test
  public void testIntsToCommaDelimitedString_whenNull_thenReturnDefault_allowed_origins() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, ExpiresFilter.intsToCommaDelimitedString(null));
  }

  /**
   * Test {@link ExpiresFilter#isEmpty(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isEmpty(String)}
   */
  @Test
  public void testIsEmpty_whenDefault_allowed_origins_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExpiresFilter.isEmpty(CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link ExpiresFilter#isEmpty(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isEmpty(String)}
   */
  @Test
  public void testIsEmpty_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExpiresFilter.isEmpty(null));
  }

  /**
   * Test {@link ExpiresFilter#isEmpty(String)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isEmpty(String)}
   */
  @Test
  public void testIsEmpty_whenStr_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.isEmpty("Str"));
  }

  /**
   * Test {@link ExpiresFilter#isNotEmpty(String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isNotEmpty(String)}
   */
  @Test
  public void testIsNotEmpty_whenDefault_allowed_origins_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.isNotEmpty(CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link ExpiresFilter#isNotEmpty(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isNotEmpty(String)}
   */
  @Test
  public void testIsNotEmpty_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.isNotEmpty(null));
  }

  /**
   * Test {@link ExpiresFilter#isNotEmpty(String)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#isNotEmpty(String)}
   */
  @Test
  public void testIsNotEmpty_whenStr_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExpiresFilter.isNotEmpty("Str"));
  }

  /**
   * Test {@link ExpiresFilter#startsWithIgnoreCase(String, String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#startsWithIgnoreCase(String, String)}
   */
  @Test
  public void testStartsWithIgnoreCase_whenDefault_allowed_origins_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.startsWithIgnoreCase(CorsFilter.DEFAULT_ALLOWED_ORIGINS, "Prefix"));
  }

  /**
   * Test {@link ExpiresFilter#startsWithIgnoreCase(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#startsWithIgnoreCase(String, String)}
   */
  @Test
  public void testStartsWithIgnoreCase_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.startsWithIgnoreCase(null, "Prefix"));
  }

  /**
   * Test {@link ExpiresFilter#startsWithIgnoreCase(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#startsWithIgnoreCase(String, String)}
   */
  @Test
  public void testStartsWithIgnoreCase_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExpiresFilter.startsWithIgnoreCase(null, null));
  }

  /**
   * Test {@link ExpiresFilter#startsWithIgnoreCase(String, String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#startsWithIgnoreCase(String, String)}
   */
  @Test
  public void testStartsWithIgnoreCase_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.startsWithIgnoreCase("String", "Prefix"));
  }

  /**
   * Test {@link ExpiresFilter#startsWithIgnoreCase(String, String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#startsWithIgnoreCase(String, String)}
   */
  @Test
  public void testStartsWithIgnoreCase_whenString_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(ExpiresFilter.startsWithIgnoreCase("String", null));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_thenReturnDefault_allowed_origins() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS,
        ExpiresFilter.substringBefore("Str", CorsFilter.DEFAULT_ALLOWED_ORIGINS));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_when42_thenReturnDefault_allowed_origins() {
    // Arrange, Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, ExpiresFilter.substringBefore("42", "42"));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>When {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_whenDefault_allowed_origins_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ExpiresFilter.substringBefore(CorsFilter.DEFAULT_ALLOWED_ORIGINS, null));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ExpiresFilter.substringBefore(null, null));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>When {@code Separator}.</li>
   *   <li>Then return {@code Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_whenSeparator_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("Str", ExpiresFilter.substringBefore("Str", "Separator"));
  }

  /**
   * Test {@link ExpiresFilter#substringBefore(String, String)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#substringBefore(String, String)}
   */
  @Test
  public void testSubstringBefore_whenStr_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ExpiresFilter.substringBefore("Str", null));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ExpiresFilter#setDefaultExpiresConfiguration(ExpiresConfiguration)}
   *   <li>{@link ExpiresFilter#setExcludedResponseStatusCodes(int[])}
   *   <li>{@link ExpiresFilter#setExpiresConfigurationByContentType(Map)}
   *   <li>{@link ExpiresFilter#toString()}
   *   <li>{@link ExpiresFilter#getDefaultExpiresConfiguration()}
   *   <li>{@link ExpiresFilter#getExcludedResponseStatusCodesAsInts()}
   *   <li>{@link ExpiresFilter#getExpiresConfigurationByContentType()}
   *   <li>{@link ExpiresFilter#getLogger()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    ExpiresConfiguration defaultExpiresConfiguration = new ExpiresConfiguration(StartingPoint.ACCESS_TIME,
        new ArrayList<>());

    // Act
    expiresFilter.setDefaultExpiresConfiguration(defaultExpiresConfiguration);
    int[] excludedResponseStatusCodes = new int[]{1, -1, 1, -1};
    expiresFilter.setExcludedResponseStatusCodes(excludedResponseStatusCodes);
    HashMap<String, ExpiresConfiguration> expiresConfigurationByContentType = new HashMap<>();
    expiresFilter.setExpiresConfigurationByContentType(expiresConfigurationByContentType);
    String actualToStringResult = expiresFilter.toString();
    ExpiresConfiguration actualDefaultExpiresConfiguration = expiresFilter.getDefaultExpiresConfiguration();
    int[] actualExcludedResponseStatusCodesAsInts = expiresFilter.getExcludedResponseStatusCodesAsInts();
    Map<String, ExpiresConfiguration> actualExpiresConfigurationByContentType = expiresFilter
        .getExpiresConfigurationByContentType();
    expiresFilter.getLogger();

    // Assert
    assertEquals("ExpiresFilter[excludedResponseStatusCode=[1, -1, 1, -1], default=ExpiresConfiguration[startingPoint"
        + "=ACCESS_TIME, duration=[]], byType={}]", actualToStringResult);
    assertTrue(actualExpiresConfigurationByContentType.isEmpty());
    assertSame(expiresConfigurationByContentType, actualExpiresConfigurationByContentType);
    assertSame(defaultExpiresConfiguration, actualDefaultExpiresConfiguration);
    assertSame(excludedResponseStatusCodes, actualExcludedResponseStatusCodesAsInts);
    assertArrayEquals(new int[]{1, -1, 1, -1}, actualExcludedResponseStatusCodesAsInts);
  }

  /**
   * Test {@link ExpiresFilter#getExcludedResponseStatusCodes()}.
   * <ul>
   *   <li>Given {@link ExpiresFilter} (default constructor).</li>
   *   <li>Then return {@code 304}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#getExcludedResponseStatusCodes()}
   */
  @Test
  public void testGetExcludedResponseStatusCodes_givenExpiresFilter_thenReturn304() {
    // Arrange, Act and Assert
    assertEquals("304", (new ExpiresFilter()).getExcludedResponseStatusCodes());
  }

  /**
   * Test {@link ExpiresFilter#getExcludedResponseStatusCodes()}.
   * <ul>
   *   <li>Then return {@code 1, -1, 1, -1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#getExcludedResponseStatusCodes()}
   */
  @Test
  public void testGetExcludedResponseStatusCodes_thenReturn1111() {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    expiresFilter.setExcludedResponseStatusCodes(new int[]{1, -1, 1, -1});

    // Act and Assert
    assertEquals("1, -1, 1, -1", expiresFilter.getExcludedResponseStatusCodes());
  }

  /**
   * Test {@link ExpiresFilter#getExcludedResponseStatusCodes()}.
   * <ul>
   *   <li>Then return {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#getExcludedResponseStatusCodes()}
   */
  @Test
  public void testGetExcludedResponseStatusCodes_thenReturnDefault_allowed_origins() {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    expiresFilter.setExcludedResponseStatusCodes(null);

    // Act and Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, expiresFilter.getExcludedResponseStatusCodes());
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_given42_whenFilterValveAddInitParamExpiresByTypeAnd42() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "42");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code a}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code a}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenA_whenFilterValveAddInitParamExpiresByTypeAndA() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "a");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code access}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code access}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenAccess_whenFilterValveAddInitParamExpiresByTypeAndAccess() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "access");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code m}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code m}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenM_whenFilterValveAddInitParamExpiresByTypeAndM() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "m");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code modification}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenModification() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "modification");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given {@code now}.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code now}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenNow_whenFilterValveAddInitParamExpiresByTypeAndNow() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "now");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Given space.</li>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and space.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_givenSpace_whenFilterValveAddInitParamExpiresByTypeAndSpace() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", " ");
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "ExpiresDefault");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Then {@link ExpiresFilter} (default constructor) ExcludedResponseStatusCodes is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_thenExpiresFilterExcludedResponseStatusCodesIs42() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", "42");
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act
    expiresFilter.init(filterConfig);

    // Assert
    assertEquals("42", expiresFilter.getExcludedResponseStatusCodes());
    assertArrayEquals(new int[]{42}, expiresFilter.getExcludedResponseStatusCodesAsInts());
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Then {@link ExpiresFilter} (default constructor) ExcludedResponseStatusCodes is {@code 304}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_thenExpiresFilterExcludedResponseStatusCodesIs304() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act
    expiresFilter.init(filterConfig);

    // Assert that nothing has changed
    assertEquals("304", expiresFilter.getExcludedResponseStatusCodes());
    assertArrayEquals(new int[]{304}, expiresFilter.getExcludedResponseStatusCodesAsInts());
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>Then {@link ExpiresFilter} (default constructor) ExcludedResponseStatusCodes is {@link CorsFilter#DEFAULT_ALLOWED_ORIGINS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_thenExpiresFilterExcludedResponseStatusCodesIsDefault_allowed_origins() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresExcludedResponseStatusCodes", CorsFilter.DEFAULT_ALLOWED_ORIGINS);
    filterConfig.addInitParam("filterValve.illegalWrapping", "Param Value");

    // Act
    expiresFilter.init(filterConfig);

    // Assert
    assertEquals(CorsFilter.DEFAULT_ALLOWED_ORIGINS, expiresFilter.getExcludedResponseStatusCodes());
    assertArrayEquals(new int[]{}, expiresFilter.getExcludedResponseStatusCodesAsInts());
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresByType} and {@code Param Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_whenFilterValveAddInitParamExpiresByTypeAndParamValue() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresByType", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>When {@link FilterValve} (default constructor) addInitParam {@code ExpiresDefault} and {@code Param Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_whenFilterValveAddInitParamExpiresDefaultAndParamValue() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    FilterValve filterConfig = new FilterValve();
    filterConfig.addInitParam("ExpiresDefault", "Param Value");

    // Act and Assert
    assertThrows(ServletException.class, () -> expiresFilter.init(filterConfig));
  }

  /**
   * Test {@link ExpiresFilter#init(FilterConfig)}.
   * <ul>
   *   <li>When {@link FilterValve} (default constructor).</li>
   *   <li>Then {@link ExpiresFilter} (default constructor) ExcludedResponseStatusCodes is {@code 304}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#init(FilterConfig)}
   */
  @Test
  public void testInit_whenFilterValve_thenExpiresFilterExcludedResponseStatusCodesIs304() throws ServletException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();

    // Act
    expiresFilter.init(new FilterValve());

    // Assert that nothing has changed
    assertEquals("304", expiresFilter.getExcludedResponseStatusCodes());
    assertArrayEquals(new int[]{304}, expiresFilter.getExcludedResponseStatusCodesAsInts());
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code a}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenA() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration("a"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code access}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenAccess() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration("access"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code expiresFilter.startingPointInvalid}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenExpiresFilterStartingPointInvalid() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ExpiresFilter()).parseExpiresConfiguration("expiresFilter.startingPointInvalid"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code Input Line}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenInputLine() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration("Input Line"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code modification}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenModification() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration("modification"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When {@code now}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenNow() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration("now"));
  }

  /**
   * Test {@link ExpiresFilter#parseExpiresConfiguration(String)}.
   * <ul>
   *   <li>When space.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpiresFilter#parseExpiresConfiguration(String)}
   */
  @Test
  public void testParseExpiresConfiguration_whenSpace() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ExpiresFilter()).parseExpiresConfiguration(" "));
  }

  /**
   * Test new {@link ExpiresFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ExpiresFilter}
   */
  @Test
  public void testNewExpiresFilter() {
    // Arrange and Act
    ExpiresFilter actualExpiresFilter = new ExpiresFilter();

    // Assert
    assertEquals("304", actualExpiresFilter.getExcludedResponseStatusCodes());
    assertNull(actualExpiresFilter.getDefaultExpiresConfiguration());
    assertFalse(actualExpiresFilter.isConfigProblemFatal());
    assertTrue(actualExpiresFilter.getExpiresConfigurationByContentType().isEmpty());
    assertArrayEquals(new int[]{304}, actualExpiresFilter.getExcludedResponseStatusCodesAsInts());
  }

  /**
   * Test XHttpServletResponse getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XHttpServletResponse#setWriteResponseBodyStarted(boolean)}
   *   <li>{@link XHttpServletResponse#getCacheControlHeader()}
   *   <li>{@link XHttpServletResponse#getLastModifiedHeader()}
   *   <li>{@link XHttpServletResponse#isLastModifiedHeaderSet()}
   *   <li>{@link XHttpServletResponse#isWriteResponseBodyStarted()}
   * </ul>
   */
  @Test
  public void testXHttpServletResponseGettersAndSetters() {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    Connector connector = new Connector();
    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    XHttpServletResponse xHttpServletResponse = expiresFilter.new XHttpServletResponse(httpServletRequestWrapper,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())));

    // Act
    xHttpServletResponse.setWriteResponseBodyStarted(true);
    String actualCacheControlHeader = xHttpServletResponse.getCacheControlHeader();
    long actualLastModifiedHeader = xHttpServletResponse.getLastModifiedHeader();
    boolean actualIsLastModifiedHeaderSetResult = xHttpServletResponse.isLastModifiedHeaderSet();

    // Assert
    assertNull(actualCacheControlHeader);
    assertEquals(0L, actualLastModifiedHeader);
    assertFalse(actualIsLastModifiedHeaderSetResult);
    assertTrue(xHttpServletResponse.isWriteResponseBodyStarted());
  }

  /**
   * Test XHttpServletResponse {@link XHttpServletResponse#XHttpServletResponse(ExpiresFilter, HttpServletRequest, HttpServletResponse)}.
   * <p>
   * Method under test: {@link XHttpServletResponse#XHttpServletResponse(ExpiresFilter, HttpServletRequest, HttpServletResponse)}
   */
  @Test
  public void testXHttpServletResponseNewXHttpServletResponse() throws IOException {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    Connector connector = new Connector();
    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(
        new org.apache.catalina.connector.Response(new Response()));

    // Act
    XHttpServletResponse actualXHttpServletResponse = expiresFilter.new XHttpServletResponse(httpServletRequestWrapper,
        httpServletResponseWrapper);

    // Assert
    ServletResponse response = actualXHttpServletResponse.getResponse();
    assertTrue(response instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = actualXHttpServletResponse.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(actualXHttpServletResponse.getOutputStream() instanceof XServletOutputStream);
    assertNull(actualXHttpServletResponse.getContentType());
    assertNull(actualXHttpServletResponse.getCacheControlHeader());
    assertNull(actualXHttpServletResponse.getTrailerFields());
    assertEquals(0L, actualXHttpServletResponse.getLastModifiedHeader());
    assertEquals(200, actualXHttpServletResponse.getStatus());
    assertEquals(8192, actualXHttpServletResponse.getBufferSize());
    assertFalse(actualXHttpServletResponse.isCommitted());
    assertFalse(actualXHttpServletResponse.isLastModifiedHeaderSet());
    assertFalse(actualXHttpServletResponse.isWriteResponseBodyStarted());
    assertTrue(headerNames.isEmpty());
    assertSame(httpServletResponseWrapper, response);
  }

  /**
   * Test XServletOutputStream getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link XServletOutputStream#XServletOutputStream(ExpiresFilter, ServletOutputStream, HttpServletRequest, XHttpServletResponse)}
   *   <li>{@link XServletOutputStream#setWriteListener(WriteListener)}
   *   <li>{@link XServletOutputStream#isReady()}
   * </ul>
   */
  @Test
  public void testXServletOutputStreamGettersAndSetters() {
    // Arrange
    ExpiresFilter expiresFilter = new ExpiresFilter();
    ByteArrayServletOutputStream byteArrayServletOutputStream = new ByteArrayServletOutputStream();
    Connector connector = new Connector();
    HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));
    ExpiresFilter expiresFilter2 = new ExpiresFilter();
    HttpServletRequestWrapper httpServletRequestWrapper2 = new HttpServletRequestWrapper(new RequestFacade(null));

    // Act
    XServletOutputStream actualXServletOutputStream = expiresFilter.new XServletOutputStream(
        byteArrayServletOutputStream, httpServletRequestWrapper,
        expiresFilter2.new XHttpServletResponse(httpServletRequestWrapper2,
            new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response()))));
    actualXServletOutputStream.setWriteListener(null);

    // Assert
    assertFalse(actualXServletOutputStream.isReady());
  }
}
