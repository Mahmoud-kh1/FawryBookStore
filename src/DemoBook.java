public class DemoBook extends Book{
    public DemoBook(String title, String isbn, int year, double price) {
        super(title, isbn, year, price);
    }
    @Override
    public void dispatch(int quantity, String email, String address) {
        System.out.println("Fawry book store: Attempted dispatch of DemoBook ["
                + getTitle() + "] – not for sale.");
    }
}
