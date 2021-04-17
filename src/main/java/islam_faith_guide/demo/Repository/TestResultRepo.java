package islam_faith_guide.demo.Repository;

import islam_faith_guide.demo.Entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepo extends JpaRepository<TestResult,Long> {
    List<TestResult> findByUserName(String name);
}
