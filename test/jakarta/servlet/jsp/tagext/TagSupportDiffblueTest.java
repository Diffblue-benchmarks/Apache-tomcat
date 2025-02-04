package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import examples.FooTag;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.TesterPageContext;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.jasper.runtime.JspContextWrapper;
import org.junit.Test;

public class TagSupportDiffblueTest {
  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>Given {@link BodyTagSupport} (default constructor).</li>
   *   <li>Then return {@link BodyTagSupport}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_givenBodyTagSupport_thenReturnBodyTagSupport() {
    // Arrange
    FooTag from = new FooTag();
    from.setParent(new BodyTagSupport());
    Class<Tag> klass = Tag.class;

    // Act
    Tag actualFindAncestorWithClassResult = TagSupport.findAncestorWithClass(from, klass);

    // Assert
    assertTrue(actualFindAncestorWithClassResult instanceof BodyTagSupport);
    assertNull(((BodyTagSupport) actualFindAncestorWithClassResult).pageContext);
    assertNull(((BodyTagSupport) actualFindAncestorWithClassResult).getBodyContent());
    assertNull(actualFindAncestorWithClassResult.getParent());
    assertNull(((BodyTagSupport) actualFindAncestorWithClassResult).getId());
    assertNull(((BodyTagSupport) actualFindAncestorWithClassResult).getValues());
  }

  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>When {@link BodyTagSupport} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenBodyTagSupport_thenReturnNull() {
    // Arrange
    BodyTagSupport from = new BodyTagSupport();
    Class<Tag> klass = Tag.class;

    // Act and Assert
    assertNull(TagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenJavaLangObject_thenReturnNull() {
    // Arrange
    BodyTagSupport from = new BodyTagSupport();
    Class<Object> klass = Object.class;

    // Act and Assert
    assertNull(TagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(TagSupport.findAncestorWithClass(null, null));
  }

  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertNull(TagSupport.findAncestorWithClass(new BodyTagSupport(), null));
  }

  /**
   * Test {@link TagSupport#findAncestorWithClass(Tag, Class)}.
   * <ul>
   *   <li>When {@link TagAdapter#TagAdapter(SimpleTag)} with adaptee is {@link SimpleTagSupport} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#findAncestorWithClass(Tag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenTagAdapterWithAdapteeIsSimpleTagSupport() {
    // Arrange
    TagAdapter from = new TagAdapter(new SimpleTagSupport());
    Class<Tag> klass = Tag.class;

    // Act and Assert
    assertNull(TagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TagSupport}
   *   <li>{@link TagSupport#setId(String)}
   *   <li>{@link TagSupport#setPageContext(PageContext)}
   *   <li>{@link TagSupport#setParent(Tag)}
   *   <li>{@link TagSupport#getId()}
   *   <li>{@link TagSupport#getParent()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    TagSupport actualTagSupport = new TagSupport();
    actualTagSupport.setId("42");
    BodyTagSupport jspTag = new BodyTagSupport();
    TesterPageContext jspContext = new TesterPageContext();
    ArrayList<String> nestedVars = new ArrayList<>();
    ArrayList<String> atBeginVars = new ArrayList<>();
    ArrayList<String> atEndVars = new ArrayList<>();
    actualTagSupport
        .setPageContext(new JspContextWrapper(jspTag, jspContext, nestedVars, atBeginVars, atEndVars, new HashMap<>()));
    BodyTagSupport t = new BodyTagSupport();
    actualTagSupport.setParent(t);
    String actualId = actualTagSupport.getId();

    // Assert
    assertEquals("42", actualId);
    assertSame(t, actualTagSupport.getParent());
  }

  /**
   * Test {@link TagSupport#doStartTag()}.
   * <p>
   * Method under test: {@link TagSupport#doStartTag()}
   */
  @Test
  public void testDoStartTag() throws JspException {
    // Arrange, Act and Assert
    assertEquals(0, (new TagSupport()).doStartTag());
  }

  /**
   * Test {@link TagSupport#doEndTag()}.
   * <p>
   * Method under test: {@link TagSupport#doEndTag()}
   */
  @Test
  public void testDoEndTag() throws JspException {
    // Arrange, Act and Assert
    assertEquals(6, (new TagSupport()).doEndTag());
  }

  /**
   * Test {@link TagSupport#doAfterBody()}.
   * <p>
   * Method under test: {@link TagSupport#doAfterBody()}
   */
  @Test
  public void testDoAfterBody() throws JspException {
    // Arrange, Act and Assert
    assertEquals(0, (new TagSupport()).doAfterBody());
  }

  /**
   * Test {@link TagSupport#release()}.
   * <ul>
   *   <li>Given {@link TagSupport} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#release()}
   */
  @Test
  public void testRelease_givenTagSupport() {
    // Arrange
    TagSupport tagSupport = new TagSupport();

    // Act
    tagSupport.release();

    // Assert that nothing has changed
    assertNull(tagSupport.getValues());
  }

  /**
   * Test {@link TagSupport#release()}.
   * <ul>
   *   <li>Given {@link TagSupport} (default constructor) Value {@code foo} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#release()}
   */
  @Test
  public void testRelease_givenTagSupportValueFooIs42() {
    // Arrange
    TagSupport tagSupport = new TagSupport();
    tagSupport.setValue("foo", "42");

    // Act
    tagSupport.release();

    // Assert
    assertNull(tagSupport.getValues());
  }

  /**
   * Test {@link TagSupport#getValue(String)}.
   * <ul>
   *   <li>Given {@link TagSupport} (default constructor) Value {@code foo} is {@code 42}.</li>
   *   <li>When {@code foo}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#getValue(String)}
   */
  @Test
  public void testGetValue_givenTagSupportValueFooIs42_whenFoo_thenReturn42() {
    // Arrange
    TagSupport tagSupport = new TagSupport();
    tagSupport.setValue("foo", "42");

    // Act and Assert
    assertEquals("42", tagSupport.getValue("foo"));
  }

  /**
   * Test {@link TagSupport#getValue(String)}.
   * <ul>
   *   <li>Given {@link TagSupport} (default constructor).</li>
   *   <li>When {@code foo}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#getValue(String)}
   */
  @Test
  public void testGetValue_givenTagSupport_whenFoo_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TagSupport()).getValue("foo"));
  }

  /**
   * Test {@link TagSupport#getValues()}.
   * <ul>
   *   <li>Given {@link TagSupport} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagSupport#getValues()}
   */
  @Test
  public void testGetValues_givenTagSupport_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TagSupport()).getValues());
  }
}
