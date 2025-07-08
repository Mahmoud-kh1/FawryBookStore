public interface BookProperty {
    String getIsbn();
    String getTitle();
    int getYear();
    double getPrice();

    void dispatch(int quantity, String email, String address);
}