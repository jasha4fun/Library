package MainBody.LibraryJS.Data;

import MainBody.LibraryJS.exception.PublicationAlreadyExistsException;
import MainBody.LibraryJS.exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, ApplicationUser> users = new HashMap<>();

    public Optional<Publication> findPublicationByTitle(String title) {
        return Optional.ofNullable(publications.get(title));
    }

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator) {
        ArrayList<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }

    public Collection <ApplicationUser> getSortedUsers(Comparator<ApplicationUser> comparator) {
        ArrayList <ApplicationUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, ApplicationUser> getUsers() {
        return users;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle()))
            throw new PublicationAlreadyExistsException(
                    "Publikacja o takim tytule już istnieje" + publication.getTitle());


        publications.put(publication.getTitle(), publication);
    }

    public void addUser (ApplicationUser user) {
        if (users.containsKey(user.getDocumentNumber()))
            throw new UserAlreadyExistsException("Podany użytkownik już istnieje " + user.getDocumentNumber());
        users.put(user.getDocumentNumber(), user);
    }

    public boolean removePublication(Publication publication) {
        if (publications.containsValue(publication)) {
            publications.remove(publication.getTitle());
            return true;
        } else {
            return false;
        }
    }


}