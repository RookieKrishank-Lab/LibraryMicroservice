package com.LibraryMicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "LibraryDetails")
public class BookDetails {

    @Id
    private int bookId;
    @NotBlank(message = "Title cant be empty")
    @Size(min=3, message = "String length is very small")
    private String bookTitle;
    @NotBlank(message = "Genre cant be empty")
//    @Size(min=3, message = "String length is very small")
    private String bookGenre;
    @NotBlank(message = "Author cant be empty")
    @Size(min=3, message = "String length is very small")
    private String author;
//    @Size(min=2, message = "Price cant be empty")
//    @NotNull(message= "positive number value is required")
    private int price;
}
