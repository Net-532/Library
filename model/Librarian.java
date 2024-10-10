package model;

public class Librarian extends Person {

    public Librarian(String name) {
        super(name);
    }

    public void manageBooks() {
        System.out.println("Бібліотекар керує книжками.");
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + getName() + '\'' +
                ", id=" + getId() +
                '}';
    }
}
