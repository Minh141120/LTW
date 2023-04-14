package main.java.com.example.demo.controller;

import com.example.demo.DAO.BookDAO;
import com.example.demo.model.Book;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@Controller
public class BookController {
    private BookDAO bookDAO = new BookDAO();
    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> books = bookDAO.selectAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    @GetMapping("/books2")
    @ResponseBody
    public List<Book> getBooks() throws IOException {
        return bookDAO.selectAllBooks();
    }
    @GetMapping("/book/{bookcode}")
    public String getBook(Model model, @PathVariable String bookcode){
        model.addAttribute("bookcode", bookcode);
        Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
        model.addAttribute("book", book);
        return "book-detail";
    }
    @PostMapping("/book/save/{bookcode}")
    public String addBook(@Valid @RequestBody Book book, @PathVariable String bookcode) {
        bookDAO.insertBook(book);
        return "redirect:/books";
    }
    @PutMapping("/book/save/{bookcode}")
    public String updateBook(Book book, @PathVariable String bookcode) {
        bookDAO.updateBook(book);
        return "redirect:/books";
    }
    @DeleteMapping("/book/delete/{bookcode}")
    public String deleteBook(@PathVariable String bookcode){
        bookDAO.deleteBook(Integer.valueOf(bookcode));
        return "redirect:/books";
    }
}
