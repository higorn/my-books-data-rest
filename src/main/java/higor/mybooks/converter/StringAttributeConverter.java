package higor.mybooks.converter;

import higor.mybooks.entity.StringEnumType;

import javax.persistence.AttributeConverter;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class StringAttributeConverter<T extends StringEnumType> implements AttributeConverter<T, String> {
  @Override
  public String convertToDatabaseColumn(T attribute) {
    return Optional.ofNullable(attribute).map(StringEnumType::getCode).orElse(null);
  }

  @Override
  public T convertToEntityAttribute(String code) {
    return Stream.of(getValues())
        .filter(c -> c.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public abstract T[] getValues();
}
