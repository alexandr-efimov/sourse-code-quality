package software.sigma.library.mocks;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.sigma.library.data.PatronDoesNotExistException;
import software.sigma.library.models.Patron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockDataServicesPatronAccessTest {

    MockDataServices ds = new MockDataServices();

    @BeforeEach
    protected void setUp() throws Exception {
        ds = new MockDataServices();
    }

    @Test
    public void testCreate() {
        assertEquals(0, ds.countActivePatrons());
    }

    @Test
    public void testAddOne() throws PatronDoesNotExistException {
        ds.addPatron(new Patron("Bob"));
        assertEquals(1, ds.countActivePatrons());
        Patron p1 = ds.findPatron("Bob");
        assertTrue(p1.hasId("Bob"));
    }

    @Test
    public void testAddAFew() throws PatronDoesNotExistException {
        ds.addPatron(new Patron("Bob"));
        ds.addPatron(new Patron("Tim"));
        ds.addPatron(new Patron("Dean"));
        assertEquals(3, ds.countActivePatrons());
        Patron p1 = ds.findPatron("Tim");
        assertTrue(p1.hasId("Tim"));
    }

    @Test
    public void testRetrieveNonexistant() {
        assertNull(ds.findPatron("nonesuch"));
    }

}
