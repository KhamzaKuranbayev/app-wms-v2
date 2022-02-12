package webbrain.incomeexpenseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbrain.incomeexpenseapp.dto.InputOutputDto;
import webbrain.incomeexpenseapp.entity.InputOutput;
import webbrain.incomeexpenseapp.enums.InputOutputType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InputOutputRepository extends JpaRepository<InputOutput, Long> {
    @Query("select io from InputOutput io where io.date >= ?1 and io.date <= ?2 and io.type = ?3")
    List<InputOutput> selectIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);

    @Query("select io from InputOutput io where io.date >= ?1 and io.date <= ?2 and io.type = ?3 order by io.date asc")
    List<InputOutput> selectProductsByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);
}
