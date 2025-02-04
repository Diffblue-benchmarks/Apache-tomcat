package org.apache.catalina.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardWrapper;
import org.junit.Test;

public class WrapperMappingInfoDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WrapperMappingInfo#WrapperMappingInfo(String, Wrapper, boolean, boolean)}
   *   <li>{@link WrapperMappingInfo#getMapping()}
   *   <li>{@link WrapperMappingInfo#getWrapper()}
   *   <li>{@link WrapperMappingInfo#isJspWildCard()}
   *   <li>{@link WrapperMappingInfo#isResourceOnly()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    StandardWrapper wrapper = new StandardWrapper();

    // Act
    WrapperMappingInfo actualWrapperMappingInfo = new WrapperMappingInfo("Mapping", wrapper, true, true);
    String actualMapping = actualWrapperMappingInfo.getMapping();
    Wrapper actualWrapper = actualWrapperMappingInfo.getWrapper();
    boolean actualIsJspWildCardResult = actualWrapperMappingInfo.isJspWildCard();

    // Assert
    assertEquals("Mapping", actualMapping);
    assertTrue(actualIsJspWildCardResult);
    assertTrue(actualWrapperMappingInfo.isResourceOnly());
    assertSame(wrapper, actualWrapper);
  }
}
