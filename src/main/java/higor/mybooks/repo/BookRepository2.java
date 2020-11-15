package higor.mybooks.repo;

import higor.mybooks.domain.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BookRepository2 extends PagingAndSortingRepository<Book, Integer> {
  @RestResource(rel = "books-filter", path = "books-filter")
  Page<Book> findByTitleContainingIgnoreCaseOrSubtitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublishingCompanyContainingIgnoreCase(
      String title, String subtitle, String author, String publishingCampany, Pageable pageable);
}
