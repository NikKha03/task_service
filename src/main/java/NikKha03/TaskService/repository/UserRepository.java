package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
