package bank;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The minimum deposit is 1.00");
    }
    double newBalance = getBalance() + amount;
    setBalance(newBalance);
    DataSource.updateAccountBalance(this);
  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 500) {
      throw new AmountException("The minimum deposit is 500");
    }

    if (getBalance() < 500) {
      throw new AmountException("Insufficient balance");
    }

    double newBalance = getBalance() - amount;
    setBalance(newBalance);
    DataSource.updateAccountBalance(this);
  }
}
