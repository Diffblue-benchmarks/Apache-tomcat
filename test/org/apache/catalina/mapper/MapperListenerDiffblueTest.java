package org.apache.catalina.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;
import org.junit.Test;

public class MapperListenerDiffblueTest {
  /**
   * Test {@link MapperListener#MapperListener(Service)}.
   * <ul>
   *   <li>When {@link StandardService} (default constructor).</li>
   *   <li>Then return DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapperListener#MapperListener(Service)}
   */
  @Test
  public void testNewMapperListener_whenStandardService_thenReturnDomainInternalIsCatalina() {
    // Arrange and Act
    MapperListener actualMapperListener = new MapperListener(new StandardService());

    // Assert
    assertEquals("Catalina", actualMapperListener.getDomainInternal());
    assertEquals("Catalina", actualMapperListener.getDomain());
    assertEquals("NEW", actualMapperListener.getStateName());
    assertEquals("type=Mapper", actualMapperListener.getObjectNameKeyProperties());
    assertNull(actualMapperListener.getObjectName());
    assertEquals(0, actualMapperListener.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualMapperListener.getState());
    assertTrue(actualMapperListener.getThrowOnFailure());
  }

  /**
   * Test {@link MapperListener#getDomainInternal()}.
   * <p>
   * Method under test: {@link MapperListener#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal() {
    // Arrange, Act and Assert
    assertEquals("Catalina", (new MapperListener(new StandardService())).getDomainInternal());
  }

  /**
   * Test {@link MapperListener#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapperListener#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardServiceDomainIsCatalina() {
    // Arrange
    StandardService service = new StandardService();
    service.setDomain("Catalina");

    // Act and Assert
    assertEquals("Catalina", (new MapperListener(service)).getDomainInternal());
  }

  /**
   * Test {@link MapperListener#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Name is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapperListener#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardServiceNameIsCatalina() {
    // Arrange
    StandardService service = new StandardService();
    service.setName("Catalina");

    // Act and Assert
    assertEquals("Catalina", (new MapperListener(service)).getDomainInternal());
  }

  /**
   * Test {@link MapperListener#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link MapperListener#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange, Act and Assert
    assertEquals("type=Mapper", (new MapperListener(new StandardService())).getObjectNameKeyProperties());
  }
}
