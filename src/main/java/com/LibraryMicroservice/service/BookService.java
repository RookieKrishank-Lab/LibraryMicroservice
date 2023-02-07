package com.LibraryMicroservice.service;

import com.LibraryMicroservice.exception.BookAuthorNotFoundException;
import com.LibraryMicroservice.exception.BookGenreNotFoundException;
import com.LibraryMicroservice.exception.BookNotFoundException;
import com.LibraryMicroservice.exception.BookTitleNotFoundException;
import com.LibraryMicroservice.model.BookDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<BookDetails> findAll();
    BookDetails addBook(BookDetails bookDetails);
    void deleteById(int bookId) ;//throws BookNotFoundException;
    BookDetails updateBookById(int bookId, BookDetails bookDetails);
    List<BookDetails> getByTitle(String Title) ;
    List<BookDetails> getByGenre(String genre);
//    List<BookDetails> findByAuthor(String Author) throws BookAuthorNotFoundException;


}
