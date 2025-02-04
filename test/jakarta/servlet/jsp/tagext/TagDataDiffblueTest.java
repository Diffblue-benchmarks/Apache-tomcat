package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.Hashtable;
import org.junit.Test;

public class TagDataDiffblueTest {
  /**
   * Test {@link TagData#TagData(Hashtable)}.
   * <p>
   * Method under test: {@link TagData#TagData(Hashtable)}
   */
  @Test
  public void testNewTagData() {
    // Arrange, Act and Assert
    assertNull((new TagData(new Hashtable<>())).getId());
  }

  /**
   * Test {@link TagData#TagData(Object[][])}.
   * <ul>
   *   <li>When array of {@link Object} with {@code Atts} and {@code Atts}.</li>
   *   <li>Then return Id is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#TagData(Object[][])}
   */
  @Test
  public void testNewTagData_whenArrayOfObjectWithAttsAndAtts_thenReturnIdIsNull() {
    // Arrange, Act and Assert
    assertNull((new TagData(new Object[][]{new Object[]{"Atts", "Atts"}})).getId());
  }

  /**
   * Test {@link TagData#TagData(Object[][])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Id is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#TagData(Object[][])}
   */
  @Test
  public void testNewTagData_whenNull_thenReturnIdIsNull() {
    // Arrange, Act and Assert
    assertNull((new TagData((Object[][]) null)).getId());
  }

  /**
   * Test {@link TagData#getId()}.
   * <ul>
   *   <li>Given {@link Hashtable#Hashtable()} {@link TagAttributeInfo#ID} is {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#getId()}
   */
  @Test
  public void testGetId_givenHashtableIdIs42_thenReturn42() {
    // Arrange
    Hashtable<String, Object> attrs = new Hashtable<>();
    attrs.put(TagAttributeInfo.ID, "42");

    // Act and Assert
    assertEquals("42", (new TagData(attrs)).getId());
  }

  /**
   * Test {@link TagData#getId()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#getId()}
   */
  @Test
  public void testGetId_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TagData(new Hashtable<>())).getId());
  }

  /**
   * Test {@link TagData#getAttribute(String)}.
   * <p>
   * Method under test: {@link TagData#getAttribute(String)}
   */
  @Test
  public void testGetAttribute() {
    // Arrange, Act and Assert
    assertNull((new TagData(new Hashtable<>())).getAttribute("Att Name"));
  }

  /**
   * Test {@link TagData#getAttributeString(String)}.
   * <ul>
   *   <li>Given {@link Hashtable#Hashtable()} {@code 42} is {@code 42}.</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#getAttributeString(String)}
   */
  @Test
  public void testGetAttributeString_givenHashtable42Is42_when42_thenReturn42() {
    // Arrange
    Hashtable<String, Object> attrs = new Hashtable<>();
    attrs.put("42", "42");

    // Act and Assert
    assertEquals("42", (new TagData(attrs)).getAttributeString("42"));
  }

  /**
   * Test {@link TagData#getAttributeString(String)}.
   * <ul>
   *   <li>When {@code Att Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagData#getAttributeString(String)}
   */
  @Test
  public void testGetAttributeString_whenAttName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TagData(new Hashtable<>())).getAttributeString("Att Name"));
  }
}
