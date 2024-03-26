package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entity.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    @Query("select br " +
            "from BorrowingRecord br join br.book b " +
            "where b.genre = 'SCIENCE_FICTION' " +
            "order by br.borrowDate desc")
    List<BorrowingRecord> getScienceFictionRecordsOrdered();
}
