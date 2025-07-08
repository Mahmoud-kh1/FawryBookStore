public class Ebook extends Book {
    private final String fileType;
    public Ebook(String title, String isbn, int year, double price, String fileType) {
        super(title, isbn, year, price);
        this.fileType = fileType;
    }
    public String getFileType() {
        return fileType;
    }
    @Override
    public void dispatch(int quantity, String email, String address) {
        // todo send to Mail service
    }

}
