package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.filters.CsrfPreventionFilter.CsrfResponseWrapper;
import org.apache.catalina.filters.CsrfPreventionFilter.LruCache;
import org.apache.catalina.filters.CsrfPreventionFilter.MimePredicate;
import org.apache.catalina.filters.CsrfPreventionFilter.PatternPredicate;
import org.apache.catalina.filters.CsrfPreventionFilter.PrefixPredicate;
import org.apache.catalina.filters.CsrfPreventionFilter.SuffixPredicate;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.coyote.Request;
import org.apache.jasper.JasperException;
import org.apache.jasper.servlet.JspCServletContext;
import org.junit.Test;

public class CsrfPreventionFilterDiffblueTest {
  /**
   * Test CsrfResponseWrapper {@link CsrfResponseWrapper#encodeRedirectURL(String)}.
   * <p>
   * Method under test: {@link CsrfResponseWrapper#encodeRedirectURL(String)}
   */
  @Test
  public void testCsrfResponseWrapperEncodeRedirectURL() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    // Act and Assert
    assertEquals("https://example.org/example?Nonce Request Parameter Name=Nonce",
        (new CsrfResponseWrapper(response2, "Nonce Request Parameter Name", "Nonce", new ArrayList<>()))
            .encodeRedirectURL("https://example.org/example"));
  }

  /**
   * Test CsrfResponseWrapper {@link CsrfResponseWrapper#encodeRedirectURL(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfResponseWrapper#encodeRedirectURL(String)}
   */
  @Test
  public void testCsrfResponseWrapperEncodeRedirectURL_thenReturnNull() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act and Assert
    assertNull((new CsrfResponseWrapper(response, "Nonce Request Parameter Name", "Nonce", new ArrayList<>()))
        .encodeRedirectURL("https://example.org/example"));
  }

  /**
   * Test CsrfResponseWrapper {@link CsrfResponseWrapper#encodeURL(String)}.
   * <p>
   * Method under test: {@link CsrfResponseWrapper#encodeURL(String)}
   */
  @Test
  public void testCsrfResponseWrapperEncodeURL() {
    // Arrange
    Response response = new Response(new org.apache.coyote.Response());
    Connector connector = new Connector();
    response.setRequest(new org.apache.catalina.connector.Request(connector, new Request()));
    HttpServletResponseWrapper response2 = new HttpServletResponseWrapper(response);

    // Act and Assert
    assertEquals("https://example.org/example?Nonce Request Parameter Name=Nonce",
        (new CsrfResponseWrapper(response2, "Nonce Request Parameter Name", "Nonce", new ArrayList<>()))
            .encodeURL("https://example.org/example"));
  }

  /**
   * Test CsrfResponseWrapper {@link CsrfResponseWrapper#encodeURL(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfResponseWrapper#encodeURL(String)}
   */
  @Test
  public void testCsrfResponseWrapperEncodeURL_thenReturnNull() {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(new TesterHttpServletResponse());

    // Act and Assert
    assertNull((new CsrfResponseWrapper(response, "Nonce Request Parameter Name", "Nonce", new ArrayList<>()))
        .encodeURL("https://example.org/example"));
  }

  /**
   * Test CsrfResponseWrapper {@link CsrfResponseWrapper#CsrfResponseWrapper(HttpServletResponse, String, String, Collection)}.
   * <p>
   * Method under test: {@link CsrfResponseWrapper#CsrfResponseWrapper(HttpServletResponse, String, String, Collection)}
   */
  @Test
  public void testCsrfResponseWrapperNewCsrfResponseWrapper() throws IOException {
    // Arrange
    HttpServletResponseWrapper response = new HttpServletResponseWrapper(
        new Response(new org.apache.coyote.Response()));

    // Act
    CsrfResponseWrapper actualCsrfResponseWrapper = new CsrfResponseWrapper(response, "Nonce Request Parameter Name",
        "Nonce", new ArrayList<>());

    // Assert
    ServletResponse response2 = actualCsrfResponseWrapper.getResponse();
    assertTrue(response2 instanceof HttpServletResponseWrapper);
    Collection<String> headerNames = actualCsrfResponseWrapper.getHeaderNames();
    assertTrue(headerNames instanceof List);
    assertTrue(actualCsrfResponseWrapper.getOutputStream() instanceof CoyoteOutputStream);
    assertNull(actualCsrfResponseWrapper.getContentType());
    assertNull(actualCsrfResponseWrapper.getTrailerFields());
    assertEquals(200, actualCsrfResponseWrapper.getStatus());
    assertEquals(8192, actualCsrfResponseWrapper.getBufferSize());
    assertFalse(actualCsrfResponseWrapper.isCommitted());
    assertTrue(headerNames.isEmpty());
    assertSame(response, response2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CsrfPreventionFilter#setEnforce(boolean)}
   *   <li>{@link CsrfPreventionFilter#setNonceCacheSize(int)}
   *   <li>{@link CsrfPreventionFilter#setNonceRequestParameterName(String)}
   *   <li>{@link CsrfPreventionFilter#isEnforce()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();

    // Act
    csrfPreventionFilter.setEnforce(true);
    csrfPreventionFilter.setNonceCacheSize(3);
    csrfPreventionFilter.setNonceRequestParameterName("Parameter Name");

    // Assert
    assertTrue(csrfPreventionFilter.isEnforce());
  }

  /**
   * Test {@link CsrfPreventionFilter#createNoNoncePredicates(ServletContext, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilter#createNoNoncePredicates(ServletContext, String)}
   */
  @Test
  public void testCreateNoNoncePredicates_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CsrfPreventionFilter.createNoNoncePredicates(null, "Patterns"));
  }

  /**
   * Test {@link CsrfPreventionFilter#enforce(HttpServletRequest, String)}.
   * <p>
   * Method under test: {@link CsrfPreventionFilter#enforce(HttpServletRequest, String)}
   */
  @Test
  public void testEnforce() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertTrue(
        csrfPreventionFilter.enforce(
            new HttpServletRequestWrapper(
                new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))),
            "Requested Path"));
  }

  /**
   * Test LruCache {@link LruCache#add(Object)}.
   * <ul>
   *   <li>Given {@link LruCache#LruCache(int)} with cacheSize is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link LruCache#add(Object)}
   */
  @Test
  public void testLruCacheAdd_givenLruCacheWithCacheSizeIsThree() {
    // Arrange
    LruCache<Object> lruCache = new LruCache<>(3);

    // Act
    lruCache.add("Key");

    // Assert
    assertTrue(lruCache.contains("Key"));
  }

  /**
   * Test LruCache {@link LruCache#add(Object)}.
   * <ul>
   *   <li>Given {@link LruCache#LruCache(int)} with cacheSize is three add {@code Key}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LruCache#add(Object)}
   */
  @Test
  public void testLruCacheAdd_givenLruCacheWithCacheSizeIsThreeAddKey() {
    // Arrange
    LruCache<Object> lruCache = new LruCache<>(3);
    lruCache.add("Key");

    // Act
    lruCache.add("Key");

    // Assert that nothing has changed
    assertTrue(lruCache.contains("Key"));
  }

  /**
   * Test LruCache {@link LruCache#add(Object)}.
   * <ul>
   *   <li>Then not {@link LruCache#LruCache(int)} with cacheSize is zero contains {@code Key}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LruCache#add(Object)}
   */
  @Test
  public void testLruCacheAdd_thenNotLruCacheWithCacheSizeIsZeroContainsKey() {
    // Arrange
    LruCache<Object> lruCache = new LruCache<>(0);

    // Act
    lruCache.add("Key");

    // Assert that nothing has changed
    assertFalse(lruCache.contains("Key"));
  }

  /**
   * Test LruCache {@link LruCache#contains(Object)}.
   * <ul>
   *   <li>Given {@link LruCache#LruCache(int)} with cacheSize is three add {@code Key}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LruCache#contains(Object)}
   */
  @Test
  public void testLruCacheContains_givenLruCacheWithCacheSizeIsThreeAddKey_thenReturnTrue() {
    // Arrange
    LruCache<Object> lruCache = new LruCache<>(3);
    lruCache.add("Key");

    // Act and Assert
    assertTrue(lruCache.contains("Key"));
  }

  /**
   * Test LruCache {@link LruCache#contains(Object)}.
   * <ul>
   *   <li>Given {@link LruCache#LruCache(int)} with cacheSize is three.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LruCache#contains(Object)}
   */
  @Test
  public void testLruCacheContains_givenLruCacheWithCacheSizeIsThree_thenReturnFalse() {
    // Arrange
    LruCache<Object> lruCache = new LruCache<>(3);

    // Act and Assert
    assertFalse(lruCache.contains("Key"));
  }

  /**
   * Test LruCache {@link LruCache#LruCache(int)}.
   * <p>
   * Method under test: {@link LruCache#LruCache(int)}
   */
  @Test
  public void testLruCacheNewLruCache() {
    // Arrange and Act
    LruCache<Object> actualLruCache = new LruCache<>(3);

    // Assert
    assertFalse(actualLruCache.contains("Key"));
  }

  /**
   * Test MimePredicate {@link MimePredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Given {@link PrintWriter#PrintWriter(Writer)} with {@link StringWriter#StringWriter()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MimePredicate#test(String)}
   */
  @Test
  public void testMimePredicateTestWithT_givenPrintWriterWithStringWriter_thenReturnFalse()
      throws MalformedURLException, JasperException {
    // Arrange
    PrintWriter aLogWriter = new PrintWriter(new StringWriter());
    URL aResourceBaseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertFalse((new MimePredicate(
        new JspCServletContext(aLogWriter, aResourceBaseURL, new ParallelWebappClassLoader(), true, true), null))
        .test("foo"));
  }

  /**
   * Test PatternPredicate {@link PatternPredicate#PatternPredicate(String)}.
   * <ul>
   *   <li>When {@code .*}.</li>
   *   <li>Then return test {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PatternPredicate#PatternPredicate(String)}
   */
  @Test
  public void testPatternPredicateNewPatternPredicate_whenDotAsterisk_thenReturnTestFoo() {
    // Arrange, Act and Assert
    assertTrue((new PatternPredicate(".*")).test("foo"));
  }

  /**
   * Test PatternPredicate {@link PatternPredicate#PatternPredicate(String)}.
   * <ul>
   *   <li>When {@code U}.</li>
   *   <li>Then return not test {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PatternPredicate#PatternPredicate(String)}
   */
  @Test
  public void testPatternPredicateNewPatternPredicate_whenU_thenReturnNotTestFoo() {
    // Arrange, Act and Assert
    assertFalse((new PatternPredicate("U")).test("foo"));
  }

  /**
   * Test PatternPredicate {@link PatternPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Given {@link PatternPredicate#PatternPredicate(String)} with regex is {@code U}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PatternPredicate#test(String)}
   */
  @Test
  public void testPatternPredicateTestWithT_givenPatternPredicateWithRegexIsU_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new PatternPredicate("U")).test("foo"));
  }

  /**
   * Test PatternPredicate {@link PatternPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PatternPredicate#test(String)}
   */
  @Test
  public void testPatternPredicateTestWithT_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new PatternPredicate(".*")).test("foo"));
  }

  /**
   * Test PrefixPredicate {@link PrefixPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Given {@link PrefixPredicate#PrefixPredicate(String)} with {@code Prefix}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PrefixPredicate#test(String)}
   */
  @Test
  public void testPrefixPredicateTestWithT_givenPrefixPredicateWithPrefix_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new PrefixPredicate("Prefix")).test("foo"));
  }

  /**
   * Test PrefixPredicate {@link PrefixPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PrefixPredicate#test(String)}
   */
  @Test
  public void testPrefixPredicateTestWithT_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new PrefixPredicate(CorsFilter.DEFAULT_ALLOWED_ORIGINS)).test("foo"));
  }

  /**
   * Test {@link CsrfPreventionFilter#skipNonceCheck(HttpServletRequest)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilter#skipNonceCheck(HttpServletRequest)}
   */
  @Test
  public void testSkipNonceCheck_thenReturnFalse() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(csrfPreventionFilter.skipNonceCheck(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link CsrfPreventionFilter#skipNonceGeneration(HttpServletRequest)}.
   * <p>
   * Method under test: {@link CsrfPreventionFilter#skipNonceGeneration(HttpServletRequest)}
   */
  @Test
  public void testSkipNonceGeneration() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(csrfPreventionFilter.skipNonceGeneration(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())))));
  }

  /**
   * Test {@link CsrfPreventionFilter#getNonceCache(HttpServletRequest, HttpSession)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link DeltaSession#DeltaSession()} Valid is {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilter#getNonceCache(HttpServletRequest, HttpSession)}
   */
  @Test
  public void testGetNonceCache_givenTrue_whenDeltaSessionValidIsTrue_thenReturnNull() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request())));

    DeltaSession session = new DeltaSession();
    session.setValid(true);

    // Act and Assert
    assertNull(csrfPreventionFilter.getNonceCache(request, session));
  }

  /**
   * Test {@link CsrfPreventionFilter#getNonceCache(HttpServletRequest, HttpSession)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CsrfPreventionFilter#getNonceCache(HttpServletRequest, HttpSession)}
   */
  @Test
  public void testGetNonceCache_whenNull_thenReturnNull() {
    // Arrange
    CsrfPreventionFilter csrfPreventionFilter = new CsrfPreventionFilter();
    Connector connector = new Connector();

    // Act and Assert
    assertNull(csrfPreventionFilter.getNonceCache(new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))), null));
  }

  /**
   * Test new {@link CsrfPreventionFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CsrfPreventionFilter}
   */
  @Test
  public void testNewCsrfPreventionFilter() {
    // Arrange and Act
    CsrfPreventionFilter actualCsrfPreventionFilter = new CsrfPreventionFilter();

    // Assert
    assertEquals(403, actualCsrfPreventionFilter.getDenyStatus());
    assertTrue(actualCsrfPreventionFilter.isEnforce());
    assertTrue(actualCsrfPreventionFilter.isConfigProblemFatal());
  }

  /**
   * Test SuffixPredicate {@link SuffixPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Given {@link SuffixPredicate#SuffixPredicate(String)} with {@code Suffix}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SuffixPredicate#test(String)}
   */
  @Test
  public void testSuffixPredicateTestWithT_givenSuffixPredicateWithSuffix_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new SuffixPredicate("Suffix")).test("foo"));
  }

  /**
   * Test SuffixPredicate {@link SuffixPredicate#test(String)} with {@code t}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SuffixPredicate#test(String)}
   */
  @Test
  public void testSuffixPredicateTestWithT_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new SuffixPredicate(CorsFilter.DEFAULT_ALLOWED_ORIGINS)).test("foo"));
  }
}
