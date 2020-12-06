package higor.mybooks.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
  @Id
  @SequenceGenerator(name = "Book_SEQ", sequenceName = "book_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_SEQ")
  private Integer      id;
  private String       title;
  private String       subtitle;
  private String       author;
  private String       publishingCompany;
  private Integer      pages;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "account_book",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id")
  )
  private List<User>   users;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Book id(Integer id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Book title(String title) {
    this.title = title;
    return this;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public Book subtitle(String subtitle) {
    this.subtitle = subtitle;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Book author(String author) {
    this.author = author;
    return this;
  }

  public String getPublishingCompany() {
    return publishingCompany;
  }

  public void setPublishingCompany(String publishingCompany) {
    this.publishingCompany = publishingCompany;
  }

  public Book publishingCompaty(String publishingCompany) {
    this.publishingCompany = publishingCompany;
    return this;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Book pages(int pages) {
    this.pages = pages;
    return this;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Book users(List<User> users) {
    this.users = users;
    return this;
  }
}
