package higor.mybooks.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserBookId implements Serializable {

  @Column(name = "account_id")
  private Integer userId;
  @Column(name = "book_id")
  private Integer bookId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass())
      return false;

    UserBookId that = (UserBookId) o;
    return Objects.equals(userId, that.userId) && (Objects.equals(bookId, that.bookId));
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, bookId);
  }
}
