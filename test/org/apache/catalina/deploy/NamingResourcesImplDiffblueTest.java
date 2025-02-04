package org.apache.catalina.deploy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import javax.management.ObjectName;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.tomcat.util.descriptor.web.ContextEjb;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.apache.tomcat.util.descriptor.web.ContextLocalEjb;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.ContextResourceEnvRef;
import org.apache.tomcat.util.descriptor.web.ContextResourceLink;
import org.apache.tomcat.util.descriptor.web.ContextService;
import org.apache.tomcat.util.descriptor.web.ContextTransaction;
import org.apache.tomcat.util.descriptor.web.MessageDestinationRef;
import org.junit.Test;

public class NamingResourcesImplDiffblueTest {
  /**
   * Test new {@link NamingResourcesImpl} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NamingResourcesImpl}
   */
  @Test
  public void testNewNamingResourcesImpl() {
    // Arrange and Act
    NamingResourcesImpl actualNamingResourcesImpl = new NamingResourcesImpl();

    // Assert
    assertEquals("Catalina", actualNamingResourcesImpl.getDomain());
    assertEquals("NEW", actualNamingResourcesImpl.getStateName());
    assertEquals("type=NamingResources", actualNamingResourcesImpl.getObjectNameKeyProperties());
    assertNull(actualNamingResourcesImpl.getContainer());
    assertNull(actualNamingResourcesImpl.getDomainInternal());
    assertNull(actualNamingResourcesImpl.getObjectName());
    assertNull(actualNamingResourcesImpl.getTransaction());
    assertEquals(0, actualNamingResourcesImpl.support.getPropertyChangeListeners().length);
    assertEquals(0, actualNamingResourcesImpl.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualNamingResourcesImpl.getState());
    assertTrue(actualNamingResourcesImpl.getThrowOnFailure());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NamingResourcesImpl#setContainer(Object)}
   *   <li>{@link NamingResourcesImpl#setTransaction(ContextTransaction)}
   *   <li>{@link NamingResourcesImpl#getContainer()}
   *   <li>{@link NamingResourcesImpl#getTransaction()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    // Act
    namingResourcesImpl.setContainer("Container");
    ContextTransaction transaction = new ContextTransaction();
    namingResourcesImpl.setTransaction(transaction);
    Object actualContainer = namingResourcesImpl.getContainer();

    // Assert
    assertEquals("Container", actualContainer);
    assertSame(transaction, namingResourcesImpl.getTransaction());
  }

  /**
   * Test {@link NamingResourcesImpl#addEjb(ContextEjb)}.
   * <ul>
   *   <li>Given {@code ejb}.</li>
   *   <li>When {@link ContextEjb} (default constructor) Link is {@code ejb}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEjb(ContextEjb)}
   */
  @Test
  public void testAddEjb_givenEjb_whenContextEjbLinkIsEjb() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEjb ejb = new ContextEjb();
    ejb.setLink("ejb");

    // Act
    namingResourcesImpl.addEjb(ejb);

    // Assert
    assertSame(namingResourcesImpl, ejb.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addEjb(ContextEjb)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ContextEjb} (default constructor) Link is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEjb(ContextEjb)}
   */
  @Test
  public void testAddEjb_givenEmptyString_whenContextEjbLinkIsEmptyString() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEjb ejb = new ContextEjb();
    ejb.setLink("");

    // Act
    namingResourcesImpl.addEjb(ejb);

    // Assert
    assertSame(namingResourcesImpl, ejb.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addEjb(ContextEjb)}.
   * <ul>
   *   <li>When {@link ContextEjb} (default constructor) LookupName is {@code ejb}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEjb(ContextEjb)}
   */
  @Test
  public void testAddEjb_whenContextEjbLookupNameIsEjb_thenThrowIllegalArgumentException() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEjb ejb = new ContextEjb();
    ejb.setLookupName("ejb");
    ejb.setLink("ejb");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> namingResourcesImpl.addEjb(ejb));
  }

  /**
   * Test {@link NamingResourcesImpl#addEjb(ContextEjb)}.
   * <ul>
   *   <li>When {@link ContextEjb} (default constructor).</li>
   *   <li>Then {@link ContextEjb} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEjb(ContextEjb)}
   */
  @Test
  public void testAddEjb_whenContextEjb_thenContextEjbNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextEjb ejb = new ContextEjb();

    // Act
    namingResourcesImpl.addEjb(ejb);

    // Assert
    assertSame(namingResourcesImpl, ejb.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ContextEnvironment} (default constructor) Value is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_givenEmptyString_whenContextEnvironmentValueIsEmptyString() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEnvironment environment = new ContextEnvironment();
    environment.setValue("");
    environment.addInjectionTarget("environment", "environment");

    // Act
    namingResourcesImpl.addEnvironment(environment);

    // Assert that nothing has changed
    assertEquals(0, namingResourcesImpl.support.getPropertyChangeListeners().length);
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Given {@code environment}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_givenEnvironment_thenArrayLengthIsZero() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEnvironment environment = new ContextEnvironment();
    environment.addInjectionTarget("environment", "environment");

    // Act
    namingResourcesImpl.addEnvironment(environment);

    // Assert that nothing has changed
    assertEquals(0, namingResourcesImpl.support.getPropertyChangeListeners().length);
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_thenArrayLengthIsZero() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.addEnvironment(new ContextEnvironment());

    // Assert that nothing has changed
    assertEquals(0, namingResourcesImpl.support.getPropertyChangeListeners().length);
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Then {@link ContextEnvironment} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_thenContextEnvironmentNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextEnvironment environment = new ContextEnvironment();

    // Act
    namingResourcesImpl.addEnvironment(environment);

    // Assert
    assertSame(namingResourcesImpl, environment.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Then {@link ContextEnvironment} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_thenContextEnvironmentNamingResourcesIsNamingResourcesImpl2() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEnvironment environment = new ContextEnvironment();
    environment.setValue("42");
    environment.addInjectionTarget("environment", "environment");

    // Act
    namingResourcesImpl.addEnvironment(environment);

    // Assert
    assertSame(namingResourcesImpl, environment.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addEnvironment(ContextEnvironment)}
   */
  @Test
  public void testAddEnvironment_thenThrowIllegalArgumentException() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    ContextEnvironment environment = new ContextEnvironment();
    environment.setLookupName("environment");
    environment.setValue("42");
    environment.addInjectionTarget("environment", "environment");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> namingResourcesImpl.addEnvironment(environment));
  }

  /**
   * Test {@link NamingResourcesImpl#addLocalEjb(ContextLocalEjb)}.
   * <ul>
   *   <li>Then {@link ContextLocalEjb} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addLocalEjb(ContextLocalEjb)}
   */
  @Test
  public void testAddLocalEjb_thenContextLocalEjbNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextLocalEjb ejb = new ContextLocalEjb();

    // Act
    namingResourcesImpl.addLocalEjb(ejb);

    // Assert
    assertSame(namingResourcesImpl, ejb.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addLocalEjb(ContextLocalEjb)}.
   * <ul>
   *   <li>Then {@link ContextLocalEjb} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addLocalEjb(ContextLocalEjb)}
   */
  @Test
  public void testAddLocalEjb_thenContextLocalEjbNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    ContextLocalEjb ejb = new ContextLocalEjb();

    // Act
    namingResourcesImpl.addLocalEjb(ejb);

    // Assert that nothing has changed
    assertNull(ejb.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addMessageDestinationRef(MessageDestinationRef)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#addMessageDestinationRef(MessageDestinationRef)}
   */
  @Test
  public void testAddMessageDestinationRef() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    MessageDestinationRef mdr = new MessageDestinationRef();

    // Act
    namingResourcesImpl.addMessageDestinationRef(mdr);

    // Assert
    assertSame(namingResourcesImpl, mdr.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addMessageDestinationRef(MessageDestinationRef)}.
   * <ul>
   *   <li>Then {@link MessageDestinationRef} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addMessageDestinationRef(MessageDestinationRef)}
   */
  @Test
  public void testAddMessageDestinationRef_thenMessageDestinationRefNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    MessageDestinationRef mdr = new MessageDestinationRef();

    // Act
    namingResourcesImpl.addMessageDestinationRef(mdr);

    // Assert that nothing has changed
    assertNull(mdr.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    NamingContextListener listener = new NamingContextListener();

    // Act
    namingResourcesImpl.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = namingResourcesImpl.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link NamingResourcesImpl#addResource(ContextResource)}.
   * <ul>
   *   <li>Then {@link ContextResource} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResource(ContextResource)}
   */
  @Test
  public void testAddResource_thenContextResourceNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextResource resource = new ContextResource();

    // Act
    namingResourcesImpl.addResource(resource);

    // Assert
    assertSame(namingResourcesImpl, resource.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addResource(ContextResource)}.
   * <ul>
   *   <li>Then {@link ContextResource} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResource(ContextResource)}
   */
  @Test
  public void testAddResource_thenContextResourceNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    ContextResource resource = new ContextResource();

    // Act
    namingResourcesImpl.addResource(resource);

    // Assert that nothing has changed
    assertNull(resource.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addResourceEnvRef(ContextResourceEnvRef)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResourceEnvRef(ContextResourceEnvRef)}
   */
  @Test
  public void testAddResourceEnvRef() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextResourceEnvRef resource = new ContextResourceEnvRef();

    // Act
    namingResourcesImpl.addResourceEnvRef(resource);

    // Assert
    assertSame(namingResourcesImpl, resource.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addResourceEnvRef(ContextResourceEnvRef)}.
   * <ul>
   *   <li>Then {@link ContextResourceEnvRef} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResourceEnvRef(ContextResourceEnvRef)}
   */
  @Test
  public void testAddResourceEnvRef_thenContextResourceEnvRefNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    ContextResourceEnvRef resource = new ContextResourceEnvRef();

    // Act
    namingResourcesImpl.addResourceEnvRef(resource);

    // Assert that nothing has changed
    assertNull(resource.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addResourceLink(ContextResourceLink)}.
   * <ul>
   *   <li>Then {@link ContextResourceLink} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResourceLink(ContextResourceLink)}
   */
  @Test
  public void testAddResourceLink_thenContextResourceLinkNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextResourceLink resourceLink = new ContextResourceLink();

    // Act
    namingResourcesImpl.addResourceLink(resourceLink);

    // Assert
    assertSame(namingResourcesImpl, resourceLink.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addResourceLink(ContextResourceLink)}.
   * <ul>
   *   <li>Then {@link ContextResourceLink} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addResourceLink(ContextResourceLink)}
   */
  @Test
  public void testAddResourceLink_thenContextResourceLinkNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    ContextResourceLink resourceLink = new ContextResourceLink();

    // Act
    namingResourcesImpl.addResourceLink(resourceLink);

    // Assert that nothing has changed
    assertNull(resourceLink.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addService(ContextService)}.
   * <ul>
   *   <li>Then {@link ContextService} (default constructor) NamingResources is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addService(ContextService)}
   */
  @Test
  public void testAddService_thenContextServiceNamingResourcesIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    ContextService service = new ContextService();

    // Act
    namingResourcesImpl.addService(service);

    // Assert
    assertSame(namingResourcesImpl, service.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#addService(ContextService)}.
   * <ul>
   *   <li>Then {@link ContextService} (default constructor) NamingResources is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#addService(ContextService)}
   */
  @Test
  public void testAddService_thenContextServiceNamingResourcesIsNull() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());
    ContextService service = new ContextService();

    // Act
    namingResourcesImpl.addService(service);

    // Assert that nothing has changed
    assertNull(service.getNamingResources());
  }

  /**
   * Test {@link NamingResourcesImpl#findEjb(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findEjb(String)}
   */
  @Test
  public void testFindEjb() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findEjb("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findEjbs()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findEjbs()}
   */
  @Test
  public void testFindEjbs() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findEjbs().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findEnvironment(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findEnvironment(String)}
   */
  @Test
  public void testFindEnvironment() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findEnvironment("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findEnvironments()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findEnvironments()}
   */
  @Test
  public void testFindEnvironments() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findEnvironments().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findLocalEjb(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findLocalEjb(String)}
   */
  @Test
  public void testFindLocalEjb() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findLocalEjb("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findLocalEjbs()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findLocalEjbs()}
   */
  @Test
  public void testFindLocalEjbs() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findLocalEjbs().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findMessageDestinationRef(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findMessageDestinationRef(String)}
   */
  @Test
  public void testFindMessageDestinationRef() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findMessageDestinationRef("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findMessageDestinationRefs()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findMessageDestinationRefs()}
   */
  @Test
  public void testFindMessageDestinationRefs() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findMessageDestinationRefs().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findResource(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResource(String)}
   */
  @Test
  public void testFindResource() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findResource("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findResourceLink(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResourceLink(String)}
   */
  @Test
  public void testFindResourceLink() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findResourceLink("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findResourceLinks()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResourceLinks()}
   */
  @Test
  public void testFindResourceLinks() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findResourceLinks().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findResources()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResources()}
   */
  @Test
  public void testFindResources() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findResources().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findResourceEnvRef(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResourceEnvRef(String)}
   */
  @Test
  public void testFindResourceEnvRef() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findResourceEnvRef("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findResourceEnvRefs()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findResourceEnvRefs()}
   */
  @Test
  public void testFindResourceEnvRefs() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findResourceEnvRefs().length);
  }

  /**
   * Test {@link NamingResourcesImpl#findService(String)}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findService(String)}
   */
  @Test
  public void testFindService() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).findService("Name"));
  }

  /**
   * Test {@link NamingResourcesImpl#findServices()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#findServices()}
   */
  @Test
  public void testFindServices() {
    // Arrange, Act and Assert
    assertEquals(0, (new NamingResourcesImpl()).findServices().length);
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link ContextResourceLink} (default constructor) Name is {@code type=NamingResources}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenContextResourceLinkNameIsTypeNamingResources() throws LifecycleException {
    // Arrange
    ContextResourceLink resourceLink = new ContextResourceLink();
    resourceLink.setName("type=NamingResources");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addResourceLink(resourceLink);
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link ContextResource} (default constructor) Name is {@code type=NamingResources}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenContextResourceNameIsTypeNamingResources() throws LifecycleException {
    // Arrange
    ContextResource resource = new ContextResource();
    resource.setName("type=NamingResources");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addResource(resource);
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenNamingResourcesImpl() throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) addEnvironment {@link ContextEnvironment} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenNamingResourcesImplAddEnvironmentContextEnvironment() throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEnvironment(new ContextEnvironment());
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) addResource {@link ContextResource} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenNamingResourcesImplAddResourceContextResource() throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addResource(new ContextResource());
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) addResourceLink {@link ContextResourceLink} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_givenNamingResourcesImplAddResourceLinkContextResourceLink() throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addResourceLink(new ContextResourceLink());
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Then {@link NamingResourcesImpl} (default constructor) ObjectName Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_thenNamingResourcesImplObjectNameDomainIsCatalina() throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=NamingResources", objectName.getCanonicalName());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#initInternal()}.
   * <ul>
   *   <li>Then {@link NamingResourcesImpl} (default constructor) ObjectName Domain is {@code type=NamingResources}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#initInternal()}
   */
  @Test
  public void testInitInternal_thenNamingResourcesImplObjectNameDomainIsTypeNamingResources()
      throws LifecycleException {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setDomain("type=NamingResources");
    namingResourcesImpl.addEjb(new ContextEjb());

    // Act
    namingResourcesImpl.initInternal();

    // Assert
    ObjectName objectName = namingResourcesImpl.getObjectName();
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals("NamingResources", keyPropertyList.get("type"));
    assertEquals("type=NamingResources", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=NamingResources", objectName.getDomain());
    assertEquals("type=NamingResources", objectName.getKeyPropertyListString());
    assertEquals("type=NamingResources:type=NamingResources", objectName.getCanonicalName());
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
  }

  /**
   * Test {@link NamingResourcesImpl#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Container is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenBasicAuthenticatorContainerIsStandardContext() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setContainer(new StandardContext());

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(basicAuthenticator);

    // Act and Assert
    assertEquals("Catalina", namingResourcesImpl.getDomainInternal());
  }

  /**
   * Test {@link NamingResourcesImpl#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link BasicAuthenticator} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenBasicAuthenticatorDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
    basicAuthenticator.setDomain("Catalina");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(basicAuthenticator);

    // Act and Assert
    assertEquals("Catalina", namingResourcesImpl.getDomainInternal());
  }

  /**
   * Test {@link NamingResourcesImpl#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) Container is {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenNamingResourcesImplContainerIsBasicAuthenticator() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(new BasicAuthenticator());

    // Act and Assert
    assertEquals("Catalina", namingResourcesImpl.getDomainInternal());
  }

  /**
   * Test {@link NamingResourcesImpl#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) Container is {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenNamingResourcesImplContainerIsNamingResourcesImpl() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(new NamingResourcesImpl());

    // Act and Assert
    assertEquals("Catalina", namingResourcesImpl.getDomainInternal());
  }

  /**
   * Test {@link NamingResourcesImpl#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenNamingResourcesImpl_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new NamingResourcesImpl()).getDomainInternal());
  }

  /**
   * Test {@link NamingResourcesImpl#getObjectNameKeyProperties()}.
   * <p>
   * Method under test: {@link NamingResourcesImpl#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName(",context=");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(standardContext);

    // Act and Assert
    assertEquals("type=NamingResources,context=/,context=,container0=null",
        namingResourcesImpl.getObjectNameKeyProperties());
  }

  /**
   * Test {@link NamingResourcesImpl#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenNamingResourcesImpl() {
    // Arrange, Act and Assert
    assertEquals("type=NamingResources", (new NamingResourcesImpl()).getObjectNameKeyProperties());
  }

  /**
   * Test {@link NamingResourcesImpl#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenNamingResourcesImplContainerIsStandardEngine() {
    // Arrange
    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(new StandardEngine());

    // Act and Assert
    assertEquals("type=NamingResources", namingResourcesImpl.getObjectNameKeyProperties());
  }

  /**
   * Test {@link NamingResourcesImpl#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenStandardContextNameIsNumberSignNumberSign() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("##");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(standardContext);

    // Act and Assert
    assertEquals("type=NamingResources,context=/,container0=null", namingResourcesImpl.getObjectNameKeyProperties());
  }

  /**
   * Test {@link NamingResourcesImpl#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Name is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamingResourcesImpl#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_givenStandardContextNameIsSlash() {
    // Arrange
    StandardContext standardContext = new StandardContext();
    standardContext.setName("/");

    NamingResourcesImpl namingResourcesImpl = new NamingResourcesImpl();
    namingResourcesImpl.setContainer(standardContext);

    // Act and Assert
    assertEquals("type=NamingResources,context=/,container0=null", namingResourcesImpl.getObjectNameKeyProperties());
  }
}
