package com.baitap.Bai2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.baitap.Bai2.model.Book;
import com.baitap.Bai2.service.BookService;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    // Directs to the home.html page
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    // Directs to books/list.html
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/list"; 
    }

    // Shows the books/form.html for adding
    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Shows the books/form.html for editing
    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "books/form";
        }
        return "redirect:/books";
    }

    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable int id, @ModelAttribute Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}