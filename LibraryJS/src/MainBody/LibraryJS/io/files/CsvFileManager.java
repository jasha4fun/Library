package MainBody.LibraryJS.io.files;

import MainBody.LibraryJS.exception.DataExportException;
import MainBody.LibraryJS.exception.DataImportException;
import MainBody.LibraryJS.exception.InvalidDataException;
import MainBody.LibraryJS.Data.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    private static final String PUBLICATIONS_FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, PUBLICATIONS_FILE_NAME);

    }

        private void exportUsers(Library library) {
            Collection<ApplicationUser> users = library.getUsers().values();
            exportToCsv(users, USERS_FILE_NAME);
        }

        private <T extends CsvConversion> void exportToCsv(Collection<T> collection, String fileName) {
            try (FileWriter fileWriter = new FileWriter(fileName);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (T element : collection) {
                    bufferedWriter.write(element.toCsv());
                    bufferedWriter.newLine();
                }

            } catch (IOException e) {
                throw new DataExportException("Błąd zapisu danych" + fileName);
            }
        }

        private Publication createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
    throw new InvalidDataException("Nieznany typ publikacji. " + type);
    }

    private Book createBook(String[] data) {
        String title = data[1];
        String author = data[3];
        String publisher = data[3];
        int releaseDate = Integer.valueOf(data[4]);
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int releaseDate = Integer.valueOf(data[3]);
        int pages = Integer.valueOf(data[4]);
        return new Magazine(title, releaseDate, pages, publisher);
    }

    private void importPublications(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PUBLICATIONS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(library::addPublication);

            } catch (FileNotFoundException e) {
                throw new DataImportException(("Brak pliku" + PUBLICATIONS_FILE_NAME));
            } catch (IOException e) {
            throw new DataExportException("Błąd odczytu pliku " + PUBLICATIONS_FILE_NAME);
        }

        }

        private void importUsers(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader( new FileReader(USERS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(library::addUser);

        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku" + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd odczytu pliku " + USERS_FILE_NAME);
        }
    }

    private ApplicationUser createUserFromString(String csvText) {
        String[] split = csvText.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String documentNumber = split[2];
        return new ApplicationUser(firstName, lastName, documentNumber);

    }
}