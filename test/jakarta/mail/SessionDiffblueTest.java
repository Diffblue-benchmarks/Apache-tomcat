package jakarta.mail;

import static org.junit.Assert.assertNull;
import java.util.Properties;
import org.junit.Test;

public class SessionDiffblueTest {
  /**
   * Test {@link Session#getInstance(Properties)} with {@code props}.
   * <p>
   * Method under test: {@link Session#getInstance(Properties)}
   */
  @Test
  public void testGetInstanceWithProps() {
    // Arrange, Act and Assert
    assertNull(Session.getInstance(new Properties()));
  }

  /**
   * Test {@link Session#getInstance(Properties, Authenticator)} with {@code props}, {@code auth}.
   * <p>
   * Method under test: {@link Session#getInstance(Properties, Authenticator)}
   */
  @Test
  public void testGetInstanceWithPropsAuth() {
    // Arrange
    Properties props = new Properties();

    // Act and Assert
    assertNull(Session.getInstance(props, new Authenticator()));
  }
}
