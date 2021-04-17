package islam_faith_guide.demo.Repository;

import islam_faith_guide.demo.Entity.TestAnswers;
import islam_faith_guide.demo.Entity.TestQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepo  extends JpaRepository<TestAnswers,Long> {
   List<TestQuestions> findByFirstTestAns(Long id);
}
