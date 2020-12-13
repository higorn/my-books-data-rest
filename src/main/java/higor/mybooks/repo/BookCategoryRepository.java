package higor.mybooks.repo;

import higor.mybooks.entity.BookCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookCategoryRepository extends PagingAndSortingRepository<BookCategory, Integer> {
}
