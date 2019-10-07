package MainBody.LibraryJS.Data;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable, CsvConversion{

    private String firstName;
    private String lastName;
    private String documentNumber;



    public User(String firstName, String lastName, String documentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(documentNumber, user.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, documentNumber);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + documentNumber;
    }

}
