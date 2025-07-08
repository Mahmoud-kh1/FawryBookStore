public class PaperBook extends Book {
    private int stock = 0;
    public PaperBook(String title, String isbn,int year, double price, int stock) {
        super(title, isbn, year, price);
        this.stock = stock;
    }
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public void reduceStock(int qty) throws IllegalArgumentException {
        if (qty > stock) throw new IllegalArgumentException(
                "there is no enough Stock for : " + getIsbn());
        stock -= qty;
    }
    @Override
    public void dispatch(int quantity, String email, String address) {
        // todo send to ship serivce
    }
    public void increaseStock(int q) {
        stock += q;
    }
}
