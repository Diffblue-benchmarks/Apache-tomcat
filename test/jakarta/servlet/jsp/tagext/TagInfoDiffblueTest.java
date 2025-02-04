package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import examples.FooTagExtraInfo;
import java.util.Hashtable;
import org.junit.Test;

public class TagInfoDiffblueTest {
  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[])}.
   * <ul>
   *   <li>Then return TagExtraInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[])}
   */
  @Test
  public void testNewTagInfo_thenReturnTagExtraInfoIsNull() {
    // Arrange
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, null, attributeInfo);

    // Assert
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagVariableInfos());
    assertNull(actualTagInfo.getTagExtraInfo());
    assertNull(actualTagInfo.getTagLibrary());
    assertNull(actualTagInfo.getDisplayName());
    assertNull(actualTagInfo.getLargeIcon());
    assertNull(actualTagInfo.getSmallIcon());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    assertFalse(actualTagInfo.hasDynamicAttributes());
    assertSame(attributeInfo, attributes);
  }

  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[])}.
   * <ul>
   *   <li>Then return TagExtraInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[])}
   */
  @Test
  public void testNewTagInfo_thenReturnTagExtraInfoIsNull2() {
    // Arrange
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};
    TagVariableInfo[] tvi = new TagVariableInfo[]{
        new TagVariableInfo("Name Given", "jane.doe@example.org", "Class Name", true, 1)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, null, attributeInfo, "Display Name", "Small Icon", "Large Icon", tvi);

    // Assert
    assertEquals("Display Name", actualTagInfo.getDisplayName());
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Large Icon", actualTagInfo.getLargeIcon());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Small Icon", actualTagInfo.getSmallIcon());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagExtraInfo());
    assertNull(actualTagInfo.getTagLibrary());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    TagVariableInfo[] tagVariableInfos = actualTagInfo.getTagVariableInfos();
    assertEquals(1, tagVariableInfos.length);
    assertFalse(actualTagInfo.hasDynamicAttributes());
    assertSame(attributeInfo, attributes);
    assertSame(tvi, tagVariableInfos);
  }

  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[], boolean)}.
   * <ul>
   *   <li>Then return TagExtraInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[], boolean)}
   */
  @Test
  public void testNewTagInfo_thenReturnTagExtraInfoIsNull3() {
    // Arrange
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};
    TagVariableInfo[] tvi = new TagVariableInfo[]{
        new TagVariableInfo("Name Given", "jane.doe@example.org", "Class Name", true, 1)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, null, attributeInfo, "Display Name", "Small Icon", "Large Icon", tvi, true);

    // Assert
    assertEquals("Display Name", actualTagInfo.getDisplayName());
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Large Icon", actualTagInfo.getLargeIcon());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Small Icon", actualTagInfo.getSmallIcon());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagExtraInfo());
    assertNull(actualTagInfo.getTagLibrary());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    TagVariableInfo[] tagVariableInfos = actualTagInfo.getTagVariableInfos();
    assertEquals(1, tagVariableInfos.length);
    assertTrue(actualTagInfo.hasDynamicAttributes());
    assertSame(attributeInfo, attributes);
    assertSame(tvi, tagVariableInfos);
  }

  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[])}.
   * <ul>
   *   <li>When {@link FooTagExtraInfo} (default constructor).</li>
   *   <li>Then TagExtraInfo return {@link FooTagExtraInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[])}
   */
  @Test
  public void testNewTagInfo_whenFooTagExtraInfo_thenTagExtraInfoReturnFooTagExtraInfo() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, tagExtraInfo, attributeInfo);

    // Assert
    TagExtraInfo tagExtraInfo2 = actualTagInfo.getTagExtraInfo();
    assertTrue(tagExtraInfo2 instanceof FooTagExtraInfo);
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagVariableInfos());
    assertNull(actualTagInfo.getTagLibrary());
    assertNull(actualTagInfo.getDisplayName());
    assertNull(actualTagInfo.getLargeIcon());
    assertNull(actualTagInfo.getSmallIcon());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    assertFalse(actualTagInfo.hasDynamicAttributes());
    assertSame(tagExtraInfo, tagExtraInfo2);
    assertSame(attributeInfo, attributes);
  }

  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[])}.
   * <ul>
   *   <li>When {@link FooTagExtraInfo} (default constructor).</li>
   *   <li>Then TagExtraInfo return {@link FooTagExtraInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[])}
   */
  @Test
  public void testNewTagInfo_whenFooTagExtraInfo_thenTagExtraInfoReturnFooTagExtraInfo2() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};
    TagVariableInfo[] tvi = new TagVariableInfo[]{
        new TagVariableInfo("Name Given", "jane.doe@example.org", "Class Name", true, 1)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, tagExtraInfo, attributeInfo, "Display Name", "Small Icon", "Large Icon", tvi);

    // Assert
    TagExtraInfo tagExtraInfo2 = actualTagInfo.getTagExtraInfo();
    assertTrue(tagExtraInfo2 instanceof FooTagExtraInfo);
    assertEquals("Display Name", actualTagInfo.getDisplayName());
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Large Icon", actualTagInfo.getLargeIcon());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Small Icon", actualTagInfo.getSmallIcon());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagLibrary());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    TagVariableInfo[] tagVariableInfos = actualTagInfo.getTagVariableInfos();
    assertEquals(1, tagVariableInfos.length);
    assertFalse(actualTagInfo.hasDynamicAttributes());
    assertSame(tagExtraInfo, tagExtraInfo2);
    assertSame(attributeInfo, attributes);
    assertSame(tvi, tagVariableInfos);
  }

  /**
   * Test {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[], boolean)}.
   * <ul>
   *   <li>When {@link FooTagExtraInfo} (default constructor).</li>
   *   <li>Then TagExtraInfo return {@link FooTagExtraInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#TagInfo(String, String, String, String, TagLibraryInfo, TagExtraInfo, TagAttributeInfo[], String, String, String, TagVariableInfo[], boolean)}
   */
  @Test
  public void testNewTagInfo_whenFooTagExtraInfo_thenTagExtraInfoReturnFooTagExtraInfo3() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagAttributeInfo[] attributeInfo = new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)};
    TagVariableInfo[] tvi = new TagVariableInfo[]{
        new TagVariableInfo("Name Given", "jane.doe@example.org", "Class Name", true, 1)};

    // Act
    TagInfo actualTagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String",
        null, tagExtraInfo, attributeInfo, "Display Name", "Small Icon", "Large Icon", tvi, true);

    // Assert
    TagExtraInfo tagExtraInfo2 = actualTagInfo.getTagExtraInfo();
    assertTrue(tagExtraInfo2 instanceof FooTagExtraInfo);
    assertEquals("Display Name", actualTagInfo.getDisplayName());
    assertEquals("Info String", actualTagInfo.getInfoString());
    assertEquals("Large Icon", actualTagInfo.getLargeIcon());
    assertEquals("Not all who wander are lost", actualTagInfo.getBodyContent());
    assertEquals("Small Icon", actualTagInfo.getSmallIcon());
    assertEquals("Tag Class Name", actualTagInfo.getTagClassName());
    assertEquals("Tag Name", actualTagInfo.getTagName());
    assertNull(actualTagInfo.getTagLibrary());
    TagAttributeInfo[] attributes = actualTagInfo.getAttributes();
    assertEquals(1, attributes.length);
    TagVariableInfo[] tagVariableInfos = actualTagInfo.getTagVariableInfos();
    assertEquals(1, tagVariableInfos.length);
    assertTrue(actualTagInfo.hasDynamicAttributes());
    assertSame(tagExtraInfo, tagExtraInfo2);
    assertSame(attributeInfo, attributes);
    assertSame(tvi, tagVariableInfos);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagInfo#setTagExtraInfo(TagExtraInfo)}
   *   <li>{@link TagInfo#setTagLibrary(TagLibraryInfo)}
   *   <li>{@link TagInfo#getAttributes()}
   *   <li>{@link TagInfo#getBodyContent()}
   *   <li>{@link TagInfo#getDisplayName()}
   *   <li>{@link TagInfo#getInfoString()}
   *   <li>{@link TagInfo#getLargeIcon()}
   *   <li>{@link TagInfo#getSmallIcon()}
   *   <li>{@link TagInfo#getTagClassName()}
   *   <li>{@link TagInfo#getTagExtraInfo()}
   *   <li>{@link TagInfo#getTagLibrary()}
   *   <li>{@link TagInfo#getTagName()}
   *   <li>{@link TagInfo#getTagVariableInfos()}
   *   <li>{@link TagInfo#hasDynamicAttributes()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagAttributeInfo tagAttributeInfo = new TagAttributeInfo("Name", true, "Type", true);

    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{tagAttributeInfo});
    FooTagExtraInfo tei = new FooTagExtraInfo();

    // Act
    tagInfo.setTagExtraInfo(tei);
    tagInfo.setTagLibrary(null);
    TagAttributeInfo[] actualAttributes = tagInfo.getAttributes();
    String actualBodyContent = tagInfo.getBodyContent();
    String actualDisplayName = tagInfo.getDisplayName();
    String actualInfoString = tagInfo.getInfoString();
    String actualLargeIcon = tagInfo.getLargeIcon();
    String actualSmallIcon = tagInfo.getSmallIcon();
    String actualTagClassName = tagInfo.getTagClassName();
    TagExtraInfo actualTagExtraInfo = tagInfo.getTagExtraInfo();
    TagLibraryInfo actualTagLibrary = tagInfo.getTagLibrary();
    String actualTagName = tagInfo.getTagName();
    TagVariableInfo[] actualTagVariableInfos = tagInfo.getTagVariableInfos();

    // Assert
    assertTrue(actualTagExtraInfo instanceof FooTagExtraInfo);
    assertEquals("Info String", actualInfoString);
    assertEquals("Not all who wander are lost", actualBodyContent);
    assertEquals("Tag Class Name", actualTagClassName);
    assertEquals("Tag Name", actualTagName);
    assertNull(actualTagVariableInfos);
    assertNull(actualTagLibrary);
    assertNull(actualDisplayName);
    assertNull(actualLargeIcon);
    assertNull(actualSmallIcon);
    assertEquals(1, actualAttributes.length);
    assertFalse(tagInfo.hasDynamicAttributes());
    assertSame(tei, actualTagExtraInfo);
    assertSame(tagAttributeInfo, actualAttributes[0]);
  }

  /**
   * Test {@link TagInfo#getVariableInfo(TagData)}.
   * <ul>
   *   <li>Then return first element ClassName is {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#getVariableInfo(TagData)}
   */
  @Test
  public void testGetVariableInfo_thenReturnFirstElementClassNameIsString() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act
    VariableInfo[] actualVariableInfo = tagInfo.getVariableInfo(new TagData(new Hashtable<>()));

    // Assert
    VariableInfo variableInfo = actualVariableInfo[0];
    assertEquals("String", variableInfo.getClassName());
    assertEquals("member", variableInfo.getVarName());
    assertEquals(0, variableInfo.getScope());
    assertEquals(1, actualVariableInfo.length);
    assertTrue(variableInfo.getDeclare());
  }

  /**
   * Test {@link TagInfo#getVariableInfo(TagData)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagInfo#getVariableInfo(TagData)}
   */
  @Test
  public void testGetVariableInfo_thenReturnNull() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();

    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});
    tagInfo.setTagExtraInfo(null);

    // Act and Assert
    assertNull(tagInfo.getVariableInfo(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagInfo#isValid(TagData)}.
   * <p>
   * Method under test: {@link TagInfo#isValid(TagData)}
   */
  @Test
  public void testIsValid() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act and Assert
    assertTrue(tagInfo.isValid(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagInfo#isValid(TagData)}.
   * <p>
   * Method under test: {@link TagInfo#isValid(TagData)}
   */
  @Test
  public void testIsValid2() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();

    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});
    tagInfo.setTagExtraInfo(null);

    // Act and Assert
    assertTrue(tagInfo.isValid(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagInfo#validate(TagData)}.
   * <p>
   * Method under test: {@link TagInfo#validate(TagData)}
   */
  @Test
  public void testValidate() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act and Assert
    assertNull(tagInfo.validate(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagInfo#validate(TagData)}.
   * <p>
   * Method under test: {@link TagInfo#validate(TagData)}
   */
  @Test
  public void testValidate2() {
    // Arrange
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        null, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act and Assert
    assertNull(tagInfo.validate(new TagData(new Hashtable<>())));
  }
}
