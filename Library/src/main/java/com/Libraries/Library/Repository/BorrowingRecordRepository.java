package com.Libraries.Library.Repository;
import com.Libraries.Library.entity.Recordentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BorrowingRecordRepository extends JpaRepository<Recordentity, Long> {
}