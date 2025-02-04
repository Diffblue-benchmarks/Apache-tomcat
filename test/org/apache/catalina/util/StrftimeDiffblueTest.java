package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

public class StrftimeDiffblueTest {
  /**
   * Test {@link Strftime#Strftime(String, Locale)}.
   * <ul>
   *   <li>When {@code Orig Format}.</li>
   *   <li>Then return {@link Strftime#simpleDateFormat} toPattern is {@code 'Orig Format'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#Strftime(String, Locale)}
   */
  @Test
  public void testNewStrftime_whenOrigFormat_thenReturnSimpleDateFormatToPatternIsOrigFormat() {
    // Arrange, Act and Assert
    SimpleDateFormat simpleDateFormat = (new Strftime("Orig Format", Locale.getDefault())).simpleDateFormat;
    assertTrue(simpleDateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(simpleDateFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals("'Orig Format'", simpleDateFormat.toPattern());
  }

  /**
   * Test {@link Strftime#Strftime(String, Locale)}.
   * <ul>
   *   <li>When {@code %}.</li>
   *   <li>Then return {@link Strftime#simpleDateFormat} toPattern is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#Strftime(String, Locale)}
   */
  @Test
  public void testNewStrftime_whenPercentSign_thenReturnSimpleDateFormatToPatternIsEmptyString() {
    // Arrange, Act and Assert
    SimpleDateFormat simpleDateFormat = (new Strftime("%", Locale.getDefault())).simpleDateFormat;
    assertTrue(simpleDateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(simpleDateFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals("", simpleDateFormat.toPattern());
  }

  /**
   * Test {@link Strftime#format(Date)}.
   * <p>
   * Method under test: {@link Strftime#format(Date)}
   */
  @Test
  public void testFormat() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());

    // Act
    String actualFormatResult = strftime
        .format(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    Calendar calendar = strftime.simpleDateFormat.getCalendar();
    assertTrue(calendar instanceof GregorianCalendar);
    assertEquals("Orig Format", actualFormatResult);
    assertEquals(0L, calendar.getTimeInMillis());
    assertEquals(1970, calendar.getWeekYear());
  }

  /**
   * Test {@link Strftime#getTimeZone()}.
   * <p>
   * Method under test: {@link Strftime#getTimeZone()}
   */
  @Test
  public void testGetTimeZone() {
    // Arrange, Act and Assert
    assertEquals(0, (new Strftime("Orig Format", Locale.getDefault())).getTimeZone().getDSTSavings());
  }

  /**
   * Test {@link Strftime#setTimeZone(TimeZone)}.
   * <p>
   * Method under test: {@link Strftime#setTimeZone(TimeZone)}
   */
  @Test
  public void testSetTimeZone() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());
    TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");

    // Act
    strftime.setTimeZone(timeZone);

    // Assert
    SimpleDateFormat simpleDateFormat = strftime.simpleDateFormat;
    Calendar calendar = simpleDateFormat.getCalendar();
    assertTrue(calendar instanceof GregorianCalendar);
    assertSame(timeZone, simpleDateFormat.getTimeZone());
    assertSame(timeZone, calendar.getTimeZone());
    assertSame(timeZone, strftime.getTimeZone());
  }

  /**
   * Test {@link Strftime#convertDateFormat(String)}.
   * <ul>
   *   <li>When {@code %42}.</li>
   *   <li>Then return {@code '%4''2'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#convertDateFormat(String)}
   */
  @Test
  public void testConvertDateFormat_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("'%4''2'", (new Strftime("Orig Format", Locale.getDefault())).convertDateFormat("%42"));
  }

  /**
   * Test {@link Strftime#convertDateFormat(String)}.
   * <ul>
   *   <li>When {@code %Pattern}.</li>
   *   <li>Then return {@code a'attern'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#convertDateFormat(String)}
   */
  @Test
  public void testConvertDateFormat_whenPattern_thenReturnAAttern() {
    // Arrange, Act and Assert
    assertEquals("a'attern'", (new Strftime("Orig Format", Locale.getDefault())).convertDateFormat("%Pattern"));
  }

