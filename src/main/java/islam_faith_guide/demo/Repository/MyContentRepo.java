package islam_faith_guide.demo.Repository;

import islam_faith_guide.demo.Entity.MyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyContentRepo extends JpaRepository<MyContent,Long> {
}
