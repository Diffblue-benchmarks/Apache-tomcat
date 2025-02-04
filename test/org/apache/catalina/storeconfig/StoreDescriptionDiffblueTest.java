package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class StoreDescriptionDiffblueTest {
  /**
   * Test {@link StoreDescription#addTransientAttribute(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#addTransientAttribute(String)}
   */
  @Test
  public void testAddTransientAttribute_givenStoreDescription() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();

    // Act
    storeDescription.addTransientAttribute("Attribute");

    // Assert
    List<String> transientAttributes = storeDescription.getTransientAttributes();
    assertEquals(1, transientAttributes.size());
    assertEquals("Attribute", transientAttributes.get(0));
  }

  /**
   * Test {@link StoreDescription#addTransientAttribute(String)}.
   * <ul>
   *   <li>Then {@link StoreDescription} (default constructor) TransientAttributes is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#addTransientAttribute(String)}
   */
  @Test
  public void testAddTransientAttribute_thenStoreDescriptionTransientAttributesIsArrayList() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    ArrayList<String> transientAttributes = new ArrayList<>();
    storeDescription.setTransientAttributes(transientAttributes);

    // Act
    storeDescription.addTransientAttribute("Attribute");

    // Assert
    List<String> transientAttributes2 = storeDescription.getTransientAttributes();
    assertEquals(1, transientAttributes2.size());
    assertEquals("Attribute", transientAttributes2.get(0));
    assertSame(transientAttributes, transientAttributes2);
  }

  /**
   * Test {@link StoreDescription#addTransientChild(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#addTransientChild(String)}
   */
  @Test
  public void testAddTransientChild_givenStoreDescription() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();

    // Act
    storeDescription.addTransientChild("Classname");

    // Assert
    List<String> transientChildren = storeDescription.getTransientChildren();
    assertEquals(1, transientChildren.size());
    assertEquals("Classname", transientChildren.get(0));
  }

  /**
   * Test {@link StoreDescription#addTransientChild(String)}.
   * <ul>
   *   <li>Then {@link StoreDescription} (default constructor) TransientChildren is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#addTransientChild(String)}
   */
  @Test
  public void testAddTransientChild_thenStoreDescriptionTransientChildrenIsArrayList() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    ArrayList<String> transientChildren = new ArrayList<>();
    storeDescription.setTransientChildren(transientChildren);

    // Act
    storeDescription.addTransientChild("Classname");

    // Assert
    List<String> transientChildren2 = storeDescription.getTransientChildren();
    assertEquals(1, transientChildren2.size());
    assertEquals("Classname", transientChildren2.get(0));
    assertSame(transientChildren, transientChildren2);
  }

  /**
   * Test {@link StoreDescription#isTransientChild(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor) TransientChildren is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientChild(String)}
   */
  @Test
  public void testIsTransientChild_givenStoreDescriptionTransientChildrenIsArrayList() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    storeDescription.setTransientChildren(new ArrayList<>());

    // Act and Assert
    assertFalse(storeDescription.isTransientChild("Classname"));
  }

  /**
   * Test {@link StoreDescription#isTransientChild(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientChild(String)}
   */
  @Test
  public void testIsTransientChild_givenStoreDescription_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StoreDescription()).isTransientChild("Classname"));
  }

  /**
   * Test {@link StoreDescription#isTransientChild(String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientChild(String)}
   */
  @Test
  public void testIsTransientChild_thenReturnTrue() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    storeDescription.addTransientChild("Classname");

    // Act and Assert
    assertTrue(storeDescription.isTransientChild("Classname"));
  }

  /**
   * Test {@link StoreDescription#isTransientAttribute(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor) TransientAttributes is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientAttribute(String)}
   */
  @Test
  public void testIsTransientAttribute_givenStoreDescriptionTransientAttributesIsArrayList() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    storeDescription.setTransientAttributes(new ArrayList<>());

    // Act and Assert
    assertFalse(storeDescription.isTransientAttribute("Attribute"));
  }

  /**
   * Test {@link StoreDescription#isTransientAttribute(String)}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientAttribute(String)}
   */
  @Test
  public void testIsTransientAttribute_givenStoreDescription_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new StoreDescription()).isTransientAttribute("Attribute"));
  }

  /**
   * Test {@link StoreDescription#isTransientAttribute(String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#isTransientAttribute(String)}
   */
  @Test
  public void testIsTransientAttribute_thenReturnTrue() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    storeDescription.addTransientAttribute("Attribute");

    // Act and Assert
    assertTrue(storeDescription.isTransientAttribute("Attribute"));
  }

  /**
   * Test {@link StoreDescription#getId()}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor) Id is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#getId()}
   */
  @Test
  public void testGetId_givenStoreDescriptionIdIsFoo_thenReturnFoo() {
    // Arrange
    StoreDescription storeDescription = new StoreDescription();
    storeDescription.setId("foo");

    // Act and Assert
    assertEquals("foo", storeDescription.getId());
  }

  /**
   * Test {@link StoreDescription#getId()}.
   * <ul>
   *   <li>Given {@link StoreDescription} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreDescription#getId()}
   */
  @Test
  public void testGetId_givenStoreDescription_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new StoreDescription()).getId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StoreDescription}
   *   <li>{@link StoreDescription#setAttributes(boolean)}
   *   <li>{@link StoreDescription#setBackup(boolean)}
   *   <li>{@link StoreDescription#setChildren(boolean)}
   *   <li>{@link StoreDescription#setDefault(boolean)}
   *   <li>{@link StoreDescription#setExternalAllowed(boolean)}
   *   <li>{@link StoreDescription#setExternalOnly(boolean)}
   *   <li>{@link StoreDescription#setId(String)}
   *   <li>{@link StoreDescription#setStandard(boolean)}
   *   <li>{@link StoreDescription#setStoreFactory(IStoreFactory)}
   *   <li>{@link StoreDescription#setStoreFactoryClass(String)}
   *   <li>{@link StoreDescription#setStoreSeparate(boolean)}
   *   <li>{@link StoreDescription#setStoreWriterClass(String)}
   *   <li>{@link StoreDescription#setTag(String)}
   *   <li>{@link StoreDescription#setTagClass(String)}
   *   <li>{@link StoreDescription#setTransientAttributes(List)}
   *   <li>{@link StoreDescription#setTransientChildren(List)}
   *   <li>{@link StoreDescription#getStoreFactory()}
   *   <li>{@link StoreDescription#getStoreFactoryClass()}
   *   <li>{@link StoreDescription#getStoreWriterClass()}
   *   <li>{@link StoreDescription#getTag()}
   *   <li>{@link StoreDescription#getTagClass()}
   *   <li>{@link StoreDescription#getTransientAttributes()}
   *   <li>{@link StoreDescription#getTransientChildren()}
   *   <li>{@link StoreDescription#isAttributes()}
   *   <li>{@link StoreDescription#isBackup()}
   *   <li>{@link StoreDescription#isChildren()}
   *   <li>{@link StoreDescription#isDefault()}
   *   <li>{@link StoreDescription#isExternalAllowed()}
   *   <li>{@link StoreDescription#isExternalOnly()}
   *   <li>{@link StoreDescription#isStandard()}
   *   <li>{@link StoreDescription#isStoreSeparate()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    StoreDescription actualStoreDescription = new StoreDescription();
    actualStoreDescription.setAttributes(true);
    actualStoreDescription.setBackup(true);
    actualStoreDescription.setChildren(true);
    actualStoreDescription.setDefault(true);
    actualStoreDescription.setExternalAllowed(true);
    actualStoreDescription.setExternalOnly(true);
    actualStoreDescription.setId("42");
    actualStoreDescription.setStandard(true);
    CatalinaClusterSF storeFactory = new CatalinaClusterSF();
    actualStoreDescription.setStoreFactory(storeFactory);
    actualStoreDescription.setStoreFactoryClass("Store Factory Class");
    actualStoreDescription.setStoreSeparate(true);
    actualStoreDescription.setStoreWriterClass("Store Writer Class");
    actualStoreDescription.setTag("Tag");
    actualStoreDescription.setTagClass("Tag Class");
    ArrayList<String> transientAttributes = new ArrayList<>();
    actualStoreDescription.setTransientAttributes(transientAttributes);
    ArrayList<String> transientChildren = new ArrayList<>();
    actualStoreDescription.setTransientChildren(transientChildren);
    IStoreFactory actualStoreFactory = actualStoreDescription.getStoreFactory();
    String actualStoreFactoryClass = actualStoreDescription.getStoreFactoryClass();
    String actualStoreWriterClass = actualStoreDescription.getStoreWriterClass();
    String actualTag = actualStoreDescription.getTag();
    String actualTagClass = actualStoreDescription.getTagClass();
    List<String> actualTransientAttributes = actualStoreDescription.getTransientAttributes();
    List<String> actualTransientChildren = actualStoreDescription.getTransientChildren();
    boolean actualIsAttributesResult = actualStoreDescription.isAttributes();
    boolean actualIsBackupResult = actualStoreDescription.isBackup();
    boolean actualIsChildrenResult = actualStoreDescription.isChildren();
    boolean actualIsDefaultResult = actualStoreDescription.isDefault();
    boolean actualIsExternalAllowedResult = actualStoreDescription.isExternalAllowed();
    boolean actualIsExternalOnlyResult = actualStoreDescription.isExternalOnly();
    boolean actualIsStandardResult = actualStoreDescription.isStandard();
    boolean actualIsStoreSeparateResult = actualStoreDescription.isStoreSeparate();

    // Assert
    assertEquals("Store Factory Class", actualStoreFactoryClass);
    assertEquals("Store Writer Class", actualStoreWriterClass);
    assertEquals("Tag Class", actualTagClass);
    assertEquals("Tag", actualTag);
    assertTrue(actualTransientAttributes.isEmpty());
    assertTrue(actualTransientChildren.isEmpty());
    assertTrue(actualIsAttributesResult);
    assertTrue(actualIsBackupResult);
    assertTrue(actualIsChildrenResult);
    assertTrue(actualIsDefaultResult);
    assertTrue(actualIsExternalAllowedResult);
    assertTrue(actualIsExternalOnlyResult);
    assertTrue(actualIsStandardResult);
    assertTrue(actualIsStoreSeparateResult);
    assertSame(transientAttributes, actualTransientAttributes);
    assertSame(transientChildren, actualTransientChildren);
    assertSame(storeFactory, actualStoreFactory);
  }
}
