package MainBody.LibraryJS.Data;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable, Comparable <Publication> , CsvConversion {

    private String title;
    private int releaseDate;
    private int pages;
    private String publisher;

    Publication(String title, int releaseDate, int pages, String publisher) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.pages = pages;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    int getPages() {
        return pages;
    }

    void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return title + ", " + publisher + ", " + releaseDate + ", stron: " + pages + ", ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return releaseDate == that.releaseDate &&
                pages == that.pages &&
                Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publisher, releaseDate, pages);
    }

    @Override
    public int compareTo(Publication p) {
        return title.compareToIgnoreCase(p.title);
    }
}