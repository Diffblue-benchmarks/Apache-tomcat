package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ExpressionTokenizerDiffblueTest {
  /**
   * Test {@link ExpressionTokenizer#ExpressionTokenizer(String)}.
   * <p>
   * Method under test: {@link ExpressionTokenizer#ExpressionTokenizer(String)}
   */
  @Test
  public void testNewExpressionTokenizer() {
    // Arrange and Act
    ExpressionTokenizer actualExpressionTokenizer = new ExpressionTokenizer("Expr");

    // Assert
    assertNull(actualExpressionTokenizer.getTokenValue());
    assertEquals(0, actualExpressionTokenizer.getIndex());
    assertTrue(actualExpressionTokenizer.hasMoreTokens());
  }

  /**
   * Test {@link ExpressionTokenizer#hasMoreTokens()}.
   * <ul>
   *   <li>Given {@link ExpressionTokenizer#ExpressionTokenizer(String)} with expr is empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#hasMoreTokens()}
   */
  @Test
  public void testHasMoreTokens_givenExpressionTokenizerWithExprIsEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ExpressionTokenizer("")).hasMoreTokens());
  }

  /**
   * Test {@link ExpressionTokenizer#hasMoreTokens()}.
   * <ul>
   *   <li>Given {@link ExpressionTokenizer#ExpressionTokenizer(String)} with {@code Expr}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#hasMoreTokens()}
   */
  @Test
  public void testHasMoreTokens_givenExpressionTokenizerWithExpr_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).hasMoreTokens());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ExpressionTokenizer#getIndex()}
   *   <li>{@link ExpressionTokenizer#getTokenValue()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer("Expr");

    // Act
    int actualIndex = expressionTokenizer.getIndex();

    // Assert
    assertNull(expressionTokenizer.getTokenValue());
    assertEquals(0, actualIndex);
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenA_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ExpressionTokenizer("Expr")).isMetaChar('A'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code &}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenAmpersand_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('&'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code =}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenEqualsSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('='));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code !}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenExclamationMark_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('!'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code >}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenGreaterThanSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('>'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code (}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenLeftParenthesis_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('('));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code <}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenLessThanSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('<'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code )}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenRightParenthesis_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar(')'));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When space.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenSpace_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar(' '));
  }

  /**
   * Test {@link ExpressionTokenizer#isMetaChar(char)}.
   * <ul>
   *   <li>When {@code |}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#isMetaChar(char)}
   */
  @Test
  public void testIsMetaChar_whenVerticalLine_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ExpressionTokenizer("Expr")).isMetaChar('|'));
  }

  /**
   * Test {@link ExpressionTokenizer#nextToken()}.
   * <ul>
   *   <li>Then {@link ExpressionTokenizer#ExpressionTokenizer(String)} with expr is empty string TokenValue is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#nextToken()}
   */
  @Test
  public void testNextToken_thenExpressionTokenizerWithExprIsEmptyStringTokenValueIsNull() {
    // Arrange
    ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer("");

    // Act
    int actualNextTokenResult = expressionTokenizer.nextToken();

    // Assert
    assertNull(expressionTokenizer.getTokenValue());
    assertEquals(0, expressionTokenizer.getIndex());
    assertFalse(expressionTokenizer.hasMoreTokens());
    assertEquals(ExpressionTokenizer.TOKEN_END, actualNextTokenResult);
  }

  /**
   * Test {@link ExpressionTokenizer#nextToken()}.
   * <ul>
   *   <li>Then {@link ExpressionTokenizer#ExpressionTokenizer(String)} with {@code Expr} TokenValue is {@code Expr}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpressionTokenizer#nextToken()}
   */
  @Test
  public void testNextToken_thenExpressionTokenizerWithExprTokenValueIsExpr() {
    // Arrange
    ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer("Expr");

    // Act
    int actualNextTokenResult = expressionTokenizer.nextToken();

    // Assert
    assertEquals("Expr", expressionTokenizer.getTokenValue());
    assertEquals(0, actualNextTokenResult);
    assertEquals(4, expressionTokenizer.getIndex());
    assertFalse(expressionTokenizer.hasMoreTokens());
  }
}
