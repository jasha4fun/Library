package MainBody.LibraryJS.Data;

import java.util.Objects;

public class Magazine extends Publication {
    public static final String TYPE = "Magazyn";
    private String title;
    private int releaseDate;
    private int pages;
    private String publisher;

    @Override
    public String toCsv() {
        return (TYPE + ": ") + getTitle() + ", " + getPublisher() + ", " + getReleaseDate() + ", stron " + getPages()
                + ".";
    }

    public Magazine(String tile, int releaseDate, int pages, String publisher) {
        super(tile, releaseDate, pages, publisher);
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = this.title;
    }

    public int getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate){
        this.releaseDate = this.releaseDate;
    }

    public int getPages(){
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;

    }
    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, pages, publisher);
    }
}
