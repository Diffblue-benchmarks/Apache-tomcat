package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class ImportHandlerDiffblueTest {
  /**
   * Test new {@link ImportHandler} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ImportHandler}
   */
  @Test
  public void testNewImportHandler() {
    // Arrange and Act
    ImportHandler actualImportHandler = new ImportHandler();

    // Assert
    assertNull(actualImportHandler.resolveClass("Name"));
    assertNull(actualImportHandler.resolveStatic("Name"));
  }

  /**
   * Test {@link ImportHandler#importStatic(String)}.
   * <ul>
   *   <li>When {@code importHandler.invalidStaticName}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#importStatic(String)}
   */
  @Test
  public void testImportStatic_whenImportHandlerInvalidStaticName() throws ELException {
    // Arrange, Act and Assert
    assertThrows(ELException.class, () -> (new ImportHandler()).importStatic("importHandler.invalidStaticName"));
  }

  /**
   * Test {@link ImportHandler#importStatic(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#importStatic(String)}
   */
  @Test
  public void testImportStatic_whenName() throws ELException {
    // Arrange, Act and Assert
    assertThrows(ELException.class, () -> (new ImportHandler()).importStatic("Name"));
  }

  /**
   * Test {@link ImportHandler#importClass(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then throw {@link ELException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#importClass(String)}
   */
  @Test
  public void testImportClass_whenName_thenThrowELException() throws ELException {
    // Arrange, Act and Assert
    assertThrows(ELException.class, () -> (new ImportHandler()).importClass("Name"));
  }

  /**
   * Test {@link ImportHandler#resolveClass(String)}.
   * <ul>
   *   <li>When {@code AbstractMethodError}.</li>
   *   <li>Then return {@link AbstractMethodError}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#resolveClass(String)}
   */
  @Test
  public void testResolveClass_whenAbstractMethodError_thenReturnAbstractMethodError() {
    // Arrange and Act
    Class<?> actualResolveClassResult = (new ImportHandler()).resolveClass("AbstractMethodError");

    // Assert
    Class<AbstractMethodError> expectedResolveClassResult = AbstractMethodError.class;
    assertEquals(expectedResolveClassResult, actualResolveClassResult);
  }

  /**
   * Test {@link ImportHandler#resolveClass(String)}.
   * <ul>
   *   <li>When {@code Appendable}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#resolveClass(String)}
   */
  @Test
  public void testResolveClass_whenAppendable_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ImportHandler()).resolveClass("Appendable"));
  }

  /**
   * Test {@link ImportHandler#resolveClass(String)}.
   * <ul>
   *   <li>When {@code .}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#resolveClass(String)}
   */
  @Test
  public void testResolveClass_whenDot_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ImportHandler()).resolveClass("."));
  }

  /**
   * Test {@link ImportHandler#resolveClass(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#resolveClass(String)}
   */
  @Test
  public void testResolveClass_whenName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ImportHandler()).resolveClass("Name"));
  }

  /**
   * Test {@link ImportHandler#resolveClass(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ImportHandler#resolveClass(String)}
   */
  @Test
  public void testResolveClass_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ImportHandler()).resolveClass(null));
  }

  /**
   * Test {@link ImportHandler#resolveStatic(String)}.
   * <p>
   * Method under test: {@link ImportHandler#resolveStatic(String)}
   */
  @Test
  public void testResolveStatic() {
    // Arrange, Act and Assert
    assertNull((new ImportHandler()).resolveStatic("Name"));
  }
}
