package org.apache.catalina;

import static org.junit.Assert.assertNull;
import org.apache.catalina.webresources.EmptyResource;
import org.apache.catalina.webresources.ExtractingRoot;
import org.junit.Test;

public class WebResourceDiffblueTest {
  /**
   * Test {@link WebResource#getStrongETag()}.
   * <p>
   * Method under test: {@link WebResource#getStrongETag()}
   */
  @Test
  public void testGetStrongETag() {
    // Arrange, Act and Assert
    assertNull((new EmptyResource(new ExtractingRoot(), "Web App Path")).getStrongETag());
  }

  /**
   * Test {@link WebResource#getCodeBase()}.
   * <p>
   * Method under test: {@link WebResource#getCodeBase()}
   */
  @Test
  public void testGetCodeBase() {
    // Arrange, Act and Assert
    assertNull((new EmptyResource(new ExtractingRoot(), "Web App Path")).getCodeBase());
  }
}
