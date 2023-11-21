package com.libraryMicroservice.service;

import com.libraryMicroservice.model.BookDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDetails> findAllBook();
    BookDetails addBook(BookDetails bookDetails);
    void deleteById(int bookId) ;
    BookDetails updateBookById(int bookId, BookDetails bookDetails);
    List<BookDetails> getByTitle(String Title) ;
    List<BookDetails> getByGenre(String genre);
    List<BookDetails> getByAuthor(String author);


}
