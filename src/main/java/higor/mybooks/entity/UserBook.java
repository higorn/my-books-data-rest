package higor.mybooks.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_book")
public class UserBook {

  @EmbeddedId
  private UserBookId id;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  private User    user;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("bookId")
  private Book    book;
  @Column(name = "is_read")
  private boolean read;

  public UserBookId getId() {
    return id;
  }

  public void setId(UserBookId id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  public UserBook id(UserBookId id) {
    this.id = id;
    return this;
  }

  public UserBook user(User user) {
    this.user = user;
    return this;
  }

  public UserBook book(Book book) {
    this.book = book;
    return this;
  }

  public UserBook read(boolean read) {
    this.read = read;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass())
      return false;

    UserBook that = (UserBook) o;
    return Objects.equals(user, that.user) && (Objects.equals(book, that.book));
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, book);
  }
}
