package MainBody.LibraryJS.app;

public class LibraryAppJS {
    private static final String APP_NAME ="Library Beta version: v2.7";

    private static final String INFO = "Witaj w mojej bibliotece! Najpierw wybierz format danych " +
            "w jakim chcesz wyświetlać i przechowywać pliki. Dalej pojawi się menu, w którym bedziesz mógł dodać " +
            "lub usunąć obiekt, wyświetlić interesujące Cię obiekty (książki, gry, muzykę etc., " +
            "jak rownież dodać użytkowników i umożliwić im wypożyczanie pozycji z bibliotek. Powodzenia! ~~ jasha4fun";

    public static void main(String[] args) {

        System.out.println(APP_NAME);
        System.out.println(INFO);

LibraryControl libraryControl = new LibraryControl();
libraryControl.controlLoop();

    }
}
