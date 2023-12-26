package tech.reliab.course.sitrovs.bank.entity;

import com.google.gson.annotations.Expose;

public class PaymentAccount extends Account {
  @Expose(serialize = true)
  private double balance;

  private void initializeWithDefaults() {
    balance = 0;
  }

  public PaymentAccount() {
    super();
    initializeWithDefaults();
  }

  public PaymentAccount(double balance) {
    super();
    this.balance = balance;
  }

  public PaymentAccount(User user, Bank bank, double balance) {
    super(user, bank);
    this.balance = balance;
  }

  public PaymentAccount(PaymentAccount paymentAccount) {
    super(paymentAccount.getId(), paymentAccount.getUser(), paymentAccount.getBank());
    this.balance = paymentAccount.getBalance();
  }

  @Override
  public String toString() {
    return 
      "\nPayment Account: {\n" +
      "\tid: " + id + ",\n" +
      "\tuser: " + (user == null ? "null" : user.getName()) + ",\n" +
      "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
      "\tbalance: " + String.format("%.2f", balance) + ",\n" +
      "}\n";
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }
}