import java.time.Year;
import java.util.*;

public class BookStore {
    private Map<String, Book> inventory = new HashMap<>();

    public void addBook(Book book, int quantity) {
        String isbn = book.getIsbn();
        if (inventory.containsKey(isbn)) {
            Book existing = inventory.get(isbn);
            if (existing instanceof PaperBook) {
                ((PaperBook) existing).increaseStock(quantity);
            }
        } else {
            if (book instanceof PaperBook) {
                ((PaperBook) book).increaseStock(quantity);
            }
            inventory.put(isbn, book);
        }
        System.out.println("Quantum book store: Added " + quantity
                + " of \"" + book.getTitle() + "\" (ISBN: " + isbn + ")");
    }

    public Book getInventoryBook(String isbn) {
        return inventory.get(isbn);
    }

    public boolean exists(String isbn) {
        return inventory.containsKey(isbn);
    }

    public List<Book> removeOutdated(int years) {
        int threshold = Year.now().getValue() - years;
        List<Book> removed = new ArrayList<>();
        inventory.entrySet().removeIf(entry -> {
            Book b = entry.getValue();
            if (b.getYear() < threshold) {
                removed.add(b);
                System.out.println("Quantum book store: Removed outdated ["
                        + b.getTitle() + "] (" + b.getYear() + ")");
                return true;
            }
            return false;
        });
        return removed;
    }

    public double buy(String isbn, int quantity, String email, String address) {
        Book book = inventory.get(isbn);
        if (book == null) {
            throw new NoSuchElementException(
                    "Quantum book store ERROR: ISBN " + isbn + " not found");
        }
        if (book instanceof DemoBook) {
            throw new IllegalArgumentException("Quantum book store ERROR: this book is not for sale");
        }

        double total = book.getPrice() * quantity;

        if (book instanceof PaperBook) {
            PaperBook pb = (PaperBook) book;
            pb.reduceStock(quantity);
        }
        book.dispatch(quantity, email, address);

        System.out.println("Quantum book store: Sold " + quantity
                + " of \"" + book.getTitle() + "\", Total = $" + total);
        return total;
    }
}