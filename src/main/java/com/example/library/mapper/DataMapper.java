package com.example.library.mapper;

import com.example.library.model.BookModel;
import com.librarymanagement.openapi.model.Books;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper {

    BookModel toEntity(Books books);

    Books toDto(BookModel bookModel);
}
