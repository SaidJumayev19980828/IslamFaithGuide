package islam_faith_guide.demo.Repository;

import islam_faith_guide.demo.Entity.TestQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowTestsRepo extends JpaRepository<TestQuestions,Long> {
   Optional<TestQuestions>findById(Long id);
}
