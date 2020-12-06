package higor.mybooks.it.listbooks;

import higor.mybooks.entity.Book;
import higor.mybooks.repo.BookRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ListBooksSteps {

  @Autowired
  private BookRepository bookRepository;
  private Page<Book>     booksPage;

  @Before
  public void setUp() {
    bookRepository.deleteAll();
  }

  @After
  public void tearDown() {
  }

  @Given("that I have books in the database")
  public void that_I_have_books_in_the_database(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.forEach(row -> {
      Book book = new Book();
      book.setTitle(row.get("title"));
      book.setSubtitle(row.get("subtitle"));
      book.setAuthor(row.get("author"));
      book.setPublishingCompany(row.get("publishingCompany"));
      bookRepository.save(book);
    });
  }

  @When("I asks for the book list with filter {string}")
  public void i_asks_for_the_book_list_with_filter(String filter) {
    filter = "null".equals(filter) ? "" : filter;
    booksPage = bookRepository.findByTerm(filter, PageRequest.of(0, 10, Sort.Direction.ASC, "title"));
  }

  @Then("I receive a list of books of size {int}")
  public void i_receive_a_list_of_books_of_size(Integer size) {
    assertEquals(size, booksPage.getContent().size());
  }
}
