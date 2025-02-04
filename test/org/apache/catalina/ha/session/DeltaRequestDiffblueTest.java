package org.apache.catalina.ha.session;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.sun.security.auth.UserPrincipal;
import java.io.IOException;
import java.security.Principal;
import org.apache.catalina.SessionListener;
import org.apache.catalina.authenticator.SingleSignOnListener;
import org.apache.catalina.realm.GenericPrincipal;
import org.junit.Test;

public class DeltaRequestDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeltaRequest#DeltaRequest()}
   *   <li>{@link DeltaRequest#getSessionId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new DeltaRequest()).getSessionId());
  }

  /**
   * Test {@link DeltaRequest#DeltaRequest(String, boolean)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return SessionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#DeltaRequest(String, boolean)}
   */
  @Test
  public void testNewDeltaRequest_when42_thenReturnSessionIdIs42() {
    // Arrange and Act
    DeltaRequest actualDeltaRequest = new DeltaRequest("42", true);

    // Assert
    assertEquals("42", actualDeltaRequest.getSessionId());
    assertEquals(0, actualDeltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#DeltaRequest(String, boolean)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return SessionId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#DeltaRequest(String, boolean)}
   */
  @Test
  public void testNewDeltaRequest_whenNull_thenReturnSessionIdIsNull() {
    // Arrange and Act
    DeltaRequest actualDeltaRequest = new DeltaRequest(null, true);

    // Assert
    assertNull(actualDeltaRequest.getSessionId());
    assertEquals(0, actualDeltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setAttribute("Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setAttribute("Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setAttribute("Name", "Value");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAttribute(String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(0, 0, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setAttribute("Name", "Value");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAttribute(String, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setAttribute(String, Object)}
   */
  @Test
  public void testSetAttribute_whenNull() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setAttribute("Name", null);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeAttribute(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeAttribute(String)}
   */
  @Test
  public void testRemoveAttribute() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.removeAttribute("Name");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeAttribute(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeAttribute(String)}
   */
  @Test
  public void testRemoveAttribute2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.removeAttribute("Name");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeAttribute(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeAttribute(String)}
   */
  @Test
  public void testRemoveAttribute3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeAttribute("Name");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeAttribute(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeAttribute(String)}
   */
  @Test
  public void testRemoveAttribute4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(0, 0, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeAttribute("Name");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNote(String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#setNote(String, Object)}
   */
  @Test
  public void testSetNote() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setNote("Name", "Value");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNote(String, Object)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code false} Size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNote(String, Object)}
   */
  @Test
  public void testSetNote_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsFalseSizeIsOne() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setNote("Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNote(String, Object)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code false} Size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNote(String, Object)}
   */
  @Test
  public void testSetNote_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsFalseSizeIsTwo() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setNote("Name", "Value");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNote(String, Object)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code true} Size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNote(String, Object)}
   */
  @Test
  public void testSetNote_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsTrueSizeIsOne() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setNote("Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNote(String, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNote(String, Object)}
   */
  @Test
  public void testSetNote_whenNull() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setNote("Name", null);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeNote(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeNote(String)}
   */
  @Test
  public void testRemoveNote() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.removeNote("Name");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeNote(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeNote(String)}
   */
  @Test
  public void testRemoveNote2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.removeNote("Name");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeNote(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeNote(String)}
   */
  @Test
  public void testRemoveNote3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeNote("Name");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeNote(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeNote(String)}
   */
  @Test
  public void testRemoveNote4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeNote("Name");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link DeltaRequest#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setMaxInactiveInterval(42);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link DeltaRequest#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setMaxInactiveInterval(42);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link DeltaRequest#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setMaxInactiveInterval(42);

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link DeltaRequest#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, "Name", "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setMaxInactiveInterval(42);

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setPrincipal(new UserPrincipal("p"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setPrincipal(new UserPrincipal("p"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setPrincipal(new UserPrincipal("p"));

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(17, 17, "Name", "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setPrincipal(new UserPrincipal("p"));

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <ul>
   *   <li>When {@link GenericPrincipal#GenericPrincipal(String)} with name is {@code deltaRequest.wrongPrincipalClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal_whenGenericPrincipalWithNameIsDeltaRequestWrongPrincipalClass() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setPrincipal(new GenericPrincipal("deltaRequest.wrongPrincipalClass"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setPrincipal(Principal)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setPrincipal(Principal)}
   */
  @Test
  public void testSetPrincipal_whenNull() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setPrincipal(null);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNew(boolean)}.
   * <p>
   * Method under test: {@link DeltaRequest#setNew(boolean)}
   */
  @Test
  public void testSetNew() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(0, 0, "Name", "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setNew(true);

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNew(boolean)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code false} Size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNew(boolean)}
   */
  @Test
  public void testSetNew_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsFalseSizeIsOne() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setNew(true);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNew(boolean)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code false} Size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNew(boolean)}
   */
  @Test
  public void testSetNew_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsFalseSizeIsTwo() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setNew(true);

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setNew(boolean)}.
   * <ul>
   *   <li>Then {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code true} Size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setNew(boolean)}
   */
  @Test
  public void testSetNew_thenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsTrueSizeIsOne() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setNew(true);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAuthType(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAuthType(String)}
   */
  @Test
  public void testSetAuthType() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setAuthType("Auth Type");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAuthType(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAuthType(String)}
   */
  @Test
  public void testSetAuthType2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.setAuthType("Auth Type");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAuthType(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAuthType(String)}
   */
  @Test
  public void testSetAuthType3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setAuthType("Auth Type");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAuthType(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setAuthType(String)}
   */
  @Test
  public void testSetAuthType4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, "Name", "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.setAuthType("Auth Type");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setAuthType(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#setAuthType(String)}
   */
  @Test
  public void testSetAuthType_whenNull() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setAuthType(null);

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#addSessionListener(SessionListener)}
   */
  @Test
  public void testAddSessionListener() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#addSessionListener(SessionListener)}
   */
  @Test
  public void testAddSessionListener2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#addSessionListener(SessionListener)}
   */
  @Test
  public void testAddSessionListener3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Assert that nothing has changed
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#addSessionListener(SessionListener)}
   */
  @Test
  public void testAddSessionListener4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Assert that nothing has changed
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeSessionListener(SessionListener)}
   */
  @Test
  public void testRemoveSessionListener() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.removeSessionListener(new SingleSignOnListener("42"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeSessionListener(SessionListener)}
   */
  @Test
  public void testRemoveSessionListener2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.removeSessionListener(new SingleSignOnListener("42"));

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeSessionListener(SessionListener)}
   */
  @Test
  public void testRemoveSessionListener3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeSessionListener(new SingleSignOnListener("42"));

    // Assert that nothing has changed
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#removeSessionListener(SessionListener)}.
   * <p>
   * Method under test: {@link DeltaRequest#removeSessionListener(SessionListener)}
   */
  @Test
  public void testRemoveSessionListener4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(1, 1, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.removeSessionListener(new SingleSignOnListener("42"));

    // Assert that nothing has changed
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addAction(int, int, String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#addAction(int, int, String, Object)}
   */
  @Test
  public void testAddAction() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.addAction(2, 2, "Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addAction(int, int, String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#addAction(int, int, String, Object)}
   */
  @Test
  public void testAddAction2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);

    // Act
    deltaRequest.addAction(2, 2, "Name", "Value");

    // Assert
    assertEquals(1, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addAction(int, int, String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#addAction(int, int, String, Object)}
   */
  @Test
  public void testAddAction3() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.addAction(2, 2, "Name", "Value");

    // Assert
    assertEquals(2, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#addAction(int, int, String, Object)}.
   * <p>
   * Method under test: {@link DeltaRequest#addAction(int, int, String, Object)}
   */
  @Test
  public void testAddAction4() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", false);
    deltaRequest.addAction(0, 0, DeltaRequest.NAME_AUTHTYPE, "Value");
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.addAction(2, 2, "Name", "Value");

    // Assert
    assertEquals(3, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#execute(DeltaSession, boolean)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#execute(DeltaSession, boolean)}
   */
  @Test
  public void testExecute_thenThrowIllegalArgumentException() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> deltaRequest.execute(new DeltaSession(), true));
  }

  /**
   * Test {@link DeltaRequest#reset()}.
   * <p>
   * Method under test: {@link DeltaRequest#reset()}
   */
  @Test
  public void testReset() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    deltaRequest.reset();

    // Assert
    assertEquals(0, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#reset()}.
   * <ul>
   *   <li>Given {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#reset()}
   */
  @Test
  public void testReset_givenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsTrue() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.reset();

    // Assert that nothing has changed
    assertEquals(0, deltaRequest.getSize());
  }

  /**
   * Test {@link DeltaRequest#setSessionId(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setSessionId(String)}
   */
  @Test
  public void testSetSessionId() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setSessionId("42");

    // Assert that nothing has changed
    assertEquals("42", deltaRequest.getSessionId());
  }

  /**
   * Test {@link DeltaRequest#setSessionId(String)}.
   * <p>
   * Method under test: {@link DeltaRequest#setSessionId(String)}
   */
  @Test
  public void testSetSessionId2() {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);

    // Act
    deltaRequest.setSessionId(null);

    // Assert
    assertNull(deltaRequest.getSessionId());
  }

  /**
   * Test {@link DeltaRequest#getSize()}.
   * <p>
   * Method under test: {@link DeltaRequest#getSize()}
   */
  @Test
  public void testGetSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new DeltaRequest("42", true)).getSize());
  }

  /**
   * Test {@link DeltaRequest#serialize()}.
   * <p>
   * Method under test: {@link DeltaRequest#serialize()}
   */
  @Test
  public void testSerialize() throws IOException {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);
    deltaRequest.addSessionListener(null);

    // Act and Assert
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '%', 0, 2, '4', '2', 1, 0, 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 17,
        '_', '_', 'S', 'E', 'T', '_', '_', 'L', 'I', 'S', 'T', 'E', 'N', 'E', 'R', '_', '_', 0},
        deltaRequest.serialize());
  }

  /**
   * Test {@link DeltaRequest#serialize()}.
   * <ul>
   *   <li>Given {@link DeltaRequest#DeltaRequest(String, boolean)} with sessionId is {@code 42} and recordAllActions is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#serialize()}
   */
  @Test
  public void testSerialize_givenDeltaRequestWithSessionIdIs42AndRecordAllActionsIsTrue() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{-84, -19, 0, 5, 'w', '\t', 0, 2, '4', '2', 1, 0, 0, 0, 0},
        (new DeltaRequest("42", true)).serialize());
  }

  /**
   * Test {@link DeltaRequest#serialize()}.
   * <ul>
   *   <li>Then return second element is minus nineteen.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeltaRequest#serialize()}
   */
  @Test
  public void testSerialize_thenReturnSecondElementIsMinusNineteen() throws IOException {
    // Arrange
    DeltaRequest deltaRequest = new DeltaRequest("42", true);
    deltaRequest.addSessionListener(new SingleSignOnListener("42"));

    // Act
    byte[] actualSerializeResult = deltaRequest.serialize();

    // Assert
    assertEquals((byte) -19, actualSerializeResult[1]);
    assertEquals((byte) -84, actualSerializeResult[0]);
    assertEquals((byte) 0, actualSerializeResult[11]);
    assertEquals((byte) 0, actualSerializeResult[144]);
    assertEquals((byte) 0, actualSerializeResult[17]);
    assertEquals((byte) 0, actualSerializeResult[19]);
    assertEquals((byte) 0, actualSerializeResult[2]);
    assertEquals((byte) 0, actualSerializeResult[20]);
    assertEquals((byte) 0, actualSerializeResult[21]);
    assertEquals((byte) 0, actualSerializeResult[22]);
    assertEquals((byte) 0, actualSerializeResult[23]);
    assertEquals((byte) 0, actualSerializeResult[6]);
    assertEquals((byte) 0, actualSerializeResult[SessionMessage.EVT_ALL_SESSION_DATA]);
    assertEquals((byte) 0, actualSerializeResult[SessionMessage.EVT_ALL_SESSION_NOCONTEXTMANAGER]);
    assertEquals((byte) 0, actualSerializeResult[SessionMessage.EVT_CHANGE_SESSION_ID]);
    assertEquals((byte) 0, actualSerializeResult[SessionMessage.EVT_SESSION_DELTA]);
    assertEquals(148, actualSerializeResult.length);
    assertEquals((byte) 17, actualSerializeResult[Float.PRECISION]);
    assertEquals((byte) 1, actualSerializeResult[10]);
    assertEquals((byte) 1, actualSerializeResult[SessionMessage.EVT_ALL_SESSION_TRANSFERCOMPLETE]);
    assertEquals((byte) 2, actualSerializeResult[145]);
    assertEquals((byte) 2, actualSerializeResult[7]);
    assertEquals((byte) 5, actualSerializeResult[18]);
    assertEquals((byte) 5, actualSerializeResult[3]);
    assertEquals('%', actualSerializeResult[5]);
    assertEquals('/', actualSerializeResult[128]);
    assertEquals('/', actualSerializeResult[133]);
    assertEquals('2', actualSerializeResult[147]);
    assertEquals('2', actualSerializeResult[9]);
    assertEquals('4', actualSerializeResult[146]);
    assertEquals('4', actualSerializeResult[8]);
    assertEquals(';', actualSerializeResult[140]);
    assertEquals('L', actualSerializeResult[123]);
    assertEquals('S', actualSerializeResult[134]);
    assertEquals('a', actualSerializeResult[125]);
    assertEquals('a', actualSerializeResult[130]);
    assertEquals('a', actualSerializeResult[Float.MAX_EXPONENT]);
    assertEquals('g', actualSerializeResult[132]);
    assertEquals('g', actualSerializeResult[139]);
    assertEquals('i', actualSerializeResult[137]);
    assertEquals('j', actualSerializeResult[124]);
    assertEquals('l', actualSerializeResult[129]);
    assertEquals('n', actualSerializeResult[131]);
    assertEquals('n', actualSerializeResult[138]);
    assertEquals('p', actualSerializeResult[142]);
    assertEquals('r', actualSerializeResult[136]);
    assertEquals('t', actualSerializeResult[135]);
    assertEquals('t', actualSerializeResult[143]);
    assertEquals('v', actualSerializeResult[126]);
    assertEquals('w', actualSerializeResult[4]);
    assertEquals('x', actualSerializeResult[141]);
  }
}
