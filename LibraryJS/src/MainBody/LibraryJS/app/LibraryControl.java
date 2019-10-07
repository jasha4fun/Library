package MainBody.LibraryJS.app;

import MainBody.LibraryJS.Data.*;
import MainBody.LibraryJS.Data.comparator.AlphabeticalTitleComparator;
import MainBody.LibraryJS.exception.*;
import MainBody.LibraryJS.io.ConsolePrinter;
import MainBody.LibraryJS.io.DataReader;
import MainBody.LibraryJS.io.files.FileManager;
import MainBody.LibraryJS.io.files.FileManagerBuilder;

import java.util.Comparator;
import java.util.InputMismatchException;

 class LibraryControl {

     private ConsolePrinter printer = new ConsolePrinter();
     private DataReader dataReader = new DataReader(printer);
     private FileManager fileManager;

     private Library library;

     LibraryControl() {
         fileManager = new FileManagerBuilder(printer, dataReader).build();
         try {
             library = fileManager.importData();
             printer.printLine("Wczytano dane. ");
         } catch (DataImportException | InvalidDataException e) {
             printer.printLine(e.getMessage());
             printer.printLine("Zainicjowano nową bazę. ");
             library = new Library();
         }
     }

     void controlLoop() {
         Option option;

         do {
             printOptions();
             option = getOption();
             switch (option) {
                 case ADD_BOOK:
                     addBook();
                     break;
                 case ADD_MAGAZINE:
                     addMagazine();
                     break;
                 case PRINT_BOOKS:
                     printBooks();
                     break;
                 case PRINT_MAGAZINES:
                     printMagazines();
                     break;
                 case DELETE_BOOK:
                     deleteBook();
                     break;
                 case DELETE_MAGAZINE:
                     deleteMagazine();
                     break;
                 case ADD_USER:
                     addUser();
                     break;
                 case PRINT_USERS:
                     printUsers();
                     break;
                 case FIND_PUBLICATION:
                     findPublication();
                     break;
                 case EXIT:
                     exit();
                     break;
                 default:
                     printer.printLine(" Brak dostępnych opcji!");
             }

         } while (option != Option.EXIT);
     }

     private Option getOption() {
         boolean optionOK = false;
         Option option = null;
         while (!optionOK) {
             try {
                 option = Option.createFormInt(dataReader.getInt());
                 optionOK = true;
             } catch (NoSuchOptionException e) {
                 printer.printLine(e.getMessage() + " Wprowadź ponownie: ");
             } catch (InputMismatchException ignored) {
                 printer.printLine(" Wprowadzono wartość, ktora nie jest liczbą, wprowadź ponownie: ");
             }
         }

         return option;
     }

     private void printOptions() {
         printer.printLine(" Wybierz dostępną opcję: ");
         for (Option option : Option.values()) {
             printer.printLine(option.toString());
         }
     }

     private void addBook() {
         try {
             Book book = dataReader.readAndCreateBook();
             library.addPublication(book);
         } catch (InputMismatchException e){
             printer.printLine(" Nie udało się utworzyć książki - niepoprawne dane");
         } catch (ArrayIndexOutOfBoundsException e){
             printer.printLine(" Brak miejsca w bibliotece, nie można dodać kolejnej książki");
         }
     }

     private void printBooks() {
        printer.printBooks(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle,String.CASE_INSENSITIVE_ORDER)));
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine(" Nie udało się utworzyć magazynu - niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(" Brak miejsca w bibliotece, nie można dodać kolejnej książki");
        }
    }
    private void printMagazines() {
        printer.printMagazines(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle,String.CASE_INSENSITIVE_ORDER)));
    }

     private void addUser() {
         ApplicationUser applicationUser = dataReader.createApplicationUser();
         try {
             library.addUser(applicationUser);
         } catch (UserAlreadyExistsException e) {
             printer.printLine(e.getMessage());
         }
     }

     private void printUsers() {
         printer.printUsers(library.getSortedUsers(
                 Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER)));
     }

     private void findPublication() {
         printer.printLine("Podaj tytuł publikacji");
         String title = dataReader.getString();
         String notFoundMessage = "Brak publikacji o takim tytule";
         library.findPublicationByTitle(title)
                 .map(Publication::toString)
                 .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
     }

    private void deleteBook() {
         try {
             Book book = dataReader.readAndCreateBook();
             if (library.removePublication(book))
                 printer.printLine("Książka została usunięta.");
             else
                 printer.printLine("Brak wskazanej książki w bibliotece");
         } catch (InputMismatchException e) {
             printer.printLine("Niepoprawne dane");
         }
    }

    private void deleteMagazine() {
         try {
             Magazine magazine = dataReader.readAndCreateMagazine();
             if (library.removePublication(magazine))
                 printer.printLine("Magazyn został usunięty.");
             else
                 printer.printLine("Brak wskazanego magazynu w bibliotece");
         } catch (InputMismatchException e) {
             printer.printLine("Niepoprawne dane.");
         }
    }

    private void exit() {
         try {
             fileManager.exportData(library);
             printer.printLine("Zapis danych do pliku zakończony powodzeniem");
         } catch (DataExportException e) {
             printer.printLine(e.getMessage());
         }

        dataReader.close();
        printer.printLine(" Do zobaczenia!");
    }

     private enum Option {
         EXIT (0, " Wyjście z programu,"),
         ADD_BOOK (1, " Dodanie książki,"),
         ADD_MAGAZINE (2, " Dodanie magazynu,"),
         PRINT_BOOKS (3, " Wyświetlenie książek dostępnych w bibliotece,"),
         PRINT_MAGAZINES (4, " Wyświetlenie magazynow dostępnych w bibliotece."),
         DELETE_BOOK(5, "Usuń książkę"),
         DELETE_MAGAZINE(6, "Usuń magazyn"),
         ADD_USER(7, "Dodaj użytkownika"),
         PRINT_USERS (8, "Wyświetl użytkowników"),
         FIND_PUBLICATION (9, "Wyszukaj publikację");

         private int value;
         private String description;

         Option (int value, String desc){
             this.value = value;
             this.description = desc;
         }

         @Override
         public String toString() {
             return value + " - " + description;
         }

         static Option createFormInt (int option) throws NoSuchOptionException {
             try {
                 return Option.values()[option];
             } catch (ArrayIndexOutOfBoundsException e) {
                 throw new NoSuchOptionException("Brak opcji o numerze " + option);
             }
         }
     }

}
