package tech.reliab.course.sitrovs.bank.service;

import tech.reliab.course.sitrovs.bank.entity.CreditAccount;

public interface CreditAccountService {
  CreditAccount create(CreditAccount creditAccount);
  boolean makeMonthlyPayment(CreditAccount account);
}
