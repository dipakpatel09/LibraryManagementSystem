package com.example.library.controller;

import com.example.library.service.BookService;
import com.librarymanagement.openapi.model.Books;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.library.BookDataUtil.getBookTestData;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void testGetBook() {
        Long bookId = 1L;
        Books bookTestData = getBookTestData();
        Mockito.when(bookService.getBook(bookId)).thenReturn(bookTestData);
        ResponseEntity<Books> expected = ResponseEntity.ok().body(bookTestData);
        ResponseEntity<Books> actual = bookController.getBook(bookId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAllBooks() {
        Books bookTestData = getBookTestData();
        Mockito.when(bookService.getAllBooks()).thenReturn(List.of(bookTestData));
        ResponseEntity<List<Books>> expected = ResponseEntity.ok().body(List.of(bookTestData));
        ResponseEntity<List<Books>> actual = bookController.getAllBooks();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCreateBook() {
        Books bookTestData = getBookTestData();
        ResponseEntity<Books> expected = ResponseEntity.status(HttpStatus.CREATED).build();
        ResponseEntity<Books> actual = bookController.createBook(bookTestData);
        Mockito.verify(bookService).createBook(bookTestData);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateBook() {
        Long bookId = 1L;
        Books bookTestData = getBookTestData();
        Mockito.when(bookService.updateBook(bookId, bookTestData)).thenReturn(bookTestData);
        ResponseEntity<Books> expected = ResponseEntity.ok().body(bookTestData);
        ResponseEntity<Books> actual = bookController.updateBook(bookId, bookTestData);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteBook() {
        ResponseEntity<Void> expected = ResponseEntity.noContent().build();
        Long bookId = 1L;
        ResponseEntity<Void> actual = bookController.deleteBook(bookId);
        Mockito.verify(bookService).deleteBook(bookId);
        Assertions.assertEquals(expected, actual);
    }

}