package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.TesterPageContext;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.jasper.runtime.JspContextWrapper;
import org.junit.Test;

public class TagAdapterDiffblueTest {
  /**
   * Test {@link TagAdapter#TagAdapter(SimpleTag)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAdapter#TagAdapter(SimpleTag)}
   */
  @Test
  public void testNewTagAdapter_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new TagAdapter(null));
  }

  /**
   * Test {@link TagAdapter#TagAdapter(SimpleTag)}.
   * <ul>
   *   <li>When {@link SimpleTagSupport} (default constructor).</li>
   *   <li>Then return Parent is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAdapter#TagAdapter(SimpleTag)}
   */
  @Test
  public void testNewTagAdapter_whenSimpleTagSupport_thenReturnParentIsNull() {
    // Arrange
    SimpleTagSupport adaptee = new SimpleTagSupport();

    // Act
    TagAdapter actualTagAdapter = new TagAdapter(adaptee);

    // Assert
    assertNull(actualTagAdapter.getParent());
    assertSame(adaptee, actualTagAdapter.getAdaptee());
  }

  /**
   * Test {@link TagAdapter#setPageContext(PageContext)}.
   * <p>
   * Method under test: {@link TagAdapter#setPageContext(PageContext)}
   */
  @Test
  public void testSetPageContext() {
    // Arrange
    TagAdapter tagAdapter = new TagAdapter(new SimpleTagSupport());
    BodyTagSupport jspTag = new BodyTagSupport();
    TesterPageContext jspContext = new TesterPageContext();
    ArrayList<String> nestedVars = new ArrayList<>();
    ArrayList<String> atBeginVars = new ArrayList<>();
    ArrayList<String> atEndVars = new ArrayList<>();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> tagAdapter.setPageContext(
        new JspContextWrapper(jspTag, jspContext, nestedVars, atBeginVars, atEndVars, new HashMap<>())));
  }

  /**
   * Test {@link TagAdapter#setParent(Tag)}.
   * <p>
   * Method under test: {@link TagAdapter#setParent(Tag)}
   */
  @Test
  public void testSetParent() {
    // Arrange
    TagAdapter tagAdapter = new TagAdapter(new SimpleTagSupport());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> tagAdapter.setParent(new BodyTagSupport()));
  }

  /**
   * Test {@link TagAdapter#getParent()}.
   * <ul>
   *   <li>Given {@link SimpleTagSupport} (default constructor) Parent is {@link SimpleTagSupport} (default constructor).</li>
   *   <li>Then return {@link TagAdapter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAdapter#getParent()}
   */
  @Test
  public void testGetParent_givenSimpleTagSupportParentIsSimpleTagSupport_thenReturnTagAdapter() {
    // Arrange
    SimpleTagSupport adaptee = new SimpleTagSupport();
    SimpleTagSupport parent = new SimpleTagSupport();
    adaptee.setParent(parent);

    // Act
    Tag actualParent = (new TagAdapter(adaptee)).getParent();

    // Assert
    assertTrue(actualParent instanceof TagAdapter);
    assertSame(parent, ((TagAdapter) actualParent).getAdaptee());
  }

  /**
   * Test {@link TagAdapter#getParent()}.
   * <ul>
   *   <li>Then return {@link BodyTagSupport} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAdapter#getParent()}
   */
  @Test
  public void testGetParent_thenReturnBodyTagSupport() {
    // Arrange
    SimpleTagSupport adaptee = new SimpleTagSupport();
    BodyTagSupport parent = new BodyTagSupport();
    adaptee.setParent(parent);

    // Act and Assert
    assertSame(parent, (new TagAdapter(adaptee)).getParent());
  }

  /**
   * Test {@link TagAdapter#getParent()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TagAdapter#getParent()}
   */
  @Test
  public void testGetParent_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TagAdapter(new SimpleTagSupport())).getParent());
  }

  /**
   * Test {@link TagAdapter#getAdaptee()}.
   * <p>
   * Method under test: {@link TagAdapter#getAdaptee()}
   */
  @Test
  public void testGetAdaptee() {
    // Arrange
    SimpleTagSupport adaptee = new SimpleTagSupport();

    // Act and Assert
    assertSame(adaptee, (new TagAdapter(adaptee)).getAdaptee());
  }

  /**
   * Test {@link TagAdapter#doStartTag()}.
   * <p>
   * Method under test: {@link TagAdapter#doStartTag()}
   */
  @Test
  public void testDoStartTag() throws JspException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new TagAdapter(new SimpleTagSupport())).doStartTag());
  }

  /**
   * Test {@link TagAdapter#doEndTag()}.
   * <p>
   * Method under test: {@link TagAdapter#doEndTag()}
   */
  @Test
  public void testDoEndTag() throws JspException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new TagAdapter(new SimpleTagSupport())).doEndTag());
  }

  /**
   * Test {@link TagAdapter#release()}.
   * <p>
   * Method under test: {@link TagAdapter#release()}
   */
  @Test
  public void testRelease() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new TagAdapter(new SimpleTagSupport())).release());
  }
}
