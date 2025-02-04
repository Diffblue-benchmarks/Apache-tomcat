package jakarta.el;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.el.TesterEvaluationListener.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

public class ListELResolverDiffblueTest {
  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_given42_whenArrayListAdd42_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertNull(listELResolver.getType(context, objectList, false));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link ListELResolver#ListELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_givenListELResolverWithReadOnlyIsFalse_thenReturnObject() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act
    Class<?> actualType = listELResolver.getType(context, objectList, false);

    // Assert
    assertTrue(context.isPropertyResolved());
    Class<Object> expectedType = Object.class;
    assertEquals(expectedType, actualType);
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_when42_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), "42"));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenA_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), (byte) 'A'));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();
    ArrayList<Object> objectList = new ArrayList<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> listELResolver.getType(context, objectList, new ArrayList<>()));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getType(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenFalse_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), false));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenMinusOne_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), -1));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> listELResolver.getType(context, new ArrayList<>(), null));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When start of heading.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenStartOfHeading_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), '\u0001'));
  }

  /**
   * Test {@link ListELResolver#getType(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getType(ELContext, Object, Object)}
   */
  @Test
  public void testGetType_whenTrue_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.getType(context, new ArrayList<>(), true));
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals("42", listELResolver.getValue(context, objectList, false));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenFalse_whenArrayListAddFalse_thenReturnFalse() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(false);

    // Act and Assert
    assertFalse((Boolean) listELResolver.getValue(context, objectList, false));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_givenTrue_whenArrayListAddTrue_thenReturnTrue() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(true);

    // Act
    Object actualValue = listELResolver.getValue(context, objectList, false);

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue((Boolean) actualValue);
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_when42_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), "42"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenA_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), (byte) 'A'));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();
    ArrayList<Object> objectList = new ArrayList<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> listELResolver.getValue(context, objectList, new ArrayList<>()));
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, "Base", "Property"));
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenFalse_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), false));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenMinusOne_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), -1));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> listELResolver.getValue(context, new ArrayList<>(), null));
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When start of heading.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenStartOfHeading_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), '\u0001'));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#getValue(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getValue(ELContext, Object, Object)}
   */
  @Test
  public void testGetValue_whenTrue_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertNull(listELResolver.getValue(context, new ArrayList<>(), true));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_given42_whenArrayListAdd42_thenArrayListSizeIsOne() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act
    listELResolver.setValue(context, objectList, false, "Value");

    // Assert
    assertEquals(1, objectList.size());
    assertEquals("Value", objectList.get(0));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given Default.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} Locale is Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenDefault_whenELContextImplLocaleIsDefault() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.setLocale(Locale.getDefault());
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> listELResolver.setValue(context, new ArrayList<>(), "Property", "Value"));
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> listELResolver.setValue(context, new ArrayList<>(), "Property", "Value"));
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Given {@link TesterEvaluationListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_givenTesterEvaluationListener() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> listELResolver.setValue(context, new ArrayList<>(), "Property", "Value"));
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();
    ArrayList<Object> objectList = new ArrayList<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> listELResolver.setValue(context, objectList, new ArrayList<>(), "Value"));
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>Then throw {@link PropertyNotWritableException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_thenThrowPropertyNotWritableException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotWritableException.class,
        () -> listELResolver.setValue(context, new ArrayList<>(), "Property", "Value"));
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    listELResolver.setValue(context, "Base", "Property", "Value");

    // Assert that nothing has changed
    assertFalse(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#setValue(ELContext, Object, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#setValue(ELContext, Object, Object, Object)}
   */
  @Test
  public void testSetValue_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> listELResolver.setValue(context, new ArrayList<>(), null, "Value"));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_given42_whenArrayListAdd42_thenELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, objectList, false);

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@link ListELResolver#ListELResolver(boolean)} with readOnly is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenListELResolverWithReadOnlyIsFalse_thenReturnFalse() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertFalse(listELResolver.isReadOnly(context, new ArrayList<>(), "Property"));
    assertTrue(context.isPropertyResolved());
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ELContextImpl#ELContextImpl()} addEvaluationListener {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_givenNull_whenELContextImplAddEvaluationListenerNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(null);

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, new ArrayList<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} EvaluationListeners size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenELContextImplEvaluationListenersSizeIsOne() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    ELContextImpl context = new ELContextImpl();
    context.addEvaluationListener(new TesterEvaluationListener());
    ArrayList<Object> objectList = new ArrayList<>();

    // Act
    listELResolver.isReadOnly(context, objectList, "Property");

    // Assert
    List<EvaluationListener> evaluationListeners = context.getEvaluationListeners();
    assertEquals(1, evaluationListeners.size());
    EvaluationListener getResult = evaluationListeners.get(0);
    assertTrue(getResult instanceof TesterEvaluationListener);
    List<Pair> resolvedProperties = ((TesterEvaluationListener) getResult).getResolvedProperties();
    assertEquals(1, resolvedProperties.size());
    Pair getResult2 = resolvedProperties.get(0);
    assertEquals("Property", getResult2.getProperty());
    assertSame(objectList, getResult2.getBase());
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_thenNotELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(false);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_when42_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.isReadOnly(context, new ArrayList<>(), "42"));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenA_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> listELResolver.isReadOnly(context, new ArrayList<>(), (byte) 'A'));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenArrayList_thenELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();
    ArrayList<Object> objectList = new ArrayList<>();

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, objectList, new ArrayList<>());

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then not {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenBase_thenNotELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, "Base", "Property");

    // Assert
    assertFalse(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenFalse_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.isReadOnly(context, new ArrayList<>(), false));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenMinusOne_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.isReadOnly(context, new ArrayList<>(), -1));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenNull_thenELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, new ArrayList<>(), null);

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code Property}.</li>
   *   <li>Then {@link ELContextImpl#ELContextImpl()} PropertyResolved.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenProperty_thenELContextImplPropertyResolved() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    boolean actualIsReadOnlyResult = listELResolver.isReadOnly(context, new ArrayList<>(), "Property");

    // Assert
    assertTrue(context.isPropertyResolved());
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When start of heading.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenStartOfHeading_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class,
        () -> listELResolver.isReadOnly(context, new ArrayList<>(), '\u0001'));
  }

  /**
   * Test {@link ListELResolver#isReadOnly(ELContext, Object, Object)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then throw {@link PropertyNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#isReadOnly(ELContext, Object, Object)}
   */
  @Test
  public void testIsReadOnly_whenTrue_thenThrowPropertyNotFoundException() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act and Assert
    assertThrows(PropertyNotFoundException.class, () -> listELResolver.isReadOnly(context, new ArrayList<>(), true));
  }

  /**
   * Test {@link ListELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link Integer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenArrayList_thenReturnInteger() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);
    ELContextImpl context = new ELContextImpl();

    // Act
    Class<?> actualCommonPropertyType = listELResolver.getCommonPropertyType(context, new ArrayList<>());

    // Assert
    Class<Integer> expectedCommonPropertyType = Integer.class;
    assertEquals(expectedCommonPropertyType, actualCommonPropertyType);
  }

  /**
   * Test {@link ListELResolver#getCommonPropertyType(ELContext, Object)}.
   * <ul>
   *   <li>When {@code Base}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListELResolver#getCommonPropertyType(ELContext, Object)}
   */
  @Test
  public void testGetCommonPropertyType_whenBase_thenReturnNull() {
    // Arrange
    ListELResolver listELResolver = new ListELResolver(true);

    // Act and Assert
    assertNull(listELResolver.getCommonPropertyType(new ELContextImpl(), "Base"));
  }
}
