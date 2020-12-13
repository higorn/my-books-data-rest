package higor.mybooks.converter;

import higor.mybooks.entity.UserBook;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class BookConditionConverter implements AttributeConverter<UserBook.ConditionType, String> {
  @Override
  public String convertToDatabaseColumn(UserBook.ConditionType condition) {
    return Optional.ofNullable(condition).map(UserBook.ConditionType::getCode).orElse(null);
  }

  @Override
  public UserBook.ConditionType convertToEntityAttribute(String code) {
    return Stream.of(UserBook.ConditionType.values())
        .filter(c -> c.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
