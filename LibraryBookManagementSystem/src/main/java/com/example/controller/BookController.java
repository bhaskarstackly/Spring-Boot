package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.model.Book;
import com.example.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public Book addBook( @RequestBody @Valid Book request) {
    	return  bookService.addBook(request);
    }

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/updateBook")
    public String updateBook(@Valid @RequestBody Book request) {
         bookService.updateBook(request);
         return "Updated successfully";
    }

    @PatchMapping("/{id}")
    public Book patchBook(@PathVariable Integer id, @RequestBody Book request) {
        return bookService.patchBook(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
         bookService.deleteBook(id);
         return "Book deleted successfully";
    }

    @GetMapping("/availableBooks")
    public Map<String, Double> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }
    
    @GetMapping("/searchByName/{bookName}")
    public Book getBookByName(@PathVariable String bookName) {
        return bookService.getBookByName(bookName);
    }
}