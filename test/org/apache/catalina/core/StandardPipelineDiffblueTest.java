package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Valve;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.junit.Test;

public class StandardPipelineDiffblueTest {
  /**
   * Test {@link StandardPipeline#StandardPipeline()}.
   * <p>
   * Method under test: {@link StandardPipeline#StandardPipeline()}
   */
  @Test
  public void testNewStandardPipeline() {
    // Arrange and Act
    StandardPipeline actualStandardPipeline = new StandardPipeline();

    // Assert
    assertEquals("NEW", actualStandardPipeline.getStateName());
    assertNull(actualStandardPipeline.getContainer());
    assertNull(actualStandardPipeline.getBasic());
    assertNull(actualStandardPipeline.getFirst());
    assertNull(actualStandardPipeline.first);
    assertEquals(0, actualStandardPipeline.getValveObjectNames().length);
    assertEquals(0, actualStandardPipeline.getValves().length);
    assertEquals(0, actualStandardPipeline.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualStandardPipeline.getState());
    assertTrue(actualStandardPipeline.isAsyncSupported());
    assertTrue(actualStandardPipeline.getThrowOnFailure());
  }

  /**
   * Test {@link StandardPipeline#StandardPipeline(Container)}.
   * <p>
   * Method under test: {@link StandardPipeline#StandardPipeline(Container)}
   */
  @Test
  public void testNewStandardPipeline2() {
    // Arrange
    StandardContext container = new StandardContext();

    // Act
    StandardPipeline actualStandardPipeline = new StandardPipeline(container);

    // Assert
    Container container2 = actualStandardPipeline.getContainer();
    assertTrue(container2 instanceof StandardContext);
    assertEquals("NEW", actualStandardPipeline.getStateName());
    assertNull(actualStandardPipeline.getBasic());
    assertNull(actualStandardPipeline.getFirst());
    assertNull(actualStandardPipeline.first);
    assertEquals(0, actualStandardPipeline.getValveObjectNames().length);
    assertEquals(0, actualStandardPipeline.getValves().length);
    assertEquals(0, actualStandardPipeline.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualStandardPipeline.getState());
    assertTrue(actualStandardPipeline.isAsyncSupported());
    assertTrue(actualStandardPipeline.getThrowOnFailure());
    assertSame(container, container2);
  }

  /**
   * Test {@link StandardPipeline#isAsyncSupported()}.
   * <p>
   * Method under test: {@link StandardPipeline#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());

    // Act and Assert
    assertTrue(standardPipeline.isAsyncSupported());
  }

  /**
   * Test {@link StandardPipeline#isAsyncSupported()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported_givenStandardPipeline() {
    // Arrange, Act and Assert
    assertTrue((new StandardPipeline()).isAsyncSupported());
  }

  /**
   * Test {@link StandardPipeline#isAsyncSupported()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()} Basic is {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#isAsyncSupported()}
   */
  @Test
  public void testIsAsyncSupported_givenStandardPipelineBasicIsBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    standardPipeline.setBasic(new BasicAuthenticator());

