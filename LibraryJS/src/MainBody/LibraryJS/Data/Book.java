package MainBody.LibraryJS.Data;

import java.util.Objects;

public class Book extends Publication {
public static final String TYPE = "Książka";

    private   String author;
    private   String isbn;

    public Book (String title, String author, int releaseDate, int pages, String publisher,
                 String isbn){
        super(title, releaseDate, pages, publisher);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toCsv() {
        return (TYPE + ": ") +
        getTitle() + ", " + author + ", " + getPublisher() + ", " + getReleaseDate()  + ", stron" + getPages()
                + ", ISBN: " + isbn + ".";

    }

    @Override
    public String toString() {
        return super.toString() + author + ", ISBN:" + isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return  Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, isbn);
    }
}
