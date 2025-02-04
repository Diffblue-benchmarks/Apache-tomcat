package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.Locale;
import org.junit.Test;

public class CharsetMapperDiffblueTest {
  /**
   * Test {@link CharsetMapper#getCharset(Locale)}.
   * <ul>
   *   <li>Given {@link CharsetMapper#CharsetMapper(String)} with name is empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CharsetMapper#getCharset(Locale)}
   */
  @Test
  public void testGetCharset_givenCharsetMapperWithNameIsEmptyString_thenReturnNull() {
    // Arrange
    CharsetMapper charsetMapper = new CharsetMapper("");

    // Act and Assert
    assertNull(charsetMapper.getCharset(Locale.getDefault()));
  }

  /**
   * Test {@link CharsetMapper#getCharset(Locale)}.
   * <ul>
   *   <li>Given {@link CharsetMapper#CharsetMapper()}.</li>
   *   <li>When Default.</li>
   *   <li>Then return {@code ISO-8859-1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CharsetMapper#getCharset(Locale)}
   */
  @Test
  public void testGetCharset_givenCharsetMapper_whenDefault_thenReturnIso88591() {
    // Arrange
    CharsetMapper charsetMapper = new CharsetMapper();

    // Act and Assert
    assertEquals("ISO-8859-1", charsetMapper.getCharset(Locale.getDefault()));
  }
}
