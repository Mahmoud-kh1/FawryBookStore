public class Main {
    public static void main(String[] args) {
        BookStore store = new BookStore();

        store.addBook(new PaperBook("java 1", "978-0134685991",
                2018, 45.0, 10), 10);
        store.addBook(new Ebook("java 2", "978-0201633610", 2017,
                1994, "pdf"), 0);
        store.addBook(new DemoBook("java 3", "DEMO001", 2016,
                2021), 0);

//         remove books older than 20 years
        store.removeOutdated(20);


      try {
            double paid = store.buy("978-0134685991", 2,
                    "alice@example.com",
                    "123 Main St, Cairo");
            System.out.println("Fawry book BookStore: Customer paid $" + paid);
        } catch (Exception ex) {
           System.err.println(ex.getMessage());
        }

        store.buy("978-0201633610", 1,
                "bob@example.com", "no-address");


        store.buy("DEMO001", 1, "carol@example.com", "nowhere");

    }

}