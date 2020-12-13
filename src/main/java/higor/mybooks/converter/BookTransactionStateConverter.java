package higor.mybooks.converter;

import higor.mybooks.entity.BookTransaction;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookTransactionStateConverter extends StringAttributeConverter<BookTransaction.TransactionState> {
  @Override
  public BookTransaction.TransactionState[] getValues() {
    return BookTransaction.TransactionState.values();
  }
}
