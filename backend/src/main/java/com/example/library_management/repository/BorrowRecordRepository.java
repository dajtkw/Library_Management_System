package com.example.library_management.repository;

import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUser(User user);
    List<BorrowRecord> findByBookId(Long bookId);
    List<BorrowRecord> findByStatus(String status);
    List<BorrowRecord> findByUserAndStatus(User user, String status);
    List<BorrowRecord> findByBookIdAndStatus(Long bookId, String status);
    List<BorrowRecord> findByReturnDateIsNull();
    List<BorrowRecord> findByUserAndReturnDateIsNull(User user);
    List<BorrowRecord> findByDueDateBeforeAndStatus(LocalDateTime dueDate, String status);
    Optional<BorrowRecord> findByBookIdAndReturnDateIsNull(Long bookId);
    List<BorrowRecord> findByUserOrderByBorrowDateDesc(User user);
    long countByReturnDateIsNull();
}
