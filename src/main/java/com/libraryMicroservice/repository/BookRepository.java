package com.libraryMicroservice.repository;

import com.libraryMicroservice.model.BookDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<BookDetails, Integer> {
    List<BookDetails> findByBookTitle(String title);

    List<BookDetails> findByBookGenre(String genre);

    List<BookDetails> findByBookAuthor(String author);

}
