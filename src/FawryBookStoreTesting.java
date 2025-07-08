import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

public class FawryBookStoreTesting {
    private BookStore store;

    @BeforeEach
    void setup() {
        store = new BookStore();
        store.addBook(new PaperBook("Paper Two", "2", 2000, 20.0, 0), 5);
        store.addBook(new Ebook("EBook Two", "222", 2010, 15.0, "pdf"), 0);
        store.addBook(new DemoBook("Demo Three", "333", 2020, 0.0), 0);
    }

    @Test
    @DisplayName("Add Book increases inventory correctly")
    void testAddBook() {
        store.addBook(new PaperBook("Paper Two", "2", 2000, 20.0, 3), 3);
        PaperBook pb = (PaperBook) store.getInventoryBook("2");
        assertEquals(8, pb.getStock());
    }

    @Test
    @DisplayName("Remove outdated books by age")
    void testRemoveOutdated() {
        List<Book> removed = store.removeOutdated(20);
        assertTrue(removed.stream().anyMatch(b -> b.getIsbn().equals("2")));
        assertFalse(store.exists("2"));
    }

    @Test
    @DisplayName("Successful paper book purchase reduces stock and returns total")
    void testBuyPaperBook() {
        double paid = store.buy("2", 2, "x@example.com", "Addr");
        assertEquals(40.0, paid);
        PaperBook pb = (PaperBook) store.getInventoryBook("2");
        assertEquals(3, pb.getStock());
    }

    @Test
    @DisplayName("Buying more than stock throws error")
    void testBuyInsufficientStock() {
        assertThrows(IllegalArgumentException.class, () -> {
            store.buy("2", 10, "x@example.com", "Addr");
        });
    }

    @Test
    @DisplayName("Buy EBook does not affect stock and returns total")
    void testBuyEBook() {
        double paid = store.buy("222", 1, "y@example.com", "Addr");
        assertEquals(15.0, paid);
        assertTrue(store.exists("222"));
    }

    @Test
    @DisplayName("Buying non-existent ISBN throws NoSuchElementException")
    void testBuyNonexistent() {
        assertThrows(NoSuchElementException.class, () -> {
            store.buy("999", 1, "a@example.com", "Addr");
        });
    }
}