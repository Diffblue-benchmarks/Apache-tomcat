package jakarta.servlet.jsp.tagext;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import java.io.IOException;
import org.apache.jasper.runtime.BodyContentImpl;
import org.apache.jasper.runtime.JspWriterImpl;
import org.junit.Test;

public class BodyContentDiffblueTest {
  /**
   * Test {@link BodyContent#flush()}.
   * <p>
   * Method under test: {@link BodyContent#flush()}
   */
  @Test
  public void testFlush() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> (new BodyContentImpl(new JspWriterImpl(), true, 3)).flush());
  }

  /**
   * Test {@link BodyContent#getEnclosingWriter()}.
   * <p>
   * Method under test: {@link BodyContent#getEnclosingWriter()}
   */
  @Test
  public void testGetEnclosingWriter() {
    // Arrange
    JspWriterImpl enclosingWriter = new JspWriterImpl();

    // Act and Assert
    assertSame(enclosingWriter, (new BodyContentImpl(enclosingWriter, true, 3)).getEnclosingWriter());
  }
}
