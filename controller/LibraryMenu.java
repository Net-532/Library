package controller;

import model.Book;
import model.Librarian;
import model.Reader;
import service.LibraryService;
import util.InputValidator;

import java.util.Scanner;

public class LibraryMenu {

    private static final LibraryService libraryService = new LibraryService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Створення тестових даних
        libraryService.addBook(new Book("Java для початківців", "Джон Доу", "123-1234567890"));
        libraryService.addReader(new Reader("Аліса"));
        libraryService.addLibrarian(new Librarian("Іван"));

        while (!exit) {
            System.out.println("\n=== Меню Бібліотеки ===");
            System.out.println("1. Додати книгу");
            System.out.println("2. Оновити книгу");
            System.out.println("3. Видалити книгу");
            System.out.println("4. Показати всі книги");
            System.out.println("5. Додати читача");
            System.out.println("6. Оновити читача");
            System.out.println("7. Видалити читача");
            System.out.println("8. Показати всіх читачів");
            System.out.println("9. Додати бібліотекаря");
            System.out.println("10. Оновити бібліотекаря");
            System.out.println("11. Видалити бібліотекаря");
            System.out.println("12. Показати всіх бібліотекарів");
            System.out.println("13. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> updateBook(scanner);
                case 3 -> removeBook(scanner);
                case 4 -> libraryService.showBooks();
                case 5 -> addReader(scanner);
                case 6 -> updateReader(scanner);
                case 7 -> removeReader(scanner);
                case 8 -> libraryService.showReaders();
                case 9 -> addLibrarian(scanner);
                case 10 -> updateLibrarian(scanner);
                case 11 -> removeLibrarian(scanner);
                case 12 -> libraryService.showLibrarians();
                case 13 -> exit = true;
                default -> System.out.println("Невірна опція.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Введіть назву книги:");
        String title = scanner.nextLine();
        System.out.println("Введіть автора книги:");
        String author = scanner.nextLine();
        System.out.println("Введіть ISBN книги (формат: 123-1234567890):");
        String isbn = scanner.nextLine();

        if (InputValidator.isValidIsbn(isbn)) {
            libraryService.addBook(new Book(title, author, isbn));
        } else {
            System.out.println("Невірний формат ISBN.");
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Введіть ISBN книги для оновлення:");
        String isbn = scanner.nextLine();
        if (InputValidator.isValidIsbn(isbn)) {
            System.out.println("Введіть нову назву:");
            String newTitle = scanner.nextLine();
            System.out.println("Введіть нового автора:");
            String newAuthor = scanner.nextLine();
            libraryService.updateBook(isbn, newTitle, newAuthor);
        } else {
            System.out.println("Невірний формат ISBN.");
        }
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Введіть ISBN книги для видалення:");
        String isbn = scanner.nextLine();
        if (InputValidator.isValidIsbn(isbn)) {
            libraryService.removeBook(isbn);
        } else {
            System.out.println("Невірний формат ISBN.");
        }
    }

    private static void addReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            libraryService.addReader(new Reader(name));
        } else {
            System.out.println("Невірне ім'я.");
        }
    }

    private static void updateReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача для оновлення:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            System.out.println("Введіть нове ім'я:");
            String newName = scanner.nextLine();
            libraryService.updateReader(name, newName);
        } else {
            System.out.println("Невірне ім'я.");
        }
    }

    private static void removeReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача для видалення:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            libraryService.removeReader(name);
        } else {
            System.out.println("Невірне ім'я.");
        }
    }

    private static void addLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            libraryService.addLibrarian(new Librarian(name));
        } else {
            System.out.println("Невірне ім'я.");
        }
    }

    private static void updateLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря для оновлення:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            System.out.println("Введіть нове ім'я:");
            String newName = scanner.nextLine();
            libraryService.updateLibrarian(name, newName);
        } else {
            System.out.println("Невірне ім'я.");
        }
    }

    private static void removeLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря для видалення:");
        String name = scanner.nextLine();
        if (InputValidator.isValidName(name)) {
            libraryService.removeLibrarian(name);
        } else {
            System.out.println("Невірне ім'я.");
        }
    }
}
