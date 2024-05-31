package com.apirest.apirest.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.apirest.Repositories.BookRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.apirest.apirest.Entities.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The book with ID: " + id + " was not found"));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book details) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The book with ID: " + id + " was not found"));

        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setPrice(details.getPrice());

        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The book with ID: " + id + " was not found"));

        bookRepository.delete(book);
        return "The product with ID: " + id + " successfully deleted.";
    }
}
