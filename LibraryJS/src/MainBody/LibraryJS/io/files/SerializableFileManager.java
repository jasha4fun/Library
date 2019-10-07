package MainBody.LibraryJS.io.files;

import MainBody.LibraryJS.exception.DataExportException;
import MainBody.LibraryJS.exception.DataImportException;
import MainBody.LibraryJS.Data.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";


    @Override
    public Library importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Library) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new DataImportException(" Plik nie istnieje! " + FILE_NAME);

        } catch (IOException e) {
            throw new DataImportException(" Błąd odczytu pliku! " + FILE_NAME);

        } catch (ClassNotFoundException e) {
            throw new DataImportException(" Typ danych niezgodny! " + FILE_NAME);
        }
        }

    @Override
    public void exportData(Library library) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);

        } catch (FileNotFoundException e) {
            throw new DataExportException(" Brak pliku! " + FILE_NAME);

        } catch (IOException e) {
            throw new DataExportException(" Błąd zapisu danych do pliku! " + FILE_NAME);
        }

    }
}

