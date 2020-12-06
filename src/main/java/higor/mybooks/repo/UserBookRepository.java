package higor.mybooks.repo;

import higor.mybooks.entity.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {
  Page<UserBook> findByUserId(Integer userId, Pageable pageable);
}
