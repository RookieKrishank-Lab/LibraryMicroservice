package com.LibraryMicroservice.service;

import com.LibraryMicroservice.exception.BookGenreNotFoundException;
import com.LibraryMicroservice.exception.BookNotFoundException;
import com.LibraryMicroservice.exception.BookTitleNotFoundException;
import com.LibraryMicroservice.exception.ResourceAlreadyExistException;
import com.LibraryMicroservice.model.BookDetails;
import com.LibraryMicroservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    //ADD
    @Override
    public BookDetails addBook(BookDetails bookDetails) {
        if (bookRepository.findById(bookDetails.getBookId()).isEmpty()) {
            return bookRepository.save(bookDetails);
        }
        throw new ResourceAlreadyExistException("Book already present");
    }

    //GET ALL
    @Override
    public List<BookDetails> findAll() {
        return bookRepository.findAll();
    }

    //UPDATE
    @Override
    public BookDetails updateBookById(int bookId, BookDetails bookDetails) {
        BookDetails bookDetails1 = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("book not found"));
        bookDetails1.setPrice(bookDetails.getPrice());
        bookDetails1.setAuthor(bookDetails.getAuthor());
        bookDetails1.setBookTitle(bookDetails.getBookTitle());
        bookDetails1.setBookGenre(bookDetails.getBookGenre());
        BookDetails details = bookRepository.save(bookDetails1);
        return details;
    }

    //DELETE
    @Override
    public void deleteById(int bookId) {
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.deleteById(bookId);
    }

    //FIND BY TITLE
    @Override
    public  List<BookDetails> getByTitle(String title){
        List<BookDetails> bookDetails = bookRepository.findByBookTitle(title)
                .orElseThrow(() -> new BookTitleNotFoundException("No such title book is available"));
        return bookDetails;
    }

    //FIND BY GENRE
    @Override
    public List<BookDetails> getByGenre(String genre) {

        List<BookDetails> bookDetails = bookRepository.findByBookGenre(genre)
                .orElseThrow(() -> new BookGenreNotFoundException("No such genre book is available"));
        return bookDetails;
    }
}



//    @Override
//    public  List<BookDetails> getByTitle(String title) throws BookTitleNotFoundException{
//        List<BookDetails> bookDetails = bookRepository.findByTitle(title);
////                .(() -> new BookTitleNotFoundException("No such title book is available"));
//        List<BookDetails> collect = bookDetails.stream().map(book -> {
//            book.getBookId();
//            book.getTitle();
//            book.getAuthor();
//            book.getGenre();
//            book.getPrice();
////            return book;
//        }).collect(Collectors.toList());
////        return collect;
//        return collect;
//    }
