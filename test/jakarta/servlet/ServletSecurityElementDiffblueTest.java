package jakarta.servlet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;

public class ServletSecurityElementDiffblueTest {
  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement()}.
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement()}
   */
  @Test
  public void testNewServletSecurityElement() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement();

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertEquals(0, actualServletSecurityElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualServletSecurityElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualServletSecurityElement.getTransportGuarantee());
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}.
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}
   */
  @Test
  public void testNewServletSecurityElement2() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(
        new HttpConstraintElement(TransportGuarantee.NONE, "https://example.org/example"));

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertEquals(EmptyRoleSemantic.PERMIT, actualServletSecurityElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualServletSecurityElement.getTransportGuarantee());
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
    assertArrayEquals(new String[]{"https://example.org/example"}, actualServletSecurityElement.getRolesAllowed());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}.
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}
   */
  @Test
  public void testNewServletSecurityElement3() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE,
        "https://example.org/example");

    // Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(httpConstraintElement,
        new ArrayList<>());

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
    assertArrayEquals(new String[]{"https://example.org/example"}, actualServletSecurityElement.getRolesAllowed());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}
   */
  @Test
  public void testNewServletSecurityElement_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(new HttpConstraintElement());

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertEquals(0, actualServletSecurityElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualServletSecurityElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualServletSecurityElement.getTransportGuarantee());
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}.
   * <ul>
   *   <li>Then return HttpMethodConstraints size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}
   */
  @Test
  public void testNewServletSecurityElement_thenReturnHttpMethodConstraintsSizeIsOne() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement();

    ArrayList<HttpMethodConstraintElement> httpMethodConstraints = new ArrayList<>();
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));

    // Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(httpConstraintElement,
        httpMethodConstraints);

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints2 = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertEquals(1, httpMethodConstraints2.size());
    assertTrue(httpMethodConstraints2 instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertEquals(1, methodNames.size());
    assertTrue(methodNames instanceof Set);
    assertEquals(0, actualServletSecurityElement.getRolesAllowed().length);
    assertTrue(methodNames.contains("https://example.org/example"));
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(Collection)}.
   * <ul>
   *   <li>Then return MethodNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(Collection)}
   */
  @Test
  public void testNewServletSecurityElement_thenReturnMethodNamesSizeIsOne() {
    // Arrange
    LinkedHashSet<HttpMethodConstraintElement> httpMethodConstraints = new LinkedHashSet<>();
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));

    // Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(httpMethodConstraints);

    // Assert
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertEquals(1, methodNames.size());
    assertTrue(methodNames instanceof Set);
    assertEquals(0, actualServletSecurityElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualServletSecurityElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualServletSecurityElement.getTransportGuarantee());
    assertTrue(methodNames.contains("https://example.org/example"));
    assertEquals(httpMethodConstraints, actualServletSecurityElement.getHttpMethodConstraints());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}.
   * <ul>
   *   <li>Then return RolesAllowed is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement)}
   */
  @Test
  public void testNewServletSecurityElement_thenReturnRolesAllowedIsNull() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(
        new HttpConstraintElement(TransportGuarantee.NONE, null));

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertNull(actualServletSecurityElement.getRolesAllowed());
    assertEquals(EmptyRoleSemantic.PERMIT, actualServletSecurityElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualServletSecurityElement.getTransportGuarantee());
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}.
   * <ul>
   *   <li>Then return RolesAllowed is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}
   */
  @Test
  public void testNewServletSecurityElement_thenReturnRolesAllowedIsNull2() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE, null);

    // Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(httpConstraintElement,
        new ArrayList<>());

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertNull(actualServletSecurityElement.getRolesAllowed());
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}
   */
  @Test
  public void testNewServletSecurityElement_thenThrowIllegalArgumentException() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement();

    ArrayList<HttpMethodConstraintElement> httpMethodConstraints = new ArrayList<>();
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new ServletSecurityElement(httpConstraintElement, httpMethodConstraints));

  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(Collection)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(Collection)}
   */
  @Test
  public void testNewServletSecurityElement_thenThrowIllegalArgumentException2() {
    // Arrange
    ArrayList<HttpMethodConstraintElement> httpMethodConstraints = new ArrayList<>();
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));
    httpMethodConstraints.add(new HttpMethodConstraintElement("https://example.org/example"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ServletSecurityElement(httpMethodConstraints));
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then HttpMethodConstraints return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(Collection)}
   */
  @Test
  public void testNewServletSecurityElement_whenArrayList_thenHttpMethodConstraintsReturnSet() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(new ArrayList<>());

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(HttpConstraintElement, Collection)}
   */
  @Test
  public void testNewServletSecurityElement_whenArrayList_thenReturnArrayLengthIsZero() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement();

    // Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(httpConstraintElement,
        new ArrayList<>());

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertEquals(0, actualServletSecurityElement.getRolesAllowed().length);
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#ServletSecurityElement(Collection)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then HttpMethodConstraints return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletSecurityElement#ServletSecurityElement(Collection)}
   */
  @Test
  public void testNewServletSecurityElement_whenNull_thenHttpMethodConstraintsReturnSet() {
    // Arrange and Act
    ServletSecurityElement actualServletSecurityElement = new ServletSecurityElement(
        (Collection<HttpMethodConstraintElement>) null);

    // Assert
    Collection<HttpMethodConstraintElement> httpMethodConstraints = actualServletSecurityElement
        .getHttpMethodConstraints();
    assertTrue(httpMethodConstraints instanceof Set);
    Collection<String> methodNames = actualServletSecurityElement.getMethodNames();
    assertTrue(methodNames instanceof Set);
    assertTrue(httpMethodConstraints.isEmpty());
    assertTrue(methodNames.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#getHttpMethodConstraints()}.
   * <p>
   * Method under test: {@link ServletSecurityElement#getHttpMethodConstraints()}
   */
  @Test
  public void testGetHttpMethodConstraints() {
    // Arrange and Act
    Collection<HttpMethodConstraintElement> actualHttpMethodConstraints = (new ServletSecurityElement())
        .getHttpMethodConstraints();

    // Assert
    assertTrue(actualHttpMethodConstraints instanceof Set);
    assertTrue(actualHttpMethodConstraints.isEmpty());
  }

  /**
   * Test {@link ServletSecurityElement#getMethodNames()}.
   * <p>
   * Method under test: {@link ServletSecurityElement#getMethodNames()}
   */
  @Test
  public void testGetMethodNames() {
    // Arrange and Act
    Collection<String> actualMethodNames = (new ServletSecurityElement()).getMethodNames();

    // Assert
    assertTrue(actualMethodNames instanceof Set);
    assertTrue(actualMethodNames.isEmpty());
  }
}
