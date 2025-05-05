package com.example.library.controller;

import com.example.library.service.BookService;
import com.librarymanagement.openapi.api.ApiApi;
import com.librarymanagement.openapi.model.Books;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController implements ApiApi {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBook(Long id) {
        Books book = bookService.getBook(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> booksList = bookService.getAllBooks();
        return ResponseEntity.ok().body(booksList);
    }

    @PostMapping
    public ResponseEntity<Books> createBook(Books book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(Long id, Books book) {
        Books bookResponse = bookService.updateBook(id, book);
        return ResponseEntity.ok().body(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
