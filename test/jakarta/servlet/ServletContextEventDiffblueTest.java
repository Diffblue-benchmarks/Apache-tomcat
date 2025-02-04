package jakarta.servlet;

import static org.junit.Assert.assertSame;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.jasper.JasperException;
import org.apache.jasper.servlet.JspCServletContext;
import org.junit.Test;

public class ServletContextEventDiffblueTest {
  /**
   * Test {@link ServletContextEvent#getServletContext()}.
   * <p>
   * Method under test: {@link ServletContextEvent#getServletContext()}
   */
  @Test
  public void testGetServletContext() throws MalformedURLException, JasperException {
    // Arrange
    PrintWriter aLogWriter = new PrintWriter(new StringWriter());
    URL aResourceBaseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    JspCServletContext source = new JspCServletContext(aLogWriter, aResourceBaseURL, new ParallelWebappClassLoader(),
        true, true);

    // Act and Assert
    assertSame(source, (new ServletContextEvent(source)).getServletContext());
  }
}