    // Act and Assert
    assertTrue(standardPipeline.isAsyncSupported());
  }

  /**
   * Test {@link StandardPipeline#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardPipeline#destroyInternal()}
   */
  @Test
  public void testDestroyInternal() {
    // Arrange
    BasicAuthenticator valve = new BasicAuthenticator();
    valve.addLifecycleListener(new AprLifecycleListener());

    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.setBasic(valve);

    // Act
    standardPipeline.destroyInternal();

    // Assert
    Valve basic = standardPipeline.getBasic();
    assertTrue(basic instanceof BasicAuthenticator);
    assertEquals("DESTROYED", ((BasicAuthenticator) basic).getStateName());
    assertNull(((BasicAuthenticator) basic).getDomainInternal());
    assertNull(((BasicAuthenticator) basic).getContainer());
    assertEquals(LifecycleState.DESTROYED, ((BasicAuthenticator) basic).getState());
  }

  /**
   * Test {@link StandardPipeline#destroyInternal()}.
   * <p>
   * Method under test: {@link StandardPipeline#destroyInternal()}
   */
  @Test
  public void testDestroyInternal2() {
    // Arrange
    BasicAuthenticator valve = new BasicAuthenticator();
    valve.addLifecycleListener(new AprLifecycleListener());

    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());
    standardPipeline.setBasic(valve);

    // Act
    standardPipeline.destroyInternal();

    // Assert
    assertNull(standardPipeline.first);
    assertEquals(1, standardPipeline.getValveObjectNames().length);
    Valve[] valves = standardPipeline.getValves();
    assertEquals(1, valves.length);
    assertSame(valve, standardPipeline.getFirst());
    assertSame(valve, valves[0]);
  }

  /**
   * Test {@link StandardPipeline#destroyInternal()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#destroyInternal()}
   */
  @Test
  public void testDestroyInternal_givenStandardPipeline_thenArrayLengthIsZero() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();

    // Act
    standardPipeline.destroyInternal();

    // Assert that nothing has changed
    assertEquals(0, standardPipeline.getValveObjectNames().length);
    assertEquals(0, standardPipeline.getValves().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardPipeline#setContainer(Container)}
   *   <li>{@link StandardPipeline#initInternal()}
   *   <li>{@link StandardPipeline#toString()}
   *   <li>{@link StandardPipeline#getBasic()}
   *   <li>{@link StandardPipeline#getContainer()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    StandardContext container = new StandardContext();

    // Act
    standardPipeline.setContainer(container);
    standardPipeline.initInternal();
    String actualToStringResult = standardPipeline.toString();
    Valve actualBasic = standardPipeline.getBasic();

    // Assert
    assertEquals("StandardPipeline[StandardContext[null]]", actualToStringResult);
    assertNull(actualBasic);
    assertSame(container, standardPipeline.getContainer());
  }

  /**
   * Test {@link StandardPipeline#setBasic(Valve)}.
   * <p>
   * Method under test: {@link StandardPipeline#setBasic(Valve)}
   */
  @Test
  public void testSetBasic() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.setBasic(valve);

    // Assert
    Valve first = standardPipeline.getFirst();
    assertTrue(first instanceof BasicAuthenticator);
    Valve valve2 = standardPipeline.first;
    assertTrue(valve2 instanceof BasicAuthenticator);
    ObjectName[] valveObjectNames = standardPipeline.getValveObjectNames();
    assertNull(valveObjectNames[1]);
    assertEquals(2, valveObjectNames.length);
    Valve[] valves = standardPipeline.getValves();
    assertEquals(2, valves.length);
    assertSame(valve, first.getNext());
    assertSame(valve, valve2.getNext());
    assertSame(valve, valves[1]);
  }

  /**
   * Test {@link StandardPipeline#setBasic(Valve)}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>When {@link BasicAuthenticator} (default constructor).</li>
   *   <li>Then first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#setBasic(Valve)}
   */
  @Test
  public void testSetBasic_givenStandardPipeline_whenBasicAuthenticator_thenFirstElementIsNull() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.setBasic(valve);

    // Assert
    ObjectName[] valveObjectNames = standardPipeline.getValveObjectNames();
    assertNull(valveObjectNames[0]);
    assertEquals(1, valveObjectNames.length);
    Valve[] valves = standardPipeline.getValves();
    assertEquals(1, valves.length);
    assertSame(valve, standardPipeline.getFirst());
    assertSame(valve, valves[0]);
  }

  /**
   * Test {@link StandardPipeline#setBasic(Valve)}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#setBasic(Valve)}
   */
  @Test
  public void testSetBasic_givenStandardPipeline_whenNull_thenArrayLengthIsZero() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();

    // Act
    standardPipeline.setBasic(null);

    // Assert that nothing has changed
    assertEquals(0, standardPipeline.getValveObjectNames().length);
    assertEquals(0, standardPipeline.getValves().length);
  }

  /**
   * Test {@link StandardPipeline#setBasic(Valve)}.
   * <ul>
   *   <li>Then second element {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#setBasic(Valve)}
   */
  @Test
  public void testSetBasic_thenSecondElementBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());
    standardPipeline.addValve(new BasicAuthenticator());
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.setBasic(valve);

    // Assert
    Valve[] valves = standardPipeline.getValves();
    Valve valve2 = valves[1];
    assertTrue(valve2 instanceof BasicAuthenticator);
    ObjectName[] valveObjectNames = standardPipeline.getValveObjectNames();
    assertNull(valveObjectNames[2]);
    assertEquals(3, valveObjectNames.length);
    assertEquals(3, valves.length);
    assertSame(valve, valve2.getNext());
    assertSame(valve, valves[2]);
  }

  /**
   * Test {@link StandardPipeline#addValve(Valve)}.
   * <ul>
   *   <li>Then {@link StandardPipeline#StandardPipeline()} {@link StandardPipeline#first} {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#addValve(Valve)}
   */
  @Test
  public void testAddValve_thenStandardPipelineFirstBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    StandardContext container = new StandardContext();
    standardPipeline.setContainer(container);
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.addValve(valve);

    // Assert
    assertTrue(standardPipeline.first instanceof BasicAuthenticator);
    Container container2 = valve.getContainer();
    assertTrue(container2 instanceof StandardContext);
    assertEquals("Catalina", valve.getDomainInternal());
    assertEquals(1, standardPipeline.getValveObjectNames().length);
    assertEquals(1, standardPipeline.getValves().length);
    assertSame(valve, standardPipeline.getFirst());
    assertSame(container, container2);
    assertSame(container, standardPipeline.getContainer());
  }

  /**
   * Test {@link StandardPipeline#getValves()}.
   * <p>
   * Method under test: {@link StandardPipeline#getValves()}
   */
  @Test
  public void testGetValves() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());

    // Act
    Valve[] actualValves = standardPipeline.getValves();

    // Assert
    assertEquals(1, actualValves.length);
    assertSame(standardPipeline.first, actualValves[0]);
  }

  /**
   * Test {@link StandardPipeline#getValves()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getValves()}
   */
  @Test
  public void testGetValves_givenStandardPipeline_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardPipeline()).getValves().length);
  }

  /**
   * Test {@link StandardPipeline#getValves()}.
   * <ul>
   *   <li>Then first element return {@link BasicAuthenticator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getValves()}
   */
  @Test
  public void testGetValves_thenFirstElementReturnBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    BasicAuthenticator valve = new BasicAuthenticator();
    standardPipeline.setBasic(valve);

    // Act
    Valve[] actualValves = standardPipeline.getValves();

    // Assert
    Valve valve2 = actualValves[0];
    assertTrue(valve2 instanceof BasicAuthenticator);
    assertEquals(1, actualValves.length);
    assertSame(valve, valve2);
  }

  /**
   * Test {@link StandardPipeline#getValveObjectNames()}.
   * <p>
   * Method under test: {@link StandardPipeline#getValveObjectNames()}
   */
  @Test
  public void testGetValveObjectNames() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());

    // Act
    ObjectName[] actualValveObjectNames = standardPipeline.getValveObjectNames();

    // Assert
    assertNull(actualValveObjectNames[0]);
    assertEquals(1, actualValveObjectNames.length);
  }

  /**
   * Test {@link StandardPipeline#getValveObjectNames()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()} Basic is {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getValveObjectNames()}
   */
  @Test
  public void testGetValveObjectNames_givenStandardPipelineBasicIsBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline();
    standardPipeline.setBasic(new BasicAuthenticator());

    // Act
    ObjectName[] actualValveObjectNames = standardPipeline.getValveObjectNames();

    // Assert
    assertNull(actualValveObjectNames[0]);
    assertEquals(1, actualValveObjectNames.length);
  }

  /**
   * Test {@link StandardPipeline#getValveObjectNames()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getValveObjectNames()}
   */
  @Test
  public void testGetValveObjectNames_givenStandardPipeline_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new StandardPipeline()).getValveObjectNames().length);
  }

  /**
   * Test {@link StandardPipeline#removeValve(Valve)}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline(Container)} with container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#removeValve(Valve)}
   */
  @Test
  public void testRemoveValve_givenStandardPipelineWithContainerIsStandardContext() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.removeValve(valve);

    // Assert
    assertEquals("DESTROYED", valve.getStateName());
    assertEquals(LifecycleState.DESTROYED, valve.getState());
  }

  /**
   * Test {@link StandardPipeline#removeValve(Valve)}.
   * <ul>
   *   <li>Then {@link BasicAuthenticator} (default constructor) StateName is {@code DESTROYED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#removeValve(Valve)}
   */
  @Test
  public void testRemoveValve_thenBasicAuthenticatorStateNameIsDestroyed() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    standardPipeline.addValve(new BasicAuthenticator());
    BasicAuthenticator valve = new BasicAuthenticator();

    // Act
    standardPipeline.removeValve(valve);

    // Assert
    assertEquals("DESTROYED", valve.getStateName());
    assertEquals(LifecycleState.DESTROYED, valve.getState());
  }

  /**
   * Test {@link StandardPipeline#getFirst()}.
   * <ul>
   *   <li>Given {@link StandardPipeline#StandardPipeline()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getFirst()}
   */
  @Test
  public void testGetFirst_givenStandardPipeline_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StandardPipeline()).getFirst());
  }

  /**
   * Test {@link StandardPipeline#getFirst()}.
   * <ul>
   *   <li>Then return {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardPipeline#getFirst()}
   */
  @Test
  public void testGetFirst_thenReturnBasicAuthenticator() {
    // Arrange
    StandardPipeline standardPipeline = new StandardPipeline(new StandardContext());
    BasicAuthenticator valve = new BasicAuthenticator();
    standardPipeline.addValve(valve);

    // Act and Assert
    assertSame(valve, standardPipeline.getFirst());
  }
}
