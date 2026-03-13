package com.example.library_management.service;

import com.example.library_management.entity.Book;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.BorrowRecordRepository;
import com.example.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BorrowRecord borrowBook(Long userId, Long bookId) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Check if book exists
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Check if book quantity is available
        if (book.getQuantity() <= 0) {
            throw new IllegalArgumentException("Book is out of stock");
        }

        // Check if user has already borrowed this book and not returned
        boolean hasActiveBorrow = borrowRecordRepository.findByUserAndReturnDateIsNull(user).stream()
                .anyMatch(record -> record.getBook().getId().equals(bookId));
        if (hasActiveBorrow) {
            throw new IllegalArgumentException("User has already borrowed this book");
        }

        // Create borrow record
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setDueDate(LocalDateTime.now().plusDays(14)); // 14 days borrowing period
        borrowRecord.setStatus("BORROWED");
        borrowRecord.setReturnDate(null);

        // Save borrow record
        borrowRecord = borrowRecordRepository.save(borrowRecord);

        // Decrease book quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        return borrowRecord;
    }
}
