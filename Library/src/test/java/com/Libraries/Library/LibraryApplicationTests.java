package com.Libraries.Library;
import com.Libraries.Library.Model.partonmodel;
import com.Libraries.Library.Model.recordmodel;
import com.Libraries.Library.Repository.BorrowingRecordRepository;
import com.Libraries.Library.Repository.bookres;
import com.Libraries.Library.Repository.partonres;
import com.Libraries.Library.controller.parton;
import com.Libraries.Library.entity.Books;
import com.Libraries.Library.controller.book;
import com.Libraries.Library.Model.bookmodel;
import com.Libraries.Library.entity.Patron;
import com.Libraries.Library.entity.Recordentity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import java.util.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = {book.class, parton.class, Record.class})
@AutoConfigureMockMvc
@WithMockUser // Simulate a user with default roles

public class LibraryApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private bookmodel bookService;
	@InjectMocks
	private partonmodel partonmode;
	@InjectMocks
	private recordmodel recordmode;
	@Mock
	private partonres patronRepository;
	@Mock
	private bookres bookRepository;
	@Mock
	private BorrowingRecordRepository borrowingRecordRepository;
	@MockBean
	private parton patronController;



	@Test
	void testAddNewBook() throws Exception {
		Books newBook = new Books(1L, "New Book", "New Author", 2024, "0987654321");
		when(bookService.add_book(any(Books.class))).thenReturn(newBook);

		mockMvc.perform(post("/api/books/add")
						.contentType("application/json")
						.content("{\"title\":\"New Book\",\"author\":\"New Author\"}")
						.with(csrf()))  // Add CSRF token for testing
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("New Book"));
	}
	@Test
	void testReturnAllBooks() throws Exception {
		mockMvc.perform(get("/api/books"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}
	@Test
	void testReturnBookById() throws Exception {
		Books book = new Books(1L, "Test Book", "Test Author", 2024, "1234567890");
		when(bookService.find_book(1L)).thenReturn(book);

		mockMvc.perform(get("/api/books/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.title").value("Test Book"));
	}
	@Test
	void testUpdateBook() throws Exception {
		Long bookId = 1L;
		Books bookToUpdate = new Books();
		bookToUpdate.setID(bookId);
		bookToUpdate.setTitle("Updated Title");

		// Mock the bookService updateBook method
		when(bookService.updateBook(eq(bookId), any(Books.class))).thenReturn(bookToUpdate);

		// Update the book with a PUT request
		mockMvc.perform(put("/api/books/update/{id}", bookId)
						.with(user("user").roles("USER"))
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.contentType("application/json")
						.content("{\"id\":1, \"title\":\"Updated Title\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Title"));
	}
	@Test
	void testDeleteBook() throws Exception {
		doNothing().when(bookService).delete_book(1L);

		mockMvc.perform(delete("/api/books/delete/1")
						.with(csrf())) // Adding CSRF token if CSRF protection is enabled
				.andExpect(status().isNoContent());
	}
	@Test
	public void getAllPatrons() {
		// Given
		List<Patron> patrons = new ArrayList<>();
		patrons.add(new Patron(1L, "John Doe", "1234567890"));
		patrons.add(new Patron(2L, "Jane Smith", "9876543210"));

		when(patronRepository.findAll()).thenReturn(patrons);

		// When
		List<Patron> result = partonmode.findAllPatrons();

		// Then
		assertEquals(2, result.size());
		assertEquals("John Doe", result.get(0).getName());
	}
	@Test
	public void getPatronById_found() {
		// Given
		Patron patron = new Patron(1L, "John Doe", "1234567890");
		when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

		// When
		Patron result = partonmode.find_parton(1L);

		// Then
		assertEquals(patron, result);
	}
	@Test
	public void createPatron() {
		// Given
		Patron patron = new Patron(null, "John Doe", "1234567890");
		Patron savedPatron = new Patron(1L, "John Doe", "1234567890");
		when(patronRepository.save(patron)).thenReturn(savedPatron);

		// When
		Patron result = partonmode.add(patron);

		// Then
		assertEquals(savedPatron, result);
	}
	@Test
	public void updatePatron() {
		// Given
		Patron existingPatron = new Patron(1L, "John Doe", "1234567890");
		Patron updatedPatron = new Patron(1L, "Jane Smith", "9876543210");
		when(patronRepository.findById(1L)).thenReturn(Optional.of(existingPatron));
		when(patronRepository.save(any(Patron.class))).thenReturn(updatedPatron);

		// When
		Patron result = partonmode.updatePatron(1L, updatedPatron);

		// Then
		assertEquals(updatedPatron, result);
	}
	@Test
	public void deletePatron() {
		// Given
		Long id = 1L;
		when(patronRepository.existsById(id)).thenReturn(true);

		// When
		partonmode.deletePatron(id);

		// Then
		verify(patronRepository, times(1)).deleteById(id);
	}
	@Test
	public void borrowBook_success() {
		// Given
		Long bookId = 1L;
		Long patronId = 2L;
		Books book = new Books(bookId, "Book 1", "author", 2012, "452");
		Patron patron = new Patron(patronId, "John Doe", "1234567890");

		// Mock the behavior of repositories
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
		when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

		// Create the expected borrowing record
		Recordentity expectedRecord = new Recordentity();
		expectedRecord.setBook(book);
		expectedRecord.setPatron(patron);
		expectedRecord.setBorrowDate(new Date());

		when(borrowingRecordRepository.save(any(Recordentity.class))).thenReturn(expectedRecord);

		// When
		Recordentity result =recordmode.borrowBook(bookId, patronId);

		// Then
		assertNotNull(result);
		assertEquals(book, result.getBook());
		assertEquals(patron, result.getPatron());
		assertNotNull(result.getBorrowDate());
	}
	@Test
	public void returnBook_success() throws Exception {
		// Given
		Long recordId = 1L;
		Recordentity existingRecord = new Recordentity();
		existingRecord.setId(recordId);
		existingRecord.setBook(new Books(1L, "Book Title", "Author", 2022, "1234"));
		existingRecord.setPatron(new Patron(2L, "Patron Name", "9876543210"));
		existingRecord.setBorrowDate(new Date());

		// Mock the behavior of the repository
		when(borrowingRecordRepository.getById(recordId)).thenReturn(existingRecord);

		// Define the expected behavior after updating
		Recordentity updatedRecord = new Recordentity();
		updatedRecord.setId(recordId);
		updatedRecord.setBook(existingRecord.getBook());
		updatedRecord.setPatron(existingRecord.getPatron());
		updatedRecord.setBorrowDate(existingRecord.getBorrowDate());
		updatedRecord.setReturnDate(new Date());

		when(borrowingRecordRepository.save(any(Recordentity.class))).thenReturn(updatedRecord);

		// When
		Recordentity result = recordmode.returnBook(recordId);

		// Then
		assertNotNull(result);
		assertEquals(recordId, result.getId());
		assertNotNull(result.getReturnDate());
		assertEquals(existingRecord.getBook(), result.getBook());
		assertEquals(existingRecord.getPatron(), result.getPatron());
		assertEquals(existingRecord.getBorrowDate(), result.getBorrowDate());
	}
}



