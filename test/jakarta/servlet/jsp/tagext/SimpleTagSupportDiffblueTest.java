package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import examples.FooTag;
import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.TesterPageContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.jasper.runtime.JspContextWrapper;
import org.junit.Test;

public class SimpleTagSupportDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SimpleTagSupport}
   *   <li>{@link SimpleTagSupport#setJspBody(JspFragment)}
   *   <li>{@link SimpleTagSupport#setJspContext(JspContext)}
   *   <li>{@link SimpleTagSupport#setParent(JspTag)}
   *   <li>{@link SimpleTagSupport#doTag()}
   *   <li>{@link SimpleTagSupport#getJspBody()}
   *   <li>{@link SimpleTagSupport#getJspContext()}
   *   <li>{@link SimpleTagSupport#getParent()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws JspException, IOException {
    // Arrange and Act
    SimpleTagSupport actualSimpleTagSupport = new SimpleTagSupport();
    actualSimpleTagSupport.setJspBody(null);
    BodyTagSupport jspTag = new BodyTagSupport();
    TesterPageContext jspContext = new TesterPageContext();
    ArrayList<String> nestedVars = new ArrayList<>();
    ArrayList<String> atBeginVars = new ArrayList<>();
    ArrayList<String> atEndVars = new ArrayList<>();
    JspContextWrapper pc = new JspContextWrapper(jspTag, jspContext, nestedVars, atBeginVars, atEndVars,
        new HashMap<>());

    actualSimpleTagSupport.setJspContext(pc);
    BodyTagSupport parent = new BodyTagSupport();
    actualSimpleTagSupport.setParent(parent);
    actualSimpleTagSupport.doTag();
    JspFragment actualJspBody = actualSimpleTagSupport.getJspBody();
    JspContext actualJspContext = actualSimpleTagSupport.getJspContext();

    // Assert
    assertNull(actualJspBody);
    assertSame(parent, actualSimpleTagSupport.getParent());
    assertSame(pc, actualJspContext);
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>Given {@link FooTag} (default constructor) Parent is {@code null}.</li>
   *   <li>Then return {@link FooTag}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_givenFooTagParentIsNull_thenReturnFooTag() {
    // Arrange
    FooTag parent = new FooTag();
    parent.setParent(null);

    FooTag parent2 = new FooTag();
    parent2.setParent(parent);

    FooTag parent3 = new FooTag();
    parent3.setParent(parent2);

    FooTag parent4 = new FooTag();
    parent4.setParent(parent3);

    FooTag from = new FooTag();
    from.setParent(parent4);
    Class<JspTag> klass = JspTag.class;

    // Act
    JspTag actualFindAncestorWithClassResult = SimpleTagSupport.findAncestorWithClass(from, klass);

    // Assert
    assertTrue(actualFindAncestorWithClassResult instanceof FooTag);
    assertNull(((FooTag) actualFindAncestorWithClassResult).pageContext);
    assertNull(((FooTag) actualFindAncestorWithClassResult).getBodyContent());
    assertNull(((FooTag) actualFindAncestorWithClassResult).getId());
    assertNull(((FooTag) actualFindAncestorWithClassResult).getValues());
    assertSame(parent3, ((FooTag) actualFindAncestorWithClassResult).getParent());
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@link BodyTagSupport} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenBodyTagSupport_thenReturnNull() {
    // Arrange
    BodyTagSupport from = new BodyTagSupport();
    Class<JspTag> klass = JspTag.class;

    // Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@code SimpleTag}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenJakartaServletJspTagextSimpleTag() {
    // Arrange
    FooTag parent = new FooTag();
    parent.setParent(null);

    FooTag parent2 = new FooTag();
    parent2.setParent(parent);

    FooTag parent3 = new FooTag();
    parent3.setParent(parent2);

    FooTag parent4 = new FooTag();
    parent4.setParent(parent3);

    FooTag from = new FooTag();
    from.setParent(parent4);
    Class<SimpleTag> klass = SimpleTag.class;

    // Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenJavaLangObject_thenReturnNull() {
    // Arrange
    BodyTagSupport from = new BodyTagSupport();
    Class<Object> klass = Object.class;

    // Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(null, null));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(new BodyTagSupport(), null));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@link SimpleTagSupport} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenSimpleTagSupport_thenReturnNull() {
    // Arrange
    SimpleTagSupport from = new SimpleTagSupport();
    Class<JspTag> klass = JspTag.class;

    // Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(from, klass));
  }

  /**
   * Test {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}.
   * <ul>
   *   <li>When {@link TagAdapter#TagAdapter(SimpleTag)} with adaptee is {@link SimpleTagSupport} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleTagSupport#findAncestorWithClass(JspTag, Class)}
   */
  @Test
  public void testFindAncestorWithClass_whenTagAdapterWithAdapteeIsSimpleTagSupport() {
    // Arrange
    TagAdapter from = new TagAdapter(new SimpleTagSupport());
    Class<JspTag> klass = JspTag.class;

    // Act and Assert
    assertNull(SimpleTagSupport.findAncestorWithClass(from, klass));
  }
}
