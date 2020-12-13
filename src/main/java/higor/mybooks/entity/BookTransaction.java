package higor.mybooks.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BookTransaction {

  public enum TransactionType {
    SWAP("SW"), BUY_AND_SELL("BS");

    private final String code;

    TransactionType(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  public enum TransactionState {
    OPEN("O"), CLOSED("C");

    private final String code;

    TransactionState(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  @Id
  private UUID             id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "src_account_id")
  private User             srcUser;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dst_account_id")
  private User             dstUser;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_book_id")
  private UserBook         userBook;
  private int              itemAmount;
  private TransactionType  type;
  private TransactionState state;
  private LocalDateTime    startDt;
  private LocalDateTime    endDt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getSrcUser() {
    return srcUser;
  }

  public void setSrcUser(User srcUser) {
    this.srcUser = srcUser;
  }

  public User getDstUser() {
    return dstUser;
  }

  public void setDstUser(User dstUser) {
    this.dstUser = dstUser;
  }

  public UserBook getUserBook() {
    return userBook;
  }

  public void setUserBook(UserBook userBook) {
    this.userBook = userBook;
  }

  public int getItemAmount() {
    return itemAmount;
  }

  public void setItemAmount(int itemAmount) {
    this.itemAmount = itemAmount;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public TransactionState getState() {
    return state;
  }

  public void setState(TransactionState state) {
    this.state = state;
  }

  public LocalDateTime getStartDt() {
    return startDt;
  }

  public void setStartDt(LocalDateTime startDt) {
    this.startDt = startDt;
  }

  public LocalDateTime getEndDt() {
    return endDt;
  }

  public void setEndDt(LocalDateTime endDt) {
    this.endDt = endDt;
  }

  public BookTransaction id(UUID id) {
    this.id = id;
    return this;
  }

  public BookTransaction srcUser(User srcUser) {
    this.srcUser = srcUser;
    return this;
  }

  public BookTransaction dstUser(User dstUser) {
    this.dstUser = dstUser;
    return this;
  }

  public BookTransaction userBook(UserBook userBook) {
    this.userBook = userBook;
    return this;
  }

  public BookTransaction itemAmount(int itemAmount) {
    this.itemAmount = itemAmount;
    return this;
  }

  public BookTransaction type(TransactionType type) {
    this.type = type;
    return this;
  }

  public BookTransaction state(TransactionState state) {
    this.state = state;
    return this;
  }

  public BookTransaction startDt(LocalDateTime startDt) {
    this.startDt = startDt;
    return this;
  }

  public BookTransaction endDt(LocalDateTime endDt) {
    this.endDt = endDt;
    return this;
  }
}
