package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.CreditAccount;

public interface CreditAccountService {
  CreditAccount create(CreditAccount creditAccount);
  public CreditAccount getCreditAccountById(int id);
  public List<CreditAccount> getAllCreditAccounts();
  boolean makeMonthlyPayment(CreditAccount account);
}
