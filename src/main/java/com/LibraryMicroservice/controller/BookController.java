package com.LibraryMicroservice.controller;

import com.LibraryMicroservice.exception.BookGenreNotFoundException;
import com.LibraryMicroservice.exception.BookNotFoundException;
import com.LibraryMicroservice.exception.BookTitleNotFoundException;
import com.LibraryMicroservice.model.BookDetails;
import com.LibraryMicroservice.repository.BookRepository;
import com.LibraryMicroservice.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addBook")
    @ApiOperation(value = "Add Book to the list")
    public ResponseEntity<BookDetails> addCustomer(@Valid @RequestBody BookDetails bookDetails)
    {
        BookDetails bookDetails1 = bookService.addBook(bookDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDetails1);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get Book list")
    public ResponseEntity<List<BookDetails>> getAllCustomers() {

        List<BookDetails> bookDetails = bookService.findAll();
        if (bookDetails.isEmpty()) {

            return new ResponseEntity<List<BookDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<BookDetails>>(bookDetails, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    @ApiOperation(value = "Delete book detail")
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId") int bookId){
        bookService.deleteById(bookId);
        return new ResponseEntity<String>("BookId "+bookId+" deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateBook/{bookId}")
    @ApiOperation(value = "Update Book details")
    public ResponseEntity<List<BookDetails>> updateBookById(@Valid @PathVariable("bookId") int bookId, @RequestBody BookDetails updatedBookDetails){
        BookDetails bookDetails = bookService.updateBookById(bookId, updatedBookDetails);
        return new ResponseEntity(bookDetails,HttpStatus.OK);
    }



    @GetMapping("/getByTitle/{title}")
    @ApiOperation(value = "Get book details by title")
    public ResponseEntity<List<BookDetails>> bookByTitle(@PathVariable("title") String title){
       List<BookDetails> bookDetails = bookService.getByTitle(title);
        return new ResponseEntity(bookDetails, HttpStatus.OK);
    }

    @GetMapping("/getByGenre/{genre}")
    @ApiOperation(value = "Get book by genre")
    public ResponseEntity <List<BookDetails>> bookByGenre(@PathVariable("genre") String genre) {
        List<BookDetails> bookDetails = bookService.getByGenre(genre);
        return new ResponseEntity(bookDetails, HttpStatus.OK);
    }
}
