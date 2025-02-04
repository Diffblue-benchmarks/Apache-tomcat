package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import examples.FooTagExtraInfo;
import java.util.Hashtable;
import org.junit.Test;

public class TagExtraInfoDiffblueTest {
  /**
   * Test {@link TagExtraInfo#getVariableInfo(TagData)}.
   * <p>
   * Method under test: {@link TagExtraInfo#getVariableInfo(TagData)}
   */
  @Test
  public void testGetVariableInfo() {
    // Arrange
    FooTagExtraInfo fooTagExtraInfo = new FooTagExtraInfo();

    // Act
    VariableInfo[] actualVariableInfo = fooTagExtraInfo.getVariableInfo(new TagData(new Hashtable<>()));

    // Assert
    VariableInfo variableInfo = actualVariableInfo[0];
    assertEquals("String", variableInfo.getClassName());
    assertEquals("member", variableInfo.getVarName());
    assertEquals(0, variableInfo.getScope());
    assertEquals(1, actualVariableInfo.length);
    assertTrue(variableInfo.getDeclare());
  }

  /**
   * Test {@link TagExtraInfo#isValid(TagData)}.
   * <p>
   * Method under test: {@link TagExtraInfo#isValid(TagData)}
   */
  @Test
  public void testIsValid() {
    // Arrange
    FooTagExtraInfo fooTagExtraInfo = new FooTagExtraInfo();

    // Act and Assert
    assertTrue(fooTagExtraInfo.isValid(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagExtraInfo#validate(TagData)}.
   * <p>
   * Method under test: {@link TagExtraInfo#validate(TagData)}
   */
  @Test
  public void testValidate() {
    // Arrange
    FooTagExtraInfo fooTagExtraInfo = new FooTagExtraInfo();

    // Act and Assert
    assertNull(fooTagExtraInfo.validate(new TagData(new Hashtable<>())));
  }

  /**
   * Test {@link TagExtraInfo#setTagInfo(TagInfo)}.
   * <p>
   * Method under test: {@link TagExtraInfo#setTagInfo(TagInfo)}
   */
  @Test
  public void testSetTagInfo() {
    // Arrange
    FooTagExtraInfo fooTagExtraInfo = new FooTagExtraInfo();
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act
    fooTagExtraInfo.setTagInfo(tagInfo);

    // Assert
    assertSame(tagInfo, fooTagExtraInfo.getTagInfo());
  }

  /**
   * Test {@link TagExtraInfo#getTagInfo()}.
   * <p>
   * Method under test: {@link TagExtraInfo#getTagInfo()}
   */
  @Test
  public void testGetTagInfo() {
    // Arrange, Act and Assert
    assertNull((new FooTagExtraInfo()).getTagInfo());
  }
}
