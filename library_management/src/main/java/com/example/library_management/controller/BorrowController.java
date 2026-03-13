package com.example.library_management.controller;

import com.example.library_management.dto.BorrowDTO;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.service.BorrowService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
