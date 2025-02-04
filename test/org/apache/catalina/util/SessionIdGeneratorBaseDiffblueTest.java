package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SessionIdGeneratorBaseDiffblueTest {
  /**
   * Test {@link SessionIdGeneratorBase#getSecureRandomClass()}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#getSecureRandomClass()}
   */
  @Test
  public void testGetSecureRandomClass() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionIdGenerator()).getSecureRandomClass());
  }

  /**
   * Test {@link SessionIdGeneratorBase#setSecureRandomClass(String)}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#setSecureRandomClass(String)}
   */
  @Test
  public void testSetSecureRandomClass() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    standardSessionIdGenerator.setSecureRandomClass("Secure Random Class");

    // Assert
    assertEquals("Secure Random Class", standardSessionIdGenerator.getSecureRandomClass());
  }

  /**
   * Test {@link SessionIdGeneratorBase#getSecureRandomAlgorithm()}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#getSecureRandomAlgorithm()}
   */
  @Test
  public void testGetSecureRandomAlgorithm() {
    // Arrange, Act and Assert
    assertEquals(SessionIdGeneratorBase.DEFAULT_SECURE_RANDOM_ALGORITHM,
        (new StandardSessionIdGenerator()).getSecureRandomAlgorithm());
  }

  /**
   * Test {@link SessionIdGeneratorBase#setSecureRandomAlgorithm(String)}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#setSecureRandomAlgorithm(String)}
   */
  @Test
  public void testSetSecureRandomAlgorithm() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    standardSessionIdGenerator.setSecureRandomAlgorithm("Secure Random Algorithm");

    // Assert
    assertEquals("Secure Random Algorithm", standardSessionIdGenerator.getSecureRandomAlgorithm());
  }

  /**
   * Test {@link SessionIdGeneratorBase#getSecureRandomProvider()}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#getSecureRandomProvider()}
   */
  @Test
  public void testGetSecureRandomProvider() {
    // Arrange, Act and Assert
    assertNull((new StandardSessionIdGenerator()).getSecureRandomProvider());
  }

  /**
   * Test {@link SessionIdGeneratorBase#setSecureRandomProvider(String)}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#setSecureRandomProvider(String)}
   */
  @Test
  public void testSetSecureRandomProvider() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    standardSessionIdGenerator.setSecureRandomProvider("Secure Random Provider");

    // Assert
    assertEquals("Secure Random Provider", standardSessionIdGenerator.getSecureRandomProvider());
  }

  /**
   * Test {@link SessionIdGeneratorBase#getJvmRoute()}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#getJvmRoute()}
   */
  @Test
  public void testGetJvmRoute() {
    // Arrange, Act and Assert
    assertEquals("", (new StandardSessionIdGenerator()).getJvmRoute());
  }

  /**
   * Test {@link SessionIdGeneratorBase#setJvmRoute(String)}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#setJvmRoute(String)}
   */
  @Test
  public void testSetJvmRoute() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    standardSessionIdGenerator.setJvmRoute("Jvm Route");

    // Assert
    assertEquals("Jvm Route", standardSessionIdGenerator.getJvmRoute());
  }

  /**
   * Test {@link SessionIdGeneratorBase#getSessionIdLength()}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#getSessionIdLength()}
   */
  @Test
  public void testGetSessionIdLength() {
    // Arrange, Act and Assert
    assertEquals(Short.SIZE, (new StandardSessionIdGenerator()).getSessionIdLength());
  }

  /**
   * Test {@link SessionIdGeneratorBase#setSessionIdLength(int)}.
   * <p>
   * Method under test: {@link SessionIdGeneratorBase#setSessionIdLength(int)}
   */
  @Test
  public void testSetSessionIdLength() {
    // Arrange
    StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();

    // Act
    standardSessionIdGenerator.setSessionIdLength(1);

    // Assert
    assertEquals(1, standardSessionIdGenerator.getSessionIdLength());
  }
}
