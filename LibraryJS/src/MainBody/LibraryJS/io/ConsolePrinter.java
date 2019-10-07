package MainBody.LibraryJS.io;

import MainBody.LibraryJS.Data.*;
import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications){
        long count = publications.stream()
                .filter(p -> p instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0)
            printLine("Brak książek w bibliotece");
    }

    public void printMagazines(Collection<Publication> publications){
        long count = publications.stream()
                .filter(p -> p instanceof Magazine)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0)
            printLine("Brak książek w bibliotece");
    }

    public void printUsers(Collection<ApplicationUser> users) {
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);
    }

    public void printLine(String text){
        System.out.println(text);
    }
}
