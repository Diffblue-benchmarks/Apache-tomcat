package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;

public class SSIFlastmodDiffblueTest {
  /**
   * Test {@link SSIFlastmod#formatDate(Date, String)}.
   * <ul>
   *   <li>Then return {@code Config Time Fmt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFlastmod#formatDate(Date, String)}
   */
  @Test
  public void testFormatDate_thenReturnConfigTimeFmt() {
    // Arrange
    SSIFlastmod ssiFlastmod = new SSIFlastmod();

    // Act and Assert
    assertEquals("Config Time Fmt", ssiFlastmod.formatDate(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), "Config Time Fmt"));
  }

  /**
   * Test {@link SSIFlastmod#formatDate(Date, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFlastmod#formatDate(Date, String)}
   */
  @Test
  public void testFormatDate_whenEmptyString_thenReturnEmptyString() {
    // Arrange
    SSIFlastmod ssiFlastmod = new SSIFlastmod();

    // Act and Assert
    assertEquals("", ssiFlastmod
        .formatDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), ""));
  }
}
