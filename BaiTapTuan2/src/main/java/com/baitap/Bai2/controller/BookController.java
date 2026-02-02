package com.baitap.Bai2.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baitap.Bai2.model.Book;
import com.baitap.Bai2.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Map<String, String> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return createResponse("Book added successfully!");
    }

    @PutMapping("/{id}")
    public Map<String, String> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return createResponse("Book updated successfully!");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return createResponse("Book deleted successfully!");
    }

    // Helper method to keep things tidy
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}