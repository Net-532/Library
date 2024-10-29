package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private final List<Book> borrowedBooks;

    public Reader(String name) {
        super(name);  // Calls constructor from Person class
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + getName() + '\'' +
                ", id=" + getId() +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
