package software.sigma.library;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.sigma.library.mocks.MockDataServices;
import software.sigma.library.models.Book;
import software.sigma.library.models.BookTitle;
import software.sigma.library.models.Patron;
import software.sigma.library.models.Receipt;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryBorrowingTest {
    private Library l;
    private Receipt r;
    private Patron p;
    private Book b;
    private MockDataServices ds;

    @BeforeEach
    void setUp() throws Exception {
        ds = new MockDataServices();
        l = new Library(ds);
        p = new Patron("p");
        ds.addPatron(p);
        ds.setBookToReturn(new BookTitle("isbn"));
        b = l.acceptBook("isbn");
    }

    @Test
    public void testCanBorrowBookThatLibraryHas() throws Exception {
        r = l.borrow(b.getId(), "p");
        assertEquals(Receipt.OK, r.getStatus());
        assertTrue(b.isBorrowed());
    }

    @Test
    public void testCanNotBorrowBookLibraryDoesNotHave() throws Exception {
        r = l.borrow("isbnWeDontHave", "p");
        assertEquals(Receipt.NO_SUCH_BOOK, r.getStatus());
    }

    @Test
    public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
        ds.setBookToReturn(new BookTitle("secondIsbn"));
        Book copy2 = l.acceptBook("secondIsbn");
        r = l.borrow(b.getId(), "p");
        assertEquals(Receipt.OK, r.getStatus());
        r = l.borrow(copy2.getId(), "p");
        assertEquals(Receipt.OK, r.getStatus());
    }

    @Test
    public void testSetReturnDateTwoWeeksFromToday() throws Exception {
        Date now = new SimpleDateFormat("MM/dd/yyyy").parse("12/19/2006");
        Date returnDate = new SimpleDateFormat("MM/dd/yyyy").parse("1/2/2007");

        l.setTmpDate(now);
        r = l.borrow(b.getId(), "p");
        assertEquals(returnDate, r.getDate());
    }

    @Test
    public void testReceiptRemembersBorrowingPatron() throws Exception {
        r = l.borrow(b.getId(), "p");
        assertEquals(p, r.getPatron());
    }

    @Test
    public void testReceiptRemembersBorrowedCopy() throws Exception {
        r = l.borrow(b.getId(), "p");
        assertEquals("isbn", r.getCopy().getTitle().getIsbn());
    }
}
