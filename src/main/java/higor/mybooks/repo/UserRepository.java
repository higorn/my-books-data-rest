package higor.mybooks.repo;

import higor.mybooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  @RestResource(rel = "by-email", path = "by-email")
  Optional<User> findByEmail(String email);
}
