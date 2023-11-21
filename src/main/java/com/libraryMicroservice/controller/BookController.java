package com.libraryMicroservice.controller;

import com.libraryMicroservice.model.BookDetails;
import com.libraryMicroservice.repository.BookRepository;
import com.libraryMicroservice.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addBook")
    @ApiOperation(value = "Add Book to the list")
    public ResponseEntity<BookDetails> addCustomer(@Valid @RequestBody BookDetails bookDetails) {
        BookDetails bookDetails1 = bookService.addBook(bookDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDetails1);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get Book list")
    public ResponseEntity<List<BookDetails>> getAllBook() {

        List<BookDetails> bookDetails = bookService.findAllBook();
        if (bookDetails.isEmpty()) {

            return new ResponseEntity("No book in the list",HttpStatus.OK);
        }
        return new ResponseEntity(bookDetails, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    @ApiOperation(value = "Delete book detail")
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId") int bookId) {
        bookService.deleteById(bookId);
        return new ResponseEntity<String>("BookId " + bookId + " deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateBook/{bookId}")
    @ApiOperation(value = "Update Book details")
    public ResponseEntity<List<BookDetails>> updateBookById(@Valid @PathVariable("bookId") int bookId, @RequestBody BookDetails updatedBookDetails) {
        BookDetails bookDetails = bookService.updateBookById(bookId, updatedBookDetails);
        return new ResponseEntity(bookDetails, HttpStatus.OK);
    }

    @RequestMapping("/findByTitle/{title}")
    @ApiOperation(value = "Get book details by title")
    public ResponseEntity<List<BookDetails>> bookByTitle(@PathVariable("title") String title) {
        List<BookDetails> bookDetails = bookService.getByTitle(title);
        if (bookDetails != null && bookDetails.size() > 0)
            return new ResponseEntity(bookDetails, HttpStatus.OK);
        else
            return new ResponseEntity("No record found", HttpStatus.OK);
    }

    @GetMapping("/findByGenre/{genre}")
    @ApiOperation(value = "Get book by genre")
    public ResponseEntity<List<BookDetails>> bookByGenre(@PathVariable("genre") String genre) {
        List<BookDetails> bookDetails = bookService.getByGenre(genre);
        if (bookDetails != null && bookDetails.size() > 0)
            return new ResponseEntity(bookDetails, HttpStatus.OK);
        else
            return new ResponseEntity("No record found", HttpStatus.OK);
    }

    @GetMapping("/findByAuthor/{author}")
    @ApiOperation(value = "Get book by author")
    public ResponseEntity<List<BookDetails>> bookByAuthor(@PathVariable("author") String author) {
        List<BookDetails> bookDetails = bookService.getByAuthor(author);
        if (bookDetails != null && bookDetails.size() > 0)
            return new ResponseEntity(bookDetails, HttpStatus.OK);
        else
            return new ResponseEntity("No record found", HttpStatus.OK);
    }
}