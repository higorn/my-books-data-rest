package higor.mybooks.entity;

import javax.persistence.*;

@Entity
public class UserScore {

  public enum ScoreCategoryType {
    BOOK_OWNER, BOOK_TRADER
  }

  @Id
  @SequenceGenerator(name = "UserScore_SEQ", sequenceName = "user_score_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserScore_SEQ")
  private Integer           id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private User              dstUser;
  private ScoreCategoryType category;
  private int               points;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ScoreCategoryType getCategory() {
    return category;
  }

  public void setCategory(ScoreCategoryType category) {
    this.category = category;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public UserScore id(Integer id) {
    this.id = id;
    return this;
  }

  public UserScore category(ScoreCategoryType category) {
    this.category = category;
    return this;
  }

  public UserScore points(int points) {
    this.points = points;
    return this;
  }
}
