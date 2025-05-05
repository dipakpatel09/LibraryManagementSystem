package com.example.library.service;

import com.example.library.exception.CustomizedGenericError;
import com.example.library.mapper.DataMapper;
import com.example.library.model.BookModel;
import com.example.library.repository.BookRepository;
import com.librarymanagement.openapi.model.Books;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.library.BookDataUtil.getBookModelTestData;
import static com.example.library.BookDataUtil.getBookTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private DataMapper dataMapper;

    @Test
    void testGetBook_ShouldReturnBookDto_WhenBookExists() {
        BookModel bookModel = getBookModelTestData();
        Books bookDto = getBookTestData();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookModel));
        when(dataMapper.toDto(bookModel)).thenReturn(bookDto);

        Books result = bookService.getBook(1L);
        assertNotNull(result);
        verify(bookRepository).findById(1L);
    }

    @Test
    void testGetBook_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomizedGenericError.class, () -> bookService.getBook(1L));
    }

    @Test
    void testGetAllBooks_ShouldReturnBooksList() {
        BookModel bookModel = getBookModelTestData();
        Books bookDto = getBookTestData();
        List<BookModel> bookList = List.of(bookModel);
        when(bookRepository.findAll()).thenReturn(bookList);
        when(dataMapper.toDto(bookModel)).thenReturn(bookDto);

        List<Books> result = bookService.getAllBooks();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookRepository).findAll();
    }

    @Test
    void testCreateBook_ShouldReturnCreatedBookDto() {
        BookModel bookModel = getBookModelTestData();
        Books bookDto = getBookTestData();
        when(dataMapper.toEntity(any(Books.class))).thenReturn(bookModel);
        when(bookRepository.save(any(BookModel.class))).thenReturn(bookModel);
        when(dataMapper.toDto(bookModel)).thenReturn(bookDto);

        Books result = bookService.createBook(bookDto);
        assertNotNull(result);
        verify(bookRepository).save(any(BookModel.class));
    }

    @Test
    void testUpdateBook_ShouldThrowException_WhenBookNotFound() {
        Books bookDto = getBookTestData();
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomizedGenericError.class, () -> bookService.updateBook(1L, bookDto));
    }

    @Test
    void testDeleteBook_ShouldDeleteBook_WhenBookExists() {
        BookModel bookModel = getBookModelTestData();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookModel));

        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void testDeleteBook_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomizedGenericError.class, () -> bookService.deleteBook(1L));
    }

}