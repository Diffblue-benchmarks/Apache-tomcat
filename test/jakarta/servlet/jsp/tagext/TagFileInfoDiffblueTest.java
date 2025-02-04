package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import examples.FooTagExtraInfo;
import org.junit.Test;

public class TagFileInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagFileInfo#TagFileInfo(String, String, TagInfo)}
   *   <li>{@link TagFileInfo#getName()}
   *   <li>{@link TagFileInfo#getPath()}
   *   <li>{@link TagFileInfo#getTagInfo()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FooTagExtraInfo tagExtraInfo = new FooTagExtraInfo();
    TagInfo tagInfo = new TagInfo("Tag Name", "Tag Class Name", "Not all who wander are lost", "Info String", null,
        tagExtraInfo, new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)});

    // Act
    TagFileInfo actualTagFileInfo = new TagFileInfo("Name", "Path", tagInfo);
    String actualName = actualTagFileInfo.getName();
    String actualPath = actualTagFileInfo.getPath();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Path", actualPath);
    assertSame(tagInfo, actualTagFileInfo.getTagInfo());
  }
}
