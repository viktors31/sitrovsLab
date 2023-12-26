package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;

public interface AtmService {
  BankAtm create(BankAtm bankAtm) throws UniquenessException;
  public BankAtm getBankAtmById(int id);
  public List<BankAtm> getAllBankAtms();
  boolean depositMoney(BankAtm bankAtm, double amount);
  boolean withdrawMoney(BankAtm bankAtm, double amount);
  public boolean isAtmSuitable(BankAtm bankAtm, double sum);
}
