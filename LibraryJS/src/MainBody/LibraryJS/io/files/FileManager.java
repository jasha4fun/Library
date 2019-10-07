package MainBody.LibraryJS.io.files;

import MainBody.LibraryJS.Data.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
