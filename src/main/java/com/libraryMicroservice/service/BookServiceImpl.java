package com.libraryMicroservice.service;

import com.libraryMicroservice.exception.BookNotFoundException;
import com.libraryMicroservice.exception.ResourceAlreadyExistException;
import com.libraryMicroservice.model.BookDetails;
import com.libraryMicroservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        else
            throw new ResourceAlreadyExistException("Book already present");
    }

    //GET ALL
    @Override
    public List<BookDetails> findAllBook() {
        return bookRepository.findAll();
    }

    //UPDATE
    @Override
    public BookDetails updateBookById(int bookId, BookDetails bookDetails) {
        BookDetails bookDetails1 = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("book not found"));
        bookDetails1.setPrice(bookDetails.getPrice());
        bookDetails1.setBookAuthor(bookDetails.getBookAuthor());
        bookDetails1.setBookTitle(bookDetails.getBookTitle());
        bookDetails1.setBookGenre(bookDetails.getBookGenre());
        BookDetails details = bookRepository.save(bookDetails1);
        return details;
    }

    //DELETE
    @Override
    public void deleteById(int bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.deleteById(bookId);
    }

    //FIND BY TITLE
    @Override
    public  List<BookDetails> getByTitle(String title){
        List<BookDetails> bookDetails = bookRepository.findByBookTitle(title);
        return bookDetails;
    }

    //FIND BY GENRE
    @Override
    public List<BookDetails> getByGenre(String genre) {
        List<BookDetails> bookDetails = bookRepository.findByBookGenre(genre);
        return bookDetails;

    }

    //FIND BY AUTHOR
    @Override
    public List<BookDetails> getByAuthor(String author) {
        List<BookDetails> bookDetails = bookRepository.findByBookAuthor(author);
        return bookDetails;
    }
}







//        if (bookDetails.isEmpty())
//            return new BookGenreNotFoundException("No such genre book is available");
//                .orElseThrow(() -> new BookGenreNotFoundException("No such genre book is available"));
//        else



//.orElseThrow(() -> new
//        bookRepository.findAll().forEach(list::add);

//        List<BookDetails> list=bookRepository.findByBookGenre(genre)
//                .orElseThrow(() -> new BookGenreNotFoundException("No such genre book is available"));
//        // bookRepository.findAll().forEach(list::add);
////        if(list.isEmpty()){
////            System.out.println("No book such genre is present");
////        }
//        return list;

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


//  if(bookDetails.isEmpty())
//          throw new BookNotFoundException("No such title found");
//          else
//          .orElseThrow(() -> new BookTitleNotFoundException("No such title book is available"));
//          return bookDetails.get();
//          List<BookDetails> collectBook =bookDetails.stream().map(bookDetails1-> {
//        bookDetails.getBookId();
//        bookDetails.getBookTitle();
//        bookDetails.getAuthor();
//        bookDetails.getBookGenre();
//        bookDetails.getPrice();
//        return bookDetails1;
//        }).collect(Collectors.toList());
//        return collectBook;