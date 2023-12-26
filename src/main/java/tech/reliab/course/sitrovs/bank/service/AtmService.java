package tech.reliab.course.sitrovs.bank.service;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;

public interface AtmService {
  BankAtm create(BankAtm bankAtm);
  boolean depositMoney(BankAtm bankAtm, double amount);
  boolean withdrawMoney(BankAtm bankAtm, double amount);
}
