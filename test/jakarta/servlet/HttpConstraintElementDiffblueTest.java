package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import org.junit.Test;

public class HttpConstraintElementDiffblueTest {
  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement()}.
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement()}
   */
  @Test
  public void testNewHttpConstraintElement() {
    // Arrange and Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement();

    // Assert
    assertEquals(0, actualHttpConstraintElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(EmptyRoleSemantic)}.
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(ServletSecurity.EmptyRoleSemantic)}
   */
  @Test
  public void testNewHttpConstraintElement2() {
    // Arrange and Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement(EmptyRoleSemantic.PERMIT);

    // Assert
    assertEquals(0, actualHttpConstraintElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(TransportGuarantee, String[])}.
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(TransportGuarantee, String[])}
   */
  @Test
  public void testNewHttpConstraintElement3() {
    // Arrange
    String[] rolesAllowed = new String[]{"https://example.org/example"};

    // Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE,
        rolesAllowed);

    // Assert
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
    assertSame(rolesAllowed, actualHttpConstraintElement.getRolesAllowed());
  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(EmptyRoleSemantic, TransportGuarantee, String[])}.
   * <ul>
   *   <li>Then return RolesAllowed is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(ServletSecurity.EmptyRoleSemantic, TransportGuarantee, String[])}
   */
  @Test
  public void testNewHttpConstraintElement_thenReturnRolesAllowedIsHttpsExampleOrgExample() {
    // Arrange
    String[] rolesAllowed = new String[]{"https://example.org/example"};

    // Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement(EmptyRoleSemantic.PERMIT,
        TransportGuarantee.NONE, rolesAllowed);

    // Assert
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
    assertSame(rolesAllowed, actualHttpConstraintElement.getRolesAllowed());
  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(EmptyRoleSemantic, TransportGuarantee, String[])}.
   * <ul>
   *   <li>When {@code DENY}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(ServletSecurity.EmptyRoleSemantic, TransportGuarantee, String[])}
   */
  @Test
  public void testNewHttpConstraintElement_whenDeny_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new HttpConstraintElement(EmptyRoleSemantic.DENY,
        TransportGuarantee.NONE, "https://example.org/example"));

  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(EmptyRoleSemantic, TransportGuarantee, String[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return RolesAllowed is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(ServletSecurity.EmptyRoleSemantic, TransportGuarantee, String[])}
   */
  @Test
  public void testNewHttpConstraintElement_whenNull_thenReturnRolesAllowedIsNull() {
    // Arrange and Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement(EmptyRoleSemantic.PERMIT,
        TransportGuarantee.NONE, null);

    // Assert
    assertNull(actualHttpConstraintElement.getRolesAllowed());
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
  }

  /**
   * Test {@link HttpConstraintElement#HttpConstraintElement(EmptyRoleSemantic, TransportGuarantee, String[])}.
   * <ul>
   *   <li>When {@code PERMIT}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpConstraintElement#HttpConstraintElement(ServletSecurity.EmptyRoleSemantic, TransportGuarantee, String[])}
   */
  @Test
  public void testNewHttpConstraintElement_whenPermit_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    HttpConstraintElement actualHttpConstraintElement = new HttpConstraintElement(EmptyRoleSemantic.PERMIT,
        TransportGuarantee.NONE);

    // Assert
    assertEquals(0, actualHttpConstraintElement.getRolesAllowed().length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualHttpConstraintElement.getEmptyRoleSemantic());
    assertEquals(TransportGuarantee.NONE, actualHttpConstraintElement.getTransportGuarantee());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HttpConstraintElement#getEmptyRoleSemantic()}
   *   <li>{@link HttpConstraintElement#getRolesAllowed()}
   *   <li>{@link HttpConstraintElement#getTransportGuarantee()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    HttpConstraintElement httpConstraintElement = new HttpConstraintElement();

    // Act
    EmptyRoleSemantic actualEmptyRoleSemantic = httpConstraintElement.getEmptyRoleSemantic();
    String[] actualRolesAllowed = httpConstraintElement.getRolesAllowed();

    // Assert
    assertEquals(0, actualRolesAllowed.length);
    assertEquals(EmptyRoleSemantic.PERMIT, actualEmptyRoleSemantic);
    assertEquals(TransportGuarantee.NONE, httpConstraintElement.getTransportGuarantee());
  }
}
