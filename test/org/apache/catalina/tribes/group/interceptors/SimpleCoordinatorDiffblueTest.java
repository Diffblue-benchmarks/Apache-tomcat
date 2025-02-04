package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class SimpleCoordinatorDiffblueTest {
  /**
   * Test {@link SimpleCoordinator#getCoordinator()}.
   * <p>
   * Method under test: {@link SimpleCoordinator#getCoordinator()}
   */
  @Test
  public void testGetCoordinator() {
    // Arrange, Act and Assert
    assertNull((new SimpleCoordinator()).getCoordinator());
  }

  /**
   * Test {@link SimpleCoordinator#isCoordinator()}.
   * <p>
   * Method under test: {@link SimpleCoordinator#isCoordinator()}
   */
  @Test
  public void testIsCoordinator() {
    // Arrange, Act and Assert
    assertFalse((new SimpleCoordinator()).isCoordinator());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SimpleCoordinator}
   *   <li>{@link SimpleCoordinator#viewChange(Member[])}
   *   <li>{@link SimpleCoordinator#getView()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    SimpleCoordinator actualSimpleCoordinator = new SimpleCoordinator();
    actualSimpleCoordinator.viewChange(new Member[]{new MemberImpl()});

    // Assert
    assertNull(actualSimpleCoordinator.getView());
    assertNull(actualSimpleCoordinator.getChannel());
    assertNull(actualSimpleCoordinator.getNext());
    assertNull(actualSimpleCoordinator.getPrevious());
    assertEquals(0, actualSimpleCoordinator.getOptionFlag());
  }
}
