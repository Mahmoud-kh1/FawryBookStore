public abstract class Book  implements BookProperty{
    private  final String title;
    private final String isbn;
    private final int year;
    private double price;

    protected Book(String title, String isbn, int year, double price) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }
     @Override
    public String getTitle() {return title;}
    @Override
    public String getIsbn() {return isbn;}
    @Override
    public int getYear() {return year;}
    @Override
    public double getPrice() {return price;}

}
