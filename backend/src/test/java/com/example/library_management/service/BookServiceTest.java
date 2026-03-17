package com.example.library_management.service;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book1;
    private Book book2;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test Book 1");
        book1.setAuthor("Author 1");
        book1.setIsbn("1234567890");
        book1.setQuantity(5);

        book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Test Book 2");
        book2.setAuthor("Author 2");
        book2.setIsbn("0987654321");
        book2.setQuantity(3);

        bookDTO = new BookDTO();
        bookDTO.setTitle("New Book");
        bookDTO.setAuthor("New Author");
        bookDTO.setIsbn("1111111111");
        bookDTO.setQuantity(10);
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<BookDTO> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Book 1", result.get(0).getTitle());
        assertEquals("Test Book 2", result.get(1).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_WithValidId_ShouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        BookDTO result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Book 1", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(99L);
        });
        verify(bookRepository, times(1)).findById(99L);
    }

    @Test
    void createBook_WithValidData_ShouldCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        BookDTO result = bookService.createBook(bookDTO);

        assertNotNull(result);
        assertEquals("Test Book 1", result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void createBook_WithNegativeQuantity_ShouldThrowIllegalArgumentException() {
        bookDTO.setQuantity(-1);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.createBook(bookDTO);
        });
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void createBook_WithNullQuantity_ShouldThrowIllegalArgumentException() {
        bookDTO.setQuantity(null);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.createBook(bookDTO);
        });
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void updateBookQuantity_WithValidData_ShouldUpdateQuantity() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        BookDTO result = bookService.updateBookQuantity(1L, 10);

        assertNotNull(result);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void updateBookQuantity_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBookQuantity(99L, 10);
        });
        verify(bookRepository, times(1)).findById(99L);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void updateBookQuantity_WithNegativeQuantity_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.updateBookQuantity(1L, -1);
        });
        verify(bookRepository, never()).findById(any());
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void deleteBook_WithValidId_ShouldDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        assertDoesNotThrow(() -> {
            bookService.deleteBook(1L);
        });
        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteBook_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(bookRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(99L);
        });
        verify(bookRepository, times(1)).existsById(99L);
        verify(bookRepository, never()).deleteById(any());
    }
}
