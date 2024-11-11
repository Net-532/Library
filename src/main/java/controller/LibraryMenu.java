package controller;

import model.Book;
import model.Librarian;
import model.Reader;
import service.LibraryService;

import util.InputValidator;
import util.SpringContext;

import java.util.List;
import java.util.Scanner;

public class LibraryMenu {

    private static final LibraryService libraryService = SpringContext.getBean(LibraryService.class);

    public static void main(String[] args) {

        System.out.println("Запуск ініціалізації бази даних...");

        System.out.println("Ініціалізація завершена.");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

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
                case 4 -> showBooks();
                case 5 -> addReader(scanner);
                case 6 -> updateReader(scanner);
                case 7 -> removeReader(scanner);
                case 8 -> showReaders();
                case 9 -> addLibrarian(scanner);
                case 10 -> updateLibrarian(scanner);
                case 11 -> removeLibrarian(scanner);
                case 12 -> showLibrarians();
                case 13 -> exit = true;
                default -> System.out.println("Невірна опція.");
            }
        }

        scanner.close();
    }

    private static void showBooks() {
        List<Book> books = libraryService.showBooks();
        books.forEach(book -> System.out.println(book.getTitle() + " - " + book.getAuthor() + " - " + book.getIsbn()));
    }

    private static void showReaders() {
        List<Reader> readers = libraryService.showReaders();
        readers.forEach(reader -> System.out.println(reader.getName()));
    }

    private static void showLibrarians() {
        List<Librarian> librarians = libraryService.showLibrarians();
        librarians.forEach(librarian -> System.out.println(librarian.getName()));
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
        Book book = libraryService.showBooks().stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
        if (book != null) {
            System.out.println("Введіть нову назву:");
            book.setTitle(scanner.nextLine());
            System.out.println("Введіть нового автора:");
            book.setAuthor(scanner.nextLine());
            libraryService.updateBook(book);
        } else {
            System.out.println("Книга з таким ISBN не знайдена.");
        }
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Введіть ISBN книги для видалення:");
        String isbn = scanner.nextLine();
        libraryService.removeBook(isbn);
    }

    private static void addReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача:");
        String name = scanner.nextLine();
        libraryService.addReader(new Reader(name));
    }

    private static void updateReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача для оновлення:");
        String name = scanner.nextLine();
        Reader reader = libraryService.showReaders().stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
        if (reader != null) {
            System.out.println("Введіть нове ім'я:");
            reader.setName(scanner.nextLine());
            libraryService.updateReader(reader);
        } else {
            System.out.println("Читача з таким ім'ям не знайдено.");
        }
    }

    private static void removeReader(Scanner scanner) {
        System.out.println("Введіть ім'я читача для видалення:");
        String name = scanner.nextLine();
        libraryService.removeReader(name);
    }

    private static void addLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря:");
        String name = scanner.nextLine();
        libraryService.addLibrarian(new Librarian(name));
    }

    private static void updateLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря для оновлення:");
        String name = scanner.nextLine();
        Librarian librarian = libraryService.showLibrarians().stream().filter(l -> l.getName().equals(name)).findFirst().orElse(null);
        if (librarian != null) {
            System.out.println("Введіть нове ім'я:");
            librarian.setName(scanner.nextLine());
            libraryService.updateLibrarian(librarian);
        } else {
            System.out.println("Бібліотекаря з таким ім'ям не знайдено.");
        }
    }

    private static void removeLibrarian(Scanner scanner) {
        System.out.println("Введіть ім'я бібліотекаря для видалення:");
        String name = scanner.nextLine();
        libraryService.removeLibrarian(name);
    }
}
