package software.sigma.library.models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PatronTest {

    @Test
    public void testCreate() {
        Address address = new Address("street1", "street2", "city", "state", "zip");
        assertNotNull(new Patron("1", "John", "Q", "Public", address, "8479991234"));
    }
}
