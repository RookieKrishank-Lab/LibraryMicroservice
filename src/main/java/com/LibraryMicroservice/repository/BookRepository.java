package com.LibraryMicroservice.repository;

import com.LibraryMicroservice.model.BookDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<BookDetails, Integer> {

//    @Query("{'Title':?0}")
    Optional<List<BookDetails>> findByBookTitle(String title);

//    @Query("{'Genre':?0}")
    Optional<List<BookDetails>> findByBookGenre(String genre);

    @Query("{'Author':?0}")
    Optional<BookDetails> findByBookAuthor(String Author);

}
