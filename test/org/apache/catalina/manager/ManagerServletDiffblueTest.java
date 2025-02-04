package org.apache.catalina.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.catalina.Wrapper;
import org.apache.catalina.util.ContextName;
import org.apache.tomcat.util.res.StringManager;
import org.junit.Test;

public class ManagerServletDiffblueTest {
  /**
   * Test {@link ManagerServlet#init()}.
   * <p>
   * Method under test: {@link ManagerServlet#init()}
   */
  @Test
  public void testInit() throws ServletException {
    // Arrange, Act and Assert
    assertThrows(UnavailableException.class, () -> (new ManagerServlet()).init());
  }

  /**
   * Test {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}.
   * <ul>
   *   <li>When extractFromPath {@code Path}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}
   */
  @Test
  public void testValidateContextName_whenExtractFromPathPath_thenReturnTrue() {
    // Arrange
    ContextName cn = ContextName.extractFromPath("Path");

    // Act and Assert
    assertTrue(ManagerServlet.validateContextName(cn, new PrintWriter(new StringWriter()), ManagerServlet.sm));
  }

  /**
   * Test {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}.
   * <ul>
   *   <li>When extractFromPath {@code /}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}
   */
  @Test
  public void testValidateContextName_whenExtractFromPathSlash_thenReturnTrue() {
    // Arrange
    ContextName cn = ContextName.extractFromPath("/");

    // Act and Assert
    assertTrue(ManagerServlet.validateContextName(cn, new PrintWriter(new StringWriter()), ManagerServlet.sm));
  }

  /**
   * Test {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ManagerServlet#validateContextName(ContextName, PrintWriter, StringManager)}
   */
  @Test
  public void testValidateContextName_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ManagerServlet.validateContextName(null, new PrintWriter(new StringWriter()), ManagerServlet.sm));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ManagerServlet}
   *   <li>{@link ManagerServlet#destroy()}
   *   <li>{@link ManagerServlet#getWrapper()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ManagerServlet actualManagerServlet = new ManagerServlet();
    actualManagerServlet.destroy();
    Wrapper actualWrapper = actualManagerServlet.getWrapper();

    // Assert
    assertNull(actualManagerServlet.getServletConfig());
    assertNull(actualWrapper);
  }
}
