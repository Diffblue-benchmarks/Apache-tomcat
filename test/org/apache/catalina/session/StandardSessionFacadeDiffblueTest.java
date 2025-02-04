package org.apache.catalina.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.ha.session.DeltaSession;
import org.junit.Test;

public class StandardSessionFacadeDiffblueTest {
  /**
   * Test {@link StandardSessionFacade#StandardSessionFacade(HttpSession)}.
   * <p>
   * Method under test: {@link StandardSessionFacade#StandardSessionFacade(HttpSession)}
   */
  @Test
  public void testNewStandardSessionFacade() {
    // Arrange and Act
    StandardSessionFacade actualStandardSessionFacade = new StandardSessionFacade(new DeltaSession());

    // Assert
    assertNull(actualStandardSessionFacade.getServletContext());
    assertNull(actualStandardSessionFacade.getId());
    assertEquals(-1, actualStandardSessionFacade.getMaxInactiveInterval());
  }

  /**
   * Test {@link StandardSessionFacade#getCreationTime()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getCreationTime()}
   */
  @Test
  public void testGetCreationTime_givenDeltaSessionValidIsTrue_thenReturnZero() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertEquals(0L, (new StandardSessionFacade(session)).getCreationTime());
  }

  /**
   * Test {@link StandardSessionFacade#getCreationTime()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getCreationTime()}
   */
  @Test
  public void testGetCreationTime_givenDeltaSessionValidIsTrue_thenReturnZero2() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertEquals(0L, (new StandardSessionFacade(new StandardSessionFacade(session))).getCreationTime());
  }

  /**
   * Test {@link StandardSessionFacade#getId()}.
   * <ul>
   *   <li>Given {@link StandardSessionFacade#StandardSessionFacade(HttpSession)} with session is {@link DeltaSession#DeltaSession()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getId()}
   */
  @Test
  public void testGetId_givenStandardSessionFacadeWithSessionIsDeltaSession_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionFacade(new DeltaSession())).getId());
  }

  /**
   * Test {@link StandardSessionFacade#getId()}.
   * <ul>
   *   <li>Given {@link StandardSessionFacade#StandardSessionFacade(HttpSession)} with session is {@link StandardSessionFacade#StandardSessionFacade(HttpSession)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getId()}
   */
  @Test
  public void testGetId_givenStandardSessionFacadeWithSessionIsStandardSessionFacade() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionFacade(new StandardSessionFacade(new DeltaSession()))).getId());
  }

  /**
   * Test {@link StandardSessionFacade#getLastAccessedTime()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getLastAccessedTime()}
   */
  @Test
  public void testGetLastAccessedTime_givenDeltaSessionValidIsTrue_thenReturnZero() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertEquals(0L, (new StandardSessionFacade(session)).getLastAccessedTime());
  }

  /**
   * Test {@link StandardSessionFacade#getLastAccessedTime()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getLastAccessedTime()}
   */
  @Test
  public void testGetLastAccessedTime_givenDeltaSessionValidIsTrue_thenReturnZero2() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertEquals(0L, (new StandardSessionFacade(new StandardSessionFacade(session))).getLastAccessedTime());
  }

  /**
   * Test {@link StandardSessionFacade#getServletContext()}.
   * <p>
   * Method under test: {@link StandardSessionFacade#getServletContext()}
   */
  @Test
  public void testGetServletContext() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionFacade(new StandardSessionFacade(new DeltaSession()))).getServletContext());
  }

  /**
   * Test {@link StandardSessionFacade#getServletContext()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getServletContext()}
   */
  @Test
  public void testGetServletContext_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionFacade(new DeltaSession())).getServletContext());
  }

  /**
   * Test {@link StandardSessionFacade#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link StandardSessionFacade#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval() {
    // Arrange
    StandardSessionFacade standardSessionFacade = new StandardSessionFacade(new DeltaSession());

    // Act
    standardSessionFacade.setMaxInactiveInterval(42);

    // Assert
    assertEquals(42, standardSessionFacade.getMaxInactiveInterval());
  }

  /**
   * Test {@link StandardSessionFacade#setMaxInactiveInterval(int)}.
   * <p>
   * Method under test: {@link StandardSessionFacade#setMaxInactiveInterval(int)}
   */
  @Test
  public void testSetMaxInactiveInterval2() {
    // Arrange
    StandardSessionFacade standardSessionFacade = new StandardSessionFacade(
        new StandardSessionFacade(new DeltaSession()));

    // Act
    standardSessionFacade.setMaxInactiveInterval(42);

    // Assert
    assertEquals(42, standardSessionFacade.getMaxInactiveInterval());
  }

  /**
   * Test {@link StandardSessionFacade#getMaxInactiveInterval()}.
   * <p>
   * Method under test: {@link StandardSessionFacade#getMaxInactiveInterval()}
   */
  @Test
  public void testGetMaxInactiveInterval() {
    // Arrange, Act and Assert
    assertEquals(-1,
        (new StandardSessionFacade(new StandardSessionFacade(new DeltaSession()))).getMaxInactiveInterval());
  }

  /**
   * Test {@link StandardSessionFacade#getMaxInactiveInterval()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getMaxInactiveInterval()}
   */
  @Test
  public void testGetMaxInactiveInterval_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new StandardSessionFacade(new DeltaSession())).getMaxInactiveInterval());
  }

  /**
   * Test {@link StandardSessionFacade#getAttribute(String)}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_givenDeltaSessionValidIsTrue_thenReturnNull() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertNull((new StandardSessionFacade(session)).getAttribute("Name"));
  }

  /**
   * Test {@link StandardSessionFacade#getAttribute(String)}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_givenDeltaSessionValidIsTrue_thenReturnNull2() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertNull((new StandardSessionFacade(new StandardSessionFacade(session))).getAttribute("Name"));
  }

  /**
   * Test {@link StandardSessionFacade#isNew()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} New {@code true} is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#isNew()}
   */
  @Test
  public void testIsNew_givenDeltaSessionNewTrueIsTrue_thenReturnTrue() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setNew(true, true);
    session.setValid(true);

    // Act and Assert
    assertTrue((new StandardSessionFacade(session)).isNew());
  }

  /**
   * Test {@link StandardSessionFacade#isNew()}.
   * <ul>
   *   <li>Given {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#isNew()}
   */
  @Test
  public void testIsNew_givenDeltaSessionValidIsTrue_thenReturnFalse() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertFalse((new StandardSessionFacade(session)).isNew());
  }

  /**
   * Test {@link StandardSessionFacade#isNew()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardSessionFacade#isNew()}
   */
  @Test
  public void testIsNew_thenReturnFalse() {
    // Arrange
    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertFalse((new StandardSessionFacade(new StandardSessionFacade(session))).isNew());
  }
}
