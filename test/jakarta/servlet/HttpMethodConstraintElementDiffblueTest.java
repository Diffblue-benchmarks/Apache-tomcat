package jakarta.servlet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import org.junit.Test;

public class HttpMethodConstraintElementDiffblueTest {
  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}.
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}
   */
  @Test
  public void testNewHttpMethodConstraintElement() {
    // Arrange and Act
    HttpMethodConstraintElement actualHttpMethodConstraintElement = new HttpMethodConstraintElement(
        "https://example.org/example",
        new HttpConstraintElement(TransportGuarantee.NONE, "https://example.org/example"));

    // Assert
    assertEquals("https://example.org/example", actualHttpMethodConstraintElement.getMethodName());
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpMethodConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpMethodConstraintElement.getTransportGuarantee());
    assertArrayEquals(new String[]{"https://example.org/example"}, actualHttpMethodConstraintElement.getRolesAllowed());
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    HttpMethodConstraintElement actualHttpMethodConstraintElement = new HttpMethodConstraintElement(
        "https://example.org/example", new HttpConstraintElement());

    // Assert
    assertEquals("https://example.org/example", actualHttpMethodConstraintElement.getMethodName());
    assertEquals(0, actualHttpMethodConstraintElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpMethodConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpMethodConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}.
   * <ul>
   *   <li>Then return MethodName is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_thenReturnMethodNameIsHttpsExampleOrgExample() {
    // Arrange and Act
    HttpMethodConstraintElement actualHttpMethodConstraintElement = new HttpMethodConstraintElement(
        "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualHttpMethodConstraintElement.getMethodName());
    assertEquals(0, actualHttpMethodConstraintElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpMethodConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpMethodConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}.
   * <ul>
   *   <li>Then return RolesAllowed is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_thenReturnRolesAllowedIsNull() {
    // Arrange and Act
    HttpMethodConstraintElement actualHttpMethodConstraintElement = new HttpMethodConstraintElement(
        "https://example.org/example", new HttpConstraintElement(TransportGuarantee.NONE, null));

    // Assert
    assertEquals("https://example.org/example", actualHttpMethodConstraintElement.getMethodName());
    assertNull(actualHttpMethodConstraintElement.getRolesAllowed());
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpMethodConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpMethodConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_whenEmptyString() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new HttpMethodConstraintElement(""));
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_whenEmptyString2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new HttpMethodConstraintElement("", new HttpConstraintElement()));

  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new HttpMethodConstraintElement(null));
  }

  /**
   * Test {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#HttpMethodConstraintElement(String, HttpConstraintElement)}
   */
  @Test
  public void testNewHttpMethodConstraintElement_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new HttpMethodConstraintElement(null, new HttpConstraintElement()));

  }

  /**
   * Test {@link HttpMethodConstraintElement#getMethodName()}.
   * <p>
   * Method under test: {@link HttpMethodConstraintElement#getMethodName()}
   */
  @Test
  public void testGetMethodName() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new HttpMethodConstraintElement("https://example.org/example")).getMethodName());
  }
}
