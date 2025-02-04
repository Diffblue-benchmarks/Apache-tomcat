package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TagAttributeInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return {@code Expected Type Name}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagAttributeInfo#TagAttributeInfo(String, boolean, String, boolean, boolean, String, boolean, boolean, String, String)}
   *   <li>{@link TagAttributeInfo#toString()}
   *   <li>{@link TagAttributeInfo#getDescription()}
   *   <li>{@link TagAttributeInfo#getExpectedTypeName()}
   *   <li>{@link TagAttributeInfo#getMethodSignature()}
   *   <li>{@link TagAttributeInfo#getName()}
   *   <li>{@link TagAttributeInfo#getTypeName()}
   *   <li>{@link TagAttributeInfo#isDeferredMethod()}
   *   <li>{@link TagAttributeInfo#isDeferredValue()}
   *   <li>{@link TagAttributeInfo#isFragment()}
   *   <li>{@link TagAttributeInfo#isRequired()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_thenReturnExpectedTypeName() {
    // Arrange and Act
    TagAttributeInfo actualTagAttributeInfo = new TagAttributeInfo("Name", true, "Type", true, true,
        "The characteristics of someone or something", true, true, "Expected Type Name", "Method Signature");
    String actualToStringResult = actualTagAttributeInfo.toString();
    String actualDescription = actualTagAttributeInfo.getDescription();
    String actualExpectedTypeName = actualTagAttributeInfo.getExpectedTypeName();
    String actualMethodSignature = actualTagAttributeInfo.getMethodSignature();
    String actualName = actualTagAttributeInfo.getName();
    String actualTypeName = actualTagAttributeInfo.getTypeName();
    boolean actualIsDeferredMethodResult = actualTagAttributeInfo.isDeferredMethod();
    boolean actualIsDeferredValueResult = actualTagAttributeInfo.isDeferredValue();
    boolean actualIsFragmentResult = actualTagAttributeInfo.isFragment();

    // Assert
    assertEquals("Expected Type Name", actualExpectedTypeName);
    assertEquals("Method Signature", actualMethodSignature);
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("Type", actualTypeName);
    assertEquals(
        "name = Name type = Type reqTime = true required = true fragment = true deferredValue = true expectedTypeName"
            + " = Expected Type Name deferredMethod = true methodSignature = Method Signature",
        actualToStringResult);
    assertTrue(actualIsDeferredMethodResult);
    assertTrue(actualIsDeferredValueResult);
    assertTrue(actualIsFragmentResult);
    assertTrue(actualTagAttributeInfo.isRequired());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return Description is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagAttributeInfo#TagAttributeInfo(String, boolean, String, boolean, boolean)}
   *   <li>{@link TagAttributeInfo#toString()}
   *   <li>{@link TagAttributeInfo#getDescription()}
   *   <li>{@link TagAttributeInfo#getExpectedTypeName()}
   *   <li>{@link TagAttributeInfo#getMethodSignature()}
   *   <li>{@link TagAttributeInfo#getName()}
   *   <li>{@link TagAttributeInfo#getTypeName()}
   *   <li>{@link TagAttributeInfo#isDeferredMethod()}
   *   <li>{@link TagAttributeInfo#isDeferredValue()}
   *   <li>{@link TagAttributeInfo#isFragment()}
   *   <li>{@link TagAttributeInfo#isRequired()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenName_thenReturnDescriptionIsNull() {
    // Arrange and Act
    TagAttributeInfo actualTagAttributeInfo = new TagAttributeInfo("Name", true, "Type", true, true);
    String actualToStringResult = actualTagAttributeInfo.toString();
    String actualDescription = actualTagAttributeInfo.getDescription();
    String actualExpectedTypeName = actualTagAttributeInfo.getExpectedTypeName();
    String actualMethodSignature = actualTagAttributeInfo.getMethodSignature();
    String actualName = actualTagAttributeInfo.getName();
    String actualTypeName = actualTagAttributeInfo.getTypeName();
    boolean actualIsDeferredMethodResult = actualTagAttributeInfo.isDeferredMethod();
    boolean actualIsDeferredValueResult = actualTagAttributeInfo.isDeferredValue();
    boolean actualIsFragmentResult = actualTagAttributeInfo.isFragment();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Type", actualTypeName);
    assertEquals("name = Name type = Type reqTime = true required = true fragment = true deferredValue = false"
        + " expectedTypeName = null deferredMethod = false methodSignature = null", actualToStringResult);
    assertNull(actualDescription);
    assertNull(actualExpectedTypeName);
    assertNull(actualMethodSignature);
    assertFalse(actualIsDeferredMethodResult);
    assertFalse(actualIsDeferredValueResult);
    assertTrue(actualIsFragmentResult);
    assertTrue(actualTagAttributeInfo.isRequired());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return not Fragment.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TagAttributeInfo#TagAttributeInfo(String, boolean, String, boolean)}
   *   <li>{@link TagAttributeInfo#toString()}
   *   <li>{@link TagAttributeInfo#getDescription()}
   *   <li>{@link TagAttributeInfo#getExpectedTypeName()}
   *   <li>{@link TagAttributeInfo#getMethodSignature()}
   *   <li>{@link TagAttributeInfo#getName()}
   *   <li>{@link TagAttributeInfo#getTypeName()}
   *   <li>{@link TagAttributeInfo#isDeferredMethod()}
   *   <li>{@link TagAttributeInfo#isDeferredValue()}
   *   <li>{@link TagAttributeInfo#isFragment()}
   *   <li>{@link TagAttributeInfo#isRequired()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenName_thenReturnNotFragment() {
    // Arrange and Act
    TagAttributeInfo actualTagAttributeInfo = new TagAttributeInfo("Name", true, "Type", true);
    String actualToStringResult = actualTagAttributeInfo.toString();
    String actualDescription = actualTagAttributeInfo.getDescription();
    String actualExpectedTypeName = actualTagAttributeInfo.getExpectedTypeName();
    String actualMethodSignature = actualTagAttributeInfo.getMethodSignature();
    String actualName = actualTagAttributeInfo.getName();
    String actualTypeName = actualTagAttributeInfo.getTypeName();
    boolean actualIsDeferredMethodResult = actualTagAttributeInfo.isDeferredMethod();
    boolean actualIsDeferredValueResult = actualTagAttributeInfo.isDeferredValue();
    boolean actualIsFragmentResult = actualTagAttributeInfo.isFragment();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Type", actualTypeName);
    assertEquals("name = Name type = Type reqTime = true required = true fragment = false deferredValue = false"
        + " expectedTypeName = null deferredMethod = false methodSignature = null", actualToStringResult);
    assertNull(actualDescription);
    assertNull(actualExpectedTypeName);
    assertNull(actualMethodSignature);
    assertFalse(actualIsDeferredMethodResult);
    assertFalse(actualIsDeferredValueResult);
    assertFalse(actualIsFragmentResult);
    assertTrue(actualTagAttributeInfo.isRequired());
  }

  /**
   * Test {@link TagAttributeInfo#canBeRequestTime()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAttributeInfo#canBeRequestTime()}
   */
  @Test
  public void testCanBeRequestTime_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new TagAttributeInfo("Name", true, "Type", false)).canBeRequestTime());
  }

  /**
   * Test {@link TagAttributeInfo#canBeRequestTime()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAttributeInfo#canBeRequestTime()}
   */
  @Test
  public void testCanBeRequestTime_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new TagAttributeInfo("Name", true, "Type", true)).canBeRequestTime());
  }

  /**
   * Test {@link TagAttributeInfo#getIdAttribute(TagAttributeInfo[])}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAttributeInfo#getIdAttribute(TagAttributeInfo[])}
   */
  @Test
  public void testGetIdAttribute_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        TagAttributeInfo.getIdAttribute(new TagAttributeInfo[]{new TagAttributeInfo("Name", true, "Type", true)}));
  }

  /**
   * Test {@link TagAttributeInfo#getIdAttribute(TagAttributeInfo[])}.
   * <ul>
   *   <li>Then return TypeName is {@code Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAttributeInfo#getIdAttribute(TagAttributeInfo[])}
   */
  @Test
  public void testGetIdAttribute_thenReturnTypeNameIsType() {
    // Arrange and Act
    TagAttributeInfo actualIdAttribute = TagAttributeInfo
        .getIdAttribute(new TagAttributeInfo[]{new TagAttributeInfo(TagAttributeInfo.ID, true, "Type", true)});

    // Assert
    assertEquals("Type", actualIdAttribute.getTypeName());
    assertNull(actualIdAttribute.getDescription());
    assertNull(actualIdAttribute.getExpectedTypeName());
    assertNull(actualIdAttribute.getMethodSignature());
    assertFalse(actualIdAttribute.isDeferredMethod());
    assertFalse(actualIdAttribute.isDeferredValue());
    assertFalse(actualIdAttribute.isFragment());
    assertTrue(actualIdAttribute.isRequired());
    assertEquals(TagAttributeInfo.ID, actualIdAttribute.getName());
  }
}
