package org.zloyleva;

import java.time.LocalDateTime;

public class Transaction {
  private final TransactionType type;
  private final double value;
  private final double total;
  private final LocalDateTime timestamp;

  public Transaction(TransactionType type, double value, double total) {
    this.type = type;
    this.value = value;
    this.total = total;
    timestamp = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return timestamp + ":\t" + type + "\t|\t" + value + "\t|\t" + total;
  }
}
