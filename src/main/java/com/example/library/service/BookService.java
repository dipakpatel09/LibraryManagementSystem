package com.example.library.service;

import com.example.library.exception.CustomizedGenericError;
import com.example.library.mapper.DataMapper;
import com.example.library.model.BookModel;
import com.example.library.repository.BookRepository;
import com.librarymanagement.openapi.model.Books;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final DataMapper dataMapper;

    public Books getBook(Long id) {
        Optional<BookModel> bookId = bookRepository.findById(id);
        if (bookId.isEmpty()) {
            throw new CustomizedGenericError("Book not found");
        }
        return dataMapper.toDto(bookId.get());
    }

    public List<Books> getAllBooks() {
        List<BookModel> bookModelList = bookRepository.findAll();

        return bookModelList.stream()
                .map(dataMapper::toDto)
                .toList();
    }

    public Books createBook(Books book) {
        BookModel bookModel = dataMapper.toEntity(book);
        BookModel savedBook = bookRepository.save(bookModel);

        return dataMapper.toDto(savedBook);
    }

    public Books updateBook(Long id, Books book) {
        Optional<BookModel> existing = bookRepository.findById(id);
        if (existing.isEmpty()) {
            throw new CustomizedGenericError("Book not found");
        }

        BookModel bookModel = existing.get();
        bookModel.setTitle(book.getTitle());
        bookModel.setAuthor(book.getAuthor());
        bookModel.setPublicationYear(book.getPublicationYear());
        BookModel savedBook = bookRepository.save(bookModel);
        return dataMapper.toDto(savedBook);
    }

    public void deleteBook(Long id) {
        Optional<BookModel> bookId = bookRepository.findById(id);
        if (bookId.isEmpty()) {
            throw new CustomizedGenericError("Book not found");
        }

        bookRepository.deleteById(id);
    }
}
