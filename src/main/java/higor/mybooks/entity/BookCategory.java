package higor.mybooks.entity;

import javax.persistence.*;

@Entity
public class BookCategory {
//  @EmbeddedId
//  private BookCategoryId id;
  @Id
  @SequenceGenerator(name = "BookCategory_SEQ", sequenceName = "book_category_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookCategory_SEQ")
  private Integer id;
  @ManyToOne(fetch = FetchType.LAZY)
//  @MapsId("bookId")
  @JoinColumn(name = "book_id")
  private Book book;
  private CategoryType category;

//  public BookCategory() {
//  }
//
//  public BookCategory(Book book, CategoryType category) {
//    this.book = book;
//    this.category = category;
//    this.id = new BookCategoryId(book.getId(), category);
//  }

//  public BookCategoryId getId() {
//    return id;
//  }
//
//  public void setId(BookCategoryId id) {
//    this.id = id;
//  }

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

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//
//    if (o == null || getClass() != o.getClass())
//      return false;
//
//    BookCategory that = (BookCategory) o;
//    return Objects.equals(book, that.book) && (Objects.equals(category, that.category));
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(book, category);
//  }
}
