package islam_faith_guide.demo.Repository;

import islam_faith_guide.demo.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
@Repository
public interface UserRepo extends JpaRepository<UserData,Long> {
    Optional<UserData>findByUserName(String name);
    Optional<UserData>findByPassword(String password);
//    Optional<UserData>findById(Long id);
}
