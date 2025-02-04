package org.apache.catalina.ha.session;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class SessionMessageImplDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SessionMessageImpl#SessionMessageImpl(String, int, byte[], String, String)}
   *   <li>{@link SessionMessageImpl#toString()}
   *   <li>{@link SessionMessageImpl#getContextName()}
   *   <li>{@link SessionMessageImpl#getEventType()}
   *   <li>{@link SessionMessageImpl#getSession()}
   *   <li>{@link SessionMessageImpl#getSessionID()}
   *   <li>{@link SessionMessageImpl#getTimestamp()}
   *   <li>{@link SessionMessageImpl#getUniqueId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] session = "AXAXAXAX".getBytes("UTF-8");

    // Act
    SessionMessageImpl actualSessionMessageImpl = new SessionMessageImpl("Context Name", 1, session, "Session ID",
        "Unique ID");
    String actualToStringResult = actualSessionMessageImpl.toString();
    String actualContextName = actualSessionMessageImpl.getContextName();
    int actualEventType = actualSessionMessageImpl.getEventType();
    byte[] actualSession = actualSessionMessageImpl.getSession();
    String actualSessionID = actualSessionMessageImpl.getSessionID();
    long actualTimestamp = actualSessionMessageImpl.getTimestamp();

    // Assert
    assertEquals("Context Name", actualContextName);
    assertEquals("SESSION-MODIFIED#Context Name#Session ID", actualToStringResult);
    assertEquals("Session ID", actualSessionID);
    assertEquals("Unique ID", actualSessionMessageImpl.getUniqueId());
    assertNull(actualSessionMessageImpl.getAddress());
    assertEquals(0L, actualTimestamp);
    assertEquals(1, actualEventType);
    assertSame(session, actualSession);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualSession);
  }

  /**
   * Test {@link SessionMessageImpl#setTimestamp(long)}.
   * <p>
   * Method under test: {@link SessionMessageImpl#setTimestamp(long)}
   */
  @Test
  public void testSetTimestamp() throws UnsupportedEncodingException {
    // Arrange
    SessionMessageImpl sessionMessageImpl = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"),
        "Session ID", "Unique ID");

    // Act
    sessionMessageImpl.setTimestamp(10L);

    // Assert
    assertEquals(10L, sessionMessageImpl.getTimestamp());
  }

  /**
   * Test {@link SessionMessageImpl#getEventTypeString()}.
   * <ul>
   *   <li>Then return {@code SESSION-ACCESSED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionMessageImpl#getEventTypeString()}
   */
  @Test
  public void testGetEventTypeString_thenReturnSessionAccessed() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("SESSION-ACCESSED",
        (new SessionMessageImpl("Context Name", 3, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
            .getEventTypeString());
  }

  /**
   * Test {@link SessionMessageImpl#getEventTypeString()}.
   * <ul>
   *   <li>Then return {@code SESSION-EXPIRED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionMessageImpl#getEventTypeString()}
   */
  @Test
  public void testGetEventTypeString_thenReturnSessionExpired() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("SESSION-EXPIRED",
        (new SessionMessageImpl("Context Name", 2, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
            .getEventTypeString());
  }

  /**
   * Test {@link SessionMessageImpl#getEventTypeString()}.
   * <ul>
   *   <li>Then return {@code SESSION-GET-ALL}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionMessageImpl#getEventTypeString()}
   */
  @Test
  public void testGetEventTypeString_thenReturnSessionGetAll() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("SESSION-GET-ALL",
        (new SessionMessageImpl("Context Name", 4, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
            .getEventTypeString());
  }

  /**
   * Test {@link SessionMessageImpl#getEventTypeString()}.
   * <ul>
   *   <li>Then return {@code SESSION-MODIFIED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionMessageImpl#getEventTypeString()}
   */
  @Test
  public void testGetEventTypeString_thenReturnSessionModified() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("SESSION-MODIFIED",
        (new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
            .getEventTypeString());
  }

  /**
   * Test {@link SessionMessageImpl#getEventTypeString()}.
   * <ul>
   *   <li>Then return {@code UNKNOWN-EVENT-TYPE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SessionMessageImpl#getEventTypeString()}
   */
  @Test
  public void testGetEventTypeString_thenReturnUnknownEventType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("UNKNOWN-EVENT-TYPE",
        (new SessionMessageImpl("Context Name", 5, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
            .getEventTypeString());
  }
}
