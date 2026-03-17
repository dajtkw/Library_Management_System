package com.example.library_management.controller;

import com.example.library_management.dto.BorrowDTO;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.service.BorrowService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@Valid @RequestBody BorrowRequest request) {
        BorrowRecord borrowRecord = borrowService.borrowBook(request.getUserId(), request.getBookId());
        BorrowDTO response = convertToDTO(borrowRecord);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{borrowRecordId}/return")
    public ResponseEntity<BorrowDTO> returnBook(@PathVariable Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowService.returnBook(borrowRecordId);
        BorrowDTO response = convertToDTO(borrowRecord);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = borrowService.getAllBorrowRecords();
        List<BorrowDTO> response = borrowRecords.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrowRecordById(@PathVariable Long id) {
        Optional<BorrowRecord> borrowRecord = borrowService.getBorrowRecordById(id);
        BorrowDTO response = borrowRecord.map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found with id: " + id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowDTO>> getBorrowRecordsByUser(@PathVariable Long userId) {
        List<BorrowRecord> borrowRecords = borrowService.getBorrowRecordsByUser(userId);
        List<BorrowDTO> response = borrowRecords.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowDTO>> getBorrowRecordsByBook(@PathVariable Long bookId) {
        List<BorrowRecord> borrowRecords = borrowService.getBorrowRecordsByBook(bookId);
        List<BorrowDTO> response = borrowRecords.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowDTO>> getOverdueBorrowRecords() {
        List<BorrowRecord> borrowRecords = borrowService.getOverdueBorrowRecords();
        List<BorrowDTO> response = borrowRecords.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<BorrowDTO>> getActiveBorrowRecordsByUser(@PathVariable Long userId) {
        List<BorrowRecord> borrowRecords = borrowService.getActiveBorrowRecordsByUser(userId);
        List<BorrowDTO> response = borrowRecords.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stats/active-count")
    public ResponseEntity<Long> getActiveBorrowCount() {
        long count = borrowService.countActiveBorrowRecords();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    private BorrowDTO convertToDTO(BorrowRecord borrowRecord) {
        BorrowDTO dto = new BorrowDTO();
        dto.setId(borrowRecord.getId());
        dto.setUserId(borrowRecord.getUser().getId());
        dto.setBookId(borrowRecord.getBook().getId());
        dto.setUsername(borrowRecord.getUser().getUsername());
        dto.setBookTitle(borrowRecord.getBook().getTitle());
        dto.setBorrowDate(borrowRecord.getBorrowDate());
        dto.setDueDate(borrowRecord.getDueDate());
        dto.setReturnDate(borrowRecord.getReturnDate());
        dto.setStatus(borrowRecord.getStatus());
        return dto;
    }

    // Inner class for borrow request with validation
    public static class BorrowRequest {
        @NotNull(message = "User ID is required")
        private Long userId;

        @NotNull(message = "Book ID is required")
        private Long bookId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }
    }
}
