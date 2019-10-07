package MainBody.LibraryJS.io;

import MainBody.LibraryJS.Data.ApplicationUser;
import MainBody.LibraryJS.Data.Book;
import MainBody.LibraryJS.Data.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader (ConsolePrinter printer){
        this.printer = printer;
    }

    public String getString() {
        return sc.nextLine();
    }

    public void close(){
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }
    public Book readAndCreateBook(){
        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Autor: ");
        String author = sc.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn = sc.nextLine();
        printer.printLine("Ilość stron:");
        int pages = getInt();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();

            return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine(){
        printer.printLine("Tytuł: ");
        String tile = sc.nextLine();
        printer.printLine("Data wydania: ");
        int releaseDate = getInt();
        printer.printLine("Ilość stron: ");
        int pages = getInt();
        printer.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();

        return new Magazine(tile, releaseDate, pages, publisher);
    }

    public ApplicationUser createApplicationUser() {
        printer.printLine("Imię ");
        String firstName = sc.nextLine();
        printer.printLine("Nazwisko ");
        String lastName = sc.nextLine();
        printer.printLine("Numer dokumentu ");
        String documentNumber = sc.nextLine();
        return new ApplicationUser(firstName, lastName, documentNumber);

    }
}
