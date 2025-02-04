package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.catalina.startup.ClassLoaderFactory.RepositoryType;
import org.junit.Test;

public class ClassLoaderFactoryDiffblueTest {
  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository("Location", RepositoryType.DIR));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent2() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository("lib", RepositoryType.DIR));
    repositories.add(new Repository("Location", RepositoryType.DIR));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent3() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository(System.getProperty("user.dir"), RepositoryType.DIR));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent4() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository("Location", null));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent5() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository("Location", RepositoryType.GLOB));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent6() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository("Location", RepositoryType.JAR));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent7() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository(System.getProperty("user.dir"), RepositoryType.GLOB));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent8() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();
    repositories.add(new Repository(System.getProperty("user.dir"), RepositoryType.JAR));

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent_whenArrayList_thenReturnNotNull() throws Exception {
    // Arrange
    ArrayList<Repository> repositories = new ArrayList<>();

    // Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(repositories, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)} with {@code repositories}, {@code parent}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(List, ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithRepositoriesParent_whenNull_thenReturnNotNull() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(null, null));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)} with {@code unpacked}, {@code packed}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithUnpackedPackedParent() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()},
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()},
        new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)} with {@code unpacked}, {@code packed}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithUnpackedPackedParent2() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), ".jar").toFile()},
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()},
        new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)} with {@code unpacked}, {@code packed}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithUnpackedPackedParent3() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()},
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), ".jar").toFile()}, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)} with {@code unpacked}, {@code packed}, {@code parent}.
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithUnpackedPackedParent4() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()},
        new File[]{Paths.get(System.getProperty("java.io.tmpdir"), "").toFile()}, new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)} with {@code unpacked}, {@code packed}, {@code parent}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassLoaderFactory#createClassLoader(File[], File[], ClassLoader)}
   */
  @Test
  public void testCreateClassLoaderWithUnpackedPackedParent_whenNull() throws Exception {
    // Arrange, Act and Assert
    assertNotNull(ClassLoaderFactory.createClassLoader(null, null, null));
  }

  /**
   * Test Repository getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Repository#Repository(String, RepositoryType)}
   *   <li>{@link Repository#getLocation()}
   *   <li>{@link Repository#getType()}
   * </ul>
   */
  @Test
  public void testRepositoryGettersAndSetters() {
    // Arrange and Act
    Repository actualRepository = new Repository("Location", RepositoryType.DIR);
    String actualLocation = actualRepository.getLocation();

    // Assert
    assertEquals("Location", actualLocation);
    assertEquals(RepositoryType.DIR, actualRepository.getType());
  }
}
