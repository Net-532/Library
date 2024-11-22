package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import service.BookService;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/books")
public class BookController extends HttpServlet {
    private BookService bookService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        // Ініціалізуємо BookService та ObjectMapper
        bookService = new BookService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        if (bookId != null) {
            Book book = bookService.getBookById(Long.parseLong(bookId));
            if (book != null) {
                writer.write(objectMapper.writeValueAsString(book));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Book not found");
            }
        } else {
            writer.write(objectMapper.writeValueAsString(bookService.getAllBooks()));
        }

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Book book = objectMapper.readValue(reader, Book.class);
        bookService.saveBook(book);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        PrintWriter writer = resp.getWriter();
        writer.write("Book created successfully");
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Book updatedBook = objectMapper.readValue(reader, Book.class);
        bookService.updateBook(updatedBook);

        PrintWriter writer = resp.getWriter();
        writer.write("Book updated successfully");
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");

        if (bookId != null) {
            boolean deleted = bookService.deleteBook(Long.parseLong(bookId));
            if (deleted) {
                resp.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = resp.getWriter();
                writer.write("Book deleted successfully");
                writer.flush();
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                PrintWriter writer = resp.getWriter();
                writer.write("Book not found");
                writer.flush();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
