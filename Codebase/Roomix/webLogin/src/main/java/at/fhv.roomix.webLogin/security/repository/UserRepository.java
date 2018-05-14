package at.fhv.roomix.webLogin.security.repository;

import at.fhv.roomix.webLogin.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
