package MainBody.LibraryJS.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationUser extends User {

    private List<Publication> publicationHistory = new ArrayList<>();
    private List<Publication> borrowedPublications = new ArrayList<>();

    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrwedPublications() {
        return borrowedPublications;
    }

    public ApplicationUser (String firstName, String lastName, String documentNumber) {
        super(firstName, lastName, documentNumber);
    }

    private void addPublicationToHistory(Publication pub) {
        publicationHistory.add(pub);
    }

    public void borrowPublication(Publication pub) {
        borrowedPublications.add(pub);
    }

    public boolean returnPublication(Publication pub) {
        boolean result = false;
        if (borrowedPublications.remove(pub)) {
            result = true;
            addPublicationToHistory(pub);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(publicationHistory, that.publicationHistory) &&
                Objects.equals(borrowedPublications, that.borrowedPublications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationHistory, borrowedPublications);

    }

    @Override
    public String toCsv() {
        return getFirstName() + " " + getLastName() + " " + getDocumentNumber();
    }
}
