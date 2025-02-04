package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.apache.catalina.core.StandardContext;
import org.apache.coyote.UpgradeToken;
import org.apache.coyote.http11.upgrade.UpgradeGroupInfo;
import org.apache.coyote.http11.upgrade.UpgradeInfo;
import org.apache.coyote.http11.upgrade.UpgradeProcessorExternal;
import org.apache.coyote.http11.upgrade.UpgradeServletInputStream;
import org.apache.tomcat.SimpleInstanceManager;
import org.apache.tomcat.util.net.Nio2Channel;
import org.apache.tomcat.util.net.Nio2Endpoint;
import org.apache.tomcat.util.net.Nio2Endpoint.Nio2SocketWrapper;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.apache.tomcat.websocket.server.WsHttpUpgradeHandler;
import org.junit.Test;

public class ServletInputStreamDiffblueTest {
  /**
   * Test {@link ServletInputStream#readLine(byte[], int, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ServletInputStream#readLine(byte[], int, int)}
   */
  @Test
  public void testReadLine_whenZero_thenReturnZero() throws IOException {
    // Arrange
    Nio2Channel channel = new Nio2Channel(null);
    Nio2SocketWrapper wrapper = new Nio2SocketWrapper(channel, new Nio2Endpoint());

    WsHttpUpgradeHandler httpUpgradeHandler = new WsHttpUpgradeHandler();
    StandardContext contextBind = new StandardContext();
    UpgradeToken upgradeToken = new UpgradeToken(httpUpgradeHandler, contextBind, new SimpleInstanceManager(),
        "Protocol");

    UpgradeProcessorExternal processor = new UpgradeProcessorExternal(wrapper, upgradeToken, new UpgradeGroupInfo());

    Nio2Channel channel2 = new Nio2Channel(new SocketBufferHandler(3, 3, true));
    Nio2SocketWrapper socketWrapper = new Nio2SocketWrapper(channel2, new Nio2Endpoint());

    // Act and Assert
    assertEquals(0, (new UpgradeServletInputStream(processor, socketWrapper, new UpgradeInfo()))
        .readLine(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 1, 0));
  }
}
