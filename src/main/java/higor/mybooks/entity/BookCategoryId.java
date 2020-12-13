package higor.mybooks.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookCategoryId implements Serializable {
  @Column(name = "book_id")
  private Integer      bookId;
  @Column(name = "category")
  private CategoryType categoryId;

  public BookCategoryId() {
  }

  public BookCategoryId(Integer bookId, CategoryType categoryId) {
    this.bookId = bookId;
    this.categoryId = categoryId;
  }

//  public CategoryType getCategoryId() {
//    return categoryId;
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BookCategoryId that = (BookCategoryId) o;
    return Objects.equals(bookId, that.bookId) && Objects.equals(categoryId, that.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookId, categoryId);
  }
}
