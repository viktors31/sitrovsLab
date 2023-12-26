package tech.reliab.course.nikolotovai.bank.service;

import java.util.List;

import tech.reliab.course.nikolotovai.bank.entity.PaymentAccount;

public interface PaymentAccountService {
  PaymentAccount create(PaymentAccount paymentAccount);
  public void printPaymentData(int id);
  public PaymentAccount getPaymentAccountById(int id);
  public List<PaymentAccount> getAllPaymentAccounts();
  boolean depositMoney(PaymentAccount account, double amount);
  boolean withdrawMoney(PaymentAccount account, double amount);
}
