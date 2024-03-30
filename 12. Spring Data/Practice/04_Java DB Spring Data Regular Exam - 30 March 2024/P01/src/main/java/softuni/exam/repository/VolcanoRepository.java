package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano, Long> {
    Optional<Volcano> findByName(String name);
    @Query("select v from Volcano v where v.isActive = true and v.elevation > 3000 and " +
            "v.lastEruption is not null order by v.elevation desc")
    List<Volcano> findVolcanoesForExport();
}
