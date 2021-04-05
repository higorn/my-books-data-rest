package higor.mybooks.repo;

import higor.mybooks.entity.Book;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@Api(tags = "Books")
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
  // It is here just for reference, should not be used
  @Deprecated
  @RestResource(rel = "find-by-filter", path = "find-by-filter")
  Page<Book> findByTitleContainingIgnoreCaseOrSubtitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(
      String title, String subtitle, String author, String publisher, Pageable pageable);
  @Query("SELECT b FROM Book b WHERE UPPER(b.title) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.subtitle) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.author) LIKE CONCAT('%',UPPER(:term),'%')"
      + " OR UPPER(b.publisher) LIKE CONCAT('%',UPPER(:term),'%')")
  @RestResource(rel = "by-term", path = "by-term")
  Page<Book> findByTerm(@Param("term") String term, Pageable pageable);
}
