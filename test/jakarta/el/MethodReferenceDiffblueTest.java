package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.lang.annotation.Annotation;
import org.junit.Test;

public class MethodReferenceDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodReference#MethodReference(Object, MethodInfo, Annotation[], Object[])}
   *   <li>{@link MethodReference#getAnnotations()}
   *   <li>{@link MethodReference#getBase()}
   *   <li>{@link MethodReference#getEvaluatedParameters()}
   *   <li>{@link MethodReference#getMethodInfo()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", returnType, new Class[]{forNameResult});

    Annotation[] annotations = new Annotation[]{null};
    Object[] evaluatedParameters = new Object[]{"Evaluated Parameters"};

    // Act
    MethodReference actualMethodReference = new MethodReference("Base", methodInfo, annotations, evaluatedParameters);
    Annotation[] actualAnnotations = actualMethodReference.getAnnotations();
    Object actualBase = actualMethodReference.getBase();
    Object[] actualEvaluatedParameters = actualMethodReference.getEvaluatedParameters();

    // Assert
    assertEquals("Base", actualBase);
    assertEquals("Evaluated Parameters", actualEvaluatedParameters[0]);
    assertNull(actualAnnotations[0]);
    assertEquals(1, actualAnnotations.length);
    assertEquals(1, actualEvaluatedParameters.length);
    assertSame(methodInfo, actualMethodReference.getMethodInfo());
    assertSame(evaluatedParameters, actualEvaluatedParameters);
    assertSame(annotations, actualAnnotations);
  }

  /**
   * Test {@link MethodReference#equals(Object)}, and {@link MethodReference#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodReference#equals(Object)}
   *   <li>{@link MethodReference#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference("Base",
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;
    MethodReference methodReference2 = new MethodReference("Base",
        new MethodInfo("Name", returnType2, new Class[]{forNameResult2}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});

    // Act and Assert
    assertEquals(methodReference, methodReference2);
    int expectedHashCodeResult = methodReference.hashCode();
    assertEquals(expectedHashCodeResult, methodReference2.hashCode());
  }

  /**
   * Test {@link MethodReference#equals(Object)}, and {@link MethodReference#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodReference#equals(Object)}
   *   <li>{@link MethodReference#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference(null,
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;
    MethodReference methodReference2 = new MethodReference(null,
        new MethodInfo("Name", returnType2, new Class[]{forNameResult2}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});

    // Act and Assert
    assertEquals(methodReference, methodReference2);
    int expectedHashCodeResult = methodReference.hashCode();
    assertEquals(expectedHashCodeResult, methodReference2.hashCode());
  }

  /**
   * Test {@link MethodReference#equals(Object)}, and {@link MethodReference#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodReference#equals(Object)}
   *   <li>{@link MethodReference#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    MethodReference methodReference = new MethodReference("Base", null, new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    MethodReference methodReference2 = new MethodReference("Base", null, new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});

    // Act and Assert
    assertEquals(methodReference, methodReference2);
    int expectedHashCodeResult = methodReference.hashCode();
    assertEquals(expectedHashCodeResult, methodReference2.hashCode());
  }

  /**
   * Test {@link MethodReference#equals(Object)}, and {@link MethodReference#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodReference#equals(Object)}
   *   <li>{@link MethodReference#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference("Base",
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});

    // Act and Assert
    assertEquals(methodReference, methodReference);
    int expectedHashCodeResult = methodReference.hashCode();
    assertEquals(expectedHashCodeResult, methodReference.hashCode());
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference(1,
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType2, new Class[]{forNameResult2}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference(null,
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType2, new Class[]{forNameResult2}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference("Base",
        new MethodInfo("Base", returnType, new Class[]{forNameResult}), new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType2, new Class[]{forNameResult2}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    MethodReference methodReference = new MethodReference("Base", null, new Annotation[]{null},
        new Object[]{"Evaluated Parameters"});
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType, new Class[]{forNameResult}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference("Base",
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), null, new Object[]{"Evaluated Parameters"});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType2, new Class[]{forNameResult2}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodReference methodReference = new MethodReference("Base",
        new MethodInfo("Name", returnType, new Class[]{forNameResult}), new Annotation[]{null}, new Object[]{42});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodReference,
        new MethodReference("Base", new MethodInfo("Name", returnType2, new Class[]{forNameResult2}),
            new Annotation[]{null}, new Object[]{"Evaluated Parameters"}));
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(new MethodReference("Base", new MethodInfo("Name", returnType, new Class[]{forNameResult}),
        new Annotation[]{null}, new Object[]{"Evaluated Parameters"}), null);
  }

  /**
   * Test {@link MethodReference#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReference#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(new MethodReference("Base", new MethodInfo("Name", returnType, new Class[]{forNameResult}),
        new Annotation[]{null}, new Object[]{"Evaluated Parameters"}), "Different type to MethodReference");
  }
}
