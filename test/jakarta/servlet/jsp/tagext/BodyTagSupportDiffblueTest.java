package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jakarta.servlet.jsp.JspException;
import org.apache.jasper.runtime.BodyContentImpl;
import org.apache.jasper.runtime.JspWriterImpl;
import org.junit.Test;

public class BodyTagSupportDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link BodyTagSupport}
   *   <li>{@link BodyTagSupport#setBodyContent(BodyContent)}
   *   <li>{@link BodyTagSupport#doInitBody()}
   *   <li>{@link BodyTagSupport#getBodyContent()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws JspException {
    // Arrange and Act
    BodyTagSupport actualBodyTagSupport = new BodyTagSupport();
    BodyContentImpl b = new BodyContentImpl(new JspWriterImpl(), true, 3);

    actualBodyTagSupport.setBodyContent(b);
    actualBodyTagSupport.doInitBody();
    BodyContent actualBodyContent = actualBodyTagSupport.getBodyContent();

    // Assert
    assertNull(actualBodyTagSupport.getParent());
    assertNull(actualBodyTagSupport.getId());
    assertSame(b, actualBodyContent);
  }

  /**
   * Test {@link BodyTagSupport#doStartTag()}.
   * <p>
   * Method under test: {@link BodyTagSupport#doStartTag()}
   */
  @Test
  public void testDoStartTag() throws JspException {
    // Arrange, Act and Assert
    assertEquals(2, (new BodyTagSupport()).doStartTag());
  }

  /**
   * Test {@link BodyTagSupport#doEndTag()}.
   * <p>
   * Method under test: {@link BodyTagSupport#doEndTag()}
   */
  @Test
  public void testDoEndTag() throws JspException {
    // Arrange, Act and Assert
    assertEquals(6, (new BodyTagSupport()).doEndTag());
  }

  /**
   * Test {@link BodyTagSupport#doAfterBody()}.
   * <p>
   * Method under test: {@link BodyTagSupport#doAfterBody()}
   */
  @Test
  public void testDoAfterBody() throws JspException {
    // Arrange, Act and Assert
    assertEquals(0, (new BodyTagSupport()).doAfterBody());
  }

  /**
   * Test {@link BodyTagSupport#release()}.
   * <ul>
   *   <li>Given {@link BodyTagSupport} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyTagSupport#release()}
   */
  @Test
  public void testRelease_givenBodyTagSupport() {
    // Arrange
    BodyTagSupport bodyTagSupport = new BodyTagSupport();

    // Act
    bodyTagSupport.release();

    // Assert that nothing has changed
    assertNull(bodyTagSupport.getValues());
  }

  /**
   * Test {@link BodyTagSupport#release()}.
   * <ul>
   *   <li>Given {@link BodyTagSupport} (default constructor) Value {@code foo} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyTagSupport#release()}
   */
  @Test
  public void testRelease_givenBodyTagSupportValueFooIs42() {
    // Arrange
    BodyTagSupport bodyTagSupport = new BodyTagSupport();
    bodyTagSupport.setValue("foo", "42");

    // Act
    bodyTagSupport.release();

    // Assert
    assertNull(bodyTagSupport.getValues());
  }

  /**
   * Test {@link BodyTagSupport#getPreviousOut()}.
   * <ul>
   *   <li>Then return {@link JspWriterImpl#JspWriterImpl()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyTagSupport#getPreviousOut()}
   */
  @Test
  public void testGetPreviousOut_thenReturnJspWriterImpl() {
    // Arrange
    BodyTagSupport bodyTagSupport = new BodyTagSupport();
    JspWriterImpl enclosingWriter = new JspWriterImpl();
    bodyTagSupport.setBodyContent(new BodyContentImpl(enclosingWriter, true, 3));

    // Act and Assert
    assertSame(enclosingWriter, bodyTagSupport.getPreviousOut());
  }
}
