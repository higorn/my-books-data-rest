package higor.mybooks.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {
  @Id
  @SequenceGenerator(name = "Book_SEQ", sequenceName = "book_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_SEQ")
  private Integer        id;
  private String         title;
  private String         subtitle;
  private String         author;
  private Integer        edition;
  private String         publisher;
  private Integer        pages;
  private String         isbn;
  @Column(name = "publishing_dt")
  private LocalDate      publishingDate;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UserBook> users;
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BookCategory> categories;

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

  public Integer getEdition() {
    return edition;
  }

  public void setEdition(Integer edition) {
    this.edition = edition;
  }
  public Book edition(Integer edition) {
    this.edition = edition;
    return this;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Book publisher(String publishingCompany) {
    this.publisher = publishingCompany;
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

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Book isbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  public LocalDate getPublishingDate() {
    return publishingDate;
  }

  public void setPublishingDate(LocalDate publishingDate) {
    this.publishingDate = publishingDate;
  }

  public Book publishingDate(LocalDate publishingDate) {
    this.publishingDate = publishingDate;
    return this;
  }

  public List<UserBook> getUsers() {
    return users;
  }

  public void setUsers(List<UserBook> users) {
    this.users = users;
  }

  public Book users(List<UserBook> users) {
    this.users = users;
    return this;
  }

  public List<BookCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<BookCategory> categories) {
    this.categories = categories;
  }

  public Book categories(List<BookCategory> categories) {
    this.categories = categories;
    return this;
  }
}
