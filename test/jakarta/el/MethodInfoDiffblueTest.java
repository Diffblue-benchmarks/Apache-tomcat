package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class MethodInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodInfo#MethodInfo(String, Class, Class[])}
   *   <li>{@link MethodInfo#getName()}
   *   <li>{@link MethodInfo#getParamTypes()}
   *   <li>{@link MethodInfo#getReturnType()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    Class<?>[] paramTypes = new Class[]{forNameResult};

    // Act
    MethodInfo actualMethodInfo = new MethodInfo("Name", returnType, paramTypes);
    String actualName = actualMethodInfo.getName();
    Class<?>[] actualParamTypes = actualMethodInfo.getParamTypes();

    // Assert
    assertEquals("Name", actualName);
    assertSame(forNameResult, actualMethodInfo.getReturnType());
    assertSame(paramTypes, actualParamTypes);
  }

  /**
   * Test {@link MethodInfo#equals(Object)}, and {@link MethodInfo#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodInfo#equals(Object)}
   *   <li>{@link MethodInfo#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", returnType, new Class[]{forNameResult});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;
    MethodInfo methodInfo2 = new MethodInfo("Name", returnType2, new Class[]{forNameResult2});

    // Act and Assert
    assertEquals(methodInfo, methodInfo2);
    int expectedHashCodeResult = methodInfo.hashCode();
    assertEquals(expectedHashCodeResult, methodInfo2.hashCode());
  }

  /**
   * Test {@link MethodInfo#equals(Object)}, and {@link MethodInfo#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodInfo#equals(Object)}
   *   <li>{@link MethodInfo#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo(null, returnType, new Class[]{forNameResult});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;
    MethodInfo methodInfo2 = new MethodInfo(null, returnType2, new Class[]{forNameResult2});

    // Act and Assert
    assertEquals(methodInfo, methodInfo2);
    int expectedHashCodeResult = methodInfo.hashCode();
    assertEquals(expectedHashCodeResult, methodInfo2.hashCode());
  }

  /**
   * Test {@link MethodInfo#equals(Object)}, and {@link MethodInfo#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodInfo#equals(Object)}
   *   <li>{@link MethodInfo#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", null, new Class[]{forNameResult});
    Class<Object> forNameResult2 = Object.class;
    MethodInfo methodInfo2 = new MethodInfo("Name", null, new Class[]{forNameResult2});

    // Act and Assert
    assertEquals(methodInfo, methodInfo2);
    int expectedHashCodeResult = methodInfo.hashCode();
    assertEquals(expectedHashCodeResult, methodInfo2.hashCode());
  }

  /**
   * Test {@link MethodInfo#equals(Object)}, and {@link MethodInfo#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MethodInfo#equals(Object)}
   *   <li>{@link MethodInfo#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", returnType, new Class[]{forNameResult});

    // Act and Assert
    assertEquals(methodInfo, methodInfo);
    int expectedHashCodeResult = methodInfo.hashCode();
    assertEquals(expectedHashCodeResult, methodInfo.hashCode());
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo(null, returnType, new Class[]{forNameResult});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodInfo, new MethodInfo("Name", returnType2, new Class[]{forNameResult2}));
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("42", returnType, new Class[]{forNameResult});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodInfo, new MethodInfo("Name", returnType2, new Class[]{forNameResult2}));
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", null, new Class[]{forNameResult});
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodInfo, new MethodInfo("Name", returnType, new Class[]{forNameResult2}));
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Class<Object> returnType = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", returnType, new Class[]{null});
    Class<Object> returnType2 = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(methodInfo, new MethodInfo("Name", returnType2, new Class[]{forNameResult}));
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;
    MethodInfo methodInfo = new MethodInfo("Name", returnType, new Class[]{forNameResult});
    Class<Object> forNameResult2 = Object.class;

    // Act and Assert
    assertNotEquals(methodInfo, new MethodInfo("Name", null, new Class[]{forNameResult2}));
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(new MethodInfo("Name", returnType, new Class[]{forNameResult}), null);
  }

  /**
   * Test {@link MethodInfo#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInfo#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Class<Object> returnType = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertNotEquals(new MethodInfo("Name", returnType, new Class[]{forNameResult}), "Different type to MethodInfo");
  }
}