  /**
   * Test {@link Strftime#convertDateFormat(String)}.
   * <ul>
   *   <li>When {@code Pattern}.</li>
   *   <li>Then return {@code 'Pattern'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#convertDateFormat(String)}
   */
  @Test
  public void testConvertDateFormat_whenPattern_thenReturnPattern() {
    // Arrange, Act and Assert
    assertEquals("'Pattern'", (new Strftime("Orig Format", Locale.getDefault())).convertDateFormat("Pattern"));
  }

  /**
   * Test {@link Strftime#convertDateFormat(String)}.
   * <ul>
   *   <li>When {@code %%}.</li>
   *   <li>Then return {@code %}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#convertDateFormat(String)}
   */
  @Test
  public void testConvertDateFormat_whenPercentSignPercentSign_thenReturnPercentSign() {
    // Arrange, Act and Assert
    assertEquals("%", (new Strftime("Orig Format", Locale.getDefault())).convertDateFormat("%%"));
  }

  /**
   * Test {@link Strftime#convertDateFormat(String)}.
   * <ul>
   *   <li>When {@code %}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#convertDateFormat(String)}
   */
  @Test
  public void testConvertDateFormat_whenPercentSign_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new Strftime("Orig Format", Locale.getDefault())).convertDateFormat("%"));
  }

  /**
   * Test {@link Strftime#quote(String, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code 'Str'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#quote(String, boolean)}
   */
  @Test
  public void testQuote_whenFalse_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("'Str'", (new Strftime("Orig Format", Locale.getDefault())).quote("Str", false));
  }

  /**
   * Test {@link Strftime#quote(String, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#quote(String, boolean)}
   */
  @Test
  public void testQuote_whenTrue_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("Str", (new Strftime("Orig Format", Locale.getDefault())).quote("Str", true));
  }

  /**
   * Test {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo%2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}
   */
  @Test
  public void testTranslateCommand_when42_thenStringBuilderWithFooToStringIsFoo2() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());
    StringBuilder buf = new StringBuilder("foo");

    // Act
    boolean actualTranslateCommandResult = strftime.translateCommand(buf, "42", 1, true);

    // Assert
    assertEquals("foo%2", buf.toString());
    assertTrue(actualTranslateCommandResult);
  }

  /**
   * Test {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo'%2'}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}
   */
  @Test
  public void testTranslateCommand_whenFalse_thenStringBuilderWithFooToStringIsFoo2() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());
    StringBuilder buf = new StringBuilder("foo");

    // Act
    boolean actualTranslateCommandResult = strftime.translateCommand(buf, "42", 1, false);

    // Assert
    assertEquals("foo'%2'", buf.toString());
    assertFalse(actualTranslateCommandResult);
  }

  /**
   * Test {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code fooEEE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}
   */
  @Test
  public void testTranslateCommand_whenFalse_thenStringBuilderWithFooToStringIsFooEEE() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());
    StringBuilder buf = new StringBuilder("foo");

    // Act
    boolean actualTranslateCommandResult = strftime.translateCommand(buf, "Pattern", 1, false);

    // Assert
    assertEquals("fooEEE", buf.toString());
    assertFalse(actualTranslateCommandResult);
  }

  /**
   * Test {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}.
   * <ul>
   *   <li>When {@code Pattern}.</li>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo'EEE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Strftime#translateCommand(StringBuilder, String, int, boolean)}
   */
  @Test
  public void testTranslateCommand_whenPattern_thenStringBuilderWithFooToStringIsFooEee() {
    // Arrange
    Strftime strftime = new Strftime("Orig Format", Locale.getDefault());
    StringBuilder buf = new StringBuilder("foo");

    // Act
    boolean actualTranslateCommandResult = strftime.translateCommand(buf, "Pattern", 1, true);

    // Assert
    assertEquals("foo'EEE", buf.toString());
    assertFalse(actualTranslateCommandResult);
  }
}
