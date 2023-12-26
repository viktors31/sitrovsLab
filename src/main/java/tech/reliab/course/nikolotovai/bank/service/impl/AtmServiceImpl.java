package tech.reliab.course.sitrovs.bank.service.impl;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;
import tech.reliab.course.sitrovs.bank.service.AtmService;

public class AtmServiceImpl implements AtmService {
  public BankAtm create(BankAtm bankAtm) {
    if (bankAtm == null) {
      return null;
    }

    if (bankAtm.getId() < 0) {
      System.out.println("Error: id must be non-negative.");
      return null;
    }

    if (bankAtm.getTotalMoney() < 0) {
      System.out.println("Error: totalMoney must be non-negative.");
      return null;
    }

    if (bankAtm.getMaintenanceCost() < 0) {
      System.out.println("Error: maintenanceCost must be non-negative.");
      return null;
    }

    if (bankAtm.getBankOffice() == null) {
      System.out.println("Error: bankOffice cant be null");
      return null;
    }

    return new BankAtm(bankAtm);
  }

  public boolean depositMoney(BankAtm bankAtm, double amount) {
    if (bankAtm == null) {
      System.out.println("Error: can not deposit money to non existing ATM");
      return false;
    }

    if (amount <= 0) {
      System.out.println("Error: cannot deposit money - deposit amount must be positive");
      return false;
    }

    if (!bankAtm.getIsCashDepositAvailable()) {
      System.out.println("Error: cannot deposit money - ATM aint allow deposits");
      return false;
    }

    bankAtm.setTotalMoney(bankAtm.getTotalMoney() + amount);
    // Добавить добавление денег в оффис и банк

    return true;
  }

  public boolean withdrawMoney(BankAtm bankAtm, double amount) {
    if (bankAtm == null) {
      System.out.println("Error: can not withdraw money from non existing ATM");
      return false;
    }

    if (amount <= 0) {
      System.out.println("Error: cannot withdraw money - withdraw amount must be positive");
      return false;
    }

    if (!bankAtm.getIsCashWithdrawalAvailable()) {
      System.out.println("Error: cannot withdraw money - ATM aint allow withdrawals");
      return false;
    }

    if (bankAtm.getTotalMoney() < amount) {
      System.out.println("Error: cannot withdraw money - ATM does not have enough money. Please come back later.");
      return false;
    }

    bankAtm.setTotalMoney(bankAtm.getTotalMoney() - amount);
    // Вычитать деньги из офиса и банка

    return true;
  }
}
