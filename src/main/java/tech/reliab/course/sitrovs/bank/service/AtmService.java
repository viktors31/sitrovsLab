package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;

public interface AtmService {
  BankAtm create(BankAtm bankAtm);
  public BankAtm getBankAtmById(int id);
  public List<BankAtm> getAllBankAtms();
  boolean depositMoney(BankAtm bankAtm, double amount);
  boolean withdrawMoney(BankAtm bankAtm, double amount);
}
