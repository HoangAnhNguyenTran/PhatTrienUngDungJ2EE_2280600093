package com.baitap.Bai2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baitap.Bai2.model.Book;
import com.baitap.Bai2.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public void addBook(Book book) {
		if (book.getId() == null || book.getId() == 0) {
			book.setId(getNextAvailableId());
		}
		bookRepository.save(book);
	}

	public void updateBook(int id, Book updatedBook) {
		Book book = bookRepository.findById(id).orElse(null);
		if (book != null) {
			book.setTitle(updatedBook.getTitle());
			book.setAuthor(updatedBook.getAuthor());
			bookRepository.save(book);
		}
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}

	private Integer getNextAvailableId() {
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			return 1;
		}
		
		// Find the smallest available ID
		for (int i = 1; i <= books.size() + 1; i++) {
			final int checkId = i;
			boolean exists = books.stream().anyMatch(book -> book.getId() == checkId);
			if (!exists) {
				return i;
			}
		}
		return books.size() + 1;
	}
}