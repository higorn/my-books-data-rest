package higor.mybooks.repo;

import higor.mybooks.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest()
class BookRepositoryTest {

  @Autowired
  private BookRepository repository;

  @Test
  void whenSaved_thenFindsByTitle() {
    Book savedBook = repository.save(
        new Book().id(1).title("Effective Java").subtitle("Programming Language Guide")
            .author("Joshua Bloch").publisher("Addison-Wesley").pages(252));
    assertNotNull(savedBook.getId());
  }
}