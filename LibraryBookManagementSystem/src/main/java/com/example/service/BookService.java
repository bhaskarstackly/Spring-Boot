package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.exception.BookNotFoundException;
import com.example.model.Book;

@Service
public class BookService {

    private int nextBookId = 101;

    private List<Book> books = new ArrayList<>();

    
    public Book addBook(Book request) {

        Book book = new Book(
            nextBookId++,
            request.getBookName().trim(),
            request.getAuthor().trim(),
            request.getPrice(),
            request.getAvailable()
        );

        books.add(book);

        return book;
    }


    public List<Book> getAllBooks() {
        return books;
    }


    public Book getBookById(Integer id) {

        for (Book book : books) {

            if (book.getBookId().equals(id)) {
                return book;
            }
        }

        throw new BookNotFoundException("Book not found with ID: " + id);
    }


    public Book updateBook(Book request) {

        for (Book book : books) {

            if (book.getBookId().equals(request.getBookId())) {

                book.setBookName(request.getBookName().trim());
                book.setAuthor(request.getAuthor().trim());
                book.setPrice(request.getPrice());
                book.setAvailable(request.getAvailable());

                return book;
            }
        }

        throw new BookNotFoundException(
            "Book not found with ID: " + request.getBookId()
        );
    }


    public void deleteBook(Integer id) {

        boolean removed = books.removeIf(book -> book.getBookId().equals(id));

        if (!removed) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
    }

    public Book patchBook(Integer id, Book request) {

        for (Book book : books) {

            if (book.getBookId().equals(id)) {

                if (request.getBookName() != null) {
                    book.setBookName(request.getBookName().trim());
                }

                if (request.getAuthor() != null) {
                    book.setAuthor(request.getAuthor().trim());
                }

                if (request.getPrice() != null) {
                    book.setPrice(request.getPrice());
                }

                if (request.getAvailable() != null) {
                    book.setAvailable(request.getAvailable());
                }

                return book;
            }
        }

        throw new BookNotFoundException(
            "Book not found with ID: " + id
        );
    }

    
    public Map<String, Double> getAvailableBooks() {

        return books.stream()
                .filter(book -> Boolean.TRUE.equals(book.getAvailable()))
                .collect(Collectors.toMap(
                        Book::getBookName,
                        Book::getPrice
                ));
    }
    
    
    public Book getBookByName(String bookName) {

        for (Book book : books) {

            if (book.getBookName().equalsIgnoreCase(bookName)) {
                return book;
            }
        }

        throw new BookNotFoundException("Book not found with name: " + bookName);
    }
    }
