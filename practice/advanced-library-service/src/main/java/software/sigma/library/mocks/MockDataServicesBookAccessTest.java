package software.sigma.library.mocks;


import org.junit.jupiter.api.Test;
import software.sigma.library.models.BookTitle;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MockDataServicesBookAccessTest {

    @Test
    public void testAddCopyCreateUniqueId() throws Exception {
        MockDataServices g = new MockDataServices();
        String id1 = g.addBook(new BookTitle("isbn")).getId();
        String id2 = g.addBook(new BookTitle("isbn")).getId();
        assertNotEquals(id1, id2);
    }

}
