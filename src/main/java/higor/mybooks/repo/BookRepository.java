package higor.mybooks.repo;

import higor.mybooks.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
  @RestResource(rel = "find-by-filter", path = "find-by-filter")
  Page<Book> findByTitleContainingIgnoreCaseOrSubtitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublishingCompanyContainingIgnoreCase(
      String title, String subtitle, String author, String publishingCompany, Pageable pageable);
  @Query("SELECT b FROM Book b WHERE UPPER(b.title) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.subtitle) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.author) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.publishingCompany) LIKE CONCAT('%',UPPER(:term),'%')")
  @RestResource(rel = "find-by-term", path = "find-by-term")
  Page<Book> findByTerm(@Param("term") String term, Pageable pageable);
}
