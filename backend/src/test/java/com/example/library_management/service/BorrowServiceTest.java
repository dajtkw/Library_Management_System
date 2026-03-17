package com.example.library_management.service;

import com.example.library_management.entity.Book;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.BorrowRecordRepository;
import com.example.library_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BorrowService borrowService;

    private User user;
    private Book book;
    private BorrowRecord borrowRecord;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setQuantity(5);

        borrowRecord = new BorrowRecord();
        borrowRecord.setId(1L);
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setDueDate(LocalDateTime.now().plusDays(14));
        borrowRecord.setStatus("BORROWED");
        borrowRecord.setReturnDate(null);
    }

    @Test
    void borrowBook_WithValidData_ShouldCreateBorrowRecord() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowRecordRepository.findByUserAndReturnDateIsNull(user)).thenReturn(Collections.emptyList());
        when(borrowRecordRepository.save(any(BorrowRecord.class))).thenReturn(borrowRecord);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BorrowRecord result = borrowService.borrowBook(1L, 1L);

        assertNotNull(result);
        assertEquals("BORROWED", result.getStatus());
        assertNull(result.getReturnDate());
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, times(1)).save(any(BorrowRecord.class));
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void borrowBook_WithInvalidUserId_ShouldThrowResourceNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            borrowService.borrowBook(99L, 1L);
        });
        verify(userRepository, times(1)).findById(99L);
        verify(bookRepository, never()).findById(any());
    }

    @Test
    void borrowBook_WithInvalidBookId_ShouldThrowResourceNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            borrowService.borrowBook(1L, 99L);
        });
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(99L);
    }

    @Test
    void borrowBook_WithOutOfStockBook_ShouldThrowIllegalArgumentException() {
        book.setQuantity(0);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(IllegalArgumentException.class, () -> {
            borrowService.borrowBook(1L, 1L);
        });
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, never()).save(any(BorrowRecord.class));
    }

    @Test
    void borrowBook_WithAlreadyBorrowedBook_ShouldThrowIllegalArgumentException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowRecordRepository.findByUserAndReturnDateIsNull(user)).thenReturn(Arrays.asList(borrowRecord));

        assertThrows(IllegalArgumentException.class, () -> {
            borrowService.borrowBook(1L, 1L);
        });
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, never()).save(any(BorrowRecord.class));
    }

    @Test
    void returnBook_WithValidData_ShouldReturnBook() {
        when(borrowRecordRepository.findById(1L)).thenReturn(Optional.of(borrowRecord));
        when(borrowRecordRepository.save(any(BorrowRecord.class))).thenReturn(borrowRecord);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BorrowRecord result = borrowService.returnBook(1L);

        assertNotNull(result);
        assertEquals("RETURNED", result.getStatus());
        assertNotNull(result.getReturnDate());
        verify(borrowRecordRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, times(1)).save(any(BorrowRecord.class));
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void returnBook_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(borrowRecordRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            borrowService.returnBook(99L);
        });
        verify(borrowRecordRepository, times(1)).findById(99L);
        verify(borrowRecordRepository, never()).save(any(BorrowRecord.class));
    }

    @Test
    void returnBook_WithAlreadyReturnedBook_ShouldThrowIllegalArgumentException() {
        borrowRecord.setReturnDate(LocalDateTime.now());
        when(borrowRecordRepository.findById(1L)).thenReturn(Optional.of(borrowRecord));

        assertThrows(IllegalArgumentException.class, () -> {
            borrowService.returnBook(1L);
        });
        verify(borrowRecordRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, never()).save(any(BorrowRecord.class));
    }

    @Test
    void getAllBorrowRecords_ShouldReturnAllRecords() {
        when(borrowRecordRepository.findAll()).thenReturn(Arrays.asList(borrowRecord));

        List<BorrowRecord> result = borrowService.getAllBorrowRecords();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(borrowRecordRepository, times(1)).findAll();
    }

    @Test
    void getBorrowRecordById_WithValidId_ShouldReturnRecord() {
        when(borrowRecordRepository.findById(1L)).thenReturn(Optional.of(borrowRecord));

        Optional<BorrowRecord> result = borrowService.getBorrowRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(borrowRecordRepository, times(1)).findById(1L);
    }

    @Test
    void getBorrowRecordById_WithInvalidId_ShouldReturnEmpty() {
        when(borrowRecordRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<BorrowRecord> result = borrowService.getBorrowRecordById(99L);

        assertFalse(result.isPresent());
        verify(borrowRecordRepository, times(1)).findById(99L);
    }

    @Test
    void getBorrowRecordsByUser_WithValidUserId_ShouldReturnRecords() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(borrowRecordRepository.findByUserOrderByBorrowDateDesc(user)).thenReturn(Arrays.asList(borrowRecord));

        List<BorrowRecord> result = borrowService.getBorrowRecordsByUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, times(1)).findByUserOrderByBorrowDateDesc(user);
    }

    @Test
    void getBorrowRecordsByUser_WithInvalidUserId_ShouldThrowResourceNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            borrowService.getBorrowRecordsByUser(99L);
        });
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    void getBorrowRecordsByBook_ShouldReturnRecords() {
        when(borrowRecordRepository.findByBookId(1L)).thenReturn(Arrays.asList(borrowRecord));

        List<BorrowRecord> result = borrowService.getBorrowRecordsByBook(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(borrowRecordRepository, times(1)).findByBookId(1L);
    }

    @Test
    void getOverdueBorrowRecords_ShouldReturnOverdueRecords() {
        when(borrowRecordRepository.findByDueDateBeforeAndStatus(any(LocalDateTime.class), eq("BORROWED")))
                .thenReturn(Arrays.asList(borrowRecord));

        List<BorrowRecord> result = borrowService.getOverdueBorrowRecords();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(borrowRecordRepository, times(1)).findByDueDateBeforeAndStatus(any(LocalDateTime.class), eq("BORROWED"));
    }

    @Test
    void getActiveBorrowRecordsByUser_WithValidUserId_ShouldReturnActiveRecords() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(borrowRecordRepository.findByUserAndReturnDateIsNull(user)).thenReturn(Arrays.asList(borrowRecord));

        List<BorrowRecord> result = borrowService.getActiveBorrowRecordsByUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findById(1L);
        verify(borrowRecordRepository, times(1)).findByUserAndReturnDateIsNull(user);
    }

    @Test
    void getActiveBorrowRecordsByUser_WithInvalidUserId_ShouldThrowResourceNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            borrowService.getActiveBorrowRecordsByUser(99L);
        });
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    void countActiveBorrowRecords_ShouldReturnCount() {
        when(borrowRecordRepository.countByReturnDateIsNull()).thenReturn(5L);

        long result = borrowService.countActiveBorrowRecords();

        assertEquals(5L, result);
        verify(borrowRecordRepository, times(1)).countByReturnDateIsNull();
    }
}
