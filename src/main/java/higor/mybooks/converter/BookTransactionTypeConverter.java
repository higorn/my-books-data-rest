package higor.mybooks.converter;

import higor.mybooks.entity.BookTransaction;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookTransactionTypeConverter extends StringAttributeConverter<BookTransaction.TransactionType> {
  @Override
  public BookTransaction.TransactionType[] getValues() {
    return BookTransaction.TransactionType.values();
  }
}
