package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
public class Reader extends Person {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reader_id")
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader() {}

    public Reader(String name) {
        super(name);
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
