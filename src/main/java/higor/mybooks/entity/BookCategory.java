package higor.mybooks.entity;

import javax.persistence.*;

@Entity
public class BookCategory {
  @Id
  @SequenceGenerator(name = "BookCategory_SEQ", sequenceName = "book_category_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookCategory_SEQ")
  private Integer id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;
  private CategoryType category;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public CategoryType getCategory() {
    return category;
  }

  public void setCategory(CategoryType category) {
    this.category = category;
  }
}
