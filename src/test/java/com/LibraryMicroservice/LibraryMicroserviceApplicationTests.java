package com.LibraryMicroservice;

import com.LibraryMicroservice.model.BookDetails;
import com.LibraryMicroservice.repository.BookRepository;
import com.LibraryMicroservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibraryMicroserviceApplicationTests {

	@Autowired
	BookService bookService;

	@MockBean
	BookRepository bookRepository;

	@Test
	public void testGetAllBook() {
		when(bookRepository.findAll()).thenReturn(Stream
				.of(new BookDetails(203,"Python","TECH","Krishank",23), new BookDetails(292,"Java","TECH","Krishank",232)).collect(Collectors.toList()));
		assertEquals(2, bookService.findAll().size());      //assertEquals(Object expected, Object actual)
	}

	@Test
	public void testsave() {
		BookDetails bookDetails=new BookDetails(203,"Python","TECH","Krishank",23);
		when(bookRepository.save(bookDetails)).thenReturn(bookDetails);
		assertEquals(bookDetails, bookService.addBook(bookDetails));
	}

	@Test
	public void testgetBookById() {
		int bookId=206;
		BookDetails bookDetails=new BookDetails(203,"Python","TECH","Krishank",23);
		System.out.println(bookId);
		when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(bookDetails));
		System.out.println(bookDetails.getBookId());
		assertEquals(bookId, bookDetails.getBookId());
	}

}
