package com.example.library;

import com.example.library.model.BookModel;
import com.librarymanagement.openapi.model.Books;

public class BookDataUtil {

    public static Books getBookTestData() {
        return new Books()
                .id(1)
                .title("Java: A Beginner's Guide")
                .author("Herbert Schildt")
                .publicationYear(2002);
    }

    public static BookModel getBookModelTestData() {
        BookModel bookModel = new BookModel();
        bookModel.setId(1L);
        bookModel.setTitle("Java: A Beginner's Guide");
        bookModel.setAuthor("Herbert Schildt");
        bookModel.setPublicationYear(2002);
        return bookModel;
    }

}
