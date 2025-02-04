package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.CredentialHandler;
import org.junit.Test;

public class NestedCredentialHandlerDiffblueTest {
  /**
   * Test {@link NestedCredentialHandler#matches(String, String)}.
   * <p>
   * Method under test: {@link NestedCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches() {
    // Arrange
    NestedCredentialHandler nestedCredentialHandler = new NestedCredentialHandler();
    nestedCredentialHandler.addCredentialHandler(new MessageDigestCredentialHandler());

    // Act and Assert
    assertFalse(nestedCredentialHandler.matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link NestedCredentialHandler#matches(String, String)}.
   * <p>
   * Method under test: {@link NestedCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches2() {
    // Arrange
    NestedCredentialHandler nestedCredentialHandler = new NestedCredentialHandler();
    nestedCredentialHandler.addCredentialHandler(new NestedCredentialHandler());

    // Act and Assert
    assertFalse(nestedCredentialHandler.matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link NestedCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>Given {@link NestedCredentialHandler} (default constructor).</li>
   *   <li>When {@code Input Credentials}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestedCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_givenNestedCredentialHandler_whenInputCredentials_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NestedCredentialHandler()).matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link NestedCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When {@code CredentialHandler}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestedCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenOrgApacheCatalinaCredentialHandler_thenReturnTrue() {
    // Arrange
    NestedCredentialHandler nestedCredentialHandler = new NestedCredentialHandler();
    nestedCredentialHandler.addCredentialHandler(new MessageDigestCredentialHandler());

    // Act and Assert
    assertTrue(nestedCredentialHandler.matches("org.apache.catalina.CredentialHandler",
        "org.apache.catalina.CredentialHandler"));
  }

  /**
   * Test {@link NestedCredentialHandler#mutate(String)}.
   * <p>
   * Method under test: {@link NestedCredentialHandler#mutate(String)}
   */
  @Test
  public void testMutate() {
    // Arrange
    NestedCredentialHandler nestedCredentialHandler = new NestedCredentialHandler();
    nestedCredentialHandler.addCredentialHandler(new NestedCredentialHandler());

    // Act and Assert
    assertNull(nestedCredentialHandler.mutate("Input Credentials"));
  }

  /**
   * Test {@link NestedCredentialHandler#mutate(String)}.
   * <ul>
   *   <li>Given {@link NestedCredentialHandler} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestedCredentialHandler#mutate(String)}
   */
  @Test
  public void testMutate_givenNestedCredentialHandler_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new NestedCredentialHandler()).mutate("Input Credentials"));
  }

  /**
   * Test {@link NestedCredentialHandler#addCredentialHandler(CredentialHandler)}.
   * <p>
   * Method under test: {@link NestedCredentialHandler#addCredentialHandler(CredentialHandler)}
   */
  @Test
  public void testAddCredentialHandler() {
    // Arrange
    NestedCredentialHandler nestedCredentialHandler = new NestedCredentialHandler();
    MessageDigestCredentialHandler handler = new MessageDigestCredentialHandler();

    // Act
    nestedCredentialHandler.addCredentialHandler(handler);

    // Assert
    CredentialHandler[] credentialHandlers = nestedCredentialHandler.getCredentialHandlers();
    assertEquals(1, credentialHandlers.length);
    assertSame(handler, credentialHandlers[0]);
  }

  /**
   * Test {@link NestedCredentialHandler#getCredentialHandlers()}.
   * <p>
   * Method under test: {@link NestedCredentialHandler#getCredentialHandlers()}
   */
  @Test
  public void testGetCredentialHandlers() {
    // Arrange, Act and Assert
    assertEquals(0, (new NestedCredentialHandler()).getCredentialHandlers().length);
  }

  /**
   * Test new {@link NestedCredentialHandler} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NestedCredentialHandler}
   */
  @Test
  public void testNewNestedCredentialHandler() {
    // Arrange, Act and Assert
    assertEquals(0, (new NestedCredentialHandler()).getCredentialHandlers().length);
  }
}
